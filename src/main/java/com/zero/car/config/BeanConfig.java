package com.zero.car.config;

import com.zero.car.util.PWM;
import com.zero.car.util.Servo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zero's
 * @version 1.0.0
 * @date 10:54 2019/6/13
 */
@Configuration
public class BeanConfig {


    @Bean(name = "servos")
    public List<Servo> servos() {
        List<Servo> servos = new ArrayList<>();
        try {
            servos.add(new Servo(6, -90, 90));
            servos.add(new Servo(1, -90, 90));

            servos.add(new Servo(2, -90, 90));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return servos;
    }

    @Bean(name = "pwmS")
    public List<PWM> pwmS() {
        List<PWM> pwmS = new ArrayList<>();
        try {
            pwmS.add(new PWM(4, 50));
            pwmS.add(new PWM(5, 50));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pwmS;
    }
}
