package com.wx.ad.controller;

import java.util.ArrayList;
import java.util.List;

import my.ann.Action;
import my.ann.Menu;
import my.web.AjaxMsg;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wx.ad.bean.GridDataModel;
import com.wx.ad.dbo.Role;
import com.wx.ad.dbo.SysUser;
import com.wx.ad.dbo.UserRole;
import com.wx.ad.util.PageFactory;
import com.wx.ad.web.AuthController;

@Menu("角色用户管理")
@RequestMapping("/sys/userrole")
@Controller
public class UserRoleController extends AuthController {

	@Action("角色用户管理")
	@RequestMapping("/list")
	public String index(Model m) {

		return "admin/role/userRole";
	}

	@Action("角色用户管理")
	@ResponseBody
	@RequestMapping("/treeData")
	public List<Role> treeData() {
		return Role.INSTANCE.query("");
	}

	@Action("角色用户管理")
	@ResponseBody
	@RequestMapping("/userGridData")
	public GridDataModel<SysUser> userGridData() {
		String realname = param("realname", "");
		String roleid = param("roleid", "");

		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();


		if (realname.length() > 0) {
			filter.append(" and realname like ? ");
			params.add(realname + "%");
		}

		if (roleid.length() > 0) {
			filter.append(" and id in(select userid from sys_user_role where roleid = ? )  ");
			params.add(roleid);
		}

		return PageFactory.newPage(SysUser.class, filter.toString(),"",
				params.toArray());
	}

	@Action("角色用户管理")
	@ResponseBody
	@RequestMapping("/add")
	public AjaxMsg add() {

		return run(new CallBack() {

			@Override
			public AjaxMsg call() throws Exception {
				UserRole r = form(UserRole.class);
				
				
				String ids = r.getUserid();
				
				for(String id:ids.split(",")){
					SysUser user = SysUser.INSTANCE.loadByName(id);

					List<String> roleids = new ArrayList<String>();

					for (Role role : user.roles()) {
						roleids.add(role.getRoleid());
					}
					if (!roleids.contains(r.getRoleid()))
						roleids.add(r.getRoleid());

					user.saveRoles(roleids);
				}
				return AjaxMsg.ok();
			}
		});
	}

	@Action("角色用户管理")
	@ResponseBody
	@RequestMapping("/delete")
	public AjaxMsg delete() {
		return run(new CallBack() {

			@Override
			public AjaxMsg call() throws Exception {
				UserRole r = form(UserRole.class);

				SysUser user = SysUser.INSTANCE.loadByName(r.getUserid());

				List<String> roleids = new ArrayList<String>();

				for (Role role : user.roles()) {
					roleids.add(role.getRoleid());
				}

				roleids.remove(r.getRoleid());

				user.saveRoles(roleids);
				return AjaxMsg.ok();
			}
		});
	}
}
