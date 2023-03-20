package jdbc_tests;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class listOfMapExamples {
    String dbUrl = "jdbc:oracle:thin:@54.82.13.103:1521:XE";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test1() {

        //creating list for keeping all the row maps

        List<Map<String,Object>> queryData = new ArrayList<>();

        Map<String, Object> row1 = new HashMap<>();
        row1.put("First Name", "Steven");
        row1.put("Second Name", "King");
        row1.put("Salary", 24000);
        row1.put("Job id", "AD_PRES");
        System.out.println(row1.toString());

        Map<String, Object> row2 = new HashMap<>();
        row2.put("First Name", "Ali");
        row2.put("Second Name", "Osman");
        row2.put("Salary", 17000);
        row2.put("Job id", "AD_PRES");
        System.out.println(row2.toString());

        queryData.add(row1);
        queryData.add(row2);

        // get the steven last name directly

        System.out.println(queryData.get(0).get("Second Name"));

        // get the neena's salary directly
        System.out.println(queryData.get(1).get("Salary"));

    }

    @Test
    public void test2() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select first_name, last_name,salary, job_id from employees where rownum<6");

        ResultSetMetaData rsmd= resultSet.getMetaData();
        resultSet.next();

        //creating list for keeping all the row maps

        List<Map<String,Object>> queryData = new ArrayList<>();

        Map<String, Object> row1 = new HashMap<>();
        row1.put(rsmd.getColumnName(1), resultSet.getString(1));
        row1.put(rsmd.getColumnName(2), resultSet.getString(2));
        row1.put(rsmd.getColumnName(3), resultSet.getInt(3));
        row1.put(rsmd.getColumnName(4), resultSet.getString(4));


        System.out.println(row1.toString());

        resultSet.next();

        Map<String, Object> row2 = new HashMap<>();
        row2.put(rsmd.getColumnName(1), resultSet.getString(1));
        row2.put(rsmd.getColumnName(2), resultSet.getString(2));
        row2.put(rsmd.getColumnName(3), resultSet.getInt(3));
        row2.put(rsmd.getColumnName(4), resultSet.getString(4));


        System.out.println(row2.toString());

        queryData.add(row1);
        queryData.add(row2);



        resultSet.close();
        statement.close();
        connection.close();
    }

}
