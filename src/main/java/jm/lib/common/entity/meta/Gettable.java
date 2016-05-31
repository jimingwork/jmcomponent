package jm.lib.common.entity.meta;

/**
 * Created by jiming.liu on 2015/12/10.
 */
public interface Gettable {
    <T> T getProperty(String property, Class<T> clazz);
}
