package com.secondhandmarket.utils;

import java.util.List;

/**
 * 通用的分页工具类
 */
public class PageUtil<T> {

    private long pageIndex; //当前页码
    private long pageSize; //每页大小
    private long totalCount; //条数
    private long pageCount;  //总页数 -- 计算出来
    private List<T> datas;  //每页数据

    private long startNum; //开始序号
    private long endNum; //结束序号

    public PageUtil(long pageIndex, long pageSize, long totalCount, List<T> datas) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.datas = datas;
        //计算总页数
        this.pageCount=(totalCount%pageSize==0)?(totalCount/pageSize):(totalCount/pageSize+1);

        //给序号赋值
        if(this.pageCount<=10){
            this.startNum=1;
            this.endNum=this.pageCount;
        }else{
            this.startNum=pageIndex-4;
            this.endNum=pageIndex+5;
            if(this.startNum<1){
                this.startNum=1;
                this.endNum=10;
            }else if(this.endNum>pageCount){
                this.startNum=pageCount-9;
                this.endNum=pageCount;
            }
        }

    }

    public long getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(long pageIndex) {
        this.pageIndex = pageIndex;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }


    public long getStartNum() {
        return startNum;
    }

    public void setStartNum(long startNum) {
        this.startNum = startNum;
    }

    public long getEndNum() {
        return endNum;
    }

    public void setEndNum(long endNum) {
        this.endNum = endNum;
    }
}
