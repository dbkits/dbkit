package io.github.mattshen.dbkit.cli.config;

public class Profile {

    private boolean isDefault;
    private String url;
    private String username;
    private String password;
    private String driverClassName;

    public boolean isDefault() {
        return isDefault;
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

    public String getDriverClassName() {
        return driverClassName;
    }
}
