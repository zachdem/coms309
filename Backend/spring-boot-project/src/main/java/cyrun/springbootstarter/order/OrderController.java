package cyrun.springbootstarter.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/orders")
	public List<Order> getAllTopics()
	{
		return orderService.getAllOrders();
	}

	@RequestMapping(method=RequestMethod.POST, value="/orders")
	public void addTopic(@RequestBody Order order)
	{
		orderService.addOrder(order);
	}

}
