package cyrun.springbootstarter.order;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orders_table")
public class Order {

	@Id
	private int order_id;
	private int customer_id;
	private int runner_id;
	private String pending_order;
	private int item_id;
	private String order_finalized;
	private String delivery_address;

	public Order() {

	}

	public Order(int order_id, int customer_id, int runner_id, String pending_order, int item_id,
			String order_finalized, String delivery_address) {
		this.order_id = order_id;
		this.customer_id = customer_id;
		this.runner_id = runner_id;
		this.pending_order = pending_order;
		this.item_id = item_id;
		this.order_finalized = order_finalized;
		this.delivery_address = delivery_address;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getRunner_id() {
		return runner_id;
	}

	public void setRunner_id(int runner_id) {
		this.runner_id = runner_id;
	}

	public String isPending_order() {
		return pending_order;
	}

	public void setPending_order(String pending_order) {
		this.pending_order = pending_order;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
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
