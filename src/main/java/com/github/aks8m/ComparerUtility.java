package com.github.aks8m;

import org.openhealthtools.mdht.uml.cda.*;

public class ComparerUtility {

    private final ClinicalDocument sourceClinicalDocument;
    private final ClinicalDocument targetClinicalDocument;
    private final ComparisonResult comparisonResult;

    public ComparerUtility(ClinicalDocument sourceClinicalDocument, ClinicalDocument targetClinicalDocument) {
        this.sourceClinicalDocument = sourceClinicalDocument;
        this.targetClinicalDocument = targetClinicalDocument;
        this.comparisonResult = new ComparisonResult();
    }

    public void compare() {

        clinicalDocumentComparison();


    }

    private void clinicalDocumentComparison(){

        //compare RealmCode
        if (!(sourceClinicalDocument.getRealmCodes().get(0).getCode() == targetClinicalDocument.getRealmCodes().get(0).getCode()
                && sourceClinicalDocument.getRealmCodes().get(0).getNullFlavor().getLiteral() == targetClinicalDocument.getRealmCodes().get(0).getNullFlavor().getLiteral()
                && sourceClinicalDocument.getRealmCodes().get(0).getCodeSystem() == targetClinicalDocument.getRealmCodes().get(0).getCodeSystem()
                && sourceClinicalDocument.getRealmCodes().get(0).getCodeSystemName() == targetClinicalDocument.getRealmCodes().get(0).getCodeSystemName()
                && sourceClinicalDocument.getRealmCodes().get(0).getCodeSystemVersion() == targetClinicalDocument.getRealmCodes().get(0).getCodeSystemVersion()
                && sourceClinicalDocument.getRealmCodes().get(0).getDisplayName() == targetClinicalDocument.getRealmCodes().get(0).getDisplayName()) )
        {
            comparisonResult.addMessage("RealmCode Comparison Error");
        }

        //compareIDs
        if ( ! (sourceClinicalDocument.getId().getAssigningAuthorityName() == targetClinicalDocument.getId().getAssigningAuthorityName()
                && sourceClinicalDocument.getId().getExtension() == targetClinicalDocument.getId().getExtension()
                && sourceClinicalDocument.getId().getRoot() == targetClinicalDocument.getId().getRoot()
                && sourceClinicalDocument.getId().getNullFlavor() == targetClinicalDocument.getId().getNullFlavor()))
        {
            comparisonResult.addMessage("IDs Comparison Error");
        }

        //compare code
        if (!(sourceClinicalDocument.getCode().getCode() == targetClinicalDocument.getCode().getCode()
                && sourceClinicalDocument.getCode().getNullFlavor().getLiteral() == targetClinicalDocument.getCode().getNullFlavor().getLiteral()
                && sourceClinicalDocument.getCode().getCodeSystem() == targetClinicalDocument.getCode().getCodeSystem()
                && sourceClinicalDocument.getCode().getCodeSystemName() == targetClinicalDocument.getCode().getCodeSystemName()
                && sourceClinicalDocument.getCode().getCodeSystemVersion() == targetClinicalDocument.getCode().getCodeSystemVersion()
                && sourceClinicalDocument.getCode().getDisplayName() == targetClinicalDocument.getCode().getDisplayName()) )
        {
            comparisonResult.addMessage("Code Comparison Error");
        }

        //title






        //recordTargetComparison(sourceClinicalDocument.getRecordTargets().get(0), targetClinicalDocument.getRecordTargets().get(0));
        //authorComparison(sourceClinicalDocument.getAuthors().get(0), targetClinicalDocument.getRecordTargets().get(0));
        //dataEntererComparison(sourceClinicalDocument.getDataEnterer(), targetClinicalDocument.getDataEnterer());
        //informantComparison(sourceClinicalDocument.getInformants().get(0),targetClinicalDocument.getInformants().get(0));


    }

    private void informantComparison(Informant12 informant12, Informant12 informant121) {
    }

    private void dataEntererComparison(DataEnterer dataEnterer, DataEnterer dataEnterer1) {
    }

    private void authorComparison(Author sourceAuthor, RecordTarget targetAuthor) {
    }

    private void recordTargetComparison(RecordTarget sourceRecordTarger, RecordTarget targetRecordTarget) {
    }




}
