package ro.ase.acs.sql;

import ro.ase.acs.contracts.SqlOperation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
        try {

            SqlOperation createTable = (SqlOperation) Class.forName("ro.ase.acs.sql.CreateOperation").getDeclaredConstructor().newInstance();
            SqlOperation insertData = (SqlOperation) Class.forName("ro.ase.acs.sql.InsertOperation").getDeclaredConstructor().newInstance();
            SqlOperation readData = (SqlOperation) Class.forName("ro.ase.acs.sql.ReadOperation").getDeclaredConstructor().newInstance();
            SqlOperation close = (SqlOperation) Class.forName("ro.ase.acs.sql.CloseOperation").getDeclaredConstructor().newInstance();

            OrchestratorSql orchestrator = new OrchestratorSql(createTable);
            orchestrator.execute();

            orchestrator = new OrchestratorSql(insertData);
            orchestrator.execute();

            orchestrator = new OrchestratorSql(readData);
            orchestrator.execute();

            orchestrator = new OrchestratorSql(close);
            orchestrator.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
