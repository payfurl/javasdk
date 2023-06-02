package com.payfurl.payfurlsdk.http.client.config;

import java.util.Objects;

public class EnvironmentConfigKey {
    private final Region region;
    private final Environment environment;

    private EnvironmentConfigKey(Region region, Environment environment) {
        this.region = region;
        this.environment = environment;
    }

    public static EnvironmentConfigKey of(Region region, Environment environment) {
        return new EnvironmentConfigKey(region, environment);
    }

    public Region getRegion() {
        return region;
    }

    public Environment getEnvironment() {
        return environment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EnvironmentConfigKey that = (EnvironmentConfigKey) o;
        return region == that.region
                && environment == that.environment;
    }

    @Override
    public int hashCode() {
        return Objects.hash(region, environment);
    }
}
