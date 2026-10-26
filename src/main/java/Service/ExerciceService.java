
package Service;

import Models.Exercice;
import Repository.ExerciceRepository;
import Repository.User;
import Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExerciceService {

    private final ExerciceRepository exerciceRepository;
    private final UserRepository userRepository;

    public ExerciceService(ExerciceRepository exerciceRepository,UserRepository userRepository) {
        this.exerciceRepository = exerciceRepository;
        this.userRepository = userRepository;
    }

    public List<Exercice> listar(Long usuarioId) {
        return exerciceRepository.findByUser_Id(usuarioId).stream().map(ExerciceService::toModel).toList();

    public Exercice addExercice(Exercice exercice, Long usuarioId) {
        User usuario = userRepository.findById(usuarioId).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        Repository.Exercice entity = new Repository.Exercice(exercice.getExercice(),exercice.getRepeats(),"");

        entity.setUser(usuario);

        Repository.Exercice salvo = exerciceRepository.save(entity);

        return toModel(salvo);
    }

    private static Exercice toModel(Repository.Exercice entity) {
        return new Exercice(entity.getName(),entity.getRepeat());
    }

    @Scheduled(fixedDelay = 60000)
    @Transactional
    public void limparExpirados() {
        LocalDateTime limite = LocalDateTime.now().minusHours(24);
        exerciceRepository.deleteByCreatedAtBefore(limite);
    }
}
