package io.github.mattshen.dbkit.core;


import io.github.mattshen.dbkit.core.models.Config;
import io.github.mattshen.dbkit.core.utils.RowsExtractor;
import io.github.mattshen.dbkit.core.utils.RowExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbAccessor {

    private static Logger LOG = LoggerFactory.getLogger(DbAccessor.class);

    private Connection conn = null;

    private DbAccessor() {
    }

    public void connect(Config cfg) throws Exception {
        if (conn == null || conn.isClosed()) {
            Class.forName(cfg.getDriverClassName());
            conn = DriverManager.getConnection(cfg.getUrl(), cfg.getUsername(), cfg.getPassword());
        }
    }

    public void close() throws SQLException {
        conn.close();
    }

    private <T> List<T> convertResultSetToList(ResultSet rs, RowExtractor<T> extractor) throws SQLException {
        List<T> list = new ArrayList<>();
        while (rs.next()) {
            list.add(extractor.extract(rs));
        }
        return list;
    }

    public <T> List<T> query(String sql,
                             RowExtractor<T> extractor) throws SQLException {
        try (Statement s = conn.createStatement();
             ResultSet rs = s.executeQuery(sql)) {
            return convertResultSetToList(rs, extractor);
        }
    }

    public <T> T query(String sql, RowsExtractor<T> extractor) throws SQLException {
        try (Statement s = conn.createStatement();
             ResultSet rs = s.executeQuery(sql)) {
            return extractor.extract(rs);
        }
    }

    public <T> List<T> getSchemas(RowExtractor<T> extractor) throws SQLException {
        try (ResultSet rs = conn.getMetaData().getSchemas()){
            return convertResultSetToList(rs, extractor);
        }
    }

    public <T> List<T> getCatalogs(RowExtractor<T> extractor) throws SQLException {
        try (ResultSet rs = conn.getMetaData().getCatalogs()){
            return convertResultSetToList(rs, extractor);
        }
    }

    public <T> List<T> describeTable(String tableName, RowExtractor<T> extractor) throws SQLException {
        String currentCatalog = conn.getCatalog();
        String currentSchema = conn.getSchema();
        try (ResultSet rs = conn.getMetaData().getColumns(currentCatalog, currentSchema, tableName, "%")) {
            return convertResultSetToList(rs, extractor);
        }
    }

    public <T> List<T> getTables(RowExtractor<T> extractor) throws SQLException {
        String currentCatalog = conn.getCatalog();
        String currentSchema = conn.getSchema();
        try (ResultSet rs = conn.getMetaData().getTables(currentCatalog, currentSchema, "%", null)) {
            return convertResultSetToList(rs, extractor);
        }
    }


    private static class SingletonHelper {
        private static final DbAccessor INSTANCE = new DbAccessor();
    }

    public static DbAccessor getInstance() {
        return SingletonHelper.INSTANCE;
    }

}
