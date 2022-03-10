package ro.ase.acs.contracts;

import java.sql.Connection;
import java.sql.SQLException;

public interface SqlOperation {

    void manipulate(Connection connection) throws SQLException;
}
