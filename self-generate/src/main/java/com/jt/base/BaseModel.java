package com.jt.base;

import java.io.Serializable;
import java.util.Date;

public class BaseModel<K> implements Serializable{
	protected static final long serialVersionUID = 1L;
	protected K id;
	protected String status;
	protected Date timestamp;
	public K getId() {
		return id;
	}
	public void setId(K id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
