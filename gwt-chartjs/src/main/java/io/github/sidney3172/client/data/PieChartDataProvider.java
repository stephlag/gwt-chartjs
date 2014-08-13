package io.github.sidney3172.client.data;

import com.google.gwt.core.client.JsArray;

public interface PieChartDataProvider extends ChartDataProvider<JsArray<Series>> {
	
	public JsArray<Series> getData();
	
	//public void reload(AsyncCallback<JsArray<Series>> callback);
}
