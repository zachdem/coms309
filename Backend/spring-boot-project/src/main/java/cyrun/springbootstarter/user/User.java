package cyrun.springbootstarter.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users_table")
public class User {

	@Id
	private Integer user_id;
	private String netid;
	private String password;
	
	public User() {
		
	}
	
	
	public User(int user_id, String netid, String password) {
		this.user_id = user_id;
		this.netid = netid;
		this.password = password;
	}
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getNetid() {
		return netid;
	}
	public void setNetid(String netid) {
		this.netid = netid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
