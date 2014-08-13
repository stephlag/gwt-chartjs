package io.github.sidney3172.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

public class DoughnutChart extends PieChart {

    private native JavaScriptObject drawDoughnut(Element canvas, JavaScriptObject data, JavaScriptObject nativeCanvas)/*-{
        if (nativeCanvas != null) {
           nativeCanvas.destroy();
        }
		return new $wnd.Chart(canvas.getContext("2d")).Doughnut(data);
    }-*/;

    @Override
    protected JavaScriptObject drawChart() {
        return drawDoughnut(canvas, provider.getData(), nativeCanvas);
    }

}
