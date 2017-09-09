package io.github.mattshen.dbkit.cli.ui;

import joptsimple.OptionParser;
import joptsimple.OptionSet;

import java.io.File;
import java.io.IOException;

import static java.io.File.pathSeparatorChar;
import static java.util.Arrays.asList;


public class ArgumentsInterpreter {

    private static final String optionSpecs = "i";
    private OptionSet options = null;
    private static OptionParser parser = null;

    public static ArgumentsInterpreter parse(String[] args) {
        final ArgumentsInterpreter argumentsInterpreter = new ArgumentsInterpreter();

        parser = new OptionParser();

        parser.acceptsAll(asList("h", "help"), "show help").forHelp();

        parser.acceptsAll(asList("i", "interactive"), "interactive mode");

        parser.acceptsAll(asList("c", "config", "cfg"), "Cli configuration file").withRequiredArg()
                .describedAs("path")
                .ofType(File.class)
                .withValuesSeparatedBy(pathSeparatorChar);

        argumentsInterpreter.options = parser.parse(args);
        return argumentsInterpreter;
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
      return options.nonOptionArguments().contains("init");
    }

}
