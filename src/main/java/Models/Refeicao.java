package Models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
@Getter
@Setter
public class Refeicao {

        private String name;
        private LocalTime hour;
        private int calouries;

        public Refeicao(String name, LocalTime hour, int calouries) {
            this.name = name;
            this.hour = hour;
            this.calouries = calouries;
        }


    }

