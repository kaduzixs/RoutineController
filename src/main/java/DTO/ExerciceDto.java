package DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExerciceDto{
    String exercice;
    int reapets;

    public void _contruct(String exercice,  int reapets){
        this.exercice = exercice;
        this.reapets = reapets;
    }
}
