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
	}
	
	private static ArrayList<String> getUsersListOfItems(String[] arguments) {
		
		System.out.println("getting user's list of items");
		ArrayList<String> userItems = new ArrayList<String>();
	    
		for(int i=1; i<arguments.length; i++){
			userItems.add(arguments[i]);
		}
		
		return userItems;
	}

	private static void checkRestaurantsForItems(ArrayList<String> userItemList) {
		ListIterator<Restaurant> restaurantIterator = restaurants.listIterator();
		
		
		while(restaurantIterator.hasNext()){
			Restaurant testRestaurant = restaurantIterator.next();
			
			System.out.println("\n\nchecking restaurant with ID "+ testRestaurant.getRestaurantId());
			
			ArrayList<Meal> restaurantMealList = testRestaurant.getMealList();
			checkIfItemsInList(restaurantMealList, userItemList);
		}
	}

	@SuppressWarnings("unchecked")
	private static void checkIfItemsInList(ArrayList<Meal> restaurantMealList,
			ArrayList<String> userItemList) {
		ListIterator restaurantMealListIterator = restaurantMealList.listIterator();
		ListIterator userItemListIterator = userItemList.listIterator();
		
		while(userItemListIterator.hasNext()){
			String aUserItem = (String)userItemListIterator.next();
			
			while(restaurantMealListIterator.hasNext()){
				
				Meal aMealFromRestaurant = (Meal)restaurantMealListIterator.next();
				ArrayList<String> mealItems = aMealFromRestaurant.getItems();
				ListIterator mealItemsIterator = mealItems.listIterator();
				
				int singleMealAvailabilityCounter = 0;
				while(mealItemsIterator.hasNext()){
					
					String anItemFromMeal = (String)mealItemsIterator.next();
					
					System.out.println(anItemFromMeal+ " " +aUserItem);
					
					if(anItemFromMeal.equals(aUserItem)){
						singleMealAvailabilityCounter++;
						System.out.println("matched");
					}
				}
				if(singleMealAvailabilityCounter == userItemList.size()){
					System.out.println("All available in one meal at prize " + aMealFromRestaurant.getPrice());
				}
			}	
		}
	}

	private static String getFileNameFromCLArguments(String[] arguments){
		if(arguments.length > 1)
			return arguments[0];
		return null;
	}
	
	private static void createRestaurantsFromFile(String inputFileName) {
		if(inputFileName != null){
			BufferedReader inputReader = createFileReader(inputFileName);
			
			try{
				while (inputReader.readLine() != null) {
			        String line = inputReader.readLine();
			        createMealsAndORRestaurant(line);		    
			    }
			}catch(Exception e){
				System.out.println(e);
			}
		}
	}


	private static void createMealsAndORRestaurant(String line) {
				
		String[] lineColumns = line.split(", ");
        
        Integer restaurantId = Integer.parseInt(lineColumns[0]);
        
        Restaurant restaurant = findRestaurant(restaurantId);
        
        if(restaurant == null){
        	restaurant = new Restaurant(restaurantId);
        	restaurants.add(restaurant);
        }
        Double mealPrice = Double.parseDouble(lineColumns[1]);
        
        ArrayList<String> mealItems = new ArrayList<String>();
        for(int i=2; i<lineColumns.length; i++){
        	mealItems.add(lineColumns[i]);
        }
       
        Meal newMeal = new Meal(mealPrice, mealItems);
        
        restaurant.addMeal(newMeal);
        
	}


	private static BufferedReader createFileReader(String inputFileName) {
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
