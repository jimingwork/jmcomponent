package jm.lib.common.entity;

import java.util.Comparator;

/**
 * Created by jiming.liu
 */
public interface Priorityable {
    public static final Comparator<Priorityable> COMPARATOR = new Comparator<Priorityable>() {
        public int compare(Priorityable o1, Priorityable o2) {
            if(o1==o2) return 0;
            if(o1==null) return -1;
            if(o2==null) return 1;
            return o1.getPriority() - o2.getPriority();
        }
    };


    short getPriority();

}
