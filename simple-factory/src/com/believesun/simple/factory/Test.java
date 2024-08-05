package com.believesun.simple.factory;

/**
 * Client客户端程序
 */
public class Test {
    public static void main(String[] args) {
        Weapon gun = WeaponFactory.get("GUN");
        gun.attack();

        Weapon tank = WeaponFactory.get("TANK");
        tank.attack();
    }
}
