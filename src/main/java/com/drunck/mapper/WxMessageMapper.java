package com.drunck.mapper;

import org.apache.ibatis.annotations.Param;

import com.drunck.base.BaseMapper;
import com.drunck.domain.WxMessage;

public interface WxMessageMapper extends BaseMapper<WxMessage> {

	WxMessage queryByOpenid(@Param("openid") String openid);
}