package org.putkov.tt.entity.state;

import org.putkov.tt.entity.Car;

public interface CarState {
    void handle(Car car);
}
