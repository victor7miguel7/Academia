package gui;

import exception.ElementoJaExisteException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Cliente;
import models.Usuario;
import negocio.ServidorAcademia;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AtualizarDadosController {

    @FXML
    private Button voltarI;
    @FXML
    private Button atualizar;
    @FXML
    private Button btnLimpar;
    @FXML
    private Label aviso;
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

    ServidorAcademia servidor = ServidorAcademia.getInstance();

    @FXML
    public void voltarPerfil() throws IOException {

        Stage stage;
        Parent root;

        stage = (Stage) voltarI.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("cliente.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void buttonAtualizar(ActionEvent event) throws IOException {


        String email = txtemail.getText();
        String peso = txtPeso.getText();
        String altura = txtAltura.getText();
        String senha = txtSenha.getText();
        String confirmarSenha = txtConfirmacaoSenha.getText();

        if(senha.equals(confirmarSenha)) {

            //Usuario cliente = new Cliente(nome, genero, email, senha, dataNascimento, peso, altura);

            List<Usuario> usuarios = servidor.usuarioListar();
            List<Cliente> clientes = new ArrayList<>();

            for (Usuario usuario : usuarios) {
                if (usuario instanceof Cliente) {
                    clientes.add((Cliente) usuario);
                }
            }

            for(int i = 0; i < clientes.size(); i++){
                if(clientes.get(i).isLogado()){
                    clientes.get(i).setEmail(email);
                    clientes.get(i).setPeso(peso);
                    clientes.get(i).setAltura(altura);
                    clientes.get(i).setSenha(senha);
                }
            }


            if(txtSenha.getText().isEmpty() || txtemail.getText().isEmpty() | txtConfirmacaoSenha.getText().isEmpty())
                this.onBtnLimpar();
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Atualização de cadastro");
            alerta.setHeaderText("Atualização realizada com sucesso!");
            alerta.setContentText("Agora você já pode ver seus dados atualizados em sua tela de perfil.");
            alerta.showAndWait();
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
            this.voltarPerfil();
        }
        else{
            aviso.setText("As senhas digitadas são diferentes!");
            txtSenha.setText("");
            txtConfirmacaoSenha.setText("");
        }
    }

    public void onBtnLimpar() {
        txtemail.setText("");
        txtSenha.setText("");
        txtAltura.setText("");
        txtPeso.setText("");
        txtConfirmacaoSenha.setText("");
        atualizar.setDisable(true);
        btnLimpar.setDisable(true);

    }

    public void onKeyReleased () {
        boolean cadastrar;
        boolean limpar;

        cadastrar=(txtemail.getText().isEmpty() | txtSenha.getText().isEmpty() | txtAltura.getText().isEmpty() | txtPeso.getText().isEmpty()
                | txtConfirmacaoSenha.getText().isEmpty());
        atualizar.setDisable(cadastrar);

        limpar = (txtemail.getText().isEmpty() & txtSenha.getText().isEmpty() & txtAltura.getText().isEmpty() & txtPeso.getText().isEmpty()
                & txtConfirmacaoSenha.getText().isEmpty());
        btnLimpar.setDisable(limpar);

    }
}
