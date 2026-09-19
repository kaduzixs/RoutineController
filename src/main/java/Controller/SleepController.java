package Controller;


import DTO.SleepDto;
import Service.SleepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.LocalTime;

@RestController
@RequestMapping("/user")
public class SleepController{
    @Autowired
    private SleepService sleepService;

    @PostMapping("/sleep")
    public Duration sleep(@RequestBody SleepDto dto){
        LocalTime hourSleep = dto.getHourSleep();
        LocalTime hourWake = dto.getHourWake();
        Duration calculateDuration = sleepService.calculateSleep(hourSleep, hourWake);
        return calculateDuration;
    }
}
