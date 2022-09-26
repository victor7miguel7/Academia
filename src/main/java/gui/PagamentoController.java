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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Cliente;
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
    @FXML private Label lblPagamentoCadastrado;
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
    public void onRealizarPagamento(ActionEvent event) throws IOException {
        String numero = txtNumero.getText().toString();
        String nome = txtNome.getText().toString();
        valorMes = mes.getValue();
        valorAno = ano.getValue();
        String cvv = txtCVV.getText().toString();

        //Usuario cliente = new Cliente("54", "Maria", "F", "maria@gmail.com", "m12345", LocalDate.of(1994, 7, 2), "80", "1.63");

        Usuario cliente = this.logarCliente();
        Pagamento pagamento = new Pagamento((Cliente) cliente, nome, numero, cvv);

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
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Pagamento");
        alerta.setHeaderText("Pagamento realizado com sucesso!");
        alerta.setContentText("Seu pagamento foi realizado. Acesse a aba pagamentos para mais informações.");
        alerta.showAndWait();
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        //ScreenManager.getInstance().getClienteController();
        this.onVoltar();

    }

    public void onVoltar() throws IOException {
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

        pagar=(txtNome.getText().isEmpty() | txtNumero.getText().isEmpty() | txtNumero.getText().length() != 16
                | txtCVV.getText().isEmpty() | txtCVV.getText().length() != 3);
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

        public Usuario logarCliente(){
        Usuario usuario = null;
        List<Usuario> usuarios = servidor.usuarioListar();
        for(int i = 0; i < usuarios.size(); i++){
            if(usuarios.get(i).isLogado()){
                usuario = usuarios.get(i);
            }
        }
        return usuario;
    }
}
