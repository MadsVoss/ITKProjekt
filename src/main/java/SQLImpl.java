import java.sql.*;
import java.util.LinkedList;

public class SQLImpl implements DAO {

    //The cpr, data, and timestamp gets saved on the database into the table ekgData in MySQL
    @Override
    public void saveLogin(final LinkedList<DTO> logintobatch) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = SQLConnector.getConnection();

                try {
                    PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO itkSEM3(cpr, password) VALUES (?, ?)");
                    for (DTO ekgDTO: logintobatch) {
                        preparedStatement.setString(1, ekgDTO.getCpr());
                        preparedStatement.setString(2, ekgDTO.getPassword());
                        preparedStatement.execute();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    //Loads data from the database back into java, here it selects everything from the table ekgData where the cpr is equal to what you write in the GUI.
//It has a limit of 150, this is because it would otherwise look cluttered. In reality it would have been nice to have the statement also select a given data and time to show some more specific data.
    @Override
    public LinkedList<DTO> loadLogin(String id) {
        LinkedList<DTO> data = new LinkedList<>();
        Connection connection = SQLConnector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM itkSEM3 WHERE cpr = ? limit 150 ");
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DTO nDTO = new DTO();
                nDTO.setId(Integer.parseInt(resultSet.getString("id")));
                nDTO.setCpr(id);
                nDTO.setPassword(resultSet.getString("password"));
                data.add(nDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}

