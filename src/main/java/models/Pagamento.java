package models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pagamento implements Serializable {

    private static final long serialVersionUID = 4864179243205862834L;

    private Cliente cliente;
    private String nomeCliente;
    private String nome;
    private String numero;
    private Double valor;
    private int mes;
    private int ano;
    private String cvv;
    private LocalDate dtPagamento;


    public Pagamento(String nome, String numero, int mes, int ano, String cvv, Double valor, LocalDate dtPagamento) {
        this.nome = nome;
        this.numero = numero;
        this.mes = mes;
        this.ano = ano;
        this.cvv = cvv;
        this.valor = 120.0;
        this.dtPagamento = LocalDate.now();
    }

    public Pagamento(Cliente cliente, String nome, String numero, Double valor) {
        this.cliente = cliente;
        this.nome = nome;
        this.numero = numero;
        this.valor = valor;
        this.dtPagamento = LocalDate.now();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDtPagamento() {
        return dtPagamento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public void setDtPagamento(LocalDate dtPagamento) {
        this.dtPagamento = dtPagamento;
    }

    public String getNomeCliente() {

        return cliente.getNome();
    }

    @Override
    public String toString() {
        return "Pagamento" +
                "nome='" + nome + '\'' +
                ", numero='" + numero + '\'' +
                ", data de Vencimento='" + mes + '/' + ano +
                ", cvv='" + cvv;
    }
}
