package com.zero.car.util;

import com.pi4j.gpio.extension.pca.PCA9685GpioProvider;
import com.pi4j.gpio.extension.pca.PCA9685Pin;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Zero's
 * @version 1.0.0
 * @date 15:56 2019/6/11
 * @copy by seth.yang
 */
public class PWM {
    PCA9685GpioProvider provider;

    private static Map<Integer, Pin> PWMS = new ConcurrentHashMap<>(16);
    protected static GpioController gpio = GpioFactory.getInstance();

    private static final Object locker = new Object();

    private static I2CBus bus;

    private static void init() {
        try {
            synchronized (locker) {
                /*
                 * 初始化I2C总线
                 */
                bus = I2CFactory.getInstance(I2CBus.BUS_1);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (I2CFactory.UnsupportedBusNumberException e) {
            e.printStackTrace();
        }
    }

    int pinIndex;
    private Pin pin;
    private float value;

    /**
     * PWM(引脚, 频率)
     *
     * @param pin
     * @param frequency
     * @throws IOException
     */
    public PWM(int pin, int frequency) throws IOException {
        this(pin, new BigDecimal(frequency));
    }

    public PWM(int pin, BigDecimal frequency) throws IOException {
        /**
         * 先初始化
         */
        init();

        /**
         * 引脚被占用抛出异常
         */
        if (PWMS.containsKey(pin)) {
            throw new IllegalStateException("Pin #" + pin + " is in use.");
        }

        this.pinIndex = pin;
        /**
         * 0 - 15
         */
        this.pin = PCA9685Pin.ALL[pin];
        /**
         * 初始化PCA9685
         * address固定为0x40
         */
        provider = new PCA9685GpioProvider(bus, 0x40, frequency);
        /**
         * 设置为PWM输出
         */
        gpio.provisionPwmOutputPin(provider, PCA9685Pin.ALL[pin], "Pulse 00");
        /**
         * 记录被占用的PIN
         */
        PWMS.put(pin, this.pin);
    }

    /**
     * 设置占空比
     * value
     * <p>
     * 0 - 4095个tick
     *
     * @param value
     */
    public void setValue(float value) {
        if (value <= 0) {
            this.value = 0;
            provider.setAlwaysOff(pin);
        } else if (value >= 1) {
            this.value = 1;
            provider.setAlwaysOn(pin);
        } else {
            this.value = value;
            provider.setPwm(pin, 0, (int) (this.value * 4095));
        }
    }

    /**
     * getValue
     * @return
     */
    public float getValue() {
        return this.value;
    }
}
