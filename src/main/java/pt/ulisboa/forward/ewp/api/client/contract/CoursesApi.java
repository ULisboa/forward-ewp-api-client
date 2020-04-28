package pt.ulisboa.forward.ewp.api.client.contract;

import eu.erasmuswithoutpaper.api.courses.CoursesResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import java.time.LocalDate;
import java.util.List;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;

/**
 * Contract interface for the Courses Forward EWP API.
 *
 * @see <a
 *     href="https://github.com/erasmus-without-paper/ewp-specs-api-courses/blob/master/response.xsd">CoursesResponse
 *     specification (element courses-response)</a>
 */
public interface CoursesApi extends BaseApi {

  @RequestLine("POST /rest/forward/ewp/courses")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseWithDataDto<CoursesResponse> findByLosIds(
      @Param("hei_id") String heiId,
      @Param("los_id") List<String> losIds,
      @Param("lois_before") LocalDate loisBefore,
      @Param("lois_after") LocalDate loisAfter,
      @Param("lois_at_date") LocalDate loisAtDate);

  default ResponseWithDataDto<CoursesResponse> findByLosIds(String heiId, List<String> losIds) {
    return findByLosIds(heiId, losIds, null, null, null);
  }

  @RequestLine("POST /rest/forward/ewp/courses")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseWithDataDto<CoursesResponse> findByLosCodes(
      @Param("hei_id") String heiId,
      @Param("los_code") List<String> losCodes,
      @Param("lois_before") LocalDate loisBefore,
      @Param("lois_after") LocalDate loisAfter,
      @Param("lois_at_date") LocalDate loisAtDate);

  default ResponseWithDataDto<CoursesResponse> findByLosCodes(String heiId, List<String> losCodes) {
    return findByLosCodes(heiId, losCodes, null, null, null);
  }
}
