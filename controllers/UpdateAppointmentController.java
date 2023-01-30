package controllers;

import com.company.Appointment;
import com.company.Contact;
import com.company.Customer;
import com.company.User;
import dao.*;
import helper.AppointmentHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Controller class for the updateAppointment.fxml.
 *
 * @author Aidan Strawder
 */
public class UpdateAppointmentController implements Initializable {

    public TextField idTextField;
    public TextField titleTextField;
    public TextArea descriptionTextArea;
    public TextField locationTextField;
    public TextField typeTextField;
    public TextField startTimeTextField;
    public TextField endTimeTextField;
    public ComboBox<String> contactComboBox;
    public DatePicker datePicker;
    public ComboBox<Integer> customerIDComboBox;
    public ComboBox<Integer> userIDComboBox;

    public Button exitButton;
    public Button saveButton;
    private final ObservableList<String> contactObservableList = FXCollections.observableArrayList();
    private final ObservableList<Integer> customerIDObservableList = FXCollections.observableArrayList();
    private final ObservableList<Integer> userIDObservableList = FXCollections.observableArrayList();
    /**
     * The initialize function for the controller of the updateAppointment.fxml.
     * Allows the user to update appointment information in the MySQL database.
     *
     * @param url the url
     * @param resourceBundle the resourceBundle to be used
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ContactDAOImpl contactDAO = new ContactDAOImpl();
        CustomerDAOImpl customerDAO = new CustomerDAOImpl();
        UserDAOImpl userDAO = new UserDAOImpl();
        ArrayList<Customer> customers;
        ArrayList<User> users;
        ArrayList<Contact> contacts;
        AppointmentHolder holder = AppointmentHolder.getInstance();
        Appointment appointment = holder.getAppointment();

        try {
            customers = customerDAO.getAllCustomers();
            users = userDAO.getAllUsers();
            contacts = contactDAO.getAllContacts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (Contact contact: contacts) {
            contactObservableList.add(contact.getName());
        }
        for (Customer customer: customers) {
            customerIDObservableList.add(customer.getID());
        }
        for (User user: users) {
            userIDObservableList.add(user.getUserID());
        }

        contactComboBox.setItems(contactObservableList);
        customerIDComboBox.setItems(customerIDObservableList);
        userIDComboBox.setItems(userIDObservableList);

        ZonedDateTime zonedStart = appointment.getStartDateTime().toInstant().atZone(ZoneId.of("America/New_York"));
        ZonedDateTime zonedEnd = appointment.getEndDateTime().toInstant().atZone(ZoneId.of("America/New_York"));
        String start = zonedStart.format(DateTimeFormatter.ofPattern("hh:mm a"));
        String end = zonedEnd.format(DateTimeFormatter.ofPattern("hh:mm a"));

        idTextField.setText(String.valueOf(appointment.getID()));
        titleTextField.setText(appointment.getTitle());
        descriptionTextArea.setText(appointment.getDescription());
        locationTextField.setText(appointment.getLocation());
        typeTextField.setText(appointment.getType());
        startTimeTextField.setText(start);
        endTimeTextField.setText(end);
        contactComboBox.setValue(appointment.getContact());
        datePicker.setValue(appointment.getStartDateTime().toLocalDateTime().toLocalDate());
        customerIDComboBox.setValue(appointment.getCustomerID());
        userIDComboBox.setValue(appointment.getUserID());
    }
    /**
     * Handles the click event for the save button.
     * When successful the appointment information is updated in the MySQL database.
     * Input validation is done using regex, if statements, the checkOverlappingTimes() and dateFormatter() functions.
     *
     * @throws SQLException addAppointment() throws SQLException
     * @throws SQLException getContactID() throws SQLException
     */
    public void onSaveButtonClick() throws SQLException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        AppointmentDAOImpl appointmentDAO = new AppointmentDAOImpl();
        ContactDAOImpl contactDAO = new ContactDAOImpl();
        Pattern pattern = Pattern.compile("((1[0-2]|0?[1-9]):([0-5][0-9]) ([AP][M]))");
        Matcher matchStart = pattern.matcher(startTimeTextField.getText());
        Matcher matchEnd = pattern.matcher(endTimeTextField.getText());
        boolean foundStart = matchStart.find();
        boolean foundEnd = matchEnd.find();

