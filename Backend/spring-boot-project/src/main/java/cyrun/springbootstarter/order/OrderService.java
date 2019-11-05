package cyrun.springbootstarter.order;

import java.util.List;

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
}
