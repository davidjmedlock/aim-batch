package com.aim.aimbatch.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A TmpProviderLoadError.
 */
@Entity
@Table(name = "tmp_provider_load_error")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TmpProviderLoadError implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "health_plan_provider_id")
    private String healthPlanProviderId;

    @Column(name = "network_code")
    private String networkCode;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "business_name")
    private String businessName;

    @Column(name = "npi")
    private String npi;

    @Column(name = "tax_id")
    private String taxId;

    @Column(name = "provider_type")
    private String providerType;

    @Column(name = "full_or_part_time")
    private String fullOrPartTime;

    @Column(name = "in_person_after_hours")
    private String inPersonAfterHours;

    @Column(name = "supervising_specialist")
    private String supervisingSpecialist;

    @Column(name = "supervising_npi")
    private String supervisingNpi;

    @Column(name = "specialization_certificate")
    private String specializationCertificate;

    @Column(name = "health_plan_provider_address_id")
    private String healthPlanProviderAddressId;

    @Column(name = "address_type")
    private String addressType;

    @Column(name = "clinic_name")
    private String clinicName;

    @Column(name = "address_1")
    private String address1;

    @Column(name = "address_2")
    private String address2;

    @Column(name = "address_3")
    private String address3;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip")
    private String zip;

    @Column(name = "county")
    private String county;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "fax_number")
    private String faxNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "taxonomy_code")
    private String taxonomyCode;

    @Column(name = "specialty_code")
    private String specialtyCode;

    @Column(name = "primary_specialty")
    private String primarySpecialty;

    @Column(name = "board_certified")
    private String boardCertified;

    @Column(name = "board_eligible")
    private String boardEligible;

    @Column(name = "is_pcp")
    private String isPcp;

    @Column(name = "accepting_new_patients")
    private String acceptingNewPatients;

    @Column(name = "accepting_new_referrals")
    private String acceptingNewReferrals;

    @Column(name = "network_tier_id")
    private String networkTierId;

    @Column(name = "provider_group_name")
    private String providerGroupName;

    @Column(name = "outpatient_appointment_availability")
    private String outpatientAppointmentAvailability;

    @Column(name = "in_directory")
    private String inDirectory;

    @Column(name = "contract_effective_date")
    private String contractEffectiveDate;

    @Column(name = "contract_termination_date")
    private String contractTerminationDate;

    @Column(name = "provider_assigned_members")
    private String providerAssignedMembers;

    @Column(name = "location_assigned_members")
    private String locationAssignedMembers;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "processed_at")
    private LocalDate processedAt;

    @Column(name = "record_status")
    private String recordStatus;

    @Column(name = "load_file_name")
    private String loadFileName;

    @Column(name = "provider_id")
    private Long providerId;

    @Column(name = "provider_network_id")
    private Long providerNetworkId;

    @Column(name = "provider_address_id")
    private Long providerAddressId;

    @Column(name = "provider_taxonomy_id")
    private Long providerTaxonomyId;

    @Column(name = "network_id")
    private Long networkId;

    @Column(name = "load_error_date")
    private LocalDate loadErrorDate;

    @Column(name = "load_error_status")
    private String loadErrorStatus;

    @Column(name = "tmp_provider_load_id")
    private Long tmpProviderLoadId;

    @ManyToOne
    @JsonIgnoreProperties("tmpProviderLoadErrors")
    private LoadErrorReason loadErrorReason;

    @Column(name = "tenant_hash_key")
    private String tenantHashKey;

    @Column(name = "credential")
    private String credential;

    @Column(name = "gender")
    private String gender;

    @Column(name = "salutation")
    private String salutation;

    @Column(name = "medical_group_npi")
    private String medicalGroupNpi;

    @Column(name = "data_entry_endpoint")
    private String dataEntryEndpoint;

    @Column(name = "source_system")
    private String sourceSystem;

    @Column(name = "in_network")
    private Boolean inNetwork;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "health_plan_contract_id")
    private String healthPlanContractId;

    @Column(name = "group_relationship_type")
    private String groupRelationshipType;

    @Column(name = "network_record_status")
    private String networkRecordStatus;

    @Column(name = "panel_status")
    private String panelStatus;
    
    @Column(name = "provider_entity_type")
    private String providerEntityType;

    @Column(name = "corporate_classification")
    private String corporateClassification;

    @Column(name = "tax_id_type")
    private String taxIdType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHealthPlanProviderId() {
        return healthPlanProviderId;
    }

    public TmpProviderLoadError healthPlanProviderId(String healthPlanProviderId) {
        this.healthPlanProviderId = healthPlanProviderId;
        return this;
    }

    public void setHealthPlanProviderId(String healthPlanProviderId) {
        this.healthPlanProviderId = healthPlanProviderId;
    }

    public String getNetworkCode() {
        return networkCode;
    }

    public TmpProviderLoadError networkCode(String networkCode) {
        this.networkCode = networkCode;
        return this;
    }

    public void setNetworkCode(String networkCode) {
        this.networkCode = networkCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public TmpProviderLoadError firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public TmpProviderLoadError lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBusinessName() {
        return businessName;
    }

    public TmpProviderLoadError businessName(String businessName) {
        this.businessName = businessName;
        return this;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getNpi() {
        return npi;
    }

    public TmpProviderLoadError npi(String npi) {
        this.npi = npi;
        return this;
    }

    public void setNpi(String npi) {
        this.npi = npi;
    }

    public String getTaxId() {
        return taxId;
    }

    public TmpProviderLoadError taxId(String taxId) {
        this.taxId = taxId;
        return this;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getProviderType() {
        return providerType;
    }

    public TmpProviderLoadError providerType(String providerType) {
        this.providerType = providerType;
        return this;
    }

    public void setProviderType(String providerType) {
        this.providerType = providerType;
    }

    public String getFullOrPartTime() {
        return fullOrPartTime;
    }

    public TmpProviderLoadError fullOrPartTime(String fullOrPartTime) {
        this.fullOrPartTime = fullOrPartTime;
        return this;
    }

    public void setFullOrPartTime(String fullOrPartTime) {
        this.fullOrPartTime = fullOrPartTime;
    }

    public String getInPersonAfterHours() {
        return inPersonAfterHours;
    }

    public TmpProviderLoadError inPersonAfterHours(String inPersonAfterHours) {
        this.inPersonAfterHours = inPersonAfterHours;
        return this;
    }

    public void setInPersonAfterHours(String inPersonAfterHours) {
        this.inPersonAfterHours = inPersonAfterHours;
    }

    public String getSupervisingSpecialist() {
        return supervisingSpecialist;
    }

    public TmpProviderLoadError supervisingSpecialist(String supervisingSpecialist) {
        this.supervisingSpecialist = supervisingSpecialist;
        return this;
    }

    public void setSupervisingSpecialist(String supervisingSpecialist) {
        this.supervisingSpecialist = supervisingSpecialist;
    }

    public String getSupervisingNpi() {
        return supervisingNpi;
    }

    public TmpProviderLoadError supervisingNpi(String supervisingNpi) {
        this.supervisingNpi = supervisingNpi;
        return this;
    }

    public void setSupervisingNpi(String supervisingNpi) {
        this.supervisingNpi = supervisingNpi;
    }

    public String getSpecializationCertificate() {
        return specializationCertificate;
    }

    public TmpProviderLoadError specializationCertificate(String specializationCertificate) {
        this.specializationCertificate = specializationCertificate;
        return this;
    }

    public void setSpecializationCertificate(String specializationCertificate) {
        this.specializationCertificate = specializationCertificate;
    }

    public String getHealthPlanProviderAddressId() {
        return healthPlanProviderAddressId;
    }

    public TmpProviderLoadError healthPlanProviderAddressId(String healthPlanProviderAddressId) {
        this.healthPlanProviderAddressId = healthPlanProviderAddressId;
        return this;
    }

    public void setHealthPlanProviderAddressId(String healthPlanProviderAddressId) {
        this.healthPlanProviderAddressId = healthPlanProviderAddressId;
    }

    public String getAddressType() {
        return addressType;
    }

    public TmpProviderLoadError addressType(String addressType) {
        this.addressType = addressType;
        return this;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getClinicName() {
        return clinicName;
    }

    public TmpProviderLoadError clinicName(String clinicName) {
        this.clinicName = clinicName;
        return this;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getAddress1() {
        return address1;
    }

    public TmpProviderLoadError address1(String address1) {
        this.address1 = address1;
        return this;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public TmpProviderLoadError address2(String address2) {
        this.address2 = address2;
        return this;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public TmpProviderLoadError address3(String address3) {
        this.address3 = address3;
        return this;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getCity() {
        return city;
    }

    public TmpProviderLoadError city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public TmpProviderLoadError state(String state) {
        this.state = state;
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public TmpProviderLoadError zip(String zip) {
        this.zip = zip;
        return this;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCounty() {
        return county;
    }

    public TmpProviderLoadError county(String county) {
        this.county = county;
        return this;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public TmpProviderLoadError phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public TmpProviderLoadError faxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
        return this;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getEmail() {
        return email;
    }

    public TmpProviderLoadError email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLatitude() {
        return latitude;
    }

    public TmpProviderLoadError latitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public TmpProviderLoadError longitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getTaxonomyCode() {
        return taxonomyCode;
    }

    public TmpProviderLoadError taxonomyCode(String taxonomyCode) {
        this.taxonomyCode = taxonomyCode;
        return this;
    }

    public void setTaxonomyCode(String taxonomyCode) {
        this.taxonomyCode = taxonomyCode;
    }

    public String getSpecialtyCode() {
        return specialtyCode;
    }

    public TmpProviderLoadError specialtyCode(String specialtyCode) {
        this.specialtyCode = specialtyCode;
        return this;
    }

    public void setSpecialtyCode(String specialtyCode) {
        this.specialtyCode = specialtyCode;
    }

    public String getPrimarySpecialty() {
        return primarySpecialty;
    }

    public TmpProviderLoadError primarySpecialty(String primarySpecialty) {
        this.primarySpecialty = primarySpecialty;
        return this;
    }

    public void setPrimarySpecialty(String primarySpecialty) {
        this.primarySpecialty = primarySpecialty;
    }

    public String getBoardCertified() {
        return boardCertified;
    }

    public TmpProviderLoadError boardCertified(String boardCertified) {
        this.boardCertified = boardCertified;
        return this;
    }

    public void setBoardCertified(String boardCertified) {
        this.boardCertified = boardCertified;
    }

    public String getBoardEligible() {
        return boardEligible;
    }

    public TmpProviderLoadError boardEligible(String boardEligible) {
        this.boardEligible = boardEligible;
        return this;
    }

    public void setBoardEligible(String boardEligible) {
        this.boardEligible = boardEligible;
    }

    public String getIsPcp() {
        return isPcp;
    }

    public TmpProviderLoadError isPcp(String isPcp) {
        this.isPcp = isPcp;
        return this;
    }

    public void setIsPcp(String isPcp) {
        this.isPcp = isPcp;
    }

    public String getAcceptingNewPatients() {
        return acceptingNewPatients;
    }

    public TmpProviderLoadError acceptingNewPatients(String acceptingNewPatients) {
        this.acceptingNewPatients = acceptingNewPatients;
        return this;
    }

    public void setAcceptingNewPatients(String acceptingNewPatients) {
        this.acceptingNewPatients = acceptingNewPatients;
    }

    public String getAcceptingNewReferrals() {
        return acceptingNewReferrals;
    }

    public TmpProviderLoadError acceptingNewReferrals(String acceptingNewReferrals) {
        this.acceptingNewReferrals = acceptingNewReferrals;
        return this;
    }

    public void setAcceptingNewReferrals(String acceptingNewReferrals) {
        this.acceptingNewReferrals = acceptingNewReferrals;
    }

    public String getNetworkTierId() {
        return networkTierId;
    }

    public TmpProviderLoadError networkTierId(String networkTierId) {
        this.networkTierId = networkTierId;
        return this;
    }

    public void setNetworkTierId(String networkTierId) {
        this.networkTierId = networkTierId;
    }

    public String getProviderGroupName() {
        return providerGroupName;
    }

    public TmpProviderLoadError providerGroupName(String providerGroupName) {
        this.providerGroupName = providerGroupName;
        return this;
    }

    public void setProviderGroupName(String providerGroupName) {
        this.providerGroupName = providerGroupName;
    }

    public String getOutpatientAppointmentAvailability() {
        return outpatientAppointmentAvailability;
    }

    public TmpProviderLoadError outpatientAppointmentAvailability(String outpatientAppointmentAvailability) {
        this.outpatientAppointmentAvailability = outpatientAppointmentAvailability;
        return this;
    }

    public void setOutpatientAppointmentAvailability(String outpatientAppointmentAvailability) {
        this.outpatientAppointmentAvailability = outpatientAppointmentAvailability;
    }

    public String getInDirectory() {
        return inDirectory;
    }

    public TmpProviderLoadError inDirectory(String inDirectory) {
        this.inDirectory = inDirectory;
        return this;
    }

    public void setInDirectory(String inDirectory) {
        this.inDirectory = inDirectory;
    }

    public String getContractEffectiveDate() {
        return contractEffectiveDate;
    }

    public TmpProviderLoadError contractEffectiveDate(String contractEffectiveDate) {
        this.contractEffectiveDate = contractEffectiveDate;
        return this;
    }

    public void setContractEffectiveDate(String contractEffectiveDate) {
        this.contractEffectiveDate = contractEffectiveDate;
    }

    public String getContractTerminationDate() {
        return contractTerminationDate;
    }

    public TmpProviderLoadError contractTerminationDate(String contractTerminationDate) {
        this.contractTerminationDate = contractTerminationDate;
        return this;
    }

    public void setContractTerminationDate(String contractTerminationDate) {
        this.contractTerminationDate = contractTerminationDate;
    }

    public String getProviderAssignedMembers() {
        return providerAssignedMembers;
    }

    public TmpProviderLoadError providerAssignedMembers(String providerAssignedMembers) {
        this.providerAssignedMembers = providerAssignedMembers;
        return this;
    }

    public void setProviderAssignedMembers(String providerAssignedMembers) {
        this.providerAssignedMembers = providerAssignedMembers;
    }

    public String getLocationAssignedMembers() {
        return locationAssignedMembers;
    }

    public TmpProviderLoadError locationAssignedMembers(String locationAssignedMembers) {
        this.locationAssignedMembers = locationAssignedMembers;
        return this;
    }

    public void setLocationAssignedMembers(String locationAssignedMembers) {
        this.locationAssignedMembers = locationAssignedMembers;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public TmpProviderLoadError createdAt(LocalDate createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getProcessedAt() {
        return processedAt;
    }

    public TmpProviderLoadError processedAt(LocalDate processedAt) {
        this.processedAt = processedAt;
        return this;
    }

    public void setProcessedAt(LocalDate processedAt) {
        this.processedAt = processedAt;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public TmpProviderLoadError recordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
        return this;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getLoadFileName() {
        return loadFileName;
    }

    public TmpProviderLoadError loadFileName(String loadFileName) {
        this.loadFileName = loadFileName;
        return this;
    }

    public void setLoadFileName(String loadFileName) {
        this.loadFileName = loadFileName;
    }

    public Long getProviderId() {
        return providerId;
    }

    public TmpProviderLoadError providerId(Long providerId) {
        this.providerId = providerId;
        return this;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public Long getProviderNetworkId() {
        return providerNetworkId;
    }

    public TmpProviderLoadError providerNetworkId(Long providerNetworkId) {
        this.providerNetworkId = providerNetworkId;
        return this;
    }

    public void setProviderNetworkId(Long providerNetworkId) {
        this.providerNetworkId = providerNetworkId;
    }

    public Long getProviderAddressId() {
        return providerAddressId;
    }

    public TmpProviderLoadError providerAddressId(Long providerAddressId) {
        this.providerAddressId = providerAddressId;
        return this;
    }

    public void setProviderAddressId(Long providerAddressId) {
        this.providerAddressId = providerAddressId;
    }

    public Long getProviderTaxonomyId() {
        return providerTaxonomyId;
    }

    public TmpProviderLoadError providerTaxonomyId(Long providerTaxonomyId) {
        this.providerTaxonomyId = providerTaxonomyId;
        return this;
    }

    public void setProviderTaxonomyId(Long providerTaxonomyId) {
        this.providerTaxonomyId = providerTaxonomyId;
    }

    public Long getNetworkId() {
        return networkId;
    }

    public TmpProviderLoadError networkId(Long networkId) {
        this.networkId = networkId;
        return this;
    }

    public void setNetworkId(Long networkId) {
        this.networkId = networkId;
    }

    public LocalDate getLoadErrorDate() {
        return loadErrorDate;
    }

    public TmpProviderLoadError loadErrorDate(LocalDate loadErrorDate) {
        this.loadErrorDate = loadErrorDate;
        return this;
    }

    public void setLoadErrorDate(LocalDate loadErrorDate) {
        this.loadErrorDate = loadErrorDate;
    }

    public String getLoadErrorStatus() {
        return loadErrorStatus;
    }

    public TmpProviderLoadError loadErrorStatus(String loadErrorStatus) {
        this.loadErrorStatus = loadErrorStatus;
        return this;
    }

    public void setLoadErrorStatus(String loadErrorStatus) {
        this.loadErrorStatus = loadErrorStatus;
    }

    public Long getTmpProviderLoadId() {
        return tmpProviderLoadId;
    }

    public TmpProviderLoadError tmpProviderLoadId(Long tmpProviderLoadId) {
        this.tmpProviderLoadId = tmpProviderLoadId;
        return this;
    }

    public void setTmpProviderLoadId(Long tmpProviderLoadId) {
        this.tmpProviderLoadId = tmpProviderLoadId;
    }

    public LoadErrorReason getLoadErrorReason() {
        return loadErrorReason;
    }

    public TmpProviderLoadError loadErrorReason(LoadErrorReason loadErrorReason) {
        this.loadErrorReason = loadErrorReason;
        return this;
    }

    public void setLoadErrorReason(LoadErrorReason loadErrorReason) {
        this.loadErrorReason = loadErrorReason;
    }

    public String getTenantHashKey() {
        return tenantHashKey;
    }

    public TmpProviderLoadError tenantHashKey(String tenantHashKey) {
        this.tenantHashKey = tenantHashKey;
        return this;
    }

    public void setTenantHashKey(String tenantHashKey) {
        this.tenantHashKey = tenantHashKey;
    }

    public String getCredential() {
        return credential;
    }

    public TmpProviderLoadError credential(String credential) {
        this.credential = credential;
        return this;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public String getGender() {
        return gender;
    }

    public TmpProviderLoadError gender(String gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSalutation() {
        return salutation;
    }

    public TmpProviderLoadError salutation(String salutation) {
        this.salutation = salutation;
        return this;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getMedicalGroupNpi() {
        return medicalGroupNpi;
    }

    public TmpProviderLoadError medicalGroupNpi(String medicalGroupNpi) {
        this.medicalGroupNpi = medicalGroupNpi;
        return this;
    }

    public void setMedicalGroupNpi(String medicalGroupNpi) {
        this.medicalGroupNpi = medicalGroupNpi;
    }

    public String getDataEntryEndpoint() {
        return dataEntryEndpoint;
    }

    public TmpProviderLoadError dataEntryEndpoint(String dataEntryEndpoint) {
        this.dataEntryEndpoint = dataEntryEndpoint;
        return this;
    }

    public void setDataEntryEndpoint(String dataEntryEndpoint) {
        this.dataEntryEndpoint = dataEntryEndpoint;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public TmpProviderLoadError sourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
        return this;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public Boolean getInNetwork() {
        return inNetwork;
    }

    public Boolean isInNetwork() {
        return inNetwork;
    }

    public TmpProviderLoadError inNetwork(Boolean inNetwork) {
        this.inNetwork = inNetwork;
        return this;
    }

    public void setInNetwork(Boolean inNetwork) {
        this.inNetwork = inNetwork;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public TmpProviderLoadError dateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getHealthPlanContractId() {
        return healthPlanContractId;
    }

    public TmpProviderLoadError healthPlanContractId(String healthPlanContractId) {
        this.healthPlanContractId = healthPlanContractId;
        return this;
    }

    public void setHealthPlanContractId(String healthPlanContractId) {
        this.healthPlanContractId = healthPlanContractId;
    }

    public String getGroupRelationshipType() {
        return groupRelationshipType;
    }

    public TmpProviderLoadError groupRelationshipType(String groupRelationshipType) {
        this.groupRelationshipType = groupRelationshipType;
        return this;
    }

    public void setGroupRelationshipType(String groupRelationshipType) {
        this.groupRelationshipType = groupRelationshipType;
    }

    public String getNetworkRecordStatus() {
        return networkRecordStatus;
    }

    public TmpProviderLoadError networkRecordStatus(String networkRecordStatus) {
        this.networkRecordStatus = networkRecordStatus;
        return this;
    }

    public void setNetworkRecordStatus(String networkRecordStatus) {
        this.networkRecordStatus = networkRecordStatus;
    }

    public String getPanelStatus() {
        return panelStatus;
    }

    public TmpProviderLoadError panelStatus(String panelStatus) {
        this.panelStatus = panelStatus;
        return this;
    }

    public void setPanelStatus(String panelStatus) {
        this.panelStatus = panelStatus;
    }

    public String getProviderEntityType() {
        return providerEntityType;
    }

    public TmpProviderLoadError providerEntityType(String providerEntityType) {
        this.providerEntityType = providerEntityType;
        return this;
    }

    public void setProviderEntityType(String providerEntityType) {
        this.providerEntityType = providerEntityType;
    }

    public String getCorporateClassification() {
        return corporateClassification;
    }

    public TmpProviderLoadError corporateClassification(String corporateClassification) {
        this.corporateClassification = corporateClassification;
        return this;
    }

    public void setCorporateClassification(String corporateClassification) {
        this.corporateClassification = corporateClassification;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TmpProviderLoadError)) {
            return false;
        }
        return id != null && id.equals(((TmpProviderLoadError) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TmpProviderLoadError{" +
                "id=" + getId() +
                ", healthPlanProviderId='" + getHealthPlanProviderId() + "'" +
                ", networkCode='" + getNetworkCode() + "'" +
                ", firstName='" + getFirstName() + "'" +
                ", lastName='" + getLastName() + "'" +
                ", businessName='" + getBusinessName() + "'" +
                ", npi='" + getNpi() + "'" +
                ", taxId='" + getTaxId() + "'" +
                ", providerType='" + getProviderType() + "'" +
                ", fullOrPartTime='" + getFullOrPartTime() + "'" +
                ", inPersonAfterHours='" + getInPersonAfterHours() + "'" +
                ", supervisingSpecialist='" + getSupervisingSpecialist() + "'" +
                ", supervisingNpi='" + getSupervisingNpi() + "'" +
                ", specializationCertificate='" + getSpecializationCertificate() + "'" +
                ", healthPlanProviderAddressId='" + getHealthPlanProviderAddressId() + "'" +
                ", addressType='" + getAddressType() + "'" +
                ", clinicName='" + getClinicName() + "'" +
                ", address1='" + getAddress1() + "'" +
                ", address2='" + getAddress2() + "'" +
                ", address3='" + getAddress3() + "'" +
                ", city='" + getCity() + "'" +
                ", state='" + getState() + "'" +
                ", zip='" + getZip() + "'" +
                ", county='" + getCounty() + "'" +
                ", phoneNumber='" + getPhoneNumber() + "'" +
                ", faxNumber='" + getFaxNumber() + "'" +
                ", email='" + getEmail() + "'" +
                ", latitude='" + getLatitude() + "'" +
                ", longitude='" + getLongitude() + "'" +
                ", taxonomyCode='" + getTaxonomyCode() + "'" +
                ", specialtyCode='" + getSpecialtyCode() + "'" +
                ", primarySpecialty='" + getPrimarySpecialty() + "'" +
                ", boardCertified='" + getBoardCertified() + "'" +
                ", boardEligible='" + getBoardEligible() + "'" +
                ", isPcp='" + getIsPcp() + "'" +
                ", acceptingNewPatients='" + getAcceptingNewPatients() + "'" +
                ", acceptingNewReferrals='" + getAcceptingNewReferrals() + "'" +
                ", networkTierId='" + getNetworkTierId() + "'" +
                ", providerGroupName='" + getProviderGroupName() + "'" +
                ", outpatientAppointmentAvailability='" + getOutpatientAppointmentAvailability() + "'" +
                ", inDirectory='" + getInDirectory() + "'" +
                ", contractEffectiveDate='" + getContractEffectiveDate() + "'" +
                ", contractTerminationDate='" + getContractTerminationDate() + "'" +
                ", providerAssignedMembers='" + getProviderAssignedMembers() + "'" +
                ", locationAssignedMembers='" + getLocationAssignedMembers() + "'" +
                ", createdAt='" + getCreatedAt() + "'" +
                ", processedAt='" + getProcessedAt() + "'" +
                ", recordStatus='" + getRecordStatus() + "'" +
                ", loadFileName='" + getLoadFileName() + "'" +
                ", providerId=" + getProviderId() +
                ", providerNetworkId=" + getProviderNetworkId() +
                ", providerAddressId=" + getProviderAddressId() +
                ", providerTaxonomyId=" + getProviderTaxonomyId() +
                ", networkId=" + getNetworkId() +
                ", credential=" + getCredential() +
                ", gender=" + getGender() +
                ", salutation=" + getSalutation() +
                ", medicalGroupNpi=" + getMedicalGroupNpi() +
                ", loadErrorDate='" + getLoadErrorDate() + "'" +
                ", loadErrorStatus='" + getLoadErrorStatus() + "'" +
                ", tmpProviderLoadId=" + getTmpProviderLoadId() +
                ", dataEntryEndpoint='" + getDataEntryEndpoint() + "'" +
                ", sourceSystem='" + getSourceSystem() + "'" +
                ", inNetwork='" + getInNetwork() + "'" +
                ", tenantHashKey='" + getTenantHashKey() +
                ", dateOfBirth='" + getDateOfBirth() + "'" +
                ", healthPlanContractId='" + getHealthPlanContractId() + "'" +
                ", groupRelationshipType='" + getGroupRelationshipType() + "'" +
                ", providerEntityType='" + getProviderEntityType() + "'" +
                "}";
    }
}
