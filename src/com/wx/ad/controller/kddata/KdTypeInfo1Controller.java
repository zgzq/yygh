package com.wx.ad.controller.kddata;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.wx.ad.bean.GridDataModel;
import com.wx.ad.bean.GridSaveModel;
import com.wx.ad.dbo.Deptart;
import com.wx.ad.dbo.GoalInfo;
import com.wx.ad.dbo.KdOutHosDetail;
import com.wx.ad.dbo.KdProvInfo1;
import com.wx.ad.dbo.KdTypeInfo1;
import com.wx.ad.dbo.Target;
import com.wx.ad.dbo.ZbsjkInfo1;
import com.wx.ad.dbo.ZmSurgeryCol;
import com.wx.ad.dbo.ZmSurgeryDateDetail;
import com.wx.ad.util.PageFactory;
import com.wx.ad.util.PageFactoryEasyUI;
import com.wx.ad.util.Test4;
import com.wx.ad.web.AuthController;

import jxl.Sheet;
import jxl.Workbook;
import my.ann.Action;
import my.ann.Menu;
import my.dao.pool.DBManager;
import my.web.AjaxMsg;
import my.web.BaseController;
import my.web.BaseController.CallBack;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Menu("肾病医院数据病种统计表(入院日期)")
@RequestMapping("/kd/typeInfo1/")
@Controller
public class KdTypeInfo1Controller extends BaseController {
	@RequestMapping("info")
	public String index() {
		return "admin/kddata/dtypeInfo1";
	}
	

	@Action("肾病医院数据病种统计表(入院日期)")
	@ResponseBody
	@RequestMapping("gridData")
	public GridDataModel<KdTypeInfo1> gridData1() {
		// String department = param("deptname", "");

		String orgid = param("orgid", "");
		String years = param("year", "");
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if (orgid.length() > 0) {
			filter.append(" and orgid = ? ");
			params.add(orgid);
		}
		if (years.length() > 0) {
			filter.append(" and year = ? ");
			params.add(years);
		}
		return PageFactoryEasyUI.newPage(KdTypeInfo1.class, filter.toString(), "", params.toArray());
	}

	@ResponseBody
	@RequestMapping("geneData")
	public JSONObject geneData(HttpServletRequest request, HttpSession session) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String orgid = request.getParameter("orgid")==null?"":request.getParameter("orgid");
		String years = request.getParameter("year")==null?"":request.getParameter("year");
		if (orgid.equals("")) {
			result.put("success", false);
			result.put("msg", "请选择医院!");
			return JSONObject.fromObject(result);
		}
		if (years.equals("")) {
			result.put("success", false);
			result.put("msg", "请选择年份!");
			return JSONObject.fromObject(result);
		}
		
