package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

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
            FXMLLoader fxmlLoader = new FXMLLoader();
            TabPane admPane = fxmlLoader.load(getClass()
                    .getResource("adm.fxml").openStream());
            this.admScene = new Scene(admPane);
            this.admController = (AdmController) fxmlLoader.getController();

            fxmlLoader = new FXMLLoader();
            GridPane addVooPane = fxmlLoader.load(getClass()
                    .getResource("/br/ufrpe/flight_system/gui/AddVoo.fxml").openStream());
            this.addVooScene = new Scene(addVooPane);
            this.addVooScreenController = (AddVooScreenController) fxmlLoader.getController();

            fxmlLoader = new FXMLLoader();
            BorderPane listaPassageirosPane = fxmlLoader.load(getClass()
                    .getResource("/br/ufrpe/flight_system/gui/ListaPassageiros.fxml").openStream());
            this.listaPassageirosScene = new Scene(listaPassageirosPane);
            this.listaPassageirosPorVooScreenController = (ListaPassageirosPorVooScreenController) fxmlLoader.getController();

            fxmlLoader = new FXMLLoader();
            GridPane addPassageirosPane = fxmlLoader.load(getClass()
                    .getResource("/br/ufrpe/flight_system/gui/AddPassageiro.fxml").openStream());
            this.addPassageirosScene = new Scene(addPassageirosPane);
            this.addPassageiroScreenController = (AddPassageiroScreenController) fxmlLoader.getController();

            fxmlLoader = new FXMLLoader();
            BorderPane emitirBilhetePane = fxmlLoader.load(getClass()
                    .getResource("/br/ufrpe/flight_system/gui/EmitirBilhete.fxml").openStream());
            this.emitirBilheteScene = new Scene(emitirBilhetePane);
            this.emitirBilheteScreenController = (EmitirBilheteScreenController) fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
