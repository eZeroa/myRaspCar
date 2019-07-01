package com.zero.car.service.impl;

import com.pi4j.io.gpio.*;
import com.zero.car.service.LightService;
import org.springframework.stereotype.Service;

/**
 * @author Zero's
 * @version 1.0.0
 * @date 11:00 2019/6/4
 */
@Service
public class LightServiceImpl implements LightService {
    private final Pin high = RaspiPin.GPIO_12;
    private GpioPinDigitalOutput digital = GpioFactory.getInstance().provisionDigitalOutputPin(high, "high", PinState.LOW);

    @Override
    public void turnOn() {

        digital.setState(PinState.HIGH);
    }

    @Override
    public void turnOff() {

        digital.setState(PinState.LOW);
    }
}
