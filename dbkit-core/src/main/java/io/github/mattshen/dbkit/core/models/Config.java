package io.github.mattshen.dbkit.core.models;

/**
 * Created by zhongweishen on 4/9/17.
 */
public class Config {

    private String driverClassName;
    private String url;
    private String username;
    private String password;

    public Config(String driverClassName, String url, String username, String password) {
        this.driverClassName = driverClassName;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
