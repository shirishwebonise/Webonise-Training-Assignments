package com.webonise.tv;

public class OffState implements WorkingState {

	@Override
	public void switchState(Context c) {
		c.setState(new OnState());
	}
	
	@Override
	public boolean isOn(){
		return false;
	}

}
