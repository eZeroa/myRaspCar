package com.zero.car.controller;

import com.zero.car.service.LightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zero's
 * @version 1.0.0
 * @date 11:04 2019/6/4
 */
@RestController
@RequestMapping("light")
public class LightController {
    private final LightService lightService;

    @Autowired
    public LightController(LightService lightService) {
        this.lightService = lightService;
    }
    @RequestMapping("on")
    public String turnON() {
        lightService.turnOn();
        return "lightON";
    }
    @RequestMapping("off")
    public String turnOFF() {
        lightService.turnOff();
        return "lightOFF";
    }

}
