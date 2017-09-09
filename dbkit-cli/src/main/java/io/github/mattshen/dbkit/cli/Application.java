package io.github.mattshen.dbkit.cli;

import io.github.mattshen.dbkit.cli.commands.CommandDefs;
import io.github.mattshen.dbkit.cli.commands.CommandFactory;
import io.github.mattshen.dbkit.cli.ui.ArgumentsInterpreter;
import io.github.mattshen.dbkit.cli.ui.Prompt;
import io.github.mattshen.dbkit.cli.utils.Console;

import java.util.Scanner;

public class Application {

    private static CommandFactory cf;

    public static void main(String[] args) {

        ArgumentsInterpreter interpreter = ArgumentsInterpreter.parse(args);

        try {
            cf = CommandFactory.create();

            if (interpreter.isHelp() && interpreter.hasNoArgs() ) {
                interpreter.printHelp();
            } else if (interpreter.isInitClient()) {
                cf.executeCommand("\\init");
            } else if (interpreter.isInteractive()) {
                cf.executeCommand(CommandDefs.CONNECT);
                openInteractiveUI();
            }

        } catch (Exception e) {
            Console.error(e.getMessage(), e);
            System.exit(1);
        }

    }

    private static void openInteractiveUI() {
        Scanner sc = new Scanner(System.in);
        Prompt.print();
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            cf.executeCommand(input);
            Prompt.print();
        }
    }

    private static void directExecute() {
        Console.log("open direct execution!");
    }

}