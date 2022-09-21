package negocio;

import dados.IRepositorioGenerico;
import dados.RepositorioGenerico;
import exception.ElementoJaExisteException;
import exception.ElementoNaoExisteException;
import models.Pagamento;
import models.Usuario;

import java.util.List;

public class ControladorPagamentos {
    private IRepositorioGenerico<Pagamento> repositorioDePagamentos;
    private static ControladorPagamentos instance;

    private ControladorPagamentos() {
        this.repositorioDePagamentos = new RepositorioGenerico<>("pagamentos.dat");
    }

    public static ControladorPagamentos getInstance() {
        if (instance == null) {
            instance = new ControladorPagamentos();
        }
        return instance;
    }

    public void inserir(Pagamento obj) throws ElementoJaExisteException {
        this.repositorioDePagamentos.inserir(obj);
    }

    public List<Pagamento> listar() {
        return this.repositorioDePagamentos.listar();
    }

    public void remover(Pagamento obj) throws ElementoNaoExisteException {
        this.repositorioDePagamentos.remover(obj);
    }

    public void atualizar(Pagamento newObj) throws ElementoNaoExisteException {
        this.repositorioDePagamentos.atualizar(newObj);
    }
}