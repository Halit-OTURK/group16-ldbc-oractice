package jdbc_tests;

import Utilities.DBUtils;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class DB_Util_Practice {
    @Test
    public void test1() {

        // create connection
        DBUtils.createConnection();
        String query = "select first_name, last_name,salary, job_id from employees where rownum<6";
       // alt + enter DB_Utilities.getQueryResultMap(query); => List<Map<String, Object>> queryResultMap = DB_Utilities.getQueryResultMap(query);
        List<Map<String, Object>> queryData = DBUtils.getQueryResultMap(query);
        for (Map<String, Object> row : queryData) {
            System.out.println(row.toString());
        }
        DBUtils.destroy();
    }

    @Test
    public void test2(){
        // create connection
        DBUtils.createConnection();
        String query = "select first_name, last_name,salary, job_id from employees where rownum<2";
       // we have only one row. because of that we can use getRowMap() utility method
        Map<String, Object> rowMap = DBUtils.getRowMap(query);
        System.out.println(rowMap.toString());

        DBUtils.destroy();
    }
}
