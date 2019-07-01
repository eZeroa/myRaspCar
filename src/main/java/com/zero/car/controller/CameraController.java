package com.zero.car.controller;

import com.zero.car.util.Camera;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author Zero's
 * @version 1.0.0
 * @date 18:22 2019/6/10
 */
@RestController
@RequestMapping("camera")
public class CameraController {

    private Camera camera;
    private boolean isInit;
    private void init() {
        camera = new Camera(8002);
        isInit = true;
    }

    @RequestMapping("on")
    public String open() {
        if (!isInit) {
            init();
        }
        try {
            camera.open();
        } catch (IOException ignored) {
        }
        return "on";
    }

    @RequestMapping("off")
    public String close() {
        if (!isInit) {
            return "camera not open";
        }
        camera.close();
        return "camera off";
    }
}
