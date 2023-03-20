package jdbc_tests;

import Utilities.DB_Utilities;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class LibraryConnectionTest {
    @Test
    public void test1() throws SQLException {

        String dbUrl = "jdbc:mysql://34.230.35.214:3306/library1";
        String dbUsername = "library1_client";
        String dbPassword = "WVF4NdGXCKHeE6VQ";

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from books");


        resultSet.next();
        System.out.println(resultSet.getString(1)+" - "+ resultSet.getString(2));



        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void test2(){
        String dbUrl = "jdbc:mysql://34.230.35.214:3306/library1";
        String dbUsername = "library1_client";
        String dbPassword = "WVF4NdGXCKHeE6VQ";

        DB_Utilities.createConnection(dbUrl,dbUsername,dbPassword);

        DB_Utilities.destroy();
    }
}
