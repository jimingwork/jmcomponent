
/**
 *
 * $Id: ConfUtil.java 126 2006-11-10 15:53:50Z  $
 * Created on 2005-12-29, 16:09:17
 */
package jm.lib.util;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

import jm.lib.framework.exception.impl.DataConvertException;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * get value form Properties, ResourceBundle or map, it will supply a default
 * value if the value is not exists or the value is not valid
 *
 * @author JimingLiu
 *
 */
public class ConfUtil {
    private static final Logger logger = LoggerFactory.getLogger(ConfUtil.class);

    private static final ResourceBundle getResourceBundle(
            String resourceBundle, Locale locale) {
        if(StrUtil.isEmpty(resourceBundle)) return null;
        ResourceBundle result;
        try {
            result = null==locale?ResourceBundle.getBundle(resourceBundle):ResourceBundle.getBundle(resourceBundle, locale);
        } catch (MissingResourceException e) {
            result = null;
            if(logger.isWarnEnabled()) {
                logger.warn("ResourceBundle [" + resourceBundle + "].["
                         + (null==locale?"":locale.toString()) +"] does not exists!" ,e);
            }
        }

        return result;
    }

    /**
     *
     * @param resourceBundle
     * @param key
     * @return
     */
    public static String getResourceValue(String resourceBundle, String key, boolean log) {
        return getResourceValue(resourceBundle, Locale.getDefault(), key, log);
    }

    /**
     *
     * @param resourceBundle
     * @param key
     * @return
     */
    public static String getResourceValue(String resourceBundle, String key) {
        return getResourceValue(resourceBundle, Locale.getDefault(), key, true);
    }

    /**
     *
     * @param resourceBundle
     * @param locale
     * @param key
     * @return
     */
    public static String getResourceValue(String resourceBundle, Locale locale, String key) {
        return getResourceValue(resourceBundle, locale, key, true);

    }

    /**
     *
     * @param resourceBundle
     * @param locale
     * @param key
     * @param log whether log if the key do not exist in the bundle
     * @return
     */
    public static String getResourceValue(String resourceBundle, Locale locale, String key, boolean log) {
        ResourceBundle rb = getResourceBundle(resourceBundle, locale);
        if(null == rb) return "";
        String result;

        try {
            result = rb.getString(key);
        } catch (MissingResourceException e) {
            result = "";
            if(log&&logger.isWarnEnabled()) {
                logger.warn("Key [" + key
                        + "] does not exist in ResourceBundle [" + resourceBundle + "].["
                        + (null==locale?"":locale.toString()) +"]" ,e);
            }
        }

        return result;

    }


    /**
     *
     * @param resourceBundle
     * @param locale
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getResourceValue(String resourceBundle, Locale locale, String key, String defaultValue) {
        String result = getResourceValue(resourceBundle, locale, key, false);
        return StrUtil.isEmpty(result)?defaultValue:result;
    }


    /**
     *
     * @param resourceBundle
     * @param key
     * @param defaultValue
     * @return
     */
    public static final String getResourceValue(String resourceBundle, String key, String defaultValue) {
        return getResourceValue(resourceBundle, Locale.getDefault(), key, defaultValue);
    }







    @SuppressWarnings("rawtypes")
    public static final boolean getBoolean(Map m, Object key,
            boolean defaultValue) {
        if(null==m) return defaultValue;
        return getBoolean((String) m.get(key), defaultValue);
    }

    public static final boolean getBoolean(String resourceBundle
            , String key, boolean defaultValue) {
        return getBoolean(getResourceValue(resourceBundle, Locale.getDefault(), key), defaultValue);
    }

    public static final boolean getBoolean(String value) {
        return getBoolean(value, false);
    }

    public static final boolean getBoolean(String value, boolean defaultValue) {
        if (StringUtils.isBlank(value))
            return defaultValue;
        return BooleanUtils.toBoolean(value);
    }

