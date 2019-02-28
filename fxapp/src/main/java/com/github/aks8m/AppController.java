package com.github.aks8m;

import com.github.aks8m.engine.CompareEngine;
import com.github.aks8m.engine.CompareEngineFactory;
import com.github.aks8m.report.result.Result;
import com.github.aks8m.report.result.ResultTreeItem;
import com.github.aks8m.traversal.MethodType.InitializeEnums;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.util.Callback;

import java.io.File;

public class AppController {

    @FXML
    private SplitPane splitPane;
    @FXML
    private TextField sourceTextField;
    @FXML
    private TextField targetTextField;
    @FXML
    private ListView<Result> resultsView;
    @FXML
    private ProgressBar compareProgressbar;
    @FXML
    private Button compareButton;
    @FXML
    private Button loadSourceButton;
    @FXML
    private Button loadTargetButton;
    @FXML
    private TreeView<String> sourceTree;
    @FXML
    private TreeView<String> targetTree;
    private CompareEngine compareEngine;
    private ResultTreeItem sourceRoot;
    private ResultTreeItem targetRoot;
    private Stage fileChooseStage = new Stage();
    private FileChooser fileChooser;
    private File sourceFile;
    private File targetFile;


    @FXML
    void initialize() {

        this.fileChooser = new FileChooser();

        this.sourceTextField.setDisable(true);
        this.targetTextField.setDisable(true);

        this.sourceRoot = new ResultTreeItem("");
        this.targetRoot = new ResultTreeItem("");
        this.sourceTree.setRoot(sourceRoot);
        this.sourceTree.setEditable(true);

//        this.sourceTree.setCellFactory(param -> new TreeCell<String>() {
//           @Override
//           public void updateItem(String item, boolean empty) {
//               super.updateItem(item, empty);
//               setText(item);
//
////               if (param.getTreeItem(0).isExpanded()) {
//////                   setText(item);
//////                   setTextFill(Color.YELLOW);
////               }
//           }
//
//
//
//        });


        this.targetTree.setRoot(targetRoot);

        InitializeEnums.initializeEnums();

        this.resultsView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.resultsView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            for (Result result : resultsView.getItems()) {
                result.isSelectedProperty().set(false);
                result.getSourceNodes().forEach(sourceNode -> sourceNode.getResultTreeItemList().forEach(resultTreeItem -> resultTreeItem.getIsSelected().set(false)));
                result.getTargetNodes().forEach(targetNode -> targetNode.getResultTreeItemList().forEach(resultTreeItem -> resultTreeItem.getIsSelected().set(false)));
            }
            newValue.isSelectedProperty().set(true);
            newValue.getSourceNodes().forEach(sourceNode -> sourceNode.getResultTreeItemList().forEach(resultTreeItem -> resultTreeItem.getIsSelected().set(true)));
            newValue.getTargetNodes().forEach(targetNode -> targetNode.getResultTreeItemList().forEach(resultTreeItem -> resultTreeItem.getIsSelected().set(true)));
        });

    }


    @FXML
    public void loadSource(ActionEvent actionEvent){

        this.fileChooser.setTitle("Browse to Source CDA File");
        this.sourceFile = this.fileChooser.showOpenDialog(this.fileChooseStage);

        if(this.sourceFile != null && this.sourceFile.isFile()){
            this.sourceTextField.setText(sourceFile.getName());
        }
    }

    @FXML
    public void loadTarget(ActionEvent actionEvent){

        this.fileChooser.setTitle("Browse to Target CDA File");
        this.targetFile = this.fileChooser.showOpenDialog(this.fileChooseStage);

        if(this.targetFile != null && this.targetFile.isFile()){
            this.targetTextField.setText(targetFile.getName());
        }
    }


   @FXML
   public void runComparison(ActionEvent actionEvent){

        this.resultsView.getItems().clear();
        this.sourceRoot = new ResultTreeItem("");
        this.targetRoot = new ResultTreeItem("");
        this.sourceTree.setRoot(sourceRoot);
        this.targetTree.setRoot(targetRoot);

       try {

           this.compareEngine = CompareEngineFactory.CreateSAXParserCompareEngine(this.sourceFile,this.targetFile, this.sourceRoot, this.targetRoot);
           this.compareEngine.start();
           this.compareProgressbar.progressProperty().bind(this.compareEngine.progressProperty());
           this.compareEngine.stateProperty().addListener((observable, oldValue, newValue) -> {

               switch (newValue) {
                   case SUCCEEDED:
                       this.compareEngine.getValue().getMismatches().stream()
                               .forEach(mismatch -> this.resultsView.getItems().add(mismatch));
                       this.compareEngine.getValue().getSectionMatchNotFound().stream().
                               forEach(sectionNotFound -> this.resultsView.getItems().add(sectionNotFound));
               }
           });


       }catch (Exception e){
           e.printStackTrace();
       }

    }

}
