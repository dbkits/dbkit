package io.github.mattshen.dbkit.cli.commands;

import io.github.mattshen.dbkit.cli.commands.definitions.ConnectDBCommand;
import io.github.mattshen.dbkit.cli.commands.definitions.InitClientCommand;
import io.github.mattshen.dbkit.cli.commands.definitions.SQLCommand;
import io.github.mattshen.dbkit.cli.commands.definitions.ShowTablesCommand;
import io.github.mattshen.dbkit.cli.utils.Strings;

import java.util.function.Function;
import java.util.stream.Stream;

public enum CommandDefs {

    INIT("init", false, params -> new InitClientCommand()),

    CONNECT("connect", false, params -> new ConnectDBCommand()),

    SHOW_TABLES("show tables", false, params -> new ShowTablesCommand()),

    EXECUTE_SQL("execute_sql", true, params -> new SQLCommand(params)),

    EXIT("exit", false, params -> () -> System.exit(0));

    final String commandName;
    final boolean isSQL;
    final Function<Object, Command> creator;

    CommandDefs(String commandName, boolean isSQL, Function<Object, Command> creator) {
        this.commandName = commandName;
        this.creator = creator;
        this.isSQL = isSQL;
    }

    public static Command findCommand(String s) {
        if (Strings.isNullOrEmpty(s)) {
            return null;
        } else {
            return Stream.of(CommandDefs.values())
                    .filter(def -> s.equals(def.commandName))
                    .findFirst()
                    .orElseGet(() -> CommandDefs.EXECUTE_SQL)
                    .creator.apply(s);

        }
    }

}
