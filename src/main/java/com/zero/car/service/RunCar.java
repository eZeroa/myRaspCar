package com.zero.car.service;

/**
 * @author myZero
 * @version 1.0.0
 * @date 2019/5/19 10:13
 */
public interface RunCar {
    /**
     * up
     */
    void up();
    /**
     * down
     */
    void down();

    /**
     * right
     * @param sleep 控制转向时间
     */
    void right(Integer sleep);
    /**
     * left
     * @param sleep 控制转向时间
     */
    void left(Integer sleep);
    /**
     * stop
     */
    void stop();
    /**
     * speedAcc
     */
    void speedAcc();
    /**
     * speedDec
     */
    void speedDec();
    /**
     * speedReset
     */
    void speedReset();

    /**
     * rotate
     */
    void rotate();

    /**
     * 设置速度
     * @param speed
     */
    void setSpeed(Integer speed);

    /**
     * 风扇顺时针
     */
    void fanClockwise();

    /**
     * 风扇逆时针
     */
    void fanAnticlockwise ();

    /**
     * 风扇停止
     */
    void fanStop ();


    /**
     * 小车强行停止
     */
    void forceStop();


}
