package com.believesun.spring6.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect // 切面(写增强代码的地方，通知 + 切点)
public class LogAdvice {
    // 使用通用切点来代替 常量字符串
    // private static final String pointCut = "execution(* com.believesun.spring6.service.UserService.*(..))";
    @Pointcut("execution(* com.believesun.spring6.service..*(..))") // 切点
    public void genericPointCut(){}
    // 前置通知
    @Before("genericPointCut()")
    public void beforeAdvice(JoinPoint joinPoint){
        // 获取 连接点 的签名，什么是签名？签名就是 修饰符列表 返回值类型 方法名 参数
        System.out.println(joinPoint.getSignature().getName());
        System.out.println("前置通知");
    }

    // 后置通知
    @AfterReturning("genericPointCut()")
    public void afterReturningAdvice(){
        System.out.println("后置通知");
    }

    // 环绕通知（最大的范围，在前置之前，在后置之后）
    @Around("genericPointCut()")
    public void aroundAdvice(ProceedingJoinPoint joinPoint){
        // 前环绕代码
        System.out.println("前环绕");

        // 调用目标代码
        try {
            joinPoint.proceed();
        } catch (Throwable e) {
            System.out.println("出现异常后的后环绕");
        }

        // 后环绕代码
        System.out.println("后环绕");
    }

    // 异常通知
    @AfterThrowing("genericPointCut()")
    public void throwAdvice(){
        System.out.println("异常通知");
    }

    // 最终通知
    @After("genericPointCut()")
    public void finallyAdvice(){
        System.out.println("最终通知");
    }
}
