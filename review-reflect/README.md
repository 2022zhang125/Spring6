# 复习反射机制
    通过反射机制，调用doSomeService中的doSome方法

## 第一步：获取方法类对象
    Class clazz = Class.forName("com.believesun.reflect.doSomeService");

## 第二步：获取指定方法
    Method declaredMethod = clazz.getDeclaredMethod("doSome",String.class,int.class);

## 第三步：集齐四要素，谁调用什么方法，传什么参数，什么返回值？
    Object obj = declaredMethod.invoke(clazz.newInstance(), "张三", 123);

## 打印
    System.out.println(obj);

# 已知： 类名：com.believesun.reflect.User，类中有属性 int age，类符合JavaBean规范，求 通过反射机制调用setter方法给age赋值
    注意：其中的 // 通过属性名 ---> 获取属性类型 很可以！！！
    public static Object setParams(String className, String paramName, int inputVal) {
        try {
            Class<?> targetClazz = Class.forName(className);
            // 通过属性名 ---> 获取属性类型
            Field field = targetClazz.getDeclaredField(paramName);
            Class<?> paramType = field.getType();

            Method setMethod = targetClazz.getDeclaredMethod("set" + paramName.toUpperCase().charAt(0) + paramName.substring(1), paramType);
            Object obj = targetClazz.getDeclaredConstructor().newInstance();
            setMethod.invoke(obj, inputVal);
            return obj;
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                 InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
