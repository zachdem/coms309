package cyrun.springbootstarter.runner;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "runners_table")


public class Runner {
	
	@Id
	private int runner_id;
	private String first_name;
	private String last_name;
	private String username;
	private String password;
	private int isu_id;
	private int routing_number;
	private int account_number;
	private String netid;
	
	public Runner() {
		
	}


	public Runner(int runner_id, String first_name, String last_name, String username, String password, int isu_id,
			int routing_number, int account_number, String netid) {
		super();
		this.runner_id = runner_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.username = username;
		this.password = password;
		this.isu_id = isu_id;
		this.routing_number = routing_number;
		this.account_number = account_number;
		this.netid = netid;
	}
	

	public int getRunner_id() {
		return runner_id;
	}

	public void setRunner_id(int runner_id) {
		this.runner_id = runner_id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getIsu_id() {
		return isu_id;
	}
	
	public void setIsu_id(int isu_id) {
		this.isu_id = isu_id;
	}
	public int getRouting_number() {
		return routing_number;
	}

	public void setRouting_number(int routing_number) {
		this.routing_number = routing_number;
	}

	public int getAccount_number() {
		return account_number;
	}

	public void setAccount_number(int account_number) {
		this.account_number = account_number;
	}

	public String getNetid() {
		return netid;
	}

	public void setNetid(String netid) {
		this.netid = netid;
	}

}
