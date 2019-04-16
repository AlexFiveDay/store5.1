package cn.tedu.store5.util;

import java.io.Serializable;

public class ResponseResult<T> implements Serializable {
	/**
	 * data返回的数据
	 */
	private static final long serialVersionUID = -3134222784441672048L;
	private Integer state;
	private String message;
	private T data;
	
	public ResponseResult() {
		super();
	}
	
	public ResponseResult(Integer state, T data) {
		super();
		this.state = state;
		this.data = data;
	}

	public ResponseResult(Integer state) {
		super();
		this.state = state;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
}
