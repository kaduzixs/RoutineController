package Service;


import Models.Exercice;
import Models.Refeicao;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExerciceService {
    String exercice;
    int reapets;

    private final List<Exercice> traine = new ArrayList<>();


    public void _contruct(String exercice,  int reapets){
        this.exercice = exercice;
        this.reapets = reapets;
    }

    public List<Exercice> listExercices(){return traine;}


    public void addExercice(Exercice exercice) {
        traine.add(exercice);
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void resetTraine() {
        traine.clear();
    }
}
