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

    private Operation createOperation;
    private Operation insertOperation;
    private Operation readOperation;
    private Operation closeOperation;


    public Orchestrator(Operation createOperation, Operation insertOperation, Operation readOperation, Operation closeOperation) {
        this.createOperation = createOperation;
        this.insertOperation = insertOperation;
        this.readOperation = readOperation;
        this.closeOperation = closeOperation;
    }

    public void executeSql(Operation createOperation, Operation insertOperation, Operation readOperation, Operation closeOperation) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
        connection.setAutoCommit(false);

        createOperation.manipulate(connection);
        insertOperation.manipulate(connection);
        readOperation.manipulate(connection);
        closeOperation.manipulate(connection);
    }

    public void executeNoSql(Operation createOperation, Operation insertOperation, Operation readOperation, Operation closeOperation) {

        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase mongoDb = mongoClient.getDatabase("test");

        MongoCollection<Document> collection = mongoDb.getCollection("employees");

        createOperation.manipulate(mongoClient, mongoDb, collection);
        insertOperation.manipulate(mongoClient, mongoDb, collection);
        readOperation.manipulate(mongoClient, mongoDb, collection);
        closeOperation.manipulate(mongoClient, mongoDb, collection);
    }
}
