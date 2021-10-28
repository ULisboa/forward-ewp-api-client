package pt.ulisboa.forward.ewp.api.client.contract.imobilities.tors;

import eu.erasmuswithoutpaper.api.imobilities.tors.v1.endpoints.ImobilityTorsGetResponseV1;
import eu.erasmuswithoutpaper.api.imobilities.tors.v1.endpoints.ImobilityTorsIndexResponseV1;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import java.time.ZonedDateTime;
import java.util.List;
import pt.ulisboa.forward.ewp.api.client.contract.BaseApi;
import pt.ulisboa.forward.ewp.api.client.dto.ResponseWithDataDto;
import pt.ulisboa.forward.ewp.api.client.dto.imobilities.tors.IncomingMobilityToRsApiSpecificationResponseDTO;

/**
 * Contract interface for the Outgoing Mobility ToRs V1 Forward EWP API.
 */
public interface IncomingMobilityToRsV1Api extends BaseApi {

  /**
   * Returns the specification of the target API, including the maximum number of Outgoing
   * Mobilities IDs accepted in any given request for a specific HEI ID.
   *
   * @param heiId HEI ID of an institution.
   * @return A response whose data contains the maximum number of Outgoing Mobilities IDs accepted
   * in any given request for a specific HEI ID.
   */
  @RequestLine("GET /api/forward/ewp/omobilities/las/v1/specification?hei_id={hei_id}")
  ResponseWithDataDto<IncomingMobilityToRsApiSpecificationResponseDTO> getApiSpecification(
      @Param("hei_id") String heiId);

  /**
   * Returns the maximum number of Outgoing Mobilities IDs that may be requested in a given request
   * to the specified HEI ID.
   *
   * @param heiId HEI ID of an institution.
   * @return Maximum number of Outgoing Mobilities IDs that the HEI accepts in a request.
   */
  default int getMaxOmobilityIdsPerRequest(String heiId) {
    return getApiSpecification(heiId).getDataObject().getMaxOmobilityIds();
  }

  @RequestLine("POST /api/forward/ewp/imobilities/tors/v1/index")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseWithDataDto<ImobilityTorsIndexResponseV1> findOutgoingMobilityIdsWithTranscriptOfRecord(
      @Param("receiving_hei_id") String receivingHeiId,
      @Param("sending_hei_id") List<String> sendingHeiIds,
      @Param("modified_since") ZonedDateTime modifiedSince);

  /**
   * @requires outgoingMobilityIds.size() <= getMaxOmobilityIdsPerRequest(heiId)
   */
  @RequestLine("POST /api/forward/ewp/imobilities/tors/v1/get")
  @Headers("Content-Type: application/x-www-form-urlencoded")
  ResponseWithDataDto<ImobilityTorsGetResponseV1> findByReceivingHeiIdAndOutgoingMobilityIds(
      @Param("receiving_hei_id") String receivingHeiId,
      @Param("omobility_id") List<String> outgoingMobilityIds);
}
