package scrapper;

import dal.FlightQueryDal;
import dal.FlightResultDal;
import model.internal.FlightQuery;
import model.internal.FlightResult;

import java.util.List;

public class DatabaseResultHandler extends ResultHandler{

    public void saveQuery(FlightQuery query){
        try{
            FlightQueryDal.saveFlightQuery(query);
        }
        catch (Exception e){
            System.err.println("Error saving flight query to database");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void addResult(List<FlightResult> result) {
        super.addResult(result);

        for(FlightResult flightResult : result){
            try{
                FlightResultDal.saveFlightResult(flightResult);
            }
            catch (Exception e){
                System.err.println("Error saving flight result to database");
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
