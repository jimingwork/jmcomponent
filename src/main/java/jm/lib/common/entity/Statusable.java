package jm.lib.common.entity;

/**
 * 这里没有采用泛型是因为 status 用量太大，如果不用 wrapper class 的话性能会好一些
 *
 *
 */
public interface Statusable {
    short getStatus();
}
