package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CadPersonalController {
    @FXML
    private Label nome;
    @FXML
    private TextField dataNascimento;
    @FXML
    private TextField email;
    @FXML
    private TextField cref;
    @FXML
    private PasswordField senha;
    @FXML
    private Button cadastro;
    @FXML
    private Button voltar;



    public void voltarAdm(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        stage = (Stage) voltar.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("adm.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
