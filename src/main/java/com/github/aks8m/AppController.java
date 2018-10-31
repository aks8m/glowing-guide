package com.github.aks8m;

import com.github.aks8m.compare.engine.CompareEngineFactory;
import com.github.aks8m.compare.engine.CompareEngine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import org.openhealthtools.mdht.uml.cda.ClinicalDocument;
import org.openhealthtools.mdht.uml.cda.util.CDAUtil;
import org.openhealthtools.mdht.uml.cda.util.ValidationResult;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;
import java.io.File;
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

    private CompareEngine compareEngine;

    @FXML
    void initialize() {

   }

   @FXML
   public void runComparison(ActionEvent actionEvent){

        try{
//            compareEngine = CompareEngineFactory.CreateMDHTCompareService(
//                   );

//            this.compareEngine.start();
//            this.compareProgressbar.progressProperty().bind(this.compareEngine.progressProperty());
//            this.compareEngine.stateProperty().addListener((observable, oldValue, newValue) -> {
//
//                switch (newValue){
//                    case SUCCEEDED:
//                        this.compareEngine.getValue().getMismatches().stream()
//                                .forEach(mismatch -> this.comparisonOutput.getItems().add(mismatch.toString()));
//                }
//            });
//

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
