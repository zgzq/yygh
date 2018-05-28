package com.wx.ad.controller;

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

@Menu("中美医院手术患者明细表")
@RequestMapping("/hos/surgeryDate/")
@Controller
public class ZmSurgeryDateController extends BaseController {
	@RequestMapping("listSurgeryDate")
	public String index() {
		return "admin/surgery/listSurgeryDate";
	}
	

	@Action("中美医院手术患者明细表")
	@ResponseBody
	@RequestMapping("gridData1")
	public GridDataModel<ZmSurgeryDateDetail> gridData1() {
		// String department = param("deptname", "");

		String departid = param("departid", "");
		String years = param("years", "");
		String month = param("month", "");
		String month2 = param("month2", "");
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if (departid.length() > 0) {
			filter.append(" and dept_code = ? ");
			params.add(departid);
		}
		if (years.length() > 0) {
			filter.append(" and year = ? ");
			params.add(years);
		}
		if(month.length() > 0 && month2.length() > 0){
			filter.append(" and month >= ? and month <= ?");
			params.add(month);
			params.add(month2);
		}else if (month.length() > 0 && month2.length() <= 0) {
			filter.append(" and month = ? ");
			params.add(month);
		}else{
			
		}
		return PageFactoryEasyUI.newPage(ZmSurgeryDateDetail.class, filter.toString(), "order by id", params.toArray());
	}
	
	@ResponseBody
	@RequestMapping("gridData")
	public GridDataModel<Deptart> gridData() {
		String department = param("deptname", "");

		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if (department.length() > 0) {
			filter.append(" and department like ? ");
			params.add(department + "%");
		}

		return PageFactory.newPage(Deptart.class, filter.toString(), "order by departid", params.toArray());
	}
	@ResponseBody
	@RequestMapping("geneData")
	public JSONObject geneData(HttpServletRequest request, HttpSession session) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String departid = request.getParameter("departid")==null?"":request.getParameter("departid");
		String year = request.getParameter("year")==null?"":request.getParameter("year");
		String month = request.getParameter("month")==null?"":request.getParameter("month");
		String month2 = request.getParameter("month2")==null?"":request.getParameter("month2");
		if (departid.equals("")) {
			result.put("success", false);
			result.put("msg", "请选择科室!");
			return JSONObject.fromObject(result);
		}
		if (year.equals("")) {
			result.put("success", false);
			result.put("msg", "请选择年度");
			return JSONObject.fromObject(result);
		}
		if (month.equals("")) {
			result.put("success", false);
			result.put("msg", "请选择月份");
			return JSONObject.fromObject(result);
		}
		Test4 t4 = new Test4();
		int recode = t4.callProcDate(departid,year,month,month2);
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
		String years = request.getParameter("year")==null?"":request.getParameter("year");
		String month = request.getParameter("month")==null?"":request.getParameter("month");
		String month2 = request.getParameter("month2")==null?"":request.getParameter("month2");
		String departid = request.getParameter("departid")==null?"":request.getParameter("departid");
		JSONArray list = Test4.geneList(years,month,month2,departid);
		if(list.size() > 0){
			HSSFWorkbook wb = new HSSFWorkbook(); 
			HSSFSheet sheet = wb.createSheet("手术信息");
			HSSFRow row = sheet.createRow((int) 0);
			HSSFCellStyle style = wb.createCellStyle(); 
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			HSSFCell cell = row.createCell((short) 0);
			cell.setCellValue("科室");  
			cell.setCellStyle(style);  
			cell = row.createCell((short) 1); 
			cell.setCellValue("住院号");  
			cell.setCellStyle(style);  
			cell = row.createCell((short) 2); 
			cell.setCellValue("姓名");  
			cell.setCellStyle(style);  
			cell = row.createCell((short) 3);  
			cell.setCellValue("性别");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 4);  
			cell.setCellValue("年龄");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 5);  
			cell.setCellValue("费别");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 6);  
			cell.setCellValue("现地址");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 7);  
			cell.setCellValue("电话");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 8);  
			cell.setCellValue("入院日期");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 9);  
			cell.setCellValue("手术日期");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 10);  
			cell.setCellValue("出院日期");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 11);  
			cell.setCellValue("住院天数");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 12);  
			cell.setCellValue("诊断");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 13);  
			cell.setCellValue("手术名称");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 14);  
			cell.setCellValue("手术医生");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 15);  
			cell.setCellValue("辅助医生");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 16);  
			cell.setCellValue("手术级别");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 17);  
			cell.setCellValue("消费金额");  
			cell.setCellStyle(style); 
			int num = 0;
			for(int i = 0; i < list.size(); i++){
				num++;
				row = sheet.createRow((int) num);
				JSONObject a1 = (JSONObject)list.get(i);
				row.createCell((short) 0).setCellValue(a1.getString("dept"));	
				row.createCell((short) 1).setCellValue(a1.getString("patient_id"));	
				row.createCell((short) 2).setCellValue(a1.getString("name"));	
				row.createCell((short) 3).setCellValue(a1.getString("sex"));	
				row.createCell((short) 4).setCellValue(a1.getString("age"));	
				row.createCell((short) 5).setCellValue(a1.getString("charge_type"));	
				row.createCell((short) 6).setCellValue(a1.getString("mailing_address"));	
				row.createCell((short) 7).setCellValue(a1.getString("phone"));	
				row.createCell((short) 8).setCellValue(a1.getString("admission_date_time"));	
				row.createCell((short) 9).setCellValue(a1.getString("operating_date"));	
				row.createCell((short) 10).setCellValue(a1.getString("discharge_date_time"));	
				row.createCell((short) 11).setCellValue(a1.getString("inhos_days"));	
				row.createCell((short) 12).setCellValue(a1.getString("diagnosis_desc"));	
				row.createCell((short) 13).setCellValue(a1.getString("operation_desc"));	
				row.createCell((short) 14).setCellValue(a1.getString("operator"));	
				row.createCell((short) 15).setCellValue(a1.getString("assistant"));	
				row.createCell((short) 16).setCellValue(a1.getString("operat_level"));	
				row.createCell((short) 17).setCellValue(a1.getString("total_payments"));	
			}
			String fileName = departid+"-"+years+"."+month+"手术基本信息";
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
