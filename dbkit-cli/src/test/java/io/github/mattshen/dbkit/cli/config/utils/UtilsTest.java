package io.github.mattshen.dbkit.cli.config.utils;

import io.github.mattshen.dbkit.cli.utils.Utils;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UtilsTest {

    @Test
    public void testResolveRowPrintFormat() {

        List<Map<String, Object>> rows = new ArrayList<>();


        Map<String, Object> row1 = new HashMap<>();
        row1.put("productName", "apple");
        row1.put("price", 4.22);
        row1.put("quantity", 400);
        row1.put("description", "apple apple apple");

        Map<String, Object> row2 = new HashMap<>();
        row2.put("productName", "watermelon");
        row2.put("price", 2.22);
        row2.put("quantity", 100);
        row2.put("description", "nice to have in summer");

        rows.add(row1);
        rows.add(row2);

        String format = Utils.resolveRowPrintFormat(rows);


        System.out.println(row1);
        System.out.println(row2);
        System.out.println(format);

        String headers = String.format(format, row1.keySet().toArray());
        String splitter = IntStream.range(0, headers.length()).mapToObj(o -> "-").collect(Collectors.joining());
        System.out.println(String.format(format, row1.values().toArray()));
        System.out.println(String.format(format, row2.values().toArray()));
        System.out.println(splitter);
        System.out.println(headers);

        Assert.assertTrue(format.length() > 0);

    }

}
