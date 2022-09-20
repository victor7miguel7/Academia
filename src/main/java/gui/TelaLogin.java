package gui;

import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import models.Usuario;
import negocio.ServidorAcademia;

import java.io.IOException;
import java.util.List;

public class TelaLogin {

    public TelaLogin() {

    }

    @FXML
    private Button button;
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



    private void checkLogin() throws IOException {
        Application m = new Application();

        String nome = username.getText().toString();
        String senha = password.getText().toString();
        List<Usuario> usuarios = ServidorAcademia.getInstance().usuarioListar();

        for(int i = 0; i < usuarios.size(); i++){

//            System.out.println(usuarios.get(i).getLogin().getEmail());
            if(usuarios.get(i).getEmail().equals(nome) && usuarios.get(i).getSenha().equals(senha)){
                m.changeScene("cliente.fxml");
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