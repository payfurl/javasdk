package com.payfurl.payfurlsdk.models.Batch;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class BatchData {
    private final String batchId;
    private final int count;
    private final String description;
    private final String status;
    private final BigDecimal progress;
    private final String results;
    private final Date dateAdded;
    private final int success;
    private final int failure;
    private final Map<String, String> metadata;

    @JsonCreator
    public BatchData(@JsonProperty("BatchId") String batchId,
                     @JsonProperty("Count") int count,
                     @JsonProperty("Description") String description,
                     @JsonProperty("Status") String status,
                     @JsonProperty("Progress") BigDecimal progress,
                     @JsonProperty("Results") String results,
                     @JsonProperty("DateAdded") Date dateAdded,
                     @JsonProperty("Success") int success,
                     @JsonProperty("Failure") int failure,
                     @JsonProperty("Metadata") Map<String, String> metadata) {

        this.batchId = batchId;
        this.count = count;
        this.description = description;
        this.status = status;
        this.progress = progress;
        this.results = results;
        this.dateAdded = dateAdded;
        this.metadata = metadata;
        this.success = success;
        this.failure = failure;
    }

    public String getBatchId() {
        return batchId;
    }

    public int getCount() {
        return count;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public BigDecimal getProgress() {
        return progress;
    }

    public String getResults() {
        return results;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public int getSuccess() {
        return success;
    }

    public int getFailure() {
        return failure;
    }

    @Override
    public String toString() {
        return "BatchData{" +
                "batchId='" + batchId + '\'' +
                ", count=" + count +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", progress=" + progress +
                ", results='" + results + '\'' +
                ", dateAdded=" + dateAdded +
                ", success=" + success +
                ", failure=" + failure +
                ", metadata=" + metadata +
                '}';
    }

    public static class Builder {
        private String batchId;
        private int count;
        private String description;
        private String status;
        private BigDecimal progress;
        private String results;
        private Date dateAdded;
        private Map<String, String> metadata;
        private int success;
        private int failure;

        public Builder withBatchId(String batchId) {
            this.batchId = batchId;
            return this;
        }

        public Builder withCount(int count) {
            this.count = count;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder withProgress(BigDecimal progress) {
            this.progress = progress;
            return this;
        }

        public Builder withResults(String results) {
            this.results = results;
            return this;
        }

        public Builder withDateAdded(Date dateAdded) {
            this.dateAdded = dateAdded;
            return this;
        }

        public Builder withSuccess(int success) {
            this.success = success;
            return this;
        }

        public Builder withFailure(int failure) {
            this.failure = failure;
            return this;
        }

        public Builder withMetadata(Map<String, String> metadata) {
            this.metadata = metadata;
            return this;
        }

        public BatchData build() {
            return new BatchData(batchId, count, description, status, progress, results, dateAdded, success, failure, metadata);
        }
    }
}
