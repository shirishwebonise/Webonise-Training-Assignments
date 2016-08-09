package com.webonise.login;

public class ClientMainProgram {

	public static void main(String[] args) {
		
		LoginHelper loginHelper = LoginHelperFactory.createLoginHelper("linkedIn");
		
		loginHelper.login();
		
	}

}
