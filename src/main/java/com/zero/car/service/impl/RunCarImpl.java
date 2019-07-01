package com.zero.car.service.impl;

import com.pi4j.io.gpio.*;
import com.zero.car.service.InfraredService;
import com.zero.car.service.LineTracingService;
import com.zero.car.service.RunCar;
import com.zero.car.util.PWM;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author myZero
 * @version 1.0.0
 * @date 2019/5/19 10:14
 */
@Service
@Scope
@Data
@NoArgsConstructor
public class RunCarImpl implements RunCar, ApplicationContextAware {


    @Autowired
    public RunCarImpl(List<PWM> pwmS) {
        a = pwmS.get(0);
        b = pwmS.get(1);
    }

    @Autowired
    private InfraredService infraredService;
    @Autowired
    private LineTracingService lineTracingService;

    private final Pin MOTOR_1_1 = RaspiPin.GPIO_00;
    private final Pin MOTOR_1_2 = RaspiPin.GPIO_01;

    private final Pin MOTOR_2_1 = RaspiPin.GPIO_03;
    private final Pin MOTOR_2_2 = RaspiPin.GPIO_04;

    private final Pin MOTOR_3_1 = RaspiPin.GPIO_10;
    private final Pin MOTOR_3_2 = RaspiPin.GPIO_11;
    private PWM a;
    private PWM b;

    private final GpioController gpIo = GpioFactory.getInstance();


    private GpioPinDigitalOutput motor_1_1 = gpIo.provisionDigitalOutputPin(MOTOR_1_1, "MOTOR_1_1", PinState.LOW);
    private GpioPinDigitalOutput motor_1_2 = gpIo.provisionDigitalOutputPin(MOTOR_1_2, "MOTOR_1_2", PinState.HIGH);
    private GpioPinDigitalOutput motor_2_1 = gpIo.provisionDigitalOutputPin(MOTOR_2_1, "MOTOR_2_1", PinState.HIGH);
    private GpioPinDigitalOutput motor_2_2 = gpIo.provisionDigitalOutputPin(MOTOR_2_2, "MOTOR_2_2", PinState.LOW);
    private GpioPinDigitalOutput motor_A_1_B = gpIo.provisionDigitalOutputPin(MOTOR_3_1, "MOTOR_3_1", PinState.LOW);
    private GpioPinDigitalOutput motor_A_1_A = gpIo.provisionDigitalOutputPin(MOTOR_3_2, "MOTOR_3_2", PinState.LOW);

    private final int start = 1;


    private int speed = start;

    @Override
    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    @Override
    public void fanClockwise() {
        motor_A_1_A.setState(PinState.LOW);
        motor_A_1_B.setState(PinState.HIGH);

    }

    @Override
    public void fanAnticlockwise() {

        motor_A_1_A.setState(PinState.HIGH);
        motor_A_1_B.setState(PinState.LOW);
    }

    @Override
    public void fanStop() {
        motor_A_1_B.setState(PinState.LOW);
        motor_A_1_A.setState(PinState.LOW);
    }


    private static final int MAX_SPEED = 5, MIN_SPEED = 0;

    private static ApplicationContext applicationContext;


    private void setSpeed(int speed) {
        if (speed < MIN_SPEED) {
            speed = MIN_SPEED;
        }
        if (speed > MAX_SPEED) {
            speed = MAX_SPEED;
        }
        this.speed = speed;
        /*
         * 将速度映射为PWM占空比
         */
        a.setValue(0.2f * speed);
        b.setValue(0.2f * speed);
    }

    @Override
    public void up() {

        setSpeed(this.start);

        motor_1_1.high();
        motor_1_2.low();

        motor_2_1.high();
        motor_2_2.low();


    }

    @Override
    public void down() {

        setSpeed(start);
        motor_1_1.low();
        motor_1_2.high();

        motor_2_1.low();
        motor_2_2.high();
    }

    @Override
    public void right(Integer sleep) {

        setSpeed(MAX_SPEED - 3);
        motor_1_1.low();
        motor_1_2.high();

        motor_2_1.high();
        motor_2_2.low();
        try {
            TimeUnit.MILLISECONDS.sleep(sleep);
        } catch (InterruptedException ignored) {

        }
        this.stop();
    }

    @Override
    public void rotate() {

        setSpeed(MAX_SPEED - 2);
        motor_1_2.high();
        motor_1_1.low();

        motor_2_2.low();
        motor_2_1.high();
    }

    @Override
    public void left(Integer sleep) {
        setSpeed(MAX_SPEED - 3);
        motor_1_1.high();
        motor_1_2.low();

        motor_2_1.low();
        motor_2_2.high();
        try {
            TimeUnit.MILLISECONDS.sleep(sleep);

        } catch (InterruptedException ignored) {

        }
        this.stop();
    }

    @Override
    public void stop() {
        if (infraredService != null && !infraredService.selfInterrupted()) {
            infraredService.interrupted();
        }

        if (lineTracingService != null && !lineTracingService.selfInterrupted()) {
            lineTracingService.interrupted();
        }
        speed = 0;
        setSpeed(speed);
    }

    @Override
    public void speedAcc() {

        speed = speed >= 5 ? 5 : ++speed;
        setSpeed(speed);

    }

    @Override
    public void speedDec() {

        speed = speed <= 0 ? 0 : --speed;
        setSpeed(speed);
    }

    @Override
    public void speedReset() {

        speed = 0;
    }


    @Override
    public void forceStop() {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        RunCarImpl.applicationContext = applicationContext;
    }
}
