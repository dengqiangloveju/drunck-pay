package com.drunck.utils;

public class ResultInfo {
	private boolean status;
	private String msg;
	private Object obj;

	public ResultInfo() {
	}
	
	public ResultInfo(boolean status) {
		this.status = status;
	}

	public ResultInfo(boolean status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public static ResultInfo instance() {
		return new ResultInfo(true);
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
}
