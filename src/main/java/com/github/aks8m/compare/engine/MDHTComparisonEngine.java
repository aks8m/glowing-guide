package com.github.aks8m.compare.engine;

import com.github.aks8m.compare.precompare.MDHTPreCompareService;
import com.github.aks8m.report.ComparisonReport;
import javafx.concurrent.Task;
import org.openhealthtools.mdht.uml.cda.ClinicalDocument;

public class MDHTComparisonEngine extends CompareEngine {

    private ComparisonReport comparisonReport;
    private final MDHTPreCompareService mdhtPreCompareService;
    //private final MDHTPostCompareService mdhtPostCompareService;

    public MDHTComparisonEngine(ClinicalDocument sourceClinicalDocument, ClinicalDocument targetClinicalDocument) {
        //super(new MDHTPreCompareService(sourceClinicalDocument,targetClinicalDocument),null);
        this.mdhtPreCompareService = new MDHTPreCompareService(sourceClinicalDocument, targetClinicalDocument);
    }

    @Override
    protected Task<ComparisonReport> createTask() {

        this.comparisonReport = new ComparisonReport();

        return new Task<ComparisonReport>() {

            @Override
            protected ComparisonReport call() throws Exception {

                //run MDHT Pre processing
                mdhtPreCompareService.start();
                mdhtPreCompareService.stateProperty().addListener((observable, oldValue, newValue) -> {
                    switch (newValue) {
                        case SUCCEEDED:
                            //run main processing
                            //run postcompare
                            comparisonReport.visited = true;

                    }
                });
//
//                //run MDHT main processing
//
//                //run MDHT postcompare processing


                //return new ComparisonReport();
                return comparisonReport;
            }
        };
    }
}
