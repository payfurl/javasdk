package com.payfurl.payfurlsdk.models.Batch;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BatchSummary {
    private final String batchId;
    private final int count;
    private final String description;
    private final String status;
    private final BigDecimal progress;
    private final LocalDateTime dateAdded;

    @JsonCreator
    public BatchSummary(@JsonProperty("BatchId") String batchId,
                        @JsonProperty("Count") int count,
                        @JsonProperty("Description") String description,
                        @JsonProperty("Status") String status,
                        @JsonProperty("Progress") BigDecimal progress,
                        @JsonProperty("DateAdded") LocalDateTime dateAdded) {

        this.batchId = batchId;
        this.count = count;
        this.description = description;
        this.status = status;
        this.progress = progress;
        this.dateAdded = dateAdded;
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

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    @Override
    public String toString() {
        return "BatchSummary{" +
                "batchId='" + batchId + '\'' +
                ", count=" + count +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", progress=" + progress +
                ", dateAdded=" + dateAdded +
                '}';
    }

    public static class Builder {
        private String batchId;
        private int count;
        private String description;
        private String status;
        private BigDecimal progress;
        private LocalDateTime dateAdded;

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

        public Builder withDateAdded(LocalDateTime dateAdded) {
            this.dateAdded = dateAdded;
            return this;
        }

        public BatchSummary build() {
            return new BatchSummary(batchId, count, description, status, progress, dateAdded);
        }
    }
}
