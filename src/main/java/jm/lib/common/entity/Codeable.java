package jm.lib.common.entity;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by jiming.liu
 */
public interface Codeable<T extends Serializable> {
    public static final Comparator<Codeable> COMPARATOR = new Comparator<Codeable>() {
        public int compare(Codeable o1, Codeable o2) {
            if(o1==o2) return 0;
            if(o1==null) return -1;
            if(o2==null) return 1;
            if(o1.getCode()==null) return -1;
            if (o1.getCode() instanceof Comparable) {
                return ((Comparable) o1.getCode()).compareTo(o2.getCode());
            } else {
                throw new RuntimeException("Not supported code type "+ o1.getCode().getClass() +" yet! Please contact.");
            }
        }

    };



    T getCode();
}
