package com.drunck.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.drunck.base.BaseMapper;
import com.drunck.base.impl.BaseServiceImpl;
import com.drunck.domain.WxUser;
import com.drunck.mapper.WxUserMapper;
import com.drunck.service.WxUserService;

import me.chanjar.weixin.mp.bean.result.WxMpUser;

@Service("wxUserService")
public class WxUserServiceImpl extends BaseServiceImpl implements WxUserService {
	@Resource
	private WxUserMapper wxUserMapper;
	
	@Override
	public BaseMapper<?> getMapper() {
		return wxUserMapper;
	}

	@Override
	public void subscribe(WxMpUser wxMpUser) {
		synchronized (WxUserServiceImpl.class) {
			WxUser result = wxUserMapper.queryByOpenid(wxMpUser.getOpenId());
			
			WxUser wxUser = new WxUser();
			wxUser.setOpenid(wxMpUser.getOpenId());
			wxUser.setNickname(wxMpUser.getNickname());
			wxUser.setSex(wxMpUser.getSex().toString());
			wxUser.setCountry(wxMpUser.getCountry());
			wxUser.setProvince(wxMpUser.getProvince());
			wxUser.setCity(wxMpUser.getCity());
			wxUser.setHeadimgurl(wxMpUser.getHeadImgUrl());
			wxUser.setSubscribe(wxMpUser.getSubscribe());
			wxUser.setSubscribeTime(new Date(wxMpUser.getSubscribeTime()*1000));
			wxUser.setRemark(wxMpUser.getRemark());
			
			if(result!=null) {
				wxUser.setId(result.getId());
				wxUserMapper.updateByPrimaryKeySelective(wxUser);
			} else {
				wxUserMapper.insertSelective(wxUser);
			}
		}
	}
	
	@Override
	public void unSubscribe(WxMpUser wxMpUser) {
		synchronized (WxUserServiceImpl.class) {
			WxUser wxUser = wxUserMapper.queryByOpenid(wxMpUser.getOpenId());
			
			if(wxUser!=null) {
				wxUser.setSubscribe(false);
				wxUserMapper.updateByPrimaryKeySelective(wxUser);
			}
		}
	}
}
