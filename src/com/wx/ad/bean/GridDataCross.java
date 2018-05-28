package com.wx.ad.bean;

import java.util.LinkedHashMap;
import java.util.Map;

public class GridDataCross {
	private String rowname;
	private LinkedHashMap<String,String> colnames;
	
	
	
	public String getRowname() {
		return rowname;
	}
	public void setRowname(String rowname) {
		this.rowname = rowname;
	}
	public Map<String, String> getColnames() {
		return colnames;
	}
	public void setColnames(LinkedHashMap<String, String> colnames) {
		this.colnames = colnames;
	}

	
}