        if (foundStart && foundEnd){
            int ID = Integer.parseInt(idTextField.getText());
            String title = titleTextField.getText();
            String description = descriptionTextArea.getText();
            String location = locationTextField.getText();
            String type = typeTextField.getText();
            Timestamp startTime = dateFormatter(startTimeTextField.getText());
            Timestamp endTime = dateFormatter(endTimeTextField.getText());

            if(startTime == null || endTime == null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Please make sure the entered date/time is within office hours (8:00 AM - 10:00PM EST), not on a weekday and is a future date/time.");
                alert.show();
                return;
            }

            if(startTime.compareTo(endTime) > 0 || startTime.compareTo(endTime) == 0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Please make sure the entered start time is before the entered end time.");
                alert.show();
                return;
            }

            Timestamp lastUpdate = Timestamp.valueOf(ZonedDateTime.now().format(dateTimeFormatter));
            String lastUpdateBy = LoginController.currentLoggedOnUser;
            String contact = contactComboBox.getValue();
            int customerID = customerIDComboBox.getValue();
            int userID = userIDComboBox.getValue();
            int contactID = contactDAO.getContactID(contact);

            if(checkOverlappingTimes(ID, startTime, endTime)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("The current date/time overlaps with an already scheduled appointment. Please choose a different date/time.");
                alert.show();
            }
            else{
                appointmentDAO.updateAppointment(ID, title, description, location, type, startTime, endTime, lastUpdate, lastUpdateBy, customerID, userID, contactID);
                Stage stage = (Stage) saveButton.getScene().getWindow();
                stage.close();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Please make sure to enter the start and end times in the correct format (hh:mm a), e.g. 2:00 PM.");
            alert.show();
        }
    }
    /**
     * Handles the click event for the clear button.
     * Clears all text fields and combo boxes.
     *
     */
    public void onClearButtonClick() {
        idTextField.clear();
        titleTextField.clear();
        descriptionTextArea.clear();
        locationTextField.clear();
        typeTextField.clear();
        startTimeTextField.clear();
        endTimeTextField.clear();
        contactComboBox.valueProperty().set(null);
        datePicker.valueProperty().set(null);
        customerIDComboBox.valueProperty().set(null);
        userIDComboBox.valueProperty().set(null);
    }
    /**
     * Handles the click event for the exit button.
     * Closes the addCustomers.fxml.
     *
     */
    public void onExitButtonClick() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
    /**
     * Handles the formatting of the time using correct timezones and does basic input validation to check for correct time constraints.
     *
     * @param time the time to be formatted
     */
    private Timestamp dateFormatter(String time){
        String amOrPM = time.substring(time.length()-2);
        String[] array = time.split(" ", 2);

        time = array[0];
        time += ":00";
        if(time.length() == 7){
            time = "0" + time;
        }
        time += " " + amOrPM;

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        DateTimeFormatter dateTimeFormatter  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(datePicker.getValue().format(dateFormatter));
        LocalTime localTime = LocalTime.parse(time, timeFormatter);
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);

        ZonedDateTime currentZonedDateTime = ZonedDateTime.now(ZoneId.of("America/New_York"));
        ZonedDateTime zonedDateTimeEST = ZonedDateTime.of(localDateTime, ZoneId.of("America/New_York"));
        DayOfWeek day = zonedDateTimeEST.getDayOfWeek();
        int hour = zonedDateTimeEST.getHour();

        if(zonedDateTimeEST.isBefore(currentZonedDateTime) || day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY || hour < 8 || hour > 22){
            return null;
        }

        ZonedDateTime zonedDateTime = zonedDateTimeEST.toInstant().atZone(ZoneId.systemDefault());

        return Timestamp.valueOf(zonedDateTime.format(dateTimeFormatter));
    }
    /**
     * Checks for overlapping appointment times.
     *
     * @param start the appointment start time to be checked
     * @param end the appointment end time to be checked
     */
    private boolean checkOverlappingTimes(int appointmentID, Timestamp start, Timestamp end) {
        AppointmentDAOImpl appointmentDAO = new AppointmentDAOImpl();
        ArrayList<Appointment> appointments;

        try {
            appointments = appointmentDAO.getAllAppointments();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (Appointment app: appointments) {
            if(app.getID() == appointmentID){
                continue;
            }

            Timestamp startTimestamp = app.getStartDateTime();
            Timestamp endTimestamp = app.getEndDateTime();

            if(start.after(startTimestamp) && start.before(endTimestamp) ||
                    end.after(startTimestamp) && end.before(endTimestamp) ||
                    start.before(startTimestamp) && end.after(endTimestamp) ||
                    start.equals(startTimestamp) || end.equals(endTimestamp) ||
                    start.equals(endTimestamp) || end.equals(startTimestamp)){
                return true;
            }
        }
        return false;
    }
}
