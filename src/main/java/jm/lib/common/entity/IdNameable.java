/**
 * Create at 2008-12-10 by jiming.liu
 * $Id$
 */

package jm.lib.common.entity;

import java.io.Serializable;
import java.util.Comparator;


public interface IdNameable<T extends Serializable> extends Idable<T>, Nameable{
    @SuppressWarnings("rawtypes")
    public static final Comparator<IdNameable> COMPARATOR = new Comparator<IdNameable>() {
        public int compare(IdNameable o1, IdNameable o2) {
            if(o1==o2) return 0;
            return o1.getName().compareTo(o2.getName());
        }
    };


}
