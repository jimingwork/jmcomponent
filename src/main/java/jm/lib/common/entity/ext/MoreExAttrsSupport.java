package jm.lib.common.entity.ext;

import jm.lib.framework.exception.impl.DataConvertException;
import jm.lib.util.ConfUtil;
import jm.lib.util.JsonUtil;
import jm.lib.util.StrUtil;
import jm.lib.util.UtilGroup;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by jiming.liu on 2016/1/15.
 */
public interface MoreExAttrsSupport extends ExAttrsSupport {

    Logger getLogger();

    default Set<Integer> attrAsSetInt(String key) throws DataConvertException {
        String value = attr(key);
        if (StringUtils.isBlank(value)) {
            return Collections.EMPTY_SET;
        }
        return StrUtil.splitToIntegerSet(value);
    }
    default Set<String> attrAsSet(String key) throws DataConvertException {
        String value = attr(key);
        if (StringUtils.isBlank(value)) {
            return Collections.EMPTY_SET;
        }
        return StrUtil.splitGeneralToSet(value);
    }

    default List<Integer> attrAsListInt(String key) throws DataConvertException {
        String value = attr(key);
        if (StringUtils.isBlank(value)) {
            return Collections.emptyList();
        }
        return StrUtil.splitToIntegerList(value);
    }

    default List<String> attrAsList(String key) throws DataConvertException {
        String value = attr(key);
        if (StringUtils.isBlank(value)) {
            return Collections.emptyList();
        }
        return ConfUtil.getList(value, Collections.emptyList());
    }

    default Map<String, String> attrAsMap(String key) throws DataConvertException {
        String value = attr(key);
        if (StringUtils.isBlank(value)) {
            return Collections.EMPTY_MAP;
        }
        try {
            Map<String, String> values = UtilGroup.splitToMap(value);
            return values;
        } catch (Throwable e) {
            String message = String.format("attrAsMap: read key %s with value %s to Map error", key, value);
            getLogger().warn(message, e);
        }
        return Collections.EMPTY_MAP;
    }


    default <T> T attrAsObject(String key, Class<T> clazz) throws DataConvertException {
        String value = attr(key);
        if (StringUtils.isBlank(value)) {
            return null;
        }

        try {
            T t = JsonUtil.mapObjectWithException(value, clazz);
            return t;
        } catch (Exception e) {
            String message = String.format("attrAsObject: read key %s with value %s to class %s error", key, value, clazz.toString());
            getLogger().error(message, e);
        }
        return null;
    }

    // TODO: later, ${SpEL} and #{SpEL}
//    Object attrAsExpr(String key);
//    Object attrAsExpr(String key, Object params);
}
