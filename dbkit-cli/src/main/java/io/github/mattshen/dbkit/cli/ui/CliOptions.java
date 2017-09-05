package io.github.mattshen.dbkit.cli.ui;

import joptsimple.OptionParser;
import joptsimple.OptionSet;


public class CliOptions {

    private static final String optionSpecs = "i";
    private OptionSet options = null;

    public static CliOptions parse(String[] args) {
        final CliOptions cliOptions = new CliOptions();
        OptionParser parser = new OptionParser(optionSpecs);
        cliOptions.options = parser.parse(args);
        return cliOptions;
    }

    public boolean isInteractive() {
        return options.has("i");
    }

}
