package Controller;


import DTO.LifeDto;
import DTO.SleepDto;
import Models.Refeicao;
import Service.LifeService;
import Service.SleepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class SleepController{
    @Autowired
    private SleepService sleepService;

    @Autowired
    private LifeService lifeService;

    @PostMapping("/sleep")
    public Duration sleep(@RequestBody SleepDto dto){
        LocalDateTime hourSleep = dto.getHourSleep();
        LocalDateTime hourWake = dto.getHourWake();
        Duration calculateDuration = sleepService.calculateSleep(hourSleep, hourWake);
        return calculateDuration;
    }

    @PostMapping("/calouries")
    public String calouries(@RequestBody LifeDto life){
        String TypeFood = life.getFood();
        int calouries = life.getCalouries();
        LocalTime hours = Refeicao.getHour();
        lifeService.addRefeicao(refeicao);
        return "Food: "+TypeFood+"Calouries: "+calouries;
    }

}
