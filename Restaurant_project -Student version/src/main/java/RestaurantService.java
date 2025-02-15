import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();



    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException{
        //Completed method code
        for(Restaurant restaurant: getRestaurants()) {
            if(restaurant.getName().equals(restaurantName))
                return restaurant;
        }
        throw new restaurantNotFoundException("Error: Restaurant could not be found");
    }


    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    private static List<String> selectedItems = new ArrayList<>();

    public List<String> getSelectedItems() {
        return selectedItems;
    }

    public void selectMenuItem(String itemName) {
        selectedItems.add(itemName);
    }

}
