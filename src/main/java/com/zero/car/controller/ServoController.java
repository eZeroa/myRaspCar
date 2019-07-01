package com.zero.car.controller;

import com.zero.car.service.ServoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zero's
 * @version 1.0.0
 * @date 19:11 2019/6/10
 */
@RestController
@RequestMapping("servo")
public class ServoController {

    private final ServoService servoService;

    @Autowired
    public ServoController(ServoService servoService) {
        this.servoService = servoService;
    }

    @RequestMapping("cameraLeftAndRight")
    public String cameraLeftAndRight(int angle) {
        return servoService.cameraLeftAndRight(angle);
    }

    @RequestMapping("cameraUpAndDown")
    public String cameraUpAndDown(int angle) {
        return servoService.cameraUpAndDown(angle);

    }

    @RequestMapping("ultrasonicWaveLeftAndRight")
    public String ultrasonicWaveLeftAndRight(int angle) {
        return servoService.ultrasonicWaveLeftAndRight(angle);
    }

}
