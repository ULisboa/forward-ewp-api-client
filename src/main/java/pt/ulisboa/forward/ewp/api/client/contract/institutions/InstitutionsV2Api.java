package pt.ulisboa.forward.ewp.api.client.contract.institutions;

import eu.erasmuswithoutpaper.api.institutions.v2.InstitutionsResponseV2;
import feign.Param;
import feign.RequestLine;
import pt.ulisboa.forward.ewp.api.client.contract.BaseApi;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;

/**
 * Contract interface for the Institutions Forward V2 EWP API.
 *
 * @see <a
 *     href="https://github.com/erasmus-without-paper/ewp-specs-api-institutions/blob/stable-v2/response.xsd">InstitutionsResponse
 *     specification (element institutions-response)</a>
 */
public interface InstitutionsV2Api extends BaseApi {

  /**
   * Returns the institution data for a given HEI ID.
   *
   * @param heiId HEI ID of an institution.
   * @return A response whose data is the institution details.
   */
  @RequestLine("GET /api/forward/ewp/institutions/v2?hei_id={heiId}")
  ResponseWithDataDto<InstitutionsResponseV2> findByHeiId(@Param("heiId") String heiId);
}
