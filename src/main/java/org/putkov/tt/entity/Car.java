package org.putkov.tt.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.putkov.tt.entity.state.CarState;
import org.putkov.tt.entity.state.FinishedState;
import org.putkov.tt.entity.state.WaitingState;
import org.putkov.tt.exception.AuthoserviceException;

import java.util.concurrent.Callable;

public class Car implements Callable<String> {
    private static final Logger logger = LogManager.getLogger(Car.class);
    private CarState state;
    private final int id;
    private Box currentBox;

    public Car(int id) {
        this.id = id;
        this.state = new WaitingState();
    }

    public CarState getState() {
        return state;
    }

    public void setState(CarState state) {
        this.state = state;
    }

    public Box getCurrentBox() {
        return currentBox;
    }

    public int getId() {
        return id;
    }
    public void setCurrentBox(Box currentBox) {
        this.currentBox = currentBox;
    }
    @Override
    public String call() throws AuthoserviceException {
        try {
            logger.info("Car {} started lifecycle", id);
            while (!(state instanceof FinishedState)) {
                logger.info("Car {} in state {}", id, state.getClass().getSimpleName());
                state.handle(this);
            }
            logger.info("Car {} finished lifecycle", id);
            return "Car " + id + " finished process";
        }catch (Exception e) {
            logger.error("Error while processing car {}", id, e);
            throw new AuthoserviceException("Error in car " + id, e);
        }
    }

}
