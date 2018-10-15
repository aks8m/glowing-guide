package com.github.aks8m;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.openhealthtools.mdht.uml.cda.ClinicalDocument;
import org.openhealthtools.mdht.uml.cda.util.CDAUtil;
import org.openhealthtools.mdht.uml.cda.util.ValidationResult;

import java.io.FileInputStream;

/**
 * Main App class for running the glowing guide C-CDA comparison engine
 *
 */
public class App extends Application
{
    public static void main( String[] args )
    {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GlowingGuide.fxml"));
        AnchorPane root = loader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Glowing Guide");
        primaryStage.show();
    }

}
