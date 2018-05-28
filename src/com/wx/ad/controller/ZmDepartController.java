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
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
import com.wx.ad.bean.GridDataModel;
import com.wx.ad.bean.GridSaveModel;
import com.wx.ad.dbo.Deptart;
import com.wx.ad.util.PageFactory;
import com.wx.ad.util.PageFactoryEasyUI;
import com.wx.ad.web.AuthController;

import my.ann.Action;
import my.ann.Menu;
import my.dao.pool.DBManager;
import my.web.AjaxMsg;
import my.web.BaseController;


@Menu("科室维护")
@RequestMapping("/hos/depart/")
@Controller
public class ZmDepartController extends BaseController {
	@RequestMapping("list")
	public String index() {
		return "admin/hostarget/departlist";
	}
	@Action("科室维护")
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

		return PageFactoryEasyUI.newPage(Deptart.class, filter.toString(), "order by departid", params.toArray());
	}
	@Action("科室维护")
	@ResponseBody
	@RequestMapping("gridData1")
	public GridDataModel<Deptart> gridData1() {
		String department = param("deptname", "");

		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if (department.length() > 0) {
			filter.append(" and department like ? ");
			params.add(department + "%");
		}

		return PageFactory.newPage(Deptart.class, filter.toString(), "order by departid", params.toArray());
	}
	@Action("科室维护")
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

				List<Deptart> insert = model.inserts(Deptart.class);

				List<Deptart> delete = model.deletes(Deptart.class);

				List<Deptart> update = model.updates(Deptart.class);

				for (Deptart dict : delete) {
					dict.delete();
				}

				for (Deptart dict : update) {
					dict.update();
				}

				for (Deptart dict : insert) {
					dict.setDepartid(dict.newId() + "");
					dict.insert();
				}
				return AjaxMsg.ok();
			}
		});

	}
	
	@ResponseBody
	@RequestMapping("export")
	public void export(HttpServletRequest request, HttpServletResponse response) {
		JSONArray list = ZmZbsjkUtil.geneListDept();
		if(list.size() > 0){
			HSSFWorkbook wb = new HSSFWorkbook(); 
			HSSFSheet sheet = wb.createSheet("科室信息");
			HSSFRow row = sheet.createRow((int) 0);
			HSSFCellStyle style = wb.createCellStyle(); 
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			HSSFCell cell = row.createCell((short) 0);
			cell.setCellValue("序列");  
			cell.setCellStyle(style);  
			cell = row.createCell((short) 1); 
			cell.setCellValue("科室编码");  
			cell.setCellStyle(style);  
			cell = row.createCell((short) 2); 
			cell.setCellValue("科室名称");  
			cell.setCellStyle(style);  
			int num = 0;
			for(int i = 0; i < list.size(); i++){
				num++;
				row = sheet.createRow((int) num);
				JSONObject a1 = (JSONObject)list.get(i);
				row.createCell((short) 0).setCellValue(a1.getString("departid"));	
				row.createCell((short) 1).setCellValue(a1.getString("departopcode"));	
				row.createCell((short) 2).setCellValue(a1.getString("department"));	
			}
			String fileName = "科室信息";
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

}
