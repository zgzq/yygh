package com.wx.ad.controller.rptcancer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wx.ad.util.ConnectionManager;

import my.dao.pool.DBManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class RptCancerUtil {

	public static int geneList(String month){
		JSONArray list = new JSONArray();
		Connection con = null;
		ConnectionManager cManager = null;
		int recode = 0;
		try {
			con = DBManager.getConnection();
			cManager = new ConnectionManager();
			CallableStatement callstatement = con
					.prepareCall("call pro_cancer_t(?,?)");//
			callstatement.setString(1, month);
			callstatement.registerOutParameter(2, java.sql.Types.VARCHAR);
			callstatement.execute();// 执行
			String str = callstatement.getString(2);
			String[] strs = split(str,"*");
			String[] names = {"科室总收入","门诊收入","医疗收入","药品收入","西药收入","中成药收入",
					"中草药收入","住院收入","医疗收入 ","药品收入 ","西药收入 ",
					"中成药收入 ","中草药收入 ","出院结算收入","门急量","市场活动体验量",
					"入院量","出院量","实际占床日数"};
			for(int i = 0 ; i < strs.length ; i ++){
				String sql = "update zm_cancer_info set value1 = ? where month = ? and project_name = ?";
				Object[] params = {strs[i],month,names[i]};
				int re = cManager.WexecuteUpdate(sql, params);
				if(re < 0){
					recode = 1;
				}
			}
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
	public static JSONArray geneList1(String month){
		ConnectionManager cManager = null;
		JSONArray list = new JSONArray();
		try {
			cManager = new ConnectionManager();
			String sql = "select * from zm_cancer_info where month = ?";
			Object[] params = {month};
			ResultSet rs = cManager.WexecuteQuery(sql,params);
			while(rs.next()){
				JSONObject a = new JSONObject();
				String project_name = rs.getString("project_name") == null ? "--":rs.getString("project_name");
				String value1 = rs.getString("value1")== null ? "--":rs.getString("value1");
				String value2 = rs.getString("value2")== null ? "--":rs.getString("value2");
				a.put("project_name", project_name);
				a.put("value1", value1);
				a.put("value2", value2);
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
	public static String[] split(String source, String div) {
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
