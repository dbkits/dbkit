package io.github.mattshen.dbkit.cli.config;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

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


