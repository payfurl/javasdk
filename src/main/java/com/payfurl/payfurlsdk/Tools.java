package com.payfurl.payfurlsdk;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public class Tools {
    private static Set<String> zeroDecimalCurrencies;

    public static BigInteger getAmountForIntCurrencies(BigDecimal amount, String currency) {
        if (zeroDecimalCurrencies == null) {
            initCardTypes();
        }

        if (zeroDecimalCurrencies.contains(currency.toUpperCase())) {
            return amount.toBigInteger();
        }

        return amount.multiply(new BigDecimal(100)).toBigInteger();
    }

    public static BigDecimal getAmountForDecimalCurrencies(BigInteger amount, String currency) {
        if (zeroDecimalCurrencies == null) {
            initCardTypes();
        }

        if (zeroDecimalCurrencies.contains(currency.toUpperCase())) {
            return new BigDecimal(amount);
        }

        return new BigDecimal(amount).divide(new BigDecimal(100));
    }

    private static void initCardTypes() {
        //currencies taken from here: https://stripe.com/docs/currencies#zero-decimal on 20/01/2022
        zeroDecimalCurrencies = new HashSet<>();
        zeroDecimalCurrencies.add("BIF");
        zeroDecimalCurrencies.add("CLP");
        zeroDecimalCurrencies.add("DJF");
        zeroDecimalCurrencies.add("GNF");
        zeroDecimalCurrencies.add("JPY");
        zeroDecimalCurrencies.add("KMF");
        zeroDecimalCurrencies.add("KRW");
        zeroDecimalCurrencies.add("MGA");
        zeroDecimalCurrencies.add("PYG");
        zeroDecimalCurrencies.add("RWF");
        zeroDecimalCurrencies.add("UGX");
        zeroDecimalCurrencies.add("VND");
        zeroDecimalCurrencies.add("VUV");
        zeroDecimalCurrencies.add("XAF");
        zeroDecimalCurrencies.add("XOF");
        zeroDecimalCurrencies.add("XPF");
    }


}