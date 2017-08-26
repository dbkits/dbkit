package io.github.mattshen.db.core;


import io.github.mattshen.db.core.utils.PropertiesHolder;
import io.github.mattshen.db.core.utils.ResultSetExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBI {

    private static Logger LOG = LoggerFactory.getLogger(DBI.class);

    private Connection conn = null;

    private DBI() {
    }

    public void connect() throws Exception {

        PropertiesHolder.loadConfig();
        Properties props = PropertiesHolder.getProps();

        Class.forName(props.getProperty(Constants.PROPERTIES_NAME_CLASSNAME));

        String url = props.getProperty(Constants.PROPERTIES_NAME_URL);
        String username = props.getProperty(Constants.PROPERTIES_NAME_USERNAME);
        String password = props.getProperty(Constants.PROPERTIES_NAME_PASSWORD);

        conn = DriverManager.getConnection(url, username, password);

    }

    public void close() throws SQLException {
        conn.close();
    }

    public <T> List<T> query(String sql,
                             ResultSetExtractor<T> extractor) throws SQLException {
        List<T> list = new ArrayList<T>();
        try (Statement s = conn.createStatement();
             ResultSet rs = s.executeQuery(sql)) {

            while (rs.next()) {
                list.add(extractor.extract(rs));
            }
        }
        return list;
    }

    private static class SingletonHelper {
        private static final DBI INSTANCE = new DBI();
    }

    public static DBI getInstance() {
        return SingletonHelper.INSTANCE;
    }

}
