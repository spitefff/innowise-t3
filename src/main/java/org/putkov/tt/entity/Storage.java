package org.putkov.tt.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.putkov.tt.exception.AuthoserviceException;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Storage {
    private static final Logger logger = LogManager.getLogger(Storage.class);
    private int parts;
    private final Lock lock = new ReentrantLock();

    public Storage(int parts) {
        this.parts = parts;
    }

    public void takeParts(int amount) throws AuthoserviceException {
        lock.lock();
        try {
            if(parts<amount){
                logger.error("Not enough parts in storage. Requested: {}, available: {}", amount, parts);
                throw new AuthoserviceException("Not enough parts in storage");
            }
            parts=parts-amount;
            logger.info("Took {} parts from storage. Remaining: {}", amount, parts);
        }finally {
            lock.unlock();
        }
    }

    public int getParts() {
        return parts;
    }
}
