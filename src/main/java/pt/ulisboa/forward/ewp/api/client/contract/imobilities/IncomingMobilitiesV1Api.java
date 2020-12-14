package pt.ulisboa.forward.ewp.api.client.contract.imobilities;

import eu.erasmuswithoutpaper.api.iias.v3.endpoints.IiasGetResponseV3;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import java.util.List;
import pt.ulisboa.forward.ewp.api.client.contract.BaseApi;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;
import pt.ulisboa.forward.ewp.api.client.dto.imobilities.IncomingMobilitiesApiSpecificationResponseDTO;

/** Contract interface for the Incoming Mobilities V1 Forward EWP API. */
public interface IncomingMobilitiesV1Api extends BaseApi {

  /**
   * Returns the specification of the target API, including the maximum number of Outgoing
   * Mobilities IDs accepted in any given request for a specific HEI ID.
   *
   * @param heiId HEI ID of an institution.
   * @return A response whose data contains the maximum number of Outgoing Mobilities IDs and codes
   *     accepted in any given request for a specific HEI ID.
   */
  @RequestLine("GET /api/forward/ewp/iias/v3/specification?hei_id={hei_id}")
  ResponseWithDataDto<IncomingMobilitiesApiSpecificationResponseDTO> getApiSpecification(
      @Param("hei_id") String heiId);

  /**
   * Returns the maximum number of Outgoing Mobility IDs that may be requested in a given request to
   * the specified HEI ID.
   *
   * @param heiId HEI ID of an institution.
   * @return Maximum number of Outgoing Mobility IDs that the HEI accepts in a request.
   */
  default int getMaxOutgoingMobilityIdsPerRequest(String heiId) {
    return getApiSpecification(heiId).getDataObject().getMaxOmobilityIds();
  }

  /** @requires omobilityIds.size() <= getMaxOutgoingMobilityIdsPerRequest(heiId) */
  @RequestLine("POST /api/forward/ewp/imobilities/v1/get")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseWithDataDto<IiasGetResponseV3> findByReceivingHeiIdAndOmobilityIds(
      @Param("receiving_hei_id") String receivingHeiId,
      @Param("omobility_id") List<String> omobilityIds);
}
