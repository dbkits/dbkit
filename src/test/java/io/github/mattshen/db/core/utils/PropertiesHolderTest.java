package io.github.mattshen.db.core.utils;


import io.github.mattshen.db.core.config.PropertiesHolder;
import org.junit.Assert;
import org.junit.Test;

import java.util.Properties;

public class PropertiesHolderTest {

    @Test
    public void config_file_should_load() {
        PropertiesHolder.loadConfig();
        Properties props = PropertiesHolder.getProps();
        Assert.assertTrue(props.propertyNames().hasMoreElements());
    }

}
