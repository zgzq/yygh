/**
 ** create by code gen .
 **/
package com.wx.ad.dbo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import my.dao.annotation.Column;
import my.dao.annotation.DateFormat;
import my.dao.annotation.Name;
import my.dao.annotation.PK;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;
import my.dao.po.BasePo;
import my.web.IUser;
import my.web.RequestContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Table("SYS_USER")
@View("SYS_USER")
@PK({ "ID" })
public class SysUser extends BasePo implements IUser {

	/**
* 
*/	
	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory.getLogger(SysUser.class);

	public static final SysUser INSTANCE = new SysUser();

	private static final String G_USER = "user";

	@Name
	@Column(value = "id", type = ColumnType.STRING)
	private String id;

	@Column(value = "username", type = ColumnType.STRING)
	private String username;

	@Column(value = "wxcount", type = ColumnType.STRING)
	private String wxcount;

	@Column(value = "realname", type = ColumnType.STRING)
	private String realname;

	@Column(value = "userpassword", type = ColumnType.STRING)
	private String userpassword;

	@Column(value = "phone", type = ColumnType.STRING)
	private String phone;

	@Column(value = "status", type = ColumnType.NUMBER)
	private Integer status;
	
	@Column(value = "sex", type = ColumnType.NUMBER)
	private String sex;
	
	@Column(value = "mobile", type = ColumnType.STRING)
	private String mobile;
	
	@Column(value = "wexno", type = ColumnType.STRING)
	private String wexno;
	
	@Column(value = "depts", type = ColumnType.STRING)
	private String depts;
	
	@Column(value = "memo", type = ColumnType.STRING)
	private String memo;
	
	@Column(value = "mail", type = ColumnType.STRING)
	private String mail;
	
	@Column(value = "postion", type = ColumnType.STRING)
	private String postion;
	
	@Column(value = "orgid", type = ColumnType.NUMBER)
	private String orgid;
	
	@Column(value = "usertype", type = ColumnType.NUMBER)
	private String usertype;
	
	@Column(value = "issend", type = ColumnType.NUMBER)
	private String issend;
	
	
	public String getIssend() {
		return issend;
	}

	public void setIssend(String issend) {
		this.issend = issend;
	}

	@Column(value = "plantypes", type = ColumnType.STRING)
	private String plantypes;

	@Override
	public boolean cachedByName() {
		return true;
	}
	
	public String getPlantypes() {
		return plantypes;
	}

	public void setPlantypes(String plantypes) {
		this.plantypes = plantypes;
	}

	public String getDepts() {
		return depts;
	}

