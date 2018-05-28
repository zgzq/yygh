package com.wx.ad.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import my.ann.Action;
import my.ann.Menu;
import my.dao.utils.Record;
import my.web.AjaxMsg;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.wx.ad.bean.GridDataModel;
import com.wx.ad.bean.GridSaveModel;
import com.wx.ad.dbo.Dict;
import com.wx.ad.dbo.KpiDateV;
import com.wx.ad.dbo.KpiDict;
import com.wx.ad.dbo.KpiPlan;
import com.wx.ad.dbo.KpiPlanDtl;
import com.wx.ad.dbo.KpiValueManualDtl;
import com.wx.ad.dbo.KpiValueManualMst;
import com.wx.ad.dbo.Role;
import com.wx.ad.dbo.RoleAp;
import com.wx.ad.dbo.RolePlan;
import com.wx.ad.dbo.SysMenu;
import com.wx.ad.dbo.SysUser;
import com.wx.ad.dbo.UserRole;
import com.wx.ad.util.PageFactory;
import com.wx.ad.web.AuthController;

@Menu("手工录入指标")
@RequestMapping("/sgwh/manual")
@Controller
public class ManualDataController extends AuthController {

	@Action("手工录入指标")
	@RequestMapping("/list")
	public String index(Model m) {
		Set<String> setMonth=new LinkedHashSet<String>();
		List<KpiDateV> listKpiDateV=KpiDateV.INSTANCE.query("year=?","2017");
		for (KpiDateV kpiDateV : listKpiDateV) {
			setMonth.add(kpiDateV.getYearmonth());
		} 

		
		m.addAttribute("month", setMonth);
		return "admin/manual/list";
	}

	@Action("手工录入指标")
	@ResponseBody
	@RequestMapping("/gridData1")
	public GridDataModel<KpiValueManualMst> treeData() {
		String yearmonth=param("yearmonth", "");
		return PageFactory.newPage(KpiValueManualMst.class, "to_char(valuedate,'yyyymm')='"+yearmonth+"'","order by valuedate,orgid");
	}

	@Action("手工录入指标")
	@ResponseBody
	@RequestMapping("/gridData2")
	public GridDataModel<KpiPlan> treeData2() {
		String orgid = param("orgid", "0");
		return PageFactory.newPage(KpiPlan.class, " orgid=? ", "",orgid);
	}
	@Action("手工录入指标")
	@ResponseBody
	@RequestMapping("/adddtl")
	public AjaxMsg add() {

		return run(new CallBack() {

			@Override
			public AjaxMsg call() throws Exception {
				
				String mid = param("mid", "");
				String planid = param("planid", "");
				List<KpiPlanDtl> list = KpiPlanDtl.INSTANCE.query("planid=? and op=3",planid);
				
				for(int i=0;i<list.size();i++){
					KpiValueManualDtl n = new KpiValueManualDtl();
					n.setId(n.newId());
					n.setKpiid(list.get(i).getKpiid());
					n.setMid(Long.valueOf(mid));
					n.insert();
				}
			
				return AjaxMsg.ok();
			}
		});
	}

	@Action("手工录入指标")
	@ResponseBody
	@RequestMapping("/deldtl")
	public AjaxMsg delete() {

		return run(new CallBack() {

			@Override
			public AjaxMsg call() throws Exception {
				String id = param("id", "");
				KpiValueManualDtl.INSTANCE.delete("id=?", id);
				return AjaxMsg.ok();
			}
		});
	}

	@Action("手工录入指标")
	@ResponseBody
	@RequestMapping("/GridDatadtl")
	public GridDataModel<KpiValueManualDtl> gridData() {
		String mid = param("mid", "0");
		return PageFactory.newPage(KpiValueManualDtl.class," mid=?"," order by kpiid", mid);
	}

	@Action("手工录入指标")
	@ResponseBody
	@RequestMapping("/saveGrid")
	public AjaxMsg save(final @RequestParam("json") String json,final @RequestParam("json1") String json1) {

		return run(new CallBack() {

			@Override
			public AjaxMsg call() throws Exception {
				//
				GridSaveModel model = JSON.parseObject(json,
						GridSaveModel.class);
				GridSaveModel model1 = JSON.parseObject(json1,
						GridSaveModel.class);
				

				List<KpiValueManualMst> insert = model.inserts(KpiValueManualMst.class);

				List<KpiValueManualMst> delete = model.deletes(KpiValueManualMst.class);

				List<KpiValueManualMst> update = model.updates(KpiValueManualMst.class);
				
				List<KpiValueManualDtl> u1=model1.updates(KpiValueManualDtl.class);

				for (KpiValueManualMst dict : delete) {
					KpiValueManualDtl.INSTANCE.delete("mid=?", dict.getId());
					dict.delete();
				}

				for (KpiValueManualMst dict : update) {
					dict.update();
				}

				for (KpiValueManualMst dict : insert) {
					dict.setId(dict.newId());
					dict.insert();
				}
				
				for(KpiValueManualDtl dtl:u1){
					dtl.update();
				}

				return AjaxMsg.ok();
			}
		});

	}

}
