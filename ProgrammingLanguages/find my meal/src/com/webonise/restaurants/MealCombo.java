package com.webonise.restaurants;

import java.util.ArrayList;

public class MealCombo {
	ArrayList<Meal> meals;
	
	MealCombo(){
		this.meals =  new ArrayList<Meal>();
	}
	
	public void addMeal(Meal meal){
		this.meals.add(meal);
	}
	
	public ArrayList<Meal> getMeals(){
		return this.meals;
	}
}
