package cyrun.springbootstarter.runner;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import cyrun.springbootstarter.user.User;


public interface RunnerRepository extends JpaRepository<Runner, Integer> {
	
	@Query(value = "SELECT runner_id, first_name, last_name, username, "
			+ " isu_id, password, routing_number, account_number, netid FROM runners_table WHERE netid = ?1", nativeQuery = true)
	Runner findByNetid(String netid);
	
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO runners_table (first_name, last_name, username, password, isu_id, routing_number, account_number, netid) "
	+ "VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8)", nativeQuery = true)
	void insertNewRunner(String first_name, String last_name, String username, String password, Integer isu_id,
			Integer routing_number, Integer account_number, String netid);
	
	@Query(value = "SELECT user_id, netid, password, first_name, last_name, "
			+ "username, routing_number, account_number, isu_id, balance FROM runners_table WHERE netid = ?1", nativeQuery = true)
	List<Runner> getRunnerList(String netid);
}


