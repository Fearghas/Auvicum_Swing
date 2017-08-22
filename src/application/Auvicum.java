package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.MainPane;

public class Auvicum extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final String APPLICATION_NAME = "AUVICUM";
        final String APPLICATION_VERSION = "0.1";
        final int WINDOW_WIDTH = 1000;
        final int WINDOW_HEIGHT = 800;

        ApplicationContext applicationContext = new ApplicationContext(APPLICATION_NAME, APPLICATION_VERSION);
        Pane mainPane = new MainPane(applicationContext, WINDOW_WIDTH, WINDOW_HEIGHT);

        Scene scene = new Scene(mainPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setTitle(applicationContext.getName() + " - " + applicationContext.getVersion());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}