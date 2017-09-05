package io.github.mattshen.dbkit.cli.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertiesHolder {

    private static Logger LOG = LoggerFactory.getLogger(PropertiesHolder.class);

    private static Properties props = null;

    public static void loadConfig() {
        ClassLoader classLoader = PropertiesHolder.class.getClassLoader();
        try (InputStream input = classLoader.getResourceAsStream("jdbc.properties")) {
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
