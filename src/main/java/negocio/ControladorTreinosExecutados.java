package negocio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import dados.IRepositorioGenerico;
import dados.RepositorioGenerico;
import exception.ElementoJaExisteException;
import exception.ElementoNaoExisteException;
import models.Cliente;
import models.TreinoExecutado;
import models.Usuario;


public class ControladorTreinosExecutados {
    private IRepositorioGenerico<TreinoExecutado> repositorioTreinosExecutados;
    private static ControladorTreinosExecutados instance;

    private ControladorTreinosExecutados() {
        this.repositorioTreinosExecutados = new RepositorioGenerico<>("treinosExecutados.dat");
    }

    public static ControladorTreinosExecutados getInstance() {
        if (instance == null) {
            instance = new ControladorTreinosExecutados();
        }
        return instance;
    }

    public void inserir(TreinoExecutado obj) throws ElementoJaExisteException{
        this.repositorioTreinosExecutados.inserir(obj);
    }

    public List<TreinoExecutado> listar() {
        return this.repositorioTreinosExecutados.listar();
    }

    public void remover(TreinoExecutado obj) throws ElementoNaoExisteException{
        this.repositorioTreinosExecutados.remover(obj);
    }

    public void atualizar(TreinoExecutado newObj) throws ElementoNaoExisteException{
        this.repositorioTreinosExecutados.atualizar(newObj);
    }

    public int consultarFrequenciaCliente(Cliente c, LocalDate inicio, LocalDate fim) {
        List<TreinoExecutado> lista = repositorioTreinosExecutados.listar();
        int frequencia = 0;
        for(int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCliente().equals(c) && lista.get(i).getData().isAfter(inicio.minusDays(1))
                    && lista.get(i).getData().isBefore(fim.plusDays(1))) {
                frequencia++;
            }
        }
        return frequencia;
    }

    public int consultarFrequenciaCliente2(Usuario c) {
        List<TreinoExecutado> lista = repositorioTreinosExecutados.listar();

        return lista.size();
    }
    public List<TreinoExecutado> listarFrequenciaCliente(Cliente c) {
        List<TreinoExecutado> lista = repositorioTreinosExecutados.listar();
        List<TreinoExecutado> novaLista = new ArrayList<>();
        for(int i = 0; i < lista.size(); i++) {
            if(lista.get(i).getCliente().equals(c)) {
                novaLista.add(lista.get(i));
            }
        }
        return novaLista;
    }
}