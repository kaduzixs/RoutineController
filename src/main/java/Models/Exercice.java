package Models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Exercice {
    private  String exercice;
    private int repeats;

    public Exercice(String exercice, int repeats){
        this.exercice = exercice;
        this.repeats = repeats;
    }
}




