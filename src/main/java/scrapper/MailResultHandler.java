package scrapper;

import model.internal.ScrappedFlight;

import java.util.LinkedList;
import java.util.List;

public class MailResultHandler extends ResultHandler {

    List<ScrappedFlight> notificationFlights = new LinkedList<>();

    public MailResultHandler(Double alertPrice) {
        super(alertPrice);
    }

    @Override
    public void addResult(List<ScrappedFlight> result) {
        super.addResult(result);

        for(ScrappedFlight flight : result){
            if(flight.getPrice() <= alertPrice){
                notificationFlights.add(flight);
            }
        }
    }

    @Override
    public void printResults() {
        StringBuilder mailMessage = new StringBuilder();

        for(ScrappedFlight flight : notificationFlights){
            mailMessage.append(getFlightDataString(flight)).append(System.lineSeparator());
        }

        MailService.sendMail("Flight notifications!", mailMessage.toString());
    }
}
