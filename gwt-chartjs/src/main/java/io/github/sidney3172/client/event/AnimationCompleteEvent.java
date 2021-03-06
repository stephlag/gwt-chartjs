package io.github.sidney3172.client.event;

import io.github.sidney3172.client.Chart;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Class represents event occurred when {@link Chart} animation has been finished
 * @author sidney3172
 *
 */
public class AnimationCompleteEvent extends GwtEvent<AnimationCompleteHandler>{

	private static Type<AnimationCompleteHandler> TYPE = new Type<AnimationCompleteHandler>();
	
	private Object sender;
	
	protected AnimationCompleteEvent(Object sender){
		this.sender = sender;
	}

	public Object getSender(){
		return sender;
	}
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<AnimationCompleteHandler> getAssociatedType() {
		return TYPE;
	}
	
	public static Type<AnimationCompleteHandler> getType(){
		return TYPE;
	}

	@Override
	protected void dispatch(AnimationCompleteHandler handler) {
		handler.onAnimationComplete(this);
	}

	  public static void fire(HasAnimationCompleteHandlers source, Object sender) {
		  AnimationCompleteEvent event = new AnimationCompleteEvent(sender);
		  source.fireEvent(event);
	  }
}
