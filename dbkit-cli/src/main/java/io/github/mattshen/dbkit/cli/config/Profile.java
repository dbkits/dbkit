package io.github.mattshen.dbkit.cli.config;

public class Profile {

    private String url="jdbc:db_type://host:1234/database";
    private String username = "dbuser";
    private String password = "dbpass";
    private String driverClassName = "com.example.DBXyzDriver";

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }
}
