# 使用SpringAOP切面编程
    第一步：引入依赖（aspectj）
        <!-- https://mvnrepository.com/artifact/org.aspectj/aspectjweaver -->
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.9.22.1</version>
            <scope>runtime</scope>
        </dependency>
    第二步：直接写tm注解即可。

## 注意：
    第一点：After是最终通知，AfterReturning是后置通知!!!
    第二点：环绕通知（@Around）是最大范围的通知。
        其中环绕通知不同于其他通知，其有一个自己的属性 ProceedingJoinPoint,在其他方法中，都含有 连接点 对象，JoinPoint
    第三点：异常通知必须要是 目标方法 抛出异常时执行而不是在测试的时候抛出，必须要在目标对象的目标方法中抛出才行

## 结果一（没有异常的时候）
    前环绕
    前置通知
    正在验证用户信息...
    后置通知
    最终通知
    后环绕

## 结果二（存在异常的时候）
    前环绕
    前置通知
    正在验证用户信息...
    异常通知
    最终通知

## 结果三（在环绕通知中处理异常）
    前环绕
    前置通知
    正在验证用户信息...
    异常通知 exception
    最终通知 finally
    出现异常后的后环绕 around catch
    后环绕 around

## 关于多个交叉业务排序使用 Order 注解
    @Order(1) @Order(2) ...... 必须要是整数！！！
    数字越小，优先级越高。

## 使用通用切点来代替原有字符串
    使用：
        // 使用通用切点来代替 常量字符串
        // private static final String pointCut = "execution(* com.believesun.spring6.service.UserService.*(..))";
        @Pointcut("execution(* com.believesun.spring6.service..*(..))")
        public void genericPointCut(){}  
    优点：
        相较于常量字符串，其优点在于可以在其他类中，通过 包名+方法名的形式去使用 通用切点表达式。

    