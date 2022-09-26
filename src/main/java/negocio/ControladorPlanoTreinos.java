package negocio;

import java.util.ArrayList;
import java.util.List;
import dados.IRepositorioGenerico;
import dados.RepositorioGenerico;
import exception.ElementoJaExisteException;
import exception.ElementoNaoExisteException;
import models.Cliente;
import models.PlanoDeTreino;
import models.Treino;

public class ControladorPlanoTreinos {
    private IRepositorioGenerico<PlanoDeTreino> repositorioPlanoDeTreinos;
    private static ControladorPlanoTreinos instance;

    private ControladorPlanoTreinos() {
        this.repositorioPlanoDeTreinos = new RepositorioGenerico<>("planoDeTreinos.dat");
    }

    public static ControladorPlanoTreinos getInstance() {
        if (instance == null) {
            instance = new ControladorPlanoTreinos();
        }
        return instance;
    }

    public void inserir(PlanoDeTreino obj) throws ElementoJaExisteException {
        this.repositorioPlanoDeTreinos.inserir(obj);
    }

    public List<PlanoDeTreino> listar() {
        return this.repositorioPlanoDeTreinos.listar();
    }

    public void remover(PlanoDeTreino obj) throws ElementoNaoExisteException {
        this.repositorioPlanoDeTreinos.remover(obj);
    }

    public void atualizar(PlanoDeTreino newObj) throws ElementoNaoExisteException {
        this.repositorioPlanoDeTreinos.atualizar(newObj);
    }

    public List<Treino> treinos(Cliente cliente) {
        List<Treino> treino = new ArrayList<>();
        List<PlanoDeTreino> planoDeTreinos = this.repositorioPlanoDeTreinos.listar();

        for (int i = 0; i < planoDeTreinos.size(); i++) {
            if (planoDeTreinos.get(i).getCliente().equals(cliente)) {
                treino = planoDeTreinos.get(i).getTreinos();
            }

        }
        return treino;
    }
}
