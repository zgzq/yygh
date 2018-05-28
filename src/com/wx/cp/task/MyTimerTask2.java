package com.wx.cp.task;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import org.slf4j.Logger;

import me.chanjar.weixin.cp.api.WxCpServiceImpl;
import my.dao.pool.DBManager;
import my.util.Util;
import my.util.Util.DBTask;

import com.wx.ad.dbo.SysUser;
import com.wx.cp.wx.WxContst;
import com.wx.cp.wx.WxCp;

public class MyTimerTask2 extends TimerTask {

	private static Logger logger =org.slf4j.LoggerFactory.getLogger(MyTimerTask2.class);
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
				String msg = "";
				String msgVlan = "";
				String dest = "";
				String destVlan = "";
				msg = ""+bs+"日报:(外网用户)请点击查看 !";
				msgVlan = ""+bs+"日报:(内网用户)请点击查看 !";
				
				for (SysUser user : list) {
//					if(!user.getId().equals("162")){
//						continue;
//					}
					logger.debug("微信推送消息开始:++"+user.getRealname());
					String send="";
					String sendVlan="";
					//上市医院
					send = rurl + "&userid=" + user.getWxcount()+"&ss=1"; 
					sendVlan = rurlVlan + "&userid=" + user.getWxcount()+"&ss=1";
					dest = String.format("<a href=\"%s\">%s</a>", send, msg);
					destVlan = String.format("<a href=\"%s\">%s</a>", sendVlan, msgVlan);
					
					System.out.println(user.getWxcount());
					WxCp.getInstance().send(user.getWxcount(), "(中美医疗集团)上市医院"+"\n"+dest+"\n\n"+destVlan);
					//非上市医院
					send = rurl + "&userid=" + user.getWxcount()+"&ss=0"; 
					sendVlan = rurlVlan + "&userid=" + user.getWxcount()+"&ss=0";
					dest = String.format("<a href=\"%s\">%s</a>", send, msg);
					destVlan = String.format("<a href=\"%s\">%s</a>", sendVlan, msgVlan);
					System.out.println(user.getWxcount());
					WxCp.getInstance().send(user.getWxcount(), "(中美集团)非上市医院"+"\n"+dest+"\n\n"+destVlan);
					logger.debug("微信推送消息结束:++"+user.getRealname());
				}

			}
		});
	}

}
