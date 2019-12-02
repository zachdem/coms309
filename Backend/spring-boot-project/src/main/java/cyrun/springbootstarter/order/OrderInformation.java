package cyrun.springbootstarter.order;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_information")
public class OrderInformation {
	
	@Id
	private Integer order_id;
	private String first_name;
	private String last_name;
	private String item_name;
	private Double item_price;
	private String location_name;
	private String runner_first_name;
	private String runner_last_name;
	private String pending_order;
	
	public OrderInformation()
	{
		
	}
	
	
	
	
	public OrderInformation(Integer order_id, String first_name, String last_name, String item_name, Double item_price,
			String location_name, String runner_first_name, String runner_last_name, String pending_order) {
		super();
		this.order_id = order_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.item_name = item_name;
		this.item_price = item_price;
		this.location_name = location_name;
		this.runner_first_name = runner_first_name;
		this.runner_last_name = runner_last_name;
		this.pending_order = pending_order;
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

	public String getRunner_first_name() {
		return runner_first_name;
	}

	public void setRunner_first_name(String runner_first_name) {
		this.runner_first_name = runner_first_name;
	}

	public String getRunner_last_name() {
		return runner_last_name;
	}

	public void setRunner_last_name(String runner_last_name) {
		this.runner_last_name = runner_last_name;
	}

	public String getPending_order() {
		return pending_order;
	}

	public void setPending_order(String pending_order) {
		this.pending_order = pending_order;
	}
	
	
	
	
	
	
	
	
}
