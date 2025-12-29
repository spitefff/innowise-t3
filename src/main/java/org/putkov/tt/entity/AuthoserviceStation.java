package org.putkov.tt.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuthoserviceStation {
    private static final Logger logger = LogManager.getLogger(AuthoserviceStation.class);
    private static AuthoserviceStation instance;
    private final Box[] boxes;
    private final Storage storage;
    private AuthoserviceStation(int boxCount, int initialParts) {
        this.boxes = new Box[boxCount];
        for (int i = 0; i < boxCount; i++) {
            boxes[i] = new Box(i+1);
        }
        this.storage = new Storage(initialParts);
        logger.info("AuthoserviceStation initialized with {} boxes and {} parts", boxCount, initialParts);
    }
    public static AuthoserviceStation init() {
        int boxCount = 3;
        int initialParts = 100;

        return new AuthoserviceStation(boxCount, initialParts);
    }
    public static AuthoserviceStation init(int boxCount, int initialParts) {
        return new AuthoserviceStation(boxCount, initialParts);
    }
    private static class Holder {
        private static final AuthoserviceStation INSTANCE = init();
    }
    public static AuthoserviceStation getInstance() {
        return Holder.INSTANCE;
    }

    public Box[] getBoxes() {
        return boxes;
    }

    public Storage getStorage() {
        return storage;
    }
}