    public static final boolean getBoolean(Object value, boolean defaultValue) {
        if(null == value) return defaultValue;
        if(value instanceof Boolean) return ((Boolean)value).booleanValue();
        if(value instanceof String) return getBoolean((String) value, defaultValue);
        if(value instanceof Number) return ((Number)value).intValue() != 0;
        return defaultValue;
    }



    @SuppressWarnings("rawtypes")
    public static final byte getByte(Map m, Object key,
            byte defaultValue) {
        if(null==m) return defaultValue;
        return getByte((String) m.get(key), defaultValue);
    }

    public static final byte getByte(String resourceBundle
            , String key, byte defaultValue) {
        return getByte(getResourceValue(resourceBundle, Locale.getDefault(), key), defaultValue);
    }

    public static final byte getByte(String value, byte defaultValue) {
        byte result = defaultValue;
        try {
            result = getByte(value);
        } catch (DataConvertException e) {
            logger.warn("value ["+value+"] convert error!", e.getNestedException());
        }
        return result;
    }

    @SuppressWarnings("rawtypes")
    public static final byte getByte(Map m, Object key) throws DataConvertException {
        return getByte((String) (null==m?null:m.get(key)));
    }

    public static final byte getByte(String resourceBundle,
            String key) throws DataConvertException {
        return getByte(getResourceValue(resourceBundle, Locale.getDefault(), key));
    }

    public static final byte getByte(String value) throws DataConvertException {
        if (StringUtils.isBlank(value)) throw new DataConvertException(value, new NullPointerException());
        byte result = 0;
        try {
            result = Byte.parseByte(value.trim());
        } catch (NumberFormatException e) {
            throw new DataConvertException(value, e);
        }
        return result;
    }






    @SuppressWarnings("rawtypes")
    public static final short getShort(Map m, Object key,
            short defaultValue) {
        if(null==m) return defaultValue;
        return getShort((String) m.get(key), defaultValue);
    }

    public static final short getShort(String resourceBundle,
            String key, short defaultValue) {
        return getShort(getResourceValue(resourceBundle, Locale.getDefault(), key), defaultValue);
    }

    public static final short getShort(String value, short defaultValue) {
        short result = defaultValue;
        try {
            result = getShort(value);
        } catch (DataConvertException e) {
            logger.warn("value ["+value+"] convert error!", e.getNestedException());
        }
        return result;
    }

    @SuppressWarnings("rawtypes")
    public static final short getShort(Map m, Object key) throws DataConvertException {
        return getShort((String) (null==m?null:m.get(key)));
    }

    public static final short getShort(String resourceBundle,
            String key) throws DataConvertException  {
        return getShort(getResourceValue(resourceBundle, Locale.getDefault(), key));
    }

    public static final short getShort(String value)  throws DataConvertException {
        if (StringUtils.isBlank(value)) throw new DataConvertException(value, new NullPointerException());;
        short result = 0;
        try {
            result = Short.parseShort(value.trim());
        } catch (NumberFormatException e) {
            throw new DataConvertException(value, e);
        }
        return result;
    }















    @SuppressWarnings("rawtypes")
    public static final int getInt(Map m, Object key,
            int defaultValue) {
        if(null==m) return defaultValue;
        return getInt((String) m.get(key), defaultValue);
    }

    public static final int getInt(String resourceBundle,
            String key, int defaultValue) {
        return getInt(getResourceValue(resourceBundle, Locale.getDefault(), key), defaultValue);
    }

    public static final int getInt(String value, int defaultValue) {
        int result = defaultValue;
        try {
            result = getInt(value);
        } catch (DataConvertException e) {
            logger.warn("value ["+value+"] convert error!", e.getNestedException());
        }
        return result;
    }

    @SuppressWarnings("rawtypes")
    public static final int getInt(Map m, Object key)  throws DataConvertException {
        return getInt((String) (null==m?null:m.get(key)));
    }

    public static final int getInt(String resourceBundle,
            String key) throws DataConvertException  {
        return getInt(getResourceValue(resourceBundle, Locale.getDefault(), key));
    }

