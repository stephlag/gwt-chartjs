package io.github.sidney3172.client.data;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Generic chart data provider. 
 *
 * @param <J> JS Object type
 */
public interface ChartDataProvider<J extends JavaScriptObject> {

    public J getData();
    
    public void reload(AsyncCallback<J> callback);
}
