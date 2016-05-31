/**
 * Create at 2009-2-11 by jiming.liu
 * $Id$
 */

package jm.lib.common.entity.ext;

import jm.lib.common.entity.Ownerable;

/**
 * 
 * @author Jiming
 * @deprecated not sure yet
 */
public interface ExOwnerable extends Ownerable {
    /**
     * 显示名称，比如定损状态时的定损员，核损状态时的核损员
     * @return
     */
    String getOwnerDispName();
    /**
     * bean property name
     * @return
     */
    String getOwnerBeanName();

}
