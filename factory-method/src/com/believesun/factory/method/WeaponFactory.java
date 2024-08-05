package com.believesun.factory.method;

/**
 * 抽象工厂角色
 */
abstract public class WeaponFactory {
    public abstract Weapon get();
    public static WeaponFactory getFactory(String factoryType) {
        if ("GUNFactory".equals(factoryType)) {
            return new GunFactory();
        }else if("TANKFactory".equals(factoryType)){
            return new TankFactory();
        }else{
            throw new RuntimeException("no factory of this");
        }
    }
}
