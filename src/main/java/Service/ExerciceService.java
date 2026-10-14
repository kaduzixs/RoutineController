
package Service;

import Models.Exercice;
import Repository.ExerciceRepository;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;
import jakarta.annotation.PostConstruct;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExerciceService {

    private final ExerciceRepository exerciceRepository;

    public ExerciceService(ExerciceRepository exerciceRepository) {
        this.exerciceRepository = exerciceRepository;
    }

    public List<Exercice> listar() {
        return exerciceRepository.findAll().stream().map(ExerciceService::toModel).toList();
    }

    public Exercice addExercice(Exercice exercice) {
        Repository.Exercice entity = new Repository.Exercice(exercice.getExercice(), exercice.getRepeats(), "");

        return toModel(exerciceRepository.save(entity));
    }

    public Exercice salvar(Exercice exercice) {
        return addExercice(exercice);
    }

    private static Exercice toModel(Repository.Exercice entity) {
        return new Exercice(entity.getName(),entity.getRepeat());
    }

    @PostConstruct
    public void limparExpiradosAoIniciar() {
        limparExpirados();
    }

    @Scheduled(fixedDelay = 60000)
    public void limparExpirados() {
        LocalDateTime limite = LocalDateTime.now().minusHours(24);
        exerciceRepository.deleteByCreatedAtBefore(limite);
    }
}
