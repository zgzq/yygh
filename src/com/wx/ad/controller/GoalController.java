package com.wx.ad.controller;

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

@Menu("年度目标维护")
@RequestMapping("/hos/goal/")
@Controller
public class GoalController extends BaseController {
	@Action("年度目标维护")
	@RequestMapping("list")
	public String index() {
		return "admin/hostarget/goallist";
	}

	@Action("年度目标维护")
	@ResponseBody
	@RequestMapping("gridData")
	public GridDataModel<GoalInfo> gridData() {
		String departid = param("departid", "");
		String years= param("year", "");
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
		return PageFactoryEasyUI.newPage(GoalInfo.class,  filter.toString(), "order by goalid", params.toArray());
	}

	@Action("年度目标维护")
	@ResponseBody
	@RequestMapping("gridDatadtl")
	public GridDataModel<GoalInfo> gridDatadtl() {

		return PageFactory.newPage(GoalInfo.class, "", "");
	}

	@Action("年度目标维护")
	@ResponseBody
	@RequestMapping("/saveGrid")
	public AjaxMsg save(final @RequestParam("json") String json, final @RequestParam("json1") String json1) {

		return run(new CallBack() {

			@Override
			public AjaxMsg call() throws Exception {
				//
				GridSaveModel model = JSON.parseObject(json, GridSaveModel.class);
				GridSaveModel model1 = JSON.parseObject(json1, GridSaveModel.class);

				List<GoalInfo> insert = model.inserts(GoalInfo.class);

				List<GoalInfo> delete = model.deletes(GoalInfo.class);

				List<GoalInfo> update = model.updates(GoalInfo.class);

				// List<KpiPlanDtl> u1=model1.updates(KpiPlanDtl.class);

				for (GoalInfo dict : delete) {
					KpiPlanDtl.INSTANCE.delete("planid=?", dict.getGoalid());
					dict.delete();
				}

				for (GoalInfo dict : update) {
					dict.update();
				}

				for (GoalInfo dict : insert) {
					dict.setGoalid(dict.newId() + "");
					dict.insert();
				}

				// for(KpiPlanDtl dtl:){
				// dtl.update();
				// }

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
		if (!file_upload.isEmpty()) {
			try {
				Workbook workbook = Workbook.getWorkbook(file_upload.getInputStream());
				// 获得第一个sheet
				Sheet sheet = workbook.getSheet(0);
				rowTotals = sheet.getRows();
				session.setAttribute("importExcelTotalRows", rowTotals);
				for (int row = 1; row < sheet.getRows(); row++) {
					session.setAttribute("importExcelCurrentRows", row);
					GoalInfo gDtl = new GoalInfo();
					for (int col = 0; col < sheet.getColumns(); col++) {
						if (sheet.getCell(col, 0).getContents().equals("科室编码")) {
							gDtl.setDepartopcode(sheet.getCell(col, row).getContents());
						}
						if (sheet.getCell(col, 0).getContents().equals("科室名称")) {
							gDtl.setDepartment(sheet.getCell(col, row).getContents());
						}
						if (sheet.getCell(col, 0).getContents().equals("指标名称")) {
							gDtl.setGoalname(sheet.getCell(col, row).getContents());
						}
						if (sheet.getCell(col, 0).getContents().equals("目标")) {
							gDtl.setYearvalue(sheet.getCell(col, row).getContents());
						}

					}
					gDtl.setGoalid(gDtl.newId() + "");
//					gDtl.setDepartid(departid);
					gDtl.setYear(year);
					gDtl.insert();

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
		String departid = request.getParameter("departid");
		JSONArray list = ZmZbsjkUtil.geneListGoal(years,departid);
		if(list.size() > 0){
			HSSFWorkbook wb = new HSSFWorkbook(); 
			HSSFSheet sheet = wb.createSheet("年度目标");
			HSSFRow row = sheet.createRow((int) 0);
			HSSFCellStyle style = wb.createCellStyle(); 
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			HSSFCell cell = row.createCell((short) 0);
			cell.setCellValue("科室编码");  
			cell.setCellStyle(style);  
			cell = row.createCell((short) 1); 
			cell.setCellValue("年度");  
			cell.setCellStyle(style);  
			cell = row.createCell((short) 2); 
			cell.setCellValue("科室名称");  
			cell.setCellStyle(style);  
			cell = row.createCell((short) 3);  
			cell.setCellValue("项目");  
			cell.setCellStyle(style); 
			cell = row.createCell((short) 4);  
			cell.setCellValue("年度目标");  
			cell.setCellStyle(style); 
			
			int num = 0;
			for(int i = 0; i < list.size(); i++){
				num++;
				row = sheet.createRow((int) num);
				JSONObject a1 = (JSONObject)list.get(i);
				row.createCell((short) 0).setCellValue(a1.getString("departopcode"));	
				row.createCell((short) 1).setCellValue(a1.getString("year"));	
				row.createCell((short) 2).setCellValue(a1.getString("department"));	
				row.createCell((short) 3).setCellValue(a1.getString("goalname"));	
				row.createCell((short) 4).setCellValue(a1.getString("yearvalue"));	
			}
			String fileName = years+"-年度目标";
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
