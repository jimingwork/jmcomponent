/**
 * Create at 2008-12-5 by jiming.liu
 * $Id$
 */

package jm.lib.common.entity.impl;

import java.util.ArrayList;
import java.util.List;

import jm.lib.common.ext.ExTreeable;

public class AbstractExTreeable<T extends ExTreeable<T>> extends AbstractTreeable<T> implements ExTreeable<T>{

    @Override
    public boolean isChildOf(T t) {
        if(isTop()) return false;
        return getParent().equals(t);
    }

    @Override
    public boolean isDescendantOf(T t) {
        if(isTop()) return false;
        if(isChildOf(t)) return true;
        return getParent().isDescendantOf(t);
    }

    @Override
    public boolean isSiblingOf(T t) {
        if(isTop()) return t.isTop();
        return getParent().equals(t.getParent());
    }

    @Override
    public List<T> getAncestors() {
        List<T> result = new ArrayList<T>();
        if(isTop()) return result;
        T parent = this.getParent();
        result.add(parent);
        while((parent=parent.getParent())!=null) {
            result.add(parent);
        }

        return result;
    }

    @Override
    public List<T> getDescendants() {
        List<T> result = new ArrayList<T>();
        List<T> c = this.getChildren();
        result.addAll(c);
        for(T v: c) {
            result.addAll(v.getDescendants());
        }
        return result;
    }


}
