package com.wx.cp.wx.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import me.chanjar.weixin.cp.bean.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.WxCpXmlOutMessage;
import my.web.RequestContext;
import my.wx.mp.aes.WXBizMsgCrypt;
import my.wx.mp.util.SimpleHttpReq;
import my.wx.mp.web.WxSpringSupport;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wx.cp.wx.WxContst;
import com.wx.cp.wx.WxCp;

@Controller
@RequestMapping("/wx")
@Scope("prototype")
public class Home extends WxSpringSupport {

//	@Override
//	protected void init() {
//		MPAct mpAct = new MPAct();
//		mpAct.setAppId(WxContst.APPID);
//		mpAct.setAppSecert(WxContst.CorpSecret);
//		mpAct.setToken(WxContst.TOKEN);
//		mpAct.setAESKey(WxContst.AESKEY);
//		this.setMpAct(mpAct);
//
//		// 可实现自己的WxHandler
//		this.setWxHandler(new WxHander());
//	}

	/**
	 * 
	 * 企业号
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/qy")
	public ModelAndView qy(HttpServletRequest request) {
		try {
			logger.debug("参数:"+RequestContext.get().params());

			// 微信加密签名
			String msg_signature = request.getParameter("msg_signature");
			// 时间戳
			String timestamp = request.getParameter("timestamp");
			// 随机数
			String nonce = request.getParameter("nonce");
			// 随机字符串
			String echostr = request.getParameter("echostr");

			// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败

			String result = null;

			if (StringUtils.isNotBlank(echostr)) {
				logger.info("echostr:" + echostr);
				try {

					WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(
							WxContst.TOKEN,WxContst.AESKEY,
							WxContst.CorpID);

					result = wxcpt.verifyUrl(msg_signature, timestamp, nonce,
							echostr);

				} catch (Exception e) {
					logger.error("error", e);
				}

				super.printText(result);
				return null;
			} else {
				try {
					WxCpXmlMessage inMessage = WxCpXmlMessage.fromEncryptedXml(
							request.getInputStream(), WxCp.getInstance()
									.getStore(), timestamp, nonce,
							msg_signature);
					logger.info(inMessage.toString());

					WxCpXmlOutMessage outMessage = WxCp.getInstance()
							.getWxCpMessageRouter().route(inMessage);
					if (outMessage != null) {
						getResponse().getWriter().write(
								outMessage.toEncryptedXml(WxCp
										.getInstance().getStore()));
					}else{
						logger.debug("out message is null");
					}

					return null;
				} catch (Exception e) {
					logger.error("error", e);
				}
			}

		} catch (Exception e) {
			logger.error("error", e);
		}
		
		return null;
	}

	/**
	 * 公众号
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/")
	public ModelAndView wxCore(HttpServletRequest req) {
		log.debug(req.getMethod());
		String sign = req.getParameter("signature");
		String time = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echo = req.getParameter("echostr");
		String encrypt = req.getParameter("encrypt_type");
		String msgSign = req.getParameter("msg_signature");
		log.debug(sign);
		log.debug(time);
		log.debug(nonce);
		log.debug(echo);
		log.debug(encrypt);
		log.debug(msgSign);
		String reply = "";
		try {
			reply = wxInteract(req);
		} catch (IOException e) {
			log.error(e.getLocalizedMessage(), e);
		}
		super.printText(reply);
		return null;
	}

	@RequestMapping(value = "/test")
	public ModelAndView test(HttpServletRequest req) {
		Map<String, Cookie> map = RequestContext.get().getCookies();
		StringBuffer sb = new StringBuffer();
		sb.append("cookie:\r\n");
		for (String key : map.keySet()) {
			sb.append("key:" + key + ",value:" + map.get(key).getValue()
					+ "\r\n");
		}
		sb.append("params:\r\n");
		sb.append(RequestContext.get().params());
		log.debug(sb.toString());

		String appid = "";
		String secret = "";
		String code = param("code", "");
		String url = String
				.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code",
						appid, secret, code);

		String result = "";
		try {
			result = SimpleHttpReq.get(url);
		} catch (IOException e) {
			log.error(e.getLocalizedMessage(), e);
		}
		System.out.println(result);
		sb.append(result);
		super.printText(sb.toString());
		return null;
	}

}
