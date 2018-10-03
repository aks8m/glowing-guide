package com.github.aks8m;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;

public class AppController {

    @FXML
    private TextArea  sourceTextArea;
    @FXML
    private TextArea targetTextArea;
    @FXML
    private ListView comparisonOutput;
    @FXML
    private Button compareButton;


    @FXML
    void initialize() {


//        sourceTextArea.setOnDragDetected(event -> {
////
////            Dragboard db = ((MouseEvent) event).ge get .getDragboard();
////            boolean success = false;
////            if (db.hasString()) {
////                target.setText(db.getString());
////                success = true;
////            }
////            /* let the source know whether the string was successfully
////             * transferred and used */
////            event.setDropCompleted(success);
////
////            event.consume();
//
//
//        });
//
//        sourceTextArea.setOnDragDropped(event -> {
//
////
////            /* allow any transfer mode */
////            Dragboard db = source.startDragAndDrop(TransferMode.ANY);
////
////            /* put a string on dragboard */
////            ClipboardContent content = new ClipboardContent();
////            content.putString(source.getText());
////            db.setContent(content);
////
////            event.consume();
//
//
//        });
//
   }


}
