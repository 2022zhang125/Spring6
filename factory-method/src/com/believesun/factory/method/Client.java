package com.believesun.factory.method;

/**
 * Client客户端程序
 */
public class Client {
    public static void main(String[] args) {
        /*WeaponFactory gunFactory = new GunFactory();
        Weapon gun = gunFactory.get();
        gun.attack();

        WeaponFactory tankFactory = new TankFactory();
        Weapon tank = tankFactory.get();
        tank.attack();*/
        // 使用简单工厂模式生产工厂对象，用工厂方法模式生成武器对象
        WeaponFactory gunFactory = WeaponFactory.getFactory("GUNFactory");
        Weapon gun = gunFactory.get();
        gun.attack();

        WeaponFactory.getFactory("TANKFactory").get().attack();
    }
}
