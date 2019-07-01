package com.zero.car.service.impl;

import com.pi4j.io.gpio.*;
import com.zero.car.service.UltrasonicWaveService;
import org.springframework.stereotype.Service;

import java.util.Stack;

/**
 * @author Zero's
 * @version 1.0.0
 * @date 15:58 2019/6/4
 */
@Service
public class UltrasonicWaveServiceImpl implements UltrasonicWaveService {
    private final Pin trig = RaspiPin.GPIO_21;
    private final Pin echo = RaspiPin.GPIO_22;


    private GpioController gpIo = GpioFactory.getInstance();
    private GpioPinDigitalOutput trigOutPut = gpIo.provisionDigitalOutputPin(this.trig, "trig", PinState.LOW);
    private GpioPinDigitalInput echoInput = gpIo.provisionDigitalInputPin(echo, "echo");


    @Override
    public String getDistance() {
        long time = System.nanoTime();
        trigOutPut.setState(PinState.HIGH);
        while (System.nanoTime() - time < 40000) {
        }
        trigOutPut.setState(PinState.LOW);

        while (echoInput.isLow()) {
        }

        time = System.nanoTime();

        while (echoInput.isHigh()) {
        }
        long dist = (System.nanoTime() - time) * 340 / 2;
        boolean tag_00 = false;
        boolean tag_01 = false;
        System.out.println(dist);
        if (dist < 100000000) {
            tag_00 = true;
        } else if (dist > 100000000 && dist < 1000000000) {
            tag_01 = true;
        } else {
        }
        Stack<Long> stack = new Stack<>();
        while (dist > 0) {
            stack.push(dist % 10);
            dist /= 10;
        }
        StringBuilder distance = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            if (tag_00) {
                distance.append(0);
                distance.append(0);
                i++;
                tag_00 = false;
            } else if (tag_01) {
                distance.append(0);
                tag_01 = false;
            } else {
                distance.append(stack.pop());
            }
        }
        System.out.println(distance.toString());
        return distance.toString();
    }
}
