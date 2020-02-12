package com.aim.aimbatch;

import com.aim.aimbatch.model.BatchFileName;
import com.aim.aimbatch.model.TenantKey;
import com.aim.aimbatch.repository.TenantKeyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

@SpringBootApplication
public class AimBatchApplication implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(AimBatchApplication.class);

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	@Qualifier("importTmpProviderLoadJob")
	Job job;

	@Autowired
	TenantKeyRepository tenantKeyRepository;

	public static void main(String[] args) {
		SpringApplication.run(AimBatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (args.length < 1) {
			throw new RuntimeException("No input file provided.");
		}

		File file = new File(args[0]);
		if (!file.exists()) {
			throw new FileNotFoundException("The input file provided was not found.");
		}

		BatchFileName batchFileName = new BatchFileName(file.getName());
		String tenantHashKey = tenantKeyRepository.findByBatchCode(batchFileName.getHealthPlanBatchCode())
				.orElseThrow(NoSuchElementException::new).getTenantHashKey();

		JobParameters params = new JobParametersBuilder()
				.addString("pathToFile", file.getAbsolutePath())
				.addString("loadType", batchFileName.getLoadType())
				.addString("healthPlanBatchCode", batchFileName.getHealthPlanBatchCode())
				.addString("tenantHashKey", tenantHashKey)
				.addLong("executionTime", System.currentTimeMillis())
				.toJobParameters();

		JobExecution je = jobLauncher.run(job, params);
		while (je.isRunning()) {
			log.info("Waiting for the job to complete.");
		}
		if (je.getStatus() == BatchStatus.COMPLETED) {
			log.info("Job {} is done. Now you can start the next job(s).", job.getName());
		}
	}
}
