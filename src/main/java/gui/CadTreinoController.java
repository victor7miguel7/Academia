package gui;
import exception.ElementoJaExisteException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Exercicio;
import javafx.scene.Parent;
import javafx.scene.Scene;
import models.Treino;
import negocio.ServidorAcademia;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
	private ListView<Exercicio> lvExercicios;


	ServidorAcademia servidor = ServidorAcademia.getInstance();

	private List<Exercicio> listExercicios = new ArrayList<>();
	private ObservableList<Exercicio> observableListExercicio;

	private List<Exercicio> listExerciciosSelected = new ArrayList<Exercicio>();
	
	
	public void onBntCadastrarTreino() {
		String tipo = cBTipoTreino.getValue();
		Treino treino = new Treino(tipo, listExerciciosSelected);
		try {
			servidor.inserir(treino);
		} catch (ElementoJaExisteException e) {
			System.out.println("Treino já cadastrado");
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setTitle("Erro no cadastro");
			alerta.setHeaderText("Treino já cadastrado");
			alerta.setContentText("Esse treino já foi cadastrado");
			alerta.showAndWait();
			throw new RuntimeException(e);
		}
	}
	
	 public void onBtnLimparClick() {
	     // lvExercicios.setSelectionModel().setSelectedItems("");
		   // cBTipoTreino.setItems();
	        btnCadastrar.setDisable(true);
	        btnLimpar.setDisable(true); 
	 }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		carregarListaExercicio();

		lvExercicios.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		cBTipoTreino.getItems().addAll(tipo);
	}
	/* public void onKeyReleased () {
	       boolean cadastrar;
	       boolean limpar;

	      cadastrar =(txtTipoTreino.getText().isEmpty() | lvExercicios.getSelectionModel().getSelectedItems());
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

		listExercicios = servidor.exercicioListar();
		observableListExercicio = FXCollections.observableArrayList(listExercicios);
		lvExercicios.setItems(observableListExercicio);

	}
	public void onAddListExercicio(ActionEvent event){
		listExerciciosSelected.add(lvExercicios.getSelectionModel().getSelectedItem());
	}

}


