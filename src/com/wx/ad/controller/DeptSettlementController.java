package com.wx.ad.controller;

import java.util.ArrayList;
import java.util.List;

import my.ann.Action;
import my.ann.Menu;
import my.web.AjaxMsg;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.wx.ad.bean.GridDataModel;
import com.wx.ad.bean.GridSaveModel;
import com.wx.ad.dbo.IndDeptSettlement;
import com.wx.ad.dbo.IndSettlement;
import com.wx.ad.dbo.UsrDept;
import com.wx.ad.util.PageFactory;
import com.wx.ad.web.AuthController;

@Menu("科室利润")
@RequestMapping("/sgwh/kslr")
@Controller
public class DeptSettlementController extends AuthController {

	@Action("科室利润")
	@RequestMapping("/list")
	public String index() {
		return "admin/kslr/list";
	}


	@Action("科室利润")
	@ResponseBody
	@RequestMapping("/gridData")
	public GridDataModel<IndDeptSettlement> gridData() {
		String orgquery = param("orgquery", "");
		String dept=param("dept", "");
		String sdate = param("sdate", "");
		String edate = param("edate", "");

		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		
		//增加控制，只能查询本人录的数据
		String userid=getLoginUer().getUserId();
		if(!userid.equals("-1")){
			filter.append(" and CREATE_USER_ID = ? ");
			params.add(userid);
		}
		if (orgquery.length() > 0) {
			filter.append(" and org_id = ?");
			params.add(orgquery);
		}
		if (dept.length() > 0) {
			filter.append(" and DEPT_CODE = ?");
			params.add(dept);
		}
		if (sdate.length() > 0) {
			sdate=sdate.replaceAll("-", "");
			filter.append(" and VALUE_DATE >= ?");
			params.add(sdate);
		}
		if (edate.length() > 0) {
			edate=edate.replaceAll("-", "");
			filter.append(" and VALUE_DATE <= ?");
			params.add(edate);
		}
		
		return PageFactory.newPage(IndDeptSettlement.class, filter.toString(),"",
				params.toArray());
	}

	@Action("科室利润")
	@ResponseBody
	@RequestMapping("/saveGrid")
	public AjaxMsg gridSave(final @RequestParam("json") String json) {

		return run(new CallBack() {

			@Override
			public AjaxMsg call() throws Exception {
				GridSaveModel model = JSON.parseObject(json,
						GridSaveModel.class);

				List<IndDeptSettlement> insert = model.inserts(IndDeptSettlement.class);

				List<IndDeptSettlement> delete = model.deletes(IndDeptSettlement.class);

				List<IndDeptSettlement> update = model.updates(IndDeptSettlement.class);

				for (IndDeptSettlement comp : delete) {
					comp.delete();
				}

				for (IndDeptSettlement comp : update) {
					//处理科室保存编码
					String deptid=comp.getDeptid();
					UsrDept d=UsrDept.INSTANCE.queryOne("id=?", deptid);
					comp.setDept_code(d.getDept_code());
					//处理日期为yyyymmdd
					String vdate=comp.getValue_date();
					if(vdate.length()>0){
						comp.setValue_date(vdate.replaceAll("-", ""));
					}
					comp.setModify_user_id(getLoginUer().getUserId());
					comp.setModify_user_name(getLoginUer().getUserName());
					
					comp.update();
				}

				for (IndDeptSettlement comp : insert) {
					//comp.setId(comp.newId()+"");
					//处理科室保存编码
					String deptid=comp.getDeptid();
					UsrDept d=UsrDept.INSTANCE.queryOne("id=?", deptid);
					comp.setDept_code(d.getDept_code());
					//处理日期为yyyymmdd
					String vdate=comp.getValue_date();
					if(vdate.length()>0){
						comp.setValue_date(vdate.replaceAll("-", ""));
					}
					comp.setCreate_user_id(getLoginUer().getUserId());
					comp.setCreate_user_name(getLoginUer().getUserName());
					comp.insert();
				}

				return AjaxMsg.ok();
			}
		});

	}
	
	
}
