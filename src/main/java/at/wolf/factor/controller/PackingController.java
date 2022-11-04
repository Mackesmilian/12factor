package at.wolf.factor.controller;

import at.wolf.factor.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class PackingController {


    @Autowired
    private WeatherService weatherService;
    @RequestMapping("/")
    public String whatToPack(){
        return weatherService.whatToPack();
    }
}
