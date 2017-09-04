package io.github.mattshen.dbkit.cli.utils;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by zhongweishen on 3/9/17.
 */
public class Utils {

    public static <T> String join(T[] values, String separator) {
        return Arrays.asList(values)
                .stream().map(v -> v.toString())
                .collect(Collectors.joining(separator));
    }

}
