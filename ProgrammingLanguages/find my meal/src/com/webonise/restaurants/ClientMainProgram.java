package com.webonise.restaurants;
import java.util.ArrayList;

public class ClientMainProgram {
	
	public static void main(String[] arguments) {
		
		String csvFileName = getFileNameFromArguments(arguments);
		
		FoodFinder foodFinder = FoodFinder.buildFinder(csvFileName);
		
	    ArrayList<String> userItemList = getUserItemList(arguments);
		
	    RestaurantsBestCombo bestMealCombo = foodFinder.findBestComboForUser(userItemList);
		
		displayMeals(bestMealCombo);
	}	

	private static String getFileNameFromArguments(String[] arguments){
		if(arguments.length > 1)
			return arguments[0];
		return null;
	}

	private static ArrayList<String> getUserItemList(String[] arguments) {
		ArrayList<String> userItems = new ArrayList<String>();
		
		for(int i=1; i<arguments.length; i++){
			userItems.add(arguments[i]);
		}
		
		return userItems;
	}
	
	private static void displayMeals(RestaurantsBestCombo bestMealCombo) {
		System.out.println("--------------------------------------------------");
		System.out.println(bestMealCombo.toString());
	}
}