		int recode = KdDataUtil.callProcTypeInfo1(years,orgid);
		if(recode == 0){
			result.put("success", true);
			result.put("msg", "生成完毕");
		}else{
			result.put("success", false);
			result.put("msg", "生成失败，请重试");
		}
		return JSONObject.fromObject(result);
	}
	@ResponseBody
	@RequestMapping("export")
	public void export2(HttpServletRequest request, HttpServletResponse response) {
		String orgid = request.getParameter("orgid")==null?"":request.getParameter("orgid");
		String years = request.getParameter("years")==null?"":request.getParameter("years");
		JSONArray list = KdDataUtil.geneListTypeInfo1(orgid,years);
		if(list.size() > 0){
			HSSFWorkbook wb = new HSSFWorkbook(); 
			HSSFSheet sheet = wb.createSheet("病种统计表(入院日期)");
			HSSFRow row = sheet.createRow((int) 0);
			HSSFCellStyle style = wb.createCellStyle(); 
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			HSSFCell cell = row.createCell((short) 0);
			cell.setCellValue("时间");  
			cell.setCellStyle(style);  
			cell = row.createCell((short) 1); 
			cell.setCellValue("IgA");  
			cell.setCellStyle(style);  
			cell = row.createCell((short) 2); 
			cell.setCellValue("HSPN");  
			cell.setCellStyle(style);  
			cell = row.createCell((short) 3);  
			cell.setCellValue("LN");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 4);  
			cell.setCellValue("CRF");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 5);  
			cell.setCellValue("CGN");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 6);  
			cell.setCellValue("NS");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 7);  
			cell.setCellValue("DM");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 8);  
			cell.setCellValue("DN");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 9);  
			cell.setCellValue("高血压");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 10);  
			cell.setCellValue("高血压肾");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 11);  
			cell.setCellValue("痛风肾");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 12);  
			cell.setCellValue("泌尿系疾病");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 13);  
			cell.setCellValue("多囊肾");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 14);  
			cell.setCellValue("其他");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 15);  
			cell.setCellValue("合计");  
			cell.setCellStyle(style); 
			
			int num = 0;
			for(int i = 0; i < list.size(); i++){
				num++;
				row = sheet.createRow((int) num);
				JSONObject a1 = (JSONObject)list.get(i);
				row.createCell((short) 0).setCellValue(a1.getString("month"));	
				row.createCell((short) 1).setCellValue(a1.getString("iga"));	
				row.createCell((short) 2).setCellValue(a1.getString("hspn"));	
				row.createCell((short) 3).setCellValue(a1.getString("ln"));	
				row.createCell((short) 4).setCellValue(a1.getString("crf"));	
				row.createCell((short) 5).setCellValue(a1.getString("cgn"));	
				row.createCell((short) 6).setCellValue(a1.getString("ns"));	
				row.createCell((short) 7).setCellValue(a1.getString("dm"));	
				row.createCell((short) 8).setCellValue(a1.getString("dn"));	
				row.createCell((short) 9).setCellValue(a1.getString("hp"));	
				row.createCell((short) 10).setCellValue(a1.getString("hpk"));	
				row.createCell((short) 11).setCellValue(a1.getString("gk"));	
				row.createCell((short) 12).setCellValue(a1.getString("md"));	
				row.createCell((short) 13).setCellValue(a1.getString("pk"));	
				row.createCell((short) 14).setCellValue(a1.getString("qt"));	
				row.createCell((short) 15).setCellValue(a1.getString("total"));	
			}
			String fileName = years+"病种统计表(入院日期)";
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			try {
		      wb.write(os);
		      byte[] content = os.toByteArray();
		      InputStream is = new ByteArrayInputStream(content);
		      // 设置response参数，可以打开下载页面
		      response.reset();
		      response.setContentType("application/vnd.ms-excel;charset=utf-8");
		      response.setHeader("Content-Disposition", "attachment;filename="
		          + new String((fileName + ".xls").getBytes(), "iso-8859-1"));
		      ServletOutputStream out = response.getOutputStream();
		 
		        bis = new BufferedInputStream(is);
		        bos = new BufferedOutputStream(out);
		        byte[] buff = new byte[2048];
		        int bytesRead;
		        // Simple read/write loop.
		        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
		          bos.write(buff, 0, bytesRead);
		        }
		      } catch (Exception e) {
		        // TODO: handle exception
		        e.printStackTrace();
		      } finally {
		        try {
					if (bis != null)
					  bis.close();
					if (bos != null)
					  bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      } 
		}
	}
	@Action("中美医院手术患者明细表")
	@ResponseBody
	@RequestMapping("gridJson")
	public JSONArray getValue() {
		JSONArray result = new JSONArray();
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		Connection conn = null;
		String sql = "select * from ZM_DEPTART order by departid";
		try {
			conn = DBManager.getConnection();
			pStatement = conn.prepareStatement(sql);
			resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				result.add(buildPerson("" + resultSet.getInt("departid"), resultSet.getString("department")));
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pStatement != null) {
					pStatement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;

	}

	private JSONObject buildPerson(String id, String name) {
		JSONObject result = new JSONObject();
		result.put("value", id);
		result.put("text", name);
		return result;
	}


}
