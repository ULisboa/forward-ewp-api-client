package pt.ulisboa.forward.ewp.api.client.contract.courses.replication;

import eu.erasmuswithoutpaper.api.courses.replication.v1.CourseReplicationResponseV1;
import feign.Param;
import feign.RequestLine;
import pt.ulisboa.forward.ewp.api.client.contract.BaseApi;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;

/**
 * Contract interface for the Simple Course Replication Forward EWP API.
 *
 * @see <a
 *     href="https://github.com/erasmus-without-paper/ewp-specs-api-course-replication/blob/master/response.xsd">SimpleCourseReplication
 *     specification (element course-replication-response)</a>
 */
public interface SimpleCourseReplicationV1Api extends BaseApi {

  /**
   * Returns a list of courses that a given HEI ID possesses.
   *
   * @param heiId HEI ID of an institution.
   * @return A response whose data is the list of courses possessed by the institution.
   */
  @RequestLine("GET /api/forward/ewp/courses/replication/v1?hei_id={heiId}")
  ResponseWithDataDto<CourseReplicationResponseV1> findAllByHeiId(@Param("heiId") String heiId);
}
