package io.github.mattshen.dbkit.core.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class PropertiesHolder {

    private static Logger LOG = LoggerFactory.getLogger(PropertiesHolder.class);

    private static Properties props = null;

    private static File getFileClassPath(String path) {
        ClassLoader classLoader = PropertiesHolder.class.getClassLoader();
        return new File(classLoader.getResource(path).getFile());
    }

    public static void loadConfig() {
        try (FileInputStream input = new FileInputStream(getFileClassPath("jdbc.properties"))) {
            Properties tmp = new Properties();
            tmp.load(input);
            props = tmp;
        } catch (IOException e) {
           LOG.error("cannot read jdbc.properties", e);
        }
    }

    public static void printConfig() {
        if (props != null) {
            props.list(System.out);
        } else {
            LOG.warn("not initialized");
        }
    }

    public static Properties getProps() {
        return props;
    }

}
