package gui;

import exception.ElementoJaExisteException;
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
import models.Cliente;
import models.PersonalTrainer;
import models.Usuario;
import negocio.ServidorAcademia;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CadastroController {

    ObservableList<String> generoList = FXCollections.observableArrayList("Feminino", "Masculino", "Outro");

    @FXML
    private Button cadastro;
    @FXML
    private Button btnLimpar;
    @FXML
    private Button voltarI;
    @FXML
    private AnchorPane paneCadastro;
    @FXML
    private Label aviso;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtDataNascimento;
    @FXML
    private TextField txtPeso;
    @FXML
    private TextField txtAltura;
    @FXML
    private TextField txtemail;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private PasswordField txtConfirmacaoSenha;
    @FXML
    private ChoiceBox generoBox;

    ServidorAcademia servidor = ServidorAcademia.getInstance();

    @FXML
    private void initialize(){
        generoBox.setItems(generoList);
    }

    @FXML
    public void voltarLogin(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        stage = (Stage) voltarI.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void buttonCadastrar() throws ElementoJaExisteException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String nome = txtNome.getText();
        String email = txtemail.getText();
        String genero = generoBox.getAccessibleText();
        LocalDate dataNascimento = LocalDate.parse(txtDataNascimento.getText(),formatter);
        String peso = txtPeso.getText();
        String altura = txtAltura.getText();
        String senha = txtSenha.getText();
        String confirmarSenha = txtConfirmacaoSenha.getText();
        String iD;

        if(senha.equals(confirmarSenha)) {

            Usuario cliente = new Cliente("50", nome, genero, email, senha, dataNascimento, peso, altura);


            try {
                servidor.inserir(cliente);
                aviso.setText("Cadastro realizado!");
            } catch (ElementoJaExisteException e) {
                aviso.setText("O cliente já existe.");
                System.out.println("Cliente já cadastrado");
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Erro no cadastro");
                alerta.setHeaderText("Cadastro já realizado");
                alerta.setContentText("Esse cliente já foi cadastrado");
                alerta.showAndWait();
                throw new RuntimeException(e);
            }


        }
        else{
            aviso.setText("As senhas digitadas são diferentes!");
            txtSenha.setText("");
            txtConfirmacaoSenha.setText("");
        }

    }

    public void onBtnLimpar() {
        txtNome.setText("");
        txtemail.setText("");
        txtSenha.setText("");
        txtAltura.setText("");
        txtPeso.setText("");
        txtConfirmacaoSenha.setText("");
        txtDataNascimento.setText("");
        cadastro.setDisable(true);
        btnLimpar.setDisable(true);

    }

    public void onKeyReleased () {
        boolean cadastrar;
        boolean limpar;

        cadastrar =(txtNome.getText().isEmpty() | txtSenha.getText().isEmpty() | txtemail.getText().isEmpty() | txtConfirmacaoSenha.getText().isEmpty() | txtDataNascimento.getText().isEmpty() );
        cadastro.setDisable(cadastrar);

        limpar =(txtNome.getText().isEmpty() & txtSenha.getText().isEmpty() & txtemail.getText().isEmpty() & txtAltura.getText().isEmpty() & txtPeso.getText().isEmpty() & txtConfirmacaoSenha.getText().isEmpty() & txtDataNascimento.getText().isEmpty());
        btnLimpar.setDisable(limpar);

    }
}
