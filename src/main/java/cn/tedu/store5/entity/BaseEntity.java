package cn.tedu.store5.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 实体类的基类
 * @author 杨大龙
 *
 */
public abstract class BaseEntity implements Serializable{
	private static final long serialVersionUID = 293498267474236870L;
	private String createUser;
	private Date createTime;
	private String modifiedUser;
	private Date modifiedTime;
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date now) {
		this.createTime = now;
	}
	public String getModifiedUser() {
		return modifiedUser;
	}
	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "BaseEntity [createUser=" + createUser + ", createTime=" + createTime + ", modifiedUser=" + modifiedUser
				+ ", modifiedTime=" + modifiedTime + "]";
	}
	
}
