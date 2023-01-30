package dao;

import com.company.Country;

import java.sql.SQLException;
import java.util.List;
/**
 * Interface for country Data Access Object Pattern (DAO) implementation.
 *
 * @author Aidan Strawder
 */
public interface CountryDAO {
    public List<Country> getAllCountries() throws SQLException;
}
