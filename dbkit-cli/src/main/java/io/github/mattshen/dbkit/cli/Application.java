package io.github.mattshen.dbkit.cli;

import io.github.mattshen.dbkit.cli.commands.CommandFactory;
import io.github.mattshen.dbkit.cli.ui.CliOptions;
import io.github.mattshen.dbkit.cli.ui.Prompt;
import io.github.mattshen.dbkit.cli.utils.Console;

import java.util.Scanner;

public class Application {

    private static CommandFactory cf;

    public static void main(String[] args) {

        CliOptions options = CliOptions.parse(args);
        try {
            cf = CommandFactory.init();
            if (options.isInteractive()) {
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
            Console.log("Your command is " + input);
            cf.executeCommand(input);
            Prompt.print();
        }
    }

    private static void directExecute() {
        Console.log("open direct execution!");
    }

}