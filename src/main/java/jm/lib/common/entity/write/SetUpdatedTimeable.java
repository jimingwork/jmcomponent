package jm.lib.common.entity.write;

import jm.lib.common.entity.UpdatedTimeable;

import java.sql.Timestamp;

/**
 * Created by jiming.liu
 */
public interface SetUpdatedTimeable extends UpdatedTimeable {
    void setUpdatedTime(Timestamp timestamp);
}
