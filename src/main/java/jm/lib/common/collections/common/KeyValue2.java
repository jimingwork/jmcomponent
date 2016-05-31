/*
 * KeyValue2.java
 * $Id: KeyValue2.java 83 2006-04-25 14:09:06Z  $
 * Created on 2005��2��9��, ����6:22
 */



package jm.lib.common.collections.common;


public class KeyValue2<K,V,V2> extends KeyValue<K,V> {

    @SuppressWarnings("rawtypes")
    public static final KeyValue2[] EMPTY_ARRAY = new KeyValue2[0];

    /**
     *
     */
    private static final long serialVersionUID = 8202977298985927437L;


    private V2 value2;

    public KeyValue2() {super();}

    public KeyValue2(K key, V value, V2 value2) {
        super(key, value);
        this.value2 = value2;
    }

    public V2 getValue2() {
        return value2;
    }

    public void setValue2(V2 value2) {
        this.value2 = value2;
    }

    @Override
    public int hashCode() {
        final int PRIME = 13697;
        int result = super.hashCode();
        result = PRIME * result + ((value2 == null) ? 0 : value2.hashCode());
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
        final KeyValue2 other = (KeyValue2) obj;
        if (value2 == null) {
            if (other.value2 != null)
                return false;
        } else if (!value2.equals(other.value2))
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
//        sb.append('}');
//
//    }



}
