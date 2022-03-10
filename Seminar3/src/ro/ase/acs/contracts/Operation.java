package ro.ase.acs.contracts;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.sql.Connection;
import java.sql.SQLException;

public interface Operation {

    void manipulate(Connection connection) throws SQLException;
    void manipulate(MongoClient mongoClient, MongoDatabase mongoDb, MongoCollection<Document> collection);
}
