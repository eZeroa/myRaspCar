package com.zero.car.service.impl;

import com.pi4j.io.gpio.*;
import com.zero.car.service.LineTracingService;
import com.zero.car.service.RunCar;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author Zero's
 * @version 1.0.0
 * @date 15:37 2019/6/16
 */
@Service
@Scope
@Data
@NoArgsConstructor
@Lazy
public class LineTracingServiceImpl implements LineTracingService {
    private final Pin INPUT_01 = RaspiPin.GPIO_02;
    private final Pin INPUT_02 = RaspiPin.GPIO_05;
    private final Pin INPUT_03 = RaspiPin.GPIO_06;
    private final Pin INPUT_04 = RaspiPin.GPIO_07;

    @Autowired
    private RunCar runCar;

    private final GpioController gpIo = GpioFactory.getInstance();
    private GpioPinDigitalInput input_01 = gpIo.provisionDigitalInputPin(INPUT_01, "INPUT_01");
    private GpioPinDigitalInput input_02 = gpIo.provisionDigitalInputPin(INPUT_02, "INPUT_02");
    private GpioPinDigitalInput input_03 = gpIo.provisionDigitalInputPin(INPUT_03, "INPUT_03");
    private GpioPinDigitalInput input_04 = gpIo.provisionDigitalInputPin(INPUT_04, "INPUT_04");

    private boolean interrupted;
    private boolean self;

    @Override
    public boolean selfInterrupted() {
        return self;
    }

    @Override
    @Async
    public void navigate() {
        interrupted = false;
        runCar.setSpeed(1);
        while (!interrupted) {
            if (input_01.isHigh() && input_02.isHigh() && input_03.isHigh() && input_04.isHigh()) {
                interrupted();
                self = false;
                break;
            }

            if (input_01.isHigh()) {
                runCar.left(3);

            } else if (input_04.isHigh()) {
                runCar.right(3);

            } else if (input_02.isHigh()) {
                if (input_01.isLow()) {
                    if (input_03.isHigh() && input_04.isHigh()) {
                        runCar.right(3);
                    }
                } else if (input_01.isHigh()) {
                    if (input_03.isLow() && input_04.isLow()) {
                        runCar.left(3);
                    }
                }

            } else if (input_03.isHigh()) {
                if (input_04.isLow()) {
                    if (input_01.isHigh() && input_02.isHigh()) {
                        runCar.left(3);
                    }
                } else if (input_04.isHigh()) {
                    if (input_01.isLow() && input_02.isLow()) {
                        runCar.right(3);
                    }
                }
            //前进
            } else if ((input_02.isLow() && input_03.isLow()) || (input_01.isLow() && input_02.isLow() && input_03.isLow() && input_04.isLow())) {
                runCar.up();
            }
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (InterruptedException ignored) {
            }
            self = true;
            runCar.stop();
        }

    }

    @Override
    public void interrupted() {
        interrupted = true;
    }
}
