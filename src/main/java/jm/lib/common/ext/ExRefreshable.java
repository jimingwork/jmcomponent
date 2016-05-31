package jm.lib.common.ext;

import java.util.Collection;

import jm.lib.common.Refreshable;
import jm.lib.common.collections.common.IdName;
import jm.lib.framework.exception.RefreshException;


/**
 * 这个接口主要是为了后台管理页面使用的,它主要被用来刷新缓存、重新载入配置等
 * @author Jiming Liu
 * @deprecated can use Treeable?
 */
public interface ExRefreshable extends Refreshable {
    String getName();


    /**
     * 获得这个 bean 的所有支持 Refreshable 的子 bean
     * @return
     */
    Collection<Refreshable> getChildren();

    /**
     * 获得这个 bean 下面的所有可刷新项
     * @return
     */
    Collection<IdName> getItems();

    /**
     * 刷新指定项
     * @param item_id
     * @throws RefreshException
     */
    void refresh(int item_id) throws RefreshException;

    /**
     * 获得整个对象的刷新项的状态，不包括子对象
     * @return
     */
    String getStatus();

    /**
     * 获得刷新项的状态
     * @param id
     * @return
     */
    String getStatus(int id);

    /**
     * 只有是 root 的 bean 才会被在初始化的时候被保存起来
     * @return
     */
    boolean isRoot();

}
