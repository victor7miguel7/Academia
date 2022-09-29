package gui;

import exception.ElementoNaoExisteException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.*;
import negocio.ServidorAcademia;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;



public class PersonalController {
    ServidorAcademia servidor = ServidorAcademia.getInstance();
    @FXML
    private Label nomeTitulo;
    @FXML
    private Label id;
    @FXML
    private Label cref;
    @FXML
    private Label nascimento;
    @FXML
    private Button btnCadExercicio;
    @FXML
    private Button remover;
    @FXML
    private Button btnCadPlano;

    @FXML
    private TableView<Exercicio> tableViewExercicio;
    @FXML
    private TableColumn<Exercicio, String> columnNome;
    @FXML
    private TableColumn<Exercicio, String> columnTipo;
    private List<Exercicio> listExercicios;
    private ObservableList<Exercicio> observableListExercicio;


    // aba plano de treino - plano de treino
    @FXML
    private TableView<PlanoDeTreino> tableViewPlanoDeTreino;
    @FXML
    private TableColumn<PlanoDeTreino, String> columnCliente;
    @FXML
    private TableColumn<PlanoDeTreino, LocalDate> columnInicio;
    @FXML
    private TableColumn<PlanoDeTreino, Period> columnDuracao;
    private List<PlanoDeTreino> listPlanosDeTreino;

    // aba plano de treino - treinos
    @FXML
    private TableView<PlanoDeTreino> tableViewTreinos;
    @FXML
    private TableColumn<Treino, String> treino;
    private ObservableList<String> observableListCliente;
    private ObservableList<Exercicio> observableListTreino;

    @FXML
    private ListView<String> listViewTreinos;
    @FXML
    private ListView<Exercicio> listViewExercicios;

    @FXML
    private Label aviso;

    @FXML
    private Button logout;


    public void initialize() {
        PersonalTrainer personal = (PersonalTrainer) logarPersonal();
        id.setText(String.valueOf((personal.getId())));
        nomeTitulo.setText(String.valueOf(personal.getNome()));
        cref.setText(String.valueOf(personal.getCref()));
        nascimento.setText(String.valueOf(personal.getDtNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));

        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        carregarExercicios();

        columnInicio.setCellValueFactory(new PropertyValueFactory<>("dataInicio"));
        columnDuracao.setCellValueFactory(new PropertyValueFactory<>("duracao"));
        columnCliente.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));

        listPlanosDeTreino = servidor.planoTreinoListar();
        ObservableList<PlanoDeTreino> observableListPlanosDeTreinos = FXCollections.observableArrayList(listPlanosDeTreino);
        this.tableViewPlanoDeTreino.setItems(observableListPlanosDeTreinos);
    }

    public void userLogOut(ActionEvent event) throws IOException {
        PersonalTrainer personal = (PersonalTrainer) logarPersonal();
        personal.setLogado(false);
        Stage stage;
        Parent root;

        stage = (Stage) logout.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onBtnCadExercicio(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        stage = (Stage) btnCadExercicio.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("cadExercicio.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onBtnCadTreino(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        stage = (Stage) btnCadExercicio.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("cadTreino.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public Usuario logarPersonal() {
        Usuario usuario = null;
        List<Usuario> usuarios = servidor.usuarioListar();
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).isLogado()) {
                usuario = usuarios.get(i);
            }
        }
        return usuario;
    }

    public void carregarExercicios() {

        listExercicios = servidor.exercicioListar();
        observableListExercicio = FXCollections.observableArrayList(listExercicios);
        this.tableViewExercicio.setItems(observableListExercicio);

    }

    @FXML
    public void onRemoverExercicio() throws ElementoNaoExisteException  {

        try{
            Exercicio exercicio = tableViewExercicio.getSelectionModel().getSelectedItem();
            tableViewExercicio.getItems().remove(exercicio);
            servidor.remover(exercicio);
        } catch (Exception e) {
            //escrever
        }
    }
        private PlanoDeTreino planoSelecionado;

        @FXML
        public void clicarMouseItemTableViewPlanoTreino () {
            try {
                planoSelecionado = tableViewPlanoDeTreino.getSelectionModel().getSelectedItem();
                List<String> listTreinos = new ArrayList<>();
                for (int i = 0; i < planoSelecionado.getTreinos().size(); i++) {
                    listTreinos.add(planoSelecionado.getTreinos().get(i).getTipo());
                }
                observableListCliente = FXCollections.observableArrayList(listTreinos);
                listViewTreinos.setItems(observableListCliente);
                listViewExercicios.setItems(null);
            } catch (Exception e) {
            }
        }

        @FXML
        public void clicarMouseItemListViewTreinos () {
            try {
                String treinoSelec = listViewTreinos.getSelectionModel().getSelectedItem();
                List<Exercicio> listExercicios = new ArrayList<>();

                for (int i = 0; i < planoSelecionado.getTreinos().size(); i++) {
                    if (planoSelecionado.getTreinos().get(i).getTipo() == treinoSelec) {
                        listExercicios = planoSelecionado.getTreinos().get(i).getExercicios();
                    }
                }
                observableListTreino = FXCollections.observableArrayList(listExercicios);
                listViewExercicios.setItems(observableListTreino);

            } catch (Exception e) {
            }
        }

        @FXML
        public void onRemoverTreino () {

            try {
                String treino = listViewTreinos.getSelectionModel().getSelectedItem();

                for (int i = 0; i < planoSelecionado.getTreinos().size(); i++) {

                    if (planoSelecionado.getTreinos().get(i).getTipo() == treino) {

                        listViewTreinos.getItems().remove(planoSelecionado.getTreinos().get(i).getTipo());
                        servidor.remover(planoSelecionado.getTreinos().get(i));
                        listViewExercicios.setItems(null);
                    }
                }

            } catch (Exception e) {

            }
        }
    public void onBtnCadPlano(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        stage = (Stage) btnCadPlano.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("cadPlanoDeTreinos.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    }