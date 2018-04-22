package dal;

import model.internal.FlightTimes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class FlightTimesDal {
    public static void saveFlightTimes(FlightTimes times) throws SQLException {
        Connection connection = DatabaseConnectionService.getConnection();

        String sql = "INSERT INTO flightTime (idTimeCombination, departure, arrival, duration)" +
                "VALUES (?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, times.getIdTimeCombination());
        preparedStatement.setString(2, times.getDepartureDateTimeString());
        preparedStatement.setString(3, times.getArrivalDateTimeString());
        preparedStatement.setInt(4, times.getDurationInteger());

        preparedStatement.executeUpdate();

        times.setIdFlightTimes(DatabaseConnectionService.getLastInsertedId(preparedStatement));
    }
}
