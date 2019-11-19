package cyrun.springbootstarter.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	@Query(value = "SELECT t1.order_id, t1.customer_id, t1.runner_id, t1.pending_order, t1.item_id, t1.order_finalized, t1.delivery_address FROM orders_table t1, users_table t2 " + 
			"WHERE t1.customer_id = t2.user_id " + 
			"AND t2.netid =  ?1", nativeQuery = true)
	public List<Order> getUserOrders(String netid);
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO orders_table (item_id, customer_id)\r\n" + 
			"SELECT t2.item_id, t3.user_id from locations_table t1, items_table t2, users_table t3 " + 
			"WHERE t1.location_id = t2.location_id " + 
			"AND t1.location_name = ?2 " + 
			"AND t2.item_name = ?1 " + 
			"AND t3.netid = ?3", nativeQuery = true)
	public void sendOrderItem(String itemName, String locationName, String netid);
	

}
