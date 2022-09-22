package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.Cliente;
import models.PersonalTrainer;
import models.Usuario;
import negocio.ServidorAcademia;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;



public class PersonalController {

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

    private String emailLogado;

    public PersonalController(){

    }

    @FXML
    private Button logout;

//    public void recebendo(String recebe) {
//        emailLogado = recebe;
//    }
    public void pegarDados(String nome){
        emailLogado = nome;
    }

    public void initialize() {
        List<Usuario> usuarios = ServidorAcademia.getInstance().usuarioListar();
        List<PersonalTrainer> personais = new ArrayList<>();


        for(int i = 0; i < usuarios.size(); i++){
            if(usuarios.get(i) instanceof PersonalTrainer){
                personais.add((PersonalTrainer) usuarios.get(i));
            }
        }

        for(int i = 0; i < personais.size(); i++){
            if (personais.get(i).getEmail().equals(emailLogado)) {
                nomeTitulo.setText(personais.get(i).getNome());
                id.setText(personais.get(i).getId());
                cref.setText(personais.get(i).getCref());
                nascimento.setText(personais.get(i).getDtNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            }
        }

    }

    public void userLogOut(ActionEvent event) throws IOException {
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
}
