package com.wx.cp.task;

import java.util.ArrayList;
import java.util.List;

import com.wx.cp.wx.WxCp;

import me.chanjar.weixin.common.bean.WxMenu;
import me.chanjar.weixin.common.bean.WxMenu.WxMenuButton;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpServiceImpl;
import my.web.BaseController;

public class WxcpMenu extends BaseController {
	private static WxCpServiceImpl service = WxCp.getInstance().getService();
	public  String getUserid(){
		return getLoginUer().getUserId();
	}
	public static void menuDelete() {
		try {
			WxCp.getInstance().getService().menuDelete();
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void menuCreate() {
		
		//WxMenu{buttons=[WxMenuButton{type='null', name='上市医院', key='null', url='null', subButtons=[WxMenuButton{type='view', name='内网用户', key='null', url='http://www.baidu.com', subButtons=[]}, WxMenuButton{type='view', name='外网用户', key='null', url='http://www.baidu.com', subButtons=[]}]}, WxMenuButton{type='null', name='非上市医', key='null', url='null', subButtons=[WxMenuButton{type='view', name='内网用户', key='null', url='http://www.baidu.com', subButtons=[]}, WxMenuButton{type='view', name='外网用户', key='null', url='http://www.baidu.com', subButtons=[]}]}]}

		WxMenu menu = new WxMenu();

		WxMenuButton button1 = new WxMenu.WxMenuButton();
		button1.setKey("button1");
		button1.setName("上市医院");
		button1.setType("click");
		
		WxMenuButton button11 = new WxMenu.WxMenuButton();
		button11.setKey("button11");
		button11.setName("内网用户");
		button11.setType("view");
		button11.setUrl("http://10.0.42.223/wxcp/qyreq/kpiday?pdate=20161128&userid="+new WxcpMenu().getUserid());
		button11.setSubButtons(null);
		WxMenuButton button12 = new WxMenu.WxMenuButton();
		button12.setKey("button12");
		button12.setName("外网用户");
		button12.setType("view");
		button12.setUrl("http://10.0.42.223/wxcp/qyreq/kpiday?pdate=20161128&userid="+new WxcpMenu().getUserid());
		button12.setSubButtons(null);
		
		List<WxMenuButton> subButtons1=new ArrayList<WxMenuButton>();
		subButtons1.add(button11);
		subButtons1.add(button12);
		button1.setSubButtons(subButtons1);

		WxMenuButton button2 = new WxMenu.WxMenuButton();
		button2.setKey("button2");
		button2.setName("上市医院");
		button2.setType("click");
		
		WxMenuButton button21 = new WxMenu.WxMenuButton();
		button21.setKey("button21");
		button21.setName("内网用户");
		button21.setType("view");
		button21.setUrl("http://www.sohu.com");
		button21.setSubButtons(null);
		WxMenuButton button22 = new WxMenu.WxMenuButton();
		button22.setKey("button22");
		button22.setName("外网用户");
		button22.setType("view");
		button22.setUrl("http://www.sohu.com");
		button22.setSubButtons(null);
		
		List<WxMenuButton> subButtons2=new ArrayList<WxMenuButton>();
		subButtons2.add(button11);
		subButtons2.add(button12);
		button2.setSubButtons(subButtons2);
		
		
		menu.getButtons().add(button1);
		menu.getButtons().add(button2);
		

		System.out.println(menu.toJson());
		
		// 创建菜单
		try {
			service.menuCreate(menu);
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
