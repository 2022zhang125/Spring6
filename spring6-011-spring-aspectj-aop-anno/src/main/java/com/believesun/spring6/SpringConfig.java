package com.believesun.spring6;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.believesun.spring6")
@EnableAspectJAutoProxy(proxyTargetClass = true) // 开启Aspectj自动代理模式 proxyTargetClass = false时启用的是JDK的代理模式，当为true时启动的是CGLIB的代理模式。
public class SpringConfig {
}
