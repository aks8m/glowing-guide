package com.github.aks8m.compare;

import com.github.aks8m.compare.report.ComparisonReport;
import com.github.aks8m.compare.report.result.Mismatch;
import com.github.aks8m.compare.report.result.Warning;
import javafx.concurrent.Task;
import org.eclipse.emf.common.util.EList;
import org.openhealthtools.mdht.uml.cda.*;
import org.openhealthtools.mdht.uml.hl7.datatypes.*;
import org.openhealthtools.mdht.uml.hl7.vocab.*;

import java.util.List;

public class MDHTCompareService extends CompareService{

    private ClinicalDocument sourceClinicalDocument;
    private ClinicalDocument targetClinicalDocument;
    private final double PROGRESS_MAX_VALUE = 100.00;
    private final double PROGRESS_INCREMENT = 3.44;
    private double currentProgressValue = 0.0;
    private ComparisonReport comparisonReport;


    public MDHTCompareService(ClinicalDocument sourceClinicalDocument, ClinicalDocument targetClinicalDocument) {
        this.sourceClinicalDocument = sourceClinicalDocument;
        this.targetClinicalDocument = targetClinicalDocument;
    }

    private double computeProgress(double incrementValue){
        if (this.currentProgressValue == 0) {
            this.currentProgressValue = incrementValue;
        } else {
            this.currentProgressValue += incrementValue;
        }
        return this.currentProgressValue;
    }

    @Override
    protected Task<ComparisonReport> createTask() {

        //create a new report for each restart of the service
        this.comparisonReport = new ComparisonReport();

        return new Task<ComparisonReport>() {

            @Override
            protected ComparisonReport call() throws Exception {

                //compare RealmCode
                compareRealmCodes(sourceClinicalDocument.getRealmCodes(),targetClinicalDocument.getRealmCodes());
                updateProgress(computeProgress(PROGRESS_INCREMENT), PROGRESS_MAX_VALUE);

                //compare typeID
                typeIDComparison(sourceClinicalDocument.getTypeId(), targetClinicalDocument.getTypeId());
                updateProgress(computeProgress(PROGRESS_INCREMENT), PROGRESS_MAX_VALUE);


                //compare templateID
                compareTemplateID(sourceClinicalDocument.getTemplateIds(), targetClinicalDocument.getTemplateIds());
                updateProgress(computeProgress(PROGRESS_INCREMENT), PROGRESS_MAX_VALUE);

                //compareID
                compareID(sourceClinicalDocument.getId(),targetClinicalDocument.getId());
                updateProgress(computeProgress(PROGRESS_INCREMENT), PROGRESS_MAX_VALUE);

                //compare code
                compareCode(sourceClinicalDocument.getCode(),targetClinicalDocument.getCode());
                updateProgress(computeProgress(PROGRESS_INCREMENT), PROGRESS_MAX_VALUE);

                //compare title
                compareTitle(sourceClinicalDocument.getTitle(),targetClinicalDocument.getTitle());
                updateProgress(computeProgress(PROGRESS_INCREMENT), PROGRESS_MAX_VALUE);

                //compare Effective Time
                compareEffectiveTime(sourceClinicalDocument.getEffectiveTime(),targetClinicalDocument.getEffectiveTime());
                updateProgress(computeProgress(PROGRESS_INCREMENT), PROGRESS_MAX_VALUE);

                //compare confidentiality code
                compareConfidentialityCode(sourceClinicalDocument.getConfidentialityCode(),targetClinicalDocument.getConfidentialityCode());
                updateProgress(computeProgress(PROGRESS_INCREMENT), PROGRESS_MAX_VALUE);

                //compare language code
                compareLanguageCode(sourceClinicalDocument.getLanguageCode(),targetClinicalDocument.getLanguageCode());
                updateProgress(computeProgress(PROGRESS_INCREMENT), PROGRESS_MAX_VALUE);

                //compare setID
                compareSetID(sourceClinicalDocument.getSetId(),targetClinicalDocument.getSetId());
                updateProgress(computeProgress(PROGRESS_INCREMENT), PROGRESS_MAX_VALUE);

                //compare versionNumber
                compareVersionNumber(sourceClinicalDocument.getVersionNumber(),targetClinicalDocument.getVersionNumber());
                updateProgress(computeProgress(PROGRESS_INCREMENT), PROGRESS_MAX_VALUE);

                //compare copyTime
                compareCopyTime(sourceClinicalDocument.getCopyTime(),targetClinicalDocument.getCopyTime());
                updateProgress(computeProgress(PROGRESS_INCREMENT), PROGRESS_MAX_VALUE);

                //compare Record Targets
                recordTargetsComparison(sourceClinicalDocument.getRecordTargets(), targetClinicalDocument.getRecordTargets());
                updateProgress(computeProgress(PROGRESS_INCREMENT), PROGRESS_MAX_VALUE);

                //compare Authors
                authorsComparison(sourceClinicalDocument.getAuthors(), targetClinicalDocument.getAuthors());
                updateProgress(computeProgress(PROGRESS_INCREMENT), PROGRESS_MAX_VALUE);

                //compare Data Enterer
                dataEntererComparison(sourceClinicalDocument.getDataEnterer(), targetClinicalDocument.getDataEnterer());
                updateProgress(computeProgress(PROGRESS_INCREMENT), PROGRESS_MAX_VALUE);

                //compare informants
                informantsComparison(sourceClinicalDocument.getInformants(), targetClinicalDocument.getInformants());
                updateProgress(computeProgress(PROGRESS_INCREMENT), PROGRESS_MAX_VALUE);

                //compare custodian
                custodianComparison(sourceClinicalDocument.getCustodian(), targetClinicalDocument.getCustodian());
                updateProgress(computeProgress(PROGRESS_INCREMENT), PROGRESS_MAX_VALUE);

                //compare information recipient
                informationRecipientComparison(sourceClinicalDocument.getInformationRecipients(),targetClinicalDocument.getInformationRecipients());
                updateProgress(computeProgress(PROGRESS_INCREMENT), PROGRESS_MAX_VALUE);

                //compare Legal Authenticator
                legalAuthenticatorComparison(sourceClinicalDocument.getLegalAuthenticator(), targetClinicalDocument.getLegalAuthenticator());
                updateProgress(computeProgress(PROGRESS_INCREMENT), PROGRESS_MAX_VALUE);

                //compare Authenticators
                authenticatorComparison(sourceClinicalDocument.getAuthenticators(),targetClinicalDocument.getAuthenticators());
                updateProgress(computeProgress(PROGRESS_INCREMENT), PROGRESS_MAX_VALUE);

                //compare participants
                participants1Comparison(sourceClinicalDocument.getParticipants(),targetClinicalDocument.getParticipants());
                updateProgress(computeProgress(PROGRESS_INCREMENT), PROGRESS_MAX_VALUE);

                //compare getInFulfullmentOf
                inFulfillmentOfComparison(sourceClinicalDocument.getInFulfillmentOfs(),targetClinicalDocument.getInFulfillmentOfs());
                updateProgress(computeProgress(PROGRESS_INCREMENT), PROGRESS_MAX_VALUE);

                //compare documentationOf
                documentationOfsComparison(sourceClinicalDocument.getDocumentationOfs(), targetClinicalDocument.getDocumentationOfs());
                updateProgress(computeProgress(PROGRESS_INCREMENT), PROGRESS_MAX_VALUE);

                //compare getRelatedDocuments
                relatedDocumentsComparison(sourceClinicalDocument.getRelatedDocuments(),targetClinicalDocument.getRelatedDocuments());
                updateProgress(computeProgress(PROGRESS_INCREMENT), PROGRESS_MAX_VALUE);

                //compare Authorizations
                authorizationsComparison(sourceClinicalDocument.getAuthorizations(),targetClinicalDocument.getAuthorizations());
                updateProgress(computeProgress(PROGRESS_INCREMENT), PROGRESS_MAX_VALUE);

                //compare componentOf
                componentOfComparison(sourceClinicalDocument.getComponentOf(),targetClinicalDocument.getComponentOf());
                updateProgress(computeProgress(PROGRESS_INCREMENT), PROGRESS_MAX_VALUE);

                //compare component
                component2Comparison(sourceClinicalDocument.getComponent(), targetClinicalDocument.getComponent());
                updateProgress(computeProgress(PROGRESS_INCREMENT), PROGRESS_MAX_VALUE);

                //compare nullFlavor
                compareNullFlavor(sourceClinicalDocument.getNullFlavor(),targetClinicalDocument.getNullFlavor());
                updateProgress(computeProgress(PROGRESS_INCREMENT), PROGRESS_MAX_VALUE);

                //compare classCode
                compareClassCode(sourceClinicalDocument.getClassCode(),targetClinicalDocument.getClassCode());
                updateProgress(computeProgress(PROGRESS_INCREMENT), PROGRESS_MAX_VALUE);

                System.out.println("");
                return null;
            }
        };
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //complex result comparison Methods
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private boolean typeIDComparison(InfrastructureRootTypeId sourceTypeID, InfrastructureRootTypeId targetTypeID) {
        boolean matched = true;
        if (sourceTypeID != null && targetTypeID != null) {
            if (sourceTypeID.getRoot() != null && targetTypeID.getRoot() != null) {
                if (!sourceTypeID.getRoot().equals(targetTypeID.getRoot())) {
                    comparisonReport.addMismatch(new Mismatch(" Type ID: Root",sourceTypeID.getRoot(),targetTypeID.getRoot()));
                    matched = false;
                }
            }
            if (sourceTypeID.getAssigningAuthorityName() != null && targetTypeID.getAssigningAuthorityName() != null) {
                if (!sourceTypeID.getAssigningAuthorityName().equals(targetTypeID.getAssigningAuthorityName())) {
                    comparisonReport.addMismatch(new Mismatch(" Type ID: Assigning Authority Name", sourceTypeID.getAssigningAuthorityName(), targetTypeID.getAssigningAuthorityName()));

                    matched = false;
                }
            }
            if (sourceTypeID.getExtension() != null && targetTypeID.getExtension() != null) {
                if (!sourceTypeID.getExtension().equals(targetTypeID.getExtension())) {
                    comparisonReport.addMismatch(new Mismatch(" Type ID: Extension", sourceTypeID.getExtension(), targetTypeID.getExtension()));

                    matched = false;
                }
            }

        }
        return matched;
    }

    private boolean dataEntererComparison(DataEnterer sourceDataEnterer, DataEnterer targetDataEnterer) {
        boolean errorExists = false;
        if (sourceDataEnterer == null && targetDataEnterer == null) {
            return true;
        } else if (sourceDataEnterer != null && targetDataEnterer != null) {
            //compare realm codes
            if (!compareRealmCodes(sourceDataEnterer.getRealmCodes(),targetDataEnterer.getRealmCodes())) {
                errorExists = true;
            }
            //compare typeID
            if (!typeIDComparison(sourceDataEnterer.getTypeId(),targetDataEnterer.getTypeId())) {
                errorExists = true;
            }
            //compare templateID
            if (!compareTemplateID(sourceDataEnterer.getTemplateIds(), targetDataEnterer.getTemplateIds())) {
                errorExists = true;
            }
            //compare time
            if (!compareTime(sourceDataEnterer.getTime(),targetDataEnterer.getTime())) {
                errorExists = true;
            }
            //compare assigned entity
            if (!assignedEntityComparison(sourceDataEnterer.getAssignedEntity(),targetDataEnterer.getAssignedEntity())) {
                errorExists = true;
            }
            //compare nullFlavor
            if (!compareNullFlavor(sourceDataEnterer.getNullFlavor(),targetDataEnterer.getNullFlavor())) {
                errorExists = true;
            }
            //compare typeCode
            if (!compareTypeCode(sourceDataEnterer.getTypeCode(),targetDataEnterer.getTypeCode())) {
                errorExists = true;
            }
            return !errorExists;

        }

        return false;

    }

    private boolean custodianComparison(Custodian sourceCustodian, Custodian targetCustodian) {
        boolean errorExists = false;
        if (sourceCustodian == null && targetCustodian == null) {
            return true;
        } else if (sourceCustodian != null && targetCustodian != null) {
            //realm code
            if (!compareRealmCodes(sourceCustodian.getRealmCodes(),targetCustodian.getRealmCodes())) {
                errorExists = true;
            }
            //typeID
            if (!typeIDComparison(sourceCustodian.getTypeId(),targetCustodian.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceCustodian.getTemplateIds(), targetCustodian.getTemplateIds())) {
                errorExists = true;
            }
            //AssignedCustodian
            if (!assignedCustodianComparison(sourceCustodian.getAssignedCustodian(),targetCustodian.getAssignedCustodian())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceCustodian.getNullFlavor(),targetCustodian.getNullFlavor())) {
                errorExists = true;
            }
            //typeCode
            if (!compareTypeCode(sourceCustodian.getTypeCode(),targetCustodian.getTypeCode()))
            {
                errorExists = true;
            }
            return !errorExists;

        }
        return false;
    }

