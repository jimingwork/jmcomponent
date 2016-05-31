/*
 * Created by Jiming Liu at 2008-8-11
 * $Id$
 */

package jm.lib.common.ext;

import java.util.Comparator;

import jm.lib.common.entity.DisplayNameable;
import jm.lib.common.entity.Nameable;

@Deprecated  // why?
public interface ExNameable extends Nameable, DisplayNameable {
    public static final Comparator<ExNameable> COMPARATOR = new Comparator<ExNameable>() {
        public int compare(ExNameable o1, ExNameable o2) {
            if(o1==o2) return 0;
            int result = Nameable.COMPARATOR.compare(o1, o2);
            if(0 != result) return result;
            return o1.getDisplayName().compareTo(o2.getDisplayName());
        }

    };


}
