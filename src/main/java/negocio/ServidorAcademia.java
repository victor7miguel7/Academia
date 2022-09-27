package negocio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import exception.ElementoJaExisteException;
import exception.ElementoNaoExisteException;
import models.*;

public class ServidorAcademia {

    private static ServidorAcademia instance;

    private ControladorExercicios controladorExercicios;
    private ControladorUsuarios controladorUsuarios;
    private ControladorPlanoTreinos controladorPlanoTreino;
    private ControladorTreinos controladorTreino;
    private ControladorTreinosExecutados controladorTreinoExecutado;
    private ControladorPagamentos controladorPagamentos;

    private ServidorAcademia() {
        this.controladorExercicios = ControladorExercicios.getInstance();
        this.controladorUsuarios = ControladorUsuarios.getInstance();
        this.controladorPlanoTreino = ControladorPlanoTreinos.getInstance();
        this.controladorTreino = ControladorTreinos.getInstance();
        this.controladorTreinoExecutado = ControladorTreinosExecutados.getInstance();
        this.controladorPagamentos = ControladorPagamentos.getInstance();
    }

    public static ServidorAcademia getInstance() {
        if (instance == null) {
            instance = new ServidorAcademia();
        }
        return instance;
    }

    public void inserir(Exercicio obj) throws ElementoJaExisteException {
        controladorExercicios.inserir(obj);
    }

    public List<Exercicio> exercicioListar() {
        return controladorExercicios.listar();
    }

    public void remover(Exercicio obj) throws ElementoNaoExisteException {
        controladorExercicios.remover(obj);
    }

    public void atualizar(Exercicio newObj) throws ElementoNaoExisteException {
        this.controladorExercicios.atualizar(newObj);
    }

    public void inserir(Usuario obj) throws ElementoJaExisteException {
        controladorUsuarios.inserir(obj);
    }

    public List<Usuario> usuarioListar() {
        return controladorUsuarios.listar();
    }

    public void remover(Usuario obj) throws ElementoNaoExisteException {
        controladorUsuarios.remover(obj);
    }

    public void atualizar(Usuario newObj) throws ElementoNaoExisteException {
        this.controladorUsuarios.atualizar(newObj);
    }

    public void inserir(PlanoDeTreino obj) throws ElementoJaExisteException {
        this.controladorPlanoTreino.inserir(obj);
    }

    public List<PlanoDeTreino> planoTreinoListar() {
        return this.controladorPlanoTreino.listar();
    }
    public List<Treino> treinos(Cliente cliente) {
        List<Treino> treino = new ArrayList<>();
        List<PlanoDeTreino> planoDeTreinos = this.controladorPlanoTreino.listar();

        for (int i = 0; i < planoDeTreinos.size(); i++) {
            if (planoDeTreinos.get(i).getCliente().equals(cliente)) {
                treino = planoDeTreinos.get(i).getTreinos();
            }

        }
        return treino;
    }
    public List<Exercicio> listaExercicio(List<Treino> treino){
        List<Exercicio> exercicio = new ArrayList<>();
        List<PlanoDeTreino> planoDeTreinos = this.controladorPlanoTreino.listar();
        List <Treino> treinos  = treino;
        for(int i = 0;i < treinos.size(); i++){
            for(int a = 0;a < planoDeTreinos.size(); i++){
                for(int b = 0 ; b < planoDeTreinos.get(a).getTreinos().size() ; b++ ){
                    if(planoDeTreinos.get(a).getTreinos().get(b).equals(treino.get(i)))
                        exercicio =  planoDeTreinos.get(a).getTreinos().get(b).getExercicios();
                }
            }

        }
        return exercicio;
    }

    public void remover(PlanoDeTreino obj) throws ElementoNaoExisteException {
        this.controladorPlanoTreino.remover(obj);
    }

    public void atualizar(PlanoDeTreino newObj) throws ElementoNaoExisteException {
        this.controladorPlanoTreino.atualizar(newObj);
    }

    public void inserir(Treino obj) throws ElementoJaExisteException {
        this.controladorTreino.inserir(obj);
    }

    public List<Treino> treinoListar() {
        return this.controladorTreino.listar();
    }

//    public List<Exercicio> listaExercicio(){
//        List<Exercicio> exercicio = new ArrayList<>();
//        List<Treino> treino = this.controladorTreino.listar();
//        for(int i = 0;i < treino.size(); i++){
//            exercicio = treino.get(i).getExercicios();
//        }
//        return exercicio;
//    }

    public void remover(Treino obj) throws ElementoNaoExisteException {
        this.controladorTreino.remover(obj);
    }

    public void atualizar(Treino newObj) throws ElementoNaoExisteException {
        this.controladorTreino.atualizar(newObj);
    }

    public void inserir(TreinoExecutado obj) throws ElementoJaExisteException {
        this.controladorTreinoExecutado.inserir(obj);
    }

    public List<TreinoExecutado> treinoExecutadoListar() {
        return this.controladorTreinoExecutado.listar();
    }

    public void remover(TreinoExecutado obj) throws ElementoNaoExisteException {
        this.controladorTreinoExecutado.remover(obj);
    }

    public void atualizar(TreinoExecutado newObj) throws ElementoNaoExisteException {
        this.controladorTreinoExecutado.atualizar(newObj);
    }
    public void inserir(Pagamento obj) throws ElementoJaExisteException {
        this.controladorPagamentos.inserir(obj);
    }

    public List<Pagamento> pagamentolistar() {
        return this.controladorPagamentos.listar();
    }

    public void remover(Pagamento obj) throws ElementoNaoExisteException {
        this.controladorPagamentos.remover(obj);
    }

    public void atualizar(Pagamento newObj) throws ElementoNaoExisteException {
        this.controladorPagamentos.atualizar(newObj);
    }

    public int consultarFrequenciaCliente(Cliente c, LocalDate inicio, LocalDate fim) {
        List<TreinoExecutado> lista = controladorTreinoExecutado.listar();
        int frequencia = 0;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCliente().equals(c) && lista.get(i).getData().isAfter(inicio.minusDays(1))
                    && lista.get(i).getData().isBefore(fim.plusDays(1))) {
                frequencia++;
            }
        }
        return frequencia;
    }

    public List<TreinoExecutado> listarFrequenciaCliente(Cliente c) {
        List<TreinoExecutado> lista = controladorTreinoExecutado.listar();
        List<TreinoExecutado> novaLista = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCliente().equals(c)) {
                novaLista.add(lista.get(i));
            }
        }
        return novaLista;
    }

    public boolean validarLogin(String email, String senha) {
        boolean igual = false;

        List<Usuario> usuarios = controladorUsuarios.listar();

        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getEmail().equals(email) && usuarios.get(i).getSenha().equals(senha)) {
                (usuarios.get(i)).setLogado(true);
                igual = true;
            }
        }
        return igual;
    }

    public int clienteID(){
        List<Usuario> usuarios = controladorUsuarios.listar();
        List<Cliente> clientes = new ArrayList<>();
        for(int i = 0; i < usuarios.size(); i++){
            if(usuarios.get(i) instanceof Cliente) {
                clientes.add((Cliente) usuarios.get(i));
            }
        }
        return clientes.size();
    }

    public int personalID(){
        List<Usuario> usuarios = controladorUsuarios.listar();
        List<PersonalTrainer> personais = new ArrayList<>();
        for(int i = 0; i < usuarios.size(); i++){
            if(usuarios.get(i) instanceof PersonalTrainer) {
                personais.add((PersonalTrainer) usuarios.get(i));
            }
        }
        return personais.size();
    }
}