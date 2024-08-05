package com.believesun.simple.factory;

/**
 * 具体产品角色
 */
public class Gun extends Weapon{
    @Override
    public void attack() {
        System.out.println("哒哒哒哒哒哒！！！");
    }
}
