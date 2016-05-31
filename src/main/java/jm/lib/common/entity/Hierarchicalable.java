package jm.lib.common.entity;


public interface Hierarchicalable<T> {
    T getParent();
    void setParent(T parent);

}
