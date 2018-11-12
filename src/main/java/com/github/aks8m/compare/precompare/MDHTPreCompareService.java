package com.github.aks8m.compare.precompare;

import com.github.aks8m.compare.Comparison;
import com.github.aks8m.compare.engine.ComparisonUtility;
import com.github.aks8m.report.ComparisonLocation;
import com.github.aks8m.report.result.Result;
import com.github.aks8m.report.result.ResultType;
import javafx.concurrent.Task;
import org.eclipse.emf.common.util.EList;
import org.openhealthtools.mdht.uml.cda.*;
import org.openhealthtools.mdht.uml.hl7.datatypes.*;
import org.openhealthtools.mdht.uml.hl7.vocab.*;

import java.io.ObjectStreamField;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class MDHTPreCompareService extends PreCompareService {

    private ClinicalDocument sourceClinicalDocument;
    private ClinicalDocument targetClinicalDocument;
    private List<Comparison> comparisons = new ArrayList<>();
    private ComparisonLocation currentLocation = new ComparisonLocation();

    public MDHTPreCompareService(ClinicalDocument sourceClinicalDocument, ClinicalDocument targetClinicalDocument) {
        this.sourceClinicalDocument = sourceClinicalDocument;
        this.targetClinicalDocument = targetClinicalDocument;
    }



    @Override
    protected Task<List<Comparison>> createTask() {

        return new Task<List<Comparison>>() {

            @Override
            protected List<Comparison> call() throws Exception {

                currentLocation.enter("Clinical Document");

                //compare RealmCode
                compareRealmCodes(sourceClinicalDocument.getRealmCodes(),targetClinicalDocument.getRealmCodes());

                //compare typeID
                typeIDComparison(sourceClinicalDocument.getTypeId(), targetClinicalDocument.getTypeId());

                //compare templateID
                compareTemplateID(sourceClinicalDocument.getTemplateIds(), targetClinicalDocument.getTemplateIds());

                //compareID
                compareID(sourceClinicalDocument.getId(),targetClinicalDocument.getId());

                //compare code
                compareCode(sourceClinicalDocument.getCode(),targetClinicalDocument.getCode());

                //compare title
                compareTitle(sourceClinicalDocument.getTitle(),targetClinicalDocument.getTitle());

                //compare Effective Time
                compareEffectiveTime(sourceClinicalDocument.getEffectiveTime(),targetClinicalDocument.getEffectiveTime());

                //compare confidentiality code
                compareConfidentialityCode(sourceClinicalDocument.getConfidentialityCode(),targetClinicalDocument.getConfidentialityCode());

                //compare language code
                compareLanguageCode(sourceClinicalDocument.getLanguageCode(),targetClinicalDocument.getLanguageCode());

                //compare setID
                compareSetID(sourceClinicalDocument.getSetId(),targetClinicalDocument.getSetId());

                //compare versionNumber
                compareVersionNumber(sourceClinicalDocument.getVersionNumber(),targetClinicalDocument.getVersionNumber());

                //compare copyTime
                compareCopyTime(sourceClinicalDocument.getCopyTime(),targetClinicalDocument.getCopyTime());

                //compare Record Targets
                recordTargetsComparison(sourceClinicalDocument.getRecordTargets(), targetClinicalDocument.getRecordTargets());

                //compare Authors
                authorsComparison(sourceClinicalDocument.getAuthors(), targetClinicalDocument.getAuthors());

                //compare Data Enterer
                dataEntererComparison(sourceClinicalDocument.getDataEnterer(), targetClinicalDocument.getDataEnterer());

                //compare informants
                informantsComparison(sourceClinicalDocument.getInformants(), targetClinicalDocument.getInformants());

                //compare custodian
                custodianComparison(sourceClinicalDocument.getCustodian(), targetClinicalDocument.getCustodian());

                //compare information recipient
                informationRecipientComparison(sourceClinicalDocument.getInformationRecipients(),targetClinicalDocument.getInformationRecipients());

                //compare Legal Authenticator
                legalAuthenticatorComparison(sourceClinicalDocument.getLegalAuthenticator(), targetClinicalDocument.getLegalAuthenticator());

                //compare Authenticators
                authenticatorComparison(sourceClinicalDocument.getAuthenticators(),targetClinicalDocument.getAuthenticators());

                //compare participants
                participants1Comparison(sourceClinicalDocument.getParticipants(),targetClinicalDocument.getParticipants());

                //compare getInFulfullmentOf
                inFulfillmentOfComparison(sourceClinicalDocument.getInFulfillmentOfs(),targetClinicalDocument.getInFulfillmentOfs());

                //compare documentationOf
                documentationOfsComparison(sourceClinicalDocument.getDocumentationOfs(), targetClinicalDocument.getDocumentationOfs());

                //compare getRelatedDocuments
                relatedDocumentsComparison(sourceClinicalDocument.getRelatedDocuments(),targetClinicalDocument.getRelatedDocuments());

                //compare Authorizations
                authorizationsComparison(sourceClinicalDocument.getAuthorizations(),targetClinicalDocument.getAuthorizations());

                //compare componentOf
                componentOfComparison(sourceClinicalDocument.getComponentOf(),targetClinicalDocument.getComponentOf());

                //compare component
                component2Comparison(sourceClinicalDocument.getComponent(), targetClinicalDocument.getComponent());

                //compare nullFlavor
                compareNullFlavor(sourceClinicalDocument.getNullFlavor(),targetClinicalDocument.getNullFlavor());

                //compare classCode
                compareClassCode(sourceClinicalDocument.getClassCode(),targetClinicalDocument.getClassCode());

                currentLocation.exit();

                return comparisons;
            }
        };
    }
//
//    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    //complex result comparison Methods
//    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void typeIDComparison(InfrastructureRootTypeId sourceTypeID, InfrastructureRootTypeId targetTypeID) {
        currentLocation.enter("Type ID");
        if (sourceTypeID != null && targetTypeID != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), sourceTypeID.getRoot(), targetTypeID.getRoot()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), sourceTypeID.getAssigningAuthorityName(), targetTypeID.getAssigningAuthorityName()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), sourceTypeID.getExtension(), targetTypeID.getExtension()));
        }
        currentLocation.exit();
    }

    private void dataEntererComparison(DataEnterer sourceDataEnterer, DataEnterer targetDataEnterer) {
        currentLocation.enter("Data Enterer");
        if (sourceDataEnterer != null && targetDataEnterer != null) {
            //compare realm codes
            compareRealmCodes(sourceDataEnterer.getRealmCodes(),targetDataEnterer.getRealmCodes());
            //compare typeID
            typeIDComparison(sourceDataEnterer.getTypeId(),targetDataEnterer.getTypeId());
            //compare templateID
            compareTemplateID(sourceDataEnterer.getTemplateIds(), targetDataEnterer.getTemplateIds());
            //compare time
            compareTime(sourceDataEnterer.getTime(),targetDataEnterer.getTime());
            //compare assigned entity
            assignedEntityComparison(sourceDataEnterer.getAssignedEntity(),targetDataEnterer.getAssignedEntity());
            //compare nullFlavor
            compareNullFlavor(sourceDataEnterer.getNullFlavor(),targetDataEnterer.getNullFlavor());
            //compare typeCode
            compareTypeCode(sourceDataEnterer.getTypeCode(),targetDataEnterer.getTypeCode());
        }
        currentLocation.exit();
    }

    private void custodianComparison(Custodian sourceCustodian, Custodian targetCustodian) {
        currentLocation.enter("Custodian");
        if (sourceCustodian != null && targetCustodian != null) {
            //realm code
            compareRealmCodes(sourceCustodian.getRealmCodes(),targetCustodian.getRealmCodes());
            //typeID
            typeIDComparison(sourceCustodian.getTypeId(),targetCustodian.getTypeId());
            //templateID
            compareTemplateID(sourceCustodian.getTemplateIds(), targetCustodian.getTemplateIds());
            //AssignedCustodian
            assignedCustodianComparison(sourceCustodian.getAssignedCustodian(),targetCustodian.getAssignedCustodian());
            //nullFlavor
            compareNullFlavor(sourceCustodian.getNullFlavor(),targetCustodian.getNullFlavor());
            //typeCode
            compareTypeCode(sourceCustodian.getTypeCode(),targetCustodian.getTypeCode());
        }
        currentLocation.exit();
    }

    private void legalAuthenticatorComparison(LegalAuthenticator sourceLegalAuthenticator, LegalAuthenticator targetLegalAuthenticator) {
        currentLocation.enter("Legal Authenticator");
        if (sourceLegalAuthenticator != null && targetLegalAuthenticator != null) {
            //realmCode
            compareRealmCodes(sourceLegalAuthenticator.getRealmCodes(),targetLegalAuthenticator.getRealmCodes());
            //compare typeID
            typeIDComparison(sourceLegalAuthenticator.getTypeId(),targetLegalAuthenticator.getTypeId());
            //compare templateID
            compareTemplateID(sourceLegalAuthenticator.getTemplateIds(), targetLegalAuthenticator.getTemplateIds());
            //compare time
            compareTime(sourceLegalAuthenticator.getTime(),targetLegalAuthenticator.getTime());
            //compare signatureCode
            compareSignatureCode(sourceLegalAuthenticator.getSignatureCode(),targetLegalAuthenticator.getSignatureCode());
            //compare assignedEntity
            assignedEntityComparison(sourceLegalAuthenticator.getAssignedEntity(),targetLegalAuthenticator.getAssignedEntity());
            //compare nullFlavor
            compareNullFlavor(sourceLegalAuthenticator.getNullFlavor(),targetLegalAuthenticator.getNullFlavor());
            //compare typeCode
            compareTypeCode(sourceLegalAuthenticator.getTypeCode(),targetLegalAuthenticator.getTypeCode());
            //compare contextControlCode
            compareContextControlCode(sourceLegalAuthenticator.getContextControlCode(),targetLegalAuthenticator.getContextControlCode());
        }
        currentLocation.exit();
    }

    private void component2Comparison(Component2 sourceComponent, Component2 targetComponent) {
        currentLocation.enter("Component2");
        if (sourceComponent != null && targetComponent != null) {
            //realmCode
            compareRealmCodes(sourceComponent.getRealmCodes(),targetComponent.getRealmCodes());
            //typeID
            typeIDComparison(sourceComponent.getTypeId(),targetComponent.getTypeId());
            //templateID
            compareTemplateID(sourceComponent.getTemplateIds(), targetComponent.getTemplateIds());
            //Choice - Non XML Body
            nonXMLBodyComparison(sourceComponent.getNonXMLBody(), targetComponent.getNonXMLBody());
            //Choice - StructuredBody
            structuredBodyComparison(sourceComponent.getStructuredBody(), targetComponent.getStructuredBody());
            //nullFlavor
            compareNullFlavor(sourceComponent.getNullFlavor(),targetComponent.getNullFlavor());
            //typeCode
            compareTypeCode(sourceComponent.getTypeCode(),targetComponent.getTypeCode());
            //contextConductionInd
            compareContextConductionInd(sourceComponent.getContextConductionInd(),targetComponent.getContextConductionInd());
        }
        currentLocation.exit();
    }

    private void patientRoleComparison(PatientRole sourcePatientRole, PatientRole targetPatientRole) {
        currentLocation.enter("Patient Role");
        if (sourcePatientRole != null && targetPatientRole != null) {
            //compare realmCode
            compareRealmCodes(sourcePatientRole.getRealmCodes(),targetPatientRole.getRealmCodes());
            //compare typeID
            typeIDComparison(sourcePatientRole.getTypeId(),targetPatientRole.getTypeId());
            //compare templateID
            compareTemplateID(sourcePatientRole.getTemplateIds(), targetPatientRole.getTemplateIds());
            //compare id
            compareIDs(sourcePatientRole.getIds(),targetPatientRole.getIds());
            //compare addr
            compareAddr(sourcePatientRole.getAddrs(),targetPatientRole.getAddrs());
            //compare telecom
            compareTelcom(sourcePatientRole.getTelecoms(),targetPatientRole.getTelecoms());
            //compare Patient
            patientComparison(sourcePatientRole.getPatient(), targetPatientRole.getPatient());
            //compareNullFlavor
            compareNullFlavor(sourcePatientRole.getNullFlavor(),targetPatientRole.getNullFlavor());
            //compare ClassCode
            compareClassCode(sourcePatientRole.getClassCode(),targetPatientRole.getClassCode());
        }
        currentLocation.exit();
    }


    private void assignedAuthorComparison(AssignedAuthor sourceAssignedAuthor, AssignedAuthor targetAssignedAuthor) {
        currentLocation.enter("Assigned Author");
        if (sourceAssignedAuthor != null && targetAssignedAuthor != null) {
            //realm Code
            compareRealmCodes(sourceAssignedAuthor.getRealmCodes(),targetAssignedAuthor.getRealmCodes());
            //result ID
            typeIDComparison(sourceAssignedAuthor.getTypeId(),targetAssignedAuthor.getTypeId());
            //templateID
            compareTemplateID(sourceAssignedAuthor.getTemplateIds(), targetAssignedAuthor.getTemplateIds());
            //ids
            compareIDs(sourceAssignedAuthor.getIds(),targetAssignedAuthor.getIds());
            //code
            compareCode(sourceAssignedAuthor.getCode(),targetAssignedAuthor.getCode());
            //addr
            compareAddr(sourceAssignedAuthor.getAddrs(),targetAssignedAuthor.getAddrs());
            //telecom
            compareTelcom(sourceAssignedAuthor.getTelecoms(),targetAssignedAuthor.getTelecoms());
            //choice - assignedPerson (Person)
            personComparison(sourceAssignedAuthor.getAssignedPerson(), targetAssignedAuthor.getAssignedPerson());
            //choice - assignedAuthorizing Device(Authorizing Device)
            authorizingDeviceComparison(sourceAssignedAuthor.getAssignedAuthoringDevice(), targetAssignedAuthor.getAssignedAuthoringDevice());
            //represented Organization
            organizationComparison(sourceAssignedAuthor.getRepresentedOrganization(),targetAssignedAuthor.getRepresentedOrganization());
            //null flavor
            compareNullFlavor(sourceAssignedAuthor.getNullFlavor(),targetAssignedAuthor.getNullFlavor());
            //class code
            compareClassCode(sourceAssignedAuthor.getClassCode(),targetAssignedAuthor.getClassCode());
        }
        currentLocation.exit();
    }

    private void patientComparison(Patient sourcePatient, Patient targetPatient) {
        currentLocation.enter("Patient");
        if (sourcePatient != null && targetPatient != null) {
            //realm code
            compareRealmCodes(sourcePatient.getRealmCodes(),targetPatient.getRealmCodes());
            //typeID
            typeIDComparison(sourcePatient.getTypeId(),targetPatient.getTypeId());
            //templateID
            compareTemplateID(sourcePatient.getTemplateIds(),targetPatient.getTemplateIds());
            //ids
            compareIDs(sourcePatient.getIds(),targetPatient.getIds());
            //names
            compareNamesPN(sourcePatient.getNames(),targetPatient.getNames());
            //administrativeGenderCode
            compareAdministrativeGenderCode(sourcePatient.getAdministrativeGenderCode(),targetPatient.getAdministrativeGenderCode());
            //birthTime
            compareBirthTime(sourcePatient.getBirthTime(),targetPatient.getBirthTime());
            //marital status code
            compareMaritalStatusCode(sourcePatient.getMaritalStatusCode(),targetPatient.getMaritalStatusCode());
            //religious affiliation code
            compareReligiosAffiliationCode(sourcePatient.getReligiousAffiliationCode(),targetPatient.getReligiousAffiliationCode());
            //race Code
            compareRaceCode(sourcePatient.getRaceCode(),targetPatient.getRaceCode());
            //ethnic group code
            compareEthnicGroupCode(sourcePatient.getEthnicGroupCode(),targetPatient.getEthnicGroupCode());
            //guardian - Guardian
            guardiansComparison(sourcePatient.getGuardians(),targetPatient.getGuardians());
            //birthPlace - Birthplace
            birthplaceComparison(sourcePatient.getBirthplace(),targetPatient.getBirthplace());
            //Language Communication - Language Communication
            languageCommunicationsComparison(sourcePatient.getLanguageCommunications(),targetPatient.getLanguageCommunications());
            //nullFlavor
            compareNullFlavor(sourcePatient.getNullFlavor(),targetPatient.getNullFlavor());
            //classCode
            compareClassCode(sourcePatient.getClassCode(),targetPatient.getClassCode());
        }
        currentLocation.exit();
    }

    private void assignedEntityComparison(AssignedEntity sourceAssignedEntity, AssignedEntity targetAssignedEntity) {
        currentLocation.enter("Assigned Entity");
        if (sourceAssignedEntity != null && targetAssignedEntity != null) {
            //realm code
            compareRealmCodes(sourceAssignedEntity.getRealmCodes(),targetAssignedEntity.getRealmCodes());
            //typeID
            typeIDComparison(sourceAssignedEntity.getTypeId(),targetAssignedEntity.getTypeId());
            //ids
            compareIDs(sourceAssignedEntity.getIds(),targetAssignedEntity.getIds());
            //code
            compareCode(sourceAssignedEntity.getCode(),targetAssignedEntity.getCode());
            //addr
            compareAddr(sourceAssignedEntity.getAddrs(),targetAssignedEntity.getAddrs());
            //telecom
            compareTelcom(sourceAssignedEntity.getTelecoms(),targetAssignedEntity.getTelecoms());
            //assignedPerson - Person
            personComparison(sourceAssignedEntity.getAssignedPerson(),targetAssignedEntity.getAssignedPerson());
            //representedOrganiztions - Organization
            organizationsComparison(sourceAssignedEntity.getRepresentedOrganizations(),targetAssignedEntity.getRepresentedOrganizations());
            //nullFlavor
            compareNullFlavor(sourceAssignedEntity.getNullFlavor(),targetAssignedEntity.getNullFlavor());
            //classCode
            compareClassCode(sourceAssignedEntity.getClassCode(),targetAssignedEntity.getClassCode());
        }
        currentLocation.exit();
    }

    private void assignedCustodianComparison(AssignedCustodian sourceAssignedCustodian, AssignedCustodian targetAssignedCustodian) {
        currentLocation.enter("Assigned Custodian");
        if (sourceAssignedCustodian != null && targetAssignedCustodian != null) {
            //realm code
            compareRealmCodes(sourceAssignedCustodian.getRealmCodes(),targetAssignedCustodian.getRealmCodes());
            //typeID
            typeIDComparison(sourceAssignedCustodian.getTypeId(),targetAssignedCustodian.getTypeId());
            //templateID
            compareTemplateID(sourceAssignedCustodian.getTemplateIds(),targetAssignedCustodian.getTemplateIds());
            //representedCustodianOrganization - CustodianOrganization
            custodianOrganizationComparison(sourceAssignedCustodian.getRepresentedCustodianOrganization(),targetAssignedCustodian.getRepresentedCustodianOrganization());
            //nullFlavor
            compareNullFlavor(sourceAssignedCustodian.getNullFlavor(),targetAssignedCustodian.getNullFlavor());
            //classCode
            compareClassCode(sourceAssignedCustodian.getClassCode(),targetAssignedCustodian.getClassCode());
        }
        currentLocation.exit();
    }

    private void associatedEntityComparison(AssociatedEntity sourceAssignedEntity, AssociatedEntity targetAssignedEntity) {
        currentLocation.enter("Assigned Entity");
        if (sourceAssignedEntity != null && targetAssignedEntity != null) {
            //realm code
            compareRealmCodes(sourceAssignedEntity.getRealmCodes(),targetAssignedEntity.getRealmCodes());
            //typeID
            typeIDComparison(sourceAssignedEntity.getTypeId(),targetAssignedEntity.getTypeId());
            //template ID
            compareTemplateID(sourceAssignedEntity.getTemplateIds(),targetAssignedEntity.getTemplateIds());
            //id
            compareIDs(sourceAssignedEntity.getTemplateIds(),targetAssignedEntity.getTemplateIds());
            //code
            compareCode(sourceAssignedEntity.getCode(),targetAssignedEntity.getCode());
            //addr
            compareAddr(sourceAssignedEntity.getAddrs(),targetAssignedEntity.getAddrs());
            //telecom
            compareTelcom(sourceAssignedEntity.getTelecoms(),targetAssignedEntity.getTelecoms());
            //associatedPerson - Person
            personComparison(sourceAssignedEntity.getAssociatedPerson(),targetAssignedEntity.getAssociatedPerson());
            //scopingOrganization - Organization
            organizationComparison(sourceAssignedEntity.getScopingOrganization(),targetAssignedEntity.getScopingOrganization());
            //nullFlavor
            compareNullFlavor(sourceAssignedEntity.getNullFlavor(),targetAssignedEntity.getNullFlavor());
            //classCode
            compareClassCode(sourceAssignedEntity.getClassCode(),targetAssignedEntity.getClassCode());
        }
        currentLocation.exit();
    }

    private void serviceEventComparison(ServiceEvent sourceServiceEvent, ServiceEvent targetServiceEvent) {
        currentLocation.enter("Service Event");
        if (sourceServiceEvent != null && targetServiceEvent != null) {
            //realmCode
            compareRealmCodes(sourceServiceEvent.getRealmCodes(),targetServiceEvent.getRealmCodes());
            //typeID
            typeIDComparison(sourceServiceEvent.getTypeId(),targetServiceEvent.getTypeId());
            //templateID
            compareTemplateID(sourceServiceEvent.getTemplateIds(),targetServiceEvent.getTemplateIds());
            //id
            compareIDs(sourceServiceEvent.getIds(),targetServiceEvent.getIds());
            //code
            compareCode(sourceServiceEvent.getCode(),targetServiceEvent.getCode());
            //effective Time
            compareEffectiveTime(sourceServiceEvent.getEffectiveTime(),targetServiceEvent.getEffectiveTime());
            //performer - performer1
            performersComparison(sourceServiceEvent.getPerformers(),targetServiceEvent.getPerformers());
            //null flavor
            compareNullFlavor(sourceServiceEvent.getNullFlavor(),targetServiceEvent.getNullFlavor());
            //class code
            compareClassCode(sourceServiceEvent.getClassCode(),targetServiceEvent.getClassCode());
            //moodCode
            compareMoodCode(sourceServiceEvent.getMoodCode(),targetServiceEvent.getMoodCode());
        }
        currentLocation.exit();
    }

    private void nonXMLBodyComparison(NonXMLBody sourceNonXMLBody, NonXMLBody targetNonXMLBody) {
        currentLocation.enter("Non XML Body");
        if (sourceNonXMLBody != null && targetNonXMLBody != null) {
            //realmCode
            compareRealmCodes(sourceNonXMLBody.getRealmCodes(),targetNonXMLBody.getRealmCodes());
            //typeID
            typeIDComparison(sourceNonXMLBody.getTypeId(),targetNonXMLBody.getTypeId());
            //templateID
            compareTemplateID(sourceNonXMLBody.getTemplateIds(),targetNonXMLBody.getTemplateIds());
            //text
            compareText(sourceNonXMLBody.getText(),targetNonXMLBody.getText());
            //confidentialityCode
            compareConfidentialityCode(sourceNonXMLBody.getConfidentialityCode(),targetNonXMLBody.getConfidentialityCode());
            //langaugeCode
            compareLanguageCode(sourceNonXMLBody.getLanguageCode(),targetNonXMLBody.getLanguageCode());
            //null flavor
            compareNullFlavor(sourceNonXMLBody.getNullFlavor(),targetNonXMLBody.getNullFlavor());
            //classCode
            compareClassCode(sourceNonXMLBody.getClassCode(),targetNonXMLBody.getClassCode());
            //moodCode
            compareMoodCode(sourceNonXMLBody.getMoodCode(),targetNonXMLBody.getMoodCode());
        }
        currentLocation.exit();
    }

    private void structuredBodyComparison(StructuredBody sourceStructuredBody, StructuredBody targetStructuredBody) {
        currentLocation.enter("Structured Body");
        if (sourceStructuredBody != null && targetStructuredBody != null) {
            //realmCode
            compareRealmCodes(sourceStructuredBody.getRealmCodes(),targetStructuredBody.getRealmCodes());
            //typeID
            typeIDComparison(sourceStructuredBody.getTypeId(),targetStructuredBody.getTypeId());
            //templateID
            compareTemplateID(sourceStructuredBody.getTemplateIds(),targetStructuredBody.getTemplateIds());
            //confidentiality Code
            compareConfidentialityCode(sourceStructuredBody.getConfidentialityCode(),targetStructuredBody.getConfidentialityCode());
            //language Code
            compareLanguageCode(sourceStructuredBody.getLanguageCode(),targetStructuredBody.getLanguageCode());
            //component - Component 3
            component3Comparison(sourceStructuredBody.getComponents(),targetStructuredBody.getComponents());
            //null Flavor
            compareNullFlavor(sourceStructuredBody.getNullFlavor(),targetStructuredBody.getNullFlavor());
            //class Code
            compareClassCode(sourceStructuredBody.getClassCode(),targetStructuredBody.getClassCode());
            //moodCode
            compareMoodCode(sourceStructuredBody.getMoodCode(),targetStructuredBody.getMoodCode());
        }
        currentLocation.exit();
    }

    private void relatedEntityComparison(RelatedEntity sourceRelatedEntity, RelatedEntity targetRelatedEntity) {
        currentLocation.enter("Related Entity");
        if (sourceRelatedEntity != null && targetRelatedEntity != null) {
            //realmCode
            compareRealmCodes(sourceRelatedEntity.getRealmCodes(),targetRelatedEntity.getRealmCodes());
            //typeID
            typeIDComparison(sourceRelatedEntity.getTypeId(),targetRelatedEntity.getTypeId());
            //templateID
            compareTemplateID(sourceRelatedEntity.getTemplateIds(),targetRelatedEntity.getTemplateIds());
            //code
            compareCode(sourceRelatedEntity.getCode(),targetRelatedEntity.getCode());
            //addr
            compareAddr(sourceRelatedEntity.getAddrs(),targetRelatedEntity.getAddrs());
            //telecom
            compareTelcom(sourceRelatedEntity.getTelecoms(),targetRelatedEntity.getTelecoms());
            //affectiveTime
            compareEffectiveTime(sourceRelatedEntity.getEffectiveTime(),targetRelatedEntity.getEffectiveTime());
            //relatedPerson - Person
            personComparison(sourceRelatedEntity.getRelatedPerson(),targetRelatedEntity.getRelatedPerson());
            //nullFlavor
            compareNullFlavor(sourceRelatedEntity.getNullFlavor(),targetRelatedEntity.getNullFlavor());
            //classCode
            compareClassCode(sourceRelatedEntity.getClassCode(),targetRelatedEntity.getClassCode());
        }
        currentLocation.exit();
    }

    private void organizationComparison(Organization sourceOrganization, Organization targetOrganization) {
        currentLocation.enter("Organization");
        if (sourceOrganization != null && targetOrganization != null) {
            //realmCode
            compareRealmCodes(sourceOrganization.getRealmCodes(),targetOrganization.getRealmCodes());
            //typeID
            typeIDComparison(sourceOrganization.getTypeId(),targetOrganization.getTypeId());
            //templatedID
            compareTemplateID(sourceOrganization.getTemplateIds(),targetOrganization.getTemplateIds());
            //ids
            compareIDs(sourceOrganization.getIds(),targetOrganization.getIds());
            //name
            compareNamesON(sourceOrganization.getNames(),targetOrganization.getNames());
            //telecom
            compareTelcom(sourceOrganization.getTelecoms(),targetOrganization.getTelecoms());
            //addr
            compareAddr(sourceOrganization.getAddrs(),targetOrganization.getAddrs());
            //standardIndustryClassCode
            compareCode(sourceOrganization.getStandardIndustryClassCode(),targetOrganization.getStandardIndustryClassCode());
            //asOrganizationPartOf - OrganizationPartOf
            organizationPartOfComparison(sourceOrganization.getAsOrganizationPartOf(),targetOrganization.getAsOrganizationPartOf());
            //nullFlavor
            compareNullFlavor(sourceOrganization.getNullFlavor(),targetOrganization.getNullFlavor());
            //classCode
            compareClassCode(sourceOrganization.getClassCode(),targetOrganization.getClassCode());
            //determinerCode
            compareDeterminerCode(sourceOrganization.getDeterminerCode(),targetOrganization.getDeterminerCode());
        }
        currentLocation.exit();
    }

    private void personComparison(Person sourcePerson, Person targetPerson) {
        currentLocation.enter("Person");
        if (sourcePerson != null && targetPerson != null) {
            //realm code
            compareRealmCodes(sourcePerson.getRealmCodes(),targetPerson.getRealmCodes());
            //typeID
            typeIDComparison(sourcePerson.getTypeId(),targetPerson.getTypeId());
            //templateID
            compareTemplateID(sourcePerson.getTemplateIds(),targetPerson.getTemplateIds());
            //name
            compareNamesPN(sourcePerson.getNames(),targetPerson.getNames());
            //nullFlavor
            compareNullFlavor(sourcePerson.getNullFlavor(),targetPerson.getNullFlavor());
            //classCode
            compareClassCode(sourcePerson.getClassCode(),targetPerson.getClassCode());
        }
        currentLocation.exit();
    }

    private void authorizingDeviceComparison(AuthoringDevice sourceAuthorizingDevice, AuthoringDevice targetAuthorizingDevice) {
        currentLocation.enter("Authorizing Device");
        if (sourceAuthorizingDevice != null && targetAuthorizingDevice != null) {
            //realmCode
            compareRealmCodes(sourceAuthorizingDevice.getRealmCodes(),targetAuthorizingDevice.getRealmCodes());
            //result Id
            typeIDComparison(sourceAuthorizingDevice.getTypeId(),targetAuthorizingDevice.getTypeId());
            //template ID
            compareTemplateID(sourceAuthorizingDevice.getTemplateIds(),targetAuthorizingDevice.getTemplateIds());
            //code
            compareCode(sourceAuthorizingDevice.getCode(),targetAuthorizingDevice.getCode());
            //manufacturedModelName
            compareSCName(sourceAuthorizingDevice.getManufacturerModelName(),targetAuthorizingDevice.getManufacturerModelName());
            //softwareName
            compareSCName(sourceAuthorizingDevice.getSoftwareName(),targetAuthorizingDevice.getSoftwareName());
            //asMaintainedEntity - MaintainedEntity
            maintainedEntityComparison(sourceAuthorizingDevice.getAsMaintainedEntities(),targetAuthorizingDevice.getAsMaintainedEntities());
            //nullFlavor
            compareNullFlavor(sourceAuthorizingDevice.getNullFlavor(),targetAuthorizingDevice.getNullFlavor());
            //classCode
            compareClassCode(sourceAuthorizingDevice.getClassCode(),targetAuthorizingDevice.getClassCode());
            //determinerCode
            compareDeterminerCode(sourceAuthorizingDevice.getDeterminerCode(),targetAuthorizingDevice.getDeterminerCode());
        }
        currentLocation.exit();
    }

    private void informantsComparison(EList<Informant12> sourceInformant12, EList<Informant12> targetInformant12) {
        currentLocation.enter("Informants");
        for (int i=0; i<sourceInformant12.size(); i++) {
            for (int j=0; j<targetInformant12.size(); j++) {
                if (sourceInformant12.get(i) != null && targetInformant12.get(j) != null) {
                    //realmCode
                    compareRealmCodes(sourceInformant12.get(i).getRealmCodes(), targetInformant12.get(j).getRealmCodes());
                    //typeID
                    typeIDComparison(sourceInformant12.get(i).getTypeId(), targetInformant12.get(j).getTypeId());
                    //template ID
                    compareTemplateID(sourceInformant12.get(i).getTemplateIds(), targetInformant12.get(j).getTemplateIds());
                    //choice - assignedEntity
                    assignedEntityComparison(sourceInformant12.get(i).getAssignedEntity(), targetInformant12.get(j).getAssignedEntity());
                    //choice - related entity
                    relatedEntityComparison(sourceInformant12.get(i).getRelatedEntity(), targetInformant12.get(j).getRelatedEntity());
                    //nullflavor
                    compareNullFlavor(sourceInformant12.get(i).getNullFlavor(), targetInformant12.get(j).getNullFlavor());
                    //result code
                    compareTypeCode(sourceInformant12.get(i).getTypeCode(), targetInformant12.get(j).getTypeCode());
                    //contextControlCode
                    compareContextControlCode(sourceInformant12.get(i).getContextControlCode(), targetInformant12.get(j).getContextControlCode());
                }
            }
        }
        currentLocation.exit();
    }

    private void recordTargetsComparison(EList<RecordTarget> sourceRecordTargets, EList<RecordTarget> targetRecordTargets) {
        currentLocation.enter("Record Targets");
        for (int i = 0; i < sourceRecordTargets.size(); i++) {
            for (int j = 0; j < targetRecordTargets.size(); j++) {
                //compare realmCode
                compareRealmCodes(sourceRecordTargets.get(i).getRealmCodes(), targetRecordTargets.get(j).getRealmCodes());
                //compare TypeID
                typeIDComparison(sourceRecordTargets.get(i).getTypeId(), targetRecordTargets.get(j).getTypeId());
                //compareTemplateID
                compareTemplateID(sourceRecordTargets.get(i).getTemplateIds(), targetRecordTargets.get(j).getTemplateIds());
                //comparePatientRole
                patientRoleComparison(sourceRecordTargets.get(i).getPatientRole(), targetRecordTargets.get(j).getPatientRole());
                //compare nullFlavor
                compareNullFlavor(sourceRecordTargets.get(i).getNullFlavor(), targetRecordTargets.get(j).getNullFlavor());
                //compare typeCode
                compareTypeCode(sourceRecordTargets.get(i).getTypeCode(), targetRecordTargets.get(j).getTypeCode());
            }
        }
        currentLocation.exit();
    }


    private void authorsComparison(EList<Author> sourceAuthors, EList<Author> targetAuthors) {
        currentLocation.enter("Authors");
        for (int i=0; i<sourceAuthors.size(); i++) {
            for (int j = 0; j < targetAuthors.size(); j++) {
                //compare realmCodes
                compareRealmCodes(sourceAuthors.get(i).getRealmCodes(), targetAuthors.get(j).getRealmCodes());
                //compare typeID
                typeIDComparison(sourceAuthors.get(i).getTypeId(), targetAuthors.get(j).getTypeId());
                //compare template ID
                compareTemplateID(sourceAuthors.get(i).getTemplateIds(), targetAuthors.get(j).getTemplateIds());
                //compare functionCode
                compareFunctionCode(sourceAuthors.get(i).getFunctionCode(),targetAuthors.get(j).getFunctionCode());
                //compare time
                compareTime(sourceAuthors.get(i).getTime(), targetAuthors.get(j).getTime());
                //compare AssignedAuthor
                assignedAuthorComparison(sourceAuthors.get(i).getAssignedAuthor(), targetAuthors.get(j).getAssignedAuthor());
                //compare nullFlavor
                compareNullFlavor(sourceAuthors.get(i).getNullFlavor(), targetAuthors.get(j).getNullFlavor());
                //compare typeCode
                compareTypeCode(sourceAuthors.get(i).getTypeCode(), targetAuthors.get(j).getTypeCode());
                //compare contextControlCode
                compareContextControlCode(sourceAuthors.get(i).getContextControlCode(), targetAuthors.get(j).getContextControlCode());
            }
        }
        currentLocation.exit();
    }

    private void participants1Comparison(EList<Participant1> sourceParticipant, EList<Participant1> targetParticipant) {
        currentLocation.enter("Patricipants1");
        for (int i=0;i<sourceParticipant.size();i++) {
            for (int j = 0; j < targetParticipant.size(); j++) {
                //realmCodes
                compareRealmCodes(sourceParticipant.get(i).getRealmCodes(), targetParticipant.get(j).getRealmCodes());
                //typeID
                typeIDComparison(sourceParticipant.get(i).getTypeId(), targetParticipant.get(j).getTypeId());
                //templateID
                compareTemplateID(sourceParticipant.get(i).getTemplateIds(), targetParticipant.get(j).getTemplateIds());
                //functionCode
                compareFunctionCode(sourceParticipant.get(i).getFunctionCode(), targetParticipant.get(j).getFunctionCode());
                //time
                compareTime(sourceParticipant.get(i).getTime(), targetParticipant.get(j).getTime());
                //associated Entity
                associatedEntityComparison(sourceParticipant.get(i).getAssociatedEntity(), targetParticipant.get(j).getAssociatedEntity());
                //nullFlavor
                compareNullFlavor(sourceParticipant.get(i).getNullFlavor(), targetParticipant.get(j).getNullFlavor());
                //typeCode
                compareTypeCode(sourceParticipant.get(i).getTypeCode(), targetParticipant.get(j).getTypeCode());
            }
        }
        currentLocation.exit();
    }

    private void documentationOfsComparison(EList<DocumentationOf> sourceDocumentationOf, EList<DocumentationOf> targetDocumentationOf) {
        currentLocation.enter("DocumentationOfs");
        for (int i=0;i<sourceDocumentationOf.size();i++) {
            for (int j = 0; j < targetDocumentationOf.size(); j++) {
                //realmCodes
                compareRealmCodes(sourceDocumentationOf.get(i).getRealmCodes(), targetDocumentationOf.get(j).getRealmCodes());
                //compare TypeID
                typeIDComparison(sourceDocumentationOf.get(i).getTypeId(), targetDocumentationOf.get(j).getTypeId());
                //compare templateID
                compareTemplateID(sourceDocumentationOf.get(i).getTemplateIds(), targetDocumentationOf.get(j).getTemplateIds());
                //compare serviceEvent
                serviceEventComparison(sourceDocumentationOf.get(i).getServiceEvent(), targetDocumentationOf.get(j).getServiceEvent());
                //compare NullFlavor
                compareNullFlavor(sourceDocumentationOf.get(i).getNullFlavor(), targetDocumentationOf.get(j).getNullFlavor());
                //compare typeCode
                compareTypeCode(sourceDocumentationOf.get(i).getTypeCode(), targetDocumentationOf.get(j).getTypeCode());
            }
        }
        currentLocation.exit();
    }

    private void organizationsComparison(EList<Organization> sourceOrganization, EList<Organization> targetOrganization)  {
        currentLocation.enter("Organizations");
        for (int i=0;i<sourceOrganization.size();i++) {
            for (int j = 0; j < targetOrganization.size(); j++) {
                //compare RealmCodes
                compareRealmCodes(sourceOrganization.get(i).getRealmCodes(), targetOrganization.get(j).getRealmCodes());
                //compare TypeID
                typeIDComparison(sourceOrganization.get(i).getTypeId(), targetOrganization.get(j).getTypeId());
                //compare templateID
                compareTemplateID(sourceOrganization.get(i).getTemplateIds(), targetOrganization.get(j).getTemplateIds());
                //compare IDs
                compareIDs(sourceOrganization.get(i).getIds(), targetOrganization.get(j).getIds());
                //compare name
                compareNamesON(sourceOrganization.get(i).getNames(),targetOrganization.get(j).getNames());
                //compare Telecom
                compareTelcom(sourceOrganization.get(i).getTelecoms(),targetOrganization.get(j).getTelecoms());
                //compare Addr
                compareAddr(sourceOrganization.get(i).getAddrs(),targetOrganization.get(j).getAddrs());
                //compare StandardIndustryClassCode
                compareCode(sourceOrganization.get(i).getStandardIndustryClassCode(),targetOrganization.get(j).getStandardIndustryClassCode());
                //compare asOrganizationPartOf - OrganizationPartOf
                organizationPartOfComparison(sourceOrganization.get(i).getAsOrganizationPartOf(),targetOrganization.get(j).getAsOrganizationPartOf());
                //compare NullFlavor
                compareNullFlavor(sourceOrganization.get(i).getNullFlavor(), targetOrganization.get(j).getNullFlavor());
                //compare classCode
                compareClassCode(sourceOrganization.get(i).getClassCode(), targetOrganization.get(j).getClassCode());
                //compare Determiner Code
                compareDeterminerCode(sourceOrganization.get(i).getDeterminerCode(),targetOrganization.get(j).getDeterminerCode());
            }
        }
        currentLocation.exit();
    }

    private void informationRecipientComparison(EList<InformationRecipient> sourceInformationRecipient, EList<InformationRecipient> targetInformationRecipient) {
        currentLocation.enter("Information Recipient");
        for (int i=0;i<sourceInformationRecipient.size();i++) {
            for (int j = 0; j < targetInformationRecipient.size(); j++) {
                //compare RealmCodes
                compareRealmCodes(sourceInformationRecipient.get(i).getRealmCodes(), targetInformationRecipient.get(j).getRealmCodes());
                //compare TypeID
                typeIDComparison(sourceInformationRecipient.get(i).getTypeId(), targetInformationRecipient.get(j).getTypeId());
                //compare templateID
                compareTemplateID(sourceInformationRecipient.get(i).getTemplateIds(), targetInformationRecipient.get(j).getTemplateIds());
                //compare Intended Recipient
                intendedRecipientComparison(sourceInformationRecipient.get(i).getIntendedRecipient(), targetInformationRecipient.get(j).getIntendedRecipient());
                //compare NullFlavor
                compareNullFlavor(sourceInformationRecipient.get(i).getNullFlavor(), targetInformationRecipient.get(j).getNullFlavor());
                //compare typeCode
                compareTypeCode(sourceInformationRecipient.get(i).getTypeCode(), targetInformationRecipient.get(j).getTypeCode());
            }
        }
        currentLocation.exit();
    }

    private void inFulfillmentOfComparison(EList<InFulfillmentOf> sourceInFulfillmentOf, EList<InFulfillmentOf> targetInFulfillmentOf) {
        currentLocation.enter("In Fulfillment Of");
        for (int i=0;i<sourceInFulfillmentOf.size();i++) {
            for (int j = 0; j < targetInFulfillmentOf.size(); j++) {
                //compare RealmCodes
                compareRealmCodes(sourceInFulfillmentOf.get(i).getRealmCodes(), targetInFulfillmentOf.get(j).getRealmCodes());
                //compare TypeID
                typeIDComparison(sourceInFulfillmentOf.get(i).getTypeId(), targetInFulfillmentOf.get(j).getTypeId());
                //compare templateID
                compareTemplateID(sourceInFulfillmentOf.get(i).getTemplateIds(), targetInFulfillmentOf.get(j).getTemplateIds());
                //compare Order
                orderComparison(sourceInFulfillmentOf.get(i).getOrder(), targetInFulfillmentOf.get(j).getOrder());
                //compare NullFlavor
                compareNullFlavor(sourceInFulfillmentOf.get(i).getNullFlavor(), targetInFulfillmentOf.get(j).getNullFlavor());
                //compare typeCode
                compareTypeCode(sourceInFulfillmentOf.get(i).getTypeCode(), targetInFulfillmentOf.get(j).getTypeCode());
            }
        }
        currentLocation.exit();
    }

    private void relatedDocumentsComparison(EList<RelatedDocument> sourceRelatedDocument, EList<RelatedDocument> targetRelatedDocument) {
        currentLocation.enter("Related Documents");
        for (int i=0;i<sourceRelatedDocument.size();i++) {
            for (int j = 0; j < targetRelatedDocument.size(); j++) {
                //compare RealmCodes
                compareRealmCodes(sourceRelatedDocument.get(i).getRealmCodes(), targetRelatedDocument.get(j).getRealmCodes());
                //compare TypeID
                typeIDComparison(sourceRelatedDocument.get(i).getTypeId(), targetRelatedDocument.get(j).getTypeId());
                //compare templateID
                compareTemplateID(sourceRelatedDocument.get(i).getTemplateIds(), targetRelatedDocument.get(j).getTemplateIds());
                //compare Order
                parentDocumentComparison(sourceRelatedDocument.get(i).getParentDocument(), targetRelatedDocument.get(j).getParentDocument());
                //compare NullFlavor
                compareNullFlavor(sourceRelatedDocument.get(i).getNullFlavor(), targetRelatedDocument.get(j).getNullFlavor());
                //compare typeCode
                compareTypeCode(sourceRelatedDocument.get(i).getTypeCode(), targetRelatedDocument.get(j).getTypeCode());
            }
        }
        currentLocation.exit();
    }

    private void authorizationsComparison(EList<Authorization> sourceAuthorizations, EList<Authorization> targetAuthorizations) {
        currentLocation.enter("Authorizations");
        for (int i=0;i<sourceAuthorizations.size();i++) {
            for (int j = 0; j < targetAuthorizations.size(); j++) {
                //compare RealmCodes
                compareRealmCodes(sourceAuthorizations.get(i).getRealmCodes(), targetAuthorizations.get(j).getRealmCodes());
                //compare TypeID
                typeIDComparison(sourceAuthorizations.get(i).getTypeId(), targetAuthorizations.get(j).getTypeId());
                //compare templateID
                compareTemplateID(sourceAuthorizations.get(i).getTemplateIds(), targetAuthorizations.get(j).getTemplateIds());
                //compare Consent
                consentComparison(sourceAuthorizations.get(i).getConsent(), targetAuthorizations.get(j).getConsent());
                //compare NullFlavor
                compareNullFlavor(sourceAuthorizations.get(i).getNullFlavor(), targetAuthorizations.get(j).getNullFlavor());
                //compare typeCode
                compareTypeCode(sourceAuthorizations.get(i).getTypeCode(), targetAuthorizations.get(j).getTypeCode());
            }
        }
        currentLocation.exit();
    }

    private void componentOfComparison(Component1 sourceComponent1, Component1 targetComponent1) {
        currentLocation.enter("Component Of");
        if (sourceComponent1 != null && targetComponent1 != null) {
            //realm code
            compareRealmCodes(sourceComponent1.getRealmCodes(),targetComponent1.getRealmCodes());
            //typeID
            typeIDComparison(sourceComponent1.getTypeId(),targetComponent1.getTypeId());
            //templateID
            compareTemplateID(sourceComponent1.getTemplateIds(),targetComponent1.getTemplateIds());
            //Encompassong Encounter
            encompassingEncounterComparison(sourceComponent1.getEncompassingEncounter(),targetComponent1.getEncompassingEncounter());
            //nullFlavor
            compareNullFlavor(sourceComponent1.getNullFlavor(),targetComponent1.getNullFlavor());
            //TypeCode
            compareTypeCode(sourceComponent1.getTypeCode(),targetComponent1.getTypeCode());
        }
        currentLocation.exit();
    }

    private void guardiansComparison(EList<Guardian> sourceGuardian, EList<Guardian> targetGuardian) {
        currentLocation.enter("Guardians");
        for (int i=0;i<sourceGuardian.size();i++) {
            for (int j = 0; j < targetGuardian.size(); j++) {
                //compare RealmCodes
                compareRealmCodes(sourceGuardian.get(i).getRealmCodes(), targetGuardian.get(j).getRealmCodes());
                //compare TypeID
                typeIDComparison(sourceGuardian.get(i).getTypeId(), targetGuardian.get(j).getTypeId());
                //compare templateID
                compareTemplateID(sourceGuardian.get(i).getTemplateIds(), targetGuardian.get(j).getTemplateIds());
                //compare IDs
                compareIDs(sourceGuardian.get(i).getIds(), targetGuardian.get(j).getIds());
                //compare code
                compareCode(sourceGuardian.get(i).getCode(),targetGuardian.get(j).getCode());
                //compare Telecom
                compareTelcom(sourceGuardian.get(i).getTelecoms(),targetGuardian.get(j).getTelecoms());
                //compare Addr
                compareAddr(sourceGuardian.get(i).getAddrs(),targetGuardian.get(j).getAddrs());
                //choice - guardianPerson (Person) and guardianOrganization (Organization)
                personComparison(sourceGuardian.get(i).getGuardianPerson(), targetGuardian.get(j).getGuardianPerson());
                //choice - guardianOrganization (Organization)
                organizationComparison(sourceGuardian.get(i).getGuardianOrganization(), targetGuardian.get(j).getGuardianOrganization());
                //compare NullFlavor
                compareNullFlavor(sourceGuardian.get(i).getNullFlavor(), targetGuardian.get(j).getNullFlavor());
                //compare classCode
                compareClassCode(sourceGuardian.get(i).getClassCode(), targetGuardian.get(j).getClassCode());
            }
        }
        currentLocation.exit();
    }

    private void birthplaceComparison(Birthplace sourceBirthplace, Birthplace targetBirthplace) {
        currentLocation.enter("Birthplace");
        if (sourceBirthplace != null && targetBirthplace != null) {
            //realm code
            compareRealmCodes(sourceBirthplace.getRealmCodes(),targetBirthplace.getRealmCodes());
            //typeID
            typeIDComparison(sourceBirthplace.getTypeId(),targetBirthplace.getTypeId());
            //templateID
            compareTemplateID(sourceBirthplace.getTemplateIds(),targetBirthplace.getTemplateIds());
            //Place
            placeComparison(sourceBirthplace.getPlace(),targetBirthplace.getPlace());
            //nullFlavor
            compareNullFlavor(sourceBirthplace.getNullFlavor(),targetBirthplace.getNullFlavor());
            //TypeCode
            compareClassCode(sourceBirthplace.getClassCode(),targetBirthplace.getClassCode());
        }
        currentLocation.exit();
    }

    private void languageCommunicationsComparison(EList<LanguageCommunication> sourceLanguageCommmunication, EList<LanguageCommunication> targetLanguageCommunication) {
        currentLocation.enter("Language Communications");
        for (int i=0;i<sourceLanguageCommmunication.size();i++) {
            for (int j = 0; j < targetLanguageCommunication.size(); j++) {
                //compare RealmCodes
                compareRealmCodes(sourceLanguageCommmunication.get(i).getRealmCodes(), targetLanguageCommunication.get(j).getRealmCodes());
                //compare TypeID
                typeIDComparison(sourceLanguageCommmunication.get(i).getTypeId(), targetLanguageCommunication.get(j).getTypeId());
                //compare language code
                compareLanguageCode(sourceLanguageCommmunication.get(i).getLanguageCode(), targetLanguageCommunication.get(j).getLanguageCode());
                //compare Moode Code
                compareCode(sourceLanguageCommmunication.get(i).getModeCode(), targetLanguageCommunication.get(j).getModeCode());
                //compare Proficiency Level Code
                compareCode(sourceLanguageCommmunication.get(i).getProficiencyLevelCode(),targetLanguageCommunication.get(j).getProficiencyLevelCode());
                //compare PreferenceInd
                comparePreferenceInd(sourceLanguageCommmunication.get(i).getPreferenceInd(),targetLanguageCommunication.get(j).getPreferenceInd());
                //compare NullFlavor
                compareNullFlavor(sourceLanguageCommmunication.get(i).getNullFlavor(), targetLanguageCommunication.get(j).getNullFlavor());
            }

        }
        currentLocation.exit();
    }

    private void custodianOrganizationComparison(CustodianOrganization sourceCustodianOrganization, CustodianOrganization targetCustodianOrganization) {
        currentLocation.enter("Custodian Organization");
        if (sourceCustodianOrganization != null && targetCustodianOrganization != null) {
            //realmCode
            compareRealmCodes(sourceCustodianOrganization.getRealmCodes(),targetCustodianOrganization.getRealmCodes());
            //result Id
            typeIDComparison(sourceCustodianOrganization.getTypeId(),targetCustodianOrganization.getTypeId());
            //template ID
            compareTemplateID(sourceCustodianOrganization.getTemplateIds(),targetCustodianOrganization.getTemplateIds());
            //id
            compareIDs(sourceCustodianOrganization.getIds(),targetCustodianOrganization.getIds());
            //name
            compareNamesEN(sourceCustodianOrganization.getNames(),targetCustodianOrganization.getNames());
            //telecom
            compareTelcom(sourceCustodianOrganization.getTelecoms(),targetCustodianOrganization.getTelecoms());
            // addr
            compareAddr(sourceCustodianOrganization.getAddrs(),targetCustodianOrganization.getAddrs());
            //nullFlavor
            compareNullFlavor(sourceCustodianOrganization.getNullFlavor(),targetCustodianOrganization.getNullFlavor());
            //classCode
            compareClassCode(sourceCustodianOrganization.getClassCode(),targetCustodianOrganization.getClassCode());
            //determinerCode
            compareDeterminerCode(sourceCustodianOrganization.getDeterminerCode(),targetCustodianOrganization.getDeterminerCode());
        }
        currentLocation.exit();
    }

    private void performersComparison(EList<Performer1> sourcePerformer1, EList<Performer1> targetPerformer1) {
        currentLocation.enter("Performers");
        for (int i=0;i<sourcePerformer1.size();i++) {
            for (int j = 0; j < targetPerformer1.size(); j++) {
                //compare RealmCodes
                compareRealmCodes(sourcePerformer1.get(i).getRealmCodes(), targetPerformer1.get(j).getRealmCodes());
                //compare TypeID
                typeIDComparison(sourcePerformer1.get(i).getTypeId(), targetPerformer1.get(j).getTypeId());
                //compare Template ID
                compareTemplateID(sourcePerformer1.get(i).getTemplateIds(), targetPerformer1.get(j).getTemplateIds());
                //compare FunctionCode
                compareCode(sourcePerformer1.get(i).getFunctionCode(), targetPerformer1.get(j).getFunctionCode());
                //compare Time
                compareTime(sourcePerformer1.get(i).getTime(),targetPerformer1.get(j).getTime());
                //compare AssignedEntity
                assignedEntityComparison(sourcePerformer1.get(i).getAssignedEntity(),targetPerformer1.get(j).getAssignedEntity());
                //typeCode
                compareTypeCode(sourcePerformer1.get(i).getTypeCode(),targetPerformer1.get(j).getTypeCode());
                //compare NullFlavor
                compareNullFlavor(sourcePerformer1.get(i).getNullFlavor(), targetPerformer1.get(j).getNullFlavor());
            }
        }
        currentLocation.exit();
    }

    private void authenticatorComparison(EList<Authenticator> sourceAuthenticator, EList<Authenticator> targetAuthenticator) {
        currentLocation.enter("Authenticator");
        for (int i=0;i<sourceAuthenticator.size();i++) {
            for (int j = 0; j < targetAuthenticator.size(); j++) {
                //compare RealmCodes
                compareRealmCodes(sourceAuthenticator.get(i).getRealmCodes(), targetAuthenticator.get(j).getRealmCodes());
                //compare TypeID
                typeIDComparison(sourceAuthenticator.get(i).getTypeId(), targetAuthenticator.get(j).getTypeId());
                //compare TemplateIDs
                compareTemplateID(sourceAuthenticator.get(i).getTemplateIds(), targetAuthenticator.get(j).getTemplateIds());
                //compare Time
                compareTime(sourceAuthenticator.get(i).getTime(), targetAuthenticator.get(j).getTime());
                //compare Signature Code
                compareCode(sourceAuthenticator.get(i).getSignatureCode(),targetAuthenticator.get(j).getSignatureCode());
                //compare Assigned Entity
                assignedEntityComparison(sourceAuthenticator.get(i).getAssignedEntity(),targetAuthenticator.get(j).getAssignedEntity());
                //compare Type Code
                compareTypeCode(sourceAuthenticator.get(i).getTypeCode(),targetAuthenticator.get(j).getTypeCode());
                //compare NullFlavor
                compareNullFlavor(sourceAuthenticator.get(i).getNullFlavor(), targetAuthenticator.get(j).getNullFlavor());        }
        }
        currentLocation.exit();
    }

    private void component3Comparison(EList<Component3> sourceComponent3, EList<Component3> targetComponent3) {
        currentLocation.enter("Component3");
        for (int i=0;i<sourceComponent3.size();i++) {
            for (int j = 0; j < targetComponent3.size(); j++) {
                //compare RealmCodes
                compareRealmCodes(sourceComponent3.get(i).getRealmCodes(), targetComponent3.get(j).getRealmCodes());
                //compare TypeID
                typeIDComparison(sourceComponent3.get(i).getTypeId(), targetComponent3.get(j).getTypeId());
                //compare TemplateIDs
                compareTemplateID(sourceComponent3.get(i).getTemplateIds(), targetComponent3.get(j).getTemplateIds());
                //compare Section
                sectionComparison(sourceComponent3.get(i).getSection(), targetComponent3.get(j).getSection());
                //compare contextConductionInd
                compareContextConductionInd(sourceComponent3.get(i).getContextConductionInd(),targetComponent3.get(j).getContextConductionInd());
                //compare Type Code
                compareTypeCode(sourceComponent3.get(i).getTypeCode(),targetComponent3.get(j).getTypeCode());
                //compare NullFlavor
                compareNullFlavor(sourceComponent3.get(i).getNullFlavor(), targetComponent3.get(j).getNullFlavor());
            }
        }
        currentLocation.exit();
    }

    private void organizationPartOfComparison(OrganizationPartOf sourceOrganizationPartOf, OrganizationPartOf targetOrganizationPartOf) {
        currentLocation.enter("Organization Part Of");
        if (sourceOrganizationPartOf != null && targetOrganizationPartOf != null) {
            //realmCode
            compareRealmCodes(sourceOrganizationPartOf.getRealmCodes(),targetOrganizationPartOf.getRealmCodes());
            //result Id
            typeIDComparison(sourceOrganizationPartOf.getTypeId(),targetOrganizationPartOf.getTypeId());
            //template ID
            compareTemplateID(sourceOrganizationPartOf.getTemplateIds(),targetOrganizationPartOf.getTemplateIds());
            //id
            compareIDs(sourceOrganizationPartOf.getIds(),targetOrganizationPartOf.getIds());
            //code
            compareCode(sourceOrganizationPartOf.getCode(),targetOrganizationPartOf.getCode());
            //statusCode
            compareCode(sourceOrganizationPartOf.getStatusCode(),targetOrganizationPartOf.getStatusCode());
            // effectiveTime
            compareEffectiveTime(sourceOrganizationPartOf.getEffectiveTime(),targetOrganizationPartOf.getEffectiveTime());
            // wholeOrganization - Organization
            organizationComparison(sourceOrganizationPartOf.getWholeOrganization(),targetOrganizationPartOf.getWholeOrganization());
            //nullFlavor
            compareNullFlavor(sourceOrganizationPartOf.getNullFlavor(),targetOrganizationPartOf.getNullFlavor());
            //classCode
            compareClassCode(sourceOrganizationPartOf.getClassCode(),targetOrganizationPartOf.getClassCode());

        }
        currentLocation.exit();
    }

    private void maintainedEntityComparison(EList<MaintainedEntity> sourceMaintainedEntity, EList<MaintainedEntity> targetMaintainedEntity) {
        currentLocation.enter("Maintained Entity");
        for (int i=0;i<sourceMaintainedEntity.size();i++) {
            for (int j = 0; j < targetMaintainedEntity.size(); j++) {
                //compare RealmCodes
                compareRealmCodes(sourceMaintainedEntity.get(i).getRealmCodes(), targetMaintainedEntity.get(j).getRealmCodes());
                //compare TypeID
                typeIDComparison(sourceMaintainedEntity.get(i).getTypeId(), targetMaintainedEntity.get(j).getTypeId());
                //compare TemplateIDs
                compareTemplateID(sourceMaintainedEntity.get(i).getTemplateIds(), targetMaintainedEntity.get(j).getTemplateIds());
                //compare EffectiveTime
                compareEffectiveTime(sourceMaintainedEntity.get(i).getEffectiveTime(), targetMaintainedEntity.get(j).getEffectiveTime());
                //compare Maintaining Person
                personComparison(sourceMaintainedEntity.get(i).getMaintainingPerson(),targetMaintainedEntity.get(j).getMaintainingPerson());
                //compare Class Code
                compareClassCode(sourceMaintainedEntity.get(i).getClassCode(),targetMaintainedEntity.get(j).getClassCode());
                //compare NullFlavor
                compareNullFlavor(sourceMaintainedEntity.get(i).getNullFlavor(), targetMaintainedEntity.get(j).getNullFlavor());        }
        }
        currentLocation.exit();
    }

    private void intendedRecipientComparison(IntendedRecipient sourceIntendedRecipeint, IntendedRecipient targetIntendedRecipient) {
        currentLocation.enter("Intended Recipient");
        if (sourceIntendedRecipeint != null && targetIntendedRecipient != null) {
            //realmCode
            compareRealmCodes(sourceIntendedRecipeint.getRealmCodes(),targetIntendedRecipient.getRealmCodes());
            //result Id
            typeIDComparison(sourceIntendedRecipeint.getTypeId(),targetIntendedRecipient.getTypeId());
            //id
            compareIDs(sourceIntendedRecipeint.getIds(),targetIntendedRecipient.getIds());
            //Addr
            compareAddr(sourceIntendedRecipeint.getAddrs(),targetIntendedRecipient.getAddrs());
            //Telecom
            compareTelcom(sourceIntendedRecipeint.getTelecoms(),targetIntendedRecipient.getTelecoms());
            //Information Recipient
            personComparison(sourceIntendedRecipeint.getInformationRecipient(),targetIntendedRecipient.getInformationRecipient());
            // ReceivedOrganization - Organization
            organizationComparison(sourceIntendedRecipeint.getReceivedOrganization(),targetIntendedRecipient.getReceivedOrganization());
            //nullFlavor
            compareNullFlavor(sourceIntendedRecipeint.getNullFlavor(),targetIntendedRecipient.getNullFlavor());
            //classCode
            compareClassCode(sourceIntendedRecipeint.getClassCode(),targetIntendedRecipient.getClassCode());
        }
        currentLocation.exit();
    }

    private void orderComparison(Order sourceOrder, Order targetOrder) {
        currentLocation.enter("Order");
        if (sourceOrder != null && targetOrder != null) {
            //realmCode
            compareRealmCodes(sourceOrder.getRealmCodes(),targetOrder.getRealmCodes());
            //result Id
            typeIDComparison(sourceOrder.getTypeId(),targetOrder.getTypeId());
            //templateID
            compareTemplateID(sourceOrder.getTemplateIds(),targetOrder.getTemplateIds());
            //id
            compareIDs(sourceOrder.getIds(),targetOrder.getIds());
            //code
            compareCode(sourceOrder.getCode(),targetOrder.getCode());
            //PriorityCode
            compareCode(sourceOrder.getPriorityCode(),targetOrder.getPriorityCode());
            //nullFlavor
            compareNullFlavor(sourceOrder.getNullFlavor(),targetOrder.getNullFlavor());
            //classCode
            compareClassCode(sourceOrder.getClassCode(),targetOrder.getClassCode());
            //moodCode
            compareMoodCode(sourceOrder.getMoodCode(),targetOrder.getMoodCode());
        }
        currentLocation.exit();
    }

    private void parentDocumentComparison(ParentDocument sourceParentDocument, ParentDocument targetParentDocument) {
        currentLocation.enter("Parent Document");
        if (sourceParentDocument != null && targetParentDocument != null) {
            //realmCode
            compareRealmCodes(sourceParentDocument.getRealmCodes(),targetParentDocument.getRealmCodes());
            //result Id
            typeIDComparison(sourceParentDocument.getTypeId(),targetParentDocument.getTypeId());
            //templateID
            compareTemplateID(sourceParentDocument.getTemplateIds(),targetParentDocument.getTemplateIds());
            //id
            compareIDs(sourceParentDocument.getIds(),targetParentDocument.getIds());
            //code
            compareCode(sourceParentDocument.getCode(),targetParentDocument.getCode());
            //text
            compareText(sourceParentDocument.getText(),targetParentDocument.getText());
            //setID
            compareSetID(sourceParentDocument.getSetId(),targetParentDocument.getSetId());
            //versionNumber
            compareVersionNumber(sourceParentDocument.getVersionNumber(),targetParentDocument.getVersionNumber());
            //nullFlavor
            compareNullFlavor(sourceParentDocument.getNullFlavor(),targetParentDocument.getNullFlavor());
            //classCode
            compareClassCode(sourceParentDocument.getClassCode(),targetParentDocument.getClassCode());
            //moodCode
            compareMoodCode(sourceParentDocument.getMoodCode(),targetParentDocument.getMoodCode());
        }
        currentLocation.exit();
    }

    private void consentComparison(Consent sourceConsent, Consent targetConsesnt) {
        currentLocation.enter("Consent");
        if (sourceConsent != null && targetConsesnt != null) {
            //realmCode
            compareRealmCodes(sourceConsent.getRealmCodes(),targetConsesnt.getRealmCodes());
            //result Id
            typeIDComparison(sourceConsent.getTypeId(),targetConsesnt.getTypeId());
            //templateID
            compareTemplateID(sourceConsent.getTemplateIds(),targetConsesnt.getTemplateIds());
            //id
            compareIDs(sourceConsent.getIds(),targetConsesnt.getIds());
            //code
            compareCode(sourceConsent.getCode(),targetConsesnt.getCode());
            //statusCode
            compareCode(sourceConsent.getStatusCode(),targetConsesnt.getStatusCode());
            //nullFlavor
            compareNullFlavor(sourceConsent.getNullFlavor(),targetConsesnt.getNullFlavor());
            //classCode
            compareClassCode(sourceConsent.getClassCode(),targetConsesnt.getClassCode());
            //moodCode
            compareMoodCode(sourceConsent.getMoodCode(),targetConsesnt.getMoodCode());
        }
        currentLocation.exit();
    }

    private void encompassingEncounterComparison(EncompassingEncounter sourceEncompassingEncounter, EncompassingEncounter targetEncompassingEncounter) {
        currentLocation.enter("Encompassing Encounter");
        if (sourceEncompassingEncounter != null && targetEncompassingEncounter != null) {
            //realmCode
            compareRealmCodes(sourceEncompassingEncounter.getRealmCodes(),targetEncompassingEncounter.getRealmCodes());
            //result Id
            typeIDComparison(sourceEncompassingEncounter.getTypeId(),targetEncompassingEncounter.getTypeId());
            //templateID
            compareTemplateID(sourceEncompassingEncounter.getTemplateIds(),targetEncompassingEncounter.getTemplateIds());
            //id
            compareIDs(sourceEncompassingEncounter.getIds(),targetEncompassingEncounter.getIds());
            //code
            compareCode(sourceEncompassingEncounter.getCode(),targetEncompassingEncounter.getCode());
            //effectiveTime
            compareEffectiveTime(sourceEncompassingEncounter.getEffectiveTime(),targetEncompassingEncounter.getEffectiveTime());
            //dischargeDispositionCode
            compareCode(sourceEncompassingEncounter.getDischargeDispositionCode(),targetEncompassingEncounter.getDischargeDispositionCode());
            //responsibleParty
            responsiblePartyComparison(sourceEncompassingEncounter.getResponsibleParty(),targetEncompassingEncounter.getResponsibleParty());
            //encounterParticipant
            encounterParticipantComparison(sourceEncompassingEncounter.getEncounterParticipants(),targetEncompassingEncounter.getEncounterParticipants());
            //location
            locationComparison(sourceEncompassingEncounter.getLocation(),targetEncompassingEncounter.getLocation());
            //nullFlavor
            compareNullFlavor(sourceEncompassingEncounter.getNullFlavor(),targetEncompassingEncounter.getNullFlavor());
            //classCode
            compareClassCode(sourceEncompassingEncounter.getClassCode(),targetEncompassingEncounter.getClassCode());
            //moodCode
            compareMoodCode(sourceEncompassingEncounter.getMoodCode(),targetEncompassingEncounter.getMoodCode());
        }
        currentLocation.exit();
    }

    private void placeComparison(Place sourcePlace, Place targetPlace) {
        currentLocation.enter("Place");
        if (sourcePlace != null && targetPlace != null) {
            //realmCode
            compareRealmCodes(sourcePlace.getRealmCodes(),targetPlace.getRealmCodes());
            //result Id
            typeIDComparison(sourcePlace.getTypeId(),targetPlace.getTypeId());
            //templateID
            compareTemplateID(sourcePlace.getTemplateIds(),targetPlace.getTemplateIds());
            //name
            compareNamesEN(sourcePlace.getNames(),targetPlace.getNames());
            //Addr
            compareAddr(sourcePlace.getAddrs(),targetPlace.getAddrs());
            //nullFlavor
            compareNullFlavor(sourcePlace.getNullFlavor(),targetPlace.getNullFlavor());
            //classCode
            compareClassCode(sourcePlace.getClassCode(),targetPlace.getClassCode());
            //DeterminerCode
            compareDeterminerCode(sourcePlace.getDeterminerCode(),targetPlace.getDeterminerCode());
        }
        currentLocation.exit();
    }

    private void sectionComparison(Section sourceSection, Section targetSection) {
        currentLocation.enter("Section");
        if (sourceSection != null && targetSection != null) {
            //realmCode
            compareRealmCodes(sourceSection.getRealmCodes(),targetSection.getRealmCodes());
            //result Id
            typeIDComparison(sourceSection.getTypeId(),targetSection.getTypeId());
            //templateID
            compareTemplateID(sourceSection.getTemplateIds(),targetSection.getTemplateIds());
            //id
            compareID(sourceSection.getId(),targetSection.getId());
            //code
            compareCode(sourceSection.getCode(),targetSection.getCode());
            //title
            compareTitle(sourceSection.getTitle(),targetSection.getTitle());
            //text
            compareText(sourceSection.getText(),targetSection.getText());
            //confidentialityCode
            compareCode(sourceSection.getConfidentialityCode(),targetSection.getConfidentialityCode());
            //languageCode
            compareLanguageCode(sourceSection.getLanguageCode(),targetSection.getLanguageCode());
            //subject
            subjectComparison(sourceSection.getSubject(),targetSection.getSubject());
            //Author
            authorsComparison(sourceSection.getAuthors(),targetSection.getAuthors());
            //Informant12
            informantsComparison(sourceSection.getInformants(),targetSection.getInformants());
            //Entry
            entryComparison(sourceSection.getEntries(),targetSection.getEntries());
            //Component 5
            component5Comparison(sourceSection.getComponents(),targetSection.getComponents());
            //ID
            compareIDAttribute(sourceSection.getSectionId(),targetSection.getSectionId());
            //nullFlavor
            compareNullFlavor(sourceSection.getNullFlavor(),targetSection.getNullFlavor());
            //classCode
            compareClassCode(sourceSection.getClassCode(),targetSection.getClassCode());
            //moodCode
            compareMoodCode(sourceSection.getMoodCode(),targetSection.getMoodCode());
        }
        currentLocation.exit();
    }

    private void responsiblePartyComparison(ResponsibleParty sourceResponsibleParty, ResponsibleParty targetResponsibleParty) {
        currentLocation.enter("Responsible Party");
        if (sourceResponsibleParty != null && targetResponsibleParty != null) {
            //realmCode
            compareRealmCodes(sourceResponsibleParty.getRealmCodes(),targetResponsibleParty.getRealmCodes());
            //result Id
            typeIDComparison(sourceResponsibleParty.getTypeId(),targetResponsibleParty.getTypeId());
            //templateID
            compareTemplateID(sourceResponsibleParty.getTemplateIds(),targetResponsibleParty.getTemplateIds());
            //AssignedEntity
            assignedEntityComparison(sourceResponsibleParty.getAssignedEntity(),targetResponsibleParty.getAssignedEntity());
            //nullFlavor
            compareNullFlavor(sourceResponsibleParty.getNullFlavor(),targetResponsibleParty.getNullFlavor());
            //typeCode
            compareTypeCode(sourceResponsibleParty.getTypeCode(),targetResponsibleParty.getTypeCode());
        }
        currentLocation.exit();
    }

    private void encounterParticipantComparison(EList<EncounterParticipant> sourceEncounterParticipant, EList<EncounterParticipant> targetEncounterParticipant) {
        currentLocation.enter("Encounter Participant");
        for (int i=0;i<sourceEncounterParticipant.size();i++) {
            for (int j = 0; j < targetEncounterParticipant.size(); j++) {
                //compare RealmCodes
                compareRealmCodes(sourceEncounterParticipant.get(i).getRealmCodes(), targetEncounterParticipant.get(j).getRealmCodes());
                //compare TypeID
                typeIDComparison(sourceEncounterParticipant.get(i).getTypeId(), targetEncounterParticipant.get(j).getTypeId());
                //compare TemplateIDs
                compareTemplateID(sourceEncounterParticipant.get(i).getTemplateIds(), targetEncounterParticipant.get(j).getTemplateIds());
                //compare Time
                compareEffectiveTime(sourceEncounterParticipant.get(i).getTime(), targetEncounterParticipant.get(j).getTime());
                //compare assignedEntity
                assignedEntityComparison(sourceEncounterParticipant.get(i).getAssignedEntity(),targetEncounterParticipant.get(j).getAssignedEntity());
                //compare Type Code
                compareTypeCode(sourceEncounterParticipant.get(i).getTypeCode(),targetEncounterParticipant.get(j).getTypeCode());
                //compare NullFlavor
                compareNullFlavor(sourceEncounterParticipant.get(i).getNullFlavor(), targetEncounterParticipant.get(j).getNullFlavor());        }
        }
        currentLocation.exit();
    }

    private void locationComparison(Location sourceLocation, Location targetLocation) {
        currentLocation.enter("Location");
        if (sourceLocation != null && targetLocation != null) {
            //realmCode
            compareRealmCodes(sourceLocation.getRealmCodes(),targetLocation.getRealmCodes());
            //result Id
            typeIDComparison(sourceLocation.getTypeId(),targetLocation.getTypeId());
            //templateID
            compareTemplateID(sourceLocation.getTemplateIds(),targetLocation.getTemplateIds());
            //HealthCareFacility
            healthCareFacilityComparison(sourceLocation.getHealthCareFacility(),targetLocation.getHealthCareFacility());
            //nullFlavor
            compareNullFlavor(sourceLocation.getNullFlavor(),targetLocation.getNullFlavor());
            //typeCode
            compareTypeCode(sourceLocation.getTypeCode(),targetLocation.getTypeCode());
        }
        currentLocation.exit();
    }

    private void subjectComparison(Subject sourceSubject, Subject targetSubject) {
        currentLocation.enter("Subject");
        if (sourceSubject != null && targetSubject != null) {
            //realmCode
            compareRealmCodes(sourceSubject.getRealmCodes(),targetSubject.getRealmCodes());
            //result Id
            typeIDComparison(sourceSubject.getTypeId(),targetSubject.getTypeId());
            //templateID
            compareTemplateID(sourceSubject.getTemplateIds(),targetSubject.getTemplateIds());
            //AwarenessCode
            compareCode(sourceSubject.getAwarenessCode(),targetSubject.getAwarenessCode());
            //Related Subject
            relatedSubjectComparison(sourceSubject.getRelatedSubject(),targetSubject.getRelatedSubject());
            //nullFlavor
            compareNullFlavor(sourceSubject.getNullFlavor(),targetSubject.getNullFlavor());
            //typeCode
            compareTypeCode(sourceSubject.getTypeCode(),targetSubject.getTypeCode());
            //contextControlCode
            compareContextControlCode(sourceSubject.getContextControlCode(),targetSubject.getContextControlCode());
        }
        currentLocation.exit();
    }

    private void entryComparison(EList<Entry> sourceEntry, EList<Entry> targetEntry) {
        currentLocation.enter("Entry");
        for (int i=0;i<sourceEntry.size();i++) {
            for (int j = 0; j < targetEntry.size(); j++) {
                //compare RealmCodes
                compareRealmCodes(sourceEntry.get(i).getRealmCodes(), targetEntry.get(j).getRealmCodes());
                //compare TypeID
                typeIDComparison(sourceEntry.get(i).getTypeId(), targetEntry.get(j).getTypeId());
                //compare TemplateIDs
                compareTemplateID(sourceEntry.get(i).getTemplateIds(), targetEntry.get(j).getTemplateIds());
                //Choice Act
                actComparison(sourceEntry.get(i).getAct(), targetEntry.get(j).getAct());
                //Choice Encounter
                encounterComparison(sourceEntry.get(i).getEncounter(), targetEntry.get(j).getEncounter());
                //Choice Observation
                observationComparison(sourceEntry.get(i).getObservation(), targetEntry.get(j).getObservation());
                //Choice ObservationMedia
                observationMediaComparison(sourceEntry.get(i).getObservationMedia(), targetEntry.get(j).getObservationMedia());
                //Choice Organizer
                organizerComparison(sourceEntry.get(i).getOrganizer(), targetEntry.get(j).getOrganizer());
                //Choice Procedure
                procedureComparison(sourceEntry.get(i).getProcedure(), targetEntry.get(j).getProcedure());
                //Choice Region of Interest
                regionOfInterestComparison(sourceEntry.get(i).getRegionOfInterest(), targetEntry.get(j).getRegionOfInterest());
                //Choice Substance Administration
                substanceAdministrationComparison(sourceEntry.get(i).getSubstanceAdministration(), targetEntry.get(j).getSubstanceAdministration());
                //Choice Supply
                supplyComparison(sourceEntry.get(i).getSupply(), targetEntry.get(j).getSupply());
                //compare contextConductionInd
                compareContextConductionInd(sourceEntry.get(i).getContextConductionInd(),targetEntry.get(j).getContextConductionInd());
                //compare Type Code
                compareTypeCode(sourceEntry.get(i).getTypeCode(),targetEntry.get(j).getTypeCode());
                //compare NullFlavor
                compareNullFlavor(sourceEntry.get(i).getNullFlavor(), targetEntry.get(j).getNullFlavor());

            }
        }
        currentLocation.exit();
    }

    private void component5Comparison(EList<Component5> sourceComponent, EList<Component5> targetComponent) {
        currentLocation.enter("Component5");
        for (int i=0;i<sourceComponent.size();i++) {
            for (int j = 0; j < targetComponent.size(); j++) {
                //compare RealmCodes
                compareRealmCodes(sourceComponent.get(i).getRealmCodes(), targetComponent.get(j).getRealmCodes());
                //compare TypeID
                typeIDComparison(sourceComponent.get(i).getTypeId(), targetComponent.get(j).getTypeId());
                //compare TemplateIDs
                compareTemplateID(sourceComponent.get(i).getTemplateIds(), targetComponent.get(j).getTemplateIds());
                //Section
                sectionComparison(sourceComponent.get(i).getSection(),targetComponent.get(j).getSection());
                //compare contextConductionInd
                compareContextConductionInd(sourceComponent.get(i).getContextConductionInd(),targetComponent.get(j).getContextConductionInd());
                //compare Type Code
                compareTypeCode(sourceComponent.get(i).getTypeCode(),targetComponent.get(j).getTypeCode());
                //compare NullFlavor
                compareNullFlavor(sourceComponent.get(i).getNullFlavor(), targetComponent.get(j).getNullFlavor());
            }
        }
        currentLocation.exit();
    }

    private void healthCareFacilityComparison(HealthCareFacility sourceHealthCareFacility, HealthCareFacility targetHealthCareFacility) {
        currentLocation.enter("Health Care Facility");
        if (sourceHealthCareFacility != null && targetHealthCareFacility != null) {
            //realmCode
            compareRealmCodes(sourceHealthCareFacility.getRealmCodes(),targetHealthCareFacility.getRealmCodes());
            //result Id
            typeIDComparison(sourceHealthCareFacility.getTypeId(),targetHealthCareFacility.getTypeId());
            //templateID
            compareTemplateID(sourceHealthCareFacility.getTemplateIds(),targetHealthCareFacility.getTemplateIds());
            //id
            compareIDs(sourceHealthCareFacility.getIds(),targetHealthCareFacility.getIds());
            //code
            compareCode(sourceHealthCareFacility.getCode(),targetHealthCareFacility.getCode());
            //location - Place
            placeComparison(sourceHealthCareFacility.getLocation(),targetHealthCareFacility.getLocation());
            //serviceProviderOgranization - Organization
            organizationComparison(sourceHealthCareFacility.getServiceProviderOrganization(),targetHealthCareFacility.getServiceProviderOrganization());
            //nullFlavor
            compareNullFlavor(sourceHealthCareFacility.getNullFlavor(),targetHealthCareFacility.getNullFlavor());
            //ClassCode
            compareClassCode(sourceHealthCareFacility.getClassCode(),targetHealthCareFacility.getClassCode());
        }
        currentLocation.exit();
    }

    private void relatedSubjectComparison(RelatedSubject sourceRelatedSubject, RelatedSubject targetRelatedSubject) {
        currentLocation.enter("Related Subject");
        if (sourceRelatedSubject != null && targetRelatedSubject != null) {
            //realmCode
            compareRealmCodes(sourceRelatedSubject.getRealmCodes(),targetRelatedSubject.getRealmCodes());
            //result Id
            typeIDComparison(sourceRelatedSubject.getTypeId(),targetRelatedSubject.getTypeId());
            //templateID
            compareTemplateID(sourceRelatedSubject.getTemplateIds(),targetRelatedSubject.getTemplateIds());
            //code
            compareCode(sourceRelatedSubject.getCode(),targetRelatedSubject.getCode());
            //Addr
            compareAddr(sourceRelatedSubject.getAddrs(),targetRelatedSubject.getAddrs());
            //Telecom
            compareTelcom(sourceRelatedSubject.getTelecoms(),targetRelatedSubject.getTelecoms());
            //subject - SubjectPerson
            subjectPersonComparison(sourceRelatedSubject.getSubject(),targetRelatedSubject.getSubject());
            //nullFlavor
            compareNullFlavor(sourceRelatedSubject.getNullFlavor(),targetRelatedSubject.getNullFlavor());
            //ClassCode
            compareClassCode(sourceRelatedSubject.getClassCode(),targetRelatedSubject.getClassCode());
        }
        currentLocation.exit();
    }

    private void actComparison(Act sourceAct, Act targetAct) {
        currentLocation.enter("Act");
        if (sourceAct != null && targetAct != null) {
            //realmCode
            compareRealmCodes(sourceAct.getRealmCodes(),targetAct.getRealmCodes());
            //result Id
            typeIDComparison(sourceAct.getTypeId(),targetAct.getTypeId());
            //templateID
            compareTemplateID(sourceAct.getTemplateIds(),targetAct.getTemplateIds());
            //id
            compareIDs(sourceAct.getIds(),targetAct.getIds());
            //code
            compareCode(sourceAct.getCode(),targetAct.getCode());
            //text
            compareText(sourceAct.getText(),targetAct.getText());
            //statusCode
            compareCode(sourceAct.getCode(),targetAct.getCode());
            //effectiveTime
            compareEffectiveTime(sourceAct.getEffectiveTime(),targetAct.getEffectiveTime());
            //priorityCode
            compareCode(sourceAct.getPriorityCode(),targetAct.getPriorityCode());
            //languageCode
            compareLanguageCode(sourceAct.getLanguageCode(),targetAct.getLanguageCode());
            //subject
            subjectComparison(sourceAct.getSubject(),targetAct.getSubject());
            //Specimen
            specimenComparison(sourceAct.getSpecimens(),targetAct.getSpecimens());
            //performer - Performer2
            performer2Comparison(sourceAct.getPerformers(),targetAct.getPerformers());
            //author - Author
            authorsComparison(sourceAct.getAuthors(),targetAct.getAuthors());
            //informant - Informant12
            informantsComparison(sourceAct.getInformants(),targetAct.getInformants());
            //participant - Participant2
            participants2Comparison(sourceAct.getParticipants(),targetAct.getParticipants());
            //entryRelationship - EntryRelationship
            entryRelationshipComparison(sourceAct.getEntryRelationships(),targetAct.getEntryRelationships());
            //reference - Reference
            referenceComparison(sourceAct.getReferences(),targetAct.getReferences());
            //Precondition - Precondition
            preconditionComparison(sourceAct.getPreconditions(),targetAct.getPreconditions());
            //nullFlavor
            compareNullFlavor(sourceAct.getNullFlavor(),targetAct.getNullFlavor());
            //ClassCode
            compareClassCode(sourceAct.getClassCode(),targetAct.getClassCode());
            //moodCode
            compareMoodCode(sourceAct.getMoodCode(),targetAct.getMoodCode());
            //negationInd
            compareNegationInd(sourceAct.getNegationInd(),targetAct.getNegationInd());
        }
        currentLocation.exit();
    }

    private void encounterComparison(Encounter sourceEncounter, Encounter targetEncounter) {
        currentLocation.enter("Encounter");
        if (sourceEncounter != null && targetEncounter != null) {
            //realmCode
            compareRealmCodes(sourceEncounter.getRealmCodes(),targetEncounter.getRealmCodes());
            //result Id
            typeIDComparison(sourceEncounter.getTypeId(),targetEncounter.getTypeId());
            //templateID
            compareTemplateID(sourceEncounter.getTemplateIds(),targetEncounter.getTemplateIds());
            //id
            compareIDs(sourceEncounter.getIds(),targetEncounter.getIds());
            //code
            compareCode(sourceEncounter.getCode(),targetEncounter.getCode());
            //text
            compareText(sourceEncounter.getText(),targetEncounter.getText());
            //statusCode
            compareCode(sourceEncounter.getCode(),targetEncounter.getCode());
            //effectiveTime
            compareEffectiveTime(sourceEncounter.getEffectiveTime(),targetEncounter.getEffectiveTime());
            //priorityCode
            compareCode(sourceEncounter.getPriorityCode(),targetEncounter.getPriorityCode());
            //subject
            subjectComparison(sourceEncounter.getSubject(),targetEncounter.getSubject());
            //Specimen
            specimenComparison(sourceEncounter.getSpecimens(),targetEncounter.getSpecimens());
            //performer - Performer2
            performer2Comparison(sourceEncounter.getPerformers(),targetEncounter.getPerformers());
            //author - Author
            authorsComparison(sourceEncounter.getAuthors(),targetEncounter.getAuthors());
            //informant - Informant12
            informantsComparison(sourceEncounter.getInformants(),targetEncounter.getInformants());
            //participant - Participant2
            participants2Comparison(sourceEncounter.getParticipants(),targetEncounter.getParticipants());
            //entryRelationship - EntryRelationship
            entryRelationshipComparison(sourceEncounter.getEntryRelationships(),targetEncounter.getEntryRelationships());
            //reference - Reference
            referenceComparison(sourceEncounter.getReferences(),targetEncounter.getReferences());
            //Precondition - Precondition
            preconditionComparison(sourceEncounter.getPreconditions(),targetEncounter.getPreconditions());
            //nullFlavor
            compareNullFlavor(sourceEncounter.getNullFlavor(),targetEncounter.getNullFlavor());
            //ClassCode
            compareClassCode(sourceEncounter.getClassCode(),targetEncounter.getClassCode());
            //moodCode
            compareMoodCode(sourceEncounter.getMoodCode(),targetEncounter.getMoodCode());
        }
        currentLocation.exit();
    }

    private void observationComparison(Observation sourceObservation, Observation targetObservation) {
        currentLocation.enter("Observation");
        if (sourceObservation != null && targetObservation != null) {
            //realmCode
            compareRealmCodes(sourceObservation.getRealmCodes(),targetObservation.getRealmCodes());
            //result Id
            typeIDComparison(sourceObservation.getTypeId(),targetObservation.getTypeId());
            //templateID
            compareTemplateID(sourceObservation.getTemplateIds(),targetObservation.getTemplateIds());
            //id
            compareIDs(sourceObservation.getIds(),targetObservation.getIds());
            //code
            compareCode(sourceObservation.getCode(),targetObservation.getCode());
            //derivationExpr
            compareDerivationExpr(sourceObservation.getDerivationExpr(),targetObservation.getDerivationExpr());
            //text
            compareText(sourceObservation.getText(),targetObservation.getText());
            //statusCode
            compareCode(sourceObservation.getCode(),targetObservation.getCode());
            //effectiveTime
            compareEffectiveTime(sourceObservation.getEffectiveTime(),targetObservation.getEffectiveTime());
            //priorityCode
            compareCode(sourceObservation.getPriorityCode(),targetObservation.getPriorityCode());
            //repeatNumber
            compareRepeatNumber(sourceObservation.getRepeatNumber(),targetObservation.getRepeatNumber());
            //languageCode
            compareLanguageCode(sourceObservation.getLanguageCode(),targetObservation.getLanguageCode());
            //value
            compareValues(sourceObservation.getValues(),targetObservation.getValues());
            //interpretationCode
            compareCodes(sourceObservation.getInterpretationCodes(),targetObservation.getInterpretationCodes());
            //methodCode
            compareCodes(sourceObservation.getMethodCodes(),targetObservation.getMethodCodes());
            //targetSiteCode
            compareTargetSiteCode(sourceObservation.getTargetSiteCodes(),targetObservation.getTargetSiteCodes());
            //subject
            subjectComparison(sourceObservation.getSubject(),targetObservation.getSubject());
            //Specimen
            specimenComparison(sourceObservation.getSpecimens(),targetObservation.getSpecimens());
            //performer - Performer2
            performer2Comparison(sourceObservation.getPerformers(),targetObservation.getPerformers());
            //author - Author
            authorsComparison(sourceObservation.getAuthors(),targetObservation.getAuthors());
            //informant - Informant12
            informantsComparison(sourceObservation.getInformants(),targetObservation.getInformants());
            //participant - Participant2
            participants2Comparison(sourceObservation.getParticipants(),targetObservation.getParticipants());
            //entryRelationship - EntryRelationship
            entryRelationshipComparison(sourceObservation.getEntryRelationships(),targetObservation.getEntryRelationships());
            //reference - Reference
            referenceComparison(sourceObservation.getReferences(),targetObservation.getReferences());
            //Precondition - Precondition
            preconditionComparison(sourceObservation.getPreconditions(),targetObservation.getPreconditions());
            //referenceRange - ReferenceRange
            referenceRangeComparison(sourceObservation.getReferenceRanges(),targetObservation.getReferenceRanges());
            //nullFlavor
            compareNullFlavor(sourceObservation.getNullFlavor(),targetObservation.getNullFlavor());
            //ClassCode
            compareClassCode(sourceObservation.getClassCode(),targetObservation.getClassCode());
            //negationInd
            compareNegationInd(sourceObservation.getNegationInd(),targetObservation.getNegationInd());
        }
        currentLocation.exit();
    }

    private void observationMediaComparison(ObservationMedia sourceObservationMedia, ObservationMedia targetObservationMedia) {
        currentLocation.enter("Observation Media");
        if (sourceObservationMedia != null && targetObservationMedia != null) {
            //realmCode
            compareRealmCodes(sourceObservationMedia.getRealmCodes(),targetObservationMedia.getRealmCodes());
            //result Id
            typeIDComparison(sourceObservationMedia.getTypeId(),targetObservationMedia.getTypeId());
            //templateID
            compareTemplateID(sourceObservationMedia.getTemplateIds(),targetObservationMedia.getTemplateIds());
            //id
            compareIDs(sourceObservationMedia.getIds(),targetObservationMedia.getIds());
            //language code
            compareLanguageCode(sourceObservationMedia.getLanguageCode(),targetObservationMedia.getLanguageCode());
            //value
            compareText(sourceObservationMedia.getValue(),targetObservationMedia.getValue());
            //subject - Subject
            subjectComparison(sourceObservationMedia.getSubject(),targetObservationMedia.getSubject());
            //specimen - Specimen
            specimenComparison(sourceObservationMedia.getSpecimens(),targetObservationMedia.getSpecimens());
            //performer - Performer2
            performer2Comparison(sourceObservationMedia.getPerformers(),targetObservationMedia.getPerformers());
            //author - Author
            authorsComparison(sourceObservationMedia.getAuthors(),targetObservationMedia.getAuthors());
            //informant - Informant12
            informantsComparison(sourceObservationMedia.getInformants(),targetObservationMedia.getInformants());
            //participant - Participant2
            participants2Comparison(sourceObservationMedia.getParticipants(),targetObservationMedia.getParticipants());
            //entryRelationship - EntryRelationship
            entryRelationshipComparison(sourceObservationMedia.getEntryRelationships(),targetObservationMedia.getEntryRelationships());
            //reference - Reference
            referenceComparison(sourceObservationMedia.getReferences(),targetObservationMedia.getReferences());
            //precondition - Precondition
            preconditionComparison(sourceObservationMedia.getPreconditions(),targetObservationMedia.getPreconditions());
            //ID
            compareIDAttribute(sourceObservationMedia.getObservationMediaId(),targetObservationMedia.getObservationMediaId());
            //nullFlavor
            compareNullFlavor(sourceObservationMedia.getNullFlavor(),targetObservationMedia.getNullFlavor());
            //ClassCode
            compareClassCode(sourceObservationMedia.getClassCode(),targetObservationMedia.getClassCode());
            //moodCode
            compareMoodCode(sourceObservationMedia.getMoodCode(),targetObservationMedia.getMoodCode());
        }
        currentLocation.exit();
    }

    private void organizerComparison(Organizer sourceOrganizer, Organizer targetOrganizer) {
        currentLocation.enter("Organizer");
        if (sourceOrganizer != null && targetOrganizer != null) {
            //realmCode
            compareRealmCodes(sourceOrganizer.getRealmCodes(),targetOrganizer.getRealmCodes());
            //result Id
            typeIDComparison(sourceOrganizer.getTypeId(),targetOrganizer.getTypeId());
            //templateID
            compareTemplateID(sourceOrganizer.getTemplateIds(),targetOrganizer.getTemplateIds());
            //id
            compareIDs(sourceOrganizer.getIds(),targetOrganizer.getIds());
            //code
            compareCode(sourceOrganizer.getCode(),targetOrganizer.getCode());
            //statusCode
            compareCode(sourceOrganizer.getStatusCode(),targetOrganizer.getStatusCode());
            //effectiveTime
            compareEffectiveTime(sourceOrganizer.getEffectiveTime(),targetOrganizer.getEffectiveTime());
            //subject - Subject
            subjectComparison(sourceOrganizer.getSubject(),targetOrganizer.getSubject());
            //specimen - Specimen
            specimenComparison(sourceOrganizer.getSpecimens(),targetOrganizer.getSpecimens());
            //performer - Performer2
            performer2Comparison(sourceOrganizer.getPerformers(),targetOrganizer.getPerformers());
            //author - Author
            authorsComparison(sourceOrganizer.getAuthors(),targetOrganizer.getAuthors());
            //informant - Informant12
            informantsComparison(sourceOrganizer.getInformants(),targetOrganizer.getInformants());
            //participant - Participant2
            participants2Comparison(sourceOrganizer.getParticipants(),targetOrganizer.getParticipants());
            //reference - Reference
            referenceComparison(sourceOrganizer.getReferences(),targetOrganizer.getReferences());
            //precondition - Precondition
            preconditionComparison(sourceOrganizer.getPreconditions(),targetOrganizer.getPreconditions());
            //component - Component4
            component4Comparison(sourceOrganizer.getComponents(),targetOrganizer.getComponents());
            //nullFlavor
            compareNullFlavor(sourceOrganizer.getNullFlavor(),targetOrganizer.getNullFlavor());
            //ClassCode
            compareClassCode(sourceOrganizer.getClassCode(),targetOrganizer.getClassCode());
            //moodCode
            compareMoodCode(sourceOrganizer.getMoodCode(),targetOrganizer.getMoodCode());
        }
        currentLocation.exit();
    }

    private void procedureComparison(Procedure sourceProcedure, Procedure targetProcedure) {
        currentLocation.enter("Procedure");
        if (sourceProcedure != null && targetProcedure != null) {
            //realmCode
            compareRealmCodes(sourceProcedure.getRealmCodes(),targetProcedure.getRealmCodes());
            //result Id
            typeIDComparison(sourceProcedure.getTypeId(),targetProcedure.getTypeId());
            //templateID
            compareTemplateID(sourceProcedure.getTemplateIds(),targetProcedure.getTemplateIds());
            //id
            compareIDs(sourceProcedure.getIds(),targetProcedure.getIds());
            //code
            compareCode(sourceProcedure.getCode(),targetProcedure.getCode());
            //text
            compareText(sourceProcedure.getText(),targetProcedure.getText());
            //statusCode
            compareCode(sourceProcedure.getStatusCode(),targetProcedure.getStatusCode());
            //effectiveTime
            compareEffectiveTime(sourceProcedure.getEffectiveTime(),targetProcedure.getEffectiveTime());
            //priorityCode
            compareCode(sourceProcedure.getPriorityCode(),targetProcedure.getPriorityCode());
            //langaugeCode
            compareCode(sourceProcedure.getLanguageCode(),targetProcedure.getLanguageCode());
            //methodCode
            compareCodes(sourceProcedure.getMethodCodes(),targetProcedure.getMethodCodes());
            //approachSiteCode
            compareCodesCD(sourceProcedure.getApproachSiteCodes(),targetProcedure.getApproachSiteCodes());
            //targetSiteCode
            compareCodesCD(sourceProcedure.getTargetSiteCodes(),targetProcedure.getTargetSiteCodes());
            //subject - Subject
            subjectComparison(sourceProcedure.getSubject(),targetProcedure.getSubject());
            //specimen - Specimen
            specimenComparison(sourceProcedure.getSpecimens(),targetProcedure.getSpecimens());
            //performer - Performer2
            performer2Comparison(sourceProcedure.getPerformers(),targetProcedure.getPerformers());
            //author - Author
            authorsComparison(sourceProcedure.getAuthors(),targetProcedure.getAuthors());
            //informant - Informant12
            informantsComparison(sourceProcedure.getInformants(),targetProcedure.getInformants());
            //participant - Participant2
            participants2Comparison(sourceProcedure.getParticipants(),targetProcedure.getParticipants());
            //entryRelationship - EntryRelationship
            entryRelationshipComparison(sourceProcedure.getEntryRelationships(),targetProcedure.getEntryRelationships());
            //reference - Reference
            referenceComparison(sourceProcedure.getReferences(),targetProcedure.getReferences());
            //precondition - Precondition
            preconditionComparison(sourceProcedure.getPreconditions(),targetProcedure.getPreconditions());
            //nullFlavor
            compareNullFlavor(sourceProcedure.getNullFlavor(),targetProcedure.getNullFlavor());
            //ClassCode
            compareClassCode(sourceProcedure.getClassCode(),targetProcedure.getClassCode());
            //moodCode
            compareMoodCode(sourceProcedure.getMoodCode(),targetProcedure.getMoodCode());
        }
        currentLocation.exit();
    }

    private void regionOfInterestComparison(RegionOfInterest sourceRegionOfInterest, RegionOfInterest targetRegionOfInterest) {
        currentLocation.enter("Region Of Interest");
        if (sourceRegionOfInterest != null && targetRegionOfInterest != null) {
            //realmCode
            compareRealmCodes(sourceRegionOfInterest.getRealmCodes(),targetRegionOfInterest.getRealmCodes());
            //result Id
            typeIDComparison(sourceRegionOfInterest.getTypeId(),targetRegionOfInterest.getTypeId());
            //templateID
            compareTemplateID(sourceRegionOfInterest.getTemplateIds(),targetRegionOfInterest.getTemplateIds());
            //id
            compareIDs(sourceRegionOfInterest.getIds(),targetRegionOfInterest.getIds());
            //code
            compareCode(sourceRegionOfInterest.getCode(),targetRegionOfInterest.getCode());
            //value
            compareValuesROI(sourceRegionOfInterest.getValues(),targetRegionOfInterest.getValues());
            //subject - Subject
            subjectComparison(sourceRegionOfInterest.getSubject(),targetRegionOfInterest.getSubject());
            //specimen - Specimen
            specimenComparison(sourceRegionOfInterest.getSpecimens(),targetRegionOfInterest.getSpecimens());
            //performer - Performer2
            performer2Comparison(sourceRegionOfInterest.getPerformers(),targetRegionOfInterest.getPerformers());
            //author - Author
            authorsComparison(sourceRegionOfInterest.getAuthors(),targetRegionOfInterest.getAuthors());
            //informant - Informant12
            informantsComparison(sourceRegionOfInterest.getInformants(),targetRegionOfInterest.getInformants());
            //participant - Participant2
            participants2Comparison(sourceRegionOfInterest.getParticipants(),targetRegionOfInterest.getParticipants());
            //entryRelationship - EntryRelationship
            entryRelationshipComparison(sourceRegionOfInterest.getEntryRelationships(),targetRegionOfInterest.getEntryRelationships());
            //reference - Reference
            referenceComparison(sourceRegionOfInterest.getReferences(),targetRegionOfInterest.getReferences());
            //precondition - Precondition
            preconditionComparison(sourceRegionOfInterest.getPreconditions(),targetRegionOfInterest.getPreconditions());
            //nullFlavor
            compareNullFlavor(sourceRegionOfInterest.getNullFlavor(),targetRegionOfInterest.getNullFlavor());
            //ClassCode
            compareClassCode(sourceRegionOfInterest.getClassCode(),targetRegionOfInterest.getClassCode());
            //moodCode
            compareMoodCode(sourceRegionOfInterest.getMoodCode(),targetRegionOfInterest.getMoodCode());
            //ID Attribute
            compareIDAttribute(sourceRegionOfInterest.getRegionOfInterestId(),targetRegionOfInterest.getRegionOfInterestId());
        }
        currentLocation.exit();
    }

    private void substanceAdministrationComparison(SubstanceAdministration sourceSubstanceAdministration, SubstanceAdministration targetSubstanceAdminstration) {
        currentLocation.enter("Substance Administration");
        if (sourceSubstanceAdministration != null && targetSubstanceAdminstration != null) {
            //realmCode
            compareRealmCodes(sourceSubstanceAdministration.getRealmCodes(),targetSubstanceAdminstration.getRealmCodes());
            //result Id
            typeIDComparison(sourceSubstanceAdministration.getTypeId(),targetSubstanceAdminstration.getTypeId());
            //templateID
            compareTemplateID(sourceSubstanceAdministration.getTemplateIds(),targetSubstanceAdminstration.getTemplateIds());
            //id
            compareIDs(sourceSubstanceAdministration.getIds(),targetSubstanceAdminstration.getIds());
            //code
            compareCode(sourceSubstanceAdministration.getCode(),targetSubstanceAdminstration.getCode());
            //text
            compareText(sourceSubstanceAdministration.getText(),targetSubstanceAdminstration.getText());
            //statusCode
            compareCode(sourceSubstanceAdministration.getStatusCode(),targetSubstanceAdminstration.getStatusCode());
            //effectiveTime
            compareEffectiveTime(sourceSubstanceAdministration.getEffectiveTimes(),targetSubstanceAdminstration.getEffectiveTimes());
            //priorityCode
            compareCode(sourceSubstanceAdministration.getPriorityCode(),targetSubstanceAdminstration.getPriorityCode());
            //repeatNumber
            compareRepeatNumber(sourceSubstanceAdministration.getRepeatNumber(),targetSubstanceAdminstration.getRepeatNumber());
            //routeCode
            compareCode(sourceSubstanceAdministration.getRouteCode(),targetSubstanceAdminstration.getRouteCode());
            //approachSiteCode
            compareCodesCD(sourceSubstanceAdministration.getApproachSiteCodes(),targetSubstanceAdminstration.getApproachSiteCodes());
            //doseQuantity
            compareDose(sourceSubstanceAdministration.getDoseQuantity(),targetSubstanceAdminstration.getDoseQuantity());
            //rateQuantity
            compareDose(sourceSubstanceAdministration.getRateQuantity(),targetSubstanceAdminstration.getRateQuantity());
            //maxDoseQuantity
            compareMaxDose(sourceSubstanceAdministration.getMaxDoseQuantity(),targetSubstanceAdminstration.getMaxDoseQuantity());
            //administrationUnitCode
            compareCode(sourceSubstanceAdministration.getAdministrationUnitCode(),targetSubstanceAdminstration.getAdministrationUnitCode());
            //subject - Subject
            subjectComparison(sourceSubstanceAdministration.getSubject(),targetSubstanceAdminstration.getSubject());
            //specimen - Specimen
            specimenComparison(sourceSubstanceAdministration.getSpecimens(),targetSubstanceAdminstration.getSpecimens());
            //consumable
            consumableComparison(sourceSubstanceAdministration.getConsumable(),targetSubstanceAdminstration.getConsumable());
            //performer - Performer2
            performer2Comparison(sourceSubstanceAdministration.getPerformers(),targetSubstanceAdminstration.getPerformers());
            //author - Author
            authorsComparison(sourceSubstanceAdministration.getAuthors(),targetSubstanceAdminstration.getAuthors());
            //informant - Informant12
            informantsComparison(sourceSubstanceAdministration.getInformants(),targetSubstanceAdminstration.getInformants());
            //participant - Participant2
            participants2Comparison(sourceSubstanceAdministration.getParticipants(),targetSubstanceAdminstration.getParticipants());
            //entryRelationship - EntryRelationship
            entryRelationshipComparison(sourceSubstanceAdministration.getEntryRelationships(),targetSubstanceAdminstration.getEntryRelationships());
            //reference - Reference
            referenceComparison(sourceSubstanceAdministration.getReferences(),targetSubstanceAdminstration.getReferences());
            //precondition - Precondition
            preconditionComparison(sourceSubstanceAdministration.getPreconditions(),targetSubstanceAdminstration.getPreconditions());
            //nullFlavor
            compareNullFlavor(sourceSubstanceAdministration.getNullFlavor(),targetSubstanceAdminstration.getNullFlavor());
            //ClassCode
            compareClassCode(sourceSubstanceAdministration.getClassCode(),targetSubstanceAdminstration.getClassCode());
            //moodCode
            compareMoodCode(sourceSubstanceAdministration.getMoodCode(),targetSubstanceAdminstration.getMoodCode());
            //negationInd
            compareNegationInd(sourceSubstanceAdministration.getNegationInd(),targetSubstanceAdminstration.getNegationInd());
        }
        currentLocation.exit();
    }

    private void supplyComparison(Supply sourceSupply, Supply targetSupply) {
        currentLocation.enter("Supply");
        if (sourceSupply != null && targetSupply != null) {
            //realmCode
            compareRealmCodes(sourceSupply.getRealmCodes(),targetSupply.getRealmCodes());
            //result Id
            typeIDComparison(sourceSupply.getTypeId(),targetSupply.getTypeId());
            //templateID
            compareTemplateID(sourceSupply.getTemplateIds(),targetSupply.getTemplateIds());
            //id
            compareIDs(sourceSupply.getIds(),targetSupply.getIds());
            //code
            compareCode(sourceSupply.getCode(),targetSupply.getCode());
            //text
            compareText(sourceSupply.getText(),targetSupply.getText());
            //statusCode
            compareCode(sourceSupply.getStatusCode(),targetSupply.getStatusCode());
            //effectiveTime
            compareEffectiveTime(sourceSupply.getEffectiveTimes(),targetSupply.getEffectiveTimes());
            //priorityCode
            compareCodes(sourceSupply.getPriorityCodes(),targetSupply.getPriorityCodes());
            //repeatNumber
            compareRepeatNumber(sourceSupply.getRepeatNumber(),targetSupply.getRepeatNumber());
            //independentInd
            comparePreferenceInd(sourceSupply.getIndependentInd(),targetSupply.getIndependentInd());
            //quantity
            compareQuantity(sourceSupply.getQuantity(),targetSupply.getQuantity());
            //expectedUseTime
            compareExpectedUseTime(sourceSupply.getExpectedUseTime(),targetSupply.getExpectedUseTime());
            //subject - Subject
            subjectComparison(sourceSupply.getSubject(),targetSupply.getSubject());
            //specimen - Specimen
            specimenComparison(sourceSupply.getSpecimens(),targetSupply.getSpecimens());
            //product
            productComparison(sourceSupply.getProduct(),targetSupply.getProduct());
            //author - Author
            authorsComparison(sourceSupply.getAuthors(),targetSupply.getAuthors());
            //informant - Informant12
            informantsComparison(sourceSupply.getInformants(),targetSupply.getInformants());
            //participant - Participant2
            participants2Comparison(sourceSupply.getParticipants(),targetSupply.getParticipants());
            //entryRelationship - EntryRelationship
            entryRelationshipComparison(sourceSupply.getEntryRelationships(),targetSupply.getEntryRelationships());
            //precondition - Precondition
            preconditionComparison(sourceSupply.getPreconditions(),targetSupply.getPreconditions());
            //nullFlavor
            compareNullFlavor(sourceSupply.getNullFlavor(),targetSupply.getNullFlavor());
            //ClassCode
            compareClassCode(sourceSupply.getClassCode(),targetSupply.getClassCode());
            //moodCode
            compareMoodCode(sourceSupply.getMoodCode(),targetSupply.getMoodCode());
        }
        currentLocation.exit();
    }

    private void subjectPersonComparison(SubjectPerson sourceSubjectPerson, SubjectPerson targetSubjectPerson) {
        currentLocation.enter("Subject Person");
        if (sourceSubjectPerson != null && targetSubjectPerson != null) {
            //realmCode
            compareRealmCodes(sourceSubjectPerson.getRealmCodes(),targetSubjectPerson.getRealmCodes());
            //result Id
            typeIDComparison(sourceSubjectPerson.getTypeId(),targetSubjectPerson.getTypeId());
            //templateID
            compareTemplateID(sourceSubjectPerson.getTemplateIds(),targetSubjectPerson.getTemplateIds());
            //name
            compareNamesPN(sourceSubjectPerson.getNames(),targetSubjectPerson.getNames());
            //administrativeGenderCode
            compareCode(sourceSubjectPerson.getAdministrativeGenderCode(),targetSubjectPerson.getAdministrativeGenderCode());
            //birthTime
            compareTime(sourceSubjectPerson.getBirthTime(),targetSubjectPerson.getBirthTime());
            //nullFlavor
            compareNullFlavor(sourceSubjectPerson.getNullFlavor(),targetSubjectPerson.getNullFlavor());
            //ClassCode
            compareClassCode(sourceSubjectPerson.getClassCode(),targetSubjectPerson.getClassCode());
            //DeterminerCode
            compareDeterminerCode(sourceSubjectPerson.getDeterminerCode(),targetSubjectPerson.getDeterminerCode());
        }
        currentLocation.exit();
    }

    private void specimenComparison(EList<Specimen> sourceSpecimen, EList<Specimen> targetSpecimen) {
        currentLocation.enter("Specimen");
        for (int i=0;i<sourceSpecimen.size();i++) {
            for (int j = 0; j < targetSpecimen.size(); j++) {
                //compare RealmCodes
                compareRealmCodes(sourceSpecimen.get(i).getRealmCodes(), targetSpecimen.get(j).getRealmCodes());
                //compare TypeID
                typeIDComparison(sourceSpecimen.get(i).getTypeId(), targetSpecimen.get(j).getTypeId());
                //compare TemplateIDs
                compareTemplateID(sourceSpecimen.get(i).getTemplateIds(), targetSpecimen.get(j).getTemplateIds());
                //Section
                specimenRoleComparison(sourceSpecimen.get(i).getSpecimenRole(),targetSpecimen.get(j).getSpecimenRole());
                //compare Type Code
                compareTypeCode(sourceSpecimen.get(i).getTypeCode(),targetSpecimen.get(j).getTypeCode());
                //compare NullFlavor
                compareNullFlavor(sourceSpecimen.get(i).getNullFlavor(), targetSpecimen.get(j).getNullFlavor());
            }
        }
        currentLocation.exit();
    }

    private void performer2Comparison(EList<Performer2> sourcePerformer, EList<Performer2> targetPerformer) {
        currentLocation.enter("Performer2");
        for (int i=0;i<sourcePerformer.size();i++) {
            for (int j = 0; j < targetPerformer.size(); j++) {
                //compare RealmCodes
                compareRealmCodes(sourcePerformer.get(i).getRealmCodes(), targetPerformer.get(j).getRealmCodes());
                //compare TypeID
                typeIDComparison(sourcePerformer.get(i).getTypeId(), targetPerformer.get(j).getTypeId());
                //compare TemplateIDs
                compareTemplateID(sourcePerformer.get(i).getTemplateIds(), targetPerformer.get(j).getTemplateIds());
                //Time
                compareTime(sourcePerformer.get(i).getTime(),targetPerformer.get(j).getTime());
                //modeCode
                compareCode(sourcePerformer.get(i).getModeCode(),targetPerformer.get(j).getModeCode());
                //assignedEntity - AssignedEntity
                assignedEntityComparison(sourcePerformer.get(i).getAssignedEntity(),targetPerformer.get(j).getAssignedEntity());
                //compare NullFlavor
                compareNullFlavor(sourcePerformer.get(i).getNullFlavor(), targetPerformer.get(j).getNullFlavor());
                //result Code
                compareTypeCode(sourcePerformer.get(i).getTypeCode(),targetPerformer.get(j).getTypeCode());        }
        }
        currentLocation.exit();
    }

    private void participants2Comparison(EList<Participant2> sourceParticipants, EList<Participant2> targetParticipants) {
        currentLocation.enter("Participants2");
        for (int i=0;i<sourceParticipants.size();i++) {
            for (int j = 0; j < targetParticipants.size(); j++) {
                //compare RealmCodes
                compareRealmCodes(sourceParticipants.get(i).getRealmCodes(), targetParticipants.get(j).getRealmCodes());
                //compare TypeID
                typeIDComparison(sourceParticipants.get(i).getTypeId(), targetParticipants.get(j).getTypeId());
                //compare TemplateIDs
                compareTemplateID(sourceParticipants.get(i).getTemplateIds(), targetParticipants.get(j).getTemplateIds());
                //Time
                compareExpectedUseTime(sourceParticipants.get(i).getTime(),targetParticipants.get(j).getTime());
                //awarenessCode
                compareCode(sourceParticipants.get(i).getAwarenessCode(),targetParticipants.get(j).getAwarenessCode());
                //participantRole - ParticipantRole
                participantRoleComparison(sourceParticipants.get(i).getParticipantRole(),targetParticipants.get(j).getParticipantRole());
                //compare NullFlavor
                compareNullFlavor(sourceParticipants.get(i).getNullFlavor(), targetParticipants.get(j).getNullFlavor());
                //result Code
                compareTypeCode(sourceParticipants.get(i).getTypeCode(),targetParticipants.get(j).getTypeCode());
                //contextControlCode
                compareContextControlCode(sourceParticipants.get(i).getContextControlCode(),targetParticipants.get(j).getContextControlCode());
            }
        }
        currentLocation.exit();
    }

    private void entryRelationshipComparison(EList<EntryRelationship> sourceEntryRelationships, EList<EntryRelationship> targetEntryRelationships) {
        currentLocation.enter("Entry Relationship");
        for (int i=0;i<sourceEntryRelationships.size();i++) {
            for (int j = 0; j < targetEntryRelationships.size(); j++) {
                //compare RealmCodes
                compareRealmCodes(sourceEntryRelationships.get(i).getRealmCodes(), targetEntryRelationships.get(j).getRealmCodes());
                //compare TypeID
                typeIDComparison(sourceEntryRelationships.get(i).getTypeId(), targetEntryRelationships.get(j).getTypeId());
                //compare TemplateIDs
                compareTemplateID(sourceEntryRelationships.get(i).getTemplateIds(), targetEntryRelationships.get(j).getTemplateIds());
                //sequenceNumber
                compareVersionNumber(sourceEntryRelationships.get(i).getSequenceNumber(),targetEntryRelationships.get(j).getSequenceNumber());
                //seperatableInd
                comparePreferenceInd(sourceEntryRelationships.get(i).getSeperatableInd(),targetEntryRelationships.get(j).getSeperatableInd());
                //Choice - Act
                actComparison(sourceEntryRelationships.get(i).getAct(), targetEntryRelationships.get(j).getAct());
                //Choice - Encounter
                encounterComparison(sourceEntryRelationships.get(i).getEncounter(), targetEntryRelationships.get(j).getEncounter());
                //Choice - Observation
                observationComparison(sourceEntryRelationships.get(i).getObservation(), targetEntryRelationships.get(j).getObservation());
                //Choice - observationMedia
                observationMediaComparison(sourceEntryRelationships.get(i).getObservationMedia(), targetEntryRelationships.get(j).getObservationMedia());
                //Choice - Organizer
                organizerComparison(sourceEntryRelationships.get(i).getOrganizer(), targetEntryRelationships.get(j).getOrganizer());
                //Choice - Procedure
                procedureComparison(sourceEntryRelationships.get(i).getProcedure(), targetEntryRelationships.get(j).getProcedure());
                //Choice - RegionOfInterest
                regionOfInterestComparison(sourceEntryRelationships.get(i).getRegionOfInterest(), targetEntryRelationships.get(j).getRegionOfInterest());
                //Choice - SubstanceAdminstration
                substanceAdministrationComparison(sourceEntryRelationships.get(i).getSubstanceAdministration(), targetEntryRelationships.get(j).getSubstanceAdministration());
                //Choice - Supply
                supplyComparison(sourceEntryRelationships.get(i).getSupply(), targetEntryRelationships.get(j).getSupply());
                //compare NullFlavor
                compareNullFlavor(sourceEntryRelationships.get(i).getNullFlavor(), targetEntryRelationships.get(j).getNullFlavor());
                //result Code
                compareTypeCode(sourceEntryRelationships.get(i).getTypeCode(),targetEntryRelationships.get(j).getTypeCode());
                //Inversion Ind
                compareInversionInd(sourceEntryRelationships.get(i).getInversionInd(),targetEntryRelationships.get(j).getInversionInd());
                //contextConductionInd
                compareContextConductionInd(sourceEntryRelationships.get(i).getContextConductionInd(),targetEntryRelationships.get(j).getContextConductionInd());
            }
        }
        currentLocation.exit();
    }

    private void referenceComparison(EList<Reference> sourceReference, EList<Reference> targetReference) {
        currentLocation.enter("Reference");
        for (int i=0;i<sourceReference.size();i++) {
            for (int j = 0; j < targetReference.size(); j++) {
                //compare RealmCodes
                compareRealmCodes(sourceReference.get(i).getRealmCodes(), targetReference.get(j).getRealmCodes());
                //compare TypeID
                typeIDComparison(sourceReference.get(i).getTypeId(), targetReference.get(j).getTypeId());
                //compare TemplateIDs
                compareTemplateID(sourceReference.get(i).getTemplateIds(), targetReference.get(j).getTemplateIds());
                //seperatableInd
                comparePreferenceInd(sourceReference.get(i).getSeperatableInd(),targetReference.get(j).getSeperatableInd());
                //Choice - ExternalAct
                externalActComparison(sourceReference.get(i).getExternalAct(), targetReference.get(j).getExternalAct());
                //Choice - ExternalObservation
                externalObservationComparison(sourceReference.get(i).getExternalObservation(), targetReference.get(j).getExternalObservation());
                //Choice - ExternalProcedure
                externalProcedureComparison(sourceReference.get(i).getExternalProcedure(), targetReference.get(j).getExternalProcedure());
                //Choice - ExternalDocument
                externalDocumentComparison(sourceReference.get(i).getExternalDocument(), targetReference.get(j).getExternalDocument());

                //compare NullFlavor
                compareNullFlavor(sourceReference.get(i).getNullFlavor(), targetReference.get(j).getNullFlavor());
                //result Code
                compareTypeCode(sourceReference.get(i).getTypeCode(),targetReference.get(j).getTypeCode());
            }
        }
        currentLocation.exit();
    }

    private void preconditionComparison(EList<Precondition> sourcePrecondition, EList<Precondition> targetPrecondition) {
        currentLocation.enter("Precondition");
        for (int i=0;i<sourcePrecondition.size();i++) {
            for (int j = 0; j < targetPrecondition.size(); j++) {
                //compare RealmCodes
                compareRealmCodes(sourcePrecondition.get(i).getRealmCodes(), targetPrecondition.get(j).getRealmCodes());
                //compare TypeID
                typeIDComparison(sourcePrecondition.get(i).getTypeId(), targetPrecondition.get(j).getTypeId());
                //compare criterion - Criterion
                criterionComparison(sourcePrecondition.get(i).getCriterion(), targetPrecondition.get(j).getCriterion());
                //compare NullFlavor
                compareNullFlavor(sourcePrecondition.get(i).getNullFlavor(), targetPrecondition.get(j).getNullFlavor());
                //result Code
                compareTypeCode(sourcePrecondition.get(i).getTypeCode(),targetPrecondition.get(j).getTypeCode());
            }
        }
        currentLocation.exit();
    }

    private void referenceRangeComparison(EList<ReferenceRange> sourceReferenceRange, EList<ReferenceRange> targetReferenceRange) {
        currentLocation.enter("Reference Range");
        for (int i=0;i<sourceReferenceRange.size();i++) {
            for (int j = 0; j < targetReferenceRange.size(); j++) {
                //compare RealmCodes
                compareRealmCodes(sourceReferenceRange.get(i).getRealmCodes(), targetReferenceRange.get(j).getRealmCodes());
                //compare TypeID
                typeIDComparison(sourceReferenceRange.get(i).getTypeId(), targetReferenceRange.get(j).getTypeId());
                //compare TemplateIDs
                compareTemplateID(sourceReferenceRange.get(i).getTemplateIds(), targetReferenceRange.get(j).getTemplateIds());
                //observationRange - ObservationRange
                observationRangeComparison(sourceReferenceRange.get(i).getObservationRange(),targetReferenceRange.get(j).getObservationRange());
                //compare NullFlavor
                compareNullFlavor(sourceReferenceRange.get(i).getNullFlavor(), targetReferenceRange.get(j).getNullFlavor());
                //result Code
                compareTypeCode(sourceReferenceRange.get(i).getTypeCode(),targetReferenceRange.get(j).getTypeCode());
            }
        }
        currentLocation.exit();
    }

    private void component4Comparison(EList<Component4> sourceComponent4, EList<Component4> targetComponent4) {
        currentLocation.enter("Component4");
        for (int i=0;i<sourceComponent4.size();i++) {
            for (int j = 0; j < targetComponent4.size(); j++) {
                //compare RealmCodes
                compareRealmCodes(sourceComponent4.get(i).getRealmCodes(), targetComponent4.get(j).getRealmCodes());
                //compare TypeID
                typeIDComparison(sourceComponent4.get(i).getTypeId(), targetComponent4.get(j).getTypeId());
                //compare TemplateIDs
                compareTemplateID(sourceComponent4.get(i).getTemplateIds(), targetComponent4.get(j).getTemplateIds());
                //sequenceNumber
                compareVersionNumber(sourceComponent4.get(i).getSequenceNumber(),targetComponent4.get(j).getSequenceNumber());
                //seperatableInd
                comparePreferenceInd(sourceComponent4.get(i).getSeperatableInd(),targetComponent4.get(j).getSeperatableInd());
                //Choice - Act
                actComparison(sourceComponent4.get(i).getAct(), targetComponent4.get(j).getAct());
                //Choice - Observation
                observationComparison(sourceComponent4.get(i).getObservation(), targetComponent4.get(j).getObservation());
                //Choice - ObservationMedia
                observationMediaComparison(sourceComponent4.get(i).getObservationMedia(), targetComponent4.get(j).getObservationMedia());
                //Choice - Organizer
                organizerComparison(sourceComponent4.get(i).getOrganizer(), targetComponent4.get(j).getOrganizer());
                //Choice - Procedure
                procedureComparison(sourceComponent4.get(i).getProcedure(), targetComponent4.get(j).getProcedure());
                //Choice - RegionOfInterest
                regionOfInterestComparison(sourceComponent4.get(i).getRegionOfInterest(), targetComponent4.get(j).getRegionOfInterest());
                //Choice - SubstanceAdministration
                substanceAdministrationComparison(sourceComponent4.get(i).getSubstanceAdministration(), targetComponent4.get(j).getSubstanceAdministration());
                //Choice - Supply
                supplyComparison(sourceComponent4.get(i).getSupply(), targetComponent4.get(j).getSupply());
                //compare NullFlavor
                compareNullFlavor(sourceComponent4.get(i).getNullFlavor(), targetComponent4.get(j).getNullFlavor());
                //result Code
                compareTypeCode(sourceComponent4.get(i).getTypeCode(),targetComponent4.get(j).getTypeCode());
                //contextConductionInd
                compareContextConductionInd(sourceComponent4.get(i).getContextConductionInd(),targetComponent4.get(j).getContextConductionInd());
            }
        }
        currentLocation.exit();
    }

    private void compareValuesROI(EList<RegionOfInterestValue> sourceValue, EList<RegionOfInterestValue> targetValue) {
        currentLocation.enter("Region Of Interest Value");

        //initialize targetListObjects
        List<List<Object>> targetListObjects = sourceValue.stream()
                .map(RegionOfInterestValue -> new ArrayList<Object>() {{add(RegionOfInterestValue.getValue()); add(RegionOfInterestValue.getNullFlavor().getLiteral()); add(RegionOfInterestValue.isUnsorted()); }} )
                .collect(Collectors.toList());

        //initialize sourceListObjects
        List<List<Object>> sourceListObjects = targetValue.stream()
                .map(RegionOfInterestValue -> new ArrayList<Object>() {{add(RegionOfInterestValue.getValue()); add(RegionOfInterestValue.getNullFlavor().getLiteral()); add(RegionOfInterestValue.isUnsorted());}} )
                .collect(Collectors.toList());

        for (List<Object> sourceObjects : sourceListObjects) {
            comparisons.add(new Comparison(new ComparisonLocation(currentLocation), ComparisonUtility.ObjectListComparison(), sourceObjects,targetListObjects));
        }

        currentLocation.exit();
    }

    private void consumableComparison(Consumable sourceConsumable, Consumable targetConsumable) {
        currentLocation.enter("Consumable");
        if (sourceConsumable != null && targetConsumable != null) {
            //realmCode
            compareRealmCodes(sourceConsumable.getRealmCodes(),targetConsumable.getRealmCodes());
            //result Id
            typeIDComparison(sourceConsumable.getTypeId(),targetConsumable.getTypeId());
            //templateID
            compareTemplateID(sourceConsumable.getTemplateIds(),targetConsumable.getTemplateIds());
            //manufacturedProduct
            manufacturedProductComparison(sourceConsumable.getManufacturedProduct(),targetConsumable.getManufacturedProduct());
            //nullFlavor
            compareNullFlavor(sourceConsumable.getNullFlavor(),targetConsumable.getNullFlavor());
            //Type Code
            compareTypeCode(sourceConsumable.getTypeCode(),targetConsumable.getTypeCode());
        }
        currentLocation.exit();
    }

    private void productComparison(Product sourceProduct, Product targetProduct) {
        currentLocation.enter("Product");
        if (sourceProduct != null && targetProduct != null) {
            //realmCode
            compareRealmCodes(sourceProduct.getRealmCodes(),targetProduct.getRealmCodes());
            //result Id
            typeIDComparison(sourceProduct.getTypeId(),targetProduct.getTypeId());
            //templateID
            compareTemplateID(sourceProduct.getTemplateIds(),targetProduct.getTemplateIds());
            //manufacturedProduct
            manufacturedProductComparison(sourceProduct.getManufacturedProduct(),targetProduct.getManufacturedProduct());
            //nullFlavor
            compareNullFlavor(sourceProduct.getNullFlavor(),targetProduct.getNullFlavor());
            //Type Code
            compareTypeCode(sourceProduct.getTypeCode(),targetProduct.getTypeCode());
        }
        currentLocation.exit();
    }

    private void specimenRoleComparison(SpecimenRole sourceSpecimenRole, SpecimenRole targetSpecimenRole) {
        currentLocation.enter("Specimen Role");
        if (sourceSpecimenRole != null && targetSpecimenRole != null) {
            //realmCode
            compareRealmCodes(sourceSpecimenRole.getRealmCodes(),targetSpecimenRole.getRealmCodes());
            //result Id
            typeIDComparison(sourceSpecimenRole.getTypeId(),targetSpecimenRole.getTypeId());
            //templateID
            compareTemplateID(sourceSpecimenRole.getTemplateIds(),targetSpecimenRole.getTemplateIds());
            //id
            compareIDs(sourceSpecimenRole.getIds(),targetSpecimenRole.getIds());
            //specimenPlayingEntity - PlayingEntity
            comparePlayingEntity(sourceSpecimenRole.getSpecimenPlayingEntity(),targetSpecimenRole.getSpecimenPlayingEntity());
            //nullFlavor
            compareNullFlavor(sourceSpecimenRole.getNullFlavor(),targetSpecimenRole.getNullFlavor());
            //Class Code
            compareClassCode(sourceSpecimenRole.getClassCode(),targetSpecimenRole.getClassCode());
        }
        currentLocation.exit();
    }

    private void participantRoleComparison(ParticipantRole sourceParticipantRole, ParticipantRole targetParticipantRole) {
        currentLocation.enter("Participant Role");
        if (sourceParticipantRole != null && targetParticipantRole != null) {
            //realmCode
            compareRealmCodes(sourceParticipantRole.getRealmCodes(),targetParticipantRole.getRealmCodes());
            //result Id
            typeIDComparison(sourceParticipantRole.getTypeId(),targetParticipantRole.getTypeId());
            //templateID
            compareTemplateID(sourceParticipantRole.getTemplateIds(),targetParticipantRole.getTemplateIds());
            //id
            compareIDs(sourceParticipantRole.getIds(),targetParticipantRole.getIds());
            //code
            compareCode(sourceParticipantRole.getCode(),targetParticipantRole.getCode());
            //addr
            compareAddr(sourceParticipantRole.getAddrs(),targetParticipantRole.getAddrs());
            //telecom
            compareTelcom(sourceParticipantRole.getTelecoms(),targetParticipantRole.getTelecoms());
            //Choice - playingDevice (Device),
            deviceComparison(sourceParticipantRole.getPlayingDevice(), targetParticipantRole.getPlayingDevice());
            //Choice - playingEntity(PlayingEntity)
            comparePlayingEntity(sourceParticipantRole.getPlayingEntity(), targetParticipantRole.getPlayingEntity());
            //scopingEntity - Entity
            entityComparison(sourceParticipantRole.getScopingEntity(),targetParticipantRole.getScopingEntity());
            //nullFlavor
            compareNullFlavor(sourceParticipantRole.getNullFlavor(),targetParticipantRole.getNullFlavor());
            //Class Code
            compareClassCode(sourceParticipantRole.getClassCode(),targetParticipantRole.getClassCode());
        }
        currentLocation.exit();
    }

    private void externalActComparison(ExternalAct sourceExternalAct, ExternalAct targetExternalAct) {
        currentLocation.enter("External Act");
        if (sourceExternalAct != null && targetExternalAct != null) {
            //realmCode
            compareRealmCodes(sourceExternalAct.getRealmCodes(),targetExternalAct.getRealmCodes());
            //result Id
            typeIDComparison(sourceExternalAct.getTypeId(),targetExternalAct.getTypeId());
            //templateID
            compareTemplateID(sourceExternalAct.getTemplateIds(),targetExternalAct.getTemplateIds());
            //id
            compareIDs(sourceExternalAct.getIds(),targetExternalAct.getIds());
            //code
            compareCode(sourceExternalAct.getCode(),targetExternalAct.getCode());
            //text
            compareText(sourceExternalAct.getText(),targetExternalAct.getText());
            //nullFlavor
            compareNullFlavor(sourceExternalAct.getNullFlavor(),targetExternalAct.getNullFlavor());
            //Class Code
            compareClassCode(sourceExternalAct.getClassCode(),targetExternalAct.getClassCode());
            //Mood Code
            compareMoodCode(sourceExternalAct.getMoodCode(),targetExternalAct.getMoodCode());
        }
        currentLocation.exit();
    }

    private void externalObservationComparison(ExternalObservation sourceExternalObservation, ExternalObservation targetExternalObservation) {
        currentLocation.enter("External Observation");
        if (sourceExternalObservation != null && targetExternalObservation != null) {
            //realmCode
            compareRealmCodes(sourceExternalObservation.getRealmCodes(),targetExternalObservation.getRealmCodes());
            //result Id
            typeIDComparison(sourceExternalObservation.getTypeId(),targetExternalObservation.getTypeId());
            //templateID
            compareTemplateID(sourceExternalObservation.getTemplateIds(),targetExternalObservation.getTemplateIds());
            //id
            compareIDs(sourceExternalObservation.getIds(),targetExternalObservation.getIds());
            //code
            compareCode(sourceExternalObservation.getCode(),targetExternalObservation.getCode());
            //text
            compareText(sourceExternalObservation.getText(),targetExternalObservation.getText());
            //nullFlavor
            compareNullFlavor(sourceExternalObservation.getNullFlavor(),targetExternalObservation.getNullFlavor());
            //Class Code
            compareClassCode(sourceExternalObservation.getClassCode(),targetExternalObservation.getClassCode());
            //Mood Code
            compareMoodCode(sourceExternalObservation.getMoodCode(),targetExternalObservation.getMoodCode());
        }
        currentLocation.exit();
    }

    private void externalProcedureComparison(ExternalProcedure sourceExternalProcedure, ExternalProcedure targetExternalProcedure) {
        currentLocation.enter("External Procedure");
        if (sourceExternalProcedure != null && targetExternalProcedure != null) {
            //realmCode
            compareRealmCodes(sourceExternalProcedure.getRealmCodes(),targetExternalProcedure.getRealmCodes());
            //result Id
            typeIDComparison(sourceExternalProcedure.getTypeId(),targetExternalProcedure.getTypeId());
            //templateID
            compareTemplateID(sourceExternalProcedure.getTemplateIds(),targetExternalProcedure.getTemplateIds());
            //id
            compareIDs(sourceExternalProcedure.getIds(),targetExternalProcedure.getIds());
            //code
            compareCode(sourceExternalProcedure.getCode(),targetExternalProcedure.getCode());
            //text
            compareText(sourceExternalProcedure.getText(),targetExternalProcedure.getText());
            //nullFlavor
            compareNullFlavor(sourceExternalProcedure.getNullFlavor(),targetExternalProcedure.getNullFlavor());
            //Class Code
            compareClassCode(sourceExternalProcedure.getClassCode(),targetExternalProcedure.getClassCode());
            //Mood Code
            compareMoodCode(sourceExternalProcedure.getMoodCode(),targetExternalProcedure.getMoodCode());
        }
        currentLocation.exit();
    }

    private void externalDocumentComparison(ExternalDocument sourceExternalDocument, ExternalDocument targetExternalDocument) {
        currentLocation.enter("External Document");
        if (sourceExternalDocument != null && targetExternalDocument != null) {
            //realmCode
            compareRealmCodes(sourceExternalDocument.getRealmCodes(),targetExternalDocument.getRealmCodes());
            //result Id
            typeIDComparison(sourceExternalDocument.getTypeId(),targetExternalDocument.getTypeId());
            //templateID
            compareTemplateID(sourceExternalDocument.getTemplateIds(),targetExternalDocument.getTemplateIds());
            //id
            compareIDs(sourceExternalDocument.getIds(),targetExternalDocument.getIds());
            //code
            compareCode(sourceExternalDocument.getCode(),targetExternalDocument.getCode());
            //text
            compareText(sourceExternalDocument.getText(),targetExternalDocument.getText());
            //setID
            compareSetID(sourceExternalDocument.getSetId(),targetExternalDocument.getSetId());
            //versionNumber
            compareVersionNumber(sourceExternalDocument.getVersionNumber(),targetExternalDocument.getVersionNumber());
            //nullFlavor
            compareNullFlavor(sourceExternalDocument.getNullFlavor(),targetExternalDocument.getNullFlavor());
            //Class Code
            compareClassCode(sourceExternalDocument.getClassCode(),targetExternalDocument.getClassCode());
            //Mood Code
            compareMoodCode(sourceExternalDocument.getMoodCode(),targetExternalDocument.getMoodCode());
        }
        currentLocation.exit();
    }

    private void criterionComparison(Criterion sourceCriterion, Criterion targetCriterion) {
        currentLocation.enter("Criterion");
        if (sourceCriterion!= null && targetCriterion != null) {
            //realmCode
            compareRealmCodes(sourceCriterion.getRealmCodes(),targetCriterion.getRealmCodes());
            //result Id
            typeIDComparison(sourceCriterion.getTypeId(),targetCriterion.getTypeId());
            //templateID
            compareTemplateID(sourceCriterion.getTemplateIds(),targetCriterion.getTemplateIds());
            //code
            compareCode(sourceCriterion.getCode(),targetCriterion.getCode());
            //text
            compareText(sourceCriterion.getText(),targetCriterion.getText());
            //value
            compareValue(sourceCriterion.getValue(),targetCriterion.getValue());
            //nullFlavor
            compareNullFlavor(sourceCriterion.getNullFlavor(),targetCriterion.getNullFlavor());
            //Class Code
            compareClassCode(sourceCriterion.getClassCode(),targetCriterion.getClassCode());
            //Mood Code
            compareMoodCode(sourceCriterion.getMoodCode(),targetCriterion.getMoodCode());
        }
        currentLocation.exit();
    }

    private void observationRangeComparison(ObservationRange sourceObservationRange, ObservationRange targetObservationRange) {
        currentLocation.enter("Observation Range");
        if (sourceObservationRange != null && targetObservationRange != null) {
            //realmCode
            compareRealmCodes(sourceObservationRange.getRealmCodes(),targetObservationRange.getRealmCodes());
            //result Id
            typeIDComparison(sourceObservationRange.getTypeId(),targetObservationRange.getTypeId());
            //templateID
            compareTemplateID(sourceObservationRange.getTemplateIds(),targetObservationRange.getTemplateIds());
            //code
            compareCode(sourceObservationRange.getCode(),targetObservationRange.getCode());
            //text
            compareText(sourceObservationRange.getText(),targetObservationRange.getText());
            //value
            compareValue(sourceObservationRange.getValue(),targetObservationRange.getValue());
            //interpretaionCode
            compareCode(sourceObservationRange.getInterpretationCode(),targetObservationRange.getInterpretationCode());
            //nullFlavor
            compareNullFlavor(sourceObservationRange.getNullFlavor(),targetObservationRange.getNullFlavor());
            //Class Code
            compareClassCode(sourceObservationRange.getClassCode(),targetObservationRange.getClassCode());
            //Mood Code
            compareMoodCode(sourceObservationRange.getMoodCode(),targetObservationRange.getMoodCode());
        }
        currentLocation.exit();
    }

    private void manufacturedProductComparison(ManufacturedProduct sourceManufacturedProduct, ManufacturedProduct targetManufacturedProduct) {
        currentLocation.enter("Manufactured Product");
        if (sourceManufacturedProduct != null && targetManufacturedProduct != null) {
            //realmCode
            compareRealmCodes(sourceManufacturedProduct.getRealmCodes(), targetManufacturedProduct.getRealmCodes());
            //result Id
            typeIDComparison(sourceManufacturedProduct.getTypeId(), targetManufacturedProduct.getTypeId());
            //templateID
            compareTemplateID(sourceManufacturedProduct.getTemplateIds(), targetManufacturedProduct.getTemplateIds());
            //id
            compareIDs(sourceManufacturedProduct.getIds(), targetManufacturedProduct.getIds());
            //Choice - manufacturedLabeled(LabeledDrug)
            labeledDrugComparison(sourceManufacturedProduct.getManufacturedLabeledDrug(), targetManufacturedProduct.getManufacturedLabeledDrug());
            //Choice - manufacturedMaterial(Material)
            materialComparison(sourceManufacturedProduct.getManufacturedMaterial(), targetManufacturedProduct.getManufacturedMaterial());
            //manufacturerOrganization - Organization
            organizationComparison(sourceManufacturedProduct.getManufacturerOrganization(),targetManufacturedProduct.getManufacturerOrganization());
            //nullFlavor
            compareNullFlavor(sourceManufacturedProduct.getNullFlavor(),targetManufacturedProduct.getNullFlavor());
            //Class Code
            compareClassCode(sourceManufacturedProduct.getClassCode(),targetManufacturedProduct.getClassCode());
        }
        currentLocation.exit();
    }

    private void comparePlayingEntity(PlayingEntity sourcePlayingEntity, PlayingEntity targetPlayingIdentity) {
        currentLocation.enter("Playing Entity");
        if (sourcePlayingEntity != null && targetPlayingIdentity != null) {
            //realmCode
            compareRealmCodes(sourcePlayingEntity.getRealmCodes(),targetPlayingIdentity.getRealmCodes());
            //result Id
            typeIDComparison(sourcePlayingEntity.getTypeId(),targetPlayingIdentity.getTypeId());
            //templateID
            compareTemplateID(sourcePlayingEntity.getTemplateIds(),targetPlayingIdentity.getTemplateIds());
            //code
            compareCode(sourcePlayingEntity.getCode(),targetPlayingIdentity.getCode());
            //quantity
            compareQuantities(sourcePlayingEntity.getQuantities(),targetPlayingIdentity.getQuantities());
            //name
            compareNamesPN(sourcePlayingEntity.getNames(),targetPlayingIdentity.getNames());
            //desc
            compareText(sourcePlayingEntity.getDesc(),targetPlayingIdentity.getDesc());
            //nullFlavor
            compareNullFlavor(sourcePlayingEntity.getNullFlavor(),targetPlayingIdentity.getNullFlavor());
            //Class Code
            compareClassCode(sourcePlayingEntity.getClassCode(),targetPlayingIdentity.getClassCode());
            //determinerCode
            compareDeterminerCode(sourcePlayingEntity.getDeterminerCode(),targetPlayingIdentity.getDeterminerCode());
        }
        currentLocation.exit();
    }

    private void deviceComparison(Device sourceDevice, Device targetDevice) {
        currentLocation.enter("Device");
        if (sourceDevice != null && targetDevice != null) {
            //realmCode
            compareRealmCodes(sourceDevice.getRealmCodes(),targetDevice.getRealmCodes());
            //result Id
            typeIDComparison(sourceDevice.getTypeId(),targetDevice.getTypeId());
            //templateID
            compareTemplateID(sourceDevice.getTemplateIds(),targetDevice.getTemplateIds());
            //code
            compareCode(sourceDevice.getCode(),targetDevice.getCode());
            //ManufacturedModelName
            compareSCName(sourceDevice.getManufacturerModelName(),targetDevice.getManufacturerModelName());
            //software Name
            compareSCName(sourceDevice.getSoftwareName(),targetDevice.getSoftwareName());
            //nullFlavor
            compareNullFlavor(sourceDevice.getNullFlavor(),targetDevice.getNullFlavor());
            //Class Code
            compareClassCode(sourceDevice.getClassCode(),targetDevice.getClassCode());
            //determinerCode
            compareDeterminerCode(sourceDevice.getDeterminerCode(),targetDevice.getDeterminerCode());
        }
        currentLocation.exit();
    }

    private void entityComparison(Entity sourceEntity, Entity targetEntity) {
        currentLocation.enter("Entity");
        if (sourceEntity != null && targetEntity != null) {
            //realmCode
            compareRealmCodes(sourceEntity.getRealmCodes(),targetEntity.getRealmCodes());
            //result Id
            typeIDComparison(sourceEntity.getTypeId(),targetEntity.getTypeId());
            //templateID
            compareTemplateID(sourceEntity.getTemplateIds(),targetEntity.getTemplateIds());
            //id
            compareIDs(sourceEntity.getIds(),targetEntity.getIds());
            //code
            compareCode(sourceEntity.getCode(),targetEntity.getCode());
            //desc
            compareText(sourceEntity.getDesc(),targetEntity.getDesc());
            //nullFlavor
            compareNullFlavor(sourceEntity.getNullFlavor(),targetEntity.getNullFlavor());
            //Class Code
            compareClassCode(sourceEntity.getClassCode(),targetEntity.getClassCode());
            //Determiner Code
            compareDeterminerCode(sourceEntity.getDeterminerCode(),targetEntity.getDeterminerCode());
        }
        currentLocation.exit();
    }

    private void labeledDrugComparison(LabeledDrug sourceLabeledDrug, LabeledDrug targetLabeledDrug) {
        currentLocation.enter("Labeled Drug");
        if (sourceLabeledDrug != null && targetLabeledDrug != null) {
            //realmCode
            compareRealmCodes(sourceLabeledDrug.getRealmCodes(),targetLabeledDrug.getRealmCodes());
            //result Id
            typeIDComparison(sourceLabeledDrug.getTypeId(),targetLabeledDrug.getTypeId());
            //templateID
            compareTemplateID(sourceLabeledDrug.getTemplateIds(),targetLabeledDrug.getTemplateIds());
            //code
            compareCode(sourceLabeledDrug.getCode(),targetLabeledDrug.getCode());
            //name
            compareNameEN(sourceLabeledDrug.getName(),targetLabeledDrug.getName());
            //nullFlavor
            compareNullFlavor(sourceLabeledDrug.getNullFlavor(),targetLabeledDrug.getNullFlavor());
            //Class Code
            compareClassCode(sourceLabeledDrug.getClassCode(),targetLabeledDrug.getClassCode());
            //determinerCode
            compareDeterminerCode(sourceLabeledDrug.getDeterminerCode(),targetLabeledDrug.getDeterminerCode());
        }
        currentLocation.exit();
    }

    private void materialComparison(Material sourceMaterial, Material targetMaterial) {
        currentLocation.enter("Material");
        if (sourceMaterial != null && targetMaterial != null) {
            //realmCode
            compareRealmCodes(sourceMaterial.getRealmCodes(),targetMaterial.getRealmCodes());
            //result Id
            typeIDComparison(sourceMaterial.getTypeId(),targetMaterial.getTypeId());
            //templateID
            compareTemplateID(sourceMaterial.getTemplateIds(),targetMaterial.getTemplateIds());
            //code
            compareCode(sourceMaterial.getCode(),targetMaterial.getCode());
            //name
            compareNameEN(sourceMaterial.getName(),targetMaterial.getName());
            //lotNumberTest
            compareTitle(sourceMaterial.getLotNumberText(),targetMaterial.getLotNumberText());
            //nullFlavor
            compareNullFlavor(sourceMaterial.getNullFlavor(),targetMaterial.getNullFlavor());
            //Class Code
            compareClassCode(sourceMaterial.getClassCode(),targetMaterial.getClassCode());
            //determinerCode
            compareDeterminerCode(sourceMaterial.getDeterminerCode(),targetMaterial.getDeterminerCode());
        }
        currentLocation.exit();
    }









