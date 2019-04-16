package cn.tedu.store5.entity;

public class Cart extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 212621748515019573L;
	private Integer num;
	private Integer cid;
    private Integer uid; 
    private Long gid;
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Long getGid() {
		return gid;
	}
	public void setGid(Long gid) {
		this.gid = gid;
	}
	@Override
	public String toString() {
		return "Cart [num=" + num + ", cid=" + cid + ", uid=" + uid + ", gid=" + gid + "]";
	}
	


}
