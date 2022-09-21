package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.Administrador;
import models.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AdmController implements Initializable {

    @FXML
    private Label lblID;
    @FXML
    private Label lblDtNascimento;
    @FXML
    private Label lblNome;

    @FXML
    private Button cadPersonal;
    @FXML
    private Button voltar;

    Usuario adm = new Administrador("13","Lula","lulinha@gmail.com","lulinha123", LocalDate.of(1945,10,07));


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblID.setText(adm.getId());
        lblNome.setText(adm.getNome());
        lblDtNascimento.setText(String.valueOf(adm.getDtNascimento()));
    }
    public void voltarLogin(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        stage = (Stage) voltar.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void cadPersonal(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        stage = (Stage) cadPersonal.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("cadPersonal.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
