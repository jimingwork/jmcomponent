/**
 * Create at 2008-12-11 by jiming.liu
 * $Id$
 */

package jm.lib.common.entity;

import java.io.Serializable;
import java.util.Comparator;

public interface IdTreeable<ID extends Serializable, T extends IdTreeable<ID, T>> extends Treeable<T>, Idable<ID> {
    public static final Comparator<IdTreeable<?, ?>> COMPARATOR = new Comparator<IdTreeable<?, ?>>() {
        @SuppressWarnings({ "unchecked", "rawtypes" })
        public int compare(IdTreeable<?, ?> o1, IdTreeable<?, ?> o2) {
            if(o1==o2) return 0;
            if(o1==null) return -1;
            if(o2==null) return 1;
            if(o1.getPid() instanceof Comparable) {
                return ((Comparable)o1.getPid()).compareTo(o2.getPid());
            } else {
                return -1;
            }
        }

    };



    ID getPid();
}
