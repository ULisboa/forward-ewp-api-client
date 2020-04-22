package pt.ulisboa.forward.ewp.api.client.contract;

import static org.assertj.core.api.Assertions.assertThat;

import eu.erasmuswithoutpaper.api.institutions.InstitutionsResponse;
import eu.erasmuswithoutpaper.api.institutions.InstitutionsResponse.Hei;
import feign.mock.HttpMethod;
import feign.mock.MockClient;
import feign.mock.MockTarget;
import javax.xml.bind.JAXBException;
import org.junit.jupiter.api.Test;
import pt.ulisboa.forward.ewp.api.client.AbstractTest;
import pt.ulisboa.forward.ewp.api.client.config.ClientConfiguration;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;
import pt.ulisboa.forward.ewp.api.client.factory.ApiClientFactory;

class InstitutionsApiTest extends AbstractTest {

  @Test
  public void testFindByHeiId_ValidRequest_Success() throws JAXBException {
    String heiId = "demo";
    InstitutionsResponse institutionsResponse = new InstitutionsResponse();
    Hei hei = new Hei();
    hei.setHeiId(heiId);
    institutionsResponse.getHei().add(hei);
    ResponseWithDataDto<InstitutionsResponse> responseBody =
        ResponseWithDataDto.createWithoutMessages(institutionsResponse);

    MockClient mockClient =
        new MockClient()
            .ok(
                HttpMethod.GET,
                "/rest/forward/ewp/institutions?hei_id=" + heiId,
                marshallToXml(responseBody));

    ClientConfiguration.configure("", "", "secret");
    InstitutionsApi client =
        ApiClientFactory.createClient(mockClient, new MockTarget<>(InstitutionsApi.class));

    ResponseWithDataDto<InstitutionsResponse> response = client.findByHeiId(heiId);
    assertThat(response).isNotNull();
    assertThat(response.getDataObject()).isNotNull();
    assertThat(response.getDataObject().getHei()).hasSize(1);
    assertThat(response.getDataObject().getHei().get(0).getHeiId()).isEqualTo(heiId);
  }
}
