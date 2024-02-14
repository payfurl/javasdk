package com.payfurl.payfurlsdk.models;

import java.util.List;

public class Info {
    private List<Country> countries;
    private List<CurrencyInformationData> currencies;
    private List<String> timezones;

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public List<CurrencyInformationData> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<CurrencyInformationData> currencies) {
        this.currencies = currencies;
    }

    public List<String> getTimezones() {
        return timezones;
    }

    public void setTimezones(List<String> timezones) {
        this.timezones = timezones;
    }

    public static class Country {
        private String friendlyName;
        private String name;
        private String twoLetterCode;
        private String threeLetterCode;
        private String numericCode;

        public String getFriendlyName() {
            return friendlyName;
        }

        public void setFriendlyName(String friendlyName) {
            this.friendlyName = friendlyName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTwoLetterCode() {
            return twoLetterCode;
        }

        public void setTwoLetterCode(String twoLetterCode) {
            this.twoLetterCode = twoLetterCode;
        }

        public String getThreeLetterCode() {
            return threeLetterCode;
        }

        public void setThreeLetterCode(String threeLetterCode) {
            this.threeLetterCode = threeLetterCode;
        }

        public String getNumericCode() {
            return numericCode;
        }

        public void setNumericCode(String numericCode) {
            this.numericCode = numericCode;
        }
    }
}
