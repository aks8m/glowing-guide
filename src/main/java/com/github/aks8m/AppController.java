package com.github.aks8m;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.openhealthtools.mdht.uml.cda.util.CDAUtil;
import org.openhealthtools.mdht.uml.cda.util.ValidationResult;

import java.io.FileInputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppController {

    @FXML
    private TextField sourceTextField;
    @FXML
    private TextField targetTextField;
    @FXML
    private ListView comparisonOutput;
    @FXML
    private Button compareButton;
    private final CompareUtility compareUtility = new CompareUtility();

    @FXML
    void initialize() {

        compareUtility.getComparisonResults().addListener((ListChangeListener<Mismatch>) c -> {

            while(c.next()){
                c.getAddedSubList().stream()
                        .forEach(o -> this.comparisonOutput.getItems().add(o.getMismatchDescription()));
            }
        });

   }

   @FXML
   public void runComparison(ActionEvent actionEvent){

        try{

            this.compareUtility.setSourceClinicalDocument(CDAUtil.load(
                    new FileInputStream(this.sourceTextField.getText()), (ValidationResult) null));
            this.compareUtility.setTargetClinicalDocument(CDAUtil.load(
                    new FileInputStream(this.targetTextField.getText()), (ValidationResult) null));

            ExecutorService executorService = Executors.newFixedThreadPool(8);

            executorService.submit(compareUtility);


        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
