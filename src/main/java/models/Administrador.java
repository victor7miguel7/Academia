package models;

import java.io.Serializable;
import java.time.LocalDate;

public class Administrador extends Usuario implements Serializable {

    private static final long serialVersionUID = 4864028016396120834L;

    public Administrador(String id, String nome, String email, String senha, LocalDate dtNascimento) {
        super(id, nome, email, senha, dtNascimento);

    }

    @Override
    public String toString() {
        return super.getNome();
    }
}