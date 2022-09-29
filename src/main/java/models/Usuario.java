package models;

import negocio.ServidorAcademia;

import java.time.LocalDate;
import java.util.Random;

public abstract class Usuario {

    protected String id;
    private String nome;

    private String email;
    private String senha;
    private LocalDate dtNascimento;
    private boolean logado;

    public Usuario(String nome, String email, String senha, LocalDate dtNascimento) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dtNascimento = dtNascimento;
        this.logado = false;
    }

    public String getEmail() { return email; }

    public String getSenha() { return senha; }

    public String getNome() {
        return nome;
    }

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return this.nome;
    }

    public String infoUsuario() {
        return "Usuario" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", dtNascimento=" + dtNascimento +
                ", logado=" + logado;
    }

    public static int randInt(int min, int max) {


        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}