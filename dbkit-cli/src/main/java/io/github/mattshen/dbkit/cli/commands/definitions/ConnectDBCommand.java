package io.github.mattshen.dbkit.cli.commands.definitions;

import io.github.mattshen.dbkit.cli.commands.Command;
import io.github.mattshen.dbkit.cli.config.ClientConfig;
import io.github.mattshen.dbkit.cli.config.ConfigKeeper;
import io.github.mattshen.dbkit.cli.config.Profile;
import io.github.mattshen.dbkit.cli.utils.Console;
import io.github.mattshen.dbkit.core.DbAccessor;
import io.github.mattshen.dbkit.core.models.Config;

public class ConnectDBCommand implements Command {

    @Override
    public void execute() {
        try {
            connectDatabase(DbAccessor.getInstance());
        } catch (Exception e) {
            Console.error(e.getMessage(), e);
        }
    }

    private void connectDatabase(DbAccessor dbAccessor) throws Exception {
        ClientConfig config = ConfigKeeper.getInstance().load();
        Profile profile = config.defaultProfile();
        Config cfg = new Config(
                profile.getDriverClassName(),
                profile.getUrl(),
                profile.getUsername(),
                profile.getPassword()
        );
        dbAccessor.connect(cfg);
        Console.log("Connected to " + profile.getUrl());
    }
}
