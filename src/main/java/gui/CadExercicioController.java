package gui;

import exception.ElementoJaExisteException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Cliente;
import models.Exercicio;
import models.Usuario;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;

import static gui.Application.servidor;

public class CadExercicioController implements Initializable {

    @FXML
    private TextField txtNome;
    @FXML
    private ChoiceBox<String> tipoBox;
    @FXML
    private Spinner <Integer> serieSpinner;
    @FXML
    private Spinner <Integer> repeticaoSpinner;
    @FXML
    private Spinner <Integer> intervaloSpinner;

    private String [] tipoExercicio = {"Costas", "Peito", "Bíceps", "Tríceps", "Ombro",
            "Quadríceps", "Glúteos", "Posterior", "Panturrilha"};
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnVoltar;
    @FXML
    private Label aviso;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tipoBox.getItems().addAll(tipoExercicio);

        SpinnerValueFactory<Integer> valueFactoryRepeticao = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,10);
        SpinnerValueFactory<Integer> valueFactorySerie = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,10);
        SpinnerValueFactory<Integer> valueFactoryIntervalo = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,5);

        valueFactoryIntervalo.setValue(1);
        valueFactoryRepeticao.setValue(1);
        valueFactorySerie.setValue(1);

        intervaloSpinner.setValueFactory(valueFactoryIntervalo);
        serieSpinner.setValueFactory(valueFactorySerie);
        repeticaoSpinner.setValueFactory(valueFactoryRepeticao);

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

    @FXML
    public void onBtnCadastrar(ActionEvent event) throws IOException {

        String nome = txtNome.getText();
        String tipo = tipoBox.getValue();
        int serie = serieSpinner.getValue();
        int repeticao = repeticaoSpinner.getValue();
        Duration intervalo = Duration.ofMinutes(intervaloSpinner.getValue());

            Exercicio exercicio = new Exercicio(nome, tipo, serie, repeticao, intervalo);

            try {
                servidor.inserir(exercicio);
                aviso.setText("Cadastro realizado!");
            } catch (ElementoJaExisteException e) {
                aviso.setText("O exercício já existe.");
                System.out.println("Exercício já cadastrado");
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Erro no cadastro");
                alerta.setHeaderText("Cadastro já realizado");
                alerta.setContentText("Esse exercício já foi cadastrado");
                alerta.showAndWait();
                throw new RuntimeException(e);
            }
    }
}
