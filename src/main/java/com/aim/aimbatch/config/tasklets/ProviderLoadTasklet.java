package com.aim.aimbatch.config.tasklets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.jdbc.core.JdbcTemplate;

public class ProviderLoadTasklet implements Tasklet {
    private static final Logger log = LoggerFactory.getLogger(ProviderLoadTasklet.class);

    private JdbcTemplate jdbcTemplate;
    private String loadType;
    private String tenantHashKey;

    public ProviderLoadTasklet(JdbcTemplate jdbcTemplate, String loadType, String tenantHashKey) {
        this.jdbcTemplate = jdbcTemplate;
        this.loadType = loadType;
        this.tenantHashKey = tenantHashKey;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        jdbcTemplate.execute("SELECT provider_load_01_insert_new_providers()");
        log.info("provider_load_01_insert_new_providers complete");
        jdbcTemplate.execute("SELECT provider_load_02_get_provider_ids()");
        log.info("provider_load_02_get_provider_ids complete");
        jdbcTemplate.execute("SELECT provider_load_03_update_providers()");
        log.info("provider_load_03_update_providers complete");
        jdbcTemplate.execute("SELECT provider_load_04_insert_new_provider_addresses()");
        log.info("provider_load_04_insert_new_provider_addresses complete");
        jdbcTemplate.execute("SELECT provider_load_05_get_provider_address_ids()");
        log.info("provider_load_05_get_provider_address_ids complete");
        jdbcTemplate.execute("SELECT provider_load_06_update_provider_addresses()");
        log.info("provider_load_06_update_provider_addresses complete");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            log.info("Sleep failed.");
        }
        jdbcTemplate.execute("SELECT provider_load_06_update_provider_addresses_2()");
        log.info("provider_load_06_update_provider_addresses_2 complete");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            log.info("Sleep failed.");
        }
        jdbcTemplate.execute("SELECT provider_load_07_update_provider_address_latitude_longitude()");
        log.info("provider_load_07_update_provider_address_latitude_longitude complete");
        jdbcTemplate.execute("SELECT provider_load_08_A_reset_original_taxonomies()");
        log.info("provider_load_08_A_reset_original_taxonomies complete");
        jdbcTemplate.execute("SELECT provider_load_08_insert_new_provider_taxonomies()");
        log.info("provider_load_08_insert_new_provider_taxonomies complete");
        jdbcTemplate.execute("SELECT provider_load_09_get_provider_taxonomy_ids()");
        log.info("provider_load_09_get_provider_taxonomy_ids complete");
        jdbcTemplate.execute("SELECT provider_load_10_update_provider_taxonomies()");
        log.info("provider_load_10_update_provider_taxonomies complete");
        jdbcTemplate.execute("SELECT provider_load_11_get_network_ids()");
        log.info("provider_load_11_get_network_ids complete");
        jdbcTemplate.execute("SELECT provider_load_12_insert_new_provider_networks()");
        log.info("provider_load_12_insert_new_provider_networks complete");
        jdbcTemplate.execute("SELECT provider_load_13_get_provider_network_ids()");
        log.info("provider_load_13_get_provider_network_ids complete");
        jdbcTemplate.execute("SELECT provider_load_14_update_provider_networks()");
        log.info("provider_load_14_update_provider_networks complete");
        jdbcTemplate.execute("SELECT provider_load_15_activate_providers()");
        log.info("provider_load_15_activate_providers complete");
        jdbcTemplate.execute("SELECT provider_load_16_activate_provider_addresses()");
        log.info("provider_load_16_activate_provider_addresses complete");
        jdbcTemplate.execute("SELECT provider_load_17_activate_provider_taxonomies()");
        log.info("provider_load_17_activate_provider_taxonomies complete");
        jdbcTemplate.execute("SELECT provider_load_18_activate_provider_networks()");
        log.info("provider_load_18_activate_provider_networks complete");

        if (loadType.equalsIgnoreCase("FULL")) {
            jdbcTemplate.execute("SELECT provider_load_19_deactivate_provider_networks()");
            jdbcTemplate.execute("SELECT provider_load_20_deactivate_provider_taxonomies()");
            jdbcTemplate.execute("SELECT provider_load_21_deactivate_provider_addresses()");
            jdbcTemplate.execute("SELECT provider_load_22_deactivate_providers()");
        }

        jdbcTemplate.execute("SELECT provider_load_23_update_provider_assigned_members()");
        log.info("provider_load_23_update_provider_assigned_members complete");
        jdbcTemplate.execute("SELECT provider_load_24_update_provider_group_relationship()");
        log.info("provider_load_24_update_provider_group_relationship complete");
        jdbcTemplate.execute("SELECT provider_load_25_set_provider_in_network()");
        log.info("provider_load_25_set_provider_in_network complete");
        jdbcTemplate.execute("SELECT provider_load_27_update_contract_header()");
        log.info("provider_load_27_update_contract_header complete");

        jdbcTemplate.execute("SELECT provider_load_997_npi_rule_scan_queue()");
        log.info("provider_load_997_npi_rule_scan_queue complete");
        jdbcTemplate.execute("SELECT provider_load_997_1_usps_rule_scan_queue()");
        log.info("provider_load_997_1_usps_rule_scan_queue complete");
        jdbcTemplate.execute("SELECT provider_load_997_2_validation_rule_scan_queue()");
        log.info("provider_load_997_2_validation_rule_scan_queue complete");

        jdbcTemplate.execute("SELECT provider_load_999_post_load()");
        log.info("provider_load_999_post_load complete");
        return RepeatStatus.FINISHED;
    }
}
