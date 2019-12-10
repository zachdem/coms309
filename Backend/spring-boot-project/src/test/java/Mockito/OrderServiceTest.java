package Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cyrun.springbootstarter.order.Order;
import cyrun.springbootstarter.order.OrderInformation;
import cyrun.springbootstarter.order.OrderInformationRepository;
import cyrun.springbootstarter.order.OrderRepository;
import cyrun.springbootstarter.order.OrderService;

public class OrderServiceTest {

	@Mock
	OrderRepository orderRepo;
	
	@Mock
	OrderInformationRepository orderInfoRepo;

	@InjectMocks
	OrderService orderService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	/**
	 * Test that verifies getUserOrders in OrderService
	 */
	@Test
	public void getUserOrdersTest() {
		// Create a new list
		List<Order> orderList = new ArrayList<>();

		String netid = "test_netid";
		// Add some orders for customer id 1
		Order order1 = new Order(1, 1, 1, "Yes", 1, "No", "123 Iowa State Ave.");
		Order order2 = new Order(2, 1, 2, "Yes", 2, "No", "123 Iowa State Ave.");
		Order order3 = new Order(3, 1, 3, "Yes", 2, "No", "123 Iowa State Ave.");
		orderList.add(order1);
		orderList.add(order2);
		orderList.add(order3);

		// Call repo and get orders with customer id 1
		when(orderRepo.getUserOrders(netid)).thenReturn(orderList);

		List<Order> returnList = orderService.getUserOrders(netid);
		
		for (int i = 0; i < returnList.size(); i++) {
			assertEquals(orderList.get(i).getOrder_id(), returnList.get(i).getOrder_id());
			assertEquals(orderList.get(i).getCustomer_id(), returnList.get(i).getCustomer_id());
			assertEquals(orderList.get(i).getRunner_id(), returnList.get(i).getRunner_id());
			assertEquals(orderList.get(i).isPending_order(), returnList.get(i).isPending_order());
			assertEquals(orderList.get(i).getItem_id(), returnList.get(i).getItem_id());
			assertEquals(orderList.get(i).isOrder_finalized(), returnList.get(i).isOrder_finalized());
			assertEquals(orderList.get(i).getDelivery_address(), returnList.get(i).getDelivery_address());
		}

	}
	
	/**
	 * Test that verifies getActiveOrders in OrderService
	 */
	@Test
	public void getActiveOrdersTest() {
		// Create a new list
		List<OrderInformation> orderList = new ArrayList<>();

		// Add some active orders
		OrderInformation order1 = new OrderInformation(1, "user1_first", "user1_last", "Hamburger", 12.0, "Clydes", null, null, null, null);
		OrderInformation order2 = new OrderInformation(2, "user2_first", "user2_last", "Salad", 9.5, "Clydes", null, null, null, null);
		OrderInformation order3 = new OrderInformation(3, "user3_first", "user3_last", "Chicken Strips", 10.0, "Clydes", null, null, null, null);
		orderList.add(order1);
		orderList.add(order2);
		orderList.add(order3);

		// Call repo and get list of orders
		when(orderInfoRepo.getActiveOrders()).thenReturn(orderList);

		List<OrderInformation> returnList = orderInfoRepo.getActiveOrders();
		
		//Verify that the list was returned successfully
		for (int i = 0; i < returnList.size(); i++) {
			assertEquals(orderList.get(i).getOrder_id(), returnList.get(i).getOrder_id());
			assertEquals(orderList.get(i).getFirst_name(), returnList.get(i).getFirst_name());
			assertEquals(orderList.get(i).getLast_name(), returnList.get(i).getLast_name());
			assertEquals(orderList.get(i).getItem_name(), returnList.get(i).getItem_name());
			assertEquals(orderList.get(i).getItem_price(), returnList.get(i).getItem_price());
		}
		
		//Convert to JSON Array so we can compare strings
		JSONArray arr = new JSONArray();
	    for (OrderInformation i : orderList)
	    {
	         JSONObject obj = new JSONObject();
	         obj.put("order_id", i.getOrder_id());
	         obj.put("first_name", i.getFirst_name());
	         obj.put("last_name", i.getLast_name());
	         obj.put("item_name", i.getItem_name());
	         obj.put("item_price", i.getItem_price());
	         obj.put("location_name", i.getLocation_name());
	         arr.put(obj);
	    }
	    
	    //Call get active orders and make sure it returns the same string
	    assertEquals(arr.toString(), orderService.getActiveOrders());
	}
	
	
	/**
	 * Test that verifies the getSingleOrderInformation function in OrderService
	 */
	@Test
	public void getSingleOrderTest() {
		OrderInformation orderInfo = new OrderInformation(1, "user1_first", "user1_last", "Hamburger", 12.0, "Clydes", "Test", "Runner", "No", "test_netid");
		when(orderInfoRepo.getSingleOrderInformation(1)).thenReturn(orderInfo);
		
		System.out.println(orderInfo.getUser_netid());
		String orderString = orderService.getSingleOrderInformation(1);
		System.out.println(orderString);
		JSONObject obj = new JSONObject(orderString);
	
		assertEquals((int)orderInfo.getOrder_id(),obj.getInt("order_id"));
		assertEquals(orderInfo.getFirst_name(),obj.getString("first_name"));
		assertEquals(orderInfo.getLast_name(),obj.getString("last_name"));
		assertEquals(orderInfo.getItem_name(),obj.getString("item_name"));
		assertEquals(orderInfo.getItem_price(),obj.getDouble("item_price") , 0);
		assertEquals(orderInfo.getLocation_name(),obj.getString("location_name"));
		assertEquals(orderInfo.getRunner_first_name(),obj.getString("runner_first_name"));
		assertEquals(orderInfo.getRunner_last_name(),obj.getString("runner_last_name"));
		assertEquals(orderInfo.getPending_order(),obj.getString("pending_order"));
		assertEquals(orderInfo.getUser_netid(),obj.getString("user_netid"));

	}


}
