package com.ts.utils;

public class Pagination {
	//页码
	private int pageNum;
	//每页显示数据条数
	private int pageSize;
	//总记录条数
	private int totalCount;
	//页数
	private int pageCount;

	public Pagination(int pageNum, int pageSize) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}

	public static Pagination currentPagination(int pageNum, int pageSize) {
		Pagination pagination = ThreadContent.pagination();

		if (pagination == null) {
			pagination = new Pagination(pageNum, pageSize);
			ThreadContent.pagination(pagination);
		}
		return pagination;
	}

	public int getPageNum() {
		return this.pageNum;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public int getTotalCount() {
		return this.totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		if (totalCount % this.pageSize > 0)
			this.pageCount = (totalCount / this.pageSize + 1);
		else
			this.pageCount = (totalCount / this.pageSize);
	}

	public int getPageCount() {
		return this.pageCount;
	}

	public int start() {
		return (this.pageNum - 1) * this.pageSize;
	}
}