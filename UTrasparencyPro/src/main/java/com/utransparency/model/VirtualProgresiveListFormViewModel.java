package com.utransparency.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VirtualProgresiveListFormViewModel {
	
	private List<VirtualProgresive> virtualProgresiveList = new ArrayList<VirtualProgresive>();
	
	private List<VirtualProgresive> virtualProgresiveListInc = new ArrayList<VirtualProgresive>();
	
	private List<VirtualProgresive> virtualProgresiveListExp = new ArrayList<VirtualProgresive>();
	
	private List<VirtualProgresive> virtualProgresiveListExpS = new ArrayList<VirtualProgresive>();
	
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

	public List<VirtualProgresive> getVirtualProgresiveListInc() {
		return virtualProgresiveListInc;
	}

	public void setVirtualProgresiveListInc(List<VirtualProgresive> virtualProgresiveListInc) {
		this.virtualProgresiveListInc = virtualProgresiveListInc;
	}

	public List<VirtualProgresive> getVirtualProgresiveListExp() {
		return virtualProgresiveListExp;
	}

	public void setVirtualProgresiveListExp(List<VirtualProgresive> virtualProgresiveListExp) {
		this.virtualProgresiveListExp = virtualProgresiveListExp;
	}

	public List<VirtualProgresive> getVirtualProgresiveListExpS() {
		return virtualProgresiveListExpS;
	}

	public void setVirtualProgresiveListExpS(List<VirtualProgresive> virtualProgresiveListExpS) {
		this.virtualProgresiveListExpS = virtualProgresiveListExpS;
	}

}
