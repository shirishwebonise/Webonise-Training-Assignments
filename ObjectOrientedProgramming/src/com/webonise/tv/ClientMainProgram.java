package com.webonise.tv;

import java.util.ArrayList;

public class ClientMainProgram {
	public static void main(String[] args){
		ArrayList<TV> setOfTVs = new ArrayList<TV>();
		
		setOfTVs.add( TVFactory.createTV("CRT") );
		setOfTVs.add( TVFactory.createTV("LED") );
		setOfTVs.add( TVFactory.createTV("LCD") );
		setOfTVs.add( TVFactory.createTV("OLED") );
		
		for( TV tv : setOfTVs ){
			tv.displayTypeOfTv();
		}
		
		TV sampleTV = TVFactory.createTV("Plasma");
		sampleTV.channelDown();
		sampleTV.channelUp();
		sampleTV.channelUp();
		sampleTV.channelUp();
		sampleTV.channelDown();
		
		sampleTV.switchState();
		
		sampleTV.channelDown();
		sampleTV.channelDown();
		sampleTV.channelUp();
		sampleTV.channelUp();
		sampleTV.channelUp();
		sampleTV.channelDown();
		sampleTV.channelDown();
		
		sampleTV.switchState();
		
		sampleTV.channelUp();
		sampleTV.channelUp();
		sampleTV.channelDown();
		
		sampleTV.switchState();
		
		sampleTV.channelDown();
		sampleTV.channelDown();
		sampleTV.channelDown();
		sampleTV.channelDown();
		
		sampleTV.switchState();
		
		sampleTV.volumeDown();
		sampleTV.volumeDown();
		sampleTV.volumeDown();
		sampleTV.channelUp();
		sampleTV.channelUp();
		
		sampleTV.switchState();
		
		sampleTV.volumeUp();
		sampleTV.volumeUp();
		sampleTV.volumeDown();
		sampleTV.volumeDown();
		sampleTV.channelUp();
		sampleTV.channelUp();
		sampleTV.channelUp();
		sampleTV.volumeUp();
		sampleTV.volumeUp();
		sampleTV.channelUp();
	}
}
