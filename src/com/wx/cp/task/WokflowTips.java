package com.wx.cp.task;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import me.chanjar.weixin.cp.api.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.WxCpUser;
import my.dao.pool.DBManager;
import my.util.Util;
import my.util.Util.DBTask;

import org.slf4j.Logger;

import com.wx.ad.dbo.KpiPlan;
import com.wx.ad.dbo.RolePlan;
import com.wx.ad.dbo.SysUser;
import com.wx.ad.dbo.UserRole;
import com.wx.cp.wx.WxContst;
import com.wx.cp.wx.WxCp;

public class WokflowTips implements Runnable {
	private static Logger logger = org.slf4j.LoggerFactory
			.getLogger(WokflowTips.class);

	public static void main(String[] args) {
		WokflowTips tips = new WokflowTips();
		tips.run();
	}

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
						" nvl(status,0)=? and f_strmatch(1,plantypes)=1 ", 1);
				String rurl = WxContst.url + "/kpiday?pdate=" + numdate;
				String msg = "";
				String dest = "";
				msg = "中美集团 -日报: " + bs + " 数据指标,请点击此信息查看 !";
				for (SysUser user : list) {
					rurl = rurl + "&userid=" + user.getId();
					dest = String.format("<a href=\"%s\">%s</a>", rurl, msg);
					
					
					//WxCp.getInstance().send(user.getId(), dest);
				}

				// String rurl=WxContst.url+"/kpi?pdate="+bs;
				// String dest =
				// String.format("<a href=\"%s\">%s</a>",rurl,"中美集团 "+bs+" 数据指标,请点击此信息查看 !");
				// //取出部门下所有的人员
				// List<WxCpUser> list=service.departGetUsers(3, true, 1);
				// String users="";
				// for(int i=0;i<list.size();i++){
				// if(users.length()>0){
				// users=users+"|"+list.get(i).getUserId();
				// }else{
				// users=list.get(i).getUserId();
				// }
				// }
				// logger.info("用户列表："+users);
				// System.out.println(dest);
				// //users="huyawei";
				// WxCp.getInstance().send(users,dest);

			}
		});

	}

}
