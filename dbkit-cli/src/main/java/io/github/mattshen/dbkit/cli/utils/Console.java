package io.github.mattshen.dbkit.cli.utils;


import java.nio.file.Paths;
import java.util.stream.Stream;

public class Console {

    public static void log(Object... objs) {
        Stream.of(objs).forEach(System.out::println);
    }

    public static void error(String message) {
        System.err.println(message);
    }

    public static void error(String message, Exception e) {
        System.err.println(message);
        e.printStackTrace();
    }

    public static void printWorkingContext() {
        String workingDir = Paths.get(".").toAbsolutePath().normalize().toString();
        Console.log("Working Dir: " + workingDir);
    }

}
