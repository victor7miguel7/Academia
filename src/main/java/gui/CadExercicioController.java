package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CadExercicioController implements Initializable {

    ObservableList<String> tipoList = FXCollections.observableArrayList("Costas", "Peito", "Bíceps", "Tríceps", "Ombro",
                                                                        "Quadríceps", "Glúteos", "Posterior", "Panturrilha");
    @FXML
    private TextField txtNome;
    @FXML
    private ChoiceBox tipoBox;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnVoltar;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tipoBox.setItems(tipoList);
    }

    @FXML
    public void onBtnVoltar(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        stage = (Stage) btnVoltar.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("personal.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
