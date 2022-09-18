package gui;

import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import java.io.IOException;
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

        if(username.getText().toString().equals("maria") && password.getText().toString().equals("123")) {

            m.changeScene("cliente.fxml");
        }
//        Login login = new Login(username.getText().toString(), password.getText().toString());
//        if(ServidorAcademia.getInstance().validarLogin(login)) {
//        	wrongLogIn.setText("Login realizado");
//            m.changeScene("cliente.fxml");
//        }

        else if(username.getText().isEmpty() && password.getText().isEmpty()) {
            wrongLogIn.setText("Por favor, preencha os espaços em branco.");
        }

        else {
            wrongLogIn.setText("Login ou senha incorretos!");
        }
    }
}