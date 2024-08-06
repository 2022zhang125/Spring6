# Spring解决循环依赖问题
    什么是循环依赖问题？
        在Husband类和Wife类中，Husband类中存在Wife属性，Wife类中也存在Husband属性。

## singleton + setter 模式 （可以）
    
    如果，我们对两个Bean都设置为单例且通过set注入的方式进行赋值操作，是否会出现异常呢？
        不会出现异常
    解决方法：
        当Spring容器去创建单例对象（singleton）时，是分为两步完成的
        第一步：调用无参数构造方法实例化对象，并且立即 “曝光”
            重点就是：实例化对象后立即 “曝光”
        第二步：给对象进行赋值操作，当对象需要一个属性时，由于其singleton的原因，所以可以直接调用。
```xml
    <!--配置Husband类-->
    <bean id="husbandBean" class="com.believesun.bean.Husband" p:name="zhangsan" p:wife-ref="wifeBean" scope="singleton"/>

    <!--配置wife类-->
    <bean id="wifeBean" class="com.believesun.bean.Wife" p:name="李四" p:husband-ref="husbandBean" scope="singleton"/>
```

## prototype + setter 模式 （不行）

    prototype意味着，对象在getBean的时候进行创建并赋值操作，这两个操作时一起完成的。
    这就会导致，A对象的B属性需要去动态创建，而创建B属性时因为循环依赖的关系，又需要A属性，而A属性的创建又需要B属性......
    这样就导致一直递归，一直创建不出最初的对象，会出现异常！！！

    Caused by: org.springframework.beans.factory.BeanCurrentlyInCreationException: 
    Error creating bean with name 'husbandBean': Requested bean is currently in creation: Is there an unresolvable circular reference?

    BeanCurrentlyInCreationException:Bean对象现在正在创建异常！！！
    这个异常很重要，这个异常一出，就代表着循环依赖出现了问题，而且出现问题的两个类必须都是prototype类型的。
```xml
    <!--配置Husband类-->   
    <bean id="husbandBean" class="com.believesun.bean.Husband" p:name="zhangsan" p:wife-ref="wifeBean" scope="prototype"/>

    <!--配置wife类-->
    <bean id="wifeBean" class="com.believesun.bean.Wife" p:name="李四" p:husband-ref="husbandBean" scope="prototype"/>
```

## 一个prototype,一个singleton + setter 模式 （可以）

    由于其中的一个对象为单例对象，所以在new ClassPathXmlApplicationContext()的时候，就直接初始化并"曝光"了,所以也不会出现其B对象创建A对象时的循环递归问题。因为singleton只有一个地址

## singleton + construction （不行）

    虽然是singleton单例对象，分布执行，但是由于是构造方法注入的形式，会导致，分布执行的第一步：实例化对象的时候，调用其必须调用其构造方法进行赋值，但是赋值又必须要另一个对象
    还是会导致创建不了对象‘
    Caused by: org.springframework.beans.factory.BeanCurrentlyInCreationException: 
    Error creating bean with name 'h': Requested bean is currently in creation: Is there an unresolvable circular reference?

# Spring如何解决循环依赖问题？？？（重点）
    大致分为三大步：
        第一步：实例化对象
            Object bean = instanceWrapper.getWrappedInstance();
        第二步：曝光出去
            this.addSingletonFactory(beanName, () -> { return this.getEarlyBeanReference(beanName, mbd, bean); });
                如何进行曝光？
                    key：代表bean的id，就是Bean的名字
                    private final Map<String, Object> singletonObjects;                 一级缓存（存放完整的Bean对象，已经赋过值的Bean对象）
                    private final Map<String, Object> earlySingletonObjects;            二级缓存（存放早期的Bean对象，还未来得及赋值的Bean对象）
                    private final Map<String, ObjectFactory<?>> singletonFactories;     三级缓存（存放制造该Bean对象的工厂对象，就是谁造出来这个Bean的工厂对象。）
        这里从一级缓存开始查找该Bean对象，依次到三级缓存，

            if (singletonFactory != null) {
                singletonObject = singletonFactory.getObject();
                this.earlySingletonObjects.put(beanName, singletonObject);
                this.singletonFactories.remove(beanName);
            }
        其中最后的三级缓存中创建对象，然后立马执行 this.earlySingletonObjects.put(beanName, singletonObject); 操作，将其曝光，也就是放到二级缓存中。最后删除三级缓存中的值
        这样就实现了"曝光"的操作。

        protected Object getSingleton(String beanName, boolean allowEarlyReference) {
        Object singletonObject = this.singletonObjects.get(beanName);
        if (singletonObject == null && this.isSingletonCurrentlyInCreation(beanName)) {
            singletonObject = this.earlySingletonObjects.get(beanName);
            if (singletonObject == null && allowEarlyReference) {
                synchronized(this.singletonObjects) {
                    singletonObject = this.singletonObjects.get(beanName);
                    if (singletonObject == null) {
                        singletonObject = this.earlySingletonObjects.get(beanName);
                        if (singletonObject == null) {
                            ObjectFactory<?> singletonFactory = (ObjectFactory)this.singletonFactories.get(beanName);
                            if (singletonFactory != null) {
                                singletonObject = singletonFactory.getObject();
                                this.earlySingletonObjects.put(beanName, singletonObject);
                                this.singletonFactories.remove(beanName);
                            }
                        }
                    }
                }
            }
        }
        return singletonObject;
    }
        第三步：填充属性值
            this.populateBean(beanName, mbd, instanceWrapper);