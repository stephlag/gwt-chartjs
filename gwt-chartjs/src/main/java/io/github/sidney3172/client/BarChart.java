package io.github.sidney3172.client;

import io.github.sidney3172.client.data.AreaChartDataProvider;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;


public class BarChart extends Chart<AreaChartDataProvider> {
	
    private native JavaScriptObject drawBar(Element canvas, JavaScriptObject data, JavaScriptObject nativeCanvas)/*-{
        if (nativeCanvas != null) {
			nativeCanvas.destroy();
		}
		return new $wnd.Chart(canvas.getContext("2d")).Bar(data); 
	}-*/;

    protected JavaScriptObject drawChart() {
        return drawBar(canvas, getProvider().getData(), nativeCanvas);
    }

    protected JavaScriptObject getChartPoints(NativeEvent event) {
        return getBarsPoints(event, nativeCanvas);
    }

    private native JavaScriptObject getBarsPoints(NativeEvent event, JavaScriptObject canvas)/*-{
		if (canvas == null || event == null)
			return null;
		return canvas.getBarsAtEvent(event);
	}-*/;

	
}
