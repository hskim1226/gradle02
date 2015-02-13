package com.apexsoft.framework.persistence.dao.page;

/**
 * Pagenateable interface.
 * 
 */
public interface Pagenateable {

	/**
	 * 페이지 나눔 정보를 조회 합니다.
	 * 
	 * @return PagenateInfoModel
	 */
	public PagenateInfo getPage();

	/**
	 * 페이지 정보를 저장 합니다.
	 * 
	 * @param pim
	 *            pim
	 */
	public void setPage(PagenateInfo pim);
}
