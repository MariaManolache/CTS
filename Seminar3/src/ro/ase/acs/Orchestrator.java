package ro.ase.acs;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import ro.ase.acs.contracts.Operation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Orchestrator {

    private Operation operation;

    public Orchestrator(Operation operation) {
        this.operation = operation;
    }

    public void execute(Operation operation) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
        connection.setAutoCommit(false);

        operation.manipulate(connection);

        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase mongoDb = mongoClient.getDatabase("test");

        MongoCollection<Document> collection = mongoDb.getCollection("employees");

        operation.manipulate(mongoClient, mongoDb, collection);
    }
}
