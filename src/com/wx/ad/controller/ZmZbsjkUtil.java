package com.wx.ad.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wx.ad.util.ConnectionManager;

import my.dao.pool.DBManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ZmZbsjkUtil {
	public static JSONArray geneList(String year ,String month,String departid){
		ConnectionManager cManager = null;
		JSONArray list = new JSONArray();
		try {
			cManager = new ConnectionManager();
			String sql = "select * from zm_zbsjk_info1 where year = ? and month = ? and departopcode = ?";
			Object[] params = {year,month,departid};
			ResultSet rs = cManager.WexecuteQuery(sql, params);
			while(rs.next()){
				JSONObject a = new JSONObject();
				String project = rs.getString("project") == null ? "--":rs.getString("project");
				String mbzb = rs.getString("mbzb")== null ? "--":rs.getString("mbzb");
				String ljwc = rs.getString("ljwc")== null ? "--":rs.getString("ljwc");
				String ljwcl = rs.getString("ljwcl")== null ? "--":rs.getString("ljwcl");
				String ljtqdb = rs.getString("ljtqdb")== null ? "--":rs.getString("ljtqdb");
				String symb = rs.getString("symb")== null ? "--":rs.getString("symb");
				String yj = rs.getString("yj")== null ? "--":rs.getString("yj");
				String dqmb = rs.getString("dqmb")== null ? "--":rs.getString("dqmb");
				String dqz = rs.getString("dqz")== null ? "--":rs.getString("dqz");
				String dqwcl = rs.getString("dqwcl")== null ? "--":rs.getString("dqwcl");
				String tqdb = rs.getString("tqdb")== null ? "--":rs.getString("tqdb");
				String hb = rs.getString("hb")== null ? "--":rs.getString("hb");
				a.put("project", project);
				a.put("mbzb", mbzb);
				a.put("ljwc", ljwc);
				if("--".equals(ljwcl)){
					a.put("ljwcl", ljwcl);
				}else{
					a.put("ljwcl", round(Double.parseDouble(ljwcl)*100,2)+"%");
				}
				if("--".equals(ljtqdb)){
					a.put("ljtqdb", ljtqdb);
				}else{
					a.put("ljtqdb", round(Double.parseDouble(ljtqdb)*100,2)+"%");
				}
				a.put("symb", symb);
				a.put("yj", yj);
				a.put("dqmb", dqmb);
				a.put("dqz", dqz);
				if("--".equals(dqwcl)){
					a.put("dqwcl", dqwcl);
				}else{
					a.put("dqwcl", round(Double.parseDouble(dqwcl)*100,2)+"%");
				}
				if("--".equals(tqdb)){
					a.put("tqdb", tqdb);
				}else{
					a.put("tqdb", round(Double.parseDouble(tqdb)*100,2)+"%");
				}
				if("--".equals(hb)){
					a.put("hb", hb);
				}else{
					a.put("hb", round(Double.parseDouble(hb)*100,2)+"%");
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
	public static JSONArray geneListGoal(String years ,String departid){
		ConnectionManager cManager = null;
		JSONArray list = new JSONArray();
		try {
			cManager = new ConnectionManager();
			StringBuffer condition = new StringBuffer();//条件
			List<String> condList = new ArrayList<String>();
			boolean b = false;
			if (!"".equals(years)) {
				if (b) {
					condition.append(" and");
				}else{
					condition.append(" where");
				}
				b = true;
				condition.append(" year = ?");
				condList.add(years);
			}
			if (!"".equals(departid)) {
				if (b) {
					condition.append(" and");
				}else{
					condition.append(" where");
				}
				b = true;
				condition.append(" departopcode = ?");
				condList.add(departid);
			}else{
				if (b) {
					condition.append(" and");
				}else{
					condition.append(" where");
				}
				b = true;
				condition.append(" departopcode is null");
			}
			String sql = "select * from zm_goal_info "+condition.toString();
			Object[] params = condList.toArray(new String[condList.size()]);
			ResultSet rs = cManager.WexecuteQuery(sql, params);
			while(rs.next()){
				JSONObject a = new JSONObject();
				String departopcode = rs.getString("departopcode") == null ? "--":rs.getString("departopcode");
				String year = rs.getString("year")== null ? "--":rs.getString("year");
				String department = rs.getString("department")== null ? "--":rs.getString("department");
				String goalname = rs.getString("goalname")== null ? "--":rs.getString("goalname");
				String yearvalue = rs.getString("yearvalue")== null ? "--":rs.getString("yearvalue");
				a.put("departopcode", departopcode);
				a.put("year", year);
				a.put("department", department);
				a.put("goalname", goalname);
				a.put("yearvalue", yearvalue);
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
	public static JSONArray geneListTarget(String years ,String month,String departid){
		ConnectionManager cManager = null;
		JSONArray list = new JSONArray();
		try {
			cManager = new ConnectionManager();
			StringBuffer condition = new StringBuffer();//条件
			List<String> condList = new ArrayList<String>();
			boolean b = false;
			if (!"".equals(years)) {
				if (b) {
					condition.append(" and");
				}else{
					condition.append(" where");
				}
				b = true;
				condition.append(" year = ?");
				condList.add(years);
			}
			if (!"".equals(month)) {
				if (b) {
					condition.append(" and");
				}else{
					condition.append(" where");
				}
				b = true;
				condition.append(" month = ?");
				condList.add(month);
			}
			if (!"".equals(departid)) {
				if (b) {
					condition.append(" and");
				}else{
					condition.append(" where");
				}
				b = true;
				condition.append(" departopcode = ?");
				condList.add(departid);
			}else{
				if (b) {
					condition.append(" and");
				}else{
					condition.append(" where");
				}
				b = true;
				condition.append(" departopcode is null");
			}
			String sql = "select * from zm_target_v "+condition.toString();
			Object[] params = condList.toArray(new String[condList.size()]);
			ResultSet rs = cManager.WexecuteQuery(sql, params);
			while(rs.next()){
				JSONObject a = new JSONObject();
				String year = rs.getString("year")== null ? "--":rs.getString("year");
				String month1 = rs.getString("month")== null ? "--":rs.getString("month");
				String departopcode = rs.getString("departopcode") == null ? "--":rs.getString("departopcode");
				String department = rs.getString("department")== null ? "--":rs.getString("department");
				String target = rs.getString("target")== null ? "--":rs.getString("target");
				String monthvalue = rs.getString("monthvalue")== null ? "--":rs.getString("monthvalue");
				a.put("year", year);
				a.put("month", month1);
				a.put("departopcode", departopcode);
				a.put("department", department);
				a.put("target", target);
				a.put("monthvalue", monthvalue);
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
	public static JSONArray geneListDept(){
		ConnectionManager cManager = null;
		JSONArray list = new JSONArray();
		try {
			cManager = new ConnectionManager();
			
			String sql = "select * from zm_deptart ";
			ResultSet rs = cManager.executeQuery(sql);
			while(rs.next()){
				JSONObject a = new JSONObject();
				String departid = rs.getString("departid")== null ? "--":rs.getString("departid");
				String departopcode = rs.getString("departopcode")== null ? "--":rs.getString("departopcode");
				String department = rs.getString("department") == null ? "--":rs.getString("department");
				a.put("departid", departid);
				a.put("departopcode", departopcode);
				a.put("department", department);
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
	
	public static double round(double yuan,int length){
		if(length<0){
			length = 0;
		}
		String my = String.format("%."+length+"f", yuan);
		return Double.parseDouble(my);
	}
}
