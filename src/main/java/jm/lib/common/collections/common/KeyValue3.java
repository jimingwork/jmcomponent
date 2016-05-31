/*
 * KeyValue3.java
 * $Id: KeyValue3.java 83 2006-04-25 14:09:06Z  $
 * Created on 2005��2��9��, ����6:22
 */


package jm.lib.common.collections.common;


public class KeyValue3<K,V,V2,V3> extends KeyValue2<K,V,V2> {

    @SuppressWarnings("rawtypes")
    public static final KeyValue3[] EMPTY_ARRAY = new KeyValue3[0];

    /**
     *
     */
    private static final long serialVersionUID = 5677803514092114839L;
    private V3 value3;

    public KeyValue3(K key, V value, V2 value2, V3 value3) {
        super(key, value, value2);
        this.value3 = value3;
    }

    public V3 getValue3() {
        return value3;
    }

    public void setValue3(V3 value3) {
        this.value3 = value3;
    }

    @Override
    public int hashCode() {
        final int PRIME = 22613;
        int result = super.hashCode();
        result = PRIME * result + ((value3 == null) ? 0 : value3.hashCode());
        return result;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        final KeyValue3 other = (KeyValue3) obj;
        if (value3 == null) {
            if (other.value3 != null)
                return false;
        } else if (!value3.equals(other.value3))
            return false;
        return true;
    }

//    public String toJSON() {
//        StringBuilder sb = new StringBuilder();
//        this.toJSON(sb);
//        return sb.toString();
//    }
//
//    public void toJSON(StringBuilder sb) {
//        sb.append('{');
//        sb.append("key:");
//        sb.append(JsonUtil.toJSON(getKey()));
//        sb.append(",value:");
//        sb.append(JsonUtil.toJSON(getValue()));
//        sb.append(",value2:");
//        sb.append(JsonUtil.toJSON(getValue2()));
//        sb.append(",value3:");
//        sb.append(JsonUtil.toJSON(getValue3()));
//        sb.append('}');
//
//    }


}
