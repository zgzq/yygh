package com.wx.cp.task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.TimerTask;

import org.slf4j.Logger;

import com.wx.ad.util.ConnectionManager;
import com.wx.ad.util.ConnectionManagerOA;

import my.util.Util;
import my.util.Util.DBTask;

public class MyTimerTask4 extends TimerTask {

	private static Logger logger =org.slf4j.LoggerFactory.getLogger(MyTimerTask4.class);
	@Override
	public void run() {
		Util.syncRunDBTask(new DBTask() {

			@Override
			public void run() throws Exception {
				ConnectionManagerOA cManager = null;
		    	ConnectionManager zManager = null;
		        try {
		        	cManager = new ConnectionManagerOA();
		        	zManager = new ConnectionManager();
		            
		            Calendar now = Calendar.getInstance();
		            int year = now.get(Calendar.YEAR);
		            int month = now.get(Calendar.MONTH)+1;
		            int day = now.get(Calendar.DAY_OF_MONTH);
		            String sql1 = "select sum(kpivalue) num from kpi_value where orgid = ? and kpiid = ? and SUBSTR(pdate,0,6) = ?";
		            String orgid = "1";
		            String kpiid = "328";
		            String yearMonth;
		            String value = "";
		            if(month < 10){
		            	yearMonth = year+"0"+month;
		            }else{
		            	yearMonth = year+""+month;
		            }
		            Object[] params1 = {orgid,kpiid,yearMonth};
		            ResultSet rs1 = zManager.WexecuteQuery(sql1, params1);
		            if(rs1.next()){
		            	value = rs1.getString("num")==null?"0":rs1.getString("num");
		            }
		            rs1.close();
		            zManager.closeRs();
		        	String sql2 = "update  formmain_5635 set field0004 = ? where field0002 = ? and Datepart(Yy,field0001) = ? and Datepart(Mm,field0001)=?";
		        	String hos = "8507457914790233028";
		        	Object[] params2 = {value,hos,year,month};
		        	int num = cManager.WexecuteUpdate(sql2, params2);
		        	if(num  > 0){
		        	}
		            
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }finally{
		        	cManager.closeConn();
		        }

			}
		});
	}

}
