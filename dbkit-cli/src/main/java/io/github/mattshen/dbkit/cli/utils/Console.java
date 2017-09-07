package io.github.mattshen.dbkit.cli.utils;


public class Console {

    public static void log(String message) {
        System.out.println(message);
    }

    public static void error(String message, Exception e) {
        System.err.println(message);
        e.printStackTrace();
    }

}
