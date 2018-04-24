package com.drunck.handler;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.drunck.builder.ImageBuilder;
import com.drunck.builder.TextBuilder;
import com.drunck.builder.TwBuilder;

import me.chanjar.weixin.common.api.WxConsts.XmlMsgType;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

@Component
public class MsgHandler extends AbstractHandler {

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService weixinService,
			WxSessionManager sessionManager) {

		if (!wxMessage.getMsgType().equals(XmlMsgType.EVENT)) {
			// TODO 可以选择将消息保存到本地
		}

		String content = wxMessage.getContent();
		if("1".equals(content)) {
			return new ImageBuilder().build("iOPsMsyDTHR5mSKzVfsTMMS5TnKDb9i-ApWGeQzYm98", wxMessage, weixinService);
		} else if("2".equals(content)) {
			return new TwBuilder().build("iOPsMsyDTHR5mSKzVfsTMMS5TnKDb9i-ApWGeQzYm98", wxMessage, weixinService);
		} else {
			return new TextBuilder().build("你好:"+content, wxMessage, weixinService);
		}
	}

}
