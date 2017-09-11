package io.github.mattshen.dbkit.cli;

import io.github.mattshen.dbkit.cli.commands.CommandDefs;
import io.github.mattshen.dbkit.cli.commands.CommandFactory;
import io.github.mattshen.dbkit.cli.ui.ArgumentsInterrogator;
import io.github.mattshen.dbkit.cli.ui.InteractiveMode;
import io.github.mattshen.dbkit.cli.utils.Console;
import io.github.mattshen.dbkit.cli.utils.IOUtils;

public class Application {

    private static CommandFactory cf;

    public static void main(String[] args) {
        ArgumentsInterrogator interrogator = ArgumentsInterrogator.parse(args);

        try {
            cf = CommandFactory.create();

            if (interrogator.isHelp() && interrogator.hasNoArgs()) {
                interrogator.printHelp();
            } else if (interrogator.isInitClient()) {
                cf.executeCommand("\\init");
            } else if (interrogator.isInteractive()) {
                cf.executeCommand(CommandDefs.CONNECT);
                InteractiveMode.openInteractiveUI(cf);
            } else {
                cf.executeCommand(CommandDefs.CONNECT);
                String input = IOUtils.readStandardInput();
                cf.executeCommand(input);
            }

            //cf.executeCommand(CommandDefs.EXIT);

        } catch (Exception e) {
            Console.error(e.getMessage(), e);
            System.exit(1);
        }

    }


}