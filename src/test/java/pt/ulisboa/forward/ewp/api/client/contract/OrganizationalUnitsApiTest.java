package pt.ulisboa.forward.ewp.api.client.contract;

import static org.assertj.core.api.Assertions.assertThat;

import eu.erasmuswithoutpaper.api.ounits.OunitsResponse;
import eu.erasmuswithoutpaper.api.ounits.OunitsResponse.Ounit;
import feign.mock.HttpMethod;
import feign.mock.MockClient;
import feign.mock.MockTarget;
import java.util.Arrays;
import javax.xml.bind.JAXBException;
import org.junit.jupiter.api.Test;
import pt.ulisboa.forward.ewp.api.client.AbstractTest;
import pt.ulisboa.forward.ewp.api.client.config.ClientConfiguration;
import pt.ulisboa.forward.ewp.api.client.dto.OrganizationalUnitsApiSpecificationResponseDTO;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;
import pt.ulisboa.forward.ewp.api.client.factory.ApiClientFactory;

class OrganizationalUnitsApiTest extends AbstractTest {

  @Test
  public void testGetApiSpecification_ValidRequest_Success() throws JAXBException {
    String heiId = "demo";

    OrganizationalUnitsApiSpecificationResponseDTO organizationalUnitsApiSpecificationResponseDTO =
        new OrganizationalUnitsApiSpecificationResponseDTO(1, 2);
    ResponseWithDataDto<OrganizationalUnitsApiSpecificationResponseDTO> responseBody =
        ResponseWithDataDto.createWithoutMessages(organizationalUnitsApiSpecificationResponseDTO);

    MockClient mockClient =
        new MockClient()
            .ok(
                HttpMethod.GET,
                "/api/forward/ewp/ounits/specification?hei_id=" + heiId,
                marshallToXml(responseBody));

    ClientConfiguration.configure("", "", "secret");
    OrganizationalUnitsApi client =
        ApiClientFactory.createClient(mockClient, new MockTarget<>(OrganizationalUnitsApi.class));

    ResponseWithDataDto<OrganizationalUnitsApiSpecificationResponseDTO> response =
        client.getApiSpecification(heiId);
    assertThat(response).isNotNull();
    assertThat(response.getDataObject()).isNotNull();
    assertThat(response.getDataObject().getMaxOunitIds()).isEqualTo(1);
    assertThat(response.getDataObject().getMaxOunitCodes()).isEqualTo(2);
  }

  @Test
  public void testGetMaxOunitsIdsPerRequest_ValidRequest_Success() throws JAXBException {
    String heiId = "demo";

    OrganizationalUnitsApiSpecificationResponseDTO organizationalUnitsApiSpecificationResponseDTO =
        new OrganizationalUnitsApiSpecificationResponseDTO(1, 2);
    ResponseWithDataDto<OrganizationalUnitsApiSpecificationResponseDTO> responseBody =
        ResponseWithDataDto.createWithoutMessages(organizationalUnitsApiSpecificationResponseDTO);

    MockClient mockClient =
        new MockClient()
            .ok(
                HttpMethod.GET,
                "/api/forward/ewp/ounits/specification?hei_id=" + heiId,
                marshallToXml(responseBody));

    ClientConfiguration.configure("", "", "secret");
    OrganizationalUnitsApi client =
        ApiClientFactory.createClient(mockClient, new MockTarget<>(OrganizationalUnitsApi.class));

    assertThat(client.getMaxOunitIdsPerRequest(heiId)).isEqualTo(1);
  }

