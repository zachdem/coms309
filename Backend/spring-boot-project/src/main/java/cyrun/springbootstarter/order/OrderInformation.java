package cyrun.springbootstarter.order;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

@Entity
@Table(name = "order_information")
public class OrderInformation {

	@Id
	private Integer order_id;
	
	private String first_name;
	
	private String last_name;
		
	private String item_name;
	
	private double item_price;
	
	private String location_name;

	public OrderInformation(String firstName, String lastName, Integer orderID, String itemName, double itemPrice,
			String locationName) {
		super();
		this.first_name = firstName;
		this.last_name = lastName;
		this.order_id = orderID;
		this.item_name = itemName;
		this.item_price = itemPrice;
		this.location_name = locationName;
	}

	public String getFirstName() {
		return first_name;
	}

	public void setFirstName(String firstName) {
		this.first_name = firstName;
	}

	public String getLastName() {
		return last_name;
	}

	public void setLastName(String lastName) {
		this.last_name = lastName;
	}

	public Integer getOrderID() {
		return order_id;
	}

	public void setOrderID(Integer orderID) {
		this.order_id = orderID;
	}

	public String getItemName() {
		return item_name;
	}

	public void setItemName(String itemName) {
		this.item_name = itemName;
	}

	public double getItemPrice() {
		return item_price;
	}

	public void setItemPrice(double itemPrice) {
		this.item_price = itemPrice;
	}

	public String getLocationName() {
		return location_name;
	}

	public void setLocationName(String locationName) {
		this.location_name = locationName;
	}
	
	
	
	
}
