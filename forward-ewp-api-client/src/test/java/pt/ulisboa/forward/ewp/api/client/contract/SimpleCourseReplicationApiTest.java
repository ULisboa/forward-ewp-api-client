package pt.ulisboa.forward.ewp.api.client.contract;

import static org.assertj.core.api.Assertions.assertThat;

import eu.erasmuswithoutpaper.api.courses.replication.CourseReplicationResponse;
import feign.mock.HttpMethod;
import feign.mock.MockClient;
import feign.mock.MockTarget;
import java.util.Arrays;
import java.util.List;
import javax.xml.bind.JAXBException;
import org.junit.jupiter.api.Test;
import pt.ulisboa.forward.ewp.api.client.AbstractTest;
import pt.ulisboa.forward.ewp.api.client.config.ClientConfiguration;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;
import pt.ulisboa.forward.ewp.api.client.factory.ApiClientFactory;

class SimpleCourseReplicationApiTest extends AbstractTest {

  @Test
  public void testFindAllByIds_ValidRequest_Success() throws JAXBException {
    String heiId = "demo";
    List<String> losIds = Arrays.asList("abc", "def");

    CourseReplicationResponse courseReplicationResponse = new CourseReplicationResponse();
    courseReplicationResponse.getLosId().addAll(losIds);
    ResponseWithDataDto<CourseReplicationResponse> responseBody =
        ResponseWithDataDto.createWithoutMessages(courseReplicationResponse);

    MockClient mockClient =
        new MockClient()
            .ok(
                HttpMethod.GET,
                "/rest/forward/ewp/courses/replication?hei_id=" + heiId,
                marshallToXml(responseBody));

    ClientConfiguration.configure("", "", "secret");
    SimpleCourseReplicationApi client =
        ApiClientFactory.createClient(
            mockClient, new MockTarget<>(SimpleCourseReplicationApi.class));

    ResponseWithDataDto<CourseReplicationResponse> response = client.findAllByHeiId(heiId);
    assertThat(response).isNotNull();
    assertThat(response.getDataObject()).isNotNull();
    assertThat(response.getDataObject().getLosId()).isEqualTo(losIds);
  }
}
