package com.github.aks8m.traversal.MethodType;

import com.github.aks8m.compare.tree.ValueNode;

import java.util.*;

public enum NodeValueType {

    ClinicalDocument(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),

    Act(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    AssignedAuthor(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    AssignedCustodian(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    AssignedEntity(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    AssociatedEntity(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    Authenticators(MethodType.ComplexLocationList, new ArrayList<Pair<String,NodeValueType>>()),
    Authors(MethodType.ComplexLocationList, new ArrayList<Pair<String,NodeValueType>>()),
    Authorizations(MethodType.ComplexLocationList, new ArrayList<Pair<String,NodeValueType>>()),
    AuthorizingDevice(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    Birthplace(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    Component1(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    Component2(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    Component3s(MethodType.ComplexLocationList, new ArrayList<Pair<String,NodeValueType>>()),
    Component4s(MethodType.ComplexLocationList, new ArrayList<Pair<String,NodeValueType>>()),
    Component5s(MethodType.ComplexLocationList, new ArrayList<Pair<String,NodeValueType>>()),
    Consent(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    Consumable(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    Criterion(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    Custodian(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    CustodianOrganization(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    DataEnterer(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    Device(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    DocumentationOfs(MethodType.ComplexLocationList, new ArrayList<Pair<String,NodeValueType>>()),
    EncompassingEncounter(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    Encounter(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    EncounterParticipants(MethodType.ComplexLocationList, new ArrayList<Pair<String,NodeValueType>>()),
    Entity(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    Entries(MethodType.ComplexLocationList, new ArrayList<Pair<String,NodeValueType>>()),
    EntryRelationships(MethodType.ComplexLocationList, new ArrayList<Pair<String,NodeValueType>>()),
    ExternalAct(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    ExternalDocument(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    ExternalObservation(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    ExternalProcedure(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    Guardians(MethodType.ComplexLocationList, new ArrayList<Pair<String,NodeValueType>>()),
    HealthCareFacility(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    Informant12s(MethodType.ComplexLocationList, new ArrayList<Pair<String,NodeValueType>>()),
    InformationRecipients(MethodType.ComplexLocationList, new ArrayList<Pair<String,NodeValueType>>()),
    InFulfillmentOfs(MethodType.ComplexLocationList, new ArrayList<Pair<String,NodeValueType>>()),
    IntendedRecipient(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    LabeledDrug(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    LanguageCommunication(MethodType.ComplexLocationList, new ArrayList<Pair<String,NodeValueType>>()),
    LegalAuthenticator(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    Location(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    MaintainedEntities(MethodType.ComplexLocationList, new ArrayList<Pair<String,NodeValueType>>()),
    ManufacturedProduct(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    Material(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    NonXMLBody(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    Observation(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    ObservationMedia(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    ObservationRange(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    Order(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    Organization(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    Organizations(MethodType.ComplexLocationList, new ArrayList<Pair<String,NodeValueType>>()),
    OrganizationPartOf(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    Organizer(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    ParentDocument(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    Participant1s(MethodType.ComplexLocationList, new ArrayList<Pair<String,NodeValueType>>()),
    Participant2s(MethodType.ComplexLocationList, new ArrayList<Pair<String,NodeValueType>>()),
    ParticipantRole(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    Patient(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    PatientRole(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    Performer1s(MethodType.ComplexLocationList, new ArrayList<Pair<String,NodeValueType>>()),
    Performer2s(MethodType.ComplexLocationList, new ArrayList<Pair<String,NodeValueType>>()),
    Person(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    Place(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    PlayingEntity(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    Preconditions(MethodType.ComplexLocationList, new ArrayList<Pair<String,NodeValueType>>()),
    Procedure(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    Product(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    RecordTargets(MethodType.ComplexLocationList, new ArrayList<Pair<String,NodeValueType>>()),
    References(MethodType.ComplexLocationList, new ArrayList<Pair<String,NodeValueType>>()),
    ReferenceRanges(MethodType.ComplexLocationList, new ArrayList<Pair<String,NodeValueType>>()),
    RegionOfInterest(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    RelatedDocuments(MethodType.ComplexLocationList, new ArrayList<Pair<String,NodeValueType>>()),
    RelatedEntity(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    RelatedSubject(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    ResponsibleParty(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    Section(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    ServiceEvent(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    Specimens(MethodType.ComplexLocationList, new ArrayList<Pair<String,NodeValueType>>()),
    SpecimenRole(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    StructuredBody(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    Subject(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    SubjectPerson(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    SubstanceAdministration(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),
    Supply(MethodType.ComplexLocation, new ArrayList<Pair<String,NodeValueType>>()),

    //LeafTypes,
    Addrs(new ArrayList<List<String>>(), MethodType.ValueNodeList),
    AdministrationUnitCode(new ArrayList<List<String>>(), MethodType.ValueNode),
    AdministrativeGenderCode(new ArrayList<List<String>>(), MethodType.ValueNode),
    ApproachSiteCodes(new ArrayList<List<String>>(), MethodType.ValueNodeList),
    AwarenessCode(new ArrayList<List<String>>(), MethodType.ValueNode),
    BirthTime(new ArrayList<List<String>>(), MethodType.ValueNode),
    ClassCode(new ArrayList<List<String>>(), MethodType.ValueNode),
    Code(new ArrayList<List<String>>(), MethodType.ValueNode),
    ConfidentialityCode(new ArrayList<List<String>>(), MethodType.ValueNode),
    ContextConductionInd(new ArrayList<List<String>>(), MethodType.ValueNode),
    ContextControlCode(new ArrayList<List<String>>(), MethodType.ValueNode),
    CopyTime(new ArrayList<List<String>>(), MethodType.ValueNode),
    Desc(new ArrayList<List<String>>(), MethodType.ValueNode),
    DerivationExpr(new ArrayList<List<String>>(), MethodType.ValueNode),
    DeterminerCode(new ArrayList<List<String>>(), MethodType.ValueNode),
    DischargeDispositionCode(new ArrayList<List<String>>(), MethodType.ValueNode),
    DoseQuantity(new ArrayList<List<String>>(), MethodType.ValueNode),
    EffectiveTime(new ArrayList<List<String>>(), MethodType.ValueNode),
    EffectiveTimes(new ArrayList<List<String>>(), MethodType.ValueNodeList),
    EthnicGroupCode(new ArrayList<List<String>>(), MethodType.ValueNode),
    ExpectedUseTime(new ArrayList<List<String>>(), MethodType.ValueNode),
    FunctionCode(new ArrayList<List<String>>(), MethodType.ValueNode),
    ID(new ArrayList<List<String>>(), MethodType.ValueNode),
    IDs(new ArrayList<List<String>>(), MethodType.ValueNodeList),
    IDAttribute(new ArrayList<List<String>>(), MethodType.ValueNode),
    InterpretationCode(new ArrayList<List<String>>(), MethodType.ValueNode),
    InterpretationCodes(new ArrayList<List<String>>(), MethodType.ValueNodeList),
    IndependentInd(new ArrayList<List<String>>(), MethodType.ValueNode),
    InversionInd(new ArrayList<List<String>>(), MethodType.ValueNode),
    LanguageCode(new ArrayList<List<String>>(), MethodType.ValueNode),
    LotNumberText(new ArrayList<List<String>>(), MethodType.ValueNode),
    ManufacturedModelName(new ArrayList<List<String>>(), MethodType.ValueNode),
    MaritalStatusCode(new ArrayList<List<String>>(), MethodType.ValueNode),
    MaxDoseQuantity(new ArrayList<List<String>>(), MethodType.ValueNode),
    MethodCodes(new ArrayList<List<String>>(), MethodType.ValueNodeList),
    MoodCode(new ArrayList<List<String>>(), MethodType.ValueNode),
    ModeCode(new ArrayList<List<String>>(), MethodType.ValueNode),
    Name(new ArrayList<List<String>>(), MethodType.ValueNode),
    Names(new ArrayList<List<String>>(), MethodType.ValueNodeList),
    NegationInd(new ArrayList<List<String>>(), MethodType.ValueNode),
    NullFlavor(new ArrayList<List<String>>(), MethodType.ValueNode),
    PreferenceInd(new ArrayList<List<String>>(), MethodType.ValueNode),
    PriorityCode(new ArrayList<List<String>>(), MethodType.ValueNode),
    PriorityCodes(new ArrayList<List<String>>(), MethodType.ValueNodeList),
    ProficiencyLevelCode(new ArrayList<List<String>>(), MethodType.ValueNode),
    Quantity(new ArrayList<List<String>>(), MethodType.ValueNode),
    Quantities(new ArrayList<List<String>>(), MethodType.ValueNodeList),
    RaceCode(new ArrayList<List<String>>(), MethodType.ValueNode),
    RateQuantity(new ArrayList<List<String>>(), MethodType.ValueNode),
    RealmCodes(new ArrayList<List<String>>(), MethodType.ValueNodeList),
    RegionOfInterestValues(new ArrayList<List<String>>(), MethodType.ValueNodeList),
    ReligiousAffiliationCode(new ArrayList<List<String>>(), MethodType.ValueNode),
    RepeatNumber(new ArrayList<List<String>>(), MethodType.ValueNode),
    RouteCode(new ArrayList<List<String>>(), MethodType.ValueNode),
    SeperatableInd(new ArrayList<List<String>>(), MethodType.ValueNode),
    SetID(new ArrayList<List<String>>(), MethodType.ValueNode),
    SequenceNumber(new ArrayList<List<String>>(), MethodType.ValueNode),
    SignatureCode(new ArrayList<List<String>>(), MethodType.ValueNode),
    SoftwareName(new ArrayList<List<String>>(), MethodType.ValueNode),
    StandardIndustryClassCode(new ArrayList<List<String>>(), MethodType.ValueNode),
    StatusCode(new ArrayList<List<String>>(), MethodType.ValueNode),
    StrucDocText(new ArrayList<List<String>>(), MethodType.ValueNode),
    TargetSiteCodes(new ArrayList<List<String>>(), MethodType.ValueNodeList),
    Telecoms(new ArrayList<List<String>>(), MethodType.ValueNodeList),
    TemplateIDs(new ArrayList<List<String>>(), MethodType.ValueNodeList),
    Time(new ArrayList<List<String>>(), MethodType.ValueNode),
    Title(new ArrayList<List<String>>(), MethodType.ValueNode),
    Text(new ArrayList<List<String>>(), MethodType.ValueNode),
    TypeCode(new ArrayList<List<String>>(), MethodType.ValueNode),
    TypeID(new ArrayList<List<String>>(), MethodType.ValueNode),
    Value(new ArrayList<List<String>>(), MethodType.ValueNode),
    Values(new ArrayList<List<String>>(), MethodType.ValueNodeList),
    VersionNumber(new ArrayList<List<String>>(), MethodType.ValueNode),
    ;

    NodeValueType(MethodType methodType, List<Pair<String, NodeValueType>> callableMethods) {
        this.methodType = methodType;
        this.callableMethods = callableMethods;
    }

    NodeValueType(List<List<String>> values, MethodType methodType) {
        this.methodType = methodType;
        this.valueMethods = values;
    }



    public enum MethodType {
        ComplexLocationList,
        ComplexLocation,
        ValueNode,
        ValueNodeList;
    }
    private MethodType methodType;

    private List<Pair<String,NodeValueType>> callableMethods;

    private List<List<String>> valueMethods;

    public MethodType getMethodType() {
        return this.methodType;
    }

    public List<Pair<String,NodeValueType>> getCallableMethods() {
        return this.callableMethods;
    }

    public void addCallableMethods(List<Pair<String,NodeValueType>> methods) {
        this.callableMethods.addAll(methods);
    }

    public List<List<String>> getValueMethods() { return this.valueMethods; }

    public void addValueMethod(List<List<String>> methods) { this.valueMethods.addAll(methods); }



}
