package models;

import java.io.Serializable;
import java.time.Duration;
import java.util.List;

public class Treino implements Serializable{

    private static final long serialVersionUID = -4377520538484301959L;

    private String tipo;
    private List<Exercicio> exercicios;

    public Treino(String tipo, List<Exercicio> exercicios) {
        this.tipo = tipo;
        this.exercicios = exercicios;
    }

    public String getTipo() {
        return tipo;
    }

    public List<Exercicio> getExercicios() {
        return exercicios;
    }


    @Override
    public String toString() {
        return "\nTreino " + tipo  + "\n" + exercicios;
    }

}