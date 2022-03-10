package ro.ase.acs.sql;

import ro.ase.acs.contracts.SqlOperation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OrchestratorSql {

    private SqlOperation operation;

    public OrchestratorSql(SqlOperation sqlOperation) {
        this.operation = sqlOperation;
    }

    public void execute() throws SQLException, ClassNotFoundException {

        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
        connection.setAutoCommit(false);

        operation.manipulate(connection);
    }
}
