package io.github.sidney3172.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

public class PolarAreaChart extends PieChart {

    private native JavaScriptObject drawPolarArea(Element canvas, JavaScriptObject data, JavaScriptObject nativeCanvas)/*-{
        if (nativeCanvas != null) {
           nativeCanvas.destroy();
        }
		return new $wnd.Chart(canvas.getContext("2d")).PolarArea(data); 
    }-*/;

    protected JavaScriptObject drawChart() {
        return drawPolarArea(canvas, provider.getData().getJsObject(), nativeCanvas);
    }

    

}
