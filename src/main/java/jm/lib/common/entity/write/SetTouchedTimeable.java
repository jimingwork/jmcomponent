package jm.lib.common.entity.write;

import jm.lib.common.entity.TouchedTimeable;

import java.sql.Timestamp;

/**
 * Created by jiming.liu
 */
public interface SetTouchedTimeable extends TouchedTimeable {
    void setTouchedTime(Timestamp timestamp);
}
