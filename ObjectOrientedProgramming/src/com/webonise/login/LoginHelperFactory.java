package com.webonise.login;

public class LoginHelperFactory {

	public static LoginHelper createLoginHelper(String string) {
		switch(string){
			case "facebook": return new FacebookLoginHelper();
			
			case "linkedIn": return new LinkedInLoginHelper();
			
			case "googlePlus": return new GooglePlusLoginHelper();
			
			case "twitter": return new TwitterLoginHelper();
			
			case "account": return new AccountLoginHelper();
		}
		return null;
	}
	
}
