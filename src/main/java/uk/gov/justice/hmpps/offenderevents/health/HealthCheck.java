package uk.gov.justice.hmpps.offenderevents.health;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

import static lombok.AccessLevel.PROTECTED;


@AllArgsConstructor(access = PROTECTED)
@Slf4j
public abstract class HealthCheck implements HealthIndicator {
    private final WebClient webClient;
    private final String baseUri;
    private final Duration timeout;

    @Override
    public Health health() {
        try {
            final var uri = baseUri + "/health/ping";
            final var response = webClient.get()
                    .uri(uri)
                    .exchange()
                    .block(timeout);
            return Health.up().withDetail("HttpStatus", response.statusCode()).build();
        } catch (final Exception e) {
            return Health.down(e).build();
        }
    }
}
