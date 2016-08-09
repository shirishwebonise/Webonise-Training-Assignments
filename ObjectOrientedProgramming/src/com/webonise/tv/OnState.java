package com.webonise.tv;

public class OnState implements WorkingState {

	@Override
	public void switchState(Context c) {
		c.setState(new OffState());
	}
	
	@Override
	public boolean isOn(){
		return true;
	}
}
