package dal;

import org.apache.commons.dbcp2.BasicDataSource;
import scrapper.ProgramProperties;

import java.sql.*;
import java.util.concurrent.Semaphore;

public class DatabaseConnectionService {
    private static final BasicDataSource dataSource = new BasicDataSource();

    static {
        dataSource.setUrl("jdbc:mysql://" + ProgramProperties.getDatabaseHostPort() + "/" + ProgramProperties.getDatabaseName());
        dataSource.setUsername(ProgramProperties.getDatabaseUsername());
        dataSource.setPassword(ProgramProperties.getDatabasePassword());
    }

    public static Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
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
