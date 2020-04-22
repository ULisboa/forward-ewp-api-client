package pt.ulisboa.forward.ewp.api.client.interceptor;

import static org.assertj.core.api.Assertions.assertThat;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import feign.Feign;
import feign.Request;
import feign.RequestLine;
import feign.mock.HttpMethod;
import feign.mock.MockClient;
import feign.mock.MockTarget;
import org.junit.jupiter.api.Test;
import pt.ulisboa.forward.ewp.api.client.config.ClientConfiguration;

class JwtRequestInterceptorTest {

  @Test
  public void testRequestInterceptor_ValidAuthorizationHeader() {
    ClientConfiguration.configure("", "host-code", "secret");
    ClientConfiguration configuration = ClientConfiguration.getInstance();

    MockClient mockClient = new MockClient();
    JwtRequestInterceptorApiTest target =
        Feign.builder()
            .client(mockClient.noContent(HttpMethod.GET, "/test"))
            .requestInterceptor(new JwtRequestInterceptor())
            .target(new MockTarget<>(JwtRequestInterceptorApiTest.class));
    target.test();

    Request request = mockClient.verifyOne(HttpMethod.GET, "/test");
    assertThat(request.headers().get(JwtRequestInterceptor.HEADER_AUTHORIZATION)).isNotEmpty();
    String authorizationHeader =
        request.headers().get(JwtRequestInterceptor.HEADER_AUTHORIZATION).iterator().next();
    assertThat(authorizationHeader).isNotEmpty();
    assertThat(authorizationHeader)
        .startsWith(JwtRequestInterceptor.HEADER_AUTHORIZATION_BEARER_PREFIX);
    String jwtToken =
        authorizationHeader.substring(
            JwtRequestInterceptor.HEADER_AUTHORIZATION_BEARER_PREFIX.length());
    assertThat(jwtToken).isNotEmpty();
    JWT.require(Algorithm.HMAC256(configuration.getSecret()))
        .withIssuer(configuration.getHostCode())
        .build()
        .verify(jwtToken);
  }

  private interface JwtRequestInterceptorApiTest {
    @RequestLine("GET /test")
    void test();
  }
}
