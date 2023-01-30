package dao;

import com.company.Contact;
import java.sql.SQLException;
import java.util.List;
/**
 * Interface for contact Data Access Object Pattern (DAO) implementation.
 *
 * @author Aidan Strawder
 */
public interface ContactDAO {
    public List<Contact> getAllContacts() throws SQLException;
}
