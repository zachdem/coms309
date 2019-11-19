package cyrun.springbootstarter.order;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
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
		
		orderRepository.sendOrderItem(item_name, location_name, netid);
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
		//System.out.println(orderRepository.getActiveOrders());
		return "received";
	}
}
