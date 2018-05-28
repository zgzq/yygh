package com.wx.ad.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.print.DocFlavor.STRING;
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
import my.web.BaseController.CallBack;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Menu("月度目标维护")
@RequestMapping("/hos/target/")
@Controller
public class TargetConroller extends BaseController {

	@RequestMapping("list")
	public String index() {
		return "admin/hostarget/targetlist";
	}

	@Action("月度目标维护")
	@ResponseBody
	@RequestMapping("gridData")
	public GridDataModel<Target> gridData() {
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
		if (years.length() > 0) {
			filter.append(" and year = ? ");
			params.add(years);
		}
		if (month.length() > 0) {
			filter.append(" and month = ? ");
			params.add(month);
		}

		return PageFactoryEasyUI.newPage(Target.class, filter.toString(), "order by id", params.toArray());
	}

	@Action("月度目标维护")
	@ResponseBody
	@RequestMapping("saveGrid")
	public AjaxMsg gridSave(final @RequestParam("json") String json) {

		return run(new CallBack() {

			@Override
			public AjaxMsg call() throws Exception {
				GridSaveModel model = JSON.parseObject(json, GridSaveModel.class);

				List<Target> insert = model.inserts(Target.class);

				List<Target> delete = model.deletes(Target.class);

				List<Target> update = model.updates(Target.class);

				for (Target dict : delete) {
					dict.delete();
				}

				for (Target dict : update) {
					dict.update();
				}

				for (Target dict : insert) {
					dict.setId(dict.newId() + "");
					dict.insert();
				}
				return AjaxMsg.ok();
			}
		});

	}

	@ResponseBody
	@RequestMapping("importExcel")
	public JSONObject importExcel(@RequestParam(value = "file_upload", required = false) MultipartFile file_upload,
			HttpServletRequest request, HttpSession session) {
		String depart = param("departid", "");
		// String departid=request.getParameter("departid");
		HashMap<String, Object> result = new HashMap<String, Object>();
		Integer rowTotals = 0;
//		String departid = request.getParameter("departid");
		String year = request.getParameter("year");
		String month = request.getParameter("month");

		/*if (departid.equals("")) {
			result.put("success", false);
			result.put("msg", "请选择科室!");
			return JSONObject.fromObject(result);
		}*/
		if (year.equals("")) {
			result.put("success", false);
			result.put("msg", "请选择年度");
			return JSONObject.fromObject(result);
		}
		if (month.equals("")) {
			result.put("success", false);
			result.put("msg", "请选择月份!");
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
					Target tg = new Target();
					for (int col = 0; col < sheet.getColumns(); col++) {

						if (sheet.getCell(col, 0).getContents().equals("科室编码")) {
							tg.setDepartopcode(sheet.getCell(col, row).getContents());
						}
						if (sheet.getCell(col, 0).getContents().equals("科室名称")) {
							tg.setDepartment(sheet.getCell(col, row).getContents());
						}
						if (sheet.getCell(col, 0).getContents().equals("指标名称")) {
							tg.setTarget(sheet.getCell(col, row).getContents());
						}
						if (sheet.getCell(col, 0).getContents().equals("目标")) {
							tg.setMonthvalue(sheet.getCell(col, row).getContents());
						}

					}
					
					tg.setId(tg.newId() + "");
					tg.setYear(year);
					tg.setMonth(month);
					tg.insert();

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
		JSONArray list = ZmZbsjkUtil.geneListTarget(years,month,departid);
		if(list.size() > 0){
			HSSFWorkbook wb = new HSSFWorkbook(); 
			HSSFSheet sheet = wb.createSheet("月度目标");
			HSSFRow row = sheet.createRow((int) 0);
			HSSFCellStyle style = wb.createCellStyle(); 
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			HSSFCell cell = row.createCell((short) 0);
			cell.setCellValue("年份");  
			cell.setCellStyle(style);  
			cell = row.createCell((short) 1); 
			cell.setCellValue("月份");  
			cell.setCellStyle(style);  
			cell = row.createCell((short) 2); 
			cell.setCellValue("科室编码");  
			cell.setCellStyle(style);  
			cell = row.createCell((short) 3); 
			cell.setCellValue("科室名称");  
			cell.setCellStyle(style);  
			cell = row.createCell((short) 4);  
			cell.setCellValue("项目");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 5);  
			cell.setCellValue("目标值");  
			cell.setCellStyle(style); 
			
			int num = 0;
			for(int i = 0; i < list.size(); i++){
				num++;
				row = sheet.createRow((int) num);
				JSONObject a1 = (JSONObject)list.get(i);
				row.createCell((short) 0).setCellValue(a1.getString("year"));	
				row.createCell((short) 1).setCellValue(a1.getString("month"));	
				row.createCell((short) 2).setCellValue(a1.getString("departopcode"));	
				row.createCell((short) 3).setCellValue(a1.getString("department"));	
				row.createCell((short) 4).setCellValue(a1.getString("target"));	
				row.createCell((short) 5).setCellValue(a1.getString("monthvalue"));	
			}
			String fileName = years+"."+month+"-月度目标";
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
	@RequestMapping("/download")
	public String download() throws IOException {
		String filepath = param("filename", "");
		System.out.println(filepath);
		Connection conn = null;
		// 创建webbook
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 在表格中添加sheel
		HSSFSheet sheet = workbook.createSheet("指标模版");
		// 添加表头
		HSSFRow row = sheet.createRow(0);
		// 创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 设置列名
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("指标名称");
		cell.setCellStyle(style);
		HSSFCell cell1 = row.createCell(1);
		cell1.setCellValue("一月目标");
		HSSFCell cell2 = row.createCell(2);
		cell2.setCellValue("二月目标");
		cell2.setCellStyle(style);
		HSSFCell cell3 = row.createCell(3);
		cell3.setCellStyle(style);
		cell3.setCellValue("三月目标");
		HSSFCell cell4 = row.createCell(4);
		cell4.setCellValue("四月月目标");
		cell4.setCellStyle(style);
		HSSFCell cell5 = row.createCell(5);
		cell5.setCellValue("五月月目标");
		cell5.setCellStyle(style);
		HSSFCell cell6 = row.createCell(6);
		cell6.setCellValue("六月目标");
		cell6.setCellStyle(style);
		HSSFCell cell7 = row.createCell(7);
		cell7.setCellValue("七月月目标");
		cell7.setCellStyle(style);
		HSSFCell cell8 = row.createCell(8);
		cell8.setCellValue("八月目标");
		cell8.setCellStyle(style);
		HSSFCell cell9 = row.createCell(9);
		cell9.setCellStyle(style);
		cell9.setCellValue("九月目标");
		cell9.setCellStyle(style);
		HSSFCell cell10 = row.createCell(10);
		cell10.setCellValue("十月目标");
		cell10.setCellStyle(style);
		HSSFCell cell11 = row.createCell(11);
		cell11.setCellValue("十一月目标");
		cell11.setCellStyle(style);
		HSSFCell cell12 = row.createCell(12);
		cell12.setCellValue("十二月目标");
		cell12.setCellStyle(style);
		conn = DBManager.getConnection();
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		String sql = "select * from ZM_TARGET";
		try {
			conn = DBManager.getConnection();
			pStatement = conn.prepareStatement(sql);
			resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				row = sheet.createRow(resultSet.getRow());
				row.createCell(0).setCellValue(resultSet.getString("target"));
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
		try {
			FileOutputStream fStream = new FileOutputStream("E:/students.xls");
			workbook.write(fStream);
			fStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
}
