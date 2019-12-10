package cyrun.springbootstarter.order;

import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cyrun.springbootstarter.user.UserService;
import cyrun.springbootstarter.websocket.UserHomeEndpoint;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderInformationRepository orderInfoRepo;
	
	@Autowired
	UserHomeEndpoint userHomeEndpoint;
	
	@Autowired UserService userService;
	
	
	public List<Order> getUserOrders(String netid)
	{
		return orderRepository.getUserOrders(netid);
	}
	
	public void placeOrder(String order)
	{
		JSONArray arr = new JSONArray(order);
		JSONObject orderItem = arr.getJSONObject(0);
		String item_name = orderItem.getString("item_name");
		String location_name = orderItem.getString("location_name");
		String netid = orderItem.getString("netid");
		Double total = orderItem.getDouble("item_price");
		
		orderRepository.sendOrderItem(item_name, location_name, netid);
		userService.deductUserBalance(total, netid);
		/*for(int i = 0; i < arr.length(); i++)
		{
			JSONObject orderItem = arr.getJSONObject(i);
			String item_name = orderItem.getString("item_name");
			String location_name = orderItem.getString("location_name");
			String netid = orderItem.getString("netid");
			System.out.println(item_name);
			System.out.println(location_name);
			System.out.println(netid);
		}*/
		
		System.out.println(arr);
	}
	
	
	public String getActiveOrders()
	{
		List<OrderInformation> orderInfoList = orderInfoRepo.getActiveOrders();
		
		JSONArray arr = new JSONArray();
	    for (OrderInformation i : orderInfoList)
	    {
	         JSONObject obj = new JSONObject();
	         obj.put("order_id", i.getOrder_id());
	         obj.put("first_name", i.getFirst_name());
	         obj.put("last_name", i.getLast_name());
	         obj.put("item_name", i.getItem_name());
	         obj.put("item_price", i.getItem_price());
	         obj.put("location_name", i.getLocation_name());
	         obj.put("user_netid", i.getUser_netid());
	         arr.put(obj);
	    }
	    return arr.toString();
	}
	
	
	public void updateRunner(String order) throws JSONException, IOException
	{
		JSONObject confirmation = new JSONObject(order);
		orderRepository.updateRunner(confirmation.getString("netid"), Integer.parseInt(confirmation.getString("order_id")));
		userHomeEndpoint.sendMessage("Order " + confirmation.getString("order_id") + " is confirmed for pickup!", confirmation.getString("user_netid"));
		
	}
	
	public String getSingleOrderInformation(Integer orderID){
		OrderInformation orderInfo = orderInfoRepo.getSingleOrderInformation(orderID);
		JSONObject obj = new JSONObject();
	    obj.put("order_id", orderInfo.getOrder_id());
        obj.put("first_name", orderInfo.getFirst_name());
        obj.put("last_name", orderInfo.getLast_name());
        obj.put("item_name", orderInfo.getItem_name());
        obj.put("item_price", orderInfo.getItem_price());
        obj.put("location_name", orderInfo.getLocation_name());
        obj.put("runner_first_name", orderInfo.getRunner_first_name());
        obj.put("runner_last_name", orderInfo.getRunner_last_name());
        obj.put("pending_order", orderInfo.getPending_order());
        obj.put("user_netid", orderInfo.getUser_netid());
		return obj.toString();
	}

}
