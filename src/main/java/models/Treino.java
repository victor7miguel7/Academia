package models;

import java.io.Serializable;
import java.time.Duration;
import java.util.List;

public class Treino implements Serializable{

    private static final long serialVersionUID = -4377520538484301959L;

    private String tipo;

    private Cliente cliente;
    private String clienteNome;
    private List<Exercicio> exercicios;

    public Treino(String tipo, List<Exercicio> exercicios, Cliente cliente) {
        this.tipo = tipo;
        this.exercicios = exercicios;
        this.cliente = cliente;
    }

    public String getTipo() {
        return tipo;
    }

    public List<Exercicio> getExercicios() {
        return exercicios;
    }

    public String getClienteNome() {
        return cliente.getNome();
    }

    @Override
    public String toString() {
        return "\nTreino " + tipo  + "\n" + exercicios;
    }
}