package cyrun.springbootstarter.order;

import java.util.List;

import org.json.JSONArray;
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
		for(int i = 0; i < arr.length(); i++)
		{
			arr.getJSONArray(i);
			//Send each item of the order to the DB
		}
		System.out.println(arr);
	}
}
