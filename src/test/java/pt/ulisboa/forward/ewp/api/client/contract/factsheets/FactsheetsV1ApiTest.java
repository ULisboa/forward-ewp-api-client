package pt.ulisboa.forward.ewp.api.client.contract.factsheets;

import static org.assertj.core.api.Assertions.assertThat;

import eu.erasmuswithoutpaper.api.factsheet.v1.FactsheetResponseV1;
import eu.erasmuswithoutpaper.api.factsheet.v1.FactsheetResponseV1.Factsheet;
import feign.mock.HttpMethod;
import feign.mock.MockClient;
import feign.mock.MockTarget;
import javax.xml.bind.JAXBException;
import org.junit.jupiter.api.Test;
import pt.ulisboa.forward.ewp.api.client.AbstractTest;
import pt.ulisboa.forward.ewp.api.client.config.ClientConfiguration;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;
import pt.ulisboa.forward.ewp.api.client.factory.ApiClientFactory;

class FactsheetsV1ApiTest extends AbstractTest {

  @Test
  void testFindByHeiId_ValidRequest_Success() throws JAXBException {
    String heiId = "demo";
    FactsheetResponseV1 factsheetResponse = new FactsheetResponseV1();
    Factsheet factsheet = new Factsheet();
    factsheet.setHeiId(heiId);
    factsheetResponse.getFactsheet().add(factsheet);
    ResponseWithDataDto<FactsheetResponseV1> responseBody =
        ResponseWithDataDto.createWithoutMessages(factsheetResponse);

    MockClient mockClient =
        new MockClient()
            .ok(
                HttpMethod.GET,
                "/api/forward/ewp/factsheets/v1?hei_id=" + heiId,
                marshallToXml(responseBody));

    ClientConfiguration.configure("", "", "secret");
    FactsheetsV1Api client =
        ApiClientFactory.createClient(mockClient, new MockTarget<>(FactsheetsV1Api.class));

    ResponseWithDataDto<FactsheetResponseV1> response = client.findByHeiId(heiId);
    assertThat(response).isNotNull();
    assertThat(response.getDataObject()).isNotNull();
    assertThat(response.getDataObject().getFactsheet()).hasSize(1);
    assertThat(response.getDataObject().getFactsheet().get(0).getHeiId()).isEqualTo(heiId);
  }
}
