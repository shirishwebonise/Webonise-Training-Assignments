package com.webonise.restaurants;
import java.util.ArrayList;

public class Meal {
	private Double price;
	private ArrayList<String> items;
	
	public Meal(Double price, ArrayList<String> items) {
		this.price = price;
		this.items = items;
	}

	public Double getPrice() {
		return price;
	}

	public ArrayList<String> getItems() {
		return items;
	}

	public boolean containsAllItems(ArrayList<String> userItems){
		if(this.items.containsAll(userItems)){
			return true;
		}
		return false;
	}
	
	public boolean containsItem(String item){
		if(this.items.contains(item)){
			return true;
		}
		return false;
	}
	
	@Override
	public String toString(){
		String itemString = "";
		
		for(int i=0; i<items.size(); i++){
			itemString = itemString+items.get(i) + " | ";
		}
		return price + " | " + itemString;
	}
	
}
