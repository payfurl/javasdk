package com.payfurl.payfurlsdk.models;

import java.util.Date;

/**
 * The query of {@code GET /settlements} (SettlementController.GetSettlements): settlements from {@code startDate}
 * up to {@code endDate} (now when null). The API reads both as dates ({@code yyyy-MM-dd}): the time of day is ignored.
 */
public class SettlementSearch {
    private final Date startDate;
    private final Date endDate;

    private SettlementSearch(Builder builder) {
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
    }

    /** Required. */
    public Date getStartDate() {
        return startDate;
    }

    /** Optional: null means now. */
    public Date getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return "SettlementSearch{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    public static class Builder {
        private Date startDate;
        private Date endDate;

        /** Required. */
        public Builder withStartDate(Date startDate) {
            this.startDate = startDate;
            return this;
        }

        /** Optional: null means now. */
        public Builder withEndDate(Date endDate) {
            this.endDate = endDate;
            return this;
        }

        public SettlementSearch build() {
            return new SettlementSearch(this);
        }
    }
}
