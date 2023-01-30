package dao;

import com.company.Customer;
import controllers.LoginController;
import helper.JDBC;
import javafx.util.Pair;
import java.sql.*;
import java.util.ArrayList;
/**
 * The implementation class for the CustomerDAO interface.
 * This class uses SQL to interact with the MySQL database.
 *
 * @author Aidan Strawder
 */
public class CustomerDAOImpl implements CustomerDAO {
    private final ArrayList<Customer> customers;
    /**
     * Customer DAO implementation class constructor.
     *
     */
    public CustomerDAOImpl(){
        customers = new ArrayList<>();
    }
    /**
     * Retrieves all customers from the MySQL database.
     *
     * @return the customers
     * @throws SQLException createStatement() throws SQLException
     * @throws SQLException executeQuery() throws SQLException
     * @throws SQLException next() throws SQLException
     * @throws SQLException  getInt() throws SQLException
     * @throws SQLException  getString() throws SQLException
     * @throws SQLException  getTimestamp() throws SQLException
     */
    @Override
    public ArrayList<Customer> getAllCustomers() throws SQLException {
        JDBC.openConnection();
        Statement statement = JDBC.connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT customers.*, first_level_divisions.Division, countries.Country FROM customers\n" +
                "INNER JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID\n" +
                "INNER JOIN countries ON first_level_divisions.Country_ID = countries.Country_ID\n" +
                "ORDER BY Customer_ID ASC;");

        while (resultSet.next()){
            int ID = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String postalCode = resultSet.getString(4);
            String phone = resultSet.getString(5);
            Timestamp createDate = resultSet.getTimestamp(6);
            String createdBy = resultSet.getString(7);
            Timestamp lastUpdate = resultSet.getTimestamp(8);
            String lastUpdateBy = resultSet.getString(9);
            int divisionID = resultSet.getInt(10);
            String division = resultSet.getString(11);
            String country = resultSet.getString(12);
            Customer customer = new Customer(ID, name, address, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy, divisionID, division, country);
            customers.add(customer);
        }

        JDBC.closeConnection();
        return customers;
    }
    /**
     * Adds customer info to the MySQL database.
     *
     * @throws SQLException preparedStatement() throws SQLException
     * @throws SQLException executeUpdate() throws SQLException
     * @throws SQLException  setInt() throws SQLException
     * @throws SQLException  setString() throws SQLException
     * @throws SQLException  setTimestamp() throws SQLException
     * @param customer the customer to be added
     */
    @Override
    public void addCustomer(Customer customer) throws SQLException {
        JDBC.openConnection();

        String sql = "INSERT INTO customers (Customer_ID, Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) \n" +
                " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setInt(1, customer.getID());
        preparedStatement.setString(2, customer.getName());
        preparedStatement.setString(3, customer.getAddress());
        preparedStatement.setString(4, customer.getPostalCode());
        preparedStatement.setString(5, customer.getPhone());
        preparedStatement.setTimestamp(6, customer.getCreateDate());
        preparedStatement.setString(7, LoginController.currentLoggedOnUser);
        preparedStatement.setTimestamp(8, customer.getLastUpdate());
        preparedStatement.setString(9, LoginController.currentLoggedOnUser);
        preparedStatement.setInt(10, customer.getDivisionID());

        preparedStatement.executeUpdate();
        JDBC.closeConnection();
    }
    /**
     * Updates customer info in the MySQL database.
     *
     * @throws SQLException preparedStatement() throws SQLException
     * @throws SQLException executeUpdate() throws SQLException
     * @throws SQLException  setInt() throws SQLException
     * @throws SQLException  setString() throws SQLException
     * @throws SQLException  setTimestamp() throws SQLException
     * @param ID the customer id
     * @param name the customer name
     * @param address the customer address
     * @param postalCode the customer postal code
     * @param phone the customer phone number
     * @param lastUpdate the appointment last update
     * @param lastUpdateBy the appointment last update by
     * @param divisionID the customer division id
     */
    @Override
    public void updateCustomer(int ID, String name, String address, String postalCode, String phone,
                               Timestamp lastUpdate, String lastUpdateBy, int divisionID) throws SQLException {
        JDBC.openConnection();

        String sql = "UPDATE customers "
                + "SET Customer_Name=?, Address=?, Postal_Code=?, Phone=?, Last_Update=?," +
                " Last_Updated_By=?, Division_ID=? WHERE Customer_ID = " + "\"" + ID + "\"" + ";";

        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, address);
        preparedStatement.setString(3, postalCode);
        preparedStatement.setString(4, phone);
        preparedStatement.setTimestamp(5, lastUpdate);
        preparedStatement.setString(6, lastUpdateBy);
        preparedStatement.setInt(7, divisionID);

        preparedStatement.executeUpdate();
        JDBC.closeConnection();
    }
    /**
     * Deletes customer info from the MySQL database.
     * Follows the foreign key constraints of the table and deletes all appointments associated with the deleted customer.
     *
     * @throws SQLException preparedStatement() throws SQLException
     * @throws SQLException executeUpdate() throws SQLException
     * @throws SQLException deleteAppointmentByCustomerID() throws SQLException
     * @param customer the customer to be deleted
     */
    @Override
    public int deleteCustomer(Customer customer) throws SQLException {
        AppointmentDAOImpl appointmentDAO = new AppointmentDAOImpl();
        appointmentDAO.deleteAppointmentByCustomerID(customer);

        JDBC.openConnection();

        String sql = "DELETE FROM customers WHERE Customer_ID = " + "\"" + customer.getID() + "\"" + ";";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        int result = preparedStatement.executeUpdate();

        JDBC.closeConnection();
        return result;
    }
    /**
     * Retrieves all the customers by country.
     *
     * @throws SQLException createStatement() throws SQLException
     * @throws SQLException executeQuery() throws SQLException
     * @throws SQLException next() throws SQLException
     * @throws SQLException  getInt() throws SQLException
     * @throws SQLException  getString() throws SQLException
     */
    public ArrayList<Pair<String, Integer>> getTotalCustomersByCountry() throws SQLException {
        ArrayList<Pair<String, Integer>> customerCountryTotals = new ArrayList<>();

        JDBC.openConnection();
        Statement statement = JDBC.connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT countries.Country, COUNT(customers.Customer_Name) as Total FROM customers\n" +
                "INNER JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID\n" +
                "INNER JOIN countries ON first_level_divisions.Country_ID = countries.Country_ID\n" +
                "GROUP BY countries.Country;");

        while (resultSet.next()){
            String country = resultSet.getString(1);
            int totalCustomers = resultSet.getInt(2);
            Pair<String, Integer> countriesAndCustomers = new Pair<>(country, totalCustomers);
            customerCountryTotals.add(countriesAndCustomers);
        }

        JDBC.closeConnection();
        return customerCountryTotals;
    }
}