    public static final int getInt(String value) throws DataConvertException  {
        if (StringUtils.isBlank(value)) throw new DataConvertException(value, new NullPointerException());;
        int result = 0;
        try {
            result = Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new DataConvertException(value, e);
        }
        return result;
    }
















    @SuppressWarnings("rawtypes")
    public static final long getLong(Map m, Object key,
            long defaultValue) {
        if(null==m) return defaultValue;
        return getLong((String) m.get(key), defaultValue);
    }

    public static final long getLong(String resourceBundle,
            String key, long defaultValue) {
        return getLong(getResourceValue(resourceBundle, Locale.getDefault(), key), defaultValue);
    }

    public static final long getLong(String value, long defaultValue) {
        long result = defaultValue;
        try {
            result = getLong(value);
        } catch (DataConvertException e) {
            logger.warn("value ["+value+"] convert error!", e.getNestedException());
        }
        return result;
    }

    @SuppressWarnings("rawtypes")
    public static final long getLong(Map m, Object key) throws DataConvertException  {
        return getLong((String) (null==m?null:m.get(key)));
    }

    public static final long getLong(String resourceBundle,
            String key) throws DataConvertException {
        return getLong(getResourceValue(resourceBundle, Locale.getDefault(), key));
    }

    public static final long getLong(String value) throws DataConvertException {
        if (StringUtils.isBlank(value)) throw new DataConvertException(value);;
        long result = 0;
        try {
            result = Long.parseLong(value.trim());
        } catch (NumberFormatException e) {
            throw new DataConvertException(value, e);
        }
        return result;
    }














    @SuppressWarnings("rawtypes")
    public static final float getFloat(Map m, Object key,
            float defaultValue) {
        if(null==m) return defaultValue;
        return getFloat((String) m.get(key), defaultValue);
    }

    public static final float getFloat(String resourceBundle,
            String key, float defaultValue) {
        return getFloat(getResourceValue(resourceBundle, Locale.getDefault(), key), defaultValue);
    }

    public static final float getFloat(String value, float defaultValue) {
        float result = defaultValue;
        try {
            result = getFloat(value);
        } catch (DataConvertException e) {
            logger.warn("value ["+value+"] convert error!", e.getNestedException());
        }
        return result;
    }

    @SuppressWarnings("rawtypes")
    public static final float getFloat(Map m, Object key) throws DataConvertException {
        return getFloat((String) (null==m?null:m.get(key)));
    }

    public static final float getFloat(String resourceBundle,
            String key) throws DataConvertException {
        return getFloat(getResourceValue(resourceBundle, Locale.getDefault(), key));
    }

    public static final float getFloat(String value) throws DataConvertException {
        if (StringUtils.isBlank(value)) throw new DataConvertException(value, new NullPointerException());;
        float result = 0.0f;
        try {
            result = Float.parseFloat(value.trim());
        } catch (NumberFormatException e) {
            throw new DataConvertException(value, e);
        }
        return result;
    }














    @SuppressWarnings("rawtypes")
    public static final double getDouble(Map m, Object key,
            double defaultValue) {
        if(null==m) return defaultValue;
        return getDouble((String) m.get(key), defaultValue);
    }

    public static final double getDouble(String resourceBundle,
            String key, double defaultValue) {
        return getDouble(getResourceValue(resourceBundle, Locale.getDefault(), key), defaultValue);
    }

    public static final double getDouble(String value, double defaultValue) {
        double result = defaultValue;
        try {
            result = getDouble(value);
        } catch (DataConvertException e) {
            logger.warn("value ["+value+"] convert error!", e.getNestedException());
        }
        return result;
    }

    @SuppressWarnings("rawtypes")
    public static final double getDouble(Map m, Object key) throws DataConvertException {
        return getDouble((String) (null==m?null:m.get(key)));
    }

    public static final double getDouble(String resourceBundle,
            String key) throws DataConvertException {
        return getDouble(getResourceValue(resourceBundle, Locale.getDefault(), key));
    }

