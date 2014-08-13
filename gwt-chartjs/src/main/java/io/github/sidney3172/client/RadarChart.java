package io.github.sidney3172.client;

import io.github.sidney3172.client.data.AreaChartDataProvider;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;

public class RadarChart extends Chart<AreaChartDataProvider> {

    private boolean scaleShowLabels = false;

    private native JavaScriptObject drawRadar(Element canvas, JavaScriptObject data, JavaScriptObject nativeCanvas, boolean scaleShowLabels)/*-{
        if (nativeCanvas != null) {
           nativeCanvas.destroy();
        }
		
		return new $wnd.Chart(canvas.getContext("2d")).Radar(data, {
			scaleShowLabels : scaleShowLabels,
			pointLabelFontSize : 10
		});
    }-*/;

    private native JavaScriptObject getClickPoints(NativeEvent event, JavaScriptObject canvas)/*-{
		if (canvas == null || event == null)
			return null;
		return canvas.getPointsAtEvent(event);
    }-*/;

    @Override
    protected JavaScriptObject drawChart() {
        return drawRadar(canvas, provider.getData(), nativeCanvas, scaleShowLabels);
    }

    public void setScaleShowLabels(boolean scaleShowLabels) {
        this.scaleShowLabels = scaleShowLabels;
    }

    @Override
    protected JavaScriptObject getChartPoints(NativeEvent event) {
        return getClickPoints(event, nativeCanvas);
    }
}
