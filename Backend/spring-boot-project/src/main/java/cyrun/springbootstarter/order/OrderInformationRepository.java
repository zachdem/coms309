package cyrun.springbootstarter.order;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface OrderInformationRepository extends JpaRepository<OrderInformation, Integer> {
	
	@Query(value = "SELECT t1.first_name, t1.last_name, t2.order_id, t3.item_name, t3.item_price, t4.location_name "
			+ "FROM users_table t1, orders_table t2, items_table t3, locations_table t4 " + 
			"WHERE t1.user_id = t2.customer_id " + 
			"AND t2.item_id = t3.item_id " + 
			"AND t3.location_id = t4.location_id " + 
			"AND t2.pending_order = 'Yes'", nativeQuery = true)
	public List<OrderInformation> getActiveOrders();
	

}
