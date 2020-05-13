package pt.ulisboa.forward.ewp.api.client.contract;

import eu.erasmuswithoutpaper.api.ounits.OunitsResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import java.util.List;
import pt.ulisboa.forward.ewp.api.client.dto.OrganizationalUnitsApiSpecificationResponseDTO;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;

/**
 * Contract interface for the Organizational Units Forward EWP API.
 *
 * @see <a
 *     href="https://github.com/erasmus-without-paper/ewp-specs-api-ounits/blob/stable-v2/response.xsd">OunitsResponse
 *     specification (element ounits-response)</a>
 */
public interface OrganizationalUnitsApi extends BaseApi {

  @RequestLine("GET /rest/forward/ewp/ounits/specification?hei_id={hei_id}")
  ResponseWithDataDto<OrganizationalUnitsApiSpecificationResponseDTO> getApiSpecification(
      @Param("hei_id") String heiId);

  /**
   * Returns the maximum number of Organizational Unit IDs that may be requested in a given request
   * to the specified HEI ID.
   *
   * @param heiId HEI ID of an institution.
   * @return Maximum number of Organizational Unit IDs that the HEI accepts in a request.
   */
  default int getMaxOunitIdsPerRequest(String heiId) {
    return getApiSpecification(heiId).getDataObject().getMaxOunitIds();
  }

  /**
   * Returns the maximum number of Organizational Unit codes that may be requested in a given
   * request to the specified HEI ID.
   *
   * @param heiId HEI ID of an institution.
   * @return Maximum number of Organizational Unit codes that the HEI accepts in a request.
   */
  default int getMaxOunitCodesPerRequest(String heiId) {
    return getApiSpecification(heiId).getDataObject().getMaxOunitCodes();
  }

  /** @requires ounitIds.size() <= getMaxOunitIdsPerRequest(heiId) */
  @RequestLine("POST /rest/forward/ewp/ounits")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseWithDataDto<OunitsResponse> findByIds(
      @Param("hei_id") String heiId, @Param("ounit_id") List<String> ounitIds);

  /** @requires ounitCodes.size() <= getMaxOunitCodesPerRequest(heiId) */
  @RequestLine("POST /rest/forward/ewp/ounits")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseWithDataDto<OunitsResponse> findByCodes(
      @Param("hei_id") String heiId, @Param("ounit_code") List<String> ounitCodes);
}
