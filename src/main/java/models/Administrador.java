package models;

import java.time.LocalDate;

public class Administrador extends Usuario{

    public Administrador(String id, String nome, String email, String senha, LocalDate dtNascimento) {
        super(id, nome, email, senha, dtNascimento);

    }

}