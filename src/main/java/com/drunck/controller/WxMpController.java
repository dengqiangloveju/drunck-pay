package com.drunck.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drunck.utils.CommonUtil;
import com.drunck.utils.ResultInfo;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;

@Controller
@RequestMapping("weixin")
public class WxMpController {
	@Resource
	private WxMpService wxMpService;

	@RequestMapping("check")
	public void list(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);

		String signature = request.getParameter("signature");
		String nonce = request.getParameter("nonce");
		String timestamp = request.getParameter("timestamp");
		String echostr = request.getParameter("echostr");

		// 消息签名不正确，说明不是公众平台发过来的消息
		if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
			response.getWriter().println("非法请求");
			return;
		}

		// 说明是一个仅仅用来验证的请求，回显echostr
		if (CommonUtil.isNotEmpty(echostr)) {
			response.getWriter().println(echostr);
			return;
		}

		response.getWriter().println("不可识别的加密类型");
		return;
	}
	
	@RequestMapping("erWeiMa")
	@ResponseBody
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
}
