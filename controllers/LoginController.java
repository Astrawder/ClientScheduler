package controllers;

import com.company.Appointment;
import dao.AppointmentDAOImpl;
import dao.UserDAOImpl;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
/**
 * Controller class for the login.fxml.
 *
 * @author Aidan Strawder
 */
public class LoginController implements Initializable {
    public TextField usernameField;
    public PasswordField passwordField;
    public Button loginButton;
    public Label locationLabel;
    public Label passwordLabel;
    public Label userNameLabel;
    public String errorMessage;
    public static String currentLoggedOnUser;

    /**
     * The initialize function for the controller of the login.fxml.
     *
     * @param url the url
     * @param resourceBundle the resourceBundle to be used
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resourceBundle = ResourceBundle.getBundle("language");
        userNameLabel.setText(resourceBundle.getString("userNameLabel"));
        passwordLabel.setText(resourceBundle.getString("passwordLabel"));
        loginButton.setText(resourceBundle.getString("loginButton"));
        locationLabel.setText(resourceBundle.getString("locationLabel") + ZoneId.systemDefault());
        errorMessage = resourceBundle.getString("errorMessage");
    }
    /**
     * Handles the click event for the login button.
     * Checks credentials and logs the activity of the login attempts.
     * When the login attempt is successful, the user is notified of upcoming appointments.
     *
     * @throws IOException load() throws IOException
     * @throws SQLException checkCredentials() throws SQLException
     */
    public void onLoginButtonClick() throws IOException, SQLException {
        UserDAOImpl userDAO = new UserDAOImpl();
        String username = usernameField.getText();
        String password = passwordField.getText();

        if(userDAO.checkCredentials(username, password)){
            loginActivity(username, password, "Successful");
            currentLoggedOnUser = username;

            Stage stage = (Stage)loginButton.getScene().getWindow();
            stage.close();

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(("resources/main.fxml"))));
            Stage mainStage = new Stage();
            mainStage.setTitle("Scheduler");
            mainStage.setScene(new Scene(root));

            mainStage.setOnShown(windowEvent -> {
                AppointmentDAOImpl appointmentDAO = new AppointmentDAOImpl();
                ArrayList<Appointment> appointments;

                try {
                    appointments = appointmentDAO.getAllAppointments();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                for (Appointment appointment: appointments) {
                    ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.systemDefault());
                    ZonedDateTime nextZonedDateTime = ZonedDateTime.of(appointment.getStartDateTime().toLocalDateTime(), ZoneId.systemDefault());
                    ChronoUnit unit = ChronoUnit.MINUTES;

                    if(!(unit.between(zonedDateTime, nextZonedDateTime) < 0) && unit.between(zonedDateTime, nextZonedDateTime) <= 15) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Upcoming appointment!\nAppointment ID: " + appointment.getID() + "\nDate and Time: " + appointment.getStartLocalDateTime());
                        alert.show();
                        return;
                    }
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("No current upcoming appointments!");
                alert.show();
            });

            mainStage.show();
        }
        else{
            usernameField.clear();
            passwordField.clear();
            ResourceBundle resourceBundle = ResourceBundle.getBundle("language");
            loginActivity(username, password, "Unsuccessful");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(resourceBundle.getString("errorMessage"));
            alert.show();
        }
    }
    /**
     * Logs all login attempts and writes logs in login_activity.txt.
     *
     * @param username the username to log
     * @param password the password to log
     * @param status the status to log
     */
    private void loginActivity(String username, String password, String status) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            FileWriter fileWriter = new FileWriter("login_activity.txt", true);
            fileWriter.write("Attempted login: " + "UTC Date/Time: " + ZonedDateTime.now(ZoneOffset.UTC).format(dateTimeFormatter) + ", Username: " + username + ", Password: " +
                    password + ", Login Status: " + status + "\n");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
