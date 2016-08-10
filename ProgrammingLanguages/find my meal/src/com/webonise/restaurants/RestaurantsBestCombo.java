package com.webonise.restaurants;

import java.util.ArrayList;

public class RestaurantsBestCombo {
	private Integer restaurantId;
	private Double amount;
	private ArrayList<Meal> meals;
	
	public RestaurantsBestCombo(Integer restaurantId, Double amount, ArrayList<Meal> meals) {
		this.restaurantId = restaurantId;
		this.amount = amount;
		this.meals = meals;
	}

	public Integer getRestaurantId() {
		return restaurantId;
	}

	public Double getAmount() {
		return amount;
	}

	public ArrayList<Meal> getMeals() {
		return meals;
	}
	
	@Override
	public String toString(){
		String mealsString = "";
		for(Meal meal : meals){
			mealsString = mealsString + "\n" + meal;
		}
		return "Resturant Id : " + this.restaurantId + "\n" + 
				"amount : " + this.amount + "\n\n" + 
				"meals" + mealsString;
	}
}
