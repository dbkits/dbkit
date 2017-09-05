package io.github.mattshen.dbkit.cli.commands;

import io.github.mattshen.dbkit.cli.config.Constants;
import io.github.mattshen.dbkit.cli.config.PropertiesHolder;
import io.github.mattshen.dbkit.cli.utils.Console;
import io.github.mattshen.dbkit.core.DbAccessor;
import io.github.mattshen.dbkit.core.models.Config;
import io.github.mattshen.dbkit.core.utils.JdbcUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class CommandFactory {

    private final HashMap<String, Command> commands;
    private DbAccessor dbAccessor;

    private CommandFactory(DbAccessor dbAccessor) {
        commands = new HashMap<>();
        this.dbAccessor = dbAccessor;
    }

    public void addCommand(final String name, final Command cmd) {
        commands.put(name.toLowerCase(), cmd);
    }

    private void defaultCommand(String name) {
        try {
            List<Object> tables = dbAccessor.query(name, JdbcUtils::extractToMap);
            tables.stream().forEach(t -> {
                Console.log(t.toString());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeCommand(String name) {
        if (commands.containsKey(name.toLowerCase())) {
            commands.get(name.toLowerCase()).execute();
        } else {
            defaultCommand(name);
        }
    }

    public static CommandFactory init() throws Exception {
        final DbAccessor dbAccessor = DbAccessor.getInstance();
        connectDatabase(dbAccessor);

        final CommandFactory cf = new CommandFactory(dbAccessor);


        //command definitions
        cf.addCommand("show tables", () -> {
            try {
                List<Object> tables = dbAccessor.getTables(JdbcUtils::extractToMap);
                tables.stream().forEach(t -> {
                    Console.log(t.toString());
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        cf.addCommand(".exit", () -> {
            System.exit(0);
        });

        return cf;
    }

    private static void connectDatabase(DbAccessor dbAccessor) throws Exception {
        PropertiesHolder.loadConfig();
        Properties props = PropertiesHolder.getProps();
        Config cfg = new Config(
                props.getProperty(Constants.PROPERTIES_NAME_CLASSNAME),
                props.getProperty(Constants.PROPERTIES_NAME_URL),
                props.getProperty(Constants.PROPERTIES_NAME_USERNAME),
                props.getProperty(Constants.PROPERTIES_NAME_PASSWORD)
        );
        dbAccessor.connect(cfg);
    }

}
