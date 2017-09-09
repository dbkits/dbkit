package io.github.mattshen.dbkit.cli.commands.definitions;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.mattshen.dbkit.cli.commands.Command;
import io.github.mattshen.dbkit.cli.config.ClientConfig;
import io.github.mattshen.dbkit.cli.config.Constants;
import io.github.mattshen.dbkit.cli.config.Profile;
import io.github.mattshen.dbkit.cli.utils.Console;
import io.github.mattshen.dbkit.cli.utils.Strings;
import io.github.mattshen.dbkit.cli.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Scanner;

public class InitClientCommand implements Command {

    @Override
    public void execute() {
        try (Scanner sc = new Scanner(System.in)) {

            ClientConfig cfg = new ClientConfig();
            Profile profile1 = new Profile();
            cfg.getProfiles().put(cfg.getDefaultProfile(), profile1);

            System.out.print(MessageFormat.format("Database URL [{0}]: ", profile1.getUrl()));
            String url = sc.nextLine();
            if (!Strings.isNullOrEmpty(url)) {
                profile1.setUrl(url);
            }

            System.out.print(MessageFormat.format("Username [{0}]: ", profile1.getUsername()));
            String username = sc.nextLine();
            if (!Strings.isNullOrEmpty(username)) {
                profile1.setUsername(username);
            }

            System.out.print(MessageFormat.format("Password [{0}]: ", profile1.getPassword()));
            String password = sc.nextLine();
            if (!Strings.isNullOrEmpty(password)) {
                profile1.setPassword(password);
            }

            System.out.print(MessageFormat.format("Driver Class Name[{0}]: ", profile1.getDriverClassName()));
            String driverClassName = sc.nextLine();
            if (!Strings.isNullOrEmpty(driverClassName)) {
                profile1.setDriverClassName(driverClassName);
            }

            Command cmd = () -> {
                try {
                    new ObjectMapper().writerWithDefaultPrettyPrinter()
                            .writeValue(new File(Utils.getConfigFilePath()), cfg);
                    System.out.println(MessageFormat.format("Config file {0} created", Utils.getConfigFilePath()));
                } catch (IOException e) {
                    Console.error("Failed to create config file", e);
                }
            };

            Utils.createConfigFolder();
            if (new File(Utils.getConfigFilePath()).exists()) {
                System.out.print(
                        MessageFormat.format("Config file {0} exists. Do you want to overwrite? [Y/N]: ",
                                Utils.getConfigFilePath())
                );
                String answer = sc.nextLine();
                if ("Y".equalsIgnoreCase(answer)) {
                    cmd.execute();
                }
            } else {
                cmd.execute();
            }

        } catch (Exception e) {
            Console.error("Failed to create config file", e);
        }
    }

}
