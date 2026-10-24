
package Repository;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Table(name = "exercices")
@Getter
@Setter
public class Exercice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int repeat;

    @Column(nullable = false)
    private String traine;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Exercice() {
    }

    public Exercice(String name, int repeat, String traine) {
        this.name = name;
        this.repeat = repeat;
        this.traine = traine;
        this.createdAt = LocalDateTime.now();
    }

    @PrePersist
    public void antesDeSalvar() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;



}
