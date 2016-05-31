package jm.lib.common.entity.write;

import jm.lib.common.entity.CreatedTimeable;

import java.sql.Timestamp;

/**
 * Created by jiming.liu
 */
public interface SetCreatedTimeable extends CreatedTimeable {
    void setCreatedTime(Timestamp timestamp);
}
