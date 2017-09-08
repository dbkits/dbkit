package io.github.mattshen.dbkit.cli.config.config;

import io.github.mattshen.dbkit.cli.config.ClientConfig;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ClientConfigTest {

    @Test
    public void testLoad() throws IOException {
        ClientConfig config = ClientConfig.load();
        Assert.assertEquals("column", config.getOutputStyle());
        Assert.assertEquals("root", config.getDefaultProfile().getUsername());
    }

}
