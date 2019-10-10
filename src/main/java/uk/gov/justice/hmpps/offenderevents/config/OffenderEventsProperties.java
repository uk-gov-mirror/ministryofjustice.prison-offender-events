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
     * Elite2 API Base URL endpoint ("http://localhost:8080")
     */
    private final String elite2ApiBaseUrl;

    /**
     * Elite2 API Base URL endpoint ("http://localhost:9090")
     */
    private final String custodyApiBaseUrl;


    /**
     * OAUTH2 API Rest URL endpoint ("http://localhost:9090/auth")
     */
    private final String oauthApiBaseUrl;

    private final String jwtPublicKey;

    public OffenderEventsProperties(@Value("${elite2.api.base.url}") @URL final String elite2ApiBaseUrl,
                                    @Value("${custody.api.base.url}") @URL final String custodyApiBaseUrl,
                                    @Value("${oauth.api.base.url}") @URL final String oauthApiBaseUrl,
                                    @Value("${jwt.public.key}") final String jwtPublicKey) {
        this.elite2ApiBaseUrl = elite2ApiBaseUrl;
        this.custodyApiBaseUrl = custodyApiBaseUrl;
        this.oauthApiBaseUrl = oauthApiBaseUrl;
        this.jwtPublicKey = jwtPublicKey;
    }
}
