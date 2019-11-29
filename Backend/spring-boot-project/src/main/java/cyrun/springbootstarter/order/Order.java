package cyrun.springbootstarter.order;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orders_table")
public class Order {

	@Id
	private Integer order_id;
	private Integer customer_id;
	private Integer runner_id;
	private String pending_order;
	private Integer item_id;
	private String order_finalized;
	private String delivery_address;

	public Order() {

	}

	public Order(Integer order_id, Integer customer_id, Integer runner_id, String pending_order, Integer item_id,
			String order_finalized, String delivery_address) {
		this.order_id = order_id;
		this.customer_id = customer_id;
		this.runner_id = runner_id;
		this.pending_order = pending_order;
		this.item_id = item_id;
		this.order_finalized = order_finalized;
		this.delivery_address = delivery_address;
	}

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	public Integer getRunner_id() {
		return runner_id;
	}

	public void setRunner_id(Integer runner_id) {
		this.runner_id = runner_id;
	}

	public String isPending_order() {
		return pending_order;
	}

	public void setPending_order(String pending_order) {
		this.pending_order = pending_order;
	}

	public Integer getItem_id() {
		return item_id;
	}

	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}

	public String isOrder_finalized() {
		return order_finalized;
	}

	public void setOrder_finalized(String order_finalized) {
		this.order_finalized = order_finalized;
	}

	public String getDelivery_address() {
		return delivery_address;
	}

	public void setDelivery_address(String delivery_address) {
		this.delivery_address = delivery_address;
	}

}
