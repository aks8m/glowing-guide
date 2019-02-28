package com.github.aks8m;

import com.github.aks8m.engine.CompareEngine;
import com.github.aks8m.engine.CompareEngineFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * Main FXApp class for running the glowing guide C-CDA comparisonobject engine
 *
 */
public class FXApp extends Application
{
    public static void main( String[] args ) { launch(args); }


    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GlowingGuide.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(this.getClass().getResource("/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Glowing Guide");
        primaryStage.show();
    }
}
