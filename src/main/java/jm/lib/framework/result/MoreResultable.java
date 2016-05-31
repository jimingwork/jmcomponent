package jm.lib.framework.result;

import jm.lib.common.entity.Codeable;

/**
 * Created by jiming.liu on 2015/5/5.
 * 用于详细结果的接口
 */
public interface MoreResultable extends Codeable<Integer> {
    /**
     * 返回值编码，不一定是 error，所以叫 code
     */
    Integer getCode();

    String getNextUrl();
}
