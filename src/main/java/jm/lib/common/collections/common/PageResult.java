/**
 * Create at 2008-10-16 by jiming.liu
 * $Id$
 */

package jm.lib.common.collections.common;

import jm.lib.framework.result.CountResult;

import java.util.List;


/**
 *
 * @author jiming.liu
 *
 * @param <T>
 * @deprecated  using latest defined Result
 */
public class PageResult<T> {
    private int page;
    private int pageSize;
    private CountResult count;
    private List<T> list;

    public PageResult() {
        super();
    }

    public PageResult(CountResult count, List<T> list) {
        super();
        this.count = count;
        this.list = list;
    }

    
    /**
     * current page index
     * @return
     */
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    /**
     * page size
     * @return
     */
    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return the count
     */
    public CountResult getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(CountResult count) {
        this.count = count;
    }

    /**
     * @return the list
     */
    public List<T> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<T> list) {
        this.list = list;
    }

}
