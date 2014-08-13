package io.github.sidney3172.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import io.github.sidney3172.client.data.PieChartDataProvider;
import io.github.sidney3172.client.data.Series;


public class PolarAreaChart extends Chart {
	
	private PieChartDataProvider provider;
	
	@Override
	public void draw() {
		reload();
	}
	
	private native JavaScriptObject drawPolarArea(Element canvas, JavaScriptObject data, JavaScriptObject nativeCanvas)/*-{
        if (typeof nativeCanvas != "undefined") {
           nativeCanvas.destroy();
        }
		return new $wnd.Chart(canvas.getContext("2d")).PolarArea(data);
	}-*/;

	@Override
	public void update() {
		if(provider == null)
			throw new NullPointerException("PieChartDataProvider is not initialized before invoking update()");
        nativeCanvas = drawPolarArea(canvas, provider.getData(), nativeCanvas);
	}

	@Override
	public void reload() {
		if(provider == null)
			throw new NullPointerException("PieChartDataProvider is not initialized before invoking reload()");
		
		
		//TODO: show loading to user
		
		provider.reload(new AsyncCallback<JsArray<Series>>() {
			
			@Override
			public void onSuccess(JsArray<Series> result) {
                nativeCanvas = drawPolarArea(canvas, provider.getData(), nativeCanvas);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void setDataProvider(PieChartDataProvider provider){
		this.provider = provider;
	}
}
