package pt.ulisboa.forward.ewp.api.client.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import pt.ulisboa.forward.ewp.api.client.config.ClientConfiguration;

public class JwtRequestInterceptor implements RequestInterceptor {

  public static final String HEADER_AUTHORIZATION = "Authorization";
  public static final String HEADER_AUTHORIZATION_BEARER_PREFIX = "Bearer ";

  @Override
  public void apply(RequestTemplate requestTemplate) {
    ClientConfiguration configuration = ClientConfiguration.getInstance();
    String jwtToken =
        JWT.create()
            .withIssuer(configuration.getHostCode())
            .sign(Algorithm.HMAC256(configuration.getSecret()));
    requestTemplate.header(HEADER_AUTHORIZATION, HEADER_AUTHORIZATION_BEARER_PREFIX + jwtToken);
  }
}
