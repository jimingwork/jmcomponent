/**
 * Create at 2008-12-31 by jiming.liu
 * $Id$
 */

package jm.lib.common.entity.impl;

import com.google.common.annotations.Beta;
import jm.lib.common.entity.meta.PojoInfo;


@Beta
public abstract class AbstractPojoInfo<T> implements PojoInfo<T> {

    /**
     *
     * @see jm.lib.common.entity.meta.PojoInfo#getRegionName(jm.lib.context.RequestContext)
     */
//    public String getRegionName(RequestContext context) {
//        Shard shard = context.getShard(getTableName());
//        if(null == shard || Shard.EMPTY_INSTANCE == shard) return getMessagePrefix();
//        String uid = shard.getUid();
//        if(StrUtil.isEmpty(uid)) return getMessagePrefix();
//        return getMessagePrefix() + shard.getUid() + ".";
//    }




}
