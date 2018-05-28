package com.wx.ad.bean;

import java.util.List;

import com.alibaba.fastjson.JSON;

public class GridSaveModel {
	private String insert;
	private String update;
	private String delete;
	
	
	public String getInsert() {
		return insert;
	}
	public void setInsert(String insert) {
		this.insert = insert;
	}
	public String getUpdate() {
		return update;
	}
	public void setUpdate(String update) {
		this.update = update;
	}
	public String getDelete() {
		return delete;
	}
	public void setDelete(String delete) {
		this.delete = delete;
	}
	
	
	public <T> List<T> updates(Class<T> cls){
		 return JSON.parseArray(update, cls);
	}
	
	public <T> List<T> inserts(Class<T> cls){
		 return JSON.parseArray(insert, cls);
	}
	
	public <T> List<T> deletes(Class<T> cls){
		 return JSON.parseArray(delete, cls);
	}
	 
}
