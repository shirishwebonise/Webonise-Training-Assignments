package restaurantSearch;
import java.util.ArrayList;
import java.util.ListIterator;


public class Restaurant {
	private Integer restaurantId;
	private ArrayList<Meal> mealList;
	
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
	
	public Meal getBestMealForItems(ArrayList<String> userItemNames){
		Meal bestMealForUser = null;
		
		ListIterator<Meal> mealListIterator = this.mealList.listIterator();
		
		while(mealListIterator.hasNext()){
			Meal meal = mealListIterator.next();
			
			if(meal.containsAllItems(userItemNames)){
				if(bestMealForUser == null){
					bestMealForUser = meal;
				}
				else if(bestMealForUser.getPrice() < meal.getPrice()){
					bestMealForUser = meal;
				}
			}
		}
		
		return bestMealForUser;
	}

	public String toString(){
		String mealListString = "";
		
		for(int i=0; i<mealList.size(); i++){
			mealListString = mealListString + this.mealList.get(i).toString() + "\n";
		}
		
		return this.restaurantId + " \n" + mealListString;
	}
}
