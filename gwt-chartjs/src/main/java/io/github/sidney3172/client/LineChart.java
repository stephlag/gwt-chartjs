package io.github.sidney3172.client;

import io.github.sidney3172.client.data.AreaChartDataProvider;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;

public class LineChart extends Chart<AreaChartDataProvider> {

    private native JavaScriptObject drawLine(Element canvas, JavaScriptObject data, JavaScriptObject nativeCanvas)/*-{
        if (nativeCanvas != null) {
           nativeCanvas.destroy();
        }
        return new $wnd.Chart(canvas.getContext("2d")).Line(data);
    }-*/;

    private native JavaScriptObject getClickPoints(NativeEvent event, JavaScriptObject canvas)/*-{
		if (canvas == null || event == null)
			return null;
		return canvas.getPointsAtEvent(event);
    }-*/;

    @Override
    protected JavaScriptObject drawChart() {
        return drawLine(canvas, getProvider().getData(), nativeCanvas);
    }

    @Override
    protected JavaScriptObject getChartPoints(NativeEvent event) {
        return getClickPoints(event, nativeCanvas);
    }

}
