package cn.tedu.store5.entity;

public class OrderItem extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2943694591883084680L;
	Integer id;
	Integer oid;
	Long gid;
	String title;
	String image;
	Long price;
	Long num;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public Long getGid() {
		return gid;
	}

	public void setGid(Long gid) {
		this.gid = gid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", oid=" + oid + ", gid=" + gid + ", title=" + title + ", image=" + image
				+ ", price=" + price + ", num=" + num + "]";
	}

}
