//package com.wx.ad.controller;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import me.chanjar.weixin.cp.api.WxCpServiceImpl;
//import me.chanjar.weixin.cp.bean.WxCpDepart;
//import my.ann.Action;
//import my.ann.Menu;
//import my.web.ActionException;
//import my.web.AjaxMsg;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.alibaba.fastjson.JSON;
//import com.wx.ad.bean.GridDataModel;
//import com.wx.ad.bean.GridSaveModel;
//import com.wx.ad.dbo.Branch;
//import com.wx.ad.util.PageFactory;
//import com.wx.ad.web.AuthController;
//import com.wx.cp.wx.WxCp;
//
//@Menu("组织机构管理")
//@RequestMapping("/sys/org")
//@Controller
//public class BranchController extends AuthController {
//
//	@Action("组织机构管理")
//	@RequestMapping("/list")
//	public String index() {
//		return "admin/branch/list";
//	}
//
//	@ResponseBody
//	@RequestMapping("/treeData")
//	public List<Branch> treeData() {
//		List<Branch> list = Branch.INSTANCE.query("");
//		Branch root = new Branch();
//		if (list.size() == 0 || !list.get(0).getId().equals("0")) {
//			root.setId("0");
//			root.setName("组织机构");
//			list.add(0, root);
//		}
//		return list;
//	}
//	
//	
//	
//	
//
//	@Action("组织机构管理")
//	@ResponseBody
//	@RequestMapping("/gridData")
//	public GridDataModel<Branch> gridData() {
//		String pid = param("pid", "0");
//		String filter = "pid = ? ";
//		return PageFactory.newPage(Branch.class, filter,"", pid);
//	}
//	
//	
//
//	@Action("组织机构管理")
//	@ResponseBody
//	@RequestMapping("/saveGrid")
//	public AjaxMsg gridSave(final @RequestParam("json") String json) {
//
//		return run(new CallBack() {
//
//			@Override
//			public AjaxMsg call() throws Exception {
//				GridSaveModel model = JSON.parseObject(json,
//						GridSaveModel.class);
//
//				List<Branch> insert = model.inserts(Branch.class);
//
//				List<Branch> delete = model.deletes(Branch.class);
//
//				List<Branch> update = model.updates(Branch.class);
//
//				for (Branch comp : delete) {
//					comp.delete();
//				}
//
//				for (Branch comp : update) {
//					
//					Branch old = Branch.INSTANCE.queryOne("code=?", comp.getCode());
//					if(old!=null&&!old.getId().equals(comp.getId())){
//						throw new ActionException("编码不能重复");
//					}
//					comp.update();
//				}
//
//				for (Branch comp : insert) {
//					
//					Branch old = Branch.INSTANCE.queryOne("code=?", comp.getCode());
//					if(old!=null){
//						throw new ActionException("编码不能重复");
//					}
//					comp.setId(String.valueOf(comp.newId()));
//					comp.insert();
//				}
//				return AjaxMsg.ok();
//			}
//		});
//
//	}
//	
//	@Action("组织机构管理")
//	@ResponseBody
//	@RequestMapping("/genWx")
//	public AjaxMsg genWx(final @RequestParam("branchs") String branchs) {
//
//		return run(new CallBack() {
//
//			@Override
//			public AjaxMsg call() throws Exception {
//				if(branchs==null||branchs.length()==0){
//					return AjaxMsg.ok();
//				}
//				WxCpServiceImpl service = WxCp.getInstance().getService();
//				List<WxCpDepart> list=service.departGet();
//				Map<Integer, WxCpDepart> map=new HashMap<Integer, WxCpDepart>();
//				for(WxCpDepart dept:list){
//					map.put(dept.getId(), dept);
//				}
//				String[] us=branchs.split(",");
//				for(int i=0;i<us.length;i++){
//					String id=us[i];
//					Branch br=Branch.INSTANCE.queryOne("id=?", id);
//					//检查微信组织机构是否存在，存在则更新，不存在则插入
//					if(map.containsKey(br.getId())){
//						//已经存在
//						WxCpDepart dp=map.get(br.getId());
//						dp.setName(br.getName());
//						service.departUpdate(dp);
//					}else{
//						//不存在
//						WxCpDepart dp=new WxCpDepart();
//						dp.setId(Integer.valueOf(br.getId()));
//						dp.setName(br.getName());
//						dp.setParentId(Integer.valueOf(br.getPid()));
//						service.departCreate(dp);
//						
//						
//					}
//				}
//
//				return AjaxMsg.ok();
//			}
//		});
//
//	}
//}
