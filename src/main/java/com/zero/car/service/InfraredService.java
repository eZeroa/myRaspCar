package com.zero.car.service;

/**
 * @author myZero
 * @version 1.0.0
 * @date 2019/5/19 18:48
 */
public interface InfraredService {
    Integer FULL = 300;
    Integer HALF = FULL / 2;
    /**
     * init
     */
    void init();

    void interrupted();

    /**
     * 自身调用stop()时被打断
     */
    boolean selfInterrupted();
}
