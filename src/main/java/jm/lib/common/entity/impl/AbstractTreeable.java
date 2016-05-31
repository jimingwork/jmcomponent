/**
 * Create at 2008-12-5 by jiming.liu
 * $Id$
 */

package jm.lib.common.entity.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import jm.lib.common.entity.Treeable;
import org.apache.commons.collections4.CollectionUtils;

public abstract class AbstractTreeable<T extends Treeable<T>> implements Treeable<T> {

    private transient T parent;
    private transient List<T> children;
    /**
     * @return the parent
     */
    @Override
    public T getParent() {
        return parent;
    }
    /**
     * @param parent the parent to set
     */
    @Override
    public void setParent(T parent) {
        this.parent = parent;
    }
    /**
     * @return the children
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> getChildren() {
        return null==children?Collections.EMPTY_LIST:children;
    }
    /**
     * @param children the children to set
     */
    @Override
    public void setChildren(List<T> children) {
        this.children = children;
    }

    @Override
    public boolean isTop() {
        return null==parent;
    }

    @Override
    public boolean isHasChildren() {
        return CollectionUtils.isNotEmpty(children);
    }
    
    
    @Override
    public boolean addChildren(Collection<T> children) {
        if(CollectionUtils.isEmpty(children)) return true;
        if(null== this.children) {
            this.children = new ArrayList<T>();
        }
        this.children.addAll(children);
        
        return true;
    }


}
