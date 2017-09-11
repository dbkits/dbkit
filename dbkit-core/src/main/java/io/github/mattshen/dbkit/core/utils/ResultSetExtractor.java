package io.github.mattshen.dbkit.core.utils;


import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface ResultSetExtractor<T> {
    T extract(ResultSet rs) throws SQLException;
}