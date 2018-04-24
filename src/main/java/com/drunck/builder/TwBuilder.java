package com.drunck.builder;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNews;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage;

public class TwBuilder extends AbstractBuilder {

	@Override
	public WxMpXmlOutMessage build(String content, WxMpXmlMessage wxMessage, WxMpService service) {
		
		WxMpMaterialNews wxMpMaterialNewsSingle = null;
		try {
			wxMpMaterialNewsSingle = service.getMaterialService().materialNewsInfo(content);
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WxMpMaterialNews.WxMpMaterialNewsArticle article = wxMpMaterialNewsSingle.getArticles().get(0);

		WxMpXmlOutNewsMessage.Item item = new WxMpXmlOutNewsMessage.Item();
		/*item.setDescription("single content");
		item.setPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/TiatVadb6AklAC6BHwibXiaPXa0zAPGRMSbRCPZUicowzib5uM7SSCRl8lsLQnQRro8wiaEnchYqg4jtuG3kJqatkfwA/0?wx_fmt=jpeg");
		item.setTitle("title");
		item.setUrl("https://www.baidu.com");*/
		item.setDescription(article.getContent());
		item.setPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/TiatVadb6AklAC6BHwibXiaPXa0zAPGRMSbRCPZUicowzib5uM7SSCRl8lsLQnQRro8wiaEnchYqg4jtuG3kJqatkfwA/0?wx_fmt=jpeg");
		item.setTitle(article.getTitle());
		item.setUrl("https://www.baidu.com");

		WxMpXmlOutNewsMessage m = WxMpXmlOutMessage.
				NEWS().
				fromUser(wxMessage.getToUser()).
				toUser(wxMessage.getFromUser()).
				addArticle(item)
				.build();
		return m;
	}

}