package controllers;

import com.company.Country;
import com.company.Customer;
import com.company.FirstLevelDivision;
import dao.CountryDAOImpl;
import dao.CustomerDAOImpl;
import dao.FirstLevelDivisionDAOImpl;
import helper.CustomerHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
/**
 * Controller class for updateCustomer.fxml.
 *
 * @author Aidan Strawder
 */
public class UpdateCustomerController implements Initializable {

    public TextField idTextField;
    public TextField phoneTextField;
    public TextField postalTextField;
    public TextField addressTextField;
    public TextField nameTextField;
    public ComboBox<String> countryComboBox;
    public ComboBox<String> fldComboBox;

    public Button exitButton;
    public Button saveButton;
    private ObservableList<String> countriesObservableList = FXCollections.observableArrayList();
    private ObservableList<String> divisionsObservableList = FXCollections.observableArrayList();
    /**
     * The initialize function for the controller of the updateCustomer.fxml.
     * Allows the user to update customer information in the MySQL database.
     * This form uses two lambda expressions to control the country and division combo box events.
     *
     * @param url the url
     * @param resourceBundle the resourceBundle to be used
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CountryDAOImpl countryDAO = new CountryDAOImpl();
        FirstLevelDivisionDAOImpl firstLevelDivisionDAO = new FirstLevelDivisionDAOImpl();
        ArrayList<Country> countries;
        ArrayList<FirstLevelDivision> divisions;
        CustomerHolder holder = CustomerHolder.getInstance();
        Customer customer = holder.getCustomer();

        try {
            countries = countryDAO.getAllCountries();
            divisions = firstLevelDivisionDAO.getAllDivisions();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (Country country: countries) {
            countriesObservableList.add(country.getCountry());
        }

        for (FirstLevelDivision division: divisions) {
            divisionsObservableList.add(division.getDivision());
        }


        countryComboBox.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            try {
                divisionsObservableList = FXCollections.observableArrayList();
                divisionsObservableList.addAll(firstLevelDivisionDAO.getAllDivisionsOfCountry(t1));
                fldComboBox.setItems(divisionsObservableList);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        fldComboBox.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            try {
                if(fldComboBox.getValue() == null){
                    return;
                }
                countriesObservableList = FXCollections.observableArrayList();
                countryComboBox.setValue(countryDAO.getCountry(t1));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        countryComboBox.setItems(countriesObservableList);
        fldComboBox.setItems(divisionsObservableList);

        idTextField.setText(String.valueOf(customer.getID()));
        nameTextField.setText(customer.getName());
        addressTextField.setText(customer.getAddress());
        postalTextField.setText(customer.getPostalCode());
        phoneTextField.setText(customer.getPhone());
        countryComboBox.setValue(customer.getCountry());
        fldComboBox.setValue(customer.getDivision());
    }
    /**
     * Handles the click event for the save button.
     * When successful the new customer information is updated in the MySQL database.
     *
     * @throws SQLException getDivisionID() throws SQLException
     * @throws SQLException updateCustomer() throws SQLException
     */
    public void onSaveButtonClick() throws SQLException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        CustomerDAOImpl customerDAO = new CustomerDAOImpl();
        FirstLevelDivisionDAOImpl firstLevelDivisionDAO = new FirstLevelDivisionDAOImpl();

        int ID = Integer.parseInt(idTextField.getText());
        String name = nameTextField.getText();
        String address = addressTextField.getText();
        String postalCode = postalTextField.getText();
        String phone = phoneTextField.getText();
        Timestamp lastUpdate = Timestamp.valueOf(ZonedDateTime.now().format(dateTimeFormatter));
        String lastUpdateBy = LoginController.currentLoggedOnUser;
        int divisionID = firstLevelDivisionDAO.getDivisionID(fldComboBox.getValue());

        customerDAO.updateCustomer(ID, name, address, postalCode, phone, lastUpdate, lastUpdateBy, divisionID);
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }
    /**
     * Handles the click event for the clear button.
     * Clears all text fields and combo boxes.
     *
     */
    public void onClearButtonClick() {
        nameTextField.clear();
        addressTextField.clear();
        postalTextField.clear();
        phoneTextField.clear();
        countryComboBox.valueProperty().set(null);
        fldComboBox.valueProperty().set(null);
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
}
