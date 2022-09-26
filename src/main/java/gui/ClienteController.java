package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.*;
import negocio.ServidorAcademia;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ClienteController {

    @FXML private Button logout;
    @FXML private Button btnPagar;
    @FXML private Button btnAtualizar;
    @FXML private Label lblID;
    @FXML private Label lblDtNascimento;
    @FXML private Label lblNome;
    @FXML private Label lblPeso;
    @FXML private Label lblAltura;
    @FXML private Label lblGenero;
    @FXML private TableView<Exercicio> tableViewExercicios;
    @FXML private TableView<Treino> tableViewTreinos;
    @FXML private TableColumn<Treino, String> columnTreinosTipo;
    @FXML private TableColumn<Exercicio, String> columnExerciciosNome;
    @FXML private TableColumn<Exercicio, String> columnExerciciosTipo;
    @FXML private TableColumn<Exercicio, Duration> columnExerciciosIntervalo;
    @FXML private TableColumn<Exercicio, Integer> columnExerciciosSeries;
    @FXML private TableColumn<Exercicio, Integer> columnExerciciosRepeticoes;
    @FXML private TableView<Pagamento> tableViewPagamento;
    @FXML private TableColumn<Pagamento, Double> columnValor;
    @FXML private TableColumn<Pagamento, LocalDate> columnDtPagamento;

    ServidorAcademia servidor = ServidorAcademia.getInstance();

    private List<Exercicio> listExercicios = new ArrayList();
    private List<Treino> listTreinos = new ArrayList();
    private List<Pagamento> listPagamentos;
    private ObservableList<Exercicio> observableListExercicios;
    private ObservableList<Treino> observableListTreinos;


    //Login login = new Login("maria", "123");
    //Usuario cliente = new Cliente("32", "Maria Beatriz", "F", "maria@gmail.com", "m12345", LocalDate.of(2000, 2,14),"57","1.57");


    public void initialize() {

        Usuario cliente = logarCliente();
        lblID.setText(String.valueOf((cliente.getId())));
        lblNome.setText(String.valueOf(cliente.getNome()));
        lblPeso.setText(String.valueOf((((Cliente)cliente).getPeso())));
        lblAltura.setText(String.valueOf((((Cliente)cliente).getAltura())));
        lblGenero.setText(String.valueOf((((Cliente) cliente).getGenero())));
        lblDtNascimento.setText(String.valueOf(cliente.getDtNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));

        columnDtPagamento.setCellValueFactory(new PropertyValueFactory<>("dtPagamento"));
        columnValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        this.onBtnAtualizar();
        carregarTableView();

    }
    public void carregarTableView() {

        columnExerciciosNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnExerciciosTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        columnExerciciosIntervalo.setCellValueFactory(new PropertyValueFactory<>("intervalo"));
        columnExerciciosSeries.setCellValueFactory(new PropertyValueFactory<>("qtdDeSeries"));
        columnExerciciosRepeticoes.setCellValueFactory(new PropertyValueFactory<>("qtdDeRepeticao"));
        columnTreinosTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        Exercicio exer1 = new Exercicio("Supino Reto", "Peito");
        Exercicio exer2 = new Exercicio("Puxada Aberta", "Costas");
        Exercicio exer3 = new Exercicio("Tríceps Pulley", "Tríceps");

        listExercicios.add(exer1);
        listExercicios.add(exer2);
        listExercicios.add(exer3);

        listTreinos = servidor.treinos((Cliente) logarCliente());

        observableListExercicios = FXCollections.observableArrayList(listExercicios);
        observableListTreinos = FXCollections.observableArrayList(listTreinos);

        tableViewExercicios.setItems(observableListExercicios);
        tableViewTreinos.setItems(observableListTreinos);
    }

    public void userLogOut(ActionEvent event) throws IOException {
        Usuario cliente = this.logarCliente();
        cliente.setLogado(false);

        Stage stage;
        Parent root;

        stage = (Stage) logout.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void onbtnRealizarPagamento(ActionEvent event) throws IOException {
            Stage stage;
            Parent root;

            stage = (Stage) btnPagar.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("pagamento.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    public void onBtnAtualizar(){

        listPagamentos = servidor.pagamentolistar();
        ObservableList<Pagamento> observableListPagamentos = FXCollections.observableArrayList(listPagamentos);
        this.tableViewPagamento.setItems(observableListPagamentos);

    }

    public Usuario logarCliente(){
        Usuario usuario = null;
        List<Usuario> usuarios = servidor.usuarioListar();
        for(int i = 0; i < usuarios.size(); i++){
            if(usuarios.get(i).isLogado()){
                usuario = usuarios.get(i);
            }
        }
        return usuario;
    }




}
