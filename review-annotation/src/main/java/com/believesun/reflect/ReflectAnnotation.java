package com.believesun.reflect;
import com.believesun.annotation.Component;
import com.believesun.bean.Person;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;

/**
 * 已知：com.believesun.bean包
 * 需求：获取其包下的所有含Component注解的对象将其存入Map集合中
 *      key = userBean value = User对象
 */
public class ReflectAnnotation {
    private static final Map<String,Object> annotationObjects = new HashMap<>();
    public static void main(String[] args) throws Exception {
        String packageName = "com.believesun.bean";
        createObjectsFromAnnotation(packageName);
        // 打印集合对象
        for(Map.Entry<String,Object> e : annotationObjects.entrySet()){
            System.out.println(e);
        }
    }
    public static void createObjectsFromAnnotation(String packageName) throws Exception{
        // 1.获取符合路径的字符串（构建类）
        ArrayList<String> resultPath = getResultPath(packageName);
        resultPath.forEach(rstPath->{
            // 2.创建对象
            try {
                Class<?> aClass = Class.forName(rstPath);
                // 3.判断该类上是否含有Component注解
                if (aClass.isAnnotationPresent(Component.class)) {
                    // 4.如果有，则创建对象，构建Map集合
                    Component annotation = aClass.getAnnotation(Component.class);
                    String key = annotation.value();
                    // 5.创建对象
                    Object obj = aClass.getDeclaredConstructor().newInstance();
                    // 6.放入Map集合中
                    annotationObjects.put(key,obj);
                }
            } catch (ClassNotFoundException | InvocationTargetException | InstantiationException |
                     IllegalAccessException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        });
    }
    public static ArrayList<String> getResultPath(String packageName) throws Exception{
        ArrayList<String>  resultPaths = new ArrayList<>();
        String strPath = packageName.replace(".","/");
        Enumeration<URL> srcPathEnu = ClassLoader.getSystemClassLoader().getResources(strPath);
        if (srcPathEnu.hasMoreElements()) {
            File file = new File(srcPathEnu.nextElement().getPath());
            // 获取其子路径文件
            File[] childFiles = file.listFiles();
            for(File child : childFiles){
                // 构造类路径
                String childPath = child.getPath();
                String[] split = childPath.split("\\\\");
                String resultClassPath = packageName + "." + split[split.length - 1].split("\\.")[0];
                resultPaths.add(resultClassPath);
            }
        }
        return resultPaths;
    }
}
