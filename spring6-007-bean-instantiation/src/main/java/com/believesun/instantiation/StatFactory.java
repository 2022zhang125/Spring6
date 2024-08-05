package com.believesun.instantiation;

/**
 * 静态工厂方法模式（简单工厂模式）
 */
public class StatFactory {
    public static Stat get(){
        return new Stat();
    }
}
