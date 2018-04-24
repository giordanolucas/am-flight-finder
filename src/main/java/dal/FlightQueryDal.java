package dal;

import model.internal.FlightQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class FlightQueryDal {
    public static void saveFlightQuery(FlightQuery query) throws SQLException {
        try (Connection connection = DatabaseConnectionService.getConnection()) {
            String sql = "INSERT INTO flightQuery (origin, destination, dateFrom, dateTo, gds)" +
                    "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, query.getOrigin());
            preparedStatement.setString(2, query.getDestination());
            preparedStatement.setDate(3, java.sql.Date.valueOf(query.getDateFrom()));
            preparedStatement.setDate(4, java.sql.Date.valueOf(query.getDateTo()));
            preparedStatement.setString(5, query.getGDS() != null ? query.getGDS().getCode() : "default");

            preparedStatement.executeUpdate();

            query.setIdFlightQuery(DatabaseConnectionService.getLastInsertedId(preparedStatement));
        }
    }
}
