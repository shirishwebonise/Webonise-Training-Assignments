package com.webonise.mobile;

import java.util.ArrayList;

public class Phonebook {
	ArrayList<Contact> contacts = new ArrayList<Contact>();
	
	public void addContact(Contact contact){
		this.contacts.add(contact);
	}

	public Contact getContactWithName(String name) {
		Contact contact = findContactWithName(name);
		return contact;
	}

	private Contact findContactWithName(String searchName) {
		Contact resultContact = null;
		for( Contact testContact : this.contacts){
			String testContactName = testContact.getName();
			if( testContactName.equals(searchName) ){
				resultContact = testContact;
			} 
		}
		return resultContact;
	}

}
