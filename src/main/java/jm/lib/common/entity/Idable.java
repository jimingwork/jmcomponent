/**
 * Create at 2008-12-10 by jiming.liu
 * $Id$
 */

package jm.lib.common.entity;

import java.io.Serializable;
import java.util.Comparator;



public interface Idable<ID extends Serializable> {
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static final Comparator<Idable> COMPARATOR = new Comparator<Idable>() {
        public int compare(Idable o1, Idable o2) {
            if(o1==o2) return 0;
            if(o1==null) return -1;
            if(o2==null) return 1;
            return ((Comparable)o1.getId()).compareTo(o2.getId());
        }
    };


    ID getId();
    void setId(ID id);
}
