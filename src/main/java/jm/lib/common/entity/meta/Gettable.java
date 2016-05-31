package jm.lib.common.entity.meta;

/**
 * Created by jiming.liu
 */
public interface Gettable {
    <T> T getProperty(String property, Class<T> clazz);
}
