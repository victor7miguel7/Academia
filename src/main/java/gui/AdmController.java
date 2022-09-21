package gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import models.Administrador;
import models.*;

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

    Usuario adm = new Administrador("13","Lula","lulinha@gmail.com","lulinha123", LocalDate.of(1945,10,07));


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblID.setText(adm.getId());
        lblNome.setText(adm.getNome());
        lblDtNascimento.setText(String.valueOf(adm.getDtNascimento()));
    }
}
