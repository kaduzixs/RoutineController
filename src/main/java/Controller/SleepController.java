package Controller;


import DTO.ExerciceDto;
import DTO.LifeDto;
import DTO.SleepDto;
import Models.Exercice;
import Models.Refeicao;
import Service.ExerciceService;
import Service.LifeService;
import Service.SleepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class SleepController{
    @Autowired
    private SleepService sleepService;

    @Autowired
    private LifeService lifeService;

    @Autowired
    private ExerciceService exerciceService;

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
        LocalTime hours = LocalTime.now();
        Refeicao refeicao = new Refeicao(TypeFood, hours, calouries);
        lifeService.addRefeicao(refeicao);
        return "Food: "+TypeFood+"Calouries: "+calouries+"Hours: "+hours;
    }

    @GetMapping("/calouries/total")
    public int totalCalorias() {
        return lifeService.somarCalorias();
    }

    @PostMapping("/exercice")
    public String exercices(@RequestBody ExerciceDto exercice){
        String TypeExercice = exercice.getExercice();
        int reapets = exercice.getReapets();
        Exercice traine = new Exercice(TypeExercice, reapets);
        exerciceService.addExercice(traine);
        return "Exercice: "+ TypeExercice+ "Repeats: "+reapets;
    }
    @GetMapping("/exercice")
    public List<Exercice> listExercices() {
        return exerciceService.listar();
    }

}