//    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    //non complex result comparison Methods
//    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void compareRealmCodes(EList<CS> source, EList<CS> target) {
        currentLocation.enter("Realm Codes");

        //initialize targetListObjects
        List<List<Object>> targetListObjects = target.stream()
                .map(cs -> new ArrayList<Object>() {{add(cs.getCode()); add(cs.getNullFlavor().getLiteral()); add(cs.getCodeSystemName()); add(cs.getCodeSystemVersion()); add(cs.getCodeSystem()); add(cs.getDisplayName());}} )
                .collect(Collectors.toList());

        //initialize sourceListObjects
        List<List<Object>> sourceListObjects = source.stream()
                .map(cs -> new ArrayList<Object>() {{add(cs.getCode()); add(cs.getNullFlavor().getLiteral()); add(cs.getCodeSystemName()); add(cs.getCodeSystemVersion()); add(cs.getCodeSystem()); add(cs.getDisplayName());}} )
                .collect(Collectors.toList());

        for (List<Object> sourceObjects : sourceListObjects) {
            comparisons.add(new Comparison(new ComparisonLocation(currentLocation), ComparisonUtility.ObjectListComparison(), sourceObjects,targetListObjects));
        }

        currentLocation.exit();
    }

    private void compareTemplateID(EList<II> source, EList<II> target) {
        currentLocation.enter("Template IDs");

        //initialize targetListObjects
        List<List<Object>> targetListObjects = target.stream()
                .map(ii -> new ArrayList<Object>() {{add(ii.getRoot()); add(ii.getNullFlavor().getLiteral()); add(ii.getAssigningAuthorityName()); add(ii.getExtension());}} )
                .collect(Collectors.toList());

        //initialize sourceListObjects
        List<List<Object>> sourceListObjects = source.stream()
                .map(ii -> new ArrayList<Object>() {{add(ii.getRoot()); add(ii.getNullFlavor().getLiteral()); add(ii.getAssigningAuthorityName()); add(ii.getExtension());}} )
                .collect(Collectors.toList());

        for (List<Object> sourceObjects : sourceListObjects) {
            comparisons.add(new Comparison(new ComparisonLocation(currentLocation), ComparisonUtility.ObjectListComparison(), sourceObjects,targetListObjects));
        }

        currentLocation.exit();
    }

    private void compareIDs(EList<II> source, EList<II> target) {
        currentLocation.enter("IDs");
        if (source.size() != 0 && target.size() != 0){
            if (source.get(0) != null && target.get(0) != null) {
                //initialize targetListObjects
                List<List<Object>> targetListObjects = target.stream()
                        .map(ii -> new ArrayList<Object>() {{
                            add(ii.getRoot());
                            add(ii.getNullFlavor().getLiteral());
                            add(ii.getAssigningAuthorityName());
                            add(ii.getExtension());
                        }})
                        .collect(Collectors.toList());

                //initialize sourceListObjects
                List<List<Object>> sourceListObjects = source.stream()
                        .map(ii -> new ArrayList<Object>() {{
                            add(ii.getRoot());
                            add(ii.getNullFlavor().getLiteral());
                            add(ii.getAssigningAuthorityName());
                            add(ii.getExtension());
                        }})
                        .collect(Collectors.toList());

                for (List<Object> sourceObjects : sourceListObjects) {
                    comparisons.add(new Comparison(new ComparisonLocation(currentLocation), ComparisonUtility.ObjectListComparison(), sourceObjects, targetListObjects));
                }
            }
        }

        currentLocation.exit();
    }

    private void compareAddr(EList<AD> source, EList<AD> target) {
        currentLocation.enter("Addr");

        //initialize targetListObjects
        List<List<Object>> targetListObjects = target.stream()
                .map(ad -> new ArrayList<Object>() {{add(ad.getText()); add(ad.getNullFlavor().getLiteral());}} )
                .collect(Collectors.toList());

        //initialize sourceListObjects
        List<List<Object>> sourceListObjects = source.stream()
                .map(ad -> new ArrayList<Object>() {{add(ad.getText()); add(ad.getNullFlavor().getLiteral());}} )
                .collect(Collectors.toList());

        for (List<Object> sourceObjects : sourceListObjects) {
            comparisons.add(new Comparison(new ComparisonLocation(currentLocation), ComparisonUtility.ObjectListComparison(), sourceObjects,targetListObjects));
        }

        currentLocation.exit();
    }

    private void compareTelcom(EList<TEL> source, EList<TEL> target) {
        currentLocation.enter("Telecom");

        //initialize targetListObjects
        List<List<Object>> targetListObjects = target.stream()
                .map(tel -> new ArrayList<Object>() {{add(tel.getValue()); add(tel.getNullFlavor().getLiteral());}} )
                .collect(Collectors.toList());

        //initialize sourceListObjects
        List<List<Object>> sourceListObjects = source.stream()
                .map(tel -> new ArrayList<Object>() {{add(tel.getValue()); add(tel.getNullFlavor().getLiteral());}} )
                .collect(Collectors.toList());

        for (List<Object> sourceObjects : sourceListObjects) {
            comparisons.add(new Comparison(new ComparisonLocation(currentLocation), ComparisonUtility.ObjectListComparison(), sourceObjects,targetListObjects));
        }

        currentLocation.exit();
    }

    private void compareNamesPN(EList<PN> source, EList<PN> target) {
        currentLocation.enter("Names");

        //initialize targetListObjects
        List<List<Object>> targetListObjects = target.stream()
                .map(pn -> new ArrayList<Object>() {{add(pn.getText()); add(pn.getNullFlavor().getLiteral());}} )
                .collect(Collectors.toList());

        //initialize sourceListObjects
        List<List<Object>> sourceListObjects = source.stream()
                .map(pn -> new ArrayList<Object>() {{add(pn.getText()); add(pn.getNullFlavor().getLiteral());}} )
                .collect(Collectors.toList());

        for (List<Object> sourceObjects : sourceListObjects) {
            comparisons.add(new Comparison(new ComparisonLocation(currentLocation), ComparisonUtility.ObjectListComparison(), sourceObjects,targetListObjects));
        }

        currentLocation.exit();
    }

    private void compareNamesEN(EList<EN> source, EList<EN> target) {
        currentLocation.enter("Names");

        //initialize targetListObjects
        List<List<Object>> targetListObjects = target.stream()
                .map(en -> new ArrayList<Object>() {{add(en.getText()); add(en.getNullFlavor().getLiteral());}} )
                .collect(Collectors.toList());

        //initialize sourceListObjects
        List<List<Object>> sourceListObjects = source.stream()
                .map(en -> new ArrayList<Object>() {{add(en.getText()); add(en.getNullFlavor().getLiteral());}} )
                .collect(Collectors.toList());

        for (List<Object> sourceObjects : sourceListObjects) {
            comparisons.add(new Comparison(new ComparisonLocation(currentLocation), ComparisonUtility.ObjectListComparison(), sourceObjects,targetListObjects));
        }

        currentLocation.exit();
    }

    private void compareNamesON(EList<ON> source, EList<ON> target) {
        currentLocation.enter("Names");

        //initialize targetListObjects
        List<List<Object>> targetListObjects = target.stream()
                .map(on -> new ArrayList<Object>() {{add(on.getText()); add(on.getNullFlavor().getLiteral());}} )
                .collect(Collectors.toList());

        //initialize sourceListObjects
        List<List<Object>> sourceListObjects = source.stream()
                .map(on -> new ArrayList<Object>() {{add(on.getText()); add(on.getNullFlavor().getLiteral());}} )
                .collect(Collectors.toList());

        for (List<Object> sourceObjects : sourceListObjects) {
            comparisons.add(new Comparison(new ComparisonLocation(currentLocation), ComparisonUtility.ObjectListComparison(), sourceObjects,targetListObjects));
        }

        currentLocation.exit();
    }

    private void compareEffectiveTime(EList<SXCM_TS> source, EList<SXCM_TS> target) {
        currentLocation.enter("Effective Time");

        //initialize targetListObjects
        List<List<Object>> targetListObjects = target.stream()
                .map(sxcm_ts -> new ArrayList<Object>() {{add(sxcm_ts.getValue()); add(sxcm_ts.getNullFlavor().getLiteral());}} )
                .collect(Collectors.toList());

        //initialize sourceListObjects
        List<List<Object>> sourceListObjects = source.stream()
                .map(sxcm_ts -> new ArrayList<Object>() {{add(sxcm_ts.getValue()); add(sxcm_ts.getNullFlavor().getLiteral());}} )
                .collect(Collectors.toList());

        for(List<Object> sourceObjects : sourceListObjects) {
            comparisons.add(new Comparison(new ComparisonLocation(currentLocation), ComparisonUtility.ObjectListComparison(),sourceObjects,targetListObjects));
        }

        currentLocation.exit();
    }

    private void compareID(II source, II target) {
        currentLocation.enter("ID");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getRoot(), target.getRoot()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getAssigningAuthorityName(), target.getAssigningAuthorityName()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getExtension(), target.getExtension()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getNullFlavor().getLiteral(), target.getNullFlavor().getLiteral()));
        }
        currentLocation.exit();
    }

    private void compareNullFlavor(NullFlavor source, NullFlavor target) {
        currentLocation.enter("Null Flavor");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
        }
        currentLocation.exit();
    }

    private void compareClassCode(RoleClass source, RoleClass target) {
        currentLocation.enter("Class Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareClassCode(EntityClassManufacturedMaterial source, EntityClassManufacturedMaterial target) {
        currentLocation.enter("Class Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareClassCode(EntityClassRoot source, EntityClassRoot target) {
        currentLocation.enter("Class Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();

    }

    private void compareClassCode(RoleClassManufacturedProduct source, RoleClassManufacturedProduct target) {
        currentLocation.enter("Class Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareClassCode(ActClassDocument source, ActClassDocument target) {
        currentLocation.enter("Class Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareClassCode(RoleClassRoot source, RoleClassRoot target) {
        currentLocation.enter("Class Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareClassCode(x_ActClassDocumentEntryAct source, x_ActClassDocumentEntryAct target) {
        currentLocation.enter("Class Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareClassCode(RoleClassSpecimen source, RoleClassSpecimen target) {
        currentLocation.enter("Class Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareClassCode(ActClassSupply source, ActClassSupply target) {
        currentLocation.enter("Class Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareClassCode(x_ActClassDocumentEntryOrganizer source, x_ActClassDocumentEntryOrganizer target) {
        currentLocation.enter("Class Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareClassCode(ActClassObservation source, ActClassObservation target) {
        currentLocation.enter("Class Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareClassCode(RoleClassServiceDeliveryLocation source, RoleClassServiceDeliveryLocation target) {
        currentLocation.enter("Class Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareClassCode(EntityClassPlace source, EntityClassPlace target) {
        currentLocation.enter("Class Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareClassCode(x_DocumentSubject source, x_DocumentSubject target) {
        currentLocation.enter("Class Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareClassCode(x_InformationRecipientRole source, x_InformationRecipientRole target) {
        currentLocation.enter("Class Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareClassCode(EntityClassOrganization source, EntityClassOrganization target) {
        currentLocation.enter("Class Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareClassCode(ActClassRoot source, ActClassRoot target) {
        currentLocation.enter("Class Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareClassCode(RoleClassAssociative source, RoleClassAssociative target) {
        currentLocation.enter("Class Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareClassCode(ActClinicalDocument source, ActClinicalDocument target) {
        currentLocation.enter("Class Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareClassCode(RoleClassAssignedEntity source, RoleClassAssignedEntity target) {
        currentLocation.enter("Class Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareClassCode(EntityClassDevice source, EntityClassDevice target) {
        currentLocation.enter("Class Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareClassCode(EntityClass source, EntityClass target) {
        currentLocation.enter("Class Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareClassCode(ActClass source, ActClass target) {
        currentLocation.enter("Class Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareClassCode(RoleClassMutualRelationship source, RoleClassMutualRelationship target) {
        currentLocation.enter("Class Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareCode(CE source, CE target) {
        currentLocation.enter("Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCode(), target.getCode()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getNullFlavor().getLiteral(), target.getNullFlavor().getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCodeSystem(), target.getCodeSystem()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCodeSystemName(), target.getCodeSystemName()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCodeSystemVersion(), target.getCodeSystemVersion()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getDisplayName(), target.getDisplayName()));
        }
        currentLocation.exit();
    }

    private void compareCode(CD source, CD target) {
        currentLocation.enter("Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCode(), target.getCode()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getNullFlavor().getLiteral(), target.getNullFlavor().getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCodeSystem(), target.getCodeSystem()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCodeSystemName(), target.getCodeSystemName()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCodeSystemVersion(), target.getCodeSystemVersion()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getDisplayName(), target.getDisplayName()));
        }
        currentLocation.exit();
    }

    private void compareConfidentialityCode(CE source, CE target) {
        currentLocation.enter("Confidentiality Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCode(), target.getCode()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getNullFlavor().getLiteral(), target.getNullFlavor().getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCodeSystem(), target.getCodeSystem()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCodeSystemName(), target.getCodeSystemName()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCodeSystemVersion(), target.getCodeSystemVersion()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getDisplayName(), target.getDisplayName()));
        }
        currentLocation.exit();
    }

    private void compareLanguageCode(CE source, CE target) {
        currentLocation.enter("Language Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCode(), target.getCode()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getNullFlavor().getLiteral(), target.getNullFlavor().getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCodeSystem(), target.getCodeSystem()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCodeSystemName(), target.getCodeSystemName()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCodeSystemVersion(), target.getCodeSystemVersion()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getDisplayName(), target.getDisplayName()));
        }
        currentLocation.exit();
    }

    private void compareTitle(ST source, ST target) {
        currentLocation.enter("Title");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getText(), target.getText()));
        }
        currentLocation.exit();
    }

    private void compareEffectiveTime(TS source, TS target) {
        currentLocation.enter("Effective Time");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getValue(), target.getValue()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getNullFlavor().getLiteral(), target.getNullFlavor().getLiteral()));
        }
        currentLocation.exit();
    }

    private void compareTypeCode(ParticipationType source, ParticipationType target) {
        currentLocation.enter("Type Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareTypeCode(ParticipationTargetSubject source, ParticipationTargetSubject target) {
        currentLocation.enter("Type Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareTypeCode(ParticipationPhysicalPerformer source, ParticipationPhysicalPerformer target) {
        currentLocation.enter("Type Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareTypeCode(x_ActRelationshipExternalReference source, x_ActRelationshipExternalReference target) {
        currentLocation.enter("Type Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareTypeCode(x_ActRelationshipEntryRelationship source, x_ActRelationshipEntryRelationship target) {
        currentLocation.enter("Type Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareTypeCode(ParticipationTargetLocation source, ParticipationTargetLocation target) {
        currentLocation.enter("Type Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareTypeCode(x_ActRelationshipEntry source, x_ActRelationshipEntry target) {
        currentLocation.enter("Type Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareTypeCode(ActRelationshipFulfills source, ActRelationshipFulfills target) {
        currentLocation.enter("Type Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareTypeCode(x_ServiceEventPerformer source, x_ServiceEventPerformer target) {
        currentLocation.enter("Type Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareTypeCode(x_ActRelationshipDocument source, x_ActRelationshipDocument target) {
        currentLocation.enter("Type Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareTypeCode(x_EncounterParticipant source, x_EncounterParticipant target) {
        currentLocation.enter("Type Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareTypeCode(x_InformationRecipient source, x_InformationRecipient target) {
        currentLocation.enter("Type Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareTypeCode(ActRelationshipHasComponent source, ActRelationshipHasComponent target) {
        currentLocation.enter("Type Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareTypeCode(ActRelationshipType source, ActRelationshipType target) {
        currentLocation.enter("Type Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareContextControlCode(ContextControl source, ContextControl target) {
        currentLocation.enter("Context Control Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareTime(TS source, TS target) {
        currentLocation.enter("Time");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getNullFlavor().getLiteral(), target.getNullFlavor().getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareSignatureCode(CS source, CS target) {
        currentLocation.enter("Signature Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCode(), target.getCode()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getNullFlavor().getLiteral(), target.getNullFlavor().getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCodeSystem(), target.getCodeSystem()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCodeSystemName(), target.getCodeSystemName()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCodeSystemVersion(), target.getCodeSystemVersion()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getDisplayName(), target.getDisplayName()));
        }
        currentLocation.exit();
    }

    private void compareFunctionCode(CE source, CE target) {
        currentLocation.enter("Function Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCode(), target.getCode()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getNullFlavor().getLiteral(), target.getNullFlavor().getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCodeSystem(), target.getCodeSystem()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCodeSystemName(), target.getCodeSystemName()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCodeSystemVersion(), target.getCodeSystemVersion()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getDisplayName(), target.getDisplayName()));
        }
        currentLocation.exit();
    }

    private void compareSetID(II source, II target) {
        currentLocation.enter("Set ID");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getRoot(), target.getRoot()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getAssigningAuthorityName(), target.getAssigningAuthorityName()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getExtension(), target.getExtension()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getNullFlavor().getLiteral(), target.getNullFlavor().getLiteral()));
        }
        currentLocation.exit();
    }

    private void compareVersionNumber(INT source, INT target) {
        currentLocation.enter("Version Number");
        if (source != null && target != null) {
            comparisons.add(new Comparison<BigInteger, BigInteger>(new ComparisonLocation(currentLocation), ComparisonUtility.BigIntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareCopyTime(TS source, TS target) {
        currentLocation.enter("Copy Time");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getNullFlavor().getLiteral(), target.getNullFlavor().getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareAdministrativeGenderCode(CE source, CE target) {
        currentLocation.enter("Administrative Gender Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getNullFlavor().getLiteral(), target.getNullFlavor().getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCode(), target.getCode()));
        }
        currentLocation.exit();
    }

    private void compareBirthTime(TS source, TS target) {
        currentLocation.enter("Birth Time");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getNullFlavor().getLiteral(), target.getNullFlavor().getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareMaritalStatusCode(CE source, CE target) {
        currentLocation.enter("Marital Status Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getNullFlavor().getLiteral(), target.getNullFlavor().getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCode(), target.getCode()));
        }
        currentLocation.exit();
    }

    private void compareReligiosAffiliationCode(CE source, CE target) {
        currentLocation.enter("Religious Affiliation Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCode(), target.getCode()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getNullFlavor().getLiteral(), target.getNullFlavor().getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCodeSystem(), target.getCodeSystem()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCodeSystemName(), target.getCodeSystemName()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCodeSystemVersion(), target.getCodeSystemVersion()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getDisplayName(), target.getDisplayName()));
        }
        currentLocation.exit();
    }

    private void compareRaceCode(CE source, CE target) {
        currentLocation.enter("Race Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCode(), target.getCode()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getNullFlavor().getLiteral(), target.getNullFlavor().getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCodeSystem(), target.getCodeSystem()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCodeSystemName(), target.getCodeSystemName()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCodeSystemVersion(), target.getCodeSystemVersion()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getDisplayName(), target.getDisplayName()));
        }
        currentLocation.exit();
    }

    private void compareEthnicGroupCode(CE source, CE target) {
        currentLocation.enter("Ethnic Group Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCode(), target.getCode()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getNullFlavor().getLiteral(), target.getNullFlavor().getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCodeSystem(), target.getCodeSystem()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCodeSystemName(), target.getCodeSystemName()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCodeSystemVersion(), target.getCodeSystemVersion()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getDisplayName(), target.getDisplayName()));
        }
        currentLocation.exit();
    }

    private void compareMoodCode(ActMood source, ActMood target) {
        currentLocation.enter("Mood Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareMoodCode(x_DocumentSubstanceMood source, x_DocumentSubstanceMood target) {
        currentLocation.enter("Mood Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareMoodCode(x_DocumentProcedureMood source, x_DocumentProcedureMood target) {
        currentLocation.enter("Mood Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareMoodCode(x_DocumentEncounterMood source, x_DocumentEncounterMood target) {
        currentLocation.enter("Mood Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareMoodCode(x_DocumentActMood source, x_DocumentActMood target) {
        currentLocation.enter("Mood Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareText(ED source, ED target) {
        currentLocation.enter("Text");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLanguage(), target.getLanguage()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getText(), target.getText()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getMediaType(), target.getMediaType()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getNullFlavor().getLiteral(), target.getNullFlavor().getLiteral()));
        }
        currentLocation.exit();
    }

    private void compareText(StrucDocText source, StrucDocText target) {
        currentLocation.enter("Text");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getText(), target.getText()));
        }
        currentLocation.exit();
    }

    private void compareDeterminerCode(EntityDeterminer source, EntityDeterminer target) {
        currentLocation.enter("Determiner Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareDeterminerCode(EntityDeterminerDetermined source, EntityDeterminerDetermined target) {
        currentLocation.enter("Determiner Code");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLiteral(), target.getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getName(), target.getName()));
            comparisons.add(new Comparison<Integer, Integer>(new ComparisonLocation(currentLocation), ComparisonUtility.IntegerComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareSCName(SC source, SC target) {
        currentLocation.enter("Name");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCode(), target.getCode()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getNullFlavor().getLiteral(), target.getNullFlavor().getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCodeSystem(), target.getCodeSystem()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCodeSystemName(), target.getCodeSystemName()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCodeSystemVersion(), target.getCodeSystemVersion()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getDisplayName(), target.getDisplayName()));
        }
        currentLocation.exit();
    }

    private void compareNameEN(EN source, EN target) {
        currentLocation.enter("Name");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getNullFlavor().getLiteral(), target.getNullFlavor().getLiteral()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getText(), target.getText()));
        }
        currentLocation.exit();
    }

    private void comparePreferenceInd(BL source, BL target) {
        currentLocation.enter("Preference Ind");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getNullFlavor().getLiteral(), target.getNullFlavor().getLiteral()));
            comparisons.add(new Comparison<Boolean, Boolean>(new ComparisonLocation(currentLocation), ComparisonUtility.BooleanComparison(), source.getValue(), target.getValue()));
        }
        currentLocation.exit();
    }

    private void compareDerivationExpr(ST source, ST target) {
        currentLocation.enter("Derivation Expr");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLanguage(), target.getLanguage()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getText(), target.getText()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getMediaType(), target.getMediaType()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getNullFlavor().getLiteral(), target.getNullFlavor().getLiteral()));
        }
        currentLocation.exit();
    }

    private void compareRepeatNumber(IVL_INT source, IVL_INT target) {
        currentLocation.enter("Repeat Number");
        if (source != null && target != null) {
            if (source.getCenter() != null && target.getCenter() != null) {
                comparisons.add(new Comparison<BigInteger, BigInteger>(new ComparisonLocation(currentLocation), ComparisonUtility.BigIntegerComparison(), source.getCenter().getValue(), target.getCenter().getValue()));
            }
            if (source.getHigh() != null && target.getHigh() != null) {
                comparisons.add(new Comparison<BigInteger, BigInteger>(new ComparisonLocation(currentLocation), ComparisonUtility.BigIntegerComparison(), source.getHigh().getValue(), target.getHigh().getValue()));
            }
            if (source.getLow() != null && target.getLow() != null) {
                comparisons.add(new Comparison<BigInteger, BigInteger>(new ComparisonLocation(currentLocation), ComparisonUtility.BigIntegerComparison(), source.getLow().getValue(), target.getLow().getValue()));
            }
            if (source.getWidth() != null && target.getWidth() != null) {
                comparisons.add(new Comparison<BigInteger, BigInteger>(new ComparisonLocation(currentLocation), ComparisonUtility.BigIntegerComparison(), source.getWidth().getValue(), target.getWidth().getValue()));
            }
            comparisons.add(new Comparison<BigInteger, BigInteger>(new ComparisonLocation(currentLocation), ComparisonUtility.BigIntegerComparison(), source.getValue(), target.getValue()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getNullFlavor().getLiteral(), target.getNullFlavor().getLiteral()));
        }
        currentLocation.exit();
    }

    private void compareValues(EList<ANY> source, EList<ANY> target) {
        currentLocation.enter("Values");
        //initialize targetListObjects
        List<List<Object>> targetListObjects = target.stream()
                .map(any -> new ArrayList<Object>() {{add(any.getNullFlavor().getLiteral());}} )
                .collect(Collectors.toList());

        //initialize sourceListObjects
        List<List<Object>> sourceListObjects = source.stream()
                .map(any -> new ArrayList<Object>() {{add(any.getNullFlavor().getLiteral());}} )
                .collect(Collectors.toList());

        for (List<Object> sourceObjects : sourceListObjects) {
            comparisons.add(new Comparison(new ComparisonLocation(currentLocation), ComparisonUtility.ObjectListComparison(), sourceObjects,targetListObjects));
        }
        currentLocation.exit();
    }

    private void compareValue(ANY source, ANY target) {
        currentLocation.enter("Value");
        if (source != null && target != null) {
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getNullFlavor().getLiteral(), target.getNullFlavor().getLiteral()));
        }
        currentLocation.exit();
    }

    private void compareCodes(EList<CE> source, EList<CE> target) {
        currentLocation.enter("Code");

        //initialize targetListObjects
        List<List<Object>> targetListObjects = target.stream()
                .map(ce -> new ArrayList<Object>() {{add(ce.getCode()); add(ce.getNullFlavor().getLiteral()); add(ce.getCodeSystemName()); add(ce.getCodeSystemVersion()); add(ce.getCodeSystem()); add(ce.getDisplayName());}} )
                .collect(Collectors.toList());

        //initialize sourceListObjects
        List<List<Object>> sourceListObjects = source.stream()
                .map(ce -> new ArrayList<Object>() {{add(ce.getCode()); add(ce.getNullFlavor().getLiteral()); add(ce.getCodeSystemName()); add(ce.getCodeSystemVersion()); add(ce.getCodeSystem()); add(ce.getDisplayName());}} )
                .collect(Collectors.toList());

        for (List<Object> sourceObjects : sourceListObjects) {
            comparisons.add(new Comparison(new ComparisonLocation(currentLocation), ComparisonUtility.ObjectListComparison(), sourceObjects,targetListObjects));
        }

        currentLocation.exit();
    }

    private void compareCodesCD(EList<CD> source, EList<CD> target) {
        currentLocation.enter("Code");

        //initialize targetListObjects
        List<List<Object>> targetListObjects = target.stream()
                .map(cd -> new ArrayList<Object>() {{add(cd.getCode()); add(cd.getNullFlavor().getLiteral()); add(cd.getCodeSystemName()); add(cd.getCodeSystemVersion()); add(cd.getCodeSystem()); add(cd.getDisplayName());}} )
                .collect(Collectors.toList());

        //initialize sourceListObjects
        List<List<Object>> sourceListObjects = source.stream()
                .map(cd -> new ArrayList<Object>() {{add(cd.getCode()); add(cd.getNullFlavor().getLiteral()); add(cd.getCodeSystemName()); add(cd.getCodeSystemVersion()); add(cd.getCodeSystem()); add(cd.getDisplayName());}} )
                .collect(Collectors.toList());

        for (List<Object> sourceObjects : sourceListObjects) {
            comparisons.add(new Comparison(new ComparisonLocation(currentLocation), ComparisonUtility.ObjectListComparison(), sourceObjects,targetListObjects));
        }

        currentLocation.exit();
    }

    private void compareTargetSiteCode(EList<CD> source, EList<CD> target) {
        currentLocation.enter("Target Site Code");

        //initialize targetListObjects
        List<List<Object>> targetListObjects = target.stream()
                .map(cd -> new ArrayList<Object>() {{add(cd.getCode()); add(cd.getNullFlavor().getLiteral()); add(cd.getCodeSystemName()); add(cd.getCodeSystemVersion()); add(cd.getCodeSystem()); add(cd.getDisplayName());}} )
                .collect(Collectors.toList());

        //initialize sourceListObjects
        List<List<Object>> sourceListObjects = source.stream()
                .map(cd -> new ArrayList<Object>() {{add(cd.getCode()); add(cd.getNullFlavor().getLiteral()); add(cd.getCodeSystemName()); add(cd.getCodeSystemVersion()); add(cd.getCodeSystem()); add(cd.getDisplayName());}} )
                .collect(Collectors.toList());

        for (List<Object> sourceObjects : sourceListObjects) {
            comparisons.add(new Comparison(new ComparisonLocation(currentLocation), ComparisonUtility.ObjectListComparison(), sourceObjects,targetListObjects));
        }

        currentLocation.exit();
    }

    private void compareDose(IVL_PQ source, IVL_PQ target) {
        currentLocation.enter("Dose");
        if (source != null && target != null) {
            if (source.getCenter() != null && target.getCenter() != null) {
                comparisons.add(new Comparison<BigDecimal, BigDecimal>(new ComparisonLocation(currentLocation), ComparisonUtility.BigDecimalComparison(), source.getCenter().getValue(), target.getCenter().getValue()));
            }
            if (source.getHigh() != null && target.getHigh() != null) {
                comparisons.add(new Comparison<BigDecimal, BigDecimal>(new ComparisonLocation(currentLocation), ComparisonUtility.BigDecimalComparison(), source.getHigh().getValue(), target.getHigh().getValue()));
            }
            if (source.getLow() != null && target.getLow() != null) {
                comparisons.add(new Comparison<BigDecimal, BigDecimal>(new ComparisonLocation(currentLocation), ComparisonUtility.BigDecimalComparison(), source.getLow().getValue(), target.getLow().getValue()));
            }
            if (source.getWidth() != null && target.getWidth() != null) {
                comparisons.add(new Comparison<BigDecimal, BigDecimal>(new ComparisonLocation(currentLocation), ComparisonUtility.BigDecimalComparison(), source.getWidth().getValue(), target.getWidth().getValue()));
            }
            comparisons.add(new Comparison<BigDecimal, BigDecimal>(new ComparisonLocation(currentLocation), ComparisonUtility.BigDecimalComparison(), source.getValue(), target.getValue()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getNullFlavor().getLiteral(), target.getNullFlavor().getLiteral()));
        }
        currentLocation.exit();
    }

    private void compareMaxDose(RTO_PQ_PQ source, RTO_PQ_PQ target) {
        currentLocation.enter("Max Dose");
        if (source != null && target != null) {
            if (source.getDenominator() != null && target.getDenominator() != null) {
                comparisons.add(new Comparison<BigDecimal, BigDecimal>(new ComparisonLocation(currentLocation), ComparisonUtility.BigDecimalComparison(), source.getDenominator().getValue(), target.getDenominator().getValue()));
            }
            if (source.getNumerator() != null && target.getNumerator() != null) {
                comparisons.add(new Comparison<BigDecimal, BigDecimal>(new ComparisonLocation(currentLocation), ComparisonUtility.BigDecimalComparison(), source.getNumerator().getValue(), target.getNumerator().getValue()));
            }
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getNullFlavor().getLiteral(), target.getNullFlavor().getLiteral()));
        }
        currentLocation.exit();
    }

    private void compareIDAttribute(String source, String target) {
        currentLocation.enter("ID Attribute");
        comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source, target));
        currentLocation.exit();
    }

    private void compareQuantity(PQ source, PQ target) {
        currentLocation.enter("Quantity");
        if (source != null && target != null) {
            comparisons.add(new Comparison<BigDecimal, BigDecimal>(new ComparisonLocation(currentLocation), ComparisonUtility.BigDecimalComparison(), source.getValue(), target.getValue()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getUnit(), target.getUnit()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getNullFlavor().getLiteral(), target.getNullFlavor().getLiteral()));
        }
        currentLocation.exit();
    }

    private void compareQuantities(EList<PQ> source, EList<PQ> target) {
        currentLocation.enter("Quantities");

        //initialize targetListObjects
        List<List<Object>> targetListObjects = target.stream()
                .map(ce -> new ArrayList<Object>() {{add(ce.getValue()); add(ce.getNullFlavor().getLiteral()); add(ce.getUnit());}} )
                .collect(Collectors.toList());

        //initialize sourceListObjects
        List<List<Object>> sourceListObjects = source.stream()
                .map(ce -> new ArrayList<Object>() {{add(ce.getValue()); add(ce.getNullFlavor().getLiteral()); add(ce.getUnit());}} )
                .collect(Collectors.toList());

        for (List<Object> sourceObjects : sourceListObjects) {
            comparisons.add(new Comparison(new ComparisonLocation(currentLocation), ComparisonUtility.ObjectListComparison(), sourceObjects,targetListObjects));
        }

        currentLocation.exit();
    }

    private void compareExpectedUseTime(IVL_TS source, IVL_TS target) {
        currentLocation.enter("Expected Use Time");
        if (source != null && target != null) {
            if (source.getCenter() != null && target.getCenter() != null) {
                comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getCenter().getValue(), target.getCenter().getValue()));
            }
            if (source.getHigh() != null && target.getHigh() != null) {
                comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getHigh().getValue(), target.getHigh().getValue()));
            }
            if (source.getLow() != null && target.getLow() != null) {
                comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getLow().getValue(), target.getLow().getValue()));
            }
            if (source.getWidth() != null && target.getWidth() != null) {
                comparisons.add(new Comparison<BigDecimal, BigDecimal>(new ComparisonLocation(currentLocation), ComparisonUtility.BigDecimalComparison(), source.getWidth().getValue(), target.getWidth().getValue()));
            }
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getValue(), target.getValue()));
            comparisons.add(new Comparison<String, String>(new ComparisonLocation(currentLocation), ComparisonUtility.StringComparison(), source.getNullFlavor().getLiteral(), target.getNullFlavor().getLiteral()));
        }
        currentLocation.exit();
    }

    private void compareNegationInd(Boolean source, Boolean target) {
        currentLocation.enter("Negation Ind");
        comparisons.add(new Comparison<Boolean,Boolean>(new ComparisonLocation(currentLocation), ComparisonUtility.BooleanComparison(),source,target));
        currentLocation.exit();
    }

    private void compareContextConductionInd(Boolean source, Boolean target) {
        currentLocation.enter("Context Conduction Ind");
        comparisons.add(new Comparison<Boolean, Boolean>(new ComparisonLocation(currentLocation), ComparisonUtility.BooleanComparison(), source, target));
        currentLocation.exit();
    }

    private void compareInversionInd(Boolean source, Boolean target) {
        currentLocation.enter("Inversion Ind");
        comparisons.add(new Comparison<Boolean, Boolean>(new ComparisonLocation(currentLocation), ComparisonUtility.BooleanComparison(), source, target));
        currentLocation.exit();
    }

}
