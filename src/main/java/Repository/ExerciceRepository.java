
package Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface ExerciceRepository extends JpaRepository<Exercice, Long> {
    List<Exercice> findByUser_Id(Long userId);
    void deleteByCreatedAtBefore(LocalDateTime limite);
}
