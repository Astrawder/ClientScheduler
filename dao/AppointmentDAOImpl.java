package dao;

import com.company.Appointment;
import com.company.Customer;
import helper.JDBC;
import javafx.util.Pair;
import java.sql.*;
import java.util.ArrayList;
/**
 * The implementation class for the AppointmentDAO interface.
 * This class uses SQL to interact with the MySQL database.
 *
 * @author Aidan Strawder
 */
public class AppointmentDAOImpl implements AppointmentDAO{
    private final ArrayList<Appointment> appointments;
    /**
     * Appointment DAO implementation class constructor.
     *
     */
    public AppointmentDAOImpl(){
        appointments = new ArrayList<>();
    }

    /**
     * Retrieves all appointments from the MySQL database.
     *
     * @return the appointments
     * @throws SQLException createStatement() throws SQLException
     * @throws SQLException executeQuery() throws SQLException
     * @throws SQLException next() throws SQLException
     * @throws SQLException  getInt() throws SQLException
     * @throws SQLException  getString() throws SQLException
     * @throws SQLException  getTimestamp() throws SQLException
     */
    @Override
    public ArrayList<Appointment> getAllAppointments() throws SQLException {
        JDBC.openConnection();
        Statement statement = JDBC.connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT appointments.*, contacts.Contact_Name FROM appointments\n" +
                "INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID ORDER BY Appointment_ID ASC;");

        while (resultSet.next()){
            int ID = resultSet.getInt(1);
            String title = resultSet.getString(2);
            String description = resultSet.getString(3);
            String location = resultSet.getString(4);
            String type = resultSet.getString(5);
            Timestamp startTime = resultSet.getTimestamp(6);
            Timestamp endTime = resultSet.getTimestamp(7);
            Timestamp createDate = resultSet.getTimestamp(8);
            String createdBy = resultSet.getString(9);
            Timestamp lastUpdate = resultSet.getTimestamp(10);
            String lastUpdateBy = resultSet.getString(11);
            int customerID = resultSet.getInt(12);
            int userID = resultSet.getInt(13);
            int contactID = resultSet.getInt(14);
            String contact = resultSet.getString(15);
            Appointment appointment = new Appointment(ID, title, description, location, type, startTime, endTime, createDate,
                    createdBy, lastUpdate, lastUpdateBy, customerID, userID, contactID, contact);
            appointments.add(appointment);
        }

