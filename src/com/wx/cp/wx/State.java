package com.wx.cp.wx;

import java.io.Serializable;

public class State implements Serializable{
	
	public String name;
	public State next;
	
	private Object data;
	
	public State(String name){
		this.name=name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public State getNext() {
		return next;
	}
	public void setNext(State next) {
		this.next = next;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
}
