package jm.lib.framework.exception;

/**
 * Created by jiming.liu on 2016/3/9.
 * When exception happens, some infomation should put in log for debug.
 * And some others need to display to customer, which need to be human friendly.
 */
public interface HumanException {
    /**
     * Get exception message for human read
     * @return
     */
    String getHumanMessage();
}
