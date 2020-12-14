package pt.ulisboa.forward.ewp.api.client.contract.ounits;

import eu.erasmuswithoutpaper.api.ounits.v2.OunitsResponseV2;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import java.util.List;
import pt.ulisboa.forward.ewp.api.client.contract.BaseApi;
import pt.ulisboa.forward.ewp.api.client.dto.OrganizationalUnitsApiSpecificationResponseDTO;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;

/**
 * Contract interface for the Organizational Units V2 Forward EWP API.
 *
 * @see <a
 *     href="https://github.com/erasmus-without-paper/ewp-specs-api-ounits/blob/stable-v2/response.xsd">OunitsResponse
 *     specification (element ounits-response)</a>
 */
public interface OrganizationalUnitsV2Api extends BaseApi {

  /**
   * Returns the specification of the target API, including the maximum number of Organizational
   * Unit IDs and codes accepted in any given request for a specific HEI ID.
   *
   * @param heiId HEI ID of an institution.
   * @return A response whose data contains the maximum number of Organizational Unit IDs and codes
   *     accepted in any given request for a specific HEI ID.
   */
  @RequestLine("GET /api/forward/ewp/ounits/v2/specification?hei_id={hei_id}")
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
  @RequestLine("POST /api/forward/ewp/ounits/v2")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseWithDataDto<OunitsResponseV2> findByIds(
      @Param("hei_id") String heiId, @Param("ounit_id") List<String> ounitIds);

  /** @requires ounitCodes.size() <= getMaxOunitCodesPerRequest(heiId) */
  @RequestLine("POST /api/forward/ewp/ounits/v2")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseWithDataDto<OunitsResponseV2> findByCodes(
      @Param("hei_id") String heiId, @Param("ounit_code") List<String> ounitCodes);
}
