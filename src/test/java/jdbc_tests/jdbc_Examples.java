package jdbc_tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class jdbc_Examples {

    String dbUrl = "jdbc:oracle:thin:@54.82.13.103:1521:XE";
    String dbUsername = "hr";
    String dbPassword = "hr";


    @Test
    public void test1() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from departments");

        resultSet.next();

        System.out.println(resultSet.getString(2));

        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2) +
                    " - " + resultSet.getString(3) + " - " + resultSet.getInt(4));
            // we write getString at 3. column because of null values
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
    @DisplayName("ResultSets Methods")
    @Test
    public void test2() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from departments");

// how to find how many row we have for query
        // 1- move to last row
        resultSet.last();
        // 2- get the row number
       // System.out.println(resultSet.getRow());
        int rowCount= resultSet.getRow();
        System.out.println("rowCount = " + rowCount);

        // print all second column information

        resultSet.beforeFirst();
        while (resultSet.next()){
            System.out.println(resultSet.getString(2));
        }

        resultSet.close();
        statement.close();
        connection.close();

    }

    @Test
    public void test3() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from departments");

// get the database related inside the dbmetadata object

        DatabaseMetaData dbmetadata = connection.getMetaData();
        System.out.println("dbmetadata.getUserName() = " + dbmetadata.getUserName());
        System.out.println("dbmetadata.getDatabaseProductName() = " + dbmetadata.getDatabaseProductName());
        System.out.println("dbmetadata.getDatabaseProductVersion() = " + dbmetadata.getDatabaseProductVersion());
        System.out.println("dbmetadata.getDriverName() = " + dbmetadata.getDriverName());
        System.out.println("dbmetadata.getDriverVersion() = " + dbmetadata.getDriverVersion());

        // get the Resultsetmetadata

        ResultSetMetaData rsMetaData =resultSet.getMetaData();

        // how many column we have?

        int columnCount = rsMetaData.getColumnCount();
        System.out.println("columnCount = " + columnCount);

        // get column names

        System.out.println("rsMetaData.getColumnName() = " + rsMetaData.getColumnName(2));

        //print all the columns names dynamically

        for (int i = 1; i <= columnCount; i++) { // int columnCount = rsMetaData.getColumnCount();
            System.out.println("rsMetaData.getColumnName() = " + rsMetaData.getColumnName(i));
        }


        resultSet.close();
        statement.close();
        connection.close();

    }
}
/*
Result Methods:
- next() → move to next row
- previous() → move to previous row
- beforeFirst() → goes before the first row
- afterLast() → goes after last row
- getRow() → get the current row number
- last() → move to last row
- absolute() - goes specific row
 */
/*
If take error about previous or other ResultSet methods for example previous
ResultSet.TYPE_SCROLL_INSENSITIVE Allows us to navigate up and down in
query results
ResultSet.CONCUR_READ_ONLY Read only, don’t update results set
 */