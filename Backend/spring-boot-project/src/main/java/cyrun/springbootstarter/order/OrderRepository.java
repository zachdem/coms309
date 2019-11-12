package cyrun.springbootstarter.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	@Query(value = "SELECT t1.order_id, t1.customer_id, t1.runner_id, t1.pending_order, t1.item_id, t1.order_finalized, t1.delivery_address FROM orders_table t1, users_table t2 " + 
			"WHERE t1.customer_id = t2.user_id " + 
			"AND t2.netid =  ?1", nativeQuery = true)
	public List<Order> getUserOrders(String netid);

}
