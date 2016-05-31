/**
 * Create at 2008-12-4 by jiming.liu
 * $Id$
 */

package jm.lib.common.entity;

import java.util.Collection;
import java.util.List;

public interface Treeable<T extends Treeable<T>> extends Hierarchicalable<T> {
    List<T> getChildren();
    void setChildren(List<T> children);
    boolean addChildren(Collection<T> children);
    boolean isTop();
    boolean isHasChildren();
}
