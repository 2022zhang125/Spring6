package com.believesun.set.pojo;

import java.util.Properties;

public class MyDataSource01 {
    private Properties properties;

    public MyDataSource01() {
    }

    public MyDataSource01(Properties properties) {
        this.properties = properties;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "MyDataSource01{" +
                "properties=" + properties +
                '}';
    }
}
