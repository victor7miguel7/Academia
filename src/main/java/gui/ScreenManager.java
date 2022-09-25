package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ScreenManager {
    private static ScreenManager instance;
    private Stage primaryStage;

    private Scene admScene;
    private AdmController admController;
    private Scene cadastroScene;
    private CadastroController cadastroController;
    private Scene cadPersonalScene;
    private CadPersonalController cadPersonalController;
    private Scene clienteScene;
    private ClienteController clienteController;
    private Scene loginScene;
    private LoginController loginController;
    private Scene pagamentoScene;
    private PagamentoController pagamentoController;
    private Scene personalScene;
    private PersonalController personalController;

    private ScreenManager() {
        this.initialize();
    }

    public static ScreenManager getInstance() {
        if (instance == null) {
            instance = new ScreenManager();
        }
        return instance;
    }

    private void initialize() {
        try {
//            FXMLLoader fxmlLoader = new FXMLLoader();
//            TabPane admPane = fxmlLoader.load(getClass()
//                    .getResource("adm.fxml").openStream());
//            this.admScene = new Scene(admPane);
//            this.admController = (AdmController) fxmlLoader.getController();
//
//            fxmlLoader = new FXMLLoader();
//            TabPane cadPane = fxmlLoader.load(getClass()
//                    .getResource("cadCliente.fxml").openStream());
//            this.cadastroScene = new Scene(cadPane);
//            this.cadastroController = (CadastroController) fxmlLoader.getController();
//
//            fxmlLoader = new FXMLLoader();
//            TabPane cadPersonalPane = fxmlLoader.load(getClass()
//                    .getResource("cadPersonal.fxml").openStream());
//            this.cadPersonalScene = new Scene(cadPersonalPane);
//            this.cadPersonalController = (CadPersonalController) fxmlLoader.getController();
            Stage primaryStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("cliente.fxml"));
            primaryStage.setTitle("Academia MMRSV");
            primaryStage.setScene(new Scene(root,600,400));
            primaryStage.show();

            Stage primaryStage1 = new Stage();
            FXMLLoader adm = new FXMLLoader();
            Parent root1 = adm.load(getClass().getResource("adm.fxml"));
            primaryStage1.setTitle("Academia MMRSV");
            primaryStage1.setScene(new Scene(root1,600,400));
            primaryStage1.show();

//            TabPane clientePane = fxmlLoader.load(getClass()
//                    .getResource("cliente.fxml").openStream());
//            this.clienteScene = new Scene(clientePane);
//            this.clienteController = (ClienteController) fxmlLoader.getController();

//            fxmlLoader = new FXMLLoader();
//            TabPane loginPane = fxmlLoader.load(getClass()
//                    .getResource("login.fxml").openStream());
//            this.loginScene = new Scene(loginPane);
//            this.loginController = (LoginController) fxmlLoader.getController();
//
//            fxmlLoader = new FXMLLoader();
//            TabPane pagamanetoPane = fxmlLoader.load(getClass()
//                    .getResource("pagamento.fxml").openStream());
//            this.pagamentoScene = new Scene(pagamanetoPane);
//            this.pagamentoController = (PagamentoController) fxmlLoader.getController();
//
//            fxmlLoader = new FXMLLoader();
//            TabPane personalPane = fxmlLoader.load(getClass()
//                    .getResource("personal.fxml").openStream());
//            this.personalScene = new Scene(personalPane);
//            this.personalController = (PersonalController) fxmlLoader.getController();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Scene getAdmScene() {
        return admScene;
    }

    public AdmController getAdmController() {
        return admController;
    }

    public Scene getCadastroScene() {
        return cadastroScene;
    }

    public CadastroController getCadastroController() {
        return cadastroController;
    }

    public Scene getCadPersonalScene() {
        return cadPersonalScene;
    }

    public CadPersonalController getCadPersonalController() {
        return cadPersonalController;
    }

    public Scene getClienteScene() {
        return clienteScene;
    }

    public ClienteController getClienteController() {
        return clienteController;
    }

    public Scene getLoginScene() {
        return loginScene;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public Scene getPagamentoScene() {
        return pagamentoScene;
    }

    public PagamentoController getPagamentoController() {
        return pagamentoController;
    }

    public Scene getPersonalScene() {
        return personalScene;
    }

    public PersonalController getPersonalController() {
        return personalController;
    }
}