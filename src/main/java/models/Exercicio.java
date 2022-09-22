package models;

import java.io.Serializable;
import java.time.Duration;

public class Exercicio implements Serializable{

    private static final long serialVersionUID = -3996100207426183192L;

    private String nome;
    private String tipo;
    private Duration intervalo;

    private int qtdDeSeries;

    private int qtdDeRepeticao;

    public Exercicio(String nome, String tipo, Duration intervalo, int qtdDeSeries, int qtdDeRepeticao) {
        super();
        this.nome = nome;
        this.tipo = tipo;
        this.intervalo = intervalo;
        this.qtdDeSeries = qtdDeSeries;
        this.qtdDeRepeticao = qtdDeRepeticao;
    }

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

    public Duration getIntervalo() {
        return intervalo;
    }

    public int getQtdDeSeries() {
        return qtdDeSeries;
    }

    public int getQtdDeRepeticao() {
        return qtdDeRepeticao;
    }

    @Override
    public String toString() {
        return "\nExercicio: " + nome + ", tipo: " + tipo + ", intervalo: " + String.format("%02d min", intervalo.getSeconds()/60)
                + ", séries: " + qtdDeSeries + ", repetições: " + qtdDeRepeticao;
    }
}