package jm.lib.framework.result;

/**
 *
 *
 * @author Jiming Liu
 * @deprecated should think deeper
 */
public interface CountResult {
    /**
     * 获得记录数
     * @return
     */
    long getCount();

    /**
     * 是否还有更多的纪录
     * @return
     */
    boolean hasMore();
}
