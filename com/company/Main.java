package com.company;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;

/**
 * The main java file of the application.
 *
 * @author Aidan Strawder
 */

public class Main extends Application {
    /**
     * The login form is displayed to the user when the program runs.
     *
     * @param stage the first stage displayed
     * @throws Exception load() throws IOException
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/login.fxml")));
        stage.setScene(new Scene(root));
        stage.setTitle("Login");
        stage.show();
    }
    /**
     * Main function and where the application is launched.
     *
     * @param args takes args
     */
    public static void main(String[] args) {launch(args);}
}