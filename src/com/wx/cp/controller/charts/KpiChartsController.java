package com.wx.cp.controller.charts;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wx.ad.dbo.SysUser;
import com.wx.cp.model.KpiCharts;
import com.wx.cp.wx.WxCp;

import my.web.BaseController;
@Controller
@RequestMapping("/mm")
public class KpiChartsController  extends BaseController {
	@Autowired
	private HttpSession session;
	/**
	 * 返回图表列表
	 * @return
	 */
	@RequestMapping("list")
	public String chartsList(Model m){
		List<KpiCharts> charts=KpiCharts.INSTANCE.query("");
		m.addAttribute("charts", charts);
		return "charts/list";
	}
	/**
	 * 返回首页
	 * @return
	 */
	@RequestMapping("home")
	public String home(){
		String users="";
		// 查询所有有日报的用户
		List<SysUser> list = SysUser.INSTANCE.query(
				" nvl(status,0)=? and nvl(issend,0)=1 and f_strmatch(1,plantypes)=1 ", 1);
		for (SysUser user : list) {
			users="&userid=" + user.getId(); 
		}
		
		String url_home=(String) session.getAttribute("url_home");
		String filter=url_home.substring( url_home.indexOf("?"));
		return "redirect:/qyreq/kpiday/home"+filter;
	}
}
