package com.wx.ad.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wx.ad.util.ConnectionManager;

import my.dao.pool.DBManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ZmCRCUtil {
		public static int genValue(String year ,String month){
		// 调用存储过程\\
		Connection con = null;
		int recode = 0;
		try {
			con = DBManager.getConnection();
			
			CallableStatement callstatement = con
					.prepareCall("call pro_crc_info(?,?)");//
			callstatement.setString(1, year);
			callstatement.setString(2, month);
			callstatement.execute();// 执行
		} catch (Exception e) {
			e.printStackTrace();
			recode = 1;
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
		return recode;
	}
		
		public static JSONArray geneList(String year ,String month){
			ConnectionManager cManager = null;
			JSONArray list = new JSONArray();
			try {
				cManager = new ConnectionManager();
				String sql = "select * from zm_crc_info where year = ? and month = ?";
				Object[] params = {year,month};
				ResultSet rs = cManager.WexecuteQuery(sql, params);
				while(rs.next()){
					JSONObject a = new JSONObject();
					String patient_id = rs.getString("patient_id") == null ? "--":rs.getString("patient_id");
					String name = rs.getString("name")== null ? "--":rs.getString("name");
					String sex = rs.getString("sex")== null ? "--":rs.getString("sex");
					String age = rs.getString("age")== null ? "--":rs.getString("age");
					String dept_name = rs.getString("dept_name")== null ? "--":rs.getString("dept_name");
					String visit_date = rs.getString("visit_date")== null ? "--":rs.getString("visit_date");
					String mailing_address = rs.getString("mailing_address")== null ? "--":rs.getString("mailing_address");
					String ordered_by_doctor = rs.getString("ordered_by_doctor")== null ? "--":rs.getString("ordered_by_doctor");
					String registration_status = rs.getString("registration_status")== null ? "--":rs.getString("registration_status");
					String charge_type = rs.getString("charge_type")== null ? "--":rs.getString("charge_type");
					String first_visit_indicator = rs.getString("first_visit_indicator")== null ? "--":rs.getString("first_visit_indicator");
					a.put("patient_id", patient_id);
					a.put("name", name);
					a.put("sex", sex);
					a.put("age", age);
					a.put("dept_name", dept_name);
					a.put("visit_date", visit_date);
					a.put("mailing_address", mailing_address);
					a.put("ordered_by_doctor", ordered_by_doctor);
					
					if("1".equals(registration_status)){
						a.put("registration_status", "未就诊");
					}else if("2".equals(registration_status)){
						a.put("registration_status", "就诊");
					}else if("3".equals(registration_status)){
						a.put("registration_status", "开单收费");
					}else if("4".equals(registration_status)){
						a.put("registration_status", "退号");
					}else{
						a.put("registration_status", "");
					}
					a.put("charge_type", charge_type);
					if("0".equals(first_visit_indicator)){
						a.put("first_visit_indicator", "复诊");
						
					}else if("1".equals(first_visit_indicator)){
						a.put("first_visit_indicator", "初诊");
					}else{
						a.put("first_visit_indicator", "");
					}
					list.add(a);
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				cManager.closeConn();
			}
			return list;
		}
		public static JSONArray geneList2(String year ,String month){
			ConnectionManager cManager = null;
			JSONArray list = new JSONArray();
			try {
				cManager = new ConnectionManager();
				String sql = "select * from zm_crc_dept where year = ? and month = ?";
				Object[] params = {year,month};
				ResultSet rs = cManager.WexecuteQuery(sql, params);
				while(rs.next()){
					JSONObject a = new JSONObject();
					String patient_id = rs.getString("patient_id") == null ? "--":rs.getString("patient_id");
					String item_name = rs.getString("item_name")== null ? "--":rs.getString("item_name");
					String dept_name = rs.getString("dept_name")== null ? "--":rs.getString("dept_name");
					String performed_by = rs.getString("performed_by")== null ? "--":rs.getString("performed_by");
					String charges = rs.getString("charges")== null ? "--":rs.getString("charges");
					String req_class = rs.getString("req_class")== null ? "--":rs.getString("req_class");
					String ordered_doctor = rs.getString("ordered_doctor")== null ? "--":rs.getString("ordered_doctor");
					String visit_time = rs.getString("visit_time")== null ? "--":rs.getString("visit_time");
					a.put("patient_id", patient_id);
					a.put("item_name", item_name);
					a.put("dept_name", dept_name);
					a.put("performed_by", performed_by);
					a.put("charges", charges);
					a.put("req_class", req_class);
					a.put("ordered_doctor", ordered_doctor);
					a.put("visit_time", visit_time);
					list.add(a);
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				cManager.closeConn();
			}
			return list;
		}
		
		public static int genValueHz1(String year ,String month){
			// 调用存储过程
			Connection con = null;
			int recode = 0;
			try {
				con = DBManager.getConnection();
				CallableStatement callstatement = con
						.prepareCall("call pro_crc_dept(?,?)");//
				callstatement.setString(1, year);
				callstatement.setString(2, month);
				callstatement.execute();// 执行
			} catch (Exception e) {
				e.printStackTrace();
				recode = 1;
			} finally {


			}
			return recode;
		}		
		
}
