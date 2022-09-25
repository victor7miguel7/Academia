package gui;

import exception.ElementoJaExisteException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Pagamento;
import models.Usuario;
import negocio.ServidorAcademia;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class PagamentoController implements Initializable {

    @FXML private TextField txtNumero;
    @FXML private TextField txtNome;
    @FXML private TextField txtCVV;
    @FXML private Spinner<Integer> mes;
    @FXML private Spinner<Integer> ano;
    @FXML private Button btnPagar;
    @FXML private Button voltar;
    @FXML private Button btnLimpar;
    private int valorMes;
    private int valorAno;
    ServidorAcademia servidor = ServidorAcademia.getInstance();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SpinnerValueFactory<Integer> valueFactoryMes = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,12);
        SpinnerValueFactory<Integer> valueFactoryAno = new SpinnerValueFactory.IntegerSpinnerValueFactory(2022,2030);

        valueFactoryMes.setValue(1);
        valueFactoryAno.setValue(2022);

        mes.setValueFactory(valueFactoryMes);
        ano.setValueFactory(valueFactoryAno);
    }
    public void onRealizarPagamento(ActionEvent event) {
        String numero = txtNumero.getText().toString();
        String nome = txtNome.getText().toString();
        valorMes = mes.getValue();
        valorAno = ano.getValue();
        String cvv = txtCVV.getText().toString();

        Pagamento pagamento = new Pagamento(nome, numero, cvv);

        try {
            servidor.inserir(pagamento);
        } catch (ElementoJaExisteException e) {
            System.out.println("Pagamento já realizado");
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro no pagamento");
            alerta.setHeaderText("Pagamento já realizado");
            alerta.setContentText("Esse pagamento já foi realizado");
            alerta.showAndWait();
            throw new RuntimeException(e);
        }
        this.onBtnLimpar();
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        ScreenManager.getInstance().getClienteController();


    }

    public void onVoltar(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        stage = (Stage) voltar.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("cliente.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onKeyReleased () {
        boolean pagar;
        boolean limpar;

        pagar=(txtNome.getText().isEmpty() |txtNumero.getText().isEmpty() | txtCVV.getText().isEmpty());
        btnPagar.setDisable(pagar);
        limpar = (txtNome.getText().isEmpty() & txtNumero.getText().isEmpty() & txtCVV.getText().isEmpty());
        btnLimpar.setDisable(limpar);

    }

    public void onBtnLimpar() {
        txtNome.setText("");
        txtNumero.setText("");
        txtCVV.setText("");
        btnLimpar.setDisable(true);
        btnPagar.setDisable(true);
    }
}
