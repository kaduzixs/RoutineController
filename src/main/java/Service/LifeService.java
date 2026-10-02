package Service;


import Models.Refeicao;
import lombok.Getter;
import lombok.Setter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
@Getter
@Setter
public class LifeService{
    public String food;
    public int caloria;

    private final List<Refeicao> refeicoes = new ArrayList<>();

    void construct(String food, int caloria){
        this.food = food;
        this.caloria = caloria;
    }

    public List<Refeicao> listarRefeicoes() {
        return refeicoes;
    }

    public void addRefeicao(Refeicao refeicao) {
        refeicoes.add(refeicao);
    }



    @Scheduled(cron = "0 0 0 * * *")
    public void resetRefeicoes() {
        refeicoes.clear();
    }


    public int somarCalorias() {
        int total = 0;
        for (Refeicao refeicao : refeicoes) {
            total += refeicao.getCalouries();
        }

        return total;
    }

    



}
