package Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cyrun.springbootstarter.menu.Item;
import cyrun.springbootstarter.menu.Location;
import cyrun.springbootstarter.menu.LocationRepository;
import cyrun.springbootstarter.menu.MenuRepository;
import cyrun.springbootstarter.menu.MenuService;
public class MenuServiceTest {


	@Mock
	LocationRepository locationRepo;
	
	@Mock
	MenuRepository menuRepo;

	@InjectMocks
	MenuService menuService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	/**
	 * Test that verifies getMenuListService in the MenuService
	 */
	@Test
	public void getMenuListTest() {
		
		// Create a new list
		List<Item> itemList = new ArrayList<>();

		String locationName = "clydes";
		// Add some items to the list
		Item item1 = new Item(1, 1, "Hamburger", 5.0);
		Item item2 = new Item(2, 1, "Salad", 6.0);
		
		itemList.add(item1);
		itemList.add(item2);

		when(menuRepo.getMenuList(locationName)).thenReturn(itemList);

		//Call the service to get return list
		List<Item> returnList = menuService.getMenuListService(locationName);
		
		//Verify list values are still the same after getting from service
		for (int i = 0; i < returnList.size(); i++) {
			assertEquals(itemList.get(i).getItem_id(), returnList.get(i).getItem_id());
			assertEquals(itemList.get(i).getItem_name(), returnList.get(i).getItem_name());
			assertEquals(itemList.get(i).getLocation_id(), returnList.get(i).getLocation_id());
		}

	}
	
	
	/**
	 * Test that verifies getLocationsService in the MenuService
	 */
	@Test
	public void getLocationsTest() {
		
		// Create a new list
		List<Location> locationList = new ArrayList<>();

		// Add some items to the list
		Location location1 = new Location(1, "Clydes");
		Location location2 = new Location(2, "West Side Market");
		
		locationList.add(location1);
		locationList.add(location2);

		// Call repo and get orders with customer id 1
		when(locationRepo.getAllLocations()).thenReturn(locationList);

		//Call the service to get return list
		List<Location> returnList = menuService.getLocationsService();
		
		//Verify list values are still the same after getting from service
		for (int i = 0; i < returnList.size(); i++) {
			assertEquals(locationList.get(i).getLocation_id(), returnList.get(i).getLocation_id());
			assertEquals(locationList.get(i).getLocation_name(), returnList.get(i).getLocation_name());
		}

	}
}
