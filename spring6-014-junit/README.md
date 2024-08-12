# 使用Spring-test 简化 Junit4
    使用注解
        @RunWith(SpringJUnit4ClassRunner.class) 去开启Spring对Junit的支持
        @ContextConfiguration("classpath:spring.xml") 去代替 ApplicationContext
    使用自动类型注入，代替频繁getBean的对象
        @AutoWired
        private User user;

# 使用Spring-test 简化 Junit5
    引入的依赖：
        <!--junit5依赖-->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.9.0</version>
            <scope>test</scope>
        </dependency>
    使用注解，不同与Junit4，Junit5不使用RunWith注解而改用ExtendWith注解
        @ExtendWith(SpringExtension.class)

# 不同点
    第一：依赖不同
    第二：注解不同
        Junit4:@RunWith(SpringJunit4ClassRunner.class)
        Junit5:@ExtendWith(SpringExtension.class)