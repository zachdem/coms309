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
	private String first_name;
	private String last_name;
	private String username;
	private Integer routing_number;
	private Integer account_number;
	private Integer isu_id;

	public User() {

	}

	public User(Integer user_id, String netid, String password, String first_name, String last_name, String username,
			Integer routing_number, Integer account_number, Integer isu_id) {
		this.user_id = user_id;
		this.netid = netid;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.username = username;
		this.routing_number = routing_number;
		this.account_number = account_number;
		this.isu_id = isu_id;
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

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getRouting_number() {
		return routing_number;
	}

	public void setRouting_number(Integer routing_number) {
		this.routing_number = routing_number;
	}

	public Integer getAccount_number() {
		return account_number;
	}

	public void setAccount_number(Integer account_number) {
		this.account_number = account_number;
	}

	public Integer getIsu_id() {
		return isu_id;
	}

	public void setIsu_id(Integer isu_id) {
		this.isu_id = isu_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

}
