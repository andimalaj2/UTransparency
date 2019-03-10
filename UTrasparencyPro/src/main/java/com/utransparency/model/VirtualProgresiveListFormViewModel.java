package com.utransparency.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VirtualProgresiveListFormViewModel {
	
	private List<VirtualProgresive> virtualProgresiveList = new ArrayList<VirtualProgresive>();
	
	private String month;
	
	private Boolean confirm;
	
	private String msg;
	
	public VirtualProgresiveListFormViewModel() {
	}
	
	public VirtualProgresiveListFormViewModel(List<VirtualProgresive> virtualProgresiveList) {
		this.virtualProgresiveList = virtualProgresiveList;
	}

	public List<VirtualProgresive> getVirtualProgresiveList() {
		return virtualProgresiveList;
	}

	public void setVirtualProgresiveList(List<VirtualProgresive> virtualProgresiveList) {
		this.virtualProgresiveList = virtualProgresiveList;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Boolean getConfirm() {
		return confirm;
	}

	public void setConfirm(Boolean confirm) {
		this.confirm = confirm;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
