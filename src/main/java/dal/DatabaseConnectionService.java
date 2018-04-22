package dal;

import scrapper.ProgramProperties;

import java.sql.*;
import java.util.concurrent.Semaphore;

public class DatabaseConnectionService {
    private static Connection connection;
    private static Semaphore connectionSemaphore = new Semaphore(1, true);

    private static Connection getNewConnection(){
        Connection newConnection;
        try {
            newConnection = DriverManager.getConnection("jdbc:mysql://" + ProgramProperties.getDatabaseHostPort() + "/" + ProgramProperties.getDatabaseName(),ProgramProperties.getDatabaseUsername(),ProgramProperties.getDatabasePassword());

            if(!newConnection.isValid(15)){
                throw new RuntimeException("Connection to database is not valid");
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not connect to database", e);
        }

        return newConnection;
    }

    protected static Connection getConnection(){
        try{
            connectionSemaphore.acquire();

            if(connection != null){
                if(connection.isValid(10)){
                    return connection;
                }
                else if (!connection.isClosed()){
                    connection.close();
                }
            }

            connection = getNewConnection();
            return connection;
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        finally {
            connectionSemaphore.release();
        }

        return null;
    }

    public static Integer getLastInsertedId(PreparedStatement stmt) throws SQLException {
        Integer generatedId = 0;

        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()){
            generatedId = rs.getInt(1);
        }

        return generatedId;
    }
}