        JDBC.closeConnection();
        return appointments;
    }
    /**
     * Retrieves all appointments from the MySQL database with a specific sql ORDER BY statement to be inputted.
     *
     * @return the appointments
     * @throws SQLException createStatement() throws SQLException
     * @throws SQLException executeQuery() throws SQLException
     * @throws SQLException next() throws SQLException
     * @throws SQLException  getInt() throws SQLException
     * @throws SQLException  getString() throws SQLException
     * @throws SQLException  getTimestamp() throws SQLException
     */
    public ArrayList<Appointment> getAllAppointments(String orderBy) throws SQLException {
        JDBC.openConnection();
        Statement statement = JDBC.connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT appointments.*, contacts.Contact_Name FROM appointments\n" +
                "INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID ORDER BY " + orderBy + ";");

        while (resultSet.next()){
            int ID = resultSet.getInt(1);
            String title = resultSet.getString(2);
            String description = resultSet.getString(3);
            String location = resultSet.getString(4);
            String type = resultSet.getString(5);
            Timestamp startTime = resultSet.getTimestamp(6);
            Timestamp endTime = resultSet.getTimestamp(7);
            Timestamp createDate = resultSet.getTimestamp(8);
            String createdBy = resultSet.getString(9);
            Timestamp lastUpdate = resultSet.getTimestamp(10);
            String lastUpdateBy = resultSet.getString(11);
            int customerID = resultSet.getInt(12);
            int userID = resultSet.getInt(13);
            int contactID = resultSet.getInt(14);
            String contact = resultSet.getString(15);
            Appointment appointment = new Appointment(ID, title, description, location, type, startTime, endTime, createDate,
                    createdBy, lastUpdate, lastUpdateBy, customerID, userID, contactID, contact);
            appointments.add(appointment);
        }

        JDBC.closeConnection();
        return appointments;
    }
    /**
     * Adds appointment info to the MySQL database.
     *
     * @throws SQLException preparedStatement() throws SQLException
     * @throws SQLException executeUpdate() throws SQLException
     * @throws SQLException  setInt() throws SQLException
     * @throws SQLException  setString() throws SQLException
     * @throws SQLException  setTimestamp() throws SQLException
     * @param appointment the appointment to be added.
     */
    @Override
    public void addAppointment(Appointment appointment) throws SQLException {
        JDBC.openConnection();

        String sql = "INSERT INTO appointments (Appointment_ID, Title, Description, Location, Type, Start, End, Create_Date, Created_By ,Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) \n" +
                " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setInt(1, appointment.getID());
        preparedStatement.setString(2, appointment.getTitle());
        preparedStatement.setString(3, appointment.getDescription());
        preparedStatement.setString(4, appointment.getLocation());
        preparedStatement.setString(5, appointment.getType());
        preparedStatement.setTimestamp(6, appointment.getStartDateTime());
        preparedStatement.setTimestamp(7, appointment.getEndDateTime());
        preparedStatement.setTimestamp(8, appointment.getCreateDate());
        preparedStatement.setString(9, appointment.getCreatedBy());
        preparedStatement.setTimestamp(10, appointment.getLastUpdate());
        preparedStatement.setString(11, appointment.getLastUpdateBy());
        preparedStatement.setInt(12, appointment.getCustomerID());
        preparedStatement.setInt(13, appointment.getUserID());
        preparedStatement.setInt(14, appointment.getContactID());

        preparedStatement.executeUpdate();
        JDBC.closeConnection();
    }
    /**
     * Updates appointment info in the MySQL database.
     *
     * @throws SQLException preparedStatement() throws SQLException
     * @throws SQLException executeUpdate() throws SQLException
     * @throws SQLException  setInt() throws SQLException
     * @throws SQLException  setString() throws SQLException
     * @throws SQLException  setTimestamp() throws SQLException
     * @param ID the appointment id
     * @param title the appointment title
     * @param description the appointment description
     * @param location the appointment location
     * @param type the appointment type
     * @param start the appointment start timestamp
     * @param end the appointment end timestamp
     * @param lastUpdate the appointment last update
     * @param lastUpdateBy the appointment last update by
     * @param customerID the appointment customer id
     * @param userID the appointment user id
     * @param contactID the appointment contact id
     */
    @Override
    public void updateAppointment(int ID, String title, String description, String location, String type,
                                  Timestamp start, Timestamp end, Timestamp lastUpdate, String lastUpdateBy, int customerID, int userID, int contactID) throws SQLException {
        JDBC.openConnection();

        String sql = "UPDATE appointments "
                + "SET Title=?, Description=?, Location=?, Type=?, Start=?, End=?, Last_Update=?," +
                " Last_Updated_By=?, Customer_ID=?, User_ID=?, Contact_ID=? WHERE Appointment_ID = " + "\"" + ID + "\"" + ";";

        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setString(1, title);
        preparedStatement.setString(2, description);
        preparedStatement.setString(3, location);
        preparedStatement.setString(4, type);
        preparedStatement.setTimestamp(5, start);
        preparedStatement.setTimestamp(6, end);
        preparedStatement.setTimestamp(7, lastUpdate);
        preparedStatement.setString(8, lastUpdateBy);
        preparedStatement.setInt(9, customerID);
        preparedStatement.setInt(10, userID);
        preparedStatement.setInt(11, contactID);

        preparedStatement.executeUpdate();
        JDBC.closeConnection();
    }
    /**
     * Deletes appointment info from the MySQL database.
     *
     * @return the result of the delete operation
     * @throws SQLException preparedStatement() throws SQLException
     * @throws SQLException executeUpdate() throws SQLException
     * @param appointment the appointment to be deleted
     */
    @Override
    public int deleteAppointment(Appointment appointment) throws SQLException {
        JDBC.openConnection();

        String sql = "DELETE FROM appointments WHERE Appointment_ID = " + "\"" + appointment.getID() + "\"" + ";";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        int result = preparedStatement.executeUpdate();

        JDBC.closeConnection();
        return result;
    }
    /**
     * Deletes appointment info in the MySQL database using customer id.
     *
     * @throws SQLException preparedStatement() throws SQLException
     * @throws SQLException executeUpdate() throws SQLException
     * @param customer the customer
     */
    @Override
    public void deleteAppointmentByCustomerID (Customer customer) throws SQLException {
        JDBC.openConnection();

        String sql = "DELETE FROM appointments WHERE Customer_ID = " + "\"" + customer.getID() + "\"" + ";";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.executeUpdate();

        JDBC.closeConnection();
    }
    /**
     * Retrieves all the appointments by type from the MySQL database;
     *
     * @throws SQLException createStatement() throws SQLException
     * @throws SQLException executeQuery() throws SQLException
     * @throws SQLException  getInt() throws SQLException
     * @throws SQLException  getString() throws SQLException
     */
    public ArrayList<Pair<String, Integer>> getAppointmentsByType() throws SQLException {
        ArrayList<Pair<String, Integer>> appointmentTypeTotals = new ArrayList<>();

        JDBC.openConnection();
        Statement statement = JDBC.connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT Type, count(Type) as Total FROM appointments\n" +
                "GROUP BY Type;");

        while (resultSet.next()){
            String type = resultSet.getString(1);
            int total = resultSet.getInt(2);
            Pair<String, Integer> typeAndTotal = new Pair<>(type, total);
            appointmentTypeTotals.add(typeAndTotal);
        }

        JDBC.closeConnection();
        return appointmentTypeTotals;
    }
    /**
     * Retrieves all appointments by month from the MySQL database;
     *
     * @throws SQLException createStatement() throws SQLException
     * @throws SQLException executeQuery() throws SQLException
     * @throws SQLException  getInt() throws SQLException
     * @throws SQLException  getString() throws SQLException
     */
    public ArrayList<Pair<String, Integer>> getAppointmentsByMonth() throws SQLException {
        ArrayList<Pair<String, Integer>> appointmentMonthTotals = new ArrayList<>();

        JDBC.openConnection();
        Statement statement = JDBC.connection.createStatement();
        ResultSet resultSet = statement.executeQuery("Select monthname(Start) as Month, count(monthname(Start)) as Total FROM appointments\n" +
                "GROUP BY Month\n" +
                "ORDER BY month(Start);");

        while (resultSet.next()){
            String month = resultSet.getString(1);
            int total = resultSet.getInt(2);
            Pair<String, Integer> monthAndTotal = new Pair<>(month, total);
            appointmentMonthTotals.add(monthAndTotal);
        }

        JDBC.closeConnection();
        return appointmentMonthTotals;
    }
}
