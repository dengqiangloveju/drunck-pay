package com.drunck.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.drunck.base.BaseMapper;
import com.drunck.base.impl.BaseServiceImpl;
import com.drunck.domain.WxMessage;
import com.drunck.mapper.WxMessageMapper;
import com.drunck.service.WxMessageService;

import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;

@Service("wxMessageService")
public class WxMessageServiceImpl extends BaseServiceImpl implements WxMessageService {
	@Resource
	private WxMessageMapper wxMessageMapper;
	
	@Override
	public BaseMapper<?> getMapper() {
		return wxMessageMapper;
	}
	
	@Override
	public void subscribe(WxMpXmlMessage wxMessage) {
		synchronized (WxMessageServiceImpl.class) {
			WxMessage result = wxMessageMapper.queryByOpenid(wxMessage.getFromUser());
			if(result==null || "0".equals(result.getStatus())) {
				WxMessage message = new WxMessage();
				message.setMerchant(wxMessage.getToUser());
				message.setFromUser(wxMessage.getFromUser());
				message.setMsgType(wxMessage.getMsgType());
				message.setEvent(wxMessage.getEvent());
				message.setCreateTime(new Date(wxMessage.getCreateTime()*1000));
				message.setStatus("1");
				wxMessageMapper.insertSelective(message);
			}
		}
	}

	@Override
	public void unSubscribe(WxMpXmlMessage wxMessage) {
		synchronized (WxMessageServiceImpl.class) {
			WxMessage result = wxMessageMapper.queryByOpenid(wxMessage.getFromUser());
			if(result==null || "1".equals(result.getStatus())) {
				WxMessage message = new WxMessage();
				message.setMerchant(wxMessage.getToUser());
				message.setFromUser(wxMessage.getFromUser());
				message.setMsgType(wxMessage.getMsgType());
				message.setEvent(wxMessage.getEvent());
				message.setCreateTime(new Date(wxMessage.getCreateTime()*1000));
				message.setStatus("0");
				wxMessageMapper.insertSelective(message);
			}
		}
	}

}
