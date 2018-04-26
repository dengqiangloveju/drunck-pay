package com.drunck.handler;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

import org.springframework.stereotype.Component;

import com.drunck.service.WxMessageService;
import com.drunck.service.WxUserService;

import java.util.Map;

import javax.annotation.Resource;

@Component
public class UnsubscribeHandler extends AbstractHandler {
	@Resource
	private WxMessageService wxMessageService;
	@Resource
	private WxUserService wxUserService;

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, 
			WxMpService weixinService, WxSessionManager sessionManager) {
		wxMessageService.unSubscribe(wxMessage);
		handleSpecial(wxMessage, weixinService);
		return null;
	}

	/**
	 * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
	 */
	private void handleSpecial(WxMpXmlMessage wxMessage, WxMpService weixinService) {
		try {
			// 获取微信用户基本信息
			WxMpUser userWxInfo = weixinService.getUserService().userInfo(wxMessage.getFromUser(), null);
			if (userWxInfo != null) {
				wxUserService.unSubscribe(userWxInfo);
			}
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
	}

}
