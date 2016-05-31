package jm.lib.common.entity.write;

import jm.lib.common.entity.Ownerable;

/**
 * Created by jiming.liu on 2015/12/10.
 */
public interface SetOwnerable extends Ownerable {
    void setOwnerId(int ownerId);
}
