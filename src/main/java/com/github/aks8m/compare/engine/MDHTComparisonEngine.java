package com.github.aks8m.compare.engine;

import com.github.aks8m.compare.precompare.MDHTPreCompareService;
import com.github.aks8m.report.ComparisonReport;
import javafx.concurrent.Task;
import org.openhealthtools.mdht.uml.cda.ClinicalDocument;

public class MDHTComparisonEngine /*extends CompareEngine*/ {

//    private final MDHTPreCompareService mdhtPreCompareService;

//    public MDHTComparisonEngine(ClinicalDocument sourceClinicalDocument, ClinicalDocument targetClinicalDocument) {
//        this.mdhtPreCompareService = new MDHTPreCompareService(sourceClinicalDocument, targetClinicalDocument);
//    }

//    @Override
    protected Task<ComparisonReport> createTask() {

        return new Task<ComparisonReport>() {

            @Override
            protected ComparisonReport call() throws Exception {

//                //run MDHT Pre processing
//                mdhtPreCompareService.start();
//
//                //run MDHT main processing
//
//                //run MDHT postcompare processing


                return new ComparisonReport();
            }
        };
    }
}
