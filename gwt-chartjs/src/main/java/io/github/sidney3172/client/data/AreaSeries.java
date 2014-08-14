package io.github.sidney3172.client.data;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayNumber;


public class AreaSeries {
	
	private AreaJsSeries jsObject;
	
	protected AreaSeries() {
		jsObject = JavaScriptObject.createObject().cast();
	}
	
	public AreaJsSeries getJsObject() {
		return jsObject;
	}
	
	static class AreaJsSeries extends JavaScriptObject {
	
		protected AreaJsSeries() {
			// Protected constructor (JSObject requirement)
		}
		
		public final native String getLabel() /*-{
	        return this.label;
	    }-*/;
	
		public final native void setLabel(String label) /*-{
	        this.label = label;
	    }-*/;
		
		public final native String getFillColor() /*-{
			return this.fillColor;
		}-*/;
		
		public final native void setFillColor(String fillColor) /*-{
			this.fillColor = fillColor;
		}-*/;
	
		public final native String getStrokeColor() /*-{
			return this.strokeColor;
		}-*/;
	
		public final native void setStrokeColor(String strokeColor) /*-{
			this.strokeColor = strokeColor;
		}-*/;
	
		public final native String getPointColor() /*-{
			return this.pointColor;
		}-*/;
	
		public final native void setPointColor(String printColor) /*-{
			this.pointColor = printColor;
		}-*/;
	
		public final native String getPointStrokeColor() /*-{
			return this.pointStrokeColor;
		}-*/;
	
		public final native void setPointStrokeColor(String pointStrokeColor) /*-{
			this.pointStrokeColor = pointStrokeColor;
		}-*/;
	
		public final native String[] getData() /*-{
			return this.data;
		}-*/;
		
		private final native void setData(JsArrayNumber data) /*-{
			this.data = data;
		}-*/;
	}
	
	public final void setData(double[] data){
		JsArrayNumber array = JsArrayNumber.createArray().cast();
		for(double str : data)	{
			array.push(str);
		}
		jsObject.setData(array);
	}
	
	public final void setLabel(String label){
		jsObject.setLabel(label);
	}


	public final void setFillColor(String fillColor) {
		jsObject.setFillColor(fillColor);
	}


	public final void setStrokeColor(String strokeColor) {
		jsObject.setStrokeColor(strokeColor);
	}

	public final void setPointColor(String printColor) {
		jsObject.setPointColor(printColor);
	}

	public final void setPointStrokeColor(String pointStrokeColor) {
		jsObject.setPointStrokeColor(pointStrokeColor);		
	}
	
}