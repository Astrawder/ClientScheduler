package dao;

import com.company.Country;
import helper.JDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
/**
 * The implementation class for the CountryDAO interface.
 * This class uses SQL to interact with the MySQL database.
 *
 * @author Aidan Strawder
 */
public class CountryDAOImpl implements CountryDAO {
    private final ArrayList<Country> countries;
    /**
     * Country DAO implementation class constructor.
     *
     */
    public CountryDAOImpl(){
        countries = new ArrayList<>();
    }
    /**
     * Retrieves all countries from the MySQL database.
     *
     * @return the countries
     * @throws SQLException createStatement() throws SQLException
     * @throws SQLException executeQuery() throws SQLException
     * @throws SQLException next() throws SQLException
     * @throws SQLException  getInt() throws SQLException
     * @throws SQLException  getString() throws SQLException
     */
    @Override
    public ArrayList<Country> getAllCountries() throws SQLException {
        JDBC.openConnection();
        Statement statement = JDBC.connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM countries;");

        while (resultSet.next()){
            int ID = resultSet.getInt(1);
            String country = resultSet.getString(2);
            Timestamp createDate = resultSet.getTimestamp(3);
            String createdBy = resultSet.getString(4);
            Timestamp lastUpdate = resultSet.getTimestamp(5);
            String lastUpdateBy = resultSet.getString(6);
            Country newCountry = new Country(ID, country, createDate, createdBy, lastUpdate, lastUpdateBy);
            countries.add(newCountry);
        }

        JDBC.closeConnection();
        return countries;
    }
    /**
     * Retrieves the country of the inputted division from the MySQL database.
     *
     * @return the country
     * @throws SQLException createStatement() throws SQLException
     * @throws SQLException executeQuery() throws SQLException
     * @throws SQLException next() throws SQLException
     * @throws SQLException  getString() throws SQLException
     * @param division the division
     */
    public String getCountry(String division) throws SQLException {
        JDBC.openConnection();
        Statement statement = JDBC.connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT Country FROM countries\n" +
                "INNER JOIN first_level_divisions ON countries.Country_ID = first_level_divisions.Country_ID\n" +
                "WHERE first_level_divisions.Division = "  + "\"" + division + "\"" + ";");
        resultSet.next();
        String country = resultSet.getString(1);
        JDBC.closeConnection();
        return country;
    }
}
