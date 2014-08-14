package io.github.sidney3172.client.data;

import com.google.gwt.core.client.JavaScriptObject;


public class Series {

	private PieJsSeries jsObject;
	
	public Series() {
		jsObject = JavaScriptObject.createObject().cast();
	}
	
	public PieJsSeries getJsObject() {
		return jsObject;
	}
	
	static class PieJsSeries extends JavaScriptObject {
		
		protected PieJsSeries() {
			// Protected constructor (JSObject requirement)
		}
	
		public final native double getValue() /*-{
			return this.value;
		}-*/;
		
		public final native void setValue(double value) /*-{
			this.value = value;
		}-*/;
	
		public final native String getColor() /*-{
			return this.color;
		}-*/;
	
		public final native void setColor(String color) /*-{
			this.color = color;
		}-*/;
		
		public final native String getHighLight() /*-{
			return this.highlight;
		}-*/;
	
		public final native void setHighLight(String highlight) /*-{
			this.highlight = highlight;
		}-*/;
		
		public final native String getLabel() /*-{
        	return this.label;
    	}-*/;

		public final native void setLabel(String label) /*-{
        	this.label = label;
    	}-*/;
	}

	public void setColor(String color) {
		jsObject.setColor(color);
	}

	public void setValue(double value) {
		jsObject.setValue(value);
	}
}
