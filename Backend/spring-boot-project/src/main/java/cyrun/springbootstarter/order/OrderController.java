package cyrun.springbootstarter.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
	
		@Autowired
		private OrderService orderService;
		
		@RequestMapping(method = RequestMethod.GET, value = "/orders/{netid}")
		public List<Order> getMenu(@PathVariable String netid) {
			return orderService.getUserOrders(netid);

		}
		
		@RequestMapping(method = RequestMethod.POST, value = "/orders/place_order")
		public void placeOrder(@RequestBody String order) {
			System.out.println(order);
		}

}
