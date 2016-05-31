/**
 * Create at 2008-12-3 by jiming.liu
 * $Id$
 */

package jm.lib.common.entity;

import java.sql.Timestamp;

/**
 * update timestamp field for a bean
 * @author jiming.liu
 *
 */
public interface Timestampable {
//    Timestamp getCreatedTime();
//    
//    Timestamp getUpdatedTime();
    
    void touchCreatedTime();

    void touchCreatedTime(long timestamp);

    void touchUpdatedTime();

    void touchUpdatedTime(long timestamp);

}
