package io.github.mattshen.dbkit.core;


import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class DbAccessorTest {

    static DbAccessor dbAccessor = null;

    @BeforeClass
    public static void setUp() throws Exception {
        dbAccessor = DbAccessor.getInstance();
        dbAccessor.connect();
    }

    @AfterClass
    public static void tearDown() throws SQLException {
        dbAccessor.close();
    }

    @Test
    public void basic_query_should_work() throws Exception {
        List<String> result = dbAccessor.query("select 1", rs -> rs.getString(1));
        Assert.assertTrue(result.size() == 1);
        Assert.assertTrue(result.get(0).equals("1"));
    }

}
