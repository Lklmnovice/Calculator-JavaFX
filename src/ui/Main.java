package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

    /**
     * Un applicazione "calcolatrice" che manda l'espressione numerica da svolgere al
     * server
     * @author WORKSTATION
     */
    public class Main extends Application {
        private double xPosition;   //per poter spostare la finestra
        private double yPosition;   //per poter spostare la finestra


        @Override
        public void start(Stage stage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("ui.fxml"));
            stage.initStyle(StageStyle.TRANSPARENT);
            Scene scene = new Scene(root);
            scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
            stage.setScene(scene);
            root.setOnMousePressed(event -> {
                xPosition = event.getSceneX();
                yPosition = event.getSceneY();
            });
            root.setOnMouseDragged(event -> {
                stage.setX(event.getScreenX() - xPosition);
                stage.setY(event.getScreenY() - yPosition);
            });
            stage.show();
        }

    }

