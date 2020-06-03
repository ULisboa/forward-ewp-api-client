package pt.ulisboa.forward.ewp.api.client.contract;

import static org.assertj.core.api.Assertions.assertThat;

import feign.mock.HttpMethod;
import feign.mock.MockClient;
import feign.mock.MockTarget;
import javax.xml.bind.JAXBException;
import org.junit.jupiter.api.Test;
import pt.ulisboa.forward.ewp.api.client.AbstractTest;
import pt.ulisboa.forward.ewp.api.client.config.ClientConfiguration;
import pt.ulisboa.forward.ewp.api.client.dto.AuthenticationTestResponseDto;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;
import pt.ulisboa.forward.ewp.api.client.factory.ApiClientFactory;

class AuthenticationApiTest extends AbstractTest {

  @Test
  public void testAuthenticationTest_ValidRequest_Success() throws JAXBException {
    String hostCode = "demo";
    AuthenticationTestResponseDto authenticationTestResponseDto =
        new AuthenticationTestResponseDto();
    authenticationTestResponseDto.setHostCode(hostCode);
    ResponseWithDataDto<AuthenticationTestResponseDto> responseBody =
        ResponseWithDataDto.createWithoutMessages(authenticationTestResponseDto);

    MockClient mockClient =
        new MockClient()
            .ok(
                HttpMethod.GET,
                "/api/forward/ewp/authentication/test",
                marshallToXml(responseBody));

    ClientConfiguration.configure("", hostCode, "secret");
    AuthenticationApi client =
        ApiClientFactory.createClient(mockClient, new MockTarget<>(AuthenticationApi.class));

    ResponseWithDataDto<AuthenticationTestResponseDto> response = client.test();
    assertThat(response).isNotNull();
    assertThat(response.getDataObject()).isNotNull();
    assertThat(response.getDataObject().getHostCode()).isEqualTo(hostCode);
  }
}
