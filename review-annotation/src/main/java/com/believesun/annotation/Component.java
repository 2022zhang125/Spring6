package com.believesun.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 注解 只能出现在类上
@Target(ElementType.TYPE)
// 可以被反射机制获取到
@Retention(RetentionPolicy.RUNTIME)

public @interface Component {
    String value();
}
