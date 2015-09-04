package com.spay.web.common.helper;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class PagerInfo {

    public static final String DEFAULT_PAGE_KEY = "page";
    public static final String DEFAULT_PAGE_SIZE_KEY = "pageSize";
    public static final String DEFAULT_PAGER_INFO_ATTRIBUTE_KEY = "pagerInfo";
    private long totalRows; // 전체 데이터 수.
    private int pageSize; // 한페이지에 노출될 데이터 수.
    private int indexSize; // 페이지 이동을 위한 색인 수.
    private int page = 1; // 현재 페이지 번호
    private static final int MAX_PAGE_SIZE = 100;

    public PagerInfo() {
        this.pageSize = 10; // Default;
        this.indexSize = 10; // Default;
    }

    public PagerInfo(HttpServletRequest request) {
        this.pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(request.getParameter(DEFAULT_PAGE_SIZE_KEY), "10"));
        this.indexSize = 10; // Default;
        this.page = Integer.parseInt(StringUtils.defaultIfEmpty(request.getParameter(DEFAULT_PAGE_KEY), "1"));
    }

    public void init(long totalRows, int pageSize, int indexSize) {
        this.totalRows = totalRows;
        this.pageSize = pageSize;
        this.indexSize = indexSize;
    }

    public void init(long totalRows) {
        this.totalRows = totalRows;
    }

    public long getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(long totalRows) {
        this.totalRows = totalRows;
    }

    public int getPageSize() {
        return pageSize > MAX_PAGE_SIZE ? MAX_PAGE_SIZE : pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getIndexSize() {
        return indexSize;
    }

    public void setIndexSize(int indexSize) {
        this.indexSize = indexSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public long getOffset() {
        return getPageSize() * (page - 1);
    }

    public int getLimit() {
        return getPageSize();
    }

}
