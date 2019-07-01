package com.zero.car.controller;

import com.zero.car.service.LaserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zero's
 * @version 1.0.0
 * @date 18:13 2019/6/10
 */
@RestController
@RequestMapping("laser")
public class LaserController {

    private final LaserService laserService;
    @Autowired
    public LaserController(LaserService laserService) {
        this.laserService = laserService;
    }

    @RequestMapping("on")
    public String open() {
        laserService.open();
        return "on";
    }

    @RequestMapping("off")
    public String close() {
        laserService.close();
        return "off";
    }
}
