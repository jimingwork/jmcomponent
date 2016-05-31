/**
 * Create at 2009-2-16 by jiming.liu
 * $Id$
 */

package jm.lib.common.entity.meta;

import jm.lib.framework.exception.impl.DataConvertException;
import jm.lib.framework.result.Result;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.Map;

/**
 *
 * @author jiming.liu
 *
 */
public interface Populateable {
    /**
     *
     * @param name
     * @param value
     * @return
     */
    Result populateProperty(String name, Object value) throws DataConvertException;

    default Errors populate(Map<String, ?> values) {
        BeanPropertyBindingResult result = new BeanPropertyBindingResult(values, "none");
        for (Map.Entry<String, ?> entry : values.entrySet()) {
            try {
                Result ret = populateProperty(entry.getKey(), entry.getValue());
                if (!ret.isRet()) {
                    result.rejectValue(entry.getKey(), "error", ret.getMsg());
                }
            } catch (DataConvertException e) {
                result.rejectValue(entry.getKey(), "error", e.getMessage());
            }

        }

        return result;
    }


}
