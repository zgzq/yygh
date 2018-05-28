package com.wx.ad.controller;

import java.util.ArrayList;
import java.util.List;

import me.chanjar.weixin.common.bean.WxMenu;
import me.chanjar.weixin.cp.api.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.WxCpUser;
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
import com.wx.ad.dbo.SysUser;
import com.wx.ad.dbo.UsrOrg;
import com.wx.ad.util.PageFactory;
import com.wx.ad.web.AuthController;
import com.wx.cp.wx.WxCp;

@Menu("用户管理")
@RequestMapping("/sys/user")
@Controller
public class UserController extends AuthController {

	@Action("用户管理")
	@RequestMapping("/list")
	public String index() {
		return "admin/user/user";
	}


	@Action("用户管理")
	@ResponseBody
	@RequestMapping("/gridData")
	public GridDataModel<SysUser> gridData() {
		String realname = param("realname", "");

		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		filter.append(" and id<>'-1'");
		if (realname.length() > 0) {
			filter.append(" and realname like ? ");
			params.add(realname + "%");
		}
		
		return PageFactory.newPage(SysUser.class, filter.toString(),"",
				params.toArray());
	}

	@Action("用户管理")
	@ResponseBody
	@RequestMapping("/saveGrid")
	public AjaxMsg gridSave(final @RequestParam("json") String json) {

		return run(new CallBack() {

			@Override
			public AjaxMsg call() throws Exception {
				GridSaveModel model = JSON.parseObject(json,
						GridSaveModel.class);

				List<SysUser> insert = model.inserts(SysUser.class);

				List<SysUser> delete = model.deletes(SysUser.class);

				List<SysUser> update = model.updates(SysUser.class);

				for (SysUser comp : delete) {
					comp.delete();
					//删除微信端用户
					WxCpServiceImpl service = WxCp.getInstance().getService();
					WxCpUser wu=null;
					try {
						wu=service.userGet(comp.getId());
					} catch (Exception e) {
						wu=null;
					}
					if (wu!=null){
						service.userDelete(comp.getId());
					}
					
				}

				for (SysUser comp : update) {
					comp.update();
				}

				for (SysUser comp : insert) {
					comp.setId(comp.newId()+"");
					comp.setStatus(1);
					comp.insert();
				}

				return AjaxMsg.ok();
			}
		});

	}
	@Action("用户管理")
	@ResponseBody
	@RequestMapping("/genWx")
	public AjaxMsg genWx(final @RequestParam("userid") String userid) {

		return run(new CallBack() {

			@Override
			public AjaxMsg call() throws Exception {
				if(userid==null||userid.length()==0){
					return AjaxMsg.ok();
				}
				WxCpServiceImpl service = WxCp.getInstance().getService();
				
				WxMenu wxMenu = service.menuGet();
				
				String[] us=userid.split(",");
				for(int i=0;i<us.length;i++){
					String id=us[i];
					SysUser user=SysUser.INSTANCE.queryOne("id=?", id);
					//查询用户机构对应的微信id
					UsrOrg org=UsrOrg.INSTANCE.queryOne("id=?", user.getOrgid());
					//检查微信通讯录用户是否存在，存在则更新，不存在则插入
					WxCpUser wu=null;
					try {
						wu=service.userGet(id);
					} catch (Exception e) {
						wu=null;
					}
					
					if(wu==null){
						wu=new WxCpUser();
						wu.setUserId(id);
						wu.setWeiXinId(user.getWexno());
						wu.setName(user.getRealname());
						
						wu.setDepartIds(new Integer[] {Integer.valueOf(org.getWxid())});
						wu.setEmail(user.getMail());
						wu.setGender(user.getSex());
						wu.setMobile(user.getMobile());
						wu.setPosition(user.getPostion());
						service.userCreate(wu);
					}else{
						//wu.setWeiXinId(user.getWxcount());
						wu.setWeiXinId(user.getWexno());
						wu.setName(user.getRealname());
						wu.setDepartIds(new Integer[] {Integer.valueOf(org.getWxid())});
						wu.setEmail(user.getMail());
						wu.setGender(user.getSex());
						wu.setMobile(user.getMobile());
						wu.setPosition(user.getPostion());
						service.userUpdate(wu);
					}
				}

				return AjaxMsg.ok();
			}
		});

	}
	
}
