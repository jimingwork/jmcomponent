/**
 * Create at 2008-12-3 by jiming.liu
 * $Id$
 */

package jm.lib.common.entity;

import java.util.Comparator;

/**
 * The table is a dictionary table and has column <b>Category</b>
 * @author jiming.liu
 *
 */
public interface Categoryable<T extends Comparable<T>> {
    public static final Comparator<Categoryable> COMPARATOR = new Comparator<Categoryable>() {

        public int compare(Categoryable o1, Categoryable o2) {
            if(o1==o2) return 0;
            if(o1==null) return -1;
            if(o2==null) return 1;
            return o1.getCatetory().compareTo(o2.getCatetory());
        }

    };
    T getCatetory();
}
