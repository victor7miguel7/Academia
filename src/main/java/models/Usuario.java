package models;

import java.time.LocalDate;

public abstract class Usuario {

    private String id;
    private String nome;

    private String email;
    private String senha;
    private LocalDate dtNascimento;
    private boolean logado;

    public Usuario(String id, String nome, String email, String senha, LocalDate dtNascimento) {
        this.id = id;
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
    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
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
}