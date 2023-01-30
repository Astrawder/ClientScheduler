package dao;

import com.company.FirstLevelDivision;

import java.sql.SQLException;
import java.util.List;
/**
 * Interface for first level division Data Access Object Pattern (DAO) implementation.
 *
 * @author Aidan Strawder
 */
public interface FirstLevelDivisionDAO {
    public List<FirstLevelDivision> getAllDivisions() throws SQLException;
}
