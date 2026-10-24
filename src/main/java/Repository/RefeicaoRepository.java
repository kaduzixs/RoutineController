
package Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RefeicaoRepository
        extends JpaRepository<Refeicao, Long> {

    List<Refeicao> findByUser_Id(Long usuarioId);
}
