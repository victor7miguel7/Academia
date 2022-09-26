package gui;
/*import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;


public class CadTreinoController {
	@FXML
	private TextField txtTipoTreino;
	@FXML
	private Button btnLimpar;
	@FXML
	private Button btnCadastrar;
	@FXML
	private ListView lvExercicios;
	
	String [] Exercicios = {"Supino Reto", "Supino Inclinado","Voador Peitoral" };
	
	
	public void onBntCadastrarClick()
	{
		
	}
	
	 public void onBtnLimparClick() {
		
	       // lvExercicios.set("");
	        txtTipoTreino.setText("");
	        btnCadastrar.setDisable(true);
	        btnLimpar.setDisable(true);
		 
	 }
	
	 public void onKeyReleased () {
	       boolean cadastrar;
	       boolean limpar;

	      //  cadastrar =(txtTipoTreino.getText().isEmpty() | lvExercicios.get().isEmpty();
	      //  btnCadastrar.setDisable(cadastrar);

	       // limpar =(txtTipoTreino.getText().isEmpty() & lvExercicios.getText().isEmpty();
	       // btnLimpar.setDisable(limpar);


	    }

	

}*/

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;



public class CadTreinoController implements Initializable{
	@FXML
	private TextField txtTipoTreino;
	@FXML
	private Button btnLimpar;
	@FXML
	private Button btnCadastrar;
	//@FXML
	//private ListView lvExercicios;
	@FXML
	private ListView<String> lvExercicios;
	//preciso criar metodo
	public void onBntCadastrarClick()
	{
		
	}
	
	 public void onBtnLimparClick() {
		//preciso descobrir o set
	       // lvExercicios.set("");
	        txtTipoTreino.setText("");
	        btnCadastrar.setDisable(true);
	        btnLimpar.setDisable(true);
		 
	 }
	
	 public void onKeyReleased () {
	       boolean cadastrar;
	       boolean limpar;
	     //preciso descobrir o get
	      //  cadastrar =(txtTipoTreino.getText().isEmpty() | lvExercicios.get().isEmpty();
	      //  btnCadastrar.setDisable(cadastrar);
	     //preciso descobrir o get
	       // limpar =(txtTipoTreino.getText().isEmpty() & lvExercicios.get().isEmpty();
	       // btnLimpar.setDisable(limpar);


	    }
	//tentando preencher lista
	String[] exercicio = {"Supino Reto","Supino Inclinado","Voador Peitoral"};
	
	String armazenaExercicio;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		lvExercicios.getItems().addAll(exercicio);
		
		lvExercicios.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				
				armazenaExercicio = lvExercicios.getSelectionModel().getSelectedItem();
				
				
				
			}	
		});
	}
}
