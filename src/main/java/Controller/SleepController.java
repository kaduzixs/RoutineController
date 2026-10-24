
package Controller;

import DTO.ExerciceDto;
import DTO.LifeDto;
import DTO.SleepDto;
import Models.Exercice;
import Repository.Refeicao;
import Service.ExerciceService;
import Service.LifeService;
import Service.SleepService;

import jakarta.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class SleepController {

    private final SleepService sleepService;
    private final LifeService lifeService;
    private final ExerciceService exerciceService;

    public SleepController(SleepService sleepService, LifeService lifeService, ExerciceService exerciceService) {
        this.sleepService = sleepService;
        this.lifeService = lifeService;
        this.exerciceService = exerciceService;
    }

    @PostMapping("/sleep")
    public Duration sleep(@RequestBody SleepDto dto) {
        return sleepService.calculateSleep(dto.getHourSleep(), dto.getHourWake());
    }

    @PostMapping("/calouries")
    public String calouries(
            @RequestBody LifeDto life,
            HttpSession session) {

        Long usuarioId = obterUsuarioId(session);

        String tipoFood = life.getFood();
        int calorias = life.getCalouries();

        Refeicao salva = lifeService.addRefeicao(tipoFood, calorias, usuarioId
        );

        return "Refeição registrada: " + salva.getName() + " | Calorias: " + salva.getCalouries();
    }

    @GetMapping("/calouries/list")
    public List<Refeicao> listarRefeicoes(HttpSession session) {
        return lifeService.listarRefeicoes(obterUsuarioId(session));
    }

    @GetMapping("/calouries/total")
    public int totalCalorias(HttpSession session) {
        return lifeService.somarCalorias(obterUsuarioId(session));
    }

    @PostMapping("/exercice")
    public String exercices(@RequestBody ExerciceDto exercice, HttpSession session) {

        Long usuarioId = obterUsuarioId(session);

        exerciceService.addExercice(new Exercice(exercice.getExercice(), exercice.getReapets()), usuarioId);

        return "Exercice: " + exercice.getExercice() + " Repeats: " + exercice.getReapets();
    }

    @GetMapping("/exercice")
    public List<Exercice> listExercices(HttpSession session) {
        return exerciceService.listar(obterUsuarioId(session));
    }

    private Long obterUsuarioId(HttpSession session) {

        Object id = session.getAttribute("usuarioId");

        if (!(id instanceof Long)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Faça login para acessar seus dados.");
        }

        return (Long) id;
    }
}
