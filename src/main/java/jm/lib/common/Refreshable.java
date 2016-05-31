package jm.lib.common;


import com.google.common.annotations.Beta;
import jm.lib.framework.exception.RefreshException;

@Beta
public interface Refreshable {
    void refresh() throws RefreshException;

    long getLastRefreshTime();

    boolean needRefresh();
}
