package restaurantSearch;
import java.util.ArrayList;


public class Meal {
	private Double price;
	private ArrayList<String> items;
	
	
	public Meal(Double price, ArrayList<String> items) {
		this.price = price;
		this.items = items;
	}


	public Double getPrice() {
		return price;
	}

	public ArrayList<String> getItems() {
		return items;
	}

	
}
