package io.github.mattshen.dbkit.core.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface RowsExtractor<T> {
    T extract(ResultSet rs) throws SQLException;
}
