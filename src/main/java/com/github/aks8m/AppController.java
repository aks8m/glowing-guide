package com.github.aks8m;

import com.github.aks8m.compare.engine.CompareEngineFactory;
import com.github.aks8m.compare.engine.CompareEngine;
import com.github.aks8m.report.result.TreeResultWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    @FXML
    private TreeView<TreeResultWrapper> sourceTree;
    @FXML
    private TreeView<TreeResultWrapper> targetTree;

    private CompareEngine compareEngine;
    private final TreeItem<TreeResultWrapper> sourceRoot = new TreeItem<>();
    private final TreeItem<TreeResultWrapper> targetRoot = new TreeItem<>();

    @FXML
    void initialize() {

//        this.sourceTextField.setText("C:\\Users\\kmaulden\\Documents\\C-CDA Comparison Files\\xmlComparisonsNew\\SSA_CCDACCD1.1_IPOACKIES_QUENTIN_nodates_9072018.xml");
//        this.targetTextField.setText("C:\\Users\\kmaulden\\Documents\\C-CDA Comparison Files\\xmlComparisonsNew\\HSEP_CCDACCDR1.1_IPOACKIES_QUENTIN_09122018.xml");

//        this.sourceTextField.setText("C:\\Users\\kmaulden\\Documents\\C-CDA Comparison Files\\xmlComparisons\\HSEP_CCDACCDR1.1_IPOACKIES_QUENTIN_09122018.xml");
//        this.targetTextField.setText("C:\\Users\\kmaulden\\Documents\\C-CDA Comparison Files\\xmlComparisons\\HSEP_CCDACCDR1.1_IPOACKIES_QUENTIN_09122018-dif.xml");

//        this.sourceTextField.setText("C:\\Users\\kmaulden\\Documents\\C-CDA Comparison Files\\xmlComparisons\\SSA_CCDACCD1.1_IAADLAND_JAN_nodates_9072018.xml");
//        this.targetTextField.setText("C:\\Users\\kmaulden\\Documents\\C-CDA Comparison Files\\xmlComparisons\\HSEP_CCDACCDR1.1_AALAND_JAN_09122018.xml");

//        this.sourceTextField.setText("C:\\Users\\kmaulden\\Documents\\C-CDA Comparison Files\\xmlComparisons\\SampleCDADocument.xml");
//        this.targetTextField.setText("C:\\Users\\kmaulden\\Documents\\C-CDA Comparison Files\\xmlComparisons\\SampleCDADocument.xml");

        this.sourceTextField.setText("C:\\Users\\kmaulden\\Documents\\C-CDA Comparison Files\\TestFiles\\HS_CCDA_R2.1_NWHINFIVE_nodates_gen01042019.xml");
        this.targetTextField.setText("C:\\Users\\kmaulden\\Documents\\C-CDA Comparison Files\\TestFiles\\EHX_CCDA_R2.1_NWHINFIVE_nodates_gen01042019.xml");

//        this.sourceTextField.setText("C:\\Users\\kmaulden\\Documents\\C-CDA Comparison Files\\TestFiles\\EHX_CCDA_R1.1_NWHINONE_nodates_gen01032019.xml");
//        this.targetTextField.setText("C:\\Users\\kmaulden\\Documents\\C-CDA Comparison Files\\TestFiles\\HS_CCDA_R1.1_NWHINONE_nodates_gen01042019.xml");

//        this.sourceTextField.setText("C:\\Users\\kmaulden\\Documents\\C-CDA Comparison Files\\TestFiles\\EHX_CCDA_R2.1_NWHINONE_nodates_gen01032019.xml");
//        this.targetTextField.setText("C:\\Users\\kmaulden\\Documents\\C-CDA Comparison Files\\TestFiles\\HS_CCDA_R2.1_NWHINONE_nodates_gen01042019.xml");

//        this.sourceTextField.setText("C:\\Users\\kmaulden\\Documents\\C-CDA Comparison Files\\TestFiles\\EHX_CCDA_R1.1_NWHINONE_nodates_gen01032019_test.xml");
//        this.targetTextField.setText("C:\\Users\\kmaulden\\Documents\\C-CDA Comparison Files\\TestFiles\\HS_CCDA_R1.1_NWHINONE_nodates_gen01042019_test.xml");

//        this.sourceTextField.setText("C:\\Users\\kmaulden\\Documents\\C-CDA Comparison Files\\TestFiles\\EHX_CCDA_R1.1_NWHINONE_nodates_gen01032019_test1.xml");
//        this.targetTextField.setText("C:\\Users\\kmaulden\\Documents\\C-CDA Comparison Files\\TestFiles\\HS_CCDA_R1.1_NWHINONE_nodates_gen01042019_test1.xml");


        sourceRoot.setValue(new TreeResultWrapper("Source Root", null));
        targetRoot.setValue(new TreeResultWrapper("Target Root", null));
        this.sourceTree.setRoot(sourceRoot);
        this.targetTree.setRoot(targetRoot);
    }


   @FXML
   public void runComparison(ActionEvent actionEvent){


        try {

            compareEngine = CompareEngineFactory.CreateMDHTCompareEngine(this.sourceTextField.getText(), this.targetTextField.getText(),
                    this.sourceRoot , this.targetRoot);
            this.compareEngine.start();
            this.compareProgressbar.progressProperty().bind(this.compareEngine.progressProperty());
            this.compareEngine.stateProperty().addListener((observable, oldValue, newValue) -> {

                switch (newValue) {
                    case SUCCEEDED:
                        this.comparisonOutput.getItems().add("MISMATCHES:" + " (" + this.compareEngine.getValue().getMismatches().size() + ")");
                        this.compareEngine.getValue().getMismatches().stream()
                                .forEach(mismatch -> this.comparisonOutput.getItems().add(mismatch.toString()));
                        this.comparisonOutput.getItems().add(" ");

                        this.comparisonOutput.getItems().add("SOURCE SECTION NOT FOUND IN TARGET:" + " (" + this.compareEngine.getValue().getSectionMatchNotFound().size() + ")");
                        this.comparisonOutput.getItems().add("NOTE: All granular mismatches in each section are suppressed");
                        this.compareEngine.getValue().getSectionMatchNotFound().stream().forEach(section -> this.comparisonOutput.getItems().add(section.toString()));
                        this.comparisonOutput.getItems().add(" ");
                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
