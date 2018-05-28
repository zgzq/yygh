package com.wx.ad.dbo;

import my.web.AjaxMsg;

public class WebMsg extends AjaxMsg{
	private String status;

	public String getStatus() {
		return status;
	}

	public WebMsg setStatus(String status) {
		this.status = status;
		return this;
	}
	

}
