package jm.lib.common.entity;

import java.util.Comparator;

/**
 *
 * using Idable
 *
 * Created by jiming.liu on 2016/4/13.
 */
public interface LongIdable extends Idable<Long> {
    public static final Comparator<LongIdable> COMPARATOR = new Comparator<LongIdable>() {
        public int compare(LongIdable o1, LongIdable o2) {
            if(o1==o2) return 0;
            if(o1==null) return -1;
            if(o2==null) return 1;
            if(o1.getId() == o2.getId()) return 0;
            return o1.getId() - o2.getId() > 0? 1: -1;
        }

    };
}
