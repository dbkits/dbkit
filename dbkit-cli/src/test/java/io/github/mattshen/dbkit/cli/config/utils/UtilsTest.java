package io.github.mattshen.dbkit.cli.config.utils;

import io.github.mattshen.dbkit.cli.utils.Utils;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class UtilsTest {

    @Test
    public void testResolveRowPrintFormat() {

        List<Map<String, Object>> rows = new ArrayList<>();

        Map<String, Object> row1 = new LinkedHashMap<>();
        row1.put("productName", "apple");
        row1.put("price", 4.22);
        row1.put("quantity", 400);
        row1.put("description", "apple apple apple");

        Map<String, Object> row2 = new LinkedHashMap<>();
        row2.put("productName", "watermelon");
        row2.put("price", 2.22);
        row2.put("quantity", 100);
        row2.put("description", "nice to have in summer");

        rows.add(row1);
        rows.add(row2);

        String format = Utils.resolveRowPrintFormat(rows);

        Assert.assertTrue(format.length() > 0);

    }

}
