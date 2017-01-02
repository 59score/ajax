package com.dynastqin.portletcore.util;

public class PageUtil {
	private int totalRecords=-1 ; //�ܼ�¼��

	private int pageIndex ;//ҳ��

	private int pageSize ;//ÿҳ��¼��

	private int totalPages;//��ҳ��
	
	private int skip;
	public int getSkip() {
		return skip;
	}
	public void setSkip(int skip) {
		this.skip = skip;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public PageUtil(int pageSize,int pageIndex)
	{
		setPageSize(pageSize);
		setPageIndex(pageIndex);
		if(this.pageIndex<1)
			this.pageIndex=1;
	}
	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
		int totalpages=CalculateTotalPages(getTotalRecords(),getPageSize());
		setTotalPages(totalpages);
		if(getPageIndex()>totalpages&&totalpages>0)
			setPageIndex(totalpages);
		setSkip((getPageIndex() - 1) * getPageSize());
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	private  int CalculateTotalPages(int totalRecords, int pageSize)
    {
        int num = totalRecords / pageSize;//����
        if ((totalRecords % pageSize) > 0)
        {
            num++;
        }
        return num;
    }
}
