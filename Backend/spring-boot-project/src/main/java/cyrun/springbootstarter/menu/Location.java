package cyrun.springbootstarter.menu;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "locations_table")
public class Location {
	
	@Id
	private Integer location_id;
	private String location_name;
	
	public Location(int location_id, String location_name) {

		this.location_id = location_id;
		this.location_name = location_name;
	}
	
	public Location() {
		
	}

	public int getLocation_id() {
		return location_id;
	}

	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}

	public String getLocation_name() {
		return location_name;
	}

	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}
	
	
	
	

}
