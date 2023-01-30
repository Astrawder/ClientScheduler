package dao;

import com.company.Appointment;
import com.company.Customer;
import javafx.util.Pair;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
/**
 * Interface for appointment Data Access Object Pattern (DAO) implementation.
 *
 * @author Aidan Strawder
 */
public interface AppointmentDAO {
    public List<Appointment> getAllAppointments() throws SQLException;
    public List<Appointment> getAllAppointments(String orderBy) throws SQLException;
    public void addAppointment(Appointment appointment) throws SQLException;
    public void updateAppointment(int ID, String title, String description, String location, String type,
                                  Timestamp start, Timestamp end, Timestamp lastUpdate, String lastUpdateBy, int customerID, int userID, int contactID) throws SQLException;
    public int deleteAppointment(Appointment appointment) throws SQLException;
    public void deleteAppointmentByCustomerID (Customer customer) throws SQLException;
    public ArrayList<Pair<String, Integer>> getAppointmentsByType() throws SQLException;
    public ArrayList<Pair<String, Integer>> getAppointmentsByMonth() throws SQLException;
}
