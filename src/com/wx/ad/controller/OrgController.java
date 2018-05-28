package com.wx.ad.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.chanjar.weixin.cp.api.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.WxCpDepart;
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
import com.wx.ad.dbo.UsrOrg;
import com.wx.ad.util.PageFactory;
import com.wx.ad.web.AuthController;
import com.wx.cp.wx.WxCp;

@Menu("组织机构管理")
@RequestMapping("/sys/org")
@Controller
public class OrgController extends AuthController {

	@Action("组织机构管理")
	@RequestMapping("/list")
	public String index() {
		return "admin/org/list";
	}


	@Action("组织机构管理")
	@ResponseBody
	@RequestMapping("/gridData")
	public GridDataModel<UsrOrg> gridData() {
		String realname = param("orgname", "");

		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if (realname.length() > 0) {
			filter.append(" and ORG_NAME like ? ");
			params.add(realname + "%");
		}
		
		return PageFactory.newPage(UsrOrg.class, filter.toString(),"",
				params.toArray());
	}

	@Action("组织机构管理")
	@ResponseBody
	@RequestMapping("/saveGrid")
	public AjaxMsg gridSave(final @RequestParam("json") String json) {

		return run(new CallBack() {

			@Override
			public AjaxMsg call() throws Exception {
				GridSaveModel model = JSON.parseObject(json,
						GridSaveModel.class);

				List<UsrOrg> insert = model.inserts(UsrOrg.class);

				List<UsrOrg> delete = model.deletes(UsrOrg.class);

				List<UsrOrg> update = model.updates(UsrOrg.class);

				for (UsrOrg comp : delete) {
					comp.delete();
				}

				for (UsrOrg comp : update) {
					comp.update();
				}

				for (UsrOrg comp : insert) {
					comp.setId(comp.newId()+"");
					comp.insert();
				}

				return AjaxMsg.ok();
			}
		});

	}
	
	@Action("组织机构管理")
	@ResponseBody
	@RequestMapping("/genWx")
	public AjaxMsg genWx(final @RequestParam("orgid") String orgids) {

		return run(new CallBack() {

			@Override
			public AjaxMsg call() throws Exception {
				if(orgids==null||orgids.length()==0){
					return AjaxMsg.ok();
				}
				WxCpServiceImpl service = WxCp.getInstance().getService();
				List<WxCpDepart> list=service.departGet();
				Map<Integer, WxCpDepart> map=new HashMap<Integer, WxCpDepart>();
				for(WxCpDepart dept:list){
					map.put(dept.getId(), dept);
				}
				String[] us=orgids.split(",");
				for(int i=0;i<us.length;i++){
					String id=us[i];
					UsrOrg br=UsrOrg.INSTANCE.queryOne("id=?", id);
					//检查微信组织机构是否存在，存在则更新，不存在则插入
					if(map.containsKey(br.getId())){
						//已经存在
						WxCpDepart dp=map.get(br.getId());
						dp.setName(br.getOrg_name());
						service.departUpdate(dp);
					}else{
						//不存在
						WxCpDepart dp=new WxCpDepart();
						dp.setId(Integer.valueOf(br.getWxid()));
						dp.setName(br.getOrg_name());
						dp.setParentId(Integer.valueOf(br.getWxpid()));
						service.departCreate(dp);
						
						
					}
				}

				return AjaxMsg.ok();
			}
		});

	}
	
}
