package my.util;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.wx.ad.dbo.Branch;
import com.wx.ad.dbo.Dict;
import com.wx.ad.dbo.UsrDept;
import com.wx.ad.dbo.UsrOrg;

public class Fn {
	public static String toUpper(String s) {
		return s.toUpperCase();
	}

	public static String json(Object obj) {
		return JSON.toJSONString(obj);
	}

	public static String dictJson(String dict) {
		List<Dict> querySub = Dict.INSTANCE.querySub(dict);
		return json(querySub);
	}

	public static String branchJson() {
		List<Branch> companys = Branch.INSTANCE.query("pid is not null order by code");
		return json(companys);
	}
	public static String orgJson() {
		List<UsrOrg> companys = UsrOrg.INSTANCE.query("");
		return json(companys);
	}
	public static String deptJson() {
		List<UsrDept> companys = UsrDept.INSTANCE.query("");
		return json(companys);
	}
	


}
