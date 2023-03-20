package jdbc_tests;

import Utilities.DB_Utilities;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class DB_Util_Practice {
    @Test
    public void test1() {

        // create connection
        DB_Utilities.createConnection();
        String query = "select first_name, last_name,salary, job_id from employees where rownum<6";
       // alt + enter DB_Utilities.getQueryResultMap(query); => List<Map<String, Object>> queryResultMap = DB_Utilities.getQueryResultMap(query);
        List<Map<String, Object>> queryData = DB_Utilities.getQueryResultMap(query);
        for (Map<String, Object> row : queryData) {
            System.out.println(row.toString());
        }
        DB_Utilities.destroy();
    }

    @Test
    public void test2(){
        // create connection
        DB_Utilities.createConnection();
        String query = "select first_name, last_name,salary, job_id from employees where rownum<2";
       // we have only one row. because of that we can use getRowMap() utility method
        Map<String, Object> rowMap = DB_Utilities.getRowMap(query);
        System.out.println(rowMap.toString());

        DB_Utilities.destroy();
    }
}
