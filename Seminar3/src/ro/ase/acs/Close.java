package ro.ase.acs;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import ro.ase.acs.contracts.Operation;

import java.sql.Connection;
import java.sql.SQLException;

public class Close implements Operation {
    @Override
    public void manipulate(Connection connection) throws SQLException {
        connection.close();
    }

    @Override
    public void manipulate(MongoClient mongoClient, MongoDatabase mongoDb, MongoCollection<Document> collection) {
        mongoClient.close();
    }
}
