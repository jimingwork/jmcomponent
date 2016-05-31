/**
 * Create at 2008-12-3 by jiming.liu
 * $Id$
 */

package jm.lib.common.entity;

import java.util.Comparator;

/**
 * using IdTreeable
 * @param <T>
 */
@Deprecated
public interface IntegerIdTreeable<T extends IntegerIdTreeable<T>> extends IdTreeable<Integer, T>, Idable<Integer> {
    public static final Comparator<IntegerIdTreeable<?>> COMPARATOR = new Comparator<IntegerIdTreeable<?>>() {
        public int compare(IntegerIdTreeable<?> o1, IntegerIdTreeable<?> o2) {
            if(o1==o2) return 0;
            if(o1==null) return -1;
            if(o2==null) return 1;
            return o1.getPid() - o2.getPid();
        }

    };


}
