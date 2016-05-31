/**
 * Create at 2008-11-7 by jiming.liu
 * $Id$
 */

package jm.lib.common.collections.common;

import java.util.Map;


/**
 *
 * @author jiming.liu
 *
 * @param <M>
 */
public class ObjectMapExtra<M> extends ObjectExtra<M, Map<String,?>> {

    public ObjectMapExtra() {};


    public ObjectMapExtra(M self, Map<String, ?> extra) {
        super(self, extra);
    }

}
