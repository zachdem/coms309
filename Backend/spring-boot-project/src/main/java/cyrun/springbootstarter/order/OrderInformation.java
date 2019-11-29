package cyrun.springbootstarter.order;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_information")
public class OrderInformation {
	
	@Id
	Integer order_id;
	String first_name;
	String last_name;
	String item_name;
	Double item_price;
	String location_name;
	
	public OrderInformation()
	{
		
	}
	
	public OrderInformation(Integer order_id, String first_name, String last_name, String item_name, Double item_price,
			String location_name) {
		this.order_id = order_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.item_name = item_name;
		this.item_price = item_price;
		this.location_name = location_name;
	}
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
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
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public Double getItem_price() {
		return item_price;
	}
	public void setItem_price(Double item_price) {
		this.item_price = item_price;
	}
	public String getLocation_name() {
		return location_name;
	}
	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}
	
	
	
	
	
	
	
	
}
