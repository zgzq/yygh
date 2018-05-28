package com.wx.cp.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import my.dao.pool.DBException;
import my.dao.pool.DBManager;
import my.view.VelocityGen;
import my.web.ActionException;
import my.wx.WxConst;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;

import com.wx.ad.dbo.KpiPlan;
import com.wx.ad.dbo.SysUser;
import com.wx.ad.dbo.UsrOrg;
import com.wx.cp.model.kpimodel;
import com.wx.cp.wx.WxContst;

public class VmGen {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final String VM_PATH = "/com/wx/cp/task/vm/";

	public VmGen() {
		Properties p = new Properties();
		try {
			p.load(VmGen.class.getResourceAsStream("velocity.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Velocity.init(p);
	}

	public String getVm(final String userid, final String psdate,
			final String pedate) {
		return run(new StrCallBack() {
			@Override
			public String call() throws Exception {
				String root = ContextLoader.getCurrentWebApplicationContext()
						.getServletContext().getRealPath("/");
				String ret = "";
				VelocityContext context = null;
				SysUser user = SysUser.INSTANCE.queryOne("id=?", userid);
				// 查询中美机构
				List<UsrOrg> olist = null;
				if (user.getUsertype() == null) {
					// 暂时不处理

				} else if (user.getUsertype().equals("1")
						|| user.getUsertype().equals("2")) {
					// 集团行政||集团领导、
					if (user.getUsertype().equals("1")) {
						olist = UsrOrg.INSTANCE.query("id>0 order by id");
					} else {
						// 医院领导
						olist = UsrOrg.INSTANCE.query("id=? order by id",
								user.getOrgid());
					}
					// 生成一级机构页面
					context = new VelocityContext();

					String filename1 = genFilename(userid, "org");
					String filename2 ="";
					for (UsrOrg org : olist) {
						filename2 = genFilename(userid, "zb");
						String orgid = org.getId();
						filename2 = orgid + "-" + filename2;
						String url = WxContst.url+"/static/"
								+ filename2;
						org.setUrl(url);
						//// 处理二级页面
						// 查询机构下及用户有权限的方案,有几个机构生成几个静态页面
						List<KpiPlan> list = KpiPlan.INSTANCE
								.query("orgid=? and id in (select planid from wx_user_plan where userid=?)",
										org.getId(), userid);
						List<kpimodel> k = new ArrayList<kpimodel>();
						for (KpiPlan plan : list) {
							kpimodel m = new kpimodel();
							m.setCode(String.valueOf(plan.getId()));
							m.setName(plan.getPlanname());
							m.setPsdate(psdate);// 日报开始日期和结束日期为同一天
							m.setPedate(pedate);
							m.setUserid(userid);
							m.setOrgid(org.getId());
							m.getKpivalue();//处理计算值
							k.add(m);
//							//如果是中美重点科室指标展现形式不一样，那么要每个重点科室生成一个静态页面 悲催、
//							if(plan.getId().longValue()==50){
//								
//							}
							
						}
						VelocityContext context1 = new VelocityContext();
						context1.put("kpis", k);
						context1.put("url", WxContst.url);
						genHtml(context1, filename2, "zbinfo.vm");
					}
					context.put("orgs", olist);
					context.put("url", WxContst.url);
					// 生成html文件,一级
					genHtml(context, filename1, "zmorg.vm");
					ret = filename1;
					
				} else if (user.getUsertype().equals("3")) {
					// 科室主任

				} else if (user.getUsertype().equals("99")) {
					// 其他

				}
				return ret;
			}
		});
	}

	public static String genFilename(String userid, String str) {
		String filename = "";
		filename = String.valueOf(System.currentTimeMillis()) + "-" + userid
				+ "-" + str + ".html";
		return filename;
	}

	public static void genHtml(VelocityContext context, String filename,
			String vmname) {
		String root = ContextLoader.getCurrentWebApplicationContext()
				.getServletContext().getRealPath("/");
		File f = new File(new File(root), "static/" + filename);
		f.getParentFile().mkdirs();
		PrintWriter pw = null;
		Template template = null;
		vmname=VM_PATH+vmname;
		try {
			pw = new PrintWriter(f, "UTF-8");
			template = Velocity.getTemplate(vmname, "UTF-8");
		} catch (ResourceNotFoundException rnfe) {
			System.out
					.println("Example : error : cannot find template model.vm");
		} catch (ParseErrorException pee) {
			System.out.println("Example : Syntax error in template model.vm :"
					+ pee);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (template != null)
			template.merge(context, pw);
		pw.close();
	}

	protected String run(StrCallBack callbak) {
		String msg = null;
		try {
			msg = callbak.call();
			DBManager.commitAll();
		} catch (DBException e) {
			logger.error("error", e);
			DBManager.rollbackAll();
		} catch (ActionException e) {
			logger.error("error", e);
			DBManager.rollbackAll();
		} catch (Exception e) {
			logger.error("error", e);
			DBManager.rollbackAll();
		} finally {
			DBManager.closeAllConnection();
		}
		return msg;
	}

	public static interface StrCallBack {
		public String call() throws Exception;
	}
}