    public static final double getDouble(String value) throws DataConvertException {
        if (StringUtils.isBlank(value)) throw new DataConvertException(value, new NullPointerException());;
        double result = 0.0;
        try {
            result = Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            throw new DataConvertException(value, e);
        }
        return result;
    }


















    /**
     *
     * @param m
     * @param key
     * @param defaultValue
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static final Date getDate(Map m, Object key,
            Date defaultValue) {
        if(null==m) return defaultValue;
        return getDate((String) m.get(key), defaultValue);
    }

    /**
     *
     * @param resourceBundle
     * @param key
     * @param defaultValue
     * @return
     */
    public static final Date getDate(String resourceBundle,
            String key, Date defaultValue) {
        return getDate(getResourceValue(resourceBundle, Locale.getDefault(), key), defaultValue);
    }

    /**
     *
     * @param value a <code>String</code> object representing a date in
     *        in the format "yyyy-mm-dd" or "yyyy/mm/dd"
     * @param defaultValue
     * @return
     */
    public static final Date getDate(String value, Date defaultValue) {
        Date result = defaultValue;
        try {
            result = getDate(value);
        } catch (DataConvertException e) {
            logger.warn("value ["+value+"] convert error!", e.getNestedException());
        }
        return result;
    }

    /**
     *
     * @param m
     * @param key
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static final Date getDate(Map m, Object key) throws DataConvertException {
        return getDate((String) (null==m?null:m.get(key)));
    }

    /**
     *
     * @param resourceBundle
     * @param key
     * @return
     */
    public static final Date getDate(String resourceBundle,
            String key) throws DataConvertException {
        return getDate(getResourceValue(resourceBundle, Locale.getDefault(), key));
    }

    /**
     *
     * @param value a <code>String</code> object representing a date in
     *        in the format "yyyy-mm-dd" or "yyyy/mm/dd"
     * @return
     */
    public static final Date getDate(String value) throws DataConvertException {
        if (StringUtils.isBlank(value)) throw new DataConvertException(value, new NullPointerException());;
        Date result = null;
        String v = (value.indexOf('/')==-1)?value:value.replace('/', '-');
        try {
            result = Date.valueOf(v.trim());
        } catch (IllegalArgumentException e) {
            throw new DataConvertException(value, e);
        }
        return result;
    }















    @SuppressWarnings("rawtypes")
    public static final Time getTime(Map m, Object key,
            Time defaultValue) {
        if(null==m) return defaultValue;
        return getTime((String) m.get(key), defaultValue);
    }

    public static final Time getTime(String resourceBundle,
            String key, Time defaultValue) {
        return getTime(getResourceValue(resourceBundle, Locale.getDefault(), key), defaultValue);
    }

    /**
     *
     * @param value a <code>String</code> object representing a Time in
     *        in the format "hh:mm:ss"
     * @param defaultValue
     * @return
     */
    public static final Time getTime(String value, Time defaultValue) {
        Time result = defaultValue;
        try {
            result = getTime(value);
        } catch (DataConvertException e) {
            logger.warn("value ["+value+"] convert error!", e.getNestedException());
        }
        return result;
    }


    @SuppressWarnings("rawtypes")
    public static final Time getTime(Map m, Object key) throws DataConvertException {
        return getTime((String) (null==m?null:m.get(key)));
    }

    public static final Time getTime(String resourceBundle,
            String key) throws DataConvertException {
        return getTime(getResourceValue(resourceBundle, Locale.getDefault(), key));
    }

    /**
     *
     * @param value a <code>String</code> object representing a Time in
     *        in the format "hh:mm:ss"
     * @return
     */
    public static final Time getTime(String value) throws DataConvertException {
        if (StringUtils.isBlank(value)) throw new DataConvertException(value, new NullPointerException());
        Time result = null;
        try {
            result = Time.valueOf(value.trim());
        } catch (IllegalArgumentException e) {
            throw new DataConvertException(value, e);
        }
        return result;
    }
















    @SuppressWarnings("rawtypes")
    public static final Timestamp getTimestamp(Map m, Object key,
            Timestamp defaultValue) {
        if(null==m) return defaultValue;
        return getTimestamp((String) m.get(key), defaultValue);
    }

