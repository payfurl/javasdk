package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class ProductItem {

    private final String productCode;
    private final String commodityCode;
    private final String description;
    private final Integer quantity;
    private final String unitOfMeasure;
    private final BigDecimal amount;
    private final BigDecimal taxAmount;

    @JsonCreator
    public ProductItem(@JsonProperty("ProductCode") String productCode,
                       @JsonProperty("CommodityCode") String commodityCode,
                       @JsonProperty("Description") String description,
                       @JsonProperty("Quantity") Integer quantity,
                       @JsonProperty("UnitOfMeasure") String unitOfMeasure,
                       @JsonProperty("Amount") BigDecimal amount,
                       @JsonProperty("TaxAmount") BigDecimal taxAmount) {


        this.productCode = productCode;
        this.commodityCode = commodityCode;
        this.description = description;
        this.quantity = quantity == null ? Integer.valueOf(0) : quantity;
        this.unitOfMeasure = unitOfMeasure;
        this.amount = amount == null ? BigDecimal.valueOf(0) : amount;
        this.taxAmount = taxAmount == null ? BigDecimal.valueOf(0) : taxAmount;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public String getDescription() {
        return description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getUnitOfMeasure() { return unitOfMeasure; }

    public BigDecimal getAmount() {
        return amount;
    }
    public BigDecimal getTaxAmount() { return taxAmount; }

    @Override
    public String toString() {
        return "ProductItem{" +
                "productCode='" + productCode + '\'' +
                ", commodityCode='" + commodityCode + '\'' +
                ", description='" + description + '\'' +
                ", quantity='" + quantity + '\'' +
                ", unitOfMeasure='" + unitOfMeasure + '\'' +
                ", amount='" + amount + '\'' +
                ", taxAmount='" + taxAmount + '\'' +
                '}';
    }

    public static class Builder {
        private String productCode;
        private String commodityCode;
        private String description;
        private Integer quantity;
        private String unitOfMeasure;
        private BigDecimal amount;
        private BigDecimal taxAmount;

        public Builder withProductCode(String productCode) {
            this.productCode = productCode;
            return this;
        }

        public Builder withCommodityCode(String commodityCode) {
            this.commodityCode = commodityCode;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withQuantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder withUnitOfMeasure(String unitOfMeasure) {
            this.unitOfMeasure = unitOfMeasure;
            return this;
        }

        public Builder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder withTaxAmount(BigDecimal taxAmount) {
            this.taxAmount = taxAmount;
            return this;
        }

        public ProductItem build() {
            return new ProductItem(productCode, commodityCode, description, quantity, unitOfMeasure, amount, taxAmount);
        }
    }
}
