package uk.gov.justice.hmpps.offenderevents.config;

import lombok.Getter;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@Validated
@Getter
public class OffenderEventsProperties {

    /**
     * Prison API Base URL endpoint ("http://localhost:8081")
     */
    private final String prisonApiBaseUrl;


    /**
     * OAUTH2 API Rest URL endpoint ("http://localhost:9090/auth")
     */
    private final String oauthApiBaseUrl;

    public OffenderEventsProperties(@Value("${prison.api.base.url}") @URL final String prisonApiBaseUrl,
                                    @Value("${oauth.api.base.url}") @URL final String oauthApiBaseUrl) {
        this.prisonApiBaseUrl = prisonApiBaseUrl;
        this.oauthApiBaseUrl = oauthApiBaseUrl;
    }
}
