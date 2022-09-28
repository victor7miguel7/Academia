package models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PlanoDeTreino implements Serializable{

    private static final long serialVersionUID = 4967484128939616324L;

    private LocalDate dataInicio;
    private Period duracao;
    private Cliente cliente;
    private String nomeCliente;
    private List<Treino> treinos;
    private List<String> nomeTreino;

    public PlanoDeTreino(LocalDate dataInicio, Period duracao, Cliente cliente, List<Treino> treinos) {
        this.dataInicio = dataInicio;
        this.duracao = duracao;
        this.cliente = cliente;
        this.treinos = treinos;
    }

    public PlanoDeTreino(LocalDate dataInicio, Period duracao, Cliente cliente) {
        this.dataInicio = dataInicio;
        this.duracao = duracao;
        this.cliente = cliente;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public Period getDuracao() {
        return duracao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Treino> getTreinos() {
        return treinos;
    }

    public String getNomeCliente() {

        return cliente.getNome();
    }


    public String toString() {
        return "\nPlanoDeTreino de " + cliente + "\nData de Inicio:" + dataInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                "\nDuracao: " + duracao.getDays() + " dias"  + treinos;
    }

    public String infoPlano() {
        return  "" +treinos;
    }

}