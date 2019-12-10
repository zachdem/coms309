package cyrun.springbootstarter.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import cyrun.springbootstarter.menu.Item;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query(value = "SELECT user_id, netid, password, first_name, last_name, "
			+ "username, routing_number, account_number, isu_id, balance FROM users_table WHERE netid = ?1", nativeQuery = true)
	User findByNetid(String netid);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO users_table (first_name, last_name, username, password, isu_id, routing_number, account_number, netid, balance) VALUES "
			+ "(?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, 500.00)", nativeQuery = true)
	void insertNewUser(String first_name, String last_name, String username, String password, Integer isu_id,
			Integer routing_number, Integer account_number, String netid);
	
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE users_table SET balance = (balance - ?1)\r\n" + 
			"WHERE netid =  ?2 ",nativeQuery = true)
	public void deductBalance(Double amount, String netid);
	
	@Modifying
	@Transactional 
	@Query(value = "UPDATE users_table SET password = ?2, routing_number = ?3, account_number = ?4 " +
	"WHERE netid = ?1", nativeQuery = true)
	void updateSettings(String netid, String password, Integer routing_number, Integer account_number);
	
	@Query(value = "SELECT user_id, netid, password, first_name, last_name, "
			+ "username, routing_number, account_number, isu_id, balance FROM users_table WHERE netid = ?1", nativeQuery = true)
	List<User> getUserList(String netid);
}
