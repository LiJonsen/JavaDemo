package com.bookstore.pojo;

import java.util.List;

/**
 * @ClassName Page
 * @Description 分页JavaBean类
 * @Author Josen
 * @Date 2020/6/11 12:53
 * @Version 1.0
 **/
public class Page<T> {
    public static final int PAGE_SIZE = 4;
    // 分页列表集合
    private List<T> list;
    // 当前页码
    private int current;
    // 总页码 =（count+PAGE_SIZE-1)/PAGE_SIZE
    private int pageTotal;
    // 列表总条数
    private int count;
    // 一页展示多少条数据
    private int pageSize = PAGE_SIZE;
    // 当前分页请求的url
    private String url;
    // 最小价格
    private int min;
    // 最大价格
    private int max;

    public Page() {
    }

    /**
     * @param list 分页查询结果集
     * @param current 当前页码
     * @param pageTotal 总页码
     * @param count 总记录数
     * @param pageSize 每页总条数
     */
    public Page(List<T> list, int current, int pageTotal, int count, int pageSize) {
        this.list = list;
        this.current = current;
        this.pageTotal = pageTotal;
        this.count = count;
        this.pageSize = pageSize;
    }

    public Page(List<T> list, int current, int pageTotal, int count, int pageSize, int min, int max) {
        this.list = list;
        this.current = current;
        this.pageTotal = pageTotal;
        this.count = count;
        this.pageSize = pageSize;
        this.min = min;
        this.max = max;
    }

    @Override
    public String toString() {
        return "Page{" +
                "list=" + list +
                ", current=" + current +
                ", pageTotal=" + pageTotal +
                ", count=" + count +
                ", pageSize=" + pageSize +
                ", url='" + url + '\'' +
                '}';
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
