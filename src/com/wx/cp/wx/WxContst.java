package com.wx.cp.wx;

public class WxContst {
	//测试用 普通管理组-集团运营数据组 误删!
	//https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=wxdbf8b9ea7e88efc3&corpsecret=poX33JJGT20xKEFSW7F4wdz1eqNU7FBAo6eqr17u6disawMNQJNFiCFb5YYfoD4r
	public static final String CorpID="wxdbf8b9ea7e88efc3";//企业号id   wxdbf8b9ea7e88efc3
	public static final String CorpSecret="poX33JJGT20xKEFSW7F4wdz1eqNU7FBAo6eqr17u6disawMNQJNFiCFb5YYfoD4r";//企业号id
	
	public static final String AESKEY = "5uLXWme29w7unqfMRyv4Ocad3bR62GM6vCBk2Db55tt";// EncodingAESKey
	public static final String TOKEN = "mHUqUASkXVQ853wVRJF1";
	
	public static final Integer AgentId = 1;
	//public static final String APPID = "wx78220924c771f3e3";
	
	public static final String url="http://zmsj.hnymyy.com.cn:8888/wxcp/qyreq";
	public static final String urlVlan="http://10.0.42.223/wxcp/qyreq";
	

	public static final String urlRoot="http://zmsj.hnymyy.com.cn:8888/wxcp";
	public static final String urlVlanRoot="http://10.0.42.223/wxcp";
	
	public static void main(String[] args) {
		System.out.println(AESKEY.length());
	}
}
	
