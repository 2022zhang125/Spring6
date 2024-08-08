package org.myspringframework.core;

public interface ApplicationContext {
    /**
     * 通过Bean的Id获取其对象
     * @param beanId bean的Id，就是我们配置文件中的Id
     * @return 对象
     */
    Object getBean(String beanId);
}
