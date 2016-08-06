package restaurantSearch;
import java.util.ArrayList;


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

	public String toString(){
		String mealListString = "";
		
		for(int i=0; i<mealList.size(); i++){
			mealListString = mealListString + this.mealList.get(i).toString() + "\n";
		}
		
		return this.restaurantId + " \n" + mealListString;
	}
}
