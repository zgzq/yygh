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

@Menu("肾病医院数据出院患者明细")
@RequestMapping("/kd/outHos/")
@Controller
public class KdOutHosDetailController extends BaseController {
	@RequestMapping("outHosDetail")
	public String index() {
		return "admin/kddata/outHosDetail";
	}
	

	@Action("肾病医院数据出院患者明细")
	@ResponseBody
	@RequestMapping("gridData")
	public GridDataModel<KdOutHosDetail> gridData1() {
		// String department = param("deptname", "");

		String orgid = param("orgid", "");
		String years = param("years", "");
		String month = param("month", "");
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
		if (month.length() > 0) {
			filter.append(" and month = ? ");
			params.add(month);
		}
		return PageFactoryEasyUI.newPage(KdOutHosDetail.class, filter.toString(), "order by discharge_date_time", params.toArray());
	}

	@ResponseBody
	@RequestMapping("geneData")
	public JSONObject geneData(HttpServletRequest request, HttpSession session) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String orgid = request.getParameter("orgid")==null?"":request.getParameter("orgid");
		String year = request.getParameter("year")==null?"":request.getParameter("year");
		String month = request.getParameter("month")==null?"":request.getParameter("month");
		if (orgid.equals("")) {
			result.put("success", false);
			result.put("msg", "请选择医院!");
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
		int recode = KdDataUtil.callProcOutHos(orgid,year,month);
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
		String month = request.getParameter("month")==null?"":request.getParameter("month");
		String years = request.getParameter("year")==null?"":request.getParameter("year");
		JSONArray list = KdDataUtil.geneListOutHosDetail(orgid,years,month);
		if(list.size() > 0){
			HSSFWorkbook wb = new HSSFWorkbook(); 
			HSSFSheet sheet = wb.createSheet("出院患者明细");
			HSSFRow row = sheet.createRow((int) 0);
			HSSFCellStyle style = wb.createCellStyle(); 
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			HSSFCell cell = row.createCell((short) 0);
			cell.setCellValue("住院号");  
			cell.setCellStyle(style);  
			cell = row.createCell((short) 1); 
			cell.setCellValue("姓名");  
			cell.setCellStyle(style);  
			cell = row.createCell((short) 2); 
			cell.setCellValue("性别");  
			cell.setCellStyle(style);  
			cell = row.createCell((short) 3);  
			cell.setCellValue("年龄");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 4);  
			cell.setCellValue("住址");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 5);  
			cell.setCellValue("诊断");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 6);  
			cell.setCellValue("来源途径");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 7);  
			cell.setCellValue("科室");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 8);  
			cell.setCellValue("主管医生");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 9);  
			cell.setCellValue("入院日期");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 10);  
			cell.setCellValue("出院日期");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 11);  
			cell.setCellValue("住院天数");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 12);  
			cell.setCellValue("消费金额");  
			cell.setCellStyle(style); 
			
			int num = 0;
			for(int i = 0; i < list.size(); i++){
				num++;
				row = sheet.createRow((int) num);
				JSONObject a1 = (JSONObject)list.get(i);
				row.createCell((short) 0).setCellValue(a1.getString("inp_no"));	
				row.createCell((short) 1).setCellValue(a1.getString("name"));	
				row.createCell((short) 2).setCellValue(a1.getString("sex"));	
				row.createCell((short) 3).setCellValue(a1.getString("age"));	
				row.createCell((short) 4).setCellValue(a1.getString("mail_address"));	
				row.createCell((short) 5).setCellValue(a1.getString("diagnosis"));	
				row.createCell((short) 6).setCellValue(a1.getString("identity"));	
				row.createCell((short) 7).setCellValue(a1.getString("dept_admission_to"));	
				row.createCell((short) 8).setCellValue(a1.getString("attending_doctor"));	
				row.createCell((short) 9).setCellValue(a1.getString("admission_date_time"));	
				row.createCell((short) 10).setCellValue(a1.getString("discharge_date_time"));	
				row.createCell((short) 11).setCellValue(a1.getString("inhos_days"));	
				row.createCell((short) 12).setCellValue(a1.getString("total_payments"));	
			}
			String fileName = years+"."+month+"出院患者明细";
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
