package io.github.sidney3172.demo.client;

import io.github.sidney3172.client.data.PieChartData;
import io.github.sidney3172.client.data.PieChartDataProvider;
import io.github.sidney3172.client.data.Series;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class TestPieChartDataProvider implements PieChartDataProvider {

	private PieChartData data;

	public PieChartData getData() {
		data = getSeries();
        return data;
	}
	
	@Override
	public void reload(AsyncCallback<PieChartData> callback) {
		data = getSeries();
		callback.onSuccess(data);
	}

	private PieChartData getSeries(){
	    PieChartData pieChartData = new PieChartData();
		
		Series series1 = new Series();
		series1.setColor("#D97041");
		series1.setValue(Math.random());
		pieChartData.addSeries(series1);

		Series series2 = new Series();
		series2.setColor("#C7604C");
		series2.setValue(Math.random());
		pieChartData.addSeries(series2);

		Series series3 = new Series();
		series3.setColor("#21323D");
		series3.setValue(Math.random());
		pieChartData.addSeries(series3);

		Series series4 = new Series();
		series4.setColor("#9D9B7F");
		series4.setValue(Math.random());
		pieChartData.addSeries(series4);

		return pieChartData;
	}

}