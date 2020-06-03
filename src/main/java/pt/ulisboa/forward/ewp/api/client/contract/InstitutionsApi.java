package pt.ulisboa.forward.ewp.api.client.contract;

import eu.erasmuswithoutpaper.api.institutions.InstitutionsResponse;
import feign.Param;
import feign.RequestLine;
import pt.ulisboa.forward.ewp.api.client.dto.HeiIdsResponseDTO;
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
   * Returns all HEI IDs registered on the EWP Registry.
   *
   * @return A response whose data is the collection of all HEI IDs registered on the EWP Registry.
   */
  @RequestLine("GET /api/forward/ewp/institutions/hei-ids")
  ResponseWithDataDto<HeiIdsResponseDTO> getAllHeiIds();

  /**
   * Returns the institution data for a given HEI ID.
   *
   * @param heiId HEI ID of an institution.
   * @return A response whose data is the institution details.
   */
  @RequestLine("GET /api/forward/ewp/institutions?hei_id={heiId}")
  ResponseWithDataDto<InstitutionsResponse> findByHeiId(@Param("heiId") String heiId);
}
