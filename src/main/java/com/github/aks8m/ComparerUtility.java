package com.github.aks8m;

import org.eclipse.emf.common.util.EList;
import org.openhealthtools.mdht.uml.cda.*;
import org.openhealthtools.mdht.uml.hl7.datatypes.*;
import org.openhealthtools.mdht.uml.hl7.vocab.*;

import java.awt.*;
import java.lang.annotation.Target;
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
            if (!((nonXMLBodyComparison(sourceComponent.getNonXMLBody(),targetComponent.getNonXMLBody(),errorMessage + " -> NonXMLBody") && sourceComponent.getNonXMLBody() != null)
                    || structuredBodyComparison(sourceComponent.getStructuredBody(),targetComponent.getStructuredBody(), errorMessage + " -> Structured Body"))) {
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
//                //compare Section
//                if (!sectionComparison(sourceComponent3.get(i).getSection(), targetComponent3.get(j).getSection(), errorMessage + " -> Section")) {
//                    specificError = true;
//                }
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
            //ID
            if (!compareIDAttribute(sourceSection.getSectionId(),targetSection.getSectionId(),errorMessage + " -> ID")) {
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
        boolean errorExists = false;
        if (sourceLocation == null && targetLocation == null) {
            return true;
        } else if (sourceLocation != null && targetLocation != null) {
            //realmCode
            if (!compareRealmCodes(sourceLocation.getRealmCodes(),targetLocation.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //type Id
            if (!typeIDComparison(sourceLocation.getTypeId(),targetLocation.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceLocation.getTemplateIds(),targetLocation.getTemplateIds(),errorMessage + " -> TemplateIDS")) {
                errorExists = true;
            }
            //HealthCareFacility
            if (!healthCareFacilityComparison(sourceLocation.getHealthCareFacility(),targetLocation.getHealthCareFacility(),errorMessage + " -> Health Care Facility")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceLocation.getNullFlavor(),targetLocation.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //typeCode
            if (!compareTypeCode(sourceLocation.getTypeCode(),targetLocation.getTypeCode(),errorMessage + " -> Type Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private Boolean subjectComparison(Subject sourceSubject, Subject targetSubject, String errorMessage) {
        boolean errorExists = false;
        if (sourceSubject == null && targetSubject == null) {
            return true;
        } else if (sourceSubject != null && targetSubject != null) {
            //realmCode
            if (!compareRealmCodes(sourceSubject.getRealmCodes(),targetSubject.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //type Id
            if (!typeIDComparison(sourceSubject.getTypeId(),targetSubject.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceSubject.getTemplateIds(),targetSubject.getTemplateIds(),errorMessage + " -> TemplateIDS")) {
                errorExists = true;
            }
            //AwarenessCode
            if (!compareCode(sourceSubject.getAwarenessCode(),targetSubject.getAwarenessCode(),errorMessage + " -> Awareness Code")) {
                errorExists = true;
            }
            //Related Subject
            if (!relatedSubjectComparison(sourceSubject.getRelatedSubject(),targetSubject.getRelatedSubject(),errorMessage + " -> Related Subject")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceSubject.getNullFlavor(),targetSubject.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //typeCode
            if (!compareTypeCode(sourceSubject.getTypeCode(),targetSubject.getTypeCode(),errorMessage + " -> Type Code")) {
                errorExists = true;
            }
            //contextControlCode
            if (!compareContextControlCode(sourceSubject.getContextControlCode(),targetSubject.getContextControlCode(),errorMessage + " -> Context Control Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private Boolean entryComparison(EList<Entry> sourceEntry, EList<Entry> targetEntry, String errorMessage) {
        Boolean errorExists = false;
        for (int i=0;i<sourceEntry.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetEntry.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (!compareRealmCodes(sourceEntry.get(i).getRealmCodes(), targetEntry.get(j).getRealmCodes(), errorMessage + " -> Realm Codes")) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourceEntry.get(i).getTypeId(), targetEntry.get(j).getTypeId(), errorMessage + " -> typeID")) {
                    specificError = true;
                }
                //compare TemplateIDs
                if (!compareTemplateID(sourceEntry.get(i).getTemplateIds(), targetEntry.get(j).getTemplateIds(), errorMessage + " -> TemplateID")) {
                    specificError = true;
                }
                //Choice Act, Encounter, Observation, ObservationMedia, Organizer, Procedure, Region of Interest, Substance Administration, Supply
                if (!(actComparison(sourceEntry.get(i).getAct(), targetEntry.get(j).getAct(), errorMessage + " -> Act")
                        || encounterComparison(sourceEntry.get(i).getEncounter(),targetEntry.get(j).getEncounter(), errorMessage + " -> Encounter")
                        || observationComparison(sourceEntry.get(i).getObservation(),targetEntry.get(j).getObservation(), errorMessage + " -> Observation")
                        || observationMediaComparison(sourceEntry.get(i).getObservationMedia(),targetEntry.get(j).getObservationMedia(), errorMessage + " -> ObservationMedia")
                        || organizerComparison(sourceEntry.get(i).getOrganizer(),targetEntry.get(j).getOrganizer(),errorMessage + " -> Organizer")
                        || procedureComparison(sourceEntry.get(i).getProcedure(),targetEntry.get(j).getProcedure(),errorMessage + " -> Procedure")
                        || regionOfInterestComparison(sourceEntry.get(i).getRegionOfInterest(),targetEntry.get(j).getRegionOfInterest(),errorMessage + " -> Region of Interest")
                        || substanceAdministrationComparison(sourceEntry.get(i).getSubstanceAdministration(),targetEntry.get(j).getSubstanceAdministration(),errorMessage + " -> Substance Administration")
                        || supplyComparison(sourceEntry.get(i).getSupply(),targetEntry.get(j).getSupply(),errorMessage + " -> Supply"))) {
                    specificError = true;
                }
                //compare contextConductionInd
                if (!compareContextConductionInd(sourceEntry.get(i).getContextConductionInd(),targetEntry.get(j).getContextConductionInd(),errorMessage + " -> Context Conduction Ind")) {
                    specificError = true;
                }
                //compare Type Code
                if (!compareTypeCode(sourceEntry.get(i).getTypeCode(),targetEntry.get(j).getTypeCode(),errorMessage + " -> TypeCode")) {
                    specificError = true;
                }
                //compare NullFlavor
                if (!compareNullFlavor(sourceEntry.get(i).getNullFlavor(), targetEntry.get(j).getNullFlavor(), errorMessage + " -> Null Flavor")) {
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

    private Boolean component5Comparison(EList<Component5> sourceComponent, EList<Component5> targetComponent, String errorMessage) {
        Boolean errorExists = false;
        for (int i=0;i<sourceComponent.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetComponent.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (!compareRealmCodes(sourceComponent.get(i).getRealmCodes(), targetComponent.get(j).getRealmCodes(), errorMessage + " -> Realm Codes")) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourceComponent.get(i).getTypeId(), targetComponent.get(j).getTypeId(), errorMessage + " -> typeID")) {
                    specificError = true;
                }
                //compare TemplateIDs
                if (!compareTemplateID(sourceComponent.get(i).getTemplateIds(), targetComponent.get(j).getTemplateIds(), errorMessage + " -> TemplateID")) {
                    specificError = true;
                }
                //Section
                if (!sectionComparison(sourceComponent.get(i).getSection(),targetComponent.get(j).getSection(),errorMessage + " -> Section")) {
                    specificError = true;
                }
                //compare contextConductionInd
                if (!compareContextConductionInd(sourceComponent.get(i).getContextConductionInd(),targetComponent.get(j).getContextConductionInd(),errorMessage + " -> Context Conduction Ind")) {
                    specificError = true;
                }
                //compare Type Code
                if (!compareTypeCode(sourceComponent.get(i).getTypeCode(),targetComponent.get(j).getTypeCode(),errorMessage + " -> TypeCode")) {
                    specificError = true;
                }
                //compare NullFlavor
                if (!compareNullFlavor(sourceComponent.get(i).getNullFlavor(), targetComponent.get(j).getNullFlavor(), errorMessage + " -> Null Flavor")) {
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

    private Boolean healthCareFacilityComparison(HealthCareFacility sourceHealthCareFacility, HealthCareFacility targetHealthCareFacility, String errorMessage) {
        boolean errorExists = false;
        if (sourceHealthCareFacility == null && targetHealthCareFacility == null) {
            return true;
        } else if (sourceHealthCareFacility != null && targetHealthCareFacility != null) {
            //realmCode
            if (!compareRealmCodes(sourceHealthCareFacility.getRealmCodes(),targetHealthCareFacility.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //type Id
            if (!typeIDComparison(sourceHealthCareFacility.getTypeId(),targetHealthCareFacility.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceHealthCareFacility.getTemplateIds(),targetHealthCareFacility.getTemplateIds(),errorMessage + " -> TemplateIDS")) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceHealthCareFacility.getIds(),targetHealthCareFacility.getIds(),errorMessage + " -> IDs")) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceHealthCareFacility.getCode(),targetHealthCareFacility.getCode(),errorMessage + " -> Code")) {
                errorExists = true;
            }
            //location - Place
            if (!placeComparison(sourceHealthCareFacility.getLocation(),targetHealthCareFacility.getLocation(),errorMessage + " -> Location")) {
                errorExists = true;
            }
            //serviceProviderOgranization - Organization
            if (!organizationComparison(sourceHealthCareFacility.getServiceProviderOrganization(),targetHealthCareFacility.getServiceProviderOrganization(),errorMessage + " -> Service Provider Organization")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceHealthCareFacility.getNullFlavor(),targetHealthCareFacility.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //ClassCode
            if (!compareClassCode(sourceHealthCareFacility.getClassCode(),targetHealthCareFacility.getClassCode(),errorMessage + " -> Type Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private Boolean relatedSubjectComparison(RelatedSubject sourceRelatedSubject, RelatedSubject targetRelatedSubject, String errorMessage) {
        boolean errorExists = false;
        if (sourceRelatedSubject == null && targetRelatedSubject == null) {
            return true;
        } else if (sourceRelatedSubject != null && targetRelatedSubject != null) {
            //realmCode
            if (!compareRealmCodes(sourceRelatedSubject.getRealmCodes(),targetRelatedSubject.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //type Id
            if (!typeIDComparison(sourceRelatedSubject.getTypeId(),targetRelatedSubject.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceRelatedSubject.getTemplateIds(),targetRelatedSubject.getTemplateIds(),errorMessage + " -> TemplateIDS")) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceRelatedSubject.getCode(),targetRelatedSubject.getCode(),errorMessage + " -> Code")) {
                errorExists = true;
            }
            //Addr
            if (!compareAddr(sourceRelatedSubject.getAddrs(),targetRelatedSubject.getAddrs(),errorMessage + " -> Addrs")) {
                errorExists = true;
            }
            //Telecom
            if (!compareTelcom(sourceRelatedSubject.getTelecoms(),targetRelatedSubject.getTelecoms(),errorMessage + " -> Telecom")) {
                errorExists = true;
            }
            //subject - SubjectPerson
            if (!subjectPersonComparison(sourceRelatedSubject.getSubject(),targetRelatedSubject.getSubject(),errorMessage + " -> Subject")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceRelatedSubject.getNullFlavor(),targetRelatedSubject.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //ClassCode
            if (!compareClassCode(sourceRelatedSubject.getClassCode(),targetRelatedSubject.getClassCode(),errorMessage + " -> Type Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private Boolean actComparison(Act sourceAct, Act targetAct, String errorMessage) {
        boolean errorExists = false;
        if (sourceAct == null && targetAct == null) {
            return true;
        } else if (sourceAct != null && targetAct != null) {
            //realmCode
            if (!compareRealmCodes(sourceAct.getRealmCodes(),targetAct.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //type Id
            if (!typeIDComparison(sourceAct.getTypeId(),targetAct.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceAct.getTemplateIds(),targetAct.getTemplateIds(),errorMessage + " -> TemplateIDS")) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceAct.getIds(),targetAct.getIds(),errorMessage + " -> IDs")) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceAct.getCode(),targetAct.getCode(),errorMessage + " -> Code")) {
                errorExists = true;
            }
            //text
            if (!compareText(sourceAct.getText(),targetAct.getText(),errorMessage + " -> Text")) {
                errorExists = true;
            }
            //statusCode
            if (!compareCode(sourceAct.getCode(),targetAct.getCode(),errorMessage + " -> Code")) {
                errorExists = true;
            }
            //effectiveTime
            if (!compareEffectiveTime(sourceAct.getEffectiveTime(),targetAct.getEffectiveTime(),errorMessage + " -> Effective Time")) {
                errorExists = true;
            }
            //priorityCode
            if (!compareCode(sourceAct.getPriorityCode(),targetAct.getPriorityCode(),errorMessage + " -> PriorityCode")) {
                errorExists = true;
            }
            //languageCode
            if (!compareLanguageCode(sourceAct.getLanguageCode(),targetAct.getLanguageCode(),errorMessage + " -> LanguageCode")) {
                errorExists = true;
            }
            //subject
            if (!subjectComparison(sourceAct.getSubject(),targetAct.getSubject(),errorMessage + " -> Subject")) {
                errorExists = true;
            }
            //Specimen
            if (!specimenComparison(sourceAct.getSpecimens(),targetAct.getSpecimens(),errorMessage + " -> Specimen")) {
                errorExists = true;
            }
            //performer - Performer2
            if (!performer2Comparison(sourceAct.getPerformers(),targetAct.getPerformers(),errorMessage + " -> Performers")) {
                errorExists = true;
            }
            //author - Author
            if (!authorsComparison(sourceAct.getAuthors(),targetAct.getAuthors(),errorMessage + " -> Authors")) {
                errorExists = true;
            }
            //informant - Informant12
            if (!informantsComparison(sourceAct.getInformants(),targetAct.getInformants(),errorMessage + " -> Informants")) {
                errorExists = true;
            }
            //participant - Participant2
            if (!participants2Comparison(sourceAct.getParticipants(),targetAct.getParticipants(),errorMessage + " -> Participants")) {
                errorExists = true;
            }
            //entryRelationship - EntryRelationship
            if (!entryRelationshipComparison(sourceAct.getEntryRelationships(),targetAct.getEntryRelationships(),errorMessage + " -> Entry Relationships")) {
                errorExists = true;
            }
            //reference - Reference
            if (!referenceComparison(sourceAct.getReferences(),targetAct.getReferences(),errorMessage + " -> Reference")) {
                errorExists = true;
            }
            //Precondition - Precondition
            if (!preconditionComparison(sourceAct.getPreconditions(),targetAct.getPreconditions(),errorExists + " -> Precondition")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceAct.getNullFlavor(),targetAct.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //ClassCode
            if (!compareClassCode(sourceAct.getClassCode(),targetAct.getClassCode(),errorMessage + " -> Type Code")) {
                errorExists = true;
            }
            //moodCode
            if (!compareMoodCode(sourceAct.getMoodCode(),targetAct.getMoodCode(),errorMessage + " -> Mood Code")) {
                errorExists = true;
            }
            //negationInd
            if (sourceAct.getNegationInd() != targetAct.getNegationInd()) {
                errorExists = true;
                comparisonResult.addMessage("Negation Ind error in " + errorMessage + " -> Negation Ind");
            }
            return !errorExists;
        }
        return false;
    }

    private Boolean encounterComparison(Encounter sourceEncounter, Encounter targetEncounter, String errorMessage) {
        boolean errorExists = false;
        if (sourceEncounter == null && targetEncounter == null) {
            return true;
        } else if (sourceEncounter != null && targetEncounter != null) {
            //realmCode
            if (!compareRealmCodes(sourceEncounter.getRealmCodes(),targetEncounter.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //type Id
            if (!typeIDComparison(sourceEncounter.getTypeId(),targetEncounter.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceEncounter.getTemplateIds(),targetEncounter.getTemplateIds(),errorMessage + " -> TemplateIDS")) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceEncounter.getIds(),targetEncounter.getIds(),errorMessage + " -> IDs")) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceEncounter.getCode(),targetEncounter.getCode(),errorMessage + " -> Code")) {
                errorExists = true;
            }
            //text
            if (!compareText(sourceEncounter.getText(),targetEncounter.getText(),errorMessage + " -> Text")) {
                errorExists = true;
            }
            //statusCode
            if (!compareCode(sourceEncounter.getCode(),targetEncounter.getCode(),errorMessage + " -> Code")) {
                errorExists = true;
            }
            //effectiveTime
            if (!compareEffectiveTime(sourceEncounter.getEffectiveTime(),targetEncounter.getEffectiveTime(),errorMessage + " -> Effective Time")) {
                errorExists = true;
            }
            //priorityCode
            if (!compareCode(sourceEncounter.getPriorityCode(),targetEncounter.getPriorityCode(),errorMessage + " -> PriorityCode")) {
                errorExists = true;
            }
            //subject
            if (!subjectComparison(sourceEncounter.getSubject(),targetEncounter.getSubject(),errorMessage + " -> Subject")) {
                errorExists = true;
            }
            //Specimen
            if (!specimenComparison(sourceEncounter.getSpecimens(),targetEncounter.getSpecimens(),errorMessage + " -> Specimen")) {
                errorExists = true;
            }
            //performer - Performer2
            if (!performer2Comparison(sourceEncounter.getPerformers(),targetEncounter.getPerformers(),errorMessage + " -> Performers")) {
                errorExists = true;
            }
            //author - Author
            if (!authorsComparison(sourceEncounter.getAuthors(),targetEncounter.getAuthors(),errorMessage + " -> Authors")) {
                errorExists = true;
            }
            //informant - Informant12
            if (!informantsComparison(sourceEncounter.getInformants(),targetEncounter.getInformants(),errorMessage + " -> Informants")) {
                errorExists = true;
            }
            //participant - Participant2
            if (!participants2Comparison(sourceEncounter.getParticipants(),targetEncounter.getParticipants(),errorMessage + " -> Participants")) {
                errorExists = true;
            }
            //entryRelationship - EntryRelationship
            if (!entryRelationshipComparison(sourceEncounter.getEntryRelationships(),targetEncounter.getEntryRelationships(),errorMessage + " -> Entry Relationships")) {
                errorExists = true;
            }
            //reference - Reference
            if (!referenceComparison(sourceEncounter.getReferences(),targetEncounter.getReferences(),errorMessage + " -> Reference")) {
                errorExists = true;
            }
            //Precondition - Precondition
            if (!preconditionComparison(sourceEncounter.getPreconditions(),targetEncounter.getPreconditions(),errorExists + " -> Precondition")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceEncounter.getNullFlavor(),targetEncounter.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //ClassCode
            if (!compareClassCode(sourceEncounter.getClassCode(),targetEncounter.getClassCode(),errorMessage + " -> Type Code")) {
                errorExists = true;
            }
            //moodCode
            if (!compareMoodCode(sourceEncounter.getMoodCode(),targetEncounter.getMoodCode(),errorMessage + " -> Mood Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private Boolean observationComparison(Observation sourceObservation, Observation targetObservation, String errorMessage) {
        boolean errorExists = false;
        if (sourceObservation == null && targetObservation == null) {
            return true;
        } else if (sourceObservation != null && targetObservation != null) {
            //realmCode
            if (!compareRealmCodes(sourceObservation.getRealmCodes(),targetObservation.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //type Id
            if (!typeIDComparison(sourceObservation.getTypeId(),targetObservation.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceObservation.getTemplateIds(),targetObservation.getTemplateIds(),errorMessage + " -> TemplateIDS")) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceObservation.getIds(),targetObservation.getIds(),errorMessage + " -> IDs")) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceObservation.getCode(),targetObservation.getCode(),errorMessage + " -> Code")) {
                errorExists = true;
            }
            //derivationExpr
            if (!compareDerivationExpr(sourceObservation.getDerivationExpr(),targetObservation.getDerivationExpr(),errorMessage + " -> Derivation Expr")) {
                errorExists = true;
            }
            //text
            if (!compareText(sourceObservation.getText(),targetObservation.getText(),errorMessage + " -> Text")) {
                errorExists = true;
            }
            //statusCode
            if (!compareCode(sourceObservation.getCode(),targetObservation.getCode(),errorMessage + " -> Code")) {
                errorExists = true;
            }
            //effectiveTime
            if (!compareEffectiveTime(sourceObservation.getEffectiveTime(),targetObservation.getEffectiveTime(),errorMessage + " -> Effective Time")) {
                errorExists = true;
            }
            //priorityCode
            if (!compareCode(sourceObservation.getPriorityCode(),targetObservation.getPriorityCode(),errorMessage + " -> PriorityCode")) {
                errorExists = true;
            }
            //repeatNumber
            if (!compareRepeatNumber(sourceObservation.getRepeatNumber(),targetObservation.getRepeatNumber(),errorMessage + " -> Repeat Number")) {
                errorExists = true;
            }
            //languageCode
            if (!compareLanguageCode(sourceObservation.getLanguageCode(),targetObservation.getLanguageCode(),errorMessage + " -> Language Code")) {
                errorExists = true;
            }
            //value
            if (!compareValues(sourceObservation.getValues(),targetObservation.getValues(),errorMessage + " -> Value")) {
                errorExists = true;
            }
            //interpretationCode
            if (!compareCodes(sourceObservation.getInterpretationCodes(),targetObservation.getInterpretationCodes(),errorMessage + " -> Interpretation Codes")) {
                errorExists = true;
            }
            //methodCode
            if (!compareCodes(sourceObservation.getMethodCodes(),targetObservation.getMethodCodes(),errorMessage + " -> Method Codes")) {
                errorExists = true;
            }
            //targetSiteCode
            if (!compareTargetSiteCode(sourceObservation.getTargetSiteCodes(),targetObservation.getTargetSiteCodes(),errorMessage + " -> Target Side Code")) {
                errorExists = true;
            }
            //subject
            if (!subjectComparison(sourceObservation.getSubject(),targetObservation.getSubject(),errorMessage + " -> Subject")) {
                errorExists = true;
            }
            //Specimen
            if (!specimenComparison(sourceObservation.getSpecimens(),targetObservation.getSpecimens(),errorMessage + " -> Specimen")) {
                errorExists = true;
            }
            //performer - Performer2
            if (!performer2Comparison(sourceObservation.getPerformers(),targetObservation.getPerformers(),errorMessage + " -> Performers")) {
                errorExists = true;
            }
            //author - Author
            if (!authorsComparison(sourceObservation.getAuthors(),targetObservation.getAuthors(),errorMessage + " -> Authors")) {
                errorExists = true;
            }
            //informant - Informant12
            if (!informantsComparison(sourceObservation.getInformants(),targetObservation.getInformants(),errorMessage + " -> Informants")) {
                errorExists = true;
            }
            //participant - Participant2
            if (!participants2Comparison(sourceObservation.getParticipants(),targetObservation.getParticipants(),errorMessage + " -> Participants")) {
                errorExists = true;
            }
            //entryRelationship - EntryRelationship
            if (!entryRelationshipComparison(sourceObservation.getEntryRelationships(),targetObservation.getEntryRelationships(),errorMessage + " -> Entry Relationships")) {
                errorExists = true;
            }
            //reference - Reference
            if (!referenceComparison(sourceObservation.getReferences(),targetObservation.getReferences(),errorMessage + " -> Reference")) {
                errorExists = true;
            }
            //Precondition - Precondition
            if (!preconditionComparison(sourceObservation.getPreconditions(),targetObservation.getPreconditions(),errorMessage + " -> Precondition")) {
                errorExists = true;
            }
            //referenceRange - ReferenceRange
            if (!referenceRangeComparison(sourceObservation.getReferenceRanges(),targetObservation.getReferenceRanges(),errorMessage + " -> Reference Range")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceObservation.getNullFlavor(),targetObservation.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //ClassCode
            if (!compareClassCode(sourceObservation.getClassCode(),targetObservation.getClassCode(),errorMessage + " -> Type Code")) {
                errorExists = true;
            }
            //negationInd
            if (sourceObservation.getNegationInd() != targetObservation.getNegationInd()) {
                errorExists = true;
                comparisonResult.addMessage("Negation Ind error in " + errorMessage + " -> Negation Ind");
            }
            return !errorExists;
        }
        return false;
    }

    private Boolean observationMediaComparison(ObservationMedia sourceObservationMedia, ObservationMedia targetObservationMedia, String errorMessage) {
        boolean errorExists = false;
        if (sourceObservationMedia == null && targetObservationMedia == null) {
            return true;
        } else if (sourceObservationMedia != null && targetObservationMedia != null) {
            //realmCode
            if (!compareRealmCodes(sourceObservationMedia.getRealmCodes(),targetObservationMedia.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //type Id
            if (!typeIDComparison(sourceObservationMedia.getTypeId(),targetObservationMedia.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceObservationMedia.getTemplateIds(),targetObservationMedia.getTemplateIds(),errorMessage + " -> TemplateIDS")) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceObservationMedia.getIds(),targetObservationMedia.getIds(),errorMessage + " -> IDs")) {
                errorExists = true;
            }
            //language code
            if (!compareLanguageCode(sourceObservationMedia.getLanguageCode(),targetObservationMedia.getLanguageCode(),errorMessage + " -> Language Code")) {
                errorExists = true;
            }
            //value
            if (!compareText(sourceObservationMedia.getValue(),targetObservationMedia.getValue(),errorMessage + " -> Value")) {
                errorExists = true;
            }
            //subject - Subject
            if (!subjectComparison(sourceObservationMedia.getSubject(),targetObservationMedia.getSubject(),errorMessage + " -> Subject")) {
                errorExists = true;
            }
            //specimen - Specimen
            if (!specimenComparison(sourceObservationMedia.getSpecimens(),targetObservationMedia.getSpecimens(),errorMessage + " -> Specimens")) {
                errorExists = true;
            }
            //performer - Performer2
            if (!performer2Comparison(sourceObservationMedia.getPerformers(),targetObservationMedia.getPerformers(),errorMessage + " -> Performer2")) {
                errorExists = true;
            }
            //author - Author
            if (!authorsComparison(sourceObservationMedia.getAuthors(),targetObservationMedia.getAuthors(),errorMessage + " -> Authors")) {
                errorExists = true;
            }
            //informant - Informant12
            if (!informantsComparison(sourceObservationMedia.getInformants(),targetObservationMedia.getInformants(),errorMessage + " -> Informants")) {
                errorExists = true;
            }
            //participant - Participant2
            if (!participants2Comparison(sourceObservationMedia.getParticipants(),targetObservationMedia.getParticipants(),errorMessage + " -> Participants")) {
                errorExists = true;
            }
            //entryRelationship - EntryRelationship
            if (!entryRelationshipComparison(sourceObservationMedia.getEntryRelationships(),targetObservationMedia.getEntryRelationships(),errorMessage + " -> Entry Relationship")) {
                errorExists = true;
            }
            //reference - Reference
            if (!referenceComparison(sourceObservationMedia.getReferences(),targetObservationMedia.getReferences(),errorMessage + " -> Reference")) {
                errorExists = true;
            }
            //precondition - Precondition
            if (!preconditionComparison(sourceObservationMedia.getPreconditions(),targetObservationMedia.getPreconditions(),errorMessage + " -> Precondition")) {
                errorExists = true;
            }
            //ID
            if (!compareIDAttribute(sourceObservationMedia.getObservationMediaId(),targetObservationMedia.getObservationMediaId(),errorMessage + " -> ID")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceObservationMedia.getNullFlavor(),targetObservationMedia.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //ClassCode
            if (!compareClassCode(sourceObservationMedia.getClassCode(),targetObservationMedia.getClassCode(),errorMessage + " -> Class Code")) {
                errorExists = true;
            }
            //moodCode
            if (!compareMoodCode(sourceObservationMedia.getMoodCode(),targetObservationMedia.getMoodCode(),errorMessage + " -> Mood Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private Boolean organizerComparison(Organizer sourceOrganizer, Organizer targetOrganizer, String errorMessage) {
        boolean errorExists = false;
        if (sourceOrganizer == null && targetOrganizer == null) {
            return true;
        } else if (sourceOrganizer != null && targetOrganizer != null) {
            //realmCode
            if (!compareRealmCodes(sourceOrganizer.getRealmCodes(),targetOrganizer.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //type Id
            if (!typeIDComparison(sourceOrganizer.getTypeId(),targetOrganizer.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceOrganizer.getTemplateIds(),targetOrganizer.getTemplateIds(),errorMessage + " -> TemplateIDS")) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceOrganizer.getIds(),targetOrganizer.getIds(),errorMessage + " -> IDs")) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceOrganizer.getCode(),targetOrganizer.getCode(),errorMessage + " -> Code")) {
                errorExists = true;
            }
            //statusCode
            if (!compareCode(sourceOrganizer.getStatusCode(),targetOrganizer.getStatusCode(),errorMessage + " -> Status Code")) {
                errorExists = true;
            }
            //effectiveTime
            if (!compareEffectiveTime(sourceOrganizer.getEffectiveTime(),targetOrganizer.getEffectiveTime(),errorMessage + " -> Effectime Time")) {
                errorExists = true;
            }
            //subject - Subject
            if (!subjectComparison(sourceOrganizer.getSubject(),targetOrganizer.getSubject(),errorMessage + " -> Subject")) {
                errorExists = true;
            }
            //specimen - Specimen
            if (!specimenComparison(sourceOrganizer.getSpecimens(),targetOrganizer.getSpecimens(),errorMessage + " -> Specimens")) {
                errorExists = true;
            }
            //performer - Performer2
            if (!performer2Comparison(sourceOrganizer.getPerformers(),targetOrganizer.getPerformers(),errorMessage + " -> Performer2")) {
                errorExists = true;
            }
            //author - Author
            if (!authorsComparison(sourceOrganizer.getAuthors(),targetOrganizer.getAuthors(),errorMessage + " -> Authors")) {
                errorExists = true;
            }
            //informant - Informant12
            if (!informantsComparison(sourceOrganizer.getInformants(),targetOrganizer.getInformants(),errorMessage + " -> Informants")) {
                errorExists = true;
            }
            //participant - Participant2
            if (!participants2Comparison(sourceOrganizer.getParticipants(),targetOrganizer.getParticipants(),errorMessage + " -> Participants")) {
                errorExists = true;
            }
            //reference - Reference
            if (!referenceComparison(sourceOrganizer.getReferences(),targetOrganizer.getReferences(),errorMessage + " -> Reference")) {
                errorExists = true;
            }
            //precondition - Precondition
            if (!preconditionComparison(sourceOrganizer.getPreconditions(),targetOrganizer.getPreconditions(),errorMessage + " -> Precondition")) {
                errorExists = true;
            }
            //component - Component4
            if (!component4Comparison(sourceOrganizer.getComponents(),targetOrganizer.getComponents(),errorMessage + " -> Component 4")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceOrganizer.getNullFlavor(),targetOrganizer.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //ClassCode
            if (!compareClassCode(sourceOrganizer.getClassCode(),targetOrganizer.getClassCode(),errorMessage + " -> Class Code")) {
                errorExists = true;
            }
            //moodCode
            if (!compareMoodCode(sourceOrganizer.getMoodCode(),targetOrganizer.getMoodCode(),errorMessage + " -> Mood Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private Boolean procedureComparison(Procedure sourceProcedure, Procedure targetProcedure, String errorMessage) {
        boolean errorExists = false;
        if (sourceProcedure == null && targetProcedure == null) {
            return true;
        } else if (sourceProcedure != null && targetProcedure != null) {
            //realmCode
            if (!compareRealmCodes(sourceProcedure.getRealmCodes(),targetProcedure.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //type Id
            if (!typeIDComparison(sourceProcedure.getTypeId(),targetProcedure.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceProcedure.getTemplateIds(),targetProcedure.getTemplateIds(),errorMessage + " -> TemplateIDS")) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceProcedure.getIds(),targetProcedure.getIds(),errorMessage + " -> IDs")) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceProcedure.getCode(),targetProcedure.getCode(),errorMessage + " -> Code")) {
                errorExists = true;
            }
            //text
            if (!compareText(sourceProcedure.getText(),targetProcedure.getText(),errorMessage + " -> Text")) {
                errorExists = true;
            }
            //statusCode
            if (!compareCode(sourceProcedure.getStatusCode(),targetProcedure.getStatusCode(),errorMessage + " -> Status Code")) {
                errorExists = true;
            }
            //effectiveTime
            if (!compareEffectiveTime(sourceProcedure.getEffectiveTime(),targetProcedure.getEffectiveTime(),errorMessage + " -> Effective Time")) {
                errorExists = true;
            }
            //priorityCode
            if (!compareCode(sourceProcedure.getPriorityCode(),targetProcedure.getPriorityCode(),errorMessage + " -> Priority Code")) {
                errorExists = true;
            }
            //langaugeCode
            if (!compareCode(sourceProcedure.getLanguageCode(),targetProcedure.getLanguageCode(),errorMessage + " -> Language Code")) {
                errorExists = true;
            }
            //methodCode
            if (!compareCodes(sourceProcedure.getMethodCodes(),targetProcedure.getMethodCodes(),errorMessage + " -> Method Code")) {
                errorExists = true;
            }
            //approachSiteCode
            if (!compareCodesCD(sourceProcedure.getApproachSiteCodes(),targetProcedure.getApproachSiteCodes(),errorMessage + " -> Approach Site Code")) {
                errorExists = true;
            }
            //targetSiteCode
            if (!compareCodesCD(sourceProcedure.getTargetSiteCodes(),targetProcedure.getTargetSiteCodes(),errorMessage + " -> Target Site Code")) {
                errorExists = true;
            }
            //subject - Subject
            if (!subjectComparison(sourceProcedure.getSubject(),targetProcedure.getSubject(),errorMessage + " -> Subject")) {
                errorExists = true;
            }
            //specimen - Specimen
            if (!specimenComparison(sourceProcedure.getSpecimens(),targetProcedure.getSpecimens(),errorMessage + " -> Specimens")) {
                errorExists = true;
            }
            //performer - Performer2
            if (!performer2Comparison(sourceProcedure.getPerformers(),targetProcedure.getPerformers(),errorMessage + " -> Performer2")) {
                errorExists = true;
            }
            //author - Author
            if (!authorsComparison(sourceProcedure.getAuthors(),targetProcedure.getAuthors(),errorMessage + " -> Authors")) {
                errorExists = true;
            }
            //informant - Informant12
            if (!informantsComparison(sourceProcedure.getInformants(),targetProcedure.getInformants(),errorMessage + " -> Informants")) {
                errorExists = true;
            }
            //participant - Participant2
            if (!participants2Comparison(sourceProcedure.getParticipants(),targetProcedure.getParticipants(),errorMessage + " -> Participants")) {
                errorExists = true;
            }
            //entryRelationship - EntryRelationship
            if (!entryRelationshipComparison(sourceProcedure.getEntryRelationships(),targetProcedure.getEntryRelationships(),errorMessage + " -> Entry Relationship")) {
                errorExists = true;
            }
            //reference - Reference
            if (!referenceComparison(sourceProcedure.getReferences(),targetProcedure.getReferences(),errorMessage + " -> Reference")) {
                errorExists = true;
            }
            //precondition - Precondition
            if (!preconditionComparison(sourceProcedure.getPreconditions(),targetProcedure.getPreconditions(),errorMessage + " -> Precondition")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceProcedure.getNullFlavor(),targetProcedure.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //ClassCode
            if (!compareClassCode(sourceProcedure.getClassCode(),targetProcedure.getClassCode(),errorMessage + " -> Class Code")) {
                errorExists = true;
            }
            //moodCode
            if (!compareMoodCode(sourceProcedure.getMoodCode(),targetProcedure.getMoodCode(),errorMessage + " -> Mood Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private Boolean regionOfInterestComparison(RegionOfInterest sourceRegionOfInterest, RegionOfInterest targetRegionOfInterest, String errorMessage) {
        boolean errorExists = false;
        if (sourceRegionOfInterest == null && targetRegionOfInterest == null) {
            return true;
        } else if (sourceRegionOfInterest != null && targetRegionOfInterest != null) {
            //realmCode
            if (!compareRealmCodes(sourceRegionOfInterest.getRealmCodes(),targetRegionOfInterest.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //type Id
            if (!typeIDComparison(sourceRegionOfInterest.getTypeId(),targetRegionOfInterest.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceRegionOfInterest.getTemplateIds(),targetRegionOfInterest.getTemplateIds(),errorMessage + " -> TemplateIDS")) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceRegionOfInterest.getIds(),targetRegionOfInterest.getIds(),errorMessage + " -> IDs")) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceRegionOfInterest.getCode(),targetRegionOfInterest.getCode(),errorMessage + " -> Code")) {
                errorExists = true;
            }
            //value
            if (!compareValuesROI(sourceRegionOfInterest.getValues(),targetRegionOfInterest.getValues(),errorMessage + " -> Value")) {
                errorExists = true;
            }
            //subject - Subject
            if (!subjectComparison(sourceRegionOfInterest.getSubject(),targetRegionOfInterest.getSubject(),errorMessage + " -> Subject")) {
                errorExists = true;
            }
            //specimen - Specimen
            if (!specimenComparison(sourceRegionOfInterest.getSpecimens(),targetRegionOfInterest.getSpecimens(),errorMessage + " -> Specimens")) {
                errorExists = true;
            }
            //performer - Performer2
            if (!performer2Comparison(sourceRegionOfInterest.getPerformers(),targetRegionOfInterest.getPerformers(),errorMessage + " -> Performer2")) {
                errorExists = true;
            }
            //author - Author
            if (!authorsComparison(sourceRegionOfInterest.getAuthors(),targetRegionOfInterest.getAuthors(),errorMessage + " -> Authors")) {
                errorExists = true;
            }
            //informant - Informant12
            if (!informantsComparison(sourceRegionOfInterest.getInformants(),targetRegionOfInterest.getInformants(),errorMessage + " -> Informants")) {
                errorExists = true;
            }
            //participant - Participant2
            if (!participants2Comparison(sourceRegionOfInterest.getParticipants(),targetRegionOfInterest.getParticipants(),errorMessage + " -> Participants")) {
                errorExists = true;
            }
            //entryRelationship - EntryRelationship
            if (!entryRelationshipComparison(sourceRegionOfInterest.getEntryRelationships(),targetRegionOfInterest.getEntryRelationships(),errorMessage + " -> Entry Relationship")) {
                errorExists = true;
            }
            //reference - Reference
            if (!referenceComparison(sourceRegionOfInterest.getReferences(),targetRegionOfInterest.getReferences(),errorMessage + " -> Reference")) {
                errorExists = true;
            }
            //precondition - Precondition
            if (!preconditionComparison(sourceRegionOfInterest.getPreconditions(),targetRegionOfInterest.getPreconditions(),errorMessage + " -> Precondition")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceRegionOfInterest.getNullFlavor(),targetRegionOfInterest.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //ClassCode
            if (!compareClassCode(sourceRegionOfInterest.getClassCode(),targetRegionOfInterest.getClassCode(),errorMessage + " -> Class Code")) {
                errorExists = true;
            }
            //moodCode
            if (!compareMoodCode(sourceRegionOfInterest.getMoodCode(),targetRegionOfInterest.getMoodCode(),errorMessage + " -> Mood Code")) {
                errorExists = true;
            }
            if (!compareIDAttribute(sourceRegionOfInterest.getRegionOfInterestId(),targetRegionOfInterest.getRegionOfInterestId(),errorMessage + " -> Region of Interest ID")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private Boolean substanceAdministrationComparison(SubstanceAdministration sourceSubstanceAdministration, SubstanceAdministration targetSubstanceAdminstration, String errorMessage) {
        boolean errorExists = false;
        if (sourceSubstanceAdministration == null && targetSubstanceAdminstration == null) {
            return true;
        } else if (sourceSubstanceAdministration != null && targetSubstanceAdminstration != null) {
            //realmCode
            if (!compareRealmCodes(sourceSubstanceAdministration.getRealmCodes(),targetSubstanceAdminstration.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //type Id
            if (!typeIDComparison(sourceSubstanceAdministration.getTypeId(),targetSubstanceAdminstration.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceSubstanceAdministration.getTemplateIds(),targetSubstanceAdminstration.getTemplateIds(),errorMessage + " -> TemplateIDS")) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceSubstanceAdministration.getIds(),targetSubstanceAdminstration.getIds(),errorMessage + " -> IDs")) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceSubstanceAdministration.getCode(),targetSubstanceAdminstration.getCode(),errorMessage + " -> Code")) {
                errorExists = true;
            }
            //text
            if (!compareText(sourceSubstanceAdministration.getText(),targetSubstanceAdminstration.getText(),errorMessage + " -> Value")) {
                errorExists = true;
            }
            //statusCode
            if (!compareCode(sourceSubstanceAdministration.getStatusCode(),targetSubstanceAdminstration.getStatusCode(),errorMessage + " -> Status Code")) {
                errorExists = true;
            }
            //effectiveTime
            if (!compareEffectiveTime(sourceSubstanceAdministration.getEffectiveTimes(),targetSubstanceAdminstration.getEffectiveTimes(),errorMessage + " -> Effective Time")) {
                errorExists = true;
            }
            //priorityCode
            if (!compareCode(sourceSubstanceAdministration.getPriorityCode(),targetSubstanceAdminstration.getPriorityCode(),errorMessage + " -> Priority Code")) {
                errorExists = true;
            }
            //repeatNumber
            if (!compareRepeatNumber(sourceSubstanceAdministration.getRepeatNumber(),targetSubstanceAdminstration.getRepeatNumber(),errorMessage + " -> Repeat Number")) {
                errorExists = true;
            }
            //routeCode
            if (!compareCode(sourceSubstanceAdministration.getRouteCode(),targetSubstanceAdminstration.getRouteCode(),errorMessage + " -> Route Code")) {
                errorExists = true;
            }
            //approachSiteCode
            if (!compareCodesCD(sourceSubstanceAdministration.getApproachSiteCodes(),targetSubstanceAdminstration.getApproachSiteCodes(),errorMessage + " -> Approach Site Code")) {
                errorExists = true;
            }
            //doseQuantity
            if (!compareDose(sourceSubstanceAdministration.getDoseQuantity(),targetSubstanceAdminstration.getDoseQuantity(),errorMessage + " -> Dose Quantity")) {
                errorExists = true;
            }
            //rateQuantity
            if (!compareDose(sourceSubstanceAdministration.getRateQuantity(),targetSubstanceAdminstration.getRateQuantity(),errorMessage + " -> Rate Quantity")) {
                errorExists = true;
            }
            //maxDoseQuantity
            if (!compareMaxDose(sourceSubstanceAdministration.getMaxDoseQuantity(),targetSubstanceAdminstration.getMaxDoseQuantity(),errorMessage + " -> Max Dose Quantity")) {
                errorExists = true;
            }
            //administrationUnitCode
            if (!compareCode(sourceSubstanceAdministration.getAdministrationUnitCode(),targetSubstanceAdminstration.getAdministrationUnitCode(),errorMessage + " -> Adminstration Unit Code")) {
                errorExists = true;
            }
            //subject - Subject
            if (!subjectComparison(sourceSubstanceAdministration.getSubject(),targetSubstanceAdminstration.getSubject(),errorMessage + " -> Subject")) {
                errorExists = true;
            }
            //specimen - Specimen
            if (!specimenComparison(sourceSubstanceAdministration.getSpecimens(),targetSubstanceAdminstration.getSpecimens(),errorMessage + " -> Specimens")) {
                errorExists = true;
            }
            //consumable
            if (!consumableComparison(sourceSubstanceAdministration.getConsumable(),targetSubstanceAdminstration.getConsumable(),errorMessage + " -> Consumable")) {
                errorExists = true;
            }
            //performer - Performer2
            if (!performer2Comparison(sourceSubstanceAdministration.getPerformers(),targetSubstanceAdminstration.getPerformers(),errorMessage + " -> Performer2")) {
                errorExists = true;
            }
            //author - Author
            if (!authorsComparison(sourceSubstanceAdministration.getAuthors(),targetSubstanceAdminstration.getAuthors(),errorMessage + " -> Authors")) {
                errorExists = true;
            }
            //informant - Informant12
            if (!informantsComparison(sourceSubstanceAdministration.getInformants(),targetSubstanceAdminstration.getInformants(),errorMessage + " -> Informants")) {
                errorExists = true;
            }
            //participant - Participant2
            if (!participants2Comparison(sourceSubstanceAdministration.getParticipants(),targetSubstanceAdminstration.getParticipants(),errorMessage + " -> Participants")) {
                errorExists = true;
            }
            //entryRelationship - EntryRelationship
            if (!entryRelationshipComparison(sourceSubstanceAdministration.getEntryRelationships(),targetSubstanceAdminstration.getEntryRelationships(),errorMessage + " -> Entry Relationship")) {
                errorExists = true;
            }
            //reference - Reference
            if (!referenceComparison(sourceSubstanceAdministration.getReferences(),targetSubstanceAdminstration.getReferences(),errorMessage + " -> Reference")) {
                errorExists = true;
            }
            //precondition - Precondition
            if (!preconditionComparison(sourceSubstanceAdministration.getPreconditions(),targetSubstanceAdminstration.getPreconditions(),errorMessage + " -> Precondition")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceSubstanceAdministration.getNullFlavor(),targetSubstanceAdminstration.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //ClassCode
            if (!compareClassCode(sourceSubstanceAdministration.getClassCode(),targetSubstanceAdminstration.getClassCode(),errorMessage + " -> Class Code")) {
                errorExists = true;
            }
            //moodCode
            if (!compareMoodCode(sourceSubstanceAdministration.getMoodCode(),targetSubstanceAdminstration.getMoodCode(),errorMessage + " -> Mood Code")) {
                errorExists = true;
            }
            //negationInd
            if (sourceSubstanceAdministration.getNegationInd() == targetSubstanceAdminstration.getNegationInd()) {
                errorExists = true;
                comparisonResult.addMessage("Negation Ind error in " + errorMessage + " -> Negation Ind");
            }
            return !errorExists;
        }
        return false;
    }

    private Boolean supplyComparison(Supply sourceSupply, Supply targetSupply, String errorMessage) {
        boolean errorExists = false;
        if (sourceSupply == null && targetSupply == null) {
            return true;
        } else if (sourceSupply != null && targetSupply != null) {
            //realmCode
            if (!compareRealmCodes(sourceSupply.getRealmCodes(),targetSupply.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //type Id
            if (!typeIDComparison(sourceSupply.getTypeId(),targetSupply.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceSupply.getTemplateIds(),targetSupply.getTemplateIds(),errorMessage + " -> TemplateIDS")) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceSupply.getIds(),targetSupply.getIds(),errorMessage + " -> IDs")) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceSupply.getCode(),targetSupply.getCode(),errorMessage + " -> Code")) {
                errorExists = true;
            }
            //text
            if (!compareText(sourceSupply.getText(),targetSupply.getText(),errorMessage + " -> Value")) {
                errorExists = true;
            }
            //statusCode
            if (!compareCode(sourceSupply.getStatusCode(),targetSupply.getStatusCode(),errorMessage + " -> Status Code")) {
                errorExists = true;
            }
            //effectiveTime
            if (!compareEffectiveTime(sourceSupply.getEffectiveTimes(),targetSupply.getEffectiveTimes(),errorMessage + " -> Effective Time")) {
                errorExists = true;
            }
            //priorityCode
            if (!compareCodes(sourceSupply.getPriorityCodes(),targetSupply.getPriorityCodes(),errorMessage + " -> Priority Code")) {
                errorExists = true;
            }
            //repeatNumber
            if (!compareRepeatNumber(sourceSupply.getRepeatNumber(),targetSupply.getRepeatNumber(),errorMessage + " -> Repeat Number")) {
                errorExists = true;
            }
            //independentInd
            if (!comparePreferenceInd(sourceSupply.getIndependentInd(),targetSupply.getIndependentInd(),errorMessage + " -> Independent Ind")) {
                errorExists = true;
            }
            //quantity
            if (!compareQuantity(sourceSupply.getQuantity(),targetSupply.getQuantity(),errorMessage + " -> Quantity")) {
                errorExists = true;
            }
            //expectedUseTime
            if (!compareExpectedUseTime(sourceSupply.getExpectedUseTime(),targetSupply.getExpectedUseTime(),errorMessage + " -> Expected Use Time")) {
                errorExists = true;
            }
            //subject - Subject
            if (!subjectComparison(sourceSupply.getSubject(),targetSupply.getSubject(),errorMessage + " -> Subject")) {
                errorExists = true;
            }
            //specimen - Specimen
            if (!specimenComparison(sourceSupply.getSpecimens(),targetSupply.getSpecimens(),errorMessage + " -> Specimens")) {
                errorExists = true;
            }
            //product
            if (!productComparison(sourceSupply.getProduct(),targetSupply.getProduct(),errorMessage + " -> Product")) {
                errorExists = true;
            }
            //author - Author
            if (!authorsComparison(sourceSupply.getAuthors(),targetSupply.getAuthors(),errorMessage + " -> Authors")) {
                errorExists = true;
            }
            //informant - Informant12
            if (!informantsComparison(sourceSupply.getInformants(),targetSupply.getInformants(),errorMessage + " -> Informants")) {
                errorExists = true;
            }
            //participant - Participant2
            if (!participants2Comparison(sourceSupply.getParticipants(),targetSupply.getParticipants(),errorMessage + " -> Participants")) {
                errorExists = true;
            }
            //entryRelationship - EntryRelationship
            if (!entryRelationshipComparison(sourceSupply.getEntryRelationships(),targetSupply.getEntryRelationships(),errorMessage + " -> Entry Relationship")) {
                errorExists = true;
            }
            //precondition - Precondition
            if (!preconditionComparison(sourceSupply.getPreconditions(),targetSupply.getPreconditions(),errorMessage + " -> Precondition")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceSupply.getNullFlavor(),targetSupply.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //ClassCode
            if (!compareClassCode(sourceSupply.getClassCode(),targetSupply.getClassCode(),errorMessage + " -> Class Code")) {
                errorExists = true;
            }
            //moodCode
            if (!compareMoodCode(sourceSupply.getMoodCode(),targetSupply.getMoodCode(),errorMessage + " -> Mood Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private Boolean subjectPersonComparison(SubjectPerson sourceSubjectPerson, SubjectPerson targetSubjectPerson, String errorMessage) {
        boolean errorExists = false;
        if (sourceSubjectPerson == null && targetSubjectPerson == null) {
            return true;
        } else if (sourceSubjectPerson != null && targetSubjectPerson != null) {
            //realmCode
            if (!compareRealmCodes(sourceSubjectPerson.getRealmCodes(),targetSubjectPerson.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //type Id
            if (!typeIDComparison(sourceSubjectPerson.getTypeId(),targetSubjectPerson.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceSubjectPerson.getTemplateIds(),targetSubjectPerson.getTemplateIds(),errorMessage + " -> TemplateIDS")) {
                errorExists = true;
            }
            //name
            if (!compareNamesPN(sourceSubjectPerson.getNames(),targetSubjectPerson.getNames(),errorMessage + " -> Name")) {
                errorExists = true;
            }
            //administrativeGenderCode
            if (!compareCode(sourceSubjectPerson.getAdministrativeGenderCode(),targetSubjectPerson.getAdministrativeGenderCode(),errorMessage + " -> Adminstrative Gender Code")) {
                errorExists = true;
            }
            //birthTime
            if (!compareTime(sourceSubjectPerson.getBirthTime(),targetSubjectPerson.getBirthTime(),errorMessage + " -> BirthTime")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceSubjectPerson.getNullFlavor(),targetSubjectPerson.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //ClassCode
            if (!compareClassCode(sourceSubjectPerson.getClassCode(),targetSubjectPerson.getClassCode(),errorMessage + " -> Class Code")) {
                errorExists = true;
            }
            //DeterminerCode
            if (!compareDeterminerCode(sourceSubjectPerson.getDeterminerCode(),targetSubjectPerson.getDeterminerCode(),errorMessage + " -> Determiner Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private Boolean specimenComparison(EList<Specimen> sourceSpecimen, EList<Specimen> targetSpecimen, String errorMessage) {
        Boolean errorExists = false;
        for (int i=0;i<sourceSpecimen.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetSpecimen.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (!compareRealmCodes(sourceSpecimen.get(i).getRealmCodes(), targetSpecimen.get(j).getRealmCodes(), errorMessage + " -> Realm Codes")) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourceSpecimen.get(i).getTypeId(), targetSpecimen.get(j).getTypeId(), errorMessage + " -> typeID")) {
                    specificError = true;
                }
                //compare TemplateIDs
                if (!compareTemplateID(sourceSpecimen.get(i).getTemplateIds(), targetSpecimen.get(j).getTemplateIds(), errorMessage + " -> TemplateID")) {
                    specificError = true;
                }
                //Section
                if (!specimenRoleComparison(sourceSpecimen.get(i).getSpecimenRole(),targetSpecimen.get(j).getSpecimenRole(),errorMessage + " -> Specimen Role")) {
                    specificError = true;
                }
                //compare Type Code
                if (!compareTypeCode(sourceSpecimen.get(i).getTypeCode(),targetSpecimen.get(j).getTypeCode(),errorMessage + " -> TypeCode")) {
                    specificError = true;
                }
                //compare NullFlavor
                if (!compareNullFlavor(sourceSpecimen.get(i).getNullFlavor(), targetSpecimen.get(j).getNullFlavor(), errorMessage + " -> Null Flavor")) {
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

    private Boolean performer2Comparison(EList<Performer2> sourcePerformer, EList<Performer2> targetPerformer, String errorMessage) {
        Boolean errorExists = false;
        for (int i=0;i<sourcePerformer.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetPerformer.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (!compareRealmCodes(sourcePerformer.get(i).getRealmCodes(), targetPerformer.get(j).getRealmCodes(), errorMessage + " -> Realm Codes")) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourcePerformer.get(i).getTypeId(), targetPerformer.get(j).getTypeId(), errorMessage + " -> typeID")) {
                    specificError = true;
                }
                //compare TemplateIDs
                if (!compareTemplateID(sourcePerformer.get(i).getTemplateIds(), targetPerformer.get(j).getTemplateIds(), errorMessage + " -> TemplateID")) {
                    specificError = true;
                }
                //Time
                if (!compareExpectedUseTime(sourcePerformer.get(i).getTime(),targetPerformer.get(j).getTime(),errorMessage + " -> Time")) {
                    specificError = true;
                }
                //modeCode
                if (!compareCode(sourcePerformer.get(i).getModeCode(),targetPerformer.get(j).getModeCode(),errorMessage + " -> Mode Code")) {
                    specificError = true;
                }
                //assignedEntity - AssignedEntity
                if (!assignedEntityComparison(sourcePerformer.get(i).getAssignedEntity(),targetPerformer.get(j).getAssignedEntity(),errorMessage + " -> AssignedEntity")) {
                    specificError = true;
                }
                //compare NullFlavor
                if (!compareNullFlavor(sourcePerformer.get(i).getNullFlavor(), targetPerformer.get(j).getNullFlavor(), errorMessage + " -> Null Flavor")) {
                    specificError = true;
                }
                //type Code
                if (!compareTypeCode(sourcePerformer.get(i).getTypeCode(),targetPerformer.get(j).getTypeCode(),errorMessage + " -> Type Code")) {
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

    private Boolean participants2Comparison(EList<Participant2> sourceParticipants, EList<Participant2> targetParticipants, String errorMessage) {
        Boolean errorExists = false;
        for (int i=0;i<sourceParticipants.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetParticipants.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (!compareRealmCodes(sourceParticipants.get(i).getRealmCodes(), targetParticipants.get(j).getRealmCodes(), errorMessage + " -> Realm Codes")) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourceParticipants.get(i).getTypeId(), targetParticipants.get(j).getTypeId(), errorMessage + " -> typeID")) {
                    specificError = true;
                }
                //compare TemplateIDs
                if (!compareTemplateID(sourceParticipants.get(i).getTemplateIds(), targetParticipants.get(j).getTemplateIds(), errorMessage + " -> TemplateID")) {
                    specificError = true;
                }
                //Time
                if (!compareExpectedUseTime(sourceParticipants.get(i).getTime(),targetParticipants.get(j).getTime(),errorMessage + " -> Time")) {
                    specificError = true;
                }
                //awarenessCode
                if (!compareCode(sourceParticipants.get(i).getAwarenessCode(),targetParticipants.get(j).getAwarenessCode(),errorMessage + " -> Awareness Code")) {
                    specificError = true;
                }
                //participantRole - ParticipantRole
                if (!participantRoleComparison(sourceParticipants.get(i).getParticipantRole(),targetParticipants.get(j).getParticipantRole(),errorMessage + " -> Participation Role")) {
                    specificError = true;
                }
                //compare NullFlavor
                if (!compareNullFlavor(sourceParticipants.get(i).getNullFlavor(), targetParticipants.get(j).getNullFlavor(), errorMessage + " -> Null Flavor")) {
                    specificError = true;
                }
                //type Code
                if (!compareTypeCode(sourceParticipants.get(i).getTypeCode(),targetParticipants.get(j).getTypeCode(),errorMessage + " -> Type Code")) {
                    specificError = true;
                }
                //contextControlCode
                if (!compareContextControlCode(sourceParticipants.get(i).getContextControlCode(),targetParticipants.get(j).getContextControlCode(),errorMessage + " -> Context Control Code")) {
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

    private Boolean entryRelationshipComparison(EList<EntryRelationship> sourceEntryRelationships, EList<EntryRelationship> targetEntryRelationships, String errorMessage) {
        Boolean errorExists = false;
        for (int i=0;i<sourceEntryRelationships.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetEntryRelationships.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (!compareRealmCodes(sourceEntryRelationships.get(i).getRealmCodes(), targetEntryRelationships.get(j).getRealmCodes(), errorMessage + " -> Realm Codes")) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourceEntryRelationships.get(i).getTypeId(), targetEntryRelationships.get(j).getTypeId(), errorMessage + " -> typeID")) {
                    specificError = true;
                }
                //compare TemplateIDs
                if (!compareTemplateID(sourceEntryRelationships.get(i).getTemplateIds(), targetEntryRelationships.get(j).getTemplateIds(), errorMessage + " -> TemplateID")) {
                    specificError = true;
                }
                //sequenceNumber
                if (!compareVersionNumber(sourceEntryRelationships.get(i).getSequenceNumber(),targetEntryRelationships.get(j).getSequenceNumber(),errorMessage + " -> Sequence Number")) {
                    specificError = true;
                }
                //seperatableInd
                if (!comparePreferenceInd(sourceEntryRelationships.get(i).getSeperatableInd(),targetEntryRelationships.get(j).getSeperatableInd(),errorMessage + " -> Seperatable Ind")) {
                    specificError = true;
                }
                //Choice - Act, Encounter,Observation,observationMedia,Organizer, Procedure,RegionOfInterest,SubstanceAdminstration,Supply
                if (!actComparison(sourceEntryRelationships.get(i).getAct(),targetEntryRelationships.get(j).getAct(),errorMessage + " -> Act")
                        || encounterComparison(sourceEntryRelationships.get(i).getEncounter(),targetEntryRelationships.get(j).getEncounter(),errorMessage + " -> Encounter")
                        || observationComparison(sourceEntryRelationships.get(i).getObservation(),targetEntryRelationships.get(j).getObservation(),errorMessage + " -> Observation")
                        || observationMediaComparison(sourceEntryRelationships.get(i).getObservationMedia(),targetEntryRelationships.get(j).getObservationMedia(),errorMessage + " -> Observation Media")
                        || organizerComparison(sourceEntryRelationships.get(i).getOrganizer(),targetEntryRelationships.get(j).getOrganizer(),errorMessage+ " -> Organizer")
                        || procedureComparison(sourceEntryRelationships.get(i).getProcedure(),targetEntryRelationships.get(j).getProcedure(),errorMessage + " -> Procedure")
                        || regionOfInterestComparison(sourceEntryRelationships.get(i).getRegionOfInterest(),targetEntryRelationships.get(j).getRegionOfInterest(),errorMessage + " -> Region of Interest")
                        || substanceAdministrationComparison(sourceEntryRelationships.get(i).getSubstanceAdministration(),targetEntryRelationships.get(j).getSubstanceAdministration(),errorMessage + " Substance Administration")
                        || supplyComparison(sourceEntryRelationships.get(i).getSupply(),targetEntryRelationships.get(j).getSupply(),errorMessage + " Supply")) {
                    specificError = true;
                }

                //compare NullFlavor
                if (!compareNullFlavor(sourceEntryRelationships.get(i).getNullFlavor(), targetEntryRelationships.get(j).getNullFlavor(), errorMessage + " -> Null Flavor")) {
                    specificError = true;
                }
                //type Code
                if (!compareTypeCode(sourceEntryRelationships.get(i).getTypeCode(),targetEntryRelationships.get(j).getTypeCode(),errorMessage + " -> Type Code")) {
                    specificError = true;
                }
                //Inversion Ind
                if (sourceEntryRelationships.get(i).getInversionInd() != targetEntryRelationships.get(j).getInversionInd()) {
                    errorExists = true;
                    comparisonResult.addMessage("Inversion Ind error in " + errorMessage + " -> Inversion Ind");

                }
                //contextConductionInd
                if (sourceEntryRelationships.get(i).getContextConductionInd() != targetEntryRelationships.get(j).getContextConductionInd()) {
                    specificError = true;
                    comparisonResult.addMessage("Context Conduction Ind error in " + errorMessage + " -> Context Conduction Ind");

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

    private Boolean referenceComparison(EList<Reference> sourceReference, EList<Reference> targetReference, String errorMessage) {
        Boolean errorExists = false;
        for (int i=0;i<sourceReference.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetReference.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (!compareRealmCodes(sourceReference.get(i).getRealmCodes(), targetReference.get(j).getRealmCodes(), errorMessage + " -> Realm Codes")) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourceReference.get(i).getTypeId(), targetReference.get(j).getTypeId(), errorMessage + " -> typeID")) {
                    specificError = true;
                }
                //compare TemplateIDs
                if (!compareTemplateID(sourceReference.get(i).getTemplateIds(), targetReference.get(j).getTemplateIds(), errorMessage + " -> TemplateID")) {
                    specificError = true;
                }
                //seperatableInd
                if (!comparePreferenceInd(sourceReference.get(i).getSeperatableInd(),targetReference.get(j).getSeperatableInd(),errorMessage + " -> Seperatable Ind")) {
                    specificError = true;
                }
                //Choice - ExternalAct, ExternalObservation, ExternalProcedure, ExternalDocument
                if (!externalActComparison(sourceReference.get(i).getExternalAct(),targetReference.get(j).getExternalAct(),errorMessage + " -> External Act")
                        || externalObservationComparison(sourceReference.get(i).getExternalObservation(),targetReference.get(j).getExternalObservation(),errorMessage + " -> External Observation")
                        || externalProcedureComparison(sourceReference.get(i).getExternalProcedure(),targetReference.get(j).getExternalProcedure(),errorMessage + " -> External Procedure")
                        || externalDocumentComparison(sourceReference.get(i).getExternalDocument(),targetReference.get(j).getExternalDocument(),errorMessage + " -> External Document")) {
                    specificError = true;
                }

                //compare NullFlavor
                if (!compareNullFlavor(sourceReference.get(i).getNullFlavor(), targetReference.get(j).getNullFlavor(), errorMessage + " -> Null Flavor")) {
                    specificError = true;
                }
                //type Code
                if (!compareTypeCode(sourceReference.get(i).getTypeCode(),targetReference.get(j).getTypeCode(),errorMessage + " -> Type Code")) {
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

    private Boolean preconditionComparison(EList<Precondition> sourcePrecondition, EList<Precondition> targetPrecondition, String errorMessage) {
        Boolean errorExists = false;
        for (int i=0;i<sourcePrecondition.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetPrecondition.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (!compareRealmCodes(sourcePrecondition.get(i).getRealmCodes(), targetPrecondition.get(j).getRealmCodes(), errorMessage + " -> Realm Codes")) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourcePrecondition.get(i).getTypeId(), targetPrecondition.get(j).getTypeId(), errorMessage + " -> typeID")) {
                    specificError = true;
                }
                //compare criterion - Criterion
                if (!criterionComparison(sourcePrecondition.get(i).getCriterion(), targetPrecondition.get(j).getCriterion(), errorMessage + " -> Criterion")) {
                    specificError = true;
                }
                //compare NullFlavor
                if (!compareNullFlavor(sourcePrecondition.get(i).getNullFlavor(), targetPrecondition.get(j).getNullFlavor(), errorMessage + " -> Null Flavor")) {
                    specificError = true;
                }
                //type Code
                if (!compareTypeCode(sourcePrecondition.get(i).getTypeCode(),targetPrecondition.get(j).getTypeCode(),errorMessage + " -> Type Code")) {
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

    private boolean referenceRangeComparison(EList<ReferenceRange> sourceReferenceRange, EList<ReferenceRange> targetReferenceRange, String errorMessage) {
        Boolean errorExists = false;
        for (int i=0;i<sourceReferenceRange.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetReferenceRange.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (!compareRealmCodes(sourceReferenceRange.get(i).getRealmCodes(), targetReferenceRange.get(j).getRealmCodes(), errorMessage + " -> Realm Codes")) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourceReferenceRange.get(i).getTypeId(), targetReferenceRange.get(j).getTypeId(), errorMessage + " -> typeID")) {
                    specificError = true;
                }
                //compare TemplateIDs
                if (!compareTemplateID(sourceReferenceRange.get(i).getTemplateIds(), targetReferenceRange.get(j).getTemplateIds(), errorMessage + " -> TemplateID")) {
                    specificError = true;
                }
                //observationRange - ObservationRange
                if (!observationRangeComparison(sourceReferenceRange.get(i).getObservationRange(),targetReferenceRange.get(j).getObservationRange(),errorMessage + " -> ObservationRange")) {
                    specificError = true;
                }
                //compare NullFlavor
                if (!compareNullFlavor(sourceReferenceRange.get(i).getNullFlavor(), targetReferenceRange.get(j).getNullFlavor(), errorMessage + " -> Null Flavor")) {
                    specificError = true;
                }
                //type Code
                if (!compareTypeCode(sourceReferenceRange.get(i).getTypeCode(),targetReferenceRange.get(j).getTypeCode(),errorMessage + " -> Type Code")) {
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

    private boolean component4Comparison(EList<Component4> sourceComponent4, EList<Component4> targetComponent4, String errorMessage) {
        Boolean errorExists = false;
        for (int i=0;i<sourceComponent4.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetComponent4.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (!compareRealmCodes(sourceComponent4.get(i).getRealmCodes(), targetComponent4.get(j).getRealmCodes(), errorMessage + " -> Realm Codes")) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourceComponent4.get(i).getTypeId(), targetComponent4.get(j).getTypeId(), errorMessage + " -> typeID")) {
                    specificError = true;
                }
                //compare TemplateIDs
                if (!compareTemplateID(sourceComponent4.get(i).getTemplateIds(), targetComponent4.get(j).getTemplateIds(), errorMessage + " -> TemplateID")) {
                    specificError = true;
                }
                //sequenceNumber
                if (!compareVersionNumber(sourceComponent4.get(i).getSequenceNumber(),targetComponent4.get(j).getSequenceNumber(),errorMessage + " -> Sequence Number")) {
                    specificError = true;
                }
                //seperatableInd
                if (!comparePreferenceInd(sourceComponent4.get(i).getSeperatableInd(),targetComponent4.get(j).getSeperatableInd(),errorMessage + " -> Seperatable Ind")) {
                    specificError = true;
                }
                //choice - Act, Encounter, Observation, ObservationMedia, Organizer, Procedure, RegionOfInterest, SubstanceAdministraion,Supply
                if (!actComparison(sourceComponent4.get(i).getAct(),targetComponent4.get(j).getAct(),errorMessage + " -> Act")
                        || observationComparison(sourceComponent4.get(i).getObservation(),targetComponent4.get(j).getObservation(),errorMessage + " -> Observation")
                        || observationMediaComparison(sourceComponent4.get(i).getObservationMedia(),targetComponent4.get(j).getObservationMedia(),errorMessage + " -> ObservationMedia")
                        || organizerComparison(sourceComponent4.get(i).getOrganizer(),targetComponent4.get(j).getOrganizer(),errorMessage+ " -> Organizer")
                        || procedureComparison(sourceComponent4.get(i).getProcedure(),targetComponent4.get(j).getProcedure(),errorMessage + " -> Procedure")
                        || regionOfInterestComparison(sourceComponent4.get(i).getRegionOfInterest(),targetComponent4.get(j).getRegionOfInterest(),errorMessage + " -> Region of Interest")
                        || substanceAdministrationComparison(sourceComponent4.get(i).getSubstanceAdministration(),targetComponent4.get(j).getSubstanceAdministration(),errorMessage + " Substance Administration")
                        || supplyComparison(sourceComponent4.get(i).getSupply(),targetComponent4.get(j).getSupply(),errorMessage + " Supply")) {
                    specificError = true;
                }
                //compare NullFlavor
                if (!compareNullFlavor(sourceComponent4.get(i).getNullFlavor(), targetComponent4.get(j).getNullFlavor(), errorMessage + " -> Null Flavor")) {
                    specificError = true;
                }
                //type Code
                if (!compareTypeCode(sourceComponent4.get(i).getTypeCode(),targetComponent4.get(j).getTypeCode(),errorMessage + " -> Type Code")) {
                    specificError = true;
                }
                //contextConductionInd
                if (sourceComponent4.get(i).getContextConductionInd() != sourceComponent4.get(j).getContextConductionInd()) {
                    specificError = true;
                    comparisonResult.addMessage("Context Conduction Ind error in " + errorMessage + " -> Context Conduction Ind");
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

    private boolean compareValuesROI(EList<RegionOfInterestValue> sourceValue, EList<RegionOfInterestValue> targetValue, String errorMessage) {
        Boolean errorExists = false;
        for (int i=0; i<sourceValue.size(); i++) {
            int targetMatches = 0;
            for (int j=0; j<targetValue.size(); j++) {
                if (Objects.equals(sourceValue.get(i).getValue(),targetValue.get(j).getValue())
                        && Objects.equals(sourceValue.get(i).getNullFlavor().getLiteral(),targetValue.get(j).getNullFlavor().getLiteral())
                        && (sourceValue.get(i).isUnsorted() == targetValue.get(j).isUnsorted()))
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

    private boolean consumableComparison(Consumable sourceConsumable, Consumable targetConsumable, String errorMessage) {
        boolean errorExists = false;
        if (sourceConsumable == null && targetConsumable == null) {
            return true;
        } else if (sourceConsumable != null && targetConsumable != null) {
            //realmCode
            if (!compareRealmCodes(sourceConsumable.getRealmCodes(),targetConsumable.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //type Id
            if (!typeIDComparison(sourceConsumable.getTypeId(),targetConsumable.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceConsumable.getTemplateIds(),targetConsumable.getTemplateIds(),errorMessage + " -> TemplateIDS")) {
                errorExists = true;
            }
            //manufacturedProduct
            if (!manufacturedProductComparison(sourceConsumable.getManufacturedProduct(),targetConsumable.getManufacturedProduct(),errorMessage + " -> ManufacturedProduct")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceConsumable.getNullFlavor(),targetConsumable.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //Type Code
            if (!compareTypeCode(sourceConsumable.getTypeCode(),targetConsumable.getTypeCode(),errorMessage + " -> Type Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean productComparison(Product sourceProduct, Product targetProduct, String errorMessage) {
        boolean errorExists = false;
        if (sourceProduct == null && targetProduct == null) {
            return true;
        } else if (sourceProduct != null && targetProduct != null) {
            //realmCode
            if (!compareRealmCodes(sourceProduct.getRealmCodes(),targetProduct.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //type Id
            if (!typeIDComparison(sourceProduct.getTypeId(),targetProduct.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceProduct.getTemplateIds(),targetProduct.getTemplateIds(),errorMessage + " -> TemplateIDS")) {
                errorExists = true;
            }
            //manufacturedProduct
            if (!manufacturedProductComparison(sourceProduct.getManufacturedProduct(),targetProduct.getManufacturedProduct(),errorMessage + " -> ManufacturedProduct")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceProduct.getNullFlavor(),targetProduct.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //Type Code
            if (!compareTypeCode(sourceProduct.getTypeCode(),targetProduct.getTypeCode(),errorMessage + " -> Type Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean specimenRoleComparison(SpecimenRole sourceSpecimenRole, SpecimenRole targetSpecimenRole, String errorMessage) {
        boolean errorExists = false;
        if (sourceSpecimenRole == null && targetSpecimenRole == null) {
            return true;
        } else if (sourceSpecimenRole != null && targetSpecimenRole != null) {
            //realmCode
            if (!compareRealmCodes(sourceSpecimenRole.getRealmCodes(),targetSpecimenRole.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //type Id
            if (!typeIDComparison(sourceSpecimenRole.getTypeId(),targetSpecimenRole.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceSpecimenRole.getTemplateIds(),targetSpecimenRole.getTemplateIds(),errorMessage + " -> TemplateIDS")) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceSpecimenRole.getIds(),targetSpecimenRole.getIds(),errorMessage + " -> IDs")) {
                errorExists = true;
            }
            //specimenPlayingEntity - PlayingEntity
            if (!comparePlayingEntity(sourceSpecimenRole.getSpecimenPlayingEntity(),targetSpecimenRole.getSpecimenPlayingEntity(),errorMessage + " -> Specimen Playing Entity")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceSpecimenRole.getNullFlavor(),targetSpecimenRole.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //Class Code
            if (!compareClassCode(sourceSpecimenRole.getClassCode(),targetSpecimenRole.getClassCode(),errorMessage + " -> Class Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean participantRoleComparison(ParticipantRole sourceParticipantRole, ParticipantRole targetParticipantRole, String errorMessage) {
        boolean errorExists = false;
        if (sourceParticipantRole == null && targetParticipantRole == null) {
            return true;
        } else if (sourceParticipantRole != null && targetParticipantRole != null) {
            //realmCode
            if (!compareRealmCodes(sourceParticipantRole.getRealmCodes(),targetParticipantRole.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //type Id
            if (!typeIDComparison(sourceParticipantRole.getTypeId(),targetParticipantRole.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceParticipantRole.getTemplateIds(),targetParticipantRole.getTemplateIds(),errorMessage + " -> TemplateIDS")) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceParticipantRole.getIds(),targetParticipantRole.getIds(),errorMessage + " -> IDs")) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceParticipantRole.getCode(),targetParticipantRole.getCode(),errorMessage + " -> Code")) {
                errorExists = true;
            }
            //addr
            if (!compareAddr(sourceParticipantRole.getAddrs(),targetParticipantRole.getAddrs(),errorMessage + " -> Addrs")) {
                errorExists = true;
            }
            //telecom
            if (!compareTelcom(sourceParticipantRole.getTelecoms(),targetParticipantRole.getTelecoms(),errorMessage + " -> Telecom")) {
                errorExists = true;
            }
            //Choice - playingDevice (Device), playingEntity(PlayingEntity)
            if (!(deviceComparison(sourceParticipantRole.getPlayingDevice(),targetParticipantRole.getPlayingDevice(),errorMessage + " -> Device")
                    || comparePlayingEntity(sourceParticipantRole.getPlayingEntity(),targetParticipantRole.getPlayingEntity(),errorMessage + " -> Playing Entity"))) {
                errorExists = true;
            }
            //scopingEntity - Entity
            if (!entityComparison(sourceParticipantRole.getScopingEntity(),targetParticipantRole.getScopingEntity(),errorMessage + " -> Scoping Entity")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceParticipantRole.getNullFlavor(),targetParticipantRole.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //Class Code
            if (!compareClassCode(sourceParticipantRole.getClassCode(),targetParticipantRole.getClassCode(),errorMessage + " -> Class Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean externalActComparison(ExternalAct sourceExternalAct, ExternalAct targetExternalAct, String errorMessage) {
        boolean errorExists = false;
        if (sourceExternalAct == null && targetExternalAct == null) {
            return true;
        } else if (sourceExternalAct != null && targetExternalAct != null) {
            //realmCode
            if (!compareRealmCodes(sourceExternalAct.getRealmCodes(),targetExternalAct.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //type Id
            if (!typeIDComparison(sourceExternalAct.getTypeId(),targetExternalAct.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceExternalAct.getTemplateIds(),targetExternalAct.getTemplateIds(),errorMessage + " -> TemplateIDS")) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceExternalAct.getIds(),targetExternalAct.getIds(),errorMessage + " -> IDs")) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceExternalAct.getCode(),targetExternalAct.getCode(),errorMessage + " -> Code")) {
                errorExists = true;
            }
            //text
            if (!compareText(sourceExternalAct.getText(),targetExternalAct.getText(),errorMessage + " -> Text")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceExternalAct.getNullFlavor(),targetExternalAct.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //Class Code
            if (!compareClassCode(sourceExternalAct.getClassCode(),targetExternalAct.getClassCode(),errorMessage + " -> Class Code")) {
                errorExists = true;
            }
            //Mood Code
            if (!compareMoodCode(sourceExternalAct.getMoodCode(),targetExternalAct.getMoodCode(),errorMessage + " -> Mood Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean externalObservationComparison(ExternalObservation sourceExternalObservation, ExternalObservation targetExternalObservation, String errorMessage) {
        boolean errorExists = false;
        if (sourceExternalObservation == null && targetExternalObservation == null) {
            return true;
        } else if (sourceExternalObservation != null && targetExternalObservation != null) {
            //realmCode
            if (!compareRealmCodes(sourceExternalObservation.getRealmCodes(),targetExternalObservation.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //type Id
            if (!typeIDComparison(sourceExternalObservation.getTypeId(),targetExternalObservation.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceExternalObservation.getTemplateIds(),targetExternalObservation.getTemplateIds(),errorMessage + " -> TemplateIDS")) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceExternalObservation.getIds(),targetExternalObservation.getIds(),errorMessage + " -> IDs")) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceExternalObservation.getCode(),targetExternalObservation.getCode(),errorMessage + " -> Code")) {
                errorExists = true;
            }
            //text
            if (!compareText(sourceExternalObservation.getText(),targetExternalObservation.getText(),errorMessage + " -> Text")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceExternalObservation.getNullFlavor(),targetExternalObservation.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //Class Code
            if (!compareClassCode(sourceExternalObservation.getClassCode(),targetExternalObservation.getClassCode(),errorMessage + " -> Class Code")) {
                errorExists = true;
            }
            //Mood Code
            if (!compareMoodCode(sourceExternalObservation.getMoodCode(),targetExternalObservation.getMoodCode(),errorMessage + " -> Mood Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean externalProcedureComparison(ExternalProcedure sourceExternalProcedure, ExternalProcedure targetExternalProcedure, String errorMessage) {
        boolean errorExists = false;
        if (sourceExternalProcedure == null && targetExternalProcedure == null) {
            return true;
        } else if (sourceExternalProcedure != null && targetExternalProcedure != null) {
            //realmCode
            if (!compareRealmCodes(sourceExternalProcedure.getRealmCodes(),targetExternalProcedure.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //type Id
            if (!typeIDComparison(sourceExternalProcedure.getTypeId(),targetExternalProcedure.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceExternalProcedure.getTemplateIds(),targetExternalProcedure.getTemplateIds(),errorMessage + " -> TemplateIDS")) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceExternalProcedure.getIds(),targetExternalProcedure.getIds(),errorMessage + " -> IDs")) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceExternalProcedure.getCode(),targetExternalProcedure.getCode(),errorMessage + " -> Code")) {
                errorExists = true;
            }
            //text
            if (!compareText(sourceExternalProcedure.getText(),targetExternalProcedure.getText(),errorMessage + " -> Text")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceExternalProcedure.getNullFlavor(),targetExternalProcedure.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //Class Code
            if (!compareClassCode(sourceExternalProcedure.getClassCode(),targetExternalProcedure.getClassCode(),errorMessage + " -> Class Code")) {
                errorExists = true;
            }
            //Mood Code
            if (!compareMoodCode(sourceExternalProcedure.getMoodCode(),targetExternalProcedure.getMoodCode(),errorMessage + " -> Mood Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean externalDocumentComparison(ExternalDocument sourceExternalDocument, ExternalDocument targetExternalDocument, String errorMessage) {
        boolean errorExists = false;
        if (sourceExternalDocument == null && targetExternalDocument == null) {
            return true;
        } else if (sourceExternalDocument != null && targetExternalDocument != null) {
            //realmCode
            if (!compareRealmCodes(sourceExternalDocument.getRealmCodes(),targetExternalDocument.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //type Id
            if (!typeIDComparison(sourceExternalDocument.getTypeId(),targetExternalDocument.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceExternalDocument.getTemplateIds(),targetExternalDocument.getTemplateIds(),errorMessage + " -> TemplateIDS")) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceExternalDocument.getIds(),targetExternalDocument.getIds(),errorMessage + " -> IDs")) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceExternalDocument.getCode(),targetExternalDocument.getCode(),errorMessage + " -> Code")) {
                errorExists = true;
            }
            //text
            if (!compareText(sourceExternalDocument.getText(),targetExternalDocument.getText(),errorMessage + " -> Text")) {
                errorExists = true;
            }
            //setID
            if (!compareSetID(sourceExternalDocument.getSetId(),targetExternalDocument.getSetId(),errorMessage + " -> Set ID")) {
                errorExists = true;
            }
            //versionNumber
            if (!compareVersionNumber(sourceExternalDocument.getVersionNumber(),targetExternalDocument.getVersionNumber(),errorMessage + " -> Version Number")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceExternalDocument.getNullFlavor(),targetExternalDocument.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //Class Code
            if (!compareClassCode(sourceExternalDocument.getClassCode(),targetExternalDocument.getClassCode(),errorMessage + " -> Class Code")) {
                errorExists = true;
            }
            //Mood Code
            if (!compareMoodCode(sourceExternalDocument.getMoodCode(),targetExternalDocument.getMoodCode(),errorMessage + " -> Mood Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean criterionComparison(Criterion sourceCriterion, Criterion targetCriterion, String errorMessage) {
        boolean errorExists = false;
        if (sourceCriterion == null && targetCriterion == null) {
            return true;
        } else if (sourceCriterion!= null && targetCriterion != null) {
            //realmCode
            if (!compareRealmCodes(sourceCriterion.getRealmCodes(),targetCriterion.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //type Id
            if (!typeIDComparison(sourceCriterion.getTypeId(),targetCriterion.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceCriterion.getTemplateIds(),targetCriterion.getTemplateIds(),errorMessage + " -> TemplateIDS")) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceCriterion.getCode(),targetCriterion.getCode(),errorMessage + " -> Code")) {
                errorExists = true;
            }
            //text
            if (!compareText(sourceCriterion.getText(),targetCriterion.getText(),errorMessage + " -> Text")) {
                errorExists = true;
            }
            //value
            if (!compareValue(sourceCriterion.getValue(),targetCriterion.getValue(),errorMessage + " -> Value")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceCriterion.getNullFlavor(),targetCriterion.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //Class Code
            if (!compareClassCode(sourceCriterion.getClassCode(),targetCriterion.getClassCode(),errorMessage + " -> Class Code")) {
                errorExists = true;
            }
            //Mood Code
            if (!compareMoodCode(sourceCriterion.getMoodCode(),targetCriterion.getMoodCode(),errorMessage + " -> Mood Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean observationRangeComparison(ObservationRange sourceObservationRange, ObservationRange targetObservationRange, String errorMessage) {
        boolean errorExists = false;
        if (sourceObservationRange == null && targetObservationRange == null) {
            return true;
        } else if (sourceObservationRange != null && targetObservationRange != null) {
            //realmCode
            if (!compareRealmCodes(sourceObservationRange.getRealmCodes(),targetObservationRange.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //type Id
            if (!typeIDComparison(sourceObservationRange.getTypeId(),targetObservationRange.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceObservationRange.getTemplateIds(),targetObservationRange.getTemplateIds(),errorMessage + " -> TemplateIDS")) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceObservationRange.getCode(),targetObservationRange.getCode(),errorMessage + " -> Code")) {
                errorExists = true;
            }
            //text
            if (!compareText(sourceObservationRange.getText(),targetObservationRange.getText(),errorMessage + " -> Text")) {
                errorExists = true;
            }
            //value
            if (!compareValue(sourceObservationRange.getValue(),targetObservationRange.getValue(),errorMessage + " -> Value")) {
                errorExists = true;
            }
            //interpretaionCode
            if (!compareCode(sourceObservationRange.getInterpretationCode(),targetObservationRange.getInterpretationCode(),errorMessage + " - Interpretation Code")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceObservationRange.getNullFlavor(),targetObservationRange.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //Class Code
            if (!compareClassCode(sourceObservationRange.getClassCode(),targetObservationRange.getClassCode(),errorMessage + " -> Class Code")) {
                errorExists = true;
            }
            //Mood Code
            if (!compareMoodCode(sourceObservationRange.getMoodCode(),targetObservationRange.getMoodCode(),errorMessage + " -> Mood Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean manufacturedProductComparison(ManufacturedProduct sourceManufacturedProduct, ManufacturedProduct targetManufacturedProduct, String errorMessage) {
        boolean errorExists = false;
        if (sourceManufacturedProduct == null && targetManufacturedProduct == null) {
            return true;
        } else if (sourceManufacturedProduct != null && targetManufacturedProduct != null) {
            //realmCode
            if (!compareRealmCodes(sourceManufacturedProduct.getRealmCodes(),targetManufacturedProduct.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //type Id
            if (!typeIDComparison(sourceManufacturedProduct.getTypeId(),targetManufacturedProduct.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceManufacturedProduct.getTemplateIds(),targetManufacturedProduct.getTemplateIds(),errorMessage + " -> TemplateIDS")) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceManufacturedProduct.getIds(),targetManufacturedProduct.getIds(),errorMessage + " -> IDs")) {
                errorExists = true;
            }
            //Choice - manufacturedLabeled(LabeledDrug), manufacturedMaterial(Material)
            if (!(labeledDrugComparison(sourceManufacturedProduct.getManufacturedLabeledDrug(),targetManufacturedProduct.getManufacturedLabeledDrug(),errorMessage + " -> Manufactured Labeled Drug")
                    || materialComparison(sourceManufacturedProduct.getManufacturedMaterial(),targetManufacturedProduct.getManufacturedMaterial(),errorMessage + " -> Manufactured Material"))) {
                errorExists = true;
            }
            //manufacturerOrganization - Organization
            if (!organizationComparison(sourceManufacturedProduct.getManufacturerOrganization(),targetManufacturedProduct.getManufacturerOrganization(),errorMessage + " -> Manufacturer Organization")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceManufacturedProduct.getNullFlavor(),targetManufacturedProduct.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //Class Code
            if (!compareClassCode(sourceManufacturedProduct.getClassCode(),targetManufacturedProduct.getClassCode(),errorMessage + " -> Class Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean comparePlayingEntity(PlayingEntity sourcePlayingEntity, PlayingEntity targetPlayingIdentity, String errorMessage) {
        boolean errorExists = false;
        if (sourcePlayingEntity == null && targetPlayingIdentity == null) {
            return true;
        } else if (sourcePlayingEntity != null && targetPlayingIdentity != null) {
            //realmCode
            if (!compareRealmCodes(sourcePlayingEntity.getRealmCodes(),targetPlayingIdentity.getRealmCodes(),errorMessage + " -> Realm Codes")) {
                errorExists = true;
            }
            //type Id
            if (!typeIDComparison(sourcePlayingEntity.getTypeId(),targetPlayingIdentity.getTypeId(),errorMessage + " -> Type ID")) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourcePlayingEntity.getTemplateIds(),targetPlayingIdentity.getTemplateIds(),errorMessage + " -> TemplateIDS")) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourcePlayingEntity.getCode(),targetPlayingIdentity.getCode(),errorMessage + " -> Code")) {
                errorExists = true;
            }
            //quantity
            if (!compareQuantities(sourcePlayingEntity.getQuantities(),targetPlayingIdentity.getQuantities(),errorMessage + " -> Quantity")) {
                errorExists = true;
            }
            //name
            if (!compareNamesPN(sourcePlayingEntity.getNames(),targetPlayingIdentity.getNames(),errorMessage + " -> Names")) {
                errorExists = true;
            }
            //desc
            if (!compareText(sourcePlayingEntity.getDesc(),targetPlayingIdentity.getDesc(),errorMessage + " -> Desc")) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourcePlayingEntity.getNullFlavor(),targetPlayingIdentity.getNullFlavor(),errorMessage + " -> Null Flavor")) {
                errorExists = true;
            }
            //Class Code
            if (!compareClassCode(sourcePlayingEntity.getClassCode(),targetPlayingIdentity.getClassCode(),errorMessage + " -> Class Code")) {
                errorExists = true;
            }
            //determinerCode
            if (!compareDeterminerCode(sourcePlayingEntity.getDeterminerCode(),targetPlayingIdentity.getDeterminerCode(),errorMessage + " -> Determiner Code")) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean deviceComparison(Device sourceDevice, Device targetDevice, String errorMessage) {
        return true;
    }

    private boolean entityComparison(Entity sourceEntity, Entity targetEntity, String errorMessage) {
        return true;
    }

    private boolean labeledDrugComparison(LabeledDrug sourceLabeledDrug, LabeledDrug targetLabeledDrug, String errorMessage) {
        return true;
    }

    private boolean materialComparison(Material sourceMaterial, Material targetMaterial, String errorMessage) {
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

    private Boolean compareEffectiveTime(EList<SXCM_TS> source, EList<SXCM_TS> target, String errorMessage) {
        Boolean errorExists = false;
        for (int i = 0; i < source.size(); i++) {
            int targetMatches = 0;
            for (int j = 0; j < target.size(); j++) {
                if (Objects.equals(source.get(i).getValue(), target.get(j).getValue())
                        && Objects.equals(source.get(i).getNullFlavor().getLiteral(), target.get(j).getNullFlavor().getLiteral())
                        && Objects.equals(source.get(i).toString(),target.get(j).toString())) {
                    targetMatches++;
                }
            }
            if (targetMatches == 0) {
                errorExists = true;
                comparisonResult.addMessage("Effective Time error source " + i + " in " + errorMessage + "\n");
            } else if (targetMatches > 1) {
                comparisonResult.addMessage("Effective Time Warning source " + i + " has " + targetMatches + " in target in " + errorMessage + "\n");
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

    private Boolean compareClassCode(EntityClassRoot source, EntityClassRoot target, String errorMessage) {
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

    private Boolean compareClassCode(RoleClassManufacturedProduct source, RoleClassManufacturedProduct target, String errorMessage) {
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

    private Boolean compareClassCode(ActClassDocument source, ActClassDocument target, String errorMessage) {
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

    private Boolean compareClassCode(RoleClassRoot source, RoleClassRoot target, String errorMessage) {
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

    private Boolean compareClassCode(x_ActClassDocumentEntryAct source, x_ActClassDocumentEntryAct target, String errorMessage) {
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

    private Boolean compareClassCode(RoleClassSpecimen source, RoleClassSpecimen target, String errorMessage) {
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

    private Boolean compareClassCode(ActClassSupply source, ActClassSupply target, String errorMessage) {
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

    private Boolean compareClassCode(x_ActClassDocumentEntryOrganizer source, x_ActClassDocumentEntryOrganizer target, String errorMessage) {
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

    private Boolean compareClassCode(ActClassObservation source, ActClassObservation target, String errorMessage) {
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

    private Boolean compareClassCode(RoleClassServiceDeliveryLocation source, RoleClassServiceDeliveryLocation target, String errorMessage) {
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

    private Boolean compareClassCode(x_DocumentSubject source, x_DocumentSubject target, String errorMessage) {
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

    private Boolean compareTypeCode(ParticipationTargetSubject source, ParticipationTargetSubject target, String errorMessage) {
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

    private Boolean compareTypeCode(ParticipationPhysicalPerformer source, ParticipationPhysicalPerformer target, String errorMessage) {
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

    private Boolean compareTypeCode(x_ActRelationshipExternalReference source, x_ActRelationshipExternalReference target, String errorMessage) {
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

    private Boolean compareTypeCode(x_ActRelationshipEntryRelationship source, x_ActRelationshipEntryRelationship target, String errorMessage) {
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

    private Boolean compareTypeCode(ParticipationTargetLocation source, ParticipationTargetLocation target, String errorMessage) {
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

    private Boolean compareTypeCode(x_ActRelationshipEntry source, x_ActRelationshipEntry target, String errorMessage) {
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

    private Boolean compareMoodCode(x_DocumentSubstanceMood source, x_DocumentSubstanceMood target, String errorMessage) {
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

    private Boolean compareMoodCode(x_DocumentProcedureMood source, x_DocumentProcedureMood target, String errorMessage) {
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

    private Boolean compareMoodCode(x_DocumentEncounterMood source, x_DocumentEncounterMood target, String errorMessage) {
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

    private Boolean compareMoodCode(x_DocumentActMood source, x_DocumentActMood target, String errorMessage) {
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
                comparisonResult.addMessage("Name error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Name error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private Boolean comparePreferenceInd(BL source, BL target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getNullFlavor(), target.getNullFlavor())
                    && Objects.equals(source.getValue(), target.getValue()))) {
                comparisonResult.addMessage("Preference Ind error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Preference Ind error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private boolean compareDerivationExpr(ST source, ST target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getText(), target.getText())
                    && Objects.equals(source.getLanguage(), target.getLanguage())
                    && Objects.equals(source.getMediaType(),target.getMediaType()))) {
                comparisonResult.addMessage("Derivation Expr error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Derivation Expr error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private boolean compareRepeatNumber(IVL_INT source, IVL_INT target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getCenter().getValue(), target.getCenter().getValue())
                    && Objects.equals(source.getHigh().getValue(), target.getHigh().getValue())
                    && Objects.equals(source.getLow().getValue(),target.getLow().getValue())
                    && Objects.equals(source.getWidth().getValue(),target.getWidth().getValue())
                    && Objects.equals(source.getValue(),target.getValue())
                    && Objects.equals(source.getNullFlavor().getLiteral(),target.getNullFlavor().getLiteral()))) {
                comparisonResult.addMessage("Repeat Number error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Repeat Number error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private boolean compareValues(EList<ANY> source, EList<ANY> target, String errorMessage) {
        return true;
    }

    private boolean compareValue(ANY source, ANY target, String errorMessage) {
        return true;
    }

    private boolean compareCodes(EList<CE> source, EList<CE> target, String errorMessage) {
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

    private boolean compareCodesCD(EList<CD> source, EList<CD> target, String errorMessage) {
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

    private boolean compareTargetSiteCode(EList<CD> source, EList<CD> target, String errorMessage) {
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

    private boolean compareDose(IVL_PQ source, IVL_PQ target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getCenter().getValue(), target.getCenter().getValue())
                    && Objects.equals(source.getHigh().getValue(), target.getHigh().getValue())
                    && Objects.equals(source.getLow().getValue(),target.getLow().getValue())
                    && Objects.equals(source.getWidth().getValue(),target.getWidth().getValue())
                    && Objects.equals(source.getValue(),target.getValue())
                    && Objects.equals(source.getNullFlavor().getLiteral(),target.getNullFlavor().getLiteral()))) {
                comparisonResult.addMessage("Repeat Number error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Repeat Number error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private boolean compareMaxDose(RTO_PQ_PQ source, RTO_PQ_PQ target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getDenominator().getValue(), target.getDenominator().getValue())
                    && Objects.equals(source.getNumerator().getValue(), target.getNumerator().getValue())
                    && Objects.equals(source.getNullFlavor().getLiteral(),target.getNullFlavor().getLiteral())
                    && Objects.equals(source.toString(),target.toString()))) {
                comparisonResult.addMessage("Repeat Number error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Repeat Number error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private boolean compareIDAttribute(String source, String target, String errorMessage) {
        if (source != null && target != null) {
            if (!(source.equals(target))) {
                comparisonResult.addMessage("Repeat Number error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Repeat Number error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private boolean compareQuantity(PQ source, PQ target, String errorMessage) {
        if (source != null && target != null) {
            if (Objects.equals(source.getValue(),target.getValue())
                    && Objects.equals(source.getUnit(),target.getUnit())
                    && Objects.equals(source.getNullFlavor().getLiteral(),target.getNullFlavor().getLiteral())) {
                comparisonResult.addMessage("Repeat Number error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Repeat Number error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private boolean compareQuantities(EList<PQ> source, EList<PQ> target, String errorMessage) {

        Boolean errorExists = false;
        for (int i=0; i<source.size(); i++) {
            int targetMatches = 0;
            for (int j=0; j<target.size(); j++) {
                if (Objects.equals(source.get(i).getValue(),target.get(j).getValue())
                        && Objects.equals(source.get(i).getUnit(),target.get(j).getUnit())
                        && Objects.equals(source.get(i).getNullFlavor().getLiteral(),target.get(j).getNullFlavor().getLiteral()))
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

    private boolean compareExpectedUseTime(IVL_TS source, IVL_TS target, String errorMessage) {
        if (source != null && target != null) {
            if (!(Objects.equals(source.getCenter().getValue(), target.getCenter().getValue())
                    && Objects.equals(source.getHigh().getValue(), target.getHigh().getValue())
                    && Objects.equals(source.getLow().getValue(),target.getLow().getValue())
                    && Objects.equals(source.getWidth().getValue(),target.getWidth().getValue())
                    && Objects.equals(source.getValue(),target.getValue())
                    && Objects.equals(source.getNullFlavor().getLiteral(),target.getNullFlavor().getLiteral()))) {
                comparisonResult.addMessage("Repeat Number error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
            comparisonResult.addMessage("Repeat Number error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

}
