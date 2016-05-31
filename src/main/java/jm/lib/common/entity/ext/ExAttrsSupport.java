package jm.lib.common.entity.ext;

import jm.lib.common.entity.meta.AttrsSupport;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author Jiming
 *
 */
public interface ExAttrsSupport extends AttrsSupport, ConfigAttrsSupport {
    default String attr(String key) {
        return getAttrs().get(key);
    }

    default String attr(String key, String default_value) {
        String attr = attr(key);
        if(null == attr) {
            return default_value;
        }
        return attr;
    }

    default boolean attrAsBool(String key, boolean default_value) {
        String attr = attr(key);
        if(StringUtils.isBlank(attr)) {
            return default_value;
        }
        return BooleanUtils.toBoolean(attr);
    }

    default int attrAsInt(String key, int default_value) {
        String attr = attr(key);
        if(StringUtils.isBlank(attr)) {
            return default_value;
        }
        try {
            int i = Integer.parseInt(attr);
            return i;
        } catch (NumberFormatException e) {
            return default_value;
        }
    }
    default long attrAsLong(String key, long default_value) {
        String attr = attr(key);
        if(StringUtils.isBlank(attr)) {
            return default_value;
        }
        try {
            long i = Long.parseLong(attr);
            return i;
        } catch (NumberFormatException e) {
            return default_value;
        }
    }

    default float attrAsFloat(String key, float default_value) {
        String attr = attr(key);
        if(StringUtils.isBlank(attr)) {
            return default_value;
        }
        try {
            float i = Float.parseFloat(attr);
            return i;
        } catch (NumberFormatException e) {
            return default_value;
        }
    }

}

