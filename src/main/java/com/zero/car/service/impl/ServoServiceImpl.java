package com.zero.car.service.impl;

import com.zero.car.service.ServoService;
import com.zero.car.util.Servo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author Zero's
 * @version 1.0.0
 * @date 17:03 2019/6/17
 */
@Service
public class ServoServiceImpl implements ServoService {

    @Autowired
    private List<Servo> servos;

    private Servo cameraLeftAndRight;
    private Servo cameraUpAndDown;
    private Servo ultrasonicWaveLeftAndRight;

    private int angleCameraLeftAndRight = 0;
    private int angleCameraUpAndDown = 0;
    private int angleUltrasonicWaveLeftAndRight = 0;

    @PostConstruct
    private void init() {
        cameraLeftAndRight = servos.get(0);
        cameraUpAndDown = servos.get(1);
        ultrasonicWaveLeftAndRight = servos.get(2);
    }

    @Override
    public String cameraLeftAndRight(Integer angle) {
        angleCameraLeftAndRight += angle;

        cameraLeftAndRight.set(checkAngle(angleCameraLeftAndRight));
        return "cameraLeftAndRight" + angle;
    }

    @Override
    public String cameraUpAndDown(Integer angle) {
        angleCameraUpAndDown += angle;

        cameraUpAndDown.set(checkAngle(angleCameraUpAndDown));
        return "cameraUpAndDown" + angle;
    }

    @Override
    public String ultrasonicWaveLeftAndRight(Integer angle) {
        angleUltrasonicWaveLeftAndRight += angle;

        ultrasonicWaveLeftAndRight.set(checkAngle(angleUltrasonicWaveLeftAndRight));
        return "ultrasonicWaveLeftAndRight" + angle;
    }

    private int checkAngle(int angle) {
        return angle > 90 ? 90 : (angle < -90 ? -90 : angle);
    }

}
