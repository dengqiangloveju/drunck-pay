package com.drunck.base.impl;

import java.io.Serializable;
import java.util.List;

import com.drunck.base.BaseMapper;
import com.drunck.base.BaseService;
import com.drunck.utils.PageBean;
import com.github.pagehelper.PageHelper;

import tk.mybatis.mapper.entity.Example;

@SuppressWarnings({"unchecked","rawtypes"})
public abstract class BaseServiceImpl implements BaseService {
	/*private BaseMapper baseMapper;

	public void setBaseMapper(BaseMapper baseMapper) {
		this.baseMapper = baseMapper;
	}*/
	
	public abstract  BaseMapper getMapper();
	
	@Override
	public <T> int save(T t) {
//		return baseMapper.insertSelective(t);
		return getMapper().insertSelective(t);
	}

	@Override
	public <T> int update(T t) {
//		return baseMapper.updateByPrimaryKeySelective(t);
		return getMapper().updateByPrimaryKeySelective(t);
	}

	@Override
	public <T> int deleteById(Serializable ID) {
//		return baseMapper.deleteByPrimaryKey(ID);
		return getMapper().deleteByPrimaryKey(ID);
	}
	
	@Override
	public <T> T queryById(Serializable ID) {
//		return (T) baseMapper.selectByPrimaryKey(ID);
		return (T) getMapper().selectByPrimaryKey(ID);
	}
	
	@Override
	public <T> T selectOne(T t) {
		return (T) getMapper().selectOne(t);
	}
	
	public <T> int count(T t) {
		int total = getMapper().selectCount(t);
		return total;
	}
	
	@Override
	public <T> List<T> select(T t) {
		List<T> list = (List<T>) getMapper().select(t);
		return list;
	}
	
	@Override
	public <T> List<T> SelectAll() {
		List<T> list = (List<T>) getMapper().selectAll();
		return list;
	}
	
	@Override
	public <T> List<T> selectByExample(Example example) {
		List<T> list = (List<T>) getMapper().selectByExample(example);
		return list;
	}
	
	@Override
	public <T> PageBean queryByPager(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		int totalnum = getMapper().selectCount(null);
		List<T> list = (List<T>) getMapper().selectAll();
		return new PageBean(list, totalnum, pageNo, pageSize);
	}
	
	@Override
	public <T> PageBean queryByPager(T t, int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		int totalnum = getMapper().selectCount(t);
		List<T> list = (List<T>) getMapper().select(t);
		return new PageBean(list, totalnum, pageNo, pageSize);
	}
}
