package com.wx.ad.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.web.AjaxMsg;
import my.web.BaseController;
import my.web.IUser;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.wx.ad.bean.GridDataModel;
import com.wx.ad.dbo.Role;
import com.wx.ad.dbo.SysUser;
import com.wx.ad.util.PageFactory;

@Controller
public class AdminHome extends BaseController {

	@RequestMapping("/admin")
	public String index(Model m) {
		return "admin/login";
	}


	@RequestMapping("/home")
	public String main(Model m) {
		m.addAttribute("user", com.wx.ad.dbo.SysUser.GetLoginUser());
		return "admin/main";
	}

	@RequestMapping("/admin/login")
	public String login(Model m) {
		return "admin/login";
	}

	@RequestMapping("/logout")
	public String logout(Model m) {
		deleteUserInCookie();
		return "admin/login";
	}
	
	@RequestMapping("/sys/desktop")
	public String desktop(Model m) {
		return "admin/desktop";
	}

	@RequestMapping("/sys/upload")
	public String upload(Model m) {
		m.addAttribute("action", param("action", ""));
		return "admin/common/upload";
	}

	@ResponseBody
	@RequestMapping("/sys/modifypassword")
	public AjaxMsg modifypassword(Model m) {
		return run(new CallBack() {

			@Override
			public AjaxMsg call() throws Exception {
				String oldpassword = param("oldpassword", "");
				String newpassword = param("newpassword", "");
				String newpassword2 = param("newpassword2", "");

				if (!newpassword.equals(newpassword2)) {
					return AjaxMsg.error(i18n("NEW_PASSWORD_ERROR"));
				}

				IUser user = getLoginUer();

				com.wx.ad.dbo.SysUser sysuser = com.wx.ad.dbo.SysUser.INSTANCE.loadByName(user.getUserId());
				if (sysuser.getUserpassword().equals(oldpassword)) {
					sysuser.setUserpassword(newpassword);
					sysuser.updateField("userpassword", newpassword);
				} else {
					return AjaxMsg.error(i18n("OLD_PASSWORD_ERROR"));
				}

				return AjaxMsg.ok();
			}
		});
	}
	@ResponseBody
	@RequestMapping("/admin/validpassword")
	public AjaxMsg validpassword(Model m) {
		return run(new CallBack() {
			@Override
			public AjaxMsg call() throws Exception {
				IUser user = getLoginUer();
				com.wx.ad.dbo.SysUser sysuser = com.wx.ad.dbo.SysUser.INSTANCE.loadByName(user.getUserId());
				if (sysuser.getUserpassword().equals("123")) {
					return AjaxMsg.error(i18n("OLD_PASSWORD_ERROR"));
				} else {
					return AjaxMsg.ok();
				}
			}
		});
	}
	
	@ResponseBody
	@RequestMapping("/sys/role/listData")
	public GridDataModel<Role> listData() {
		String filter = "1 = ? ";
		return PageFactory.newPage(Role.class, filter, "1");
	}

	@RequestMapping("/dologin")
	public String dologin(final Model map) {
		return run(new StrCallBack() {
			@Override
			public String call() throws Exception {
				String username = param("username", "");
				String password = param("password", "");

				SysUser user = SysUser.INSTANCE.queryOne("username=?", username);
				if (user == null) {
					map.addAttribute("msg", i18n("LOGIN_USER_NOT_EXIST"));
					return "admin/login";
				} else if (!password.equals(user.getUserpassword())) {
					map.addAttribute("msg", i18n("LOGIN_PASSWORD_ERROR"));
					return "admin/login";
				} else {
					saveUserInCookie(user, true);
					map.addAttribute("user", user);
					return "admin/main";
				}
			}
		}, map);
	}
	

	@RequestMapping("/doupload")
	public String upload(@RequestParam("file") MultipartFile photoFile,Model m){
		String root = getRequest().getSession().getServletContext()
				.getRealPath("/");
		String name = photoFile.getOriginalFilename();
		int index  = name.lastIndexOf(".");
		String ext = name.substring(index);
		String uuid = UUID.randomUUID().toString()+ext;
		File target = new File(new File(root),"image/"+uuid);
//		target.getParentFile().mkdirs();
		try {
			photoFile.transferTo(target);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		m.addAttribute("imgpath", "/image/"+uuid);
		
		return "admin/upload/upload";
	}
	
	@RequestMapping("/main/ke/uploadjson")
	public ModelAndView uploadjson(@RequestParam("imgFile") MultipartFile item) {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		PrintWriter out = null;
		try {
			String savePath = request.getSession().getServletContext()
					.getRealPath("/")
					+ "attached/";

			File f = new File(savePath);
			f.mkdirs();

			// 文件保存目录URL
			String saveUrl = request.getContextPath() + "/attached/";

			// 定义允许上传的文件扩展名
			HashMap<String, String> extMap = new HashMap<String, String>();
			extMap.put("image", "gif,jpg,jpeg,png,bmp");
			extMap.put("flash", "swf,flv");
			extMap.put("media",
					"swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
			extMap.put("file",
					"doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

			// 最大文件大小
			long maxSize = 200000000;

			response.setContentType("text/html; charset=UTF-8");
			out = response.getWriter();

			if (!ServletFileUpload.isMultipartContent(request)) {
				out.println(getError("请选择文件。"));
				return null;
			}
			// 检查目录
			File uploadDir = new File(savePath);
			if (!uploadDir.isDirectory()) {
				out.println(getError("上传目录不存在。"));
				return null;
			}
			// 检查目录写权限
			if (!uploadDir.canWrite()) {
				out.println(getError("上传目录没有写权限。"));
				return null;
			}

			String dirName = request.getParameter("dir");
			if (dirName == null) {
				dirName = "image";
			}
			if (!extMap.containsKey(dirName)) {
				out.println(getError("目录名不正确。"));
				return null;
			}
			// 创建文件夹
			savePath += dirName + "/";
			saveUrl += dirName + "/";
			File saveDirFile = new File(savePath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String ymd = sdf.format(new Date());
			savePath += ymd + "/";
			saveUrl += ymd + "/";
			File dirFile = new File(savePath);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}

			String fileName = item.getOriginalFilename();
			long fileSize = item.getSize();
			if (fileSize > maxSize) {
				out.println(getError("上传文件大小超过限制。"));
				return null;
			}

			String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1)
					.toLowerCase();
			if (!Arrays.<String> asList(extMap.get(dirName).split(","))
					.contains(fileExt)) {
				out.println(getError("上传文件扩展名是不允许的扩展名。\n只允许"
						+ extMap.get(dirName) + "格式。"));
				return null;
			}

			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			String newFileName = df.format(new Date()) + "_"
					+ new Random().nextInt(1000) + "." + fileExt;
			try {
				File uploadedFile = new File(savePath, newFileName);
				item.transferTo(uploadedFile);
			} catch (Exception e) {
				out.println(getError("上传文件失败。"));
				return null;
			}

			JSONObject obj = new JSONObject();
			obj.put("error", 0);
			obj.put("url", saveUrl + newFileName);
			out.println(obj.toJSONString());

			return null;
		} catch (Exception e) {
			try {
				out.println(getError(e.getMessage()));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
		return null;
	}
	
	private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}
}
