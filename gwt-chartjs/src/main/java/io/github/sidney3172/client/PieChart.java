package io.github.sidney3172.client;

import io.github.sidney3172.client.data.PieChartDataProvider;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;

public class PieChart extends Chart<PieChartDataProvider> {

    private native JavaScriptObject drawPie(Element canvas, JavaScriptObject data, JavaScriptObject nativeCanvas)/*-{
        if (nativeCanvas != null) {
           nativeCanvas.destroy();
        }

        return new $wnd.Chart(canvas.getContext("2d")).Pie(data);
    }-*/;

    private native JavaScriptObject getSegments(NativeEvent event, JavaScriptObject canvas)/*-{
		if (canvas == null || event == null)
			return null;
		return canvas.getSegmentsAtEvent(event);
    }-*/;

    @Override
    protected JavaScriptObject drawChart() {
        return drawPie(canvas, provider.getData().getJsObject(),nativeCanvas);
    }

    @Override
    protected JavaScriptObject getChartPoints(NativeEvent event) {
        return getSegments(event, nativeCanvas);
    }

}
