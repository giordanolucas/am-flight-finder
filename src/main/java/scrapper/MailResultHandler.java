package scrapper;

import model.internal.FlightResult;

import java.util.LinkedList;
import java.util.List;

public class MailResultHandler extends ResultHandler {

    List<FlightResult> notificationFlights = new LinkedList<>();

    public MailResultHandler(Double alertPrice) {
        super(alertPrice);
    }

    @Override
    public void addResult(List<FlightResult> result) {
        super.addResult(result);

        for(FlightResult flight : result){
            if(flight.getPrice() <= alertPrice){
                notificationFlights.add(flight);
            }
        }
    }

    @Override
    public void printResults() {
        StringBuilder mailMessage = new StringBuilder();

        for(FlightResult flight : notificationFlights){
            mailMessage.append(getFlightDataString(flight)).append(System.lineSeparator());
        }

        MailService.sendMail("Flight notifications!", mailMessage.toString());
    }
}
