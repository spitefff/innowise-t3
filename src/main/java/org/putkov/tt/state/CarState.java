package org.putkov.tt.state;

import org.putkov.tt.entity.Car;

public interface CarState {
    void handle(Car car);
}
