package io.github.mattshen.dbkit.core;

import java.util.List;

public class CLI {
    public static void main(String[] args) throws Exception {

        DbAccessor dba = DbAccessor.getInstance();
        dba.connect();
        List<String> resultList = dba.query("select 1", rs -> {
            return rs.getString(1);
        });
        dba.close();

        resultList.forEach(item -> {
            System.out.println(item);
        });

    }
}