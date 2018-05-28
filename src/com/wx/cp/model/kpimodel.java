package com.wx.cp.model;

import java.util.ArrayList;
import java.util.List;

public class kpimodel {
	private String code;// 方案id
	private String name;
	private String psdate;
	private String pedate;

	private String userid;// 用户ID
	private String orgid;
	private String org_pic;

	public String getOrg_pic() {
		return org_pic;
	}

	public void setOrg_pic(String org_pic) {
		this.org_pic = org_pic;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPsdate() {
		return psdate;
	}

	public void setPsdate(String psdate) {
		this.psdate = psdate;
	}

	public String getPedate() {
		return pedate;
	}

	public void setPedate(String pedate) {
		this.pedate = pedate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	List<kpivalue> lks = null;

	public List<KpidictValue> getKpivalue() {
		// 查询方案对应的项目
		List<KpidictValue> list = new ArrayList<KpidictValue>();

		if (code.equals("13")) {
			// 中美重点科室指标先返回科室信息,先查询重点科室，再后续页面再查询指标数值
			KpidictValue value3 = new KpidictValue();
			value3.setDictname("消化科");
			value3.setKpivalue("xhk");
			value3.setPlanid(code);
			value3.setPdate(psdate);
			list.add(value3);
			KpidictValue value4 = new KpidictValue();
			value4.setDictname("妇科");
			value4.setKpivalue("fk");
			value4.setPlanid(code);
			value4.setPdate(psdate);
			list.add(value4);
			KpidictValue value5 = new KpidictValue();
			value5.setDictname("骨科");
			value5.setKpivalue("gk");
			value5.setPlanid(code);
			value5.setPdate(psdate);
			list.add(value5);
			KpidictValue value6 = new KpidictValue();
			value6.setDictname("心内科");
			value6.setKpivalue("xnk");
			value6.setPlanid(code);
			value6.setPdate(psdate);
			list.add(value6);
			KpidictValue value7 = new KpidictValue();
			value7.setDictname("肿瘤科");
			value7.setKpivalue("zlk");
			value7.setPlanid(code);
			value7.setPdate(psdate);
			list.add(value7);
			KpidictValue value8 = new KpidictValue();
			value8.setDictname("儿科");
			value8.setKpivalue("ek");
			value8.setPlanid(code);
			value8.setPdate(psdate);
			list.add(value8);
			KpidictValue value9 = new KpidictValue();
			value9.setDictname("产科");
			value9.setKpivalue("ck");
			value9.setPlanid(code);
			value9.setPdate(psdate);
			list.add(value9);
		} 
		else if (code.equals("81")) {
			List<JmyyDept> jmyyDepts= JmyyDept.INSTANCE.query("1=1");
			for (JmyyDept jmyyDept : jmyyDepts) {
				list.add(new KpidictValue(jmyyDept.getDeptname(), psdate, code, jmyyDept.getDeptname()));
			}
		}
		else {
			list = KpidictValue.INSTANCE.query(" planid=? and pdate=? order by korder", code,
					psdate);
			// 不是中美科室指标那么展现方式不一样，统一处理,通过存储过程查询指标的数值
		}
		// else if (code.equals("2")) {
		// // 查询中美科室指标
		//
		// kpivalue value3 = new kpivalue();
		// value3.setName("消化科");
		// value3.setValue("XHK");
		// list.add(value3);
		// kpivalue value4 = new kpivalue();
		// value4.setName("妇科");
		// value4.setValue("FK");
		// list.add(value4);
		// kpivalue value5 = new kpivalue();
		// value5.setName("骨科");
		// value5.setValue("GK");
		// list.add(value5);
		// kpivalue value6 = new kpivalue();
		// value6.setName("心内科");
		// value6.setValue("XNK");
		// list.add(value6);
		// kpivalue value7 = new kpivalue();
		// value7.setName("肿瘤科");
		// value7.setValue("ZLK");
		// list.add(value7);
		//
		// } else if (code.equals("3")) {
		// // 查询誉美指标
		// Ym ym = Ym.INSTANCE.queryOne("to_char(uploaddate,'yyyy-mm-dd') =?",
		// pdate);
		// if(ym==null){
		// return list;
		// }
		// kpivalue value2 = new kpivalue();
		// value2.setName("总收入(毛收入)");
		// value2.setValue(ym.getTotalincome());
		// list.add(value2);
		// kpivalue value3 = new kpivalue();
		// value3.setName("总利润");
		// value3.setValue(ym.getTotalprofit());
		// list.add(value3);
		// kpivalue value4 = new kpivalue();
		// value4.setName("带药收入");
		// value4.setValue(ym.getTakedrugincome());
		// list.add(value4);
		// kpivalue value5 = new kpivalue();
		// value5.setName("带药门诊量");
		// value5.setValue(ym.getTakedrugclinic());
		// list.add(value5);
		// kpivalue value6 = new kpivalue();
		// value6.setName("血透收入");
		// value6.setValue(ym.getHemodialysisincome());
		// list.add(value6);
		// kpivalue value7 = new kpivalue();
		// value7.setName("血透量");
		// value7.setValue(ym.getHemodialysisnum());
		// list.add(value7);
		// kpivalue value8 = new kpivalue();
		// value8.setName("总门诊量");
		// value8.setValue(ym.getTotalclinicnum());
		// list.add(value8);
		// kpivalue value9 = new kpivalue();
		// value9.setName("总入院量");
		// value9.setValue(ym.getTohospitalnum());
		// list.add(value9);
		//
		// kpivalue value91 = new kpivalue();
		// value91.setName("总出院量");
		// value91.setValue(ym.getOuthospitalnum());
		// list.add(value91);
		//
		// kpivalue value10 = new kpivalue();
		// value10.setName("实际占床日数");
		// value10.setValue(ym.getInbedays());
		// list.add(value10);
		// kpivalue value11 = new kpivalue();
		// value11.setName("次均门诊费用");
		// value11.setValue(ym.getTimeclinicincome());
		// list.add(value11);
		// kpivalue value12 = new kpivalue();
		// value12.setName("人均住院费用");
		// value12.setValue(ym.getPersonhospitalincome());
		// list.add(value12);
		//
		// kpivalue value13 = new kpivalue();
		// value13.setName("平均住院天数");
		// value13.setValue(ym.getAverageinhospitaldays());
		// list.add(value13);
		//
		// kpivalue value14 = new kpivalue();
		// value14.setName("实际开放床日数");
		// value14.setValue(ym.getOpenbeddays());
		// list.add(value14);
		//
		// kpivalue value15 = new kpivalue();
		// value15.setName("床位使用率");
		// value15.setValue(ym.getBedrate());
		// list.add(value15);
		//
		// } else if (code.equals("4")) {
		// // 查询誉美科室指标
		// YmDept ymdept = YmDept.INSTANCE.queryOne(
		// "to_char(uploaddate,'yyyy-mm-dd') =?", pdate);
		// if(ymdept==null){
		// return list;
		// }
		// kpivalue value2 = new kpivalue();
		// value2.setName("肾病专科收入");
		// value2.setValue(ymdept.getTotalincome());
		// list.add(value2);
		// kpivalue value3 = new kpivalue();
		// value3.setName("肾病门诊收入");
		// value3.setValue(ymdept.getClinicincome());
		// list.add(value3);
		// kpivalue value4 = new kpivalue();
		// value4.setName("肾病住院收入");
		// value4.setValue(ymdept.getHospitalincome());
		// list.add(value4);
		// kpivalue value5 = new kpivalue();
		// value5.setName("肾病门诊量");
		// value5.setValue(ymdept.getTotalclinicnum());
		// list.add(value5);
		// kpivalue value6 = new kpivalue();
		// value6.setName("肾病门诊首诊");
		// value6.setValue(ymdept.getClinicfirstnum());
		// list.add(value6);
		// kpivalue value7 = new kpivalue();
		// value7.setName("肾病门诊复诊");
		// value7.setValue(ymdept.getClinicsecnum());
		// list.add(value7);
		// kpivalue value8 = new kpivalue();
		// value8.setName("肾病入院量");
		// value8.setValue(ymdept.getTotaltohospitalnum());
		// list.add(value8);
		// kpivalue value9 = new kpivalue();
		// value9.setName("肾病入院量首诊");
		// value9.setValue(ymdept.getTohospitalnumfirst());
		// list.add(value9);
		// kpivalue value10 = new kpivalue();
		// value10.setName("肾病入院量复诊");
		// value10.setValue(ymdept.getTohospitalnumsec());
		// list.add(value10);
		// kpivalue value11 = new kpivalue();
		// value11.setName("肾病出院量");
		// value11.setValue(ymdept.getTotalouthospitalnum());
		// list.add(value11);
		// kpivalue value12 = new kpivalue();
		// value12.setName("肾病出院量首诊");
		// value12.setValue(ymdept.getOuthospitalnumfirst());
		// list.add(value12);
		// kpivalue value13 = new kpivalue();
		// value13.setName("肾病出院量复诊");
		// value13.setValue(ymdept.getOuthospitalnumsec());
		// list.add(value13);
		// kpivalue value14 = new kpivalue();
		// value14.setName("肾病实际占床日数");
		// value14.setValue(ymdept.getInbeddays());
		// list.add(value14);
		// kpivalue value15 = new kpivalue();
		// value15.setName("肾病次均门诊费用");
		// value15.setValue(ymdept.getTimeclinicincome());
		// list.add(value15);
		// kpivalue value16 = new kpivalue();
		// value16.setName("肾病人均住院费用");
		// value16.setValue(ymdept.getPersonhospitalincome());
		// list.add(value16);
		// kpivalue value17 = new kpivalue();
		// value17.setName("肾病人均住院费用首诊");
		// value17.setValue(ymdept.getPersonhospitalincomefirst());
		// list.add(value17);
		// kpivalue value18 = new kpivalue();
		// value18.setName("肾病人均住院费用复诊");
		// value18.setValue(ymdept.getPersonhospitalincomesec());
		// list.add(value18);
		// kpivalue value19 = new kpivalue();
		// value19.setName("肾病平均住院天数");
		// value19.setValue(ymdept.getAverageinhospitaldays());
		// list.add(value19);
		// kpivalue value20 = new kpivalue();
		// value20.setName("肾病平均住院天数首诊");
		// value20.setValue(ymdept.getAverageinhospitaldaysfirst());
		// list.add(value20);
		// kpivalue value21 = new kpivalue();
		// value21.setName("肾病平均住院天数复诊");
		// value21.setValue(ymdept.getAverageinhospitaldayssec());
		// list.add(value21);
		// kpivalue value22 = new kpivalue();
		// value22.setName("收住院率");
		// value22.setValue(ymdept.getHospitalrate());
		// list.add(value22);
		// kpivalue value23 = new kpivalue();
		// value23.setName("收住院率首诊");
		// value23.setValue(ymdept.getHospitalratefirst());
		// list.add(value23);
		// kpivalue value24 = new kpivalue();
		// value24.setName("收住院率复诊");
		// value24.setValue(ymdept.getHospitalratesec());
		// list.add(value24);
		//
		// kpivalue value25 = new kpivalue();
		// value25.setName("实际开放床日数");
		// value25.setValue(ymdept.getOpendays());
		// list.add(value25);
		//
		// kpivalue value26 = new kpivalue();
		// value26.setName("床位使用率");
		// value26.setValue(ymdept.getBedrate());
		// list.add(value26);
		//
		//
		// }

		return list;
	}

}
