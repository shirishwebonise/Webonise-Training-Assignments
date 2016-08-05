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
		return mealList;
	}

	public void addMeal(Meal newMeal) {
		this.mealList.add(newMeal);
	}
	

	public Integer getRestaurantId() {
		return restaurantId;
	}


}
