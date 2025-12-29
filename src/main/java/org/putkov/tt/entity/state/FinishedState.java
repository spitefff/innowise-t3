package org.putkov.tt.entity.state;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.putkov.tt.entity.Car;

public class FinishedState implements CarState{
    private static final Logger logger = LogManager.getLogger(FinishedState.class);
    @Override
    public void handle(Car car) {
        logger.info("Car {} has been repaired and is ready to leave.", car.getId());

    }
}
