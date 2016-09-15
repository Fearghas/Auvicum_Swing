/**
 * Created by ah on 06.09.2016.
 */

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

    public final class FileChooser extends Application {

        private Desktop desktop = Desktop.getDesktop();

        @Override
        public void start(final Stage stage) {
            stage.setTitle("File Chooser Sample");

            final javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
            final Button openButton = new Button("Open a Picture...");
            final Button openMultipleButton = new Button("Open Pictures...");
            final MenuBar menuBar = new MenuBar();
            final Menu menuFile = new Menu("File");
            final Menu menuEdit = new Menu("Edit");
            final MenuItem menuItem = new MenuItem("Open");

            menuFile.getItems().add(menuItem); //Hier ActionListener für DropdownMenü hinterlegen
            menuBar.getMenus().addAll(menuFile, menuEdit);

            openButton.setOnAction(
                    (javafx.event.ActionEvent e) -> {
                        File file = fileChooser.showOpenDialog(stage);
                        if (file != null) {
                            openFile(file);
                        }
                    });

            openMultipleButton.setOnAction(
                    (javafx.event.ActionEvent e) -> {
                        List<File> list =
                                fileChooser.showOpenMultipleDialog(stage);
                        if (list != null) {
                            for (File file : list) {
                                openFile(file);
                            }
                        }
                    });

            final GridPane inputGridPane = new GridPane(); // GridPane = Raster zur Orientierung v. Objekten

            GridPane.setConstraints(openButton, 0, 1);
            GridPane.setConstraints(openMultipleButton, 1, 1);
            GridPane.setConstraints(menuBar, 0, 0);
            inputGridPane.setHgap(20);
            inputGridPane.setVgap(10);
            inputGridPane.getChildren().addAll(openButton, openMultipleButton, menuBar);

            final Pane rootGroup = new VBox(15);
            rootGroup.getChildren().addAll(inputGridPane);
            rootGroup.setPadding(new Insets(20, 20, 20, 20));

            stage.setScene(new Scene(rootGroup));
            stage.show();
        }

        public static void main(String[] args) {
            Application.launch(args);
        }

        private void openFile(File file) {
            try {
                desktop.open(file);
            } catch (IOException ex) {
                Logger.getLogger(
                        FileChooser.class.getName()).log(
                        Level.SEVERE, null, ex
                );
            }
        }
    }



