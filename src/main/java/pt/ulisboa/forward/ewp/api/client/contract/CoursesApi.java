package pt.ulisboa.forward.ewp.api.client.contract;

import eu.erasmuswithoutpaper.api.courses.CoursesResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import java.time.LocalDate;
import java.util.List;
import pt.ulisboa.forward.ewp.api.client.dto.CoursesApiSpecificationResponseDTO;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;

/**
 * Contract interface for the Courses Forward EWP API.
 *
 * @see <a
 *     href="https://github.com/erasmus-without-paper/ewp-specs-api-courses/blob/master/response.xsd">CoursesResponse
 *     specification (element courses-response)</a>
 */
public interface CoursesApi extends BaseApi {

  /**
   * Returns the specification of the target API, including the maximum number of LOS IDs and codes
   * accepted in any given request for a specific HEI ID.
   *
   * @param heiId HEI ID of an institution.
   * @return A response whose data contains the maximum number of LOS IDs and codes accepted in any
   *     given request for a specific HEI ID.
   */
  @RequestLine("GET /rest/forward/ewp/courses/specification?hei_id={hei_id}")
  ResponseWithDataDto<CoursesApiSpecificationResponseDTO> getApiSpecification(
      @Param("hei_id") String heiId);

  /**
   * Returns the maximum number of LOS IDs that may be requested in a given request to the specified
   * HEI ID.
   *
   * @param heiId HEI ID of an institution.
   * @return Maximum number of LOS IDs that the HEI accepts in a request.
   */
  default int getMaxLosIdsPerRequest(String heiId) {
    return getApiSpecification(heiId).getDataObject().getMaxLosIds();
  }

  /**
   * Returns the maximum number of LOS codes that may be requested in a given request to the
   * specified HEI ID.
   *
   * @param heiId HEI ID of an institution.
   * @return Maximum number of LOS codes that the HEI accepts in a request.
   */
  default int getMaxLosCodesPerRequest(String heiId) {
    return getApiSpecification(heiId).getDataObject().getMaxLosCodes();
  }

  /** @requires losIds.size() <= getMaxLosIdsPerRequest(heiId) */
  @RequestLine("POST /rest/forward/ewp/courses")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseWithDataDto<CoursesResponse> findByLosIds(
      @Param("hei_id") String heiId,
      @Param("los_id") List<String> losIds,
      @Param("lois_before") LocalDate loisBefore,
      @Param("lois_after") LocalDate loisAfter,
      @Param("lois_at_date") LocalDate loisAtDate);

  /** @requires losIds.size() <= getMaxLosIdsPerRequest(heiId) */
  default ResponseWithDataDto<CoursesResponse> findByLosIds(String heiId, List<String> losIds) {
    return findByLosIds(heiId, losIds, null, null, null);
  }

  /** @requires losCodes.size() <= getMaxLosCodesPerRequest(heiId) */
  @RequestLine("POST /rest/forward/ewp/courses")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseWithDataDto<CoursesResponse> findByLosCodes(
      @Param("hei_id") String heiId,
      @Param("los_code") List<String> losCodes,
      @Param("lois_before") LocalDate loisBefore,
      @Param("lois_after") LocalDate loisAfter,
      @Param("lois_at_date") LocalDate loisAtDate);

  /** @requires losCodes.size() <= getMaxLosCodesPerRequest(heiId) */
  default ResponseWithDataDto<CoursesResponse> findByLosCodes(String heiId, List<String> losCodes) {
    return findByLosCodes(heiId, losCodes, null, null, null);
  }
}
