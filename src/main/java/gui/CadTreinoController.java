package gui;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Exercicio;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import negocio.ServidorAcademia;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CadTreinoController implements Initializable {
	@FXML
	private ChoiceBox<String> cBTipoTreino;
	private String [] tipo = {"Superior","Inferior"};
	@FXML
	private Button btnLimpar;
	@FXML
	private Button btnVoltar;
	@FXML
	private Button btnCadastrar;
	@FXML
	private ListView lvExercicios;
	@FXML
	private ObservableList<Exercicio> observableListExercicio;

	ServidorAcademia servidor = ServidorAcademia.getInstance();

	@FXML
	private List<Exercicio> exercicio = servidor.listar();
	
	
	public void onBntCadastrarClick()
	{
		
	}
	
	 public void onBtnLimparClick() {
	     // lvExercicios.setSelectionModel().setSelectedItems("");
		   // cBTipoTreino.setItems();
	        btnCadastrar.setDisable(true);
	        btnLimpar.setDisable(true); 
	 }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lvExercicios.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		cBTipoTreino.getItems().addAll(tipo);
		carregarListaExercicio();

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
	public void carregarListaExercicio(){

		observableListExercicio = FXCollections.observableArrayList(exercicio);
		lvExercicios.setItems(observableListExercicio);

	}

}


