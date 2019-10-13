package cyrun.springbootstarter.menu;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LocationRepository extends JpaRepository<Location, Integer>{
	
	@Query(value = "SELECT t1.location_id, t1.location_name FROM locations_table t1 ", nativeQuery = true)
	List<Location> getAllLocations();

}
