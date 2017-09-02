package io.github.mattshen.dbkit.core;


import io.github.mattshen.dbkit.core.utils.JdbcUtils;
import io.github.mattshen.dbkit.core.utils.ResultSetExtractor;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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

    @Test
    public void get_shemas_should_work() throws Exception {
        List<Map<String, Object>> schemas = dbAccessor.getSchemas(JdbcUtils::extractToMap);
        Assert.assertTrue(schemas.size() >= 0);
    }

    @Test
    public void get_catalogs_should_work() throws Exception {
        List<Map<String, Object>> catalogs = dbAccessor.getCatalogs(JdbcUtils::extractToMap);
        Assert.assertTrue(catalogs.size() > 0);
    }

    @Test
    public void get_tables_should_work() throws Exception {
        List<Object> tables = dbAccessor.getTables(JdbcUtils::extractToMap);
        Assert.assertTrue(tables.size() > 0);
    }

}
