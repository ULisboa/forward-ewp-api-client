package pt.ulisboa.forward.ewp.api.client.contract.courses.replication;

import static org.assertj.core.api.Assertions.assertThat;

import eu.erasmuswithoutpaper.api.courses.replication.v1.CourseReplicationResponseV1;
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

class SimpleCourseReplicationV1ApiTest extends AbstractTest {

  @Test
  public void testFindAllByIds_ValidRequest_Success() throws JAXBException {
    String heiId = "demo";
    List<String> losIds = Arrays.asList("abc", "def");

    CourseReplicationResponseV1 courseReplicationResponse = new CourseReplicationResponseV1();
    courseReplicationResponse.getLosId().addAll(losIds);
    ResponseWithDataDto<CourseReplicationResponseV1> responseBody =
        ResponseWithDataDto.createWithoutMessages(courseReplicationResponse);

    MockClient mockClient =
        new MockClient()
            .ok(
                HttpMethod.GET,
                "/api/forward/ewp/courses/replication/v1?hei_id=" + heiId,
                marshallToXml(responseBody));

    ClientConfiguration.configure("", "", "secret");
    SimpleCourseReplicationV1Api client =
        ApiClientFactory.createClient(
            mockClient, new MockTarget<>(SimpleCourseReplicationV1Api.class));

    ResponseWithDataDto<CourseReplicationResponseV1> response = client.findAllByHeiId(heiId);
    assertThat(response).isNotNull();
    assertThat(response.getDataObject()).isNotNull();
    assertThat(response.getDataObject().getLosId()).isEqualTo(losIds);
  }
}
