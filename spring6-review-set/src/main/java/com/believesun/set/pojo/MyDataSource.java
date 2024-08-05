package com.believesun.set.pojo;

public class MyDataSource {
    private String username;
    private String password;
    private String driver;
    private String url;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "MyDataSource{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", driver='" + driver + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public MyDataSource(String username, String password, String driver, String url) {
        this.username = username;
        this.password = password;
        this.driver = driver;
        this.url = url;
    }

    public MyDataSource() {
    }
}
