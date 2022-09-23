package gui;

import dados.IRepositorioGenerico;
import dados.RepositorioGenerico;
import exception.ElementoJaExisteException;
import javafx.css.Size;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtDataNascimento;
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
//    @FXML
//    private Label lblPersonalExiste;

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
        String nome;
        String email;
        String cref;
        LocalDate dataNascimento;
        String senha;
        String iD;
        String confirmarSenha;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        nome = txtNome.getText();
        email =  txtEmail.getText();
        cref = txtCref.getText();
        senha =  txtSenha.getText();
        confirmarSenha = txtConfirmarSenha.getText();
        dataNascimento =   LocalDate.parse(txtDataNascimento.getText(),formatter);
        iD = txtID.getText();


       if(senha.equals(confirmarSenha)) {

           Usuario personal = new PersonalTrainer(iD, cref, nome, email, senha,dataNascimento);

            try {
                lblPersonalCadastrado.setText("Personal cadastrado!");
                servidor.inserir(personal);
                onBtnLimpar();
            }catch (ElementoJaExisteException e){
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Erro no cadastro");
                alerta.setHeaderText("cadastro já realizado anteriormente");
                alerta.setContentText("Esse cadastro já foi realizado anteriormente");
                alerta.showAndWait();
             //   lblPersonalExiste.setText("Personal já existe");
                throw new RuntimeException(e);



            }


       }
       else if(txtNome.getText().isEmpty() | txtID.getText().isEmpty() | txtSenha.getText().isEmpty() | txtCref.getText().isEmpty() | txtEmail.getText().isEmpty() | txtConfirmarSenha.getText().isEmpty() | txtDataNascimento.getText().isEmpty()){
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
        txtID.setText("");
        txtSenha.setText("");
        txtConfirmarSenha.setText("");
        txtDataNascimento.setText("");
        btnCadastro.setDisable(true);
        btnLimpar.setDisable(true);



    }

    public void onKeyReleased () {
       boolean cadastrar;
       boolean limpar;

        cadastrar =(txtNome.getText().isEmpty() | txtID.getText().isEmpty() | txtSenha.getText().isEmpty() | txtCref.getText().isEmpty() | txtEmail.getText().isEmpty() | txtConfirmarSenha.getText().isEmpty() | txtDataNascimento.getText().isEmpty() );
        btnCadastro.setDisable(cadastrar);

        limpar =(txtNome.getText().isEmpty() & txtSenha.getText().isEmpty() & txtEmail.getText().isEmpty() & txtID.getText().isEmpty() & txtCref.getText().isEmpty() & txtConfirmarSenha.getText().isEmpty() & txtDataNascimento.getText().isEmpty());
        btnLimpar.setDisable(limpar);


    }


}
