package com.zero.car.controller;

import com.zero.car.service.UltrasonicWaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zero's
 * @version 1.0.0
 * @date 16:12 2019/6/4
 */
@RestController
@RequestMapping("wave")
public class UltrasonicWaveController {
    private final UltrasonicWaveService ultrasonicWaveService;
    @Autowired
    public UltrasonicWaveController(UltrasonicWaveService ultrasonicWaveService) {
        this.ultrasonicWaveService = ultrasonicWaveService;
    }
    @RequestMapping("distance")
    public String getDistance() {
        return ultrasonicWaveService.getDistance();
    }
}
