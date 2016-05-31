/**
 * Create at 2008-10-9 by jiming.liu
 * $Id$
 */

package jm.lib.util;

import com.google.common.collect.Multimap;
import com.google.common.collect.Table;
import jm.lib.Global;
import jm.lib.common.collections.common.KeyValue;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Array;
import java.util.*;


//@SuppressFBWarnings(value= {"MS_PKGPROTECT", "MS_MUTABLE_ARRAY"}, justification="All array in this class are immutable.")
public class EmptyObjects {



    /**
     * An empty immutable <code>Class</code> array.
     */
    @SuppressWarnings("rawtypes")
    public static final Class[] ARRAY_CLASS = ArrayUtils.EMPTY_CLASS_ARRAY;
    /**
     * An empty immutable <code>String</code> array.
     */
    public static final String[] ARRAY_STRING = ArrayUtils.EMPTY_STRING_ARRAY;
    /**
     * An empty immutable <code>long</code> array.
     */
    public static final long[] ARRAY_LONG = ArrayUtils.EMPTY_LONG_ARRAY;
    /**
     * An empty immutable <code>Long</code> array.
     */
    public static final Long[] ARRAY_LONG_OBJECT = ArrayUtils.EMPTY_LONG_OBJECT_ARRAY;
    /**
     * An empty immutable <code>int</code> array.
     */
    public static final int[] ARRAY_INTEGER = ArrayUtils.EMPTY_INT_ARRAY;
    /**
     * An empty immutable <code>Integer</code> array.
     */
    public static final Integer[] ARRAY_INTEGER_OBJECT = ArrayUtils.EMPTY_INTEGER_OBJECT_ARRAY;
    /**
     * An empty immutable <code>short</code> array.
     */
    public static final short[] ARRAY_SHORT = ArrayUtils.EMPTY_SHORT_ARRAY;
    /**
     * An empty immutable <code>Short</code> array.
     */
    public static final Short[] ARRAY_SHORT_OBJECT = ArrayUtils.EMPTY_SHORT_OBJECT_ARRAY;
    /**
     * An empty immutable <code>byte</code> array.
     */
    public static final byte[] ARRAY_BYTE = ArrayUtils.EMPTY_BYTE_ARRAY;
    /**
     * An empty immutable <code>Byte</code> array.
     */
    public static final Byte[] ARRAY_BYTE_OBJECT = ArrayUtils.EMPTY_BYTE_OBJECT_ARRAY;
    /**
     * An empty immutable <code>double</code> array.
     */
    public static final double[] ARRAY_DOUBLE = ArrayUtils.EMPTY_DOUBLE_ARRAY;
    /**
     * An empty immutable <code>Double</code> array.
     */
    public static final Double[] ARRAY_DOUBLE_OBJECT = ArrayUtils.EMPTY_DOUBLE_OBJECT_ARRAY;
    /**
     * An empty immutable <code>float</code> array.
     */
    public static final float[] ARRAY_FLOAT = ArrayUtils.EMPTY_FLOAT_ARRAY;
    /**
     * An empty immutable <code>Float</code> array.
     */
    public static final Float[] ARRAY_FLOAT_OBJECT = ArrayUtils.EMPTY_FLOAT_OBJECT_ARRAY;
    /**
     * An empty immutable <code>boolean</code> array.
     */
    public static final boolean[] ARRAY_BOOLEAN = ArrayUtils.EMPTY_BOOLEAN_ARRAY;
    /**
     * An empty immutable <code>Boolean</code> array.
     */
    public static final Boolean[] ARRAY_BOOLEAN_OBJECT = ArrayUtils.EMPTY_BOOLEAN_OBJECT_ARRAY;
    /**
     * An empty immutable <code>char</code> array.
     */
    public static final char[] ARRAY_CHAR = ArrayUtils.EMPTY_CHAR_ARRAY;
    /**
     * An empty immutable <code>Character</code> array.
     */
    public static final Character[] ARRAY_CHARACTER_OBJECT = ArrayUtils.EMPTY_CHARACTER_OBJECT_ARRAY;

    /**
     * An empty immutable <code>Map</code> array.
     */
    @SuppressWarnings("rawtypes")
    public static final Map[] ARRAY_MAP = new Map[0];

    /**
     * An empty immutable <code>List</code> array.
     */
    @SuppressWarnings("rawtypes")
    public static final List[] ARRAY_LIST = new List[0];

