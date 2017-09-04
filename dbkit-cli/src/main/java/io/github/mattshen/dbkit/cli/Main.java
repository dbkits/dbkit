package io.github.mattshen.dbkit.cli;

import io.github.mattshen.dbkit.cli.config.Constants;
import io.github.mattshen.dbkit.cli.config.PropertiesHolder;
import io.github.mattshen.dbkit.cli.utils.Console;
import io.github.mattshen.dbkit.core.DbAccessor;
import io.github.mattshen.dbkit.core.models.Config;
import io.github.mattshen.dbkit.core.utils.JdbcUtils;

import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    static DbAccessor db = DbAccessor.getInstance();

    public static void main(String[] args) {
        boolean success = connect();
        if (success) {
            scanInput();
        } else {
            System.exit(1);
        }
    }

    static boolean connect() {
        try {
            PropertiesHolder.loadConfig();
            Properties props = PropertiesHolder.getProps();
            Config cfg = new Config(
                    props.getProperty(Constants.PROPERTIES_NAME_CLASSNAME),
                    props.getProperty(Constants.PROPERTIES_NAME_URL),
                    props.getProperty(Constants.PROPERTIES_NAME_USERNAME),
                    props.getProperty(Constants.PROPERTIES_NAME_PASSWORD)
            );
            db.connect(cfg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            Console.log("Cannot connect to database");
            return false;
        }
    }

    static void showTables() {
        try {
            List<Object> tables = db.getTables(JdbcUtils::extractToMap);
            tables.stream().forEach(t -> {
                Console.log(t.toString());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void executeSQL(String sql) {
        try {
            List<Object> tables = db.query(sql, JdbcUtils::extractToMap);
            tables.stream().forEach(t -> {
                Console.log(t.toString());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void scanInput() {
        Scanner sc = new Scanner(System.in);
        System.out.print("dbkit-cli>");
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            Console.log("Your command is " + input);
            if (input.equalsIgnoreCase(".exit")) {
                System.exit(0);
            } else if (input.equalsIgnoreCase("show tables;")) {
                showTables();
            } else if (input.startsWith("select")) {
                executeSQL(input);
            }
            System.out.print("dbkit-cli>");
        }
    }

}