    public static final Timestamp getTimestamp(String resourceBundle,
            String key, Timestamp defaultValue) {
        return getTimestamp(getResourceValue(resourceBundle, Locale.getDefault(), key), defaultValue);
    }

    /**
     *
     * @param value a <code>String</code> object representing a Timestamp in
     *        in the format "yyyy-mm-dd hh:mm:ss.fffffffff"
     * @param defaultValue
     * @return
     */
    public static final Timestamp getTimestamp(String value, Timestamp defaultValue) {
        Timestamp result = defaultValue;
        try {
            result = getTimestamp(value);
        } catch (DataConvertException e) {
            logger.warn("value ["+value+"] convert error! return default value {}", e.getNestedException(), defaultValue);
        }
        return result;
    }

    @SuppressWarnings("rawtypes")
    public static final Timestamp getTimestamp(Map m, Object key) throws DataConvertException {
        return getTimestamp((String) (null==m?null:m.get(key)));
    }

    public static final Timestamp getTimestamp(String resourceBundle,
            String key) throws DataConvertException {
        return getTimestamp(getResourceValue(resourceBundle, Locale.getDefault(), key));
    }

    /**
     *
     * @param value a <code>String</code> object representing a Timestamp in
     *        in the format "yyyy-mm-dd hh:mm:ss.fffffffff"
     * @return
     */
    public static final Timestamp getTimestamp(String value) throws DataConvertException {
        if (StringUtils.isBlank(value)) throw new DataConvertException(value, new NullPointerException());;
        Timestamp result = null;
        String v = (value.indexOf('/')==-1)?value:value.replace('/', '-');
        try {
            result = Timestamp.valueOf(v.trim());
        } catch (IllegalArgumentException e) {
            throw new DataConvertException(value, e);
        }
        return result;
    }






    public static final String getString(String s, String defaultValue) {
        return StrUtil.isEmpty(s)?defaultValue:s;
    }

    @SuppressWarnings("rawtypes")
    public static final String getString(Map m, String key, String defaultValue) {
        if(null==m) return defaultValue;
        return getObjectAsString(m.get(key), defaultValue);
    }

    public static final String getString(Object o) {
        return getObjectAsString(o, null);
    }

    /**
     *
     * @param o
     * @param defaultValue
     * @return
     */
    public static final String getObjectAsString(Object o, String defaultValue) {
        if(null==o) return defaultValue;
        if (o instanceof String) {
            return (String) o;
        }
        if (o instanceof String[]) {
            String[] arr = (String[]) o;
            if(arr.length==0) return defaultValue;
            if(arr.length==1) return getString(arr[0], defaultValue);
            return StrUtil.join(',', arr);
        }
        if (o instanceof java.util.Date) {
            if (o instanceof java.sql.Date) {
                java.sql.Date d = (java.sql.Date) o;
                return DateFormatGroup.DATE.format(d);
            }
            if (o instanceof java.sql.Timestamp) {
                java.sql.Timestamp d = (java.sql.Timestamp) o;
                return DateFormatGroup.DATETIME.format(d);
            }
//            if (o instanceof java.sql.Time) {
//                java.sql.Timestamp d = (java.sql.Timestamp) o;
//                return DateUtil.formatDateTime();
//            }
        }
        return o.toString();
    }
    
    public static List<String> getList(String s, List<String> defaultValue) {
        List<String> result = StrUtil.splitGeneralToList(s);
        return result.isEmpty()?defaultValue:result;
    }
    
    public static List<String> getList(String resourceBundle, String key, List<String> defaultValue) {
        String s = getResourceValue(resourceBundle, key, null);
        List<String> result = StrUtil.splitGeneralToList(s);
        return result.isEmpty()?defaultValue:result;
    }
    
    @SuppressWarnings("rawtypes")
    public static List<String> getList(Map m, Object key, List<String> defaultValue) {
        List<String> result = StrUtil.splitGeneralToList(((null==m)?null:m.get(key)).toString());
        return result.isEmpty()?defaultValue:result;
    }


