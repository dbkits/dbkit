package io.github.mattshen.dbkit.cli.utils;

import java.util.*;
import java.util.stream.Collectors;

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

}
