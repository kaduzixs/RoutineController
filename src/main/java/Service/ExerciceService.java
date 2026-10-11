
package Service;

import Repository.Exercice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciceService {

    private final Exercice exerciceRepository;

    public ExerciceService(Exercice exerciceRepository) {
        this.exerciceRepository = exerciceRepository;
    }

    public List<Models.Exercice> listar() {
        return exerciceRepository.findAll();
    }

    public Models.Exercice addExercice(Models.Exercice exercice) {
        return exerciceRepository.save(exercice);
    }

    public Models.Exercice salvar(Models.Exercice exercice) {
        return exerciceRepository.save(exercice);
    }
}