    /**
     * Get s as Integer list
     * @param s
     * @param defaultValue
     * @return
     */
    public static List<Integer> getIntList(String s, List<Integer> defaultValue) {
        List<Integer> result = StrUtil.splitToIntegerList(s);
        return result.isEmpty()?defaultValue:result;
    }

    public static List<Integer> getIntList(String resourceBundle, String key, List<Integer> defaultValue) {
        String s = getResourceValue(resourceBundle, key, null);
        return getIntList(s, defaultValue);
    }

    @SuppressWarnings("rawtypes")
    public static List<Integer> getIntList(Map m, Object key, List<Integer> defaultValue) {
        String s = ((null == m) ? null : m.get(key)).toString();
        return getIntList(s, defaultValue);
    }

    /**
     * Get s as Integer set
     * @param s
     * @param defaultValue
     * @return
     */
    public static Set<Integer> getIntSet(String s, Set<Integer> defaultValue) {
        Set<Integer> result = StrUtil.splitToIntegerSet(s);
        return result.isEmpty()?defaultValue:result;
    }

    public static Set<Integer> getIntSet(String resourceBundle, String key, Set<Integer> defaultValue) {
        String s = getResourceValue(resourceBundle, key, null);
        return getIntSet(s, defaultValue);
    }

    public static Set<Integer> getIntSet(Map m, Object key, Set<Integer> defaultValue) {
        String s = ((null == m) ? null : m.get(key)).toString();
        return getIntSet(s, defaultValue);
    }


    /**
     *
     * @param <T>
     * @param o
     * @param default_value
     * @return
     */
    public static final <T> T noNull(T o, T default_value) {
        return null==o?default_value:o;
    }

    /**
     *
     * @param <T>
     * @param o
     * @param default_value
     * @return
     */
    public static final <T> T noNull(T o, T o1, T default_value) {
        return null!=o?o:(o1==null?default_value:o1);
    }

    /**
     *
     * @param <T>
     * @param o
     * @param default_value
     * @return
     */
    public static final <T> T noNull(T o, T o1, T o2, T default_value) {
        if(null != o) return o;
        if(null != o1) return o1;
        if(null != o2) return o2;
        return default_value;
    }


    /**
     *
     * @param m
     * @param key
     * @param default_value
     * @param <K>
     * @param <V>
     * @return 如果 m 中没有值的话就返回 default_value
     */
    public static <K, V> V noNull(Map<K,V> m, K key, V default_value) {
        if(null==m) return default_value;
        V result = m.get(key);
        return null==result?default_value:result;
    }


    /**
     * Get first non-null object from the input array
     * @param <T>
     * @param o
     * @return
     */
    public static final <T> T pick(T ... o) {
        if(ArrayUtils.isEmpty(o)) return null;
        for(int i=0; i<o.length; i++) {
            if(null != o[i]) return o[i];
        }
        return null;
    }

    public static List<Integer> parseToIntegerList(String s) {
        List<Integer> result = new ArrayList<Integer>();
        parseToIntegerCollection(s, result);
        return result;
    }

    public static Set parseToIntegerSet(String s) {
        HashSet<Integer> result = new HashSet<Integer>();
        parseToIntegerCollection(s, result);
        return result;
    }

    public static void parseToIntegerCollection(String s, Collection collection) {
        if(StringUtils.isBlank(s)) return;
        List<String> values = UtilGroup.splitToList(s);
        List<Integer> integers = new ArrayList<Integer>(values.size());
        try {
            for (String value : values) {
                integers.add(Integer.valueOf(value));
            }
        } catch (Exception e) {
            logger.error("parse value " + s + " to IntegerCollection error.", e);
            return;
        }
        collection.addAll(integers);
    }

    public static Map<String, String> parseToMap(String s) {
        // TODO:
        return null;

    }

    public static Map<String, Integer> parseToMapStringInteger(String s) {
        // TODO:
        return null;

    }

}


