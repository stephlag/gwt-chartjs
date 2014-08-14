package io.github.sidney3172.client.data;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Generic chart data provider. 
 *
 * @param <D> ChartData type
 */
public interface ChartDataProvider<D extends ChartData> {

    public D getData();
    
    public void reload(AsyncCallback<D> callback);
}
