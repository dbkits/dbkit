package io.github.mattshen.dbkit.cli.config;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

/*
 {
  "outputStyle": "column",
  "profiles": {
    "mysql": {
      "default": true,
      "datasource.url": "jdbc:mysql://localhost:3306/awtest",
      "datasource.username": "root",
      "datasource.password": "",
      "datasource.driver-class-name": "com.mysql.jdbc.Driver"
    },
    "postgres": {
      "datasource.url": "jdbc:mysql://localhost:3306/awtest",
      "datasource.username": "root",
      "datasource.password": "",
      "datasource.driver-class-name": "com.mysql.jdbc.Driver"
    }
  }
}
 */
public class ClientConfig {

    private String outputStyle;
    private Map<String, Profile> profiles;

    private ClientConfig() {
    }

    public String getOutputStyle() {
        return outputStyle;
    }

    public Map<String, Profile> getProfiles() {
        return profiles;
    }

    public Profile getDefaultProfile() {
        return profiles.get("mysql");
    }

    public static ClientConfig load() throws IOException {
        ClassLoader classLoader = ClientConfig.class.getClassLoader();
        ClientConfig config = new ObjectMapper().readValue(
                classLoader.getResourceAsStream("client.config.json"),
                ClientConfig.class);
        return config;
    }

}


