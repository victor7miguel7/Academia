package models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class  Cliente extends Usuario implements Serializable{

    private static final long serialVersionUID = 4864028013205862834L;

    private String genero;
    private String peso;
    private String altura;
    private double imc;

    public Cliente(String nome, String genero, String email, String senha, LocalDate dtNascimento,
                   String peso, String altura) {
        super(nome, email, senha, dtNascimento);
        this.genero = genero;
        this.peso = peso;
        this.altura = altura;
        //this.imc = calcularImc();
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public double getImc() {
        return imc;
    }

    public void setImc(double imc) {
        this.imc = imc;
    }

    public String getGenero() {
        return genero;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public int calcularIdade(LocalDate dtNascimento) {
        LocalDate hoje = LocalDate.now();
        int idade = (int) dtNascimento.until(hoje, ChronoUnit.YEARS);
        return idade;
    }

    //    public double calcularImc() {
//        double imc = peso / (altura * altura);
//
//        return imc;
//    }

    @Override
    public String infoUsuario(){
        return "\n  Nome:  " + super.getNome() +
                "\n  ID:  " + super.getId() +
                "\n  Genero:  " + genero +
                "\n  Peso:  " + peso +
                "\n  Altura:  " + altura +
                "\n  Email:  " + super.getEmail() +
                "\n  Data de Nascimento:  " + super.getDtNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public String toString() {
        return super.getNome();
    }
}