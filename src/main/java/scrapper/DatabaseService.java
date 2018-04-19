package scrapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DatabaseService {
    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://" + ProgramProperties.getDatabaseHostPort() + "/" + ProgramProperties.getDatabaseName(),ProgramProperties.getDatabaseUsername(),ProgramProperties.getDatabasePassword());

            if(!connection.isValid(15)){
                throw new RuntimeException("Connection to database is not valid");
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not connect to database", e);
        }

        return connection;
    }
}
