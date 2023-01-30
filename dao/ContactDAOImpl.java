package dao;

import com.company.Contact;
import helper.JDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 * The implementation class for the ContactDAO interface.
 * This class uses SQL to interact with the MySQL database.
 *
 * @author Aidan Strawder
 */
public class ContactDAOImpl implements ContactDAO{

    private final ArrayList<Contact> contacts;
    /**
     * Contact DAO implementation class constructor.
     *
     */
    public ContactDAOImpl(){
        contacts = new ArrayList<>();
    }
    /**
     * Retrieves all contacts from the MySQL database.
     *
     * @return the customers
     * @throws SQLException createStatement() throws SQLException
     * @throws SQLException executeQuery() throws SQLException
     * @throws SQLException next() throws SQLException
     * @throws SQLException  getInt() throws SQLException
     * @throws SQLException  getString() throws SQLException
     */
    @Override
    public ArrayList<Contact> getAllContacts() throws SQLException {
        JDBC.openConnection();
        Statement statement = JDBC.connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM contacts;");

        while (resultSet.next()){
            int ID = resultSet.getInt(1);
            String contactName = resultSet.getString(2);
            String email = resultSet.getString(3);
            Contact contact = new Contact(ID, contactName, email);
            contacts.add(contact);
        }

        JDBC.closeConnection();
        return contacts;
    }
    /**
     * Retrieves the id of the inputted contact name.
     *
     * @return the customers
     * @throws SQLException createStatement() throws SQLException
     * @throws SQLException executeQuery() throws SQLException
     * @throws SQLException next() throws SQLException
     * @throws SQLException  getInt() throws SQLException
     * @param name the contact name
     */
    public int getContactID(String name) throws SQLException {
        JDBC.openConnection();
        Statement statement = JDBC.connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT Contact_ID FROM contacts WHERE Contact_Name = " + "\"" + name + "\"" + ";");
        resultSet.next();
        int ID = resultSet.getInt(1);
        JDBC.closeConnection();
        return ID;
    }
}
