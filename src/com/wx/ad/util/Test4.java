package com.wx.ad.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.wx.ad.util.ConnectionManager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import oracle.jdbc.OracleTypes;

public class Test4 {

    Connection conn=null;
    CallableStatement cs=null;//PreparedStatement,Statement
    CallableStatement cs1=null;//PreparedStatement,Statement
    ResultSet rs;
    private String sepa1 = "[*]";
    public int callProc(String dept_code1,String year1,String month1){
    	ConnectionManager cManager = null;
    	int recode = 0;
        try {
        	cManager = new ConnectionManager();
    		conn = cManager.getConnection();
    		String sqlDel = "delete from zm_surgery where dept_code = ? and year = ? and month = ?";
	        Object[] params1 = {dept_code1,year1,month1};
	        int re = cManager.WexecuteUpdate(sqlDel,params1);
	        if(re >= 0){
	        	
	        }else{
	          	recode = 1;
	        }
            cs = conn.prepareCall("{call pro_surgery(?,?,?)}");
            cs.setString(1, dept_code1);
            cs.setString(2, year1);
            cs.setString(3, month1);
            cs.execute();
//            cs1 = conn.prepareCall("{call pro_surgery_col()}");
//            cs.registerOutParameter(1, OracleTypes.CURSOR);
//            cs1.execute();
            
            
            
            
        } catch (SQLException e) {
            e.printStackTrace();
            recode = 1;
        }finally{
        	cManager.closeConn();
        }
        return recode;
    }
    public int callProcDate(String dept_code1,String year1,String month1,String month2){
    	ConnectionManager cManager = null;
    	int recode = 0;
    	try {
    		cManager = new ConnectionManager();
    		conn = cManager.getConnection();
    		String sqlDel = "";
    		int re = 0;
    		if(!"".equals(month2)){
    			sqlDel = "delete from zm_surgerydate where dept_code = ? and year = ? and month >= ? and month <= ?";
    			Object[] params1 = {dept_code1,year1,month1,month2};
    			re = cManager.WexecuteUpdate(sqlDel,params1);
    		}else{
    			sqlDel = "delete from zm_surgerydate where dept_code = ? and year = ? and month = ?";
    			Object[] params1 = {dept_code1,year1,month1};
    			re = cManager.WexecuteUpdate(sqlDel,params1);
    		}
    		if(re >= 0){
    			
    		}else{
    			recode = 1;
    		}
    		cs = conn.prepareCall("{call pro_surgerydate(?,?,?,?)}");
    		cs.setString(1, dept_code1);
    		cs.setString(2, year1);
    		cs.setString(3, month1);
    		cs.setString(4, month2);
    		cs.execute();
//            cs1 = conn.prepareCall("{call pro_surgery_col()}");
//            cs.registerOutParameter(1, OracleTypes.CURSOR);
//            cs1.execute();
    		
    		
    		
    		
    	} catch (SQLException e) {
    		e.printStackTrace();
    		recode = 1;
    	}finally{
    		cManager.closeConn();
    	}
    	return recode;
    }
    public int callProcCol(String year1,String month1){
    	ConnectionManager cManager = null;
    	int recode = 0;
    	try {
    		cManager = new ConnectionManager();
    		conn = cManager.getConnection();
    		String sqlDel = "delete from zm_surgery_collect where year = ? and month = ?";
    		Object[] params1 = {year1,month1};
    		int re = cManager.WexecuteUpdate(sqlDel,params1);
    		if(re >= 0){
    			
    		}else{
    			recode = 1;
    		}
    		cs = conn.prepareCall("{call pro_surgery_col(?,?)}");
    		cs.setString(1, year1);
    		cs.setString(2, month1);
    		cs.execute();
//            cs1 = conn.prepareCall("{call pro_surgery_col()}");
//            cs.registerOutParameter(1, OracleTypes.CURSOR);
//            cs1.execute();
    		String sql = "select dept_code,operat_level,count(*) num,sum(inhos_days) days,sum(total_payments) pays"
    				+ " from zm_surgery where year = ? and month = ? group by dept_code,operat_level ";
    		Object[] params = {year1,month1};
//    		System.out.println("2");
    		ResultSet rs = cManager.WexecuteQuery(sql, params);
    		Map<String,Map<String,String>> map = new HashMap<>();
//    		Map<String,String> map1 = new HashMap<>();
    		while(rs.next()){
    			String dept_code = rs.getString("dept_code");
    			String operat_level = rs.getString("operat_level");
    			String num = rs.getString("num");
    			String  days = rs.getString("days");
    			String pays = rs.getString("pays");
    			if(num == null){
    				num = "0";
    			}
    			if(days == null){
    				days = "0";
    			}
    			if(pays == null){
    				pays = "0";
    			}
    			if(map.get(dept_code) != null){
    				Map<String,String> map1 = map.get(dept_code);
    				String str1 = num + sepa1+ days + sepa1 + pays;
    				map1.put(operat_level, str1);
    				map.put(dept_code, map1);
    			}else{
    				Map<String,String> map1 = new HashMap<>();
    				String str1 = num + sepa1+ days + sepa1 + pays;
    				map1.put(operat_level, str1);
    				map.put(dept_code, map1);
    			}
    		}
    		rs.close();
    		//遍历
    		List<String> list = new ArrayList<>();
    		for (Map.Entry<String,Map<String,String>> entry : map.entrySet()) { 
    			String dept_code = entry.getKey();
    			Map<String,String> map1_ = entry.getValue();
    			int num2 = 0;
    			int num3 = 0;
    			int num4 = 0;
    			int num0 = 0;
    			int days = 0;
    			double pays = 0.0;
    			double avgs = 0.0;
    			for(Map.Entry<String, String> entry1 : map1_.entrySet()){
    				String level = entry1.getKey();
    				if("2".equals(level)){
    					String str1 = entry1.getValue();
    					String[] strs1 = split(str1,sepa1);
    					if(!"".equals(strs1[0])){
    						num2 = Integer.parseInt(strs1[0]);
    					}
    					if(!"".equals(strs1[1])){
    						days += Integer.parseInt(strs1[1]);
    					}
    					if(!"".equals(strs1[2])){
    						pays += Double.parseDouble(strs1[2]);
    					}
    					
    				}else if("3".equals(level)){
    					String str1 = entry1.getValue();
    					String[] strs1 = split(str1,sepa1);
    					if(!"".equals(strs1[0])){
    						num3 = Integer.parseInt(strs1[0]);
    					}
    					if(!"".equals(strs1[1])){
    						days += Integer.parseInt(strs1[1]);
    					}
    					if(!"".equals(strs1[2])){
    						pays += Double.parseDouble(strs1[2]);
    					}
    				}else if("4".equals(level)){
    					String str1 = entry1.getValue();
    					String[] strs1 = split(str1,sepa1);
    					if(!"".equals(strs1[0])){
    						num4 = Integer.parseInt(strs1[0]);
    					}
    					if(!"".equals(strs1[1])){
    						days += Integer.parseInt(strs1[1]);
    					}
    					if(!"".equals(strs1[2])){
    						pays += Double.parseDouble(strs1[2]);
    					}
    				}else{
    					String str1 = entry1.getValue();
    					String[] strs1 = split(str1,sepa1);
    					if(!"".equals(strs1[0])){
    						num0 += Integer.parseInt(strs1[0]);
    					}
    					if(!"".equals(strs1[1])){
    						days += Integer.parseInt(strs1[1]);
    					}
    					if(!"".equals(strs1[2])){
    						pays += Double.parseDouble(strs1[2]);
    					}
    				}
    				avgs = round(pays/(num2+num3+num4),2);
    				String str = dept_code+sepa1+"E0115"+sepa1+(num2+num3+num4+num0)+sepa1+days+sepa1+pays
    						+sepa1+avgs+sepa1+num2+sepa1+num3+sepa1+num4;
    				list.add(str);
    			}
    		}
    		
    		String sqlQ = "select * from zm_surgery_collect where year = ? and month = ?";
    		ResultSet rs1 = cManager.WexecuteQuery(sqlQ, params);
    		System.out.println("3");
    		while(rs1.next()){
    			String order_by = rs1.getString("ordered_by")==null?"--":rs1.getString("ordered_by");
    			String performed_by = rs1.getString("performed_by")==null?"--":rs1.getString("performed_by");
    			String peps = rs1.getString("num_per")==null?"0":rs1.getString("num_per");
    			String days = rs1.getString("bed_days")==null?"0":rs1.getString("bed_days");
    			String pays = rs1.getString("total_payments")==null?"0":rs1.getString("total_payments");
    			String avgs = rs1.getString("avg_payments")==null?"0":rs1.getString("avg_payments");
    			String str = order_by+sepa1+performed_by+sepa1+peps+sepa1+days+
    					sepa1+pays+sepa1+avgs+sepa1+""+sepa1+""+sepa1+"";
    			list.add(str);
    		}
    		rs1.close();
    		//排序
    		Map<Integer,String> mapE = new HashMap<>();
    		for(String s : list){
    			String[] strs = split(s,sepa1);
    			if("E0119".equals(strs[0])){
    				if("E0115".equals(strs[1])){
    					mapE.put(1, s);
    				}else{}
    			}else if("E0172".equals(strs[0]) || "E0183".equals(strs[0]) || "E01722".equals(strs[0])
    					|| "E01723".equals(strs[0])|| "E01132".equals(strs[0])){
    				if("E0115".equals(strs[1])){
    					mapE.put(2, s);
    				}else{}
    			}else if("E01191".equals(strs[0])){
    				if("E0115".equals(strs[1])){
    					mapE.put(3, s);
    				}else if("E0216".equals(strs[1])){
    					mapE.put(4, s);
    				}else{}
    			}else if("E0209".equals(strs[0])){
    				if("E0115".equals(strs[1])){
    					mapE.put(5, s);
    				}else if("E0216".equals(strs[1])){
    					mapE.put(6, s);
    				}else{}
    			}else if("E01811".equals(strs[0])){
    				if("E0115".equals(strs[1])){
    					mapE.put(7, s);
    				}else if("E0216".equals(strs[1])){
    					mapE.put(8, s);
    				}else{}
    			}else if("E01871".equals(strs[0])){
    				if("E0115".equals(strs[1])){
    					mapE.put(9, s);
    				}else{}
    			}else if("E0123".equals(strs[0])){
    				if("E0216".equals(strs[1])){
    					mapE.put(10, s);
    				}else{}
    			}else if("E01190".equals(strs[0])){
    				if("E0216".equals(strs[1])){
    					mapE.put(11, s);
    				}else if("E0152".equals(strs[1])){
    					mapE.put(12, s);
    				}else{}
    			}else{}
    		}
    		Map<Integer,String> mapE_ = sortMapByKey(mapE,0);
    		//删除
    		int re1 = cManager.WexecuteUpdate(sqlDel,params1);
    		if(re1 >= 0){
    			
    		}else{
    			recode = 1;
    		}
    		//重新放入
            for (Map.Entry<Integer,String> entry : mapE_.entrySet()) {
            	int m = entry.getKey();
            	String str = entry.getValue();
            	String[] strs = split(str,sepa1);
            	String order_by = strs[0];
            	String performed_by = strs[1];
            	String peps = strs[2];
            	String days = strs[3];
            	String pays = strs[4];
            	String avgs = strs[5];
            	String num2 = strs[6];
            	String num3 = strs[7];
            	String num4 = strs[8];
     
            	String sqlIns = "insert into zm_surgery_collect(YEAR,MONTH,ORDERED_BY,PERFORMED_BY,NUM_PER,BED_DAYS,TOTAL_PAYMENTS,AVG_PAYMENTS,SECOND_S,THIRD_S,FORTH_S) values(?,?,?,?,?,?,?,?,?,?,?)";
            	Object[] params2 = {year1,month1,order_by,performed_by,peps,
            			days,round(Double.parseDouble(pays),2),avgs,num2,num3,num4};
            	int rnum = cManager.WexecuteUpdate(sqlIns, params2);
            	if(rnum == 1){
            		
            	}else{
            		recode = 1;
            	}
            }
    		
    	} catch (SQLException e) {
    		e.printStackTrace();
    		recode = 1;
    	}finally{
    		cManager.closeConn();
    	}
    	return recode;
    }
    
