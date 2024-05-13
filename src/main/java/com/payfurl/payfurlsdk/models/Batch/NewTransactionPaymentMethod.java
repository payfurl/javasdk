package com.payfurl.payfurlsdk.models.Batch;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NewTransactionPaymentMethod {
    private final int count;
    private final String description;
    private final String batch;

    @JsonCreator
    public NewTransactionPaymentMethod(@JsonProperty("Count") int count,
                                       @JsonProperty("Description") String description,
                                       @JsonProperty("Batch") String batch) {

        this.count = count;
        this.description = description;
        this.batch = batch;
    }

    public int getCount() {
        return count;
    }

    public String getDescription() {
        return description;
    }

    public String getBatch() {
        return batch;
    }

    @Override
    public String toString() {
        return "NewTransactionPaymentMethod{" +
                "count=" + count +
                ", description='" + description + '\'' +
                ", batch='" + batch + '\'' +
                '}';
    }

    public static class Builder {
        private int count;
        private String description;
        private String batch;

        public Builder withCount(int count) {
            this.count = count;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withBatch(String batch) {
            this.batch = batch;
            return this;
        }

        public NewTransactionPaymentMethod build() {
            return new NewTransactionPaymentMethod(count, description, batch);
        }
    }
}
