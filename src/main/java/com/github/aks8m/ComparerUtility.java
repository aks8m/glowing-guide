package com.github.aks8m;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.openhealthtools.mdht.uml.cda.*;
import org.openhealthtools.mdht.uml.hl7.datatypes.CS;

import java.util.Objects;

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

        clinicalDocumentComparison("Clinical Document");


    }

    private void clinicalDocumentComparison(String errorMessage){

        //compare RealmCodemake
        compareRealmCodes(sourceClinicalDocument.getRealmCodes(),targetClinicalDocument.getRealmCodes(),errorMessage + " -> Realm Codes");

        //compare typeID
        typeIDComparison(sourceClinicalDocument.getTypeId(), targetClinicalDocument.getTypeId(),errorMessage);

        //compare templateID
        for (int i=0; i < 2; i++) {
            for (int j=0; j<2; j++) {
                if ( ! (Objects.equals(sourceClinicalDocument.getTemplateIds().get(i).getNullFlavor(),targetClinicalDocument.getTemplateIds().get(j).getNullFlavor())
                        && Objects.equals(sourceClinicalDocument.getTemplateIds().get(i).getRoot(),targetClinicalDocument.getTemplateIds().get(j).getRoot())
                        && Objects.equals(sourceClinicalDocument.getTemplateIds().get(i).getExtension(),targetClinicalDocument.getTemplateIds().get(j).getExtension())
                        && Objects.equals(sourceClinicalDocument.getTemplateIds().get(i).getAssigningAuthorityName(),targetClinicalDocument.getTemplateIds().get(j).getAssigningAuthorityName())))
                {
                    comparisonResult.addMessage("Potential templateID error in " + errorMessage);
                }
            }
        }


        //compareIDs
        if ( ! (Objects.equals(sourceClinicalDocument.getId().getAssigningAuthorityName(),targetClinicalDocument.getId().getAssigningAuthorityName())
                && Objects.equals(sourceClinicalDocument.getId().getExtension(),targetClinicalDocument.getId().getExtension())
                && Objects.equals(sourceClinicalDocument.getId().getRoot(),targetClinicalDocument.getId().getRoot())
                && Objects.equals(sourceClinicalDocument.getId().getNullFlavor(),targetClinicalDocument.getId().getNullFlavor())))
        {
            comparisonResult.addMessage("IDs Comparison error in " + errorMessage);
        }

        //compare code
        if (!(Objects.equals(sourceClinicalDocument.getCode().getCode(),targetClinicalDocument.getCode().getCode())
                && Objects.equals(sourceClinicalDocument.getCode().getNullFlavor().getLiteral(),targetClinicalDocument.getCode().getNullFlavor().getLiteral())
                && Objects.equals(sourceClinicalDocument.getCode().getCodeSystem(),targetClinicalDocument.getCode().getCodeSystem())
                && Objects.equals(sourceClinicalDocument.getCode().getCodeSystemName(),targetClinicalDocument.getCode().getCodeSystemName())
                && Objects.equals(sourceClinicalDocument.getCode().getCodeSystemVersion(),targetClinicalDocument.getCode().getCodeSystemVersion())
                && Objects.equals(sourceClinicalDocument.getCode().getDisplayName(),targetClinicalDocument.getCode().getDisplayName())) )
        {
            comparisonResult.addMessage("Code Comparison error in " + errorMessage);
        }

        //compare title
        if (! (Objects.equals(sourceClinicalDocument.getTitle().getText(),targetClinicalDocument.getTitle().getText()))) {
            comparisonResult.addMessage("Title Comparison error in " + errorMessage);
        }

        //compare Effective Time
        if (! (Objects.equals(sourceClinicalDocument.getEffectiveTime().getValue(),targetClinicalDocument.getEffectiveTime().getValue())
                && Objects.equals(sourceClinicalDocument.getEffectiveTime().getNullFlavor(),targetClinicalDocument.getEffectiveTime().getNullFlavor())))
        {
            comparisonResult.addMessage("Effective Time error in " + errorMessage);
        }

        //compare confidentiality code
        if (! (Objects.equals(sourceClinicalDocument.getConfidentialityCode().getCode(),targetClinicalDocument.getConfidentialityCode().getCode())
                && Objects.equals(sourceClinicalDocument.getConfidentialityCode().getNullFlavor().getLiteral(),targetClinicalDocument.getConfidentialityCode().getNullFlavor().getLiteral())
                && Objects.equals(sourceClinicalDocument.getConfidentialityCode().getCodeSystem(),targetClinicalDocument.getConfidentialityCode().getCodeSystem())
                && Objects.equals(sourceClinicalDocument.getConfidentialityCode().getCodeSystemName(),targetClinicalDocument.getConfidentialityCode().getCodeSystemName())
                && Objects.equals(sourceClinicalDocument.getConfidentialityCode().getCodeSystemVersion(),targetClinicalDocument.getConfidentialityCode().getCodeSystemVersion())
                && Objects.equals(sourceClinicalDocument.getConfidentialityCode().getDisplayName(),targetClinicalDocument.getConfidentialityCode().getDisplayName())))
        {
            comparisonResult.addMessage("Confidentiality Code error in " + errorMessage);
        }

        //compare language code
        if (! (Objects.equals(sourceClinicalDocument.getLanguageCode().getNullFlavor(),targetClinicalDocument.getLanguageCode().getNullFlavor())
                && Objects.equals(sourceClinicalDocument.getLanguageCode().getDisplayName(),targetClinicalDocument.getLanguageCode().getDisplayName()))
                && Objects.equals(sourceClinicalDocument.getLanguageCode().getCodeSystemVersion(),targetClinicalDocument.getLanguageCode().getCodeSystemVersion())
                && Objects.equals(sourceClinicalDocument.getLanguageCode().getCodeSystem(),targetClinicalDocument.getLanguageCode().getCodeSystem())
                && Objects.equals(sourceClinicalDocument.getLanguageCode().getCode(),targetClinicalDocument.getLanguageCode().getCode()))
        {
            comparisonResult.addMessage("Language Code error in " + errorMessage);
        }

        //compare setID -> came as null ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        //compare versionNumber -> came as null ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //compare copyTime -> came as null ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //compare Record Targets
        for (int i=0; i<sourceClinicalDocument.getRecordTargets().size(); i++) {
            for (int j=0; j<targetClinicalDocument.getRecordTargets().size();j++) {
                recordTargetComparison(sourceClinicalDocument.getRecordTargets().get(i), targetClinicalDocument.getRecordTargets().get(j), errorMessage + " -> Record Targets");
            }
        }

        //compare Author
        for (int i=0; i<sourceClinicalDocument.getAuthors().size(); i++) {
            for (int j = 0; j < targetClinicalDocument.getAuthors().size(); j++) {
                authorComparison(sourceClinicalDocument.getAuthors().get(i), targetClinicalDocument.getAuthors().get(j), errorMessage + " -> Authors");
            }
        }

        //compare Data Enterer
        dataEntererComparison(sourceClinicalDocument.getDataEnterer(), targetClinicalDocument.getDataEnterer(),errorMessage + " -> Data Enterer");

        //compare informant
        for (int i=0; i<sourceClinicalDocument.getInformants().size(); i++) {
            for (int j = 0; j < targetClinicalDocument.getInformants().size(); j++) {
                informantComparison(sourceClinicalDocument.getInformants().get(i), targetClinicalDocument.getInformants().get(j), errorMessage + " -> Informant");
            }
        }

        //compare custodian
        custodianComparison(sourceClinicalDocument.getCustodian(), targetClinicalDocument.getCustodian(),errorMessage + " -> Custodian");

        //compare information recipient -> came as null ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //compare Legal Authenticator
        legalAuthenticatorComparison(sourceClinicalDocument.getLegalAuthenticator(), targetClinicalDocument.getLegalAuthenticator(),errorMessage + " -> Legal Authenticator");

        //compare Authenticators -> came as null ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //compare participants
        for (int i=0; i<2; i++) {
            for (int j=0; j<2; j++) {
                participant1Comparison(sourceClinicalDocument.getParticipants().get(i),targetClinicalDocument.getParticipants().get(j),errorMessage + " -> Participants");
            }
        }

        //compare getFulfullmentOf -> came as null ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //compare documentationOf
        documentationOfComparison(sourceClinicalDocument.getDocumentationOfs().get(0),targetClinicalDocument.getDocumentationOfs().get(0),errorMessage + " -> Documentation");

        //compare getRelatedDocuments -> came as null ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //compare Authorizations -> came as null ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //compare componentOf -> came as null ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //compare component
        component2Comparison(sourceClinicalDocument.getComponent(), targetClinicalDocument.getComponent(),errorMessage = " -> Component");

        //compare nullFlavor
        if (sourceClinicalDocument.getNullFlavor() != targetClinicalDocument.getNullFlavor()) {
            comparisonResult.addMessage("nullFlavor error in " + errorMessage);
        }

        //compare classCode
        if (sourceClinicalDocument.getClassCode() != targetClinicalDocument.getClassCode()) {
            comparisonResult.addMessage("classCode error in " + errorMessage);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //complex type comparison Methods
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void typeIDComparison(InfrastructureRootTypeId sourceTypeID, InfrastructureRootTypeId targetTypeID, String errorMessage) {
        if (sourceTypeID != null && targetTypeID != null) {
            if (!(Objects.equals(sourceTypeID.getRoot(), targetTypeID.getRoot())
                    && Objects.equals(sourceTypeID.getAssigningAuthorityName(), targetTypeID.getAssigningAuthorityName())
                    && Objects.equals(sourceTypeID.getExtension(), targetTypeID.getExtension()))) {
                comparisonResult.addMessage("TypeID error in" + errorMessage);
            }
        }
    }

    private void recordTargetComparison(RecordTarget sourceRecordTarget, RecordTarget targetRecordTarget, String errorMessage) {
        if (sourceRecordTarget != null && targetRecordTarget != null) {
            //compare realmCode
            compareRealmCodes(sourceRecordTarget.getRealmCodes(),sourceRecordTarget.getRealmCodes(),errorMessage + " -> Realm Codes");

            //compare TypeID -> null //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            //compareTemplateID -> null //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            //comparePatientRole
            patientRoleComparison(sourceRecordTarget.getPatientRole(), targetRecordTarget.getPatientRole(), errorMessage + " -> Patient Role");

            //compare nullFlavor
            if (!(Objects.equals(sourceRecordTarget.getNullFlavor(), targetRecordTarget.getNullFlavor()))) {
                comparisonResult.addMessage("Null Flavor error in " + errorMessage);
            }

            //compare typeCode
            if (!(Objects.equals(sourceRecordTarget.getTypeCode(), targetRecordTarget.getTypeCode()))) {
                comparisonResult.addMessage("Type Code error in " + errorMessage);
            }
        }
    }

    private void authorComparison(Author sourceAuthor, Author targetAuthor, String errorMessage) {
        if (sourceAuthor != null && targetAuthor != null) {
            //compare realm codes
            compareRealmCodes(sourceAuthor.getRealmCodes(),sourceAuthor.getRealmCodes(),errorMessage + " -> Realm Codes");


            //compare typeID
            //typeIDComparison(sourceAuthor.getTypeId(),targetAuthor.getTypeId(),errorMessage + " -> typeID");

            //compare template ID
            for (int i = 0; i < sourceAuthor.getTemplateIds().size(); i++) {
                for (int j = 0; j < targetAuthor.getTemplateIds().size(); j++) {
                    if (!(Objects.equals(sourceAuthor.getTemplateIds().get(i).getNullFlavor(), targetAuthor.getTemplateIds().get(j).getNullFlavor())
                            && Objects.equals(sourceAuthor.getTemplateIds().get(i).getRoot(), targetAuthor.getTemplateIds().get(j).getRoot())
                            && Objects.equals(sourceAuthor.getTemplateIds().get(i).getExtension(), targetAuthor.getTemplateIds().get(j).getExtension())
                            && Objects.equals(sourceAuthor.getTemplateIds().get(i).getAssigningAuthorityName(), targetAuthor.getTemplateIds().get(j).getAssigningAuthorityName()))) {
                        comparisonResult.addMessage("Potential templateID error in " + errorMessage);
                    }
                }
            }

            //compare functionCode
//        if (!(Objects.equals(sourceAuthor.getFunctionCode().getCode(),targetAuthor.getFunctionCode().getCode())))
//        {
//            comparisonResult.addMessage("Function Code error in " + errorMessage);
//        }

            //compare time
            if (!(Objects.equals(sourceAuthor.getTime().getValue(), targetAuthor.getTime().getValue()))) {
                comparisonResult.addMessage("Time error in " + errorMessage);
            }

            //compare AssignedAuthor
            assignedAuthorComparison(sourceAuthor.getAssignedAuthor(), targetAuthor.getAssignedAuthor(), errorMessage + " -> Assigned Author");

            //compare nullFlavor
            if (!(Objects.equals(sourceAuthor.getNullFlavor().getLiteral(), targetAuthor.getNullFlavor().getLiteral()))) {
                comparisonResult.addMessage("Null Flavor error in " + errorMessage);
            }

            //compare typeCode
            if (!(Objects.equals(sourceAuthor.getTypeCode().getLiteral(), targetAuthor.getTypeCode().getLiteral()))) {
                comparisonResult.addMessage("Type Code error in " + errorMessage);
            }

            //compare contextControlCode
            if (!(Objects.equals(sourceAuthor.getContextControlCode().getLiteral(), targetAuthor.getContextControlCode().getLiteral()))) {
                comparisonResult.addMessage("Context Control Code error in " + errorMessage);
            }
        }

    }

    private void dataEntererComparison(DataEnterer sourceDataEnterer, DataEnterer targetDataEnterer, String errorMessage) {
        if (sourceDataEnterer != null && targetDataEnterer != null) {
            //compare realm code
            compareRealmCodes(sourceDataEnterer.getRealmCodes(),targetDataEnterer.getRealmCodes(),errorMessage + " -> Realm Codes");


            //compare typeID
            typeIDComparison(sourceDataEnterer.getTypeId(),targetDataEnterer.getTypeId(), errorMessage + " -> type ID");

            //compare templateID

            //compare time
            if (!(Objects.equals(sourceDataEnterer.getTime().getValue(),targetDataEnterer.getTime().getValue())
                && Objects.equals(sourceDataEnterer.getNullFlavor().getLiteral(),targetDataEnterer.getNullFlavor().getLiteral())))
            {
                comparisonResult.addMessage("Data Enterer error in " + errorMessage);
            }

            //compare assigned entity
            assignedEntityComparison(sourceDataEnterer.getAssignedEntity(),targetDataEnterer.getAssignedEntity(),errorMessage + " -> Assigned Entity");

            //compare nullFlavor
            if (!(Objects.equals(sourceDataEnterer.getNullFlavor().getLiteral(),targetDataEnterer.getNullFlavor().getLiteral())))
            {
                comparisonResult.addMessage("Null Flavor error in " + errorMessage);
            }

            //compare typeCode
            if (!(Objects.equals(sourceDataEnterer.getTypeCode().getLiteral(),targetDataEnterer.getTypeCode().getLiteral())))
            {
                comparisonResult.addMessage("Type Code error in " + errorMessage);
            }

        }
    }

    private void informantComparison(Informant12 sourceInformant12, Informant12 targetInformant12, String errorMessage) {
        if (sourceInformant12 != null && targetInformant12 != null) {
            //realm code
            compareRealmCodes(sourceInformant12.getRealmCodes(),targetInformant12.getRealmCodes(),errorMessage + " -> Realm Codes");

            //typeID

            //template ID

            //choice - assignedEntity, related entity

            //nullflavor

            //type code

            //contextControlCode

        }
    }

    private void custodianComparison(Custodian sourceCustodian, Custodian targetCustodian, String errorMessage) {
        if (sourceCustodian != null && targetCustodian != null) {
            //realm code
            compareRealmCodes(sourceCustodian.getRealmCodes(),targetCustodian.getRealmCodes(),errorMessage + " -> Realm Codes");

            //typeID
            typeIDComparison(sourceCustodian.getTypeId(),targetCustodian.getTypeId(), errorMessage + " -> type ID");

            //templateID

            //AssignedCustodian
            assignedCustodianComparison(sourceCustodian.getAssignedCustodian(),targetCustodian.getAssignedCustodian(),errorMessage + " -> Assigned Custodian");

            //nullFlavor
            if (!(Objects.equals(sourceCustodian.getNullFlavor().getLiteral(),targetCustodian.getNullFlavor().getLiteral())))
            {
                comparisonResult.addMessage("Null Flavor error in " + errorMessage);
            }

            //typeCode
            if (!(Objects.equals(sourceCustodian.getTypeCode().getLiteral(),targetCustodian.getTypeCode().getLiteral())))
            {
                comparisonResult.addMessage("Type Code error in " + errorMessage);
            }

        }
    }

    private void legalAuthenticatorComparison(LegalAuthenticator sourceLegalAuthenticator, LegalAuthenticator targetLegalAuthenticator, String errorMessage) {
        if (sourceLegalAuthenticator != null && targetLegalAuthenticator != null) {
            //compare realm code
            compareRealmCodes(sourceLegalAuthenticator.getRealmCodes(),targetLegalAuthenticator.getRealmCodes(),errorMessage + " -> Realm Codes");


            //compare typeID
            typeIDComparison(sourceLegalAuthenticator.getTypeId(),targetLegalAuthenticator.getTypeId(),errorMessage + " -> type ID");

            //compare templateID

            //compare time
            if (!(Objects.equals(sourceLegalAuthenticator.getTime().getValue(), targetLegalAuthenticator.getTime().getValue())
                && Objects.equals(sourceLegalAuthenticator.getTime().getNullFlavor(), targetLegalAuthenticator.getTime().getNullFlavor())))
            {
                comparisonResult.addMessage("Time error in " + errorMessage);
            }

            //compare signatureCode
            if (!(Objects.equals(sourceLegalAuthenticator.getSignatureCode().getCode(),targetLegalAuthenticator.getSignatureCode().getCode())
                        && Objects.equals(sourceLegalAuthenticator.getSignatureCode().getNullFlavor().getLiteral(),targetLegalAuthenticator.getSignatureCode().getNullFlavor().getLiteral())
                        && Objects.equals(sourceLegalAuthenticator.getSignatureCode().getCodeSystem(),targetLegalAuthenticator.getSignatureCode().getCodeSystem())
                        && Objects.equals(sourceLegalAuthenticator.getSignatureCode().getCodeSystemName(),targetLegalAuthenticator.getSignatureCode().getCodeSystemName())
                        && Objects.equals(sourceLegalAuthenticator.getSignatureCode().getCodeSystemVersion(),targetLegalAuthenticator.getSignatureCode().getCodeSystemVersion())
                        && Objects.equals(sourceLegalAuthenticator.getSignatureCode().getDisplayName(),targetLegalAuthenticator.getSignatureCode().getDisplayName())
                ) )
                {
                    comparisonResult.addMessage("Signature Code Comparison Error in" + errorMessage);
                }

            //compare assignedEntity
            assignedEntityComparison(sourceLegalAuthenticator.getAssignedEntity(),targetLegalAuthenticator.getAssignedEntity(),errorMessage + " Assigned Entity");

            //compare nullFlavor
            if (!(Objects.equals(sourceLegalAuthenticator.getNullFlavor().getLiteral(),targetLegalAuthenticator.getNullFlavor().getLiteral())))
            {
                comparisonResult.addMessage("Null Flavor error in " + errorMessage);
            }

            //compare typeCode
            if (!(Objects.equals(sourceLegalAuthenticator.getTypeCode().getLiteral(),targetLegalAuthenticator.getTypeCode().getLiteral())))
            {
                comparisonResult.addMessage("Type Code error in " + errorMessage);
            }

            //compare contextControlCode
            if (!(Objects.equals(sourceLegalAuthenticator.getContextControlCode().getLiteral(),targetLegalAuthenticator.getContextControlCode().getLiteral())
                && Objects.equals(sourceLegalAuthenticator.getContextControlCode().getName(),targetLegalAuthenticator.getContextControlCode().getName())))
            {
                comparisonResult.addMessage("Null Flavor error in " + errorMessage);
            }
        }
    }

    private void participant1Comparison(Participant1 sourceParticipant, Participant1 targetParticipant, String errorMessage) {
        if (sourceParticipant != null && targetParticipant != null) {
            //realmCode
            compareRealmCodes(sourceParticipant.getRealmCodes(),targetParticipant.getRealmCodes(),errorMessage + " -> Realm Codes");

            //typeID

            //templateID

            //functionCode
            if (sourceParticipant.getFunctionCode() != null && targetParticipant.getFunctionCode() != null) {
                if (! (Objects.equals(sourceParticipant.getFunctionCode().getCode(), targetParticipant.getFunctionCode().getCode()))
                        && Objects.equals(sourceParticipant.getFunctionCode().getNullFlavor(), targetParticipant.getFunctionCode().getNullFlavor()))
                {
                    comparisonResult.addMessage("Function Code error in " + errorMessage);
                }
            }

            //time
            if (! (Objects.equals(sourceParticipant.getTime().getValue(),targetParticipant.getTime().getValue())
                && Objects.equals(sourceParticipant.getTime().toString(),targetParticipant.getTime().toString())))
            {
                comparisonResult.addMessage("Time error in " + errorMessage);
            }

            //associated Entity
            associatedEntityComparison(sourceParticipant.getAssociatedEntity(), targetParticipant.getAssociatedEntity(), errorMessage + " -> Associated Entity");

            //nullflavor
            if (!(Objects.equals(sourceParticipant.getNullFlavor().getLiteral(),targetParticipant.getNullFlavor().getLiteral())))
            {
                comparisonResult.addMessage("Null Flavor error in " + errorMessage);
            }

            //typeCode
            if (!(Objects.equals(sourceParticipant.getTypeCode().getLiteral(),targetParticipant.getTypeCode().getLiteral())))
            {
                comparisonResult.addMessage("Type Code error in " + errorMessage);
            }
        }
    }

    private void documentationOfComparison(DocumentationOf sourceDocumentationOf, DocumentationOf targetDocumentationOf, String errorMessage) {
        if (sourceDocumentationOf != null && targetDocumentationOf != null) {
            //compare realmCodes
            compareRealmCodes(sourceDocumentationOf.getRealmCodes(),targetDocumentationOf.getRealmCodes(),errorMessage + " -> Realm Codes");

            //compare TypeID
            typeIDComparison(sourceDocumentationOf.getTypeId(),targetDocumentationOf.getTypeId(),errorMessage + " -> typeID");

            //compare templateID

            //compare serviceEvent
            serviceEventComparison(sourceDocumentationOf.getServiceEvent(),targetDocumentationOf.getServiceEvent(),errorMessage + " -> Service Event");

            //compare NullFlavor
            if (!(Objects.equals(sourceDocumentationOf.getNullFlavor().getLiteral(),targetDocumentationOf.getNullFlavor().getLiteral())))
            {
                comparisonResult.addMessage("Null Flavor error in " + errorMessage);
            }

            //compare typeCode
            if (!(Objects.equals(sourceDocumentationOf.getTypeCode().getLiteral(),targetDocumentationOf.getTypeCode().getLiteral())))
            {
                comparisonResult.addMessage("Type Code error in " + errorMessage);
            }
        }
    }

    private void component2Comparison(Component2 sourceComponent, Component2 targetComponent, String errorMessage) {
        if (sourceComponent != null && targetComponent != null) {
            //realmCode
            compareRealmCodes(sourceComponent.getRealmCodes(),targetComponent.getRealmCodes(),errorMessage + " -> Realm Codes");

            //typeID
            typeIDComparison(sourceComponent.getTypeId(),targetComponent.getTypeId(),errorMessage + " -> type ID");

            //templateID

            //Choice - Non XML Body, StructuredBody
            if (sourceComponent.getNonXMLBody() != null && targetComponent.getNonXMLBody() != null) {
                nonXMLBodyComparison(sourceComponent.getNonXMLBody(),targetComponent.getNonXMLBody(),errorMessage + " -> NonXMLBody");
            }

            if (sourceComponent.getStructuredBody() != null && targetComponent.getStructuredBody() != null) {
                structuredBodyComparison(sourceComponent.getStructuredBody(),targetComponent.getStructuredBody(), errorMessage + " -> Structured Body");
            }

            //nullFlavor
            if (!(Objects.equals(sourceComponent.getNullFlavor().getLiteral(),targetComponent.getNullFlavor().getLiteral())))
            {
                comparisonResult.addMessage("Null Flavor error in " + errorMessage);
            }

            //typeCode
            if (!(Objects.equals(sourceComponent.getTypeCode().getLiteral(),targetComponent.getTypeCode().getLiteral())))
            {
                comparisonResult.addMessage("Type Code error in " + errorMessage);
            }

            //contextConductionInd
            if (!(Objects.equals(sourceComponent.getContextConductionInd().toString(),targetComponent.getContextConductionInd().toString())))
            {
                comparisonResult.addMessage("Context Conduction Ind error in " + errorMessage);
            }
        }
    }

    private void patientRoleComparison(PatientRole sourcePatientRole, PatientRole targetPatientRole, String errorMessage) {
        if (sourcePatientRole != null && targetPatientRole != null) {
            //compare realmCode
            compareRealmCodes(sourcePatientRole.getRealmCodes(),targetPatientRole.getRealmCodes(),errorMessage + " -> Realm Codes");

            //compare typeID
            //typeIDComparison(sourcePatientRole.getTypeId(),targetPatientRole.getTypeId(),errorMessage = " -> typeID");

            //compare templateID
            for (int i = 0; i < sourcePatientRole.getTemplateIds().size(); i++) {
                for (int j = 0; j < targetPatientRole.getTemplateIds().size(); j++) {
                    if (!(Objects.equals(sourcePatientRole.getTemplateIds().get(i).getNullFlavor(), targetPatientRole.getTemplateIds().get(j).getNullFlavor())
                            && Objects.equals(sourcePatientRole.getTemplateIds().get(i).getRoot(), targetPatientRole.getTemplateIds().get(j).getRoot())
                            && Objects.equals(sourcePatientRole.getTemplateIds().get(i).getExtension(), targetPatientRole.getTemplateIds().get(j).getExtension())
                            && Objects.equals(sourcePatientRole.getTemplateIds().get(i).getAssigningAuthorityName(), targetPatientRole.getTemplateIds().get(j).getAssigningAuthorityName()))) {
                        comparisonResult.addMessage("Potential templateID error in " + errorMessage);
                    }
                }
            }

            //compare id
            for (int i = 0; i < sourcePatientRole.getIds().size(); i++) {
                for (int j = 0; j < targetPatientRole.getIds().size(); j++) {
                    if (!(Objects.equals(sourcePatientRole.getIds().get(i).getNullFlavor(), targetPatientRole.getIds().get(j).getNullFlavor())
                            && Objects.equals(sourcePatientRole.getIds().get(i).getRoot(), targetPatientRole.getIds().get(j).getRoot())
                            && Objects.equals(sourcePatientRole.getIds().get(i).getExtension(), targetPatientRole.getIds().get(j).getExtension())
                            && Objects.equals(sourcePatientRole.getIds().get(i).getAssigningAuthorityName(), targetPatientRole.getIds().get(j).getAssigningAuthorityName()))) {
                        comparisonResult.addMessage("Potential IDs error in " + errorMessage);
                    }
                }
            }

            //compare addr
            for (int i = 0; i < sourcePatientRole.getIds().size(); i++) {
                for (int j = 0; j < targetPatientRole.getIds().size(); j++) {
                    if (!(Objects.equals(sourcePatientRole.getAddrs().get(i).getNullFlavor(), targetPatientRole.getAddrs().get(j).getNullFlavor())
                            && Objects.equals(sourcePatientRole.getAddrs().get(i).getText(), targetPatientRole.getAddrs().get(j).getText()))) {
                        comparisonResult.addMessage("Potential addrs error in " + errorMessage);
                    }
                }
            }

            //compare telecom
            for (int i = 0; i < sourcePatientRole.getIds().size(); i++) {
                for (int j = 0; j < targetPatientRole.getIds().size(); j++) {
                    if (!(Objects.equals(sourcePatientRole.getTelecoms().get(i).getNullFlavor(), targetPatientRole.getTelecoms().get(j).getNullFlavor())
                            && Objects.equals(sourcePatientRole.getTelecoms().get(i).getValue(), targetPatientRole.getTelecoms().get(j).getValue()))) {
                        comparisonResult.addMessage("Potential telecom error in " + errorMessage);
                    }
                }
            }

            //compare Patient
            patientComparison(sourcePatientRole.getPatient(), targetPatientRole.getPatient(), errorMessage + " -> Patient");

            //compareNullFlavor
            if (!(Objects.equals(sourcePatientRole.getNullFlavor().getLiteral(), targetPatientRole.getNullFlavor().getLiteral()))) {
                comparisonResult.addMessage("Null Flavor error in " + errorMessage);
            }

            //compare ClassCode
            if (!(Objects.equals(sourcePatientRole.getClassCode().getLiteral(), targetPatientRole.getClassCode().getLiteral())
                    && Objects.equals(sourcePatientRole.getClassCode().getName(), targetPatientRole.getClassCode().getName())
                    && Objects.equals(sourcePatientRole.getClassCode().getValue(), targetPatientRole.getClassCode().getValue()))) {
                comparisonResult.addMessage("Class Code error in " + errorMessage);
            }
        }
    }

    private void assignedAuthorComparison(AssignedAuthor sourceAssignedAuthor, AssignedAuthor targetAssignedAuthor, String errorMessage) {
        if (sourceAssignedAuthor != null && targetAssignedAuthor != null) {
            //realm Code
            compareRealmCodes(sourceAssignedAuthor.getRealmCodes(),targetAssignedAuthor.getRealmCodes(),errorMessage + " -> Realm Codes");
            
            //type ID
            typeIDComparison(sourceAssignedAuthor.getTypeId(),targetAssignedAuthor.getTypeId(),errorMessage + " -> type ID");

            //templateID

            //id
            for (int i = 0; i < sourceAssignedAuthor.getIds().size(); i++) {
                for (int j = 0; j < targetAssignedAuthor.getIds().size(); j++) {
                    if (!(Objects.equals(sourceAssignedAuthor.getIds().get(i).getNullFlavor(), targetAssignedAuthor.getIds().get(j).getNullFlavor())
                            && Objects.equals(sourceAssignedAuthor.getIds().get(i).getRoot(), targetAssignedAuthor.getIds().get(j).getRoot())
                            && Objects.equals(sourceAssignedAuthor.getIds().get(i).getExtension(), targetAssignedAuthor.getIds().get(j).getExtension())
                            && Objects.equals(sourceAssignedAuthor.getIds().get(i).getAssigningAuthorityName(), targetAssignedAuthor.getIds().get(j).getAssigningAuthorityName()))) {
                        comparisonResult.addMessage("Potential IDs error in " + errorMessage);
                    }
                }
            }

            //code
            if (!(Objects.equals(sourceAssignedAuthor.getCode().getCode(),targetAssignedAuthor.getCode().getCode())
                    && Objects.equals(sourceAssignedAuthor.getCode().getNullFlavor().getLiteral(),targetAssignedAuthor.getCode().getNullFlavor().getLiteral())
                    && Objects.equals(sourceAssignedAuthor.getCode().getCodeSystem(),targetAssignedAuthor.getCode().getCodeSystem())
                    && Objects.equals(sourceAssignedAuthor.getCode().getCodeSystemName(),targetAssignedAuthor.getCode().getCodeSystemName())
                    && Objects.equals(sourceAssignedAuthor.getCode().getCodeSystemVersion(),targetAssignedAuthor.getCode().getCodeSystemVersion())
                    && Objects.equals(sourceAssignedAuthor.getCode().getDisplayName(),targetAssignedAuthor.getCode().getDisplayName())) )
            {
                comparisonResult.addMessage("Code Comparison error in " + errorMessage);
            }

            //addr

            //telecom

            //choice - assignedPerson (Person), assignedAuthorizing Device(AUthorizing Device)

            //represented Organization

            //null flavor

            //class code
        }
    }

    private void patientComparison(Patient sourcePatient, Patient targetPatient, String errorMessage) {
    }

    private void assignedEntityComparison(AssignedEntity sourceAssignedEntity, AssignedEntity targetAssignedEntity, String errorMessage) {
    }

    private void assignedCustodianComparison(AssignedCustodian sourceAssignedCustodian, AssignedCustodian targetAssignedCustodian, String errorMessage) {
    }

    private void associatedEntityComparison(AssociatedEntity sourceAssignedEntity, AssociatedEntity targetAssignedEntity, String errorMessage) {
    }

    private void serviceEventComparison(ServiceEvent sourceServiceEvent, ServiceEvent targetServiceEvent, String errorMessage) {
    }

    private void nonXMLBodyComparison(NonXMLBody sourceNonXMLBody, NonXMLBody targetNonXMLBody, String errorMessage) {
    }

    private void structuredBodyComparison(StructuredBody sourceStructuredBody, StructuredBody targetStructuredBody, String errorMessage) {
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //non complex type comparison Methods
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void compareRealmCodes(EList<CS> source, EList<CS> target, String errorMessage) {

        for (int i=0; i<source.size(); i++) {
            for (int j=0; j<target.size(); j++) {
                if (!(Objects.equals(source.get(i).getCode(),target.get(0).getCode())
                        && Objects.equals(source.get(i).getNullFlavor().getLiteral(),target.get(j).getNullFlavor().getLiteral())
                        && Objects.equals(source.get(i).getCodeSystem(),target.get(j).getCodeSystem())
                        && Objects.equals(source.get(i).getCodeSystemName(),target.get(j).getCodeSystemName())
                        && Objects.equals(source.get(i).getCodeSystemVersion(),target.get(j).getCodeSystemVersion())
                        && Objects.equals(source.get(i).getDisplayName(),target.get(j).getDisplayName())
                ) )
                {
                    comparisonResult.addMessage("RealmCode Comparison Error in" + errorMessage);
                }
            }
        }
    }



}
