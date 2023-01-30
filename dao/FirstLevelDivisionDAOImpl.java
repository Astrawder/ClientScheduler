package dao;

import com.company.FirstLevelDivision;
import helper.JDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
/**
 * The implementation class for the FirstLevelDivisionDAO interface.
 * This class uses SQL to interact with the MySQL database.
 *
 * @author Aidan Strawder
 */
public class FirstLevelDivisionDAOImpl implements FirstLevelDivisionDAO {
    private final ArrayList<FirstLevelDivision> divisions;
    /**
     * FirstLevelDivision DAO implementation class constructor.
     *
     */
    public FirstLevelDivisionDAOImpl(){
        divisions = new ArrayList<>();
    }
    /**
     * Retrieves all divisions from the MySQL database.
     *
     * @return the divisions
     * @throws SQLException createStatement() throws SQLException
     * @throws SQLException executeQuery() throws SQLException
     * @throws SQLException next() throws SQLException
     * @throws SQLException  getInt() throws SQLException
     * @throws SQLException  getString() throws SQLException
     */
    @Override
    public ArrayList<FirstLevelDivision> getAllDivisions() throws SQLException {
        JDBC.openConnection();
        Statement statement = JDBC.connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM first_level_divisions;");

        while (resultSet.next()){
            int ID = resultSet.getInt(1);
            String division = resultSet.getString(2);
            Timestamp createDate = resultSet.getTimestamp(3);
            String createdBy = resultSet.getString(4);
            Timestamp lastUpdate = resultSet.getTimestamp(5);
            String lastUpdateBy = resultSet.getString(6);
            int countryID = resultSet.getInt(7);
            FirstLevelDivision firstLevelDivision = new FirstLevelDivision(ID, division, createDate, createdBy, lastUpdate, lastUpdateBy, countryID);
            divisions.add(firstLevelDivision);
        }
        JDBC.closeConnection();
        return divisions;
    }
    /**
     * Retrieves divisions by country from the MySQL database.
     *
     * @return the divisionsOfCountry
     * @throws SQLException createStatement() throws SQLException
     * @throws SQLException executeQuery() throws SQLException
     * @throws SQLException next() throws SQLException
     * @throws SQLException  getString() throws SQLException
     * @param country the country
     */
    public ArrayList<String> getAllDivisionsOfCountry(String country) throws SQLException {
        ArrayList<String> divisionsOfCountry = new ArrayList<>();
        JDBC.openConnection();
        Statement statement = JDBC.connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT Division FROM first_level_divisions\n" +
                "INNER JOIN countries ON first_level_divisions.Country_ID = countries.Country_ID\n" +
                "WHERE Country = " + "\"" + country + "\"" + ";");

        while (resultSet.next()){
            String division = resultSet.getString(1);
            divisionsOfCountry.add(division);
        }
        JDBC.closeConnection();
        return divisionsOfCountry;
    }
    /**
     * Retrieves a division id based on the inputted division from the MySQL database.
     *
     * @throws SQLException createStatement() throws SQLException
     * @throws SQLException executeQuery() throws SQLException
     * @throws SQLException next() throws SQLException
     * @throws SQLException  getInt() throws SQLException
     * @param division the division
     */
    public int getDivisionID(String division) throws SQLException {
        JDBC.openConnection();
        Statement statement = JDBC.connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT Division_ID FROM first_level_divisions WHERE Division = "  + "\"" + division + "\"" + ";");
        resultSet.next();
        int ID = resultSet.getInt(1);
        JDBC.closeConnection();
        return ID;
    }
}
