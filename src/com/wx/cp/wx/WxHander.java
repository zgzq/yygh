package com.wx.cp.wx;

import java.util.List;

import my.cache.CacheManager;
import my.dao.pool.DBManager;
import my.wx.WeiXinManager;
import my.wx.mp.commons.WxMsgType;
import my.wx.mp.core.WxDefaultHandler;
import my.wx.mp.vo.OutPutMsg;
import my.wx.mp.vo.ReceiveMsg;
import my.wx.test.SysUserWechat;

public class WxHander extends WxDefaultHandler {
	@Override
	public OutPutMsg eClick(ReceiveMsg rm) {
		OutPutMsg om = new OutPutMsg(rm);
		om.setMsgType(WxMsgType.text.name());
		om.setContent("MENU_CLICK:" + rm.getEventKey()
				+ " <a href=\"http://www.huogb.com/weixin/test?openid="
				+ rm.getFromUserName() + "\">test url</a>");

		if (WeiXinManager.EVENT_WY.equals(rm.getEventKey())) {
			//om = processWY(rm);
		}

		if (log.isInfoEnabled()) {
			log.info("接收到菜单点击消息...");
			log.info("from={}, to={}, event={}, key={}", rm.getFromUserName(),
					rm.getToUserName(), rm.getEvent(), rm.getEventKey());
		}
		return om;
	}



	/*
	 * 关注后自动发送的消息。
	 */
	@Override
	public OutPutMsg eSub(ReceiveMsg rm) {
		OutPutMsg om = new OutPutMsg(rm);
		om.setMsgType(WxMsgType.text.name());
		om.setContent("欢迎关注中美集团企业号");
		if (log.isInfoEnabled()) {
			log.info("接收到订阅消息...");
			log.info("from={}, to={}, event={}", rm.getFromUserName(),
					rm.getToUserName(), rm.getEvent());
		}
		return om;
	}

	/*
	 * 取消关注后可以重新绑定其他的手机号
	 */
	@Override
	public void eUnSub(ReceiveMsg rm) {
		if (log.isInfoEnabled()) {
			log.info("接收到退订消息...");
			log.info("from={}, to={}, event={}", rm.getFromUserName(),
					rm.getToUserName(), rm.getEvent());
		}

		run(rm, new EventCall() {

			@Override
			public void call(ReceiveMsg rm) throws Exception {
				String openid = rm.getFromUserName();
			}
		});

	}

	public OutPutMsg run(ReceiveMsg rm, CallBack callback) {
		OutPutMsg om = new OutPutMsg(rm);
		try {
			om = callback.call(rm);
			DBManager.commitAll();
		} catch (Exception e) {
			DBManager.rollbackAll();
			om.setContent("微信服务暂不可用.");
			log.error("error", e);
		} finally {
			DBManager.closeAllConnection();
		}
		return om;
	}

	public void run(ReceiveMsg rm, EventCall callback) {
		try {
			callback.call(rm);
			DBManager.commitAll();
		} catch (Exception e) {
			DBManager.rollbackAll();
			log.error("error", e);
		} finally {
			DBManager.closeAllConnection();
		}
	}

	public static interface CallBack {
		public OutPutMsg call(ReceiveMsg rm) throws Exception;
	}

	public static interface EventCall {
		public void call(ReceiveMsg rm) throws Exception;
	}

}
