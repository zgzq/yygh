package com.wx.ad.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import my.ann.Action;
import my.ann.Menu;
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
import com.wx.ad.dbo.KpiDict;
import com.wx.ad.dbo.KpiPlan;
import com.wx.ad.dbo.KpiPlanDtl;
import com.wx.ad.dbo.Role;
import com.wx.ad.dbo.RoleAp;
import com.wx.ad.dbo.RolePlan;
import com.wx.ad.dbo.SysMenu;
import com.wx.ad.dbo.SysUser;
import com.wx.ad.dbo.UserRole;
import com.wx.ad.util.PageFactory;
import com.wx.ad.web.AuthController;

@Menu("指标方案管理")
@RequestMapping("/sys/kpiplan")
@Controller
public class KpiPlanController extends AuthController {

	@Action("指标方案管理")
	@RequestMapping("/list")
	public String index(Model m) {
		return "admin/kpiplan/list";
	}

	@Action("指标方案管理")
	@ResponseBody
	@RequestMapping("/gridData1")
	public GridDataModel<KpiPlan> treeData() {
		return PageFactory.newPage(KpiPlan.class, "", "");
	}

	@Action("指标方案管理")
	@ResponseBody
	@RequestMapping("/adddtl")
	public AjaxMsg add() {

		return run(new CallBack() {

			@Override
			public AjaxMsg call() throws Exception {
				String planid = param("planid", "");
				String kpiids = param("kpiids", "");
				List<KpiPlanDtl> list = KpiPlanDtl.INSTANCE.query("planid=?",
						planid);
				List<String> set=new ArrayList<String>();
				for(int i=0;i<list.size();i++){
					KpiPlanDtl d = list.get(i);
					set.add(String.valueOf(d.getKpiid()));
				}
				for (String id : kpiids.split(",")) {
						if (set.contains(id)) {
							continue;
						} else {
							KpiPlanDtl n = new KpiPlanDtl();
							n.setId(n.newId());
							n.setKpiid(Long.valueOf(id));
							n.setPlanid(Long.valueOf(planid));
							n.insert();
						}


				}
				return AjaxMsg.ok();
			}
		});
	}

	@Action("指标方案管理")
	@ResponseBody
	@RequestMapping("/deldtl")
	public AjaxMsg delete() {

		return run(new CallBack() {

			@Override
			public AjaxMsg call() throws Exception {
				String id = param("id", "");
				KpiPlanDtl.INSTANCE.delete("id=?", id);
				return AjaxMsg.ok();
			}
		});
	}

	@Action("指标方案管理")
	@ResponseBody
	@RequestMapping("/GridDatadtl")
	public GridDataModel<KpiPlanDtl> gridData() {
		String planid = param("planid", "0");
		return PageFactory.newPage(KpiPlanDtl.class," planid=?"," order by dictcode", planid);
	}

	@Action("指标方案管理")
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
				

				List<KpiPlan> insert = model.inserts(KpiPlan.class);

				List<KpiPlan> delete = model.deletes(KpiPlan.class);

				List<KpiPlan> update = model.updates(KpiPlan.class);
				
				List<KpiPlanDtl> u1=model1.updates(KpiPlanDtl.class);

				for (KpiPlan dict : delete) {
					KpiPlanDtl.INSTANCE.delete("planid=?", dict.getId());
					dict.delete();
				}

				for (KpiPlan dict : update) {
					dict.update();
				}

				for (KpiPlan dict : insert) {
					dict.setId(dict.newId());
					dict.insert();
				}
				
				for(KpiPlanDtl dtl:u1){
					dtl.update();
				}

				return AjaxMsg.ok();
			}
		});

	}

}
