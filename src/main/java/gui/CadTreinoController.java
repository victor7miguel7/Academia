package gui;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Exercicio;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CadTreinoController implements Initializable {
	@FXML
	private TextField txtTipoTreino;
	@FXML
	private Button btnLimpar;
	@FXML
	private Button btnCadastrar;
	@FXML
	private ListView<Exercicio> lvExercicios;
	@FXML
	private Button btnVoltar;
	
	String [] Exercicios = {"Supino Reto", "Supino Inclinado","Voador Peitoral" };
	
	
	public void onBntCadastrarClick()
	{
		
	}
	
	 public void onBtnLimparClick() {
	      // lvExercicios.setSelectionModel().setSelectedItems("");
	        txtTipoTreino.setText("");
	        btnCadastrar.setDisable(true);
	        btnLimpar.setDisable(true); 
	 }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	/* public void onKeyReleased () {
	       boolean cadastrar;
	       boolean limpar;*/

	      /*cadastrar =(txtTipoTreino.getText().isEmpty() | lvExercicios.getSelectionModel().getSelectedItems());
	       btnCadastrar.setDisable(cadastrar);

	        limpar =(txtTipoTreino.getText().isEmpty() & lvExercicios.getSelectionModel().getSelectedItems());
	       btnLimpar.setDisable(limpar);
	    }*/

	public void onBtnVoltar() throws IOException {
        Stage stage;
        Parent root;

		stage = (Stage) btnVoltar.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("personal.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}


