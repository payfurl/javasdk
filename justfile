# payfurl/javasdk. `just` lists the recipes.

# Compile and the unit tests, as CI runs them (offline: nothing calls an API)
test:
    mvn -B -ntp verify

# Build the jar with a version, as the release workflow does, into target/
package version="0.0.0-SNAPSHOT":
    mvn -B -ntp package -DskipTests -Drevision={{version}}

# The integration tests, in payfurl/platform, against this checkout and an API built from source (Docker); args go to Maven
e2e *args:
    #!/usr/bin/env bash
    set -euo pipefail
    sdk=$(pwd)
    platform=${PLATFORM_DIR:-}
    if [ -z "$platform" ]; then
        for candidate in ../platform ../../platform; do
            if [ -f "$candidate/e2e/api-tests/javasdk/docker-compose.yml" ]; then platform=$candidate; break; fi
        done
    fi
    [ -n "$platform" ] && [ -f "$platform/e2e/api-tests/javasdk/docker-compose.yml" ] \
        || { echo "no payfurl/platform checkout at ../platform or ../../platform: set PLATFORM_DIR" >&2; exit 1; }
    cd "$platform" && JAVASDK_DIR="$sdk" just e2e javasdk {{args}}
