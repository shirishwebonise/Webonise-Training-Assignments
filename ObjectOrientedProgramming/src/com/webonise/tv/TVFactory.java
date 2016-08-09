package com.webonise.tv;

public class TVFactory {
	private TVFactory(){};
	
	public static TV createTV(String tvType){
		switch(tvType){
		case "CRT" : return new CRTtv();
		case "LED" : return new LEDtv();
		case "LCD" : return new LCDtv();
		case "Plasma" : return new Plasmatv();
		case "OLED" : return new OLEDtv();
		}
		
		return null;
	}
}
