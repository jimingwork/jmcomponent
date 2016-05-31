package jm.lib.common.entity.meta;

import com.google.common.annotations.Beta;

/**
 * Created by jiming.liu on 2015/12/8.
 */
@Beta
public interface Copyable<T> {

    /**
     * create a new empty bean
     * @return
     */
    T newBean();
    /**
     *
     * @return
     */
    T cloneBean();

    /**
     * copy all properties
     * @param o
     */
    void copyTo(T o);

    /**
     * Only copy <code>properties</code>
     * @param o
     * @param properties
     */
    void copyTo(T o, long properties);
}
