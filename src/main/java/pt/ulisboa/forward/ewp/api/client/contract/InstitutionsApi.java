package pt.ulisboa.forward.ewp.api.client.contract;

import eu.erasmuswithoutpaper.api.institutions.InstitutionsResponse;
import feign.Param;
import feign.RequestLine;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;

/**
 * Contract interface for the Institutions Forward EWP API.
 *
 * @see <a
 *     href="https://github.com/erasmus-without-paper/ewp-specs-api-institutions/blob/stable-v2/response.xsd">InstitutionsResponse
 *     specification (element institutions-response)</a>
 */
public interface InstitutionsApi extends BaseApi {

  /**
   * Returns the institution data for a given HEI ID.
   *
   * @param heiId HEI ID of an institution.
   * @return A response whose data is the institution details.
   */
  @RequestLine("GET /rest/forward/ewp/institutions?hei_id={heiId}")
  ResponseWithDataDto<InstitutionsResponse> findByHeiId(@Param("heiId") String heiId);
}
