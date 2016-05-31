/**
 * Create at 2008-12-3 by jiming.liu
 * $Id$
 */

package jm.lib.common.entity;

import java.util.Comparator;

/**
 * using Idable
 */
public interface IntegerIdable extends Idable<Integer>{
    public static final Comparator<IntegerIdable> COMPARATOR = new Comparator<IntegerIdable>() {
        public int compare(IntegerIdable o1, IntegerIdable o2) {
            if(o1==o2) return 0;
            if(o1==null) return -1;
            if(o2==null) return 1;
            return o1.getId() - o2.getId();
        }

    };


}
