package com.github.aks8m;

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
