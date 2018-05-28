package com.wx.ad.controller.kpimp;

import java.sql.CallableStatement;
import java.sql.Connection;

import my.dao.pool.DBManager;

public class KpimpUtil {
		public static void genValue(String orgid,String yearmonth ,String userid){
		// 调用存储过程\\
		Connection con = null;
		try {
			con = DBManager.getConnection();
			CallableStatement callstatement = con
					.prepareCall("call kpicalpk.P_mp(?,?,?)");//
			callstatement.setString(1, orgid);
			callstatement.setString(2, yearmonth);
			callstatement.setString(3, userid);
			callstatement.execute();// 执行
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

//			try {
//				if (con != null) {
//					con.close();
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

		}
	}
		
		public static void genValueHz1(String dt ,String userid){
			// 调用存储过程
			Connection con = null;
			try {
				con = DBManager.getConnection();
				CallableStatement callstatement = con
						.prepareCall("call kpicalpk.P_mp_hz1(?,?)");//
				callstatement.setString(1, dt);
				callstatement.setString(2, userid);
				callstatement.execute();// 执行
			} catch (Exception e) {
				e.printStackTrace();
			} finally {


			}
		}		
		public static void genValueHz2(String dt ,String userid){
			// 调用存储过程
			Connection con = null;
			try {
				con = DBManager.getConnection();
				CallableStatement callstatement = con
						.prepareCall("call kpicalpk.P_mp_hz2(?,?)");//
				callstatement.setString(1, dt);
				callstatement.setString(2, userid);
				callstatement.execute();// 执行
			} catch (Exception e) {
				e.printStackTrace();
			} finally {


			}
		}		
		public static void genValueHz3(String dt1 , String dt2 ,String userid){
			// 调用存储过程
			Connection con = null;
			try {
				con = DBManager.getConnection();
				CallableStatement callstatement = con
						.prepareCall("call kpicalpk.P_mp_hz3(?,?,?)");//
				callstatement.setString(1, dt1);
				callstatement.setString(2, dt2);
				callstatement.setString(3, userid);
				callstatement.execute();// 执行
			} catch (Exception e) {
				e.printStackTrace();
			} finally {


			}
		}		
		public static void genValueHz4(String dt1 , String dt2 ,String userid){
			// 调用存储过程
			Connection con = null;
			try {
				con = DBManager.getConnection();
				CallableStatement callstatement = con
						.prepareCall("call kpicalpk.P_mp_hz4(?,?,?)");//
				callstatement.setString(1, dt1);
				callstatement.setString(2, dt2);
				callstatement.setString(3, userid);
				callstatement.execute();// 执行
			} catch (Exception e) {
				e.printStackTrace();
			} finally {


			}
		}	
		
		public static void genValueHz5(String yearmonth ,String userid){
			// 调用存储过程
			Connection con = null;
			try {
				con = DBManager.getConnection();
				CallableStatement callstatement = con
						.prepareCall("call kpicalpk.P_mp_hz5(?,?)");//
				callstatement.setString(1, yearmonth);
				callstatement.setString(2, userid);
				callstatement.execute();// 执行
			} catch (Exception e) {
				e.printStackTrace();
			} finally {


			}
		}	
		public static void genValueHz6(String yearmonth ,String userid){
			// 调用存储过程
			Connection con = null;
			try {
				con = DBManager.getConnection();
				CallableStatement callstatement = con
						.prepareCall("call kpicalpk.P_mp_hz6(?,?)");//
				callstatement.setString(1, yearmonth);
				callstatement.setString(2, userid);
				callstatement.execute();// 执行
			} catch (Exception e) {
				e.printStackTrace();
			} finally {


			}
		}	
}
