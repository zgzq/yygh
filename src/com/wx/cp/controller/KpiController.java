package com.wx.cp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import my.web.BaseController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wx.ad.dbo.KpiPlan;
import com.wx.ad.dbo.SysUser;
import com.wx.ad.dbo.UsrOrg;
import com.wx.cp.model.KpiCharts;
import com.wx.cp.model.KpiValueMpCp;
import com.wx.cp.model.KpiValueMpCpRoleV;
import com.wx.cp.model.KpidictValue;
import com.wx.cp.model.kpimodel;

/**
 * 指标数据
 * 
 * @author 
 *
 */
@Controller
@RequestMapping("/qyreq")
public class KpiController extends BaseController {
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpSession session;
	
	

	
	/**
	 * @param map
	 * @return 日报
	 */
	@RequestMapping("/kpiday")
	public String kpiindex(Model map) {
		/**
		 * 9家医院
		 */
		String pdate = param("pdate", "");// 日期
		String userid = param("userid", ""); // 用户ID
		String ss = param("ss", ""); // 上市标志  1 是  0否
		SysUser user = SysUser.INSTANCE.queryOne("id=?", userid);
		// 查询中美机构
		List<UsrOrg> olist = null;
		
		// 保存当前url
		String url = "";
		url = request.getScheme() +"://" + request.getServerName()  
                        + ":" +request.getServerPort()  
                        + request.getServletPath();
		if (request.getQueryString() != null){
			url += "?" + request.getQueryString();
		}
		session.setAttribute("url_home", url.replace("kpiday", "kpiday/home"));
		
		
		if (user.getUsertype() == null) {
			// 暂时不处理

		} else if (user.getUsertype().equals("1")) {
			// 集团行政
			olist = UsrOrg.INSTANCE.query("id>0 and ss=? order by id",ss);
			map.addAttribute("orgs", olist);
			map.addAttribute("userid", userid);
			map.addAttribute("pdate", pdate);
			map.addAttribute("ss", ss);

		} else if (user.getUsertype().equals("2")) {
			// 医院领导
			olist = UsrOrg.INSTANCE.query("id=? and ss=? order by id", user.getOrgid(),ss);
			map.addAttribute("orgs", olist);
			map.addAttribute("userid", userid);
			map.addAttribute("pdate", pdate);
			map.addAttribute("ss", ss);

		} else if (user.getUsertype().equals("3")) {
			// 科室主任

		} else if (user.getUsertype().equals("99")) {
			// 其他

		}
		
		/**
		 * 图表列表
		 */
		List<KpiCharts> charts=KpiCharts.INSTANCE.query("");
		map.addAttribute("charts", charts);
		
		
		/**
		 * 数据对比
		 */
		List<KpiValueMpCpRoleV> userRoleMpCp= KpiValueMpCpRoleV.INSTANCE.query("id=? and ss=? order by orgid", user.getUserId(),ss);
		Map<String,List<KpiValueMpCp>> mapKvmpds=new LinkedHashMap<String,List<KpiValueMpCp>>();
		for (KpiValueMpCpRoleV kpiValueMpCpRoleV : userRoleMpCp) {
			UsrOrg org=UsrOrg.INSTANCE.queryOne("id=? order by id", kpiValueMpCpRoleV.getOrgid());
			List<KpiValueMpCp> kvmpds=KpiValueMpCp.INSTANCE.query("pdate=? and orgid =? order by seq",pdate,kpiValueMpCpRoleV.getOrgid());
			mapKvmpds.put(org.getOrg_name(), kvmpds);
		}
		//map集合  即使权限设置有重复  也会去重
		map.addAttribute("mapKvmpds", mapKvmpds);
		
		return "index";

	}

	@RequestMapping("/zbinfo")
	public String zbinfo(Model map) {
		String orgid = param("id", "");
		String orgname=param("orgname", "");
		String userid = param("userid", "");
		String pdate = param("pdate", "");
		String qid=param("qid", "");
		String ss=param("ss", "ss");
		List<String> plannames=new ArrayList<String>();
		// 查询机构下及用户有权限的方案
		List<KpiPlan> list = KpiPlan.INSTANCE
				.query("orgid=? and id in (select planid from wx_user_plan where userid=?)",
						orgid, userid);
		List<kpimodel> k = new ArrayList<kpimodel>();
		for (KpiPlan plan : list) {
			kpimodel m = new kpimodel();
			m.setCode(String.valueOf(plan.getId()));
			m.setName(plan.getPlanname());
			plannames.add(plan.getPlanname());
			m.setPsdate(pdate);// 日报开始日期和结束日期为同一天
			m.setPedate(pdate);
			m.setUserid(userid);
			m.setOrgid(orgid);
			m.setOrg_pic(plan.getOrg_pic());;
			k.add(m);
		}
		//所有机构信息
		List<UsrOrg> orgs=UsrOrg.INSTANCE
				.query("id>0 and ss=?",ss);
		
		map.addAttribute("kpis", k);
		map.addAttribute("plannames", plannames);
		map.addAttribute("kpiscount", k.size());
		map.addAttribute("pdate", pdate);
		map.addAttribute("orgid", orgid);
		map.addAttribute("orgname", orgname);
		map.addAttribute("userid", userid);
		map.addAttribute("qid", qid);
		map.addAttribute("userid", userid);
		map.addAttribute("pdate", pdate);
		map.addAttribute("orgs", orgs);
		map.addAttribute("ss", ss);
		
		return "zbinfo";
	}

	@RequestMapping("/zmdept")
	public String zmdept(Model map) {
		String ks = param("ks", "");
		String pdate = param("pdate", "");
		String planid = param("planid", "");
		String userid = param("userid", "");
		String ss = param("ss", "ss");
		List<KpidictValue> list = KpidictValue.INSTANCE.query(
				" planid=? and pdate=? and deptcode=? order by korder", planid, pdate, ks);
		String title = "";
		if (ks.equals("xhk")) {
			title = "消化科";
		} else if (ks.equals("fk")) {
			title = "妇科";
		} else if (ks.equals("gk")) {
			title = "骨科";
		} else if (ks.equals("xnk")) {
			title = "心内科";
		} else if (ks.equals("zlk")) {
			title = "肿瘤";
		} else if (ks.equals("ek")){
			title = "儿科";
		} else if (ks.equals("ck")){
			title = "产科"; 
		}else{
			
		}
		//所有机构信息
				List<UsrOrg> orgs=UsrOrg.INSTANCE
						.query("id>0 and ss=?" ,ss);
		map.addAttribute("orgname", title);
		map.addAttribute("kvs", list);
		map.addAttribute("pdate", pdate);
		map.addAttribute("userid", userid);
		map.addAttribute("orgs", orgs);
		map.addAttribute("ss", ss);
		return "zmdept";
	}
	@RequestMapping("/jmdept")
	public String jmdept(Model map) {
		String ks = param("ks", "");
		String pdate = param("pdate", "");
		String planid = param("planid", "");
		String userid = param("userid", "");
		String ss = param("ss", "ss");
		List<KpidictValue> list = KpidictValue.INSTANCE.query(
				" planid=? and pdate=? and deptcode=? order by korder", planid, pdate, ks);
		String title = ks;
		
		//所有机构信息
				List<UsrOrg> orgs=UsrOrg.INSTANCE
						.query("id>0 and ss=?" ,ss);
		map.addAttribute("orgname", title);
		map.addAttribute("kvs", list);
		map.addAttribute("pdate", pdate);
		map.addAttribute("userid", userid);
		map.addAttribute("orgs", orgs);
		map.addAttribute("ss", ss);
		return "zmdept";
	}
}
