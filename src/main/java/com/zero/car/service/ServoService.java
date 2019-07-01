package com.zero.car.service;

/**
 * @author Zero's
 * @version 1.0.0
 * @date 17:01 2019/6/17
 */
public interface ServoService {

    /**
     * 摄像头舵机左右
     * @param angle 旋转角度
     * @return
     */
    String cameraLeftAndRight(Integer angle);

    /**
     * 摄像头舵机上下
     * @param angle 旋转角度
     * @return
     */
    String cameraUpAndDown(Integer angle);

    /**
     * 超声波舵机左右
     * @param angle 旋转角度
     * @return
     */
    String ultrasonicWaveLeftAndRight(Integer angle);

}
