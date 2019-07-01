package com.zero.car.controller;

import com.zero.car.service.InfraredService;
import com.zero.car.service.LineTracingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author myZero
 * @version 1.0.0
 * @date 2019/5/19 18:47
 */
@RestController
@RequestMapping("/navigate")
@Lazy
public class NavigateController {

    @Autowired
    private InfraredService infraredService;
    @Autowired
    private LineTracingService lineTracingService;


    @RequestMapping("/goAvoid")
    public String goAvoid() {
        infraredService.init();
        return "go";
    }

    @RequestMapping("/goLine")
    public String goLine() {
        lineTracingService.navigate();
        return "navigate";
    }
}
