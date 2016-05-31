package jm.lib.common.entity;

import java.util.Comparator;

/**
 * Created by jiming.liu on 2012/11/24.
 */
public interface VarCodeable {
    public static final Comparator<VarCodeable> COMPARATOR = new Comparator<VarCodeable>() {
        public int compare(VarCodeable o1, VarCodeable o2) {
            if(o1==o2) return 0;
            if(o1==null) return -1;
            if(o2==null) return 1;
            if(o1.getVarCode()==null) return -1;

            return o1.getVarCode().compareTo(o2.getVarCode());
        }

    };



    String getVarCode();

}
