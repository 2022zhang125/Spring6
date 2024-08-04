package com.believesun.set.pojo;

import java.util.Map;
import java.util.Properties;

public class People {
    private Map<Integer,String> addr;
    private Properties properties;

    @Override
    public String toString() {
        return "People{" +
                "addr=" + addr +
                ", properties=" + properties +
                '}';
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Map<Integer, String> getAddr() {
        return addr;
    }

    public void setAddr(Map<Integer, String> addr) {
        this.addr = addr;
    }
}
