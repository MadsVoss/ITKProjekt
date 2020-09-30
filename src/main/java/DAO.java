import java.util.LinkedList;

public interface DAO {
    void saveLogin(LinkedList<DTO> ekgdtobatch);
    LinkedList<DTO> loadLogin(String id);
}