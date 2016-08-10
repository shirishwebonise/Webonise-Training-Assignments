package com.webonise.restaurants;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

public class FoodFinder {
	private static FoodFinder finderInstance;
	private ArrayList<Restaurant> restaurants;

	public static FoodFinder buildFinder(String fileName){
		try{
			if(finderInstance == null){
				finderInstance = new FoodFinder(fileName);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return finderInstance;
	}
	
	private FoodFinder(String fileName) throws Exception{
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		
		this.restaurants = new ArrayList<Restaurant>();
		
		String line;
		while((line = reader.readLine()) != null){
			Integer restaurantId = extractIdFromLineFields(line);
			
			Restaurant restaurant = findOrCreateRestaurant(restaurantId);
			
			Meal newMeal = identifyMeal(line);
			
			restaurant.addMeal(newMeal);
			
			addOrUpdateRestaurant(restaurant);
		}
		
		reader.close();
	}


	private Integer extractIdFromLineFields(String line) {
		String[] lineFields = line.split(", ");
		String restaurantIdString = lineFields[0];
		Integer restaurantId = Integer.parseInt(restaurantIdString);
		return restaurantId;
	}
	
	private Restaurant findOrCreateRestaurant(Integer restaurantId) {
		ListIterator<Restaurant> restaurantsIterator = restaurants.listIterator();
		
		while(restaurantsIterator.hasNext()){
			Restaurant restaurant = restaurantsIterator.next();
			if(restaurant.getRestaurantId() == restaurantId){
				return restaurant;
			}
		}
		
		return new Restaurant(restaurantId); 
	}
	
	private Meal identifyMeal(String line) {
		
		String[] lineFields = line.split(", ");
		
		Double price = Double.parseDouble(lineFields[1]);
		
		ArrayList<String> mealItems = new ArrayList<String>();
		
		for(int i=2; i<lineFields.length; i++){
			mealItems.add(lineFields[i]);
		}
		
		Meal meal = new Meal(price, mealItems);
		
		return meal;
	}
	
	private void addOrUpdateRestaurant(Restaurant restaurant) {
		if(! this.restaurants.contains(restaurant)){
			this.restaurants.add(restaurant);
		}
	}

	public RestaurantsBestCombo findBestComboForUser(ArrayList<String> userItemList) {
		ListIterator<Restaurant> restaurantIterator = restaurants.listIterator();
		
		HashMap<Integer, ArrayList<Meal>> bestDealsInRestaurants = new HashMap<Integer, ArrayList<Meal>>();
			
		while(restaurantIterator.hasNext()){
			Restaurant testRestaurant = restaurantIterator.next();
			Integer testRestaurantId = testRestaurant.getRestaurantId();
			bestDealsInRestaurants.put( testRestaurantId, testRestaurant.getBestComboForItems(userItemList) );
		}
		
		RestaurantsBestCombo bestDealFromSelectedDeals = findBestDealFromSelected(bestDealsInRestaurants);
		
		return bestDealFromSelectedDeals;
	}

	private RestaurantsBestCombo findBestDealFromSelected(
			HashMap<Integer, ArrayList<Meal>> bestDealsInRestaurants) {
			
		HashMap<Integer, Double> restaurantMealTotals = new HashMap<Integer, Double>();
		
		for (Integer restId : bestDealsInRestaurants.keySet()) {
	    	ArrayList<Meal> meals = bestDealsInRestaurants.get(restId);
	        restaurantMealTotals = new HashMap<Integer, Double>();
	        restaurantMealTotals.put(restId, calculateTotal(meals));
	    }
	    
	    Integer bestDealRestaurant = getBestDealRestaurant(restaurantMealTotals);
	    
		return new RestaurantsBestCombo(bestDealRestaurant, calculateTotal(bestDealsInRestaurants.get(bestDealRestaurant)), bestDealsInRestaurants.get(bestDealRestaurant));
	}

	private Integer getBestDealRestaurant(HashMap<Integer, Double> restaurantMealTotals) {
		Integer bestRestaurantId = null;
		Double leastTotal = null;
		
	    for (Integer thisId : restaurantMealTotals.keySet()) {
	    	if(leastTotal == null || restaurantMealTotals.get(thisId)<leastTotal){
	    		leastTotal = restaurantMealTotals.get(thisId);
	    		bestRestaurantId = thisId;
	    	}
	    }
		return bestRestaurantId;
	}

	private Double calculateTotal(ArrayList<Meal> meals) {
		Double total = 0.0;
		for(Meal meal : meals){
			total += meal.getPrice();
		}
		
		return total;
	}	
}