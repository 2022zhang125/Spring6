package org.myspringfarmework.core;

import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassPathXmlApplicationContext implements ApplicationContext {
    private static final Logger logger = LoggerFactory.getLogger(ClassPathXmlApplicationContext.class);
    private static final Map<String, Object> singletonObjects = new HashMap<>();

    public ClassPathXmlApplicationContext(String configLocation) {
        try {
            // 解析XML文件
            // 1.获取XML文件
            InputStream xmlPath = ClassLoader.getSystemClassLoader().getResourceAsStream(configLocation);
            SAXReader saxReader = new SAXReader();
            Document doc = saxReader.read(xmlPath);
            // 2.获取其所有的bean标签
            List<Node> nodes = doc.selectNodes("//bean");
            // 3.加入缓存，提前曝光出去
            nodes.forEach(ClassPathXmlApplicationContext::addSingletonObjects);
            // 4.赋值操作
            nodes.forEach(ClassPathXmlApplicationContext::getParams);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取所有的property对象
     *
     * @param node bean对象
     */
    public static void getParams(Node node) {
        // 1.获取所有的property标签
        Element element = (Element) node;
        List<Node> propertys = element.selectNodes("property");
        String beanId = element.attributeValue("id");
        if (!(propertys.isEmpty())) {
            propertys.forEach(property -> {
                // 2.获取所有的name,value,以及ref
                Element ele = (Element) property;
                String name = ele.attributeValue("name");
                String value = ele.attributeValue("value");
                String ref = ele.attributeValue("ref");
                // ClassName, ParamName, inputValue
                if (ref != null) {
                    // 非简单类型
                    populateParams(beanId, name, singletonObjects.get(ref));
                    logger.info("name = " + name + " ref = " + ref);
                }
                if (value != null) {
                    // 简单类型
                    populateParams(beanId, name, convertToRequiredType(singletonObjects.get(beanId),name,value));
                    logger.info("name = " + name + " value = " + value);
                }
            });
        }
    }

    /**
     * 将inputValue属性变为符合要求的属性
     * @param bean bean对象
     * @param paramName 属性名
     * @param value 属性值
     * @return 符合要求的对象
     */
    private static Object convertToRequiredType(Object bean, String paramName, String value) {
        try {
            Field paramField = bean.getClass().getDeclaredField(paramName);
            Class<?> paramTypeClass = paramField.getType();
            if (paramTypeClass == int.class) {
                return Integer.parseInt(value);
            } else if (paramTypeClass == long.class) {
                return Long.parseLong(value);
            } else if (paramTypeClass == boolean.class) {
                return Boolean.parseBoolean(value);
            } else if (paramTypeClass == double.class) {
                return Double.parseDouble(value);
            } else {
                return value;
            }
        } catch (NoSuchFieldException e) {
            logger.error("Error converting value for paramName: " + paramName, e);
            throw new RuntimeException("Error converting value for paramName: " + paramName, e);
        }
    }
    /**
     * 给对象进行setter赋值操作
     *
     * @param beanId     Bean的ID
     * @param paramName  属性名
     * @param inputValue 值
     */
    public static void populateParams(String beanId, String paramName, Object inputValue) {
        try {
            // 什么对象，调用什么方法，传递什么参数，返回什么类型
            Object obj = singletonObjects.get(beanId);
            String setterName = "set" + paramName.toUpperCase().charAt(0) + paramName.substring(1);
            // 获取参数类型
            Field paramFiled = obj.getClass().getDeclaredField(paramName);
            Class<?> paramTypeClass = paramFiled.getType();

            Method setterMethod = obj.getClass().getDeclaredMethod(setterName, paramTypeClass);
            setterMethod.invoke(obj, inputValue);
        } catch (NoSuchMethodException | NoSuchFieldException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 加入缓存中，提前曝光实例化的空对象
     *
     * @param node bean对象节点
     */
    public static void addSingletonObjects(Node node) {
        Element element = (Element) node;
        // 1.获取bean标签中的id和class
        String id = element.attributeValue("id");
        String className = element.attributeValue("class");
        try {
            // 2.通过构造方法实例化对象
            Class<?> clazz = Class.forName(className);
            Object obj = clazz.getDeclaredConstructor().newInstance();
            // 3.存入缓存中，曝光出去
            singletonObjects.put(id, obj);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object getBean(String beanId) {
        return singletonObjects.get(beanId);
    }
}
