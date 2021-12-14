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
        Long start = System.nanoTime();
        Long duration = 0l;
        jdbcTemplate.execute("SELECT provider_load_01_insert_new_providers()");
        jdbcTemplate.execute("SELECT provider_load_02_get_provider_ids()");
        jdbcTemplate.execute("SELECT provider_load_03_update_providers()");
        duration = (System.nanoTime() - start) / 1000000;
        start = System.nanoTime();
        log.info("Update providers: {} ms", duration);
        jdbcTemplate.execute("SELECT provider_load_04_insert_new_provider_addresses()");
        duration = (System.nanoTime() - start) / 1000000;
        start = System.nanoTime();
        log.info("Insert new addresses: {} ms", duration);
        jdbcTemplate.execute("SELECT provider_load_05_get_provider_address_ids()");
        duration = (System.nanoTime() - start) / 1000000;
        start = System.nanoTime();
        log.info("Get Provider Address IDs: {} ms", duration);
        jdbcTemplate.execute("SELECT provider_load_06_update_provider_addresses()");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            log.info("Sleep failed.");
        }
        duration = (System.nanoTime() - start) / 1000000;
        start = System.nanoTime();
        log.info("Update provider addresses: {} ms", duration);
        jdbcTemplate.execute("SELECT provider_load_06_update_provider_addresses_2()");
        duration = (System.nanoTime() - start) / 1000000;
        start = System.nanoTime();
        log.info("Update provider addresses 2: {} ms", duration);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            log.info("Sleep failed.");
        }
        jdbcTemplate.execute("SELECT provider_load_07_update_provider_address_latitude_longitude()");
        jdbcTemplate.execute("SELECT provider_load_08_insert_new_provider_taxonomies()");
        jdbcTemplate.execute("SELECT provider_load_09_get_provider_taxonomy_ids()");
        jdbcTemplate.execute("SELECT provider_load_10_update_provider_taxonomies()");
        jdbcTemplate.execute("SELECT provider_load_11_get_network_ids()");
        jdbcTemplate.execute("SELECT provider_load_12_insert_new_provider_networks()");
        jdbcTemplate.execute("SELECT provider_load_13_get_provider_network_ids()");
        jdbcTemplate.execute("SELECT provider_load_14_update_provider_networks()");
        jdbcTemplate.execute("SELECT provider_load_15_activate_providers()");
        jdbcTemplate.execute("SELECT provider_load_16_activate_provider_addresses()");
        jdbcTemplate.execute("SELECT provider_load_17_activate_provider_taxonomies()");
        jdbcTemplate.execute("SELECT provider_load_18_activate_provider_networks()");

        if (loadType.equalsIgnoreCase("FULL")) {
            jdbcTemplate.execute("SELECT provider_load_19_deactivate_provider_networks()");
            jdbcTemplate.execute("SELECT provider_load_20_deactivate_provider_taxonomies()");
            jdbcTemplate.execute("SELECT provider_load_21_deactivate_provider_addresses()");
            jdbcTemplate.execute("SELECT provider_load_22_deactivate_providers()");
        }

        jdbcTemplate.execute("SELECT provider_load_23_update_provider_assigned_members()");
        jdbcTemplate.execute("SELECT provider_load_24_update_provider_group_relationship()");

        // If full load, close out open alerts that do not re-present in this load
        if (loadType.equalsIgnoreCase("FULL")) {
            Integer i = 0;
            i = jdbcTemplate.queryForObject("SELECT provider_load_996_1_close_resolved_provider_alerts(?)", new Object[] {tenantHashKey},
                    (rs, row) -> rs.getInt(1));
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                log.info("Sleep failed.");
            }
            log.info("Closed {} PROVIDER alerts that are now resolved", i);
            i = jdbcTemplate.queryForObject("SELECT provider_load_996_2_close_resolved_provider_address_alerts(?)", new Object[] {tenantHashKey},
                    (rs, row) -> rs.getInt(1));
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                log.info("Sleep failed.");
            }
            log.info("Closed {} PROVIDER ADDRESS alerts that are now resolved", i);
            i = jdbcTemplate.queryForObject("SELECT provider_load_996_3_close_resolved_provider_network_alerts(?)", new Object[] {tenantHashKey},
                    (rs, row) -> rs.getInt(1));
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                log.info("Sleep failed.");
            }
            log.info("Closed {} PROVIDER NETWORK alerts that are now resolved", i);
            i = jdbcTemplate.queryForObject("SELECT provider_load_996_4_close_resolved_provider_taxonomy_alerts(?)", new Object[] {tenantHashKey},
                    (rs, row) -> rs.getInt(1));
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                log.info("Sleep failed.");
            }
            log.info("Closed {} PROVIDER TAXONOMY alerts that are now resolved", i);
        }
        start = System.nanoTime();
        jdbcTemplate.execute("SELECT provider_load_997_npi_rule_scan_queue()");
        duration = (System.nanoTime() - start) / 1000000;
        start = System.nanoTime();
        log.info("NPI Rule Scan: {} ms", duration);
        jdbcTemplate.execute("SELECT provider_load_997_1_usps_rule_scan_queue()");
        duration = (System.nanoTime() - start) / 1000000;
        start = System.nanoTime();
        log.info("USPS Rule Scan: {} ms", duration);
        jdbcTemplate.execute("SELECT provider_load_998_create_alerts()");
        duration = (System.nanoTime() - start) / 1000000;
        start = System.nanoTime();
        log.info("Create Alerts: {} ms", duration);
        jdbcTemplate.execute("SELECT provider_load_998_2_create_group_rel_alert()");
        duration = (System.nanoTime() - start) / 1000000;
        start = System.nanoTime();
        log.info("Create Group Relationship Alerts: {} ms", duration);
        jdbcTemplate.execute("SELECT provider_load_998_3_create_duplicate_npi_alert()");
        duration = (System.nanoTime() - start) / 1000000;
        start = System.nanoTime();
        log.info("Create Duplicate NPI Alert: {} ms", duration);
        jdbcTemplate.execute("SELECT provider_load_998_4_create_duplicate_taxid_alert()");
        duration = (System.nanoTime() - start) / 1000000;
        start = System.nanoTime();
        log.info("Create duplicate Tax ID alert: {} ms", duration);
        jdbcTemplate.execute("SELECT provider_load_998_5_recommend_city_state_zip()");
        duration = (System.nanoTime() - start) / 1000000;
        start = System.nanoTime();
        log.info("Recommend city/state/zip: {} ms", duration);

        jdbcTemplate.execute("SELECT provider_load_999_post_load()");
        duration = (System.nanoTime() - start) / 1000000;
        start = System.nanoTime();
        log.info("Post Load: {} ms", duration);

        return RepeatStatus.FINISHED;
    }
}
