package gui;

import dados.IRepositorioGenerico;
import dados.RepositorioGenerico;
import exception.ElementoJaExisteException;
import javafx.css.Size;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.PersonalTrainer;
import models.Usuario;
import negocio.ServidorAcademia;
import negocio.ControladorUsuarios;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CadPersonalController {
    @FXML
    private Label lblSenhasDiferentes;
    @FXML
    private TextField txtNome;
    //@FXML
    //private TextField txtDataNascimento;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtCref;
    @FXML
    private TextField txtSenha;
    @FXML
    private Button btnCadastro;
    @FXML
    private Button voltar;
    @FXML
    private Button btnLimpar;
    @FXML
    private TextField txtConfirmarSenha;

    @FXML
    private DatePicker dtDataNascimento;

    @FXML
    private Label lblPersonalCadastrado;

    ServidorAcademia servidor = ServidorAcademia.getInstance();


    public void voltarAdm(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        stage = (Stage) voltar.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("adm.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void buttonCadastrar() throws ElementoJaExisteException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String nome = txtNome.getText();
        String email = txtEmail.getText();
        String cref = txtCref.getText();
        LocalDate dataNascimento = dtDataNascimento.getValue();
        String senha = txtSenha.getText();
        String confirmarSenha = txtConfirmarSenha.getText();

        if(senha.equals(confirmarSenha)) {

            Usuario personal = new PersonalTrainer(cref, nome, email, senha,dataNascimento);

            try {
                servidor.inserir(personal);
                personal.setId(String.valueOf(("p0" + servidor.personalID())));
                lblPersonalCadastrado.setText("Personal cadastrado!");
            }catch (ElementoJaExisteException e){
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Erro no cadastro");
                alerta.setHeaderText("cadastro já realizado anteriormente");
                alerta.setContentText("Esse cadastro já foi realizado anteriormente");
                alerta.showAndWait();
                throw new RuntimeException(e);

            }
        }
        else if(txtNome.getText().isEmpty() | txtSenha.getText().isEmpty() | txtCref.getText().isEmpty() | txtEmail.getText().isEmpty() | txtConfirmarSenha.getText().isEmpty() | dtDataNascimento.getValue() == null){
            lblSenhasDiferentes.setText("Por favor, preencha os espaços em branco!");
        }
        else{
            lblSenhasDiferentes.setText("As senhas digitadas são diferentes!");
            txtSenha.setText("");
            txtConfirmarSenha.setText("");
        }

    }
    public void onBtnLimpar() {
        txtNome.setText("");
        txtCref.setText("");
        txtEmail.setText("");
        txtSenha.setText("");
        txtConfirmarSenha.setText("");
        dtDataNascimento.setValue(null);
        btnCadastro.setDisable(true);
        btnLimpar.setDisable(true);



    }

    public void onKeyReleased () {
        boolean cadastrar;
        boolean limpar;

        cadastrar =(txtNome.getText().isEmpty() | txtSenha.getText().isEmpty() | txtCref.getText().isEmpty() | txtEmail.getText().isEmpty() | txtConfirmarSenha.getText().isEmpty() | dtDataNascimento.getValue() == null);
        btnCadastro.setDisable(cadastrar);

        limpar =(txtNome.getText().isEmpty() & txtSenha.getText().isEmpty() & txtEmail.getText().isEmpty()  & txtCref.getText().isEmpty() & txtConfirmarSenha.getText().isEmpty() & dtDataNascimento.getValue() == null);
        btnLimpar.setDisable(limpar);


    }


}
