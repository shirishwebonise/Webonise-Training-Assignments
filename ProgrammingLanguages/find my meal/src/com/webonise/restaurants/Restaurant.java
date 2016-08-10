package com.webonise.restaurants;
import java.util.ArrayList;
import java.util.ListIterator;

public class Restaurant {
	private Integer restaurantId;
	private ArrayList<Meal> mealList;
	//private ArrayList<MealCombo> mealCombinations;
	
	public Restaurant(Integer restaurantId) {
		this.restaurantId = restaurantId;
		this.mealList = new ArrayList<Meal>();
	}

	public ArrayList<Meal> getMealList() {
		return this.mealList;
	}

	public void addMeal(Meal newMeal) {
		this.mealList.add(newMeal);
	}

	public Integer getRestaurantId() {
		return this.restaurantId;
	}
	
	public ArrayList<Meal> getBestComboForItems(ArrayList<String> userItemNames){
		
		ArrayList<Meal> filteredMeals = filterMeals(userItemNames);
		
		ArrayList<Meal> bestCombo = calculateBestComboUsingBinarySieve(
				userItemNames, filteredMeals);

		return bestCombo;
	}

	private ArrayList<Meal> calculateBestComboUsingBinarySieve(
			ArrayList<String> userItemNames, ArrayList<Meal> filteredMeals) {
		ArrayList<Meal> bestCombo = new ArrayList<Meal>();
		Double bestTotal = 0.0;
		for(Integer i=1; i<=Math.pow(2, filteredMeals.size()); i++){
			String binarySieveString = Integer.toBinaryString(i);
			
			@SuppressWarnings("unchecked")
			ArrayList<Meal> meals = (ArrayList<Meal>)filteredMeals.clone();
			
			for(int j=0; j<binarySieveString.length(); j++){
				if(binarySieveString.charAt(binarySieveString.length()-1-j)=='0'){
					meals.set(meals.size()-j-1, null);
				}
			}
			
			meals = filterNullsFromCombo(meals);
			
			ArrayList<String> comboListItems = new ArrayList<String>();
			for(Meal meal : meals){
				if(meal!=null){
					comboListItems.addAll(meal.getItems());
				}
			}
			
			if(comboListItems.containsAll(userItemNames)){
				Double total = 0.0;
				for(int k=0; k<meals.size(); k++){
					total += meals.get(k).getPrice();
				}
				if( bestCombo.size()==0 || total < bestTotal ){
					bestCombo = meals;
					bestTotal = total;
				}
			}
		}
		return bestCombo;
	}

	private ArrayList<Meal> filterNullsFromCombo(ArrayList<Meal> bestCombo) {
		ArrayList<Meal> combo = new ArrayList<Meal>();
		
		for(Meal meal : bestCombo){
			if(meal!=null){
				combo.add(meal);
			}
		}
		return combo;
	}

	private ArrayList<Meal> filterMeals(ArrayList<String> userItemNames) {
		@SuppressWarnings("unchecked")
		ArrayList<Meal> copyOfMealList = (ArrayList<Meal>)this.mealList.clone();
		ListIterator<Meal> mealListIterator = this.mealList.listIterator();
		
		while(mealListIterator.hasNext()){
			Meal meal = mealListIterator.next();
			Boolean meaContainsAUserItem = false;
			
			for(String userItem : userItemNames){
				if(meal.containsItem(userItem)){
					meaContainsAUserItem = true;
					break;
				}
			}
			
			if(!meaContainsAUserItem){
				copyOfMealList.remove(meal);
			}
		}
		
		return copyOfMealList;
	}

	public String toString(){
		String mealListString = "";
		
		for(int i=0; i<mealList.size(); i++){
			mealListString = mealListString + this.mealList.get(i).toString() + "\n";
		}
		
		return this.restaurantId + " \n" + mealListString;
	}
}
