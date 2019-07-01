package com.zero.car.service.impl;

import com.pi4j.io.gpio.*;
import com.zero.car.service.InfraredService;
import com.zero.car.service.RunCar;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author myZero
 * @version 1.0.0
 * @date 2019/5/19 18:48
 */
@Service
@Scope
@Data
@NoArgsConstructor
@Lazy
public class InfraredServiceImpl implements InfraredService, ApplicationContextAware {
    private boolean self;
    @Override
    public boolean selfInterrupted() {
        return self;
    }

    private final Pin INPUT_01 = RaspiPin.GPIO_27;
    private final Pin INPUT_02 = RaspiPin.GPIO_28;
    private final Pin INPUT_03 = RaspiPin.GPIO_29;
    private final Pin INPUT_04 = RaspiPin.GPIO_24;

    @Autowired
    private RunCar runCar;

    private final GpioController gpIo = GpioFactory.getInstance();
    private GpioPinDigitalInput frontLeftInput = gpIo.provisionDigitalInputPin(INPUT_01, "INPUT_01");
    private GpioPinDigitalInput frontRightInput = gpIo.provisionDigitalInputPin(INPUT_02, "INPUT_02");
    private GpioPinDigitalInput rearLeftInput = gpIo.provisionDigitalInputPin(INPUT_03, "INPUT_03");
    private GpioPinDigitalInput rearRightInput = gpIo.provisionDigitalInputPin(INPUT_04, "INPUT_04");

    private boolean interrupted;


    private static ApplicationContext applicationContext;

    @Async
    public void infrared() {

    }

    @Override
    @Async
    public void init() {
        if (runCar == null) {
            return;
        }
        interrupted = false;
        long start = System.currentTimeMillis();
        while (!interrupted) {
            if (System.currentTimeMillis() - start > 6000) {
                self = false;
                interrupted();
            }
            smartCar();
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException ignored) {
            }
        }
    }

    @Override
    public void interrupted() {
        interrupted = true;
    }

    private void smartCar() {
        boolean frontLeft = frontLeftInput.isLow();
        boolean frontRight = frontRightInput.isLow();
        boolean rearLeft = rearLeftInput.isLow();
        boolean rearRight = rearRightInput.isLow();

        if (frontLeft && frontRight) {
            runCar.down();
            interrupted = false;
        } else if (rearLeft && rearRight) {
            runCar.up();
            interrupted = false;
        } else if (frontLeft) {
            runCar.right(FULL);
            interrupted = false;
            if (frontRightInput.isLow()) {
                runCar.left(HALF);
                interrupted = false;
            }
            runCar.up();
            interrupted = false;
        } else if (frontRight) {
            runCar.left(FULL);
            interrupted = false;
            if (frontLeftInput.isLow()) {
                runCar.right(HALF);
                interrupted = false;
            }
            runCar.up();
            interrupted = false;
        } else if (rearLeft) {
            runCar.right(FULL);
            interrupted = false;
            if (rearRightInput.isLow()) {
                runCar.left(HALF);
                interrupted = false;
            }
            runCar.up();
            interrupted = false;
        } else if (rearRight) {
            runCar.left(FULL);
            interrupted = false;
            if (rearLeftInput.isLow()) {
                runCar.right(HALF);
                interrupted = false;
            }
            runCar.up();
            interrupted = false;
        } else {
            runCar.up();
            interrupted = false;
        }
        self = true;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        InfraredServiceImpl.applicationContext = applicationContext;
    }
}
