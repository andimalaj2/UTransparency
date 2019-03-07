package com.utransparency.model;

import java.util.ArrayList;
import java.util.List;

public class VirtualProgresiveListFormViewModel {
	
	private List<VirtualProgresive> virtualProgresiveList = new ArrayList<VirtualProgresive>();
	
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

}
