package dao;

import com.company.User;
import helper.JDBC;

import java.sql.*;
import java.util.ArrayList;
/**
 * The implementation class for the UserDAO interface.
 * This class uses SQL to interact with the MySQL database.
 *
 * @author Aidan Strawder
 */
public class UserDAOImpl implements UserDAO {

    private final ArrayList<User> users;
    /**
     * User DAO implementation class constructor.
     *
     */
    public UserDAOImpl(){
        users = new ArrayList<>();
    }
    /**
     * Retrieves all users from the MySQL database.
     *
     * @return the users
     * @throws SQLException createStatement() throws SQLException
     * @throws SQLException executeQuery() throws SQLException
     * @throws SQLException next() throws SQLException
     * @throws SQLException  getInt() throws SQLException
     * @throws SQLException  getString() throws SQLException
     */
    @Override
    public ArrayList<User> getAllUsers() throws SQLException {
        JDBC.openConnection();
        Statement statement = JDBC.connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users;");

        while (resultSet.next()){
            int ID = resultSet.getInt(1);
            String username = resultSet.getString(2);
            String password = resultSet.getString(3);
            Timestamp createDate = resultSet.getTimestamp(4);
            String createdBy = resultSet.getString(5);
            Timestamp lastUpdate = resultSet.getTimestamp(6);
            String lastUpdateBy = resultSet.getString(7);
            User user = new User(ID, username, password, createDate, createdBy, lastUpdate, lastUpdateBy);
            users.add(user);
        }

        JDBC.closeConnection();
        return users;
    }
    /**
     * Checks login user credentials in the MySQL database.
     *
     * @return boolean
     * @throws SQLException preparedStatement() throws SQLException
     * @throws SQLException executeQuery() throws SQLException
     * @throws SQLException next() throws SQLException
     * @throws SQLException  setString() throws SQLException
     * @param username the username to check
     * @param password the password to check
     */
    @Override
    public boolean checkCredentials(String username, String password) throws SQLException {
        JDBC.openConnection();

        String sql = "SELECT * FROM users WHERE " + "User_Name = ? AND Password = ?;";

        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(!resultSet.next()){
            JDBC.closeConnection();
            return false;
        }
        else{
            JDBC.closeConnection();
            return true;
        }
    }
}
