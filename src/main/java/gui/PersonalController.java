package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class PersonalController {

    public PersonalController(){

    }

    @FXML
    private Button logout;

    public void userLogOut(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        stage = (Stage) logout.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("TelaLogin.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
