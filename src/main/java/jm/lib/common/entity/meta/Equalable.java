/**
 * Create at 2009-3-3 by jiming.liu
 * $Id$
 */

package jm.lib.common.entity.meta;

import java.util.Collection;
/**
 * Interface to compare partial properties of beans
 * @author jiming.liu
 *
 */
public interface Equalable<T> {
    /**
     *
     * @param o
     * @param properties
     * @param include, include or exclude
     * @return
     */
    boolean equals(T o, Collection<String> properties, boolean include);

    /**
     * Is represent same POJO, their identity(ID) properties should be same. Other properties are not required to be equals.
     * @param o
     * @return
     */
    boolean isSameIdentity(T o);
}
