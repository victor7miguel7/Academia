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

    public Exercicio(String nome, String tipo, int series, int repeticoes, Duration intervalo) {
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

    public String getIntervalo() {
        return String.format("%02d min", intervalo.getSeconds()/60);
    }

    public int getSeries() {
        return series;
    }

    public int getRepeticoes() {
        return repeticoes;
    }

    public void setIntervalo(Duration intervalo) {
        this.intervalo = intervalo;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public void setRepeticoes(int repeticoes) {
        this.repeticoes = repeticoes;
    }

    @Override
    public String toString() {
        return nome + ",    tipo: " + tipo + ",    intervalo: " + String.format("%02d min", intervalo.getSeconds()/60) + ",    séries: " + series + ",    repetições: " + repeticoes;
    }

}