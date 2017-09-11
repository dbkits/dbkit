package io.github.mattshen.dbkit.cli.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class IOUtils {

    public static String readStandardInput() {
        List<String> input = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = in.readLine()) != null) {
                input.add(line);
            }
        } catch (IOException e) {
            Console.error(e.getMessage(), e);
            System.exit(1);
        }
        return input.stream().collect(Collectors.joining(" "));
    }

    public static void silentExecute(Function<Void, Void> func) {
        PrintStream original = System.out;
        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                //DO NOTHING
            }
        }));

        func.apply(null);

        System.setOut(original);

    }

}
