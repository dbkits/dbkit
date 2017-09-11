package io.github.mattshen.dbkit.core.utils;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;


public class JdbcUtils {

    /**
     * Determine the column name to use. The column name is determined based on a
     * lookup using ResultSetMetaData.
     * <p>This method implementation takes into account recent clarifications
     * expressed in the JDBC 4.0 specification:
     * <p><i>columnLabel - the label for the column specified with the SQL AS clause.
     * If the SQL AS clause was not specified, then the label is the name of the column</i>.
     * @return the column name to use
     * @param resultSetMetaData the current meta data to use
     * @param columnIndex the index of the column for the look up
     * @throws SQLException in case of lookup failure
     */
    public static String lookupColumnName(ResultSetMetaData resultSetMetaData, int columnIndex) throws SQLException {
        String name = resultSetMetaData.getColumnLabel(columnIndex);
        if (name == null || name.length() < 1) {
            name = resultSetMetaData.getColumnName(columnIndex);
        }
        return name;
    }

    public static Map<String, Object> extractToMap(ResultSet rs) throws SQLException {

        Map<String, Object> map = new LinkedHashMap<>();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        for (int index = 1; index <= columnCount; index++) {
            String column = JdbcUtils.lookupColumnName(rsmd, index);
            Object value = rs.getObject(column);
            if (value == null) {
                map.put(column, null);
            } else if (value instanceof Integer) {
                map.put(column, value);
            } else if (value instanceof String) {
                map.put(column, value);
            } else if (value instanceof Boolean) {
                map.put(column, value);
            } else if (value instanceof Date) {
                map.put(column, ((Date) value).getTime());
            } else if (value instanceof Long) {
                map.put(column, value);
            } else if (value instanceof Double) {
                map.put(column, value);
            } else if (value instanceof Float) {
                map.put(column, value);
            } else if (value instanceof BigDecimal) {
                map.put(column, value);
            } else if (value instanceof Byte) {
                map.put(column, value);
            } else if (value instanceof byte[]) {
                map.put(column, value);
            } else {
                throw new IllegalArgumentException("Unsupported Type: " + value.getClass());
            }
        }
        return map;
    }


}
