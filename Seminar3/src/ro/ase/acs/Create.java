package ro.ase.acs;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import ro.ase.acs.contracts.Operation;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Create implements Operation {
    @Override
    public void manipulate(Connection connection) throws SQLException {
        String sqlDrop = "DROP TABLE IF EXISTS employees";
        String sqlCreate = "CREATE TABLE employees(id INTEGER PRIMARY KEY,"
                + "name TEXT, address TEXT, salary REAL)";

        Statement statement = connection.createStatement();
        statement.executeUpdate(sqlDrop);
        statement.executeUpdate(sqlCreate);
        statement.close();
        connection.commit();
    }

    @Override
    public void manipulate(MongoClient mongoClient, MongoDatabase mongoDb, MongoCollection<Document> collection) {
        if(collection != null) {
            collection.drop();
        }

        mongoDb.createCollection("employees");
    }
}
