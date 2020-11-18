import java.sql.*;


public class SQLConnector {
    private static Connection connection;
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                //connection = DriverManager.getConnection("jdbc:mysql://db.diplomportal.dk/s190608?" + "user=s190608&password=5USibIiZSIh7RR85vBFgH");
                connection = DriverManager.getConnection("jdbc:mariadb://192.168.239.21:3306/myuser", "madsboi", "pog");
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
    private static String findUser(String mail,String Password){
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return userCPR;
    }
}

/*
   static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static String url2 = "jdbc:mariadb://192.168.239.21:3306/logins";
    static String url = "jdbc:mariadb://[2001:878:200:4102:207:e9ff:fe62:b76]:3306/logins";
    String addresse = "jdbc:mariadb://[ip6]:3306/schemanavn";
    private static Connection conn = null;
    private static Statement statement = null;
    private PreparedStatement prep = null;

    static String inputfraCGI = null;
    public static void main(String[] args) {
        try{
            inputfraCGI = args[0];
        }catch (Exception e){

        }


        //Bruges bare til at have en metode som kan k re det hele fra.
        //DBcomm db = new DBcomm();
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            //mysql skal  ndres senere til MariaDB, localhost til en IPaddresse -
            String user, pass;
            user = "madsboi";
            pass = "pog";
            // url="jdbc:mysql://localhost:3306/phoenixpoint?serverTimezone=Europe/Amsterdam&amp";

            // Skal man fx. bruge 127.0.0.1 til en remote maskine?
//Connection connection =
// DriverManager.getConnection("jdbc:mariadb://localhost:3306/DB?user=root&password=myPassword");
            //T nk jer om - kan man opn  mariadb forbindelse til en anden maskine uden at  ndre denne her?


            conn = DriverManager.getConnection(url, user, pass);
            if (conn != null) {

                System.out.println("Im in");
            } else {
                System.out.println("connection not made");
            }

            //find out which columns are in current table:
            statement = conn.createStatement();
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

            //
            findUser("madsboi","pog");


            //db.getHomeData();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }




    }

    private static String findUser(String mail,String Password){
      String userCPR =null;

      String sqlFindUser = "select idloginoplysninger,cpr,mail from loginoplysninger where password ='" +Password+ "'and mail ='"+ mail +"';";
        try {
            ResultSet rs = statement.executeQuery(sqlFindUser);
            rs.next();
            int id = rs.getInt(1);
            String cpr = rs.getString(2);
            String email = rs.getString(3);
            System.out.println("Id:"+id);
            System.out.println("cpr:"+cpr);
            System.out.println("mail:"+email);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return userCPR;
    }
}
 */