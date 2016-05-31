package jm.lib.common.entity.write;

import jm.lib.common.entity.Statusable;

/**
 * Created by jiming.liu on 2015/12/9.
 */
public interface SetStatusable extends Statusable {
    void setStatus(short status);
}
