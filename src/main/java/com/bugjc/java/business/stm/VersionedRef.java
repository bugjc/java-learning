package com.bugjc.java.business.stm;

/**
 * 带版本号的对象引用
 *
 * @author aoki
 * @date 2021/4/8
 **/
public final class VersionedRef<T> {
    final T value;
    final long version;

    //构造方法
    public VersionedRef(T value, long version) {
        this.value = value;
        this.version = version;
    }
}
