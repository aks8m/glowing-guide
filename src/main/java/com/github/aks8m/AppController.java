package com.github.aks8m;

import com.github.aks8m.compare.CompareFactory;
import com.github.aks8m.compare.CompareService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import org.openhealthtools.mdht.uml.cda.util.CDAUtil;
import org.openhealthtools.mdht.uml.cda.util.ValidationResult;

import java.io.FileInputStream;

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

    private CompareService compareService;

    @FXML
    void initialize() {

        this.sourceTextField.setText("/Users/asills/devops/glowing-guide/HSEP_CCDACCDR1.1_AALAND_JAN_09122018.xml");
        this.targetTextField.setText("/Users/asills/devops/glowing-guide/HSEP_CCDACCDR1.1_AALAND_JAN_09122018.xml");

   }

   @FXML
   public void runComparison(ActionEvent actionEvent){

        try{
            compareService = CompareFactory.CreateMDHTCompareService(
                    CDAUtil.load(
                    new FileInputStream(this.sourceTextField.getText()), (ValidationResult) null),
                    CDAUtil.load(
                            new FileInputStream(this.targetTextField.getText()), (ValidationResult) null));

            this.compareService.start();
            this.compareProgressbar.progressProperty().bind(this.compareService.progressProperty());


        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
