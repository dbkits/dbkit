package io.github.mattshen.dbkit.cli.utils;


import java.util.stream.Stream;

public class Console {

    public static void log(Object... objs) {
        Stream.of(objs).forEach(obj -> {
            System.out.println(obj);
        });
    }

    public static void error(String message) {
        System.err.println(message);
    }

    public static void error(String message, Exception e) {
        System.err.println(message);
        e.printStackTrace();
    }

}
