package controllers;

import com.company.Customer;
import dao.CustomerDAOImpl;
import helper.CustomerHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
/**
 * Controller class for the customers.fxml.
 *
 * @author Aidan Strawder
 */
public class CustomerController implements Initializable {
    public TableView<Customer> customerTable;
    public TableColumn<Customer, Integer> idColumn;
    public TableColumn<Customer, String> nameColumn;
    public TableColumn<Customer, String> addressColumn;
    public TableColumn<Customer, String> postalCodeColumn;
    public TableColumn<Customer, String> phoneColumn;
    public TableColumn<Customer, String> countryColumn;
    public TableColumn<Customer, String> divisionColumn;
    private final ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();
    /**
     * The initialize function for the controller of the customers.fxml.
     * Contains the tableview that displays customer information.
     *
     * @param url the url
     * @param resourceBundle the resourceBundle to be used
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CustomerDAOImpl customerDAO = new CustomerDAOImpl();
        ArrayList<Customer> customers;

        try {
            customers = customerDAO.getAllCustomers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        divisionColumn.setCellValueFactory(new PropertyValueFactory<>("division"));

        customerObservableList.addAll(customers);
        customerTable.setItems(customerObservableList);
    }
    /**
     * Handles the click event for the add button.
     * Loads the addCustomer.fxml when clicked.
     *
     * @throws IOException load() throws IOException
     */
    public void onAddCustomerButtonClick() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/addCustomer.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
        stage.setOnHidden(windowEvent -> refreshCustomerTable());
    }
    /**
     * Handles the click event for the updateCustomers button.
     * Loads the updateCustomers.fxml when clicked.
     *
     * @throws IOException load() throws IOException
     */
    public void onUpdateCustomerButtonClick() throws IOException {
        Customer customer = customerTable.getSelectionModel().getSelectedItem();

        if(customer == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a customer record before attempting to update.");
            alert.show();
            return;
        }

        CustomerHolder holder = CustomerHolder.getInstance();
        holder.setCustomer(customer);

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/updateCustomer.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
        stage.setOnHidden(windowEvent -> refreshCustomerTable());
    }
    /**
     * Handles the click event for the delete button.
     * When successful it deletes a customer from the MySQL database.
     *
     * @throws SQLException deleteCustomer throws SQLException
     */
    public void onDeleteCustomerButtonClick() throws SQLException {
        Customer customer = customerTable.getSelectionModel().getSelectedItem();
        if(customer == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a customer record before attempting to delete.");
            alert.show();
            return;
        }

        CustomerDAOImpl customerDAO = new CustomerDAOImpl();

        if(customerDAO.deleteCustomer(customer) != 0 ){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("The customer record was successfully deleted!");
            alert.show();
        }

        refreshCustomerTable();
    }
    /**
     * Refreshes the tableview.
     *
     */
    private void refreshCustomerTable(){
        customerTable.getItems().clear();
        CustomerDAOImpl customerDAO = new CustomerDAOImpl();
        ArrayList<Customer> customers;

        try {
            customers = customerDAO.getAllCustomers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        customerObservableList.addAll(customers);
        customerTable.setItems(customerObservableList);
    }
}
