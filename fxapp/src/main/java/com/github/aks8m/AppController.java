package com.github.aks8m;

import com.github.aks8m.compare.ComparisonService;
import com.github.aks8m.compare.SAXUnorderedLexicographicalComparisonService;
import com.github.aks8m.engine.CompareEngine;
import com.github.aks8m.engine.CompareEngineFactory;
import com.github.aks8m.report.result.Result;
import com.github.aks8m.report.result.ResultTreeItem;
import com.github.aks8m.tree.AttributeNode;
import com.github.aks8m.tree.ElementNode;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import jdk.nashorn.internal.runtime.regexp.joni.ast.Node;

import java.io.File;
import java.util.HashMap;

public class AppController {

    @FXML
    public Separator seperator;
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
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private Label attributeTextField;
    @FXML
    private Label elementTextField;


    private CompareEngine compareEngine;
    private ComparisonService sectionComparisonService;
    private ResultTreeItem sourceRoot;
    private ResultTreeItem targetRoot;
    private Stage fileChooseStage = new Stage();
    private FileChooser fileChooser;
    private File sourceFile;
    private File targetFile;
    private TreeItem<String> sourceSection;
    private TreeItem<String> targetSection;

    private HashMap<TreeItem<String>, TreeItem<String>> nodeMap = new HashMap<>();

    private enum ResultsEnum {
        ALL, ATTRIBUTE, VALUE, SECTION
    }

    private ResultsEnum resultsEnum = ResultsEnum.ALL;


