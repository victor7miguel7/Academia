package gui;

import exception.ElementoJaExisteException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Cliente;
import models.PlanoDeTreino;
import models.Treino;
import models.Usuario;
import negocio.ServidorAcademia;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CadastroPlano {

    @FXML
    private Button btnVoltar;
    @FXML private Spinner<Integer> duracao;
    @FXML private DatePicker dtInicio;
    @FXML
    private Label aviso;
    @FXML
    private Button salvarTreino;

    @FXML
    private ListView<Treino> lvTreinos;
    @FXML
    private ListView<Cliente> lvClientes;
    @FXML
    private ObservableList<Treino> observableListTreino;
    @FXML
    private ObservableList<Cliente> observableListCliente;
    private Treino treinoSelecionado;
    private Cliente clienteSelecionado;
    private List<Treino> listaTreinos = new ArrayList<>();
    @FXML
    private List<Cliente>  listClientes = new ArrayList<>();
    ServidorAcademia servidor =  ServidorAcademia.getInstance();


    public void initialize() {

        listaTreinos = servidor.treinoListar();
        observableListTreino = FXCollections.observableArrayList(listaTreinos);
        lvTreinos.setItems(observableListTreino);

        List<Usuario> usuario = servidor.usuarioListar();
        for (int i = 0; i< usuario.size(); i++){
            if( usuario.get(i) instanceof Cliente){
                listClientes.add((Cliente) usuario.get(i));
            }
        }

        observableListCliente = FXCollections.observableArrayList(listClientes);
        lvClientes.setItems(observableListCliente);

        SpinnerValueFactory<Integer> valueFactoryDuracao = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,50);

        valueFactoryDuracao.setValue(1);

        duracao.setValueFactory(valueFactoryDuracao);

    }

    public void onBtnVoltarClick(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        stage = (Stage) btnVoltar.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("personal.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void clicarMouseItemListViewTreino () {
        try {
            treinoSelecionado = lvTreinos.getSelectionModel().getSelectedItem();

        } catch (Exception e) {
        }
    }

    public void onBtnAdicionarClick(ActionEvent event) throws IOException {
        listaTreinos.add(lvTreinos.getSelectionModel().getSelectedItem());

    }

    @FXML
    public void clicarMouseItemListViewClientes () {
        try {
            clienteSelecionado = lvClientes.getSelectionModel().getSelectedItem();

        } catch (Exception e) {
        }
    }

    public void onBtnCadastrarClick(ActionEvent event) {

        LocalDate dataInicio = dtInicio.getValue();
        Period tempo = Period.ofDays(duracao.getValue());
        PlanoDeTreino planoDeTreino = new PlanoDeTreino(dataInicio, tempo, clienteSelecionado, listaTreinos);

        try {
            servidor.inserir(planoDeTreino);
            aviso.setText("Cadastro realizado!");
        } catch (ElementoJaExisteException e) {
            aviso.setText("O Plano já existe.");
            System.out.println("Plano de Treino já cadastrado");
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro no cadastro");
            alerta.setHeaderText("Cadastro já realizado");
            alerta.setContentText("Esse Plano de Treino já foi cadastrado");
            alerta.showAndWait();
            throw new RuntimeException(e);
        }
    }
}
