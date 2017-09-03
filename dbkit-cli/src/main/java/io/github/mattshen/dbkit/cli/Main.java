package io.github.mattshen.dbkit.cli;

import io.github.mattshen.dbkit.cli.utils.IOUtils;
import io.github.mattshen.dbkit.core.*;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

import java.util.Arrays;
import java.util.List;

public class Main {

  public static void main(String[] args) throws Exception {
    System.out.println("hello cli");

    System.out.println("args=" + IOUtils.join(args, ","));

    OptionParser parser = new OptionParser("h:u:p:t:");
    OptionSet options = parser.parse(args);

    System.out.println(options.valueOf("h"));
    System.out.println(options.valueOf("u"));
    System.out.println(options.valueOf("p"));
    System.out.println(options.valueOf("t"));

  }

}