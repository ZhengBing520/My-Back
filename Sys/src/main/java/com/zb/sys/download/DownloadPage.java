package com.zb.sys.download;

import java.util.Objects;

/**
 * Created by bzheng on 2019/12/4.
 * 数据分页对象
 */
public class DownloadPage {

    /**
     * 总数
     */
    private long total = 0;

    /**
     * 总页数
     */
    private long totalPage = 0;

    /**
     * 每页显示条数，默认 5000
     */
    private long size = 5000;

    /**
     * 当前页
     */
    private long current = 1;

    /**
     * 是否存在下一页
     *
     * @return true / false
     */
    public boolean hasNext() {
        return this.current < this.totalPage;
    }

    /**
     * 下一页
     *
     */
    public void next() {
        this.current++;
    }

    public DownloadPage(long total, long size) {
        this.total = total;
        this.size = size;
        this.totalPage = getPages();
    }

    public DownloadPage(long total) {
        this.total = total;
        this.totalPage = getPages();
    }

    /**
     * 当前分页总页数
     */
    private long getPages() {
        if (getSize() == 0) {
            return 0L;
        }
        long pages = getTotal() / getSize();
        if (getTotal() % getSize() != 0) {
            pages++;
        }
        return pages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DownloadPage that = (DownloadPage) o;
        return total == that.total &&
                totalPage == that.totalPage &&
                size == that.size &&
                current == that.current;
    }

    @Override
    public String toString() {
        return "DownloadPage{" +
                "total=" + total +
                ", totalPage=" + totalPage +
                ", size=" + size +
                ", current=" + current +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, totalPage, size, current);
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }
}
