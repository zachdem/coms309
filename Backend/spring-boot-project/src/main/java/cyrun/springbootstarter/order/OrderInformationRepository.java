package cyrun.springbootstarter.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderInformationRepository extends JpaRepository<OrderInformation, Integer> {

	@Query(value = "SELECT t1.first_name, t1.last_name, t2.order_id, t3.item_name, t3.item_price, t4.location_name, null runner_first_name, null runner_last_name, null pending_order, t1.netid user_netid "
			+ "FROM users_table t1, orders_table t2, items_table t3, locations_table t4 " + 
			"WHERE t1.user_id = t2.customer_id " + 
			"AND t2.item_id = t3.item_id " + 
			"AND t3.location_id = t4.location_id " + 
			"AND t2.pending_order = 'Yes'", nativeQuery = true)
	public List<OrderInformation> getActiveOrders();
	
	@Query(value = "SELECT t2.first_name, t2.last_name, t1.order_id, t3.item_name, t3.item_price, t4.location_name, t5.first_name runner_first_name, t5.last_name runner_last_name, t1.pending_order, null user_netid   \r\n" + 
			"FROM orders_table t1\r\n" + 
			"JOIN users_table t2 ON t1.customer_id = t2.user_id\r\n" + 
			"JOIN items_table t3 ON t1.item_id = t3.item_id\r\n" + 
			"JOIN locations_table t4 ON t3.location_id = t4.location_id\r\n" + 
			"LEFT JOIN runners_table t5 ON t1.runner_id = t5.runner_id\r\n" + 
			"WHERE t1.order_id = ?1\r\n" + 
			"", nativeQuery = true)
	public OrderInformation getSingleOrderInformation(Integer orderID);
}
