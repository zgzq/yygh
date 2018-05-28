package com.wx.ad.controller;

import my.web.BaseController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/hov")
@Controller
public class HovController extends BaseController{
	
	@RequestMapping("/kpidictmuti")
	public String kpimuti() {
		return "admin/kpiplan/kpihovmuti";
	}
	
	@RequestMapping("/manualdatamuti")
	public String manualdatamuti(Model m) {
		String orgid = param("orgid", "0");
		m.addAttribute("orgid", orgid);
		return "admin/manual/manualvaluehovmuti";
	}
	
	@RequestMapping("/usermuti")
	public String usermuti() {
		return "admin/user/userhovmuti";
	}
}
