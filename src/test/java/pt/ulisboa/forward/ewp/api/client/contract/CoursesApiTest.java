package pt.ulisboa.forward.ewp.api.client.contract;

import static org.assertj.core.api.Assertions.assertThat;

import eu.erasmuswithoutpaper.api.courses.CoursesResponse;
import eu.erasmuswithoutpaper.api.courses.CoursesResponse.LearningOpportunitySpecification;
import feign.mock.HttpMethod;
import feign.mock.MockClient;
import feign.mock.MockTarget;
import java.util.Arrays;
import javax.xml.bind.JAXBException;
import org.junit.jupiter.api.Test;
import pt.ulisboa.forward.ewp.api.client.AbstractTest;
import pt.ulisboa.forward.ewp.api.client.config.ClientConfiguration;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;
import pt.ulisboa.forward.ewp.api.client.factory.ApiClientFactory;

class CoursesApiTest extends AbstractTest {

  @Test
  public void testFindByLosIds_ValidRequest_Success() throws JAXBException {
    String heiId = "demo";
    String losId1 = "abc";
    String losId2 = "def";

    CoursesResponse coursesResponse = new CoursesResponse();
    LearningOpportunitySpecification los1 = new CoursesResponse.LearningOpportunitySpecification();
    los1.setLosId(losId1);
    coursesResponse.getLearningOpportunitySpecification().add(los1);
    LearningOpportunitySpecification los2 = new CoursesResponse.LearningOpportunitySpecification();
    los2.setLosId(losId2);
    coursesResponse.getLearningOpportunitySpecification().add(los2);
    ResponseWithDataDto<CoursesResponse> responseBody =
        ResponseWithDataDto.createWithoutMessages(coursesResponse);

    MockClient mockClient =
        new MockClient()
            .ok(HttpMethod.POST, "/rest/forward/ewp/courses", marshallToXml(responseBody));

    ClientConfiguration.configure("", "", "secret");
    CoursesApi client =
        ApiClientFactory.createClient(mockClient, new MockTarget<>(CoursesApi.class));

    ResponseWithDataDto<CoursesResponse> response =
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

    CoursesResponse coursesResponse = new CoursesResponse();
    LearningOpportunitySpecification los1 = new CoursesResponse.LearningOpportunitySpecification();
    los1.setLosCode(losCode1);
    coursesResponse.getLearningOpportunitySpecification().add(los1);
    LearningOpportunitySpecification los2 = new CoursesResponse.LearningOpportunitySpecification();
    los2.setLosCode(losCode2);
    coursesResponse.getLearningOpportunitySpecification().add(los2);
    ResponseWithDataDto<CoursesResponse> responseBody =
        ResponseWithDataDto.createWithoutMessages(coursesResponse);

    MockClient mockClient =
        new MockClient()
            .ok(HttpMethod.POST, "/rest/forward/ewp/courses", marshallToXml(responseBody));

    ClientConfiguration.configure("", "", "secret");
    CoursesApi client =
        ApiClientFactory.createClient(mockClient, new MockTarget<>(CoursesApi.class));

    ResponseWithDataDto<CoursesResponse> response =
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
