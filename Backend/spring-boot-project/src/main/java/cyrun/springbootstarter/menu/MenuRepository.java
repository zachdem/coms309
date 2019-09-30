package cyrun.springbootstarter.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<Item, Integer> {
	
	@Query(value = "SELECT t1.item_id, t1.location_id, t1.item_name, t1.item_price FROM items_table t1, locations_table t2 "
			+ "WHERE t1.location_id = t2.location_id "
			+ "AND t2.location_name = ?1", nativeQuery = true)
	List<Item> getMenuList(String location_name);

}
