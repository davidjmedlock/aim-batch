package com.aim.aimbatch.config.tasklets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.jdbc.core.JdbcTemplate;

public class ProviderScrubTasklet implements Tasklet {
    private static final Logger log = LoggerFactory.getLogger(ProviderScrubTasklet.class);

    private JdbcTemplate jdbcTemplate;
    private String fileName;
    private String tenantHashKey;

    public ProviderScrubTasklet(JdbcTemplate jdbcTemplate, String fileName, String tenantHashKey) {
        this.jdbcTemplate = jdbcTemplate;
        this.fileName = fileName;
        this.tenantHashKey = tenantHashKey;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        Integer i = jdbcTemplate.queryForObject("SELECT provider_load_00_init(?, ?)", new Object[] {fileName, tenantHashKey},
                (rs, row) -> rs.getInt(1));
        log.info("Updated {} rows with file name and tenant hash key: {} : {}", i, fileName, tenantHashKey);

        /* This will just run all the functions we need to run sequentially */
        Long start = System.nanoTime();
        Long duration = 0l;
        /* Data Scrub Routines */
        jdbcTemplate.execute("SELECT provider_scrub_01_health_plan_provider_id()");
        jdbcTemplate.execute("SELECT provider_scrub_02_missing_network_code()");
        jdbcTemplate.execute("SELECT provider_scrub_03_missing_first_name()");
        jdbcTemplate.execute("SELECT provider_scrub_04_missing_last_name()");
        jdbcTemplate.execute("SELECT provider_scrub_05_missing_taxonomy_specialty()");
        jdbcTemplate.execute("SELECT provider_scrub_06_bad_network_code()");
        jdbcTemplate.execute("SELECT provider_scrub_07_mismatched_taxonomy_specialty()");
        jdbcTemplate.execute("SELECT provider_scrub_08_missing_provider_type()");
        jdbcTemplate.execute("SELECT provider_scrub_09_invalid_provider_type()");
        jdbcTemplate.execute("SELECT provider_scrub_10_missing_physician_npi()");
        jdbcTemplate.execute("SELECT provider_scrub_11_missing_ft_pt()");
        jdbcTemplate.execute("SELECT provider_scrub_12_invalid_ft_pt()");
        jdbcTemplate.execute("SELECT provider_scrub_13_invalid_in_person_after_hours()");
        jdbcTemplate.execute("SELECT provider_scrub_14_missing_supervising_specialist_non_physician()");
        jdbcTemplate.execute("SELECT provider_scrub_15_missing_supervising_npi_non_physician()");
        jdbcTemplate.execute("SELECT provider_scrub_16_missing_health_plan_provider_address_id()");
        jdbcTemplate.execute("SELECT provider_scrub_17_missing_address_1()");
        jdbcTemplate.execute("SELECT provider_scrub_18_missing_city()");
        jdbcTemplate.execute("SELECT provider_scrub_19_missing_state()");
        jdbcTemplate.execute("SELECT provider_scrub_20_missing_zip_code()");
        jdbcTemplate.execute("SELECT provider_scrub_21_missing_phone_number()");
        jdbcTemplate.execute("SELECT provider_scrub_22_missing_address_type()");
        jdbcTemplate.execute("SELECT provider_scrub_23_invalid_address_type()");
        jdbcTemplate.execute("SELECT provider_scrub_24_missing_county()");
        jdbcTemplate.execute("SELECT provider_scrub_25_invalid_city_state_zip_county()");
        jdbcTemplate.execute("SELECT provider_scrub_26_invalid_primary_specialty()");
        jdbcTemplate.execute("SELECT provider_scrub_27_invalid_board_certified()");
        jdbcTemplate.execute("SELECT provider_scrub_28_invalid_board_eligible()");
        jdbcTemplate.execute("SELECT provider_scrub_29_invalid_is_pcp()");
        jdbcTemplate.execute("SELECT provider_scrub_30_invalid_accepting_new_patients()");
        jdbcTemplate.execute("SELECT provider_scrub_31_invalid_accepting_new_referrals()");
        jdbcTemplate.execute("SELECT provider_scrub_32_invalid_outpatient_appt_availability()");
        jdbcTemplate.execute("SELECT provider_scrub_33_invalid_in_directory()");
        jdbcTemplate.execute("SELECT provider_scrub_34_missing_contract_effective_date()");
        jdbcTemplate.execute("SELECT provider_scrub_35_invalid_contract_effective_date()");
        jdbcTemplate.execute("SELECT provider_scrub_36_invalid_contract_termination_date()");
        jdbcTemplate.execute("SELECT provider_scrub_37_invalid_provider_assigned_members()");
        jdbcTemplate.execute("SELECT provider_scrub_38_invalid_location_assigned_members()");
        jdbcTemplate.execute("SELECT provider_scrub_39_invalid_credential()");
        jdbcTemplate.execute("SELECT provider_scrub_40_invalid_gender()");
        jdbcTemplate.execute("SELECT provider_scrub_42_invalid_salutation()");
        jdbcTemplate.execute("SELECT provider_scrub_43_in_network_expired_contract()");
        jdbcTemplate.execute("SELECT provider_scrub_44_contract_expired_members_assigned()");
        jdbcTemplate.execute("SELECT provider_scrub_45_oon_members_assigned()");
        jdbcTemplate.execute("SELECT provider_scrub_46_expired_contract()");
        jdbcTemplate.execute("SELECT provider_scrub_47_expired_contract_accepting_new_members()");
        jdbcTemplate.execute("SELECT provider_scrub_48_members_assigned_non_pcp()");
        jdbcTemplate.execute("SELECT provider_scrub_49_non_pcp_accepting_new_members()");
        jdbcTemplate.execute("SELECT provider_scrub_50_invalid_npi_format()");
        jdbcTemplate.execute("SELECT provider_scrub_51_missing_name()");
        jdbcTemplate.execute("SELECT provider_scrub_54_invalid_city_state_zip()");
        jdbcTemplate.execute("SELECT provider_scrub_55_invalid_phone_number()");
        jdbcTemplate.execute("SELECT provider_scrub_56_invalid_area_code()");
        jdbcTemplate.execute("SELECT provider_scrub_57_invalid_date_of_birth()");
        jdbcTemplate.execute("SELECT provider_scrub_58_invalid_tax_id_format()");

        /* Post Scrub Routines */
        jdbcTemplate.execute("SELECT provider_scrub_998_post_scrub()");
        jdbcTemplate.execute("SELECT provider_scrub_999_flag_error_records()");
        duration = (System.nanoTime() - start) / 1000000;
        start = System.nanoTime();
        log.info("Scrub Flag Error Records: {} ms", duration);

        return RepeatStatus.FINISHED;
    }
}
