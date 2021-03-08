package com.aim.aimbatch.config;

import com.aim.aimbatch.config.tasklets.ProviderLoadResetTasklet;
import com.aim.aimbatch.config.tasklets.ProviderLoadTasklet;
import com.aim.aimbatch.config.tasklets.ProviderScrubTasklet;
import com.aim.aimbatch.model.TmpProviderLoad;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class ProviderLoadConfiguration {
    private static final Logger log = LoggerFactory.getLogger(ProviderLoadConfiguration.class);

    private static final String OVERRIDDEN_BY_EXPRESSION = null;

    private static final String[] NAMES = {"health_plan_provider_id", "network_code", "first_name", "last_name", "business_name",
                                            "npi", "tax_id", "provider_type", "provider_assigned_members", "full_or_part_time",
                                            "in_person_after_hours", "supervising_specialist", "supervising_npi", "specialization_certificate",
                                            "health_plan_provider_address_id", "address_type", "clinic_name", "address_1", "address_2",
                                            "address_3", "city", "state", "zip", "county", "phone_number", "fax_number", "email",
                                            "latitude", "longitude", "location_assigned_members", "taxonomy_code", "specialty_code",
                                            "primarySpecialty", "board_certified", "board_eligible", "is_pcp", "accepting_new_patients",
                                            "accepting_new_referrals", "network_tier_id", "provider_group_name", "outpatient_appointment_availability",
                                            "in_directory", "contract_effective_date", "contract_termination_date", "credential", "gender",
                                            "salutation", "medical_group_npi", "in_network", "source_system"};

    private static final String SQL = "INSERT INTO tmp_provider_load (health_plan_provider_id, network_code, first_name, last_name, business_name, " +
            "npi, tax_id, provider_type, provider_assigned_members, full_or_part_time, in_person_after_hours, supervising_specialist, supervising_npi, " +
            "specialization_certificate, health_plan_provider_address_id, address_type, clinic_name, address_1, address_2, address_3, city, state, zip, county, " +
            "phone_number, fax_number, email, latitude, longitude, location_assigned_members, taxonomy_code, specialty_code, primary_specialty, board_certified, " +
            "board_eligible, is_pcp, accepting_new_patients, accepting_new_referrals, network_tier_id, provider_group_name, outpatient_appointment_availability, " +
            "in_directory, contract_effective_date, contract_termination_date, credential, gender, salutation, medical_group_npi, in_network, source_system) " +
            "VALUES (:healthPlanProviderId, :networkCode, :firstName, :lastName, :businessName, :npi, :taxId, " +
            ":providerType, :providerAssignedMembers, :fullOrPartTime, :inPersonAfterHours, :supervisingSpecialist, :supervisingNpi, :specializationCertificate, " +
            ":healthPlanProviderAddressId, :addressType, :clinicName, :address1, :address2, :address3, :city, :state, :zip, :county, :phoneNumber, :faxNumber," +
            ":email, :latitude, :longitude, :locationAssignedMembers, :taxonomyCode, :specialtyCode, :primarySpecialty, :boardCertified, :boardEligible, :isPcp, " +
            ":acceptingNewPatients, :acceptingNewReferrals, :networkTierId, :providerGroupName, :outpatientAppointmentAvailability, :inDirectory, :contractEffectiveDate, " +
            ":contractTerminationDate, :credential, :gender, :salutation, :medicalGroupNpi, :inNetwork, :sourceSystem)";

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Bean
    @StepScope
    public FlatFileItemReader<TmpProviderLoad> reader(@Value("#{jobParameters[pathToFile]}") String pathToFile) {
        log.info("Building FlatFileItemReader for file: {}", pathToFile);
        FlatFileItemReader<TmpProviderLoad> reader = new FlatFileItemReaderBuilder<TmpProviderLoad>()
                .name("tmpProviderLoadReader")
                .resource(new FileSystemResource(pathToFile))
                .delimited()
                .delimiter("|")
                .names(ProviderLoadConfiguration.NAMES)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<TmpProviderLoad>() {{
                    setTargetType(TmpProviderLoad.class);
                }})
                .build();
        reader.setLinesToSkip(1);
        return reader;
    }

    @Bean
    public JdbcBatchItemWriter<TmpProviderLoad> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<TmpProviderLoad>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql(ProviderLoadConfiguration.SQL)
                .dataSource(dataSource)
                .build();
    }

    // TODO: Add tenantHashKey as a parameter here, pass it in from the call to the step, and initialize it in the tasklet; use it to filter the delete
    @Bean
    @StepScope
    public ProviderLoadResetTasklet providerLoadResetTasklet(@Value("#{jobParameters[tenantHashKey]}") String tenantHashKey) {
        return new ProviderLoadResetTasklet(this.jdbcTemplate, tenantHashKey);
    }

    @Bean
    @StepScope
    public ProviderScrubTasklet providerScrubTasklet(
            @Value("#{jobParameters[pathToFile]}") String pathToFile,
            @Value("#{jobParameters[tenantHashKey]}") String tenantHashKey) {
        log.info("Building Provider Scrub Tasklet for file: {}", pathToFile);
        return new ProviderScrubTasklet(this.jdbcTemplate, pathToFile, tenantHashKey);
    }

    @Bean
    @StepScope
    public ProviderLoadTasklet providerLoadTasklet(@Value("#{jobParameters[loadType]}") String loadType) {
        return new ProviderLoadTasklet(this.jdbcTemplate, loadType);
    }

    @Bean
    public Job importTmpProviderLoadJob(JobCompletionNotificationListener listener, Step loadProviderFile) {
        return jobBuilderFactory.get("importTmpProviderLoadJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .start(providerLoadReset())
                .next(loadProviderFile)
                .next(providerScrub())
                .next(providerLoad())
                .build();
    }

    @Bean
    public Step loadProviderFile(JdbcBatchItemWriter<TmpProviderLoad> writer) {
        return stepBuilderFactory.get("loadProviderFile")
                .<TmpProviderLoad, TmpProviderLoad> chunk(10)
                .reader(reader(ProviderLoadConfiguration.OVERRIDDEN_BY_EXPRESSION))
                .writer(writer)
                .build();
    }

    @Bean
    public Step providerLoadReset() {
        return stepBuilderFactory.get("providerLoadReset")
                .tasklet(providerLoadResetTasklet(ProviderLoadConfiguration.OVERRIDDEN_BY_EXPRESSION)).build();
    }

    @Bean
    public Step providerScrub() {
        return stepBuilderFactory.get("providerScrub")
                .tasklet(providerScrubTasklet(ProviderLoadConfiguration.OVERRIDDEN_BY_EXPRESSION, ProviderLoadConfiguration.OVERRIDDEN_BY_EXPRESSION)).build();
    }

    @Bean
    public Step providerLoad() {
        return stepBuilderFactory.get("providerLoad")
                .tasklet(providerLoadTasklet(ProviderLoadConfiguration.OVERRIDDEN_BY_EXPRESSION)).build();
    }
}
