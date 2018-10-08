package com.github.aks8m;

import org.eclipse.emf.common.util.EList;
import org.openhealthtools.mdht.uml.cda.*;
import org.openhealthtools.mdht.uml.hl7.datatypes.*;
import org.openhealthtools.mdht.uml.hl7.vocab.*;

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
    private Boolean typeIDComparison(InfrastructureRootTypeId sourceTypeID, InfrastructureRootTypeId targetTypeID, String errorMessage) {
        if (sourceTypeID != null && targetTypeID != null) {
            if (!(Objects.equals(sourceTypeID.getRoot(), targetTypeID.getRoot())
                    && Objects.equals(sourceTypeID.getAssigningAuthorityName(), targetTypeID.getAssigningAuthorityName())
                    && Objects.equals(sourceTypeID.getExtension(), targetTypeID.getExtension()))) {
                comparisonResult.addMessage("TypeID error in" + errorMessage + "\n");
                return false;
            }
        }
        return true;
    }

    private Boolean dataEntererComparison(DataEnterer sourceDataEnterer, DataEnterer targetDataEnterer, String errorMessage) {
        boolean errorExists = false;
        if (sourceDataEnterer == null && targetDataEnterer == null) {
            return true;
        } else if (sourceDataEnterer != null && targetDataEnterer != null) {
            //compare realm codes
            if (!compareRealmCodes(sourceDataEnterer.getRealmCodes(),targetDataEnterer.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //compare typeID
            if (!typeIDComparison(sourceDataEnterer.getTypeId(),targetDataEnterer.getTypeId(), errorMessage + " -> type ID")) {
                errorExists = true;
            }
            //compare templateID
            if (!compareTemplateID(sourceDataEnterer.getTemplateIds(), targetDataEnterer.getTemplateIds(),errorMessage + " -> Template IDS")) {
                errorExists = true;
            }
            //compare time
            if (!compareTime(sourceDataEnterer.getTime(),targetDataEnterer.getTime(),errorMessage + " -> Time")) {
                errorExists = true;
            }
            //compare assigned entity
            if (!assignedEntityComparison(sourceDataEnterer.getAssignedEntity(),targetDataEnterer.getAssignedEntity(),errorMessage + " -> Assigned Entity")) {
                errorExists = true;
            }
            //compare nullFlavor
            if (!compareNullFlavor(sourceDataEnterer.getNullFlavor(),targetDataEnterer.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //compare typeCode
            if (!compareTypeCode(sourceDataEnterer.getTypeCode(),targetDataEnterer.getTypeCode(),errorMessage + " -> Type Code")) {
                errorExists = true;
            }
            return !errorExists;

        }

        return false;

    }

    private Boolean custodianComparison(Custodian sourceCustodian, Custodian targetCustodian, String errorMessage) {
        boolean errorExists = false;
        if (sourceCustodian == null && targetCustodian == null) {
            return true;
        } else if (sourceCustodian != null && targetCustodian != null) {
            //realm code
            if (!compareRealmCodes(sourceCustodian.getRealmCodes(),targetCustodian.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //typeID
            if (!typeIDComparison(sourceCustodian.getTypeId(),targetCustodian.getTypeId(), errorMessage + " -> type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceCustodian.getTemplateIds(), targetCustodian.getTemplateIds(),errorMessage + " -> Template IDS")) {
                errorExists = true;
            }
            //AssignedCustodian
            if (!assignedCustodianComparison(sourceCustodian.getAssignedCustodian(),targetCustodian.getAssignedCustodian(),errorMessage + " -> Assigned Custodian")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceCustodian.getNullFlavor(),targetCustodian.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //typeCode
            if (!compareTypeCode(sourceCustodian.getTypeCode(),targetCustodian.getTypeCode(),errorMessage + " -> Type Code"))
            {
                errorExists = true;
            }
            return !errorExists;

        }
        return false;
    }

    private Boolean legalAuthenticatorComparison(LegalAuthenticator sourceLegalAuthenticator, LegalAuthenticator targetLegalAuthenticator, String errorMessage) {
        Boolean errorExists = false;
        if (sourceLegalAuthenticator == null && targetLegalAuthenticator == null) {
            return true;
        } else if (sourceLegalAuthenticator != null && targetLegalAuthenticator != null) {
            //realmCode
            if (!compareRealmCodes(sourceLegalAuthenticator.getRealmCodes(),targetLegalAuthenticator.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //compare typeID
            if (!typeIDComparison(sourceLegalAuthenticator.getTypeId(),targetLegalAuthenticator.getTypeId(),errorMessage + " -> type ID")) {
                errorExists = true;
            }
            //compare templateID
            if (!compareTemplateID(sourceLegalAuthenticator.getTemplateIds(), targetLegalAuthenticator.getTemplateIds(),errorMessage + " -> Template IDS")) {
                errorExists = true;
            }
            //compare time
            if (!compareTime(sourceLegalAuthenticator.getTime(),targetLegalAuthenticator.getTime(),errorMessage + " -> Time")) {
                errorExists = true;
            }
            //compare signatureCode
            if (!compareSignatureCode(sourceLegalAuthenticator.getSignatureCode(),targetLegalAuthenticator.getSignatureCode(),errorMessage + " -> Signature Code")) {
                errorExists = true;
            }
            //compare assignedEntity
            if (!assignedEntityComparison(sourceLegalAuthenticator.getAssignedEntity(),targetLegalAuthenticator.getAssignedEntity(),errorMessage + " -> Assigned Entity")) {
                errorExists = true;
            }
            //compare nullFlavor
            if (!compareNullFlavor(sourceLegalAuthenticator.getNullFlavor(),targetLegalAuthenticator.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //compare typeCode
            if (!compareTypeCode(sourceLegalAuthenticator.getTypeCode(),targetLegalAuthenticator.getTypeCode(),errorMessage + " -> Type Code")) {
                errorExists = true;
            }
            //compare contextControlCode
            if (!compareContextControlCode(sourceLegalAuthenticator.getContextControlCode(),targetLegalAuthenticator.getContextControlCode(), errorMessage + " -> Context Control Code"))
            {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;

    }

    private Boolean component2Comparison(Component2 sourceComponent, Component2 targetComponent, String errorMessage) {
        boolean errorExists = false;
        if (sourceComponent == null && targetComponent == null) {
            return true;
        } else if (sourceComponent != null && targetComponent != null) {
            //realmCode
            if (!compareRealmCodes(sourceComponent.getRealmCodes(),targetComponent.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //typeID
            if (!typeIDComparison(sourceComponent.getTypeId(),targetComponent.getTypeId(),errorMessage + " -> type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceComponent.getTemplateIds(), targetComponent.getTemplateIds(),errorMessage + " -> Template IDS")) {
                errorExists = true;
            }
            //Choice - Non XML Body, StructuredBody
            if (!(nonXMLBodyComparison(sourceComponent.getNonXMLBody(),targetComponent.getNonXMLBody(),errorMessage + " -> NonXMLBody") || structuredBodyComparison(sourceComponent.getStructuredBody(),targetComponent.getStructuredBody(), errorMessage + " -> Structured Body"))) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceComponent.getNullFlavor(),targetComponent.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //typeCode
            if (!compareTypeCode(sourceComponent.getTypeCode(),targetComponent.getTypeCode(),errorMessage + " -> Type Code")) {
                errorExists = true;
            }
            //contextConductionInd
            if (!compareContextConductionInd(sourceComponent.getContextConductionInd(),targetComponent.getContextConductionInd(),errorMessage + " -> Context Conduction Ind"))
            {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private Boolean patientRoleComparison(PatientRole sourcePatientRole, PatientRole targetPatientRole, String errorMessage) {
        boolean errorExists = false;
        if (sourcePatientRole == null && targetPatientRole == null) {
            return true;
        } else if (sourcePatientRole != null && targetPatientRole != null) {
            //compare realmCode
            if (!compareRealmCodes(sourcePatientRole.getRealmCodes(),targetPatientRole.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //compare typeID
            if (!typeIDComparison(sourcePatientRole.getTypeId(),targetPatientRole.getTypeId(),errorMessage = " -> Type ID")) {
                errorExists = true;
            }
            //compare templateID
            if (!compareTemplateID(sourcePatientRole.getTemplateIds(), targetPatientRole.getTemplateIds(),errorMessage + " -> Template IDS")) {
                errorExists = true;
            }
            //compare id
            if (!compareIDs(sourcePatientRole.getIds(),targetPatientRole.getIds(),errorMessage + " -> IDs")) {
                errorExists = true;
            }
            //compare addr
            if (!compareAddr(sourcePatientRole.getAddrs(),targetPatientRole.getAddrs(),errorMessage + " -> Addrs")) {
                errorExists = true;
            }
            //compare telecom
            if (!compareTelcom(sourcePatientRole.getTelecoms(),targetPatientRole.getTelecoms(), errorMessage + " -> Telecoms")) {
                errorExists = true;
            }
            //compare Patient
            if (!patientComparison(sourcePatientRole.getPatient(), targetPatientRole.getPatient(), errorMessage + " -> Patient")) {
                errorExists = true;
            }
            //compareNullFlavor
            if (!compareNullFlavor(sourcePatientRole.getNullFlavor(),targetPatientRole.getNullFlavor(), errorMessage + " -> NullFlavor")) {
                errorExists = true;
            }
            //compare ClassCode
            if (!compareClassCode(sourcePatientRole.getClassCode(),targetPatientRole.getClassCode(), errorMessage + " -> ClassCode")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }


    private Boolean assignedAuthorComparison(AssignedAuthor sourceAssignedAuthor, AssignedAuthor targetAssignedAuthor, String errorMessage) {
        boolean errorExists = false;
        if (sourceAssignedAuthor == null && targetAssignedAuthor == null) {
            return true;
        } else if (sourceAssignedAuthor != null && targetAssignedAuthor != null) {
            //realm Code
            if (!compareRealmCodes(sourceAssignedAuthor.getRealmCodes(),targetAssignedAuthor.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //type ID
            if (!typeIDComparison(sourceAssignedAuthor.getTypeId(),targetAssignedAuthor.getTypeId(),errorMessage + " -> type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceAssignedAuthor.getTemplateIds(), targetAssignedAuthor.getTemplateIds(),errorMessage + " -> Template IDS")) {
                errorExists = true;
            }
            //ids
            if (!compareIDs(sourceAssignedAuthor.getIds(),targetAssignedAuthor.getIds(),errorMessage + " -> IDs")) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceAssignedAuthor.getCode(),targetAssignedAuthor.getCode(),errorMessage + " -> Compare Code")) {
                errorExists = true;
            }
            //addr
            if (!compareAddr(sourceAssignedAuthor.getAddrs(),targetAssignedAuthor.getAddrs(),errorMessage + " -> Addrs")) {
                errorExists = true;
            }
            //telecom
            if (!compareTelcom(sourceAssignedAuthor.getTelecoms(),targetAssignedAuthor.getTelecoms(), errorMessage + " -> telecom")) {
                errorExists = true;
            }
            //choice - assignedPerson (Person), assignedAuthorizing Device(AUthorizing Device)
            if (!(personComparison(sourceAssignedAuthor.getAssignedPerson(),targetAssignedAuthor.getAssignedPerson(),errorMessage + " -> Assigned Person") || authorizingDeviceComparison(sourceAssignedAuthor.getAssignedAuthoringDevice(),targetAssignedAuthor.getAssignedAuthoringDevice(),errorMessage + " -> Assigned Authorizing Device"))) {
                errorExists = true;
            }
            //represented Organization
            if (!organizationComparison(sourceAssignedAuthor.getRepresentedOrganization(),targetAssignedAuthor.getRepresentedOrganization(),errorMessage + " -> Represented Organization")) {
                errorExists = true;
            }
            //null flavor
            if (!compareNullFlavor(sourceAssignedAuthor.getNullFlavor(),targetAssignedAuthor.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //class code
            if (!compareClassCode(sourceAssignedAuthor.getClassCode(),targetAssignedAuthor.getClassCode(),errorMessage + " -> Class Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private Boolean patientComparison(Patient sourcePatient, Patient targetPatient, String errorMessage) {
        boolean errorExists = false;
        if (sourcePatient == null && targetPatient == null) {
            return true;
        } else if (sourcePatient != null && targetPatient != null) {
            //realm code
            if (!compareRealmCodes(sourcePatient.getRealmCodes(),targetPatient.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //typeID
            if (!typeIDComparison(sourcePatient.getTypeId(),targetPatient.getTypeId(),errorMessage + " -> type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourcePatient.getTemplateIds(),targetPatient.getTemplateIds(),errorMessage + " -> Template IDs")) {
                errorExists = true;
            }
            //ids
            if (!compareIDs(sourcePatient.getIds(),targetPatient.getIds(),errorMessage + " -> IDs")) {
                errorExists = true;
            }
            //names
            if (!compareNamesPN(sourcePatient.getNames(),targetPatient.getNames(),errorMessage + " -> Names")) {
                errorExists = true;
            }
            //administrativeGenderCode
            if (!compareAdministrativeGenderCode(sourcePatient.getAdministrativeGenderCode(),targetPatient.getAdministrativeGenderCode(),errorMessage + " -> Administrative Gender Code")) {
                errorExists = true;
            }
            //birthTime
            if (!compareBirthTime(sourcePatient.getBirthTime(),targetPatient.getBirthTime(),errorMessage + " -> Birth Time")) {
                errorExists = true;
            }
            //marital status code
            if (!compareMaritalStatusCode(sourcePatient.getMaritalStatusCode(),targetPatient.getMaritalStatusCode(),errorMessage + " -> Marital Status Code")) {
                errorExists = true;
            }
            //religious affiliation code
            if (!compareReligiosAffiliationCode(sourcePatient.getReligiousAffiliationCode(),targetPatient.getReligiousAffiliationCode(),errorMessage + " -> Religious Affiliation Code")) {
                errorExists = true;
            }
            //race Code
            if (!compareRaceCode(sourcePatient.getRaceCode(),targetPatient.getRaceCode(),errorMessage + " -> Race Code")) {
                errorExists = true;
            }
            //ethnic group code
            if (!compareEthnicGroupCode(sourcePatient.getEthnicGroupCode(),targetPatient.getEthnicGroupCode(),errorMessage + " -> Ethnic Group Code")) {
                errorExists = true;
            }
            //guardian - Guardian
            if (!guardiansComparison(sourcePatient.getGuardians(),targetPatient.getGuardians(),errorMessage + " -> Guardians")) {
                errorExists = true;
            }
            //birthPlace - Birthplace
            if (!birthplaceComparison(sourcePatient.getBirthplace(),targetPatient.getBirthplace(),errorMessage + " -> Birthplace")) {
                errorExists = true;
            }
            //Language Communication - Language Communication
            if (!languageCommunicationsComparison(sourcePatient.getLanguageCommunications(),targetPatient.getLanguageCommunications(),errorMessage + " -> Language Communication")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourcePatient.getNullFlavor(),targetPatient.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //classCode
            if (!compareClassCode(sourcePatient.getClassCode(),targetPatient.getClassCode(),errorMessage + " -> Class Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private Boolean assignedEntityComparison(AssignedEntity sourceAssignedEntity, AssignedEntity targetAssignedEntity, String errorMessage) {
        boolean errorExists = false;
        if (sourceAssignedEntity == null && targetAssignedEntity == null) {
            return true;
        } else if (sourceAssignedEntity != null && targetAssignedEntity != null) {
            //realm code
            if (!compareRealmCodes(sourceAssignedEntity.getRealmCodes(),targetAssignedEntity.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //typeID
            if (!typeIDComparison(sourceAssignedEntity.getTypeId(),targetAssignedEntity.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //ids
            if (!compareIDs(sourceAssignedEntity.getIds(),targetAssignedEntity.getIds(),errorMessage + " -> IDs")) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceAssignedEntity.getCode(),targetAssignedEntity.getCode(),errorMessage + " -> Code")) {
                errorExists = true;
            }
            //addr
            if (!compareAddr(sourceAssignedEntity.getAddrs(),targetAssignedEntity.getAddrs(),errorMessage + " -> Addr")) {
                errorExists = true;
            }
            //telecom
            if (!compareTelcom(sourceAssignedEntity.getTelecoms(),targetAssignedEntity.getTelecoms(),errorMessage + " -> telecom")) {
                errorExists = true;
            }
            //assignedPerson - Person
            if (!personComparison(sourceAssignedEntity.getAssignedPerson(),targetAssignedEntity.getAssignedPerson(),errorMessage + " -> Assigned Person")) {
                errorExists = true;
            }
            //representedOrganiztions - Organization
            if (!organizationsComparison(sourceAssignedEntity.getRepresentedOrganizations(),targetAssignedEntity.getRepresentedOrganizations(),errorMessage + " -> Represented Organization")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceAssignedEntity.getNullFlavor(),targetAssignedEntity.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //classCode
            if (!compareClassCode(sourceAssignedEntity.getClassCode(),targetAssignedEntity.getClassCode(),errorMessage + " -> Class Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private Boolean assignedCustodianComparison(AssignedCustodian sourceAssignedCustodian, AssignedCustodian targetAssignedCustodian, String errorMessage) {
        boolean errorExists = false;
        if (sourceAssignedCustodian == null && targetAssignedCustodian == null) {
            return true;
        } else if (sourceAssignedCustodian != null && targetAssignedCustodian != null) {
            //realm code
            if (!compareRealmCodes(sourceAssignedCustodian.getRealmCodes(),targetAssignedCustodian.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //typeID
            if (!typeIDComparison(sourceAssignedCustodian.getTypeId(),targetAssignedCustodian.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceAssignedCustodian.getTemplateIds(),targetAssignedCustodian.getTemplateIds(),errorMessage + " -> Template IDs")) {
                errorExists = true;
            }
            //representedCustodianOrganization - CustodianOrganization
            if (!custodianOrganizationComparison(sourceAssignedCustodian.getRepresentedCustodianOrganization(),targetAssignedCustodian.getRepresentedCustodianOrganization(),errorMessage + " -> Represented Custodian Organization")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceAssignedCustodian.getNullFlavor(),targetClinicalDocument.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //classCode
            if (!compareClassCode(sourceAssignedCustodian.getClassCode(),targetAssignedCustodian.getClassCode(),errorMessage + " -> Class Code")) {
               errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private Boolean associatedEntityComparison(AssociatedEntity sourceAssignedEntity, AssociatedEntity targetAssignedEntity, String errorMessage) {
        boolean errorExists = false;
        if (sourceAssignedEntity == null && targetAssignedEntity == null) {
            return true;
        } else if (sourceAssignedEntity != null && targetAssignedEntity != null) {
            //realm code
            if (!compareRealmCodes(sourceAssignedEntity.getRealmCodes(),targetAssignedEntity.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //typeID
            if (!typeIDComparison(sourceAssignedEntity.getTypeId(),targetAssignedEntity.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //template ID
            if (!compareTemplateID(sourceAssignedEntity.getTemplateIds(),targetAssignedEntity.getTemplateIds(),errorMessage + " -> Template IDs")) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceAssignedEntity.getTemplateIds(),targetAssignedEntity.getTemplateIds(),errorMessage + " -> IDs")) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceAssignedEntity.getCode(),targetAssignedEntity.getCode(),errorMessage + " -> Code")) {
                errorExists = true;
            }
            //addr
            if (!compareAddr(sourceAssignedEntity.getAddrs(),targetAssignedEntity.getAddrs(),errorMessage + " -> Addr")) {
                errorExists = true;
            }
            //telecom
            if (!compareTelcom(sourceAssignedEntity.getTelecoms(),targetAssignedEntity.getTelecoms(),errorMessage + " -> Telecoms")) {
                errorExists = true;
            }
            //associatedPerson - Person
            if (!personComparison(sourceAssignedEntity.getAssociatedPerson(),targetAssignedEntity.getAssociatedPerson(),errorMessage + " -> Associated Person")) {
                errorExists = true;
            }
            //scopingOrganization - Organization
            if (!organizationComparison(sourceAssignedEntity.getScopingOrganization(),targetAssignedEntity.getScopingOrganization(),errorMessage + " -> Scoping Organization")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceAssignedEntity.getNullFlavor(),targetAssignedEntity.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //classCode
            if (!compareClassCode(sourceAssignedEntity.getClassCode(),targetAssignedEntity.getClassCode(),errorMessage + " -> Class Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private Boolean serviceEventComparison(ServiceEvent sourceServiceEvent, ServiceEvent targetServiceEvent, String errorMessage) {
        boolean errorExists = false;
        if (sourceServiceEvent == null && targetServiceEvent == null) {
            return true;
        } else if (sourceServiceEvent != null && targetServiceEvent != null) {
            //realmCode
            if (!compareRealmCodes(sourceServiceEvent.getRealmCodes(),targetServiceEvent.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //typeID
            if (!typeIDComparison(sourceServiceEvent.getTypeId(),targetServiceEvent.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceServiceEvent.getTemplateIds(),targetServiceEvent.getTemplateIds(),errorMessage + " -> Template ID")) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceServiceEvent.getIds(),targetServiceEvent.getIds(),errorMessage + " -> IDs")) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceServiceEvent.getCode(),targetServiceEvent.getCode(),errorMessage + " -> Code")) {
                errorExists = true;
            }
            //effective Time
            if (!compareEffectiveTime(sourceServiceEvent.getEffectiveTime(),targetServiceEvent.getEffectiveTime(),errorMessage + " -> Effective Time")) {
                errorExists = true;
            }
            //performer - performer1
            if (!performersComparison(sourceServiceEvent.getPerformers(),targetServiceEvent.getPerformers(),errorMessage + " -> Performers")) {
                errorExists = true;
            }
            //null flavor
            if (!compareNullFlavor(sourceServiceEvent.getNullFlavor(),targetServiceEvent.getNullFlavor(),errorMessage + " -> NullFlavor")) {
                errorExists = true;
            }
            //class code
            if (!compareClassCode(sourceServiceEvent.getClassCode(),targetServiceEvent.getClassCode(),errorMessage + " -> Class Code")) {
                errorExists = true;
            }
            //moodCode
            if (!compareMoodCode(sourceServiceEvent.getMoodCode(),targetServiceEvent.getMoodCode(),errorMessage + " -> Mood Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private Boolean nonXMLBodyComparison(NonXMLBody sourceNonXMLBody, NonXMLBody targetNonXMLBody, String errorMessage) {
        boolean errorExists = false;
        if (sourceNonXMLBody == null && targetNonXMLBody == null) {
            return true;
        } else if (sourceNonXMLBody != null && targetNonXMLBody != null) {
            //realmCode
            if (!compareRealmCodes(sourceNonXMLBody.getRealmCodes(),targetNonXMLBody.getRealmCodes(),errorMessage + " -> Non XML Body Comparison")) {
                errorExists = true;
            }
            //typeID
            if (!typeIDComparison(sourceNonXMLBody.getTypeId(),targetNonXMLBody.getTypeId(),errorMessage + " -> type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceNonXMLBody.getTemplateIds(),targetNonXMLBody.getTemplateIds(),errorMessage + " -> Template ID")) {
                errorExists = true;
            }
            //text
            if (!compareText(sourceNonXMLBody.getText(),targetNonXMLBody.getText(),errorMessage + " -> Text")) {
                errorExists = true;
            }
            //confidentialityCode
            if (!compareConfidentialityCode(sourceNonXMLBody.getConfidentialityCode(),targetNonXMLBody.getConfidentialityCode(),errorMessage + " -> Confidentiality Code")) {
                errorExists = true;
            }
            //langaugeCode
            if (!compareLanguageCode(sourceNonXMLBody.getLanguageCode(),targetNonXMLBody.getLanguageCode(),errorMessage + " -> Language Code")) {
                errorExists = true;
            }
            //null flavor
            if (!compareNullFlavor(sourceNonXMLBody.getNullFlavor(),targetNonXMLBody.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //classCode
            if (!compareClassCode(sourceNonXMLBody.getClassCode(),targetNonXMLBody.getClassCode(),errorMessage + " -> Class Code")) {
                errorExists = true;
            }
            //moodCode
            if (!compareMoodCode(sourceNonXMLBody.getMoodCode(),targetNonXMLBody.getMoodCode(),errorMessage + " -> Mood Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private Boolean structuredBodyComparison(StructuredBody sourceStructuredBody, StructuredBody targetStructuredBody, String errorMessage) {
        boolean errorExists = false;
        if (sourceStructuredBody == null && targetStructuredBody == null) {
            return true;
        } else if (sourceStructuredBody != null && targetStructuredBody != null) {
            //realmCode
            if (!compareRealmCodes(sourceStructuredBody.getRealmCodes(),targetStructuredBody.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //typeID
            if (!typeIDComparison(sourceStructuredBody.getTypeId(),targetStructuredBody.getTypeId(),errorMessage + " -> TypeID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceStructuredBody.getTemplateIds(),targetStructuredBody.getTemplateIds(),errorMessage + " -> template ID")) {
                errorExists = true;
            }
            //confidentiality Code
            if (!compareConfidentialityCode(sourceStructuredBody.getConfidentialityCode(),targetStructuredBody.getConfidentialityCode(),errorMessage + " -> Confidentiality Code")) {
                errorExists = true;
            }
            //language Code
            if (!compareLanguageCode(sourceStructuredBody.getLanguageCode(),targetStructuredBody.getLanguageCode(),errorMessage + " -> Language Code")) {
                errorExists = true;
            }
            //component - Component 3
            if (!component3Comparison(sourceStructuredBody.getComponents(),targetStructuredBody.getComponents(),errorMessage + " -> Component")) {
                errorExists = true;
            }
            //null Flavor
            if (!compareNullFlavor(sourceStructuredBody.getNullFlavor(),targetStructuredBody.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //class Code
            if (!compareClassCode(sourceStructuredBody.getClassCode(),targetStructuredBody.getClassCode(),errorMessage + " -> Class Code")) {
                errorExists = true;
            }
            //moodCode
            if (!compareMoodCode(sourceStructuredBody.getMoodCode(),targetStructuredBody.getMoodCode(),errorMessage + " -> Mood Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private Boolean relatedEntityComparison(RelatedEntity sourceRelatedEntity, RelatedEntity targetRelatedEntity, String errorMessage) {
        boolean errorExists = false;
        if (sourceRelatedEntity == null && targetRelatedEntity == null) {
            return true;
        } else if (sourceRelatedEntity != null && targetRelatedEntity != null) {
            //realmCode
            if (!compareRealmCodes(sourceRelatedEntity.getRealmCodes(),targetRelatedEntity.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //typeID
            if (!typeIDComparison(sourceRelatedEntity.getTypeId(),targetRelatedEntity.getTypeId(),errorMessage + " -> TypeID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceRelatedEntity.getTemplateIds(),targetRelatedEntity.getTemplateIds(),errorMessage + " -> template IDs")) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceRelatedEntity.getCode(),targetRelatedEntity.getCode(),errorMessage + " -> Code")) {
                errorExists = true;
            }
            //addr
            if (!compareAddr(sourceRelatedEntity.getAddrs(),targetRelatedEntity.getAddrs(),errorMessage + " -> Addrs")) {
                errorExists = true;
            }
            //telecom
            if (!compareTelcom(sourceRelatedEntity.getTelecoms(),targetRelatedEntity.getTelecoms(),errorMessage + " -> Telecoms")) {
                errorExists = true;
            }
            //affectiveTime
            if (!compareEffectiveTime(sourceRelatedEntity.getEffectiveTime(),targetRelatedEntity.getEffectiveTime(),errorMessage + " -> Effective Time")) {
                errorExists = true;
            }
            //relatedPerson - Person
            if (!personComparison(sourceRelatedEntity.getRelatedPerson(),targetRelatedEntity.getRelatedPerson(),errorMessage + " -> Related Person")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceRelatedEntity.getNullFlavor(),targetRelatedEntity.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //classCode
            if (!compareClassCode(sourceRelatedEntity.getClassCode(),targetRelatedEntity.getClassCode(),errorMessage + " -> Class Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private Boolean organizationComparison(Organization sourceOrganization, Organization targetOrganization, String errorMessage) {
        boolean errorExists = false;
        if (sourceOrganization == null && targetOrganization == null) {
            return true;
        } else if (sourceOrganization != null && targetOrganization != null) {
            //realmCode
            if (!compareRealmCodes(sourceOrganization.getRealmCodes(),targetOrganization.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //typeID
            if (!typeIDComparison(sourceOrganization.getTypeId(),targetOrganization.getTypeId(),errorMessage + " -> TypeID")) {
                errorExists = true;
            }
            //templatedID
            if (!compareTemplateID(sourceOrganization.getTemplateIds(),targetOrganization.getTemplateIds(),errorMessage + " -> Template ID")) {
                errorExists = true;
            }
            //ids
            if (!compareIDs(sourceOrganization.getIds(),targetOrganization.getIds(),errorMessage + " -> IDs")) {
                errorExists = true;
            }
            //name
            if (!compareNamesON(sourceOrganization.getNames(),targetOrganization.getNames(),errorMessage + " -> Name")) {
                errorExists = true;
            }
            //telecom
            if (!compareTelcom(sourceOrganization.getTelecoms(),targetOrganization.getTelecoms(),errorMessage + " -> Telecoms")) {
                errorExists = true;
            }
            //addr
            if (!compareAddr(sourceOrganization.getAddrs(),targetOrganization.getAddrs(),errorMessage + " -> Addrs")) {
                errorExists = true;
            }
            //standardIndustryClassCode
            if (!compareCode(sourceOrganization.getStandardIndustryClassCode(),targetOrganization.getStandardIndustryClassCode(),errorMessage + " -> Standard Industry Class Code")) {
                errorExists = true;
            }
            //asOrganizationPartOf - OrganizationPartOf
            if (!organizationPartOfComparison(sourceOrganization.getAsOrganizationPartOf(),targetOrganization.getAsOrganizationPartOf(),errorMessage + " -> As Organization Part Of")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceOrganization.getNullFlavor(),targetOrganization.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //classCode
            if (!compareClassCode(sourceOrganization.getClassCode(),targetOrganization.getClassCode(),errorMessage + " -> Class Code")) {
                errorExists = true;
            }
            //determinerCode
            if (!compareDeterminerCode(sourceOrganization.getDeterminerCode(),targetOrganization.getDeterminerCode(),errorMessage + " -> Determiner Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private Boolean personComparison(Person sourcePerson, Person targetPerson, String errorMessage) {
        boolean errorExists = false;
        if (sourcePerson == null && targetPerson == null) {
            return true;
        } else if (sourcePerson != null && targetPerson != null) {
            //realm code
            if (!compareRealmCodes(sourcePerson.getRealmCodes(),targetPerson.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //typeID
            if (!typeIDComparison(sourcePerson.getTypeId(),targetPerson.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourcePerson.getTemplateIds(),targetPerson.getTemplateIds(),errorMessage + " -> Template ID")) {
                errorExists = true;
            }
            //name
            if (!compareNamesPN(sourcePerson.getNames(),targetPerson.getNames(),errorMessage + " -> Names")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourcePerson.getNullFlavor(),targetPerson.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //classCode
            if (!compareClassCode(sourcePerson.getClassCode(),targetPerson.getClassCode(),errorMessage + " -> Class Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private Boolean authorizingDeviceComparison(AuthoringDevice sourceAuthorizingDevice, AuthoringDevice targetAuthorizingDevice, String errorMessage) {
        boolean errorExists = false;
        if (sourceAuthorizingDevice == null && targetAuthorizingDevice == null) {
            return true;
        } else if (sourceAuthorizingDevice != null && targetAuthorizingDevice != null) {
            //realmCode
            if (!compareRealmCodes(sourceAuthorizingDevice.getRealmCodes(),targetAuthorizingDevice.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //type Id
            if (!typeIDComparison(sourceAuthorizingDevice.getTypeId(),targetAuthorizingDevice.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //template ID
            if (!compareTemplateID(sourceAuthorizingDevice.getTemplateIds(),targetAuthorizingDevice.getTemplateIds(),errorMessage + " -> TemplateID")) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceAuthorizingDevice.getCode(),targetAuthorizingDevice.getCode(),errorMessage + " -> Code")) {
                errorExists = true;
            }
            //manufacturedModelName
            if (!compareSCName(sourceAuthorizingDevice.getManufacturerModelName(),targetAuthorizingDevice.getManufacturerModelName(),errorMessage + " -> Manufactured Model Name")) {
                errorExists = true;
            }
            //softwareName
            if (!compareSCName(sourceAuthorizingDevice.getSoftwareName(),targetAuthorizingDevice.getSoftwareName(),errorMessage + " -> Software Name")) {
                errorExists = true;
            }
            //asMaintainedEntity - MaintainedEntity
            if (!maintainedEntityComparison(sourceAuthorizingDevice.getAsMaintainedEntities(),targetAuthorizingDevice.getAsMaintainedEntities(),errorMessage + " -> As Maintained Entity's")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceAuthorizingDevice.getNullFlavor(),targetAuthorizingDevice.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //classCode
            if (!compareClassCode(sourceAuthorizingDevice.getClassCode(),targetAuthorizingDevice.getClassCode(),errorMessage + " -> Class Code")) {
                errorExists = true;
            }
            //determinerCode
            if (!compareDeterminerCode(sourceAuthorizingDevice.getDeterminerCode(),targetAuthorizingDevice.getDeterminerCode(),errorMessage + " -> Determiner Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private Boolean informantsComparison(EList<Informant12> sourceInformant12, EList<Informant12> targetInformant12, String errorMessage) {
        Boolean errorExists = false;
        for (int i=0; i<sourceInformant12.size(); i++) {
            int targetMatches = 0;
            for (int j = 0; j < sourceInformant12.size(); j++) {
                boolean specificError = false;
                //realmCode
                if (!compareRealmCodes(sourceInformant12.get(i).getRealmCodes(), targetInformant12.get(j).getRealmCodes(), errorMessage + " -> Realm Codes")) {
                    specificError = true;
                }
                //typeID
                if (!typeIDComparison(sourceInformant12.get(i).getTypeId(), targetInformant12.get(j).getTypeId(), errorMessage + " -> Type ID")) {
                    specificError = true;
                }
                //template ID
                if (!compareTemplateID(sourceInformant12.get(i).getTemplateIds(), targetInformant12.get(j).getTemplateIds(), errorMessage + " -> Template IDS")) {
                    specificError = true;
                }
                //choice - assignedEntity, related entity
                if (!(assignedEntityComparison(sourceInformant12.get(i).getAssignedEntity(), targetInformant12.get(j).getAssignedEntity(), errorMessage + " -> Assigned Entity") || relatedEntityComparison(sourceInformant12.get(i).getRelatedEntity(), targetInformant12.get(j).getRelatedEntity(), errorMessage + " -> Related Entity"))) {
                    specificError = true;
                }
                //nullflavor
                if (!compareNullFlavor(sourceInformant12.get(i).getNullFlavor(), targetInformant12.get(j).getNullFlavor(), errorMessage + " -> Null Flavor")) {
                    specificError = true;
                }
                //type code
                if (!compareTypeCode(sourceInformant12.get(i).getTypeCode(), targetInformant12.get(j).getTypeCode(), errorMessage + " -> Type Code")) {
                    specificError = true;
                }
                //contextControlCode
                if (!compareContextControlCode(sourceInformant12.get(i).getContextControlCode(), targetInformant12.get(j).getContextControlCode(), errorMessage + " -> Context Control Code")) {
                    specificError = true;
                }

                if (!specificError) {
                    targetMatches++;
                }
            }
            if (targetMatches == 0) {
                errorExists = true;
                comparisonResult.addMessage("Informants Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
                comparisonResult.addMessage("Informants Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private Boolean recordTargetsComparison(EList<RecordTarget> sourceRecordTargets, EList<RecordTarget> targetRecordTargets, String errorMessage) {
        Boolean errorExists = false;
        for (int i = 0; i < sourceRecordTargets.size(); i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetRecordTargets.size(); j++) {
                boolean specificError = false;
                //compare realmCode
                if (!compareRealmCodes(sourceRecordTargets.get(i).getRealmCodes(), targetRecordTargets.get(j).getRealmCodes(), errorMessage + " -> Realm Codes")) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourceRecordTargets.get(i).getTypeId(), targetRecordTargets.get(j).getTypeId(), errorMessage + " -> Type ID")) {
                    specificError = true;
                }
                //compareTemplateID
                if (!compareTemplateID(sourceRecordTargets.get(i).getTemplateIds(), targetRecordTargets.get(j).getTemplateIds(), errorMessage + " -> Template IDS")) {
                    specificError = true;
                }
                //comparePatientRole
                if (!patientRoleComparison(sourceRecordTargets.get(i).getPatientRole(), targetRecordTargets.get(j).getPatientRole(), errorMessage + " -> Patient Role")) {
                    specificError = true;
                }
                //compare nullFlavor
                if (!compareNullFlavor(sourceRecordTargets.get(i).getNullFlavor(), targetRecordTargets.get(j).getNullFlavor(), errorMessage + " -> Null Flavor")) {
                    specificError = true;
                }
                //compare typeCode
                if (!compareTypeCode(sourceRecordTargets.get(i).getTypeCode(), targetRecordTargets.get(j).getTypeCode(), errorMessage + " -> Type Code")) {
                    specificError = true;
                }

                if (!specificError) {
                    targetMatches++;
                }
            }
            if (targetMatches == 0) {
                errorExists = true;
                comparisonResult.addMessage("Record Targets Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
                comparisonResult.addMessage("Record Targets Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }


    private Boolean authorsComparison(EList<Author> sourceAuthors, EList<Author> targetAuthors, String errorMessage) {
        Boolean errorExists = false;
        for (int i=0; i<sourceAuthors.size(); i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetAuthors.size(); j++) {
                boolean specificError = false;
                //compare realmCodes
                if (!compareRealmCodes(sourceAuthors.get(i).getRealmCodes(), targetAuthors.get(j).getRealmCodes(), errorMessage + " -> Realm Codes")) {
                    specificError = true;
                }
                //compare typeID
                if (!typeIDComparison(sourceAuthors.get(i).getTypeId(), targetAuthors.get(j).getTypeId(), errorMessage + " -> typeID")) {
                    specificError = true;
                }
                //compare template ID
                if (!compareTemplateID(sourceAuthors.get(i).getTemplateIds(), targetAuthors.get(j).getTemplateIds(), errorMessage + " -> Template IDS")) {
                    specificError = true;
                }
                //compare functionCode
                if (!compareFunctionCode(sourceAuthors.get(i).getFunctionCode(),targetAuthors.get(j).getFunctionCode(),errorMessage + " -> Function Code")) {
                    specificError = true;
                }
                //compare time
                if (!compareTime(sourceAuthors.get(i).getTime(), targetAuthors.get(j).getTime(), errorMessage + " -> Time")) {
                    specificError = true;
                }
                //compare AssignedAuthor
                if (!assignedAuthorComparison(sourceAuthors.get(i).getAssignedAuthor(), targetAuthors.get(j).getAssignedAuthor(), errorMessage + " -> Assigned Author")) {
                    specificError = true;
                }
                //compare nullFlavor
                if (!compareNullFlavor(sourceAuthors.get(i).getNullFlavor(), targetAuthors.get(j).getNullFlavor(), errorMessage + " -> Null Flavor")) {
                    specificError = true;
                }
                //compare typeCode
                if (!compareTypeCode(sourceAuthors.get(i).getTypeCode(), targetAuthors.get(j).getTypeCode(), errorMessage + " -> Type Code")) {
                    specificError = true;
                }
                //compare contextControlCode
                if (!compareContextControlCode(sourceAuthors.get(i).getContextControlCode(), targetAuthors.get(j).getContextControlCode(), errorMessage + " -> Context Control Code")) {
                    specificError = true;
                }

                if (!specificError) {
                    targetMatches++;
                }
            }
            if (targetMatches == 0) {
                errorExists = true;
                comparisonResult.addMessage("Authors Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
                comparisonResult.addMessage("Authors Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private Boolean participants1Comparison(EList<Participant1> sourceParticipant, EList<Participant1> targetParticipant, String errorMessage) {
        Boolean errorExists = false;
        for (int i=0;i<sourceParticipant.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetParticipant.size(); j++) {
                boolean specificError = false;
                //realmCodes
                if (!compareRealmCodes(sourceParticipant.get(i).getRealmCodes(), targetParticipant.get(j).getRealmCodes(), errorMessage + " -> Realm Codes")) {
                    specificError = true;
                }
                //typeID
                if (!typeIDComparison(sourceParticipant.get(i).getTypeId(), targetParticipant.get(j).getTypeId(), errorMessage + " - Type ID")) {
                    specificError = true;
                }
                //templateID
                if (!compareTemplateID(sourceParticipant.get(i).getTemplateIds(), targetParticipant.get(j).getTemplateIds(), errorMessage + " -> Template IDS")) {
                    specificError = true;
                }
                //functionCode
                if (!compareFunctionCode(sourceParticipant.get(i).getFunctionCode(), targetParticipant.get(j).getFunctionCode(), errorMessage + " -> Function Code")) {
                    specificError = true;
                }
                //time
                if (!compareTime(sourceParticipant.get(i).getTime(), targetParticipant.get(j).getTime(), errorMessage + " -> Time")) {
                    specificError = true;
                }
                //associated Entity
                if (!associatedEntityComparison(sourceParticipant.get(i).getAssociatedEntity(), targetParticipant.get(j).getAssociatedEntity(), errorMessage + " -> Associated Entity")) {
                    specificError = true;
                }
                //nullFlavor
                if (!compareNullFlavor(sourceParticipant.get(i).getNullFlavor(), targetParticipant.get(j).getNullFlavor(), errorMessage + " -> Null Flavor")) {
                    specificError = true;
                }
                //typeCode
                if (!compareTypeCode(sourceParticipant.get(i).getTypeCode(), targetParticipant.get(j).getTypeCode(), errorMessage + " -> Type Code")) {
                    specificError = true;
                }

                if (!specificError) {
                    targetMatches++;
                }
            }
            if (targetMatches == 0) {
                errorExists = true;
                comparisonResult.addMessage("Participants Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
                comparisonResult.addMessage("Participants Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private Boolean documentationOfsComparison(EList<DocumentationOf> sourceDocumentationOf, EList<DocumentationOf> targetDocumentationOf, String errorMessage) {
        Boolean errorExists = false;
        for (int i=0;i<sourceDocumentationOf.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetDocumentationOf.size(); j++) {
                boolean specificError = false;
                //realmCodes
                if (!compareRealmCodes(sourceDocumentationOf.get(i).getRealmCodes(), targetDocumentationOf.get(j).getRealmCodes(), errorMessage + " -> Realm Codes")) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourceDocumentationOf.get(i).getTypeId(), targetDocumentationOf.get(j).getTypeId(), errorMessage + " -> typeID")) {
                    specificError = true;
                }
                //compare templateID
                if (!compareTemplateID(sourceDocumentationOf.get(i).getTemplateIds(), targetDocumentationOf.get(j).getTemplateIds(), errorMessage + " -> Template IDS")) {
                    specificError = true;
                }
                //compare serviceEvent
                if (!serviceEventComparison(sourceDocumentationOf.get(i).getServiceEvent(), targetDocumentationOf.get(j).getServiceEvent(), errorMessage + " -> Service Event")) {
                    specificError = true;
                }
                //compare NullFlavor
                if (!compareNullFlavor(sourceDocumentationOf.get(i).getNullFlavor(), targetDocumentationOf.get(j).getNullFlavor(), errorMessage + " -> Null Flavor")) {
                    specificError = true;
                }
                //compare typeCode
                if (!compareTypeCode(sourceDocumentationOf.get(i).getTypeCode(), targetDocumentationOf.get(j).getTypeCode(), errorMessage + " -> Type Code")){
                    specificError = true;
                }

                if(!specificError) {
                    targetMatches++;
                }

            }
            if (targetMatches == 0) {
                errorExists = true;
                comparisonResult.addMessage("DocumentationsOf Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
                comparisonResult.addMessage("DocumentationsOf Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private Boolean organizationsComparison(EList<Organization> sourceOrganization, EList<Organization> targetOrganization, String errorMessage)  {
        Boolean errorExists = false;
        for (int i=0;i<sourceOrganization.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetOrganization.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (!compareRealmCodes(sourceOrganization.get(i).getRealmCodes(), targetOrganization.get(j).getRealmCodes(), errorMessage + " -> Realm Codes")) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourceOrganization.get(i).getTypeId(), targetOrganization.get(j).getTypeId(), errorMessage + " -> typeID")) {
                    specificError = true;
                }
                //compare templateID
                if (!compareTemplateID(sourceOrganization.get(i).getTemplateIds(), targetOrganization.get(j).getTemplateIds(), errorMessage + " -> Template IDS")) {
                    specificError = true;
                }
                //compare IDs
                if (!compareIDs(sourceOrganization.get(i).getIds(), targetOrganization.get(j).getIds(), errorMessage + " -> IDs")) {
                    specificError = true;
                }
                //compare name
                if (!compareNamesON(sourceOrganization.get(i).getNames(),targetOrganization.get(j).getNames(),errorMessage + " -> Names")) {
                    specificError = true;
                }
                //compare Telecom
                if (!compareTelcom(sourceOrganization.get(i).getTelecoms(),targetOrganization.get(j).getTelecoms(),errorMessage + " -> Telecoms")) {
                    specificError = true;
                }
                //compare Addr
                if (!compareAddr(sourceOrganization.get(i).getAddrs(),targetOrganization.get(j).getAddrs(),errorMessage + " -> Addrs")) {
                    specificError = true;
                }
                //compare StandardIndustryClassCode
                if (!compareCode(sourceOrganization.get(i).getStandardIndustryClassCode(),targetOrganization.get(j).getStandardIndustryClassCode(),errorMessage + " -> Standard Industry Class Code")) {
                    specificError = true;
                }
                //compare asOrganizationPartOf - OrganizationPartOf
                if (!organizationPartOfComparison(sourceOrganization.get(i).getAsOrganizationPartOf(),targetOrganization.get(j).getAsOrganizationPartOf(),errorMessage + " -> OrganizationPartOf")) {
                    specificError = true;
                }
                //compare NullFlavor
                if (!compareNullFlavor(sourceOrganization.get(i).getNullFlavor(), targetOrganization.get(j).getNullFlavor(), errorMessage + " -> Null Flavor")) {
                    specificError = true;
                }
                //compare classCode
                if (!compareClassCode(sourceOrganization.get(i).getClassCode(), targetOrganization.get(j).getClassCode(), errorMessage + " -> Type Code")) {
                    specificError = true;
                }
                //compare Determiner Code
                if (!compareDeterminerCode(sourceOrganization.get(i).getDeterminerCode(),targetOrganization.get(j).getDeterminerCode(),errorMessage + " -> Determiner Code")) {
                    specificError = true;
                }

                if (!specificError) {
                    targetMatches++;
                }

            }
            if (targetMatches == 0) {
                errorExists = true;
                comparisonResult.addMessage("Organization Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
                comparisonResult.addMessage("Organization Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private Boolean informationRecipientComparison(EList<InformationRecipient> sourceInformationRecipient, EList<InformationRecipient> targetInformationRecipient, String errorMessage) {
        Boolean errorExists = false;
        for (int i=0;i<sourceInformationRecipient.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetInformationRecipient.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (!compareRealmCodes(sourceInformationRecipient.get(i).getRealmCodes(), targetInformationRecipient.get(j).getRealmCodes(), errorMessage + " -> Realm Codes")) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourceInformationRecipient.get(i).getTypeId(), targetInformationRecipient.get(j).getTypeId(), errorMessage + " -> typeID")) {
                    specificError = true;
                }
                //compare templateID
                if (!compareTemplateID(sourceInformationRecipient.get(i).getTemplateIds(), targetInformationRecipient.get(j).getTemplateIds(), errorMessage + " -> Template IDS")) {
                    specificError = true;
                }
                //compare Intended Recipient
                if (!intendedRecipientComparison(sourceInformationRecipient.get(i).getIntendedRecipient(), targetInformationRecipient.get(j).getIntendedRecipient(), errorMessage + " -> IntendedRecipient")) {
                    specificError = true;
                }
                //compare NullFlavor
                if (!compareNullFlavor(sourceInformationRecipient.get(i).getNullFlavor(), targetInformationRecipient.get(j).getNullFlavor(), errorMessage + " -> Null Flavor")) {
                    specificError = true;
                }
                //compare typeCode
                if (!compareTypeCode(sourceInformationRecipient.get(i).getTypeCode(), targetInformationRecipient.get(j).getTypeCode(), errorMessage + " -> Type Code")) {
                    specificError = true;
                }

                if (!specificError) {
                    targetMatches++;
                }

            }
            if (targetMatches == 0) {
                errorExists = true;
                comparisonResult.addMessage("DocumentationsOf Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
                comparisonResult.addMessage("DocumentationsOf Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private Boolean inFulfillmentOfComparison(EList<InFulfillmentOf> sourceInFulfillmentOf, EList<InFulfillmentOf> targetInFulfillmentOf, String errorMessage) {
        Boolean errorExists = false;
        for (int i=0;i<sourceInFulfillmentOf.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetInFulfillmentOf.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (!compareRealmCodes(sourceInFulfillmentOf.get(i).getRealmCodes(), targetInFulfillmentOf.get(j).getRealmCodes(), errorMessage + " -> Realm Codes")) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourceInFulfillmentOf.get(i).getTypeId(), targetInFulfillmentOf.get(j).getTypeId(), errorMessage + " -> typeID")) {
                    specificError = true;
                }
                //compare templateID
                if (!compareTemplateID(sourceInFulfillmentOf.get(i).getTemplateIds(), targetInFulfillmentOf.get(j).getTemplateIds(), errorMessage + " -> Template IDS")) {
                    specificError = true;
                }
                //compare Order
                if (!orderComparison(sourceInFulfillmentOf.get(i).getOrder(), targetInFulfillmentOf.get(j).getOrder(), errorMessage + " -> Order")) {
                    specificError = true;
                }
                //compare NullFlavor
                if (!compareNullFlavor(sourceInFulfillmentOf.get(i).getNullFlavor(), targetInFulfillmentOf.get(j).getNullFlavor(), errorMessage + " -> Null Flavor")) {
                    specificError = true;
                }
                //compare typeCode
                if (!compareTypeCode(sourceInFulfillmentOf.get(i).getTypeCode(), targetInFulfillmentOf.get(j).getTypeCode(), errorMessage + " -> Type Code")) {
                    specificError = true;
                }

                if (!specificError) {
                    targetMatches++;
                }

            }
            if (targetMatches == 0) {
                errorExists = true;
                comparisonResult.addMessage("DocumentationsOf Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
                comparisonResult.addMessage("DocumentationsOf Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private Boolean relatedDocumentsComparison(EList<RelatedDocument> sourceRelatedDocument, EList<RelatedDocument> targetRelatedDocument, String errorMessage) {
        Boolean errorExists = false;
        for (int i=0;i<sourceRelatedDocument.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetRelatedDocument.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (compareRealmCodes(sourceRelatedDocument.get(i).getRealmCodes(), targetRelatedDocument.get(j).getRealmCodes(), errorMessage + " -> Realm Codes")) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourceRelatedDocument.get(i).getTypeId(), targetRelatedDocument.get(j).getTypeId(), errorMessage + " -> typeID")) {
                    specificError = true;
                }
                //compare templateID
                if (!compareTemplateID(sourceRelatedDocument.get(i).getTemplateIds(), targetRelatedDocument.get(j).getTemplateIds(), errorMessage + " -> Template IDS")) {
                    specificError = true;
                }
                //compare Order
                if (!parentDocumentComparison(sourceRelatedDocument.get(i).getParentDocument(), targetRelatedDocument.get(j).getParentDocument(), errorMessage + " -> Order")) {
                    specificError = true;
                }
                //compare NullFlavor
                if (!compareNullFlavor(sourceRelatedDocument.get(i).getNullFlavor(), targetRelatedDocument.get(j).getNullFlavor(), errorMessage + " -> Null Flavor")) {
                    specificError = true;
                }
                //compare typeCode
                if (!compareTypeCode(sourceRelatedDocument.get(i).getTypeCode(), targetRelatedDocument.get(j).getTypeCode(), errorMessage + " -> Type Code")) {
                    specificError = true;
                }


                if (!specificError){
                    targetMatches++;
                }

            }
            if (targetMatches == 0) {
                errorExists = true;
                comparisonResult.addMessage("DocumentationsOf Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
                comparisonResult.addMessage("DocumentationsOf Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private Boolean authorizationsComparison(EList<Authorization> sourceAuthorizations, EList<Authorization> targetAuthorizations, String errorMessage) {
        Boolean errorExists = false;
        for (int i=0;i<sourceAuthorizations.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetAuthorizations.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (!compareRealmCodes(sourceAuthorizations.get(i).getRealmCodes(), targetAuthorizations.get(j).getRealmCodes(), errorMessage + " -> Realm Codes")) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourceAuthorizations.get(i).getTypeId(), targetAuthorizations.get(j).getTypeId(), errorMessage + " -> typeID")) {
                    specificError = true;
                }
                //compare templateID
                if (!compareTemplateID(sourceAuthorizations.get(i).getTemplateIds(), targetAuthorizations.get(j).getTemplateIds(), errorMessage + " -> Template IDS")) {
                    specificError = true;
                }
                //compare Consent
                if (!consentComparison(sourceAuthorizations.get(i).getConsent(), targetAuthorizations.get(j).getConsent(), errorMessage + " -> Consent")) {
                    specificError = true;
                }
                //compare NullFlavor
                if (!compareNullFlavor(sourceAuthorizations.get(i).getNullFlavor(), targetAuthorizations.get(j).getNullFlavor(), errorMessage + " -> Null Flavor")) {
                    specificError = true;
                }
                //compare typeCode
                if (!compareTypeCode(sourceAuthorizations.get(i).getTypeCode(), targetAuthorizations.get(j).getTypeCode(), errorMessage + " -> Type Code")) {
                    specificError = true;
                }

                if (!specificError){
                    targetMatches++;
                }

            }
            if (targetMatches == 0) {
                errorExists = true;
                comparisonResult.addMessage("DocumentationsOf Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
                comparisonResult.addMessage("DocumentationsOf Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private Boolean componentOfComparison(Component1 sourceComponent1, Component1 targetComponent1, String errorMessage) {
        boolean errorExists = false;
        if (sourceComponent1 == null && targetComponent1 == null) {
            return true;
        } else if (sourceComponent1 != null && targetComponent1 != null) {
            //realm code
            if (!compareRealmCodes(sourceComponent1.getRealmCodes(),targetComponent1.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //typeID
            if (!typeIDComparison(sourceComponent1.getTypeId(),targetComponent1.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceComponent1.getTemplateIds(),targetComponent1.getTemplateIds(),errorMessage + " -> Template ID")) {
                errorExists = true;
            }
            //Encompassong Encounter
            if (!encompassingEncounterComparison(sourceComponent1.getEncompassingEncounter(),targetComponent1.getEncompassingEncounter(),errorMessage + " -> Encompassing Encounter")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceComponent1.getNullFlavor(),targetComponent1.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //TypeCode
            if (!compareTypeCode(sourceComponent1.getTypeCode(),targetComponent1.getTypeCode(),errorMessage + " -> Class Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private Boolean guardiansComparison(EList<Guardian> sourceGuardian, EList<Guardian> targetGuardian, String errorMessage) {
        Boolean errorExists = false;
        for (int i=0;i<sourceGuardian.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetGuardian.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (!compareRealmCodes(sourceGuardian.get(i).getRealmCodes(), targetGuardian.get(j).getRealmCodes(), errorMessage + " -> Realm Codes")) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourceGuardian.get(i).getTypeId(), targetGuardian.get(j).getTypeId(), errorMessage + " -> typeID")) {
                    specificError = true;
                }
                //compare templateID
                if (!compareTemplateID(sourceGuardian.get(i).getTemplateIds(), targetGuardian.get(j).getTemplateIds(), errorMessage + " -> Template IDS")) {
                    specificError = true;
                }
                //compare IDs
                if (!compareIDs(sourceGuardian.get(i).getIds(), targetGuardian.get(j).getIds(), errorMessage + " -> IDs")) {
                    specificError = true;
                }
                //compare code
                if (!compareCode(sourceGuardian.get(i).getCode(),targetGuardian.get(j).getCode(),errorMessage + " -> Code")) {
                    specificError = true;
                }
                //compare Telecom
                if (!compareTelcom(sourceGuardian.get(i).getTelecoms(),targetGuardian.get(j).getTelecoms(),errorMessage + " -> Telecoms")) {
                    specificError = true;
                }
                //compare Addr
                if (!compareAddr(sourceGuardian.get(i).getAddrs(),targetGuardian.get(j).getAddrs(),errorMessage + " -> Addrs")) {
                    specificError = true;
                }
                //choice - guardianPerson (Person) and guardianOrganization (Organization)
                if (!(personComparison(sourceGuardian.get(i).getGuardianPerson(),targetGuardian.get(j).getGuardianPerson(),errorMessage + " -> Standard Undustry Class Code") || organizationComparison(sourceGuardian.get(i).getGuardianOrganization(),targetGuardian.get(j).getGuardianOrganization(),errorMessage + " -> OrganizationPartOf"))) {
                    specificError = true;
                }
                //compare NullFlavor
                if (!compareNullFlavor(sourceGuardian.get(i).getNullFlavor(), targetGuardian.get(j).getNullFlavor(), errorMessage + " -> Null Flavor")) {
                    specificError = true;
                }
                //compare classCode
                if (!compareClassCode(sourceGuardian.get(i).getClassCode(), targetGuardian.get(j).getClassCode(), errorMessage + " -> Type Code")) {
                    specificError = true;
                }

                if (!specificError){
                    targetMatches++;
                }

            }
            if (targetMatches == 0) {
                errorExists = true;
                comparisonResult.addMessage("Organization Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
                comparisonResult.addMessage("Organization Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private Boolean birthplaceComparison(Birthplace sourceBirthplace, Birthplace targetBirthplace, String errorMessage) {
        boolean errorExists = false;
        if (sourceBirthplace == null && targetBirthplace == null) {
            return true;
        } else if (sourceBirthplace != null && targetBirthplace != null) {
            //realm code
            if (!compareRealmCodes(sourceBirthplace.getRealmCodes(),targetBirthplace.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //typeID
            if (!typeIDComparison(sourceBirthplace.getTypeId(),targetBirthplace.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceBirthplace.getTemplateIds(),targetBirthplace.getTemplateIds(),errorMessage + " -> Template ID")) {
                errorExists = true;
            }
            //Place
            if (!placeComparison(sourceBirthplace.getPlace(),targetBirthplace.getPlace(),errorMessage + " -> Encompassing Encounter")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceBirthplace.getNullFlavor(),targetBirthplace.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //TypeCode
            if (!compareClassCode(sourceBirthplace.getClassCode(),targetBirthplace.getClassCode(),errorMessage + " -> Class Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private Boolean languageCommunicationsComparison(EList<LanguageCommunication> sourceLanguageCommmunication, EList<LanguageCommunication> targetLanguageCommunication, String errorMessage) {
        Boolean errorExists = false;
        for (int i=0;i<sourceLanguageCommmunication.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetLanguageCommunication.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (!compareRealmCodes(sourceLanguageCommmunication.get(i).getRealmCodes(), targetLanguageCommunication.get(j).getRealmCodes(), errorMessage + " -> Realm Codes")) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourceLanguageCommmunication.get(i).getTypeId(), targetLanguageCommunication.get(j).getTypeId(), errorMessage + " -> typeID")) {
                    specificError = true;
                }
                //compare language code
                if (!compareLanguageCode(sourceLanguageCommmunication.get(i).getLanguageCode(), targetLanguageCommunication.get(j).getLanguageCode(), errorMessage + " -> Language Code")) {
                    specificError = true;
                }
                //compare Moode Code
                if (!compareCode(sourceLanguageCommmunication.get(i).getModeCode(), targetLanguageCommunication.get(j).getModeCode(), errorMessage + " -> IDs")) {
                    specificError = true;
                }
                //compare Proficiency Level Code
                if (!compareCode(sourceLanguageCommmunication.get(i).getProficiencyLevelCode(),targetLanguageCommunication.get(j).getProficiencyLevelCode(),errorMessage + " -> Code")) {
                    specificError = true;
                }
                //compare PreferenceInd
                if (!comparePreferenceInd(sourceLanguageCommmunication.get(i).getPreferenceInd(),targetLanguageCommunication.get(j).getPreferenceInd(),errorMessage + " -> Telecoms")) {
                    specificError = true;
                }
                //compare NullFlavor
                if (!compareNullFlavor(sourceLanguageCommmunication.get(i).getNullFlavor(), targetLanguageCommunication.get(j).getNullFlavor(), errorMessage + " -> Null Flavor")) {
                    specificError = true;
                }


                if (!specificError){
                    targetMatches++;
                }

            }
            if (targetMatches == 0) {
                errorExists = true;
                comparisonResult.addMessage("Organization Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
                comparisonResult.addMessage("Organization Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private Boolean custodianOrganizationComparison(CustodianOrganization sourceCustodianOrganization, CustodianOrganization targetCustodianOrganization, String errorMessage) {
        boolean errorExists = false;
        if (sourceCustodianOrganization == null && targetCustodianOrganization == null) {
            return true;
        } else if (sourceCustodianOrganization != null && targetCustodianOrganization != null) {
            //realmCode
            if (!compareRealmCodes(sourceCustodianOrganization.getRealmCodes(),targetCustodianOrganization.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //type Id
            if (!typeIDComparison(sourceCustodianOrganization.getTypeId(),targetCustodianOrganization.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //template ID
            if (!compareTemplateID(sourceCustodianOrganization.getTemplateIds(),targetCustodianOrganization.getTemplateIds(),errorMessage + " -> TemplateID")) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceCustodianOrganization.getIds(),targetCustodianOrganization.getIds(),errorMessage + " -> IDs")) {
                errorExists = true;
            }
            //name
            if (!compareNamesEN(sourceCustodianOrganization.getNames(),targetCustodianOrganization.getNames(),errorMessage + " -> Manufactured Model Name")) {
                errorExists = true;
            }
            //telecom
            if (!compareTelcom(sourceCustodianOrganization.getTelecoms(),targetCustodianOrganization.getTelecoms(),errorMessage + " -> Telecoms")) {
                errorExists = true;
            }
            // addr
            if (!compareAddr(sourceCustodianOrganization.getAddrs(),targetCustodianOrganization.getAddrs(),errorMessage + " -> Addrs")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceCustodianOrganization.getNullFlavor(),targetCustodianOrganization.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //classCode
            if (!compareClassCode(sourceCustodianOrganization.getClassCode(),targetCustodianOrganization.getClassCode(),errorMessage + " -> Class Code")) {
                errorExists = true;
            }
            //determinerCode
            if (!compareDeterminerCode(sourceCustodianOrganization.getDeterminerCode(),targetCustodianOrganization.getDeterminerCode(),errorMessage + " -> Determiner Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean performersComparison(EList<Performer1> sourcePerformer1, EList<Performer1> targetPerformer1, String errorMessage) {
        Boolean errorExists = false;
//        for (int i=0;i<sourcePerformer1.size();i++) {
//            int targetMatches = 0;
//            for (int j = 0; j < targetPerformer1.size(); j++) {
//                boolean specificError = false;
//                //compare RealmCodes
//                if (!compareRealmCodes(sourcePerformer1.get(i).getRealmCodes(), targetPerformer1.get(j).getRealmCodes(), errorMessage + " -> Realm Codes")) {
//                    specificError = true;
//                }
//                //compare TypeID
//                if (!typeIDComparison(sourcePerformer1.get(i).getTypeId(), targetPerformer1.get(j).getTypeId(), errorMessage + " -> typeID")) {
//                    specificError = true;
//                }
//                //compare Template ID
//                if (!compareTemplateID(sourcePerformer1.get(i).getTemplateIds(), targetPerformer1.get(j).getTemplateIds(), errorMessage + " -> Template IDs")) {
//                    specificError = true;
//                }
//                //compare FunctionCode
//                if (!compareCode(sourcePerformer1.get(i).getFunctionCode(), targetPerformer1.get(j).getFunctionCode(), errorMessage + " -> Function Code")) {
//                    specificError = true;
//                }
//                //compare Time
//                if (!compareTime(sourcePerformer1.get(i).getTime(),targetPerformer1.get(j).getTime(),errorMessage + " -> Time")) {
//                    specificError = true;
//                }
//                //compare AssignedEntity
//                if (!assignedEntityComparison(sourcePerformer1.get(i).getAssignedEntity(),targetPerformer1.get(j).getAssignedEntity(),errorMessage + " -> AssignedEntity")) {
//                    specificError = true;
//                }
//                //typeCode
//                if (!compareTypeCode(sourcePerformer1.get(i).getTypeCode(),targetPerformer1.get(j).getTypeCode(),errorMessage + " -> TypeCode")) {
//                    specificError = true;
//                }
//                //compare NullFlavor
//                if (!compareNullFlavor(sourcePerformer1.get(i).getNullFlavor(), targetPerformer1.get(j).getNullFlavor(), errorMessage + " -> Null Flavor")) {
//                    specificError = true;
//                }
//
//
//                if (!specificError){
//                    targetMatches++;
//                }
//
//            }
//            if (targetMatches == 0) {
//                errorExists = true;
//                comparisonResult.addMessage("Organization Comparison Error source " + i + " in " + errorMessage + "\n");
//            }  else if (targetMatches>1) {
//                comparisonResult.addMessage("Organization Comparison Warning source " + i + " in " + errorMessage + "\n");
//            }
//        }
        return !errorExists;
    }

    private Boolean authenticatorComparison(EList<Authenticator> sourceAuthenticator, EList<Authenticator> targetAuthenticator, String errorMessage) {
        Boolean errorExists = false;
        for (int i=0;i<sourceAuthenticator.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetAuthenticator.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (!compareRealmCodes(sourceAuthenticator.get(i).getRealmCodes(), targetAuthenticator.get(j).getRealmCodes(), errorMessage + " -> Realm Codes")) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourceAuthenticator.get(i).getTypeId(), targetAuthenticator.get(j).getTypeId(), errorMessage + " -> typeID")) {
                    specificError = true;
                }
                //compare TemplateIDs
                if (!compareTemplateID(sourceAuthenticator.get(i).getTemplateIds(), targetAuthenticator.get(j).getTemplateIds(), errorMessage + " -> TemplateID")) {
                    specificError = true;
                }
                //compare Time
                if (!compareTime(sourceAuthenticator.get(i).getTime(), targetAuthenticator.get(j).getTime(), errorMessage + " -> Time")) {
                    specificError = true;
                }
                //compare Signature Code
                if (!compareCode(sourceAuthenticator.get(i).getSignatureCode(),targetAuthenticator.get(j).getSignatureCode(),errorMessage + " -> SignatureCode")) {
                    specificError = true;
                }
                //compare Assigned Entity
                if (!assignedEntityComparison(sourceAuthenticator.get(i).getAssignedEntity(),targetAuthenticator.get(j).getAssignedEntity(),errorMessage + " -> Assigned Entity")) {
                    specificError = true;
                }
                //compare Type Code
                if (!compareTypeCode(sourceAuthenticator.get(i).getTypeCode(),targetAuthenticator.get(j).getTypeCode(),errorMessage + " -> TypeCode")) {
                    specificError = true;
                }
                //compare NullFlavor
                if (!compareNullFlavor(sourceAuthenticator.get(i).getNullFlavor(), targetAuthenticator.get(j).getNullFlavor(), errorMessage + " -> Null Flavor")) {
                    specificError = true;
                }


                if (!specificError){
                    targetMatches++;
                }

            }
            if (targetMatches == 0) {
                errorExists = true;
                comparisonResult.addMessage("Organization Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
                comparisonResult.addMessage("Organization Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private Boolean component3Comparison(EList<Component3> sourceComponent3, EList<Component3> targetComponent3, String errorMessage) {
        Boolean errorExists = false;
        for (int i=0;i<sourceComponent3.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetComponent3.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (!compareRealmCodes(sourceComponent3.get(i).getRealmCodes(), targetComponent3.get(j).getRealmCodes(), errorMessage + " -> Realm Codes")) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourceComponent3.get(i).getTypeId(), targetComponent3.get(j).getTypeId(), errorMessage + " -> typeID")) {
                    specificError = true;
                }
                //compare TemplateIDs
                if (!compareTemplateID(sourceComponent3.get(i).getTemplateIds(), targetComponent3.get(j).getTemplateIds(), errorMessage + " -> TemplateID")) {
                    specificError = true;
                }
                //compare Section
                if (!sectionComparison(sourceComponent3.get(i).getSection(), targetComponent3.get(j).getSection(), errorMessage + " -> Section")) {
                    specificError = true;
                }
                //compare contextConductionInd
                if (!compareContextConductionInd(sourceComponent3.get(i).getContextConductionInd(),targetComponent3.get(j).getContextConductionInd(),errorMessage + " -> ContextConductionInd")) {
                    specificError = true;
                }
                //compare Type Code
                if (!compareTypeCode(sourceComponent3.get(i).getTypeCode(),targetComponent3.get(j).getTypeCode(),errorMessage + " -> TypeCode")) {
                    specificError = true;
                }
                //compare NullFlavor
                if (!compareNullFlavor(sourceComponent3.get(i).getNullFlavor(), targetComponent3.get(j).getNullFlavor(), errorMessage + " -> Null Flavor")) {
                    specificError = true;
                }


                if (!specificError){
                    targetMatches++;
                }

            }
            if (targetMatches == 0) {
                errorExists = true;
                comparisonResult.addMessage("Organization Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
                comparisonResult.addMessage("Organization Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private Boolean organizationPartOfComparison(OrganizationPartOf sourceOrganizationPartOf, OrganizationPartOf targetOrganizationPartOf, String errorMessage) {
        boolean errorExists = false;
        if (sourceOrganizationPartOf == null && targetOrganizationPartOf == null) {
            return true;
        } else if (sourceOrganizationPartOf != null && targetOrganizationPartOf != null) {
            //realmCode
            if (!compareRealmCodes(sourceOrganizationPartOf.getRealmCodes(),targetOrganizationPartOf.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //type Id
            if (!typeIDComparison(sourceOrganizationPartOf.getTypeId(),targetOrganizationPartOf.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //template ID
            if (!compareTemplateID(sourceOrganizationPartOf.getTemplateIds(),targetOrganizationPartOf.getTemplateIds(),errorMessage + " -> TemplateID")) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceOrganizationPartOf.getIds(),targetOrganizationPartOf.getIds(),errorMessage + " -> IDs")) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceOrganizationPartOf.getCode(),targetOrganizationPartOf.getCode(),errorMessage + " -> Code")) {
                errorExists = true;
            }
            //statusCode
            if (!compareCode(sourceOrganizationPartOf.getStatusCode(),targetOrganizationPartOf.getStatusCode(),errorMessage + " -> Status Code")) {
                errorExists = true;
            }
            // effectiveTime
            if (!compareEffectiveTime(sourceOrganizationPartOf.getEffectiveTime(),targetOrganizationPartOf.getEffectiveTime(),errorMessage + " -> EffectiveTime")) {
                errorExists = true;
            }
            // wholeOrganization - Organization
            if (!organizationComparison(sourceOrganizationPartOf.getWholeOrganization(),targetOrganizationPartOf.getWholeOrganization(),errorMessage + " -> Whole Organization")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceOrganizationPartOf.getNullFlavor(),targetOrganizationPartOf.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //classCode
            if (!compareClassCode(sourceOrganizationPartOf.getClassCode(),targetOrganizationPartOf.getClassCode(),errorMessage + " -> Class Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private Boolean maintainedEntityComparison(EList<MaintainedEntity> sourceMaintainedEntity, EList<MaintainedEntity> targetMaintainedEntity, String errorMessage) {
        Boolean errorExists = false;
        for (int i=0;i<sourceMaintainedEntity.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetMaintainedEntity.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (!compareRealmCodes(sourceMaintainedEntity.get(i).getRealmCodes(), targetMaintainedEntity.get(j).getRealmCodes(), errorMessage + " -> Realm Codes")) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourceMaintainedEntity.get(i).getTypeId(), targetMaintainedEntity.get(j).getTypeId(), errorMessage + " -> typeID")) {
                    specificError = true;
                }
                //compare TemplateIDs
                if (!compareTemplateID(sourceMaintainedEntity.get(i).getTemplateIds(), targetMaintainedEntity.get(j).getTemplateIds(), errorMessage + " -> TemplateID")) {
                    specificError = true;
                }
                //compare EffectiveTime
                if (!compareEffectiveTime(sourceMaintainedEntity.get(i).getEffectiveTime(), targetMaintainedEntity.get(j).getEffectiveTime(), errorMessage + " -> EffectiveTime")) {
                    specificError = true;
                }
                //compare Maintaining Person
                if (!personComparison(sourceMaintainedEntity.get(i).getMaintainingPerson(),targetMaintainedEntity.get(j).getMaintainingPerson(),errorMessage + " -> MaintainingPerson")) {
                    specificError = true;
                }
                //compare Class Code
                if (!compareClassCode(sourceMaintainedEntity.get(i).getClassCode(),targetMaintainedEntity.get(j).getClassCode(),errorMessage + " -> Class Code")) {
                    specificError = true;
                }
                //compare NullFlavor
                if (!compareNullFlavor(sourceMaintainedEntity.get(i).getNullFlavor(), targetMaintainedEntity.get(j).getNullFlavor(), errorMessage + " -> Null Flavor")) {
                    specificError = true;
                }


                if (!specificError){
                    targetMatches++;
                }

            }
            if (targetMatches == 0) {
                errorExists = true;
                comparisonResult.addMessage("Organization Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
                comparisonResult.addMessage("Organization Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private Boolean intendedRecipientComparison(IntendedRecipient sourceIntendedRecipeint, IntendedRecipient targetIntendedRecipient, String errorMessage) {
        boolean errorExists = false;
        if (sourceIntendedRecipeint == null && targetIntendedRecipient == null) {
            return true;
        } else if (sourceIntendedRecipeint != null && targetIntendedRecipient != null) {
            //realmCode
            if (!compareRealmCodes(sourceIntendedRecipeint.getRealmCodes(),targetIntendedRecipient.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //type Id
            if (!typeIDComparison(sourceIntendedRecipeint.getTypeId(),targetIntendedRecipient.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceIntendedRecipeint.getIds(),targetIntendedRecipient.getIds(),errorMessage + " -> IDs")) {
                errorExists = true;
            }
            //Addr
            if (!compareAddr(sourceIntendedRecipeint.getAddrs(),targetIntendedRecipient.getAddrs(),errorMessage + " -> Addrs")) {
                errorExists = true;
            }
            //Telecom
            if (!compareTelcom(sourceIntendedRecipeint.getTelecoms(),targetIntendedRecipient.getTelecoms(),errorMessage + " -> Telecoms")) {
                errorExists = true;
            }
            //Information Recipient
            if (!personComparison(sourceIntendedRecipeint.getInformationRecipient(),targetIntendedRecipient.getInformationRecipient(),errorMessage + " -> InformationRecipient")) {
                errorExists = true;
            }
            // ReceivedOrganization - Organization
            if (!organizationComparison(sourceIntendedRecipeint.getReceivedOrganization(),targetIntendedRecipient.getReceivedOrganization(),errorMessage + " -> Received Organization")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceIntendedRecipeint.getNullFlavor(),targetIntendedRecipient.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //classCode
            if (!compareClassCode(sourceIntendedRecipeint.getClassCode(),targetIntendedRecipient.getClassCode(),errorMessage + " -> Class Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private Boolean orderComparison(Order sourceOrder, Order targetOrder, String errorMessage) {
        boolean errorExists = false;
        if (sourceOrder == null && targetOrder == null) {
            return true;
        } else if (sourceOrder != null && targetOrder != null) {
            //realmCode
            if (!compareRealmCodes(sourceOrder.getRealmCodes(),targetOrder.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //type Id
            if (!typeIDComparison(sourceOrder.getTypeId(),targetOrder.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceOrder.getTemplateIds(),targetOrder.getTemplateIds(),errorMessage + " -> TemplateIDS")) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceOrder.getIds(),targetOrder.getIds(),errorMessage + " -> IDs")) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceOrder.getCode(),targetOrder.getCode(),errorMessage + " -> Code")) {
                errorExists = true;
            }
            //PriorityCode
            if (!compareCode(sourceOrder.getPriorityCode(),targetOrder.getPriorityCode(),errorMessage + " -> PriorityCode")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceOrder.getNullFlavor(),targetOrder.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //classCode
            if (!compareClassCode(sourceOrder.getClassCode(),targetOrder.getClassCode(),errorMessage + " -> Class Code")) {
                errorExists = true;
            }
            //moodCode
            if (!compareMoodCode(sourceOrder.getMoodCode(),targetOrder.getMoodCode(),errorMessage + " -> Mood Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private Boolean parentDocumentComparison(ParentDocument sourceParentDocument, ParentDocument targetParentDocument, String errorMessage) {
        boolean errorExists = false;
        if (sourceParentDocument == null && targetParentDocument == null) {
            return true;
        } else if (sourceParentDocument != null && targetParentDocument != null) {
            //realmCode
            if (!compareRealmCodes(sourceParentDocument.getRealmCodes(),targetParentDocument.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //type Id
            if (!typeIDComparison(sourceParentDocument.getTypeId(),targetParentDocument.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceParentDocument.getTemplateIds(),targetParentDocument.getTemplateIds(),errorMessage + " -> TemplateIDS")) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceParentDocument.getIds(),targetParentDocument.getIds(),errorMessage + " -> IDs")) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceParentDocument.getCode(),targetParentDocument.getCode(),errorMessage + " -> Code")) {
                errorExists = true;
            }
            //text
            if (!compareText(sourceParentDocument.getText(),targetParentDocument.getText(),errorMessage + " -> Text")) {
                errorExists = true;
            }
            //setID
            if (!compareSetID(sourceParentDocument.getSetId(),targetParentDocument.getSetId(),errorMessage + " -> SetID")) {
                errorExists = true;
            }
            //versionNumber
            if (!compareVersionNumber(sourceParentDocument.getVersionNumber(),targetParentDocument.getVersionNumber(),errorMessage + " -> Version Number")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceParentDocument.getNullFlavor(),targetParentDocument.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //classCode
            if (!compareClassCode(sourceParentDocument.getClassCode(),targetParentDocument.getClassCode(),errorMessage + " -> Class Code")) {
                errorExists = true;
            }
            //moodCode
            if (!compareMoodCode(sourceParentDocument.getMoodCode(),targetParentDocument.getMoodCode(),errorMessage + " -> Mood Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private Boolean consentComparison(Consent sourceConsent, Consent targetConsesnt, String errorMessage) {
        boolean errorExists = false;
        if (sourceConsent == null && targetConsesnt == null) {
            return true;
        } else if (sourceConsent != null && targetConsesnt != null) {
            //realmCode
            if (!compareRealmCodes(sourceConsent.getRealmCodes(),targetConsesnt.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //type Id
            if (!typeIDComparison(sourceConsent.getTypeId(),targetConsesnt.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceConsent.getTemplateIds(),targetConsesnt.getTemplateIds(),errorMessage + " -> TemplateIDS")) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceConsent.getIds(),targetConsesnt.getIds(),errorMessage + " -> IDs")) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceConsent.getCode(),targetConsesnt.getCode(),errorMessage + " -> Code")) {
                errorExists = true;
            }
            //statusCode
            if (!compareCode(sourceConsent.getStatusCode(),targetConsesnt.getStatusCode(),errorMessage + " -> Status Code")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceConsent.getNullFlavor(),targetConsesnt.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //classCode
            if (!compareClassCode(sourceConsent.getClassCode(),targetConsesnt.getClassCode(),errorMessage + " -> Class Code")) {
                errorExists = true;
            }
            //moodCode
            if (!compareMoodCode(sourceConsent.getMoodCode(),targetConsesnt.getMoodCode(),errorMessage + " -> Mood Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private Boolean encompassingEncounterComparison(EncompassingEncounter sourceEncompassingEncounter, EncompassingEncounter targetEncompassingEncounter, String errorMessage) {
        boolean errorExists = false;
        if (sourceEncompassingEncounter == null && targetEncompassingEncounter == null) {
            return true;
        } else if (sourceEncompassingEncounter != null && targetEncompassingEncounter != null) {
            //realmCode
            if (!compareRealmCodes(sourceEncompassingEncounter.getRealmCodes(),targetEncompassingEncounter.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //type Id
            if (!typeIDComparison(sourceEncompassingEncounter.getTypeId(),targetEncompassingEncounter.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceEncompassingEncounter.getTemplateIds(),targetEncompassingEncounter.getTemplateIds(),errorMessage + " -> TemplateIDS")) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceEncompassingEncounter.getIds(),targetEncompassingEncounter.getIds(),errorMessage + " -> IDs")) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceEncompassingEncounter.getCode(),targetEncompassingEncounter.getCode(),errorMessage + " -> Code")) {
                errorExists = true;
            }
            //effectiveTime
            if (!compareEffectiveTime(sourceEncompassingEncounter.getEffectiveTime(),targetEncompassingEncounter.getEffectiveTime(),errorMessage + " -> EffectiveTime")) {
                errorExists = true;
            }
            //dischargeDispositionCode
            if (!compareCode(sourceEncompassingEncounter.getDischargeDispositionCode(),targetEncompassingEncounter.getDischargeDispositionCode(),errorMessage + " -> Discharge Disposition Code")) {
                errorExists = true;
            }
            //responsibleParty
            if (!responsiblePartyComparison(sourceEncompassingEncounter.getResponsibleParty(),targetEncompassingEncounter.getResponsibleParty(),errorMessage + " -> Responsible Party")) {
                errorExists = true;
            }
            //encounterParticipant
            if (!encounterParticipantComparison(sourceEncompassingEncounter.getEncounterParticipants(),targetEncompassingEncounter.getEncounterParticipants(),errorMessage + " -> Encounter Participants")) {
                errorExists = true;
            }
            //location
            if (!locationComparison(sourceEncompassingEncounter.getLocation(),targetEncompassingEncounter.getLocation(),errorMessage + " -> Location")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceEncompassingEncounter.getNullFlavor(),targetEncompassingEncounter.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //classCode
            if (!compareClassCode(sourceEncompassingEncounter.getClassCode(),targetEncompassingEncounter.getClassCode(),errorMessage + " -> Class Code")) {
                errorExists = true;
            }
            //moodCode
            if (!compareMoodCode(sourceEncompassingEncounter.getMoodCode(),targetEncompassingEncounter.getMoodCode(),errorMessage + " -> Mood Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private Boolean placeComparison(Place sourcePlace, Place targetPlace, String errorMessage) {
        boolean errorExists = false;
        if (sourcePlace == null && targetPlace == null) {
            return true;
        } else if (sourcePlace != null && targetPlace != null) {
            //realmCode
            if (!compareRealmCodes(sourcePlace.getRealmCodes(),targetPlace.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //type Id
            if (!typeIDComparison(sourcePlace.getTypeId(),targetPlace.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourcePlace.getTemplateIds(),targetPlace.getTemplateIds(),errorMessage + " -> TemplateIDS")) {
                errorExists = true;
            }
            //name
            if (!compareNamesEN(sourcePlace.getNames(),targetPlace.getNames(),errorMessage + " -> Name")) {
                errorExists = true;
            }
            //Addr
            if (!compareAddr(sourcePlace.getAddrs(),targetPlace.getAddrs(),errorMessage + " -> Addrs")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourcePlace.getNullFlavor(),targetPlace.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //classCode
            if (!compareClassCode(sourcePlace.getClassCode(),targetPlace.getClassCode(),errorMessage + " -> Class Code")) {
                errorExists = true;
            }
            //DeterminerCode
            if (!compareDeterminerCode(sourcePlace.getDeterminerCode(),targetPlace.getDeterminerCode(),errorMessage + " -> DeterminerCode")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private Boolean sectionComparison(Section sourceSection, Section targetSection, String errorMessage) {
        boolean errorExists = false;
        if (sourceSection == null && targetSection == null) {
            return true;
        } else if (sourceSection != null && targetSection != null) {
            //realmCode
            if (!compareRealmCodes(sourceSection.getRealmCodes(),targetSection.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //type Id
            if (!typeIDComparison(sourceSection.getTypeId(),targetSection.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceSection.getTemplateIds(),targetSection.getTemplateIds(),errorMessage + " -> TemplateIDS")) {
                errorExists = true;
            }
            //id
            if (!compareID(sourceSection.getId(),targetSection.getId(),errorMessage + " -> ID")) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceSection.getCode(),targetSection.getCode(),errorMessage + " -> Code")) {
                errorExists = true;
            }
            //title
            if (!compareTitle(sourceSection.getTitle(),targetSection.getTitle(),errorMessage + " -> Title")) {
                errorExists = true;
            }
            //text
            if (!compareText(sourceSection.getText(),targetSection.getText(),errorMessage + " -> Text")) {
                errorExists = true;
            }
            //confidentialityCode
            if (!compareCode(sourceSection.getConfidentialityCode(),targetSection.getConfidentialityCode(),errorMessage + " -> Confidentiality Code")) {
                errorExists = true;
            }
            //languageCode
            if (!compareLanguageCode(sourceSection.getLanguageCode(),targetSection.getLanguageCode(),errorMessage + " -> Language Code")) {
                errorExists = true;
            }
            //subject
            if (!subjectComparison(sourceSection.getSubject(),targetSection.getSubject(),errorMessage + " -> Subject")) {
                errorExists = true;
            }
            //Author
            if (!authorsComparison(sourceSection.getAuthors(),targetSection.getAuthors(),errorMessage + " -> Authors")) {
                errorExists = true;
            }
            //Informant12
            if (!informantsComparison(sourceSection.getInformants(),targetSection.getInformants(),errorMessage + " -> Informants")) {
                errorExists = true;
            }
            //Entry
            if (!entryComparison(sourceSection.getEntries(),targetSection.getEntries(),errorMessage + " -> Entry")) {
                errorExists = true;
            }
            //Component 5
            if (!component5Comparison(sourceSection.getComponents(),targetSection.getComponents(),errorMessage + " -> Component(5)")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceSection.getNullFlavor(),targetSection.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //classCode
            if (!compareClassCode(sourceSection.getClassCode(),targetSection.getClassCode(),errorMessage + " -> Class Code")) {
                errorExists = true;
            }
            //moodCode
            if (!compareMoodCode(sourceSection.getMoodCode(),targetSection.getMoodCode(),errorMessage + " -> Mood Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private Boolean responsiblePartyComparison(ResponsibleParty sourceResponsibleParty, ResponsibleParty targetResponsibleParty, String errorMessage) {
        boolean errorExists = false;
        if (sourceResponsibleParty == null && targetResponsibleParty == null) {
            return true;
        } else if (sourceResponsibleParty != null && targetResponsibleParty != null) {
            //realmCode
            if (!compareRealmCodes(sourceResponsibleParty.getRealmCodes(),targetResponsibleParty.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //type Id
            if (!typeIDComparison(sourceResponsibleParty.getTypeId(),targetResponsibleParty.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceResponsibleParty.getTemplateIds(),targetResponsibleParty.getTemplateIds(),errorMessage + " -> TemplateIDS")) {
                errorExists = true;
            }
            //AssignedEntity
            if (!assignedEntityComparison(sourceResponsibleParty.getAssignedEntity(),targetResponsibleParty.getAssignedEntity(),errorMessage + " -> AssignedEntity")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceResponsibleParty.getNullFlavor(),targetResponsibleParty.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //typeCode
            if (!compareTypeCode(sourceResponsibleParty.getTypeCode(),targetResponsibleParty.getTypeCode(),errorMessage + " -> Type Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private Boolean encounterParticipantComparison(EList<EncounterParticipant> sourceEncounterParticipant, EList<EncounterParticipant> targetEncounterParticipant, String errorMessage) {
        Boolean errorExists = false;
        for (int i=0;i<sourceEncounterParticipant.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetEncounterParticipant.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (!compareRealmCodes(sourceEncounterParticipant.get(i).getRealmCodes(), targetEncounterParticipant.get(j).getRealmCodes(), errorMessage + " -> Realm Codes")) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourceEncounterParticipant.get(i).getTypeId(), targetEncounterParticipant.get(j).getTypeId(), errorMessage + " -> typeID")) {
                    specificError = true;
                }
                //compare TemplateIDs
                if (!compareTemplateID(sourceEncounterParticipant.get(i).getTemplateIds(), targetEncounterParticipant.get(j).getTemplateIds(), errorMessage + " -> TemplateID")) {
                    specificError = true;
                }
                //compare Time
                if (!compareEffectiveTime(sourceEncounterParticipant.get(i).getTime(), targetEncounterParticipant.get(j).getTime(), errorMessage + " -> Time")) {
                    specificError = true;
                }
                //compare assignedEntity
                if (!assignedEntityComparison(sourceEncounterParticipant.get(i).getAssignedEntity(),targetEncounterParticipant.get(j).getAssignedEntity(),errorMessage + " -> AssignedEntity")) {
                    specificError = true;
                }
                //compare Type Code
                if (!compareTypeCode(sourceEncounterParticipant.get(i).getTypeCode(),targetEncounterParticipant.get(j).getTypeCode(),errorMessage + " -> TypeCode")) {
                    specificError = true;
                }
                //compare NullFlavor
                if (!compareNullFlavor(sourceEncounterParticipant.get(i).getNullFlavor(), targetEncounterParticipant.get(j).getNullFlavor(), errorMessage + " -> Null Flavor")) {
                    specificError = true;
                }


                if (!specificError){
                    targetMatches++;
                }

            }
            if (targetMatches == 0) {
                errorExists = true;
                comparisonResult.addMessage("Organization Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
                comparisonResult.addMessage("Organization Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;

    }

    private Boolean locationComparison(Location sourceLocation, Location targetLocation, String errorMessage) {
        return true;
    }

    private Boolean subjectComparison(Subject sourceSubject, Subject targetSubject, String errorMessage) {
        return true;
    }

    private Boolean entryComparison(EList<Entry> sourceEntry, EList<Entry> targetEntry, String errorMessage) {
        return true;
    }

    private Boolean component5Comparison(EList<Component5> sourceEntry, EList<Component5> targetEntry, String errorMessage) {
        return true;
    }









    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //non complex type comparison Methods
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private Boolean compareRealmCodes(EList<CS> source, EList<CS> target, String errorMessage) {
        Boolean errorExists = false;
        for (int i=0; i<source.size(); i++) {
            int targetMatches = 0;
            for (int j=0; j<target.size(); j++) {
                if (Objects.equals(source.get(i).getCode(),target.get(j).getCode())
                        && Objects.equals(source.get(i).getNullFlavor().getLiteral(),target.get(j).getNullFlavor().getLiteral())
                        && Objects.equals(source.get(i).getCodeSystem(),target.get(j).getCodeSystem())
                        && Objects.equals(source.get(i).getCodeSystemName(),target.get(j).getCodeSystemName())
                        && Objects.equals(source.get(i).getCodeSystemVersion(),target.get(j).getCodeSystemVersion())
                        && Objects.equals(source.get(i).getDisplayName(),target.get(j).getDisplayName()))
                {
                    targetMatches++;
                }
            }
            if (targetMatches==0) {
                errorExists = true;
                comparisonResult.addMessage("Realm Codes Error source " + i + " in " + errorMessage + "\n");
            } else if (targetMatches>1) {
                comparisonResult.addMessage("Real Codes Warning source " + i + " has " + targetMatches + " in target in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private Boolean compareTemplateID(EList<II> source, EList<II> target, String errorMessage) {
        Boolean errorExists = false;
        for (int i = 0; i < source.size(); i++) {
            int targetMatches = 0;
            for (int j = 0; j < target.size(); j++) {
                if (Objects.equals(source.get(i).getNullFlavor(), target.get(j).getNullFlavor())
                        && Objects.equals(source.get(i).getRoot(), target.get(j).getRoot())
                        && Objects.equals(source.get(i).getExtension(), target.get(j).getExtension())
                        && Objects.equals(source.get(i).getAssigningAuthorityName(), target.get(j).getAssigningAuthorityName())) {
                    targetMatches++;
                }
            }
            if (targetMatches==0) {
                errorExists = true;
                comparisonResult.addMessage("Template IDs Error source " + i + " in " + errorMessage + "\n");
            } else if (targetMatches>1) {
                comparisonResult.addMessage("Template IDs Warning source " + i + " has " + targetMatches + " in target in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private Boolean compareIDs(EList<II> source, EList<II> target, String errorMessage) {
        Boolean errorExists = false;
        for (int i = 0; i < source.size(); i++) {
            int targetMatches = 0;
            for (int j = 0; j < target.size(); j++) {
                if (source.get(i) != null && target.get(j) != null) { ///////////////////////////////////////////////////////////////////////////why is this needed? size is 1 but everything is null
                    if (Objects.equals(source.get(i).getNullFlavor(), target.get(j).getNullFlavor())
                            && Objects.equals(source.get(i).getRoot(), target.get(j).getRoot())
                            && Objects.equals(source.get(i).getExtension(), target.get(j).getExtension())
                            && Objects.equals(source.get(i).getAssigningAuthorityName(), target.get(j).getAssigningAuthorityName())) {
                        targetMatches++;
                    }
                } else {
                    targetMatches=1;
                }
            }
            if (targetMatches==0) {
                errorExists = true;
                comparisonResult.addMessage("IDs Error source " + i + " in " + errorMessage + "\n");
            } else if (targetMatches>1) {
                comparisonResult.addMessage("IDs Warning source " + i + " has " + targetMatches + " in target in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private Boolean compareAddr(EList<AD> source, EList<AD> target, String errorMessage) {
        Boolean errorExists = false;
        for (int i = 0; i < source.size(); i++) {
            int targetMatches = 0;
            for (int j = 0; j < target.size(); j++) {
                if (Objects.equals(source.get(i).getNullFlavor(), target.get(j).getNullFlavor())
                        && Objects.equals(source.get(i).getText(), target.get(j).getText())) {
                    targetMatches++;
                }
            }
            if (targetMatches==0) {
                errorExists = true;
                comparisonResult.addMessage("Addr Error source " + i + " in " + errorMessage + "\n");
            } else if (targetMatches>1) {
                comparisonResult.addMessage("Addr Warning source " + i + " has " + targetMatches + " in target in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private Boolean compareTelcom(EList<TEL> source, EList<TEL> target, String errorMessage) {
        Boolean errorExists = false;
        for (int i = 0; i < source.size(); i++) {
            int targetMatches = 0;
            for (int j = 0; j < target.size(); j++) {
                if (Objects.equals(source.get(i).getNullFlavor(), target.get(j).getNullFlavor())
                        && Objects.equals(source.get(i).getValue(), target.get(j).getValue())) {
                    targetMatches++;
                }
            }
            if (targetMatches==0) {
                errorExists = true;
                comparisonResult.addMessage("Telecom Error source " + i + " in " + errorMessage + "\n");
            } else if (targetMatches>1) {
                comparisonResult.addMessage("Telecom Warning source " + i + " has " + targetMatches + " in target in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private Boolean compareNamesPN(EList<PN> source, EList<PN> target, String errorMessage) {
        Boolean errorExists = false;
        for (int i = 0; i < source.size(); i++) {
            int targetMatches = 0;
            for (int j = 0; j < target.size(); j++) {
                if (Objects.equals(source.get(i).getText(), target.get(j).getText()) && Objects.equals(source.get(i).getNullFlavor(), target.get(j).getNullFlavor())) {
                    targetMatches++;
                }
            }
            if (targetMatches == 0) {
                errorExists = true;
                comparisonResult.addMessage("Names Error source " + i + " in " + errorMessage + "\n");
            } else if (targetMatches > 1) {
                comparisonResult.addMessage("Names Warning source " + i + " has " + targetMatches + " in target in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private Boolean compareNamesEN(EList<EN> source, EList<EN> target, String errorMessage) {
        Boolean errorExists = false;
        for (int i = 0; i < source.size(); i++) {
            int targetMatches = 0;
            for (int j = 0; j < target.size(); j++) {
                if (Objects.equals(source.get(i).getText(), target.get(j).getText()) && Objects.equals(source.get(i).getNullFlavor(), target.get(j).getNullFlavor())) {
                    targetMatches++;
                }
            }
            if (targetMatches == 0) {
                errorExists = true;
                comparisonResult.addMessage("Names Error source " + i + " in " + errorMessage + "\n");
            } else if (targetMatches > 1) {
                comparisonResult.addMessage("Names Warning source " + i + " has " + targetMatches + " in target in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private Boolean compareNamesON(EList<ON> source, EList<ON> target, String errorMessage) {
        Boolean errorExists = false;
        for (int i = 0; i < source.size(); i++) {
            int targetMatches = 0;
            for (int j = 0; j < target.size(); j++) {
                if (Objects.equals(source.get(i).getText(), target.get(j).getText()) && Objects.equals(source.get(i).getNullFlavor(), target.get(j).getNullFlavor())) {
                    targetMatches++;
                }
            }
            if (targetMatches == 0) {
                errorExists = true;
                comparisonResult.addMessage("Names Error source " + i + " in " + errorMessage + "\n");
            } else if (targetMatches > 1) {
                comparisonResult.addMessage("Names Warning source " + i + " has " + targetMatches + " in target in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private Boolean compareID(II source, II target, String errorMessage) {
        if (source != null && target != null) {
            if ( ! (Objects.equals(source.getAssigningAuthorityName(),target.getAssigningAuthorityName())
                    && Objects.equals(source.getExtension(),target.getExtension())
                    && Objects.equals(source.getRoot(),target.getRoot())
                    && Objects.equals(source.getNullFlavor(),target.getNullFlavor())))
            {
                comparisonResult.addMessage("IDs Comparison error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("IDs Comparison error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean compareNullFlavor(NullFlavor source, NullFlavor target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getLiteral(), target.getLiteral()))) {
                comparisonResult.addMessage("Null Flavor error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Null Flavor error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean compareClassCode(RoleClass source, RoleClass target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getLiteral(), target.getLiteral())
                    && Objects.equals(source.getName(), target.getName())
                    && Objects.equals(source.getValue(), target.getValue()))) {
                comparisonResult.addMessage("Class Code error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Class Code error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean compareClassCode(EntityClassPlace source, EntityClassPlace target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getLiteral(), target.getLiteral())
                    && Objects.equals(source.getName(), target.getName())
                    && Objects.equals(source.getValue(), target.getValue()))) {
                comparisonResult.addMessage("Class Code error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Class Code error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean compareClassCode(x_InformationRecipientRole source, x_InformationRecipientRole target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getLiteral(), target.getLiteral())
                    && Objects.equals(source.getName(), target.getName())
                    && Objects.equals(source.getValue(), target.getValue()))) {
                comparisonResult.addMessage("Class Code error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Class Code error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean compareClassCode(EntityClassOrganization source, EntityClassOrganization target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getLiteral(), target.getLiteral())
                    && Objects.equals(source.getName(), target.getName())
                    && Objects.equals(source.getValue(), target.getValue()))) {
                comparisonResult.addMessage("Class Code error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Class Code error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean compareClassCode(ActClassRoot source, ActClassRoot target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getLiteral(), target.getLiteral())
                    && Objects.equals(source.getName(), target.getName())
                    && Objects.equals(source.getValue(), target.getValue()))) {
                comparisonResult.addMessage("Class Code error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Class Code error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }


    private Boolean compareClassCode(RoleClassAssociative source, RoleClassAssociative target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getLiteral(), target.getLiteral())
                    && Objects.equals(source.getName(), target.getName())
                    && Objects.equals(source.getValue(), target.getValue()))) {
                comparisonResult.addMessage("Class Code error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Class Code error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }


    private Boolean compareClassCode(ActClinicalDocument source, ActClinicalDocument target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getLiteral(), target.getLiteral())
                    && Objects.equals(source.getName(), target.getName())
                    && Objects.equals(source.getValue(), target.getValue()))) {
                comparisonResult.addMessage("classCode error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("classCode error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean compareClassCode(RoleClassAssignedEntity source, RoleClassAssignedEntity target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getLiteral(), target.getLiteral())
                    && Objects.equals(source.getName(), target.getName())
                    && Objects.equals(source.getValue(), target.getValue()))) {
                comparisonResult.addMessage("classCode error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("classCode error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean compareClassCode(EntityClassDevice source, EntityClassDevice target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getLiteral(), target.getLiteral())
                    && Objects.equals(source.getName(), target.getName())
                    && Objects.equals(source.getValue(), target.getValue()))) {
                comparisonResult.addMessage("classCode error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("classCode error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean compareClassCode(EntityClass source,EntityClass target,String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getLiteral(), target.getLiteral())
                    && Objects.equals(source.getName(), target.getName())
                    && Objects.equals(source.getValue(), target.getValue()))) {
                comparisonResult.addMessage("classCode error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("classCode error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }
    private Boolean compareClassCode(ActClass source,ActClass target,String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getLiteral(), target.getLiteral())
                    && Objects.equals(source.getName(), target.getName())
                    && Objects.equals(source.getValue(), target.getValue()))) {
                comparisonResult.addMessage("classCode error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("classCode error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }
    private Boolean compareClassCode(RoleClassMutualRelationship source,RoleClassMutualRelationship target,String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getLiteral(), target.getLiteral())
                    && Objects.equals(source.getName(), target.getName())
                    && Objects.equals(source.getValue(), target.getValue()))) {
                comparisonResult.addMessage("classCode error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("classCode error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean compareCode(CE source, CE target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getCode(), target.getCode())
                    && Objects.equals(source.getNullFlavor().getLiteral(), target.getNullFlavor().getLiteral())
                    && Objects.equals(source.getCodeSystem(), target.getCodeSystem())
                    && Objects.equals(source.getCodeSystemName(), target.getCodeSystemName())
                    && Objects.equals(source.getCodeSystemVersion(), target.getCodeSystemVersion())
                    && Objects.equals(source.getDisplayName(), target.getDisplayName()))) {
                comparisonResult.addMessage("Code Comparison error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Code Comparison error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean compareCode(CD source, CD target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getCode(), target.getCode())
                    && Objects.equals(source.getNullFlavor().getLiteral(), target.getNullFlavor().getLiteral())
                    && Objects.equals(source.getCodeSystem(), target.getCodeSystem())
                    && Objects.equals(source.getCodeSystemName(), target.getCodeSystemName())
                    && Objects.equals(source.getCodeSystemVersion(), target.getCodeSystemVersion())
                    && Objects.equals(source.getDisplayName(), target.getDisplayName()))) {
                comparisonResult.addMessage("Code Comparison error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Code Comparison error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean compareConfidentialityCode(CE source, CE target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getCode(), target.getCode())
                    && Objects.equals(source.getNullFlavor().getLiteral(), target.getNullFlavor().getLiteral())
                    && Objects.equals(source.getCodeSystem(), target.getCodeSystem())
                    && Objects.equals(source.getCodeSystemName(), target.getCodeSystemName())
                    && Objects.equals(source.getCodeSystemVersion(), target.getCodeSystemVersion())
                    && Objects.equals(source.getDisplayName(), target.getDisplayName()))) {
                comparisonResult.addMessage("Confidentiality Code Comparison error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Confidentiality Code Comparison error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean compareLanguageCode(CE source, CE target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getCode(), target.getCode())
                    && Objects.equals(source.getNullFlavor().getLiteral(), target.getNullFlavor().getLiteral())
                    && Objects.equals(source.getCodeSystem(), target.getCodeSystem())
                    && Objects.equals(source.getCodeSystemName(), target.getCodeSystemName())
                    && Objects.equals(source.getCodeSystemVersion(), target.getCodeSystemVersion())
                    && Objects.equals(source.getDisplayName(), target.getDisplayName()))) {
                comparisonResult.addMessage("Language Code Comparison error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Language Code Comparison error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean compareTitle(ST source, ST target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getText(), target.getText()))) {
                comparisonResult.addMessage("Title Comparison error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Title Comparison error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean compareEffectiveTime(TS source, TS target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getValue(), target.getValue())
                    && Objects.equals(source.getNullFlavor(), target.getNullFlavor()))) {
                comparisonResult.addMessage("Effective Time error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Effective Time error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean compareTypeCode(ParticipationType source, ParticipationType target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getLiteral(), target.getLiteral())
                    && Objects.equals(source.getName(), target.getName())
                    && Objects.equals(source.getValue(), target.getValue()))) {
                comparisonResult.addMessage("Type Code error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Type Code error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean compareTypeCode(ActRelationshipFulfills source, ActRelationshipFulfills target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getLiteral(), target.getLiteral())
                    && Objects.equals(source.getName(), target.getName())
                    && Objects.equals(source.getValue(), target.getValue()))) {
                comparisonResult.addMessage("Type Code error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Type Code error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean compareTypeCode(x_ServiceEventPerformer source, x_ServiceEventPerformer target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getLiteral(), target.getLiteral())
                    && Objects.equals(source.getName(), target.getName())
                    && Objects.equals(source.getValue(), target.getValue()))) {
                comparisonResult.addMessage("Type Code error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Type Code error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean compareTypeCode(x_ActRelationshipDocument source, x_ActRelationshipDocument target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getLiteral(), target.getLiteral())
                    && Objects.equals(source.getName(), target.getName())
                    && Objects.equals(source.getValue(), target.getValue()))) {
                comparisonResult.addMessage("Type Code error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Type Code error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean compareTypeCode(x_EncounterParticipant source, x_EncounterParticipant target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getLiteral(), target.getLiteral())
                    && Objects.equals(source.getName(), target.getName())
                    && Objects.equals(source.getValue(), target.getValue()))) {
                comparisonResult.addMessage("Type Code error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Type Code error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean compareTypeCode(x_InformationRecipient source, x_InformationRecipient target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getLiteral(), target.getLiteral())
                    && Objects.equals(source.getName(), target.getName())
                    && Objects.equals(source.getValue(), target.getValue()))) {
                comparisonResult.addMessage("Type Code error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Type Code error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean compareTypeCode(ActRelationshipHasComponent source, ActRelationshipHasComponent target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getLiteral(), target.getLiteral())
                    && Objects.equals(source.getName(), target.getName())
                    && Objects.equals(source.getValue(), target.getValue()))) {
                comparisonResult.addMessage("Type Code error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Type Code error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean compareTypeCode(ActRelationshipType source, ActRelationshipType target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getLiteral(), target.getLiteral())
                    && Objects.equals(source.getName(), target.getName())
                    && Objects.equals(source.getValue(), target.getValue()))) {
                comparisonResult.addMessage("Type Code error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Type Code error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean compareContextControlCode(ContextControl source, ContextControl target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getLiteral(), target.getLiteral())
                    && Objects.equals(source.getName(), target.getName())
                    && Objects.equals(source.getValue(), source.getValue()))) {
                comparisonResult.addMessage("Context Control Code error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Context Control Code error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean compareTime(TS source, TS target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getValue(), target.getValue())
                    && Objects.equals(source.getNullFlavor(), target.getNullFlavor()))) {
                comparisonResult.addMessage("Time error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Time error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean compareSignatureCode(CS source, CS target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getCode(), target.getCode())
                    && Objects.equals(source.getNullFlavor().getLiteral(), target.getNullFlavor().getLiteral())
                    && Objects.equals(source.getCodeSystem(), target.getCodeSystem())
                    && Objects.equals(source.getCodeSystemName(), target.getCodeSystemName())
                    && Objects.equals(source.getCodeSystemVersion(), target.getCodeSystemVersion())
                    && Objects.equals(source.getDisplayName(), target.getDisplayName())))
            {
                comparisonResult.addMessage("Signature Code Comparison Error in" + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Signature Code Comparison Error in" + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean compareFunctionCode(CE source, CE target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getCode(), target.getCode())
                    && Objects.equals(source.getNullFlavor().getLiteral(), target.getNullFlavor().getLiteral())
                    && Objects.equals(source.getCodeSystem(), target.getCodeSystem())
                    && Objects.equals(source.getCodeSystemName(), target.getCodeSystemName())
                    && Objects.equals(source.getCodeSystemVersion(), target.getCodeSystemVersion())
                    && Objects.equals(source.getDisplayName(), target.getDisplayName()))) {
                comparisonResult.addMessage("Function Code Comparison error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Function Code Comparison error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean compareContextConductionInd(Boolean source, Boolean target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source, target))) {
                comparisonResult.addMessage("Context Conduction Ind error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Context Conduction Ind error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean compareSetID(II source, II target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getAssigningAuthorityName(),target.getAssigningAuthorityName())
                    && Objects.equals(source.getExtension(),target.getExtension())
                    && Objects.equals(source.getRoot(),target.getRoot())))
            {
                comparisonResult.addMessage("Set ID error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Set ID error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean compareVersionNumber(INT source, INT target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getValue(),target.getValue()))) {
                comparisonResult.addMessage("Version Number error in " + errorMessage + "\n");
            }
            return false;
        } else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Version Number error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean compareCopyTime(TS source, TS target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getValue(),target.getValue()) && Objects.equals(source.getNullFlavor(),target.getValue()))) {
                comparisonResult.addMessage("Copy Time error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Copy Time error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean compareAdministrativeGenderCode(CE source, CE target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getCode(),target.getCode()) && Objects.equals(source.getNullFlavor(),target.getNullFlavor())))
            {
                comparisonResult.addMessage("Administrative Gender Code error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Administrative Gender Code error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean compareBirthTime(TS source, TS target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getValue(),target.getValue())
                    && Objects.equals(source.getNullFlavor(),target.getNullFlavor())))
            {
                comparisonResult.addMessage("Birth Time error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Birth Time error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean compareMaritalStatusCode(CE source, CE target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getCode(),target.getCode())
                    && Objects.equals(source.getNullFlavor(),target.getNullFlavor())))
            {
                comparisonResult.addMessage("Marital Status Code error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Marital Status Code error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean compareReligiosAffiliationCode(CE source, CE target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getNullFlavor(),target.getNullFlavor())
                    && Objects.equals(source.getCode(),target.getCode())
                    && Objects.equals(source.getCodeSystem(),target.getCodeSystem())
                    && Objects.equals(source.getCodeSystemName(),target.getCodeSystemName())
                    && Objects.equals(source.getCodeSystemVersion(),target.getCodeSystemVersion())
                    && Objects.equals(source.getDisplayName(),target.getDisplayName())))
            {
                comparisonResult.addMessage("Religious Affiliation Code error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Religious Affiliation Code error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean compareRaceCode(CE source, CE target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getNullFlavor(),target.getNullFlavor())
                    && Objects.equals(source.getCode(),target.getCode())
                    && Objects.equals(source.getCodeSystem(),target.getCodeSystem())
                    && Objects.equals(source.getCodeSystemName(),target.getCodeSystemName())
                    && Objects.equals(source.getCodeSystemVersion(),target.getCodeSystemVersion())
                    && Objects.equals(source.getDisplayName(),target.getDisplayName())))
            {
                comparisonResult.addMessage("Race Code error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Race Code error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean compareEthnicGroupCode(CE source, CE target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getNullFlavor(),target.getNullFlavor())
                    && Objects.equals(source.getCode(),target.getCode())
                    && Objects.equals(source.getCodeSystem(),target.getCodeSystem())
                    && Objects.equals(source.getCodeSystemName(),target.getCodeSystemName())
                    && Objects.equals(source.getCodeSystemVersion(),target.getCodeSystemVersion())
                    && Objects.equals(source.getDisplayName(),target.getDisplayName())))
            {
                comparisonResult.addMessage("Ethnic Group Code error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Ethnic Group Code error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean compareMoodCode(ActMood source, ActMood target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getLiteral(),target.getLiteral())
                    && Objects.equals(source.getName(),target.getName())
                    && Objects.equals(source.getValue(),target.getValue())))
            {
                comparisonResult.addMessage("Mood Code error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Mood Code error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean compareText(ED source, ED target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getLanguage(), target.getLanguage())
                    && Objects.equals(source.getText(), target.getText())
                    && Objects.equals(source.getMediaType(), target.getMediaType())
                    && Objects.equals(source.getNullFlavor(), target.getNullFlavor()))) {
                comparisonResult.addMessage("Text error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Text error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean compareText(StrucDocText source, StrucDocText target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getText(), target.getText()))) {
                comparisonResult.addMessage("Text error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Text error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean compareDeterminerCode(EntityDeterminer source, EntityDeterminer target, String errorMessage) {
        if (source != null && target != null) {
            if(!(Objects.equals(source.getLiteral(),target.getLiteral())
                    && Objects.equals(source.getName(),target.getName())
                    && Objects.equals(source.getValue(),target.getValue())))
            {
                comparisonResult.addMessage("Determiner Code error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Determiner Code error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean compareSCName(SC source, SC target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getNullFlavor(), target.getNullFlavor())
                    && Objects.equals(source.getCode(), target.getCode())
                    && Objects.equals(source.getCodeSystem(), target.getCodeSystem())
                    && Objects.equals(source.getCodeSystemName(), target.getCodeSystemName())
                    && Objects.equals(source.getCodeSystemVersion(), target.getCodeSystemVersion())
                    && Objects.equals(source.getDisplayName(), target.getDisplayName()))) {
                comparisonResult.addMessage("Manufactured Model Name error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Manufactured Model Name error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean comparePreferenceInd(BL source, BL target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getNullFlavor(), target.getNullFlavor())
                    && Objects.equals(source.getValue(), target.getValue()))) {
                comparisonResult.addMessage("Manufactured Model Name error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Manufactured Model Name error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }
}
