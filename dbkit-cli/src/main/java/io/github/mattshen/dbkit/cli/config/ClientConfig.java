package io.github.mattshen.dbkit.cli.config;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ClientConfig {

    private String outputStyle = "column";
    private String defaultProfile = "profile1";
    private Map<String, Profile> profiles = new HashMap<>();


    public String getOutputStyle() {
        return outputStyle;
    }

    public void setOutputStyle(String outputStyle) {
        this.outputStyle = outputStyle;
    }

    public Map<String, Profile> getProfiles() {
        return profiles;
    }

    public String getDefaultProfile() {
        return defaultProfile;
    }

    public void setDefaultProfile(String defaultProfile) {
        this.defaultProfile = defaultProfile;
    }

    public Profile defaultProfile() {
        return profiles.get(this.defaultProfile);
    }

}


