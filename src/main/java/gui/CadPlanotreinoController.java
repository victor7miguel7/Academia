package gui;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Exercicio;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CadPlanotreinoController {

@FXML
private Button btnVoltar;
@FXML
private Button btnCadastrar;
@FXML
private Button btnAdicionar;
@FXML private Spinner<Integer> intervalo;
@FXML private Spinner<Integer> series;
@FXML private Spinner<Integer> repeticoes;
@FXML private Spinner<Integer> duracao;
@FXML
private ListView lvTreinos;
@FXML
private ListView<Exercicio> lvExercicios;
@FXML
private ListView lvClientes;
@FXML
private DatePicker dpDataIni;

@FXML
public void onBtnVoltarClick(ActionEvent event) throws IOException {
    Stage stage;
    Parent root;

    stage = (Stage) btnVoltar.getScene().getWindow();
    root = FXMLLoader.load(getClass().getResource("personal.fxml"));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
}
public void onBtnAdicionarClick(ActionEvent event) throws IOException {
}
public void onBtnCadastrarClick(ActionEvent event) throws IOException {
}




}

