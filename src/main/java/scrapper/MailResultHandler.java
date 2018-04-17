package scrapper;

import model.internal.FlightInfo;
import model.internal.ScrappedFlight;

import java.util.List;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
