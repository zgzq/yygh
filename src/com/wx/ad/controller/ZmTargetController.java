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
import com.wx.ad.dbo.ZmIMP;
import com.wx.ad.util.PageFactory;
import com.wx.ad.util.PageFactoryEasyUI;
import com.wx.ad.util.Test3;
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

@Menu("中美医院指标数据库(科室月度)")
@RequestMapping("/hos/hospital/")
@Controller
public class ZmTargetController extends BaseController {
	@RequestMapping("list")
	public String index() {
		return "admin/hostarget/list";
	}

	@Action("中美医院指标数据库(科室月度)")
	@ResponseBody
	@RequestMapping("gridData1")
	public GridDataModel<ZbsjkInfo1> gridData1() {
		// String department = param("deptname", "");

		String departid = param("departid", "");
		String years = param("years", "");
		String month = param("month", "");
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if (departid.length() > 0) {
			filter.append(" and departopcode = ? ");
			params.add(departid);
		}
		if("".equals(departid)){
			departid = "0";
			filter.append(" and departopcode = ? ");
			params.add(departid);
		}
		if (years.length() > 0) {
			filter.append(" and year = ? ");
			params.add(years);
		}
		if (month.length() > 0) {
			filter.append(" and month = ? ");
			params.add(month);
		}
		return PageFactoryEasyUI.newPage(ZbsjkInfo1.class, filter.toString(), "order by id", params.toArray());
	}

	@Action("中美医院指标数据库(科室月度)")
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
		String departid = request.getParameter("departid");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		/*if (departid.equals("")) {
			result.put("success", false);
			result.put("msg", "请选择科室!");
			return JSONObject.fromObject(result);
		}*/
		
		if("".equals(departid)){
			departid = "0";
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
		Test3 t3 = new Test3();
		int recode = t3.callProc(departid,year,month);
		if(recode != -1){
			result.put("success", true);
			result.put("msg", "生成完毕,生成"+recode+"条数据");
		}else{
			result.put("success", false);
			result.put("msg", "生成失败，请重试");
		}
		return JSONObject.fromObject(result);
	}
	
