package org.putkov.tt.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Box {
    private static final Logger logger = LogManager.getLogger(Box.class);
    private final int id;
    private final Lock lock = new ReentrantLock();

    public Box(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean tryAcquire(Car car){
        if(lock.tryLock()){
            logger.info("Car {} acquired Box {}", car.getId(), id);
            return true;
        }
        return false;
    }

    public Lock getLock() {
        return lock;
    }

    public void release(Car car){
        lock.unlock();
        logger.info("Car {} released Box {}", car.getId(), id);
    }
}
