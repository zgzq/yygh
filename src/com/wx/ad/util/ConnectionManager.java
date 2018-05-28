package com.wx.ad.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionManager {


	Connection conn = null;
	ResultSet rs = null;
	public int resultNum = 0;
	Statement stmt = null;
	PreparedStatement ps = null;
	String driver="oracle.jdbc.driver.OracleDriver";
    String url="jdbc:oracle:thin:@10.0.42.205:1521:orcl";
	public ConnectionManager() {
		try {
			Class.forName(driver);
            conn = DriverManager.getConnection(url, "zmsjzx", "zmsjzx");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return conn;
	}

	public String callProcedure(String sql, Object[] params) {
		String jobId = "";
		try {
			CallableStatement cs = conn.prepareCall(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					Object uuu = params[i];
					String uu = uuu.toString();
					uu.replace("'", "''");
					cs.setObject(i + 1, uu);
				}
			}
			cs.registerOutParameter(params.length + 1, java.sql.Types.VARCHAR);
			cs.executeUpdate();
			jobId = cs.getString(params.length + 1);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return jobId;

	}

	public ResultSet executeQuery(String sql) {
		rs = null;
		resultNum = 0;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			/*
			 * rs.last(); resultNum=rs.getRow(); rs.beforeFirst();
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	public int executeUpdate(String sql) {
		resultNum = 0;
		try {
			stmt = conn.createStatement();
			resultNum = stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) {
			try {
				stmt.close();
			} catch (Exception e2) {
			}
			e.printStackTrace();
		}
		return resultNum;
	}

	public int WexecuteUpdate(String sql, Object[] params) {
		resultNum = 0;
		try {
			ps = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					Object uuu = params[i];
					String uu = uuu.toString();
					uu.replace("'", "''");
					ps.setObject(i + 1, uu);
				}
			}
			resultNum = ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			try {
				ps.close();
			} catch (Exception e2) {
			}
			e.printStackTrace();
			return -1;
		}
		return resultNum;
	}

	public ResultSet WexecuteQuery(String sql, Object[] params) {
		ps = null;
		rs = null;
		try {
			ps = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
			}
			rs = ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	public void closeRs() {
		if (ps != null) {
			try {
				ps.close();
			} catch (Exception e) {
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
			}
		}
		ps = null;
		rs = null;
		stmt = null;
	}

	public void closeConn() {
		if (ps != null) {
			try {
				ps.close();
			} catch (Exception e) {
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
			}
		}
		ps = null;
		rs = null;
		stmt = null;
		conn = null;

	}


}
