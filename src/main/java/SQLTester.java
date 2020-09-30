
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLTester {

    public static void main(String[] args) {
        Connection instance = SQLConnector.getConnection();
        try {
            Statement statement = instance.createStatement();
            ResultSet show_tables = statement.executeQuery("SHOW TABLES");
            while (show_tables.next()) {
                System.out.println(show_tables.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
