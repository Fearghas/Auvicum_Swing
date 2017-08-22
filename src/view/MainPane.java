package view;

import application.ApplicationContext;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import model.PatientParticle;
import model.SimplePatientParticle;
import persist.CsvFileLoader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.prefs.Preferences;


public class MainPane extends BorderPane {

    public MainPane(ApplicationContext applicationContext, int width, int height) {

        // To be done: Outsource and implement leftPane with Buttons for interacting/manipulating with workflow-presentation
        VBox leftPane = new VBox();
        leftPane.getChildren().addAll(new Label("File Name: Placeholder"));

        // Optional: Implement and Outsource Status Bar/feedback from application at the bottom of mainPane
        HBox bottomPane = new HBox();
        String comment = "This is bottom: Placeholder";
        Label bottom = new Label(comment);
        bottomPane.getChildren().addAll(bottom);

        // Divide leftPane and rightPane with vertical SplitPane
        final int INFO_AREA_WIDTH = 200;
        final SplitPane verticalSplitPane = new SplitPane();

        // To be done: paint and show workflow of patientlist with JavaFX on rightPane
        Pane rightPane = new Pane();
        ImageView sample = new ImageView("view/KRN.gif");
        rightPane.getChildren().addAll(sample);

        verticalSplitPane.setDividerPosition(0, (double) INFO_AREA_WIDTH / (double) width);
        verticalSplitPane.getItems().addAll(leftPane, rightPane);

        // Set Layout for BorderPane
        setTop(createMenuBar(applicationContext));
        setLeft(leftPane);
        setCenter(verticalSplitPane);
        setBottom(bottomPane);
    }

    // Method: Create MenuBar in Top-Section of BorderPane
    private MenuBar createMenuBar(ApplicationContext applicationContext) {
        MenuBar menuBar = new MenuBar();

        Menu menu;
        MenuItem menuItem;

        menu = new Menu("File");
        menuBar.getMenus().add(menu);
        menuItem = new MenuItem("Choose muster.txt");
        menu.getItems().add(menuItem);
        menuItem.setOnAction((ActionEvent event) -> loadObject(applicationContext));

        return menuBar;
    }

    // Method: read CSV-File
    private void loadObject(ApplicationContext applicationContext) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File muster.txt");

        // Initialize the system preferences
        final Preferences preferences = Preferences.userRoot().node("Auvicum_JavaFX");
        final String currentDirectoryKey = "directory";

        // Retrieve the current directory from the system preferences,
        // and set it as the initial directory of the chooser, if possible
        String currentDirectoryName = preferences.get(currentDirectoryKey, "");
        if (currentDirectoryName.length() > 0) {
            File currentDirectory = new File(currentDirectoryName);
            if (currentDirectory.isDirectory()) {
                chooser.setInitialDirectory(currentDirectory);
            }
        }

        File selectedFile = chooser.showOpenDialog(getScene().getWindow());
        if (selectedFile != null) {
            ArrayList<PatientParticle> patientList = null;

            try {
                patientList = CsvFileLoader.createWorkflowData(selectedFile.getPath());
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (patientList != null) {
                //applicationContext.getRenderer.setData(patientList);

                // Check if values are correct
                System.out.println("PatientListSize: " + patientList.size());
                SimplePatientParticle particle = (SimplePatientParticle) patientList.get(0);
                Date ankunft = new Date(particle.getArrival());
                Date anfang = new Date(particle.getMedicalExamination());
                Date ende = new Date(particle.getDischarge());
                System.out.println("For Patient ID 1 values are: ");
                System.out.println("Ankunft: " + ankunft);
                System.out.println("Anfang: " + anfang);
                System.out.println("Ende: " + ende);
            }

            // Store the current path in the system preferences
            String directoryName = selectedFile.getAbsolutePath();
            preferences.put(currentDirectoryKey, directoryName);
        }
    }
}
