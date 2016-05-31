package jm.lib.common.entity.write;

import jm.lib.common.entity.Ownerable;

/**
 * Created by jiming.liu
 */
public interface SetOwnerable extends Ownerable {
    void setOwnerId(int ownerId);
}
