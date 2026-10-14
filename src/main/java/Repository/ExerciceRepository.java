
package Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;

public interface ExerciceRepository extends JpaRepository<Exercice, Long> {
    void deleteByCreatedAtBefore(LocalDateTime limite);
}
