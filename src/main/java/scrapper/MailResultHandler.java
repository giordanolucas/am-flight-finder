package scrapper;

import model.internal.ScrappedFlight;

import java.util.List;

public class MailResultHandler extends ResultHandler {

    public MailResultHandler(Double alertPrice) {
        super(alertPrice);
    }

    @Override
    public void addResult(List<ScrappedFlight> result) {
        super.addResult(result);

        for(ScrappedFlight flight : result){
            if(flight.getPrice() <= alertPrice){
                MailService.sendMail(flight.getFlightInfo().printInfo(), getFlightDataString(flight));
            }
        }
    }

    @Override
    public void printResults() {
        super.printResults();
    }
}