	@ResponseBody
	@RequestMapping("importExcel")
	public JSONObject importExcel(@RequestParam(value = "file_upload", required = false) MultipartFile file_upload,
			HttpServletRequest request, HttpSession session) {
		String depart = param("departid", "");
		// String departid=request.getParameter("departid");
		HashMap<String, Object> result = new HashMap<String, Object>();
		Integer rowTotals = 0;
		String departid = request.getParameter("departid");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		/*if (departid.equals("")) {
			result.put("success", false);
			result.put("msg", "请选择科室!");
			return JSONObject.fromObject(result);
		}*/
		/*if("".equals(departid)){
			departid = "0";
		}*/
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
		Test3 t3 = new Test3();
		int recode = t3.delTarget(departid,year,month);
		if(recode != -1){
			
		}else{
			result.put("success", false);
			result.put("msg", "保存失败!");
			return JSONObject.fromObject(result);
		}
		if (!file_upload.isEmpty()) {
			try {
				Workbook workbook = Workbook.getWorkbook(file_upload.getInputStream());
				// 获得第一个sheet
				Sheet sheet = workbook.getSheet(0);
				rowTotals = sheet.getRows();
				session.setAttribute("importExcelTotalRows", rowTotals);
				for (int row = 1; row < sheet.getRows(); row++) {
					session.setAttribute("importExcelCurrentRows", row);
					ZmIMP zBtl = new ZmIMP();
					for (int col = 0; col < sheet.getColumns(); col++) {

						if (sheet.getCell(col, 0).getContents().equals("科室编码")) {
							zBtl.setDept_code(sheet.getCell(col, row).getContents());
						}
						if (sheet.getCell(col, 0).getContents().equals("项目")) {
							zBtl.setXm(sheet.getCell(col, row).getContents());
						}
						if (sheet.getCell(col, 0).getContents().equals("指标")) {
							zBtl.setDqz(sheet.getCell(col, row).getContents());
						}
						

					}
					zBtl.setId(zBtl.newId() + "");
//					zBtl.setDept_code(departid);
					zBtl.setYear(year);
					zBtl.setMonth(month);
					zBtl.insert();

				}
				workbook.close();
				DBManager.commitAll();

				result.put("success", true);
				result.put("msg", "保存成功!本次导入" + (rowTotals - 1) + "条数据");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				DBManager.rollbackAll();
				e.printStackTrace();
				result.put("success", false);
				result.put("msg", "保存失败!");
			}
		} else {
			DBManager.rollbackAll();
			result.put("success", false);
			result.put("msg", "请选择文件!");
		}

		return JSONObject.fromObject(result);
	}
	
	
	@ResponseBody
	@RequestMapping("export")
	public void export(HttpServletRequest request, HttpServletResponse response) {
		String years = request.getParameter("years");
		String month = request.getParameter("month");
		String departid = request.getParameter("departid");
		if("".equals(departid)){
			departid = "0";
		}
		JSONArray list = ZmZbsjkUtil.geneList(years,month,departid);
		if(list.size() > 0){
			HSSFWorkbook wb = new HSSFWorkbook(); 
			HSSFSheet sheet = wb.createSheet("医院指标信息");
			HSSFRow row = sheet.createRow((int) 0);
			HSSFCellStyle style = wb.createCellStyle(); 
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			HSSFCell cell = row.createCell((short) 0);
			cell.setCellValue("项目");  
			cell.setCellStyle(style);  
			cell = row.createCell((short) 1); 
			cell.setCellValue("目标指标");  
			cell.setCellStyle(style);  
			cell = row.createCell((short) 2); 
			cell.setCellValue("累计完成");  
			cell.setCellStyle(style);  
			cell = row.createCell((short) 3);  
			cell.setCellValue("累计完成率");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 4);  
			cell.setCellValue("累计同期对比");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 5);  
			cell.setCellValue("剩余目标");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 6);  
			cell.setCellValue("月均");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 7);  
			cell.setCellValue("当期目标");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 8);  
			cell.setCellValue("当期值");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 9);  
			cell.setCellValue("当期完成率");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 10);  
			cell.setCellValue("同期对比");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 11);  
			cell.setCellValue("环比");  
			cell.setCellStyle(style); 
			int num = 0;
			for(int i = 0; i < list.size(); i++){
				num++;
				row = sheet.createRow((int) num);
				JSONObject a1 = (JSONObject)list.get(i);
				row.createCell((short) 0).setCellValue(a1.getString("project"));	
				row.createCell((short) 1).setCellValue(a1.getString("mbzb"));	
				row.createCell((short) 2).setCellValue(a1.getString("ljwc"));	
				row.createCell((short) 3).setCellValue(a1.getString("ljwcl"));	
				row.createCell((short) 4).setCellValue(a1.getString("ljtqdb"));	
				row.createCell((short) 5).setCellValue(a1.getString("symb"));	
				row.createCell((short) 6).setCellValue(a1.getString("yj"));	
				row.createCell((short) 7).setCellValue(a1.getString("dqmb"));	
				row.createCell((short) 8).setCellValue(a1.getString("dqz"));	
				row.createCell((short) 9).setCellValue(a1.getString("dqwcl"));	
				row.createCell((short) 10).setCellValue(a1.getString("tqdb"));	
				row.createCell((short) 11).setCellValue(a1.getString("hb"));	
			}
			String fileName = years+"."+month+"-指标信息";
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


	@Action("中美医院指标数据库(科室月度)")
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

	@Action("科室维护")
	@ResponseBody
	@RequestMapping("saveGrid")
	public AjaxMsg gridSave(final @RequestParam("json") String json) {

		return run(new CallBack() {

			@Override
			public AjaxMsg call() throws Exception {
				GridSaveModel model = JSON.parseObject(json, GridSaveModel.class);

				List<ZbsjkInfo1> insert = model.inserts(ZbsjkInfo1.class);

				List<ZbsjkInfo1> delete = model.deletes(ZbsjkInfo1.class);

				List<ZbsjkInfo1> update = model.updates(ZbsjkInfo1.class);

				for (ZbsjkInfo1 dict : delete) {
					dict.delete();
				}

				for (ZbsjkInfo1 dict : update) {
					dict.update();
				}

				for (ZbsjkInfo1 dict : insert) {
					dict.setId(dict.newId() + "");
					dict.insert();
				}
				return AjaxMsg.ok();
			}
		});

	}

}
