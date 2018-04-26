package com.drunck.mapper;

import org.apache.ibatis.annotations.Param;

import com.drunck.base.BaseMapper;
import com.drunck.domain.WxUser;

public interface WxUserMapper extends BaseMapper<WxUser> {

	WxUser queryByOpenid(@Param("openId") String openId);
}