package com.github.aks8m;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.openhealthtools.mdht.uml.cda.*;
import org.openhealthtools.mdht.uml.hl7.datatypes.AD;
import org.openhealthtools.mdht.uml.hl7.datatypes.CS;
import org.openhealthtools.mdht.uml.hl7.datatypes.II;
import org.openhealthtools.mdht.uml.hl7.datatypes.TEL;
import org.openhealthtools.mdht.uml.hl7.vocab.NullFlavor;
import org.openhealthtools.mdht.uml.hl7.vocab.RoleClass;

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
        typeIDComparison(sourceClinicalDocument.getTypeId(), targetClinicalDocument.getTypeId(),errorMessage + " -> typeIDs");

        //compare templateID
        compareTemplateID(sourceClinicalDocument.getTemplateIds(), targetClinicalDocument.getTemplateIds(),errorMessage + " -> Template IDS");


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
        compareNullFlavor(sourceClinicalDocument.getNullFlavor(),targetClinicalDocument.getNullFlavor(),errorMessage + " -> NullFlavor");

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

            //compareTemplateID
            compareTemplateID(sourceRecordTarget.getTemplateIds(), targetRecordTarget.getTemplateIds(),errorMessage + " -> Template IDS");


            //comparePatientRole
            patientRoleComparison(sourceRecordTarget.getPatientRole(), targetRecordTarget.getPatientRole(), errorMessage + " -> Patient Role");

            //compare nullFlavor
            compareNullFlavor(sourceRecordTarget.getNullFlavor(),targetRecordTarget.getNullFlavor(),errorMessage + " -> Null Flavor");

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
            compareTemplateID(sourceAuthor.getTemplateIds(), targetAuthor.getTemplateIds(),errorMessage + " -> Template IDS");


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
            compareNullFlavor(sourceAuthor.getNullFlavor(),targetAuthor.getNullFlavor(),errorMessage + " -> Null Flavor");


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
            compareTemplateID(sourceDataEnterer.getTemplateIds(), targetDataEnterer.getTemplateIds(),errorMessage + " -> Template IDS");

            //compare time
            if (!(Objects.equals(sourceDataEnterer.getTime().getValue(),targetDataEnterer.getTime().getValue())
                && Objects.equals(sourceDataEnterer.getNullFlavor().getLiteral(),targetDataEnterer.getNullFlavor().getLiteral())))
            {
                comparisonResult.addMessage("Data Enterer error in " + errorMessage);
            }

            //compare assigned entity
            assignedEntityComparison(sourceDataEnterer.getAssignedEntity(),targetDataEnterer.getAssignedEntity(),errorMessage + " -> Assigned Entity");

            //compare nullFlavor
            compareNullFlavor(sourceDataEnterer.getNullFlavor(),targetDataEnterer.getNullFlavor(),errorMessage + " -> Null Flavor");


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
            compareTemplateID(sourceInformant12.getTemplateIds(), targetInformant12.getTemplateIds(),errorMessage + " -> Template IDS");

            //choice - assignedEntity, related entity

            //nullflavor
            compareNullFlavor(sourceInformant12.getNullFlavor(),targetInformant12.getNullFlavor(),errorMessage + " -> Null Flavor");

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
            compareTemplateID(sourceCustodian.getTemplateIds(), targetCustodian.getTemplateIds(),errorMessage + " -> Template IDS");

            //AssignedCustodian
            assignedCustodianComparison(sourceCustodian.getAssignedCustodian(),targetCustodian.getAssignedCustodian(),errorMessage + " -> Assigned Custodian");

            //nullFlavor
            compareNullFlavor(sourceCustodian.getNullFlavor(),targetCustodian.getNullFlavor(),errorMessage + " -> Null Flavor");


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
            compareTemplateID(sourceLegalAuthenticator.getTemplateIds(), targetLegalAuthenticator.getTemplateIds(),errorMessage + " -> Template IDS");


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
            compareNullFlavor(sourceLegalAuthenticator.getNullFlavor(),targetLegalAuthenticator.getNullFlavor(),errorMessage + " -> Null Flavor");

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
            compareTemplateID(sourceParticipant.getTemplateIds(), targetParticipant.getTemplateIds(),errorMessage + " -> Template IDS");


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
            compareNullFlavor(sourceParticipant.getNullFlavor(),targetParticipant.getNullFlavor(),errorMessage + " -> Null Flavor");


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
            compareTemplateID(sourceDocumentationOf.getTemplateIds(), targetDocumentationOf.getTemplateIds(),errorMessage + " -> Template IDS");


            //compare serviceEvent
            serviceEventComparison(sourceDocumentationOf.getServiceEvent(),targetDocumentationOf.getServiceEvent(),errorMessage + " -> Service Event");

            //compare NullFlavor
            compareNullFlavor(sourceDocumentationOf.getNullFlavor(),targetDocumentationOf.getNullFlavor(),errorMessage + " -> Null Flavor");


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
            compareTemplateID(sourceComponent.getTemplateIds(), targetComponent.getTemplateIds(),errorMessage + " -> Template IDS");


            //Choice - Non XML Body, StructuredBody
            if (sourceComponent.getNonXMLBody() != null && targetComponent.getNonXMLBody() != null) {
                nonXMLBodyComparison(sourceComponent.getNonXMLBody(),targetComponent.getNonXMLBody(),errorMessage + " -> NonXMLBody");
            }

            if (sourceComponent.getStructuredBody() != null && targetComponent.getStructuredBody() != null) {
                structuredBodyComparison(sourceComponent.getStructuredBody(),targetComponent.getStructuredBody(), errorMessage + " -> Structured Body");
            }

            //nullFlavor
            compareNullFlavor(sourceComponent.getNullFlavor(),targetComponent.getNullFlavor(),errorMessage + " -> Null Flavor");


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
            compareTemplateID(sourcePatientRole.getTemplateIds(), targetPatientRole.getTemplateIds(),errorMessage + " -> Template IDS");


            //compare id
            compareIDs(sourcePatientRole.getIds(),targetPatientRole.getIds(),errorMessage + " -> IDs");

            //compare addr
            compareAddr(sourcePatientRole.getAddrs(),targetPatientRole.getAddrs(),errorMessage + " -> Addrs");

            //compare telecom
            compareTelcom(sourcePatientRole.getTelecoms(),targetPatientRole.getTelecoms(), errorMessage + " -> Telecoms");

            //compare Patient
            patientComparison(sourcePatientRole.getPatient(), targetPatientRole.getPatient(), errorMessage + " -> Patient");

            //compareNullFlavor
            compareNullFlavor(sourcePatientRole.getNullFlavor(),targetPatientRole.getNullFlavor(), errorMessage + " -> NullFlavor");

            //compare ClassCode
            compareClassCode(sourcePatientRole.getClassCode(),targetPatientRole.getClassCode(), errorMessage + " -> ClassCode");
        }
    }


    private void assignedAuthorComparison(AssignedAuthor sourceAssignedAuthor, AssignedAuthor targetAssignedAuthor, String errorMessage) {
        if (sourceAssignedAuthor != null && targetAssignedAuthor != null) {
            //realm Code
            compareRealmCodes(sourceAssignedAuthor.getRealmCodes(),targetAssignedAuthor.getRealmCodes(),errorMessage + " -> Realm Codes");

            //type ID
            typeIDComparison(sourceAssignedAuthor.getTypeId(),targetAssignedAuthor.getTypeId(),errorMessage + " -> type ID");

            //templateID
            compareTemplateID(sourceAssignedAuthor.getTemplateIds(), targetAssignedAuthor.getTemplateIds(),errorMessage + " -> Template IDS");

            //ids
            compareIDs(sourceAssignedAuthor.getIds(),targetAssignedAuthor.getIds(),errorMessage + " -> IDs");

            //code
//            if (!(Objects.equals(sourceAssignedAuthor.getCode().getCode(),targetAssignedAuthor.getCode().getCode())
//                    && Objects.equals(sourceAssignedAuthor.getCode().getNullFlavor().getLiteral(),targetAssignedAuthor.getCode().getNullFlavor().getLiteral())
//                    && Objects.equals(sourceAssignedAuthor.getCode().getCodeSystem(),targetAssignedAuthor.getCode().getCodeSystem())
//                    && Objects.equals(sourceAssignedAuthor.getCode().getCodeSystemName(),targetAssignedAuthor.getCode().getCodeSystemName())
//                    && Objects.equals(sourceAssignedAuthor.getCode().getCodeSystemVersion(),targetAssignedAuthor.getCode().getCodeSystemVersion())
//                    && Objects.equals(sourceAssignedAuthor.getCode().getDisplayName(),targetAssignedAuthor.getCode().getDisplayName())) )
//            {
//                comparisonResult.addMessage("Code Comparison error in " + errorMessage);
//            }

            //addr
            compareAddr(sourceAssignedAuthor.getAddrs(),targetAssignedAuthor.getAddrs(),errorMessage + " -> Addrs");

            //telecom
            compareTelcom(sourceAssignedAuthor.getTelecoms(),targetAssignedAuthor.getTelecoms(), errorMessage + " -> telecom");

            //choice - assignedPerson (Person), assignedAuthorizing Device(AUthorizing Device)

            //represented Organization

            //null flavor
            compareNullFlavor(sourceAssignedAuthor.getNullFlavor(),targetAssignedAuthor.getNullFlavor(),errorMessage + " -> Null Flavor");

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

    private void compareTemplateID(EList<II> source, EList<II> target, String errorMessage) {
        for (int i = 0; i < source.size(); i++) {
            for (int j = 0; j < target.size(); j++) {
                if (!(Objects.equals(source.get(i).getNullFlavor(), target.get(j).getNullFlavor())
                        && Objects.equals(source.get(i).getRoot(), target.get(j).getRoot())
                        && Objects.equals(source.get(i).getExtension(), target.get(j).getExtension())
                        && Objects.equals(source.get(i).getAssigningAuthorityName(), target.get(j).getAssigningAuthorityName()))) {
                    comparisonResult.addMessage("Potential templateID error in " + errorMessage);
                }
            }
        }
    }

    private void compareIDs(EList<II> source, EList<II> target, String errorMessage) {
        for (int i = 0; i < source.size(); i++) {
            for (int j = 0; j < target.size(); j++) {
                if (!(Objects.equals(source.get(i).getNullFlavor(), target.get(j).getNullFlavor())
                        && Objects.equals(source.get(i).getRoot(), target.get(j).getRoot())
                        && Objects.equals(source.get(i).getExtension(), target.get(j).getExtension())
                        && Objects.equals(source.get(i).getAssigningAuthorityName(), target.get(j).getAssigningAuthorityName()))) {
                    comparisonResult.addMessage("Potential IDs error in " + errorMessage);
                }
            }
        }
    }

    private void compareAddr(EList<AD> source, EList<AD> target, String errorMessage) {
        for (int i = 0; i < source.size(); i++) {
            for (int j = 0; j < target.size(); j++) {
                if (!(Objects.equals(source.get(i).getNullFlavor(), target.get(j).getNullFlavor())
                        && Objects.equals(source.get(i).getText(), target.get(j).getText()))) {
                    comparisonResult.addMessage("Potential addrs error in " + errorMessage);
                }
            }
        }

    }

    private void compareTelcom(EList<TEL> source, EList<TEL> target, String errorMessage) {
        for (int i = 0; i < source.size(); i++) {
            for (int j = 0; j < target.size(); j++) {
                if (!(Objects.equals(source.get(i).getNullFlavor(), target.get(j).getNullFlavor())
                        && Objects.equals(source.get(i).getValue(), target.get(j).getValue()))) {
                    comparisonResult.addMessage("Potential telecoms error in " + errorMessage);
                }
            }
        }
    }

    private void compareNullFlavor(NullFlavor source, NullFlavor target, String errorMessage) {
        if (!(Objects.equals(source.getLiteral(), target.getLiteral()))) {
            comparisonResult.addMessage("Null Flavor error in " + errorMessage);
        }
    }

    private void compareClassCode(RoleClass source, RoleClass target, String errorMessage) {
        if (!(Objects.equals(source.getLiteral(), target.getLiteral())
                && Objects.equals(source.getName(), target.getName())
                && Objects.equals(source.getValue(), target.getValue()))) {
            comparisonResult.addMessage("Class Code error in " + errorMessage);
        }
    }

}
