package gui;

import exception.ElementoJaExisteException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.*;
import negocio.ServidorAcademia;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Application extends javafx.application.Application {

    private static Stage stg;
    static ServidorAcademia servidor = ServidorAcademia.getInstance();

    @Override
    public void start(Stage primaryStage) throws Exception{
        stg = primaryStage;
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Academia MMRSV");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }

    public static void main(String[] args) {
        ServidorAcademia servidor = ServidorAcademia.getInstance();


        Usuario personal1 = new PersonalTrainer("23", "João", "joao@gmail.com", "12345", LocalDate.of(2000, 5, 20));
        Usuario personal2 = new PersonalTrainer("25", "Pedro", "pedro@gmail.com", "p12345", LocalDate.of(2000, 5, 20));
        Usuario cliente1 = new Cliente("Maria", "F", "maria@gmail.com", "m12345", LocalDate.of(1994, 7, 2), "80",
                "1.63");
        Usuario cliente2 = new Cliente("Stella", "F", "stella@gmail.com", "m15423", LocalDate.of(1995, 5, 19), "60",
                "1.65");
        Usuario cliente3 = new Cliente("Rafael", "M", "rafael@gmail.com", "m54321", LocalDate.of(2003, 8, 19), "75",
                "1.74");

        Usuario adm1 = new Administrador( "Marina", "marina@gmail.com", "2002", LocalDate.of(2003, 3, 20));

        Exercicio exer1 = new Exercicio("Supino Reto", "Peito", Duration.ofMinutes(10), 4, 10);
        Exercicio exer2 = new Exercicio("Puxada Aberta", "Costas", Duration.ofMinutes(15), 4, 10);
        Exercicio exer3 = new Exercicio("Tríceps Pulley", "Tríceps", Duration.ofMinutes(7), 3, 12);
        Exercicio exer4 = new Exercicio("Agachamento Livre", "Quadríceps/Glúteos", Duration.ofMinutes(10), 4, 10);
        Exercicio exer5 = new Exercicio("Extensora", "Quadríceps", Duration.ofMinutes(12), 4, 12);
        Exercicio exer6 = new Exercicio("Stiff", "Posterior", Duration.ofMinutes(8), 3, 12);

        List<Exercicio> exerciciosA = new ArrayList<>();
        List<Exercicio> exerciciosB = new ArrayList<>();

        exerciciosA.add(exer1);
        exerciciosA.add(exer2);
        exerciciosA.add(exer3);

        exerciciosB.add(exer4);
        exerciciosB.add(exer5);
        exerciciosB.add(exer6);

        Treino treino1 = new Treino("Superior", exerciciosA);
        Treino treino2 = new Treino("Inferior", exerciciosB);

        List<Treino> treinosA = new ArrayList<>();
        List<Treino> treinosB = new ArrayList<>();
        List<Treino> treinosC = new ArrayList<>();

        treinosA.add(treino1);
        treinosB.add(treino2);
        treinosC.add(treino1);
        treinosC.add(treino2);

        PlanoDeTreino planoTreino1 = new PlanoDeTreino(LocalDate.of(2022, 8, 20), Period.ofDays(45),
                (Cliente) cliente1, treinosA);
        PlanoDeTreino planoTreino2 = new PlanoDeTreino(LocalDate.of(2022, 8, 20), Period.ofDays(45),
                (Cliente) cliente2, treinosB);
        PlanoDeTreino planoTreino3 = new PlanoDeTreino(LocalDate.of(2022, 8, 20), Period.ofDays(45),
                (Cliente) cliente3, treinosC);

        TreinoExecutado treinoExe1 = new TreinoExecutado((Cliente) cliente1, treino1, LocalDate.of(2022, 8, 21));
        TreinoExecutado treinoExe2 = new TreinoExecutado((Cliente) cliente2, treino2, LocalDate.of(2022, 8, 22));
        TreinoExecutado treinoExe3 = new TreinoExecutado((Cliente) cliente3, treino2, LocalDate.of(2022, 8, 22));
        TreinoExecutado treinoExe4 = new TreinoExecutado((Cliente) cliente3, treino1, LocalDate.of(2022, 8, 22));

        try {
            servidor.inserir(exer1);
            servidor.inserir(exer2);
            servidor.inserir(exer3);
            servidor.inserir(exer4);
            servidor.inserir(exer5);
            servidor.inserir(exer6);

            servidor.inserir(treino1);
            servidor.inserir(treino2);

            servidor.inserir(planoTreino1);
            servidor.inserir(planoTreino2);
            servidor.inserir(planoTreino3);

            servidor.inserir(treinoExe1);
            servidor.inserir(treinoExe2);
            servidor.inserir(treinoExe3);
            servidor.inserir(treinoExe4);

            servidor.inserir(cliente1);
            servidor.inserir(cliente2);
            servidor.inserir(cliente3);
            servidor.inserir(personal1);
            servidor.inserir(personal2);
            servidor.inserir(adm1);

        } catch (ElementoJaExisteException jaExiste) {
            System.out.println("Elemento já existente");
            jaExiste.printStackTrace();
        }
        launch();
    }
}