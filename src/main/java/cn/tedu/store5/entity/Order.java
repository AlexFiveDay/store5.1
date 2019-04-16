package cn.tedu.store5.entity;

import java.util.Date;

public class Order extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6618874855168132024L;
	Integer oid;
	Integer uid;
	String name;
	String phone;
	String address;
	Integer status;
	Long price;
	Date order_time;
	Date pay_time;

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Date getOrder_time() {
		return order_time;
	}

	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}

	public Date getPay_time() {
		return pay_time;
	}

	public void setPay_time(Date pay_time) {
		this.pay_time = pay_time;
	}

	@Override
	public String toString() {
		return "Order [oid=" + oid + ", uid=" + uid + ", name=" + name + ", phone=" + phone + ", address=" + address
				+ ", status=" + status + ", price=" + price + ", order_time=" + order_time + ", pay_time=" + pay_time
				+ "]";
	}

}
