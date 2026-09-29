package Models;

import java.time.LocalTime;
public class Refeicao {

        private String nome;
        private LocalTime horario;

        public Refeicao(String nome, LocalTime horario) {
            this.nome = nome;
            this.horario = horario;
        }

        public String getNome() {
            return nome;
        }

        public LocalTime getHorario() {
            return horario;
        }
    }

