package com.wx.ad.controller.kddata;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wx.ad.util.ConnectionManager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class KdDataUtil {
	 public static int callProcOutHos(String orgid,String year1,String month1){
		 	Connection conn=null;
		 	CallableStatement cs=null;
	    	ConnectionManager cManager = null;
	    	int recode = 0;
	    	try {
	    		cManager = new ConnectionManager();
	    		conn = cManager.getConnection();
	    		String sqlDel = "";
	    		int re = 0;
	    		
    			sqlDel = "delete from kd_outhosdetail where orgid = ? and year = ? and month = ?";
    			Object[] params1 = {orgid,year1,month1};
    			re = cManager.WexecuteUpdate(sqlDel,params1);
	    		
	    		if(re >= 0){
	    			
	    		}
	    		cs = conn.prepareCall("{call pro_kdouthos(?,?,?)}");
	    		cs.setString(1, orgid);
	    		cs.setString(2, year1);
	    		cs.setString(3, month1);
	    		cs.execute();
//	            cs1 = conn.prepareCall("{call pro_surgery_col()}");
//	            cs.registerOutParameter(1, OracleTypes.CURSOR);
//	            cs1.execute();
	    		
	    		
	    		
	    		
	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    		recode = -1;
	    	}finally{
	    		cManager.closeConn();
	    	}
	    	return recode;
	    }
	 public static int callProcOutPat(String orgid,String year1,String month1){
		 Connection conn=null;
		 CallableStatement cs=null;
		 ConnectionManager cManager = null;
		 int recode = 0;
		 try {
			 cManager = new ConnectionManager();
			 conn = cManager.getConnection();
			 String sqlDel = "";
			 int re = 0;
			 
			 sqlDel = "delete from kd_outpatdetail where orgid = ? and year = ? and month = ?";
			 Object[] params1 = {orgid,year1,month1};
			 re = cManager.WexecuteUpdate(sqlDel,params1);
			 
			 if(re >= 0){
				 
			 }
			 cs = conn.prepareCall("{call pro_kdoutpat(?,?,?)}");
			 cs.setString(1, orgid);
			 cs.setString(2, year1);
			 cs.setString(3, month1);
			 cs.execute();
//	            cs1 = conn.prepareCall("{call pro_surgery_col()}");
//	            cs.registerOutParameter(1, OracleTypes.CURSOR);
//	            cs1.execute();
			 
			 
			 
			 
		 } catch (SQLException e) {
			 e.printStackTrace();
			 recode = -1;
		 }finally{
			 cManager.closeConn();
		 }
		 return recode;
	 }
	 public static int callProcProvInfo1(String year1,String orgid){
		 Connection conn=null;
		 CallableStatement cs=null;
		 ConnectionManager cManager = null;
		 int recode = 0;
		 try {
			 cManager = new ConnectionManager();
			 conn = cManager.getConnection();
			 String sqlDel = "";
			 int re = 0;
			 
			 sqlDel = "delete from kd_prov_value where orgid = ? and v_year = ?";
			 Object[] params1 = {orgid,year1};
			 re = cManager.WexecuteUpdate(sqlDel,params1);
			 
			 if(re >= 0){
				 
			 }
			 cs = conn.prepareCall("{call pro_kd_prov(?,?)}");
			 cs.setString(1, year1);
			 cs.setString(2, orgid);
			 cs.execute();
//	            cs1 = conn.prepareCall("{call pro_surgery_col()}");
//	            cs.registerOutParameter(1, OracleTypes.CURSOR);
//	            cs1.execute();
		 } catch (SQLException e) {
			 e.printStackTrace();
			 recode = -1;
		 }finally{
			 cManager.closeConn();
		 }
		 return recode;
	 }
	 public static int callProcTypeInfo1(String year1,String orgid){
		 Connection conn=null;
		 CallableStatement cs=null;
		 ConnectionManager cManager = null;
		 int recode = 0;
		 try {
			 cManager = new ConnectionManager();
			 conn = cManager.getConnection();
			 String sqlDel = "";
			 int re = 0;
			 
			 sqlDel = "delete from kd_type_value where orgid = ? and v_year = ?";
			 Object[] params1 = {orgid,year1};
			 re = cManager.WexecuteUpdate(sqlDel,params1);
			 
			 if(re >= 0){
				 
			 }
			 cs = conn.prepareCall("{call pro_kd_type(?,?)}");
			 cs.setString(1, year1);
			 cs.setString(2, orgid);
			 cs.execute();
//	            cs1 = conn.prepareCall("{call pro_surgery_col()}");
//	            cs.registerOutParameter(1, OracleTypes.CURSOR);
//	            cs1.execute();
		 } catch (SQLException e) {
			 e.printStackTrace();
			 recode = -1;
		 }finally{
			 cManager.closeConn();
		 }
		 return recode;
	 }
	 public static int callProcTypeInfo2(String year1,String orgid){
		 Connection conn=null;
		 CallableStatement cs=null;
		 ConnectionManager cManager = null;
		 int recode = 0;
		 try {
			 cManager = new ConnectionManager();
			 conn = cManager.getConnection();
			 String sqlDel = "";
			 int re = 0;
			 
			 sqlDel = "delete from kd_type_value2 where orgid = ? and v_year = ?";
			 Object[] params1 = {orgid,year1};
			 re = cManager.WexecuteUpdate(sqlDel,params1);
			 
			 if(re >= 0){
				 
			 }
			 cs = conn.prepareCall("{call pro_kd_type2(?,?)}");
			 cs.setString(1, year1);
			 cs.setString(2, orgid);
			 cs.execute();
//	            cs1 = conn.prepareCall("{call pro_surgery_col()}");
//	            cs.registerOutParameter(1, OracleTypes.CURSOR);
//	            cs1.execute();
		 } catch (SQLException e) {
			 e.printStackTrace();
			 recode = -1;
		 }finally{
			 cManager.closeConn();
		 }
		 return recode;
	 }
	 public static int callProcProvInfo2(String year1,String orgid){
		 Connection conn=null;
		 CallableStatement cs=null;
		 ConnectionManager cManager = null;
		 int recode = 0;
		 try {
			 cManager = new ConnectionManager();
			 conn = cManager.getConnection();
			 String sqlDel = "";
			 int re = 0;
			 
			 sqlDel = "delete from kd_prov_value2 where orgid = ? and v_year = ?";
			 Object[] params1 = {orgid,year1};
			 re = cManager.WexecuteUpdate(sqlDel,params1);
			 
			 if(re >= 0){
				 
			 }
			 cs = conn.prepareCall("{call pro_kd_prov2(?,?)}");
			 cs.setString(1, year1);
			 cs.setString(2, orgid);
			 cs.execute();
//	            cs1 = conn.prepareCall("{call pro_surgery_col()}");
//	            cs.registerOutParameter(1, OracleTypes.CURSOR);
//	            cs1.execute();
			 
			 
			 
			 
		 } catch (SQLException e) {
			 e.printStackTrace();
			 recode = -1;
		 }finally{
			 cManager.closeConn();
		 }
		 return recode;
	 }
	 public static int callProcOutHosPay(String orgid,String year1,String month1){
		 Connection conn=null;
		 CallableStatement cs=null;
		 ConnectionManager cManager = null;
		 int recode = 0;
		 try {
			 cManager = new ConnectionManager();
			 conn = cManager.getConnection();
			 String sqlDel = "";
			 int re = 0;
			 
			 sqlDel = "delete from kd_outhospaydetail where orgid = ? and v_year = ? and v_month = ?";
			 Object[] params1 = {orgid,year1,month1};
			 re = cManager.WexecuteUpdate(sqlDel,params1);
			 
			 if(re >= 0){
				 
			 }
			 cs = conn.prepareCall("{call pro_kd_outhospay(?,?,?)}");
			 cs.setString(1, year1);
			 cs.setString(2, month1);
			 cs.setString(3, orgid);
			 cs.execute();
//	            cs1 = conn.prepareCall("{call pro_surgery_col()}");
//	            cs.registerOutParameter(1, OracleTypes.CURSOR);
//	            cs1.execute();
			 
			 
			 
			 
		 } catch (SQLException e) {
			 e.printStackTrace();
			 recode = -1;
		 }finally{
			 cManager.closeConn();
		 }
		 return recode;
	 }
	 public static JSONArray geneListOutHosDetail(String orgid,String year,String month){
			ConnectionManager cManager = null;
			JSONArray list = new JSONArray();
			try {
				cManager = new ConnectionManager();
				String sql = "select * from kd_outhosdetail where orgid = ? and year = ? and month = ?";
				Object[] params = {orgid,year,month};
				ResultSet rs = cManager.WexecuteQuery(sql,params);
				while(rs.next()){
					JSONObject a = new JSONObject();
					String inp_no = rs.getString("inp_no") == null ? "--":rs.getString("inp_no");
					String name = rs.getString("name")== null ? "--":rs.getString("name");
					String sex = rs.getString("sex")== null ? "--":rs.getString("sex");
					String age = rs.getString("age")== null ? "--":rs.getString("age");
					String mail_address = rs.getString("mail_address")== null ? "--":rs.getString("mail_address");
					String diagnosis = rs.getString("diagnosis")== null ? "--":rs.getString("diagnosis");
					String identity = rs.getString("identity")== null ? "--":rs.getString("identity");
					String dept_admission_to = rs.getString("dept_admission_to")== null ? "--":rs.getString("dept_admission_to");
					String attending_doctor = rs.getString("attending_doctor")== null ? "--":rs.getString("attending_doctor");
					String admission_date_time = rs.getString("admission_date_time")== null ? "--":rs.getString("admission_date_time");
					String discharge_date_time = rs.getString("discharge_date_time")== null ? "--":rs.getString("discharge_date_time");
					String inhos_days = rs.getString("inhos_days")== null ? "--":rs.getString("inhos_days");
					String total_payments = rs.getString("total_payments")== null ? "--":rs.getString("total_payments");
					a.put("inp_no", inp_no);
					a.put("name", name);
					a.put("sex", sex);
					a.put("age", age);
					a.put("mail_address", mail_address);
					a.put("diagnosis", diagnosis);
					a.put("identity", identity);
					a.put("dept_admission_to", dept_admission_to);
					a.put("attending_doctor", attending_doctor);
					a.put("admission_date_time", admission_date_time);
					a.put("discharge_date_time", discharge_date_time);
					a.put("inhos_days", inhos_days);
					a.put("total_payments", total_payments);
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
	 public static JSONArray geneListOutHosPayDetail(String orgid,String year,String month){
		 ConnectionManager cManager = null;
		 JSONArray list = new JSONArray();
		 try {
			 cManager = new ConnectionManager();
			 String sql = "select * from kd_outhospay_v where orgid = ? and v_year = ? and v_month = ?";
			 Object[] params = {orgid,year,month};
			 ResultSet rs = cManager.WexecuteQuery(sql,params);
			 if("2".equals(orgid)){
				 while(rs.next()){
					 JSONObject a = new JSONObject();
					 String ct = rs.getString("ct") == null ? "--":rs.getString("ct");
					 String rpom = rs.getString("rpom")== null ? "--":rs.getString("rpom");
					 String blsr = rs.getString("blsr")== null ? "--":rs.getString("blsr");
					 String clsr = rs.getString("clsr")== null ? "--":rs.getString("clsr");
					 String cc = rs.getString("cc")== null ? "--":rs.getString("cc");
					 String fs = rs.getString("fs")== null ? "--":rs.getString("fs");
					 String fskyh = rs.getString("fskyh")== null ? "--":rs.getString("fskyh");
					 String gh = rs.getString("gh")== null ? "--":rs.getString("gh");
					 String hc = rs.getString("hc")== null ? "--":rs.getString("hc");
					 String hy = rs.getString("hy")== null ? "--":rs.getString("hy");
					 String hykyh = rs.getString("hykyh")== null ? "--":rs.getString("hykyh");
					 String jh = rs.getString("jh")== null ? "--":rs.getString("jh");
					 String jc = rs.getString("jc")== null ? "--":rs.getString("jc");
					 String jctx = rs.getString("jctx")== null ? "--":rs.getString("jctx");
					 String ll = rs.getString("ll")== null ? "--":rs.getString("ll");
					 String mz = rs.getString("mz")== null ? "--":rs.getString("mz");
					 String mr = rs.getString("mr")== null ? "--":rs.getString("mr");
					 String qt = rs.getString("qt")== null ? "--":rs.getString("qt");
					 String st = rs.getString("st")== null ? "--":rs.getString("st");
					 String ssf = rs.getString("ssf")== null ? "--":rs.getString("ssf");
					 String tj = rs.getString("tj")== null ? "--":rs.getString("tj");
					 String wcj = rs.getString("wcj")== null ? "--":rs.getString("wcj");
					 String xy = rs.getString("xy")== null ? "--":rs.getString("xy");
					 String xd = rs.getString("xd")== null ? "--":rs.getString("xd");
					 String xt = rs.getString("xt")== null ? "--":rs.getString("xt");
					 String yq = rs.getString("yq")== null ? "--":rs.getString("yq");
					 String yl = rs.getString("yl")== null ? "--":rs.getString("yl");
					 String zl = rs.getString("zl")== null ? "--":rs.getString("zl");
					 String zcy = rs.getString("zcy")== null ? "--":rs.getString("zcy");
					 String zchy = rs.getString("zchy")== null ? "--":rs.getString("zchy");
					 String zy = rs.getString("zy")== null ? "--":rs.getString("zzcy");
					 String zzcy = rs.getString("zzcy")== null ? "--":rs.getString("xy");
					 a.put("ct", ct);
					 a.put("rpom", rpom);
					 a.put("blsr", blsr);
					 a.put("clsr", clsr);
					 a.put("cc", cc);
					 a.put("fs", fs);
					 a.put("fskyh", fskyh);
					 a.put("gh", gh);
					 a.put("hc", hc);
					 a.put("hy", hy);
					 a.put("hykyh", hykyh);
					 a.put("jh", jh);
					 a.put("jc", jc);
					 a.put("jctx", jctx);
					 a.put("ll", ll);
					 a.put("mz", mz);
					 a.put("mr", mr);
					 a.put("qt", qt);
					 a.put("st", st);
					 a.put("ssf", ssf);
					 a.put("tj", tj);
					 a.put("wcj", wcj);
					 a.put("xy", xy);
					 a.put("xd", xd);
					 a.put("xt", xt);
					 a.put("tq", yq);
					 a.put("yl", yl);
					 a.put("zl", zl);
					 a.put("zcy", zcy);
					 a.put("zchy", zchy);
					 a.put("zy", zy);
					 a.put("zzcy", zzcy);
					 list.add(a);
				 }
			 }else if("5".equals(orgid)){
				 while(rs.next()){
					 JSONObject a = new JSONObject();
					 String cwf = rs.getString("cwf") == null ? "--":rs.getString("cwf");
					 String fsf = rs.getString("fsf")== null ? "--":rs.getString("fsf");
					 String hyf = rs.getString("hyf")== null ? "--":rs.getString("hyf");
					 String jcf = rs.getString("jcf")== null ? "--":rs.getString("jcf");
					 String zlf = rs.getString("zlf")== null ? "--":rs.getString("zlf");
					 String xyf = rs.getString("xyf")== null ? "--":rs.getString("xyf");
					 String zchyf = rs.getString("zchyf")== null ? "--":rs.getString("zchyf");
					 String zcyf = rs.getString("zcy")== null ? "--":rs.getString("zcfyf");
					 String yqk = rs.getString("yqk")== null ? "--":rs.getString("yqk");
					 String qtf = rs.getString("qtf")== null ? "--":rs.getString("qtf");
					 a.put("cwf", cwf);
					 a.put("fsf", fsf);
					 a.put("hyf", hyf);
					 a.put("jcf", jcf);
					 a.put("zlf", zlf);
					 a.put("xyf", xyf);
					 a.put("zchyf", zchyf);
					 a.put("zcyf", zcyf);
					 a.put("yqk", yqk);
					 a.put("qtf", qtf);
					 list.add(a);
				 }
			 }else{}
			 
			 rs.close();
		 } catch (SQLException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 } finally {
			 cManager.closeConn();
		 }
		 return list;
	 }
	 public static JSONArray geneListProvInfo1(String orgid,String year){
		 ConnectionManager cManager = null;
		 JSONArray list = new JSONArray();
		 try {
			 cManager = new ConnectionManager();
			 String sql = "select * from kd_provinfo1_v where orgid = ? and year = ?";
			 Object[] params = {orgid,year};
			 ResultSet rs = cManager.WexecuteQuery(sql,params);
			 while(rs.next()){
				 JSONObject a = new JSONObject();
				 String province_name = rs.getString("province_name") == null ? "--":rs.getString("province_name");
				 String m01 = rs.getString("m01")== null ? "--":rs.getString("m01");
				 String m02 = rs.getString("m02")== null ? "--":rs.getString("m02");
				 String m03 = rs.getString("m03")== null ? "--":rs.getString("m03");
				 String m04 = rs.getString("m04")== null ? "--":rs.getString("m04");
				 String m05 = rs.getString("m05")== null ? "--":rs.getString("m05");
				 String m06 = rs.getString("m06")== null ? "--":rs.getString("m06");
				 String m07 = rs.getString("m07")== null ? "--":rs.getString("m07");
				 String m08 = rs.getString("m08")== null ? "--":rs.getString("m08");
				 String m09 = rs.getString("m09")== null ? "--":rs.getString("m09");
				 String m10 = rs.getString("m10")== null ? "--":rs.getString("m10");
				 String m11 = rs.getString("m11")== null ? "--":rs.getString("m11");
				 String m12 = rs.getString("m12")== null ? "--":rs.getString("m12");
				 String total = rs.getString("total")== null ? "--":rs.getString("total");
				 a.put("province_name", province_name);
				 a.put("m01", m01);
				 a.put("m02", m02);
				 a.put("m03", m03);
				 a.put("m04", m04);
				 a.put("m05", m05);
				 a.put("m06", m06);
				 a.put("m07", m07);
				 a.put("m08", m08);
				 a.put("m09", m09);
				 a.put("m10", m10);
				 a.put("m11", m11);
				 a.put("m12", m12);
				 a.put("total", total);
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
	 public static JSONArray geneListTypeInfo1(String orgid,String year){
		 ConnectionManager cManager = null;
		 JSONArray list = new JSONArray();
		 try {
			 cManager = new ConnectionManager();
			 String sql = "select * from kd_typeinfo1_v where orgid = ? and year = ?";
			 Object[] params = {orgid,year};
			 ResultSet rs = cManager.WexecuteQuery(sql,params);
			 while(rs.next()){
				 JSONObject a = new JSONObject();
				 String month = rs.getString("month") == null ? "--":rs.getString("month");
				 String iga = rs.getString("iga")== null ? "--":rs.getString("iga");
				 String hspn = rs.getString("hspn")== null ? "--":rs.getString("hspn");
				 String ln = rs.getString("ln")== null ? "--":rs.getString("ln");
				 String crf = rs.getString("crf")== null ? "--":rs.getString("crf");
				 String cgn = rs.getString("cgn")== null ? "--":rs.getString("cgn");
				 String ns = rs.getString("ns")== null ? "--":rs.getString("ns");
				 String dm = rs.getString("dm")== null ? "--":rs.getString("dm");
				 String dn = rs.getString("dn")== null ? "--":rs.getString("dn");
				 String hp = rs.getString("hp")== null ? "--":rs.getString("hp");
				 String hpk = rs.getString("hpk")== null ? "--":rs.getString("hpk");
				 String gk = rs.getString("gk")== null ? "--":rs.getString("gk");
				 String md = rs.getString("md")== null ? "--":rs.getString("md");
				 String pk = rs.getString("pk")== null ? "--":rs.getString("pk");
				 String qt = rs.getString("qt")== null ? "--":rs.getString("qt");
				 String total = rs.getString("total")== null ? "--":rs.getString("total");
				 a.put("month", month);
				 a.put("iga", iga);
				 a.put("hspn", hspn);
				 a.put("ln", ln);
				 a.put("crf", crf);
				 a.put("cgn", cgn);
				 a.put("ns", ns);
				 a.put("dm", dm);
				 a.put("dn", dn);
				 a.put("hp", hp);
				 a.put("hpk", hpk);
				 a.put("gk", gk);
				 a.put("md", md);
				 a.put("pk", pk);
				 a.put("qt", qt);
				 a.put("total", total);
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
	 public static JSONArray geneListTypeInfo2(String orgid,String year){
		 ConnectionManager cManager = null;
		 JSONArray list = new JSONArray();
		 try {
			 cManager = new ConnectionManager();
			 String sql = "select * from kd_typeinfo2_v where orgid = ? and year = ?";
			 Object[] params = {orgid,year};
			 ResultSet rs = cManager.WexecuteQuery(sql,params);
			 while(rs.next()){
				 JSONObject a = new JSONObject();
				 String month = rs.getString("month") == null ? "--":rs.getString("month");
				 String iga = rs.getString("iga")== null ? "--":rs.getString("iga");
				 String hspn = rs.getString("hspn")== null ? "--":rs.getString("hspn");
				 String ln = rs.getString("ln")== null ? "--":rs.getString("ln");
				 String crf = rs.getString("crf")== null ? "--":rs.getString("crf");
				 String cgn = rs.getString("cgn")== null ? "--":rs.getString("cgn");
				 String ns = rs.getString("ns")== null ? "--":rs.getString("ns");
				 String dm = rs.getString("dm")== null ? "--":rs.getString("dm");
				 String dn = rs.getString("dn")== null ? "--":rs.getString("dn");
				 String hp = rs.getString("hp")== null ? "--":rs.getString("hp");
				 String hpk = rs.getString("hpk")== null ? "--":rs.getString("hpk");
				 String gk = rs.getString("gk")== null ? "--":rs.getString("gk");
				 String md = rs.getString("md")== null ? "--":rs.getString("md");
				 String pk = rs.getString("pk")== null ? "--":rs.getString("pk");
				 String qt = rs.getString("qt")== null ? "--":rs.getString("qt");
				 String total = rs.getString("total")== null ? "--":rs.getString("total");
				 a.put("month", month);
				 a.put("iga", iga);
				 a.put("hspn", hspn);
				 a.put("ln", ln);
				 a.put("crf", crf);
				 a.put("cgn", cgn);
				 a.put("ns", ns);
				 a.put("dm", dm);
				 a.put("dn", dn);
				 a.put("hp", hp);
				 a.put("hpk", hpk);
				 a.put("gk", gk);
				 a.put("md", md);
				 a.put("pk", pk);
				 a.put("qt", qt);
				 a.put("total", total);
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
	 public static JSONArray geneListProvInfo2(String orgid,String year){
		 ConnectionManager cManager = null;
		 JSONArray list = new JSONArray();
		 try {
			 cManager = new ConnectionManager();
			 String sql = "select * from kd_provinfo2_v where orgid = ? and year = ?";
			 Object[] params = {orgid,year};
			 ResultSet rs = cManager.WexecuteQuery(sql,params);
			 while(rs.next()){
				 JSONObject a = new JSONObject();
				 String province_name = rs.getString("province_name") == null ? "--":rs.getString("province_name");
				 String m01 = rs.getString("m01")== null ? "--":rs.getString("m01");
				 String m02 = rs.getString("m02")== null ? "--":rs.getString("m02");
				 String m03 = rs.getString("m03")== null ? "--":rs.getString("m03");
				 String m04 = rs.getString("m04")== null ? "--":rs.getString("m04");
				 String m05 = rs.getString("m05")== null ? "--":rs.getString("m05");
				 String m06 = rs.getString("m06")== null ? "--":rs.getString("m06");
				 String m07 = rs.getString("m07")== null ? "--":rs.getString("m07");
				 String m08 = rs.getString("m08")== null ? "--":rs.getString("m08");
				 String m09 = rs.getString("m09")== null ? "--":rs.getString("m09");
				 String m10 = rs.getString("m10")== null ? "--":rs.getString("m10");
				 String m11 = rs.getString("m11")== null ? "--":rs.getString("m11");
				 String m12 = rs.getString("m12")== null ? "--":rs.getString("m12");
				 String total = rs.getString("total")== null ? "--":rs.getString("total");
				 a.put("province_name", province_name);
				 a.put("m01", m01);
				 a.put("m02", m02);
				 a.put("m03", m03);
				 a.put("m04", m04);
				 a.put("m05", m05);
				 a.put("m06", m06);
				 a.put("m07", m07);
				 a.put("m08", m08);
				 a.put("m09", m09);
				 a.put("m10", m10);
				 a.put("m11", m11);
				 a.put("m12", m12);
				 a.put("total", total);
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
	 public static JSONArray geneListOutPatDetail(String orgid,String year,String month){
		 ConnectionManager cManager = null;
		 JSONArray list = new JSONArray();
		 try {
			 cManager = new ConnectionManager();
			 String sql = "select * from kd_outpatdetail where orgid = ? and year = ? and month = ?";
			 Object[] params = {orgid,year,month};
			 ResultSet rs = cManager.WexecuteQuery(sql,params);
			 while(rs.next()){
				 JSONObject a = new JSONObject();
				 String name = rs.getString("name")== null ? "--":rs.getString("name");
				 String sex = rs.getString("sex")== null ? "--":rs.getString("sex");
				 String age = rs.getString("age")== null ? "--":rs.getString("age");
				 String unit_in_contract = rs.getString("unit_in_contract")== null ? "--":rs.getString("unit_in_contract");
				 String identity = rs.getString("identity")== null ? "--":rs.getString("identity");
				 a.put("name", name);
				 a.put("sex", sex);
				 a.put("age", age);
				 a.put("unit_in_contract", unit_in_contract);
				 a.put("identity", identity);
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
}
