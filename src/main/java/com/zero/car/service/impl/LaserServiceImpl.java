package com.zero.car.service.impl;

import com.pi4j.io.gpio.*;
import com.zero.car.service.LaserService;
import org.springframework.stereotype.Service;

/**
 * @author Zero's
 * @version 1.0.0
 * @date 18:10 2019/6/10
 */
@Service
public class LaserServiceImpl implements LaserService {
    private final Pin high = RaspiPin.GPIO_14;
    private GpioPinDigitalOutput digital = GpioFactory.getInstance().provisionDigitalOutputPin(high, "high", PinState.LOW);

    @Override
    public void open() {
        digital.setState(PinState.HIGH);
    }

    @Override
    public void close() {
        digital.setState(PinState.LOW);
    }
}
