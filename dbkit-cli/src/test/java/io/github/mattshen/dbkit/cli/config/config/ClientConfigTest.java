package io.github.mattshen.dbkit.cli.config.config;

import io.github.mattshen.dbkit.cli.config.ClientConfig;
import io.github.mattshen.dbkit.cli.config.ConfigKeeper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ClientConfigTest {

    @Test
    public void testLoad() throws IOException {
        ClientConfig config = ConfigKeeper.getInstance().load();
        Assert.assertEquals("column", config.getOutputStyle());
        Assert.assertEquals("root", config.defaultProfile().getUsername());
    }

}
