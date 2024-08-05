package com.believesun.simple.factory;

/**
 * Weapon武器工厂类
 */
public class WeaponFactory {
    public static Weapon get(String weaponType){
        if ("TANK".equals(weaponType)) {
            return new Tank();
        }else if("GUN".equals(weaponType)){
            return new Gun();
        }else{
            throw new RuntimeException("不支持该类型武器的生产");
        }
    }
}
