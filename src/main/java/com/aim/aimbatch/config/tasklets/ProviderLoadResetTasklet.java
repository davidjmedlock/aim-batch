package com.aim.aimbatch.config.tasklets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;

public class ProviderLoadResetTasklet implements Tasklet {
    private static final Logger log = LoggerFactory.getLogger(ProviderLoadResetTasklet.class);

    private JdbcTemplate jdbcTemplate;

    public ProviderLoadResetTasklet(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("Resetting the Provider Load and Provider Load Error tables.");
        jdbcTemplate.execute("DELETE FROM tmp_provider_load_error");
        jdbcTemplate.execute("DELETE FROM tmp_provider_load");

        return RepeatStatus.FINISHED;
    }
}
