import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE
    LocalTime openingTime = LocalTime.parse("10:30:00");
    LocalTime closingTime = LocalTime.parse("22:00:00");

    public void initRestaurant() {
        restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //Completed test case code

        //This code would also work at all the System times and without using Mockito
        /*LocalTime openingTime = LocalTime.parse("00:00:00");
        LocalTime closingTime = LocalTime.parse("23:59:59");
        Restaurant restaurant = new Restaurant("Test", "Pune", openingTime, closingTime);
        boolean isOpen = restaurant.isRestaurantOpen();
        assertEquals(true, isOpen);*/

        //Making use of Mockito
        LocalTime mockTime = LocalTime.parse("12:00:00");
        Restaurant restaurant = Mockito.spy(new Restaurant("Test", "Pune", openingTime, closingTime));
        Mockito.when(restaurant.getCurrentTime()).thenReturn(mockTime);
        boolean isOpen = restaurant.isRestaurantOpen();
        assertTrue(isOpen);
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //Completed test case code

        //This code would also work at all the System times and without using Mockito
        /*LocalTime openingTime = LocalTime.parse("23:59:59");
        LocalTime closingTime = LocalTime.parse("00:00:00");
        Restaurant restaurant = new Restaurant("Test", "Pune", openingTime, closingTime);
        boolean isOpen = restaurant.isRestaurantOpen();
        assertEquals(false, isOpen);*/

        //Making use of Mockito
        LocalTime mockTime = LocalTime.parse("09:00:00");
        Restaurant restaurant = Mockito.spy(new Restaurant("Test", "Pune", openingTime, closingTime));
        Mockito.when(restaurant.getCurrentTime()).thenReturn(mockTime);
        boolean isOpen = restaurant.isRestaurantOpen();
        assertFalse(isOpen);

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        initRestaurant();

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }


    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        initRestaurant();

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        initRestaurant();

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}