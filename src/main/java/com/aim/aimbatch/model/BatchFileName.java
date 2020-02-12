package com.aim.aimbatch.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BatchFileName {
    private final Logger log = LoggerFactory.getLogger(BatchFileName.class);

    private static final List<String> VALID_LOAD_TYPES = Collections.unmodifiableList(Arrays.asList("FULL", "INCREMENTAL"));

    private static final List<String> VALID_FILE_TYPES = Collections.unmodifiableList(Arrays.asList("PROVIDER"));

    private String fileName;

    private String healthPlanBatchCode;

    private String dateStamp;

    private String loadType;

    private String fileType;

    public BatchFileName(String fileName) {
        this.fileName = fileName.toUpperCase();
        parseFileName();
    }

    private void parseFileName() {
        String[] fileNameNoExt = fileName.split("\\.");
        if (fileNameNoExt.length < 2) {
            log.error("Invalid file name identified : {}", fileName);
            throw new RuntimeException("File name is invalid. Must be formatted as [BATCHCODE]_[DATESEGMENT]_[LOADTYPE]_[FILETYPE].csv");
        }

        String[] fileSegments = fileNameNoExt[0].split("_");
        if (fileSegments.length < 4) {
            log.error("Invalid file name identified (missing elements) : {}", fileName);
            throw new RuntimeException("File name is invalid. Must be formatted as [BATCHCODE]_[DATESTAMP]_[LOADTYPE]_[FILETYPE].csv");
        }

        healthPlanBatchCode = fileSegments[0];
        dateStamp = fileSegments[1];

        loadType = fileSegments[2];
        if (!BatchFileName.VALID_LOAD_TYPES.contains(loadType)) {
            log.error("Invalid load type identified : {}", loadType);
            throw new RuntimeException("Specified load type is invalid.");
        }

        fileType = fileSegments[3];
        if (!BatchFileName.VALID_FILE_TYPES.contains(fileType)) {
            log.error("Invalid file type identified : {}", fileType);
            throw new RuntimeException("Specified file type is invalid.");
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getHealthPlanBatchCode() {
        return healthPlanBatchCode;
    }

    public void setHealthPlanBatchCode(String healthPlanBatchCode) {
        this.healthPlanBatchCode = healthPlanBatchCode;
    }

    public String getDateStamp() {
        return dateStamp;
    }

    public void setDateStamp(String dateStamp) {
        this.dateStamp = dateStamp;
    }

    public String getLoadType() {
        return loadType;
    }

    public void setLoadType(String loadType) {
        this.loadType = loadType;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return "BatchFileName{" +
                "fileName='" + fileName + '\'' +
                ", healthPlanBatchCode='" + healthPlanBatchCode + '\'' +
                ", dateStamp='" + dateStamp + '\'' +
                ", loadType='" + loadType + '\'' +
                ", fileType='" + fileType + '\'' +
                '}';
    }
}
