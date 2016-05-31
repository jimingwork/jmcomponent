/**
 * Create at 2008-11-7 by jiming.liu
 * $Id$
 */

package jm.lib.common.collections.common;

import java.io.Serializable;

/**
 *
 * @author jiming.liu
 *
 * @param <M> the object itself
 * @param <E> the extra object related with it
 */
public class ObjectExtra<M, E> implements Serializable {
    private M self;
    private E extra;

    public ObjectExtra() {}

    public ObjectExtra(M self, E extra) {
        super();
        this.extra = extra;
        this.self = self;
    }




    /**
     * @return the self
     */
    public M getSelf() {
        return self;
    }



    /**
     * @param self the self to set
     */
    public void setSelf(M self) {
        this.self = self;
    }



    /**
     * @return the extra
     */
    public E getExtra() {
        return extra;
    }



    /**
     * @param extra the extra to set
     */
    public void setExtra(E extra) {
        this.extra = extra;
    }



    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((extra == null) ? 0 : extra.hashCode());
        result = prime * result + ((self == null) ? 0 : self.hashCode());
        return result;
    }



    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ObjectExtra other = (ObjectExtra) obj;
        if (extra == null) {
            if (other.extra != null)
                return false;
        } else if (!extra.equals(other.extra))
            return false;
        if (self == null) {
            if (other.self != null)
                return false;
        } else if (!self.equals(other.self))
            return false;
        return true;
    }


//    public String toJSON() {
//        StringBuilder sb = new StringBuilder();
//        toJSON(sb);
//        return sb.toString();
//    }

//    public void toJSON(StringBuilder sb) {
//        sb.append("{self:").append(JsonUtil.toJSON(self));
//        sb.append(", extra:").append(JsonUtil.toJSON(extra));
//        sb.append('}');
//
//    }
//
//    public void toJSON(Appendable a) throws IOException {
//        a.append("{self:").append(JsonUtil.toJSON(self));
//        a.append(", extra:").append(JsonUtil.toJSON(extra));
//        a.append('}');
//        /* OPTI:
//        sb.append("{self:");
//        if(self instanceof Collection) {
//
//        } else if (self instanceof Map) {
//
//        } else {
//            sb.append(JSONUtil.toJSON(self));
//        }
//        sb.append(", extra:").append(JSONUtil.toJSON(extra));
//        sb.append('}');
//        */
//    }

    public String toString() {
        return null==self?"":self.toString();
    }

}
