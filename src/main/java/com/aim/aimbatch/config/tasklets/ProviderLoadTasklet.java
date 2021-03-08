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

    public ProviderLoadTasklet(JdbcTemplate jdbcTemplate, String loadType) {
        this.jdbcTemplate = jdbcTemplate;
        this.loadType = loadType;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        jdbcTemplate.execute("SELECT provider_load_01_insert_new_providers()");
        jdbcTemplate.execute("SELECT provider_load_02_get_provider_ids()");
        jdbcTemplate.execute("SELECT provider_load_03_update_providers()");
        jdbcTemplate.execute("SELECT provider_load_04_insert_new_provider_addresses()");
        jdbcTemplate.execute("SELECT provider_load_05_get_provider_address_ids()");
        jdbcTemplate.execute("SELECT provider_load_06_update_provider_addresses()");
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
        jdbcTemplate.execute("SELECT provider_load_998_create_alerts()");
        jdbcTemplate.execute("SELECT provider_load_999_post_load()");

        return RepeatStatus.FINISHED;
    }
}
