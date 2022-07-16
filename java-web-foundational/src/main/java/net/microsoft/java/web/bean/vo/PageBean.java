package net.microsoft.java.web.bean.vo;

import java.util.List;

/**
 * @description:
 * @Date on 2022/6/20
 * @author: suche
 **/

public class PageBean<T> {

    /**
     * 总条数
     */
    private Long totalCount;


    /**
     * 数据列表
     */
    private List<T> dataList;


    /**
     * 页码(当前页)
     */
    private Integer pageNo;

    /**
     * 页面条数
     */
    private Integer pageSize;

    /**
     * 总页数
     */
    private Long totalCountPage;

    public Long getTotalCountPage() {
        return totalCountPage;
    }

    public void setTotalCountPage(Long totalCountPage) {
        this.totalCountPage = totalCountPage;
    }


    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}
