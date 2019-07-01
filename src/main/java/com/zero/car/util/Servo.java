package com.zero.car.util;

import com.pi4j.gpio.extension.pca.PCA9685Pin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zero's
 * @version 1.0.0
 * @date 16:07 2019/6/11
 */
public class Servo extends PWM implements TimeoutListener {
    @Override
    public void onTimeout() {

    }

    /**
     * 枚举
     */
    public enum Direction {
        INCREMENT, DECREMENT
    }

    /**
     * 最小的有效脉宽
     */
    private static final int SERVO_DURATION_MIN = 500;
    /**
     * 中位的脉宽值
     */
    private static final int SERVO_DURATION_NEUTRAL = 1500;
    /**
     * 最大有效脉宽
     */
    private static final int SERVO_DURATION_MAX = 2500;

    /**
     * 用于记录当前舵机停留的角度
     */
    private int angle = 0;
    /**
     * 舵机允许的最小和最大的角度，可通过构造函数指定
     */
    private int minAngle = -90, maxAngle = 90;

    /**
     * 舵机转动方向
     */
    private Direction dir = Direction.INCREMENT;

    /**
     * 事件处理器
     */
    private List<ServoListener> list = new ArrayList<>();

    public Servo(int pin, final int min, final int max) throws IOException {
        super(pin, 50);
        minAngle = min;
        maxAngle = max;
    }

    /**
     * 设定舵机的角度
     */
    public void set(int angle) {
        if (angle < minAngle)
            angle = minAngle;
        if (angle > maxAngle)
            angle = maxAngle;
        this.angle = angle;
        double tmp = angle + 90;
        // 将角度计算为脉宽
        double duration = SERVO_DURATION_MIN + 100 * tmp / 9;

        /*
         * 脉宽大于最大脉宽
         */
        if (duration > SERVO_DURATION_MAX) {

            duration = SERVO_DURATION_MAX;

            /*
             * 脉宽小于最小脉宽
             */
        } else if (duration < SERVO_DURATION_MIN) {

            duration = SERVO_DURATION_MIN;

        }

        /*
         *  GpioController provider
         *  设置PWM
         */
        provider.setPwm(PCA9685Pin.ALL[pinIndex], (int) duration);
    }

    /**
     * 获取舵机当前停留的角度
     */
    public int get() {
        return this.angle;
    }

    public void addListener(ServoListener listener) {
        list.add(listener);
    }

    protected void fireListener(int border) {
        for (ServoListener listener : list) {
            listener.onReachBorder(this, border);
        }
    }
}
