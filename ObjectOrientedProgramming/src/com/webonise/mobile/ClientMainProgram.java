package com.webonise.mobile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientMainProgram {

	public static void main(String[] args) {
		
		Mobile mobile = new Mobile();
		
		System.out.println("Creating a contact...");
		ContactDetails contactDetails = getContactDetailsAndCreateContact();
		mobile.addContact(contactDetails);
		System.out.println("Contact created...");
		
		System.out.println("\n\nSearching for contact with name: joey");
		Contact contact = mobile.searchContact("joey");
		if(contact==null){
			System.out.println("Contact not found");
		}
		else{
			System.out.println("Contact found => " + contact);
		}
		
		System.out.println("\n\nMaking a call to contact: " + contact);
		mobile.makeACall(contact);
	}

	private static ContactDetails getContactDetailsAndCreateContact() {
		ContactDetails contactDetails = new ContactDetails();
		
		promptUser("Enter your phone number: ");
		contactDetails.contactNumber = getNumberFromUser();
		
		promptUser("Enter your name: ");
		contactDetails.name = getNameFromUser();
		
		return contactDetails;
	}
	
	private static String getNumberFromUser() {
		String contactNumber = getLineOfInputFromStdIn();
		return contactNumber;
	}

	private static void promptUser(String text) {
		System.out.print(text);
	}
	
	private static String getLineOfInputFromStdIn() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		String name = null;
		try {
			name = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return name;
	}
	
	private static String getNameFromUser() {
		String contactName = getLineOfInputFromStdIn();
		return contactName;
	}
}
