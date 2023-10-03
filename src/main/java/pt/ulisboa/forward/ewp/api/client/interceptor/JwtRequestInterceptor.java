package pt.ulisboa.forward.ewp.api.client.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAmount;
import java.util.Date;
import pt.ulisboa.forward.ewp.api.client.config.ClientConfiguration;

public class JwtRequestInterceptor implements RequestInterceptor {

  public static final String HEADER_AUTHORIZATION = "Authorization";
  public static final String HEADER_AUTHORIZATION_BEARER_PREFIX = "Bearer ";

  private static final TemporalAmount TOKEN_EXPIRATION_OFFSET = Duration.ofSeconds(30L);

  @Override
  public void apply(RequestTemplate requestTemplate) {
    ClientConfiguration configuration = ClientConfiguration.getInstance();
    Date expiresAt = Date.from(ZonedDateTime.now().plus(TOKEN_EXPIRATION_OFFSET).toInstant());
    String jwtToken =
        JWT.create()
            .withIssuer(configuration.getClientId())
            .withIssuedAt(new Date())
            .withExpiresAt(expiresAt)
            .withClaim("client", "forward-ewp-api-client")
            .sign(Algorithm.HMAC256(configuration.getSecret()));
    requestTemplate.header(HEADER_AUTHORIZATION, HEADER_AUTHORIZATION_BEARER_PREFIX + jwtToken);
  }
}
