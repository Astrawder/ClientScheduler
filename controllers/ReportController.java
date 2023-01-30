package controllers;

import com.company.Appointment;
import dao.AppointmentDAOImpl;
import dao.CustomerDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Pair;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.ResourceBundle;
/**
 * Controller class for the reports.fxml.
 *
 * @author Aidan Strawder
 */
public class ReportController implements Initializable {
    public TableView<Appointment> scheduleTableView;
    public TableColumn<Appointment, String> contactColumn;
    public TableColumn<Appointment, Integer> appointmentIDColumn;
    public TableColumn<Appointment, String> titleColumn;
    public TableColumn<Appointment, String> typeColumn;
    public TableColumn<Appointment, String> descriptionColumn;
    public TableColumn<Appointment, Timestamp> startColumn;
    public TableColumn<Appointment, Timestamp> endColumn;
    public TableColumn<Appointment, Integer> customerIDColumn;
    public TableView<Pair<String, Integer>> appointmentTypeTableView;
    public TableColumn<Pair<String, Integer>, String> appointmentTypeColumn;
    public TableColumn<Pair<String, Integer>, Integer> totalTypeColumn;
    public TableView<Pair<String, Integer>> appointmentMonthTableView;
    public TableColumn<Pair<String, Integer>, String> appointmentMonthColumn;
    public TableColumn<Pair<String, Integer>, Integer> totalMonthColumn;
    public TableView<Pair<String, Integer>> customerCountryTableView;
    public TableColumn<Pair<String, Integer>, String> countryColumn;
    public TableColumn<Pair<String, Integer>, Integer> totalCustomersColumn;
    private final ObservableList<Appointment> appointmentObservableList = FXCollections.observableArrayList();
    private final ObservableList<Pair<String, Integer>> appointmentTypesObservableList = FXCollections.observableArrayList();
    private final ObservableList<Pair<String, Integer>> appointmentMonthsObservableList = FXCollections.observableArrayList();
    private final ObservableList<Pair<String, Integer>> customersByCountriesObservableList = FXCollections.observableArrayList();
    /**
     * The initialize function for the controller of the reports.fxml.
     * Contains multiple tableviews that displays various amounts of information regarding appointments and customers.
     *
     * @param url the url
     * @param resourceBundle the resourceBundle to be used
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AppointmentDAOImpl appointmentDAO = new AppointmentDAOImpl();
        CustomerDAOImpl customerDAO = new CustomerDAOImpl();
        ArrayList<Appointment> appointments;
        ArrayList<Pair<String, Integer>> appointmentTypeTotals;
        ArrayList<Pair<String, Integer>> appointmentMonthTotals;
        ArrayList<Pair<String, Integer>> customersByCountryTotals;

        try {
            appointments = appointmentDAO.getAllAppointments("contacts.Contact_Name");
            appointmentTypeTotals = appointmentDAO.getAppointmentsByType();
            appointmentMonthTotals = appointmentDAO.getAppointmentsByMonth();
            customersByCountryTotals = customerDAO.getTotalCustomersByCountry();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
        appointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        startColumn.setCellValueFactory(new PropertyValueFactory<>("startLocalDateTime"));
        endColumn.setCellValueFactory(new PropertyValueFactory<>("endLocalDateTime"));
        customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        appointmentObservableList.addAll(appointments);
        scheduleTableView.setItems(appointmentObservableList);

        appointmentTypeColumn.setCellValueFactory(new PropertyValueFactory<>("key"));
        totalTypeColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        appointmentTypesObservableList.addAll(appointmentTypeTotals);
        appointmentTypeTableView.setItems(appointmentTypesObservableList);

        appointmentMonthColumn.setCellValueFactory(new PropertyValueFactory<>("key"));
        totalMonthColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        appointmentMonthsObservableList.addAll(appointmentMonthTotals);
        appointmentMonthTableView.setItems(appointmentMonthsObservableList);

        countryColumn.setCellValueFactory(new PropertyValueFactory<>("key"));
        totalCustomersColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        customersByCountriesObservableList.addAll(customersByCountryTotals);
        customerCountryTableView.setItems(customersByCountriesObservableList);
    }
}
