package io.github.mattshen.dbkit.cli.ui;


import io.github.mattshen.dbkit.cli.commands.CommandFactory;

import java.util.Scanner;

public class InteractiveMode {
    public static void openInteractiveUI(CommandFactory cf) {
        Scanner sc = new Scanner(System.in);
        Prompt.print();
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            cf.executeCommand(input);
            Prompt.print();
        }
    }
}