    public static JSONArray geneList(String year ,String month,String month2,String departid){
		ConnectionManager cManager = null;
		JSONArray list = new JSONArray();
		try {
			cManager = new ConnectionManager();
			String sql = "";
			ResultSet rs = null;
			if(!"".equals(month2)){
				sql = "select * from zm_SURGERYDATE where year = ? and month >= ? and month <= ? and dept_code = ?";
				Object[] params = {year,month,month2,departid};
				rs = cManager.WexecuteQuery(sql, params);
			}else{
				
				sql = "select * from zm_SURGERYDATE where year = ? and month = ? and dept_code = ?";
				Object[] params = {year,month,departid};
				rs = cManager.WexecuteQuery(sql, params);
			}
			while(rs.next()){
				JSONObject a = new JSONObject();
				String dept = rs.getString("dept") == null ? "--":rs.getString("dept");
				String patient_id = rs.getString("patient_id") == null ? "--":rs.getString("patient_id");
				String name = rs.getString("name")== null ? "--":rs.getString("name");
				String sex = rs.getString("sex")== null ? "--":rs.getString("sex");
				String age = rs.getString("age")== null ? "--":rs.getString("age");
				String charge_type = rs.getString("charge_type")== null ? "--":rs.getString("charge_type");
				String mailing_address = rs.getString("mailing_address")== null ? "--":rs.getString("mailing_address");
				String phone = rs.getString("phone")== null ? "--":rs.getString("phone");
				String admission_date_time = rs.getString("admission_date_time")== null ? "--":rs.getString("admission_date_time");
				String operating_date = rs.getString("operating_date")== null ? "--":rs.getString("operating_date");
				String discharge_date_time = rs.getString("discharge_date_time")== null ? "--":rs.getString("discharge_date_time");
				String inhos_days = rs.getString("inhos_days")== null ? "--":rs.getString("inhos_days");
				String diagnosis_desc = rs.getString("diagnosis_desc")== null ? "--":rs.getString("diagnosis_desc");
				String operation_desc = rs.getString("operation_desc")== null ? "--":rs.getString("operation_desc");
				String operator = rs.getString("operator")== null ? "--":rs.getString("operator");
				String assistant = rs.getString("assistant")== null ? "--":rs.getString("assistant");
				String operat_level = rs.getString("operat_level")== null ? "--":rs.getString("operat_level");
				String total_payments = rs.getString("total_payments")== null ? "--":rs.getString("total_payments");
				a.put("dept", dept);
				a.put("patient_id", patient_id);
				a.put("name", name);
				a.put("sex", sex);
				a.put("age", age);
				a.put("charge_type", charge_type);
				a.put("mailing_address", mailing_address);
				a.put("phone", phone);
				a.put("admission_date_time", admission_date_time);
				a.put("operating_date", operating_date);
				a.put("discharge_date_time", discharge_date_time);
				a.put("inhos_days", inhos_days);
				a.put("diagnosis_desc", diagnosis_desc);
				a.put("operation_desc", operation_desc);
				a.put("operator", operator);
				a.put("assistant", assistant);
				a.put("operat_level", operat_level);
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
    public  Map sortMapByKey(Map map,int type) {
		if (map == null || map.isEmpty()) {
			return map;
		}
		Collection<String> keyset= map.keySet();  
        List<String> list = new ArrayList<String>(keyset);  
          
        Collections.sort(list);  
        
        Map newMap = new LinkedHashMap();  
	    if (type==0) {
		    for (int i = 0; i < list.size(); i++) {  
		        newMap.put(list.get(i), map.get(list.get(i)));  
		    }
		}else{
			for (int i = list.size()-1; i >=0; i--) {  
				newMap.put(list.get(i), map.get(list.get(i)));  
		    }
		}
	    return newMap;
	}
    public  double round(double yuan,int length){
		if(length<0){
			length = 0;
		}
		String my = String.format("%."+length+"f", yuan);
		return Double.parseDouble(my);
	}
	public  String[] split(String source, String div) {
		int arynum = 0;
		int intIdx = 0;
		int intIdex = 0;
		int div_length = div.length();
		if (source.compareTo("") != 0) {
			if (source.indexOf(div) != -1) {
				intIdx = source.indexOf(div);
				int intCount = 1;
				do {
					if (source.indexOf(div, intIdx + div_length) != -1) {
						intIdx = source.indexOf(div, intIdx + div_length);
						arynum = intCount;
					} else {
						arynum += 2;
						break;
					}
					intCount++;
				} while (true);
			} else {
				arynum = 1;
			}
		} else {
			arynum = 0;
		}
		intIdx = 0;
		intIdex = 0;
		String returnStr[] = new String[arynum];
		if (source.compareTo("") != 0) {
			if (source.indexOf(div) != -1) {
				intIdx = source.indexOf(div);
				returnStr[0] = source.substring(0, intIdx);
				int intCount = 1;
				do {
					if (source.indexOf(div, intIdx + div_length) != -1) {
						intIdex = source.indexOf(div, intIdx + div_length);
						returnStr[intCount] = source.substring(intIdx + div_length, intIdex);
						intIdx = source.indexOf(div, intIdx + div_length);
					} else {
						returnStr[intCount] = source.substring(intIdx + div_length, source.length());
						break;
					}
					intCount++;
				} while (true);
			} else {
				returnStr[0] = source.substring(0, source.length());
				return returnStr;
			}
		} else {
			return returnStr;
		}
		return returnStr;
	}
}
