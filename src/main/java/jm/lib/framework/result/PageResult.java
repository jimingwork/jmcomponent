package jm.lib.framework.result;

import org.apache.commons.lang3.builder.Builder;
import org.springframework.cglib.beans.ImmutableBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Created by jiming.liu on 2015/2/26.
 */
public class PageResult<T> extends Result implements Serializable {

    private static final long serialVersionUID = -276187002721760134L;

    public static final int PAGE_SIZE_MAX = 1000;
    public static final int PAGE_SIZE_MIN = 1;
    public static final int PAGE_SIZE_DEFAULT = 10;

    public static final PageResult EMPTY = (PageResult) ImmutableBean.create(success("没有数据。", Collections.EMPTY_LIST).build());


    public static final <T> PageResult<T> empty() {
        return (PageResult<T>)EMPTY;
    }


    /**
     * 1 besed index, first page is 1, second page is 2 ...
     */
    private int page = 1;
    private int pageSize = PAGE_SIZE_DEFAULT;
    private long total;
    private List<T> data;


    public static PageResult fail(String message) {
        return new PageResult(false, message);
    }


    public PageResult() {

    }

    public PageResult(Page<T> page) {
        this.setRet(true);
        this.setMsg("成功。");
        this.setPage(page.getNumber() + 1);
        this.setPageSize(page.getSize());
        this.setTotal(page.getTotalElements());
        this.setData(page.getContent());

    }

    public PageResult(List<T> data, Pageable pageRequest, long total) {
        this.setRet(true);
        this.setMsg("成功。");
        this.setPage(pageRequest.getPageNumber() + 1);
        this.setPageSize(pageRequest.getPageSize());
        this.setTotal(total);
        this.setData(data);

    }

    /**
     *
     * @param data
     * @param page  1 based index
     * @param pageSize
     * @param total
     */
    public PageResult(List<T> data, int page, int pageSize, long total) {
        this.setRet(true);
        this.setMsg("成功。");
        this.setPage(page);
        this.setPageSize(pageSize);
        this.setTotal(total);
        this.setData(data);
    }

    public static <T> PageResultBuilder<T> success(String message, List<T> data) {
        return new PageResultBuilder<T>(message, data);
    }

    private PageResult(boolean b, String message) {
        super(b, message);
    }


    PageResult(boolean ret, String message, int page, int pageSize, long total, List<T> data) {
        super(ret, message);
        this.page = page;
        this.pageSize = pageSize;
        this.total = total;
        this.data = data;
    }



    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        if(page < 1) {
            this.page = 1;
        } else {
            this.page = page;
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if(pageSize < PAGE_SIZE_MIN) {
            this.pageSize = PAGE_SIZE_MIN;
        } else if (pageSize > PAGE_SIZE_MAX) {
            this.pageSize = PAGE_SIZE_MAX;
        } else {
            this.pageSize = pageSize;
        }
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getData() {
        if (null == data) {
            return Collections.EMPTY_LIST;
        }
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public static Builder newBuilder() {
        PageResultBuilder builder = new PageResultBuilder();
        return builder;
    }

    public static Builder newBuilder(PageResult copy) {
        PageResultBuilder builder = new PageResultBuilder();
        builder.ret = copy.isRet();
        builder.message = copy.getMsg();
        builder.page = copy.page;
        builder.pageSize = copy.pageSize;
        builder.total = copy.total;
        builder.data = copy.data;
        return builder;
    }


    public static class PageResultBuilder<E> implements Builder<PageResult<E>>{
        private boolean ret;
        private String message;
        private int page = -1;
        private int pageSize = -1;
        private long total = -1;

        private List<E> data = null;

        public PageResultBuilder() { }

        public PageResultBuilder(String messsage) {
            this.message = messsage;
        }

        public PageResultBuilder(String message, List<E> data) {
            this.message = message;
            this.data = data;
        }

        public PageResultBuilder ret(boolean ret) {
            this.ret = ret;
            return this;
        }

        public PageResultBuilder message(String message) {
            this.message = message;
            return this;
        }

        public PageResultBuilder currentPage(int page) {
            this.page = page;
            return this;
        }

        public PageResultBuilder pageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }


        public PageResultBuilder total(long total) {
            this.total = total;
            return this;
        }


        public PageResult<E> build() {
            if(page < 0) {return PageResult.fail("请指定页码！");}
            if(pageSize < 0) {return PageResult.fail("请指定每页条数！");}
            if(total < 0) {return PageResult.fail("请指定记录总数！");}
            if(data == null) {return PageResult.fail("请指定数据！");}
            PageResult<E> result = new PageResult(ret, message, page, pageSize, total, data);

            return result;
        }

    }


}
