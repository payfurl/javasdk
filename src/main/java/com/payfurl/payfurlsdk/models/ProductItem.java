package com.payfurl.payfurlsdk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class ProductItem {

    private final String productCode;
    private final String commodityCode;
    private final String description;
    private final BigDecimal quantity;
    private final String unitOfMeasure;
    private final BigDecimal amount;

    @JsonCreator
    public ProductItem(@JsonProperty("ProductCode") String productCode,
                       @JsonProperty("CommodityCode") String commodityCode,
                       @JsonProperty("Description") String description,
                       @JsonProperty("Quantity") BigDecimal quantity,
                       @JsonProperty("UnitOfMeasure") String unitOfMeasure,
                       @JsonProperty("Amount") BigDecimal amount) {


        this.productCode = productCode;
        this.commodityCode = commodityCode;
        this.description = description;
        this.quantity = quantity;
        this.unitOfMeasure = unitOfMeasure;
        this.amount = amount;
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

    public BigDecimal getQuantity() {
        return quantity;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "ProductItem{" +
                "productCode='" + productCode + '\'' +
                ", commodityCode='" + commodityCode + '\'' +
                ", description='" + description + '\'' +
                ", quantity='" + quantity + '\'' +
                ", unitOfMeasure='" + unitOfMeasure + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }

    public static class Builder {
        private String productCode;
        private String commodityCode;
        private String description;
        private BigDecimal quantity;
        private String unitOfMeasure;
        private BigDecimal amount;

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

        public Builder withQuantity(BigDecimal quantity) {
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

        public ProductItem createProductItem() {
            return new ProductItem(productCode, commodityCode, description, quantity, unitOfMeasure, amount);
        }
    }
}
