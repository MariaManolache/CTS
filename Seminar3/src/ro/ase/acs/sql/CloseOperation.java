package ro.ase.acs.sql;

import ro.ase.acs.contracts.SqlOperation;

import java.sql.Connection;
import java.sql.SQLException;

public class CloseOperation implements SqlOperation {
    @Override
    public void manipulate(Connection connection) throws SQLException {
        connection.close();
    }
}
