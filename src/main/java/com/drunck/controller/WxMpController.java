package com.drunck.controller;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.drunck.utils.CommonUtil;
import com.drunck.utils.ResultInfo;
import com.google.gson.JsonObject;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.material.WxMpMaterial;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNews;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialUploadResult;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;

@RestController
@RequestMapping("weixin")
public class WxMpController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private WxMpService wxMpService;
	@Resource
	private WxMpMessageRouter router;

	@RequestMapping(value="check", method=RequestMethod.GET, produces="text/plain;charset=utf-8")
	public String get(String signature, String timestamp, String nonce, String echostr, HttpServletResponse response) throws IOException {
		this.logger.info("\n接收到来自微信服务器的认证消息：[{}, {}, {}, {}]", signature, timestamp, nonce, echostr);
		
		// 消息签名不正确，说明不是公众平台发过来的消息
		if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
			return "请求参数非法，请核实!";
		}

		// 说明是一个仅仅用来验证的请求，回显echostr
		if (CommonUtil.isNotEmpty(echostr)) {
			//response.getWriter().println(echostr);
			return echostr;
		}
		
		return "非法请求";
	}
	
	@RequestMapping(value="check", method=RequestMethod.POST, produces="application/xml; charset=UTF-8")
	public String post(@RequestBody String requestBody, String signature, String timestamp, String nonce, 
			@RequestParam(name = "encrypt_type", required = false) String encType, 
			@RequestParam(name = "msg_signature", required = false) String msgSignature, 
			HttpServletResponse response) throws IOException {
		
		this.logger.info("\n接收微信请求：[signature=[{}], encType=[{}], msgSignature=[{}],"
		            + " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
		        signature, encType, msgSignature, timestamp, nonce, requestBody);

		// 消息签名不正确，说明不是公众平台发过来的消息
		if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
			return "非法请求，可能属于伪造的请求!";
		}

		String out = null;
		if (encType == null) {
			// 明文传输的消息
			WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);
			WxMpXmlOutMessage outMessage = route(inMessage);
			if (outMessage == null) {
				return "";
			}

			out = outMessage.toXml();
		} else if ("aes".equals(encType)) {
			// aes加密的消息
			WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(requestBody, wxMpService.getWxMpConfigStorage(), timestamp, nonce, msgSignature);
			this.logger.debug("\n消息解密后内容为：\n{} ", inMessage.toString());
			WxMpXmlOutMessage outMessage = this.route(inMessage);
			if (outMessage == null) {
				return "";
			}

			out = outMessage.toEncryptedXml(this.wxMpService.getWxMpConfigStorage());
		}

		this.logger.debug("\n组装回复信息：{}", out);
		
		return out;
	}
	
	private WxMpXmlOutMessage route(WxMpXmlMessage message) {
		try {
			return router.route(message);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return null;
	}

	@RequestMapping("erWeiMa")
	public ResultInfo createJob() throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		// 临时ticket
		WxMpQrCodeTicket ticket = wxMpService.getQrcodeService().qrCodeCreateTmpTicket("test", 60);
		File file = wxMpService.getQrcodeService().qrCodePicture(ticket);

		String url = file.getPath();

		resultInfo.setStatus(true);
		resultInfo.setMsg(url);

		return resultInfo;
	}
	
	@RequestMapping("singleMaterial")
	public ResultInfo tempMaterial() throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		
		File file = new File("E:\\mmm.jpg");
		WxMpMaterial wxMaterial = new WxMpMaterial();
		wxMaterial.setFile(file);
	    wxMaterial.setName("mmm.jpg");
	    WxMpMaterialUploadResult res = wxMpService.getMaterialService().materialFileUpload(WxConsts.MediaFileType.IMAGE, wxMaterial);
	    
	    System.out.println("-----------------------------------");
	    System.out.println(JSONObject.toJSONString(res));
		
		// 单图文消息
	    WxMpMaterialNews wxMpMaterialNewsSingle = new WxMpMaterialNews();
	    WxMpMaterialNews.WxMpMaterialNewsArticle article = new WxMpMaterialNews.WxMpMaterialNewsArticle();
	    article.setAuthor("author");
	    article.setThumbMediaId(res.getMediaId());
	    article.setTitle("single title");
	    article.setContent("single content");
	    article.setContentSourceUrl("content url");
	    article.setShowCoverPic(true);
	    article.setDigest("single news");
	    wxMpMaterialNewsSingle.addArticle(article);

		resultInfo.setStatus(true);
		resultInfo.setMsg(JSONObject.toJSONString(wxMpMaterialNewsSingle));

		return resultInfo;
	}
}
