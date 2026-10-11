package Repository;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "exercices")
public class Exercice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private int repeat;

    @Column(nullable = false)
    private String traine;

    public Exercice(){}

    public Exercice(String name, int repeat, String traine){
        this.name = name;
        this.repeat = repeat;
        this.traine = traine;
    }
}