    @FXML
    void initialize() {

        this.fileChooser = new FileChooser();

        this.sourceTextField.setDisable(true);
        this.targetTextField.setDisable(true);

        this.attributeTextField.setText("Denotes XML Attribute");
        this.elementTextField.setText("Denotes XML Element");

        this.sourceTree.setRoot(sourceRoot);
        this.sourceTree.setEditable(true);
        this.targetTree.setRoot(targetRoot);

        this.resultsView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.resultsView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            for (Result result : this.resultsView.getItems()) {
                result.isSelectedProperty().set(false);
            }
            newValue.isSelectedProperty().set(true);

        });

        this.choiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                switch (newValue) {
                    case "All Results":
                        resultsEnum = ResultsEnum.ALL;
                        collapseTreeViews();
                        displayResults();
                        break;
                    case "Value Mismatches":
                        resultsEnum = ResultsEnum.VALUE;
                        collapseTreeViews();
                        displayResults();
                        break;
                    case "Section Mismatches":
                        resultsEnum = ResultsEnum.SECTION;
                        collapseTreeViews();
                        displayResults();
                        break;
                    case "Attribute Mismatches":
                        resultsEnum = ResultsEnum.ATTRIBUTE;
                        collapseTreeViews();
                        displayResults();
                        break;
                }
            }
        });
        Callback<TreeView<String>, TreeCell<String>> defaultCellFactory = TextFieldTreeCell.forTreeView();

        this.sourceTree.setCellFactory((TreeView<String> tv) -> {
            TreeCell<String> cell = defaultCellFactory.call(tv);
            cell.treeItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    if (newValue instanceof AttributeNode) {
                        cell.setText(newValue.getValue());
                        cell.setTextFill(Color.BLACK);
                        Rectangle rectangle = new Rectangle(12,12, Color.TRANSPARENT);
                        rectangle.setStroke(Color.LIGHTBLUE);
                        cell.getTreeItem().setGraphic(new ImageView(rectangle.snapshot(null, null)));
                    } else if (newValue instanceof ElementNode) {
                        cell.setText(newValue.getValue());
                        cell.setTextFill(Color.BLACK);
                        Circle circle = new Circle(7,7,7, Color.TRANSPARENT);
                        circle.setStroke(Color.YELLOWGREEN);
                        cell.getTreeItem().setGraphic(new ImageView(circle.snapshot(null,null)));
                    } else {
                        cell.setText(newValue.getValue());
                        cell.setTextFill(Color.BLACK);
                    }

                    if (((ResultTreeItem ) cell.getTreeItem()).getIsSelected().getValue()) {
                        cell.setTextFill(Color.DARKRED);
                    } else {
                        cell.setTextFill(Color.BLACK);
                    }
                }

            });
            return cell;
        });

        this.targetTree.setCellFactory((TreeView<String> tv) -> {
            TreeCell<String> cell = defaultCellFactory.call(tv);
            cell.treeItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    if (newValue instanceof AttributeNode) {
                        cell.setText(newValue.getValue());
                        cell.setTextFill(Color.BLACK);
                        Rectangle rectangle = new Rectangle(12,12, Color.TRANSPARENT);
                        rectangle.setStroke(Color.LIGHTBLUE);
                        cell.getTreeItem().setGraphic(new ImageView(rectangle.snapshot(null, null)));
                    } else if (newValue instanceof ElementNode) {
                        cell.setText(newValue.getValue());
                        cell.setTextFill(Color.BLACK);
                        Circle circle = new Circle(7,7,7, Color.TRANSPARENT);
                        circle.setStroke(Color.YELLOWGREEN);
                        cell.getTreeItem().setGraphic(new ImageView(circle.snapshot(null,null)));
                    } else {
                        cell.setText(newValue.getValue());
                        cell.setTextFill(Color.BLACK);
                    }

                    if (((ResultTreeItem ) cell.getTreeItem()).getIsSelected().getValue()) {
                        cell.setTextFill(Color.DARKRED);
                    } else {
                        cell.setTextFill(Color.BLACK);
                    }
                }

            });
            return cell;
        });

        sourceTree.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue != null && newValue != oldValue) {
                this.sourceSection = newValue;
                System.out.println(newValue.getValue());
            }
        }));

        targetTree.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue != null && newValue != oldValue) {
                this.targetSection = newValue;
                System.out.println(newValue.getValue());
            }
        }));
    }

    @FXML
    public void loadSource(ActionEvent actionEvent) {

        this.fileChooser.setTitle("Browse to Source CDA File");
        this.sourceFile = this.fileChooser.showOpenDialog(this.fileChooseStage);

        if (this.sourceFile != null && this.sourceFile.isFile()) {
            this.sourceTextField.setText(sourceFile.getName());
        }
    }

    @FXML
    public void loadTarget(ActionEvent actionEvent) {

        this.fileChooser.setTitle("Browse to Target CDA File");
        this.targetFile = this.fileChooser.showOpenDialog(this.fileChooseStage);

        if (this.targetFile != null && this.targetFile.isFile()) {
            this.targetTextField.setText(targetFile.getName());
        }
    }


    @FXML
    public void runComparison(ActionEvent actionEvent) {

        this.resultsView.getItems().clear();
        this.sourceRoot = new ResultTreeItem("");
        this.targetRoot = new ResultTreeItem("");
        this.sourceTree.setRoot(sourceRoot);
        this.targetTree.setRoot(targetRoot);

        try {

            this.compareEngine = CompareEngineFactory.CreateSAXParserFileCompareEngine(this.sourceFile, this.targetFile, this.nodeMap);
            this.compareEngine.start();
            this.compareProgressbar.progressProperty().bind(this.compareEngine.progressProperty());
            this.compareEngine.stateProperty().addListener((observable, oldValue, newValue) -> {

                switch (newValue) {
                    case SUCCEEDED:
                        this.sourceTree.setRoot(this.compareEngine.getValue().getSourceRoot());
                        this.targetTree.setRoot(this.compareEngine.getValue().getTargetRoot());
                        displayResults();
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void displayResults() {
        this.resultsView.getItems().clear();
        switch (this.resultsEnum) {
            case ALL:
                this.compareEngine.getValue().getAllMismatches().stream()
                        .forEach(mismatch -> this.resultsView.getItems().add(mismatch));
                break;
            case VALUE:
                this.compareEngine.getValue().getValueMismatches().stream()
                        .forEach(mismatch -> this.resultsView.getItems().add(mismatch));
                break;
            case ATTRIBUTE:
                this.compareEngine.getValue().getAttributeMismatches().stream()
                        .forEach(mismatch -> this.resultsView.getItems().add(mismatch));
                break;
            case SECTION:
                this.compareEngine.getValue().getSectionMatchNotFound().stream()
                        .forEach(mismatch -> this.resultsView.getItems().add(mismatch));
                break;
        }

    }

    @FXML
    public void runSectionComparison(ActionEvent actionEvent) {
        if (this.sourceSection != null && this.targetSection != null) {

            collapseTreeViews();

            this.resultsView.getItems().clear();

            try {

                this.sectionComparisonService = new SAXUnorderedLexicographicalComparisonService(this.sourceSection, this.targetSection, this.nodeMap);
                this.sectionComparisonService.start();
                this.sectionComparisonService.stateProperty().addListener((observable, oldValue, newValue) -> {

                    switch (newValue) {
                        case SUCCEEDED:
                            this.sectionComparisonService.getValue().stream()
                                    .forEach(result -> this.resultsView.getItems().add(result));

                    }
                });


            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void collapseTreeViews() {
        for (Result result : this.resultsView.getItems()) {
            result.isSelectedProperty().set(false);
        }
    }

}