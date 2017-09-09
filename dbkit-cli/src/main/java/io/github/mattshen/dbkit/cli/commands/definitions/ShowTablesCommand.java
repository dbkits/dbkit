package io.github.mattshen.dbkit.cli.commands.definitions;

import io.github.mattshen.dbkit.cli.commands.Command;
import io.github.mattshen.dbkit.cli.utils.Console;
import io.github.mattshen.dbkit.cli.utils.Utils;
import io.github.mattshen.dbkit.core.DbAccessor;
import io.github.mattshen.dbkit.core.utils.JdbcUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ShowTablesCommand implements Command {

    @Override
    public void execute() {
        try {
            List<Map<String, Object>> rows  = DbAccessor.getInstance().getTables(JdbcUtils::extractToMap);
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

}
