package gui;

import exception.ElementoNaoExisteException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Administrador;
import models.*;
import negocio.ServidorAcademia;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdmController implements Initializable {
    ServidorAcademia servidor = ServidorAcademia.getInstance();
    @FXML private Label lblID;
    @FXML private Label lblDtNascimento;
    @FXML private Label lblNome;
    @FXML private Button butCadPersonal;
    @FXML private Button butSair;
    @FXML private Label infoPersonal;
    @FXML private Label infoCliente;
    @FXML private ListView<Usuario> listViewPersonal;
    @FXML private ListView<Usuario> listViewCliente;
    @FXML private Button buttonRemover;
    @FXML private TableView<Pagamento> tableViewPagamento;
    @FXML private TableColumn<Pagamento, Double> columnValor;
    @FXML private TableColumn<Pagamento, LocalDate> columnDtPagamento;
    @FXML private TableColumn<Pagamento, String> columnClientes;

    private List<Pagamento> listPagamentos;
    private ObservableList<Exercicio> observableListPagamentos;

    private List<Usuario> listPersonal = new ArrayList<>();
    private List<Usuario> listClientes = new ArrayList<>();

    private ObservableList<Usuario> observableListPersonal;
    private ObservableList<Usuario> observableListCliente;



    Usuario adm = logarCliente();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblID.setText(adm.getId());
        lblNome.setText(adm.getNome());
        lblDtNascimento.setText(String.valueOf(adm.getDtNascimento()));

        carregarListaPersonal();
        carregarListaCliente();

        columnDtPagamento.setCellValueFactory(new PropertyValueFactory<>("dtPagamento"));
        columnValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        columnClientes.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        carregarPagamentos();

    }
    public void voltarLogin(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        stage = (Stage) butSair.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void cadPersonal(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        stage = (Stage) butCadPersonal.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("cadPersonal.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void carregarListaPersonal(){
        List<Usuario> usuario = servidor.usuarioListar();
        for (int i = 0; i< usuario.size(); i++){
            if( usuario.get(i) instanceof PersonalTrainer){
                listPersonal.add(usuario.get(i));
            }
        }
        observableListPersonal = FXCollections.observableArrayList(listPersonal);
        listViewPersonal.setItems(observableListPersonal);
    }
    @FXML
    public void clicarMouseItemListViewPersonal(){
       try{
           Usuario personal = listViewPersonal.getSelectionModel().getSelectedItem();
           infoPersonal.setText(personal.infoUsuario());
       } catch (Exception e) {
            //escrever
       }
    }

    @FXML
    public void clicarMouseItemListViewCliente(){
        try{
            Usuario cliente = listViewCliente.getSelectionModel().getSelectedItem();
            infoCliente.setText(cliente.infoUsuario());
        } catch (Exception e) {
            //escrever
        }
    }
    @FXML
    public void removerPersonal() throws ElementoNaoExisteException {

        try{
            Usuario usuario = listViewPersonal.getSelectionModel().getSelectedItem();
            infoPersonal.setText("");
            listViewPersonal.getItems().remove(usuario);
            //servidor.remover(usuario);
        } catch (Exception e) {
            //escrever
        }
    }
    @FXML
    public void removerCliente() throws ElementoNaoExisteException {

        try{
            Usuario usuario = listViewCliente.getSelectionModel().getSelectedItem();
            infoCliente.setText("");
            listViewCliente.getItems().remove(usuario);
            //servidor.remover(usuario);
        } catch (Exception e) {
            //escrever
        }
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

    public void carregarPagamentos(){

        listPagamentos = servidor.pagamentolistar();
        ObservableList<Pagamento> observableListPagamentos = FXCollections.observableArrayList(listPagamentos);
        this.tableViewPagamento.setItems(observableListPagamentos);

    }
    public Usuario logarCliente(){
        Usuario usuario = null;
        List<Usuario> usuarios = servidor.usuarioListar();
        for(int i = 0; i < usuarios.size(); i++){
            if(usuarios.get(i).isLogado()){
                usuario = usuarios.get(i);
            }
        }
        return usuario;
    }
}
