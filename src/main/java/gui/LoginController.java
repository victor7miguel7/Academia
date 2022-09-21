package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import models.Administrador;
import models.Cliente;
import models.PersonalTrainer;
import models.Usuario;
import negocio.ServidorAcademia;

import java.io.IOException;
import java.util.List;

public class LoginController {

    public LoginController() {

    }

    @FXML
    private Button cadastro;
    @FXML
    private AnchorPane paneAcademia;
    @FXML
    private Label wrongLogIn;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    public void userLogIn(ActionEvent event) throws IOException {
        checkLogin();

    }


    public void telaCadastro(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        stage = (Stage) cadastro.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("cadastro.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    private void checkLogin() throws IOException {
        Application m = new Application();

        String nome = username.getText().toString();
        String senha = password.getText().toString();
        List<Usuario> usuarios = ServidorAcademia.getInstance().usuarioListar();

        for(int i = 0; i < usuarios.size(); i++){

            if(usuarios.get(i) instanceof Cliente) {
                if (usuarios.get(i).getEmail().equals(nome) && usuarios.get(i).getSenha().equals(senha)) {
                    m.changeScene("cliente.fxml");
                }
            }
            else if(usuarios.get(i) instanceof Administrador) {
                if (usuarios.get(i).getEmail().equals(nome) && usuarios.get(i).getSenha().equals(senha)) {
                    m.changeScene("adm.fxml");
                }
            }
            else if(usuarios.get(i) instanceof PersonalTrainer) {
                if (usuarios.get(i).getEmail().equals(nome) && usuarios.get(i).getSenha().equals(senha)) {
                    m.changeScene("personal.fxml");
                }
            }
        }

//        if(username.getText().toString().equals("maria") && password.getText().toString().equals("123")) {
//
//            m.changeScene("cliente.fxml");
//        }
//        Login login = new Login(username.getText().toString(), password.getText().toString());
//        if(ServidorAcademia.getInstance().validarLogin(login)) {
//        	wrongLogIn.setText("Login realizado");
//            m.changeScene("cliente.fxml");
//        }

        if(username.getText().isEmpty() && password.getText().isEmpty()) {
            wrongLogIn.setText("Por favor, preencha os espaços em branco.");
        }

        else {
            wrongLogIn.setText("Login ou senha incorretos!");
        }
    }
}