package dao;

import com.company.Customer;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
/**
 * Interface for customer Data Access Object Pattern (DAO) implementation.
 *
 * @author Aidan Strawder
 */
public interface CustomerDAO {
    public List<Customer> getAllCustomers() throws SQLException;
    public void addCustomer(Customer customer) throws SQLException;
    public void updateCustomer(int ID, String name, String address, String postalCode, String phone,
                               Timestamp lastUpdate, String lastUpdateBy, int divisionID) throws SQLException;
    public int deleteCustomer(Customer customer) throws SQLException;
}
