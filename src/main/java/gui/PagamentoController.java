package gui;

import exception.ElementoJaExisteException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import models.Pagamento;
import models.Usuario;
import negocio.ServidorAcademia;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class PagamentoController implements Initializable {

    @FXML
    private TextField txtNumero;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtCVV;
    @FXML
    private Spinner<Integer> mes;
    @FXML
    private Spinner<Integer> ano;
    @FXML
    private Button btnPagar;
    private int valorMes;
    private int valorAno;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SpinnerValueFactory<Integer> valueFactoryMes = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,31);
        SpinnerValueFactory<Integer> valueFactoryAno = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,12);

        valueFactoryMes.setValue(1);
        valueFactoryAno.setValue(1);

        mes.setValueFactory(valueFactoryMes);
        ano.setValueFactory(valueFactoryAno);
    }
    public void onRealizarPagamento() throws ElementoJaExisteException {
        String numero = txtNumero.getText().toString();
        String nome = txtNome.getText().toString();
        valorMes = mes.getValue();
        valorAno = ano.getValue();
        String cvv = txtCVV.getText().toString();

        Pagamento pagamento = new Pagamento(nome, numero, valorMes, valorAno, cvv);
        ServidorAcademia.getInstance().inserir(pagamento);
    }
}
