package jm.lib.common.entity.write;

import jm.lib.common.entity.Statusable;

/**
 * Created by jiming.liu
 */
public interface SetStatusable extends Statusable {
    void setStatus(short status);
}
