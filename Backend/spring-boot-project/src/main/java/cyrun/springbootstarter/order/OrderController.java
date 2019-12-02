package cyrun.springbootstarter.order;

import org.json.JSONArray;
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
		public String getMenu(@PathVariable String netid) {
			JSONArray orderList = new JSONArray(orderService.getUserOrders(netid));
			System.out.println(orderList.toString());
			return orderList.toString();
		}
		
		
		@RequestMapping(method = RequestMethod.GET, value = "/orders/singleorder/{orderid}")
		public String getSingleOrderInfo(@PathVariable Integer orderid) {
			return orderService.getSingleOrderInformation(orderid);
		}
		
		@RequestMapping(method = RequestMethod.POST, value = "/orders/place_order")
		public String placeOrder(@RequestBody String order) {
			System.out.println(order);
			orderService.placeOrder(order);
			return "received";
		}
		
		@RequestMapping(method = RequestMethod.GET , value = "/orders/active_orders")
		public String getActiveOrders() {
			return orderService.getActiveOrders();
		}
		
		@RequestMapping(method = RequestMethod.POST, value = "/orders/updateRunner")
		public String updateRunners(@RequestBody String order)
		{
			orderService.updateRunner(order);
			return "received";
		}
		
}
