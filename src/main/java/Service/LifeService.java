
package Service;

import Repository.Refeicao;
import Repository.RefeicaoRepository;
import Repository.User;
import Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class LifeService {

    private final RefeicaoRepository refeicaoRepository;
    private final UserRepository userRepository;

    public LifeService(
            RefeicaoRepository refeicaoRepository,
            UserRepository userRepository) {

        this.refeicaoRepository = refeicaoRepository;
        this.userRepository = userRepository;
    }

    public Refeicao addRefeicao(String nome, int calorias, Long usuarioId) {

        User usuario = userRepository.findById(usuarioId).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        Refeicao refeicao = new Refeicao(nome, LocalTime.now(), calorias);
        refeicao.setUser(usuario);
        return refeicaoRepository.save(refeicao);
    }

    public List<Refeicao> listarRefeicoes(Long usuarioId) {
        return refeicaoRepository.findByUser_Id(usuarioId);
    }

    public int somarCalorias(Long usuarioId) {
        return listarRefeicoes(usuarioId).stream().mapToInt(Refeicao::getCalouries).sum();
    }
}
