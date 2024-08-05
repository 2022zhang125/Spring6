# Factory Method Pattern （工厂方法模式）

## 组成
    * 抽象产品角色
        abstract Weapon 
    * 具体产品角色
        Gun extends Weapon
        Tank extends Weapon
    * 抽象工厂角色
        abstract WeaponFactory
            abstract get();
            static default getFactory(String factoryType) ----> 使用简单工厂模式（静态工厂方法模式）
    * 具体工厂角色
        GunFactory extends WeaponFactory
        TankFactory extends WeaponFactory

## 优点
    ● 一个调用者想创建一个对象，只要知道其名称就可以了。 
    ● 扩展性高，如果想增加一个产品，只要扩展一个工厂类就可以。
    ● 屏蔽产品的具体实现，调用者只关心产品的接口。
        与简单工厂模式不同，工厂方法模式符合了OCP原则，当用户想要添加一个武器的时候，只需要创建类及其工厂类即可，不需要修改其原先代码
        解决了上帝类的存在，一个Factory崩了，也就崩一个

## 缺点
    每次增加一个产品时，都需要增加一个具体类和对象实现工厂，使得系统中类的个数成倍增加，在一定程度上增加了系统的复杂度，同时也增加了系统具体类的依赖。这并不是什么好事。
    会导致类爆炸。