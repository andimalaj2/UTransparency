package com.utransparency.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VirtualProgresiveListFormViewModel {
	
	private List<VirtualProgresive> virtualProgresiveList = new ArrayList<VirtualProgresive>();
	
	private String month;
	
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

}
