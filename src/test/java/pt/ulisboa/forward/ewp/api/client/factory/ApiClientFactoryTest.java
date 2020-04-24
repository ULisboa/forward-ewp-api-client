package pt.ulisboa.forward.ewp.api.client.factory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ulisboa.forward.ewp.api.client.config.ClientConfiguration;
import pt.ulisboa.forward.ewp.api.client.contract.InstitutionsApi;

class ApiClientFactoryTest {

  @BeforeEach
  public void beforeEach() {
    ClientConfiguration.reset();
  }

  @Test
  void testCreateClient_NoConfiguration_Exception() {
    assertThatThrownBy(() -> ApiClientFactory.createClient(InstitutionsApi.class))
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("Client configuration must be set before creating a client");
  }

  @Test
  void testCreateClient_ValidConfiguration_CreatedSuccessfully() {
    ClientConfiguration.configure("https://example.com", "host-code", "secret");
    InstitutionsApi client = ApiClientFactory.createClient(InstitutionsApi.class);
    assertThat(client).isNotNull();
  }
}
