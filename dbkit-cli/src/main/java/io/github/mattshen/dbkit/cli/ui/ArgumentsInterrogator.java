package io.github.mattshen.dbkit.cli.ui;

import joptsimple.OptionParser;
import joptsimple.OptionSet;

import java.io.File;
import java.io.IOException;

import static java.util.Arrays.asList;


public class ArgumentsInterrogator {

    private static final String optionSpecs = "i";
    private OptionSet options = null;
    private static OptionParser parser = null;

    public static ArgumentsInterrogator parse(String[] args) {
        final ArgumentsInterrogator argumentsInterrogator = new ArgumentsInterrogator();

        parser = new OptionParser();

        parser.acceptsAll(asList("h", "help"), "show help").forHelp();

        parser.acceptsAll(asList("i", "interactive"), "interactive mode");

        parser.acceptsAll(asList("c", "config", "cfg"), "Cli configuration file").withRequiredArg()
                .describedAs("path")
                .ofType(File.class);

        argumentsInterrogator.options = parser.parse(args);
        return argumentsInterrogator;
    }


    public void printHelp() throws IOException {
        parser.printHelpOn(System.out);
    }

    public boolean isHelp() {
        return options.has("h") || options.has("help");
    }

    public boolean hasNoArgs() {
        return options.nonOptionArguments().isEmpty() && !options.hasOptions();
    }

    public boolean isInteractive() {
        return options.has("i");
    }

    public boolean isInitClient() {
      return !options.hasOptions() && options.nonOptionArguments().contains("init");
    }

}
