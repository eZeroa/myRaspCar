package com.zero.car.controller;

import com.zero.car.service.RunCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author myZero
 * @version 1.0.0
 * @date 2019/5/19 10:17
 */
@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private RunCar runCar;

    @RequestMapping("/up")
    public String up() {
        runCar.up();
        return "up";
    }

    @RequestMapping("/down")
    public String down() {
        runCar.down();
        return "down";
    }

    @RequestMapping("/left")
    public String left(Integer sleep) {
        runCar.left(sleep);
        return "left";

    }

    @RequestMapping("/right")
    public String right(Integer sleep) {
        runCar.right(sleep);
        return "right";

    }

    @RequestMapping("/speedAcc")
    public String speedAcc() {
        runCar.speedAcc();
        return "speedAcc";

    }

    @RequestMapping("/speedDec")
    public String speedDec() {
        runCar.speedDec();
        return "speedDec";

    }

    @RequestMapping("speedReset")
    public String speedReset() {
        runCar.speedReset();
        return "speedReset";

    }

    @RequestMapping("stop")
    public String stop() {
        runCar.stop();
        return "stop";

    }

    @RequestMapping("rotate")
    public String rotate() {
        runCar.rotate();
        return "rotate";
    }

    @RequestMapping("fanC")
    public String fanC() {
        runCar.fanClockwise();
        return "fanClockwise";
    }

    @RequestMapping("fanA")
    public String fanA() {
        runCar.fanAnticlockwise();
        return "fanAnticlockwise";
    }

    @RequestMapping("fanS")
    public String fanS() {
        runCar.fanStop();
        return "fanStop";
    }


}
