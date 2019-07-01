package com.zero.car.service;

/**
 * @author Zero's
 * @version 1.0.0
 * @date 15:57 2019/6/4
 */
public interface UltrasonicWaveService {
    /**
     * 超声波测距
     * @return
     * 返回结果单位为cm
     */
    String getDistance();
}
