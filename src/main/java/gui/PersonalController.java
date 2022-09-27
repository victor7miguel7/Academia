package gui;

import exception.ElementoNaoExisteException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;



public class PersonalController {
    ServidorAcademia servidor = ServidorAcademia.getInstance();
    @FXML private Label nomeTitulo;
    @FXML private Label id;
    @FXML private Label cref;
    @FXML private Label nascimento;
    @FXML private Button btnCadExercicio;

    @FXML private TableView<Exercicio> tableViewExercicio;
    @FXML private TableColumn<Exercicio, String> columnNome;
    @FXML private TableColumn<Exercicio, String> columnTipo;
    private List<Exercicio> listExercicios;
    private ObservableList<Exercicio> observableListExercicio;



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

    public Usuario logarPersonal(){
        Usuario usuario = null;
        List<Usuario> usuarios = servidor.usuarioListar();
        for(int i = 0; i < usuarios.size(); i++){
            if(usuarios.get(i).isLogado()){
                usuario = usuarios.get(i);
            }
        }
        return usuario;
    }

    public void carregarExercicios(){

        listExercicios = servidor.exercicioListar();
        observableListExercicio = FXCollections.observableArrayList(listExercicios);
        this.tableViewExercicio.setItems(observableListExercicio);

    }

    @FXML
    public void onRemoverExercicio() throws ElementoNaoExisteException {

        try {
            Exercicio exercicio = tableViewExercicio.getSelectionModel().getSelectedItem();
            tableViewExercicio.getItems().remove(exercicio);
            servidor.remover(exercicio);
        } catch (Exception e) {
            //escrever
        }
    }
}
