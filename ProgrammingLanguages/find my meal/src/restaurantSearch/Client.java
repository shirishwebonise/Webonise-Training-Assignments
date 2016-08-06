package restaurantSearch;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.ListIterator;

public class Client {
	private static ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
	
	public static void main(String[] args) {
		String inputFileName = getFileNameFromCLArguments(args);
		
		ArrayList<String> userItemList = getUsersListOfItems(args);
		
		createRestaurantsFromFile(inputFileName);
			
		
		if(restaurants != null){
			checkRestaurantsForItems(userItemList);
		}
		else{
			System.out.println("Restaurants not recorded");
		}
	}
	
	private static String getFileNameFromCLArguments(String[] arguments){
		if(arguments.length > 1)
			return arguments[0];
		return null;
	}
	
	private static ArrayList<String> getUsersListOfItems(String[] arguments) {
		
		ArrayList<String> userItems = new ArrayList<String>();
	    
		for(int i=1; i<arguments.length; i++){
			userItems.add(arguments[i]);
		}
		
		return userItems;
	}
	
	private static void createRestaurantsFromFile(String inputFileName) {
		if(inputFileName != null){
			BufferedReader inputReader = createFileReaderForFile(inputFileName);
			
			try{
				String line;
				while ((line = inputReader.readLine()) != null) {
			        createOrUpdateRestaurant(line);		    
			    }
			}catch(Exception e){
				System.out.println(e);
			}
		}
	}

	private static void checkRestaurantsForItems(ArrayList<String> userItemList) {
		ListIterator<Restaurant> restaurantIterator = restaurants.listIterator();
		
		Meal bestMealSelected = null;
		Restaurant restaurantWithBestDeal = null;
		
		while(restaurantIterator.hasNext()){
			Restaurant thisRestaurant = restaurantIterator.next();
			
			Meal selectedMeal = thisRestaurant.getBestMealForItems(userItemList);
			
			if(selectedMeal != null){
				if(bestMealSelected!=null){
					if(selectedMeal.getPrice() < bestMealSelected.getPrice()){
						restaurantWithBestDeal = thisRestaurant;
						bestMealSelected = selectedMeal;
					}
				}
				else{
					bestMealSelected = selectedMeal;
					restaurantWithBestDeal = thisRestaurant;
				}
			}
			else{
				System.out.println("    All items are not available in this restaurant");
			}
		}
		
		if(bestMealSelected!=null){
			System.out.println("\n\nBest matching meal : restaurant - "+restaurantWithBestDeal.getRestaurantId()+", meal - "+bestMealSelected);
		}
		else{
			System.out.println("\nAll items are not available in any restaurant");
		}
	}
	
	private static void createOrUpdateRestaurant(String line) {
				
		String[] lineFields = line.split(", ");
        
        Integer restaurantId = Integer.parseInt(lineFields[0]);
        
        Restaurant restaurant = findRestaurant(restaurantId);
        
        if(restaurant == null){
        	restaurant = new Restaurant(restaurantId);
        	restaurants.add(restaurant);
        }
                
        Double mealPrice = Double.parseDouble(lineFields[1]); 
        
        ArrayList<String> mealItems = new ArrayList<String>();
        for(int i=2; i<lineFields.length; i++){
        	mealItems.add(lineFields[i]);
        }
        
        Meal newMeal = new Meal(mealPrice, mealItems);        
        
        restaurant.addMeal(newMeal);
	}

	private static BufferedReader createFileReaderForFile(String inputFileName) {
		BufferedReader inputReader = null;
		try{
			inputReader = new BufferedReader(new FileReader(inputFileName));
		}catch(Exception e){
			System.out.print("cannot find file");
		}
		return inputReader;
	}

	private static Restaurant findRestaurant(Integer restaurantId) {
	    ListIterator<Restaurant> restaurantIterator = restaurants.listIterator();
	    
	    while(restaurantIterator.hasNext()){
	    	Restaurant restaurant = restaurantIterator.next();
	    	if(restaurant.getRestaurantId() == restaurantId)
	    		return restaurant;
	    }
		return null;
	}

}
