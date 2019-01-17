package com.github.aks8m;

import com.github.aks8m.compare.engine.CompareEngineFactory;
import com.github.aks8m.compare.engine.CompareEngine;
import com.github.aks8m.report.result.Result;
import com.github.aks8m.report.result.ResultTreeItem;
import com.github.aks8m.traversal.MethodType.InitializeEnums;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
        this.targetTree.setRoot(targetRoot);

        InitializeEnums.initializeEnums();

        this.resultsView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.resultsView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            for (Result result : resultsView.getItems()) {
                result.isSelectedProperty().set(false);
            }
            newValue.isSelectedProperty().set(true);
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

           compareEngine = CompareEngineFactory.CreateMDHTCompareEngine(this.sourceFile, this.targetFile,
                   this.sourceRoot , this.targetRoot);
           this.compareEngine.start();
           this.compareProgressbar.progressProperty().bind(this.compareEngine.progressProperty());
           this.compareEngine.stateProperty().addListener((observable, oldValue, newValue) -> {

               switch (newValue) {
                   case SUCCEEDED:
                       this.compareEngine.getValue().getMismatches().stream()
                               .forEach(mismatch -> this.resultsView.getItems().add(mismatch));
               }
           });


       }catch (Exception e){
           e.printStackTrace();
       }
       
    }

}
