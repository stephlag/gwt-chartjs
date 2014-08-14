package io.github.sidney3172.client;

import io.github.sidney3172.client.data.ChartData;
import io.github.sidney3172.client.data.ChartDataProvider;
import io.github.sidney3172.client.event.AnimationCompleteEvent;
import io.github.sidney3172.client.event.AnimationCompleteHandler;
import io.github.sidney3172.client.event.DataSelectionEvent;
import io.github.sidney3172.client.event.DataSelectionHandler;
import io.github.sidney3172.client.event.HasAnimationCompleteHandlers;
import io.github.sidney3172.client.event.HasDataSelectionEventHandlers;
import io.github.sidney3172.client.options.animation.AnimationOptions;
import io.github.sidney3172.client.options.animation.HasAnimation;
import io.github.sidney3172.client.resources.ChartStyle;
import io.github.sidney3172.client.resources.Resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * Base class for all chart widgets<br/>
 * Class describes generic behavior of all chart widgets
 * 
 * @author sidney3172
 *
 */
public abstract class Chart<P extends ChartDataProvider<? extends ChartData>> extends SimplePanel
		implements HasAnimationCompleteHandlers, HasAnimation, HasClickHandlers, HasDataSelectionEventHandlers {

	private static Resources resources;
	
	private boolean animationEnabled = true;
	protected AnimationOptions animationOptions;

    protected JavaScriptObject nativeCanvas;
	protected CanvasElement canvas;
	protected ChartStyle style;
	
	protected P provider;

	static{
		resources = GWT.create(Resources.class);
	}
	
	/**
	 * This constructor creates new chart instance with custom {@link ChartStyle}.
	 * @param style - new CssResource used for styling charts
	 */
	public Chart(ChartStyle style){
		setChartStyle(style);
		canvas = Document.get().createCanvasElement();
		getElement().appendChild(canvas);
        sinkEvents(Event.ONCLICK);
        addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent clickEvent) {
                JavaScriptObject data = getChartPoints(clickEvent.getNativeEvent());
                if (data != null) {
                    DataSelectionEvent.fire(Chart.this, Chart.this, data);
                }
            }
        });
	}
	
	/**
	 * Constructor creates chart with default style
	 */
	public Chart() {
		this(resources.chartStyle());
	}
	
	public final P getProvider() {
		return provider;
	}

	public final void setProvider(P provider) {
		this.provider = provider;
	}

    public final void setDataProvider(P provider) {
        setProvider(provider);
    }

	protected abstract JavaScriptObject getChartPoints(NativeEvent event);

	/**
	 * Set new style to the char widget. New style will be injected automatically.<br/>
	 * NOTICE: new style will be applied after re-drawing of chart<br/>
     * 
	 * @param style
	 */
	public void setChartStyle(ChartStyle style){
		style.ensureInjected();
		setStylePrimaryName(style.chart());
	}

    protected void processEvents(JavaScriptObject object) {
        this.nativeCanvas = object;
    }

	@Override
	protected void onAttach() {
		ChartJs.ensureInjected();
		super.onAttach();
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
            public void execute() {
                draw();
            }
        });
	}

	/**
	 * Method re-drawing chart widget without re-requesting data from data provider.<br/>
	 * To update data call {@link #reload()} method instead
	 */
	public void update() {
        if (provider == null) {
            throw new NullPointerException("Data provider is not specified before calling update()");
        }
        processEvents(drawChart());
    }
	
	/**
	 * Method requesting data from data provider and re-drawing chart.
	 */
	public void reload() {
        if (provider == null) {
            throw new NullPointerException(getClass().getName() + " is not initialized before invoking reload()");
        }
        //TODO: show loading to user

        provider.reload(new ProviderAsyncCallBack());
    }
	
	private class ProviderAsyncCallBack<T extends ChartData> implements AsyncCallback<T>	{

		ProviderAsyncCallBack(){
		}
		
		public void onSuccess(T result) {
		    processEvents(drawChart());
		}
		
		public void onFailure(Throwable caught) {
			// TODO Handle failures
		}
		
	}
	
	
	/**
	 * Method preparing data and invoking native draw method.
	 */
	protected final void draw() {
        reload();
    }
	
	protected abstract JavaScriptObject drawChart();
	
	/**
	 * Method sets pixel width of chart area.
	 * @param width - width in pixels
	 * TODO: replace it with generic {@link #setWidth(String)} and {@link #setSize(String, String)}
	 */
	public void setPixelWidth(int width) {
		canvas.setWidth(width);
	}

    public void setWidth(String width) {
        canvas.getStyle().setProperty("width", width);
    }

    public void setHeight(String height){
        canvas.getStyle().setProperty("height", height);
    }

	/**
	 * Method sets pixel height of chart area
	 * @param height - height in pixels
	 * TODO: replace it with generic {@link #setHeight(String)} and {@link #setSize(String, String)}
	 */
	public void setPixelHeight(int height) {
		canvas.setHeight(height);
	}
	
	@Override
	public void addAnimationCompleteHandler(AnimationCompleteHandler handler) {
		addHandler(handler, AnimationCompleteEvent.getType());
	}
	
	@Override
	public void setAnimationEnabled(boolean enable) {
		
	}

    /**
     * Creates snapshot of current state of chart as image
	 *
     * @return Image object or null if Chart not rendered (or in progress)
     */
    public Image getSnapshot(){
        String code= getBase64Image(nativeCanvas);
        if(code == null)
            return null;
        Image image = new Image(code);
        return image;
    }

    private native String getBase64Image(JavaScriptObject nativeCanvas)/*-{
        if(nativeCanvas != null)
            return nativeCanvas.toBase64Image();
        return null;
    }-*/;
	
	@Override
	public boolean isAnimationEnabled() {
		return animationEnabled;
	}
	
	@Override
	public void setAnimationOptions(AnimationOptions options) {
		this.animationOptions = options;
		if(animationEnabled && animationOptions == null)
			animationOptions = new AnimationOptions();
	}

    @Override
    /**
     * Important Note : clickHandler added internally by default to handle DataSelectionEvent.<br>
     * In case external clickHandler destroying chart (eg update() method invoked) this will lead
     * to DataSelectionEvent won't be created
     */
    public HandlerRegistration addClickHandler(ClickHandler clickHandler) {
        return addHandler(clickHandler, ClickEvent.getType());
    }

    @Override
    public HandlerRegistration addDataSelectionHandler(DataSelectionHandler handler) {
        return addHandler(handler, DataSelectionEvent.getType());
    }
}
