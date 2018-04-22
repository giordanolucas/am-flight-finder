package dal;

import model.internal.FlightResult;
import model.internal.FlightTimes;
import model.internal.TimeCombination;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TimeCombinationDal {
    public static void saveTimeCombination(TimeCombination timeCombination) throws SQLException {
        Connection connection = DatabaseConnectionService.getConnection();

        String sql = "INSERT INTO timeCombination (idFlightResult)" +
                "VALUES (?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, timeCombination.getIdFlightResult());

        preparedStatement.executeUpdate();

        timeCombination.setIdTimeCombination(DatabaseConnectionService.getLastInsertedId(preparedStatement));

        for(FlightTimes times : timeCombination.getFlightTimes()){
            times.setIdTimeCombination(timeCombination.getIdTimeCombination());
            FlightTimesDal.saveFlightTimes(times);
        }
    }
}
