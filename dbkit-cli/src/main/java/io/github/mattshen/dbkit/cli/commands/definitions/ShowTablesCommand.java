package io.github.mattshen.dbkit.cli.commands.definitions;

import io.github.mattshen.dbkit.cli.commands.Command;
import io.github.mattshen.dbkit.cli.utils.Console;
import io.github.mattshen.dbkit.core.DbAccessor;
import io.github.mattshen.dbkit.core.utils.JdbcUtils;

import java.util.List;
import java.util.Map;

public class ShowTablesCommand implements Command {

    @Override
    public void execute() {
        try {
            List<Map> tables = DbAccessor.getInstance().getTables(JdbcUtils::extractToMap);
            tables.stream().forEach(t -> {
                Console.log(t.toString());
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
