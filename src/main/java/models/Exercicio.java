package models;

import java.io.Serializable;
import java.time.Duration;

public class Exercicio implements Serializable{

    private static final long serialVersionUID = -3996100207426183192L;

    private String nome;
    private String tipo;

    public Exercicio(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "\nExercicio: " + nome + ", tipo: " + tipo;
    }
}