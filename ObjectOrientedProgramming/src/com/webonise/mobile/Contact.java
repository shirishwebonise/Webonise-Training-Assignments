package com.webonise.mobile;

public class Contact {
	private ContactDetails contactDetails;

	public Contact(ContactDetails details) {
		this.contactDetails = new ContactDetails();
		this.contactDetails.contactNumber = details.contactNumber;
		this.contactDetails.name = details.name;
	}

	public String getContactNumber() {
		return this.contactDetails.contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactDetails.contactNumber = contactNumber;
	}

	public String getName() {
		return contactDetails.name;
	}

	public void setName(String name) {
		this.contactDetails.name = name;
	}
	
	@Override
	public String toString(){
		return "name: " + this.contactDetails.name + " | " + 
				"number: " + this.contactDetails.contactNumber;
	}
}
