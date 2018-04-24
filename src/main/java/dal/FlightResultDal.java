package dal;

import com.google.gson.Gson;
import model.internal.FlightResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class FlightResultDal {
    private static Gson gson = new Gson();

    public static void saveFlightResult(FlightResult flightResult) throws SQLException {
        try (Connection connection = DatabaseConnectionService.getConnection()) {
            String sql = "INSERT INTO flightResult (idFlightQuery, price, airline, timeCombinationsJson)" +
                    "VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, flightResult.getFlightQuery().getIdFlightQuery());
            preparedStatement.setDouble(2, flightResult.getPrice());
            preparedStatement.setString(3, flightResult.getAirline());
            preparedStatement.setString(4, gson.toJson(flightResult.getTimeCombinations()));

            preparedStatement.executeUpdate();
        }
    }

    public static void saveFlightResultBatch(List<FlightResult> flightResultBatch) throws SQLException {
        try (Connection connection = DatabaseConnectionService.getConnection()) {
            String sql = "INSERT INTO flightResult (idFlightQuery, price, airline, timeCombinationsJson)" +
                    "VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            for (FlightResult flightResult : flightResultBatch) {
                preparedStatement.setInt(1, flightResult.getFlightQuery().getIdFlightQuery());
                preparedStatement.setDouble(2, flightResult.getPrice());
                preparedStatement.setString(3, flightResult.getAirline());
                preparedStatement.setString(4, gson.toJson(flightResult.getTimeCombinations()));

                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
        }
    }
}
