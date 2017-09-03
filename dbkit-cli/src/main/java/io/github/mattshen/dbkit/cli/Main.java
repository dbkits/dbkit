package io.github.mattshen.dbkit.cli;

import io.github.mattshen.dbkit.core.*;
import java.util.List;

public class Main {

  public static void main(String[] args) throws Exception {
    System.out.println("hello cli");

    DbAccessor dbAccessor = DbAccessor.getInstance();
    dbAccessor.connect();

    List<String> result = dbAccessor.query("select 1", rs -> rs.getString(1));
    System.out.println("result: " + result.get(0));

    dbAccessor.close();

  }

}