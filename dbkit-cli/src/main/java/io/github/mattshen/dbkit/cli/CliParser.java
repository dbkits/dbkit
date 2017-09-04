package io.github.mattshen.dbkit.cli;

import joptsimple.OptionParser;
import joptsimple.OptionSet;

/**
 * Created by zhongweishen on 4/9/17.
 */
public class CliParser {

    private String[] args;
    private OptionSet options = null;

    public static OptionParser defaultOptionParser() {
        OptionParser parser = new OptionParser("h:u:p:t:");
        return parser;
    }

    public CliParser createParser(String[] args) {
        this.args = args;
        return this;
    }

    public CliParser setParser(OptionParser parser) {
        options = parser.parse(args);
        return this;
    }



}
