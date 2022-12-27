package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public class Order {
    private final String orderNumber;
    private final BigDecimal freightAmount;
    private final BigDecimal dutyAmount;
    private final List<ProductItem> items;

    @JsonCreator
    public Order(@JsonProperty("OrderNumber") String orderNumber,
                 @JsonProperty("FreightAmount") BigDecimal freightAmount,
                 @JsonProperty("DutyAmount") BigDecimal dutyAmount,
                 @JsonProperty("Items") List<ProductItem> items) {

        this.orderNumber = orderNumber;
        this.freightAmount = freightAmount;
        this.dutyAmount = dutyAmount;
        this.items = items;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public BigDecimal getFreightAmount() {
        return freightAmount;
    }

    public BigDecimal getDutyAmount() {
        return dutyAmount;
    }

    public List<ProductItem> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNumber='" + orderNumber + '\'' +
                ", freightAmount='" + freightAmount + '\'' +
                ", dutyAmount='" + dutyAmount + '\'' +
                ", items='" + items + '\'' +
                '}';
    }

    public static class Builder {
        private String orderNumber;
        private BigDecimal freightAmount;
        private BigDecimal dutyAmount;
        private List<ProductItem> items;

        public Builder withOrderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
            return this;
        }

        public Builder withFreightAmount(BigDecimal freightAmount) {
            this.freightAmount = freightAmount;
            return this;
        }

        public Builder withDutyAmount(BigDecimal dutyAmount) {
            this.dutyAmount = dutyAmount;
            return this;
        }

        public Builder withItems(List<ProductItem> items) {
            this.items = items;
            return this;
        }

        public Order build() {
            return new Order(orderNumber, freightAmount, dutyAmount, items);
        }
    }
}
