package com.drunck.utils;

import java.util.List;

public class PageBean {
	private List<?> records;
	private int totalnum;
	private int pageNo = 1;
	private int pageSize = 10;

	private int totalPage;
	private int prePage;
	private int nextPage;
	private int startPage;
	private int endPage;

	public PageBean() {
	}

	public PageBean(List<?> records, int totalnum, int pageNo, int pageSize) {
		this.records = records;
		this.totalnum = totalnum;
		this.pageNo = pageNo;
		this.pageSize = pageSize;

		totalPage = (totalnum + pageSize - 1) / pageSize;

		if (pageNo - 1 > 0) {
			prePage = pageNo - 1;
		} else {
			prePage = 1;
		}
		if (pageNo + 1 < totalPage) {
			nextPage = totalPage + 1;
		} else {
			nextPage = totalPage;
		}

		if (totalPage < 10) {
			startPage = 1;
			endPage = totalPage;
		} else {
			startPage = pageNo - 4;
			endPage = pageNo + 5;
			if (startPage < 0) {
				startPage = 1;
				endPage = 10;
			} else if (endPage > totalPage) {
				startPage = totalPage - 9;
				endPage = totalPage;
			}
		}
	}

	public List<?> getRecords() {
		return records;
	}

	public void setRecords(List<?> records) {
		this.records = records;
	}

	public int getTotalnum() {
		return totalnum;
	}

	public void setTotalnum(int totalnum) {
		this.totalnum = totalnum;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPrePage() {
		return prePage;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
}
