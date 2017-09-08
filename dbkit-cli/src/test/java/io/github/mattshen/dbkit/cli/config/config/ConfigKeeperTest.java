package io.github.mattshen.dbkit.cli.config.config;

import io.github.mattshen.dbkit.cli.config.ConfigKeeper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


public class ConfigKeeperTest {

    private ConfigKeeper clientCfg;

    @Before
    public void setUp() {
        clientCfg = ConfigKeeper.getInstance();
    }


    @Test
    public void testGetValue() {

        Map<String, Object> rootConfig = new HashMap<>();
        Map<String, Object> profiles = new HashMap<>();
        Map<String, Object> mysqlProfile = new HashMap<>();
        Map<String, Object> postgresProfile = new HashMap<>();

        rootConfig.put("outputStyle", "column");
        rootConfig.put("profiles", profiles);

        profiles.put("mysql", mysqlProfile);
        profiles.put("postgres", postgresProfile);

        mysqlProfile.put("default", true);
        mysqlProfile.put("datasource.username", "root");

        postgresProfile.put("datasource.username", "sa");

        clientCfg.setConfig(rootConfig);


        //positive cases
        Object outputStyle = clientCfg.getValue("outputStyle");
        Assert.assertTrue(outputStyle.equals("column"));

        Object mysqlConfig = clientCfg.getValue("profiles", "mysql");
        Assert.assertTrue(mysqlConfig instanceof Map);

        Object isMysqlDefaultProfile = clientCfg.getValue("profiles", "mysql", "default");
        Assert.assertTrue(isMysqlDefaultProfile instanceof Boolean);

        Object postgresUsername = clientCfg.getValue("profiles", "postgres", "datasource.username");
        Assert.assertEquals("sa", postgresUsername);

        Object entireConfig = clientCfg.getValue();
        Assert.assertTrue(entireConfig instanceof Map);

        //negative cases
        Object invalidValue1 = clientCfg.getValue("xyz");
        Assert.assertNull(invalidValue1);

        Object invalidValue2 = clientCfg.getValue("profiles", "mysql", "default", "xyz");
        Assert.assertNull(invalidValue2);

        Object invalidValue3 = clientCfg.getValue("mysql");
        Assert.assertNull(invalidValue3);

        Object invalidValue4 = clientCfg.getValue("default");
        Assert.assertNull(invalidValue4);

    }

}
