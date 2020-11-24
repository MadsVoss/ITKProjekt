import java.sql.*;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.StringTokenizer;


public class SQLConnector {
    private static Connection connection;
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection("jdbc:mysql://db.diplomportal.dk/s190608?" + "user=s190608&password=5USibIiZSIh7RR85vBFgH");
                //connection = DriverManager.getConnection("jdbc:mariadb://192.168.239.21:3306/myuser", "madsboi", "pog");
                //connection = DriverManager.getConnection("jdbc:mariadb://[2001:878:200:4102:207:e9ff:fe62:b76]:3306/logins" + "user=madsboi&password=pog");
            }
            Statement statement = connection.createStatement();
            statement = connection.createStatement();
            String sql = "select * from loginoplysninger;";
            ResultSet rs = statement.executeQuery(sql);
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int numberOfColumns = rsMetaData.getColumnCount();

            // get the column names; column indexes start from 1
            for (int i = 1; i < numberOfColumns + 1; i++) {
                String columnName = rsMetaData.getColumnName(i);
                // Get the name of the column's table name
                String tableName = rsMetaData.getTableName(i);
                System.out.println("column name=" + columnName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static String findUser(String mail,String Password){
        String userCPR =null;

        String sqlFindUser = "select idloginoplysninger,cpr,mail from loginoplysninger where password ='" +Password+ "'and mail ='"+ mail +"';";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sqlFindUser);
            rs.next();
            int id = rs.getInt(1);
            String cpr = rs.getString(2);
            String email = rs.getString(3);
            System.out.println("Id:"+id);
            System.out.println("cpr:"+cpr);
            System.out.println("mail:"+email);
            userCPR = cpr;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return userCPR;
    }
}
