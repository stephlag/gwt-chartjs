/**
 * 
 */
package io.github.sidney3172.client.data;

import com.google.gwt.core.client.JsArray;

/**
 * {@link ChartData} for pie charts.
 *
 */
public class PieChartData implements ChartData {
	
	private JsArray<Series.PieJsSeries> jsObject;
	
	public PieChartData() {
		jsObject = JsArray.createArray().cast();
	}
	
	public JsArray<Series.PieJsSeries> getJsObject() {
		return jsObject;
	}

	public void addSeries(Series series) {
		jsObject.push(series.getJsObject());		
	}
	
	
}
