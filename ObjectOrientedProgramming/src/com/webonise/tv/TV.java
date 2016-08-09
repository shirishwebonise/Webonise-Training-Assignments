package com.webonise.tv;

public abstract class TV implements Context{
	private WorkingState state;
	private String TYPE_OF_TV;
	private Integer channelNo;
	private Integer channelUpperLimit = 512;
	private Integer channelLowerLimit = 0;
	private Integer volume;
	private Integer volumeUpperLimit = 50;
	private Integer volumeLoweLimit = 0;
	
	public TV(String type){
		this.TYPE_OF_TV = type;
		this.channelNo = 0;
		this.volume = 0;
		this.state = new OffState();
	}
	
	public void displayTypeOfTv(){ 
		System.out.println("Type of TV: " + TYPE_OF_TV);
	};
	
	public void setState(WorkingState state){
		this.state = state;
	}
	
	public void switchState(){
		this.state.switchState(this);
	}
	
	public void channelUp(){
		if( this.state.isOn() ){
			incrementChannel();
			printChannel();
		}
	}

	private void printChannel() {
		System.out.println("Channel : " + this.channelNo);
	}

	private void incrementChannel() {
		if( this.channelNo < this.channelUpperLimit ){
			this.channelNo++;
		}
	}
	
	public void channelDown(){
		if(  this.state.isOn() ){
			decrementChannel();
			printChannel();
		}
	}

	private void decrementChannel() {
		if( this.channelNo > this.channelLowerLimit ){
			this.channelNo--;
		}
	}

	public void volumeDown() {
		if( this.state.isOn() ){
			decrementVolume();
			displayVolume();
		}
	}

	private void decrementVolume() {
		if( this.volume > this.volumeLoweLimit ){
			this.volume--;
		}
	}

	public void volumeUp() {
		if( this.state.isOn() ){
			incrementVolume();
			displayVolume();
		}
	}

	private void incrementVolume() {
		if( this.volume < this.volumeUpperLimit ){
			this.volume++;
		}
	}

	private void displayVolume() {
		System.out.println("Volume : " + this.volume);
	}
	
	
}
