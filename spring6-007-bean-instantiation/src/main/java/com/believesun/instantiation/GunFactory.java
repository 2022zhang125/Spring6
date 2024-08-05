package com.believesun.instantiation;

/**
 * 使用工厂方法模式
 */
public class GunFactory {
    // 这里是实例方法，而不是静态方法
    public Gun get(){
        return new Gun();
    }
}
