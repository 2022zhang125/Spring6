# Bean的生命周期（粗略版本）
    五步：
        第一步：实例化Bean对象（调用无参数构造方法）
        第二步：给Bean对象赋值（调用Set方法）
                检查是否实现了 BeanNameAware,BeanClassLoaderAware,BeanFactoryAware(Aware 明白，认识，察觉)
                为了给于用户一些已知信息。
            Bean后处理器before
                检查是否实现了 InitializingBean接口
                为了在初始化之前进行操作。
        第三步：初始化Bean对象（调用initBean方法，自己写的，方法名随意。）
            Bean后处理器after
        第四步：使用Bean对象
                检查是否实现了 DisposableBean接口
        第五步：摧毁Bean对象（调用destroyBean方法，也是自己写的。）

## 注意
    该生命周期只针对 singleton对象也就是单例对象，对于prototype对象，只会执行前八步（也就是会死在使用bean那一步，后面的DisposableBean
    以及自己的destroyBean方法都不在执行）

# 半路上让Spring管理自己的对象

    @Test
    public void testRegisterBean(){
        User user =  new User();
        System.out.println(user);
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        defaultListableBeanFactory.registerSingleton("userBean",user);

        // 调用Spring容器中的对象
        User userBean = defaultListableBeanFactory.getBean("userBean", User.class);
        System.out.println(userBean);
    }