package scrapper;

import model.internal.FlightResult;
import model.internal.FlightSearch;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class MailResultHandler {

    private static HashMap<FlightSearch, LocalDateTime> sentNotifications = new HashMap<>();

    public static void notify(FlightSearch search, List<FlightResult> result) {
        for(FlightResult flight : result){
            if(flight.getPrice() <= search.getAlertPrice()){
                if(sentNotifications.containsKey(search)){
                    if(sentNotifications.get(search).plusHours(3).isBefore(LocalDateTime.now())){
                        sendNotification(search, flight);
                        break;
                    }
                }
                else{
                    sendNotification(search, flight);
                    break;
                }
            }
        }
    }

    private static void sendNotification(FlightSearch search, FlightResult flight){
        sentNotifications.put(search, LocalDateTime.now());

        String subject = "Cheap flights!! " + search.getDescription();
        String text = flight.getPriceString() + " :: " + flight.getFlightQuery().printInfo() + " :: " + flight.getAirline();

        MailService.sendMail(subject, text);
    }
}
