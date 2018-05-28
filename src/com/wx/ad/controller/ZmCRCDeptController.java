package com.wx.ad.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
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
import com.wx.ad.dbo.ZmCRCDept;
import com.wx.ad.dbo.ZmCRCInfo;
import com.wx.ad.dbo.ZmSurgeryCol;
import com.wx.ad.dbo.ZmSurgeryDetail;
import com.wx.ad.util.PageFactory;
import com.wx.ad.util.PageFactoryEasyUI;
import com.wx.ad.util.Test3;
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

@Menu("大肠癌筛查开单执行表")
@RequestMapping("/zm/crcDept/")
@Controller
public class ZmCRCDeptController extends BaseController {
	@RequestMapping("list")
	public String index() {
		return "admin/zmcrc/zmcrcDept";
	}

	
	@Action("大肠癌筛查开单执行表")
	@ResponseBody
	@RequestMapping("gridData2")
	public GridDataModel<ZmCRCDept> gridData2() {
		// String department = param("deptname", "");
		
//		String departid = param("departid", "");
		String years = param("year", "");
		String month = param("month", "");
		
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
//		if (departid.length() > 0) {
//			filter.append(" and dept_code = ? ");
//			params.add(departid);
//		}
		if (years.length() > 0) {
			filter.append(" and year = ? ");
			params.add(years);
		}
		if (month.length() > 0) {
			filter.append(" and month = ? ");
			params.add(month);
		}
		return PageFactoryEasyUI.newPage(ZmCRCDept.class, filter.toString(), "order by id", params.toArray());
	}
	
	@ResponseBody
	@RequestMapping("geneData2")
	public JSONObject geneData2(HttpServletRequest request, HttpSession session) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String years = request.getParameter("years");
		String month = request.getParameter("month");
		int recode = ZmCRCUtil.genValueHz1(years, month);
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
	@RequestMapping("export2")
	public void export2(HttpServletRequest request, HttpServletResponse response) {
		String years = request.getParameter("years");
		String month = request.getParameter("month");
		JSONArray list = ZmCRCUtil.geneList2(years,month);
		if(list.size() > 0){
			HSSFWorkbook wb = new HSSFWorkbook(); 
			HSSFSheet sheet = wb.createSheet("开单执行");
			HSSFRow row = sheet.createRow((int) 0);
			HSSFCellStyle style = wb.createCellStyle(); 
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			HSSFCell cell = row.createCell((short) 0);
			cell.setCellValue("患者标识号");  
			cell.setCellStyle(style);  
			cell = row.createCell((short) 1); 
			cell.setCellValue("项目名称");  
			cell.setCellStyle(style);  
			cell = row.createCell((short) 2); 
			cell.setCellValue("开单科室");  
			cell.setCellStyle(style);  
			cell = row.createCell((short) 3);  
			cell.setCellValue("执行科室");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 4);  
			cell.setCellValue("费用");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 5);  
			cell.setCellValue("开单类别");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 6);  
			cell.setCellValue("开单医生");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 7);  
			cell.setCellValue("就诊时间");  
			cell.setCellStyle(style); 
			int num = 0;
			for(int i = 0; i < list.size(); i++){
				num++;
				row = sheet.createRow((int) num);
				JSONObject a1 = (JSONObject)list.get(i);
				row.createCell((short) 0).setCellValue(a1.getString("patient_id"));	
				row.createCell((short) 1).setCellValue(a1.getString("item_name"));	
				row.createCell((short) 2).setCellValue(a1.getString("dept_name"));	
				row.createCell((short) 3).setCellValue(a1.getString("performed_by"));	
				row.createCell((short) 4).setCellValue(a1.getString("charges"));	
				row.createCell((short) 5).setCellValue(a1.getString("req_class"));	
				row.createCell((short) 6).setCellValue(a1.getString("ordered_doctor"));	
				row.createCell((short) 7).setCellValue(a1.getString("visit_time"));	
			}
			String fileName = "大肠癌筛查-"+years+"."+month+"-开单执行";
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

	
	

	private JSONObject buildPerson(String id, String name) {
		JSONObject result = new JSONObject();
		result.put("value", id);
		result.put("text", name);
		return result;
	}


}
