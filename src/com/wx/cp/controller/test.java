package com.wx.cp.controller;


public class test {
	final String s="流程";
	public static void main(String[] args) {
		String dest = String.format("<a href=\"%s\">%s</a>",
				"https://open.weixin.qq.com/connect/oauth2/authorize?appid=1"
						+ "&redirect_uri=" + "http://baidu.com"
						+ "&response_type=code&scope=snsapi_base&state=1#wechat_redirect",
				"您有待审批的流程，请点击待审批！");
		System.out.println(dest);
	}
}
