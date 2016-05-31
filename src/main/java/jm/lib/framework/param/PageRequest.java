package jm.lib.framework.param;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by jiming.liu on 2016/4/14.
 *
 * Copy from Spring. The one in spring is very well defined, but the one critical defect is it's page is from zero.
 *
 */
public class PageRequest implements Pageable, Serializable {

    private static final long serialVersionUID = 1232835778694716861L;

    public static final int DEFAULT_PAGESIZE = 20;

    private final int page;
    private final int size;

    private final Sort sort;

    public PageRequest() {
        this(1, DEFAULT_PAGESIZE);
    }

    /**
     * Creates a new {@link PageRequest}. Pages are one(1) indexed, thus providing 1 for {@code page} will return
     * the first page.
     *
     * @param page must not be less than one(1).
     * @param size must not be less than one(1).
     */
    public PageRequest(int page, int size) {

        if (page < 1) {
            throw new IllegalArgumentException("Page index must not be less than one!");
        }

        if (size < 1) {
            throw new IllegalArgumentException("Page size must not be less than one!");
        }

        this.page = page;
        this.size = size;
        this.sort = null;
    }

    public PageRequest(int page, int size, Sort sort) {

        if (page < 1) {
            throw new IllegalArgumentException("Page index must not be less than one!");
        }

        if (size < 1) {
            throw new IllegalArgumentException("Page size must not be less than one!");
        }

        this.page = page;
        this.size = size;
        this.sort = sort;
    }

    public PageRequest(int page, int size, Collection<String> fields) {
        if (page < 1) {
            throw new IllegalArgumentException("Page index must not be less than one!");
        }

        if (size < 1) {
            throw new IllegalArgumentException("Page size must not be less than one!");
        }
        if(CollectionUtils.isEmpty(fields)) {
            this.sort = null;
        } else {
            ArrayList<Sort.Order> orders = new ArrayList<Sort.Order>(fields.size());
            for (String field : fields) {
                Sort.Direction direction = Sort.Direction.ASC;
                String trim = field.trim();
                if(trim.length() == 0) continue;
                if (trim.charAt('0') == '-') {
                    direction = Sort.Direction.DESC;
                    trim = trim.substring(1);
                }
                orders.add(new Sort.Order(direction, trim));
            }

            this.sort = new Sort(orders);
        }
        this.page = page;
        this.size = size;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#getPageSize()
     */
    public int getPageSize() {
        return size;
    }


    public int getPageNumber() {
        return page;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    /*
         * (non-Javadoc)
         * @see org.springframework.data.domain.Pageable#getOffset()
         */
    public int getOffset() {
        return (page-1) * getPageSize();
    }

    public int getEndOffset() {
        return page * getPageSize();
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#hasPrevious()
     */
    public boolean hasPrevious() {
        return page > 1;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#previousOrFirst()
     */
    @Deprecated
    public Pageable previousOrFirst() {
        throw new RuntimeException("Not implemented yet! Please contact jiming liu.");
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#next()
     */
    @Deprecated
    public Pageable next() {
        throw new RuntimeException("Not implemented yet! Please contact jiming liu.");
    }

    /**
     * Returns the {@link Pageable} requesting the previous {@link org.springframework.data.domain.Page}.
     *
     * @return
     */
    @Deprecated
    public Pageable previous() {
        throw new RuntimeException("Not implemented yet! Please contact jiming liu.");
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#first()
     */
    @Deprecated
    public Pageable first() {
        throw new RuntimeException("Not implemented yet! Please contact jiming liu.");
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;

        result = prime * result + page;
        result = prime * result + size;

        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        PageRequest other = (PageRequest) obj;
        return this.page == other.page && this.size == other.size;
    }
}
