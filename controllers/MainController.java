package controllers;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
/**
 * Controller class for the main.fxml.
 *
 * @author Aidan Strawder
 */
public class MainController implements Initializable {
    public String hoverStyle = "-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;";
    public String buttonStyle = "-fx-background-color: #008080;";
    public BorderPane mainBorderPane;
    public Button customerButton;
    public Button appointmentButton;
    public Button reportsButton;
    public Button signOutButton;

    public Button exitButton;
    /**
     * The initialize function for the controller of the main.fxml.
     * The initialize function utilizes multiple lambda expressions to create a hover animation for the buttons in the main form.
     * The lambda expressions greatly minimizes the amount of code to create this hover animation.
     *
     * @param url the url
     * @param resourceBundle the resourceBundle to be used
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            mainBorderPane.setCenter(FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/customers.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        customerButton.setOnMouseEntered(e -> customerButton.setStyle(hoverStyle));
        customerButton.setOnMouseExited(e -> customerButton.setStyle(buttonStyle));
        appointmentButton.setOnMouseEntered(e -> appointmentButton.setStyle(hoverStyle));
        appointmentButton.setOnMouseExited(e -> appointmentButton.setStyle(buttonStyle));
        reportsButton.setOnMouseEntered(e -> reportsButton.setStyle(hoverStyle));
        reportsButton.setOnMouseExited(e -> reportsButton.setStyle(buttonStyle));
        signOutButton.setOnMouseEntered(e -> signOutButton.setStyle(hoverStyle));
        signOutButton.setOnMouseExited(e -> signOutButton.setStyle(buttonStyle));
        exitButton.setOnMouseEntered(e -> exitButton.setStyle(hoverStyle));
        exitButton.setOnMouseExited(e -> exitButton.setStyle(buttonStyle));
    }
    /**
     * Handles the click event for the customers button.
     * Loads the customers.fxml when clicked.
     *
     * @throws IOException load() throws IOException
     */
    public void onCustomersButtonClick() throws IOException {
        mainBorderPane.getChildren().remove(mainBorderPane.getCenter());
        mainBorderPane.setCenter(FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/customers.fxml"))));
    }
    /**
     * Handles the click event for the appointments button.
     * Loads the appointments.fxml when clicked.
     *
     * @throws IOException load() throws IOException
     */
    public void onAppointmentsButtonClick() throws IOException {
        mainBorderPane.getChildren().remove(mainBorderPane.getCenter());
        mainBorderPane.setCenter(FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/appointments.fxml"))));
    }
    /**
     * Handles the click event for the reports button.
     * Loads the reports.fxml when clicked.
     *
     * @throws IOException load() throws IOException
     */
    public void onReportsButtonClick() throws IOException {
        mainBorderPane.getChildren().remove(mainBorderPane.getCenter());
        mainBorderPane.setCenter(FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/reports.fxml"))));
    }
    /**
     * Handles the click event for the sign-out button.
     * Loads the login.fxml when clicked.
     *
     * @throws IOException load() throws IOException
     */
    public void onSignOutButtonClick() throws IOException {
        LoginController.currentLoggedOnUser = "";
        Stage stage = (Stage)signOutButton.getScene().getWindow();
        stage.close();

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(("resources/login.fxml"))));
        Stage mainStage = new Stage();
        mainStage.setTitle("Login");
        mainStage.setScene(new Scene(root));
        mainStage.show();
    }
    /**
     * Handles the click event for the exit button.
     * Closes the application.
     *
     */
    public void onExitButtonClick() {
        Platform.exit();
    }
}
