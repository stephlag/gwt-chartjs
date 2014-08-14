package io.github.sidney3172.demo.client;

import io.github.sidney3172.client.data.AreaChartData;
import io.github.sidney3172.client.data.AreaChartDataProvider;
import io.github.sidney3172.client.data.AreaSeries;
import io.github.sidney3172.client.data.SeriesBuilder;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class TestAreaChartDataProvider implements AreaChartDataProvider {

    private AreaChartData data;

    @Override
    public AreaChartData getData() {
        data = createChartData();
        return data;
    }

    @Override
    public void reload(AsyncCallback<AreaChartData> callback) {
        data = createChartData();
        callback.onSuccess(data);
    }

    private AreaChartData createChartData(){
        AreaChartData data = new AreaChartData();
        data.setLabels(new String[] {"January","February","March","April","May","June","July"});
        data.setSeries(createSeries());
        return data;
    }

    private List<AreaSeries> createSeries() {
        List<AreaSeries> series = new ArrayList<AreaSeries>();

        series.add( SeriesBuilder
                .create()
                .withLabel("Serie 1")
                .withFillColor("rgba(220,220,220,0.5)")
                .withStoreColor("rgba(220,220,220,1)")
                .withPointColor("rgba(220,220,220,1)")
                .withPointStrokeColor("#fff")
                .withData(getRandomDigits())
                .get());
        series.add(SeriesBuilder
                .create()
                .withLabel("Serie 2")
                .withFillColor("rgba(151,187,205,0.5)")
                .withStoreColor("rgba(151,187,205,1)")
                .withPointColor("rgba(151,187,205,1)")
                .withPointStrokeColor("#fff")
                .withData(getRandomDigits())
                .get());

        return series;
    }

    private double[] getRandomDigits(){
        return new double[] { Random.nextInt(400), Random.nextInt(400), Random.nextInt(400), Random.nextInt(400), Random.nextInt(400), Random.nextInt(400), Random.nextInt(400) };
    }

}