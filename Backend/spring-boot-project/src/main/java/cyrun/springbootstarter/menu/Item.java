package cyrun.springbootstarter.menu;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="items_table")
public class Item {
	
	@Id
	private Integer item_id;
	private Integer location_id;
	private String item_name;
	private double item_price;
	
	public Item() {
	}
	
	public Item(int item_id, int location_id, String item_name, double item_price) {
		this.item_id = item_id;
		this.location_id = location_id;
		this.item_name = item_name;
		this.item_price = item_price;
	}
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public int getLocation_id() {
		return location_id;
	}
	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public double getItem_price() {
		return item_price;
	}
	public void setItem_price(double item_price) {
		this.item_price = item_price;
	}

}
