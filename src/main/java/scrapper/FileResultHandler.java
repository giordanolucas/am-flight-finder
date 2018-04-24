package scrapper;

import model.internal.FlightResult;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileResultHandler extends ResultHandler {

    private String filename;
    private List<FlightResult> allResults = new ArrayList<>();

    public FileResultHandler(String filename) {
        super();
        this.filename = filename;
    }

    public FileResultHandler(String filename, Double alertPrice) {
        super(alertPrice);
        this.filename = filename;
    }

    public void printResults() {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "utf-8"))) {
            allResults.stream()
                    .filter(Objects::nonNull)
                    .sorted((x, y) -> (x.getPrice().equals(y.getPrice())) ? 0 : (x.getPrice() > y.getPrice() ? 1 : -1))
                    .map(FileResultHandler::getFlightDataString)
                    .forEach(x -> {
                        try {
                            writer.write(x);
                            writer.write(System.lineSeparator());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
