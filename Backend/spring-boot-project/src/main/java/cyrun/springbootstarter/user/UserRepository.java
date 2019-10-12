package cyrun.springbootstarter.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query(value = "SELECT user_id, netid, password FROM users_table WHERE NETID = ?1", nativeQuery = true)
	User findByNetid(String netid);
}
