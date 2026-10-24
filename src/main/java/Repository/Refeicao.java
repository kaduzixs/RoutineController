
package Repository;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "refeicoes")
public class Refeicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalTime hour;

    @Column(nullable = false)
    private int calouries;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Refeicao() {}

    public Refeicao(String name, LocalTime hour, int calouries) {
        this.name = name;
        this.hour = hour;
        this.calouries = calouries;
    }


}
