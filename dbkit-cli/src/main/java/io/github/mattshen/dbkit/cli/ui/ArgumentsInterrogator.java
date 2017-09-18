package io.github.mattshen.dbkit.cli.ui;

import joptsimple.OptionParser;
import joptsimple.OptionSet;

import java.io.File;
import java.io.IOException;

import static java.util.Arrays.asList;


public class ArgumentsInterrogator {

    private static ArgumentsInterrogator instance = new ArgumentsInterrogator();

    private static final String optionSpecs = "i";
    private OptionSet options = null;
    private static OptionParser parser = null;

    public static ArgumentsInterrogator getInstance() {
        return instance;
    }

    public ArgumentsInterrogator parse(String[] args) {

        parser = new OptionParser();

        parser.acceptsAll(asList("h", "help"), "show help").forHelp();

        parser.acceptsAll(asList("i", "interactive"), "interactive mode");

        parser.acceptsAll(asList("c", "config", "cfg"), "cli configuration file path").withRequiredArg()
                .describedAs("path")
                .ofType(File.class);
        parser.acceptsAll(asList("f", "format"), "output format").withRequiredArg();

        instance.options = parser.parse(args);
        return instance;
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

    public String getOutputFormat() {
        return (String)options.valueOf("format");
    }

}
