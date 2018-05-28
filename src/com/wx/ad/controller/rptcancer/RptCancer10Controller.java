package com.wx.ad.controller.rptcancer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.wx.ad.bean.GridDataModel;
import com.wx.ad.bean.GridSaveModel;
import com.wx.ad.dbo.GoalInfo;
import com.wx.ad.dbo.KpiPlanDtl;
import com.wx.ad.dbo.ZmCancerInfo;
import com.wx.ad.util.PageFactory;
import com.wx.ad.util.PageFactoryEasyUI;
import com.wx.ad.web.AuthController;

import jxl.Sheet;
import jxl.Workbook;
import my.ann.Action;
import my.ann.Menu;
import my.dao.pool.DBManager;
import my.web.AjaxMsg;
import my.web.BaseController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Menu("肿瘤科指标信息")
@RequestMapping("/kpi/rptcancer10/")
@Controller
public class RptCancer10Controller extends BaseController {
	@Action("肿瘤科指标信息")
	@RequestMapping("list")
	public String index() {
		return "admin/rptcancer/rptcancer10/list";
	}

	@Action("肿瘤科指标信息")
	@ResponseBody
	@RequestMapping("gridData")
	public GridDataModel<ZmCancerInfo> gridData() {
		String month= param("month", "");
		if("".equals(month)){
			month = "0";
		}
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if (month.length() > 0) {
			filter.append(" and month = ? ");
			params.add(month);
		}
//		int num = RptCancerUtil.geneList(month);	
		return PageFactoryEasyUI.newPage(ZmCancerInfo.class,  filter.toString(), "order by id", params.toArray());
	}

	@ResponseBody
	@RequestMapping("export")
	public void export(HttpServletRequest request, HttpServletResponse response) {
		String month = request.getParameter("month");
		if("".equals(month)){
			month = "0";
		}
		JSONArray list = RptCancerUtil.geneList1(month);
		if(list.size() > 0){
			HSSFWorkbook wb = new HSSFWorkbook(); 
			HSSFSheet sheet = wb.createSheet("肿瘤科指标数据");
			HSSFRow row = sheet.createRow((int) 0);
			HSSFCellStyle style = wb.createCellStyle(); 
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			HSSFCell cell = row.createCell((short) 0);
			cell.setCellValue("指标名称");  
			cell.setCellStyle(style);  
			cell = row.createCell((short) 1); 
			cell.setCellValue("2018");  
			cell.setCellStyle(style);  
			cell = row.createCell((short) 2); 
			cell.setCellValue("2017");  
			cell.setCellStyle(style);  
			
			int num = 0;
			for(int i = 0; i < list.size(); i++){
				num++;
				row = sheet.createRow((int) num);
				JSONObject a1 = (JSONObject)list.get(i);
				row.createCell((short) 0).setCellValue(a1.getString("project_name"));	
				row.createCell((short) 1).setCellValue(a1.getString("value1"));	
				row.createCell((short) 2).setCellValue(a1.getString("value2"));	
			}
			String fileName = "肿瘤内科指标信息-"+month;
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
	
	@ResponseBody
	@RequestMapping("getProgressValueByJson")
	public JSONObject getProgressValueByJson(Model m, HttpSession session) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		Integer importExcelTotalRows = (Integer) session.getAttribute("importExcelTotalRows");
		Integer importExcelCurrentRows = (Integer) session.getAttribute("importExcelCurrentRows");
		Double value = 0.00;
		if (importExcelTotalRows != 0 && importExcelTotalRows != null && importExcelCurrentRows != null) {
			value = Math.ceil(importExcelCurrentRows * 100.0 / importExcelTotalRows);
		}

		result.put("progressValue", "23");
		return JSONObject.fromObject(result);
	}
}
