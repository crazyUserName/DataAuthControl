package com.guttv.vo;

/**   
 * @Description: TODO
 * @author: koukaiqiang
 * @date:   2017年6月20日 上午10:12:42   
 *     
 */
public class PageVO {

	private int nowPage;
	private long totalSize;
	private int pageSize;
	private int start;
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public long getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
