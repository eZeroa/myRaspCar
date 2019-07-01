package com.zero.car.service;

/**
 * @author Zero's
 * @version 1.0.0
 * @date 15:36 2019/6/16
 */
public interface LineTracingService {
    /**
     * 黑白线寻迹
     */
    void navigate();

    void interrupted();

    /**
     * 自身调用stop()时被打断
     */
    boolean selfInterrupted();
}
