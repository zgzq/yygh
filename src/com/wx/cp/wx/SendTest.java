package com.wx.cp.wx;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.chanjar.weixin.common.bean.WxMenu;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.WxCpMessage;
import me.chanjar.weixin.cp.bean.WxCpMessage.WxArticle;
import me.chanjar.weixin.cp.bean.WxCpUser;
import me.chanjar.weixin.cp.bean.WxCpXmlMessage;
import my.wx.mp.vo.Article;

public class SendTest {
	public static void main(String[] args) {
		try {
			WxCpServiceImpl service = WxCp.getInstance().getService();
			/*//取出部门下所有的人员
			List<WxCpUser> list=service.departGetUsers(2, true, 1);
			String users="";
			for(int i=0;i<list.size();i++){
				if(users.length()>0){
					users=users+"|"+list.get(i).getUserId();
				}else{
					users=list.get(i).getUserId();
				}
			}*/
			
			//WxCp.getInstance().send("162", "<a href=\"http://10.0.42.223/wxcp/qyreq/kpimp\">2017目标对比</a>");
			//给用户发送消息
			WxArticle article0 = new WxArticle();
			article0.setTitle("微信公众帐号开发教程Java版");  
            article0.setUrl("#");  

            WxArticle article1 = new WxArticle();  
            article1.setTitle("第4篇消息及消息处理工具的封装");  
            article1.setUrl("");  

            WxArticle article2 = new WxArticle();  
            article2.setTitle("第5篇各种消息的接收与响应");  
            article2.setUrl("");  

            WxArticle article3 = new WxArticle();  
            article3.setTitle("第6篇文本消息的内容长度限制揭秘");  
            article3.setUrl("");  
            
            WxArticle article4 = new WxArticle();  
            article4.setTitle("第7篇文本消息的内容长度限制揭秘");  
            article4.setUrl("");  
            
            List<WxArticle> articleList = new ArrayList<WxArticle>();  
            articleList.add(article0);  
            articleList.add(article1);  
            articleList.add(article2);  
            articleList.add(article3);  
            articleList.add(article4);  
            
            // 创建图文消息  
            WxCpMessage message = new WxCpMessage();  
            message.setToUser("162");
            message.setAgentId(WxContst.AgentId.toString());
            message.setMsgType("news");
            message.setArticles(articleList);
            
            service.messageSend(message);
            
			
//			WxMenu wxMenu=WxCp.getInstance().getService().menuGet();
//			System.out.println(wxMenu);
			/*WxCp.getInstance().getService().menuDelete();*/
//////			
//			WxCp.getInstance().menuCreate();
			//
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
