package io.github.sidney3172.client.data;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Generic chart data provider. 
 *
 * @param <J> JS Object type
 */
// TODO Really use generics
public interface ChartDataProvider<J extends JavaScriptObject> {

    public JavaScriptObject getData();
    
    public void reload(AsyncCallback<JavaScriptObject> callback);
}
