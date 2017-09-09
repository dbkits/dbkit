package io.github.mattshen.dbkit.cli.commands;

import io.github.mattshen.dbkit.cli.config.ClientConfig;
import io.github.mattshen.dbkit.cli.config.Profile;
import io.github.mattshen.dbkit.cli.utils.Console;
import io.github.mattshen.dbkit.cli.utils.Utils;
import io.github.mattshen.dbkit.core.DbAccessor;
import io.github.mattshen.dbkit.core.models.Config;
import io.github.mattshen.dbkit.core.utils.JdbcUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CommandFactory {

    private final HashMap<String, Command> commands;
    private DbAccessor dbAccessor;

    private CommandFactory() {
        commands = new HashMap<>();
    }

    public void addCommand(final String name, final Command cmd) {
        commands.put(name.toLowerCase(), cmd);
    }

    private void defaultCommand(String name) {
        try {
            List<Map<String, Object>> rows = dbAccessor.query(name, JdbcUtils::extractToMap);

            if (rows.size() > 0) {
                String format = Utils.resolveRowPrintFormat(rows);

                String headers = String.format(format, rows.get(0).keySet().toArray());
                String splitter = IntStream.range(0, headers.length()).mapToObj(o -> "-").collect(Collectors.joining());

                Console.log(headers);
                Console.log(splitter);

                rows.stream().forEach(row -> {
                    Console.log(String.format(format, row.values().toArray()));
                });
            } else {
                Console.log("No Result!");
            }
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

    public static CommandFactory create() throws Exception {
        final CommandFactory cf = new CommandFactory();
        //command definitions

        cf.addCommand("\\connect", () -> {
            try {
                cf.dbAccessor = DbAccessor.getInstance();
                connectDatabase(cf.dbAccessor);

            } catch (Exception e) {
                Console.error(e.getMessage(), e);
            }
        });

        cf.addCommand("\\init", new InitClientCommand());

        cf.addCommand("show tables", () -> {
            try {
                List<Map> tables = cf.dbAccessor.getTables(JdbcUtils::extractToMap);
                tables.stream().forEach(t -> {
                    Console.log(t.toString());
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        cf.addCommand("\\exit", () -> {
            System.exit(0);
        });

        return cf;
    }

    private static void connectDatabase(DbAccessor dbAccessor) throws Exception {
        ClientConfig config = ClientConfig.load();
        Profile profile = config.getDefaultProfile();
        Config cfg = new Config(
            profile.getDriverClassName(),
            profile.getUrl(),
            profile.getUsername(),
            profile.getPassword()
        );
        dbAccessor.connect(cfg);
    }
}
