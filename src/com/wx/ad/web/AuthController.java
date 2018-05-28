package com.wx.ad.web;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import my.ann.Action;
import my.ann.Menu;
import my.auth.Auth;
import my.dao.pool.DBManager;
import my.util.SysParam;
import my.web.BaseController;
import my.web.IUser;

import com.wx.ad.dbo.MenuAction;
import com.wx.ad.dbo.SysMenu;
import com.wx.ad.dbo.SysUser;

public class AuthController extends BaseController implements Auth {

	private String menuid;
	private ConcurrentHashMap<String, String> methodActionMap = new java.util.concurrent.ConcurrentHashMap<String, String>();

	public AuthController() {
		init();
	}

	private void init() {
		synchronized (this) {

			try {

				if (menuid != null) {
					return;
				}

				Menu menu = this.getClass().getAnnotation(Menu.class);

				if (menu == null) {
					throw new RuntimeException("no menu annotation ");
				}

				menuid = menu.value();

				SysMenu sysMenu = SysMenu.INSTANCE.queryOne("text=? and url is not null",
						menu.value());

				if (sysMenu == null) {
					throw new RuntimeException("can not find sysMenu ");
				} else {
					menuid = sysMenu.getId();
				}

				if (SysParam.isDevMode()) {
					//MenuAction.INSTANCE.delete(" menuid = ? ", menuid);

					for (Method m1 : this.getClass().getMethods()) {
						if (m1.getModifiers() == Modifier.PUBLIC) {
							if (m1.isAnnotationPresent(Action.class)) {
								Action action = m1.getAnnotation(Action.class);
								logger.debug("{} {}",
										this.getClass().getName(),
										action.value());

								MenuAction m = new MenuAction();
								m.setAction(action.value().toLowerCase());
								m.setMenuid(menuid);

								MenuAction ma = m.queryOne(
										"menuid = ? and action=?",
										m.getMenuid(), m.getAction());
								if (ma == null) {
									m.insert();
								}
								methodActionMap
										.put(m1.getName(), m.getAction());
							}
						}
					}
					DBManager.commitAll();
					DBManager.closeAllConnection();
				}
			} catch (Exception e) {
				logger.error("error", e);
			}
		}
	}

	@Override
	public boolean auth(String methodName, IUser iuser) {

		if (iuser.getUserId().equals("admin")) {
			return true;
		}
		
		
		String action = getActionName(methodName);
		if (action == null) {
			logger.debug("method：{}未设置授权名称", methodName);
			return true;
		}

		SysUser user = SysUser.INSTANCE.loadByName(iuser.getUserId());
		Set<String> actions = user.rights().get(menuid);
		if (actions == null) {
			return false;
		}
		

		if ("查询".equals(action)) {
			return true;
		}

		return actions.contains(getActionName(methodName));
	}

	@Override
	public String getMenuId() {
		return menuid;
	}

	@Override
	public String getActionName(String methodName) {
		return methodActionMap.get(methodName);
	}

}