    /**
     * An empty immutable <code>Set</code> array.
     */
    @SuppressWarnings("rawtypes")
    public static final Set[] ARRAY_SET = new Set[0];

    /**
     * An empty immutable <code>Collection</code> array.
     */
    @SuppressWarnings("rawtypes")
    public static final Collection[] ARRAY_COLLECTION = new Collection[0];


    public static final Object OBJECT_UN_SERIALIZABLE = new Object();


    public static final Object OBJECT =
    Global.CUSTOMER_VERSION? // Global.CUSTOMER_VERSION - BEGIN
        new Object()
        : // Global.CUSTOMER_VERSION - MIDDLE
        new KeyValue<Integer, Integer>(-536870907,-2147483525) {
            private static final long serialVersionUID = 886536705779477462L;
            public void setKey(Integer k) {}
            public Integer setValue(Integer obj) {return null;}
            public String toJSON() {return "''";}
            public void toJSON(StringBuilder sb) {sb.append("''");}
            public String toString() {return "";}
        }
        // Global.CUSTOMER_VERSION - END
    ;

    public static final Object[] ARRAY_OBJECT = ArrayUtils.EMPTY_OBJECT_ARRAY;

    public static final String STRING = "";

    public static final <T> Enumeration<T> emptyEnumeration() {
        return Collections.emptyEnumeration();
    }

    public static final <T> Iterator<T> emptyIterator() {
        return Collections.emptyIterator();
    }

    @SuppressWarnings("rawtypes")
    public static final ListIterator emptyListIterator() {
        return Collections.emptyListIterator();
    }

    @SuppressWarnings("rawtypes")
    public static final boolean isEmpty(Object o) {
        if(null==o || OBJECT_UN_SERIALIZABLE == o || OBJECT.equals(o)) return true;
        if(o instanceof String) {
            return StrUtil.isEmpty((String)o);
        } else if(o instanceof Collection) {
            return ((Collection)o).isEmpty();
        } else if(o instanceof Map) {
            return ((Map)o).isEmpty();
        } else if(o instanceof Multimap) {
            return ((Multimap)o).isEmpty();
        } else if(o instanceof Table) {
            return ((Table)o).isEmpty();
        } else if(o instanceof Iterator) {
            return !((Iterator)o).hasNext();
        } else if(o instanceof Enumeration) {
            return !((Enumeration)o).hasMoreElements();
        } else if(o.getClass().isArray()) {
            return Array.getLength(o) == 0;
        } else {
            return false;
        }
    }

    public static final boolean isEmptyArray(Object[] o) {
        return null == o || 0 == o.length;
    }

    public static final boolean isEmptyCollection(Collection<?> o) {
        return null == o || o.isEmpty();
    }

    public static final boolean isEmptyMap(Map<?, ?> o) {
        return null == o || o.isEmpty();
    }

//    public static final boolean isEmptyShard(Shard shard) {
//        return null==shard || Shard.EMPTY_INSTANCE.equals(shard);
//    }

    public static final boolean isEmptyIterator(Iterator<?> it) {
        return null == it || !it.hasNext();
    }

    public static final boolean isEmptyEnumeration(Enumeration<?> en) {
        return null == en || !en.hasMoreElements();
    }

    @SuppressWarnings("unchecked")
    public static final <K,V> Map<K,V> noNull(Map<K,V> m) {
        return (null == m) ? Collections.EMPTY_MAP: m;
    }

    public static final <T> List<T> noNull(List<T> l) {
        return (null == l) ? Collections.EMPTY_LIST: l;
    }
    
    public static final <T> Set<T> noNull(Set<T> item) {
        return (null == item) ? Collections.EMPTY_SET: item;
    }

    public static final <T> Collection<T> noNull(Collection<T> item) {
        return (null == item) ? Collections.EMPTY_LIST: item;
    }

    public static final String noNull(String o) {
        return null == o ? "" : o;
    }
    
    public static final <T> T noNull(T o, T defaultValue) {
        return null == o ? defaultValue : o;
    }

    public static final boolean isEmpty(String s) {
        return StrUtil.isEmpty(s);
    }
    
    public static final boolean isEmptyGreedy(String s) {
        return StrUtil.isEmptyGreedy(s);
    }


    /**
     * Any element is arr equals null
     * @param arr
     * @return
     */
    public static boolean isAnyNull(Object ... arr) {
        if(null == arr) return true;
        for (int i = 0; i < arr.length; i++) {
            if(null == arr[i]) return true;
        }
        return false;
    }



}
