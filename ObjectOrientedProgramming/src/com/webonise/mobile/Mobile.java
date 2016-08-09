package com.webonise.mobile;

public class Mobile {
	
	private Phonebook phoneBook;
	
	public Mobile(){
		this.phoneBook = new Phonebook();
	}
	
	public void addContact(ContactDetails details) {
		Contact newContact = new Contact(details);
		this.phoneBook.addContact( newContact);
	}
	
	public void makeACall(String contactNumber) {
		System.out.println("Calling : " + contactNumber);
	}
	
	public void makeACall(Contact contact) {
		System.out.println("Calling : " + contact.toString());
	}

	public Contact searchContact(String name){
		Contact contact = this.phoneBook.getContactWithName(name);
		return contact;
	}
}
