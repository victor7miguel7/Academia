package models;

import java.io.Serializable;
import java.time.Duration;

public class Exercicio implements Serializable{



    private static final long serialVersionUID = -3996100207426183192L;

    private String nome;
    private String tipo;
    private Duration intervalo;
    private int series;
    private int repeticoes;

    public Exercicio(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public Exercicio(String nome, String tipo, Duration intervalo, int series, int repeticoes) {
        this.nome = nome;
        this.tipo = tipo;
        this.intervalo = intervalo;
        this.series = series;
        this.repeticoes = repeticoes;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public Duration getIntervalo() {
        return intervalo;
    }

    public int getSeries() {
        return series;
    }

    public int getRepeticoes() {
        return repeticoes;
    }

    @Override
    public String toString() {
        return nome + ",    tipo: " + tipo + ",    intervalo: " + intervalo + ",    séries: " + series + ",    repetições: " + repeticoes;
    }
}