package jm.lib.common.entity.write;

import jm.lib.common.entity.UpdatedTimeable;

import java.sql.Timestamp;

/**
 * Created by jiming.liu on 2015/12/10.
 */
public interface SetUpdatedTimeable extends UpdatedTimeable {
    void setUpdatedTime(Timestamp timestamp);
}
