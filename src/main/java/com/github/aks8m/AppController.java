package com.github.aks8m;

import com.github.aks8m.compare.engine.CompareEngineFactory;
import com.github.aks8m.compare.engine.CompareEngine;
import com.github.aks8m.compare.engine.MDHTComparisonEngine;
import com.github.aks8m.compare.precompare.MDHTPreCompareService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import org.openhealthtools.mdht.uml.cda.ClinicalDocument;
import org.openhealthtools.mdht.uml.cda.util.CDAUtil;
import org.openhealthtools.mdht.uml.cda.util.ValidationResult;

import com.github.aks8m.compare.Comparison;
import com.github.aks8m.report.result.Result;

import java.util.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AppController {

    @FXML
    private TextField sourceTextField;
    @FXML
    private TextField targetTextField;
    @FXML
    private ListView comparisonOutput;
    @FXML
    private ProgressBar compareProgressbar;
    @FXML
    private Button compareButton;

    private CompareEngine compareEngine;


    @FXML
    void initialize() {
//        this.sourceTextField.setText("C:\\Users\\kmaulden\\Documents\\C-CDA Comparison Files\\xmlComparisons\\ErrorsThrown\\HSEP_IPOACKIES_QUENTIN_typeIDAndTitleError1.xml");
//        this.targetTextField.setText("C:\\Users\\kmaulden\\Documents\\C-CDA Comparison Files\\xmlComparisons\\ErrorsThrown\\HSEP_IPOACKIES_QUENTIN_typeIDAndTitleError2.xml");
//        this.sourceTextField.setText("C:\\Users\\kmaulden\\Documents\\C-CDA Comparison Files\\xmlComparisons\\HSEP_CCDACCDR1.1_IPOACKIES_QUENTIN_09122018.xml");
//        this.targetTextField.setText("C:\\Users\\kmaulden\\Documents\\C-CDA Comparison Files\\xmlComparisons\\HSEP_CCDACCDR1.1_IPOACKIES_QUENTIN_09122018.xml");
        this.sourceTextField.setText("C:\\Users\\kmaulden\\Documents\\C-CDA Comparison Files\\xmlComparisons\\ErrorsThrown\\HSEP_IPOACKIES_QUENTIN_complexListErrorParticipant1.xml");
        this.targetTextField.setText("C:\\Users\\kmaulden\\Documents\\C-CDA Comparison Files\\xmlComparisons\\ErrorsThrown\\HSEP_IPOACKIES_QUENTIN_complexListErrorParticipant2.xml");

    }


   @FXML
   public void runComparison(ActionEvent actionEvent){


        try {

            compareEngine = CompareEngineFactory.CreateMDHTCompareEngine(this.sourceTextField.getText(), this.targetTextField.getText());
            this.compareEngine.start();
            this.compareProgressbar.progressProperty().bind(this.compareEngine.progressProperty());
            this.compareEngine.stateProperty().addListener((observable, oldValue, newValue) -> {

                switch (newValue) {
                    case SUCCEEDED:
                        this.comparisonOutput.getItems().add("--------------------------------COMPARE RESULTS--------------------------------");
                        this.comparisonOutput.getItems().add("MISMATCHES:");
                        this.compareEngine.getValue().getMismatches().stream()
                                .forEach(mismatch -> this.comparisonOutput.getItems().add(mismatch.toString()));
                        this.comparisonOutput.getItems().add(" ");
                        this.comparisonOutput.getItems().add("WARNINGS:");
                        this.compareEngine.getValue().getWarnings().stream()
                                .forEach(mismatch -> this.comparisonOutput.getItems().add(mismatch.toString()));
                        this.comparisonOutput.getItems().add(" ");
                        this.comparisonOutput.getItems().add(" ");
                        this.comparisonOutput.getItems().add("------------------------------POST COMPARE RESULTS-----------------------------");
                        this.comparisonOutput.getItems().add("MISMATCHES:");
                        this.compareEngine.getValue().getPostCompareMismatches().stream()
                                .forEach(mismatch -> this.comparisonOutput.getItems().add(mismatch.toString()));
                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
