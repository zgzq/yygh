package com.wx.cp.wx;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxMenu;
import me.chanjar.weixin.common.bean.WxMenu.WxMenuButton;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.cp.api.WxCpInMemoryConfigStorage;
import me.chanjar.weixin.cp.api.WxCpMessageHandler;
import me.chanjar.weixin.cp.api.WxCpMessageRouter;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.WxCpMessage;
import me.chanjar.weixin.cp.bean.WxCpUser;
import me.chanjar.weixin.cp.bean.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.WxCpXmlOutMessage;
import me.chanjar.weixin.cp.bean.WxCpXmlOutTextMessage;

import org.slf4j.Logger;

public class WxCp {
	private static Logger logger =org.slf4j.LoggerFactory.getLogger(WxCp.class);
	
	public static final String CorpID=WxContst.CorpID;//企业号id
	public static final String CorpSecret=WxContst.CorpSecret;//企业号id
	public static final String AESKEY = WxContst.AESKEY;// EncodingAESKey
	public static final String TOKEN = WxContst.TOKEN;
	public static final Integer AgentId =WxContst.AgentId;
	
	private WxCpServiceImpl service = null;
	private WxCpInMemoryConfigStorage store = null;
	private static WxCp cp = null;
	private WxCpMessageRouter wxCpMessageRouter;
	
	public static WxCp getInstance() {
		if (cp == null) {
			synchronized (WxCp.class) {
				if (cp == null) {
					cp = new WxCp();
				}
			}
		}
		return cp;
	}

	private WxCp() {
		init();
	}

	private void init() {
		service = new WxCpServiceImpl();

		store = new WxCpInMemoryConfigStorage();
		store.setAesKey(AESKEY);
		store.setCorpId(CorpID);
		store.setToken(TOKEN);
		store.setCorpSecret(CorpSecret);
		store.setAgentId(AgentId+"");

		service.setWxCpConfigStorage(store);

		wxCpMessageRouter = new WxCpMessageRouter(service);

		wxCpMessageRouter.rule().async(false).agentId(AgentId) // 拦截内容为“哈哈”的消息
				.handler(new WxCpMessageHandler() {
					@Override
					public WxCpXmlOutMessage handle(WxCpXmlMessage wxMessage,
							Map<String, Object> context,
							WxCpService wxCpService,
							WxSessionManager sessionManager)
							throws WxErrorException {
						logger.debug("message from :{}",wxMessage.getFromUserName());
						logger.debug("message to :{}",wxMessage.getToUserName());
						WxCpUser user = wxCpService.userGet(wxMessage.getFromUserName());
						
						logger.debug("message from :{}",user==null?"":user.toJson());
						logger.debug("event:"+wxMessage.getEvent());
						String content = String.format("收到来自%s的消息，消息内容:%s", user.getName(),wxMessage.getContent());
						
						if("WX_BTN_TEST".equals(wxMessage.getEventKey()) ){
							content=String.format("用户%s,点击了测试按钮", user.getName(),wxMessage.getEvent());;
						}
						
						if("scancode_waitmsg".equals(wxMessage.getEvent())){
							content=String.format("用户%s,点击了扫码按钮,收到扫码结果：%s", user.getName(),wxMessage.getScanCodeInfo().getScanResult());
						}
						if(WxConsts.EVT_SUBSCRIBE.equals(wxMessage.getEvent())){
							content="欢迎关注中美集团";
						}
						
						 WxCpXmlOutTextMessage m = WxCpXmlOutMessage
					                .TEXT()
					                .content(content)
					                .fromUser(wxMessage.getToUserName())
					                .toUser(wxMessage.getFromUserName())
					            .build();
						return m;
					}
				}).end();

	}

	public void send(String tousers,String msg) throws WxErrorException {
		WxCpMessage message = new WxCpMessage();
		message.setAgentId(AgentId+"");
		message.setToUser(tousers);
		message.setMsgType("text");
		message.setContent(msg);
		service.messageSend(message);
	}

	public void menuCreate() throws WxErrorException {
		service.getAccessToken();
		
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
		button11.setUrl("http://www.sohu.com");
		button11.setSubButtons(null);
		WxMenuButton button12 = new WxMenu.WxMenuButton();
		button12.setKey("button12");
		button12.setName("外网用户");
		button12.setType("view");
		button12.setUrl("http://www.sohu.com");
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
		
//		button = new WxMenu.WxMenuButton();
//		button.setKey("WX_BTN_TEST2");
//		button.setName("扫码");
//		button.setType("scancode_push");
//		button.setType("scancode_waitmsg");
//		menu.getButtons().add(button);
		
//		String url="";
//		button.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+getStore().getCorpId()+"&redirect_uri="+url+"&response_type=code&scope=snsapi_base&state=1#wechat_redirect");
//		menu.getButtons().add(button);
		
		System.out.println(menu.toJson());
		//service.menuCreate(menu);
		
		// 设置菜单
		getInstance().getService().menuCreate(menu);
	}

	public static void main(String[] args) throws WxErrorException {

//		WxMenu wxMenu = getInstance().getService().menuGet();
//		System.out.println(wxMenu == null ? "null" : wxMenu.toJson());
//		getInstance().getService().menuDelete();

		getInstance().menuCreate();
		// send();
	}

	public WxCpServiceImpl getService() {
		return service;
	}

	public void setService(WxCpServiceImpl service) {
		this.service = service;
	}

	public WxCpInMemoryConfigStorage getStore() {
		return store;
	}

	public void setStore(WxCpInMemoryConfigStorage store) {
		this.store = store;
	}

	public WxCpMessageRouter getWxCpMessageRouter() {
		return wxCpMessageRouter;
	}

	public void setWxCpMessageRouter(WxCpMessageRouter wxCpMessageRouter) {
		this.wxCpMessageRouter = wxCpMessageRouter;
	}

}