  @Test
  public void testGetMaxOunitCodesPerRequest_ValidRequest_Success() throws JAXBException {
    String heiId = "demo";

    OrganizationalUnitsApiSpecificationResponseDTO organizationalUnitsApiSpecificationResponseDTO =
        new OrganizationalUnitsApiSpecificationResponseDTO(1, 2);
    ResponseWithDataDto<OrganizationalUnitsApiSpecificationResponseDTO> responseBody =
        ResponseWithDataDto.createWithoutMessages(organizationalUnitsApiSpecificationResponseDTO);

    MockClient mockClient =
        new MockClient()
            .ok(
                HttpMethod.GET,
                "/api/forward/ewp/ounits/specification?hei_id=" + heiId,
                marshallToXml(responseBody));

    ClientConfiguration.configure("", "", "secret");
    OrganizationalUnitsApi client =
        ApiClientFactory.createClient(mockClient, new MockTarget<>(OrganizationalUnitsApi.class));

    assertThat(client.getMaxOunitCodesPerRequest(heiId)).isEqualTo(2);
  }

  @Test
  public void testFindByIds_ValidRequest_Success() throws JAXBException {
    String heiId = "demo";
    String ounitId1 = "abc";
    String ounitId2 = "def";

    OunitsResponse ounitsResponse = new OunitsResponse();
    Ounit ounit1 = new OunitsResponse.Ounit();
    ounit1.setOunitId(ounitId1);
    ounitsResponse.getOunit().add(ounit1);
    Ounit ounit2 = new OunitsResponse.Ounit();
    ounit2.setOunitId(ounitId2);
    ounitsResponse.getOunit().add(ounit2);
    ResponseWithDataDto<OunitsResponse> responseBody =
        ResponseWithDataDto.createWithoutMessages(ounitsResponse);

    MockClient mockClient =
        new MockClient()
            .ok(HttpMethod.POST, "/api/forward/ewp/ounits", marshallToXml(responseBody));

    ClientConfiguration.configure("", "", "secret");
    OrganizationalUnitsApi client =
        ApiClientFactory.createClient(mockClient, new MockTarget<>(OrganizationalUnitsApi.class));

    ResponseWithDataDto<OunitsResponse> response =
        client.findByIds(heiId, Arrays.asList(ounitId1, ounitId2));
    assertThat(response).isNotNull();
    assertThat(response.getDataObject()).isNotNull();
    assertThat(response.getDataObject().getOunit()).hasSize(2);
    assertThat(response.getDataObject().getOunit().get(0).getOunitId()).isEqualTo(ounitId1);
    assertThat(response.getDataObject().getOunit().get(1).getOunitId()).isEqualTo(ounitId2);
  }

  @Test
  public void testFindByCodes_ValidRequest_Success() throws JAXBException {
    String heiId = "demo";
    String ounitCode1 = "abc";
    String ounitCode2 = "def";

    OunitsResponse ounitsResponse = new OunitsResponse();
    Ounit ounit1 = new OunitsResponse.Ounit();
    ounit1.setOunitCode(ounitCode1);
    ounitsResponse.getOunit().add(ounit1);
    Ounit ounit2 = new OunitsResponse.Ounit();
    ounit2.setOunitCode(ounitCode2);
    ounitsResponse.getOunit().add(ounit2);
    ResponseWithDataDto<OunitsResponse> responseBody =
        ResponseWithDataDto.createWithoutMessages(ounitsResponse);

    MockClient mockClient =
        new MockClient()
            .ok(HttpMethod.POST, "/api/forward/ewp/ounits", marshallToXml(responseBody));

    ClientConfiguration.configure("", "", "secret");
    OrganizationalUnitsApi client =
        ApiClientFactory.createClient(mockClient, new MockTarget<>(OrganizationalUnitsApi.class));

    ResponseWithDataDto<OunitsResponse> response =
        client.findByIds(heiId, Arrays.asList(ounitCode1, ounitCode2));
    assertThat(response).isNotNull();
    assertThat(response.getDataObject()).isNotNull();
    assertThat(response.getDataObject().getOunit()).hasSize(2);
    assertThat(response.getDataObject().getOunit().get(0).getOunitCode()).isEqualTo(ounitCode1);
    assertThat(response.getDataObject().getOunit().get(1).getOunitCode()).isEqualTo(ounitCode2);
  }
}
