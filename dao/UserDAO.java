package dao;

import com.company.User;

import java.sql.SQLException;
import java.util.List;
/**
 * Interface for user Data Access Object Pattern (DAO) implementation.
 *
 * @author Aidan Strawder
 */
public interface UserDAO {
    public List<User> getAllUsers() throws SQLException;
    public boolean checkCredentials(String username, String password) throws SQLException;
}
