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
import models.Cliente;
import models.Exercicio;
import javafx.scene.Parent;
import javafx.scene.Scene;
import models.Treino;
import models.Usuario;
import negocio.ServidorAcademia;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CadTreinoController implements Initializable {

	ServidorAcademia servidor = ServidorAcademia.getInstance();
	@FXML private ChoiceBox<String> cBTipoTreino;
	private String [] tipo = {"Superior","Inferior"};
	@FXML private Button btnLimpar;
	@FXML private Button btnVoltar;
	@FXML private Button btnCadastrar;
	@FXML private ListView<Usuario> listViewCliente;
	@FXML private ListView<Exercicio> lvExercicios;

	private List<Exercicio> listExercicios = new ArrayList<>();
	private ObservableList<Exercicio> observableListExercicio;
	private List<Exercicio> listExerciciosSelected = new ArrayList<>();

	private List<Usuario> listClientes = new ArrayList<>();
	private ObservableList<Usuario> observableListCliente;

	public void onBntCadastrarTreino() {
		String tipo = cBTipoTreino.getValue();
		Usuario cliente = listViewCliente.getSelectionModel().getSelectedItem();
		Treino treino = new Treino(tipo, listExerciciosSelected, (Cliente) cliente);
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
		carregarListaCliente();

		lvExercicios.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		cBTipoTreino.getItems().addAll(tipo);
	}
	 public void onKeyReleased() {
//	       boolean cadastrar;
//		   boolean addExercicio;
//
//	      cadastrar =(cBTipoTreino.getItems().isEmpty() | lvExercicios.getSelectionModel().getSelectedItems().isEmpty() |
//				  		listViewCliente.getSelectionModel().getSelectedItems().isEmpty());
//	       btnCadastrar.setDisable(cadastrar);
//
//		 addExercicio =(cBTipoTreino.isPressed() & lvExercicios.getSelectionModel().getSelectedItems().contains());
//		 btnCadastrar.setDisable(addExercicio);
	    }

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

	public void carregarListaCliente(){
		List<Usuario> usuario = servidor.usuarioListar();
		for (int i = 0; i< usuario.size(); i++){
			if( usuario.get(i) instanceof Cliente){
				listClientes.add(usuario.get(i));
			}
		}
		observableListCliente = FXCollections.observableArrayList(listClientes);
		listViewCliente.setItems(observableListCliente);
	}

}


