package org.putkov.tt.state;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.putkov.tt.entity.AuthoserviceStation;
import org.putkov.tt.entity.Box;
import org.putkov.tt.entity.Car;
import org.putkov.tt.entity.Storage;

import java.util.concurrent.TimeUnit;

public class RepairingState implements CarState{
    private static final Logger logger = LogManager.getLogger(RepairingState.class);
    @Override
    public void handle(Car car) {
        logger.info("Car {} is being repaired...", car.getId());
        AuthoserviceStation station = AuthoserviceStation.getInstance();
        Storage storage = station.getStorage();
        Box box = car.getCurrentBox();

        try {
            storage.takeParts(1);
            logger.info("Car {} took parts from storage", car.getId());

            logger.info("Car {} is being repaired in box {}", car.getId(), box.getId());
            TimeUnit.SECONDS.sleep(2);

            box.getLock().unlock();
            logger.info("Car {} finished repair and released box {}", car.getId(), box.getId());
            car.setState(new FinishedState());
        } catch (Exception e) {
            logger.error("Error while repairing car {}", car.getId(), e);
            car.setState(new FinishedState());
        }

    }
}
