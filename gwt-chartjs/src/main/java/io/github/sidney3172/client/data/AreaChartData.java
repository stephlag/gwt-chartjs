package io.github.sidney3172.client.data;

import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayString;

public class AreaChartData implements ChartData {
	
	private AreaJsChartData jsObject;

	public AreaChartData() {
		jsObject = JavaScriptObject.createObject().cast();
	}
	
	public JavaScriptObject getJsObject() {
		return jsObject;
	}
	
	public final void setLabels(String[] labels){
		JsArrayString array = JsArrayString.createArray().cast();
		for(String str : labels) {
			array.push(str);
		}
		jsObject.setLabels(array);
	}
	
	public final void setSeries(List<AreaSeries> series)	{
		JsArray<AreaSeries.AreaJsSeries> jsSeries = JavaScriptObject.createArray().cast();
		for(AreaSeries serie : series)	{
			jsSeries.push(serie.getJsObject());
		}
		jsObject.setSeries(jsSeries);
	}
	
	private static class AreaJsChartData extends JavaScriptObject {
	
		protected AreaJsChartData() {
			// Protected constructor (JSObject requirement)
		}
		
		private final native void setLabels(JsArrayString labels) /*-{
			this.labels = labels;
		}-*/;
	
		private final native JsArray<AreaSeries.AreaJsSeries> getSeries() /*-{
			return this.datasets;
		}-*/;
	
		private final native void setSeries(JsArray<AreaSeries.AreaJsSeries> series) /*-{
			this.datasets = series;
		}-*/;
	}
}
