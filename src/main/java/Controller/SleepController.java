package Controller;


import DTO.SleepDto;
import Service.SleepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class SleepController{
    @Autowired
    private SleepService sleepService;

    @PostMapping("/sleep")
    public Duration sleep(@RequestBody SleepDto dto){
        LocalDateTime hourSleep = dto.getHourSleep();
        LocalDateTime hourWake = dto.getHourWake();
        Duration calculateDuration = sleepService.calculateSleep(hourSleep, hourWake);
        return calculateDuration;
    }

}
