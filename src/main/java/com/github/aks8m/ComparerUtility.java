package com.github.aks8m;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.openhealthtools.mdht.uml.cda.*;
import org.openhealthtools.mdht.uml.hl7.datatypes.*;
import org.openhealthtools.mdht.uml.hl7.vocab.*;
import sun.applet.Main;

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

        //compare RealmCode
        compareRealmCodes(sourceClinicalDocument.getRealmCodes(),targetClinicalDocument.getRealmCodes(),errorMessage + " -> Realm Codes");

        //compare typeID
        typeIDComparison(sourceClinicalDocument.getTypeId(), targetClinicalDocument.getTypeId(),errorMessage + " -> typeIDs");

        //compare templateID
        compareTemplateID(sourceClinicalDocument.getTemplateIds(), targetClinicalDocument.getTemplateIds(),errorMessage + " -> Template IDS");

        //compareID
        compareID(sourceClinicalDocument.getId(),targetClinicalDocument.getId(),errorMessage + " -> ID");

        //compare code
        compareCode(sourceClinicalDocument.getCode(),targetClinicalDocument.getCode(),errorMessage + " -> Code");

        //compare title
        compareTitle(sourceClinicalDocument.getTitle(),targetClinicalDocument.getTitle(), errorMessage + " -> Title");

        //compare Effective Time
        compareEffectiveTime(sourceClinicalDocument.getEffectiveTime(),targetClinicalDocument.getEffectiveTime(),errorMessage + " -> Effective Time");

        //compare confidentiality code
        compareConfidentialityCode(sourceClinicalDocument.getConfidentialityCode(),targetClinicalDocument.getConfidentialityCode(), errorMessage + " -> Confidentiality Code");

        //compare language code
        compareLanguageCode(sourceClinicalDocument.getLanguageCode(),targetClinicalDocument.getLanguageCode(),errorMessage + " -> Language Code");

        //compare setID
        compareSetID(sourceClinicalDocument.getSetId(),targetClinicalDocument.getSetId(),errorMessage + " -> Set ID");

        //compare versionNumber
        compareVersionNumber(sourceClinicalDocument.getVersionNumber(),targetClinicalDocument.getVersionNumber(),errorMessage + " -> Version Number");

        //compare copyTime
        compareCopyTime(sourceClinicalDocument.getCopyTime(),targetClinicalDocument.getCopyTime(),errorMessage + " -> Copy Time");

        //compare Record Targets
        recordTargetsComparison(sourceClinicalDocument.getRecordTargets(), targetClinicalDocument.getRecordTargets(), errorMessage + " -> Record Targets");

        //compare Authors
        authorsComparison(sourceClinicalDocument.getAuthors(), targetClinicalDocument.getAuthors(), errorMessage + " -> Authors");

        //compare Data Enterer
        dataEntererComparison(sourceClinicalDocument.getDataEnterer(), targetClinicalDocument.getDataEnterer(),errorMessage + " -> Data Enterer");

        //compare informants
        informantsComparison(sourceClinicalDocument.getInformants(), targetClinicalDocument.getInformants(), errorMessage + " -> Informant");

        //compare custodian
        custodianComparison(sourceClinicalDocument.getCustodian(), targetClinicalDocument.getCustodian(),errorMessage + " -> Custodian");

        //compare information recipient
        informationRecipientComparison(sourceClinicalDocument.getInformationRecipients(),targetClinicalDocument.getInformationRecipients(),errorMessage + " -> Information Recipients");

        //compare Legal Authenticator
        legalAuthenticatorComparison(sourceClinicalDocument.getLegalAuthenticator(), targetClinicalDocument.getLegalAuthenticator(),errorMessage + " -> Legal Authenticator");

        //compare Authenticators
        authenticatorComparison(sourceClinicalDocument.getAuthenticators(),targetClinicalDocument.getAuthenticators(),errorMessage + " -> Authenticators");

        //compare participants
        participants1Comparison(sourceClinicalDocument.getParticipants(),targetClinicalDocument.getParticipants(),errorMessage + " -> Participants");

        //compare getInFulfullmentOf
        inFulfillmentOfComparison(sourceClinicalDocument.getInFulfillmentOfs(),targetClinicalDocument.getInFulfillmentOfs(),errorMessage + " -> In Fullfillments Of");

        //compare documentationOf
        documentationOfsComparison(sourceClinicalDocument.getDocumentationOfs(), targetClinicalDocument.getDocumentationOfs(), errorMessage + " -> Documentation");

        //compare getRelatedDocuments
        relatedDocumentsComparison(sourceClinicalDocument.getRelatedDocuments(),targetClinicalDocument.getRelatedDocuments(), errorMessage + " -> Related Documents");

        //compare Authorizations
        authorizationsComparison(sourceClinicalDocument.getAuthorizations(),targetClinicalDocument.getAuthorizations(),errorMessage + " -> Authorizations");

        //compare componentOf
        componentOfComparison(sourceClinicalDocument.getComponentOf(),targetClinicalDocument.getComponentOf(),errorMessage + " -> componentOf");

        //compare component
        component2Comparison(sourceClinicalDocument.getComponent(), targetClinicalDocument.getComponent(),errorMessage + " -> Component");

        //compare nullFlavor
        compareNullFlavor(sourceClinicalDocument.getNullFlavor(),targetClinicalDocument.getNullFlavor(),errorMessage + " -> NullFlavor");

        //compare classCode
        compareClassCode(sourceClinicalDocument.getClassCode(),targetClinicalDocument.getClassCode(),errorMessage + " -> Class Code");
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //complex type comparison Methods
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void typeIDComparison(InfrastructureRootTypeId sourceTypeID, InfrastructureRootTypeId targetTypeID, String errorMessage) {
        if (sourceTypeID != null && targetTypeID != null) {
            if (!(Objects.equals(sourceTypeID.getRoot(), targetTypeID.getRoot())
                    && Objects.equals(sourceTypeID.getAssigningAuthorityName(), targetTypeID.getAssigningAuthorityName())
                    && Objects.equals(sourceTypeID.getExtension(), targetTypeID.getExtension()))) {
                comparisonResult.addMessage("TypeID error in" + errorMessage + "\n");
            }
        }
    }

    private void recordTargetsComparison(EList<RecordTarget> sourceRecordTargets, EList<RecordTarget> targetRecordTargets, String errorMessage) {
        for (int i = 0; i < sourceRecordTargets.size(); i++) {
            for (int j = 0; j < targetRecordTargets.size(); j++) {
                //compare realmCode
                compareRealmCodes(sourceRecordTargets.get(i).getRealmCodes(), targetRecordTargets.get(j).getRealmCodes(), errorMessage + " -> Realm Codes");

                //compare TypeID
                typeIDComparison(sourceRecordTargets.get(i).getTypeId(), targetRecordTargets.get(j).getTypeId(), errorMessage + " -> Type ID");

                //compareTemplateID
                compareTemplateID(sourceRecordTargets.get(i).getTemplateIds(), targetRecordTargets.get(j).getTemplateIds(), errorMessage + " -> Template IDS");

                //comparePatientRole
                patientRoleComparison(sourceRecordTargets.get(i).getPatientRole(), targetRecordTargets.get(j).getPatientRole(), errorMessage + " -> Patient Role");

                //compare nullFlavor
                compareNullFlavor(sourceRecordTargets.get(i).getNullFlavor(), targetRecordTargets.get(j).getNullFlavor(), errorMessage + " -> Null Flavor");

                //compare typeCode
                compareTypeCode(sourceRecordTargets.get(i).getTypeCode(), targetRecordTargets.get(j).getTypeCode(), errorMessage + " -> Type Code");
            }
        }

    }

    private void authorsComparison(EList<Author> sourceAuthors, EList<Author> targetAuthors, String errorMessage) {
        for (int i=0; i<sourceAuthors.size(); i++) {
            for (int j = 0; j < targetAuthors.size(); j++) {
                //compare realm codes
                compareRealmCodes(sourceAuthors.get(i).getRealmCodes(), targetAuthors.get(j).getRealmCodes(), errorMessage + " -> Realm Codes");

                //compare typeID
                typeIDComparison(sourceAuthors.get(i).getTypeId(), targetAuthors.get(j).getTypeId(), errorMessage + " -> typeID");

                //compare template ID
                compareTemplateID(sourceAuthors.get(i).getTemplateIds(), targetAuthors.get(j).getTemplateIds(), errorMessage + " -> Template IDS");

                //compare functionCode
                compareFunctionCode(sourceAuthors.get(i).getFunctionCode(),targetAuthors.get(j).getFunctionCode(),errorMessage + " -> Function Code");

                //compare time
                compareTime(sourceAuthors.get(i).getTime(), targetAuthors.get(j).getTime(), errorMessage + " -> Time");

                //compare AssignedAuthor
                assignedAuthorComparison(sourceAuthors.get(i).getAssignedAuthor(), targetAuthors.get(j).getAssignedAuthor(), errorMessage + " -> Assigned Author");

                //compare nullFlavor
                compareNullFlavor(sourceAuthors.get(i).getNullFlavor(), targetAuthors.get(j).getNullFlavor(), errorMessage + " -> Null Flavor");

                //compare typeCode
                compareTypeCode(sourceAuthors.get(i).getTypeCode(), targetAuthors.get(j).getTypeCode(), errorMessage + " -> Type Code");

                //compare contextControlCode
                compareContextControlCode(sourceAuthors.get(i).getContextControlCode(), targetAuthors.get(j).getContextControlCode(), errorMessage + " -> Context Control Code");
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
            compareTime(sourceDataEnterer.getTime(),targetDataEnterer.getTime(),errorMessage + " -> Time");

            //compare assigned entity
            assignedEntityComparison(sourceDataEnterer.getAssignedEntity(),targetDataEnterer.getAssignedEntity(),errorMessage + " -> Assigned Entity");

            //compare nullFlavor
            compareNullFlavor(sourceDataEnterer.getNullFlavor(),targetDataEnterer.getNullFlavor(),errorMessage + " -> Null Flavor");

            //compare typeCode
            compareTypeCode(sourceDataEnterer.getTypeCode(),targetDataEnterer.getTypeCode(),errorMessage + " -> Type Code");

        }
    }

    private void informantsComparison(EList<Informant12> sourceInformant12, EList<Informant12> targetInformant12, String errorMessage) {
        for (int i=0; i<sourceInformant12.size(); i++) {
            for (int j = 0; j < sourceInformant12.size(); j++) {
                    //realm code
                    compareRealmCodes(sourceInformant12.get(i).getRealmCodes(), targetInformant12.get(j).getRealmCodes(), errorMessage + " -> Realm Codes");

                    //typeID
                    typeIDComparison(sourceInformant12.get(i).getTypeId(), targetInformant12.get(j).getTypeId(), errorMessage + " -> Type ID");

                    //template ID
                    compareTemplateID(sourceInformant12.get(i).getTemplateIds(), targetInformant12.get(j).getTemplateIds(), errorMessage + " -> Template IDS");

                    //choice - assignedEntity, related entity
                    if (sourceInformant12.get(i).getAssignedEntity() != null && targetInformant12.get(j).getAssignedEntity() != null) {
                        assignedEntityComparison(sourceInformant12.get(i).getAssignedEntity(), targetInformant12.get(j).getAssignedEntity(), errorMessage + " -> Assigned Entity");
                    }

                    if (sourceInformant12.get(i).getRelatedEntity() != null && sourceInformant12.get(j).getRelatedEntity() != null) {
                        relatedEntityComparison(sourceInformant12.get(i).getRelatedEntity(), targetInformant12.get(j).getRelatedEntity(), errorMessage + " -> Related Entity");
                    }

                    //nullflavor
                    compareNullFlavor(sourceInformant12.get(i).getNullFlavor(), targetInformant12.get(j).getNullFlavor(), errorMessage + " -> Null Flavor");

                    //type code
                    compareTypeCode(sourceInformant12.get(i).getTypeCode(), targetInformant12.get(j).getTypeCode(), errorMessage + " -> Type Code");

                    //contextControlCode
                    compareContextControlCode(sourceInformant12.get(i).getContextControlCode(), targetInformant12.get(j).getContextControlCode(), errorMessage + " -> Context Control Code");

            }
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
            compareTypeCode(sourceCustodian.getTypeCode(),targetCustodian.getTypeCode(),errorMessage + " -> Type Code");

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
            compareTime(sourceLegalAuthenticator.getTime(),targetLegalAuthenticator.getTime(),errorMessage + " -> Time");

            //compare signatureCode
            compareSignatureCode(sourceLegalAuthenticator.getSignatureCode(),targetLegalAuthenticator.getSignatureCode(),errorMessage + " -> Signature Code");

            //compare assignedEntity
            assignedEntityComparison(sourceLegalAuthenticator.getAssignedEntity(),targetLegalAuthenticator.getAssignedEntity(),errorMessage + " Assigned Entity");

            //compare nullFlavor
            compareNullFlavor(sourceLegalAuthenticator.getNullFlavor(),targetLegalAuthenticator.getNullFlavor(),errorMessage + " -> Null Flavor");

            //compare typeCode
            compareTypeCode(sourceLegalAuthenticator.getTypeCode(),targetLegalAuthenticator.getTypeCode(),errorMessage + " -> Type Code");

            //compare contextControlCode
            compareContextControlCode(sourceLegalAuthenticator.getContextControlCode(),targetLegalAuthenticator.getContextControlCode(), errorMessage + " -> Context Control Code");
        }
    }

    private void participants1Comparison(EList<Participant1> sourceParticipant, EList<Participant1> targetParticipant, String errorMessage) {
        for (int i=0;i<sourceParticipant.size();i++) {
            for (int j = 0; j < targetParticipant.size(); j++) {
                    //realmCode
                    compareRealmCodes(sourceParticipant.get(i).getRealmCodes(), targetParticipant.get(j).getRealmCodes(), errorMessage + " -> Realm Codes");

                    //typeID
                    typeIDComparison(sourceParticipant.get(i).getTypeId(), targetParticipant.get(j).getTypeId(), errorMessage + " - Type ID");

                    //templateID
                    compareTemplateID(sourceParticipant.get(i).getTemplateIds(), targetParticipant.get(j).getTemplateIds(), errorMessage + " -> Template IDS");

                    //functionCode
                    compareFunctionCode(sourceParticipant.get(i).getFunctionCode(),targetParticipant.get(j).getFunctionCode(),errorMessage + " -> Function Code");

                    //time
                    compareTime(sourceParticipant.get(i).getTime(), targetParticipant.get(j).getTime(), errorMessage + " -> Time");

                    //associated Entity
                    associatedEntityComparison(sourceParticipant.get(i).getAssociatedEntity(), targetParticipant.get(j).getAssociatedEntity(), errorMessage + " -> Associated Entity");

                    //nullFlavor
                    compareNullFlavor(sourceParticipant.get(i).getNullFlavor(), targetParticipant.get(j).getNullFlavor(), errorMessage + " -> Null Flavor");

                    //typeCode
                    compareTypeCode(sourceParticipant.get(i).getTypeCode(), targetParticipant.get(j).getTypeCode(), errorMessage + " -> Type Code");
            }
        }
    }

    private void documentationOfsComparison(EList<DocumentationOf> sourceDocumentationOf, EList<DocumentationOf> targetDocumentationOf, String errorMessage) {
        for (int i=0;i<sourceDocumentationOf.size();i++) {
            for (int j = 0; j < targetDocumentationOf.size(); j++) {
                    //compare realmCodes
                    compareRealmCodes(sourceDocumentationOf.get(i).getRealmCodes(), targetDocumentationOf.get(j).getRealmCodes(), errorMessage + " -> Realm Codes");

                    //compare TypeID
                    typeIDComparison(sourceDocumentationOf.get(i).getTypeId(), targetDocumentationOf.get(j).getTypeId(), errorMessage + " -> typeID");

                    //compare templateID
                    compareTemplateID(sourceDocumentationOf.get(i).getTemplateIds(), targetDocumentationOf.get(j).getTemplateIds(), errorMessage + " -> Template IDS");

                    //compare serviceEvent
                    serviceEventComparison(sourceDocumentationOf.get(i).getServiceEvent(), targetDocumentationOf.get(j).getServiceEvent(), errorMessage + " -> Service Event");

                    //compare NullFlavor
                    compareNullFlavor(sourceDocumentationOf.get(i).getNullFlavor(), targetDocumentationOf.get(j).getNullFlavor(), errorMessage + " -> Null Flavor");

                    //compare typeCode
                    compareTypeCode(sourceDocumentationOf.get(i).getTypeCode(), targetDocumentationOf.get(j).getTypeCode(), errorMessage + " -> Type Code");

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
            compareTypeCode(sourceComponent.getTypeCode(),targetComponent.getTypeCode(),errorMessage + " -> Type Code");

            //contextConductionInd
            compareContextConductionInd(sourceComponent.getContextConductionInd(),targetComponent.getContextConductionInd(),errorMessage + " -> Context Conduction Ind");
        }
    }

    private void patientRoleComparison(PatientRole sourcePatientRole, PatientRole targetPatientRole, String errorMessage) {
        if (sourcePatientRole != null && targetPatientRole != null) {
            //compare realmCode
            compareRealmCodes(sourcePatientRole.getRealmCodes(),targetPatientRole.getRealmCodes(),errorMessage + " -> Realm Codes");

            //compare typeID
            typeIDComparison(sourcePatientRole.getTypeId(),targetPatientRole.getTypeId(),errorMessage = " -> Type ID");

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
            compareCode(sourceAssignedAuthor.getCode(),targetAssignedAuthor.getCode(),errorMessage + " -> Compare Code");

            //addr
            compareAddr(sourceAssignedAuthor.getAddrs(),targetAssignedAuthor.getAddrs(),errorMessage + " -> Addrs");

            //telecom
            compareTelcom(sourceAssignedAuthor.getTelecoms(),targetAssignedAuthor.getTelecoms(), errorMessage + " -> telecom");

            //choice - assignedPerson (Person), assignedAuthorizing Device(AUthorizing Device)
            if (sourceAssignedAuthor.getAssignedPerson() != null && targetAssignedAuthor.getAssignedPerson() != null) {
                personComparison(sourceAssignedAuthor.getAssignedPerson(),targetAssignedAuthor.getAssignedPerson(),errorMessage + " -> Assigned Person");
            }

            if (sourceAssignedAuthor.getAssignedAuthoringDevice() != null && targetAssignedAuthor.getAssignedAuthoringDevice() != null) {
                authorizingDeviceComparison(sourceAssignedAuthor.getAssignedAuthoringDevice(),targetAssignedAuthor.getAssignedAuthoringDevice(),errorMessage + " -> Assigned Authorizing Device");
            }

            //represented Organization
            organizationComparison(sourceAssignedAuthor.getRepresentedOrganization(),targetAssignedAuthor.getRepresentedOrganization(),errorMessage + " -> Represented Organization");

            //null flavor
            compareNullFlavor(sourceAssignedAuthor.getNullFlavor(),targetAssignedAuthor.getNullFlavor(),errorMessage + " -> Null Flavor");

            //class code
            compareClassCode(sourceAssignedAuthor.getClassCode(),targetAssignedAuthor.getClassCode(),errorMessage + " -> Class Code");

        }
    }

    private void patientComparison(Patient sourcePatient, Patient targetPatient, String errorMessage) {
        if (sourcePatient != null && targetPatient != null) {
            //realm code
            compareRealmCodes(sourcePatient.getRealmCodes(),targetPatient.getRealmCodes(),errorMessage + " -> Realm Codes");

            //typeID
            typeIDComparison(sourcePatient.getTypeId(),targetPatient.getTypeId(),errorMessage + " -> type ID");

            //templateID
            compareTemplateID(sourcePatient.getTemplateIds(),targetPatient.getTemplateIds(),errorMessage + " -> Template IDs");

            //ids
            compareIDs(sourcePatient.getIds(),targetPatient.getIds(),errorMessage + " -> IDs");

            //names
            compareNames(sourcePatient.getNames(),targetPatient.getNames(),errorMessage + " -> Names");

            //administrativeGenderCode
            compareAdministrativeGenderCode(sourcePatient.getAdministrativeGenderCode(),targetPatient.getAdministrativeGenderCode(),errorMessage + " -> Administrative Gender Code");

            //birthTime
            compareBirthTime(sourcePatient.getBirthTime(),targetPatient.getBirthTime(),errorMessage + " -> Birth Time");

            //marital status code
            compareMaritalStatusCode(sourcePatient.getMaritalStatusCode(),targetPatient.getMaritalStatusCode(),errorMessage + " -> Marital Status Code");

            //religious affiliation code
            compareReligiosAffiliationCode(sourcePatient.getReligiousAffiliationCode(),targetPatient.getReligiousAffiliationCode(),errorMessage + " -> Religious Affiliation Code");

            //race Code
            compareRaceCode(sourcePatient.getRaceCode(),targetPatient.getRaceCode(),errorMessage + " -> Race Code");

            //ethnic group code
            compareEthnicGroupCode(sourcePatient.getEthnicGroupCode(),targetPatient.getEthnicGroupCode(),errorMessage + " -> Ethnic Group Code");

            //guardian - Guardian
            guardiansComparison(sourcePatient.getGuardians(),targetPatient.getGuardians(),errorMessage + " -> Guardians");

            //birthPlace - Birthplace
            birthplaceComparison(sourcePatient.getBirthplace(),targetPatient.getBirthplace(),errorMessage + " -> Birthplace");

            //Language Communication - Language Communication
            languageCommunicationsComparison(sourcePatient.getLanguageCommunications(),targetPatient.getLanguageCommunications(),errorMessage + " -> Language Communication");

            //nullFlavor
            compareNullFlavor(sourcePatient.getNullFlavor(),targetPatient.getNullFlavor(),errorMessage + " -> Null Flavor");

            //classCode
            compareClassCode(sourcePatient.getClassCode(),targetPatient.getClassCode(),errorMessage + " -> Class Code");
        }
    }

    private void assignedEntityComparison(AssignedEntity sourceAssignedEntity, AssignedEntity targetAssignedEntity, String errorMessage) {
        if (sourceAssignedEntity != null && targetAssignedEntity != null) {
            //realm code
            compareRealmCodes(sourceAssignedEntity.getRealmCodes(),targetAssignedEntity.getRealmCodes(),errorMessage + " -> Realm Codes");

            //typeID
            typeIDComparison(sourceAssignedEntity.getTypeId(),targetAssignedEntity.getTypeId(),errorMessage + " -> Type ID");

            //ids
            compareIDs(sourceAssignedEntity.getIds(),targetAssignedEntity.getIds(),errorMessage + " -> IDs");

            //code
            compareCode(sourceAssignedEntity.getCode(),targetAssignedEntity.getCode(),errorMessage + " -> Code");

            //addr
            compareAddr(sourceAssignedEntity.getAddrs(),targetAssignedEntity.getAddrs(),errorMessage + " -> Addr");

            //telecom
            compareTelcom(sourceAssignedEntity.getTelecoms(),targetAssignedEntity.getTelecoms(),errorMessage + " -> telecom");

            //assignedPerson - Person
            personComparison(sourceAssignedEntity.getAssignedPerson(),targetAssignedEntity.getAssignedPerson(),errorMessage + " -> Assigned Person");

            //representedOrganiztions - Organization
            organizationsComparison(sourceAssignedEntity.getRepresentedOrganizations(),targetAssignedEntity.getRepresentedOrganizations(),errorMessage + " -> Represented Organization");

            //nullFlavor
            compareNullFlavor(sourceAssignedEntity.getNullFlavor(),targetAssignedEntity.getNullFlavor(),errorMessage + " -> Null Flavor");

            //classCode
            compareClassCode(sourceAssignedEntity.getClassCode(),targetAssignedEntity.getClassCode(),errorMessage + " -> Class Code");
        }
    }

    private void assignedCustodianComparison(AssignedCustodian sourceAssignedCustodian, AssignedCustodian targetAssignedCustodian, String errorMessage) {
        if (sourceAssignedCustodian != null && targetAssignedCustodian != null) {
            //realm code
            compareRealmCodes(sourceAssignedCustodian.getRealmCodes(),targetAssignedCustodian.getRealmCodes(),errorMessage + " -> Realm Codes");

            //typeID
            typeIDComparison(sourceAssignedCustodian.getTypeId(),targetAssignedCustodian.getTypeId(),errorMessage + " -> Type ID");

            //templateID
            compareTemplateID(sourceAssignedCustodian.getTemplateIds(),targetAssignedCustodian.getTemplateIds(),errorMessage + " -> Template IDs");

            //representedCustodianOrganization - CustodianOrganization
            custodianOrganizationComparison(sourceAssignedCustodian.getRepresentedCustodianOrganization(),targetAssignedCustodian.getRepresentedCustodianOrganization(),errorMessage + " -> Represented Custodian Organization");

            //nullFlavor
            compareNullFlavor(sourceAssignedCustodian.getNullFlavor(),targetClinicalDocument.getNullFlavor(),errorMessage + " -> Null Flavor");

            //classCode
            compareClassCode(sourceAssignedCustodian.getClassCode(),targetAssignedCustodian.getClassCode(),errorMessage + " -> Class Code");
        }
    }

    private void associatedEntityComparison(AssociatedEntity sourceAssignedEntity, AssociatedEntity targetAssignedEntity, String errorMessage) {
        if (sourceAssignedEntity != null && targetAssignedEntity != null) {
            //realm code
            compareRealmCodes(sourceAssignedEntity.getRealmCodes(),targetAssignedEntity.getRealmCodes(),errorMessage + " -> Realm Codes");

            //typeID
            typeIDComparison(sourceAssignedEntity.getTypeId(),targetAssignedEntity.getTypeId(),errorMessage + " -> Type ID");

            //template ID
            compareTemplateID(sourceAssignedEntity.getTemplateIds(),targetAssignedEntity.getTemplateIds(),errorMessage + " -> Template IDs");

            //id
            compareIDs(sourceAssignedEntity.getTemplateIds(),targetAssignedEntity.getTemplateIds(),errorMessage + " -> IDs");

            //code
            compareCode(sourceAssignedEntity.getCode(),targetAssignedEntity.getCode(),errorMessage + " -> Code");

            //addr
            compareAddr(sourceAssignedEntity.getAddrs(),targetAssignedEntity.getAddrs(),errorMessage + " -> Addr");

            //telecom
            compareTelcom(sourceAssignedEntity.getTelecoms(),targetAssignedEntity.getTelecoms(),errorMessage + " -> Telecoms");

            //associatedPerson - Person
            personComparison(sourceAssignedEntity.getAssociatedPerson(),targetAssignedEntity.getAssociatedPerson(),errorMessage + " -> Associated Person");

            //scopingOrganization - Organization
            organizationComparison(sourceAssignedEntity.getScopingOrganization(),targetAssignedEntity.getScopingOrganization(),errorMessage + " -> Scoping Organization");

            //nullFlavor
            compareNullFlavor(sourceAssignedEntity.getNullFlavor(),targetAssignedEntity.getNullFlavor(),errorMessage + " -> Null Flavor");

            //classCode
            compareClassCode(sourceAssignedEntity.getClassCode(),targetAssignedEntity.getClassCode(),errorMessage + " -> Class Code");
        }
    }

    private void serviceEventComparison(ServiceEvent sourceServiceEvent, ServiceEvent targetServiceEvent, String errorMessage) {
        if (sourceServiceEvent != null && targetServiceEvent != null) {
            //realm code
            compareRealmCodes(sourceServiceEvent.getRealmCodes(),targetServiceEvent.getRealmCodes(),errorMessage + " -> Realm Codes");

            //typeID
            typeIDComparison(sourceServiceEvent.getTypeId(),targetServiceEvent.getTypeId(),errorMessage + " -> Type ID");

            //templateID
            compareTemplateID(sourceServiceEvent.getTemplateIds(),targetServiceEvent.getTemplateIds(),errorMessage + " -> Template ID");

            //id
            compareIDs(sourceServiceEvent.getIds(),targetServiceEvent.getIds(),errorMessage + " -> IDs");

            //code
            compareCode(sourceServiceEvent.getCode(),targetServiceEvent.getCode(),errorMessage + " -> Code");

            //effective Time
            compareEffectiveTime(sourceServiceEvent.getEffectiveTime(),targetServiceEvent.getEffectiveTime(),errorMessage + " -> Effective Time");

            //performer - performer1
            performersComparison(sourceServiceEvent.getPerformers(),targetServiceEvent.getPerformers(),errorMessage + " -> Performers");

            //null flavor
            compareNullFlavor(sourceServiceEvent.getNullFlavor(),targetServiceEvent.getNullFlavor(),errorMessage + " -> NullFlavor");

            //class code
            compareClassCode(sourceServiceEvent.getClassCode(),targetServiceEvent.getClassCode(),errorMessage + " -> Class Code");

            //moodCode
            compareMoodCode(sourceServiceEvent.getMoodCode(),targetServiceEvent.getMoodCode(),errorMessage + " -> Mood Code");
        }
    }

    private void nonXMLBodyComparison(NonXMLBody sourceNonXMLBody, NonXMLBody targetNonXMLBody, String errorMessage) {
        if (sourceNonXMLBody != null && targetNonXMLBody != null) {
            //realmCode
            compareRealmCodes(sourceNonXMLBody.getRealmCodes(),targetNonXMLBody.getRealmCodes(),errorMessage + " -> Non XML Body Comparison");

            //typeID
            typeIDComparison(sourceNonXMLBody.getTypeId(),targetNonXMLBody.getTypeId(),errorMessage + " -> type ID");

            //templateID
            compareTemplateID(sourceNonXMLBody.getTemplateIds(),targetNonXMLBody.getTemplateIds(),errorMessage + " -> Template ID");

            //text
            compareText(sourceNonXMLBody.getText(),targetNonXMLBody.getText(),errorMessage + " -> Text");

            //confidentialityCode
            compareConfidentialityCode(sourceNonXMLBody.getConfidentialityCode(),targetNonXMLBody.getConfidentialityCode(),errorMessage + " -> Confidentiality Code");

            //langaugeCode
            compareLanguageCode(sourceNonXMLBody.getLanguageCode(),targetNonXMLBody.getLanguageCode(),errorMessage + " -> Language Code");

            //null flavor
            compareNullFlavor(sourceNonXMLBody.getNullFlavor(),targetNonXMLBody.getNullFlavor(),errorMessage + " -> Null Flavor");

            //classCode
            compareClassCode(sourceNonXMLBody.getClassCode(),targetNonXMLBody.getClassCode(),errorMessage + " -> Class Code");

            //moodCode
            compareMoodCode(sourceNonXMLBody.getMoodCode(),targetNonXMLBody.getMoodCode(),errorMessage + " -> Mood Code");
        }
    }

    private void structuredBodyComparison(StructuredBody sourceStructuredBody, StructuredBody targetStructuredBody, String errorMessage) {
        if (sourceStructuredBody != null && targetStructuredBody != null) {
            //realmCode
            compareRealmCodes(sourceStructuredBody.getRealmCodes(),targetStructuredBody.getRealmCodes(),errorMessage + " -> Realm Codes");

            //typeID
            typeIDComparison(sourceStructuredBody.getTypeId(),targetStructuredBody.getTypeId(),errorMessage + " -> TypeID");

            //templateID
            compareTemplateID(sourceStructuredBody.getTemplateIds(),targetStructuredBody.getTemplateIds(),errorMessage + " -> template ID");

            //confidentiality Code
            compareConfidentialityCode(sourceStructuredBody.getConfidentialityCode(),targetStructuredBody.getConfidentialityCode(),errorMessage + " -> Confidentiality Code");

            //language Code
            compareLanguageCode(sourceStructuredBody.getLanguageCode(),targetStructuredBody.getLanguageCode(),errorMessage + " -> Language Code");

            //component - Component 3
            component3Comparison(sourceStructuredBody.getComponents(),targetStructuredBody.getComponents(),errorMessage + " -> Component");

            //null Flavor
            compareNullFlavor(sourceStructuredBody.getNullFlavor(),targetStructuredBody.getNullFlavor(),errorMessage + " -> Null Flavor");

            //class Code
            compareClassCode(sourceStructuredBody.getClassCode(),targetStructuredBody.getClassCode(),errorMessage + " -> Class Code");

            //moodCode
            compareMoodCode(sourceStructuredBody.getMoodCode(),targetStructuredBody.getMoodCode(),errorMessage + " -> Mood Code");
        }
    }

    private void relatedEntityComparison(RelatedEntity sourceRelatedEntity, RelatedEntity targetRelatedEntity, String errorMessage) {
        if (sourceRelatedEntity != null && targetRelatedEntity != null) {
            //realmCode
            compareRealmCodes(sourceRelatedEntity.getRealmCodes(),targetRelatedEntity.getRealmCodes(),errorMessage + " -> Realm Codes");

            //typeID
            typeIDComparison(sourceRelatedEntity.getTypeId(),targetRelatedEntity.getTypeId(),errorMessage + " -> TypeID");

            //templateID
            compareTemplateID(sourceRelatedEntity.getTemplateIds(),targetRelatedEntity.getTemplateIds(),errorMessage + " -> template IDs");

            //code
            compareCode(sourceRelatedEntity.getCode(),targetRelatedEntity.getCode(),errorMessage + " -> Code");

            //addr
            compareAddr(sourceRelatedEntity.getAddrs(),targetRelatedEntity.getAddrs(),errorMessage + " -> Addrs");

            //telecom
            compareTelcom(sourceRelatedEntity.getTelecoms(),targetRelatedEntity.getTelecoms(),errorMessage + " -> Telecoms");

            //affectiveTime
            compareEffectiveTime(sourceRelatedEntity.getEffectiveTime(),targetRelatedEntity.getEffectiveTime(),errorMessage + " -> Effective Time");

            //relatedPerson - Person
            personComparison(sourceRelatedEntity.getRelatedPerson(),targetRelatedEntity.getRelatedPerson(),errorMessage + " -> Related Person");

            //nullFlavor
            compareNullFlavor(sourceRelatedEntity.getNullFlavor(),targetRelatedEntity.getNullFlavor(),errorMessage + " -> Null Flavor");

            //classCode
            compareClassCode(sourceRelatedEntity.getClassCode(),targetRelatedEntity.getClassCode(),errorMessage + " -> Class Code");
        }
    }

    private void organizationComparison(Organization sourceOrganization, Organization targetOrganization, String errorMessage) {
        if (sourceOrganization != null && targetOrganization != null) {
            //realm Code
            compareRealmCodes(sourceOrganization.getRealmCodes(),targetOrganization.getRealmCodes(),errorMessage + " -> Realm Codes");

            //typeID
            typeIDComparison(sourceOrganization.getTypeId(),targetOrganization.getTypeId(),errorMessage + " -> TypeID");

            //templatedID
            compareTemplateID(sourceOrganization.getTemplateIds(),targetOrganization.getTemplateIds(),errorMessage + " -> Template ID");

            //ids
            compareIDs(sourceOrganization.getIds(),targetOrganization.getIds(),errorMessage + " -> IDs");

            //name////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //compareNames(sourceOrganization.getNames(),targetOrganization.getNames(),errorMessage + " -> Name");

            //telecom
            compareTelcom(sourceOrganization.getTelecoms(),targetOrganization.getTelecoms(),errorMessage + " -> Telecoms");

            //addr
            compareAddr(sourceOrganization.getAddrs(),targetOrganization.getAddrs(),errorMessage + " -> Addrs");

            //standardIndustryClassCode
            compareCode(sourceOrganization.getStandardIndustryClassCode(),targetOrganization.getStandardIndustryClassCode(),errorMessage + " -> Standard Industry Class Code");

            //asOrganizationPartOf - OrganizationPartOf
            organizationPartOfComparison(sourceOrganization.getAsOrganizationPartOf(),targetOrganization.getAsOrganizationPartOf(),errorMessage + " -> As Organization Part Of");

            //nullFlavor
            compareNullFlavor(sourceOrganization.getNullFlavor(),targetOrganization.getNullFlavor(),errorMessage + " -> Null Flavor");

            //classCode
            compareClassCode(sourceOrganization.getClassCode(),targetOrganization.getClassCode(),errorMessage + " -> Class Code");

            //determinerCode
            compareDeterminerCode(sourceOrganization.getDeterminerCode(),targetOrganization.getDeterminerCode(),errorMessage + " -> Determiner Code");
        }
    }

    private void personComparison(Person sourcePerson, Person targetPerson, String errorMessage) {
        if (sourcePerson != null && targetPerson != null) {
            //realmCode
            compareRealmCodes(sourcePerson.getRealmCodes(),targetPerson.getRealmCodes(),errorMessage + " -> Realm Codes");

            //typeID
            typeIDComparison(sourcePerson.getTypeId(),targetPerson.getTypeId(),errorMessage + " -> Type ID");

            //templateID
            compareTemplateID(sourcePerson.getTemplateIds(),targetPerson.getTemplateIds(),errorMessage + " -> Template ID");

            //name
            compareNames(sourcePerson.getNames(),targetPerson.getNames(),errorMessage + " -> Names");

            //nullFlavor
            compareNullFlavor(sourcePerson.getNullFlavor(),targetPerson.getNullFlavor(),errorMessage + " -> Null Flavor");

            //classCode
            compareClassCode(sourcePerson.getClassCode(),targetPerson.getClassCode(),errorMessage + " -> Class Code");
        }
    }

    private void authorizingDeviceComparison(AuthoringDevice sourceAuthorizingDevice, AuthoringDevice targetAuthorizingDevice, String errorMessage) {
        if (sourceAuthorizingDevice != null && targetAuthorizingDevice != null) {
            //realm code
            compareRealmCodes(sourceAuthorizingDevice.getRealmCodes(),targetAuthorizingDevice.getRealmCodes(),errorMessage + " -> Realm Codes");

            //type Id
            typeIDComparison(sourceAuthorizingDevice.getTypeId(),targetAuthorizingDevice.getTypeId(),errorMessage + " -> Type ID");

            //template ID
            compareTemplateID(sourceAuthorizingDevice.getTemplateIds(),targetAuthorizingDevice.getTemplateIds(),errorMessage + " -> TemplateID");

            //code
            compareCode(sourceAuthorizingDevice.getCode(),targetAuthorizingDevice.getCode(),errorMessage + " -> Code");

            //manufacturedModelName
            compareSCName(sourceAuthorizingDevice.getManufacturerModelName(),targetAuthorizingDevice.getManufacturerModelName(),errorMessage + " -> Manufactured Model Name");

            //softwareName
            compareSCName(sourceAuthorizingDevice.getSoftwareName(),targetAuthorizingDevice.getSoftwareName(),errorMessage + " -> Software Name");

            //asMaintainedEntity - MaintainedEntity
            maintainedEntityComparison(sourceAuthorizingDevice.getAsMaintainedEntities(),targetAuthorizingDevice.getAsMaintainedEntities(),errorMessage + " -> As Maintained Entity's");

            //nullFlavor
            compareNullFlavor(sourceAuthorizingDevice.getNullFlavor(),targetAuthorizingDevice.getNullFlavor(),errorMessage + " -> Null Flavor");

            //classCode
            compareClassCode(sourceAuthorizingDevice.getClassCode(),targetAuthorizingDevice.getClassCode(),errorMessage + " -> Class Code");

            //determinerCode
            compareDeterminerCode(sourceAuthorizingDevice.getDeterminerCode(),targetAuthorizingDevice.getDeterminerCode(),errorMessage + " -> Determiner Code");
        }
    }

    private void organizationsComparison(EList<Organization> sourceOrganization, EList<Organization> targetOrganization, String errorMessage) {
    }

    private void informationRecipientComparison(EList<InformationRecipient> source, EList<InformationRecipient> target, String errorMessage) {
    }

    private void inFulfillmentOfComparison(EList<InFulfillmentOf> source, EList<InFulfillmentOf> target, String errorMessage) {
    }

    private void relatedDocumentsComparison(EList<RelatedDocument> source, EList<RelatedDocument> target, String errorMessage) {
    }

    private void authorizationsComparison(EList<Authorization> source, EList<Authorization> target, String errorMessage) {
    }

    private void componentOfComparison(Component1 source, Component1 targer, String errorMessage) {
    }

    private void guardiansComparison(EList<Guardian> source, EList<Guardian> target, String errorMessage) {
    }

    private void birthplaceComparison(Birthplace source, Birthplace target, String errorMessage) {
    }

    private void languageCommunicationsComparison(EList<LanguageCommunication> source, EList<LanguageCommunication> target, String errorMessage) {
    }

    private void custodianOrganizationComparison(CustodianOrganization source, CustodianOrganization target, String errorMessage) {
    }

    private void performersComparison(EList<Performer1> source, EList<Performer1> target, String errorMessage) {
    }

    private void authenticatorComparison(EList<Authenticator> source, EList<Authenticator> target, String errorMessage) {
    }

    private void component3Comparison(EList<Component3> source, EList<Component3> target, String errorMessage) {
    }

    private void organizationPartOfComparison(OrganizationPartOf source, OrganizationPartOf target, String errorMessage) {
    }

    private void maintainedEntityComparison(EList<MaintainedEntity> source, EList<MaintainedEntity> target, String errorMessage) {
    }









    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //non complex type comparison Methods
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void compareRealmCodes(EList<CS> source, EList<CS> target, String errorMessage) {
        int count = 0;
        int max = Math.max(source.size(),target.size());
        for (int i=0; i<source.size(); i++) {
            for (int j=0; j<target.size(); j++) {
                if (Objects.equals(source.get(i).getCode(),target.get(j).getCode())
                        && Objects.equals(source.get(i).getNullFlavor().getLiteral(),target.get(j).getNullFlavor().getLiteral())
                        && Objects.equals(source.get(i).getCodeSystem(),target.get(j).getCodeSystem())
                        && Objects.equals(source.get(i).getCodeSystemName(),target.get(j).getCodeSystemName())
                        && Objects.equals(source.get(i).getCodeSystemVersion(),target.get(j).getCodeSystemVersion())
                        && Objects.equals(source.get(i).getDisplayName(),target.get(j).getDisplayName()))
                {
                    count++;
                }
            }
        }

        if (count == 0 && max != 0) {
            comparisonResult.addMessage("RealmCode error in " + errorMessage + "\n");
        } else if (count < max) {
            comparisonResult.addMessage("Potential RealmCode error in " + errorMessage + "\n");
        }
    }

    private void compareTemplateID(EList<II> source, EList<II> target, String errorMessage) {
        int count = 0;
        int max = Math.max(source.size(),target.size());
        for (int i = 0; i < source.size(); i++) {
            for (int j = 0; j < target.size(); j++) {
                if (Objects.equals(source.get(i).getNullFlavor(), target.get(j).getNullFlavor())
                        && Objects.equals(source.get(i).getRoot(), target.get(j).getRoot())
                        && Objects.equals(source.get(i).getExtension(), target.get(j).getExtension())
                        && Objects.equals(source.get(i).getAssigningAuthorityName(), target.get(j).getAssigningAuthorityName())) {
                    count++;
                }
            }
        }

        if (count == 0 && max != 0) {
            comparisonResult.addMessage("templateID error in " + errorMessage + "\n");
        } else if (count < max) {
            comparisonResult.addMessage("Potential templateID error in " + errorMessage + "\n");
        }
    }

    private void compareIDs(EList<II> source, EList<II> target, String errorMessage) {
        int count = 0;
        int max = Math.max(source.size(),target.size());
        for (int i = 0; i < source.size(); i++) {
            for (int j = 0; j < target.size(); j++) {
                if (source.get(i) != null && target.get(j) != null) { ///////////////////////////////////////////////////////////////////////////why is this needed? size is 1 but everything is null
                    if (Objects.equals(source.get(i).getNullFlavor(), target.get(j).getNullFlavor())
                            && Objects.equals(source.get(i).getRoot(), target.get(j).getRoot())
                            && Objects.equals(source.get(i).getExtension(), target.get(j).getExtension())
                            && Objects.equals(source.get(i).getAssigningAuthorityName(), target.get(j).getAssigningAuthorityName())) {
                        count++;
                    }
                } else {
                    count = max;
                }
            }
        }

        if (count == 0 && max != 0) {
            comparisonResult.addMessage("ID error in " + errorMessage + "\n");
        } else if (count < max) {
            comparisonResult.addMessage("Potential ID error in " + errorMessage + "\n");
        }
    }

    private void compareAddr(EList<AD> source, EList<AD> target, String errorMessage) {
        int count = 0;
        int max = Math.max(source.size(),target.size());
        for (int i = 0; i < source.size(); i++) {
            for (int j = 0; j < target.size(); j++) {
                if (Objects.equals(source.get(i).getNullFlavor(), target.get(j).getNullFlavor())
                        && Objects.equals(source.get(i).getText(), target.get(j).getText())) {
                    count++;
                }
            }
        }

        if (count == 0 && max != 0) {
            comparisonResult.addMessage("Addr error in " + errorMessage + "\n");
        } else if (count < max) {
            comparisonResult.addMessage("Potential Addr error in " + errorMessage + "\n");
        }
    }

    private void compareTelcom(EList<TEL> source, EList<TEL> target, String errorMessage) {
        int count = 0;
        int max = Math.max(source.size(),target.size());
        for (int i = 0; i < source.size(); i++) {
            for (int j = 0; j < target.size(); j++) {
                if (Objects.equals(source.get(i).getNullFlavor(), target.get(j).getNullFlavor())
                        && Objects.equals(source.get(i).getValue(), target.get(j).getValue())) {
                    count++;
                }
            }
        }

        if (count == 0 && max != 0) {
            comparisonResult.addMessage("Telecom error in " + errorMessage + "\n");
        } else if (count < max) {
            comparisonResult.addMessage("Potential telecom error in " + errorMessage + "\n");
        }
    }

    private void compareNames(EList<PN> source, EList<PN> target, String errorMessage) {
        int count = 0;
        int max = Math.max(source.size(),target.size());
        for (int i=0; i<source.size();i++) {
            for (int j=0; j<target.size();j++) {
                if(Objects.equals(source.get(i).getText(),target.get(j).getText()) && Objects.equals(source.get(i).getNullFlavor(),target.get(j).getNullFlavor()))
                {
                    count++;
                }
            }
        }

        if (count == 0 && max != 0) {
            comparisonResult.addMessage("Names error in " + errorMessage + "\n");
        } else if (count < max) {
            comparisonResult.addMessage("Potential Names error in " + errorMessage + "\n");
        }
    }

    private void compareID(II source, II target, String errorMessage) {
        if (source != null && target != null) {
            if ( ! (Objects.equals(source.getAssigningAuthorityName(),target.getAssigningAuthorityName())
                    && Objects.equals(source.getExtension(),target.getExtension())
                    && Objects.equals(source.getRoot(),target.getRoot())
                    && Objects.equals(source.getNullFlavor(),target.getNullFlavor())))
            {
                comparisonResult.addMessage("IDs Comparison error in " + errorMessage + "\n");
            }
        }
    }

    private void compareNullFlavor(NullFlavor source, NullFlavor target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getLiteral(), target.getLiteral()))) {
                comparisonResult.addMessage("Null Flavor error in " + errorMessage + "\n");
            }
        }
    }

    private void compareClassCode(RoleClass source, RoleClass target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getLiteral(), target.getLiteral())
                    && Objects.equals(source.getName(), target.getName())
                    && Objects.equals(source.getValue(), target.getValue()))) {
                comparisonResult.addMessage("Class Code error in " + errorMessage + "\n");
            }
        }
    }

    private void compareClassCode(EntityClassOrganization source, EntityClassOrganization target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getLiteral(), target.getLiteral())
                    && Objects.equals(source.getName(), target.getName())
                    && Objects.equals(source.getValue(), target.getValue()))) {
                comparisonResult.addMessage("Class Code error in " + errorMessage + "\n");
            }
        }
    }

    private void compareClassCode(ActClassRoot source, ActClassRoot target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getLiteral(), target.getLiteral())
                    && Objects.equals(source.getName(), target.getName())
                    && Objects.equals(source.getValue(), target.getValue()))) {
                comparisonResult.addMessage("Class Code error in " + errorMessage + "\n");
            }
        }
    }


    private void compareClassCode(RoleClassAssociative source, RoleClassAssociative target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getLiteral(), target.getLiteral())
                    && Objects.equals(source.getName(), target.getName())
                    && Objects.equals(source.getValue(), target.getValue()))) {
                comparisonResult.addMessage("Class Code error in " + errorMessage + "\n");
            }
        }
    }

    private void compareClassCode(ActClinicalDocument source, ActClinicalDocument target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getLiteral(), target.getLiteral())
                    && Objects.equals(source.getName(), target.getName())
                    && Objects.equals(source.getValue(), target.getValue()))) {
                comparisonResult.addMessage("classCode error in " + errorMessage + "\n");
            }
        }
    }

    private void compareClassCode(RoleClassAssignedEntity source, RoleClassAssignedEntity target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getLiteral(), target.getLiteral())
                    && Objects.equals(source.getName(), target.getName())
                    && Objects.equals(source.getValue(), target.getValue()))) {
                comparisonResult.addMessage("classCode error in " + errorMessage + "\n");
            }
        }
    }

    private void compareClassCode(EntityClassDevice source, EntityClassDevice target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getLiteral(), target.getLiteral())
                    && Objects.equals(source.getName(), target.getName())
                    && Objects.equals(source.getValue(), target.getValue()))) {
                comparisonResult.addMessage("classCode error in " + errorMessage + "\n");
            }
        }
    }

    private void compareClassCode(EntityClass source,EntityClass target,String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getLiteral(), target.getLiteral())
                    && Objects.equals(source.getName(), target.getName())
                    && Objects.equals(source.getValue(), target.getValue()))) {
                comparisonResult.addMessage("classCode error in " + errorMessage + "\n");
            }
        }
    }
    private void compareClassCode(ActClass source,ActClass target,String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getLiteral(), target.getLiteral())
                    && Objects.equals(source.getName(), target.getName())
                    && Objects.equals(source.getValue(), target.getValue()))) {
                comparisonResult.addMessage("classCode error in " + errorMessage + "\n");
            }
        }
    }
    private void compareClassCode(RoleClassMutualRelationship source,RoleClassMutualRelationship target,String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getLiteral(), target.getLiteral())
                    && Objects.equals(source.getName(), target.getName())
                    && Objects.equals(source.getValue(), target.getValue()))) {
                comparisonResult.addMessage("classCode error in " + errorMessage + "\n");
            }
        }
    }

    private void compareCode(CE source, CE target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getCode(), target.getCode())
                    && Objects.equals(source.getNullFlavor().getLiteral(), target.getNullFlavor().getLiteral())
                    && Objects.equals(source.getCodeSystem(), target.getCodeSystem())
                    && Objects.equals(source.getCodeSystemName(), target.getCodeSystemName())
                    && Objects.equals(source.getCodeSystemVersion(), target.getCodeSystemVersion())
                    && Objects.equals(source.getDisplayName(), target.getDisplayName()))) {
                comparisonResult.addMessage("Code Comparison error in " + errorMessage + "\n");
            }
        }
    }

    private void compareConfidentialityCode(CE source, CE target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getCode(), target.getCode())
                    && Objects.equals(source.getNullFlavor().getLiteral(), target.getNullFlavor().getLiteral())
                    && Objects.equals(source.getCodeSystem(), target.getCodeSystem())
                    && Objects.equals(source.getCodeSystemName(), target.getCodeSystemName())
                    && Objects.equals(source.getCodeSystemVersion(), target.getCodeSystemVersion())
                    && Objects.equals(source.getDisplayName(), target.getDisplayName()))) {
                comparisonResult.addMessage("Confidentiality Code Comparison error in " + errorMessage + "\n");
            }
        }
    }

    private void compareLanguageCode(CE source, CE target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getCode(), target.getCode())
                    && Objects.equals(source.getNullFlavor().getLiteral(), target.getNullFlavor().getLiteral())
                    && Objects.equals(source.getCodeSystem(), target.getCodeSystem())
                    && Objects.equals(source.getCodeSystemName(), target.getCodeSystemName())
                    && Objects.equals(source.getCodeSystemVersion(), target.getCodeSystemVersion())
                    && Objects.equals(source.getDisplayName(), target.getDisplayName()))) {
                comparisonResult.addMessage("Language Code Comparison error in " + errorMessage + "\n");
            }
        }
    }

    private void compareTitle(ST source, ST target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getText(), target.getText()))) {
                comparisonResult.addMessage("Title Comparison error in " + errorMessage + "\n");
            }
        }
    }

    private void compareEffectiveTime(TS source, TS target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getValue(), target.getValue())
                    && Objects.equals(source.getNullFlavor(), target.getNullFlavor()))) {
                comparisonResult.addMessage("Effective Time error in " + errorMessage + "\n");
            }
        }
    }

    private void compareTypeCode(ParticipationType source, ParticipationType target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getLiteral(), target.getLiteral())
                    && Objects.equals(source.getName(), target.getName())
                    && Objects.equals(source.getValue(), target.getValue()))) {
                comparisonResult.addMessage("Type Code error in " + errorMessage + "\n");
            }
        }
    }

    private void compareTypeCode(ActRelationshipHasComponent source, ActRelationshipHasComponent target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getLiteral(), target.getLiteral())
                    && Objects.equals(source.getName(), target.getName())
                    && Objects.equals(source.getValue(), target.getValue()))) {
                comparisonResult.addMessage("Type Code error in " + errorMessage + "\n");
            }
        }
    }

    private void compareTypeCode(ActRelationshipType source, ActRelationshipType target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getLiteral(), target.getLiteral())
                    && Objects.equals(source.getName(), target.getName())
                    && Objects.equals(source.getValue(), target.getValue()))) {
                comparisonResult.addMessage("Type Code error in " + errorMessage + "\n");
            }
        }
    }

    private void compareContextControlCode(ContextControl source, ContextControl target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getLiteral(), target.getLiteral())
                    && Objects.equals(source.getName(), target.getName())
                    && Objects.equals(source.getValue(), source.getValue()))) {
                comparisonResult.addMessage("Context Control Code error in " + errorMessage + "\n");
            }
        }
    }

    private void compareTime(TS source, TS target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getValue(), target.getValue())
                    && Objects.equals(source.getNullFlavor(), target.getNullFlavor()))) {
                comparisonResult.addMessage("Time error in " + errorMessage + "\n");
            }
        }
    }

    private void compareSignatureCode(CS source, CS target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getCode(), target.getCode())
                    && Objects.equals(source.getNullFlavor().getLiteral(), target.getNullFlavor().getLiteral())
                    && Objects.equals(source.getCodeSystem(), target.getCodeSystem())
                    && Objects.equals(source.getCodeSystemName(), target.getCodeSystemName())
                    && Objects.equals(source.getCodeSystemVersion(), target.getCodeSystemVersion())
                    && Objects.equals(source.getDisplayName(), target.getDisplayName())))
            {
                comparisonResult.addMessage("Signature Code Comparison Error in" + errorMessage + "\n");
            }
        }
    }

    private void compareFunctionCode(CE source, CE target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getCode(), target.getCode())
                    && Objects.equals(source.getNullFlavor().getLiteral(), target.getNullFlavor().getLiteral())
                    && Objects.equals(source.getCodeSystem(), target.getCodeSystem())
                    && Objects.equals(source.getCodeSystemName(), target.getCodeSystemName())
                    && Objects.equals(source.getCodeSystemVersion(), target.getCodeSystemVersion())
                    && Objects.equals(source.getDisplayName(), target.getDisplayName()))) {
                comparisonResult.addMessage("Function Code Comparison error in " + errorMessage + "\n");
            }
        }
    }

    private void compareContextConductionInd(Boolean source, Boolean target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source, target))) {
                comparisonResult.addMessage("Context Conduction Ind error in " + errorMessage + "\n");
            }
        }
    }

    private void compareSetID(II source, II target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getAssigningAuthorityName(),target.getAssigningAuthorityName())
                    && Objects.equals(source.getExtension(),target.getExtension())
                    && Objects.equals(source.getRoot(),target.getRoot())))
            {
                comparisonResult.addMessage("Set ID error in " + errorMessage + "\n");
            }
        }
    }

    private void compareVersionNumber(INT source, INT target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getValue(),target.getValue()))) {
                comparisonResult.addMessage("Version Number error in " + errorMessage + "\n");
            }

        }
    }

    private void compareCopyTime(TS source, TS target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getValue(),target.getValue()) && Objects.equals(source.getNullFlavor(),target.getValue()))) {
                comparisonResult.addMessage("Copy Time error in " + errorMessage + "\n");
            }
        }

    }

    private void compareAdministrativeGenderCode(CE source, CE target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getCode(),target.getCode()) && Objects.equals(source.getNullFlavor(),target.getNullFlavor())))
            {
                comparisonResult.addMessage("Administrative Gender Code error in " + errorMessage + "\n");
            }
        }
    }

    private void compareBirthTime(TS source, TS target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getValue(),target.getValue())
                    && Objects.equals(source.getNullFlavor(),target.getNullFlavor())))
            {
                comparisonResult.addMessage("Birth Time error in " + errorMessage + "\n");
            }
        }
    }

    private void compareMaritalStatusCode(CE source, CE target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getCode(),target.getCode())
                    && Objects.equals(source.getNullFlavor(),target.getNullFlavor())))
            {
                comparisonResult.addMessage("Marital Status Code error in " + errorMessage + "\n");
            }
        }
    }

    private void compareReligiosAffiliationCode(CE source, CE target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getNullFlavor(),target.getNullFlavor())
                    && Objects.equals(source.getCode(),target.getCode())
                    && Objects.equals(source.getCodeSystem(),target.getCodeSystem())
                    && Objects.equals(source.getCodeSystemName(),target.getCodeSystemName())
                    && Objects.equals(source.getCodeSystemVersion(),target.getCodeSystemVersion())
                    && Objects.equals(source.getDisplayName(),target.getDisplayName())))
            {
                comparisonResult.addMessage("Religious Affiliation Code error in " + errorMessage + "\n");
            }
        }
    }

    private void compareRaceCode(CE source, CE target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getNullFlavor(),target.getNullFlavor())
                    && Objects.equals(source.getCode(),target.getCode())
                    && Objects.equals(source.getCodeSystem(),target.getCodeSystem())
                    && Objects.equals(source.getCodeSystemName(),target.getCodeSystemName())
                    && Objects.equals(source.getCodeSystemVersion(),target.getCodeSystemVersion())
                    && Objects.equals(source.getDisplayName(),target.getDisplayName())))
            {
                comparisonResult.addMessage("Race Code error in " + errorMessage + "\n");
            }
        }
    }

    private void compareEthnicGroupCode(CE source, CE target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getNullFlavor(),target.getNullFlavor())
                    && Objects.equals(source.getCode(),target.getCode())
                    && Objects.equals(source.getCodeSystem(),target.getCodeSystem())
                    && Objects.equals(source.getCodeSystemName(),target.getCodeSystemName())
                    && Objects.equals(source.getCodeSystemVersion(),target.getCodeSystemVersion())
                    && Objects.equals(source.getDisplayName(),target.getDisplayName())))
            {
                comparisonResult.addMessage("Ethnic Group Code error in " + errorMessage + "\n");
            }
        }
    }

    private void compareMoodCode(ActMood source, ActMood target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getLiteral(),target.getLiteral())
                    && Objects.equals(source.getName(),target.getName())
                    && Objects.equals(source.getValue(),target.getValue())))
            {
                comparisonResult.addMessage("Mood Code error in " + errorMessage + "\n");
            }
        }
    }

    private void compareText(ED source, ED target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getLanguage(), target.getLanguage())
                    && Objects.equals(source.getText(), target.getText())
                    && Objects.equals(source.getMediaType(), target.getMediaType())
                    && Objects.equals(source.getNullFlavor(), target.getNullFlavor()))) {
                comparisonResult.addMessage("Text error in " + errorMessage + "\n");
            }
        }
    }

    private void compareDeterminerCode(EntityDeterminer source, EntityDeterminer target, String errorMessage) {
        if (source != null && target != null) {
            if(!(Objects.equals(source.getLiteral(),target.getLiteral())
                    && Objects.equals(source.getName(),target.getName())
                    && Objects.equals(source.getValue(),target.getValue())))
            {
                comparisonResult.addMessage("Determiner Code error in " + errorMessage + "\n");
            }
        }
    }

    private void compareSCName(SC source, SC target, String errorMessage) {
        if (!(Objects.equals(source.getNullFlavor(),target.getNullFlavor())
                && Objects.equals(source.getCode(),target.getCode())
                && Objects.equals(source.getCodeSystem(),target.getCodeSystem())
                && Objects.equals(source.getCodeSystemName(),target.getCodeSystemName())
                && Objects.equals(source.getCodeSystemVersion(),target.getCodeSystemVersion())
                && Objects.equals(source.getDisplayName(),target.getDisplayName())))
        {
            comparisonResult.addMessage("Manufactured Model Name error in " + errorMessage + "\n");
        }
    }
}
