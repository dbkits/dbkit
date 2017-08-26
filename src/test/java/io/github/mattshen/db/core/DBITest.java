package io.github.mattshen.db.core;


import org.junit.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBITest {

    static DBI dbi = null;

    @BeforeClass
    public static void setUp() throws Exception {
        dbi = DBI.getInstance();
        dbi.connect();
    }

    @AfterClass
    public static void tearDown() throws SQLException {
        dbi.close();
    }

    @Test
    public void basic_query_should_work() throws Exception {
        List<String> result = dbi.query("select 1", rs -> rs.getString(1));
        Assert.assertTrue(result.size() == 1);
        Assert.assertTrue(result.get(0).equals("1"));
    }

}
