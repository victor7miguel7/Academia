package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CadastroController {

    ObservableList<String> generoList = FXCollections.observableArrayList("Feminino", "Masculino", "Outro");

    @FXML
    private Button cadastro;
    @FXML
    private Button voltar;
    @FXML
    private AnchorPane paneCadastro;
    @FXML
    private Label senhasDif;
    @FXML
    private TextField dataNascimento;
    @FXML
    private TextField peso;
    @FXML
    private TextField altura;
    @FXML
    private TextField email;
    @FXML
    private PasswordField senha;
    @FXML
    private PasswordField confirmacaoSenha;
    @FXML
    private ChoiceBox generoBox;

    @FXML
    private void initialize(){
        generoBox.setItems(generoList);
    }

    @FXML
    public void voltarLogin(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        stage = (Stage) voltar.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
