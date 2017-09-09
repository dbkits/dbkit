package io.github.mattshen.dbkit.cli.utils;

import io.github.mattshen.dbkit.cli.config.Constants;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.File;

public class Utils {

    public static <T> String join(T[] values, String separator) {
        return Arrays.asList(values)
                .stream().map(v -> v.toString())
                .collect(Collectors.joining(separator));
    }

    public static String resolveRowPrintFormat(List<Map<String, Object>> rows) {
        if (rows.size() > 0) {
            Map<String, Integer> columnSizeSetting = new LinkedHashMap<>();
            rows.forEach(row -> {
                row.entrySet().forEach(entry -> {
                    String key = String.valueOf(entry.getKey());
                    String value = String.valueOf(entry.getValue());
                    if (columnSizeSetting.get(key) == null) {
                        columnSizeSetting.put(key, Math.max(String.valueOf(value).length(), key.length()));
                    } else {
                        columnSizeSetting.put(
                                key,
                                Math.max(columnSizeSetting.get(key), String.valueOf(value).length())
                        );
                    }
                });
            });

            return columnSizeSetting.values().stream()
                    .map(v -> "%-" + v + "s")
                    .collect(Collectors.joining(" | "));

        } else {
            return "";
        }
    }

    public static String getConfigFilePath() {
        return System.getProperty("user.home")
                        + System.getProperty("file.separator")
                        + Constants.CONFIG_DIR
                        + System.getProperty("file.separator")
                        + Constants.CONFIG_FILE_NAME;
    }

    public static void createConfigFolder() {
        String configFolder = System.getProperty("user.home") + System.getProperty("file.separator") + Constants.CONFIG_DIR;
        File file = new File(configFolder);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

}
