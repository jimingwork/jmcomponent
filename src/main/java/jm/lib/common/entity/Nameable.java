/**
 * Create at 2008-12-3 by jiming.liu
 * $Id$
 */

package jm.lib.common.entity;

import java.util.Comparator;

public interface Nameable {
    public static final Comparator<Nameable> COMPARATOR = new Comparator<Nameable>() {
        public int compare(Nameable o1, Nameable o2) {
            if(o1==o2) return 0;
            if(o1==null) return -1;
            if(o2==null) return 1;
            if(o1.getName()==null) return -1;

            return o1.getName().compareTo(o2.getName());
        }

    };



    String getName();
}
