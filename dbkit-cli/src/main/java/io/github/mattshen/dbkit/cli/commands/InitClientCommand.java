package io.github.mattshen.dbkit.cli.commands;

import io.github.mattshen.dbkit.cli.Application;
import io.github.mattshen.dbkit.cli.utils.Console;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class InitClientCommand implements Command {

    @Override
    public void execute() {
        try {
            ClassLoader cl = Application.class.getClassLoader();
            InputStream is = cl.getResourceAsStream("client.config.json");
            Files.copy(is, new File("./dbkit.config.json").toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            Console.error("Failed to create config file", e);
        }
    }

}
