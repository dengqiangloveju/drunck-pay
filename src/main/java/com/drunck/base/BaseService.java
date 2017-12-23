package com.drunck.base;

import java.io.Serializable;
import java.util.List;

import com.drunck.utils.PageBean;

import tk.mybatis.mapper.entity.Example;

public interface BaseService {
	public <T> int save(T t);
	public <T> int update(T t);
	public <T> int deleteById(Serializable ID);
	public <T> T queryById(Serializable ID);
	public <T> T selectOne(T t);
	public <T> int count(T t);
	public <T> List<T> select(T t);
	public <T> List<T> SelectAll();
	public <T> List<T> selectByExample(Example example);
	public <T> PageBean queryByPager(int pageNo, int pageSize);
	public <T> PageBean queryByPager(T t, int pageNo, int pageSize);
}
