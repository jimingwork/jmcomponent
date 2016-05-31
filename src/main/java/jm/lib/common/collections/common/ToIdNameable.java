/**
 * Create at 2008-11-10 by jiming.liu
 * $Id$
 */

package jm.lib.common.collections.common;

import jm.lib.common.entity.IdNameable;

import java.io.Serializable;

public interface ToIdNameable<T extends Serializable> {
    IdNameable<T> getIdName();
}
