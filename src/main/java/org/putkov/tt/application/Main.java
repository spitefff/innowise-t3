package org.putkov.tt.application;

import org.putkov.tt.entity.AuthoserviceStation;
import org.putkov.tt.entity.Car;
import org.putkov.tt.exception.AuthoserviceException;
import org.putkov.tt.parser.ServiceConfigParser;
import org.putkov.tt.parser.impl.ServiceConfigParserImpl;
import org.putkov.tt.reader.ServiceConfigReader;
import org.putkov.tt.reader.impl.ServiceConfigReaderImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            
            ServiceConfigReader reader = new ServiceConfigReaderImpl();
            ServiceConfigParser parser = new ServiceConfigParserImpl();


            List<String> lines = reader.read("config.txt");


            int[] values = parser.parse(lines);
            int carsCount = values[0];
            int boxesCount = values[1];
            int partsCount = values[2];

            logger.info("Config loaded: Cars=" + carsCount +
                    ", Boxes=" + boxesCount +
                    ", Parts=" + partsCount);


            AuthoserviceStation station = AuthoserviceStation.getInstance();
            station.init(boxesCount, partsCount);

            ExecutorService executor = Executors.newFixedThreadPool(carsCount);
            List<Future<String>> results = new ArrayList<>();

            for (int i = 1; i <= carsCount; i++) {
                Car car = new Car(i);
                results.add(executor.submit(car));
            }

            for (Future<String> result : results) {
                try {
                    logger.info(result.get());
                } catch (ExecutionException e) {
                    logger.log(Level.SEVERE, "Error executing car task", e);
                }
            }

            executor.shutdown();
        } catch (AuthoserviceException e) {
            logger.log(Level.SEVERE, "Config error: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unexpected error", e);
        }
    }
}
