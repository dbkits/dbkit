package io.github.mattshen.dbkit.cli;

import io.github.mattshen.dbkit.cli.utils.Utils;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("hello cli");

        System.out.println("args=" + Utils.join(args, ","));

        OptionParser parser = new OptionParser("h:u:p:t:");
        OptionSet options = parser.parse(args);

        System.out.println(options.valueOf("h"));
        System.out.println(options.valueOf("u"));
        System.out.println(options.valueOf("p"));
        System.out.println(options.valueOf("t"));

        processStdIn();

    }

    static void processStdIn() {
        BufferedReader br = null;

        try {

            br = new BufferedReader(new InputStreamReader(System.in));

            while (true) {

                System.out.print("Enter something : ");
                String input = br.readLine();

                if ("q".equals(input)) {
                    System.out.println("Exit!");
                    System.exit(0);
                }

                System.out.println("input : " + input);
                System.out.println("-----------\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}