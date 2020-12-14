package pt.ulisboa.forward.ewp.api.client.contract.courses;

import static org.assertj.core.api.Assertions.assertThat;

import eu.erasmuswithoutpaper.api.courses.v0.CoursesResponseV0;
import eu.erasmuswithoutpaper.api.courses.v0.CoursesResponseV0.LearningOpportunitySpecification;
import feign.mock.HttpMethod;
import feign.mock.MockClient;
import feign.mock.MockTarget;
import java.util.Arrays;
import javax.xml.bind.JAXBException;
import org.junit.jupiter.api.Test;
import pt.ulisboa.forward.ewp.api.client.AbstractTest;
import pt.ulisboa.forward.ewp.api.client.config.ClientConfiguration;
import pt.ulisboa.forward.ewp.api.client.dto.CoursesApiSpecificationResponseDTO;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;
import pt.ulisboa.forward.ewp.api.client.factory.ApiClientFactory;

class CoursesV0ApiTest extends AbstractTest {

  @Test
  public void testGetApiSpecification_ValidRequest_Success() throws JAXBException {
    String heiId = "demo";

    CoursesApiSpecificationResponseDTO coursesApiSpecificationResponseDTO =
        new CoursesApiSpecificationResponseDTO(1, 2);
    ResponseWithDataDto<CoursesApiSpecificationResponseDTO> responseBody =
        ResponseWithDataDto.createWithoutMessages(coursesApiSpecificationResponseDTO);

    MockClient mockClient =
        new MockClient()
            .ok(
                HttpMethod.GET,
                "/api/forward/ewp/courses/v0/specification?hei_id=" + heiId,
                marshallToXml(responseBody));

    ClientConfiguration.configure("", "", "secret");
    CoursesV0Api client =
        ApiClientFactory.createClient(mockClient, new MockTarget<>(CoursesV0Api.class));

    ResponseWithDataDto<CoursesApiSpecificationResponseDTO> response =
        client.getApiSpecification(heiId);
    assertThat(response).isNotNull();
    assertThat(response.getDataObject()).isNotNull();
    assertThat(response.getDataObject().getMaxLosIds()).isEqualTo(1);
    assertThat(response.getDataObject().getMaxLosCodes()).isEqualTo(2);
  }

  @Test
  public void testGetMaxLosIdsPerRequest_ValidRequest_Success() throws JAXBException {
    String heiId = "demo";

    CoursesApiSpecificationResponseDTO coursesApiSpecificationResponseDTO =
        new CoursesApiSpecificationResponseDTO(1, 2);
    ResponseWithDataDto<CoursesApiSpecificationResponseDTO> responseBody =
        ResponseWithDataDto.createWithoutMessages(coursesApiSpecificationResponseDTO);

    MockClient mockClient =
        new MockClient()
            .ok(
                HttpMethod.GET,
                "/api/forward/ewp/courses/v0/specification?hei_id=" + heiId,
                marshallToXml(responseBody));

    ClientConfiguration.configure("", "", "secret");
    CoursesV0Api client =
        ApiClientFactory.createClient(mockClient, new MockTarget<>(CoursesV0Api.class));

    assertThat(client.getMaxLosIdsPerRequest(heiId)).isEqualTo(1);
  }

  @Test
  public void testGetMaxLosCodesPerRequest_ValidRequest_Success() throws JAXBException {
    String heiId = "demo";

    CoursesApiSpecificationResponseDTO coursesApiSpecificationResponseDTO =
        new CoursesApiSpecificationResponseDTO(1, 2);
    ResponseWithDataDto<CoursesApiSpecificationResponseDTO> responseBody =
        ResponseWithDataDto.createWithoutMessages(coursesApiSpecificationResponseDTO);

    MockClient mockClient =
        new MockClient()
            .ok(
                HttpMethod.GET,
                "/api/forward/ewp/courses/v0/specification?hei_id=" + heiId,
                marshallToXml(responseBody));

    ClientConfiguration.configure("", "", "secret");
    CoursesV0Api client =
        ApiClientFactory.createClient(mockClient, new MockTarget<>(CoursesV0Api.class));

    assertThat(client.getMaxLosCodesPerRequest(heiId)).isEqualTo(2);
  }

