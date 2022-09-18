package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.*;
import negocio.ServidorAcademia;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ClienteController implements Initializable {

    @FXML
    private Button logout;
    @FXML
    private Label lblID;
    @FXML
    private Label lblDtNascimento;
    @FXML
    private Label lblNome;
    @FXML
    private Label lblPeso;
    @FXML
    private Label lblAltura;
    @FXML
    private Label lblGenero;
    @FXML
    private TableView<Exercicio> tableViewExercicios;
    @FXML
    private TableView<Treino> tableViewTreinos;
    @FXML
    private TableColumn<Treino, String> columnTreinosTipo;
    @FXML
    private TableColumn<Exercicio, String> columnExerciciosNome;
    @FXML
    private TableColumn<Exercicio, String> columnExerciciosTipo;
    @FXML
    private TableColumn<Exercicio, Duration> columnExerciciosIntervalo;
    @FXML
    private TableColumn<Exercicio, Integer> columnExerciciosSeries;
    @FXML
    private TableColumn<Exercicio, Integer> columnExerciciosRepeticoes;

    private List<Exercicio> listExercicios = new ArrayList();

    private ObservableList<Exercicio> observableListExercicios;
    Login login = new Login("maria", "123");
    Usuario cliente = new Cliente("32", "Maria Beatriz", "F", login, LocalDate.of(2000, 2,14),57,1.57);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblID.setText(String.valueOf((cliente.getId())));
        lblNome.setText(String.valueOf(cliente.getNome()));
        lblID.setText(String.valueOf((cliente.getId())));
        lblPeso.setText(String.valueOf((((Cliente)cliente).getPeso())));
        lblAltura.setText(String.valueOf((((Cliente)cliente).getAltura())));
        lblGenero.setText(String.valueOf((((Cliente) cliente).getGenero())));
        lblDtNascimento.setText(String.valueOf(cliente.getDtNascimento()));


    }

    public void carregarTableViewClientes() {

        columnExerciciosNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnExerciciosTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        columnExerciciosIntervalo.setCellValueFactory(new PropertyValueFactory<>("intervalo"));
        columnExerciciosSeries.setCellValueFactory(new PropertyValueFactory<>("series"));
        columnExerciciosRepeticoes.setCellValueFactory(new PropertyValueFactory<>("repeticoes"));

        Exercicio exer1 = new Exercicio("Supino Reto", "Peito", Duration.ofMinutes(10), 4, 10);
        Exercicio exer2 = new Exercicio("Puxada Aberta", "Costas", Duration.ofMinutes(15), 4, 10);
        Exercicio exer3 = new Exercicio("Tríceps Pulley", "Tríceps", Duration.ofMinutes(7), 3, 12);

        listExercicios.add(exer1);
        listExercicios.add(exer2);
        listExercicios.add(exer3);

        observableListExercicios = FXCollections.observableArrayList(listExercicios);

        tableViewExercicios.setItems(observableListExercicios);
    }

    public void userLogOut(ActionEvent event) throws IOException {
        Application m = new Application();
        m.changeScene("login.fxml");
    }

    //public  void importarCliente() {

        //Usuario u = ServidorAcademia.getInstance().usuarioListar().get(0);


//        String id = u.getId();
//        String peso =  String.valueOf(((Cliente) u).getPeso());
//        String altura = String.valueOf(((Cliente)u).getAltura());
//        String genero = ((Cliente)u).getGenero();

//        lblID.setText(id);
//        lblPeso.setText(peso);
//        lblAltura.setText(altura);
//        lblGenero.setText(genero);
//}
}