	public void setDepts(String depts) {
		this.depts = depts;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getWxcount() {
		return wxcount;
	}

	public void setWxcount(String wxcount) {
		this.wxcount = wxcount;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getWexno() {
		return wexno;
	}

	public void setWexno(String wexno) {
		this.wexno = wexno;
	}


	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPostion() {
		return postion;
	}

	public void setPostion(String postion) {
		this.postion = postion;
	}

	public String getCredate() {
		return credate;
	}

	public void setCredate(String credate) {
		this.credate = credate;
	}

	@DateFormat("yyyy-MM-dd")
	@Column(value = "credate", type = ColumnType.DATE)
	private String credate;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String getUserName() {
		return username;
	}

	@Override
	public String getUserXm() {
		return realname;
	}

	@Override
	public String getRoleNames() {
		return "";
	}

	@Override
	public String getUserId() {
		return id;
	}

	public static SysUser GetLoginUser() {
		HttpServletRequest req = RequestContext.get().getRequest();
		SysUser loginUser = (SysUser) req.getAttribute(G_USER);
		if (loginUser == null) {
			IUser cookie_user = RequestContext.get().getUserFromCookie();
			if (cookie_user == null)
				return null;
			SysUser user = SysUser.INSTANCE.loadByName(cookie_user.getUserId());
			if (user != null) {
				req.setAttribute(G_USER, user);
				return user;
			}
		} else {
			logger.debug("find user in req attribute");
		}
		return loginUser;
	}

	public boolean IsAdmin() {
		return "-1".equals(id);
	}

	/**
	 * 用户的所有角色
	 * 
	 * @return
	 */
	public List<Role> roles() {
		return Role.INSTANCE
				.query(" roleid in (select roleid from sys_USER_ROLE where userid = ? )",
						getId());
	}
	
	private boolean hasRole(String roleid){
		List<Role> roles = roles();
		for(Role r:roles){
			if(r.getId().equals(roleid)){
				return true;
			}
		}
		
		return false;
	}
	

	/**
	 * 保存用户对应的角色。
	 * 
	 * @param roleids
	 */
	public void saveRoles(List<String> roleids) {
		// 清除用户角色关系缓存
		Role.INSTANCE.evictAll(Role.INSTANCE.cacheRegion());
		// 清除用户权限缓存
		evictRights();

		UserRole.INSTANCE.save(roleids, this.id);
	}

	public void evictRights() {
		evict(rightCacheRegion(), rightCacheKey());
	}

	public void evictAllRights() {
		evictAll(rightCacheRegion());
	}

	public String roleListCacheKey() {
		return "rolelist#" + getId();
	}

	/**
	 * 获得该用户的所有权限，注意缓存
	 * 
	 * @return
	 */
	public Map<String, Set<String>> rights() {
		@SuppressWarnings("unchecked")
		Map<String, Set<String>> rights = (Map<String, Set<String>>) fromCache(
				rightCacheRegion(), rightCacheKey());
		if (rights == null) {
			rights = new TreeMap<String, Set<String>>();
			for (Role role : roles()) {
				List<RoleAp> aps = RoleAp.INSTANCE.query("roleid=?", role.getId());
				for (RoleAp ap : aps) {
					String menucode = ap.menu().getId();
					String[] actionset =new String[]{};
					if (ap.getActionset()!=null){
						actionset=ap.getActionset().trim().split(",");
					}
					
					Set<String> actions = rights.get(menucode);
					if (actions == null) {
						actions = new LinkedHashSet<String>();
						rights.put(menucode, actions);
					}
					for (String s : actionset) {
						actions.add(s);
					}
				}
			}
			cache(rightCacheRegion(), rightCacheKey(), (Serializable) rights);
		}
		return rights;
	}

	private String rightCacheRegion() {
		return "userright";
	}

	private String rightCacheKey() {
		return "#" + id;
	}

	public List<SysMenu> menus() {
		List<SysMenu> cached = SysMenu.INSTANCE.loadAll();

		List<SysMenu> menus = new ArrayList<SysMenu>();
		for (SysMenu m : cached) {
			menus.add(new SysMenu(m));
		}
		if (!IsAdmin()) {
			Map<String, Set<String>> map = rights();

			Iterator<SysMenu> it = menus.iterator();
			while (it.hasNext()) {
				SysMenu m = it.next();
				if (!map.containsKey(m.getId())) {
					it.remove();
				}
			}
		}
		return buildTree(menus);
	}

	private List<SysMenu> buildTree(List<SysMenu> menus) {
		List<SysMenu> tree = new ArrayList<SysMenu>();
		tree.addAll(menus);
		return tree;
	}

	public String getText() {
		return realname;
	}

	public String getValue() {
		return id;
	}

	public static String currentUserMenuData() {
		SysUser user = GetLoginUser();
		StringBuffer sb = new StringBuffer();
		for (SysMenu menu : user.menus()) {
			if (sb.length() > 0) {
				sb.append(",");
			}
			sb.append(tojson(menu));

		}

		sb.insert(0, "var data=[");
		sb.append("]");

		return sb.toString();
	}

	private static Object tojson(SysMenu menu) {

		StringBuffer sb = new StringBuffer();
		sb.append("{\n");
		sb.append(String.format("id: \"%s\",\n", menu.getId()));
		if (menu.getPid() == null || menu.getPid().length() > 0) {
			sb.append(String.format("pid: \"%s\",\n", menu.getPid()));
		}

		if (RequestContext.get().getRequest().getLocale() == Locale.CHINA) {
			sb.append(String.format("text: \"%s\",\n", menu.getText()));
		} else {
			sb.append(String.format("text: \"%s\",\n", menu.getTexten()));
		}

		if (menu.getUrl() == null && menu.getId().length() == 4) {
			sb.append("expanded: false\n");
		} else if(menu.getUrl()!=null){
			sb.append(String.format("url: WEB_ROOT+\"%s\"\n", menu.getUrl()));
		}else if(menu.getUrl()==null){
			sb.append("expanded: false\n");
		}
		sb.append("}");
		return sb.toString();
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	

	 
	
}
