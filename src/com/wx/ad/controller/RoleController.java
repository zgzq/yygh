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
import my.web.BaseController.CallBack;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.wx.ad.bean.GridDataModel;
import com.wx.ad.dbo.Role;
import com.wx.ad.dbo.RoleAp;
import com.wx.ad.dbo.SysMenu;
import com.wx.ad.web.AuthController;

@Menu("角色权限管理")
@RequestMapping("/sys/role")
@Controller
public class RoleController extends AuthController {

	@Action("角色权限管理")
	@RequestMapping("/list")
	public String index(Model m) {
		return "admin/role/role";
	}

	@Action("角色权限管理")
	@ResponseBody
	@RequestMapping("/treeData")
	public List<Role> treeData() {
		return Role.INSTANCE.query("");
	}
	

	@Action("角色权限管理")
	@ResponseBody
	@RequestMapping("/add")
	public AjaxMsg add() {

		return run(new CallBack() {

			@Override
			public AjaxMsg call() throws Exception {
				Role r = form(Role.class);

				if (StringUtils.isNotBlank(r.getRoleid())) {
					r.update();

					return AjaxMsg.ok();
				}

				Role old = Role.INSTANCE.queryOne("rolename=? ",
						r.getRolename());

				if (old != null) {
					return AjaxMsg.error(i18n("rolename_duplicate"));
				} else {
					r.setRoleid(String.valueOf(r.newId()));
					r.setCt(getDateTime());
					r.insert();
				}
				return AjaxMsg.ok();
			}
		});
	}

	@Action("角色权限管理")
	@ResponseBody
	@RequestMapping("/delete")
	public AjaxMsg delete() {

		return run(new CallBack() {

			@Override
			public AjaxMsg call() throws Exception {
				Role r = form(Role.class);
				r.delete();
				return AjaxMsg.ok();
			}
		});
	}

	@Action("角色权限管理")
	@ResponseBody
	@RequestMapping("/gridData")
	public GridDataModel<SysMenu> gridData() {
		String roleid = param("roleid", "0");

		List<SysMenu> menus = SysMenu.INSTANCE.loadAll();
		Role role = Role.INSTANCE.loadByName(roleid);
		if (role != null) {
			Map<String, Set<String>> map = role.roleApsMap();
			for (SysMenu menu : menus) {
				menu.setHasRight(map.get(menu.getId()));
			}
		}
		GridDataModel<SysMenu> gridDataModel = new GridDataModel<SysMenu>();
		gridDataModel.setRows(menus);
		gridDataModel.setTotal(menus.size());

		return gridDataModel;
	}
	/*public ModelAndView gridData() {
		String roleid = param("roleid", "0");

		List<SysMenu> menus = SysMenu.INSTANCE.loadAll();
		Role role = Role.INSTANCE.loadByName(roleid);
		if (role != null) {
			Map<String, Set<String>> map = role.roleApsMap();
			for (SysMenu menu : menus) {
				menu.setHasRight(map.get(menu.getId()));
			}
		}
		GridDataModel<SysMenu> gridDataModel = new GridDataModel<SysMenu>();
		gridDataModel.setRows(menus);
		gridDataModel.setTotal(menus.size());

		printJson(JSON.toJSONString(gridDataModel));
		return null;
	}*/
	
	

	@Action("角色权限管理")
	@ResponseBody
	@RequestMapping("/save")
	public AjaxMsg save(final @RequestParam("id") String roleid,
			final @RequestParam("rights") String rightstr) {

		return run(new CallBack() {

			@Override
			public AjaxMsg call() throws Exception {
				Role role = Role.INSTANCE.loadByName(roleid);
				if (role == null) {
					return AjaxMsg.error(i18n("role_not_exists"));
				}
				List<RoleAp> roleaps = new ArrayList<RoleAp>();
				if (rightstr == null || rightstr.length() == 0) {
					role.saveRoleAps(roleaps);
				}

				String[] rights = rightstr.split(",");
				Map<String, Set<String>> map = new HashMap<String, Set<String>>();
				
				Set<String> pmenu = new HashSet<String>();

				for (String right : rights) {
					if (right == null || right.length() == 0
							|| right.indexOf('_') <= 0) {
						continue;
					}
					String[] m_a = right.split("_");
					Set<String> actions = map.get(m_a[0].trim());
					if (actions == null) {
						actions = new HashSet<String>();
						map.put(m_a[0].trim(), actions);
					}
					actions.add(m_a[1].trim());
				}

				

				for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
					RoleAp ap = new RoleAp();
					ap.setRoleid(roleid);
					ap.setMenuid(entry.getKey());
					ap.setActionset(build(entry.getValue()));
					roleaps.add(ap);
					
					addpid(entry.getKey(), pmenu);

				}

				for (String pid : pmenu) {
					RoleAp ap = new RoleAp();
					ap.setRoleid(roleid);
					ap.setMenuid(pid);
					ap.setActionset("");
					roleaps.add(ap);
				}

				System.out.println(JSON.toJSONString(roleaps));
				
				Set<String> set =new HashSet<String>();
				
				for(RoleAp ap:roleaps ){
					if(set.contains(String.format("%s_%s", ap.getRoleid(),ap.getMenuid()))){
						System.out.println(ap.getRoleid()+"_"+ap.getMenuid());
					}else{
						set.add(String.format("%s_%s", ap.getRoleid(),ap.getMenuid()));
					}
				}
				
				role.saveRoleAps(roleaps);

				return AjaxMsg.ok();
			}
		});

	}

	protected void addpid(String key, Set<String> pmenu) {
		if (StringUtils.isBlank(key)) {
			return;
		}
		SysMenu menu = SysMenu.INSTANCE.loadByName(key);
		if (StringUtils.isNotBlank(menu.getPid())) {
			pmenu.add(menu.getPid());
			addpid(menu.getPid(), pmenu);
		}

	}

	protected String build(Set<String> value) {
		StringBuilder sb = new StringBuilder();
		for (String v : value) {
			if (sb.length() > 0) {
				sb.append(",");
			}
			sb.append(v);
		}
		return sb.toString();
	}
}
