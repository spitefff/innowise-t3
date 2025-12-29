package org.putkov.tt.entity.state;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.putkov.tt.entity.AuthoserviceStation;
import org.putkov.tt.entity.Box;
import org.putkov.tt.entity.Car;

import java.util.concurrent.TimeUnit;

public class WaitingState implements CarState{
    private static final Logger logger = LogManager.getLogger(WaitingState.class);

    @Override
    public void handle(Car car) {
        logger.info("Car {} is waiting for a free box...", car.getId());
        AuthoserviceStation station = AuthoserviceStation.getInstance();
        Box[] boxes = station.getBoxes();
        while (true){
            //кар пробует занять бокс
            for(Box box : boxes){
                if(box.getLock().tryLock()){
                    logger.info("Car {} took box {}", car.getId(), box.getId());
                    car.setCurrentBox(box);
                    car.setState(new RepairingState());
                    return;
                }
                logger.info("Car {}: no free boxes, waiting...", car.getId());
                try{
                    TimeUnit.SECONDS.sleep(1);
                }catch(InterruptedException e){
                    Thread.currentThread().interrupt();
                    logger.error("Car {} was interrupted while waiting", car.getId());
                    return;
                }
            }
        }


    }
}