  @Test
  public void testFindByLosIds_ValidRequest_Success() throws JAXBException {
    String heiId = "demo";
    String losId1 = "abc";
    String losId2 = "def";

    CoursesResponseV0 coursesResponse = new CoursesResponseV0();
    LearningOpportunitySpecification los1 =
        new CoursesResponseV0.LearningOpportunitySpecification();
    los1.setLosId(losId1);
    coursesResponse.getLearningOpportunitySpecification().add(los1);
    LearningOpportunitySpecification los2 =
        new CoursesResponseV0.LearningOpportunitySpecification();
    los2.setLosId(losId2);
    coursesResponse.getLearningOpportunitySpecification().add(los2);
    ResponseWithDataDto<CoursesResponseV0> responseBody =
        ResponseWithDataDto.createWithoutMessages(coursesResponse);

    MockClient mockClient =
        new MockClient()
            .ok(HttpMethod.POST, "/api/forward/ewp/courses/v0", marshallToXml(responseBody));

    ClientConfiguration.configure("", "", "secret");
    CoursesV0Api client =
        ApiClientFactory.createClient(mockClient, new MockTarget<>(CoursesV0Api.class));

    ResponseWithDataDto<CoursesResponseV0> response =
        client.findByLosIds(heiId, Arrays.asList(losId1, losId2));
    assertThat(response).isNotNull();
    assertThat(response.getDataObject()).isNotNull();
    assertThat(response.getDataObject().getLearningOpportunitySpecification()).hasSize(2);
    assertThat(response.getDataObject().getLearningOpportunitySpecification().get(0).getLosId())
        .isEqualTo(losId1);
    assertThat(response.getDataObject().getLearningOpportunitySpecification().get(1).getLosId())
        .isEqualTo(losId2);
  }

  @Test
  public void testFindByCodes_ValidRequest_Success() throws JAXBException {
    String heiId = "demo";
    String losCode1 = "abc";
    String losCode2 = "def";

    CoursesResponseV0 coursesResponse = new CoursesResponseV0();
    LearningOpportunitySpecification los1 =
        new CoursesResponseV0.LearningOpportunitySpecification();
    los1.setLosCode(losCode1);
    coursesResponse.getLearningOpportunitySpecification().add(los1);
    LearningOpportunitySpecification los2 =
        new CoursesResponseV0.LearningOpportunitySpecification();
    los2.setLosCode(losCode2);
    coursesResponse.getLearningOpportunitySpecification().add(los2);
    ResponseWithDataDto<CoursesResponseV0> responseBody =
        ResponseWithDataDto.createWithoutMessages(coursesResponse);

    MockClient mockClient =
        new MockClient()
            .ok(HttpMethod.POST, "/api/forward/ewp/courses/v0", marshallToXml(responseBody));

    ClientConfiguration.configure("", "", "secret");
    CoursesV0Api client =
        ApiClientFactory.createClient(mockClient, new MockTarget<>(CoursesV0Api.class));

    ResponseWithDataDto<CoursesResponseV0> response =
        client.findByLosCodes(heiId, Arrays.asList(losCode1, losCode2));
    assertThat(response).isNotNull();
    assertThat(response.getDataObject()).isNotNull();
    assertThat(response.getDataObject().getLearningOpportunitySpecification()).hasSize(2);
    assertThat(response.getDataObject().getLearningOpportunitySpecification().get(0).getLosCode())
        .isEqualTo(losCode1);
    assertThat(response.getDataObject().getLearningOpportunitySpecification().get(1).getLosCode())
        .isEqualTo(losCode2);
  }
}
