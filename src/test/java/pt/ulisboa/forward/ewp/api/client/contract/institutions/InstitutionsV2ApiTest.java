package pt.ulisboa.forward.ewp.api.client.contract.institutions;

import static org.assertj.core.api.Assertions.assertThat;

import eu.erasmuswithoutpaper.api.institutions.v2.InstitutionsResponseV2;
import eu.erasmuswithoutpaper.api.institutions.v2.InstitutionsResponseV2.Hei;
import feign.mock.HttpMethod;
import feign.mock.MockClient;
import feign.mock.MockTarget;
import javax.xml.bind.JAXBException;
import org.junit.jupiter.api.Test;
import pt.ulisboa.forward.ewp.api.client.AbstractTest;
import pt.ulisboa.forward.ewp.api.client.config.ClientConfiguration;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;
import pt.ulisboa.forward.ewp.api.client.factory.ApiClientFactory;

class InstitutionsV2ApiTest extends AbstractTest {

  @Test
  public void testFindByHeiId_ValidRequest_Success() throws JAXBException {
    String heiId = "demo";
    InstitutionsResponseV2 institutionsResponse = new InstitutionsResponseV2();
    Hei hei = new Hei();
    hei.setHeiId(heiId);
    institutionsResponse.getHei().add(hei);
    ResponseWithDataDto<InstitutionsResponseV2> responseBody =
        ResponseWithDataDto.createWithoutMessages(institutionsResponse);

    MockClient mockClient =
        new MockClient()
            .ok(
                HttpMethod.GET,
                "/api/forward/ewp/institutions/v2?hei_id=" + heiId,
                marshallToXml(responseBody));

    ClientConfiguration.configure("", "", "secret");
    InstitutionsV2Api client =
        ApiClientFactory.createClient(mockClient, new MockTarget<>(InstitutionsV2Api.class));

    ResponseWithDataDto<InstitutionsResponseV2> response = client.findByHeiId(heiId);
    assertThat(response).isNotNull();
    assertThat(response.getDataObject()).isNotNull();
    assertThat(response.getDataObject().getHei()).hasSize(1);
    assertThat(response.getDataObject().getHei().get(0).getHeiId()).isEqualTo(heiId);
  }
}
