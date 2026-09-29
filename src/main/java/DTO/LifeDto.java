package DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LifeDto{
    public String food;
    public int calouries;

    public void contruct(String food, int calouries){
        this.food = food;
        this.calouries = calouries;
    }
}
