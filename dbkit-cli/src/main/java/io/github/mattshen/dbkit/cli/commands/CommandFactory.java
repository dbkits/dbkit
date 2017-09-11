package io.github.mattshen.dbkit.cli.commands;

import io.github.mattshen.dbkit.cli.utils.Console;
import io.github.mattshen.dbkit.cli.utils.Strings;

public class CommandFactory {

    public static CommandFactory create() throws Exception {
        return new CommandFactory();
    }

    public void executeCommand(String s) {
        if (Strings.isNullOrEmpty(s)) {
            Console.error("Invalid Command");
        } else {
            Command cmd;
            if (s.startsWith("\\")) {
                cmd = CommandDefs.findCommand(s.substring(1));
            } else {
                cmd = CommandDefs.findCommand(s);
            }
            cmd.execute();
        }
    }

    public void executeCommand(CommandDefs cmd) {
        cmd.creator.apply(null).execute();
    }
}
