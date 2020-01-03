package nl.alirezaa.dbConnection;

import nl.alirezaa.webshopBackendApplication;
import io.dropwizard.db.DataSourceFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class dbConnection {
    private static dbConnection instance;

    //Singleton omdat er maar 1x een database conn gemaakt moet worden.
    public static dbConnection getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null){
            instance = new dbConnection();
        }
        return instance;
    }

    private Connection connection;

    //constructor
    public dbConnection() throws SQLException, ClassNotFoundException {
        InitConnection();
    }

    // Hier wordt de connectie aangemaakt.
    public void InitConnection() throws  SQLException, ClassNotFoundException {
        DataSourceFactory database = webshopBackendApplication.getConfiguration().getDataSourceFactory();

        Class.forName(database.getDriverClass());

        // Hier wordt check uitgevoerd voor de connectie.
        connection = DriverManager.getConnection(database.getUrl(), database.getUser(), database.getPassword());
    }

    // Voor het uitvoeren van query.
    public PreparedStatement createPreparedStatement(String query) throws SQLException, ClassNotFoundException {
        return connection.prepareStatement(query);
    }

}
