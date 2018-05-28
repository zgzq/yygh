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
import com.wx.ad.dbo.KpiPlan;
import com.wx.ad.dbo.Role;
import com.wx.ad.dbo.RoleAp;
import com.wx.ad.dbo.RolePlan;
import com.wx.ad.dbo.SysMenu;
import com.wx.ad.dbo.SysUser;
import com.wx.ad.dbo.UserPlan;
import com.wx.ad.util.PageFactory;
import com.wx.ad.web.AuthController;

@Menu("用户权限方案")
@RequestMapping("/sys/userplan")
@Controller
public class UserPlanController extends AuthController {

	@Action("用户权限方案")
	@RequestMapping("/list")
	public String index(Model m) {
		return "admin/user/userplan";
	}

	@Action("用户权限方案")
	@ResponseBody
	@RequestMapping("/treeData")
	public GridDataModel<SysUser> treeData() {
		return PageFactory.newPage(SysUser.class, "id<>'-1'","");
	}


	@Action("用户权限方案")
	@ResponseBody
	@RequestMapping("/gridData")
	public ModelAndView gridData() {
		String userid = param("userid", "0");

		List<KpiPlan> plans = KpiPlan.INSTANCE.loadAll();
		SysUser user = SysUser.INSTANCE.queryOne("id=?", userid);
		if (user != null) {
			List<UserPlan> list = UserPlan.INSTANCE.query("userid=?", userid);
			for (int i = 0; i < plans.size(); i++) {
				KpiPlan p = plans.get(i);
				p.setHasRight("0");
				
				for (int j = 0; j < list.size(); j++) {
					UserPlan rp = list.get(j);
					if (p.getId().longValue() == rp.getPlanid().longValue()) {
						p.setHasRight("1");
					} else {
						continue;
					}

				}
			}
		}
		GridDataModel<KpiPlan> gridDataModel = new GridDataModel<KpiPlan>();
		gridDataModel.setRows(plans);
		gridDataModel.setTotal(plans.size());

		printJson(JSON.toJSONString(gridDataModel));
		return null;
	}

	@Action("用户权限方案")
	@ResponseBody
	@RequestMapping("/save")
	public AjaxMsg save(final @RequestParam("id") String userid,
			final @RequestParam("rights") String rightstr) {

		return run(new CallBack() {

			@Override
			public AjaxMsg call() throws Exception {
				SysUser user = SysUser.INSTANCE.queryOne("id=?", userid);
				if (user == null) {
					return AjaxMsg.error(i18n("role_not_exists"));
				}
				List<UserPlan> roleaps = new ArrayList<UserPlan>();
				if (rightstr == null || rightstr.length() == 0) {
					UserPlan.INSTANCE.save(roleaps, userid);
				} else {
					String[] rights = rightstr.split(",");
					for (String right : rights) {
						if(right==null||right.length()==0){
							continue;
						}
						UserPlan rp = new UserPlan();
						rp.setPlanid(Long.valueOf(right));
						rp.setUserid(Long.valueOf(userid));
						roleaps.add(rp);
					}
					UserPlan.INSTANCE.save(roleaps, userid);
				}

				return AjaxMsg.ok();
			}
		});

	}

}
