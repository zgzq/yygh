package com.wx.cp.task;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import org.slf4j.Logger;

import me.chanjar.weixin.cp.api.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.WxCpMessage;
import me.chanjar.weixin.cp.bean.WxCpMessage.WxArticle;
import my.dao.pool.DBManager;
import my.util.Util;
import my.util.Util.DBTask;

import com.wx.ad.dbo.SysUser;
import com.wx.cp.wx.WxContst;
import com.wx.cp.wx.WxCp;

public class MyTimerTask3 extends TimerTask {

	private static Logger logger =org.slf4j.LoggerFactory.getLogger(MyTimerTask.class);
	@Override
	public void run() {
		Util.syncRunDBTask(new DBTask() {

			@Override
			public void run() throws Exception {
				Date cd = new Date();
				Date bd = new Date();
				Calendar calendar = Calendar.getInstance(); // 得到日历
				calendar.setTime(cd);// 把当前时间赋给日历
				calendar.add(Calendar.DAY_OF_MONTH, -1); // 设置为前一天
				bd = calendar.getTime(); // 得到前一天的时间

				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String s = formatter.format(cd); // 当前日期
				String bs = formatter.format(bd); // 前一天日期
				WxCpServiceImpl service = WxCp.getInstance().getService();

				// 日期转换为数值格式
				String numdate = "";
				numdate = bs.replaceAll("-", "");

				// 每天推送之前，先计算前一天的指标数据，然后再推送
				// // 调用存储过程\\
				Connection con = null;
				con = DBManager.getConnection();
				CallableStatement callstatement = con
						.prepareCall("call zmkpipk.p_getkpivaue(?,?,?,?,?)");//
				callstatement.setString(1, numdate);
				callstatement.setString(2, numdate);
				callstatement.setString(3, "1");
				callstatement.setString(4, "");
				callstatement.setString(5, "");
				callstatement.execute();// 执行

				// 查询所有有日报的用户
				List<SysUser> list = SysUser.INSTANCE.query(
						" nvl(status,0)=? and nvl(issend,0)=1 and f_strmatch(1,plantypes)=1 ", 1);
				String rurl = WxContst.url + "/kpiday?pdate=" + numdate;
				String rurlVlan = WxContst.urlVlan + "/kpiday?pdate=" + numdate;
				
				for (SysUser user : list) {
					/*if(!user.getId().equals("162")){
						continue;
					}*/
					logger.debug("微信推送消息开始:++"+user.getRealname());
					
					//内网
					WxArticle articleVlan = new WxArticle();  
		            articleVlan.setTitle("中美集团信息部");  
		            articleVlan.setUrl(WxContst.urlVlanRoot+"/building.jsp"); 
		            articleVlan.setPicUrl(WxContst.urlVlanRoot+"/image/wxcp/cpxxb.jpg");
					WxArticle article1 = new WxArticle();  
					article1.setTitle("(中美医疗集团)上市医院\n"+bs+"日报.(内网入口)");  
					article1.setDescription("aaaaaa");  
		            article1.setUrl(rurlVlan + "&userid=" + user.getWxcount()+"&ss=1");  
		            article1.setPicUrl(WxContst.urlVlanRoot+"/image/wxcp/logo_jt.png");
		            WxArticle article2 = new WxArticle();  
		            article2.setTitle("(中美医疗集团)非上市医院\n"+bs+"日报.(内网入口)");  
		            article2.setDescription("bbbbbb");  
		            article2.setUrl(rurlVlan + "&userid=" + user.getWxcount()+"&ss=0");  
		            article2.setPicUrl(WxContst.urlVlanRoot+"/image/wxcp/logo_jt.png");
		            
					//外网
		            WxArticle article = new WxArticle();  
		            article.setTitle("中美集团信息部");  
		            article.setUrl(WxContst.urlRoot+"/building.jsp"); 
		            article.setPicUrl(WxContst.urlRoot+"/image/wxcp/cpxxb.jpg");
					WxArticle article3 = new WxArticle();  
					article3.setTitle("(中美集团)上市医院\n"+bs+"日报.(外网入口)");  
					article3.setDescription("cccccc");  
					article3.setUrl(rurl + "&userid=" + user.getWxcount()+"&ss=1");  
					article3.setPicUrl(WxContst.urlRoot+"/image/wxcp/logo_jt.png");
		            WxArticle article4 = new WxArticle();  
		            article4.setTitle("(中美集团)非上市医院\n"+bs+"日报.(外网入口)");  
					article4.setDescription("dddddd");  
		            article4.setUrl(rurl + "&userid=" + user.getWxcount()+"&ss=0");  
		            article4.setPicUrl(WxContst.urlRoot+"/image/wxcp/logo_jt.png");
		            
		            /*内网消息列表*/
		            
		            List<WxArticle> articleListVlan = new ArrayList<WxArticle>();  
		            articleListVlan.add(articleVlan);  
		            articleListVlan.add(article1);  
		            articleListVlan.add(article2);  
		            /*外网消息列表*/
		            
		            List<WxArticle> articleList = new ArrayList<WxArticle>();  
		            articleList.add(article);  
		            articleList.add(article3);  
		            articleList.add(article4);
		            // 创建图文消息  
		            WxCpMessage messageVlan = new WxCpMessage();  
		            messageVlan.setToUser(user.getId());
		            messageVlan.setAgentId(WxContst.AgentId.toString());
		            messageVlan.setMsgType("news");
		            messageVlan.setArticles(articleListVlan);
		            
		            WxCpMessage message = new WxCpMessage();  
		            message.setToUser(user.getId());
		            message.setAgentId(WxContst.AgentId.toString());
		            message.setMsgType("news");
		            message.setArticles(articleList);
		            
		            // 发送图文消息
		            service.messageSend(messageVlan);
		            service.messageSend(message);
		            
					logger.debug("微信推送消息结束:++"+user.getRealname());
				}

			}
		});
	}

}