    private boolean legalAuthenticatorComparison(LegalAuthenticator sourceLegalAuthenticator, LegalAuthenticator targetLegalAuthenticator) {
        boolean errorExists = false;
        if (sourceLegalAuthenticator == null && targetLegalAuthenticator == null) {
            return true;
        } else if (sourceLegalAuthenticator != null && targetLegalAuthenticator != null) {
            //realmCode
            if (!compareRealmCodes(sourceLegalAuthenticator.getRealmCodes(),targetLegalAuthenticator.getRealmCodes())) {
                errorExists = true;
            }
            //compare typeID
            if (!typeIDComparison(sourceLegalAuthenticator.getTypeId(),targetLegalAuthenticator.getTypeId())) {
                errorExists = true;
            }
            //compare templateID
            if (!compareTemplateID(sourceLegalAuthenticator.getTemplateIds(), targetLegalAuthenticator.getTemplateIds())) {
                errorExists = true;
            }
            //compare time
            if (!compareTime(sourceLegalAuthenticator.getTime(),targetLegalAuthenticator.getTime())) {
                errorExists = true;
            }
            //compare signatureCode
            if (!compareSignatureCode(sourceLegalAuthenticator.getSignatureCode(),targetLegalAuthenticator.getSignatureCode())) {
                errorExists = true;
            }
            //compare assignedEntity
            if (!assignedEntityComparison(sourceLegalAuthenticator.getAssignedEntity(),targetLegalAuthenticator.getAssignedEntity())) {
                errorExists = true;
            }
            //compare nullFlavor
            if (!compareNullFlavor(sourceLegalAuthenticator.getNullFlavor(),targetLegalAuthenticator.getNullFlavor())) {
                errorExists = true;
            }
            //compare typeCode
            if (!compareTypeCode(sourceLegalAuthenticator.getTypeCode(),targetLegalAuthenticator.getTypeCode())) {
                errorExists = true;
            }
            //compare contextControlCode
            if (!compareContextControlCode(sourceLegalAuthenticator.getContextControlCode(),targetLegalAuthenticator.getContextControlCode()))
            {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;

    }

    private boolean component2Comparison(Component2 sourceComponent, Component2 targetComponent) {
        boolean errorExists = false;
        if (sourceComponent == null && targetComponent == null) {
            return true;
        } else if (sourceComponent != null && targetComponent != null) {
            //realmCode
            if (!compareRealmCodes(sourceComponent.getRealmCodes(),targetComponent.getRealmCodes())) {
                errorExists = true;
            }
            //typeID
            if (!typeIDComparison(sourceComponent.getTypeId(),targetComponent.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceComponent.getTemplateIds(), targetComponent.getTemplateIds())) {
                errorExists = true;
            }
            //Choice - Non XML Body, StructuredBody
            if (!(sourceComponent.getNonXMLBody() == null && targetComponent.getNonXMLBody() == null
                    && sourceComponent.getStructuredBody() == null && targetComponent.getStructuredBody() == null)) {
                if (!((nonXMLBodyComparison(sourceComponent.getNonXMLBody(), targetComponent.getNonXMLBody()) && sourceComponent.getNonXMLBody() != null)
                        || (structuredBodyComparison(sourceComponent.getStructuredBody(), targetComponent.getStructuredBody()) && sourceComponent.getStructuredBody() != null))) {
                    errorExists = true;
                }
            }
            //nullFlavor
            if (!compareNullFlavor(sourceComponent.getNullFlavor(),targetComponent.getNullFlavor())) {
                errorExists = true;
            }
            //typeCode
            if (!compareTypeCode(sourceComponent.getTypeCode(),targetComponent.getTypeCode())) {
                errorExists = true;
            }
            //contextConductionInd
            if (sourceComponent.getContextConductionInd() != targetComponent.getContextConductionInd()) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean patientRoleComparison(PatientRole sourcePatientRole, PatientRole targetPatientRole) {
        boolean errorExists = false;
        if (sourcePatientRole == null && targetPatientRole == null) {
            return true;
        } else if (sourcePatientRole != null && targetPatientRole != null) {
            //compare realmCode
            if (!compareRealmCodes(sourcePatientRole.getRealmCodes(),targetPatientRole.getRealmCodes())) {
                errorExists = true;
            }
            //compare typeID
            if (!typeIDComparison(sourcePatientRole.getTypeId(),targetPatientRole.getTypeId())) {
                errorExists = true;
            }
            //compare templateID
            if (!compareTemplateID(sourcePatientRole.getTemplateIds(), targetPatientRole.getTemplateIds())) {
                errorExists = true;
            }
            //compare id
            if (!compareIDs(sourcePatientRole.getIds(),targetPatientRole.getIds())) {
                errorExists = true;
            }
            //compare addr
            if (!compareAddr(sourcePatientRole.getAddrs(),targetPatientRole.getAddrs())) {
                errorExists = true;
            }
            //compare telecom
            if (!compareTelcom(sourcePatientRole.getTelecoms(),targetPatientRole.getTelecoms())) {
                errorExists = true;
            }
            //compare Patient
            if (!patientComparison(sourcePatientRole.getPatient(), targetPatientRole.getPatient())) {
                errorExists = true;
            }
            //compareNullFlavor
            if (!compareNullFlavor(sourcePatientRole.getNullFlavor(),targetPatientRole.getNullFlavor())) {
                errorExists = true;
            }
            //compare ClassCode
            if (!compareClassCode(sourcePatientRole.getClassCode(),targetPatientRole.getClassCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }


    private boolean assignedAuthorComparison(AssignedAuthor sourceAssignedAuthor, AssignedAuthor targetAssignedAuthor) {
        boolean errorExists = false;
        if (sourceAssignedAuthor == null && targetAssignedAuthor == null) {
            return true;
        } else if (sourceAssignedAuthor != null && targetAssignedAuthor != null) {
            //realm Code
            if (!compareRealmCodes(sourceAssignedAuthor.getRealmCodes(),targetAssignedAuthor.getRealmCodes())) {
                errorExists = true;
            }
            //result ID
            if (!typeIDComparison(sourceAssignedAuthor.getTypeId(),targetAssignedAuthor.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceAssignedAuthor.getTemplateIds(), targetAssignedAuthor.getTemplateIds())) {
                errorExists = true;
            }
            //ids
            if (!compareIDs(sourceAssignedAuthor.getIds(),targetAssignedAuthor.getIds())) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceAssignedAuthor.getCode(),targetAssignedAuthor.getCode())) {
                errorExists = true;
            }
            //addr
            if (!compareAddr(sourceAssignedAuthor.getAddrs(),targetAssignedAuthor.getAddrs())) {
                errorExists = true;
            }
            //telecom
            if (!compareTelcom(sourceAssignedAuthor.getTelecoms(),targetAssignedAuthor.getTelecoms())) {
                errorExists = true;
            }
            //choice - assignedPerson (Person), assignedAuthorizing Device(AUthorizing Device)
            if (!(sourceAssignedAuthor.getAssignedPerson() == null && targetAssignedAuthor.getAssignedPerson() == null
                    && targetAssignedAuthor.getAssignedAuthoringDevice() == null && sourceAssignedAuthor.getAssignedAuthoringDevice() == null)) {
                if (!((personComparison(sourceAssignedAuthor.getAssignedPerson(), targetAssignedAuthor.getAssignedPerson()) && sourceAssignedAuthor.getAssignedPerson() != null)
                        || (authorizingDeviceComparison(sourceAssignedAuthor.getAssignedAuthoringDevice(), targetAssignedAuthor.getAssignedAuthoringDevice()) && sourceAssignedAuthor.getAssignedAuthoringDevice() != null))) {
                    errorExists = true;
                }
            }
            //represented Organization
            if (!organizationComparison(sourceAssignedAuthor.getRepresentedOrganization(),targetAssignedAuthor.getRepresentedOrganization())) {
                errorExists = true;
            }
            //null flavor
            if (!compareNullFlavor(sourceAssignedAuthor.getNullFlavor(),targetAssignedAuthor.getNullFlavor())) {
                errorExists = true;
            }
            //class code
            if (!compareClassCode(sourceAssignedAuthor.getClassCode(),targetAssignedAuthor.getClassCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean patientComparison(Patient sourcePatient, Patient targetPatient) {
        boolean errorExists = false;
        if (sourcePatient == null && targetPatient == null) {
            return true;
        } else if (sourcePatient != null && targetPatient != null) {
            //realm code
            if (!compareRealmCodes(sourcePatient.getRealmCodes(),targetPatient.getRealmCodes())) {
                errorExists = true;
            }
            //typeID
            if (!typeIDComparison(sourcePatient.getTypeId(),targetPatient.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourcePatient.getTemplateIds(),targetPatient.getTemplateIds())) {
                errorExists = true;
            }
            //ids
            if (!compareIDs(sourcePatient.getIds(),targetPatient.getIds())) {
                errorExists = true;
            }
            //names
            if (!compareNamesPN(sourcePatient.getNames(),targetPatient.getNames())) {
                errorExists = true;
            }
            //administrativeGenderCode
            if (!compareAdministrativeGenderCode(sourcePatient.getAdministrativeGenderCode(),targetPatient.getAdministrativeGenderCode())) {
                errorExists = true;
            }
            //birthTime
            if (!compareBirthTime(sourcePatient.getBirthTime(),targetPatient.getBirthTime())) {
                errorExists = true;
            }
            //marital status code
            if (!compareMaritalStatusCode(sourcePatient.getMaritalStatusCode(),targetPatient.getMaritalStatusCode())) {
                errorExists = true;
            }
            //religious affiliation code
            if (!compareReligiosAffiliationCode(sourcePatient.getReligiousAffiliationCode(),targetPatient.getReligiousAffiliationCode())) {
                errorExists = true;
            }
            //race Code
            if (!compareRaceCode(sourcePatient.getRaceCode(),targetPatient.getRaceCode())) {
                errorExists = true;
            }
            //ethnic group code
            if (!compareEthnicGroupCode(sourcePatient.getEthnicGroupCode(),targetPatient.getEthnicGroupCode())) {
                errorExists = true;
            }
            //guardian - Guardian
            if (!guardiansComparison(sourcePatient.getGuardians(),targetPatient.getGuardians())) {
                errorExists = true;
            }
            //birthPlace - Birthplace
            if (!birthplaceComparison(sourcePatient.getBirthplace(),targetPatient.getBirthplace())) {
                errorExists = true;
            }
            //Language Communication - Language Communication
            if (!languageCommunicationsComparison(sourcePatient.getLanguageCommunications(),targetPatient.getLanguageCommunications())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourcePatient.getNullFlavor(),targetPatient.getNullFlavor())) {
                errorExists = true;
            }
            //classCode
            if (!compareClassCode(sourcePatient.getClassCode(),targetPatient.getClassCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean assignedEntityComparison(AssignedEntity sourceAssignedEntity, AssignedEntity targetAssignedEntity) {
        boolean errorExists = false;
        if (sourceAssignedEntity == null && targetAssignedEntity == null) {
            return true;
        } else if (sourceAssignedEntity != null && targetAssignedEntity != null) {
            //realm code
            if (!compareRealmCodes(sourceAssignedEntity.getRealmCodes(),targetAssignedEntity.getRealmCodes())) {
                errorExists = true;
            }
            //typeID
            if (!typeIDComparison(sourceAssignedEntity.getTypeId(),targetAssignedEntity.getTypeId())) {
                errorExists = true;
            }
            //ids
            if (!compareIDs(sourceAssignedEntity.getIds(),targetAssignedEntity.getIds())) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceAssignedEntity.getCode(),targetAssignedEntity.getCode())) {
                errorExists = true;
            }
            //addr
            if (!compareAddr(sourceAssignedEntity.getAddrs(),targetAssignedEntity.getAddrs())) {
                errorExists = true;
            }
            //telecom
            if (!compareTelcom(sourceAssignedEntity.getTelecoms(),targetAssignedEntity.getTelecoms())) {
                errorExists = true;
            }
            //assignedPerson - Person
            if (!personComparison(sourceAssignedEntity.getAssignedPerson(),targetAssignedEntity.getAssignedPerson())) {
                errorExists = true;
            }
            //representedOrganiztions - Organization
            if (!organizationsComparison(sourceAssignedEntity.getRepresentedOrganizations(),targetAssignedEntity.getRepresentedOrganizations())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceAssignedEntity.getNullFlavor(),targetAssignedEntity.getNullFlavor())) {
                errorExists = true;
            }
            //classCode
            if (!compareClassCode(sourceAssignedEntity.getClassCode(),targetAssignedEntity.getClassCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean assignedCustodianComparison(AssignedCustodian sourceAssignedCustodian, AssignedCustodian targetAssignedCustodian) {
        boolean errorExists = false;
        if (sourceAssignedCustodian == null && targetAssignedCustodian == null) {
            return true;
        } else if (sourceAssignedCustodian != null && targetAssignedCustodian != null) {
            //realm code
            if (!compareRealmCodes(sourceAssignedCustodian.getRealmCodes(),targetAssignedCustodian.getRealmCodes())) {
                errorExists = true;
            }
            //typeID
            if (!typeIDComparison(sourceAssignedCustodian.getTypeId(),targetAssignedCustodian.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceAssignedCustodian.getTemplateIds(),targetAssignedCustodian.getTemplateIds())) {
                errorExists = true;
            }
            //representedCustodianOrganization - CustodianOrganization
            if (!custodianOrganizationComparison(sourceAssignedCustodian.getRepresentedCustodianOrganization(),targetAssignedCustodian.getRepresentedCustodianOrganization())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceAssignedCustodian.getNullFlavor(),targetClinicalDocument.getNullFlavor())) {
                errorExists = true;
            }
            //classCode
            if (!compareClassCode(sourceAssignedCustodian.getClassCode(),targetAssignedCustodian.getClassCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean associatedEntityComparison(AssociatedEntity sourceAssignedEntity, AssociatedEntity targetAssignedEntity) {
        boolean errorExists = false;
        if (sourceAssignedEntity == null && targetAssignedEntity == null) {
            return true;
        } else if (sourceAssignedEntity != null && targetAssignedEntity != null) {
            //realm code
            if (!compareRealmCodes(sourceAssignedEntity.getRealmCodes(),targetAssignedEntity.getRealmCodes())) {
                errorExists = true;
            }
            //typeID
            if (!typeIDComparison(sourceAssignedEntity.getTypeId(),targetAssignedEntity.getTypeId())) {
                errorExists = true;
            }
            //template ID
            if (!compareTemplateID(sourceAssignedEntity.getTemplateIds(),targetAssignedEntity.getTemplateIds())) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceAssignedEntity.getTemplateIds(),targetAssignedEntity.getTemplateIds())) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceAssignedEntity.getCode(),targetAssignedEntity.getCode())) {
                errorExists = true;
            }
            //addr
            if (!compareAddr(sourceAssignedEntity.getAddrs(),targetAssignedEntity.getAddrs())) {
                errorExists = true;
            }
            //telecom
            if (!compareTelcom(sourceAssignedEntity.getTelecoms(),targetAssignedEntity.getTelecoms())) {
                errorExists = true;
            }
            //associatedPerson - Person
            if (!personComparison(sourceAssignedEntity.getAssociatedPerson(),targetAssignedEntity.getAssociatedPerson())) {
                errorExists = true;
            }
            //scopingOrganization - Organization
            if (!organizationComparison(sourceAssignedEntity.getScopingOrganization(),targetAssignedEntity.getScopingOrganization())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceAssignedEntity.getNullFlavor(),targetAssignedEntity.getNullFlavor())) {
                errorExists = true;
            }
            //classCode
            if (!compareClassCode(sourceAssignedEntity.getClassCode(),targetAssignedEntity.getClassCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean serviceEventComparison(ServiceEvent sourceServiceEvent, ServiceEvent targetServiceEvent) {
        boolean errorExists = false;
        if (sourceServiceEvent == null && targetServiceEvent == null) {
            return true;
        } else if (sourceServiceEvent != null && targetServiceEvent != null) {
            //realmCode
            if (!compareRealmCodes(sourceServiceEvent.getRealmCodes(),targetServiceEvent.getRealmCodes())) {
                errorExists = true;
            }
            //typeID
            if (!typeIDComparison(sourceServiceEvent.getTypeId(),targetServiceEvent.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceServiceEvent.getTemplateIds(),targetServiceEvent.getTemplateIds())) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceServiceEvent.getIds(),targetServiceEvent.getIds())) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceServiceEvent.getCode(),targetServiceEvent.getCode())) {
                errorExists = true;
            }
            //effective Time
            if (!compareEffectiveTime(sourceServiceEvent.getEffectiveTime(),targetServiceEvent.getEffectiveTime())) {
                errorExists = true;
            }
            //performer - performer1
            if (!performersComparison(sourceServiceEvent.getPerformers(),targetServiceEvent.getPerformers())) {
                errorExists = true;
            }
            //null flavor
            if (!compareNullFlavor(sourceServiceEvent.getNullFlavor(),targetServiceEvent.getNullFlavor())) {
                errorExists = true;
            }
            //class code
            if (!compareClassCode(sourceServiceEvent.getClassCode(),targetServiceEvent.getClassCode())) {
                errorExists = true;
            }
            //moodCode
            if (!compareMoodCode(sourceServiceEvent.getMoodCode(),targetServiceEvent.getMoodCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean nonXMLBodyComparison(NonXMLBody sourceNonXMLBody, NonXMLBody targetNonXMLBody) {
        boolean errorExists = false;
        if (sourceNonXMLBody == null && targetNonXMLBody == null) {
            return true;
        } else if (sourceNonXMLBody != null && targetNonXMLBody != null) {
            //realmCode
            if (!compareRealmCodes(sourceNonXMLBody.getRealmCodes(),targetNonXMLBody.getRealmCodes())) {
                errorExists = true;
            }
            //typeID
            if (!typeIDComparison(sourceNonXMLBody.getTypeId(),targetNonXMLBody.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceNonXMLBody.getTemplateIds(),targetNonXMLBody.getTemplateIds())) {
                errorExists = true;
            }
            //text
            if (!compareText(sourceNonXMLBody.getText(),targetNonXMLBody.getText())) {
                errorExists = true;
            }
            //confidentialityCode
            if (!compareConfidentialityCode(sourceNonXMLBody.getConfidentialityCode(),targetNonXMLBody.getConfidentialityCode())) {
                errorExists = true;
            }
            //langaugeCode
            if (!compareLanguageCode(sourceNonXMLBody.getLanguageCode(),targetNonXMLBody.getLanguageCode())) {
                errorExists = true;
            }
            //null flavor
            if (!compareNullFlavor(sourceNonXMLBody.getNullFlavor(),targetNonXMLBody.getNullFlavor())) {
                errorExists = true;
            }
            //classCode
            if (!compareClassCode(sourceNonXMLBody.getClassCode(),targetNonXMLBody.getClassCode())) {
                errorExists = true;
            }
            //moodCode
            if (!compareMoodCode(sourceNonXMLBody.getMoodCode(),targetNonXMLBody.getMoodCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean structuredBodyComparison(StructuredBody sourceStructuredBody, StructuredBody targetStructuredBody) {
        boolean errorExists = false;
        if (sourceStructuredBody == null && targetStructuredBody == null) {
            return true;
        } else if (sourceStructuredBody != null && targetStructuredBody != null) {
            //realmCode
            if (!compareRealmCodes(sourceStructuredBody.getRealmCodes(),targetStructuredBody.getRealmCodes())) {
                errorExists = true;
            }
            //typeID
            if (!typeIDComparison(sourceStructuredBody.getTypeId(),targetStructuredBody.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceStructuredBody.getTemplateIds(),targetStructuredBody.getTemplateIds())) {
                errorExists = true;
            }
            //confidentiality Code
            if (!compareConfidentialityCode(sourceStructuredBody.getConfidentialityCode(),targetStructuredBody.getConfidentialityCode())) {
                errorExists = true;
            }
            //language Code
            if (!compareLanguageCode(sourceStructuredBody.getLanguageCode(),targetStructuredBody.getLanguageCode())) {
                errorExists = true;
            }
            //component - Component 3
            if (!component3Comparison(sourceStructuredBody.getComponents(),targetStructuredBody.getComponents())) {
                errorExists = true;
            }
            //null Flavor
            if (!compareNullFlavor(sourceStructuredBody.getNullFlavor(),targetStructuredBody.getNullFlavor())) {
                errorExists = true;
            }
            //class Code
            if (!compareClassCode(sourceStructuredBody.getClassCode(),targetStructuredBody.getClassCode())) {
                errorExists = true;
            }
            //moodCode
            if (!compareMoodCode(sourceStructuredBody.getMoodCode(),targetStructuredBody.getMoodCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean relatedEntityComparison(RelatedEntity sourceRelatedEntity, RelatedEntity targetRelatedEntity) {
        boolean errorExists = false;
        if (sourceRelatedEntity == null && targetRelatedEntity == null) {
            return true;
        } else if (sourceRelatedEntity != null && targetRelatedEntity != null) {
            //realmCode
            if (!compareRealmCodes(sourceRelatedEntity.getRealmCodes(),targetRelatedEntity.getRealmCodes())) {
                errorExists = true;
            }
            //typeID
            if (!typeIDComparison(sourceRelatedEntity.getTypeId(),targetRelatedEntity.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceRelatedEntity.getTemplateIds(),targetRelatedEntity.getTemplateIds())) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceRelatedEntity.getCode(),targetRelatedEntity.getCode())) {
                errorExists = true;
            }
            //addr
            if (!compareAddr(sourceRelatedEntity.getAddrs(),targetRelatedEntity.getAddrs())) {
                errorExists = true;
            }
            //telecom
            if (!compareTelcom(sourceRelatedEntity.getTelecoms(),targetRelatedEntity.getTelecoms())) {
                errorExists = true;
            }
            //affectiveTime
            if (!compareEffectiveTime(sourceRelatedEntity.getEffectiveTime(),targetRelatedEntity.getEffectiveTime())) {
                errorExists = true;
            }
            //relatedPerson - Person
            if (!personComparison(sourceRelatedEntity.getRelatedPerson(),targetRelatedEntity.getRelatedPerson())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceRelatedEntity.getNullFlavor(),targetRelatedEntity.getNullFlavor())) {
                errorExists = true;
            }
            //classCode
            if (!compareClassCode(sourceRelatedEntity.getClassCode(),targetRelatedEntity.getClassCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean organizationComparison(Organization sourceOrganization, Organization targetOrganization) {
        boolean errorExists = false;
        if (sourceOrganization == null && targetOrganization == null) {
            return true;
        } else if (sourceOrganization != null && targetOrganization != null) {
            //realmCode
            if (!compareRealmCodes(sourceOrganization.getRealmCodes(),targetOrganization.getRealmCodes())) {
                errorExists = true;
            }
            //typeID
            if (!typeIDComparison(sourceOrganization.getTypeId(),targetOrganization.getTypeId())) {
                errorExists = true;
            }
            //templatedID
            if (!compareTemplateID(sourceOrganization.getTemplateIds(),targetOrganization.getTemplateIds())) {
                errorExists = true;
            }
            //ids
            if (!compareIDs(sourceOrganization.getIds(),targetOrganization.getIds())) {
                errorExists = true;
            }
            //name
            if (!compareNamesON(sourceOrganization.getNames(),targetOrganization.getNames())) {
                errorExists = true;
            }
            //telecom
            if (!compareTelcom(sourceOrganization.getTelecoms(),targetOrganization.getTelecoms())) {
                errorExists = true;
            }
            //addr
            if (!compareAddr(sourceOrganization.getAddrs(),targetOrganization.getAddrs())) {
                errorExists = true;
            }
            //standardIndustryClassCode
            if (!compareCode(sourceOrganization.getStandardIndustryClassCode(),targetOrganization.getStandardIndustryClassCode())) {
                errorExists = true;
            }
            //asOrganizationPartOf - OrganizationPartOf
            if (!organizationPartOfComparison(sourceOrganization.getAsOrganizationPartOf(),targetOrganization.getAsOrganizationPartOf())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceOrganization.getNullFlavor(),targetOrganization.getNullFlavor())) {
                errorExists = true;
            }
            //classCode
            if (!compareClassCode(sourceOrganization.getClassCode(),targetOrganization.getClassCode())) {
                errorExists = true;
            }
            //determinerCode
            if (!compareDeterminerCode(sourceOrganization.getDeterminerCode(),targetOrganization.getDeterminerCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean personComparison(Person sourcePerson, Person targetPerson) {
        boolean errorExists = false;
        if (sourcePerson == null && targetPerson == null) {
            return true;
        } else if (sourcePerson != null && targetPerson != null) {
            //realm code
            if (!compareRealmCodes(sourcePerson.getRealmCodes(),targetPerson.getRealmCodes())) {
                errorExists = true;
            }
            //typeID
            if (!typeIDComparison(sourcePerson.getTypeId(),targetPerson.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourcePerson.getTemplateIds(),targetPerson.getTemplateIds())) {
                errorExists = true;
            }
            //name
            if (!compareNamesPN(sourcePerson.getNames(),targetPerson.getNames())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourcePerson.getNullFlavor(),targetPerson.getNullFlavor())) {
                errorExists = true;
            }
            //classCode
            if (!compareClassCode(sourcePerson.getClassCode(),targetPerson.getClassCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean authorizingDeviceComparison(AuthoringDevice sourceAuthorizingDevice, AuthoringDevice targetAuthorizingDevice) {
        boolean errorExists = false;
        if (sourceAuthorizingDevice == null && targetAuthorizingDevice == null) {
            return true;
        } else if (sourceAuthorizingDevice != null && targetAuthorizingDevice != null) {
            //realmCode
            if (!compareRealmCodes(sourceAuthorizingDevice.getRealmCodes(),targetAuthorizingDevice.getRealmCodes())) {
                errorExists = true;
            }
            //result Id
            if (!typeIDComparison(sourceAuthorizingDevice.getTypeId(),targetAuthorizingDevice.getTypeId())) {
                errorExists = true;
            }
            //template ID
            if (!compareTemplateID(sourceAuthorizingDevice.getTemplateIds(),targetAuthorizingDevice.getTemplateIds())) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceAuthorizingDevice.getCode(),targetAuthorizingDevice.getCode())) {
                errorExists = true;
            }
            //manufacturedModelName
            if (!compareSCName(sourceAuthorizingDevice.getManufacturerModelName(),targetAuthorizingDevice.getManufacturerModelName())) {
                errorExists = true;
            }
            //softwareName
            if (!compareSCName(sourceAuthorizingDevice.getSoftwareName(),targetAuthorizingDevice.getSoftwareName())) {
                errorExists = true;
            }
            //asMaintainedEntity - MaintainedEntity
            if (!maintainedEntityComparison(sourceAuthorizingDevice.getAsMaintainedEntities(),targetAuthorizingDevice.getAsMaintainedEntities())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceAuthorizingDevice.getNullFlavor(),targetAuthorizingDevice.getNullFlavor())) {
                errorExists = true;
            }
            //classCode
            if (!compareClassCode(sourceAuthorizingDevice.getClassCode(),targetAuthorizingDevice.getClassCode())) {
                errorExists = true;
            }
            //determinerCode
            if (!compareDeterminerCode(sourceAuthorizingDevice.getDeterminerCode(),targetAuthorizingDevice.getDeterminerCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean informantsComparison(EList<Informant12> sourceInformant12, EList<Informant12> targetInformant12) {
        boolean errorExists = false;
        for (int i=0; i<sourceInformant12.size(); i++) {
            int targetMatches = 0;
            for (int j=0; j<targetInformant12.size(); j++) {
                if (sourceInformant12.get(i) != null && targetInformant12.get(j) != null) {
                    boolean specificError = false;
                    //realmCode
                    if (!compareRealmCodes(sourceInformant12.get(i).getRealmCodes(), targetInformant12.get(j).getRealmCodes())) {
                        specificError = true;
                    }
                    //typeID
                    if (!typeIDComparison(sourceInformant12.get(i).getTypeId(), targetInformant12.get(j).getTypeId())) {
                        specificError = true;
                    }
                    //template ID
                    if (!compareTemplateID(sourceInformant12.get(i).getTemplateIds(), targetInformant12.get(j).getTemplateIds())) {
                        specificError = true;
                    }
                    //choice - assignedEntity, related entity
                    if (!(sourceInformant12.get(i).getAssignedEntity() == null && targetInformant12.get(j).getAssignedEntity() == null
                            && sourceInformant12.get(i).getRelatedEntity() == null && sourceInformant12.get(j).getRelatedEntity() == null)) {
                        if (!((assignedEntityComparison(sourceInformant12.get(i).getAssignedEntity(), targetInformant12.get(j).getAssignedEntity()) && sourceInformant12.get(i).getAssignedEntity() != null)
                                || (relatedEntityComparison(sourceInformant12.get(i).getRelatedEntity(), targetInformant12.get(j).getRelatedEntity()) && sourceInformant12.get(i).getRelatedEntity() != null))) {
                            specificError = true;
                        }
                    }
                    //nullflavor
                    if (!compareNullFlavor(sourceInformant12.get(i).getNullFlavor(), targetInformant12.get(j).getNullFlavor())) {
                        specificError = true;
                    }
                    //result code
                    if (!compareTypeCode(sourceInformant12.get(i).getTypeCode(), targetInformant12.get(j).getTypeCode())) {
                        specificError = true;
                    }
                    //contextControlCode
                    if (!compareContextControlCode(sourceInformant12.get(i).getContextControlCode(), targetInformant12.get(j).getContextControlCode())) {
                        specificError = true;
                    }

                    if (!specificError) {
                        targetMatches++;
                    }
                } else {
                    targetMatches = 1;
                }
            }
            if (targetMatches == 0) {
                errorExists = true;
//                comparisonReport.addMessage("Informants Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
//                comparisonReport.addMessage("Informants Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private boolean recordTargetsComparison(EList<RecordTarget> sourceRecordTargets, EList<RecordTarget> targetRecordTargets) {
        boolean errorExists = false;
        for (int i = 0; i < sourceRecordTargets.size(); i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetRecordTargets.size(); j++) {
                boolean specificError = false;
                //compare realmCode
                if (!compareRealmCodes(sourceRecordTargets.get(i).getRealmCodes(), targetRecordTargets.get(j).getRealmCodes())) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourceRecordTargets.get(i).getTypeId(), targetRecordTargets.get(j).getTypeId())) {
                    specificError = true;
                }
                //compareTemplateID
                if (!compareTemplateID(sourceRecordTargets.get(i).getTemplateIds(), targetRecordTargets.get(j).getTemplateIds())) {
                    specificError = true;
                }
                //comparePatientRole
                if (!patientRoleComparison(sourceRecordTargets.get(i).getPatientRole(), targetRecordTargets.get(j).getPatientRole())) {
                    specificError = true;
                }
                //compare nullFlavor
                if (!compareNullFlavor(sourceRecordTargets.get(i).getNullFlavor(), targetRecordTargets.get(j).getNullFlavor())) {
                    specificError = true;
                }
                //compare typeCode
                if (!compareTypeCode(sourceRecordTargets.get(i).getTypeCode(), targetRecordTargets.get(j).getTypeCode())) {
                    specificError = true;
                }

                if (!specificError) {
                    targetMatches++;
                }
            }
            if (targetMatches == 0) {
                errorExists = true;
//                comparisonReport.addMessage("Record Targets Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
//                comparisonReport.addMessage("Record Targets Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }


    private boolean authorsComparison(EList<Author> sourceAuthors, EList<Author> targetAuthors) {
        boolean errorExists = false;
        for (int i=0; i<sourceAuthors.size(); i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetAuthors.size(); j++) {
                boolean specificError = false;
                //compare realmCodes
                if (!compareRealmCodes(sourceAuthors.get(i).getRealmCodes(), targetAuthors.get(j).getRealmCodes())) {
                    specificError = true;
                }
                //compare typeID
                if (!typeIDComparison(sourceAuthors.get(i).getTypeId(), targetAuthors.get(j).getTypeId())) {
                    specificError = true;
                }
                //compare template ID
                if (!compareTemplateID(sourceAuthors.get(i).getTemplateIds(), targetAuthors.get(j).getTemplateIds())) {
                    specificError = true;
                }
                //compare functionCode
                if (!compareFunctionCode(sourceAuthors.get(i).getFunctionCode(),targetAuthors.get(j).getFunctionCode())) {
                    specificError = true;
                }
                //compare time
                if (!compareTime(sourceAuthors.get(i).getTime(), targetAuthors.get(j).getTime())) {
                    specificError = true;
                }
                //compare AssignedAuthor
                if (!assignedAuthorComparison(sourceAuthors.get(i).getAssignedAuthor(), targetAuthors.get(j).getAssignedAuthor())) {
                    specificError = true;
                }
                //compare nullFlavor
                if (!compareNullFlavor(sourceAuthors.get(i).getNullFlavor(), targetAuthors.get(j).getNullFlavor())) {
                    specificError = true;
                }
                //compare typeCode
                if (!compareTypeCode(sourceAuthors.get(i).getTypeCode(), targetAuthors.get(j).getTypeCode())) {
                    specificError = true;
                }
                //compare contextControlCode
                if (!compareContextControlCode(sourceAuthors.get(i).getContextControlCode(), targetAuthors.get(j).getContextControlCode())) {
                    specificError = true;
                }

                if (!specificError) {
                    targetMatches++;
                }
            }
            if (targetMatches == 0) {
                errorExists = true;
//                comparisonReport.addMessage("Authors Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
//                comparisonReport.addMessage("Authors Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private boolean participants1Comparison(EList<Participant1> sourceParticipant, EList<Participant1> targetParticipant) {
        boolean errorExists = false;
        for (int i=0;i<sourceParticipant.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetParticipant.size(); j++) {
                boolean specificError = false;
                //realmCodes
                if (!compareRealmCodes(sourceParticipant.get(i).getRealmCodes(), targetParticipant.get(j).getRealmCodes())) {
                    specificError = true;
                }
                //typeID
                if (!typeIDComparison(sourceParticipant.get(i).getTypeId(), targetParticipant.get(j).getTypeId())) {
                    specificError = true;
                }
                //templateID
                if (!compareTemplateID(sourceParticipant.get(i).getTemplateIds(), targetParticipant.get(j).getTemplateIds())) {
                    specificError = true;
                }
                //functionCode
                if (!compareFunctionCode(sourceParticipant.get(i).getFunctionCode(), targetParticipant.get(j).getFunctionCode())) {
                    specificError = true;
                }
                //time
                if (!compareTime(sourceParticipant.get(i).getTime(), targetParticipant.get(j).getTime())) {
                    specificError = true;
                }
                //associated Entity
                if (!associatedEntityComparison(sourceParticipant.get(i).getAssociatedEntity(), targetParticipant.get(j).getAssociatedEntity())) {
                    specificError = true;
                }
                //nullFlavor
                if (!compareNullFlavor(sourceParticipant.get(i).getNullFlavor(), targetParticipant.get(j).getNullFlavor())) {
                    specificError = true;
                }
                //typeCode
                if (!compareTypeCode(sourceParticipant.get(i).getTypeCode(), targetParticipant.get(j).getTypeCode())) {
                    specificError = true;
                }

                if (!specificError) {
                    targetMatches++;
                }
            }
            if (targetMatches == 0) {
                errorExists = true;
//                comparisonReport.addMessage("Participants Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
//                comparisonReport.addMessage("Participants Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private boolean documentationOfsComparison(EList<DocumentationOf> sourceDocumentationOf, EList<DocumentationOf> targetDocumentationOf) {
        boolean errorExists = false;
        for (int i=0;i<sourceDocumentationOf.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetDocumentationOf.size(); j++) {
                boolean specificError = false;
                //realmCodes
                if (!compareRealmCodes(sourceDocumentationOf.get(i).getRealmCodes(), targetDocumentationOf.get(j).getRealmCodes())) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourceDocumentationOf.get(i).getTypeId(), targetDocumentationOf.get(j).getTypeId())) {
                    specificError = true;
                }
                //compare templateID
                if (!compareTemplateID(sourceDocumentationOf.get(i).getTemplateIds(), targetDocumentationOf.get(j).getTemplateIds())) {
                    specificError = true;
                }
                //compare serviceEvent
                if (!serviceEventComparison(sourceDocumentationOf.get(i).getServiceEvent(), targetDocumentationOf.get(j).getServiceEvent())) {
                    specificError = true;
                }
                //compare NullFlavor
                if (!compareNullFlavor(sourceDocumentationOf.get(i).getNullFlavor(), targetDocumentationOf.get(j).getNullFlavor())) {
                    specificError = true;
                }
                //compare typeCode
                if (!compareTypeCode(sourceDocumentationOf.get(i).getTypeCode(), targetDocumentationOf.get(j).getTypeCode())){
                    specificError = true;
                }

                if(!specificError) {
                    targetMatches++;
                }

            }
            if (targetMatches == 0) {
                errorExists = true;
//                comparisonReport.addMessage("DocumentationsOf Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
//                comparisonReport.addMessage("DocumentationsOf Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private boolean organizationsComparison(EList<Organization> sourceOrganization, EList<Organization> targetOrganization)  {
        boolean errorExists = false;
        for (int i=0;i<sourceOrganization.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetOrganization.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (!compareRealmCodes(sourceOrganization.get(i).getRealmCodes(), targetOrganization.get(j).getRealmCodes())) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourceOrganization.get(i).getTypeId(), targetOrganization.get(j).getTypeId())) {
                    specificError = true;
                }
                //compare templateID
                if (!compareTemplateID(sourceOrganization.get(i).getTemplateIds(), targetOrganization.get(j).getTemplateIds())) {
                    specificError = true;
                }
                //compare IDs
                if (!compareIDs(sourceOrganization.get(i).getIds(), targetOrganization.get(j).getIds())) {
                    specificError = true;
                }
                //compare name
                if (!compareNamesON(sourceOrganization.get(i).getNames(),targetOrganization.get(j).getNames())) {
                    specificError = true;
                }
                //compare Telecom
                if (!compareTelcom(sourceOrganization.get(i).getTelecoms(),targetOrganization.get(j).getTelecoms())) {
                    specificError = true;
                }
                //compare Addr
                if (!compareAddr(sourceOrganization.get(i).getAddrs(),targetOrganization.get(j).getAddrs())) {
                    specificError = true;
                }
                //compare StandardIndustryClassCode
                if (!compareCode(sourceOrganization.get(i).getStandardIndustryClassCode(),targetOrganization.get(j).getStandardIndustryClassCode())) {
                    specificError = true;
                }
                //compare asOrganizationPartOf - OrganizationPartOf
                if (!organizationPartOfComparison(sourceOrganization.get(i).getAsOrganizationPartOf(),targetOrganization.get(j).getAsOrganizationPartOf())) {
                    specificError = true;
                }
                //compare NullFlavor
                if (!compareNullFlavor(sourceOrganization.get(i).getNullFlavor(), targetOrganization.get(j).getNullFlavor())) {
                    specificError = true;
                }
                //compare classCode
                if (!compareClassCode(sourceOrganization.get(i).getClassCode(), targetOrganization.get(j).getClassCode())) {
                    specificError = true;
                }
                //compare Determiner Code
                if (!compareDeterminerCode(sourceOrganization.get(i).getDeterminerCode(),targetOrganization.get(j).getDeterminerCode())) {
                    specificError = true;
                }

                if (!specificError) {
                    targetMatches++;
                }

            }
            if (targetMatches == 0) {
                errorExists = true;
//                comparisonReport.addMessage("Organization Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
//                comparisonReport.addMessage("Organization Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private boolean informationRecipientComparison(EList<InformationRecipient> sourceInformationRecipient, EList<InformationRecipient> targetInformationRecipient) {
        boolean errorExists = false;
        for (int i=0;i<sourceInformationRecipient.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetInformationRecipient.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (!compareRealmCodes(sourceInformationRecipient.get(i).getRealmCodes(), targetInformationRecipient.get(j).getRealmCodes())) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourceInformationRecipient.get(i).getTypeId(), targetInformationRecipient.get(j).getTypeId())) {
                    specificError = true;
                }
                //compare templateID
                if (!compareTemplateID(sourceInformationRecipient.get(i).getTemplateIds(), targetInformationRecipient.get(j).getTemplateIds())) {
                    specificError = true;
                }
                //compare Intended Recipient
                if (!intendedRecipientComparison(sourceInformationRecipient.get(i).getIntendedRecipient(), targetInformationRecipient.get(j).getIntendedRecipient())) {
                    specificError = true;
                }
                //compare NullFlavor
                if (!compareNullFlavor(sourceInformationRecipient.get(i).getNullFlavor(), targetInformationRecipient.get(j).getNullFlavor())) {
                    specificError = true;
                }
                //compare typeCode
                if (!compareTypeCode(sourceInformationRecipient.get(i).getTypeCode(), targetInformationRecipient.get(j).getTypeCode())) {
                    specificError = true;
                }

                if (!specificError) {
                    targetMatches++;
                }

            }
            if (targetMatches == 0) {
                errorExists = true;
//                comparisonReport.addMessage("InformationRecipient Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
//                comparisonReport.addMessage("InformationRecipient Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private boolean inFulfillmentOfComparison(EList<InFulfillmentOf> sourceInFulfillmentOf, EList<InFulfillmentOf> targetInFulfillmentOf) {
        boolean errorExists = false;
        for (int i=0;i<sourceInFulfillmentOf.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetInFulfillmentOf.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (!compareRealmCodes(sourceInFulfillmentOf.get(i).getRealmCodes(), targetInFulfillmentOf.get(j).getRealmCodes())) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourceInFulfillmentOf.get(i).getTypeId(), targetInFulfillmentOf.get(j).getTypeId())) {
                    specificError = true;
                }
                //compare templateID
                if (!compareTemplateID(sourceInFulfillmentOf.get(i).getTemplateIds(), targetInFulfillmentOf.get(j).getTemplateIds())) {
                    specificError = true;
                }
                //compare Order
                if (!orderComparison(sourceInFulfillmentOf.get(i).getOrder(), targetInFulfillmentOf.get(j).getOrder())) {
                    specificError = true;
                }
                //compare NullFlavor
                if (!compareNullFlavor(sourceInFulfillmentOf.get(i).getNullFlavor(), targetInFulfillmentOf.get(j).getNullFlavor())) {
                    specificError = true;
                }
                //compare typeCode
                if (!compareTypeCode(sourceInFulfillmentOf.get(i).getTypeCode(), targetInFulfillmentOf.get(j).getTypeCode())) {
                    specificError = true;
                }

                if (!specificError) {
                    targetMatches++;
                }

            }
            if (targetMatches == 0) {
                errorExists = true;
//                comparisonReport.addMessage("InFulfillmentOf Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
//                comparisonReport.addMessage("InFulfillmentOf Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private boolean relatedDocumentsComparison(EList<RelatedDocument> sourceRelatedDocument, EList<RelatedDocument> targetRelatedDocument) {
        boolean errorExists = false;
        for (int i=0;i<sourceRelatedDocument.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetRelatedDocument.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (compareRealmCodes(sourceRelatedDocument.get(i).getRealmCodes(), targetRelatedDocument.get(j).getRealmCodes())) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourceRelatedDocument.get(i).getTypeId(), targetRelatedDocument.get(j).getTypeId())) {
                    specificError = true;
                }
                //compare templateID
                if (!compareTemplateID(sourceRelatedDocument.get(i).getTemplateIds(), targetRelatedDocument.get(j).getTemplateIds())) {
                    specificError = true;
                }
                //compare Order
                if (!parentDocumentComparison(sourceRelatedDocument.get(i).getParentDocument(), targetRelatedDocument.get(j).getParentDocument())) {
                    specificError = true;
                }
                //compare NullFlavor
                if (!compareNullFlavor(sourceRelatedDocument.get(i).getNullFlavor(), targetRelatedDocument.get(j).getNullFlavor())) {
                    specificError = true;
                }
                //compare typeCode
                if (!compareTypeCode(sourceRelatedDocument.get(i).getTypeCode(), targetRelatedDocument.get(j).getTypeCode())) {
                    specificError = true;
                }


                if (!specificError){
                    targetMatches++;
                }

            }
            if (targetMatches == 0) {
                errorExists = true;
//                comparisonReport.addMessage("Related Documents Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
//                comparisonReport.addMessage("Related Documents Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private boolean authorizationsComparison(EList<Authorization> sourceAuthorizations, EList<Authorization> targetAuthorizations) {
        boolean errorExists = false;
        for (int i=0;i<sourceAuthorizations.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetAuthorizations.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (!compareRealmCodes(sourceAuthorizations.get(i).getRealmCodes(), targetAuthorizations.get(j).getRealmCodes())) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourceAuthorizations.get(i).getTypeId(), targetAuthorizations.get(j).getTypeId())) {
                    specificError = true;
                }
                //compare templateID
                if (!compareTemplateID(sourceAuthorizations.get(i).getTemplateIds(), targetAuthorizations.get(j).getTemplateIds())) {
                    specificError = true;
                }
                //compare Consent
                if (!consentComparison(sourceAuthorizations.get(i).getConsent(), targetAuthorizations.get(j).getConsent())) {
                    specificError = true;
                }
                //compare NullFlavor
                if (!compareNullFlavor(sourceAuthorizations.get(i).getNullFlavor(), targetAuthorizations.get(j).getNullFlavor())) {
                    specificError = true;
                }
                //compare typeCode
                if (!compareTypeCode(sourceAuthorizations.get(i).getTypeCode(), targetAuthorizations.get(j).getTypeCode())) {
                    specificError = true;
                }

                if (!specificError){
                    targetMatches++;
                }

            }
            if (targetMatches == 0) {
                errorExists = true;
//                comparisonReport.addMessage("Authorizations Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
//                comparisonReport.addMessage("Authorizations Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private boolean componentOfComparison(Component1 sourceComponent1, Component1 targetComponent1) {
        boolean errorExists = false;
        if (sourceComponent1 == null && targetComponent1 == null) {
            return true;
        } else if (sourceComponent1 != null && targetComponent1 != null) {
            //realm code
            if (!compareRealmCodes(sourceComponent1.getRealmCodes(),targetComponent1.getRealmCodes())) {
                errorExists = true;
            }
            //typeID
            if (!typeIDComparison(sourceComponent1.getTypeId(),targetComponent1.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceComponent1.getTemplateIds(),targetComponent1.getTemplateIds())) {
                errorExists = true;
            }
            //Encompassong Encounter
            if (!encompassingEncounterComparison(sourceComponent1.getEncompassingEncounter(),targetComponent1.getEncompassingEncounter())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceComponent1.getNullFlavor(),targetComponent1.getNullFlavor())) {
                errorExists = true;
            }
            //TypeCode
            if (!compareTypeCode(sourceComponent1.getTypeCode(),targetComponent1.getTypeCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean guardiansComparison(EList<Guardian> sourceGuardian, EList<Guardian> targetGuardian) {
        boolean errorExists = false;
        for (int i=0;i<sourceGuardian.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetGuardian.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (!compareRealmCodes(sourceGuardian.get(i).getRealmCodes(), targetGuardian.get(j).getRealmCodes())) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourceGuardian.get(i).getTypeId(), targetGuardian.get(j).getTypeId())) {
                    specificError = true;
                }
                //compare templateID
                if (!compareTemplateID(sourceGuardian.get(i).getTemplateIds(), targetGuardian.get(j).getTemplateIds())) {
                    specificError = true;
                }
                //compare IDs
                if (!compareIDs(sourceGuardian.get(i).getIds(), targetGuardian.get(j).getIds())) {
                    specificError = true;
                }
                //compare code
                if (!compareCode(sourceGuardian.get(i).getCode(),targetGuardian.get(j).getCode())) {
                    specificError = true;
                }
                //compare Telecom
                if (!compareTelcom(sourceGuardian.get(i).getTelecoms(),targetGuardian.get(j).getTelecoms())) {
                    specificError = true;
                }
                //compare Addr
                if (!compareAddr(sourceGuardian.get(i).getAddrs(),targetGuardian.get(j).getAddrs())) {
                    specificError = true;
                }
                //choice - guardianPerson (Person) and guardianOrganization (Organization)
                if (!(sourceGuardian.get(i).getGuardianPerson() == null && targetGuardian.get(j).getGuardianPerson() == null
                        && sourceGuardian.get(i).getGuardianOrganization() == null && targetGuardian.get(j).getGuardianOrganization() == null)) {
                    if (!((personComparison(sourceGuardian.get(i).getGuardianPerson(), targetGuardian.get(j).getGuardianPerson()) && sourceGuardian.get(i).getGuardianPerson() != null)
                            || (organizationComparison(sourceGuardian.get(i).getGuardianOrganization(), targetGuardian.get(j).getGuardianOrganization()) && sourceGuardian.get(i).getGuardianOrganization() != null))) {
                        specificError = true;
                    }
                }
                //compare NullFlavor
                if (!compareNullFlavor(sourceGuardian.get(i).getNullFlavor(), targetGuardian.get(j).getNullFlavor())) {
                    specificError = true;
                }
                //compare classCode
                if (!compareClassCode(sourceGuardian.get(i).getClassCode(), targetGuardian.get(j).getClassCode())) {
                    specificError = true;
                }

                if (!specificError){
                    targetMatches++;
                }

            }
            if (targetMatches == 0) {
                errorExists = true;
//                comparisonReport.addMessage("Guardians Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
//                comparisonReport.addMessage("Guardians Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private boolean birthplaceComparison(Birthplace sourceBirthplace, Birthplace targetBirthplace) {
        boolean errorExists = false;
        if (sourceBirthplace == null && targetBirthplace == null) {
            return true;
        } else if (sourceBirthplace != null && targetBirthplace != null) {
            //realm code
            if (!compareRealmCodes(sourceBirthplace.getRealmCodes(),targetBirthplace.getRealmCodes())) {
                errorExists = true;
            }
            //typeID
            if (!typeIDComparison(sourceBirthplace.getTypeId(),targetBirthplace.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceBirthplace.getTemplateIds(),targetBirthplace.getTemplateIds())) {
                errorExists = true;
            }
            //Place
            if (!placeComparison(sourceBirthplace.getPlace(),targetBirthplace.getPlace())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceBirthplace.getNullFlavor(),targetBirthplace.getNullFlavor())) {
                errorExists = true;
            }
            //TypeCode
            if (!compareClassCode(sourceBirthplace.getClassCode(),targetBirthplace.getClassCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean languageCommunicationsComparison(EList<LanguageCommunication> sourceLanguageCommmunication, EList<LanguageCommunication> targetLanguageCommunication) {
        boolean errorExists = false;
        for (int i=0;i<sourceLanguageCommmunication.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetLanguageCommunication.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (!compareRealmCodes(sourceLanguageCommmunication.get(i).getRealmCodes(), targetLanguageCommunication.get(j).getRealmCodes())) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourceLanguageCommmunication.get(i).getTypeId(), targetLanguageCommunication.get(j).getTypeId())) {
                    specificError = true;
                }
                //compare language code
                if (!compareLanguageCode(sourceLanguageCommmunication.get(i).getLanguageCode(), targetLanguageCommunication.get(j).getLanguageCode())) {
                    specificError = true;
                }
                //compare Moode Code
                if (!compareCode(sourceLanguageCommmunication.get(i).getModeCode(), targetLanguageCommunication.get(j).getModeCode())) {
                    specificError = true;
                }
                //compare Proficiency Level Code
                if (!compareCode(sourceLanguageCommmunication.get(i).getProficiencyLevelCode(),targetLanguageCommunication.get(j).getProficiencyLevelCode())) {
                    specificError = true;
                }
                //compare PreferenceInd
                if (!comparePreferenceInd(sourceLanguageCommmunication.get(i).getPreferenceInd(),targetLanguageCommunication.get(j).getPreferenceInd())) {
                    specificError = true;
                }
                //compare NullFlavor
                if (!compareNullFlavor(sourceLanguageCommmunication.get(i).getNullFlavor(), targetLanguageCommunication.get(j).getNullFlavor())) {
                    specificError = true;
                }


                if (!specificError){
                    targetMatches++;
                }

            }
            if (targetMatches == 0) {
                errorExists = true;
//                comparisonReport.addMessage("Language Communications Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
//                comparisonReport.addMessage("Language Communications Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private boolean custodianOrganizationComparison(CustodianOrganization sourceCustodianOrganization, CustodianOrganization targetCustodianOrganization) {
        boolean errorExists = false;
        if (sourceCustodianOrganization == null && targetCustodianOrganization == null) {
            return true;
        } else if (sourceCustodianOrganization != null && targetCustodianOrganization != null) {
            //realmCode
            if (!compareRealmCodes(sourceCustodianOrganization.getRealmCodes(),targetCustodianOrganization.getRealmCodes())) {
                errorExists = true;
            }
            //result Id
            if (!typeIDComparison(sourceCustodianOrganization.getTypeId(),targetCustodianOrganization.getTypeId())) {
                errorExists = true;
            }
            //template ID
            if (!compareTemplateID(sourceCustodianOrganization.getTemplateIds(),targetCustodianOrganization.getTemplateIds())) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceCustodianOrganization.getIds(),targetCustodianOrganization.getIds())) {
                errorExists = true;
            }
            //name
            if (!compareNamesEN(sourceCustodianOrganization.getNames(),targetCustodianOrganization.getNames())) {
                errorExists = true;
            }
            //telecom
            if (!compareTelcom(sourceCustodianOrganization.getTelecoms(),targetCustodianOrganization.getTelecoms())) {
                errorExists = true;
            }
            // addr
            if (!compareAddr(sourceCustodianOrganization.getAddrs(),targetCustodianOrganization.getAddrs())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceCustodianOrganization.getNullFlavor(),targetCustodianOrganization.getNullFlavor())) {
                errorExists = true;
            }
            //classCode
            if (!compareClassCode(sourceCustodianOrganization.getClassCode(),targetCustodianOrganization.getClassCode())) {
                errorExists = true;
            }
            //determinerCode
            if (!compareDeterminerCode(sourceCustodianOrganization.getDeterminerCode(),targetCustodianOrganization.getDeterminerCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean performersComparison(EList<Performer1> sourcePerformer1, EList<Performer1> targetPerformer1) {
        boolean errorExists = false;
        for (int i=0;i<sourcePerformer1.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetPerformer1.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (!compareRealmCodes(sourcePerformer1.get(i).getRealmCodes(), targetPerformer1.get(j).getRealmCodes())) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourcePerformer1.get(i).getTypeId(), targetPerformer1.get(j).getTypeId())) {
                    specificError = true;
                }
                //compare Template ID
                if (!compareTemplateID(sourcePerformer1.get(i).getTemplateIds(), targetPerformer1.get(j).getTemplateIds())) {
                    specificError = true;
                }
                //compare FunctionCode
                if (!compareCode(sourcePerformer1.get(i).getFunctionCode(), targetPerformer1.get(j).getFunctionCode())) {
                    specificError = true;
                }
                //compare Time
                if (!compareTime(sourcePerformer1.get(i).getTime(),targetPerformer1.get(j).getTime())) {
                    specificError = true;
                }
                //compare AssignedEntity
                if (!assignedEntityComparison(sourcePerformer1.get(i).getAssignedEntity(),targetPerformer1.get(j).getAssignedEntity())) {
                    specificError = true;
                }
                //typeCode
                if (!compareTypeCode(sourcePerformer1.get(i).getTypeCode(),targetPerformer1.get(j).getTypeCode())) {
                    specificError = true;
                }
                //compare NullFlavor
                if (!compareNullFlavor(sourcePerformer1.get(i).getNullFlavor(), targetPerformer1.get(j).getNullFlavor())) {
                    specificError = true;
                }


                if (!specificError){
                    targetMatches++;
                }

            }
            if (targetMatches == 0) {
                errorExists = true;
//                comparisonReport.addMessage("Performers Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
//                comparisonReport.addMessage("Performers Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private boolean authenticatorComparison(EList<Authenticator> sourceAuthenticator, EList<Authenticator> targetAuthenticator) {
        boolean errorExists = false;
        for (int i=0;i<sourceAuthenticator.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetAuthenticator.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (!compareRealmCodes(sourceAuthenticator.get(i).getRealmCodes(), targetAuthenticator.get(j).getRealmCodes())) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourceAuthenticator.get(i).getTypeId(), targetAuthenticator.get(j).getTypeId())) {
                    specificError = true;
                }
                //compare TemplateIDs
                if (!compareTemplateID(sourceAuthenticator.get(i).getTemplateIds(), targetAuthenticator.get(j).getTemplateIds())) {
                    specificError = true;
                }
                //compare Time
                if (!compareTime(sourceAuthenticator.get(i).getTime(), targetAuthenticator.get(j).getTime())) {
                    specificError = true;
                }
                //compare Signature Code
                if (!compareCode(sourceAuthenticator.get(i).getSignatureCode(),targetAuthenticator.get(j).getSignatureCode())) {
                    specificError = true;
                }
                //compare Assigned Entity
                if (!assignedEntityComparison(sourceAuthenticator.get(i).getAssignedEntity(),targetAuthenticator.get(j).getAssignedEntity())) {
                    specificError = true;
                }
                //compare Type Code
                if (!compareTypeCode(sourceAuthenticator.get(i).getTypeCode(),targetAuthenticator.get(j).getTypeCode())) {
                    specificError = true;
                }
                //compare NullFlavor
                if (!compareNullFlavor(sourceAuthenticator.get(i).getNullFlavor(), targetAuthenticator.get(j).getNullFlavor())) {
                    specificError = true;
                }


                if (!specificError){
                    targetMatches++;
                }

            }
            if (targetMatches == 0) {
                errorExists = true;
//                comparisonReport.addMessage("Authenticator Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
//                comparisonReport.addMessage("Authenticator Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private boolean component3Comparison(EList<Component3> sourceComponent3, EList<Component3> targetComponent3) {
        boolean errorExists = false;
        for (int i=0;i<sourceComponent3.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetComponent3.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (!compareRealmCodes(sourceComponent3.get(i).getRealmCodes(), targetComponent3.get(j).getRealmCodes())) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourceComponent3.get(i).getTypeId(), targetComponent3.get(j).getTypeId())) {
                    specificError = true;
                }
                //compare TemplateIDs
                if (!compareTemplateID(sourceComponent3.get(i).getTemplateIds(), targetComponent3.get(j).getTemplateIds())) {
                    specificError = true;
                }
                //compare Section
                if (!sectionComparison(sourceComponent3.get(i).getSection(), targetComponent3.get(j).getSection())) {
                    specificError = true;
                }
                //compare contextConductionInd
                if (sourceComponent3.get(i).getContextConductionInd() != targetComponent3.get(j).getContextConductionInd()) {
                    errorExists = true;
//                    comparisonReport.addMessage("Context Conduction Ind in " + errorMessage + " -> Context Conduction Ind\n");
                }
                //compare Type Code
                if (!compareTypeCode(sourceComponent3.get(i).getTypeCode(),targetComponent3.get(j).getTypeCode())) {
                    specificError = true;
                }
                //compare NullFlavor
                if (!compareNullFlavor(sourceComponent3.get(i).getNullFlavor(), targetComponent3.get(j).getNullFlavor())) {
                    specificError = true;
                }


                if (!specificError){
                    targetMatches++;
                }

            }
            if (targetMatches == 0) {
                errorExists = true;
//                comparisonReport.addMessage("Component3 Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
//                comparisonReport.addMessage("Component3 Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private boolean organizationPartOfComparison(OrganizationPartOf sourceOrganizationPartOf, OrganizationPartOf targetOrganizationPartOf) {
        boolean errorExists = false;
        if (sourceOrganizationPartOf == null && targetOrganizationPartOf == null) {
            return true;
        } else if (sourceOrganizationPartOf != null && targetOrganizationPartOf != null) {
            //realmCode
            if (!compareRealmCodes(sourceOrganizationPartOf.getRealmCodes(),targetOrganizationPartOf.getRealmCodes())) {
                errorExists = true;
            }
            //result Id
            if (!typeIDComparison(sourceOrganizationPartOf.getTypeId(),targetOrganizationPartOf.getTypeId())) {
                errorExists = true;
            }
            //template ID
            if (!compareTemplateID(sourceOrganizationPartOf.getTemplateIds(),targetOrganizationPartOf.getTemplateIds())) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceOrganizationPartOf.getIds(),targetOrganizationPartOf.getIds())) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceOrganizationPartOf.getCode(),targetOrganizationPartOf.getCode())) {
                errorExists = true;
            }
            //statusCode
            if (!compareCode(sourceOrganizationPartOf.getStatusCode(),targetOrganizationPartOf.getStatusCode())) {
                errorExists = true;
            }
            // effectiveTime
            if (!compareEffectiveTime(sourceOrganizationPartOf.getEffectiveTime(),targetOrganizationPartOf.getEffectiveTime())) {
                errorExists = true;
            }
            // wholeOrganization - Organization
            if (!organizationComparison(sourceOrganizationPartOf.getWholeOrganization(),targetOrganizationPartOf.getWholeOrganization())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceOrganizationPartOf.getNullFlavor(),targetOrganizationPartOf.getNullFlavor())) {
                errorExists = true;
            }
            //classCode
            if (!compareClassCode(sourceOrganizationPartOf.getClassCode(),targetOrganizationPartOf.getClassCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean maintainedEntityComparison(EList<MaintainedEntity> sourceMaintainedEntity, EList<MaintainedEntity> targetMaintainedEntity) {
        boolean errorExists = false;
        for (int i=0;i<sourceMaintainedEntity.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetMaintainedEntity.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (!compareRealmCodes(sourceMaintainedEntity.get(i).getRealmCodes(), targetMaintainedEntity.get(j).getRealmCodes())) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourceMaintainedEntity.get(i).getTypeId(), targetMaintainedEntity.get(j).getTypeId())) {
                    specificError = true;
                }
                //compare TemplateIDs
                if (!compareTemplateID(sourceMaintainedEntity.get(i).getTemplateIds(), targetMaintainedEntity.get(j).getTemplateIds())) {
                    specificError = true;
                }
                //compare EffectiveTime
                if (!compareEffectiveTime(sourceMaintainedEntity.get(i).getEffectiveTime(), targetMaintainedEntity.get(j).getEffectiveTime())) {
                    specificError = true;
                }
                //compare Maintaining Person
                if (!personComparison(sourceMaintainedEntity.get(i).getMaintainingPerson(),targetMaintainedEntity.get(j).getMaintainingPerson())) {
                    specificError = true;
                }
                //compare Class Code
                if (!compareClassCode(sourceMaintainedEntity.get(i).getClassCode(),targetMaintainedEntity.get(j).getClassCode())) {
                    specificError = true;
                }
                //compare NullFlavor
                if (!compareNullFlavor(sourceMaintainedEntity.get(i).getNullFlavor(), targetMaintainedEntity.get(j).getNullFlavor())) {
                    specificError = true;
                }


                if (!specificError){
                    targetMatches++;
                }

            }
            if (targetMatches == 0) {
                errorExists = true;
//                comparisonReport.addMessage("MaintainedEntity Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
//                comparisonReport.addMessage("MaintainedEntity Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private boolean intendedRecipientComparison(IntendedRecipient sourceIntendedRecipeint, IntendedRecipient targetIntendedRecipient) {
        boolean errorExists = false;
        if (sourceIntendedRecipeint == null && targetIntendedRecipient == null) {
            return true;
        } else if (sourceIntendedRecipeint != null && targetIntendedRecipient != null) {
            //realmCode
            if (!compareRealmCodes(sourceIntendedRecipeint.getRealmCodes(),targetIntendedRecipient.getRealmCodes())) {
                errorExists = true;
            }
            //result Id
            if (!typeIDComparison(sourceIntendedRecipeint.getTypeId(),targetIntendedRecipient.getTypeId())) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceIntendedRecipeint.getIds(),targetIntendedRecipient.getIds())) {
                errorExists = true;
            }
            //Addr
            if (!compareAddr(sourceIntendedRecipeint.getAddrs(),targetIntendedRecipient.getAddrs())) {
                errorExists = true;
            }
            //Telecom
            if (!compareTelcom(sourceIntendedRecipeint.getTelecoms(),targetIntendedRecipient.getTelecoms())) {
                errorExists = true;
            }
            //Information Recipient
            if (!personComparison(sourceIntendedRecipeint.getInformationRecipient(),targetIntendedRecipient.getInformationRecipient())) {
                errorExists = true;
            }
            // ReceivedOrganization - Organization
            if (!organizationComparison(sourceIntendedRecipeint.getReceivedOrganization(),targetIntendedRecipient.getReceivedOrganization())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceIntendedRecipeint.getNullFlavor(),targetIntendedRecipient.getNullFlavor())) {
                errorExists = true;
            }
            //classCode
            if (!compareClassCode(sourceIntendedRecipeint.getClassCode(),targetIntendedRecipient.getClassCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean orderComparison(Order sourceOrder, Order targetOrder) {
        boolean errorExists = false;
        if (sourceOrder == null && targetOrder == null) {
            return true;
        } else if (sourceOrder != null && targetOrder != null) {
            //realmCode
            if (!compareRealmCodes(sourceOrder.getRealmCodes(),targetOrder.getRealmCodes())) {
                errorExists = true;
            }
            //result Id
            if (!typeIDComparison(sourceOrder.getTypeId(),targetOrder.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceOrder.getTemplateIds(),targetOrder.getTemplateIds())) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceOrder.getIds(),targetOrder.getIds())) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceOrder.getCode(),targetOrder.getCode())) {
                errorExists = true;
            }
            //PriorityCode
            if (!compareCode(sourceOrder.getPriorityCode(),targetOrder.getPriorityCode())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceOrder.getNullFlavor(),targetOrder.getNullFlavor())) {
                errorExists = true;
            }
            //classCode
            if (!compareClassCode(sourceOrder.getClassCode(),targetOrder.getClassCode())) {
                errorExists = true;
            }
            //moodCode
            if (!compareMoodCode(sourceOrder.getMoodCode(),targetOrder.getMoodCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean parentDocumentComparison(ParentDocument sourceParentDocument, ParentDocument targetParentDocument) {
        boolean errorExists = false;
        if (sourceParentDocument == null && targetParentDocument == null) {
            return true;
        } else if (sourceParentDocument != null && targetParentDocument != null) {
            //realmCode
            if (!compareRealmCodes(sourceParentDocument.getRealmCodes(),targetParentDocument.getRealmCodes())) {
                errorExists = true;
            }
            //result Id
            if (!typeIDComparison(sourceParentDocument.getTypeId(),targetParentDocument.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceParentDocument.getTemplateIds(),targetParentDocument.getTemplateIds())) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceParentDocument.getIds(),targetParentDocument.getIds())) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceParentDocument.getCode(),targetParentDocument.getCode())) {
                errorExists = true;
            }
            //text
            if (!compareText(sourceParentDocument.getText(),targetParentDocument.getText())) {
                errorExists = true;
            }
            //setID
            if (!compareSetID(sourceParentDocument.getSetId(),targetParentDocument.getSetId())) {
                errorExists = true;
            }
            //versionNumber
            if (!compareVersionNumber(sourceParentDocument.getVersionNumber(),targetParentDocument.getVersionNumber())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceParentDocument.getNullFlavor(),targetParentDocument.getNullFlavor())) {
                errorExists = true;
            }
            //classCode
            if (!compareClassCode(sourceParentDocument.getClassCode(),targetParentDocument.getClassCode())) {
                errorExists = true;
            }
            //moodCode
            if (!compareMoodCode(sourceParentDocument.getMoodCode(),targetParentDocument.getMoodCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean consentComparison(Consent sourceConsent, Consent targetConsesnt) {
        boolean errorExists = false;
        if (sourceConsent == null && targetConsesnt == null) {
            return true;
        } else if (sourceConsent != null && targetConsesnt != null) {
            //realmCode
            if (!compareRealmCodes(sourceConsent.getRealmCodes(),targetConsesnt.getRealmCodes())) {
                errorExists = true;
            }
            //result Id
            if (!typeIDComparison(sourceConsent.getTypeId(),targetConsesnt.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceConsent.getTemplateIds(),targetConsesnt.getTemplateIds())) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceConsent.getIds(),targetConsesnt.getIds())) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceConsent.getCode(),targetConsesnt.getCode())) {
                errorExists = true;
            }
            //statusCode
            if (!compareCode(sourceConsent.getStatusCode(),targetConsesnt.getStatusCode())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceConsent.getNullFlavor(),targetConsesnt.getNullFlavor())) {
                errorExists = true;
            }
            //classCode
            if (!compareClassCode(sourceConsent.getClassCode(),targetConsesnt.getClassCode())) {
                errorExists = true;
            }
            //moodCode
            if (!compareMoodCode(sourceConsent.getMoodCode(),targetConsesnt.getMoodCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean encompassingEncounterComparison(EncompassingEncounter sourceEncompassingEncounter, EncompassingEncounter targetEncompassingEncounter) {
        boolean errorExists = false;
        if (sourceEncompassingEncounter == null && targetEncompassingEncounter == null) {
            return true;
        } else if (sourceEncompassingEncounter != null && targetEncompassingEncounter != null) {
            //realmCode
            if (!compareRealmCodes(sourceEncompassingEncounter.getRealmCodes(),targetEncompassingEncounter.getRealmCodes())) {
                errorExists = true;
            }
            //result Id
            if (!typeIDComparison(sourceEncompassingEncounter.getTypeId(),targetEncompassingEncounter.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceEncompassingEncounter.getTemplateIds(),targetEncompassingEncounter.getTemplateIds())) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceEncompassingEncounter.getIds(),targetEncompassingEncounter.getIds())) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceEncompassingEncounter.getCode(),targetEncompassingEncounter.getCode())) {
                errorExists = true;
            }
            //effectiveTime
            if (!compareEffectiveTime(sourceEncompassingEncounter.getEffectiveTime(),targetEncompassingEncounter.getEffectiveTime())) {
                errorExists = true;
            }
            //dischargeDispositionCode
            if (!compareCode(sourceEncompassingEncounter.getDischargeDispositionCode(),targetEncompassingEncounter.getDischargeDispositionCode())) {
                errorExists = true;
            }
            //responsibleParty
            if (!responsiblePartyComparison(sourceEncompassingEncounter.getResponsibleParty(),targetEncompassingEncounter.getResponsibleParty())) {
                errorExists = true;
            }
            //encounterParticipant
            if (!encounterParticipantComparison(sourceEncompassingEncounter.getEncounterParticipants(),targetEncompassingEncounter.getEncounterParticipants())) {
                errorExists = true;
            }
            //location
            if (!locationComparison(sourceEncompassingEncounter.getLocation(),targetEncompassingEncounter.getLocation())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceEncompassingEncounter.getNullFlavor(),targetEncompassingEncounter.getNullFlavor())) {
                errorExists = true;
            }
            //classCode
            if (!compareClassCode(sourceEncompassingEncounter.getClassCode(),targetEncompassingEncounter.getClassCode())) {
                errorExists = true;
            }
            //moodCode
            if (!compareMoodCode(sourceEncompassingEncounter.getMoodCode(),targetEncompassingEncounter.getMoodCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean placeComparison(Place sourcePlace, Place targetPlace) {
        boolean errorExists = false;
        if (sourcePlace == null && targetPlace == null) {
            return true;
        } else if (sourcePlace != null && targetPlace != null) {
            //realmCode
            if (!compareRealmCodes(sourcePlace.getRealmCodes(),targetPlace.getRealmCodes())) {
                errorExists = true;
            }
            //result Id
            if (!typeIDComparison(sourcePlace.getTypeId(),targetPlace.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourcePlace.getTemplateIds(),targetPlace.getTemplateIds())) {
                errorExists = true;
            }
            //name
            if (!compareNamesEN(sourcePlace.getNames(),targetPlace.getNames())) {
                errorExists = true;
            }
            //Addr
            if (!compareAddr(sourcePlace.getAddrs(),targetPlace.getAddrs())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourcePlace.getNullFlavor(),targetPlace.getNullFlavor())) {
                errorExists = true;
            }
            //classCode
            if (!compareClassCode(sourcePlace.getClassCode(),targetPlace.getClassCode())) {
                errorExists = true;
            }
            //DeterminerCode
            if (!compareDeterminerCode(sourcePlace.getDeterminerCode(),targetPlace.getDeterminerCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean sectionComparison(Section sourceSection, Section targetSection) {
        boolean errorExists = false;
        if (sourceSection == null && targetSection == null) {
            return true;
        } else if (sourceSection != null && targetSection != null) {
            //realmCode
            if (!compareRealmCodes(sourceSection.getRealmCodes(),targetSection.getRealmCodes())) {
                errorExists = true;
            }
            //result Id
            if (!typeIDComparison(sourceSection.getTypeId(),targetSection.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceSection.getTemplateIds(),targetSection.getTemplateIds())) {
                errorExists = true;
            }
            //id
            if (!compareID(sourceSection.getId(),targetSection.getId())) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceSection.getCode(),targetSection.getCode())) {
                errorExists = true;
            }
            //title
            if (!compareTitle(sourceSection.getTitle(),targetSection.getTitle())) {
                errorExists = true;
            }
            //text
            if (!compareText(sourceSection.getText(),targetSection.getText())) {
                errorExists = true;
            }
            //confidentialityCode
            if (!compareCode(sourceSection.getConfidentialityCode(),targetSection.getConfidentialityCode())) {
                errorExists = true;
            }
            //languageCode
            if (!compareLanguageCode(sourceSection.getLanguageCode(),targetSection.getLanguageCode())) {
                errorExists = true;
            }
            //subject
            if (!subjectComparison(sourceSection.getSubject(),targetSection.getSubject())) {
                errorExists = true;
            }
            //Author
            if (!authorsComparison(sourceSection.getAuthors(),targetSection.getAuthors())) {
                errorExists = true;
            }
            //Informant12
            if (!informantsComparison(sourceSection.getInformants(),targetSection.getInformants())) {
                errorExists = true;
            }
            //Entry
            if (!entryComparison(sourceSection.getEntries(),targetSection.getEntries())) {
                errorExists = true;
            }
            //Component 5
            if (!component5Comparison(sourceSection.getComponents(),targetSection.getComponents())) {
                errorExists = true;
            }
            //ID
            if (!compareIDAttribute(sourceSection.getSectionId(),targetSection.getSectionId())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceSection.getNullFlavor(),targetSection.getNullFlavor())) {
                errorExists = true;
            }
            //classCode
            if (!compareClassCode(sourceSection.getClassCode(),targetSection.getClassCode())) {
                errorExists = true;
            }
            //moodCode
            if (!compareMoodCode(sourceSection.getMoodCode(),targetSection.getMoodCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean responsiblePartyComparison(ResponsibleParty sourceResponsibleParty, ResponsibleParty targetResponsibleParty) {
        boolean errorExists = false;
        if (sourceResponsibleParty == null && targetResponsibleParty == null) {
            return true;
        } else if (sourceResponsibleParty != null && targetResponsibleParty != null) {
            //realmCode
            if (!compareRealmCodes(sourceResponsibleParty.getRealmCodes(),targetResponsibleParty.getRealmCodes())) {
                errorExists = true;
            }
            //result Id
            if (!typeIDComparison(sourceResponsibleParty.getTypeId(),targetResponsibleParty.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceResponsibleParty.getTemplateIds(),targetResponsibleParty.getTemplateIds())) {
                errorExists = true;
            }
            //AssignedEntity
            if (!assignedEntityComparison(sourceResponsibleParty.getAssignedEntity(),targetResponsibleParty.getAssignedEntity())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceResponsibleParty.getNullFlavor(),targetResponsibleParty.getNullFlavor())) {
                errorExists = true;
            }
            //typeCode
            if (!compareTypeCode(sourceResponsibleParty.getTypeCode(),targetResponsibleParty.getTypeCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean encounterParticipantComparison(EList<EncounterParticipant> sourceEncounterParticipant, EList<EncounterParticipant> targetEncounterParticipant) {
        boolean errorExists = false;
        for (int i=0;i<sourceEncounterParticipant.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetEncounterParticipant.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (!compareRealmCodes(sourceEncounterParticipant.get(i).getRealmCodes(), targetEncounterParticipant.get(j).getRealmCodes())) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourceEncounterParticipant.get(i).getTypeId(), targetEncounterParticipant.get(j).getTypeId())) {
                    specificError = true;
                }
                //compare TemplateIDs
                if (!compareTemplateID(sourceEncounterParticipant.get(i).getTemplateIds(), targetEncounterParticipant.get(j).getTemplateIds())) {
                    specificError = true;
                }
                //compare Time
                if (!compareEffectiveTime(sourceEncounterParticipant.get(i).getTime(), targetEncounterParticipant.get(j).getTime())) {
                    specificError = true;
                }
                //compare assignedEntity
                if (!assignedEntityComparison(sourceEncounterParticipant.get(i).getAssignedEntity(),targetEncounterParticipant.get(j).getAssignedEntity())) {
                    specificError = true;
                }
                //compare Type Code
                if (!compareTypeCode(sourceEncounterParticipant.get(i).getTypeCode(),targetEncounterParticipant.get(j).getTypeCode())) {
                    specificError = true;
                }
                //compare NullFlavor
                if (!compareNullFlavor(sourceEncounterParticipant.get(i).getNullFlavor(), targetEncounterParticipant.get(j).getNullFlavor())) {
                    specificError = true;
                }


                if (!specificError){
                    targetMatches++;
                }

            }
            if (targetMatches == 0) {
                errorExists = true;
//                comparisonReport.addMessage("encounterParticipant Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
//                comparisonReport.addMessage("encounterParticipant Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;

    }

    private boolean locationComparison(Location sourceLocation, Location targetLocation) {
        boolean errorExists = false;
        if (sourceLocation == null && targetLocation == null) {
            return true;
        } else if (sourceLocation != null && targetLocation != null) {
            //realmCode
            if (!compareRealmCodes(sourceLocation.getRealmCodes(),targetLocation.getRealmCodes())) {
                errorExists = true;
            }
            //result Id
            if (!typeIDComparison(sourceLocation.getTypeId(),targetLocation.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceLocation.getTemplateIds(),targetLocation.getTemplateIds())) {
                errorExists = true;
            }
            //HealthCareFacility
            if (!healthCareFacilityComparison(sourceLocation.getHealthCareFacility(),targetLocation.getHealthCareFacility())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceLocation.getNullFlavor(),targetLocation.getNullFlavor())) {
                errorExists = true;
            }
            //typeCode
            if (!compareTypeCode(sourceLocation.getTypeCode(),targetLocation.getTypeCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean subjectComparison(Subject sourceSubject, Subject targetSubject) {
        boolean errorExists = false;
        if (sourceSubject == null && targetSubject == null) {
            return true;
        } else if (sourceSubject != null && targetSubject != null) {
            //realmCode
            if (!compareRealmCodes(sourceSubject.getRealmCodes(),targetSubject.getRealmCodes())) {
                errorExists = true;
            }
            //result Id
            if (!typeIDComparison(sourceSubject.getTypeId(),targetSubject.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceSubject.getTemplateIds(),targetSubject.getTemplateIds())) {
                errorExists = true;
            }
            //AwarenessCode
            if (!compareCode(sourceSubject.getAwarenessCode(),targetSubject.getAwarenessCode())) {
                errorExists = true;
            }
            //Related Subject
            if (!relatedSubjectComparison(sourceSubject.getRelatedSubject(),targetSubject.getRelatedSubject())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceSubject.getNullFlavor(),targetSubject.getNullFlavor())) {
                errorExists = true;
            }
            //typeCode
            if (!compareTypeCode(sourceSubject.getTypeCode(),targetSubject.getTypeCode())) {
                errorExists = true;
            }
            //contextControlCode
            if (!compareContextControlCode(sourceSubject.getContextControlCode(),targetSubject.getContextControlCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean entryComparison(EList<Entry> sourceEntry, EList<Entry> targetEntry) {
        boolean errorExists = false;
        for (int i=0;i<sourceEntry.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetEntry.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (!compareRealmCodes(sourceEntry.get(i).getRealmCodes(), targetEntry.get(j).getRealmCodes())) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourceEntry.get(i).getTypeId(), targetEntry.get(j).getTypeId())) {
                    specificError = true;
                }
                //compare TemplateIDs
                if (!compareTemplateID(sourceEntry.get(i).getTemplateIds(), targetEntry.get(j).getTemplateIds())) {
                    specificError = true;
                }
                //Choice Act, Encounter, Observation, ObservationMedia, Organizer, Procedure, Region of Interest, Substance Administration, Supply
                if (!(sourceEntry.get(i).getAct() == null && targetEntry.get(j).getAct() == null
                        && sourceEntry.get(i).getEncounter() == null && targetEntry.get(j).getEncounter() == null
                        && sourceEntry.get(i).getObservation() == null && targetEntry.get(j).getObservation() == null
                        && sourceEntry.get(i).getObservationMedia() == null && targetEntry.get(j).getObservationMedia() == null
                        && sourceEntry.get(i).getOrganizer() == null && targetEntry.get(j).getOrganizer() == null
                        && sourceEntry.get(i).getProcedure() == null && targetEntry.get(j).getProcedure() == null
                        && sourceEntry.get(i).getRegionOfInterest() == null && targetEntry.get(j).getRegionOfInterest() == null
                        && sourceEntry.get(i).getSubstanceAdministration() == null && targetEntry.get(j).getSubstanceAdministration() == null
                        && sourceEntry.get(i).getSupply() == null && targetEntry.get(j).getSupply() == null)) {
                    if (!((actComparison(sourceEntry.get(i).getAct(), targetEntry.get(j).getAct()) && sourceEntry.get(i).getAct() != null)
                            || (encounterComparison(sourceEntry.get(i).getEncounter(), targetEntry.get(j).getEncounter()) && sourceEntry.get(i).getEncounter() != null)
                            || (observationComparison(sourceEntry.get(i).getObservation(), targetEntry.get(j).getObservation()) && sourceEntry.get(i).getObservation() != null)
                            || (observationMediaComparison(sourceEntry.get(i).getObservationMedia(), targetEntry.get(j).getObservationMedia()) && sourceEntry.get(i).getObservationMedia() != null)
                            || (organizerComparison(sourceEntry.get(i).getOrganizer(), targetEntry.get(j).getOrganizer()) && sourceEntry.get(i).getOrganizer() != null)
                            || (procedureComparison(sourceEntry.get(i).getProcedure(), targetEntry.get(j).getProcedure()) && sourceEntry.get(i).getProcedure() != null)
                            || (regionOfInterestComparison(sourceEntry.get(i).getRegionOfInterest(), targetEntry.get(j).getRegionOfInterest()) && sourceEntry.get(i).getRegionOfInterest() != null)
                            || (substanceAdministrationComparison(sourceEntry.get(i).getSubstanceAdministration(), targetEntry.get(j).getSubstanceAdministration()) && sourceEntry.get(i).getSubstanceAdministration() != null)
                            || (supplyComparison(sourceEntry.get(i).getSupply(), targetEntry.get(j).getSupply()) && sourceEntry.get(i).getSupply() != null))) {
                        specificError = true;
                    }
                }
                //compare contextConductionInd
                if (sourceEntry.get(i).getContextConductionInd() != targetEntry.get(j).getContextConductionInd()) {
                    errorExists = true;
//                    comparisonReport.addMessage("Context Conduction Ind in " + errorMessage + " -> Context Conduction Ind\n");
                }
                //compare Type Code
                if (!compareTypeCode(sourceEntry.get(i).getTypeCode(),targetEntry.get(j).getTypeCode())) {
                    specificError = true;
                }
                //compare NullFlavor
                if (!compareNullFlavor(sourceEntry.get(i).getNullFlavor(), targetEntry.get(j).getNullFlavor())) {
                    specificError = true;
                }


                if (!specificError){
                    targetMatches++;
                }

            }
            if (targetMatches == 0) {
                errorExists = true;
//                comparisonReport.addMessage("Entry Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
//                comparisonReport.addMessage("Entry Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private boolean component5Comparison(EList<Component5> sourceComponent, EList<Component5> targetComponent) {
        boolean errorExists = false;
        for (int i=0;i<sourceComponent.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetComponent.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (!compareRealmCodes(sourceComponent.get(i).getRealmCodes(), targetComponent.get(j).getRealmCodes())) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourceComponent.get(i).getTypeId(), targetComponent.get(j).getTypeId())) {
                    specificError = true;
                }
                //compare TemplateIDs
                if (!compareTemplateID(sourceComponent.get(i).getTemplateIds(), targetComponent.get(j).getTemplateIds())) {
                    specificError = true;
                }
                //Section
                if (!sectionComparison(sourceComponent.get(i).getSection(),targetComponent.get(j).getSection())) {
                    specificError = true;
                }
                //compare contextConductionInd
                if (sourceComponent.get(i).getContextConductionInd() != targetComponent.get(j).getContextConductionInd()) {
                    errorExists = true;
//                    comparisonReport.addMessage("Context Conduction Ind in " + errorMessage + " -> Context Conduction Ind\n");
                }
                //compare Type Code
                if (!compareTypeCode(sourceComponent.get(i).getTypeCode(),targetComponent.get(j).getTypeCode())) {
                    specificError = true;
                }
                //compare NullFlavor
                if (!compareNullFlavor(sourceComponent.get(i).getNullFlavor(), targetComponent.get(j).getNullFlavor())) {
                    specificError = true;
                }


                if (!specificError){
                    targetMatches++;
                }

            }
            if (targetMatches == 0) {
                errorExists = true;
//                comparisonReport.addMessage("Component5 Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
//                comparisonReport.addMessage("Component5 Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private boolean healthCareFacilityComparison(HealthCareFacility sourceHealthCareFacility, HealthCareFacility targetHealthCareFacility) {
        boolean errorExists = false;
        if (sourceHealthCareFacility == null && targetHealthCareFacility == null) {
            return true;
        } else if (sourceHealthCareFacility != null && targetHealthCareFacility != null) {
            //realmCode
            if (!compareRealmCodes(sourceHealthCareFacility.getRealmCodes(),targetHealthCareFacility.getRealmCodes())) {
                errorExists = true;
            }
            //result Id
            if (!typeIDComparison(sourceHealthCareFacility.getTypeId(),targetHealthCareFacility.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceHealthCareFacility.getTemplateIds(),targetHealthCareFacility.getTemplateIds())) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceHealthCareFacility.getIds(),targetHealthCareFacility.getIds())) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceHealthCareFacility.getCode(),targetHealthCareFacility.getCode())) {
                errorExists = true;
            }
            //location - Place
            if (!placeComparison(sourceHealthCareFacility.getLocation(),targetHealthCareFacility.getLocation())) {
                errorExists = true;
            }
            //serviceProviderOgranization - Organization
            if (!organizationComparison(sourceHealthCareFacility.getServiceProviderOrganization(),targetHealthCareFacility.getServiceProviderOrganization())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceHealthCareFacility.getNullFlavor(),targetHealthCareFacility.getNullFlavor())) {
                errorExists = true;
            }
            //ClassCode
            if (!compareClassCode(sourceHealthCareFacility.getClassCode(),targetHealthCareFacility.getClassCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean relatedSubjectComparison(RelatedSubject sourceRelatedSubject, RelatedSubject targetRelatedSubject) {
        boolean errorExists = false;
        if (sourceRelatedSubject == null && targetRelatedSubject == null) {
            return true;
        } else if (sourceRelatedSubject != null && targetRelatedSubject != null) {
            //realmCode
            if (!compareRealmCodes(sourceRelatedSubject.getRealmCodes(),targetRelatedSubject.getRealmCodes())) {
                errorExists = true;
            }
            //result Id
            if (!typeIDComparison(sourceRelatedSubject.getTypeId(),targetRelatedSubject.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceRelatedSubject.getTemplateIds(),targetRelatedSubject.getTemplateIds())) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceRelatedSubject.getCode(),targetRelatedSubject.getCode())) {
                errorExists = true;
            }
            //Addr
            if (!compareAddr(sourceRelatedSubject.getAddrs(),targetRelatedSubject.getAddrs())) {
                errorExists = true;
            }
            //Telecom
            if (!compareTelcom(sourceRelatedSubject.getTelecoms(),targetRelatedSubject.getTelecoms())) {
                errorExists = true;
            }
            //subject - SubjectPerson
            if (!subjectPersonComparison(sourceRelatedSubject.getSubject(),targetRelatedSubject.getSubject())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceRelatedSubject.getNullFlavor(),targetRelatedSubject.getNullFlavor())) {
                errorExists = true;
            }
            //ClassCode
            if (!compareClassCode(sourceRelatedSubject.getClassCode(),targetRelatedSubject.getClassCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean actComparison(Act sourceAct, Act targetAct) {
        boolean errorExists = false;
        if (sourceAct == null && targetAct == null) {
            return true;
        } else if (sourceAct != null && targetAct != null) {
            //realmCode
            if (!compareRealmCodes(sourceAct.getRealmCodes(),targetAct.getRealmCodes())) {
                errorExists = true;
            }
            //result Id
            if (!typeIDComparison(sourceAct.getTypeId(),targetAct.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceAct.getTemplateIds(),targetAct.getTemplateIds())) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceAct.getIds(),targetAct.getIds())) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceAct.getCode(),targetAct.getCode())) {
                errorExists = true;
            }
            //text
            if (!compareText(sourceAct.getText(),targetAct.getText())) {
                errorExists = true;
            }
            //statusCode
            if (!compareCode(sourceAct.getCode(),targetAct.getCode())) {
                errorExists = true;
            }
            //effectiveTime
            if (!compareEffectiveTime(sourceAct.getEffectiveTime(),targetAct.getEffectiveTime())) {
                errorExists = true;
            }
            //priorityCode
            if (!compareCode(sourceAct.getPriorityCode(),targetAct.getPriorityCode())) {
                errorExists = true;
            }
            //languageCode
            if (!compareLanguageCode(sourceAct.getLanguageCode(),targetAct.getLanguageCode())) {
                errorExists = true;
            }
            //subject
            if (!subjectComparison(sourceAct.getSubject(),targetAct.getSubject())) {
                errorExists = true;
            }
            //Specimen
            if (!specimenComparison(sourceAct.getSpecimens(),targetAct.getSpecimens())) {
                errorExists = true;
            }
            //performer - Performer2
            if (!performer2Comparison(sourceAct.getPerformers(),targetAct.getPerformers())) {
                errorExists = true;
            }
            //author - Author
            if (!authorsComparison(sourceAct.getAuthors(),targetAct.getAuthors())) {
                errorExists = true;
            }
            //informant - Informant12
            if (!informantsComparison(sourceAct.getInformants(),targetAct.getInformants())) {
                errorExists = true;
            }
            //participant - Participant2
            if (!participants2Comparison(sourceAct.getParticipants(),targetAct.getParticipants())) {
                errorExists = true;
            }
            //entryRelationship - EntryRelationship
            if (!entryRelationshipComparison(sourceAct.getEntryRelationships(),targetAct.getEntryRelationships())) {
                errorExists = true;
            }
            //reference - Reference
            if (!referenceComparison(sourceAct.getReferences(),targetAct.getReferences())) {
                errorExists = true;
            }
            //Precondition - Precondition
            if (!preconditionComparison(sourceAct.getPreconditions(),targetAct.getPreconditions())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceAct.getNullFlavor(),targetAct.getNullFlavor())) {
                errorExists = true;
            }
            //ClassCode
            if (!compareClassCode(sourceAct.getClassCode(),targetAct.getClassCode())) {
                errorExists = true;
            }
            //moodCode
            if (!compareMoodCode(sourceAct.getMoodCode(),targetAct.getMoodCode())) {
                errorExists = true;
            }
            //negationInd
            if (sourceAct.getNegationInd() != targetAct.getNegationInd()) {
                errorExists = true;
//                comparisonReport.addMessage("Negation Ind error in " + errorMessage + " -> Negation Ind");
            }
            return !errorExists;
        }
        return false;
    }

    private boolean encounterComparison(Encounter sourceEncounter, Encounter targetEncounter) {
        boolean errorExists = false;
        if (sourceEncounter == null && targetEncounter == null) {
            return true;
        } else if (sourceEncounter != null && targetEncounter != null) {
            //realmCode
            if (!compareRealmCodes(sourceEncounter.getRealmCodes(),targetEncounter.getRealmCodes())) {
                errorExists = true;
            }
            //result Id
            if (!typeIDComparison(sourceEncounter.getTypeId(),targetEncounter.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceEncounter.getTemplateIds(),targetEncounter.getTemplateIds())) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceEncounter.getIds(),targetEncounter.getIds())) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceEncounter.getCode(),targetEncounter.getCode())) {
                errorExists = true;
            }
            //text
            if (!compareText(sourceEncounter.getText(),targetEncounter.getText())) {
                errorExists = true;
            }
            //statusCode
            if (!compareCode(sourceEncounter.getCode(),targetEncounter.getCode())) {
                errorExists = true;
            }
            //effectiveTime
            if (!compareEffectiveTime(sourceEncounter.getEffectiveTime(),targetEncounter.getEffectiveTime())) {
                errorExists = true;
            }
            //priorityCode
            if (!compareCode(sourceEncounter.getPriorityCode(),targetEncounter.getPriorityCode())) {
                errorExists = true;
            }
            //subject
            if (!subjectComparison(sourceEncounter.getSubject(),targetEncounter.getSubject())) {
                errorExists = true;
            }
            //Specimen
            if (!specimenComparison(sourceEncounter.getSpecimens(),targetEncounter.getSpecimens())) {
                errorExists = true;
            }
            //performer - Performer2
            if (!performer2Comparison(sourceEncounter.getPerformers(),targetEncounter.getPerformers())) {
                errorExists = true;
            }
            //author - Author
            if (!authorsComparison(sourceEncounter.getAuthors(),targetEncounter.getAuthors())) {
                errorExists = true;
            }
            //informant - Informant12
            if (!informantsComparison(sourceEncounter.getInformants(),targetEncounter.getInformants())) {
                errorExists = true;
            }
            //participant - Participant2
            if (!participants2Comparison(sourceEncounter.getParticipants(),targetEncounter.getParticipants())) {
                errorExists = true;
            }
            //entryRelationship - EntryRelationship
            if (!entryRelationshipComparison(sourceEncounter.getEntryRelationships(),targetEncounter.getEntryRelationships())) {
                errorExists = true;
            }
            //reference - Reference
            if (!referenceComparison(sourceEncounter.getReferences(),targetEncounter.getReferences())) {
                errorExists = true;
            }
            //Precondition - Precondition
            if (!preconditionComparison(sourceEncounter.getPreconditions(),targetEncounter.getPreconditions())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceEncounter.getNullFlavor(),targetEncounter.getNullFlavor())) {
                errorExists = true;
            }
            //ClassCode
            if (!compareClassCode(sourceEncounter.getClassCode(),targetEncounter.getClassCode())) {
                errorExists = true;
            }
            //moodCode
            if (!compareMoodCode(sourceEncounter.getMoodCode(),targetEncounter.getMoodCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean observationComparison(Observation sourceObservation, Observation targetObservation) {
        boolean errorExists = false;
        if (sourceObservation == null && targetObservation == null) {
            return true;
        } else if (sourceObservation != null && targetObservation != null) {
            //realmCode
            if (!compareRealmCodes(sourceObservation.getRealmCodes(),targetObservation.getRealmCodes())) {
                errorExists = true;
            }
            //result Id
            if (!typeIDComparison(sourceObservation.getTypeId(),targetObservation.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceObservation.getTemplateIds(),targetObservation.getTemplateIds())) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceObservation.getIds(),targetObservation.getIds())) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceObservation.getCode(),targetObservation.getCode())) {
                errorExists = true;
            }
            //derivationExpr
            if (!compareDerivationExpr(sourceObservation.getDerivationExpr(),targetObservation.getDerivationExpr())) {
                errorExists = true;
            }
            //text
            if (!compareText(sourceObservation.getText(),targetObservation.getText())) {
                errorExists = true;
            }
            //statusCode
            if (!compareCode(sourceObservation.getCode(),targetObservation.getCode())) {
                errorExists = true;
            }
            //effectiveTime
            if (!compareEffectiveTime(sourceObservation.getEffectiveTime(),targetObservation.getEffectiveTime())) {
                errorExists = true;
            }
            //priorityCode
            if (!compareCode(sourceObservation.getPriorityCode(),targetObservation.getPriorityCode())) {
                errorExists = true;
            }
            //repeatNumber
            if (!compareRepeatNumber(sourceObservation.getRepeatNumber(),targetObservation.getRepeatNumber())) {
                errorExists = true;
            }
            //languageCode
            if (!compareLanguageCode(sourceObservation.getLanguageCode(),targetObservation.getLanguageCode())) {
                errorExists = true;
            }
            //value
            if (!compareValues(sourceObservation.getValues(),targetObservation.getValues())) {
                errorExists = true;
            }
            //interpretationCode
            if (!compareCodes(sourceObservation.getInterpretationCodes(),targetObservation.getInterpretationCodes())) {
                errorExists = true;
            }
            //methodCode
            if (!compareCodes(sourceObservation.getMethodCodes(),targetObservation.getMethodCodes())) {
                errorExists = true;
            }
            //targetSiteCode
            if (!compareTargetSiteCode(sourceObservation.getTargetSiteCodes(),targetObservation.getTargetSiteCodes())) {
                errorExists = true;
            }
            //subject
            if (!subjectComparison(sourceObservation.getSubject(),targetObservation.getSubject())) {
                errorExists = true;
            }
            //Specimen
            if (!specimenComparison(sourceObservation.getSpecimens(),targetObservation.getSpecimens())) {
                errorExists = true;
            }
            //performer - Performer2
            if (!performer2Comparison(sourceObservation.getPerformers(),targetObservation.getPerformers())) {
                errorExists = true;
            }
            //author - Author
            if (!authorsComparison(sourceObservation.getAuthors(),targetObservation.getAuthors())) {
                errorExists = true;
            }
            //informant - Informant12
            if (!informantsComparison(sourceObservation.getInformants(),targetObservation.getInformants())) {
                errorExists = true;
            }
            //participant - Participant2
            if (!participants2Comparison(sourceObservation.getParticipants(),targetObservation.getParticipants())) {
                errorExists = true;
            }
            //entryRelationship - EntryRelationship
            if (!entryRelationshipComparison(sourceObservation.getEntryRelationships(),targetObservation.getEntryRelationships())) {
                errorExists = true;
            }
            //reference - Reference
            if (!referenceComparison(sourceObservation.getReferences(),targetObservation.getReferences())) {
                errorExists = true;
            }
            //Precondition - Precondition
            if (!preconditionComparison(sourceObservation.getPreconditions(),targetObservation.getPreconditions())) {
                errorExists = true;
            }
            //referenceRange - ReferenceRange
            if (!referenceRangeComparison(sourceObservation.getReferenceRanges(),targetObservation.getReferenceRanges())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceObservation.getNullFlavor(),targetObservation.getNullFlavor())) {
                errorExists = true;
            }
            //ClassCode
            if (!compareClassCode(sourceObservation.getClassCode(),targetObservation.getClassCode())) {
                errorExists = true;
            }
            //negationInd
            if (sourceObservation.getNegationInd() != targetObservation.getNegationInd()) {
                errorExists = true;
//                comparisonReport.addMessage("Negation Ind error in " + errorMessage + " -> Negation Ind");
            }
            return !errorExists;
        }
        return false;
    }

    private boolean observationMediaComparison(ObservationMedia sourceObservationMedia, ObservationMedia targetObservationMedia) {
        boolean errorExists = false;
        if (sourceObservationMedia == null && targetObservationMedia == null) {
            return true;
        } else if (sourceObservationMedia != null && targetObservationMedia != null) {
            //realmCode
            if (!compareRealmCodes(sourceObservationMedia.getRealmCodes(),targetObservationMedia.getRealmCodes())) {
                errorExists = true;
            }
            //result Id
            if (!typeIDComparison(sourceObservationMedia.getTypeId(),targetObservationMedia.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceObservationMedia.getTemplateIds(),targetObservationMedia.getTemplateIds())) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceObservationMedia.getIds(),targetObservationMedia.getIds())) {
                errorExists = true;
            }
            //language code
            if (!compareLanguageCode(sourceObservationMedia.getLanguageCode(),targetObservationMedia.getLanguageCode())) {
                errorExists = true;
            }
            //value
            if (!compareText(sourceObservationMedia.getValue(),targetObservationMedia.getValue())) {
                errorExists = true;
            }
            //subject - Subject
            if (!subjectComparison(sourceObservationMedia.getSubject(),targetObservationMedia.getSubject())) {
                errorExists = true;
            }
            //specimen - Specimen
            if (!specimenComparison(sourceObservationMedia.getSpecimens(),targetObservationMedia.getSpecimens())) {
                errorExists = true;
            }
            //performer - Performer2
            if (!performer2Comparison(sourceObservationMedia.getPerformers(),targetObservationMedia.getPerformers())) {
                errorExists = true;
            }
            //author - Author
            if (!authorsComparison(sourceObservationMedia.getAuthors(),targetObservationMedia.getAuthors())) {
                errorExists = true;
            }
            //informant - Informant12
            if (!informantsComparison(sourceObservationMedia.getInformants(),targetObservationMedia.getInformants())) {
                errorExists = true;
            }
            //participant - Participant2
            if (!participants2Comparison(sourceObservationMedia.getParticipants(),targetObservationMedia.getParticipants())) {
                errorExists = true;
            }
            //entryRelationship - EntryRelationship
            if (!entryRelationshipComparison(sourceObservationMedia.getEntryRelationships(),targetObservationMedia.getEntryRelationships())) {
                errorExists = true;
            }
            //reference - Reference
            if (!referenceComparison(sourceObservationMedia.getReferences(),targetObservationMedia.getReferences())) {
                errorExists = true;
            }
            //precondition - Precondition
            if (!preconditionComparison(sourceObservationMedia.getPreconditions(),targetObservationMedia.getPreconditions())) {
                errorExists = true;
            }
            //ID
            if (!compareIDAttribute(sourceObservationMedia.getObservationMediaId(),targetObservationMedia.getObservationMediaId())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceObservationMedia.getNullFlavor(),targetObservationMedia.getNullFlavor())) {
                errorExists = true;
            }
            //ClassCode
            if (!compareClassCode(sourceObservationMedia.getClassCode(),targetObservationMedia.getClassCode())) {
                errorExists = true;
            }
            //moodCode
            if (!compareMoodCode(sourceObservationMedia.getMoodCode(),targetObservationMedia.getMoodCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean organizerComparison(Organizer sourceOrganizer, Organizer targetOrganizer) {
        boolean errorExists = false;
        if (sourceOrganizer == null && targetOrganizer == null) {
            return true;
        } else if (sourceOrganizer != null && targetOrganizer != null) {
            //realmCode
            if (!compareRealmCodes(sourceOrganizer.getRealmCodes(),targetOrganizer.getRealmCodes())) {
                errorExists = true;
            }
            //result Id
            if (!typeIDComparison(sourceOrganizer.getTypeId(),targetOrganizer.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceOrganizer.getTemplateIds(),targetOrganizer.getTemplateIds())) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceOrganizer.getIds(),targetOrganizer.getIds())) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceOrganizer.getCode(),targetOrganizer.getCode())) {
                errorExists = true;
            }
            //statusCode
            if (!compareCode(sourceOrganizer.getStatusCode(),targetOrganizer.getStatusCode())) {
                errorExists = true;
            }
            //effectiveTime
            if (!compareEffectiveTime(sourceOrganizer.getEffectiveTime(),targetOrganizer.getEffectiveTime())) {
                errorExists = true;
            }
            //subject - Subject
            if (!subjectComparison(sourceOrganizer.getSubject(),targetOrganizer.getSubject())) {
                errorExists = true;
            }
            //specimen - Specimen
            if (!specimenComparison(sourceOrganizer.getSpecimens(),targetOrganizer.getSpecimens())) {
                errorExists = true;
            }
            //performer - Performer2
            if (!performer2Comparison(sourceOrganizer.getPerformers(),targetOrganizer.getPerformers())) {
                errorExists = true;
            }
            //author - Author
            if (!authorsComparison(sourceOrganizer.getAuthors(),targetOrganizer.getAuthors())) {
                errorExists = true;
            }
            //informant - Informant12
            if (!informantsComparison(sourceOrganizer.getInformants(),targetOrganizer.getInformants())) {
                errorExists = true;
            }
            //participant - Participant2
            if (!participants2Comparison(sourceOrganizer.getParticipants(),targetOrganizer.getParticipants())) {
                errorExists = true;
            }
            //reference - Reference
            if (!referenceComparison(sourceOrganizer.getReferences(),targetOrganizer.getReferences())) {
                errorExists = true;
            }
            //precondition - Precondition
            if (!preconditionComparison(sourceOrganizer.getPreconditions(),targetOrganizer.getPreconditions())) {
                errorExists = true;
            }
            //component - Component4
            if (!component4Comparison(sourceOrganizer.getComponents(),targetOrganizer.getComponents())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceOrganizer.getNullFlavor(),targetOrganizer.getNullFlavor())) {
                errorExists = true;
            }
            //ClassCode
            if (!compareClassCode(sourceOrganizer.getClassCode(),targetOrganizer.getClassCode())) {
                errorExists = true;
            }
            //moodCode
            if (!compareMoodCode(sourceOrganizer.getMoodCode(),targetOrganizer.getMoodCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean procedureComparison(Procedure sourceProcedure, Procedure targetProcedure) {
        boolean errorExists = false;
        if (sourceProcedure == null && targetProcedure == null) {
            return true;
        } else if (sourceProcedure != null && targetProcedure != null) {
            //realmCode
            if (!compareRealmCodes(sourceProcedure.getRealmCodes(),targetProcedure.getRealmCodes())) {
                errorExists = true;
            }
            //result Id
            if (!typeIDComparison(sourceProcedure.getTypeId(),targetProcedure.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceProcedure.getTemplateIds(),targetProcedure.getTemplateIds())) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceProcedure.getIds(),targetProcedure.getIds())) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceProcedure.getCode(),targetProcedure.getCode())) {
                errorExists = true;
            }
            //text
            if (!compareText(sourceProcedure.getText(),targetProcedure.getText())) {
                errorExists = true;
            }
            //statusCode
            if (!compareCode(sourceProcedure.getStatusCode(),targetProcedure.getStatusCode())) {
                errorExists = true;
            }
            //effectiveTime
            if (!compareEffectiveTime(sourceProcedure.getEffectiveTime(),targetProcedure.getEffectiveTime())) {
                errorExists = true;
            }
            //priorityCode
            if (!compareCode(sourceProcedure.getPriorityCode(),targetProcedure.getPriorityCode())) {
                errorExists = true;
            }
            //langaugeCode
            if (!compareCode(sourceProcedure.getLanguageCode(),targetProcedure.getLanguageCode())) {
                errorExists = true;
            }
            //methodCode
            if (!compareCodes(sourceProcedure.getMethodCodes(),targetProcedure.getMethodCodes())) {
                errorExists = true;
            }
            //approachSiteCode
            if (!compareCodesCD(sourceProcedure.getApproachSiteCodes(),targetProcedure.getApproachSiteCodes())) {
                errorExists = true;
            }
            //targetSiteCode
            if (!compareCodesCD(sourceProcedure.getTargetSiteCodes(),targetProcedure.getTargetSiteCodes())) {
                errorExists = true;
            }
            //subject - Subject
            if (!subjectComparison(sourceProcedure.getSubject(),targetProcedure.getSubject())) {
                errorExists = true;
            }
            //specimen - Specimen
            if (!specimenComparison(sourceProcedure.getSpecimens(),targetProcedure.getSpecimens())) {
                errorExists = true;
            }
            //performer - Performer2
            if (!performer2Comparison(sourceProcedure.getPerformers(),targetProcedure.getPerformers())) {
                errorExists = true;
            }
            //author - Author
            if (!authorsComparison(sourceProcedure.getAuthors(),targetProcedure.getAuthors())) {
                errorExists = true;
            }
            //informant - Informant12
            if (!informantsComparison(sourceProcedure.getInformants(),targetProcedure.getInformants())) {
                errorExists = true;
            }
            //participant - Participant2
            if (!participants2Comparison(sourceProcedure.getParticipants(),targetProcedure.getParticipants())) {
                errorExists = true;
            }
            //entryRelationship - EntryRelationship
            if (!entryRelationshipComparison(sourceProcedure.getEntryRelationships(),targetProcedure.getEntryRelationships())) {
                errorExists = true;
            }
            //reference - Reference
            if (!referenceComparison(sourceProcedure.getReferences(),targetProcedure.getReferences())) {
                errorExists = true;
            }
            //precondition - Precondition
            if (!preconditionComparison(sourceProcedure.getPreconditions(),targetProcedure.getPreconditions())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceProcedure.getNullFlavor(),targetProcedure.getNullFlavor())) {
                errorExists = true;
            }
            //ClassCode
            if (!compareClassCode(sourceProcedure.getClassCode(),targetProcedure.getClassCode())) {
                errorExists = true;
            }
            //moodCode
            if (!compareMoodCode(sourceProcedure.getMoodCode(),targetProcedure.getMoodCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean regionOfInterestComparison(RegionOfInterest sourceRegionOfInterest, RegionOfInterest targetRegionOfInterest) {
        boolean errorExists = false;
        if (sourceRegionOfInterest == null && targetRegionOfInterest == null) {
            return true;
        } else if (sourceRegionOfInterest != null && targetRegionOfInterest != null) {
            //realmCode
            if (!compareRealmCodes(sourceRegionOfInterest.getRealmCodes(),targetRegionOfInterest.getRealmCodes())) {
                errorExists = true;
            }
            //result Id
            if (!typeIDComparison(sourceRegionOfInterest.getTypeId(),targetRegionOfInterest.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceRegionOfInterest.getTemplateIds(),targetRegionOfInterest.getTemplateIds())) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceRegionOfInterest.getIds(),targetRegionOfInterest.getIds())) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceRegionOfInterest.getCode(),targetRegionOfInterest.getCode())) {
                errorExists = true;
            }
            //value
            if (!compareValuesROI(sourceRegionOfInterest.getValues(),targetRegionOfInterest.getValues())) {
                errorExists = true;
            }
            //subject - Subject
            if (!subjectComparison(sourceRegionOfInterest.getSubject(),targetRegionOfInterest.getSubject())) {
                errorExists = true;
            }
            //specimen - Specimen
            if (!specimenComparison(sourceRegionOfInterest.getSpecimens(),targetRegionOfInterest.getSpecimens())) {
                errorExists = true;
            }
            //performer - Performer2
            if (!performer2Comparison(sourceRegionOfInterest.getPerformers(),targetRegionOfInterest.getPerformers())) {
                errorExists = true;
            }
            //author - Author
            if (!authorsComparison(sourceRegionOfInterest.getAuthors(),targetRegionOfInterest.getAuthors())) {
                errorExists = true;
            }
            //informant - Informant12
            if (!informantsComparison(sourceRegionOfInterest.getInformants(),targetRegionOfInterest.getInformants())) {
                errorExists = true;
            }
            //participant - Participant2
            if (!participants2Comparison(sourceRegionOfInterest.getParticipants(),targetRegionOfInterest.getParticipants())) {
                errorExists = true;
            }
            //entryRelationship - EntryRelationship
            if (!entryRelationshipComparison(sourceRegionOfInterest.getEntryRelationships(),targetRegionOfInterest.getEntryRelationships())) {
                errorExists = true;
            }
            //reference - Reference
            if (!referenceComparison(sourceRegionOfInterest.getReferences(),targetRegionOfInterest.getReferences())) {
                errorExists = true;
            }
            //precondition - Precondition
            if (!preconditionComparison(sourceRegionOfInterest.getPreconditions(),targetRegionOfInterest.getPreconditions())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceRegionOfInterest.getNullFlavor(),targetRegionOfInterest.getNullFlavor())) {
                errorExists = true;
            }
            //ClassCode
            if (!compareClassCode(sourceRegionOfInterest.getClassCode(),targetRegionOfInterest.getClassCode())) {
                errorExists = true;
            }
            //moodCode
            if (!compareMoodCode(sourceRegionOfInterest.getMoodCode(),targetRegionOfInterest.getMoodCode())) {
                errorExists = true;
            }
            if (!compareIDAttribute(sourceRegionOfInterest.getRegionOfInterestId(),targetRegionOfInterest.getRegionOfInterestId())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean substanceAdministrationComparison(SubstanceAdministration sourceSubstanceAdministration, SubstanceAdministration targetSubstanceAdminstration) {
        boolean errorExists = false;
        if (sourceSubstanceAdministration == null && targetSubstanceAdminstration == null) {
            return true;
        } else if (sourceSubstanceAdministration != null && targetSubstanceAdminstration != null) {
            //realmCode
            if (!compareRealmCodes(sourceSubstanceAdministration.getRealmCodes(),targetSubstanceAdminstration.getRealmCodes())) {
                errorExists = true;
            }
            //result Id
            if (!typeIDComparison(sourceSubstanceAdministration.getTypeId(),targetSubstanceAdminstration.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceSubstanceAdministration.getTemplateIds(),targetSubstanceAdminstration.getTemplateIds())) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceSubstanceAdministration.getIds(),targetSubstanceAdminstration.getIds())) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceSubstanceAdministration.getCode(),targetSubstanceAdminstration.getCode())) {
                errorExists = true;
            }
            //text
            if (!compareText(sourceSubstanceAdministration.getText(),targetSubstanceAdminstration.getText())) {
                errorExists = true;
            }
            //statusCode
            if (!compareCode(sourceSubstanceAdministration.getStatusCode(),targetSubstanceAdminstration.getStatusCode())) {
                errorExists = true;
            }
            //effectiveTime
            if (!compareEffectiveTime(sourceSubstanceAdministration.getEffectiveTimes(),targetSubstanceAdminstration.getEffectiveTimes())) {
                errorExists = true;
            }
            //priorityCode
            if (!compareCode(sourceSubstanceAdministration.getPriorityCode(),targetSubstanceAdminstration.getPriorityCode())) {
                errorExists = true;
            }
            //repeatNumber
            if (!compareRepeatNumber(sourceSubstanceAdministration.getRepeatNumber(),targetSubstanceAdminstration.getRepeatNumber())) {
                errorExists = true;
            }
            //routeCode
            if (!compareCode(sourceSubstanceAdministration.getRouteCode(),targetSubstanceAdminstration.getRouteCode())) {
                errorExists = true;
            }
            //approachSiteCode
            if (!compareCodesCD(sourceSubstanceAdministration.getApproachSiteCodes(),targetSubstanceAdminstration.getApproachSiteCodes())) {
                errorExists = true;
            }
            //doseQuantity
            if (!compareDose(sourceSubstanceAdministration.getDoseQuantity(),targetSubstanceAdminstration.getDoseQuantity())) {
                errorExists = true;
            }
            //rateQuantity
            if (!compareDose(sourceSubstanceAdministration.getRateQuantity(),targetSubstanceAdminstration.getRateQuantity())) {
                errorExists = true;
            }
            //maxDoseQuantity
            if (!compareMaxDose(sourceSubstanceAdministration.getMaxDoseQuantity(),targetSubstanceAdminstration.getMaxDoseQuantity())) {
                errorExists = true;
            }
            //administrationUnitCode
            if (!compareCode(sourceSubstanceAdministration.getAdministrationUnitCode(),targetSubstanceAdminstration.getAdministrationUnitCode())) {
                errorExists = true;
            }
            //subject - Subject
            if (!subjectComparison(sourceSubstanceAdministration.getSubject(),targetSubstanceAdminstration.getSubject())) {
                errorExists = true;
            }
            //specimen - Specimen
            if (!specimenComparison(sourceSubstanceAdministration.getSpecimens(),targetSubstanceAdminstration.getSpecimens())) {
                errorExists = true;
            }
            //consumable
            if (!consumableComparison(sourceSubstanceAdministration.getConsumable(),targetSubstanceAdminstration.getConsumable())) {
                errorExists = true;
            }
            //performer - Performer2
            if (!performer2Comparison(sourceSubstanceAdministration.getPerformers(),targetSubstanceAdminstration.getPerformers())) {
                errorExists = true;
            }
            //author - Author
            if (!authorsComparison(sourceSubstanceAdministration.getAuthors(),targetSubstanceAdminstration.getAuthors())) {
                errorExists = true;
            }
            //informant - Informant12
            if (!informantsComparison(sourceSubstanceAdministration.getInformants(),targetSubstanceAdminstration.getInformants())) {
                errorExists = true;
            }
            //participant - Participant2
            if (!participants2Comparison(sourceSubstanceAdministration.getParticipants(),targetSubstanceAdminstration.getParticipants())) {
                errorExists = true;
            }
            //entryRelationship - EntryRelationship
            if (!entryRelationshipComparison(sourceSubstanceAdministration.getEntryRelationships(),targetSubstanceAdminstration.getEntryRelationships())) {
                errorExists = true;
            }
            //reference - Reference
            if (!referenceComparison(sourceSubstanceAdministration.getReferences(),targetSubstanceAdminstration.getReferences())) {
                errorExists = true;
            }
            //precondition - Precondition
            if (!preconditionComparison(sourceSubstanceAdministration.getPreconditions(),targetSubstanceAdminstration.getPreconditions())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceSubstanceAdministration.getNullFlavor(),targetSubstanceAdminstration.getNullFlavor())) {
                errorExists = true;
            }
            //ClassCode
            if (!compareClassCode(sourceSubstanceAdministration.getClassCode(),targetSubstanceAdminstration.getClassCode())) {
                errorExists = true;
            }
            //moodCode
            if (!compareMoodCode(sourceSubstanceAdministration.getMoodCode(),targetSubstanceAdminstration.getMoodCode())) {
                errorExists = true;
            }
            //negationInd
            if (sourceSubstanceAdministration.getNegationInd() == targetSubstanceAdminstration.getNegationInd()) {
                errorExists = true;
//                comparisonReport.addMessage("Negation Ind error in " + errorMessage + " -> Negation Ind");
            }
            return !errorExists;
        }
        return false;
    }

    private boolean supplyComparison(Supply sourceSupply, Supply targetSupply) {
        boolean errorExists = false;
        if (sourceSupply == null && targetSupply == null) {
            return true;
        } else if (sourceSupply != null && targetSupply != null) {
            //realmCode
            if (!compareRealmCodes(sourceSupply.getRealmCodes(),targetSupply.getRealmCodes())) {
                errorExists = true;
            }
            //result Id
            if (!typeIDComparison(sourceSupply.getTypeId(),targetSupply.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceSupply.getTemplateIds(),targetSupply.getTemplateIds())) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceSupply.getIds(),targetSupply.getIds())) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceSupply.getCode(),targetSupply.getCode())) {
                errorExists = true;
            }
            //text
            if (!compareText(sourceSupply.getText(),targetSupply.getText())) {
                errorExists = true;
            }
            //statusCode
            if (!compareCode(sourceSupply.getStatusCode(),targetSupply.getStatusCode())) {
                errorExists = true;
            }
            //effectiveTime
            if (!compareEffectiveTime(sourceSupply.getEffectiveTimes(),targetSupply.getEffectiveTimes())) {
                errorExists = true;
            }
            //priorityCode
            if (!compareCodes(sourceSupply.getPriorityCodes(),targetSupply.getPriorityCodes())) {
                errorExists = true;
            }
            //repeatNumber
            if (!compareRepeatNumber(sourceSupply.getRepeatNumber(),targetSupply.getRepeatNumber())) {
                errorExists = true;
            }
            //independentInd
            if (!comparePreferenceInd(sourceSupply.getIndependentInd(),targetSupply.getIndependentInd())) {
                errorExists = true;
            }
            //quantity
            if (!compareQuantity(sourceSupply.getQuantity(),targetSupply.getQuantity())) {
                errorExists = true;
            }
            //expectedUseTime
            if (!compareExpectedUseTime(sourceSupply.getExpectedUseTime(),targetSupply.getExpectedUseTime())) {
                errorExists = true;
            }
            //subject - Subject
            if (!subjectComparison(sourceSupply.getSubject(),targetSupply.getSubject())) {
                errorExists = true;
            }
            //specimen - Specimen
            if (!specimenComparison(sourceSupply.getSpecimens(),targetSupply.getSpecimens())) {
                errorExists = true;
            }
            //product
            if (!productComparison(sourceSupply.getProduct(),targetSupply.getProduct())) {
                errorExists = true;
            }
            //author - Author
            if (!authorsComparison(sourceSupply.getAuthors(),targetSupply.getAuthors())) {
                errorExists = true;
            }
            //informant - Informant12
            if (!informantsComparison(sourceSupply.getInformants(),targetSupply.getInformants())) {
                errorExists = true;
            }
            //participant - Participant2
            if (!participants2Comparison(sourceSupply.getParticipants(),targetSupply.getParticipants())) {
                errorExists = true;
            }
            //entryRelationship - EntryRelationship
            if (!entryRelationshipComparison(sourceSupply.getEntryRelationships(),targetSupply.getEntryRelationships())) {
                errorExists = true;
            }
            //precondition - Precondition
            if (!preconditionComparison(sourceSupply.getPreconditions(),targetSupply.getPreconditions())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceSupply.getNullFlavor(),targetSupply.getNullFlavor())) {
                errorExists = true;
            }
            //ClassCode
            if (!compareClassCode(sourceSupply.getClassCode(),targetSupply.getClassCode())) {
                errorExists = true;
            }
            //moodCode
            if (!compareMoodCode(sourceSupply.getMoodCode(),targetSupply.getMoodCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean subjectPersonComparison(SubjectPerson sourceSubjectPerson, SubjectPerson targetSubjectPerson) {
        boolean errorExists = false;
        if (sourceSubjectPerson == null && targetSubjectPerson == null) {
            return true;
        } else if (sourceSubjectPerson != null && targetSubjectPerson != null) {
            //realmCode
            if (!compareRealmCodes(sourceSubjectPerson.getRealmCodes(),targetSubjectPerson.getRealmCodes())) {
                errorExists = true;
            }
            //result Id
            if (!typeIDComparison(sourceSubjectPerson.getTypeId(),targetSubjectPerson.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceSubjectPerson.getTemplateIds(),targetSubjectPerson.getTemplateIds())) {
                errorExists = true;
            }
            //name
            if (!compareNamesPN(sourceSubjectPerson.getNames(),targetSubjectPerson.getNames())) {
                errorExists = true;
            }
            //administrativeGenderCode
            if (!compareCode(sourceSubjectPerson.getAdministrativeGenderCode(),targetSubjectPerson.getAdministrativeGenderCode())) {
                errorExists = true;
            }
            //birthTime
            if (!compareTime(sourceSubjectPerson.getBirthTime(),targetSubjectPerson.getBirthTime())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceSubjectPerson.getNullFlavor(),targetSubjectPerson.getNullFlavor())) {
                errorExists = true;
            }
            //ClassCode
            if (!compareClassCode(sourceSubjectPerson.getClassCode(),targetSubjectPerson.getClassCode())) {
                errorExists = true;
            }
            //DeterminerCode
            if (!compareDeterminerCode(sourceSubjectPerson.getDeterminerCode(),targetSubjectPerson.getDeterminerCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean specimenComparison(EList<Specimen> sourceSpecimen, EList<Specimen> targetSpecimen) {
        boolean errorExists = false;
        for (int i=0;i<sourceSpecimen.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetSpecimen.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (!compareRealmCodes(sourceSpecimen.get(i).getRealmCodes(), targetSpecimen.get(j).getRealmCodes())) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourceSpecimen.get(i).getTypeId(), targetSpecimen.get(j).getTypeId())) {
                    specificError = true;
                }
                //compare TemplateIDs
                if (!compareTemplateID(sourceSpecimen.get(i).getTemplateIds(), targetSpecimen.get(j).getTemplateIds())) {
                    specificError = true;
                }
                //Section
                if (!specimenRoleComparison(sourceSpecimen.get(i).getSpecimenRole(),targetSpecimen.get(j).getSpecimenRole())) {
                    specificError = true;
                }
                //compare Type Code
                if (!compareTypeCode(sourceSpecimen.get(i).getTypeCode(),targetSpecimen.get(j).getTypeCode())) {
                    specificError = true;
                }
                //compare NullFlavor
                if (!compareNullFlavor(sourceSpecimen.get(i).getNullFlavor(), targetSpecimen.get(j).getNullFlavor())) {
                    specificError = true;
                }


                if (!specificError){
                    targetMatches++;
                }

            }
            if (targetMatches == 0) {
                errorExists = true;
//                comparisonReport.addMessage("Specimen Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
//                comparisonReport.addMessage("Specimen Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private boolean performer2Comparison(EList<Performer2> sourcePerformer, EList<Performer2> targetPerformer) {
        boolean errorExists = false;
        for (int i=0;i<sourcePerformer.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetPerformer.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (!compareRealmCodes(sourcePerformer.get(i).getRealmCodes(), targetPerformer.get(j).getRealmCodes())) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourcePerformer.get(i).getTypeId(), targetPerformer.get(j).getTypeId())) {
                    specificError = true;
                }
                //compare TemplateIDs
                if (!compareTemplateID(sourcePerformer.get(i).getTemplateIds(), targetPerformer.get(j).getTemplateIds())) {
                    specificError = true;
                }
                //Time
                if (!compareTime(sourcePerformer.get(i).getTime(),targetPerformer.get(j).getTime())) {
                    specificError = true;
                }
                //modeCode
                if (!compareCode(sourcePerformer.get(i).getModeCode(),targetPerformer.get(j).getModeCode())) {
                    specificError = true;
                }
                //assignedEntity - AssignedEntity
                if (!assignedEntityComparison(sourcePerformer.get(i).getAssignedEntity(),targetPerformer.get(j).getAssignedEntity())) {
                    specificError = true;
                }
                //compare NullFlavor
                if (!compareNullFlavor(sourcePerformer.get(i).getNullFlavor(), targetPerformer.get(j).getNullFlavor())) {
                    specificError = true;
                }
                //result Code
                if (!compareTypeCode(sourcePerformer.get(i).getTypeCode(),targetPerformer.get(j).getTypeCode())) {
                    specificError = true;
                }


                if (!specificError){
                    targetMatches++;
                }

            }
            if (targetMatches == 0) {
                errorExists = true;
//                comparisonReport.addMessage("Performer2 Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
//                comparisonReport.addMessage("Performer2 Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private boolean participants2Comparison(EList<Participant2> sourceParticipants, EList<Participant2> targetParticipants) {
        boolean errorExists = false;
        for (int i=0;i<sourceParticipants.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetParticipants.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (!compareRealmCodes(sourceParticipants.get(i).getRealmCodes(), targetParticipants.get(j).getRealmCodes())) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourceParticipants.get(i).getTypeId(), targetParticipants.get(j).getTypeId())) {
                    specificError = true;
                }
                //compare TemplateIDs
                if (!compareTemplateID(sourceParticipants.get(i).getTemplateIds(), targetParticipants.get(j).getTemplateIds())) {
                    specificError = true;
                }
                //Time
                if (!compareExpectedUseTime(sourceParticipants.get(i).getTime(),targetParticipants.get(j).getTime())) {
                    specificError = true;
                }
                //awarenessCode
                if (!compareCode(sourceParticipants.get(i).getAwarenessCode(),targetParticipants.get(j).getAwarenessCode())) {
                    specificError = true;
                }
                //participantRole - ParticipantRole
                if (!participantRoleComparison(sourceParticipants.get(i).getParticipantRole(),targetParticipants.get(j).getParticipantRole())) {
                    specificError = true;
                }
                //compare NullFlavor
                if (!compareNullFlavor(sourceParticipants.get(i).getNullFlavor(), targetParticipants.get(j).getNullFlavor())) {
                    specificError = true;
                }
                //result Code
                if (!compareTypeCode(sourceParticipants.get(i).getTypeCode(),targetParticipants.get(j).getTypeCode())) {
                    specificError = true;
                }
                //contextControlCode
                if (!compareContextControlCode(sourceParticipants.get(i).getContextControlCode(),targetParticipants.get(j).getContextControlCode())) {
                    specificError = true;
                }


                if (!specificError){
                    targetMatches++;
                }

            }
            if (targetMatches == 0) {
                errorExists = true;
//                comparisonReport.addMessage("Participants2 Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
//                comparisonReport.addMessage("Participants2 Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private boolean entryRelationshipComparison(EList<EntryRelationship> sourceEntryRelationships, EList<EntryRelationship> targetEntryRelationships) {
        boolean errorExists = false;
        for (int i=0;i<sourceEntryRelationships.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetEntryRelationships.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (!compareRealmCodes(sourceEntryRelationships.get(i).getRealmCodes(), targetEntryRelationships.get(j).getRealmCodes())) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourceEntryRelationships.get(i).getTypeId(), targetEntryRelationships.get(j).getTypeId())) {
                    specificError = true;
                }
                //compare TemplateIDs
                if (!compareTemplateID(sourceEntryRelationships.get(i).getTemplateIds(), targetEntryRelationships.get(j).getTemplateIds())) {
                    specificError = true;
                }
                //sequenceNumber
                if (!compareVersionNumber(sourceEntryRelationships.get(i).getSequenceNumber(),targetEntryRelationships.get(j).getSequenceNumber())) {
                    specificError = true;
                }
                //seperatableInd
                if (!comparePreferenceInd(sourceEntryRelationships.get(i).getSeperatableInd(),targetEntryRelationships.get(j).getSeperatableInd())) {
                    specificError = true;
                }
                //Choice - Act, Encounter,Observation,observationMedia,Organizer, Procedure,RegionOfInterest,SubstanceAdminstration,Supply
                if (!(sourceEntryRelationships.get(i).getAct() == null && targetEntryRelationships.get(j).getAct() == null
                        && sourceEntryRelationships.get(i).getEncounter() == null && targetEntryRelationships.get(j).getEncounter() == null
                        && sourceEntryRelationships.get(i).getObservation() == null && targetEntryRelationships.get(j).getObservation() == null
                        && sourceEntryRelationships.get(i).getObservationMedia() == null && targetEntryRelationships.get(j).getObservationMedia() == null
                        && sourceEntryRelationships.get(i).getOrganizer() == null && targetEntryRelationships.get(j).getOrganizer() == null
                        && sourceEntryRelationships.get(i).getProcedure() == null && targetEntryRelationships.get(j).getProcedure() == null
                        && sourceEntryRelationships.get(i).getRegionOfInterest() == null && targetEntryRelationships.get(j).getRegionOfInterest() == null
                        && sourceEntryRelationships.get(i).getSubstanceAdministration() == null && targetEntryRelationships.get(j).getSubstanceAdministration() == null
                        && sourceEntryRelationships.get(i).getSupply() == null && targetEntryRelationships.get(j).getSupply() == null)) {
                    if (!((actComparison(sourceEntryRelationships.get(i).getAct(), targetEntryRelationships.get(j).getAct()) && sourceEntryRelationships.get(i).getAct() != null)
                            || (encounterComparison(sourceEntryRelationships.get(i).getEncounter(), targetEntryRelationships.get(j).getEncounter()) && sourceEntryRelationships.get(i).getEncounter() != null)
                            || (observationComparison(sourceEntryRelationships.get(i).getObservation(), targetEntryRelationships.get(j).getObservation()) && sourceEntryRelationships.get(i).getObservation() != null)
                            || (observationMediaComparison(sourceEntryRelationships.get(i).getObservationMedia(), targetEntryRelationships.get(j).getObservationMedia()) && sourceEntryRelationships.get(i).getObservationMedia() != null)
                            || (organizerComparison(sourceEntryRelationships.get(i).getOrganizer(), targetEntryRelationships.get(j).getOrganizer()) && sourceEntryRelationships.get(i).getOrganizer() != null)
                            || (procedureComparison(sourceEntryRelationships.get(i).getProcedure(), targetEntryRelationships.get(j).getProcedure()) && sourceEntryRelationships.get(i).getProcedure() != null)
                            || (regionOfInterestComparison(sourceEntryRelationships.get(i).getRegionOfInterest(), targetEntryRelationships.get(j).getRegionOfInterest()) && sourceEntryRelationships.get(i).getRegionOfInterest() != null)
                            || (substanceAdministrationComparison(sourceEntryRelationships.get(i).getSubstanceAdministration(), targetEntryRelationships.get(j).getSubstanceAdministration()) && sourceEntryRelationships.get(i).getSubstanceAdministration() != null)
                            || (supplyComparison(sourceEntryRelationships.get(i).getSupply(), targetEntryRelationships.get(j).getSupply()) && sourceEntryRelationships.get(i).getSupply() != null))) {
                        specificError = true;
                    }
                }

                //compare NullFlavor
                if (!compareNullFlavor(sourceEntryRelationships.get(i).getNullFlavor(), targetEntryRelationships.get(j).getNullFlavor())) {
                    specificError = true;
                }
                //result Code
                if (!compareTypeCode(sourceEntryRelationships.get(i).getTypeCode(),targetEntryRelationships.get(j).getTypeCode())) {
                    specificError = true;
                }
                //Inversion Ind
                if (sourceEntryRelationships.get(i).getInversionInd() != targetEntryRelationships.get(j).getInversionInd()) {
                    errorExists = true;
//                    comparisonReport.addMessage("Inversion Ind error in " + errorMessage + " -> Inversion Ind");

                }
                //contextConductionInd
                if (sourceEntryRelationships.get(i).getContextConductionInd() != targetEntryRelationships.get(j).getContextConductionInd()) {
                    specificError = true;
//                    comparisonReport.addMessage("Context Conduction Ind error in " + errorMessage + " -> Context Conduction Ind");

                }


                if (!specificError){
                    targetMatches++;
                }

            }
            if (targetMatches == 0) {
                errorExists = true;
//                comparisonReport.addMessage("Entry Relationship Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
//                comparisonReport.addMessage("Entry Relationship Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private boolean referenceComparison(EList<Reference> sourceReference, EList<Reference> targetReference) {
        boolean errorExists = false;
        for (int i=0;i<sourceReference.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetReference.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (!compareRealmCodes(sourceReference.get(i).getRealmCodes(), targetReference.get(j).getRealmCodes())) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourceReference.get(i).getTypeId(), targetReference.get(j).getTypeId())) {
                    specificError = true;
                }
                //compare TemplateIDs
                if (!compareTemplateID(sourceReference.get(i).getTemplateIds(), targetReference.get(j).getTemplateIds())) {
                    specificError = true;
                }
                //seperatableInd
                if (!comparePreferenceInd(sourceReference.get(i).getSeperatableInd(),targetReference.get(j).getSeperatableInd())) {
                    specificError = true;
                }
                //Choice - ExternalAct, ExternalObservation, ExternalProcedure, ExternalDocument
                if (!(sourceReference.get(i).getExternalAct() == null && targetReference.get(j).getExternalAct() == null
                        && sourceReference.get(i).getExternalObservation() == null && targetReference.get(j).getExternalObservation() == null
                        && sourceReference.get(i).getExternalProcedure() == null && targetReference.get(j).getExternalProcedure() == null
                        && sourceReference.get(i).getExternalDocument() == null && targetReference.get(j).getExternalDocument() == null)) {
                    if (!((externalActComparison(sourceReference.get(i).getExternalAct(), targetReference.get(j).getExternalAct()) && sourceReference.get(i).getExternalAct() != null)
                            || (externalObservationComparison(sourceReference.get(i).getExternalObservation(), targetReference.get(j).getExternalObservation()) && sourceReference.get(i).getExternalObservation() != null)
                            || (externalProcedureComparison(sourceReference.get(i).getExternalProcedure(), targetReference.get(j).getExternalProcedure()) && sourceReference.get(i).getExternalProcedure() != null)
                            || (externalDocumentComparison(sourceReference.get(i).getExternalDocument(), targetReference.get(j).getExternalDocument()) && sourceReference.get(i).getExternalDocument() != null))) {
                        specificError = true;
                    }
                }
                //compare NullFlavor
                if (!compareNullFlavor(sourceReference.get(i).getNullFlavor(), targetReference.get(j).getNullFlavor())) {
                    specificError = true;
                }
                //result Code
                if (!compareTypeCode(sourceReference.get(i).getTypeCode(),targetReference.get(j).getTypeCode())) {
                    specificError = true;
                }

                if (!specificError){
                    targetMatches++;
                }

            }
            if (targetMatches == 0) {
                errorExists = true;
//                comparisonReport.addMessage("Reference Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
//                comparisonReport.addMessage("Reference Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private boolean preconditionComparison(EList<Precondition> sourcePrecondition, EList<Precondition> targetPrecondition) {
        boolean errorExists = false;
        for (int i=0;i<sourcePrecondition.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetPrecondition.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (!compareRealmCodes(sourcePrecondition.get(i).getRealmCodes(), targetPrecondition.get(j).getRealmCodes())) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourcePrecondition.get(i).getTypeId(), targetPrecondition.get(j).getTypeId())) {
                    specificError = true;
                }
                //compare criterion - Criterion
                if (!criterionComparison(sourcePrecondition.get(i).getCriterion(), targetPrecondition.get(j).getCriterion())) {
                    specificError = true;
                }
                //compare NullFlavor
                if (!compareNullFlavor(sourcePrecondition.get(i).getNullFlavor(), targetPrecondition.get(j).getNullFlavor())) {
                    specificError = true;
                }
                //result Code
                if (!compareTypeCode(sourcePrecondition.get(i).getTypeCode(),targetPrecondition.get(j).getTypeCode())) {
                    specificError = true;
                }

                if (!specificError){
                    targetMatches++;
                }

            }
            if (targetMatches == 0) {
                errorExists = true;
//                comparisonReport.addMessage("Precondition Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
//                comparisonReport.addMessage("Precondition Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private boolean referenceRangeComparison(EList<ReferenceRange> sourceReferenceRange, EList<ReferenceRange> targetReferenceRange) {
        boolean errorExists = false;
        for (int i=0;i<sourceReferenceRange.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetReferenceRange.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (!compareRealmCodes(sourceReferenceRange.get(i).getRealmCodes(), targetReferenceRange.get(j).getRealmCodes())) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourceReferenceRange.get(i).getTypeId(), targetReferenceRange.get(j).getTypeId())) {
                    specificError = true;
                }
                //compare TemplateIDs
                if (!compareTemplateID(sourceReferenceRange.get(i).getTemplateIds(), targetReferenceRange.get(j).getTemplateIds())) {
                    specificError = true;
                }
                //observationRange - ObservationRange
                if (!observationRangeComparison(sourceReferenceRange.get(i).getObservationRange(),targetReferenceRange.get(j).getObservationRange())) {
                    specificError = true;
                }
                //compare NullFlavor
                if (!compareNullFlavor(sourceReferenceRange.get(i).getNullFlavor(), targetReferenceRange.get(j).getNullFlavor())) {
                    specificError = true;
                }
                //result Code
                if (!compareTypeCode(sourceReferenceRange.get(i).getTypeCode(),targetReferenceRange.get(j).getTypeCode())) {
                    specificError = true;
                }

                if (!specificError){
                    targetMatches++;
                }

            }
            if (targetMatches == 0) {
                errorExists = true;
//                comparisonReport.addMessage("Reference Range Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
//                comparisonReport.addMessage("Reference Range Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private boolean component4Comparison(EList<Component4> sourceComponent4, EList<Component4> targetComponent4) {
        boolean errorExists = false;
        for (int i=0;i<sourceComponent4.size();i++) {
            int targetMatches = 0;
            for (int j = 0; j < targetComponent4.size(); j++) {
                boolean specificError = false;
                //compare RealmCodes
                if (!compareRealmCodes(sourceComponent4.get(i).getRealmCodes(), targetComponent4.get(j).getRealmCodes())) {
                    specificError = true;
                }
                //compare TypeID
                if (!typeIDComparison(sourceComponent4.get(i).getTypeId(), targetComponent4.get(j).getTypeId())) {
                    specificError = true;
                }
                //compare TemplateIDs
                if (!compareTemplateID(sourceComponent4.get(i).getTemplateIds(), targetComponent4.get(j).getTemplateIds())) {
                    specificError = true;
                }
                //sequenceNumber
                if (!compareVersionNumber(sourceComponent4.get(i).getSequenceNumber(),targetComponent4.get(j).getSequenceNumber())) {
                    specificError = true;
                }
                //seperatableInd
                if (!comparePreferenceInd(sourceComponent4.get(i).getSeperatableInd(),targetComponent4.get(j).getSeperatableInd())) {
                    specificError = true;
                }
                //choice - Act, Encounter, Observation, ObservationMedia, Organizer, Procedure, RegionOfInterest, SubstanceAdministraion,Supply
                if (!(sourceComponent4.get(i).getAct() == null && targetComponent4.get(j).getAct() == null
                        && sourceComponent4.get(i).getEncounter() == null && targetComponent4.get(j).getEncounter() == null
                        && sourceComponent4.get(i).getObservation() == null && targetComponent4.get(j).getObservation() == null
                        && sourceComponent4.get(i).getObservationMedia() == null && targetComponent4.get(j).getObservationMedia() == null
                        && sourceComponent4.get(i).getOrganizer() == null && targetComponent4.get(j).getOrganizer() == null
                        && sourceComponent4.get(i).getProcedure() == null && targetComponent4.get(j).getProcedure() == null
                        && sourceComponent4.get(i).getRegionOfInterest() == null && targetComponent4.get(j).getRegionOfInterest() == null
                        && sourceComponent4.get(i).getSubstanceAdministration() == null && targetComponent4.get(j).getSubstanceAdministration() == null
                        && sourceComponent4.get(i).getSupply() == null && targetComponent4.get(j).getSupply() == null)) {
                    if (!((actComparison(sourceComponent4.get(i).getAct(), targetComponent4.get(j).getAct()) && sourceComponent4.get(i).getAct() != null)
                            || (observationComparison(sourceComponent4.get(i).getObservation(), targetComponent4.get(j).getObservation()) && sourceComponent4.get(i).getObservation() != null)
                            || (observationMediaComparison(sourceComponent4.get(i).getObservationMedia(), targetComponent4.get(j).getObservationMedia()) && sourceComponent4.get(i).getObservationMedia() != null)
                            || (organizerComparison(sourceComponent4.get(i).getOrganizer(), targetComponent4.get(j).getOrganizer()) && sourceComponent4.get(i).getOrganizer() != null)
                            || (procedureComparison(sourceComponent4.get(i).getProcedure(), targetComponent4.get(j).getProcedure()) && sourceComponent4.get(i).getProcedure() != null)
                            || (regionOfInterestComparison(sourceComponent4.get(i).getRegionOfInterest(), targetComponent4.get(j).getRegionOfInterest()) && sourceComponent4.get(i).getRegionOfInterest() != null)
                            || (substanceAdministrationComparison(sourceComponent4.get(i).getSubstanceAdministration(), targetComponent4.get(j).getSubstanceAdministration()) && sourceComponent4.get(i).getSubstanceAdministration() != null)
                            || (supplyComparison(sourceComponent4.get(i).getSupply(), targetComponent4.get(j).getSupply()) && sourceComponent4.get(i).getSupply() != null))) {
                        specificError = true;
                    }
                }
                //compare NullFlavor
                if (!compareNullFlavor(sourceComponent4.get(i).getNullFlavor(), targetComponent4.get(j).getNullFlavor())) {
                    specificError = true;
                }
                //result Code
                if (!compareTypeCode(sourceComponent4.get(i).getTypeCode(),targetComponent4.get(j).getTypeCode())) {
                    specificError = true;
                }
                //contextConductionInd
                if (sourceComponent4.get(i).getContextConductionInd() != sourceComponent4.get(j).getContextConductionInd()) {
                    specificError = true;
//                    comparisonReport.addMessage("Context Conduction Ind error in " + errorMessage + " -> Context Conduction Ind");
                }

                if (!specificError){
                    targetMatches++;
                }

            }
            if (targetMatches == 0) {
                errorExists = true;
//                comparisonReport.addMessage("Component4 Comparison Error source " + i + " in " + errorMessage + "\n");
            }  else if (targetMatches>1) {
//                comparisonReport.addMessage("Component4 Comparison Warning source " + i + " in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private boolean compareValuesROI(EList<RegionOfInterestValue> sourceValue, EList<RegionOfInterestValue> targetValue) {
        boolean errorExists = false;
        for (int i=0; i<sourceValue.size(); i++) {
            int targetMatches = 0;
            for (int j=0; j<targetValue.size(); j++) {
                if (sourceValue.get(i).getValue().equals(targetValue.get(j).getValue())
                        && sourceValue.get(i).getNullFlavor().getLiteral().equals(targetValue.get(j).getNullFlavor().getLiteral())
                        && (sourceValue.get(i).isUnsorted() == targetValue.get(j).isUnsorted()))
                {
                    targetMatches++;
                }
            }
            if (targetMatches==0) {
                errorExists = true;
//                comparisonReport.addMessage("Region of Interest Value Error source " + i + " in " + errorMessage + "\n");
            } else if (targetMatches>1) {
//                comparisonReport.addMessage("Region of Interest Value Warning source " + i + " has " + targetMatches + " in target in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private boolean consumableComparison(Consumable sourceConsumable, Consumable targetConsumable) {
        boolean errorExists = false;
        if (sourceConsumable == null && targetConsumable == null) {
            return true;
        } else if (sourceConsumable != null && targetConsumable != null) {
            //realmCode
            if (!compareRealmCodes(sourceConsumable.getRealmCodes(),targetConsumable.getRealmCodes())) {
                errorExists = true;
            }
            //result Id
            if (!typeIDComparison(sourceConsumable.getTypeId(),targetConsumable.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceConsumable.getTemplateIds(),targetConsumable.getTemplateIds())) {
                errorExists = true;
            }
            //manufacturedProduct
            if (!manufacturedProductComparison(sourceConsumable.getManufacturedProduct(),targetConsumable.getManufacturedProduct())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceConsumable.getNullFlavor(),targetConsumable.getNullFlavor())) {
                errorExists = true;
            }
            //Type Code
            if (!compareTypeCode(sourceConsumable.getTypeCode(),targetConsumable.getTypeCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean productComparison(Product sourceProduct, Product targetProduct) {
        boolean errorExists = false;
        if (sourceProduct == null && targetProduct == null) {
            return true;
        } else if (sourceProduct != null && targetProduct != null) {
            //realmCode
            if (!compareRealmCodes(sourceProduct.getRealmCodes(),targetProduct.getRealmCodes())) {
                errorExists = true;
            }
            //result Id
            if (!typeIDComparison(sourceProduct.getTypeId(),targetProduct.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceProduct.getTemplateIds(),targetProduct.getTemplateIds())) {
                errorExists = true;
            }
            //manufacturedProduct
            if (!manufacturedProductComparison(sourceProduct.getManufacturedProduct(),targetProduct.getManufacturedProduct())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceProduct.getNullFlavor(),targetProduct.getNullFlavor())) {
                errorExists = true;
            }
            //Type Code
            if (!compareTypeCode(sourceProduct.getTypeCode(),targetProduct.getTypeCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean specimenRoleComparison(SpecimenRole sourceSpecimenRole, SpecimenRole targetSpecimenRole) {
        boolean errorExists = false;
        if (sourceSpecimenRole == null && targetSpecimenRole == null) {
            return true;
        } else if (sourceSpecimenRole != null && targetSpecimenRole != null) {
            //realmCode
            if (!compareRealmCodes(sourceSpecimenRole.getRealmCodes(),targetSpecimenRole.getRealmCodes())) {
                errorExists = true;
            }
            //result Id
            if (!typeIDComparison(sourceSpecimenRole.getTypeId(),targetSpecimenRole.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceSpecimenRole.getTemplateIds(),targetSpecimenRole.getTemplateIds())) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceSpecimenRole.getIds(),targetSpecimenRole.getIds())) {
                errorExists = true;
            }
            //specimenPlayingEntity - PlayingEntity
            if (!comparePlayingEntity(sourceSpecimenRole.getSpecimenPlayingEntity(),targetSpecimenRole.getSpecimenPlayingEntity())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceSpecimenRole.getNullFlavor(),targetSpecimenRole.getNullFlavor())) {
                errorExists = true;
            }
            //Class Code
            if (!compareClassCode(sourceSpecimenRole.getClassCode(),targetSpecimenRole.getClassCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean participantRoleComparison(ParticipantRole sourceParticipantRole, ParticipantRole targetParticipantRole) {
        boolean errorExists = false;
        if (sourceParticipantRole == null && targetParticipantRole == null) {
            return true;
        } else if (sourceParticipantRole != null && targetParticipantRole != null) {
            //realmCode
            if (!compareRealmCodes(sourceParticipantRole.getRealmCodes(),targetParticipantRole.getRealmCodes())) {
                errorExists = true;
            }
            //result Id
            if (!typeIDComparison(sourceParticipantRole.getTypeId(),targetParticipantRole.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceParticipantRole.getTemplateIds(),targetParticipantRole.getTemplateIds())) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceParticipantRole.getIds(),targetParticipantRole.getIds())) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceParticipantRole.getCode(),targetParticipantRole.getCode())) {
                errorExists = true;
            }
            //addr
            if (!compareAddr(sourceParticipantRole.getAddrs(),targetParticipantRole.getAddrs())) {
                errorExists = true;
            }
            //telecom
            if (!compareTelcom(sourceParticipantRole.getTelecoms(),targetParticipantRole.getTelecoms())) {
                errorExists = true;
            }
            //Choice - playingDevice (Device), playingEntity(PlayingEntity)
            if (!(sourceParticipantRole.getPlayingDevice() == null && targetParticipantRole.getPlayingDevice() == null
                    && sourceParticipantRole.getPlayingEntity() == null && targetParticipantRole.getPlayingEntity() == null)) {
                if (!((deviceComparison(sourceParticipantRole.getPlayingDevice(), targetParticipantRole.getPlayingDevice()) && sourceParticipantRole.getPlayingDevice() != null)
                        || (comparePlayingEntity(sourceParticipantRole.getPlayingEntity(), targetParticipantRole.getPlayingEntity()) && sourceParticipantRole.getPlayingEntity() != null))) {
                    errorExists = true;
                }
            }
            //scopingEntity - Entity
            if (!entityComparison(sourceParticipantRole.getScopingEntity(),targetParticipantRole.getScopingEntity())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceParticipantRole.getNullFlavor(),targetParticipantRole.getNullFlavor())) {
                errorExists = true;
            }
            //Class Code
            if (!compareClassCode(sourceParticipantRole.getClassCode(),targetParticipantRole.getClassCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean externalActComparison(ExternalAct sourceExternalAct, ExternalAct targetExternalAct) {
        boolean errorExists = false;
        if (sourceExternalAct == null && targetExternalAct == null) {
            return true;
        } else if (sourceExternalAct != null && targetExternalAct != null) {
            //realmCode
            if (!compareRealmCodes(sourceExternalAct.getRealmCodes(),targetExternalAct.getRealmCodes())) {
                errorExists = true;
            }
            //result Id
            if (!typeIDComparison(sourceExternalAct.getTypeId(),targetExternalAct.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceExternalAct.getTemplateIds(),targetExternalAct.getTemplateIds())) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceExternalAct.getIds(),targetExternalAct.getIds())) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceExternalAct.getCode(),targetExternalAct.getCode())) {
                errorExists = true;
            }
            //text
            if (!compareText(sourceExternalAct.getText(),targetExternalAct.getText())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceExternalAct.getNullFlavor(),targetExternalAct.getNullFlavor())) {
                errorExists = true;
            }
            //Class Code
            if (!compareClassCode(sourceExternalAct.getClassCode(),targetExternalAct.getClassCode())) {
                errorExists = true;
            }
            //Mood Code
            if (!compareMoodCode(sourceExternalAct.getMoodCode(),targetExternalAct.getMoodCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean externalObservationComparison(ExternalObservation sourceExternalObservation, ExternalObservation targetExternalObservation) {
        boolean errorExists = false;
        if (sourceExternalObservation == null && targetExternalObservation == null) {
            return true;
        } else if (sourceExternalObservation != null && targetExternalObservation != null) {
            //realmCode
            if (!compareRealmCodes(sourceExternalObservation.getRealmCodes(),targetExternalObservation.getRealmCodes())) {
                errorExists = true;
            }
            //result Id
            if (!typeIDComparison(sourceExternalObservation.getTypeId(),targetExternalObservation.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceExternalObservation.getTemplateIds(),targetExternalObservation.getTemplateIds())) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceExternalObservation.getIds(),targetExternalObservation.getIds())) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceExternalObservation.getCode(),targetExternalObservation.getCode())) {
                errorExists = true;
            }
            //text
            if (!compareText(sourceExternalObservation.getText(),targetExternalObservation.getText())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceExternalObservation.getNullFlavor(),targetExternalObservation.getNullFlavor())) {
                errorExists = true;
            }
            //Class Code
            if (!compareClassCode(sourceExternalObservation.getClassCode(),targetExternalObservation.getClassCode())) {
                errorExists = true;
            }
            //Mood Code
            if (!compareMoodCode(sourceExternalObservation.getMoodCode(),targetExternalObservation.getMoodCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean externalProcedureComparison(ExternalProcedure sourceExternalProcedure, ExternalProcedure targetExternalProcedure) {
        boolean errorExists = false;
        if (sourceExternalProcedure == null && targetExternalProcedure == null) {
            return true;
        } else if (sourceExternalProcedure != null && targetExternalProcedure != null) {
            //realmCode
            if (!compareRealmCodes(sourceExternalProcedure.getRealmCodes(),targetExternalProcedure.getRealmCodes())) {
                errorExists = true;
            }
            //result Id
            if (!typeIDComparison(sourceExternalProcedure.getTypeId(),targetExternalProcedure.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceExternalProcedure.getTemplateIds(),targetExternalProcedure.getTemplateIds())) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceExternalProcedure.getIds(),targetExternalProcedure.getIds())) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceExternalProcedure.getCode(),targetExternalProcedure.getCode())) {
                errorExists = true;
            }
            //text
            if (!compareText(sourceExternalProcedure.getText(),targetExternalProcedure.getText())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceExternalProcedure.getNullFlavor(),targetExternalProcedure.getNullFlavor())) {
                errorExists = true;
            }
            //Class Code
            if (!compareClassCode(sourceExternalProcedure.getClassCode(),targetExternalProcedure.getClassCode())) {
                errorExists = true;
            }
            //Mood Code
            if (!compareMoodCode(sourceExternalProcedure.getMoodCode(),targetExternalProcedure.getMoodCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean externalDocumentComparison(ExternalDocument sourceExternalDocument, ExternalDocument targetExternalDocument) {
        boolean errorExists = false;
        if (sourceExternalDocument == null && targetExternalDocument == null) {
            return true;
        } else if (sourceExternalDocument != null && targetExternalDocument != null) {
            //realmCode
            if (!compareRealmCodes(sourceExternalDocument.getRealmCodes(),targetExternalDocument.getRealmCodes())) {
                errorExists = true;
            }
            //result Id
            if (!typeIDComparison(sourceExternalDocument.getTypeId(),targetExternalDocument.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceExternalDocument.getTemplateIds(),targetExternalDocument.getTemplateIds())) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceExternalDocument.getIds(),targetExternalDocument.getIds())) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceExternalDocument.getCode(),targetExternalDocument.getCode())) {
                errorExists = true;
            }
            //text
            if (!compareText(sourceExternalDocument.getText(),targetExternalDocument.getText())) {
                errorExists = true;
            }
            //setID
            if (!compareSetID(sourceExternalDocument.getSetId(),targetExternalDocument.getSetId())) {
                errorExists = true;
            }
            //versionNumber
            if (!compareVersionNumber(sourceExternalDocument.getVersionNumber(),targetExternalDocument.getVersionNumber())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceExternalDocument.getNullFlavor(),targetExternalDocument.getNullFlavor())) {
                errorExists = true;
            }
            //Class Code
            if (!compareClassCode(sourceExternalDocument.getClassCode(),targetExternalDocument.getClassCode())) {
                errorExists = true;
            }
            //Mood Code
            if (!compareMoodCode(sourceExternalDocument.getMoodCode(),targetExternalDocument.getMoodCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean criterionComparison(Criterion sourceCriterion, Criterion targetCriterion) {
        boolean errorExists = false;
        if (sourceCriterion == null && targetCriterion == null) {
            return true;
        } else if (sourceCriterion!= null && targetCriterion != null) {
            //realmCode
            if (!compareRealmCodes(sourceCriterion.getRealmCodes(),targetCriterion.getRealmCodes())) {
                errorExists = true;
            }
            //result Id
            if (!typeIDComparison(sourceCriterion.getTypeId(),targetCriterion.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceCriterion.getTemplateIds(),targetCriterion.getTemplateIds())) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceCriterion.getCode(),targetCriterion.getCode())) {
                errorExists = true;
            }
            //text
            if (!compareText(sourceCriterion.getText(),targetCriterion.getText())) {
                errorExists = true;
            }
            //value
            if (!compareValue(sourceCriterion.getValue(),targetCriterion.getValue())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceCriterion.getNullFlavor(),targetCriterion.getNullFlavor())) {
                errorExists = true;
            }
            //Class Code
            if (!compareClassCode(sourceCriterion.getClassCode(),targetCriterion.getClassCode())) {
                errorExists = true;
            }
            //Mood Code
            if (!compareMoodCode(sourceCriterion.getMoodCode(),targetCriterion.getMoodCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean observationRangeComparison(ObservationRange sourceObservationRange, ObservationRange targetObservationRange) {
        boolean errorExists = false;
        if (sourceObservationRange == null && targetObservationRange == null) {
            return true;
        } else if (sourceObservationRange != null && targetObservationRange != null) {
            //realmCode
            if (!compareRealmCodes(sourceObservationRange.getRealmCodes(),targetObservationRange.getRealmCodes())) {
                errorExists = true;
            }
            //result Id
            if (!typeIDComparison(sourceObservationRange.getTypeId(),targetObservationRange.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceObservationRange.getTemplateIds(),targetObservationRange.getTemplateIds())) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceObservationRange.getCode(),targetObservationRange.getCode())) {
                errorExists = true;
            }
            //text
            if (!compareText(sourceObservationRange.getText(),targetObservationRange.getText())) {
                errorExists = true;
            }
            //value
            if (!compareValue(sourceObservationRange.getValue(),targetObservationRange.getValue())) {
                errorExists = true;
            }
            //interpretaionCode
            if (!compareCode(sourceObservationRange.getInterpretationCode(),targetObservationRange.getInterpretationCode())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceObservationRange.getNullFlavor(),targetObservationRange.getNullFlavor())) {
                errorExists = true;
            }
            //Class Code
            if (!compareClassCode(sourceObservationRange.getClassCode(),targetObservationRange.getClassCode())) {
                errorExists = true;
            }
            //Mood Code
            if (!compareMoodCode(sourceObservationRange.getMoodCode(),targetObservationRange.getMoodCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean manufacturedProductComparison(ManufacturedProduct sourceManufacturedProduct, ManufacturedProduct targetManufacturedProduct) {
        boolean errorExists = false;
        if (sourceManufacturedProduct == null && targetManufacturedProduct == null) {
            return true;
        } else if (sourceManufacturedProduct != null && targetManufacturedProduct != null) {
            //realmCode
            if (!compareRealmCodes(sourceManufacturedProduct.getRealmCodes(), targetManufacturedProduct.getRealmCodes())) {
                errorExists = true;
            }
            //result Id
            if (!typeIDComparison(sourceManufacturedProduct.getTypeId(), targetManufacturedProduct.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceManufacturedProduct.getTemplateIds(), targetManufacturedProduct.getTemplateIds())) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceManufacturedProduct.getIds(), targetManufacturedProduct.getIds())) {
                errorExists = true;
            }
            //Choice - manufacturedLabeled(LabeledDrug), manufacturedMaterial(Material)
            if (!(sourceManufacturedProduct.getManufacturedLabeledDrug() == null && targetManufacturedProduct.getManufacturedLabeledDrug() == null
                    && sourceManufacturedProduct.getManufacturedMaterial() == null && targetManufacturedProduct.getManufacturedMaterial() == null)) {
                if (!((labeledDrugComparison(sourceManufacturedProduct.getManufacturedLabeledDrug(), targetManufacturedProduct.getManufacturedLabeledDrug()) && sourceManufacturedProduct.getManufacturedLabeledDrug() != null)
                        || (materialComparison(sourceManufacturedProduct.getManufacturedMaterial(), targetManufacturedProduct.getManufacturedMaterial()) && sourceManufacturedProduct.getManufacturedMaterial() != null))) {
                    errorExists = true;
                }
            }
            //manufacturerOrganization - Organization
            if (!organizationComparison(sourceManufacturedProduct.getManufacturerOrganization(),targetManufacturedProduct.getManufacturerOrganization())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceManufacturedProduct.getNullFlavor(),targetManufacturedProduct.getNullFlavor())) {
                errorExists = true;
            }
            //Class Code
            if (!compareClassCode(sourceManufacturedProduct.getClassCode(),targetManufacturedProduct.getClassCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean comparePlayingEntity(PlayingEntity sourcePlayingEntity, PlayingEntity targetPlayingIdentity) {
        boolean errorExists = false;
        if (sourcePlayingEntity == null && targetPlayingIdentity == null) {
            return true;
        } else if (sourcePlayingEntity != null && targetPlayingIdentity != null) {
            //realmCode
            if (!compareRealmCodes(sourcePlayingEntity.getRealmCodes(),targetPlayingIdentity.getRealmCodes())) {
                errorExists = true;
            }
            //result Id
            if (!typeIDComparison(sourcePlayingEntity.getTypeId(),targetPlayingIdentity.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourcePlayingEntity.getTemplateIds(),targetPlayingIdentity.getTemplateIds())) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourcePlayingEntity.getCode(),targetPlayingIdentity.getCode())) {
                errorExists = true;
            }
            //quantity
            if (!compareQuantities(sourcePlayingEntity.getQuantities(),targetPlayingIdentity.getQuantities())) {
                errorExists = true;
            }
            //name
            if (!compareNamesPN(sourcePlayingEntity.getNames(),targetPlayingIdentity.getNames())) {
                errorExists = true;
            }
            //desc
            if (!compareText(sourcePlayingEntity.getDesc(),targetPlayingIdentity.getDesc())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourcePlayingEntity.getNullFlavor(),targetPlayingIdentity.getNullFlavor())) {
                errorExists = true;
            }
            //Class Code
            if (!compareClassCode(sourcePlayingEntity.getClassCode(),targetPlayingIdentity.getClassCode())) {
                errorExists = true;
            }
            //determinerCode
            if (!compareDeterminerCode(sourcePlayingEntity.getDeterminerCode(),targetPlayingIdentity.getDeterminerCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean deviceComparison(Device sourceDevice, Device targetDevice) {

        boolean errorExists = false;
        if (sourceDevice == null && targetDevice == null) {
            return true;
        } else if (sourceDevice != null && targetDevice != null) {
            //realmCode
            if (!compareRealmCodes(sourceDevice.getRealmCodes(),targetDevice.getRealmCodes())) {
                errorExists = true;
            }
            //result Id
            if (!typeIDComparison(sourceDevice.getTypeId(),targetDevice.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceDevice.getTemplateIds(),targetDevice.getTemplateIds())) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceDevice.getCode(),targetDevice.getCode())) {
                errorExists = true;
            }
            //ManufacturedModelName
            if (!compareSCName(sourceDevice.getManufacturerModelName(),targetDevice.getManufacturerModelName())) {
                errorExists = true;
            }
            //software Name
            if (!compareSCName(sourceDevice.getSoftwareName(),targetDevice.getSoftwareName())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceDevice.getNullFlavor(),targetDevice.getNullFlavor())) {
                errorExists = true;
            }
            //Class Code
            if (!compareClassCode(sourceDevice.getClassCode(),targetDevice.getClassCode())) {
                errorExists = true;
            }
            //determinerCode
            if (!compareDeterminerCode(sourceDevice.getDeterminerCode(),targetDevice.getDeterminerCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean entityComparison(Entity sourceEntity, Entity targetEntity) {
        boolean errorExists = false;
        if (sourceEntity == null && targetEntity == null) {
            return true;
        } else if (sourceEntity != null && targetEntity != null) {
            //realmCode
            if (!compareRealmCodes(sourceEntity.getRealmCodes(),targetEntity.getRealmCodes())) {
                errorExists = true;
            }
            //result Id
            if (!typeIDComparison(sourceEntity.getTypeId(),targetEntity.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceEntity.getTemplateIds(),targetEntity.getTemplateIds())) {
                errorExists = true;
            }
            //id
            if (!compareIDs(sourceEntity.getIds(),targetEntity.getIds())) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceEntity.getCode(),targetEntity.getCode())) {
                errorExists = true;
            }
            //desc
            if (!compareText(sourceEntity.getDesc(),targetEntity.getDesc())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceEntity.getNullFlavor(),targetEntity.getNullFlavor())) {
                errorExists = true;
            }
            //Class Code
            if (!compareClassCode(sourceEntity.getClassCode(),targetEntity.getClassCode())) {
                errorExists = true;
            }
            //t
            if (!compareDeterminerCode(sourceEntity.getDeterminerCode(),targetEntity.getDeterminerCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean labeledDrugComparison(LabeledDrug sourceLabeledDrug, LabeledDrug targetLabeledDrug) {
        boolean errorExists = false;
        if (sourceLabeledDrug == null && targetLabeledDrug == null) {
            return true;
        } else if (sourceLabeledDrug != null && targetLabeledDrug != null) {
            //realmCode
            if (!compareRealmCodes(sourceLabeledDrug.getRealmCodes(),targetLabeledDrug.getRealmCodes())) {
                errorExists = true;
            }
            //result Id
            if (!typeIDComparison(sourceLabeledDrug.getTypeId(),targetLabeledDrug.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceLabeledDrug.getTemplateIds(),targetLabeledDrug.getTemplateIds())) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceLabeledDrug.getCode(),targetLabeledDrug.getCode())) {
                errorExists = true;
            }
            //name
            if (!compareNameEN(sourceLabeledDrug.getName(),targetLabeledDrug.getName())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceLabeledDrug.getNullFlavor(),targetLabeledDrug.getNullFlavor())) {
                errorExists = true;
            }
            //Class Code
            if (!compareClassCode(sourceLabeledDrug.getClassCode(),targetLabeledDrug.getClassCode())) {
                errorExists = true;
            }
            //determinerCode
            if (!compareDeterminerCode(sourceLabeledDrug.getDeterminerCode(),targetLabeledDrug.getDeterminerCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }

    private boolean materialComparison(Material sourceMaterial, Material targetMaterial) {
        boolean errorExists = false;
        if (sourceMaterial == null && targetMaterial == null) {
            return true;
        } else if (sourceMaterial != null && targetMaterial != null) {
            //realmCode
            if (!compareRealmCodes(sourceMaterial.getRealmCodes(),targetMaterial.getRealmCodes())) {
                errorExists = true;
            }
            //result Id
            if (!typeIDComparison(sourceMaterial.getTypeId(),targetMaterial.getTypeId())) {
                errorExists = true;
            }
            //templateID
            if (!compareTemplateID(sourceMaterial.getTemplateIds(),targetMaterial.getTemplateIds())) {
                errorExists = true;
            }
            //code
            if (!compareCode(sourceMaterial.getCode(),targetMaterial.getCode())) {
                errorExists = true;
            }
            //name
            if (!compareNameEN(sourceMaterial.getName(),targetMaterial.getName())) {
                errorExists = true;
            }
            //lotNumberTest
            if (!compareTitle(sourceMaterial.getLotNumberText(),targetMaterial.getLotNumberText())) {
                errorExists = true;
            }
            //nullFlavor
            if (!compareNullFlavor(sourceMaterial.getNullFlavor(),targetMaterial.getNullFlavor())) {
                errorExists = true;
            }
            //Class Code
            if (!compareClassCode(sourceMaterial.getClassCode(),targetMaterial.getClassCode())) {
                errorExists = true;
            }
            //determinerCode
            if (!compareDeterminerCode(sourceMaterial.getDeterminerCode(),targetMaterial.getDeterminerCode())) {
                errorExists = true;
            }
            return !errorExists;
        }
        return false;
    }









    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //non complex result comparison Methods
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private boolean compareRealmCodes(EList<CS> source, EList<CS> target) {
        boolean errorExists = false;
        for (int i=0; i<source.size(); i++) {
            int targetMatches = 0;
            for (int j=0; j<target.size(); j++) {
                boolean sourceTargetMismatch = false;
                if (source.get(i).getCode() != null && target.get(j).getCode() != null) {
                    if (!source.get(i).getCode().equals(target.get(j).getCode())) {
                        sourceTargetMismatch = true;
//                        comparisonReport.addMessage("Code error source " + i + " in " + errorMessage + "\n");
                        comparisonReport.addMismatch(new Mismatch(" Realm Codes: Code",source.get(i).getCode(),target.get(j).getCode()));

                    }
                }
                if (source.get(i).getNullFlavor().getLiteral() != null && target.get(j).getNullFlavor().getLiteral() != null) {
                    if (!source.get(i).getNullFlavor().getLiteral().equals(target.get(j).getNullFlavor().getLiteral())) {
                        sourceTargetMismatch = true;
//                        comparisonReport.addMessage("Null Flavor error source " + i + " in " + errorMessage + "\n");
                        comparisonReport.addMismatch(new Mismatch(" Realm Codes: Null Flavor Literal",source.get(i).getNullFlavor().getLiteral(),target.get(j).getNullFlavor().getLiteral()));

                    }
                }
                if (source.get(i).getCodeSystemName() != null && target.get(j).getCodeSystemName() != null) {
                    if (!source.get(i).getCodeSystemName().equals(target.get(j).getCodeSystemName())) {
                        sourceTargetMismatch = true;
//                        comparisonReport.addMessage("Code System Name error source " + i + " in " + errorMessage + "\n");
                        comparisonReport.addMismatch(new Mismatch(" Realm Codes: Code System Name",source.get(i).getCodeSystemName(),target.get(j).getCodeSystemName()));

                    }
                }
                if (source.get(i).getCodeSystem() != null && target.get(j).getCodeSystem() != null) {
                    if (!source.get(i).getCodeSystem().equals(target.get(j).getCodeSystem())) {
                        sourceTargetMismatch = true;
//                        comparisonReport.addMessage("Code System error source " + i + " in " + errorMessage + "\n");
                        comparisonReport.addMismatch(new Mismatch(" Realm Codes: Code System",source.get(i).getCodeSystem(),target.get(j).getCodeSystem()));

                    }
                }
                if (source.get(i).getCodeSystemVersion() != null && target.get(j).getCodeSystemVersion() != null) {
                    if (!source.get(i).getCodeSystemVersion().equals(target.get(j).getCodeSystemVersion())) {
                        sourceTargetMismatch = true;
//                        comparisonReport.addMessage("Code System Version error source " + i + " in " + errorMessage + "\n");
                        comparisonReport.addMismatch(new Mismatch(" Realm Codes: Code System Version",source.get(i).getCodeSystemVersion(),target.get(j).getCodeSystemVersion()));

                    }
                }
                if (source.get(i).getDisplayName() != null && target.get(j).getDisplayName() != null) {
                    if (!source.get(i).getDisplayName().equals(target.get(j).getDisplayName())) {
                        sourceTargetMismatch = true;
//                        comparisonReport.addMessage("Display Name error source " + i + " in " + errorMessage + "\n");
                        comparisonReport.addMismatch(new Mismatch(" Realm Codes: Display Name",source.get(i).getDisplayName(),target.get(j).getDisplayName()));

                    }
                }

                if (!sourceTargetMismatch){
                    targetMatches++;
                }
            }
            if (targetMatches==0) {
                errorExists = true;
//                comparisonReport.addMessage("Realm Codes Error source " + i + " in " + errorMessage + "\n");
            } else if (targetMatches>1) {
//                comparisonReport.addMessage("Real Codes Warning source " + i + " has " + targetMatches + " in target in " + errorMessage + "\n");
                comparisonReport.addWarning(new Warning(" Realm Codes: ",source.get(i).toString(),""));

            }
        }
        return !errorExists;
    }

    private boolean compareTemplateID(EList<II> source, EList<II> target) {
        boolean errorExists = false;
        for (int i = 0; i < source.size(); i++) {
            int targetMatches = 0;
            for (int j = 0; j < target.size(); j++) {
                boolean sourceTargetMismatch = false;
                if (source.get(i).getRoot() != null && target.get(j).getRoot() != null) {
                    if (!source.get(i).getRoot().equals(target.get(j).getRoot())) {
//                        comparisonReport.addMessage("Get Root error in " + errorMessage + "\n");
                        sourceTargetMismatch = true;
                    }
                }
                if (source.get(i).getAssigningAuthorityName() != null && target.get(j).getAssigningAuthorityName() != null) {
                    if (!source.get(i).getAssigningAuthorityName().equals(target.get(j).getAssigningAuthorityName())) {
//                        comparisonReport.addMessage("Assigning Authority Name error in " + errorMessage + "\n");
                        sourceTargetMismatch = true;
                    }
                }
                if (source.get(i).getExtension() != null && target.get(j).getExtension() != null) {
                    if (!source.get(i).getExtension().equals(target.get(j).getExtension())) {
//                        comparisonReport.addMessage("Extension error in " + errorMessage + "\n");
                        sourceTargetMismatch = true;
                    }
                }
                if (source.get(i).getNullFlavor().getLiteral() != null && target.get(j).getNullFlavor().getLiteral() != null) {
                    if (!source.get(i).getNullFlavor().getLiteral().equals(target.get(j).getNullFlavor().getLiteral())) {
//                        comparisonReport.addMessage("Null Flavor ror in " + errorMessage + "\n");
                        sourceTargetMismatch = true;
                    }
                }
                if (!sourceTargetMismatch) {
                    targetMatches++;
                }
            }
            if (targetMatches==0) {
                errorExists = true;
//                comparisonReport.addMessage("Template IDs Error source " + i + " in " + errorMessage + "\n");
            } else if (targetMatches>1) {
//                comparisonReport.addMessage("Template IDs Warning source " + i + " has " + targetMatches + " in target in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private boolean compareIDs(EList<II> source, EList<II> target) {
        boolean errorExists = false;
        for (int i = 0; i < source.size(); i++) {
            int targetMatches = 0;
            for (int j = 0; j < target.size(); j++) {
                boolean sourceTargetMismatch = false;
                if (source.get(i) != null && target.get(j) != null) {
                    if (source.get(i).getRoot() != null && target.get(j).getRoot() != null) {
                        if (!source.get(i).getRoot().equals(target.get(j).getRoot())) {
//                            comparisonReport.addMessage("Get Root error in " + errorMessage + "\n");
                            sourceTargetMismatch = true;
                        }
                    }
                    if (source.get(i).getAssigningAuthorityName() != null && target.get(j).getAssigningAuthorityName() != null) {
                        if (!source.get(i).getAssigningAuthorityName().equals(target.get(j).getAssigningAuthorityName())) {
//                            comparisonReport.addMessage("Assigning Authority Name error in " + errorMessage + "\n");
                            sourceTargetMismatch = true;
                        }
                    }
                    if (source.get(i).getExtension() != null && target.get(j).getExtension() != null) {
                        if (!source.get(i).getExtension().equals(target.get(j).getExtension())) {
//                            comparisonReport.addMessage("Extension error in " + errorMessage + "\n");
                            sourceTargetMismatch = true;
                        }
                    }
                    if (source.get(i).getNullFlavor().getLiteral() != null && target.get(j).getNullFlavor().getLiteral() != null) {
                        if (!source.get(i).getNullFlavor().getLiteral().equals(target.get(j).getNullFlavor().getLiteral())) {
//                            comparisonReport.addMessage("Null Flavor ror in " + errorMessage + "\n");
                            sourceTargetMismatch = true;
                        }
                    }
                    if (!sourceTargetMismatch) {
                        targetMatches++;
                    }
                } else {
                    targetMatches = 1;
                }
            }
            if (targetMatches==0) {
                errorExists = true;
//                comparisonReport.addMessage("IDs Error source " + i + " in " + errorMessage + "\n");
            } else if (targetMatches>1) {
//                comparisonReport.addMessage("IDs Warning source " + i + " has " + targetMatches + " in target in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private boolean compareAddr(EList<AD> source, EList<AD> target) {
        boolean errorExists = false;
        for (int i = 0; i < source.size(); i++) {
            int targetMatches = 0;
            for (int j = 0; j < target.size(); j++) {
                boolean sourceTargetMismatch = false;
                if (source.get(i).getText() != null && target.get(j).getText() != null) {
                    if (!source.get(i).getText().equals(target.get(j).getText())) {
//                        comparisonReport.addMessage("Text error in " + errorMessage + "\n");
                        sourceTargetMismatch = true;
                    }
                }
                if (source.get(i).getNullFlavor().getLiteral() != null && target.get(j).getNullFlavor().getLiteral() != null) {
                    if (!source.get(i).getNullFlavor().getLiteral().equals(target.get(j).getNullFlavor().getLiteral())) {
//                        comparisonReport.addMessage("Null Flavor ror in " + errorMessage + "\n");
                        sourceTargetMismatch = true;
                    }
                }
                if (!sourceTargetMismatch) {
                    targetMatches++;
                }
            }
            if (targetMatches==0) {
                errorExists = true;
//                comparisonReport.addMessage("Addr Error source " + i + " in " + errorMessage + "\n");
            } else if (targetMatches>1) {
//                comparisonReport.addMessage("Addr Warning source " + i + " has " + targetMatches + " in target in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private boolean compareTelcom(EList<TEL> source, EList<TEL> target) {
        boolean errorExists = false;
        for (int i = 0; i < source.size(); i++) {
            int targetMatches = 0;
            for (int j = 0; j < target.size(); j++) {
                boolean sourceTargetMismatch = false;
                if (source.get(i).getValue() != null && target.get(j).getValue() != null) {
                    if (!source.get(i).getValue().equals(target.get(j).getValue())) {
//                        comparisonReport.addMessage("Value error in " + errorMessage + "\n");
                        sourceTargetMismatch = true;
                    }
                }
                if (source.get(i).getNullFlavor().getLiteral() != null && target.get(j).getNullFlavor().getLiteral() != null) {
                    if (!source.get(i).getNullFlavor().getLiteral().equals(target.get(j).getNullFlavor().getLiteral())) {
//                        comparisonReport.addMessage("Null Flavor ror in " + errorMessage + "\n");
                        sourceTargetMismatch = true;
                    }
                }
                if (!sourceTargetMismatch) {
                    targetMatches++;
                }
            }
            if (targetMatches==0) {
                errorExists = true;
//                comparisonReport.addMessage("Telecom Error source " + i + " in " + errorMessage + "\n");
            } else if (targetMatches>1) {
//                comparisonReport.addMessage("Telecom Warning source " + i + " has " + targetMatches + " in target in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private boolean compareNamesPN(EList<PN> source, EList<PN> target) {
        boolean errorExists = false;
        for (int i = 0; i < source.size(); i++) {
            int targetMatches = 0;
            for (int j = 0; j < target.size(); j++) {
                boolean sourceTargetMismatch = false;
                if (source.get(i).getText() != null && target.get(j).getText() != null) {
                    if (!source.get(i).getText().equals(target.get(j).getText())) {
//                        comparisonReport.addMessage("Text error in " + errorMessage + "\n");
                        sourceTargetMismatch = true;
                    }
                }
                if (source.get(i).getNullFlavor().getLiteral() != null && target.get(j).getNullFlavor().getLiteral() != null) {
                    if (!source.get(i).getNullFlavor().getLiteral().equals(target.get(j).getNullFlavor().getLiteral())) {
//                        comparisonReport.addMessage("Null Flavor ror in " + errorMessage + "\n");
                        sourceTargetMismatch = true;
                    }
                }
                if (!sourceTargetMismatch) {
                    targetMatches++;
                }
            }
            if (targetMatches == 0) {
                errorExists = true;
//                comparisonReport.addMessage("Names Error source " + i + " in " + errorMessage + "\n");
            } else if (targetMatches > 1) {
//                comparisonReport.addMessage("Names Warning source " + i + " has " + targetMatches + " in target in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private boolean compareNamesEN(EList<EN> source, EList<EN> target) {
        boolean errorExists = false;
        for (int i = 0; i < source.size(); i++) {
            int targetMatches = 0;
            for (int j = 0; j < target.size(); j++) {
                boolean sourceTargetMismatch = false;
                if (source.get(i).getText() != null && target.get(j).getText() != null) {
                    if (!source.get(i).getText().equals(target.get(j).getText())) {
//                        comparisonReport.addMessage("Text error in " + errorMessage + "\n");
                        sourceTargetMismatch = true;
                    }
                }
                if (source.get(i).getNullFlavor().getLiteral() != null && target.get(j).getNullFlavor().getLiteral() != null) {
                    if (!source.get(i).getNullFlavor().getLiteral().equals(target.get(j).getNullFlavor().getLiteral())) {
//                        comparisonReport.addMessage("Null Flavor ror in " + errorMessage + "\n");
                        sourceTargetMismatch = true;
                    }
                }
                if (!sourceTargetMismatch) {
                    targetMatches++;
                }
            }
            if (targetMatches == 0) {
                errorExists = true;
//                comparisonReport.addMessage("Names Error source " + i + " in " + errorMessage + "\n");
            } else if (targetMatches > 1) {
//                comparisonReport.addMessage("Names Warning source " + i + " has " + targetMatches + " in target in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private boolean compareNamesON(EList<ON> source, EList<ON> target) {

        boolean errorExists = false;
        for (int i = 0; i < source.size(); i++) {
            int targetMatches = 0;
            for (int j = 0; j < target.size(); j++) {
                boolean sourceTargetMismatch = false;
                if (source.get(i).getText() != null && target.get(j).getText() != null) {
                    if (!source.get(i).getText().equals(target.get(j).getText())) {
//                        comparisonReport.addMessage("Text error in " + errorMessage + "\n");
                        sourceTargetMismatch = true;
                    }
                }
                if (source.get(i).getNullFlavor().getLiteral() != null && target.get(j).getNullFlavor().getLiteral() != null) {
                    if (!source.get(i).getNullFlavor().getLiteral().equals(target.get(j).getNullFlavor().getLiteral())) {
//                        comparisonReport.addMessage("Null Flavor ror in " + errorMessage + "\n");
                        sourceTargetMismatch = true;
                    }
                }
                if (!sourceTargetMismatch) {
                    targetMatches++;
                }
            }
            if (targetMatches == 0) {
                errorExists = true;
//                comparisonReport.addMessage("Names Error source " + i + " in " + errorMessage + "\n");
            } else if (targetMatches > 1) {
//                comparisonReport.addMessage("Names Warning source " + i + " has " + targetMatches + " in target in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private boolean compareEffectiveTime(EList<SXCM_TS> source, EList<SXCM_TS> target) {
        boolean errorExists = false;
        for (int i = 0; i < source.size(); i++) {
            int targetMatches = 0;
            for (int j = 0; j < target.size(); j++) {
                boolean sourceTargetMismatch = false;
                if (source.get(i).getValue() != null && target.get(j).getValue() != null) {
                    if (!source.get(i).getValue().equals(target.get(j).getValue())) {
//                        comparisonReport.addMessage("Value error in " + errorMessage + "\n");
                        sourceTargetMismatch = true;
                    }
                }
                if (source.get(i).toString() != null && target.get(j).toString() != null) {
                    if (!source.get(i).toString().equals(target.get(j).toString())) {
//                        comparisonReport.addMessage("To String error in " + errorMessage + "\n");
                        sourceTargetMismatch = true;
                    }
                }
                if (source.get(i).getNullFlavor().getLiteral() != null && target.get(j).getNullFlavor().getLiteral() != null) {
                    if (!source.get(i).getNullFlavor().getLiteral().equals(target.get(j).getNullFlavor().getLiteral())) {
//                        comparisonReport.addMessage("Null Flavor ror in " + errorMessage + "\n");
                        sourceTargetMismatch = true;
                    }
                }
                if (!sourceTargetMismatch) {
                    targetMatches++;
                }
            }
            if (targetMatches == 0) {
                errorExists = true;
//                comparisonReport.addMessage("Effective Time error source " + i + " in " + errorMessage + "\n");
            } else if (targetMatches > 1) {
//                comparisonReport.addMessage("Effective Time Warning source " + i + " has " + targetMatches + " in target in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private boolean compareID(II source, II target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getRoot() != null && target.getRoot() != null) {
                if (!source.getRoot().equals(target.getRoot())) {
//                    comparisonReport.addMessage("Get Root error in " + errorMessage + "\n");
                    matched = false;
                }
            }
            if (source.getAssigningAuthorityName() != null && target.getAssigningAuthorityName() != null) {
                if (!source.getAssigningAuthorityName().equals(target.getAssigningAuthorityName())) {
//                    comparisonReport.addMessage("Assigning Authority Name error in " + errorMessage + "\n");
                    matched = false;
                }
            }
            if (source.getExtension() != null && target.getExtension() != null) {
                if (!source.getExtension().equals(target.getExtension())) {
//                    comparisonReport.addMessage("Extension error in " + errorMessage + "\n");
                    matched = false;
                }
            }
            if (source.getNullFlavor().getLiteral() != null && target.getNullFlavor().getLiteral() != null) {
                if (!source.getNullFlavor().getLiteral().equals(target.getNullFlavor().getLiteral())) {
//                    comparisonReport.addMessage("Null Flavor ror in " + errorMessage + "\n");
                    matched = false;
                }
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("IDs Comparison error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareNullFlavor(NullFlavor source, NullFlavor target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
//                    comparisonReport.addMessage("Literal error in + " + errorMessage + "\n");
                    matched = false;
                }
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Null Flavor error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareClassCode(RoleClass source, RoleClass target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage(" Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getName())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
        } else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Class Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareClassCode(EntityClassManufacturedMaterial source, EntityClassManufacturedMaterial target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage(" Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getName())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Class Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareClassCode(EntityClassRoot source, EntityClassRoot target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getName())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage(" Value error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Class Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareClassCode(RoleClassManufacturedProduct source, RoleClassManufacturedProduct target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage(" Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getName())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Class Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareClassCode(ActClassDocument source, ActClassDocument target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage(" Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getName())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Class Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareClassCode(RoleClassRoot source, RoleClassRoot target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage(" Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getName())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Class Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareClassCode(x_ActClassDocumentEntryAct source, x_ActClassDocumentEntryAct target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage(" Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getName())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Class Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareClassCode(RoleClassSpecimen source, RoleClassSpecimen target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
                    this.comparisonReport.addMismatch(new Mismatch("Literal", source.getLiteral(), target.getLiteral()));
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getName())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Class Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareClassCode(ActClassSupply source, ActClassSupply target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage(" Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getName())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Class Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareClassCode(x_ActClassDocumentEntryOrganizer source, x_ActClassDocumentEntryOrganizer target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage(" Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getName())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Class Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareClassCode(ActClassObservation source, ActClassObservation target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage(" Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getName())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Class Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareClassCode(RoleClassServiceDeliveryLocation source, RoleClassServiceDeliveryLocation target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage(" Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getName())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Class Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareClassCode(EntityClassPlace source, EntityClassPlace target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage(" Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getName())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Class Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareClassCode(x_DocumentSubject source, x_DocumentSubject target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage(" Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getName())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Class Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareClassCode(x_InformationRecipientRole source, x_InformationRecipientRole target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage(" Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getName())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Class Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareClassCode(EntityClassOrganization source, EntityClassOrganization target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage(" Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getName())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Class Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareClassCode(ActClassRoot source, ActClassRoot target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage(" Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getName())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Class Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }


    private boolean compareClassCode(RoleClassAssociative source, RoleClassAssociative target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage(" Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getName())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Class Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }


    private boolean compareClassCode(ActClinicalDocument source, ActClinicalDocument target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage(" Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getName())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Class Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareClassCode(RoleClassAssignedEntity source, RoleClassAssignedEntity target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage(" Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getName())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Class Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareClassCode(EntityClassDevice source, EntityClassDevice target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage(" Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getName())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Class Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareClassCode(EntityClass source, EntityClass target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage(" Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getName())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Class Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }
    private boolean compareClassCode(ActClass source, ActClass target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage(" Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getName())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Class Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }
    private boolean compareClassCode(RoleClassMutualRelationship source, RoleClassMutualRelationship target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage(" Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getName())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Class Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareCode(CE source, CE target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getCode() != null && target.getCode() != null) {
                if (!source.getCode().equals(target.getCode())) {
                    matched = false;
//                    comparisonReport.addMessage("Code error in " + errorMessage + "\n");
                }
            }
            if (source.getNullFlavor().getLiteral() != null && target.getNullFlavor().getLiteral() != null) {
                if (!source.getNullFlavor().getLiteral().equals(target.getNullFlavor().getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Null Flavor error in " + errorMessage + "\n");
                }
            }
            if (source.getCodeSystem() != null && target.getCodeSystem() != null) {
                if (!source.getCodeSystem().equals(target.getCodeSystem())) {
                    matched = false;
//                    comparisonReport.addMessage("Code System error in " + errorMessage + "\n");
                }
            }
            if (source.getCodeSystemName() != null && target.getCodeSystemName() != null) {
                if (!source.getCodeSystemName().equals(target.getCodeSystemName())) {
                    matched = false;
//                    comparisonReport.addMessage("Code System Name error in " + errorMessage + "\n");
                }
            }
            if (source.getCodeSystemVersion() != null && target.getCodeSystemVersion() != null) {
                if (!source.getCodeSystemVersion().equals(target.getCodeSystemVersion())) {
                    matched = false;
//                    comparisonReport.addMessage("Code System Version error in " + errorMessage + "\n");
                }
            }
            if (source.getDisplayName() != null && target.getDisplayName() != null) {
                if (!source.getDisplayName().equals(target.getDisplayName())) {
                    matched = false;
//                    comparisonReport.addMessage("Display Name error in " + errorMessage + "\n");
                }
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Code Comparison error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareCode(CD source, CD target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getCode() != null && target.getCode() != null) {
                if (!source.getCode().equals(target.getCode())) {
                    matched = false;
//                    comparisonReport.addMessage("Code error in " + errorMessage + "\n");
                }
            }
            if (source.getNullFlavor().getLiteral() != null && target.getNullFlavor().getLiteral() != null) {
                if (!source.getNullFlavor().getLiteral().equals(target.getNullFlavor().getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Null Flavor error in " + errorMessage + "\n");
                }
            }
            if (source.getCodeSystem() != null && target.getCodeSystem() != null) {
                if (!source.getCodeSystem().equals(target.getCodeSystem())) {
                    matched = false;
//                    comparisonReport.addMessage("Code System error in " + errorMessage + "\n");
                }
            }
            if (source.getCodeSystemName() != null && target.getCodeSystemName() != null) {
                if (!source.getCodeSystemName().equals(target.getCodeSystemName())) {
                    matched = false;
//                    comparisonReport.addMessage("Code System Name error in " + errorMessage + "\n");
                }
            }
            if (source.getCodeSystemVersion() != null && target.getCodeSystemVersion() != null) {
                if (!source.getCodeSystemVersion().equals(target.getCodeSystemVersion())) {
                    matched = false;
//                    comparisonReport.addMessage("Code System Version error in " + errorMessage + "\n");
                }
            }
            if (source.getDisplayName() != null && target.getDisplayName() != null) {
                if (!source.getDisplayName().equals(target.getDisplayName())) {
                    matched = false;
//                    comparisonReport.addMessage("Display Name error in " + errorMessage + "\n");
                }
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Code Comparison error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareConfidentialityCode(CE source, CE target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getCode() != null && target.getCode() != null) {
                if (!source.getCode().equals(target.getCode())) {
                    matched = false;
//                    comparisonReport.addMessage("Code error in " + errorMessage + "\n");
                }
            }
            if (source.getNullFlavor().getLiteral() != null && target.getNullFlavor().getLiteral() != null) {
                if (!source.getNullFlavor().getLiteral().equals(target.getNullFlavor().getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Null Flavor error in " + errorMessage + "\n");
                }
            }
            if (source.getCodeSystem() != null && target.getCodeSystem() != null) {
                if (!source.getCodeSystem().equals(target.getCodeSystem())) {
                    matched = false;
//                    comparisonReport.addMessage("Code System error in " + errorMessage + "\n");
                }
            }
            if (source.getCodeSystemName() != null && target.getCodeSystemName() != null) {
                if (!source.getCodeSystemName().equals(target.getCodeSystemName())) {
                    matched = false;
//                    comparisonReport.addMessage("Code System Name error in " + errorMessage + "\n");
                }
            }
            if (source.getCodeSystemVersion() != null && target.getCodeSystemVersion() != null) {
                if (!source.getCodeSystemVersion().equals(target.getCodeSystemVersion())) {
                    matched = false;
//                    comparisonReport.addMessage("Code System Version error in " + errorMessage + "\n");
                }
            }
            if (source.getDisplayName() != null && target.getDisplayName() != null) {
                if (!source.getDisplayName().equals(target.getDisplayName())) {
                    matched = false;
//                    comparisonReport.addMessage("Display Name error in " + errorMessage + "\n");
                }
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Confidentiality Code Comparison error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareLanguageCode(CE source, CE target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getCode() != null && target.getCode() != null) {
                if (!source.getCode().equals(target.getCode())) {
                    matched = false;
//                    comparisonReport.addMessage("Code error in " + errorMessage + "\n");
                }
            }
            if (source.getNullFlavor().getLiteral() != null && target.getNullFlavor().getLiteral() != null) {
                if (!source.getNullFlavor().getLiteral().equals(target.getNullFlavor().getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Null Flavor error in " + errorMessage + "\n");
                }
            }
            if (source.getCodeSystem() != null && target.getCodeSystem() != null) {
                if (!source.getCodeSystem().equals(target.getCodeSystem())) {
                    matched = false;
//                    comparisonReport.addMessage("Code System error in " + errorMessage + "\n");
                }
            }
            if (source.getCodeSystemName() != null && target.getCodeSystemName() != null) {
                if (!source.getCodeSystemName().equals(target.getCodeSystemName())) {
                    matched = false;
//                    comparisonReport.addMessage("Code System Name error in " + errorMessage + "\n");
                }
            }
            if (source.getCodeSystemVersion() != null && target.getCodeSystemVersion() != null) {
                if (!source.getCodeSystemVersion().equals(target.getCodeSystemVersion())) {
                    matched = false;
//                    comparisonReport.addMessage("Code System Version error in " + errorMessage + "\n");
                }
            }
            if (source.getDisplayName() != null && target.getDisplayName() != null) {
                if (!source.getDisplayName().equals(target.getDisplayName())) {
                    matched = false;
//                    comparisonReport.addMessage("Display Name error in " + errorMessage + "\n");
                }
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Language Code Comparison error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareTitle(ST source, ST target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getText() != null && target.getText() != null) {
                if (!source.getText().equals(target.getText())) {
                    matched = false;
//                    comparisonReport.addMessage("Text error in " + errorMessage + "\n");
                    comparisonReport.addMismatch(new Mismatch(" Title: Text",source.getText(),target.getText()));

                }
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Title Comparison error in " + errorMessage + "\n");
            comparisonReport.addMismatch(new Mismatch("Title",source.getText(),target.getText()));

            return false;
        }
        return matched;
    }

    private boolean compareEffectiveTime(TS source, TS target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
            if (source.getNullFlavor().getLiteral() != null && target.getNullFlavor().getLiteral() != null) {
                if (!source.getNullFlavor().getLiteral().equals(target.getNullFlavor().getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Literal in " + errorMessage + "\n");
                }
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Effective Time error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareTypeCode(ParticipationType source, ParticipationType target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getName())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Type Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareTypeCode(ParticipationTargetSubject source, ParticipationTargetSubject target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getName())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Type Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareTypeCode(ParticipationPhysicalPerformer source, ParticipationPhysicalPerformer target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getName())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Type Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareTypeCode(x_ActRelationshipExternalReference source, x_ActRelationshipExternalReference target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getName())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Type Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareTypeCode(x_ActRelationshipEntryRelationship source, x_ActRelationshipEntryRelationship target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getName())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Type Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareTypeCode(ParticipationTargetLocation source, ParticipationTargetLocation target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getName())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Type Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareTypeCode(x_ActRelationshipEntry source, x_ActRelationshipEntry target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getName())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Type Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareTypeCode(ActRelationshipFulfills source, ActRelationshipFulfills target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getName())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Type Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareTypeCode(x_ServiceEventPerformer source, x_ServiceEventPerformer target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getName())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Type Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareTypeCode(x_ActRelationshipDocument source, x_ActRelationshipDocument target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getName())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Type Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareTypeCode(x_EncounterParticipant source, x_EncounterParticipant target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getName())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Type Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareTypeCode(x_InformationRecipient source, x_InformationRecipient target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getName())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Type Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareTypeCode(ActRelationshipHasComponent source, ActRelationshipHasComponent target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getName())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Type Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareTypeCode(ActRelationshipType source, ActRelationshipType target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getName())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Type Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareContextControlCode(ContextControl source, ContextControl target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getName())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Type Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareTime(TS source, TS target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getNullFlavor().getLiteral() != null && target.getNullFlavor().getLiteral() != null) {
                if (!source.getNullFlavor().getLiteral().equals(target.getNullFlavor().getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Time error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareSignatureCode(CS source, CS target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getCode() != null && target.getCode() != null) {
                if (!source.getCode().equals(target.getCode())) {
                    matched = false;
//                    comparisonReport.addMessage("Code error in " + errorMessage + "\n");
                }
            }
            if (source.getNullFlavor().getLiteral() != null && target.getNullFlavor().getLiteral() != null) {
                if (!source.getNullFlavor().getLiteral().equals(target.getNullFlavor().getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Null Flavor error in " + errorMessage + "\n");
                }
            }
            if (source.getCodeSystem() != null && target.getCodeSystem() != null) {
                if (!source.getCodeSystem().equals(target.getCodeSystem())) {
                    matched = false;
//                    comparisonReport.addMessage("Code System error in " + errorMessage + "\n");
                }
            }
            if (source.getCodeSystemName() != null && target.getCodeSystemName() != null) {
                if (!source.getCodeSystemName().equals(target.getCodeSystemName())) {
                    matched = false;
//                    comparisonReport.addMessage("Code System Name error in " + errorMessage + "\n");
                }
            }
            if (source.getCodeSystemVersion() != null && target.getCodeSystemVersion() != null) {
                if (!source.getCodeSystemVersion().equals(target.getCodeSystemVersion())) {
                    matched = false;
//                    comparisonReport.addMessage("Code System Version error in " + errorMessage + "\n");
                }
            }
            if (source.getDisplayName() != null && target.getDisplayName() != null) {
                if (!source.getDisplayName().equals(target.getDisplayName())) {
                    matched = false;
//                    comparisonReport.addMessage("Display Name error in " + errorMessage + "\n");
                }
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Signature Code Comparison Error in" + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareFunctionCode(CE source, CE target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getCode() != null && target.getCode() != null) {
                if (!source.getCode().equals(target.getCode())) {
                    matched = false;
//                    comparisonReport.addMessage("Code error in " + errorMessage + "\n");
                }
            }
            if (source.getNullFlavor().getLiteral() != null && target.getNullFlavor().getLiteral() != null) {
                if (!source.getNullFlavor().getLiteral().equals(target.getNullFlavor().getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Null Flavor error in " + errorMessage + "\n");
                }
            }
            if (source.getCodeSystem() != null && target.getCodeSystem() != null) {
                if (!source.getCodeSystem().equals(target.getCodeSystem())) {
                    matched = false;
//                    comparisonReport.addMessage("Code System error in " + errorMessage + "\n");
                }
            }
            if (source.getCodeSystemName() != null && target.getCodeSystemName() != null) {
                if (!source.getCodeSystemName().equals(target.getCodeSystemName())) {
                    matched = false;
//                    comparisonReport.addMessage("Code System Name error in " + errorMessage + "\n");
                }
            }
            if (source.getCodeSystemVersion() != null && target.getCodeSystemVersion() != null) {
                if (!source.getCodeSystemVersion().equals(target.getCodeSystemVersion())) {
                    matched = false;
//                    comparisonReport.addMessage("Code System Version error in " + errorMessage + "\n");
                }
            }
            if (source.getDisplayName() != null && target.getDisplayName() != null) {
                if (!source.getDisplayName().equals(target.getDisplayName())) {
                    matched = false;
//                    comparisonReport.addMessage("Display Name error in " + errorMessage + "\n");
                }
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Function Code Comparison error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareSetID(II source, II target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getRoot() != null && target.getRoot() != null) {
                if (!source.getRoot().equals(target.getRoot())) {
//                    comparisonReport.addMessage("Get Root error in " + errorMessage + "\n");
                    matched = false;
                }
            }
            if (source.getAssigningAuthorityName() != null && target.getAssigningAuthorityName() != null) {
                if (!source.getAssigningAuthorityName().equals(target.getAssigningAuthorityName())) {
//                    comparisonReport.addMessage("Assigning Authority Name error in " + errorMessage + "\n");
                    matched = false;
                }
            }
            if (source.getExtension() != null && target.getExtension() != null) {
                if (!source.getExtension().equals(target.getExtension())) {
//                    comparisonReport.addMessage("Extension error in " + errorMessage + "\n");
                    matched = false;
                }
            }
            if (source.getNullFlavor().getLiteral() != null && target.getNullFlavor().getLiteral() != null) {
                if (!source.getNullFlavor().getLiteral().equals(target.getNullFlavor().getLiteral())) {
//                    comparisonReport.addMessage("Null Flavor ror in " + errorMessage + "\n");
                    matched = false;
                }
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Set ID error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareVersionNumber(INT source, INT target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getValue() != null && target.getValue() != null) {
                if (source.getValue().equals(target.getValue())) {
                    matched = false;
//                    comparisonReport.addMessage("Value error in " + errorMessage + "\n");
                }
            }
        } else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Version Number error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareCopyTime(TS source, TS target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getNullFlavor().getLiteral() != null && target.getNullFlavor().getLiteral() != null) {
                if (!source.getNullFlavor().getLiteral().equals(target.getNullFlavor().getLiteral())) {
//                    comparisonReport.addMessage("Null Flavor ror in " + errorMessage + "\n");
                    matched = false;
                }
            }
            if (source.getValue() != null && target.getValue() != null) {
                if (source.getValue().equals(target.getValue())) {
                    matched = false;
//                    comparisonReport.addMessage("Value error in " + errorMessage + "\n");
                }
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Copy Time error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareAdministrativeGenderCode(CE source, CE target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getNullFlavor().getLiteral() != null && target.getNullFlavor().getLiteral() != null) {
                if (!source.getNullFlavor().getLiteral().equals(target.getNullFlavor().getLiteral())) {
//                    comparisonReport.addMessage("Null Flavor ror in " + errorMessage + "\n");
                    matched = false;
                }
            }
            if (source.getCode() != null && target.getCode() != null) {
                if (!source.getCode().equals(target.getCode())) {
//                    comparisonReport.addMessage("Null Flavor ror in " + errorMessage + "\n");
                    matched = false;
                }
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Administrative Gender Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareBirthTime(TS source, TS target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getNullFlavor().getLiteral() != null && target.getNullFlavor().getLiteral() != null) {
                if (!source.getNullFlavor().getLiteral().equals(target.getNullFlavor().getLiteral())) {
//                    comparisonReport.addMessage("Null Flavor ror in " + errorMessage + "\n");
                    matched = false;
                }
            }
            if (source.getValue() != null && target.getValue() != null) {
                if (source.getValue().equals(target.getValue())) {
                    matched = false;
//                    comparisonReport.addMessage("Value Number error in " + errorMessage + "\n");
                }
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Birth Time error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareMaritalStatusCode(CE source, CE target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getNullFlavor().getLiteral() != null && target.getNullFlavor().getLiteral() != null) {
                if (!source.getNullFlavor().getLiteral().equals(target.getNullFlavor().getLiteral())) {
//                    comparisonReport.addMessage("Null Flavor ror in " + errorMessage + "\n");
                    matched = false;
                }
            }
            if (source.getCode() != null && target.getCode() != null) {
                if (!source.getCode().equals(target.getCode())) {
//                    comparisonReport.addMessage("Null Flavor ror in " + errorMessage + "\n");
                    matched = false;
                }
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Marital Status Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareReligiosAffiliationCode(CE source, CE target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getCode() != null && target.getCode() != null) {
                if (!source.getCode().equals(target.getCode())) {
                    matched = false;
//                    comparisonReport.addMessage("Code error in " + errorMessage + "\n");
                }
            }
            if (source.getNullFlavor().getLiteral() != null && target.getNullFlavor().getLiteral() != null) {
                if (!source.getNullFlavor().getLiteral().equals(target.getNullFlavor().getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Null Flavor error in " + errorMessage + "\n");
                }
            }
            if (source.getCodeSystem() != null && target.getCodeSystem() != null) {
                if (!source.getCodeSystem().equals(target.getCodeSystem())) {
                    matched = false;
//                    comparisonReport.addMessage("Code System error in " + errorMessage + "\n");
                }
            }
            if (source.getCodeSystemName() != null && target.getCodeSystemName() != null) {
                if (!source.getCodeSystemName().equals(target.getCodeSystemName())) {
                    matched = false;
//                    comparisonReport.addMessage("Code System Name error in " + errorMessage + "\n");
                }
            }
            if (source.getCodeSystemVersion() != null && target.getCodeSystemVersion() != null) {
                if (!source.getCodeSystemVersion().equals(target.getCodeSystemVersion())) {
                    matched = false;
//                    comparisonReport.addMessage("Code System Version error in " + errorMessage + "\n");
                }
            }
            if (source.getDisplayName() != null && target.getDisplayName() != null) {
                if (!source.getDisplayName().equals(target.getDisplayName())) {
                    matched = false;
//                    comparisonReport.addMessage("Display Name error in " + errorMessage + "\n");
                }
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Religious Affiliation Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareRaceCode(CE source, CE target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getCode() != null && target.getCode() != null) {
                if (!source.getCode().equals(target.getCode())) {
                    matched = false;
//                    comparisonReport.addMessage("Code error in " + errorMessage + "\n");
                }
            }
            if (source.getNullFlavor().getLiteral() != null && target.getNullFlavor().getLiteral() != null) {
                if (!source.getNullFlavor().getLiteral().equals(target.getNullFlavor().getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Null Flavor error in " + errorMessage + "\n");
                }
            }
            if (source.getCodeSystem() != null && target.getCodeSystem() != null) {
                if (!source.getCodeSystem().equals(target.getCodeSystem())) {
                    matched = false;
//                    comparisonReport.addMessage("Code System error in " + errorMessage + "\n");
                }
            }
            if (source.getCodeSystemName() != null && target.getCodeSystemName() != null) {
                if (!source.getCodeSystemName().equals(target.getCodeSystemName())) {
                    matched = false;
//                    comparisonReport.addMessage("Code System Name error in " + errorMessage + "\n");
                }
            }
            if (source.getCodeSystemVersion() != null && target.getCodeSystemVersion() != null) {
                if (!source.getCodeSystemVersion().equals(target.getCodeSystemVersion())) {
                    matched = false;
//                    comparisonReport.addMessage("Code System Version error in " + errorMessage + "\n");
                }
            }
            if (source.getDisplayName() != null && target.getDisplayName() != null) {
                if (!source.getDisplayName().equals(target.getDisplayName())) {
                    matched = false;
//                    comparisonReport.addMessage("Display Name error in " + errorMessage + "\n");
                }
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Race Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareEthnicGroupCode(CE source, CE target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getCode() != null && target.getCode() != null) {
                if (!source.getCode().equals(target.getCode())) {
                    matched = false;
//                    comparisonReport.addMessage("Code error in " + errorMessage + "\n");
                }
            }
            if (source.getNullFlavor().getLiteral() != null && target.getNullFlavor().getLiteral() != null) {
                if (!source.getNullFlavor().getLiteral().equals(target.getNullFlavor().getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Null Flavor error in " + errorMessage + "\n");
                }
            }
            if (source.getCodeSystem() != null && target.getCodeSystem() != null) {
                if (!source.getCodeSystem().equals(target.getCodeSystem())) {
                    matched = false;
//                    comparisonReport.addMessage("Code System error in " + errorMessage + "\n");
                }
            }
            if (source.getCodeSystemName() != null && target.getCodeSystemName() != null) {
                if (!source.getCodeSystemName().equals(target.getCodeSystemName())) {
                    matched = false;
//                    comparisonReport.addMessage("Code System Name error in " + errorMessage + "\n");
                }
            }
            if (source.getCodeSystemVersion() != null && target.getCodeSystemVersion() != null) {
                if (!source.getCodeSystemVersion().equals(target.getCodeSystemVersion())) {
                    matched = false;
//                    comparisonReport.addMessage("Code System Version error in " + errorMessage + "\n");
                }
            }
            if (source.getDisplayName() != null && target.getDisplayName() != null) {
                if (!source.getDisplayName().equals(target.getDisplayName())) {
                    matched = false;
//                    comparisonReport.addMessage("Display Name error in " + errorMessage + "\n");
                }
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Ethnic Group Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareMoodCode(ActMood source, ActMood target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Version Number error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Mood Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareMoodCode(x_DocumentSubstanceMood source, x_DocumentSubstanceMood target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Version Number error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Mood Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareMoodCode(x_DocumentProcedureMood source, x_DocumentProcedureMood target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Version Number error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Mood Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareMoodCode(x_DocumentEncounterMood source, x_DocumentEncounterMood target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Version Number error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Mood Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareMoodCode(x_DocumentActMood source, x_DocumentActMood target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getName())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Version Number error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Mood Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareText(ED source, ED target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLanguage() != null && target.getLanguage() != null) {
                if (!source.getLanguage().equals(target.getLanguage())) {
                    matched = false;
//                    comparisonReport.addMessage("Language error in " + errorMessage + "\n");
                }
            }
            if (source.getText() != null && target.getText() != null) {
                if (!source.getText().equals(target.getText())) {
                    matched = false;
//                    comparisonReport.addMessage("Text error in " + errorMessage + "\n");
                }
            }
            if (source.getMediaType() != null && target.getMediaType() != null) {
                if (!source.getMediaType().equals(target.getMediaType())) {
                    matched = false;
//                    comparisonReport.addMessage("Media Type error in " + errorMessage + "\n");
                }
            }
            if (source.getNullFlavor().getLiteral() != null && target.getNullFlavor().getLiteral() != null) {
                if (!source.getNullFlavor().getLiteral().equals(target.getNullFlavor().getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Null Flavor error in " + errorMessage + "\n");
                }
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Text error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareText(StrucDocText source, StrucDocText target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getText() != null && target.getText() != null) {
                if (!source.getText().equals(target.getText())) {
                    matched = false;
//                    comparisonReport.addMessage("Text error in " + errorMessage + "\n");
                }
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Text error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareDeterminerCode(EntityDeterminer source, EntityDeterminer target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getName())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() !=  target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Determiner Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareDeterminerCode(EntityDeterminerDetermined source, EntityDeterminerDetermined target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLiteral() != null && target.getLiteral() != null) {
                if (!source.getLiteral().equals(target.getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Literal error in " + errorMessage + "\n");
                }
            }
            if (source.getName() != null && target.getName() != null) {
                if (!source.getName().equals(target.getName())) {
                    matched = false;
//                    comparisonReport.addMessage("Name error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() !=  target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Determiner Code error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareSCName(SC source, SC target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getCode() != null && target.getCode() != null) {
                if (!source.getCode().equals(target.getCode())) {
                    matched = false;
//                    comparisonReport.addMessage("Code error in " + errorMessage + "\n");
                }
            }
            if (source.getNullFlavor().getLiteral() != null && target.getNullFlavor().getLiteral() != null) {
                if (!source.getNullFlavor().getLiteral().equals(target.getNullFlavor().getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Null Flavor error in " + errorMessage + "\n");
                }
            }
            if (source.getCodeSystem() != null && target.getCodeSystem() != null) {
                if (!source.getCodeSystem().equals(target.getCodeSystem())) {
                    matched = false;
//                    comparisonReport.addMessage("Code System error in " + errorMessage + "\n");
                }
            }
            if (source.getCodeSystemName() != null && target.getCodeSystemName() != null) {
                if (!source.getCodeSystemName().equals(target.getCodeSystemName())) {
                    matched = false;
//                    comparisonReport.addMessage("Code System Name error in " + errorMessage + "\n");
                }
            }
            if (source.getCodeSystemVersion() != null && target.getCodeSystemVersion() != null) {
                if (!source.getCodeSystemVersion().equals(target.getCodeSystemVersion())) {
                    matched = false;
//                    comparisonReport.addMessage("Code System Version error in " + errorMessage + "\n");
                }
            }
            if (source.getDisplayName() != null && target.getDisplayName() != null) {
                if (!source.getDisplayName().equals(target.getDisplayName())) {
                    matched = false;
//                    comparisonReport.addMessage("Display Name error in " + errorMessage + "\n");
                }
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Name error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareNameEN(EN source, EN target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getNullFlavor().getLiteral() != null && target.getNullFlavor().getLiteral() != null) {
                if (!source.getNullFlavor().getLiteral().equals(target.getNullFlavor().getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Null Flavor error in " + errorMessage + "\n");
                }
            }
            if (source.getText() != null && target.getText() != null) {
                if (!source.getText().equals(target.getText())) {
                    matched = false;
//                    comparisonReport.addMessage("Text error in " + errorMessage + "\n");
                }
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Name error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean comparePreferenceInd(BL source, BL target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getNullFlavor().getLiteral() != null && target.getNullFlavor().getLiteral() != null) {
                if (!source.getNullFlavor().getLiteral().equals(target.getNullFlavor().getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Null Flavor error in " + errorMessage + "\n");
                }
            }
            if (source.getValue() != target.getValue()) {
                matched = false;
//                comparisonReport.addMessage("Value error in " + errorMessage + "\n");
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Preference Ind error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareDerivationExpr(ST source, ST target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getLanguage() != null && target.getLanguage() != null) {
                if (!source.getLanguage().equals(target.getLanguage())) {
                    matched = false;
//                    comparisonReport.addMessage("Language error in " + errorMessage + "\n");
                }
            }
            if (source.getText() != null && target.getText() != null) {
                if (!source.getText().equals(target.getText())) {
                    matched = false;
//                    comparisonReport.addMessage("Text error in " + errorMessage + "\n");
                }
            }
            if (source.getMediaType() != null && target.getMediaType() != null) {
                if (!source.getMediaType().equals(target.getMediaType())) {
                    matched = false;
//                    comparisonReport.addMessage("Media Type error in " + errorMessage + "\n");
                }
            }
            if (source.getNullFlavor().getLiteral() != null && target.getNullFlavor().getLiteral() != null) {
                if (!source.getNullFlavor().getLiteral().equals(target.getNullFlavor().getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Null Flavor error in " + errorMessage + "\n");
                }
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Derivation Expr error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareRepeatNumber(IVL_INT source, IVL_INT target) {
        Boolean matched = true;
        if (source != null && target != null) {
            if (source.getCenter() != null && target.getCenter() != null) {
                if (source.getCenter().getValue() != null && target.getCenter().getValue() != null) {
                    if (!source.getCenter().getValue().equals(target.getCenter().getValue())) {
                        matched = false;
//                        comparisonReport.addMessage("Center error in " + errorMessage + "\n");
                    }
                }
            }
            if (source.getHigh() != null && target.getHigh() != null) {
                if (source.getHigh().getValue() != null && target.getHigh().getValue() != null) {
                    if (!source.getHigh().getValue().equals(target.getHigh().getValue())) {
                        matched = false;
//                        comparisonReport.addMessage("High error in " + errorMessage + "\n");
                    }
                }
            }
            if (source.getLow() != null && target.getLow() != null) {
                if (source.getLow().getValue() != null && target.getLow().getValue() != null) {
                    if (!source.getLow().getValue().equals(target.getLow().getValue())) {
                        matched = false;
//                        comparisonReport.addMessage("Low error in " + errorMessage + "\n");
                    }
                }
            }
            if (source.getWidth() != null && target.getWidth() != null) {
                if (source.getWidth().getValue() != null && target.getWidth().getValue() != null) {
                    if (!source.getWidth().getValue().equals(target.getWidth().getValue())) {
                        matched = false;
//                        comparisonReport.addMessage("Width error in " + errorMessage + "\n");
                    }
                }
            }
            if (source.getValue() != null && target.getValue() != null) {
                if (!source.getValue().equals(target.getValue())) {
                    matched = false;
//                    comparisonReport.addMessage("Media Type error in " + errorMessage + "\n");
                }
            }
            if (source.getNullFlavor().getLiteral() != null && target.getNullFlavor().getLiteral() != null) {
                if (!source.getNullFlavor().getLiteral().equals(target.getNullFlavor().getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Null Flavor error in " + errorMessage + "\n");
                }
            }

        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Repeat Number error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareValues(EList<ANY> source, EList<ANY> target) {
        return true;
    }

    private boolean compareValue(ANY source, ANY target) {
        return true;
    }

    private boolean compareCodes(EList<CE> source, EList<CE> target) {
        boolean errorExists = false;
        for (int i=0; i<source.size(); i++) {
            int targetMatches = 0;
            for (int j=0; j<target.size(); j++) {
                boolean sourceTargetMismatch = false;
                if (source.get(i).getCode() != null && target.get(j).getCode() != null) {
                    if (!source.get(i).getCode().equals(target.get(j).getCode())) {
                        sourceTargetMismatch = true;
//                        comparisonReport.addMessage("Code error source " + i + " in " + errorMessage + "\n");
                    }
                }
                if (source.get(i).getNullFlavor().getLiteral() != null && target.get(j).getNullFlavor().getLiteral() != null) {
                    if (!source.get(i).getNullFlavor().getLiteral().equals(target.get(j).getNullFlavor().getLiteral())) {
                        sourceTargetMismatch = true;
//                        comparisonReport.addMessage("Null Flavor error source " + i + " in " + errorMessage + "\n");
                    }
                }
                if (source.get(i).getCodeSystemName() != null && target.get(j).getCodeSystemName() != null) {
                    if (!source.get(i).getCodeSystemName().equals(target.get(j).getCodeSystemName())) {
                        sourceTargetMismatch = true;
//                        comparisonReport.addMessage("Code System Name error source " + i + " in " + errorMessage + "\n");
                    }
                }
                if (source.get(i).getCodeSystem() != null && target.get(j).getCodeSystem() != null) {
                    if (!source.get(i).getCodeSystem().equals(target.get(j).getCodeSystem())) {
                        sourceTargetMismatch = true;
//                        comparisonReport.addMessage("Code System error source " + i + " in " + errorMessage + "\n");
                    }
                }
                if (source.get(i).getCodeSystemVersion() != null && target.get(j).getCodeSystemVersion() != null) {
                    if (!source.get(i).getCodeSystemVersion().equals(target.get(j).getCodeSystemVersion())) {
                        sourceTargetMismatch = true;
//                        comparisonReport.addMessage("Code System Version error source " + i + " in " + errorMessage + "\n");
                    }
                }
                if (source.get(i).getDisplayName() != null && target.get(j).getDisplayName() != null) {
                    if (!source.get(i).getDisplayName().equals(target.get(j).getDisplayName())) {
                        sourceTargetMismatch = true;
//                        comparisonReport.addMessage("Display Name error source " + i + " in " + errorMessage + "\n");
                    }
                }

                if (!sourceTargetMismatch){
                    targetMatches++;
                }
            }
            if (targetMatches==0) {
                errorExists = true;
//                comparisonReport.addMessage("Codes Error source " + i + " in " + errorMessage + "\n");
            } else if (targetMatches>1) {
//                comparisonReport.addMessage("Codes Warning source " + i + " has " + targetMatches + " in target in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private boolean compareCodesCD(EList<CD> source, EList<CD> target) {
        boolean errorExists = false;
        for (int i=0; i<source.size(); i++) {
            int targetMatches = 0;
            for (int j=0; j<target.size(); j++) {
                boolean sourceTargetMismatch = false;
                if (source.get(i).getCode() != null && target.get(j).getCode() != null) {
                    if (!source.get(i).getCode().equals(target.get(j).getCode())) {
                        sourceTargetMismatch = true;
//                        comparisonReport.addMessage("Code error source " + i + " in " + errorMessage + "\n");
                    }
                }
                if (source.get(i).getNullFlavor().getLiteral() != null && target.get(j).getNullFlavor().getLiteral() != null) {
                    if (!source.get(i).getNullFlavor().getLiteral().equals(target.get(j).getNullFlavor().getLiteral())) {
                        sourceTargetMismatch = true;
//                        comparisonReport.addMessage("Null Flavor error source " + i + " in " + errorMessage + "\n");
                    }
                }
                if (source.get(i).getCodeSystemName() != null && target.get(j).getCodeSystemName() != null) {
                    if (!source.get(i).getCodeSystemName().equals(target.get(j).getCodeSystemName())) {
                        sourceTargetMismatch = true;
//                        comparisonReport.addMessage("Code System Name error source " + i + " in " + errorMessage + "\n");
                    }
                }
                if (source.get(i).getCodeSystem() != null && target.get(j).getCodeSystem() != null) {
                    if (!source.get(i).getCodeSystem().equals(target.get(j).getCodeSystem())) {
                        sourceTargetMismatch = true;
//                        comparisonReport.addMessage("Code System error source " + i + " in " + errorMessage + "\n");
                    }
                }
                if (source.get(i).getCodeSystemVersion() != null && target.get(j).getCodeSystemVersion() != null) {
                    if (!source.get(i).getCodeSystemVersion().equals(target.get(j).getCodeSystemVersion())) {
                        sourceTargetMismatch = true;
//                        comparisonReport.addMessage("Code System Version error source " + i + " in " + errorMessage + "\n");
                    }
                }
                if (source.get(i).getDisplayName() != null && target.get(j).getDisplayName() != null) {
                    if (!source.get(i).getDisplayName().equals(target.get(j).getDisplayName())) {
                        sourceTargetMismatch = true;
//                        comparisonReport.addMessage("Display Name error source " + i + " in " + errorMessage + "\n");
                    }
                }

                if (!sourceTargetMismatch){
                    targetMatches++;
                }
            }
            if (targetMatches==0) {
                errorExists = true;
//                comparisonReport.addMessage("RCodes Error source " + i + " in " + errorMessage + "\n");
            } else if (targetMatches>1) {
//                comparisonReport.addMessage("Codes Warning source " + i + " has " + targetMatches + " in target in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private boolean compareTargetSiteCode(EList<CD> source, EList<CD> target) {
        boolean errorExists = false;
        for (int i=0; i<source.size(); i++) {
            int targetMatches = 0;
            for (int j=0; j<target.size(); j++) {
                boolean sourceTargetMismatch = false;
                if (source.get(i).getCode() != null && target.get(j).getCode() != null) {
                    if (!source.get(i).getCode().equals(target.get(j).getCode())) {
                        sourceTargetMismatch = true;
//                        comparisonReport.addMessage("Code error source " + i + " in " + errorMessage + "\n");
                    }
                }
                if (source.get(i).getNullFlavor().getLiteral() != null && target.get(j).getNullFlavor().getLiteral() != null) {
                    if (!source.get(i).getNullFlavor().getLiteral().equals(target.get(j).getNullFlavor().getLiteral())) {
                        sourceTargetMismatch = true;
//                        comparisonReport.addMessage("Null Flavor error source " + i + " in " + errorMessage + "\n");
                    }
                }
                if (source.get(i).getCodeSystemName() != null && target.get(j).getCodeSystemName() != null) {
                    if (!source.get(i).getCodeSystemName().equals(target.get(j).getCodeSystemName())) {
                        sourceTargetMismatch = true;
//                        comparisonReport.addMessage("Code System Name error source " + i + " in " + errorMessage + "\n");
                    }
                }
                if (source.get(i).getCodeSystem() != null && target.get(j).getCodeSystem() != null) {
                    if (!source.get(i).getCodeSystem().equals(target.get(j).getCodeSystem())) {
                        sourceTargetMismatch = true;
//                        comparisonReport.addMessage("Code System error source " + i + " in " + errorMessage + "\n");
                    }
                }
                if (source.get(i).getCodeSystemVersion() != null && target.get(j).getCodeSystemVersion() != null) {
                    if (!source.get(i).getCodeSystemVersion().equals(target.get(j).getCodeSystemVersion())) {
                        sourceTargetMismatch = true;
//                        comparisonReport.addMessage("Code System Version error source " + i + " in " + errorMessage + "\n");
                    }
                }
                if (source.get(i).getDisplayName() != null && target.get(j).getDisplayName() != null) {
                    if (!source.get(i).getDisplayName().equals(target.get(j).getDisplayName())) {
                        sourceTargetMismatch = true;
//                        comparisonReport.addMessage("Display Name error source " + i + " in " + errorMessage + "\n");
                    }
                }

                if (!sourceTargetMismatch){
                    targetMatches++;
                }
            }
            if (targetMatches==0) {
                errorExists = true;
//                comparisonReport.addMessage("Target Site Codes Error source " + i + " in " + errorMessage + "\n");
            } else if (targetMatches>1) {
//                comparisonReport.addMessage("Target Site Warning source " + i + " has " + targetMatches + " in target in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private boolean compareDose(IVL_PQ source, IVL_PQ target) {
        Boolean matched = true;
        if (source != null && target != null) {
            if (source.getCenter() != null && target.getCenter() != null) {
                if (source.getCenter().getValue() != null && target.getCenter().getValue() != null) {
                    if (!source.getCenter().getValue().equals(target.getCenter().getValue())) {
                        matched = false;
//                        comparisonReport.addMessage("Center error in " + errorMessage + "\n");
                    }
                }
            }
            if (source.getHigh() != null && target.getHigh() != null) {
                if (source.getHigh().getValue() != null && target.getHigh().getValue() != null) {
                    if (!source.getHigh().getValue().equals(target.getHigh().getValue())) {
                        matched = false;
//                        comparisonReport.addMessage("High error in " + errorMessage + "\n");
                    }
                }
            }
            if (source.getLow() != null && target.getLow() != null) {
                if (source.getLow().getValue() != null && target.getLow().getValue() != null) {
                    if (!source.getLow().getValue().equals(target.getLow().getValue())) {
                        matched = false;
//                        comparisonReport.addMessage("Low error in " + errorMessage + "\n");
                    }
                }
            }
            if (source.getWidth() != null && target.getWidth() != null) {
                if (source.getWidth().getValue() != null && target.getWidth().getValue() != null) {
                    if (!source.getWidth().getValue().equals(target.getWidth().getValue())) {
                        matched = false;
//                        comparisonReport.addMessage("Width error in " + errorMessage + "\n");
                    }
                }
            }
            if (source.getValue() != null && target.getValue() != null) {
                if (!source.getValue().equals(target.getValue())) {
                    matched = false;
//                    comparisonReport.addMessage("Media Type error in " + errorMessage + "\n");
                }
            }
            if (source.getNullFlavor().getLiteral() != null && target.getNullFlavor().getLiteral() != null) {
                if (!source.getNullFlavor().getLiteral().equals(target.getNullFlavor().getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Null Flavor error in " + errorMessage + "\n");
                }
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Dose error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareMaxDose(RTO_PQ_PQ source, RTO_PQ_PQ target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getDenominator().getValue() != null && target.getDenominator().getValue() != null) {
                if (!source.getDenominator().getValue().equals(target.getDenominator().getValue())) {
                    matched = false;
//                    comparisonReport.addMessage("Denominator error in " + errorMessage + "\n");
                }
            }
            if (source.getNumerator().getValue() != null && target.getNumerator().getValue() != null) {
                if (!source.getNumerator().getValue().equals(target.getNumerator().getValue())) {
                    matched = false;
//                    comparisonReport.addMessage("Numerator error in " + errorMessage + "\n");
                }
            }
            if (source.getNullFlavor().getLiteral() != null && target.getNullFlavor().getLiteral() != null) {
                if (!source.getNullFlavor().getLiteral().equals(target.getNullFlavor().getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("NullFlavor error in " + errorMessage + "\n");
                }
            }
            if (source.toString() != null && target.toString() != null) {
                if (!source.toString().equals(target.toString())) {
                    matched = false;
//                    comparisonReport.addMessage("To String error in " + errorMessage + "\n");
                }
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Max Dose error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareIDAttribute(String source, String target) {
        if (source != null && target != null) {
            if (!(source.equals(target))) {
//                comparisonReport.addMessage("ID Attributes error in " + errorMessage + "\n");
                return false;
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("ID Attributes error in " + errorMessage + "\n");
            return false;
        }
        return true;
    }

    private boolean compareQuantity(PQ source, PQ target) {
        boolean matched = true;
        if (source != null && target != null) {
            if (source.getValue() != null && target.getValue() != null) {
                if (!source.getValue().equals(target.getValue())) {
                    matched = false;
//                    comparisonReport.addMessage("Value error in " + errorMessage + "\n");
                }
            }
            if (source.getUnit() != null && target.getUnit() != null) {
                if (!source.getUnit().equals(target.getUnit())) {
                    matched = false;
//                    comparisonReport.addMessage("Unit error in " + errorMessage + "\n");
                }
            }
            if (source.getNullFlavor().getLiteral() != null && target.getNullFlavor().getLiteral() != null) {
                if (!source.getNullFlavor().getLiteral().equals(target.getNullFlavor().getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Null Flavor error in " + errorMessage + "\n");
                }
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Quantity error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

    private boolean compareQuantities(EList<PQ> source, EList<PQ> target) {

        boolean errorExists = false;
        for (int i=0; i<source.size(); i++) {
            int targetMatches = 0;
            for (int j=0; j<target.size(); j++) {
                boolean sourceTargetMismatch = false;
                if (source.get(i).getValue() != null && target.get(j).getValue() != null) {
                    if (!source.get(i).getValue().equals(target.get(j).getValue())) {
                        sourceTargetMismatch = true;
//                        comparisonReport.addMessage("Value error source " + i + " in " + errorMessage + "\n");
                    }
                }
                if (source.get(i).getNullFlavor().getLiteral() != null && target.get(j).getNullFlavor().getLiteral() != null) {
                    if (!source.get(i).getNullFlavor().getLiteral().equals(target.get(j).getNullFlavor().getLiteral())) {
                        sourceTargetMismatch = true;
//                        comparisonReport.addMessage("Null Flavor error source " + i + " in " + errorMessage + "\n");
                    }
                }
                if (source.get(i).getUnit() != null && target.get(j).getUnit() != null) {
                    if (!source.get(i).getUnit().equals(target.get(j).getUnit())) {
                        sourceTargetMismatch = true;
//                        comparisonReport.addMessage("Unit error source " + i + " in " + errorMessage + "\n");
                    }
                }

                if (!sourceTargetMismatch){
                    targetMatches++;
                }
            }
            if (targetMatches==0) {
                errorExists = true;
//                comparisonReport.addMessage("Quantities Error source " + i + " in " + errorMessage + "\n");
            } else if (targetMatches>1) {
//                comparisonReport.addMessage("Quantities Warning source " + i + " has " + targetMatches + " in target in " + errorMessage + "\n");
            }
        }
        return !errorExists;
    }

    private boolean compareExpectedUseTime(IVL_TS source, IVL_TS target) {
        Boolean matched = true;
        if (source != null && target != null) {
            if (source.getCenter() != null && target.getCenter() != null) {
                if (source.getCenter().getValue() != null && target.getCenter().getValue() != null) {
                    if (!source.getCenter().getValue().equals(target.getCenter().getValue())) {
                        matched = false;
//                        comparisonReport.addMessage("Center error in " + errorMessage + "\n");
                    }
                }
            }
            if (source.getHigh() != null && target.getHigh() != null) {
                if (source.getHigh().getValue() != null && target.getHigh().getValue() != null) {
                    if (!source.getHigh().getValue().equals(target.getHigh().getValue())) {
                        matched = false;
//                        comparisonReport.addMessage("High error in " + errorMessage + "\n");
                    }
                }
            }
            if (source.getLow() != null && target.getLow() != null) {
                if (source.getLow().getValue() != null && target.getLow().getValue() != null) {
                    if (!source.getLow().getValue().equals(target.getLow().getValue())) {
                        matched = false;
//                        comparisonReport.addMessage("Low error in " + errorMessage + "\n");
                    }
                }
            }
            if (source.getWidth() != null && target.getWidth() != null) {
                if (source.getWidth().getValue() != null && target.getWidth().getValue() != null) {
                    if (!source.getWidth().getValue().equals(target.getWidth().getValue())) {
                        matched = false;
//                        comparisonReport.addMessage("Width error in " + errorMessage + "\n");
                    }
                }
            }
            if (source.getValue() != null && target.getValue() != null) {
                if (!source.getValue().equals(target.getValue())) {
                    matched = false;
//                    comparisonReport.addMessage("Media Type error in " + errorMessage + "\n");
                }
            }
            if (source.getNullFlavor().getLiteral() != null && target.getNullFlavor().getLiteral() != null) {
                if (!source.getNullFlavor().getLiteral().equals(target.getNullFlavor().getLiteral())) {
                    matched = false;
//                    comparisonReport.addMessage("Null Flavor error in " + errorMessage + "\n");
                }
            }
        }  else if ((source != null && target == null) || (source == null && target != null)) {
//            comparisonReport.addMessage("Expected Use Time error in " + errorMessage + "\n");
            return false;
        }
        return matched;
    }

}
