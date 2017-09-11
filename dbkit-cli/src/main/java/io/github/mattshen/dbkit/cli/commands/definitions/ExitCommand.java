package io.github.mattshen.dbkit.cli.commands.definitions;

import io.github.mattshen.dbkit.cli.commands.Command;
import io.github.mattshen.dbkit.cli.utils.Console;
import io.github.mattshen.dbkit.core.DbAccessor;

import java.sql.SQLException;

public class ExitCommand implements Command {
    @Override
    public void execute() {
        try {
            DbAccessor.getInstance().close();
        } catch (SQLException e) {
            Console.error(e.getMessage(), e);
        }

        System.exit(1);
    }
}
