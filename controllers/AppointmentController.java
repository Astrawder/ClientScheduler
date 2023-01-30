package controllers;

import com.company.Appointment;
import dao.AppointmentDAOImpl;
import helper.AppointmentHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
/**
 * Controller class for the appointments.fxml.
 *
 * @author Aidan Strawder
 */
public class AppointmentController implements Initializable {
    public TableView<Appointment> appointmentTable;
    public TableColumn<Appointment, Integer> idColumn;
    public TableColumn<Appointment, String> titleColumn;
    public TableColumn<Appointment, String> descriptionColumn;
    public TableColumn<Appointment, String> locationColumn;
    public TableColumn<Appointment, String> contactColumn;
    public TableColumn<Appointment, String> typeColumn;
    public TableColumn<Appointment, Timestamp> startTimeColumn;
    public TableColumn<Appointment, Timestamp> endTimeColumn;
    public TableColumn<Appointment, Integer> customerIDColumn;
    public TableColumn<Appointment, Integer> userIDColumn;
    public RadioButton allRadioButton;
    public RadioButton weeklyRadioButton;
    public RadioButton monthlyRadioButton;
    private final ObservableList<Appointment> appointmentObservableList = FXCollections.observableArrayList();
    /**
     * The initialize function for the controller of the appointments.fxml.
     * Contains the tableview that displays appointment information.
     *
     * @param url the url
     * @param resourceBundle the resourceBundle to be used
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AppointmentDAOImpl appointmentDAO = new AppointmentDAOImpl();
        ArrayList<Appointment> appointments;

        try {
            appointments = appointmentDAO.getAllAppointments();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startLocalDateTime"));
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endLocalDateTime"));
        customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        userIDColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));

        appointmentObservableList.addAll(appointments);
        appointmentTable.setItems(appointmentObservableList);
        allRadioButton.setSelected(true);
    }
    /**
     * Handles the click event for the add button.
     * Loads the addAppointment.fxml when clicked.
     *
     * @throws IOException load() throws IOException
     */
    public void onAddAppointmentClick() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/addAppointment.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
        stage.setOnHidden(windowEvent -> refreshAppointmentTable());
        allRadioButton.setSelected(true);
        weeklyRadioButton.setSelected(false);
        monthlyRadioButton.setSelected(false);
    }
    /**
     * Handles the click event for the update button.
     * Loads the updateAppointment.fxml when clicked.
     *
     * @throws IOException load() throws IOException
     */
    public void onUpdateAppointmentClick() throws IOException {
        Appointment appointment = appointmentTable.getSelectionModel().getSelectedItem();

        if(appointment == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select an appointment before attempting to update.");
            alert.show();
            return;
        }

        AppointmentHolder holder = AppointmentHolder.getInstance();
        holder.setAppointment(appointment);

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/updateAppointment.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
        stage.setOnHidden(windowEvent -> refreshAppointmentTable());
        allRadioButton.setSelected(true);
        weeklyRadioButton.setSelected(false);
        monthlyRadioButton.setSelected(false);
    }
    /**
     * Handles the click event for the delete button.
     * When successful it deletes an appointment from the MySQL database.
     *
     * @throws SQLException deleteAppointment() throws SQLException
     */
    public void onDeleteAppointmentClick() throws SQLException {
        Appointment appointment = appointmentTable.getSelectionModel().getSelectedItem();
        if(appointment == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select an appointment before attempting to delete.");
            alert.show();
            return;
        }

        AppointmentDAOImpl appointmentDAO = new AppointmentDAOImpl();

        if(appointmentDAO.deleteAppointment(appointment) != 0 ){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("The appointment of Type: " + appointment.getType() + " with ID: " + appointment.getID() + " was successfully canceled!");
            alert.show();
        }

        refreshAppointmentTable();
    }
    /**
     * Handles the click event for the all radio button.
     * Filters appointments in order by appointment id.
     *
     */
    public void onAllFilterRadioClick() {
        weeklyRadioButton.setSelected(false);
        monthlyRadioButton.setSelected(false);
        refreshAppointmentTable();
    }
    /**
     * Handles the click event for the weekly radio button.
     * Filters appointments in order by week number out of the year.
     *
     */
    public void onWeeklyFilterRadioClick() {
        allRadioButton.setSelected(false);
        monthlyRadioButton.setSelected(false);

        appointmentTable.getItems().clear();
        AppointmentDAOImpl appointmentDAO = new AppointmentDAOImpl();
        ArrayList<Appointment> appointments;

        try {
            appointments = appointmentDAO.getAllAppointments("YEAR(Start), WEEK(Start)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        appointmentObservableList.addAll(appointments);
        appointmentTable.setItems(appointmentObservableList);
    }
    /**
     * Handles the click event for the monthly radio button.
     * Filters appointments in order by month number out of the year.
     *
     */
    public void onMonthlyFilterRadioClick() {
        allRadioButton.setSelected(false);
        weeklyRadioButton.setSelected(false);

        appointmentTable.getItems().clear();
        AppointmentDAOImpl appointmentDAO = new AppointmentDAOImpl();
        ArrayList<Appointment> appointments;

        try {
            appointments = appointmentDAO.getAllAppointments("YEAR(Start), MONTH(Start)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        appointmentObservableList.addAll(appointments);
        appointmentTable.setItems(appointmentObservableList);
    }
    /**
     * Refreshes the tableview.
     *
     */
    private void refreshAppointmentTable(){
        appointmentTable.getItems().clear();
        AppointmentDAOImpl appointmentDAO = new AppointmentDAOImpl();
        ArrayList<Appointment> appointments;

        try {
            appointments = appointmentDAO.getAllAppointments();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        appointmentObservableList.addAll(appointments);
        appointmentTable.setItems(appointmentObservableList);
    }
}
