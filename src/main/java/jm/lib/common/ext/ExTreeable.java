/**
 * Create at 2008-12-4 by jiming.liu
 * $Id$
 */

package jm.lib.common.ext;

import java.util.List;

import jm.lib.common.entity.Treeable;

public interface ExTreeable<T extends ExTreeable<T>> extends Treeable<T> {

    boolean isChildOf(T t);

    /**
     * is child or grand child or grand grand child ...
     * @param t
     * @return
     */
    boolean isDescendantOf(T t);

    boolean isSiblingOf(T t);

    List<T> getDescendants();


    /**
     * <p>
     * get all ancestors, leaf -> top order, which means the return list[0] is father, list[1] is grandfather
     * @return
     */
    List<T> getAncestors();


}
